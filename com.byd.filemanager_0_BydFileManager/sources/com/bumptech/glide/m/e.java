package com.bumptech.glide.m;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ResourceDecoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f3413a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, List<a<?, ?>>> f3414b = new HashMap();

    /* JADX INFO: compiled from: ResourceDecoderRegistry.java */
    private static class a<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f3415a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<R> f3416b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final com.bumptech.glide.load.f<T, R> f3417c;

        public a(Class<T> cls, Class<R> cls2, com.bumptech.glide.load.f<T, R> fVar) {
            this.f3415a = cls;
            this.f3416b = cls2;
            this.f3417c = fVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.f3415a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f3416b);
        }
    }

    private synchronized List<a<?, ?>> c(String str) {
        List<a<?, ?>> arrayList;
        if (!this.f3413a.contains(str)) {
            this.f3413a.add(str);
        }
        arrayList = this.f3414b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f3414b.put(str, arrayList);
        }
        return arrayList;
    }

    public synchronized <T, R> void a(String str, com.bumptech.glide.load.f<T, R> fVar, Class<T> cls, Class<R> cls2) {
        c(str).add(new a<>(cls, cls2, fVar));
    }

    public synchronized <T, R> List<com.bumptech.glide.load.f<T, R>> b(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f3413a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f3414b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2)) {
                        arrayList.add(aVar.f3417c);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized <T, R> List<Class<R>> d(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f3413a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f3414b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2) && !arrayList.contains(aVar.f3416b)) {
                        arrayList.add(aVar.f3416b);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized void e(List<String> list) {
        ArrayList<String> arrayList = new ArrayList(this.f3413a);
        this.f3413a.clear();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.f3413a.add(it.next());
        }
        for (String str : arrayList) {
            if (!list.contains(str)) {
                this.f3413a.add(str);
            }
        }
    }
}
