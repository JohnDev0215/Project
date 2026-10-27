package com.bumptech.glide.k;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: EncoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<C0084a<?>> f2702a = new ArrayList();

    /* JADX INFO: renamed from: com.bumptech.glide.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EncoderRegistry.java */
    private static final class C0084a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f2703a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final com.bumptech.glide.load.a<T> f2704b;

        C0084a(Class<T> cls, com.bumptech.glide.load.a<T> aVar) {
            this.f2703a = cls;
            this.f2704b = aVar;
        }

        boolean a(Class<?> cls) {
            return this.f2703a.isAssignableFrom(cls);
        }
    }

    public synchronized <T> void a(Class<T> cls, com.bumptech.glide.load.a<T> aVar) {
        this.f2702a.add(new C0084a<>(cls, aVar));
    }

    public synchronized <T> com.bumptech.glide.load.a<T> b(Class<T> cls) {
        for (C0084a<?> c0084a : this.f2702a) {
            if (c0084a.a(cls)) {
                return (com.bumptech.glide.load.a<T>) c0084a.f2704b;
            }
        }
        return null;
    }
}
