package kotlin.text;

import java.nio.charset.Charset;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: compiled from: Charsets.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f4111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f4112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f4113c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f4114d;
    public static final Charset e;
    public static final Charset f;
    public static final d g = new d();

    static {
        Charset charsetForName = Charset.forName("UTF-8");
        kotlin.jvm.internal.h.c(charsetForName, "Charset.forName(\"UTF-8\")");
        f4111a = charsetForName;
        Charset charsetForName2 = Charset.forName("UTF-16");
        kotlin.jvm.internal.h.c(charsetForName2, "Charset.forName(\"UTF-16\")");
        f4112b = charsetForName2;
        Charset charsetForName3 = Charset.forName("UTF-16BE");
        kotlin.jvm.internal.h.c(charsetForName3, "Charset.forName(\"UTF-16BE\")");
        f4113c = charsetForName3;
        Charset charsetForName4 = Charset.forName("UTF-16LE");
        kotlin.jvm.internal.h.c(charsetForName4, "Charset.forName(\"UTF-16LE\")");
        f4114d = charsetForName4;
        Charset charsetForName5 = Charset.forName("US-ASCII");
        kotlin.jvm.internal.h.c(charsetForName5, "Charset.forName(\"US-ASCII\")");
        e = charsetForName5;
        Charset charsetForName6 = Charset.forName(InternalZipConstants.AES_HASH_CHARSET);
        kotlin.jvm.internal.h.c(charsetForName6, "Charset.forName(\"ISO-8859-1\")");
        f = charsetForName6;
    }

    private d() {
    }
}
