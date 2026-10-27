package com.blankj.utilcode.util;

import android.util.Base64;

/* JADX INFO: compiled from: EncodeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static byte[] a(String str) {
        return (str == null || str.length() == 0) ? new byte[0] : Base64.decode(str, 2);
    }
}
