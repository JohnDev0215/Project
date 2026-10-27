package org.apache.http.util;

/* JADX INFO: loaded from: classes7.dex */
public class Asserts {
    public static void check(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void notNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalStateException(str + " is null");
    }
}
