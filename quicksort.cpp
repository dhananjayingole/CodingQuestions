#include<bits/stdc++.h>
using namespace std;

int partitionfunc(vector<int>&arr, int low, int high){
    int pivot = arr[high];
    
    int i = low-1;
    
    for(int j = low; j< high; j++){
        if(arr[j] <= pivot){
            i++;
            swap(arr[i], arr[j]); // will put all ele at correct position.
        }
    }
    //  for placing pivot at correct position
    swap(arr[i+1], arr[high]);
    
    return i+1;
}

void quickSort(vector<int> &arr, int low, int high){
    if(low < high){
        int pivotindex = partitionfunc(arr, low, high);
        
        quickSort(arr, low, pivotindex - 1);
        
        quickSort(arr, pivotindex + 1 , high);
    }
}

int main(){
    int size;
    cout<<"Enter Size:";
    cin>>size;
    
    vector<int> arr(size);
    
    cout<<"Enter Ele:";
    for(int i=0;i<size;i++){
        cin>>arr[i];
    }
    int low = 0;
    int high = arr.size()-1;
    quickSort(arr, low, high);
    
    for(int num: arr){
        cout<< num<<" ";
    }
    return 0;
}
