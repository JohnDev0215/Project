package cn.hutool.core.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: compiled from: CharsetUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f2948a = StandardCharsets.ISO_8859_1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f2949b = StandardCharsets.UTF_8;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f2950c;

    static {
        Charset charsetForName;
        try {
            charsetForName = Charset.forName("GBK");
        } catch (UnsupportedCharsetException unused) {
            charsetForName = null;
        }
        f2950c = charsetForName;
    }

    public static Charset a(String str) throws UnsupportedCharsetException {
        return c.a.a.d.b.i(str) ? Charset.defaultCharset() : Charset.forName(str);
    }

    public static Charset b() {
        return Charset.defaultCharset();
    }
}
