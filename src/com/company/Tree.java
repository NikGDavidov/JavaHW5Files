package com.company;

import java.io.File;

public class Tree {

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast){
        System.out.print(indent);// рисуем отступ
        String indentFiles = indent;// рисуем отступ
        if (isLast){
            System.out.print("└─");
            indent += "  ";
            indentFiles +="  ";
        }
        else {
            System.out.print("├─");
            indent += "│ ";
            indentFiles +="  ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;

        for (int i = 0; i < files.length; i++){
           if (files[i].isDirectory())
               subDirTotal++;
           else System.out.println(indentFiles + "-" + files[i].getName());
        }


        int subDirCounter = 0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory()){
                print(files[i], indent, subDirCounter  == subDirTotal - 1);
                subDirCounter++;
            }
        }


    }

}
