package com.byd.auto_emanual.util;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class CloseUtil {
    public static void close(Closeable closeable, String tag) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                MLog.e(tag, "close exception", e);
            }
        }
    }
}
