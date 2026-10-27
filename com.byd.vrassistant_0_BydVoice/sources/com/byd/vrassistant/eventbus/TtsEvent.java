package com.byd.vrassistant.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class TtsEvent {
    int process;
    String text;

    public int getProcess() {
        return this.process;
    }

    public TtsEvent(String str, int i) {
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
