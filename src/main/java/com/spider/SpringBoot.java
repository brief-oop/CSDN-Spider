package com.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenjie
 * @date 2020/8/5 9:42
 */
@SpringBootApplication
public class SpringBoot {

    //日志设置
    public static SimpleDateFormat sdf;
    public static PrintStream ps;

    public SpringBoot() throws FileNotFoundException {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "./logs/"+sdf.format(new Date())+".txt";

        ps = new PrintStream(new FileOutputStream(new File(fileName)),true);
        System.setOut(ps);
    }

    public static void main(String[] args){
        SpringApplication.run(SpringBoot.class,args);
    }
}
