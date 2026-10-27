package defpackage;

import defpackage.y11;

/* JADX INFO: compiled from: InterfaceRequest.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f21<P extends y11> implements vt0<ch1> {
    public final ch1 e;

    public f21(ch1 ch1Var) {
        this.e = ch1Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.e.close();
    }

    @Override // defpackage.vt0
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final ch1 f0() {
        return this.e.P0();
    }
}
