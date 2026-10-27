package com.facebook.react.uimanager;

/* JADX INFO: loaded from: classes6.dex */
public class ReactRootViewTagGenerator {
    private static int sNextRootViewTag = 1;

    public static synchronized int getNextRootViewTag() {
        int i;
        i = sNextRootViewTag;
        sNextRootViewTag = i + 10;
        return i;
    }
}
