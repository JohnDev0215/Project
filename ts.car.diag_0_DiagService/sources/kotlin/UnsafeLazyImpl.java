package kotlin;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u00022\u00020\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0014\u001a\u00020\u0011¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00048\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u00048\u0016@\u0000X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0006R\u0016\u0010\u0012\u001a\u00020\u00118\u0000@\u0000X\u0088\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lkotlin/UnsafeLazyImpl;", "T", "Lkotlin/b;", "Ljava/io/Serializable;", "Lkotlin/Any;", "writeReplace", "()Ljava/lang/Object;", "Lkotlin/Boolean;", "isInitialized", "()Z", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "_value", "Ljava/lang/Object;", "getValue", "value", "Lkotlin/Function0;", "initializer", "Lkotlin/jvm/b/a;", "p0", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class UnsafeLazyImpl<T> implements b<T>, Serializable {
    private Object _value;
    private kotlin.jvm.b.a<? extends T> initializer;

    public UnsafeLazyImpl(@NotNull kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.q.c(aVar, "initializer");
        this.initializer = aVar;
        this._value = n.f1523a;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    public T getValue() {
        if (this._value == n.f1523a) {
            kotlin.jvm.b.a<? extends T> aVar = this.initializer;
            if (aVar == null) {
                kotlin.jvm.internal.q.h();
                throw null;
            }
            this._value = aVar.invoke();
            this.initializer = null;
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
