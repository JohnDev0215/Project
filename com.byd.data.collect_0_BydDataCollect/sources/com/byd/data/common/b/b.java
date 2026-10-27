package com.byd.data.common.b;

import java.io.Serializable;

/* JADX INFO: compiled from: BaseModel.java */
/* JADX INFO: loaded from: classes.dex */
public class b<T> implements Serializable {
    public String json;
    private String reason;
    public int rebacekResult;
    protected T result;
    protected byte updateIndex;

    public b(String str, int i2) {
        this.reason = str;
        this.rebacekResult = i2;
    }

    public String getReason() {
        return this.reason;
    }

    public int getRebacekResult() {
        return this.rebacekResult;
    }

    public T getResult() {
        return this.result;
    }

    public byte getUpdateIndex() {
        return this.updateIndex;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setRebacekResult(int i2) {
        this.rebacekResult = i2;
    }

    public void setResult(T t) {
        this.result = t;
    }

    public void setUpdateIndex(byte b2) {
        this.updateIndex = b2;
    }

    public void setResult(T t, String str) {
        this.result = t;
        this.json = str;
    }
}
