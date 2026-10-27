package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b!\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012B\u0011\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0013J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0014¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\nR\u0016\u0010\r\u001a\u00020\b8\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u000e¨\u0006\u0014"}, d2 = {"Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/a;", "intercepted", "()Lkotlin/coroutines/a;", "Lkotlin/Unit;", "releaseIntercepted", "()V", "Lkotlin/coroutines/CoroutineContext;", "_context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "Lkotlin/coroutines/a;", "p0", "p1", "<init>", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;)V", "(Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public abstract class ContinuationImpl extends BaseContinuationImpl {
    private final CoroutineContext _context;
    private transient kotlin.coroutines.a<Object> intercepted;

    public ContinuationImpl(@Nullable kotlin.coroutines.a<Object> aVar, @Nullable CoroutineContext coroutineContext) {
        super(aVar);
        this._context = coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.a
    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this._context;
        if (coroutineContext != null) {
            return coroutineContext;
        }
        q.h();
        throw null;
    }

    @NotNull
    public final kotlin.coroutines.a<Object> intercepted() {
        kotlin.coroutines.a<Object> aVarB = this.intercepted;
        if (aVarB == null) {
            kotlin.coroutines.b bVar = (kotlin.coroutines.b) getContext().get(kotlin.coroutines.b.f1489b);
            if (bVar == null || (aVarB = bVar.b(this)) == null) {
                aVarB = this;
            }
            this.intercepted = aVarB;
        }
        return aVarB;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    protected void releaseIntercepted() {
        kotlin.coroutines.a<?> aVar = this.intercepted;
        if (aVar != null && aVar != this) {
            CoroutineContext.a aVar2 = getContext().get(kotlin.coroutines.b.f1489b);
            if (aVar2 == null) {
                q.h();
                throw null;
            }
            ((kotlin.coroutines.b) aVar2).a(aVar);
        }
        this.intercepted = a.f1496c;
    }

    public ContinuationImpl(@Nullable kotlin.coroutines.a<Object> aVar) {
        this(aVar, aVar != null ? aVar.getContext() : null);
    }
}
