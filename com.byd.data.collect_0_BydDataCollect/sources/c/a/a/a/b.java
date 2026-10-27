package c.a.a.a;

import java.nio.charset.Charset;
import java.util.Base64;

/* JADX INFO: compiled from: Base64.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Charset f3014a = c.a.a.g.c.f3076b;

    public static byte[] a(CharSequence charSequence) {
        return c.a(charSequence);
    }

    public static String b(byte[] bArr) {
        return Base64.getEncoder().encodeToString(bArr);
    }
}
