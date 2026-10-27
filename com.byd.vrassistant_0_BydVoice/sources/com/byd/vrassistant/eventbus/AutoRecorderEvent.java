package com.byd.vrassistant.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class AutoRecorderEvent {
    public int event;
    public String filename;

    public AutoRecorderEvent(int i, String str) {
        this.event = i;
        this.filename = str;
    }
}
