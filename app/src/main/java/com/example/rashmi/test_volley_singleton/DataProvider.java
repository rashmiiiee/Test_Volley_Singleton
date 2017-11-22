package com.example.rashmi.test_volley_singleton;

/**
 * Created by QCS2015 on 22-11-2017.
 */

public class DataProvider {
    private String dp_title,dp_image,dp_content;
    private int dp_ratebar;


    public DataProvider(String dp_title, String dp_image, String dp_content, int dp_ratebar) {
        this.dp_title = dp_title;
        this.dp_image = dp_image;
        this.dp_content = dp_content;
        this.dp_ratebar = dp_ratebar;
    }

    public String getDp_title() {
        return dp_title;
    }

    public String getDp_image() {
        return dp_image;
    }

    public String getDp_content() {
        return dp_content;
    }

    public int getDp_ratebar() {
        return dp_ratebar;
    }
}
