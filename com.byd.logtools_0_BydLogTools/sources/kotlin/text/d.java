package kotlin.text;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: Charsets.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f5162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f5163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f5164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f5165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Charset f5166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Charset f5167f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Charset f5168g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static Charset f5169h;
    public static final d i = new d();

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        kotlin.jvm.internal.i.b(charsetForName, "Charset.forName(\"UTF-8\")");
        f5162a = charsetForName;
        Charset charsetForName2 = Charset.forName("UTF-16");
        kotlin.jvm.internal.i.b(charsetForName2, "Charset.forName(\"UTF-16\")");
        f5163b = charsetForName2;
        Charset charsetForName3 = Charset.forName("UTF-16BE");
        kotlin.jvm.internal.i.b(charsetForName3, "Charset.forName(\"UTF-16BE\")");
        f5164c = charsetForName3;
        Charset charsetForName4 = Charset.forName("UTF-16LE");
        kotlin.jvm.internal.i.b(charsetForName4, "Charset.forName(\"UTF-16LE\")");
        f5165d = charsetForName4;
        Charset charsetForName5 = Charset.forName("US-ASCII");
        kotlin.jvm.internal.i.b(charsetForName5, "Charset.forName(\"US-ASCII\")");
        f5166e = charsetForName5;
        Charset charsetForName6 = Charset.forName("ISO-8859-1");
        kotlin.jvm.internal.i.b(charsetForName6, "Charset.forName(\"ISO-8859-1\")");
        f5167f = charsetForName6;
    }

    private d() {
    }

    public final Charset a() {
        Charset charset = f5169h;
        if (charset != null) {
            return charset;
        }
        Charset charsetForName = Charset.forName("UTF-32BE");
        kotlin.jvm.internal.i.b(charsetForName, "Charset.forName(\"UTF-32BE\")");
        f5169h = charsetForName;
        return charsetForName;
    }

    public final Charset b() {
        Charset charset = f5168g;
        if (charset != null) {
            return charset;
        }
        Charset charsetForName = Charset.forName("UTF-32LE");
        kotlin.jvm.internal.i.b(charsetForName, "Charset.forName(\"UTF-32LE\")");
        f5168g = charsetForName;
        return charsetForName;
    }
}
