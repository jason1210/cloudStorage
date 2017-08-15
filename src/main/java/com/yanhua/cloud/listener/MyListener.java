package com.yanhua.cloud.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jasonzhu on 2017/2/7.
 */
public class MyListener implements ServletContextListener {
    public static ServletContext context;

    public static ServletContext getContext() {
        return context;
    }

    public static void setContext(ServletContext context) {
        MyListener.context = context;
    }

    public void contextInitialized(ServletContextEvent sce) {
        context=sce.getServletContext();
        context.setAttribute("appId", "294198760000266035");
        context.setAttribute("redirectUri", "http://183.131.15.28:8280/cloud_storage/redirect");
        context.setAttribute("appSecret", "0afa6fed1ffd301bdf0e4ddf61714d0a");
        context.setAttribute("webUrl",sce.getServletContext().getRealPath("/"));
        System.out.println("MyListener contextInitialized--------------");
        System.out.println("项目根路径--------------"+sce.getServletContext().getRealPath("/"));
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");//读取bean.xml中的内容
//        User p = ctx.getBean("person",User.class);//创建bean的引用对象
//        p.toString();
//        System.out.println("SpringContextHolder初始化init-------------------------"+p.toString());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed--------------");
    }
}
