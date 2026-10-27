package com.ts.androidauto.aap.common;

/* JADX INFO: loaded from: classes.dex */
public class CommonUtil {
    public static void sleep(long timeMillions) {
        try {
            Thread.sleep(timeMillions);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
