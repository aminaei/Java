package TestBench;

/**
 * Created by am on 4/6/16.
 */
import data_structures.BloomFilter;
import data_structures.Hash;

import java.util.Iterator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TB_HashIPAddress {
    static BloomFilter BFilter;
    static int size = 70000;

    static String filename = "/home/am/Classes/CS310/Assignment3/src/data/ip2country.tsv";
    static File file = new File(filename);
    static String [] ipaddr_Table = new String[10000];


    static Hash<String, String> hashTable = new Hash<String, String>(size);

    public static void main(String[] args) {
        System.out.println("Staring Hash Tester....");

        long  start, stop;

        float currPrec = 0;
        float prevPrec = 0;

        start = System.currentTimeMillis();

        try {
            Scanner inputStream = new Scanner(file);
            inputStream.nextLine();
            float i = 0;
            while(inputStream.hasNextLine()){
                // Reading one line
                String data = inputStream.nextLine();

                String [] IPTable = data.split("\t");
                String temp = IPTable[1]+" "+IPTable[2]+" "+IPTable[3];
//                System.out.println("Temp = "+temp);
                hashTable.add(IPTable[0],temp);


                currPrec = (float) ((i/63543)*100.0);

                if((currPrec - prevPrec)>1){
                    System.out.println(" >>>>> "+(int) currPrec+"% is completed <<<<< ");
                    prevPrec = currPrec;
                }


//                if((i % 635)==0)
//                    System.out.println("===> Line Number : " + (int) i + " " + currPrec + " " + prevPrec);

//                if(i==10)
//                    break;
                i++;


            }
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        stop = System.currentTimeMillis();
        System.out.println("Time for Hash Table ADD Operation was : "+(stop-start));

        System.out.println("\n <<<<<<<<<<------------------->>>>>>>>>>  ");
        long  start_retrieve, stop_retrieve;

        int k = 0;


        try{
            Scanner getinputStream = new Scanner(file);
            getinputStream.nextLine();

            while(getinputStream.hasNextLine()) {
                // Reading one line
                String data = getinputStream.nextLine();
                //System.out.println(data);

                String[] temp = data.split("\t");

                ipaddr_Table[k] = temp[0];

                //System.out.println("ipaddr_Table[" + k + "] = " + ipaddr_Table[k]);

                if (k == 10000-1)
                    break;

                k++;
            }
            getinputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        start_retrieve = System.currentTimeMillis();

        for(int j=0;j<10000;j++){

            //System.out.println("ipaddr_Table["+j+"] = "+ipaddr_Table[j]);

            System.out.println(" >>-->>-->> Hash Table Contains : Key["+ipaddr_Table[j]+"] :"+hashTable.getValue(ipaddr_Table[j]));
        }

        stop_retrieve = System.currentTimeMillis();
        System.out.println("Time for Hash Table operation was : "+(stop_retrieve-start_retrieve));




    }

    }
