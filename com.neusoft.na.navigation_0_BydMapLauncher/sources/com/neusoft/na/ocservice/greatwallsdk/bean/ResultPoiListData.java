package com.neusoft.na.ocservice.greatwallsdk.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ResultPoiListData {
    private int RequestID = 0;
    private List<PoiData> result = new ArrayList();

    public List<PoiData> getResult() {
        return this.result;
    }

    public void setResult(List<PoiData> list) {
        this.result = list;
    }

    public void addRecord(PoiData poiData) {
        this.result.add(poiData);
    }

    public int getRequestID() {
        return this.RequestID;
    }

    public void setRequestID(int i) {
        this.RequestID = i;
    }
}
