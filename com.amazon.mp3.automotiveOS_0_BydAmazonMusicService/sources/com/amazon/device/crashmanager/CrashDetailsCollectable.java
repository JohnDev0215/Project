package com.amazon.device.crashmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface CrashDetailsCollectable {
    Map<String, String> collect(Throwable th);
}
