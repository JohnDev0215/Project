package com.android.permissioncontroller.permission.utils;

/* JADX INFO: loaded from: classes8.dex */
public final class IoUtils {
    private IoUtils() {
    }

    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }
}
