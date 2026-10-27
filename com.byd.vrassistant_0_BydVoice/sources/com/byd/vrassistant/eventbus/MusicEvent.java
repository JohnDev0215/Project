package com.byd.vrassistant.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class MusicEvent {
    int process;
    String text;

    public int getProcess() {
        return this.process;
    }

    public MusicEvent(String str, int i) {
        this.text = str;
        this.process = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
