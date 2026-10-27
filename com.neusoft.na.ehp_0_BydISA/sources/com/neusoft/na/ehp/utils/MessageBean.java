package com.neusoft.na.ehp.utils;

import android.location.Location;

/* JADX INFO: loaded from: classes.dex */
public class MessageBean {
    private int[] addl;
    private int[] data;
    private String db_version;
    private Location location;
    private MessageType messageType;

    public String getDb_version() {
        return this.db_version;
    }

    public void setDb_version(String str) {
        this.db_version = str;
    }

    public int[] getAddl() {
        return this.addl;
    }

    public void setAddl(int[] iArr) {
        this.addl = iArr;
    }

    public int[] getData() {
        return this.data;
    }

    public void setData(int[] iArr) {
        this.data = iArr;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
