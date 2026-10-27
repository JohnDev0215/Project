package g5;

import java.io.Serializable;

/* JADX INFO: compiled from: LazyJVM.kt */
/* JADX INFO: loaded from: classes.dex */
final class n<T> implements f<T>, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private r5.a<? extends T> f6559e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile Object f6560g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Object f6561h;

    public n(r5.a<? extends T> initializer, Object obj) {
        kotlin.jvm.internal.l.e(initializer, "initializer");
        this.f6559e = initializer;
        this.f6560g = p.f6562a;
        this.f6561h = obj == null ? this : obj;
    }

    private final Object writeReplace() {
        return new d(getValue());
    }

    public boolean a() {
        return this.f6560g != p.f6562a;
    }

    @Override // g5.f
    public T getValue() {
        T tInvoke;
        T t6 = (T) this.f6560g;
        p pVar = p.f6562a;
        if (t6 != pVar) {
            return t6;
        }
        synchronized (this.f6561h) {
            tInvoke = (T) this.f6560g;
            if (tInvoke == pVar) {
                r5.a<? extends T> aVar = this.f6559e;
                kotlin.jvm.internal.l.c(aVar);
                tInvoke = aVar.invoke();
                this.f6560g = tInvoke;
                this.f6559e = null;
            }
        }
        return tInvoke;
    }

    public String toString() {
        return a() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    public /* synthetic */ n(r5.a aVar, Object obj, int i7, kotlin.jvm.internal.g gVar) {
        this(aVar, (i7 & 2) != 0 ? null : obj);
    }
}
