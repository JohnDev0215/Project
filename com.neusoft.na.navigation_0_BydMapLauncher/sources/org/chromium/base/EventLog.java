package org.chromium.base;

import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class EventLog {
    public static void writeEvent(int i, int i2) {
        android.util.EventLog.writeEvent(i, i2);
    }
}
