package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: RequiredCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class lb2<T> implements Callback<T> {
    public Callback<T> e;

    public lb2(Callback<T> callback) {
        this.e = callback;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(T t) {
        this.e.onResult(t);
        this.e = null;
    }
}
