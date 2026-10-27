package kotlinx.coroutines.flow.internal;

import g5.l;
import g5.r;
import java.util.Objects;
import k5.g;
import k5.h;
import kotlin.coroutines.jvm.internal.d;
import kotlin.coroutines.jvm.internal.e;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.p1;
import r5.q;
import y5.i;

/* JADX INFO: compiled from: SafeCollector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector<T> extends d implements FlowCollector<T> {
    public final g collectContext;
    public final int collectContextSize;
    public final FlowCollector<T> collector;
    private k5.d<? super r> completion;
    private g lastEmissionContext;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeCollector(FlowCollector<? super T> flowCollector, g gVar) {
        super(NoOpContinuation.INSTANCE, h.f7276e);
        this.collector = flowCollector;
        this.collectContext = gVar;
        this.collectContextSize = ((Number) gVar.fold(0, SafeCollector$collectContextSize$1.INSTANCE)).intValue();
    }

    private final void checkContext(g gVar, g gVar2, T t6) {
        if (gVar2 instanceof DownstreamExceptionElement) {
            exceptionTransparencyViolated((DownstreamExceptionElement) gVar2, t6);
        }
        SafeCollector_commonKt.checkContext(this, gVar);
        this.lastEmissionContext = gVar;
    }

    private final void exceptionTransparencyViolated(DownstreamExceptionElement downstreamExceptionElement, Object obj) {
        throw new IllegalStateException(i.f("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + downstreamExceptionElement.f7611e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t6, k5.d<? super r> dVar) {
        try {
            Object objEmit = emit(dVar, t6);
            if (objEmit == l5.d.d()) {
                kotlin.coroutines.jvm.internal.h.c(dVar);
            }
            return objEmit == l5.d.d() ? objEmit : r.f6563a;
        } catch (Throwable th) {
            this.lastEmissionContext = new DownstreamExceptionElement(th);
            throw th;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public e getCallerFrame() {
        k5.d<? super r> dVar = this.completion;
        if (!(dVar instanceof e)) {
            dVar = null;
        }
        return (e) dVar;
    }

    @Override // kotlin.coroutines.jvm.internal.d, k5.d
    public g getContext() {
        g context;
        k5.d<? super r> dVar = this.completion;
        return (dVar == null || (context = dVar.getContext()) == null) ? h.f7276e : context;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public Object invokeSuspend(Object obj) {
        Throwable thB = l.b(obj);
        if (thB != null) {
            this.lastEmissionContext = new DownstreamExceptionElement(thB);
        }
        k5.d<? super r> dVar = this.completion;
        if (dVar != null) {
            dVar.resumeWith(obj);
        }
        return l5.d.d();
    }

    @Override // kotlin.coroutines.jvm.internal.d, kotlin.coroutines.jvm.internal.a
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }

    private final Object emit(k5.d<? super r> dVar, T t6) {
        g context = dVar.getContext();
        p1.e(context);
        g gVar = this.lastEmissionContext;
        if (gVar != context) {
            checkContext(context, gVar, t6);
        }
        this.completion = dVar;
        q qVar = SafeCollectorKt.emitFun;
        FlowCollector<T> flowCollector = this.collector;
        Objects.requireNonNull(flowCollector, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        return qVar.invoke(flowCollector, t6, this);
    }
}
