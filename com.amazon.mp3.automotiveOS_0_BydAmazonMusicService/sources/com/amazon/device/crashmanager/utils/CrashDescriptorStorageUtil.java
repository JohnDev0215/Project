package com.amazon.device.crashmanager.utils;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface CrashDescriptorStorageUtil {
    void clearAll();

    boolean contains(String str);

    Integer getCount(String str);

    List<String> getDescriptors();

    void persistCrashDescriptors();

    Integer prune(String str);

    void push(String str);

    Boolean shouldDedupe(String str);
}
