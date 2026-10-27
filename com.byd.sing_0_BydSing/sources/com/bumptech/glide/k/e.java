package com.bumptech.glide.k;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ResourceDecoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f2711a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, List<a<?, ?>>> f2712b = new HashMap();

    /* JADX INFO: compiled from: ResourceDecoderRegistry.java */
    private static class a<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f2713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<R> f2714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final com.bumptech.glide.load.f<T, R> f2715c;

        public a(Class<T> cls, Class<R> cls2, com.bumptech.glide.load.f<T, R> fVar) {
            this.f2713a = cls;
            this.f2714b = cls2;
            this.f2715c = fVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.f2713a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f2714b);
        }
    }

    private synchronized List<a<?, ?>> c(String str) {
        List<a<?, ?>> arrayList;
        if (!this.f2711a.contains(str)) {
            this.f2711a.add(str);
        }
        arrayList = this.f2712b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f2712b.put(str, arrayList);
        }
        return arrayList;
    }

    public synchronized <T, R> void a(String str, com.bumptech.glide.load.f<T, R> fVar, Class<T> cls, Class<R> cls2) {
        c(str).add(new a<>(cls, cls2, fVar));
    }

    public synchronized <T, R> List<com.bumptech.glide.load.f<T, R>> b(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f2711a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f2712b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2)) {
                        arrayList.add(aVar.f2715c);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized <T, R> List<Class<R>> d(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f2711a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f2712b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2) && !arrayList.contains(aVar.f2714b)) {
                        arrayList.add(aVar.f2714b);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized void e(List<String> list) {
        ArrayList<String> arrayList = new ArrayList(this.f2711a);
        this.f2711a.clear();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.f2711a.add(it.next());
        }
        for (String str : arrayList) {
            if (!list.contains(str)) {
                this.f2711a.add(str);
            }
        }
    }
}
