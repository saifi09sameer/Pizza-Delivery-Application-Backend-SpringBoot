package com.FoodCard.FoodCardApplication.service;

class Test {
    public static void main(String[] args) {
        int num = 453;
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        System.out.println(sum);
    }


}
