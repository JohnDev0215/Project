package kotlin.coroutines.jvm.internal;

import g5.l;
import g5.m;
import g5.r;
import java.io.Serializable;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements k5.d<Object>, e, Serializable {
    private final k5.d<Object> completion;

    public a(k5.d<Object> dVar) {
        this.completion = dVar;
    }

    public k5.d<r> create(k5.d<?> completion) {
        kotlin.jvm.internal.l.e(completion, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public e getCallerFrame() {
        k5.d<Object> dVar = this.completion;
        if (dVar instanceof e) {
            return (e) dVar;
        }
        return null;
    }

    public final k5.d<Object> getCompletion() {
        return this.completion;
    }

    public StackTraceElement getStackTraceElement() {
        return g.d(this);
    }

    protected abstract Object invokeSuspend(Object obj);

    protected void releaseIntercepted() {
    }

    @Override // k5.d
    public final void resumeWith(Object obj) {
        Object objInvokeSuspend;
        k5.d<Object> dVar = this;
        while (true) {
            h.b(dVar);
            a aVar = (a) dVar;
            k5.d<Object> dVar2 = aVar.completion;
            kotlin.jvm.internal.l.c(dVar2);
            try {
                objInvokeSuspend = aVar.invokeSuspend(obj);
            } catch (Throwable th) {
                l.a aVar2 = g5.l.f6557e;
                obj = g5.l.a(m.a(th));
            }
            if (objInvokeSuspend == l5.d.d()) {
                return;
            }
            l.a aVar3 = g5.l.f6557e;
            obj = g5.l.a(objInvokeSuspend);
            aVar.releaseIntercepted();
            if (!(dVar2 instanceof a)) {
                dVar2.resumeWith(obj);
                return;
            }
            dVar = dVar2;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    public k5.d<r> create(Object obj, k5.d<?> completion) {
        kotlin.jvm.internal.l.e(completion, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
