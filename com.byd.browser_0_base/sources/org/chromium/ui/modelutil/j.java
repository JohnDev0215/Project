package org.chromium.ui.modelutil;

import android.view.View;
import defpackage.w62;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: PropertyModelChangeProcessor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j<M extends k<P>, V, P> {
    public final V a;
    public final M b;
    public final a<M, V, P> c;
    public final w62 d = new w62(1, this);

    /* JADX INFO: compiled from: PropertyModelChangeProcessor.java */
    public interface a<M, V, P> {
        void b(k kVar, Object obj, Object obj2);
    }

    public j(M m, V v, a<M, V, P> aVar, boolean z) {
        this.b = m;
        this.a = v;
        this.c = aVar;
        if (z) {
            for (Object obj : m.b()) {
                this.c.b(this.b, this.a, obj);
            }
        }
        m.a(this.d);
    }

    public static j a(PropertyModel propertyModel, View view, a aVar, boolean z) {
        return new j(propertyModel, view, aVar, z);
    }

    public static <M extends k<P>, V, P> j<M, V, P> b(M m, V v, a<M, V, P> aVar) {
        return new j<>(m, v, aVar, true);
    }

    public final void c() {
        this.b.d(this.d);
    }
}
