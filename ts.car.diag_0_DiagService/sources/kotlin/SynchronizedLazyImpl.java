package kotlin;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u00022\u00020\u0003B\u0019\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0018B)\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u0017\u0010\u001dJ\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00048\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00048\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0011\u001a\u00020\u00048\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0006R\u0016\u0010\u0013\u001a\u00020\u00128\u0000@\u0000X\u0088\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/b;", "Ljava/io/Serializable;", "Lkotlin/Any;", "writeReplace", "()Ljava/lang/Object;", "Lkotlin/Boolean;", "isInitialized", "()Z", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "lock", "Ljava/lang/Object;", "_value", "getValue", "value", "Lkotlin/Function0;", "initializer", "Lkotlin/jvm/b/a;", "p0", "p1", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "Lkotlin/Int;", "p2", "Lkotlin/jvm/internal/o;", "p3", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class SynchronizedLazyImpl<T> implements b<T>, Serializable {
    private volatile Object _value;
    private kotlin.jvm.b.a<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(@NotNull kotlin.jvm.b.a<? extends T> aVar, @Nullable Object obj) {
        kotlin.jvm.internal.q.c(aVar, "initializer");
        this.initializer = aVar;
        this._value = n.f1523a;
        this.lock = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public T getValue() {
        T tInvoke;
        T t = (T) this._value;
        if (t != n.f1523a) {
            return t;
        }
        synchronized (this.lock) {
            tInvoke = (T) this._value;
            if (tInvoke == n.f1523a) {
                kotlin.jvm.b.a<? extends T> aVar = this.initializer;
                if (aVar == null) {
                    kotlin.jvm.internal.q.h();
                    throw null;
                }
                tInvoke = aVar.invoke();
                this._value = tInvoke;
                this.initializer = null;
            }
        }
        return tInvoke;
    }

    public boolean isInitialized() {
        return this._value != n.f1523a;
    }

    @NotNull
    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ SynchronizedLazyImpl(kotlin.jvm.b.a aVar, Object obj, int i, kotlin.jvm.internal.o oVar) {
        this(aVar, (i & 2) != 0 ? null : obj);
    }
}
