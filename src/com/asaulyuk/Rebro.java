package com.asaulyuk;

import org.apache.commons.graph.Edge;

public class Rebro implements Edge {
    Boolean valid = true;
    String address;

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getValid() {
        return valid;
    }

    public Rebro(String address) {
        this.address= address;
    }

    public String getAddress() {
        return address;
    }
}
