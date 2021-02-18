package com.spider.controller;

import com.spider.blogs.BlogList;
import com.spider.task.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author chenjie
 * @date 2020/8/12 16:44
 */
@RestController
public class Controller {

    @RequestMapping("/download/{name}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String name) {
        System.out.println("sundan");
        //要上传的文件名字
        String fileName = name;
        //通过文件的保存文件夹路径加上文件的名字来获得文件
        String FILE_DIR="./logs/";
        File file = new File(FILE_DIR, fileName);
        //当文件存在
        if (file.exists()) {
            //首先设置响应的内容格式是force-download，那么你一旦点击下载按钮就会自动下载文件了
            String type=request.getServletContext().getMimeType(fileName);
            response.setContentType(type);
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition","attachment; filename="+file.getName());
            //进行读写操作
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                //从源文件中读
                int i = bis.read(buffer);
                while (i != -1) {
                    //写到response的输出流中
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //善后工作，关闭各种流
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("文件不存在！");
        }
    }

    @RequestMapping("/download")
    public String displayDownload() {
        String info = "";

        File folder = new File("./logs");
        File[] listOfFiles = folder.listFiles();
        info += "所有日志文件如下：</br></br>";
        for (File allFile : listOfFiles) {
            info += "日志名称：" + allFile.getName()+",    ";
            info += "<a target=\"_blank\" href=\"http://39.103.150.227/download/"+allFile.getName()+"\">点击下载</a></br>";
        }

        return info;
    }

    @RequestMapping("/displayAllBlog")
    public String displayAllBlog(){
        String info = "";

        info += "程序开始执行时间为："+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(BlogList.date)
                +",到目前为止，成功执行次数为："+ Task.sum+"次，产生异常次数为："+Task.errorSum+"次。</br>";


        info += "</br>task1任务页：</br>";
        List<String> task1 = BlogList.task1;
        for (String s : task1) {
            info += "<a target=\"_blank\" href=\""+s+"\">"+s+"</a>";
            info += "</br>";
        }
        info += "</br>task2任务页：</br>";
        List<String> task2 = BlogList.task2;
        for (String s : task2) {
            info += "<a target=\"_blank\" href=\""+s+"\">"+s+"</a>";
            info += "</br>";
        }
        info += "</br>task3任务页：</br>";
        List<String> task3 = BlogList.task3;
        for (String s : task3) {
            info += "<a target=\"_blank\" href=\""+s+"\">"+s+"</a>";
            info += "</br>";
        }
        info += "</br>task4任务页：</br>";
        List<String> task4 = BlogList.task4;
        for (String s : task4) {
            info += "<a target=\"_blank\" href=\""+s+"\">"+s+"</a>";
            info += "</br>";
        }

        return info;
    }

    @RequestMapping("/addIndexPage/{userID}/{type}")
    public void addIndexPage(@PathVariable String userID, @PathVariable String type){
        BlogList.addIndexPage(userID,type);
    }

}
