package f2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import n1.k;

/* JADX INFO: compiled from: ResourceDecoderRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f6345a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, List<a<?, ?>>> f6346b = new HashMap();

    /* JADX INFO: compiled from: ResourceDecoderRegistry.java */
    private static class a<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Class<T> f6347a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Class<R> f6348b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final k<T, R> f6349c;

        public a(Class<T> cls, Class<R> cls2, k<T, R> kVar) {
            this.f6347a = cls;
            this.f6348b = cls2;
            this.f6349c = kVar;
        }

        public boolean a(Class<?> cls, Class<?> cls2) {
            return this.f6347a.isAssignableFrom(cls) && cls2.isAssignableFrom(this.f6348b);
        }
    }

    private synchronized List<a<?, ?>> c(String str) {
        List<a<?, ?>> arrayList;
        if (!this.f6345a.contains(str)) {
            this.f6345a.add(str);
        }
        arrayList = this.f6346b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f6346b.put(str, arrayList);
        }
        return arrayList;
    }

    public synchronized <T, R> void a(String str, k<T, R> kVar, Class<T> cls, Class<R> cls2) {
        c(str).add(new a<>(cls, cls2, kVar));
    }

    public synchronized <T, R> List<k<T, R>> b(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f6345a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f6346b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2)) {
                        arrayList.add(aVar.f6349c);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized <T, R> List<Class<R>> d(Class<T> cls, Class<R> cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<String> it = this.f6345a.iterator();
        while (it.hasNext()) {
            List<a<?, ?>> list = this.f6346b.get(it.next());
            if (list != null) {
                for (a<?, ?> aVar : list) {
                    if (aVar.a(cls, cls2) && !arrayList.contains(aVar.f6348b)) {
                        arrayList.add(aVar.f6348b);
                    }
                }
            }
        }
        return arrayList;
    }

    public synchronized void e(List<String> list) {
        ArrayList<String> arrayList = new ArrayList(this.f6345a);
        this.f6345a.clear();
        this.f6345a.addAll(list);
        for (String str : arrayList) {
            if (!list.contains(str)) {
                this.f6345a.add(str);
            }
        }
    }
}
