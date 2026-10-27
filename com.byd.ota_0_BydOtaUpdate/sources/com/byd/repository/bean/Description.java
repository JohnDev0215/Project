package com.byd.repository.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class Description implements Serializable {
    public String description;
    public String functionAddition;
    public String functionOptimization;

    public String toString() {
        return "Description{functionAddition='" + this.functionAddition + "', functionOptimization='" + this.functionOptimization + "', description='" + this.description + "'}";
    }
}
