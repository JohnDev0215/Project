package androidx.databinding;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: WeakListener.java */
/* JADX INFO: loaded from: classes.dex */
class r<T> extends WeakReference<ViewDataBinding> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o<T> f2082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final int f2083b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private T f2084c;

    public r(ViewDataBinding viewDataBinding, int i7, o<T> oVar, ReferenceQueue<ViewDataBinding> referenceQueue) {
        super(viewDataBinding, referenceQueue);
        this.f2083b = i7;
        this.f2082a = oVar;
    }

    protected ViewDataBinding a() {
        ViewDataBinding viewDataBinding = (ViewDataBinding) get();
        if (viewDataBinding == null) {
            e();
        }
        return viewDataBinding;
    }

    public T b() {
        return this.f2084c;
    }

    public void c(androidx.lifecycle.o oVar) {
        this.f2082a.b(oVar);
    }

    public void d(T t6) {
        e();
        this.f2084c = t6;
        if (t6 != null) {
            this.f2082a.a(t6);
        }
    }

    public boolean e() {
        boolean z6;
        T t6 = this.f2084c;
        if (t6 != null) {
            this.f2082a.d(t6);
            z6 = true;
        } else {
            z6 = false;
        }
        this.f2084c = null;
        return z6;
    }
}
