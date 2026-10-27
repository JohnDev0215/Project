package com.bumptech.glide.q;

import com.bumptech.glide.load.l;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<?>> f2492a = new ArrayList();

    private static final class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f2493a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final l<T> f2494b;

        a(Class<T> cls, l<T> lVar) {
            this.f2493a = cls;
            this.f2494b = lVar;
        }

        boolean a(Class<?> cls) {
            return this.f2493a.isAssignableFrom(cls);
        }
    }

    public synchronized <Z> void a(Class<Z> cls, l<Z> lVar) {
        this.f2492a.add(new a<>(cls, lVar));
    }

    public synchronized <Z> l<Z> b(Class<Z> cls) {
        int size = this.f2492a.size();
        for (int i = 0; i < size; i++) {
            a<?> aVar = this.f2492a.get(i);
            if (aVar.a(cls)) {
                return (l<Z>) aVar.f2494b;
            }
        }
        return null;
    }
}
