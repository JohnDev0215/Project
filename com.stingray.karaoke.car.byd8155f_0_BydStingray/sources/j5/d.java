package J5;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(String str) {
        super("Login Failed: ".concat(str));
        m.f(str, "message");
    }
}
