package kotlin;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000 \u0018*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u00022\u00020\u0003:\u0001\u0019B\u0011\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0012¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00048\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00048\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0011\u001a\u00020\u00048\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0006R\u0016\u0010\u0013\u001a\u00020\u00128\u0000@\u0000X\u0088\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Lkotlin/SafePublicationLazyImpl;", "T", "Lkotlin/b;", "Ljava/io/Serializable;", "Lkotlin/Any;", "writeReplace", "()Ljava/lang/Object;", "Lkotlin/Boolean;", "isInitialized", "()Z", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "final", "Ljava/lang/Object;", "_value", "getValue", "value", "Lkotlin/Function0;", "initializer", "Lkotlin/jvm/b/a;", "p0", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "Companion", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class SafePublicationLazyImpl<T> implements b<T>, Serializable {
    private static final AtomicReferenceFieldUpdater<SafePublicationLazyImpl<?>, Object> valueUpdater = AtomicReferenceFieldUpdater.newUpdater(SafePublicationLazyImpl.class, Object.class, "_value");
    private volatile Object _value;
    private final Object final;
    private volatile kotlin.jvm.b.a<? extends T> initializer;

    public SafePublicationLazyImpl(@NotNull kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.q.c(aVar, "initializer");
        this.initializer = aVar;
        this._value = n.f1523a;
        this.final = n.f1523a;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public T getValue() {
        T t = (T) this._value;
        if (t != n.f1523a) {
            return t;
        }
        kotlin.jvm.b.a<? extends T> aVar = this.initializer;
        if (aVar != null) {
            T tInvoke = aVar.invoke();
            if (valueUpdater.compareAndSet(this, n.f1523a, tInvoke)) {
                this.initializer = null;
                return tInvoke;
            }
        }
        return (T) this._value;
    }

    public boolean isInitialized() {
        return this._value != n.f1523a;
    }

    @NotNull
    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
