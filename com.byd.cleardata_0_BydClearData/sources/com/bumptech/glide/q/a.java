package com.bumptech.glide.q;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<C0085a<?>> f2478a = new ArrayList();

    /* JADX INFO: renamed from: com.bumptech.glide.q.a$a, reason: collision with other inner class name */
    private static final class C0085a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f2479a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.bumptech.glide.load.d<T> f2480b;

        C0085a(Class<T> cls, com.bumptech.glide.load.d<T> dVar) {
            this.f2479a = cls;
            this.f2480b = dVar;
        }

        boolean a(Class<?> cls) {
            return this.f2479a.isAssignableFrom(cls);
        }
    }

    public synchronized <T> void a(Class<T> cls, com.bumptech.glide.load.d<T> dVar) {
        this.f2478a.add(new C0085a<>(cls, dVar));
    }

    public synchronized <T> com.bumptech.glide.load.d<T> b(Class<T> cls) {
        for (C0085a<?> c0085a : this.f2478a) {
            if (c0085a.a(cls)) {
                return (com.bumptech.glide.load.d<T>) c0085a.f2480b;
            }
        }
        return null;
    }
}
