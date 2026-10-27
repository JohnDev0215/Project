package kotlin;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
final class SafePublicationLazyImpl<T> implements kotlin.a<T>, Serializable {
    public static final a Companion = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater<SafePublicationLazyImpl<?>, Object> f4064b = AtomicReferenceFieldUpdater.newUpdater(SafePublicationLazyImpl.class, Object.class, "_value");
    private volatile Object _value;

    /* JADX INFO: renamed from: final, reason: not valid java name */
    private final Object f0final;
    private volatile kotlin.jvm.b.a<? extends T> initializer;

    /* JADX INFO: compiled from: LazyJVM.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public SafePublicationLazyImpl(kotlin.jvm.b.a<? extends T> aVar) {
        kotlin.jvm.internal.h.d(aVar, "initializer");
        this.initializer = aVar;
        j jVar = j.f4070a;
        this._value = jVar;
        this.f0final = jVar;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.a
    public T getValue() {
        T t = (T) this._value;
        j jVar = j.f4070a;
        if (t != jVar) {
            return t;
        }
        kotlin.jvm.b.a<? extends T> aVar = this.initializer;
        if (aVar != null) {
            T tInvoke = aVar.invoke();
            if (f4064b.compareAndSet(this, jVar, tInvoke)) {
                this.initializer = null;
                return tInvoke;
            }
        }
        return (T) this._value;
    }

    public boolean isInitialized() {
        return this._value != j.f4070a;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
