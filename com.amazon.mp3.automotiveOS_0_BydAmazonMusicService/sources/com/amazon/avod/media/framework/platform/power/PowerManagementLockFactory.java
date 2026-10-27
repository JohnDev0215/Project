package com.amazon.avod.media.framework.platform.power;

/* JADX INFO: loaded from: classes2.dex */
public interface PowerManagementLockFactory {

    public interface PowerManagementLock {
        void acquire();

        void release();
    }

    PowerManagementLock newWifiLock(String str);
}
