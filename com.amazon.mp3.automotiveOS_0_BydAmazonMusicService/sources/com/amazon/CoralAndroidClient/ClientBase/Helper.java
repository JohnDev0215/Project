package com.amazon.CoralAndroidClient.ClientBase;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Helper {
    public static boolean isStringNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
