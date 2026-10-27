package defpackage;

import defpackage.d00;
import defpackage.m00;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class c00 extends ed {
    public final m00 f;
    public transient b00<Object> g;

    public c00() {
        throw null;
    }

    public c00(b00<Object> b00Var, m00 m00Var) {
        super(b00Var);
        this.f = m00Var;
    }

    @Override // defpackage.b00
    public final m00 getContext() {
        m00 m00Var = this.f;
        i21.b(m00Var);
        return m00Var;
    }

    @Override // defpackage.ed
    public final void k() {
        b00<?> b00Var = this.g;
        if (b00Var != null && b00Var != this) {
            m00 m00Var = this.f;
            i21.b(m00Var);
            int i = d00.a;
            m00.b bVar = m00Var.get(d00.a.e);
            i21.b(bVar);
            ((d00) bVar).b1(b00Var);
        }
        this.g = qv.e;
    }
}
