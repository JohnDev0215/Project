package kotlin.coroutines.jvm.internal;

import kotlin.jvm.internal.i;
import kotlin.l.c;

/* JADX INFO: compiled from: ContinuationImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    private final kotlin.l.c _context;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient kotlin.l.a<Object> f5100b;

    public ContinuationImpl(kotlin.l.a<Object> aVar, kotlin.l.c cVar) {
        super(aVar);
        this._context = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.l.a
    public kotlin.l.c getContext() {
        kotlin.l.c cVar = this._context;
        if (cVar == null) {
            i.g();
        }
        return cVar;
    }

    public final kotlin.l.a<Object> intercepted() {
        kotlin.l.a<Object> aVarB = this.f5100b;
        if (aVarB == null) {
            kotlin.l.b bVar = (kotlin.l.b) getContext().c(kotlin.l.b.f5126a);
            if (bVar == null || (aVarB = bVar.b(this)) == null) {
                aVarB = this;
            }
            this.f5100b = aVarB;
        }
        return aVarB;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    protected void releaseIntercepted() {
        kotlin.l.a<?> aVar = this.f5100b;
        if (aVar != null && aVar != this) {
            c.a aVarC = getContext().c(kotlin.l.b.f5126a);
            if (aVarC == null) {
                i.g();
            }
            ((kotlin.l.b) aVarC).a(aVar);
        }
        this.f5100b = a.f5101b;
    }

    public ContinuationImpl(kotlin.l.a<Object> aVar) {
        this(aVar, aVar != null ? aVar.getContext() : null);
    }
}
