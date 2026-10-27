package com.bumptech.glide.q;

import com.bumptech.glide.load.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f2487a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, List<a<?, ?>>> f2488b = new HashMap();

    private static class a<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f2489a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<R> f2490b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final k<T, R> f2491c;

        public a(Class<T> cls, Class<R> cls2, k<T, R> kVar) {
            this.f2489a = cls;
            this.f2490b = cls2;
            this.f2491c = kVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.f2489a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f2490b);
        }
    }

    private synchronized List<a<?, ?>> c(String str) {
        List<a<?, ?>> arrayList;
        if (!this.f2487a.contains(str)) {
            this.f2487a.add(str);
        }
        arrayList = this.f2488b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f2488b.put(str, arrayList);
        }
        return arrayList;
    }

    public synchronized <T, R> void a(String str, k<T, R> kVar, Class<T> cls, Class<R> cls2) {
        c(str).add(new a<>(cls, cls2, kVar));
    }

    public synchronized <T, R> List<k<T, R>> b(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f2487a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f2488b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2)) {
                        arrayList.add(aVar.f2491c);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized <T, R> List<Class<R>> d(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f2487a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f2488b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2) && !arrayList.contains(aVar.f2490b)) {
                        arrayList.add(aVar.f2490b);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized void e(List<String> list) {
        ArrayList<String> arrayList = new ArrayList(this.f2487a);
        this.f2487a.clear();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.f2487a.add(it.next());
        }
        for (String str : arrayList) {
            if (!list.contains(str)) {
                this.f2487a.add(str);
            }
        }
    }
}
