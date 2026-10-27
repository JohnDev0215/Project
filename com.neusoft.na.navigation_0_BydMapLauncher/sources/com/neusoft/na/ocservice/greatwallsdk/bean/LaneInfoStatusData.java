package com.neusoft.na.ocservice.greatwallsdk.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LaneInfoStatusData {
    private int display = 0;
    private List<LaneIconIdData> lanes = new ArrayList();

    public int getDisplay() {
        return this.display;
    }

    public void setDisplay(int i) {
        this.display = i;
    }

    public List<LaneIconIdData> getLanes() {
        return this.lanes;
    }

    public void setLanes(List<LaneIconIdData> list) {
        this.lanes = list;
    }
}
