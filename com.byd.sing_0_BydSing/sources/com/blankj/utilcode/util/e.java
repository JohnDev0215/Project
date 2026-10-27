package com.blankj.utilcode.util;

import com.alibaba.fastjson.util.IdentityHashMap;
import com.android.volley.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ConvertUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f2609a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final char[] f2610b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            for (int i = 7; i >= 0; i--) {
                sb.append(((b2 >> i) & 1) == 0 ? '0' : '1');
            }
        }
        return sb.toString();
    }

    public static String b(byte[] bArr) {
        return c(bArr, true);
    }

    public static String c(byte[] bArr, boolean z) {
        if (bArr == null) {
            return BuildConfig.FLAVOR;
        }
        char[] cArr = z ? f2609a : f2610b;
        int length = bArr.length;
        if (length <= 0) {
            return BuildConfig.FLAVOR;
        }
        char[] cArr2 = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] >> 4) & 15];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return new String(cArr2);
    }

    public static ByteArrayOutputStream d(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[IdentityHashMap.DEFAULT_SIZE];
                while (true) {
                    int i = inputStream.read(bArr, 0, IdentityHashMap.DEFAULT_SIZE);
                    if (i != -1) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                        }
                    }
                }
                return byteArrayOutputStream;
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static byte[] e(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        return d(inputStream).toByteArray();
    }
}
