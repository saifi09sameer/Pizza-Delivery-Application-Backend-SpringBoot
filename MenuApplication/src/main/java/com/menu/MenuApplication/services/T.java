package com.menu.MenuApplication.services;

public class T {
    public static void main(String[] args) {
        int arr[] = {10,20,30};
        int max = 0;
        for (int index = 0; index < arr.length; index++) {
            if (arr[index]>max){
                max=arr[index];
            }
        }
    }
}
