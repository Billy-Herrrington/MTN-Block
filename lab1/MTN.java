package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class MTN {
    public static void main(String[] args){

        //Демонстрация хэш функции
        String statement1 = "Welcome to the club Boddy!";
        int hashvalue = statement1.hashCode();

        System.out.println("Statement = " + statement1 + " Whose hash value = " + hashvalue);

        //Розшифруємо масив
        String[] list1 = {"Mai", "Tien"};
        String[] list2 = {"Mai", "Tien"};

        int hash1 = Arrays.hashCode(list1);
        int hash2 = Arrays.hashCode(list2);
        
        //Хєширую масиви {"Mai", "Tien"}
        System.out.println("\nhash1 = " + hash1 + "\nhash2 = " + hash2);

    }
}
