package com.byd.taskmanager.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c<T> implements b<T> {
    private Object uiObj;

    public boolean isUIAvailable() {
        return this.uiObj != null;
    }

    @Override // com.byd.taskmanager.b.b
    public void onCanceled(String str) {
    }

    public c<T> registUiObject(Object obj) {
        this.uiObj = obj;
        return this;
    }

    public c<T> unregistUiObject() {
        this.uiObj = null;
        return this;
    }
}
