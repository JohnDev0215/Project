package p;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class cs {
    public static final Charset a;
    public static volatile Charset b;
    public static volatile Charset c;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        v03.i(charsetForName, "forName(\"UTF-8\")");
        a = charsetForName;
        v03.i(Charset.forName("UTF-16"), "forName(\"UTF-16\")");
        v03.i(Charset.forName("UTF-16BE"), "forName(\"UTF-16BE\")");
        v03.i(Charset.forName("UTF-16LE"), "forName(\"UTF-16LE\")");
        v03.i(Charset.forName("US-ASCII"), "forName(\"US-ASCII\")");
        v03.i(Charset.forName("ISO-8859-1"), "forName(\"ISO-8859-1\")");
    }
}
