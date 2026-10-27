package y5;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: Charsets.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f9991a = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f9992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f9993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f9994d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Charset f9995e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Charset f9996f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Charset f9997g;

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        kotlin.jvm.internal.l.d(charsetForName, "forName(\"UTF-8\")");
        f9992b = charsetForName;
        Charset charsetForName2 = Charset.forName("UTF-16");
        kotlin.jvm.internal.l.d(charsetForName2, "forName(\"UTF-16\")");
        f9993c = charsetForName2;
        Charset charsetForName3 = Charset.forName("UTF-16BE");
        kotlin.jvm.internal.l.d(charsetForName3, "forName(\"UTF-16BE\")");
        f9994d = charsetForName3;
        Charset charsetForName4 = Charset.forName("UTF-16LE");
        kotlin.jvm.internal.l.d(charsetForName4, "forName(\"UTF-16LE\")");
        f9995e = charsetForName4;
        Charset charsetForName5 = Charset.forName("US-ASCII");
        kotlin.jvm.internal.l.d(charsetForName5, "forName(\"US-ASCII\")");
        f9996f = charsetForName5;
        Charset charsetForName6 = Charset.forName("ISO-8859-1");
        kotlin.jvm.internal.l.d(charsetForName6, "forName(\"ISO-8859-1\")");
        f9997g = charsetForName6;
    }

    private d() {
    }
}
