package com.byd.affinity;

/* JADX INFO: loaded from: classes.dex */
public class ThreadAffinity {
    static {
        System.loadLibrary("byd-core");
        System.out.println("ThreadAffinity load ThreadAffinity lib success V1.1.1");
    }

    public native int bindPidToCore(int i, int i2);
}
