package kotlin.coroutines.jvm.internal;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.SinceKotlin;
import kotlin.Throwable;
import kotlin.coroutines.CoroutineContext;
import kotlin.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b!\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0011\b\u0016\u0012\u0006\u0010\"\u001a\u00020\u0001¢\u0006\u0004\b#\u0010$J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H$¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u000e\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R\u0019\u0010\r\u001a\u00020\u00018\u0006@\u0000¢\u0006\f\n\u0004\b\r\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001d\u001a\u00020\u00028\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010!\u001a\u00020\u001e86@\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006%"}, d2 = {"Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/a;", "Lkotlin/coroutines/jvm/internal/b;", "Ljava/io/Serializable;", "Lkotlin/Any;", "result", "Lkotlin/Unit;", "resumeWith", "(Ljava/lang/Object;)V", "invokeSuspend", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "()V", "completion", "create", "(Lkotlin/coroutines/a;)Lkotlin/coroutines/a;", "value", "(Ljava/lang/Object;Lkotlin/coroutines/a;)Lkotlin/coroutines/a;", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "Lkotlin/coroutines/a;", "getCompletion", "()Lkotlin/coroutines/a;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/b;", "callerFrame", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "context", "p0", "<init>", "(Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public abstract class BaseContinuationImpl implements kotlin.coroutines.a<Object>, b, Serializable {

    @Nullable
    private final kotlin.coroutines.a<Object> completion;

    public BaseContinuationImpl(@Nullable kotlin.coroutines.a<Object> aVar) {
        this.completion = aVar;
    }

    @NotNull
    public kotlin.coroutines.a<q> create(@NotNull kotlin.coroutines.a<?> completion) {
        kotlin.jvm.internal.q.c(completion, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @Nullable
    public b getCallerFrame() {
        kotlin.coroutines.a<Object> aVar = this.completion;
        if (!(aVar instanceof b)) {
            aVar = null;
        }
        return (b) aVar;
    }

    @Nullable
    public final kotlin.coroutines.a<Object> getCompletion() {
        return this.completion;
    }

    @Override // kotlin.coroutines.a
    @NotNull
    public abstract /* synthetic */ CoroutineContext getContext();

    @Nullable
    public StackTraceElement getStackTraceElement() {
        return c.d(this);
    }

    @Nullable
    protected abstract Object invokeSuspend(@NotNull Object result);

    protected void releaseIntercepted() {
    }

    @Override // kotlin.coroutines.a
    public final void resumeWith(@NotNull Object result) {
        Object objInvokeSuspend;
        while (true) {
            d.a(this);
            kotlin.coroutines.a<Object> aVar = this.completion;
            if (aVar == null) {
                kotlin.jvm.internal.q.h();
                throw null;
            }
            try {
                objInvokeSuspend = this.invokeSuspend(result);
            } catch (Throwable th) {
                Result.Companion companion = Result.INSTANCE;
                result = Result.m2constructorimpl(Throwable.a(th));
            }
            if (objInvokeSuspend == kotlin.coroutines.intrinsics.c.a()) {
                return;
            }
            Result.Companion companion2 = Result.INSTANCE;
            result = Result.m2constructorimpl(objInvokeSuspend);
            this.releaseIntercepted();
            if (!(aVar instanceof BaseContinuationImpl)) {
                aVar.resumeWith(result);
                return;
            }
            this = (BaseContinuationImpl) aVar;
        }
    }

    @NotNull
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

    @NotNull
    public kotlin.coroutines.a<q> create(@Nullable Object value, @NotNull kotlin.coroutines.a<?> completion) {
        kotlin.jvm.internal.q.c(completion, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
