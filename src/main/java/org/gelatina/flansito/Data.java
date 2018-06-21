package org.gelatina.flansito;

public class Data {
    String body;
    String title;

    @Override
    public String toString() {
        return "Data{" +
                "body='" + body + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Data() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

