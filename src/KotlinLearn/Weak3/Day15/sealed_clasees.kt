package KotlinLearn.Weak3.Day15

import java.io.IOException


// ===== SEALED CLASS FOR UI STATE =====
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String, val code: Int? = null) : UiState<Nothing>()

    // Helper methods
    fun isLoading(): Boolean = this is Loading
    fun isSuccess(): Boolean = this is Success
    fun isError(): Boolean = this is Error

    fun getDataOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }
}

// ===== SEALED CLASS FOR EVENTS =====
sealed class UserEvent {
    data class NavigateToProfile(val userId: Int) : UserEvent()
    data class ShowToast(val message: String) : UserEvent()
    object Logout : UserEvent()
    data class OpenUrl(val url: String) : UserEvent()
}

// ===== SEALED CLASS FOR NETWORK RESPONSES =====
sealed class NetworkResponse<out T> {
    data class Success<T>(val data: T, val timestamp: Long = System.currentTimeMillis()) : NetworkResponse<T>()
    data class Failure(val exception: Exception, val retryable: Boolean = true) : NetworkResponse<Nothing>()
    object Unauthorized : NetworkResponse<Nothing>()
    object NoInternet : NetworkResponse<Nothing>()

    fun isSuccessful(): Boolean = this is Success
    fun getDataOrThrow(): T = when (this) {
        is Success -> data
        is Failure -> throw exception
        Unauthorized -> throw SecurityException("Unauthorized")
        NoInternet -> throw IOException("No internet connection")
    }
}

// ===== DATA CLASSES =====
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val role: UserRole
)

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String
)

// ===== REPOSITORY WITH SEALED CLASSES =====
class UserRepository {
    private val users = listOf(
        User(1, "Alice Johnson", "alice@email.com", UserRole.ADMIN),
        User(2, "Bob Smith", "bob@email.com", UserRole.EDITOR),
        User(3, "Charlie Brown", "charlie@email.com", UserRole.VIEWER)
    )

    suspend fun fetchUser(id: Int): NetworkResponse<User> {
        return try {
            // Simulate network delay
            kotlinx.coroutines.delay(1000)

            val user = users.find { it.id == id }
            if (user != null) {
                NetworkResponse.Success(user)
            } else {
                NetworkResponse.Failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            NetworkResponse.Failure(e)
        }
    }

    suspend fun fetchAllUsers(): NetworkResponse<List<User>> {
        return try {
            kotlinx.coroutines.delay(1000)
            NetworkResponse.Success(users)
        } catch (e: Exception) {
            NetworkResponse.Failure(e)
        }
    }
}

// ===== VIEWMODEL WITH UI STATE =====
class UserViewModel(private val repository: UserRepository) {
    private val _uiState = mutableStateOf<UiState<User>>(UiState.Idle)
    val uiState: State<UiState<User>> = _uiState

    private val _events = mutableSharedFlow<UserEvent>()
    val events: SharedFlow<UserEvent> = _events.asSharedFlow()

    fun loadUser(id: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            when (val response = repository.fetchUser(id)) {
                is NetworkResponse.Success -> {
                    _uiState.value = UiState.Success(response.data)
                    _events.emit(UserEvent.ShowToast("User loaded successfully"))
                }
                is NetworkResponse.Failure -> {
                    _uiState.value = UiState.Error(response.exception.message ?: "Unknown error")
                    if (response.retryable) {
                        _events.emit(UserEvent.ShowToast("Retryable error: ${response.exception.message}"))
                    }
                }
                NetworkResponse.Unauthorized -> {
                    _uiState.value = UiState.Error("Unauthorized access")
                    _events.emit(UserEvent.Logout)
                }
                NetworkResponse.NoInternet -> {
                    _uiState.value = UiState.Error("No internet connection")
                }
            }
        }
    }

    fun onUserClick(userId: Int) {
        viewModelScope.launch {
            _events.emit(UserEvent.NavigateToProfile(userId))
        }
    }
}

// ===== COMPOSE UI USING SEALED CLASSES =====
@Composable
fun UserScreen(viewModel: UserViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle events
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UserEvent.NavigateToProfile -> {
                    // Navigate to profile screen
                    navController.navigate("profile/${event.userId}")
                }
                is UserEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                UserEvent.Logout -> {
                    // Clear user data and navigate to login
                    clearUserData()
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
                is UserEvent.OpenUrl -> {
                    // Open URL in browser
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.url))
                    context.startActivity(intent)
                }
            }
        }
    }

    // Render UI based on state
    when (uiState) {
        is UiState.Idle -> {
            Text("Welcome! Press button to load user")
            Button(onClick = { viewModel.loadUser(1) }) {
                Text("Load User")
            }
        }
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                Text("Loading user data...")
            }
        }
        is UiState.Success -> {
            val user = (uiState as UiState.Success<User>).data
            UserProfileCard(
                user = user,
                onUserClick = { viewModel.onUserClick(user.id) }
            )
        }
        is UiState.Error -> {
            val error = uiState as UiState.Error
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("❌ ${error.message}", color = Color.Red)
                Button(onClick = { viewModel.loadUser(1) }) {
                    Text("Retry")
                }
            }
        }
    }
}

@Composable
fun UserProfileCard(user: User, onUserClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onUserClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = user.name, fontWeight = FontWeight.Bold)
            Text(text = user.email)
            Text(text = "Role: ${user.role}", color = Color.Gray)
        }
    }
}