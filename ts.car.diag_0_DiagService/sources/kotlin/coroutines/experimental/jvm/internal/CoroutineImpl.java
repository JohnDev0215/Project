package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.c;
import kotlin.coroutines.experimental.e.b;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Lambda;
import kotlin.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0016\u0012\u0006\u0010!\u001a\u00020\u001e\u0012\u0006\u0010\"\u001a\u00020\u0001¢\u0006\u0004\b#\u0010$J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\bH$¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0010\u0010\u0012R\u0016\u0010\u000f\u001a\u00020\u00018\u0000@\u0000X\u0085\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00148\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0019\u001a\u00020\u00148\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u001c\u001a\u00020\u00018\u0006@\u0000¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u00018\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u0013R\u0016\u0010\u001f\u001a\u00020\u001e8\u0000@\u0000X\u0085\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006%"}, d2 = {"Lkotlin/coroutines/experimental/jvm/internal/CoroutineImpl;", "Lkotlin/coroutines/experimental/a;", "Lkotlin/jvm/internal/Lambda;", "Lkotlin/Any;", "value", "Lkotlin/Unit;", "resume", "(Ljava/lang/Object;)V", "Lkotlin/Throwable;", "exception", "resumeWithException", "(Ljava/lang/Throwable;)V", "data", "doResume", "(Ljava/lang/Object;Ljava/lang/Throwable;)Ljava/lang/Object;", "completion", "create", "(Lkotlin/coroutines/experimental/a;)Lkotlin/coroutines/experimental/a;", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/a;)Lkotlin/coroutines/experimental/a;", "Lkotlin/coroutines/experimental/a;", "Lkotlin/coroutines/experimental/c;", "_context", "Lkotlin/coroutines/experimental/c;", "getContext", "()Lkotlin/coroutines/experimental/c;", "context", "getFacade", "()Lkotlin/coroutines/experimental/a;", "facade", "_facade", "Lkotlin/Int;", "label", "I", "p0", "p1", "<init>", "(ILkotlin/coroutines/experimental/Continuation;)V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public abstract class CoroutineImpl extends Lambda<Object> implements kotlin.coroutines.experimental.a<Object> {
    private final c _context;
    private kotlin.coroutines.experimental.a<Object> _facade;

    @JvmField
    @Nullable
    protected kotlin.coroutines.experimental.a<Object> completion;

    @JvmField
    protected int label;

    public CoroutineImpl(int i, @Nullable kotlin.coroutines.experimental.a<Object> aVar) {
        super(i);
        this.completion = aVar;
        this.label = aVar != null ? 0 : -1;
        kotlin.coroutines.experimental.a<Object> aVar2 = this.completion;
        this._context = aVar2 != null ? aVar2.getContext() : null;
    }

    @NotNull
    public kotlin.coroutines.experimental.a<q> create(@NotNull kotlin.coroutines.experimental.a<?> completion) {
        kotlin.jvm.internal.q.c(completion, "completion");
        throw new IllegalStateException("create(Continuation) has not been overridden");
    }

    @Nullable
    protected abstract Object doResume(@Nullable Object data, @Nullable Throwable exception);

    @Override // kotlin.coroutines.experimental.a
    @NotNull
    public c getContext() {
        c cVar = this._context;
        if (cVar != null) {
            return cVar;
        }
        kotlin.jvm.internal.q.h();
        throw null;
    }

    @NotNull
    public final kotlin.coroutines.experimental.a<Object> getFacade() {
        if (this._facade == null) {
            c cVar = this._context;
            if (cVar == null) {
                kotlin.jvm.internal.q.h();
                throw null;
            }
            this._facade = a.a(cVar, this);
        }
        kotlin.coroutines.experimental.a<Object> aVar = this._facade;
        if (aVar != null) {
            return aVar;
        }
        kotlin.jvm.internal.q.h();
        throw null;
    }

    @Override // kotlin.coroutines.experimental.a
    public void resume(@Nullable Object value) {
        kotlin.coroutines.experimental.a<Object> aVar = this.completion;
        if (aVar == null) {
            kotlin.jvm.internal.q.h();
            throw null;
        }
        try {
            Object objDoResume = doResume(value, null);
            if (objDoResume != b.a()) {
                if (aVar == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                aVar.resume(objDoResume);
            }
        } catch (Throwable th) {
            aVar.resumeWithException(th);
        }
    }

    @Override // kotlin.coroutines.experimental.a
    public void resumeWithException(@NotNull Throwable exception) {
        kotlin.jvm.internal.q.c(exception, "exception");
        kotlin.coroutines.experimental.a<Object> aVar = this.completion;
        if (aVar == null) {
            kotlin.jvm.internal.q.h();
            throw null;
        }
        try {
            Object objDoResume = doResume(null, exception);
            if (objDoResume != b.a()) {
                if (aVar == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                aVar.resume(objDoResume);
            }
        } catch (Throwable th) {
            aVar.resumeWithException(th);
        }
    }

    @NotNull
    public kotlin.coroutines.experimental.a<q> create(@Nullable Object value, @NotNull kotlin.coroutines.experimental.a<?> completion) {
        kotlin.jvm.internal.q.c(completion, "completion");
        throw new IllegalStateException("create(Any?;Continuation) has not been overridden");
    }
}
