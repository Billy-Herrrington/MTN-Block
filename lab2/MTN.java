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

        System.out.println("\nhash1 = " + hash1 + "\nhash2 = " + hash2);

        //Демонтрация серию блоков в цепочке в новой классе Block
        //First block is mineBlock
        ArrayList<Block> blockChain = new ArrayList<>();
        String[] initialValues = {"Mai has mining 0.000260202 MTN", "Max has mining 0.000260202 MTN"};
        Block firstBlock = new Block(initialValues, 0);
        blockChain.add(firstBlock);
        System.out.println("\nFirst block is " + firstBlock.toString());
        System.out.println("The Block chain is " + blockChain.toString());

        //Block two
        String[] GivesPrise = {"Mai gives Max 0.000260202 MTN" , "Max gives Mai 0.000260202 MTN"};
        Block secondBlock = new Block(GivesPrise, firstBlock.getBlockHash());
        blockChain.add(secondBlock);
        System.out.println("\nblock is " + secondBlock.toString());
        System.out.println("The Block chain is " + blockChain.toString());

    }
}
