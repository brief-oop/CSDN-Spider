package com.spider.blogs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenjie
 * @date 2020/8/12 12:43
 */
public class BlogList {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 每2分钟执行一次的定时任务，单篇博客每天增加访问量：720
     */
    public static List<String> task1;
    /**
     * 每13分钟执行一次的定时任务，单篇博客每天增加访问量：120左右
     */
    public static List<String> task2;
    /**
     * 每33分钟执行一次的定时任务，单篇博客每天增加访问量：40左右
     */
    public static List<String> task3;
    /**
     * 每33分钟执行一次的定时任务，单篇博客每天增加访问量：40左右
     */
    public static List<String> task4;

    public static Date date;

    static {
        date = new Date();
        System.out.println(sdf.format(new Date()) + "开始进行博客网址的初始化！");
        task1 = new ArrayList<String>();
        task2 = new ArrayList<String>();
        task3 = new ArrayList<String>();
        task4 = new ArrayList<String>();

        /*task1.add("https://blog.csdn.net/cczxcce/article/details/107764204");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107764262");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107518364");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107400059");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107644933");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107791697");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107781877");
        task1.add("https://blog.csdn.net/cczxcce/article/details/107773812");

        task2.add("https://blog.csdn.net/cczxcce/article/details/107416619");
        task2.add("https://blog.csdn.net/cczxcce/article/details/107589078");
        task2.add("https://blog.csdn.net/cczxcce/article/details/107785556");
        task2.add("https://blog.csdn.net/cczxcce/article/details/107810197");
        task2.add("https://blog.csdn.net/cczxcce/article/details/107807976");

        task3.add("https://blog.csdn.net/cczxcce/article/details/108059326");
        task3.add("https://blog.csdn.net/cczxcce/article/details/107394972");
        task3.add("https://blog.csdn.net/cczxcce/article/details/107505145");
        task3.add("https://blog.csdn.net/cczxcce/article/details/107593527");
        task3.add("https://blog.csdn.net/cczxcce/article/details/107591159");
        task3.add("https://blog.csdn.net/cczxcce/article/details/107409102");
        task3.add("https://blog.csdn.net/cczxcce/article/details/107395266");

        task4.add("https://blog.csdn.net/cczxcce/article/details/107982288");
        task4.add("https://blog.csdn.net/cczxcce/article/details/107395146");*/

        addIndexPage("Brief_","task1");

        System.out.println(sdf.format(new Date()) + "博客网址的初始化完成！");
    }

    /**
     * 添加个人主页，把当前主页下所有博客全部添加
     * @param indexPage
     * @param type
     */
    public static void addIndexPage(String indexPage,String type){
        String indexUrl = "https://blog.csdn.net/"+indexPage;
        Document document = null;
        try{
            document = Jsoup.connect(indexUrl).get();
        }catch (Exception e){
            System.out.println("*********************************************************************");
            System.out.println("添加个人主页博客内容出错，主页输入有误！");
            System.out.println("*********************************************************************");
            return;
        }
        Elements infoBox = document.getElementsByClass("info-box");
        Elements content = document.getElementsByClass("article-item-box");
        for (int i=0;i<infoBox.size();i++){
            String url = content.get(i).getElementsByTag("a").attr("href")+"";
            if ("task1".equals(type)){
                task1.add(url);
            }else if ("task2".equals(type)){
                task2.add(url);
            }else if ("task3".equals(type)){
                task3.add(url);
            }else{
                task4.add(url);
            }
        }
        System.out.println(222);
    }



    public static void addPage(List<String> blogList,String type){
        for (String blog : blogList) {
            if ("task1".equals(type)){
                task1.add(blog);
            }else if ("task2".equals(type)){
                task2.add(blog);
            }else if ("task3".equals(type)){
                task3.add(blog);
            }else{
                task4.add(blog);
            }
        }
    }
}
