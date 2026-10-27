package defpackage;

import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: OneshotSupplierImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class rs1<T> implements qs1<T> {
    public final s62<T> e = new s62<>();
    public final ThreadUtils.a f = new ThreadUtils.a();

    public final void a(T t) {
        this.f.getClass();
        this.e.b(t);
    }

    @Override // defpackage.nw2
    public final T get() {
        this.f.getClass();
        s62<T> s62Var = this.e;
        if (s62Var.d()) {
            return s62Var.b;
        }
        return null;
    }

    @Override // defpackage.qs1
    public final T h(Callback<T> callback) {
        this.f.getClass();
        this.e.g(callback);
        return get();
    }
}
