package org.chromium.ui.modelutil;

import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: compiled from: PropertyObservable.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class k<T> {
    public final org.chromium.base.b<a<T>> a = new org.chromium.base.b<>();

    /* JADX INFO: compiled from: PropertyObservable.java */
    public interface a<T> {
        void a(k<T> kVar, T t);
    }

    public final void a(a<T> aVar) {
        this.a.a(aVar);
    }

    public abstract ArrayList b();

    public final void c(T t) {
        Iterator<a<T>> it = this.a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(this, t);
            }
        }
    }

    public final void d(a<T> aVar) {
        this.a.c(aVar);
    }
}
