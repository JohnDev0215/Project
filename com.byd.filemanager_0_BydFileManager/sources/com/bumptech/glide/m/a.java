package com.bumptech.glide.m;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: EncoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<C0079a<?>> f3404a = new ArrayList();

    /* JADX INFO: renamed from: com.bumptech.glide.m.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EncoderRegistry.java */
    private static final class C0079a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f3405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.bumptech.glide.load.a<T> f3406b;

        C0079a(Class<T> cls, com.bumptech.glide.load.a<T> aVar) {
            this.f3405a = cls;
            this.f3406b = aVar;
        }

        boolean a(Class<?> cls) {
            return this.f3405a.isAssignableFrom(cls);
        }
    }

    public synchronized <T> void a(Class<T> cls, com.bumptech.glide.load.a<T> aVar) {
        this.f3404a.add(new C0079a<>(cls, aVar));
    }

    public synchronized <T> com.bumptech.glide.load.a<T> b(Class<T> cls) {
        for (C0079a<?> c0079a : this.f3404a) {
            if (c0079a.a(cls)) {
                return (com.bumptech.glide.load.a<T>) c0079a.f3406b;
            }
        }
        return null;
    }
}
