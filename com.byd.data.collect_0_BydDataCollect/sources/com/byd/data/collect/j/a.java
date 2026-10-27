package com.byd.data.collect.j;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import c.a.b.j.f;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: Aes128Utils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a */
    private static final byte[] f3179a = "0000000000000000".getBytes();

    @TargetApi(29)
    public static String a(String str, String str2) {
        f fVar = new f("AES/CBC/PKCS7Padding", str.getBytes(StandardCharsets.UTF_8));
        fVar.p(f3179a);
        return fVar.e(str2);
    }

    @SuppressLint({"DeprecatedProvider"})
    public static String b(String str, String str2) {
        if (str2.length() != 16) {
            com.byd.data.common.i.f.g("Aes128Utils", "Key长度不是16位");
            return null;
        }
        f fVar = new f("AES/CBC/PKCS7Padding", str2.getBytes(StandardCharsets.UTF_8));
        fVar.p(f3179a);
        return new String(fVar.a(str));
    }
}
