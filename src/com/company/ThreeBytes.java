package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ThreeBytes {
  /*   3***. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
   и представляют собой, например, состояния ячеек поля для игры в крестикинолики,
    где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом,
      3 – резервное значение. Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
     Записать в файл 9 значений так, чтобы они заняли три байта
     */
    public static void arrayPack (int[] arr){
        String binary = "";
        for (int i = 0; i<9; i++) {
            String binaryPlus = Integer.toBinaryString(arr[i]);
            if (binaryPlus.equals("0")) binaryPlus += "0";//доводим значение до двузначного
            if (binaryPlus.equals("1")) binaryPlus = "01";// доводим значение до двузначного
            binary += binaryPlus;
            if ((i + 1) % 3 > 0)
                binary += "0";// 0 используем как разделитеоь между двузначными числами внутри будущего байта
        //    System.out.println(binary);//после цикла получилась строка с 24 символами из 0 и 1 как раз на 3 байта
        }
        int[] intArr = new int[3];// сразу в массив байт записать не получается, так как могут быть числа до 256, а в byte влезают от -128 до 127
        intArr[0] = Integer.parseInt(binary.substring(0,8),2);
        intArr[1] = Integer.parseInt(binary.substring(8,16),2);
        intArr[2] = Integer.parseInt(binary.substring(16),2);
          byte [] byteArr = new byte [3];
          for (int i=0 ; i<3; i++){
              byteArr[i]=(byte)(intArr[i] -128); // чтобы число вместилось в byte (-128..127) - необходимо учесть при расшифровке
        }
        try {
            Files.write(Paths.get("encoded.txt"), byteArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
/* код для проверки правильности записи данных.
        try {
           byte[] data = Files.readAllBytes(Paths.get("encoded.txt"));
           for (int i = 0; i<data.length; i++) {
               System.out.println(data[i]);
           }
        } catch (IOException e){
            e.printStackTrace();
        }


         for (int i = 0; i<3; i++) {
            System.out.println(intArr[i]);
            System.out.println(Integer.toBinaryString(intArr[i]));
            System.out.println(byteArr[i]);
            System.out.println(Integer.toBinaryString(byteArr[i]+128));

        }
*/
        }
    }

