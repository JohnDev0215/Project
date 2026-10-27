package com.byd.vrassistant.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class FinalOperation {
    String action;
    boolean toast;

    public FinalOperation(String str, boolean z) {
        this.action = str;
        this.toast = z;
    }

    public boolean isToast() {
        return this.toast;
    }

    public void setToast(boolean z) {
        this.toast = z;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }
}
