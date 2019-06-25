package com.steven;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName LogUtils
 * @Description TODO
 * @Author Steven
 * @Date 2019-6-20 9:11
 * @Version 1.0
 */
public class LogUtils {
    //换行符,Windows与Linux有区别
    private final static String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void log(double[] x, String[] y, String path){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < x.length; i++){
            stringBuilder.append(x[i]).append(",").append(y[i]).append(LINE_SEPARATOR);
        }

        FileWriter fw = null;

        try {
            File file = new File(path);
            if(!file.exists()) file.createNewFile();
            fw = new FileWriter(file);
            fw.write(String.valueOf(stringBuilder));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fw != null){
                    fw.flush();
                }
                if(fw != null){
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
