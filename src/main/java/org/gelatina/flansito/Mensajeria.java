package org.gelatina.flansito;

public class Mensajeria {

    String to;

    Data data;

    @Override
    public String toString() {
        return "Mensajeria{" +
                "to='" + to + '\'' +
                ", data=" + data +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Mensajeria() {
    }
}

