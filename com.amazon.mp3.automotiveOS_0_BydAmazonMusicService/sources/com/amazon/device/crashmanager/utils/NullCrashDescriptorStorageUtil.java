package com.amazon.device.crashmanager.utils;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NullCrashDescriptorStorageUtil implements CrashDescriptorStorageUtil {
    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public void clearAll() {
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public boolean contains(String str) {
        return false;
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public void persistCrashDescriptors() {
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public void push(String str) {
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public List<String> getDescriptors() {
        return Collections.emptyList();
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public Integer getCount(String str) {
        return 0;
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public Integer prune(String str) {
        return new Integer(0);
    }

    @Override // com.amazon.device.crashmanager.utils.CrashDescriptorStorageUtil
    public Boolean shouldDedupe(String str) {
        return new Boolean(false);
    }
}
