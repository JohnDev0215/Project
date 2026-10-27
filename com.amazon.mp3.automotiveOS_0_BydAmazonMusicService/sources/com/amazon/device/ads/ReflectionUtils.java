package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class ReflectionUtils {
    ReflectionUtils() {
    }

    public boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
