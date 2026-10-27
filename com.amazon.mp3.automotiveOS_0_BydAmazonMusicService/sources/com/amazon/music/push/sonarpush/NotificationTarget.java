package com.amazon.music.push.sonarpush;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationTarget {
    private String mApplicationId;
    private String mDestination;
    private int mType;

    public String getDestination() {
        return this.mDestination;
    }

    public void setDestination(String str) {
        this.mDestination = str;
    }

    public String getApplicationId() {
        return this.mApplicationId;
    }

    public void setApplicationId(String str) {
        this.mApplicationId = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }
}
