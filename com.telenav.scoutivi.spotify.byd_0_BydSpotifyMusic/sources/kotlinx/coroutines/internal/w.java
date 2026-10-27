package kotlinx.coroutines.internal;

import kotlinx.coroutines.m0;

/* JADX INFO: compiled from: Atomic.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class w {
    public abstract d<?> a();

    public final boolean b(w wVar) {
        d<?> dVarA;
        d<?> dVarA2 = a();
        return (dVarA2 == null || (dVarA = wVar.a()) == null || dVarA2.g() >= dVarA.g()) ? false : true;
    }

    public abstract Object c(Object obj);

    public String toString() {
        return m0.a(this) + '@' + m0.b(this);
    }
}
