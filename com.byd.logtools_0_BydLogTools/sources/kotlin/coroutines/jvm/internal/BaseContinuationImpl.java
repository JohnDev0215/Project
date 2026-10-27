package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Result;
import kotlin.g;
import kotlin.j;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseContinuationImpl implements kotlin.l.a<Object>, b, Serializable {
    private final kotlin.l.a<Object> completion;

    public BaseContinuationImpl(kotlin.l.a<Object> aVar) {
        this.completion = aVar;
    }

    public kotlin.l.a<j> create(kotlin.l.a<?> aVar) {
        i.c(aVar, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public b getCallerFrame() {
        kotlin.l.a<Object> aVar = this.completion;
        if (!(aVar instanceof b)) {
            aVar = null;
        }
        return (b) aVar;
    }

    public final kotlin.l.a<Object> getCompletion() {
        return this.completion;
    }

    @Override // kotlin.l.a
    public abstract /* synthetic */ kotlin.l.c getContext();

    public StackTraceElement getStackTraceElement() {
        return d.d(this);
    }

    protected abstract Object invokeSuspend(Object obj);

    protected void releaseIntercepted() {
    }

    @Override // kotlin.l.a
    public final void resumeWith(Object obj) {
        Object objInvokeSuspend;
        BaseContinuationImpl baseContinuationImpl = this;
        while (true) {
            e.a(baseContinuationImpl);
            kotlin.l.a<Object> aVar = baseContinuationImpl.completion;
            if (aVar == null) {
                i.g();
            }
            try {
                objInvokeSuspend = baseContinuationImpl.invokeSuspend(obj);
            } catch (Throwable th) {
                Result.a aVar2 = Result.Companion;
                obj = Result.m2constructorimpl(g.a(th));
            }
            if (objInvokeSuspend == kotlin.coroutines.intrinsics.c.b()) {
                return;
            }
            Result.a aVar3 = Result.Companion;
            obj = Result.m2constructorimpl(objInvokeSuspend);
            baseContinuationImpl.releaseIntercepted();
            if (!(aVar instanceof BaseContinuationImpl)) {
                aVar.resumeWith(obj);
                return;
            }
            baseContinuationImpl = (BaseContinuationImpl) aVar;
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

    public kotlin.l.a<j> create(Object obj, kotlin.l.a<?> aVar) {
        i.c(aVar, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
