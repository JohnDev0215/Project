package Q8;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f7211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile Charset f7212b;
    public static volatile Charset c;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        A7.m.e(charsetForName, "forName(...)");
        f7211a = charsetForName;
        A7.m.e(Charset.forName("UTF-16"), "forName(...)");
        A7.m.e(Charset.forName("UTF-16BE"), "forName(...)");
        A7.m.e(Charset.forName("UTF-16LE"), "forName(...)");
        A7.m.e(Charset.forName("US-ASCII"), "forName(...)");
        A7.m.e(Charset.forName("ISO-8859-1"), "forName(...)");
    }
}
