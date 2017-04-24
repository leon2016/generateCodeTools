package com.leon.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class freeMarkerHelloWorld {
    public static void main(String[] args) {
        try {
            // 1.创建一个合适的Configration对象
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")
                    + "\\WebContent\\WEB-INF\\template"));
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
            // 2.获取或创建一个模版。
            Template template = configuration.getTemplate("hello_world.html");
            // 3.生成模板数据Map
            Map<String, Object> paramMap = new HashMap<String, Object>(); // 输出普通变量值
            paramMap.put("text", "使用Freemarker生成静态文件！");

            List<String> testList = new ArrayList<String>(); // list标签迭代list
            testList.add("A");
            testList.add("B");
            testList.add("C");
            testList.add("D");
            paramMap.put("testList", testList);

            Map<String, Object> testMap = new HashMap<String, Object>();// list标签迭代map
            testMap.put("key1", "value1");
            testMap.put("key2", "value2");
            testMap.put("key3", null);
            testMap.put("key4", "value3");
            paramMap.put("testMap", testMap);
            // 5.创建OutputStreamWriter对象生成文件
            Writer writer = new OutputStreamWriter(new FileOutputStream("success.html"), "UTF-8");
            template.process(paramMap, writer);

            System.out.println("恭喜，生成成功~~");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
