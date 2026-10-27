package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: CachedProviderAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class bl<T> extends x62<T> implements b72<T> {
    public final Callback<bl> c;
    public T d;

    public bl(x62<T> x62Var, T t, Callback<bl> callback) {
        super(x62Var.b);
        this.c = callback;
        x62Var.a.add(this);
        this.d = t;
    }

    @Override // defpackage.b72
    public final void a(int i, T t) {
        this.d = t;
        Callback<bl> callback = this.c;
        if (callback != null) {
            callback.onResult(this);
        }
    }
}
