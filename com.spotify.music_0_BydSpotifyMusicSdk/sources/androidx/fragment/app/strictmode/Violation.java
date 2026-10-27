package androidx.fragment.app.strictmode;

import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public abstract class Violation extends RuntimeException {
    private final ww0 fragment;

    public Violation(ww0 ww0Var, String str) {
        super(str);
        this.fragment = ww0Var;
    }

    public final ww0 a() {
        return this.fragment;
    }
}
