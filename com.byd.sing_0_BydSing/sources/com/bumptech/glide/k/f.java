package com.bumptech.glide.k;

import com.bumptech.glide.load.g;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ResourceEncoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<?>> f2716a = new ArrayList();

    /* JADX INFO: compiled from: ResourceEncoderRegistry.java */
    private static final class a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f2717a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final g<T> f2718b;

        a(Class<T> cls, g<T> gVar) {
            this.f2717a = cls;
            this.f2718b = gVar;
        }

        boolean a(Class<?> cls) {
            return this.f2717a.isAssignableFrom(cls);
        }
    }

    public synchronized <Z> void a(Class<Z> cls, g<Z> gVar) {
        this.f2716a.add(new a<>(cls, gVar));
    }

    public synchronized <Z> g<Z> b(Class<Z> cls) {
        int size = this.f2716a.size();
        for (int i = 0; i < size; i++) {
            a<?> aVar = this.f2716a.get(i);
            if (aVar.a(cls)) {
                return (g<Z>) aVar.f2718b;
            }
        }
        return null;
    }
}
