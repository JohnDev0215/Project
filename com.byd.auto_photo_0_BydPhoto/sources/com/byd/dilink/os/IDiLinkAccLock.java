package com.byd.dilink.os;

import android.os.WorkSource;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAccLock {
    void acquire();

    void acquire(long j);

    String getTag();

    boolean isHeld();

    void release();

    void release(int i);

    void setAccSource(WorkSource workSource);

    void setHistoryTag(String str);

    void setReferenceCounted(boolean z);

    void setTag(String str);

    Runnable wrap(Runnable runnable);
}
