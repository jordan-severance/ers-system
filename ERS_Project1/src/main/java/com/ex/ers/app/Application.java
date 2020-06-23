package com.ex.ers.app;

public abstract class Application {

    protected String title;

    public abstract void run() throws Exception;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
