package com.squareup.moshi;

import com.squareup.moshi.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p.ds2;

/* JADX INFO: loaded from: classes.dex */
public final class o {
    static final List<f.e> e;
    private final List<f.e> a;
    private final int b;
    private final ThreadLocal<c> c = new ThreadLocal<>();
    private final Map<Object, f<?>> d = new LinkedHashMap();

    public static final class a {
        final List<f.e> a = new ArrayList();
        int b = 0;

        public a a(f.e eVar) {
            if (eVar == null) {
                throw new IllegalArgumentException("factory == null");
            }
            List<f.e> list = this.a;
            int i = this.b;
            this.b = i + 1;
            list.add(i, eVar);
            return this;
        }

        public a b(Object obj) {
            if (obj != null) {
                return a(com.squareup.moshi.a.d(obj));
            }
            throw new IllegalArgumentException("adapter == null");
        }

        public a c(f.e eVar) {
            if (eVar == null) {
                throw new IllegalArgumentException("factory == null");
            }
            this.a.add(eVar);
            return this;
        }

        public o d() {
            return new o(this);
        }
    }

    public static final class b<T> extends f<T> {
        final Type a;
        final String b;
        final Object c;
        f<T> d;

        public b(Type type, String str, Object obj) {
            this.a = type;
            this.b = str;
            this.c = obj;
        }

        @Override // com.squareup.moshi.f
        public T fromJson(JsonReader jsonReader) {
            f<T> fVar = this.d;
            if (fVar != null) {
                return fVar.fromJson(jsonReader);
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        @Override // com.squareup.moshi.f
        public void toJson(m mVar, T t) {
            f<T> fVar = this.d;
            if (fVar == null) {
                throw new IllegalStateException("JsonAdapter isn't ready");
            }
            fVar.toJson(mVar, t);
        }

        public String toString() {
            f<T> fVar = this.d;
            return fVar != null ? fVar.toString() : super.toString();
        }
    }

    public final class c {
        final List<b<?>> a = new ArrayList();
        final Deque<b<?>> b = new ArrayDeque();
        boolean c;

        public c() {
        }

        public <T> void a(f<T> fVar) {
            this.b.getLast().d = fVar;
        }

        public IllegalArgumentException b(IllegalArgumentException illegalArgumentException) {
            if (this.c) {
                return illegalArgumentException;
            }
            this.c = true;
            if (this.b.size() == 1 && this.b.getFirst().b == null) {
                return illegalArgumentException;
            }
            StringBuilder sb = new StringBuilder(illegalArgumentException.getMessage());
            Iterator<b<?>> itDescendingIterator = this.b.descendingIterator();
            while (itDescendingIterator.hasNext()) {
                b<?> next = itDescendingIterator.next();
                sb.append("\nfor ");
                sb.append(next.a);
                if (next.b != null) {
                    sb.append(' ');
                    sb.append(next.b);
                }
            }
            return new IllegalArgumentException(sb.toString(), illegalArgumentException);
        }

        public void c(boolean z) {
            this.b.removeLast();
            if (this.b.isEmpty()) {
                o.this.c.remove();
                if (z) {
                    synchronized (o.this.d) {
                        try {
                            int size = this.a.size();
                            for (int i = 0; i < size; i++) {
                                b<?> bVar = this.a.get(i);
                                f<T> fVar = (f) o.this.d.put(bVar.c, bVar.d);
                                if (fVar != 0) {
                                    bVar.d = fVar;
                                    o.this.d.put(bVar.c, fVar);
                                }
                            }
                        } finally {
                        }
                    }
                }
            }
        }

        public <T> f<T> d(Type type, String str, Object obj) {
            int size = this.a.size();
            for (int i = 0; i < size; i++) {
                b<?> bVar = this.a.get(i);
                if (bVar.c.equals(obj)) {
                    this.b.add(bVar);
                    f<T> fVar = (f<T>) bVar.d;
                    return fVar != null ? fVar : bVar;
                }
            }
            b<?> bVar2 = new b<>(type, str, obj);
            this.a.add(bVar2);
            this.b.add(bVar2);
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(5);
        e = arrayList;
        arrayList.add(q.a);
        arrayList.add(e.b);
        arrayList.add(n.c);
        arrayList.add(com.squareup.moshi.b.c);
        arrayList.add(p.a);
        arrayList.add(d.d);
    }

    public o(a aVar) {
        int size = aVar.a.size();
        List<f.e> list = e;
        ArrayList arrayList = new ArrayList(list.size() + size);
        arrayList.addAll(aVar.a);
        arrayList.addAll(list);
        this.a = Collections.unmodifiableList(arrayList);
        this.b = aVar.b;
    }

    private Object g(Type type, Set<? extends Annotation> set) {
        return set.isEmpty() ? type : Arrays.asList(type, set);
    }

    public <T> f<T> c(Class<T> cls) {
        return e(cls, ds2.a);
    }

    public <T> f<T> d(Type type) {
        return e(type, ds2.a);
    }

    public <T> f<T> e(Type type, Set<? extends Annotation> set) {
        return f(type, set, null);
    }

    public <T> f<T> f(Type type, Set<? extends Annotation> set, String str) {
        if (type == null) {
            throw new NullPointerException("type == null");
        }
        if (set == null) {
            throw new NullPointerException("annotations == null");
        }
        Type typeP = ds2.p(ds2.a(type));
        Object objG = g(typeP, set);
        synchronized (this.d) {
            try {
                f<T> fVar = (f) this.d.get(objG);
                if (fVar != null) {
                    return fVar;
                }
                c cVar = this.c.get();
                if (cVar == null) {
                    cVar = new c();
                    this.c.set(cVar);
                }
                f<T> fVarD = cVar.d(typeP, str, objG);
                try {
                    if (fVarD != null) {
                        return fVarD;
                    }
                    try {
                        int size = this.a.size();
                        for (int i = 0; i < size; i++) {
                            f<T> fVar2 = (f<T>) this.a.get(i).a(typeP, set, this);
                            if (fVar2 != null) {
                                cVar.a(fVar2);
                                cVar.c(true);
                                return fVar2;
                            }
                        }
                        throw new IllegalArgumentException("No JsonAdapter for " + ds2.u(typeP, set));
                    } catch (IllegalArgumentException e2) {
                        throw cVar.b(e2);
                    }
                } finally {
                    cVar.c(false);
                }
            } finally {
            }
        }
    }

    public a h() {
        a aVar = new a();
        int i = this.b;
        for (int i2 = 0; i2 < i; i2++) {
            aVar.a(this.a.get(i2));
        }
        int size = this.a.size() - e.size();
        for (int i3 = this.b; i3 < size; i3++) {
            aVar.c(this.a.get(i3));
        }
        return aVar;
    }

    public <T> f<T> i(f.e eVar, Type type, Set<? extends Annotation> set) {
        if (set == null) {
            throw new NullPointerException("annotations == null");
        }
        Type typeP = ds2.p(ds2.a(type));
        int iIndexOf = this.a.indexOf(eVar);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Unable to skip past unknown factory " + eVar);
        }
        int size = this.a.size();
        for (int i = iIndexOf + 1; i < size; i++) {
            f<T> fVar = (f<T>) this.a.get(i).a(typeP, set, this);
            if (fVar != null) {
                return fVar;
            }
        }
        throw new IllegalArgumentException("No next JsonAdapter for " + ds2.u(typeP, set));
    }
}
