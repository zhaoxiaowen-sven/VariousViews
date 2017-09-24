package com.sven.variousviews.bean;


public class FruitBean {

    private String name;

    private String id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    private int type;

    public int getType() {
        return type;
    }

    public FruitBean(String name, String id, String url, int type) {
        this.name = name;
        this.id = id;
        this.url = url;
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public FruitBean(String name, String id, String url) {
        this.name = name;
        this.id = id;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
