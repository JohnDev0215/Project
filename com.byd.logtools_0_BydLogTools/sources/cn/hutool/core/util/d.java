package cn.hutool.core.util;

/* JADX INFO: compiled from: HexUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static byte[] a(CharSequence charSequence) {
        return c.a.a.a.a.f2868a.a(charSequence);
    }

    public static byte[] b(String str) {
        return a(str);
    }

    public static char[] c(byte[] bArr, boolean z) {
        return (z ? c.a.a.a.a.f2868a : c.a.a.a.a.f2869b).b(bArr);
    }

    public static String d(byte[] bArr) {
        return e(bArr, true);
    }

    public static String e(byte[] bArr, boolean z) {
        return new String(c(bArr, z));
    }
}
