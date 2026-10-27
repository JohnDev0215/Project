package kotlinx.coroutines.internal;

import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: Scopes.kt */
/* JADX INFO: loaded from: classes.dex */
public class y<T> extends kotlinx.coroutines.a<T> implements kotlin.coroutines.jvm.internal.e {
    public final k5.d<T> uCont;

    /* JADX WARN: Multi-variable type inference failed */
    public y(k5.g gVar, k5.d<? super T> dVar) {
        super(gVar, true);
        this.uCont = dVar;
    }

    @Override // kotlinx.coroutines.s1
    protected void afterCompletion(Object obj) {
        i.c(l5.c.c(this.uCont), kotlinx.coroutines.c0.a(obj, this.uCont), null, 2, null);
    }

    @Override // kotlinx.coroutines.a
    protected void afterResume(Object obj) {
        k5.d<T> dVar = this.uCont;
        dVar.resumeWith(kotlinx.coroutines.c0.a(obj, dVar));
    }

    public final kotlin.coroutines.jvm.internal.e getCallerFrame() {
        return (kotlin.coroutines.jvm.internal.e) this.uCont;
    }

    public final l1 getParent$kotlinx_coroutines_core() {
        return (l1) this.parentContext.get(l1.f7703d);
    }

    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlinx.coroutines.s1
    protected final boolean isScopedCoroutine() {
        return true;
    }
}
