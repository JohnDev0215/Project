package com.facebook.common.disk;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class NoOpDiskTrimmableRegistry implements DiskTrimmableRegistry {

    @Nullable
    private static NoOpDiskTrimmableRegistry sInstance;

    @Override // com.facebook.common.disk.DiskTrimmableRegistry
    public void registerDiskTrimmable(DiskTrimmable trimmable) {
    }

    private NoOpDiskTrimmableRegistry() {
    }

    public static synchronized NoOpDiskTrimmableRegistry getInstance() {
        if (sInstance == null) {
            sInstance = new NoOpDiskTrimmableRegistry();
        }
        return sInstance;
    }
}
