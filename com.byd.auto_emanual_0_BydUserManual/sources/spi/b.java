package spi;

import android.content.Context;

/* JADX INFO: compiled from: ServiceInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class b<S, P> {
    public Class<S> a;
    public Class<P> b;

    public b(Class<S> cls, Class<P> cls2) {
        this.a = cls;
        this.b = cls2;
    }

    public S a(Context context) {
        return (S) d.a().a(context, this.b);
    }
}
