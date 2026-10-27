package com.nuance.android.vocalizer;

import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
public class VocalizerSpeechItem {
    protected Semaphore mSemaphore;
    private String mText = null;
    private String mId = null;

    protected VocalizerSpeechItem() {
        this.mSemaphore = null;
        Semaphore semaphore = new Semaphore(1);
        this.mSemaphore = semaphore;
        try {
            semaphore.acquire();
        } catch (Exception unused) {
        }
    }

    protected void setText(String str) {
        this.mText = str;
    }

    protected void setId(String str) {
        this.mId = str;
    }

    public String getText() {
        return this.mText;
    }

    public String getId() {
        return this.mId;
    }

    public void waitForComplete() {
        try {
            this.mSemaphore.acquire();
        } catch (Exception unused) {
        }
    }
}
