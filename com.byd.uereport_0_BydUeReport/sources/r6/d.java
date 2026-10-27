package r6;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: StringUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f7854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f7855b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f7856c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Charset f7857d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f7858e;

    static {
        Charset charsetDefaultCharset = Charset.defaultCharset();
        f7854a = charsetDefaultCharset;
        Charset charsetForName = Charset.forName("SJIS");
        f7855b = charsetForName;
        f7856c = Charset.forName("GB2312");
        Charset charsetForName2 = Charset.forName("EUC_JP");
        f7857d = charsetForName2;
        f7858e = charsetForName.equals(charsetDefaultCharset) || charsetForName2.equals(charsetDefaultCharset);
    }
}
