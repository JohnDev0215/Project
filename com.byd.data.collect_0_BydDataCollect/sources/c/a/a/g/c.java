package c.a.a.g;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: compiled from: CharsetUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f3075a = StandardCharsets.ISO_8859_1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f3076b = StandardCharsets.UTF_8;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f3077c;

    static {
        Charset charsetForName;
        try {
            charsetForName = Charset.forName("GBK");
        } catch (UnsupportedCharsetException unused) {
            charsetForName = null;
        }
        f3077c = charsetForName;
    }
}
