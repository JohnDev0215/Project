package c6;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: Credentials.kt */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f3824a = new o();

    private o() {
    }

    public static final String a(String username, String password, Charset charset) {
        kotlin.jvm.internal.l.e(username, "username");
        kotlin.jvm.internal.l.e(password, "password");
        kotlin.jvm.internal.l.e(charset, "charset");
        return "Basic " + p6.h.f8870j.b(username + ':' + password, charset).a();
    }
}
