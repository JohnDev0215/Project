package com.byd.data.common.entity.bms;

import com.byd.data.common.b.b;

/* JADX INFO: loaded from: classes.dex */
public class QueryResult extends b<QueryDataBean> {
    public int code;
    public short countryCode;
    public int mccmnc;
    public String message;
    public byte switchStatus;
    public byte uploadCycle;

    public QueryResult(String str, int i2) {
        super(str, i2);
    }
}
