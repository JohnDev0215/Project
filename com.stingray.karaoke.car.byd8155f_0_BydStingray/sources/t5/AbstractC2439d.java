package t5;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* JADX INFO: renamed from: t5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2439d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f22471a = Charset.defaultCharset();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f22472b = Charset.forName("SJIS");

    static {
        try {
            Charset.forName("GB2312");
        } catch (UnsupportedCharsetException unused) {
        }
        Charset charsetForName = Charset.forName("EUC_JP");
        Charset charset = f22472b;
        Charset charset2 = f22471a;
        if (charset.equals(charset2)) {
            return;
        }
        charsetForName.equals(charset2);
    }
}
