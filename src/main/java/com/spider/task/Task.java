package com.spider.task;


import com.spider.blogs.BlogList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author chenjie
 * @date 2020/8/12 21:38
 */
@Configuration
@EnableScheduling
public class Task {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 正常访问次数
     */
    public static Integer sum;
    /**
     * 异常产生次数
     */
    public static Integer errorSum;
    public static List<String> task1;
    public static List<String> task2;
    public static List<String> task3;
    public static List<String> task4;

    public static boolean task1Flag;
    public static boolean task2Flag;
    public static boolean task3Flag;
    public static boolean task4Flag;

    {
        new BlogList();
        sum = 0;
        errorSum = 0;
        task1 = BlogList.task1;
        task2 = BlogList.task2;
        task3 = BlogList.task3;
        task4 = BlogList.task4;
        task1Flag = true;
        task2Flag = true;
        task3Flag = true;
        task4Flag = true;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(sdf.format(new Date()) +"更新博客地址后的相关信息如下：");
        System.out.println("task1的数量为："+task1.size());
        for (String s : task1) {
            System.out.println(s);
        }
        System.out.println("task2的数量为："+task2.size());
        for (String s : task2) {
            System.out.println(s);
        }
        System.out.println("task3的数量为："+task3.size());
        for (String s : task3) {
            System.out.println(s);
        }
        System.out.println("task4的数量为："+task4.size());
        for (String s : task4) {
            System.out.println(s);
        }
        System.out.println("共计："+(task1.size()+task2.size()+task3.size()+task4.size()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * 每天零点执行，更新日志输出文件目录
     *
     * @throws FileNotFoundException
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void configureTasks1() throws FileNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(new Date());
        PrintStream ps = new PrintStream("./logs/" + fileName + ".txt");
        System.setOut(ps);
    }

    /**
     * 每2分钟执行一次的定时任务，单篇博客每天增加访问量：720
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    private void tasks1() {
        task1Flag = false;
        System.out.println();
        System.out.println("#####################定时任务一开始#############################");
        List<String> list = new ArrayList<String>();
        for (String s : task1) {
            list.add(s);
        }
        visitBlogPage(list);
        System.out.println("#####################定时任务一结束#############################");
        System.out.println();
        task1Flag = true;
    }

    /**
     * 每3分钟执行一次的定时任务，单篇博客每天增加访问量：480左右
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    private void tasks2() {
        if(task2.size()==0) return;
        task2Flag = false;
        System.out.println();
        System.out.println("#####################定时任务二开始#############################");
        List<String> list = new ArrayList<String>();
        for (String s : task2) {
            list.add(s);
        }
        visitBlogPage(list);
        System.out.println("#####################定时任务二结束#############################");
        System.out.println();
        task2Flag = true;
    }

    /**
     * 每7分钟执行一次的定时任务，单篇博客每天增加访问量：200左右
     */
    @Scheduled(cron = "0 0/7 * * * ?")
    private void tasks3() {
        if(task3.size()==0) return;
        task3Flag = false;
        System.out.println();
        System.out.println("#####################定时任务三开始#############################");
        List<String> list = new ArrayList<String>();
        for (String s : task3) {
            list.add(s);
        }
        visitBlogPage(list);
        System.out.println("#####################定时任务三结束#############################");
        System.out.println();
        task3Flag = true;
    }

    /**
     * 每33分钟执行一次的定时任务，单篇博客每天增加访问量：200左右
     */
    @Scheduled(cron = "0 0/33 * * * ?")
    private void tasks4() {
        if(task4.size()==0) return;
        task4Flag = false;
        System.out.println();
        System.out.println("#####################定时任务四开始#############################");
        List<String> list = new ArrayList<String>();
        for (String s : task4) {
            list.add(s);
        }
        visitBlogPage(list);
        System.out.println("#####################定时任务四结束#############################");
        System.out.println();
        task4Flag = true;
    }


    /**
     * 每17分钟，刷新博客地址
     */
    @Scheduled(cron = "0 0/17 * * * ?")
    private void updateBlogList() throws InterruptedException {

        while (true) {
            if (task1Flag && task2Flag && task3Flag && task4Flag) {
                System.out.println();System.out.println();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(sdf.format(new Date()) +"更新博客地址前的相关信息如下：");
                System.out.println("task1的数量为："+task1.size()+",task2的数量为："+task2.size()+",task3的数量为："
                        +task3.size()+"task4的数量为："+task4.size()+",共计："+(task1.size()+task2.size()+task3.size()+task4.size()));
                task1 = BlogList.task1;
                task2 = BlogList.task2;
                task3 = BlogList.task3;
                task4 = BlogList.task4;
                break;
            }
            Thread.sleep(5000);
        }
        System.out.println(sdf.format(new Date()) +"更新博客地址后的相关信息如下：");
        System.out.println("task1的数量为："+task1.size());
        for (String s : task1) {
            System.out.println(s);
        }
        System.out.println("task2的数量为："+task2.size());
        for (String s : task2) {
            System.out.println(s);
        }
        System.out.println("task3的数量为："+task3.size());
        for (String s : task3) {
            System.out.println(s);
        }
        System.out.println("task4的数量为："+task4.size());
        for (String s : task4) {
            System.out.println(s);
        }
        System.out.println("共计："+(task1.size()+task2.size()+task3.size()+task4.size()));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();System.out.println();
    }


    /**
     * 每11分钟，输出当前概要信息
     */
    @Scheduled(cron = "0 0/11 * * * ?")
    private void showNowInfo(){
        System.out.println();System.out.println();
        System.out.println("################################################################################");
        System.out.println("到" + sdf.format(new Date()) + "为止，一共成功增加访问量：" + sum + "次，期间产生异常数量为：" + errorSum);
        System.out.println("################################################################################");
        System.out.println();System.out.println();
    }


    private void visitBlogPage(List<String> list) {
        for (String blog : list) {
            try {
                Document document = Jsoup.connect(blog).get();
                Elements title = document.getElementsByTag("title");
                Element element = document.getElementsByClass("read-count").get(0);
                sum++;
                System.out.println(sdf.format(new Date()) + "博客(" + title.text() + ")的访问量为：" + element.text());
            } catch (Exception e) {
                errorSum++;
                System.out.println("********************************************************************************");
                System.out.println(sdf.format(new Date()) + "访问博客(" + blog + ")产生异常，中止本次定时任务。原因是：" + e.fillInStackTrace());
                System.out.println("********************************************************************************");
            }
        }
    }
    


}
