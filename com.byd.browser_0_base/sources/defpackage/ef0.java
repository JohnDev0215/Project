package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.s0;

/* JADX INFO: compiled from: DynamicResourceReadyOnceCallback.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ef0 implements Callback<pb2> {
    public final cf0 e;
    public final Callback<pb2> f;

    public ef0(s0 s0Var, Callback callback) {
        this.e = s0Var;
        this.f = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(pb2 pb2Var) {
        this.f.onResult(pb2Var);
        this.e.c(this);
    }
}
