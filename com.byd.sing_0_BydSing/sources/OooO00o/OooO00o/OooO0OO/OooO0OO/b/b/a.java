package OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b;

import java.nio.charset.Charset;
import kotlin.jvm.internal.h;
import kotlin.text.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f132a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f133b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f134c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f135d;

    static {
        Charset charset = d.f4111a;
        byte[] bytes = "byd".getBytes(charset);
        h.c(bytes, "(this as java.lang.String).getBytes(charset)");
        f133b = bytes;
        byte[] bytes2 = "13348de87c13f93829d3377eafc6a900".getBytes(charset);
        h.c(bytes2, "(this as java.lang.String).getBytes(charset)");
        f134c = bytes2;
        byte[] bytes3 = "client_credentials".getBytes(charset);
        h.c(bytes3, "(this as java.lang.String).getBytes(charset)");
        f135d = bytes3;
    }

    public final byte[] a() {
        return f134c;
    }

    public final byte[] b() {
        return f135d;
    }

    public final byte[] c() {
        return f133b;
    }
}
