package h;

import f.a0;
import f.f0;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: ParameterHandler.java */
/* JADX INFO: loaded from: classes.dex */
abstract class p<T> {

    /* JADX INFO: compiled from: ParameterHandler.java */
    class a extends p<Iterable<T>> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable Iterable<T> iterable) {
            if (iterable == null) {
                return;
            }
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                p.this.a(rVar, it.next());
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    class b extends p<Object> {
        b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // h.p
        void a(r rVar, @Nullable Object obj) {
            if (obj == null) {
                return;
            }
            int length = Array.getLength(obj);
            for (int i2 = 0; i2 < length; i2++) {
                p.this.a(rVar, Array.get(obj, i2));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class c<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h.h<T, f0> f5612c;

        c(Method method, int i2, h.h<T, f0> hVar) {
            this.f5610a = method;
            this.f5611b = i2;
            this.f5612c = hVar;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            if (t == null) {
                throw y.o(this.f5610a, this.f5611b, "Body parameter value must not be null.", new Object[0]);
            }
            try {
                rVar.l(this.f5612c.a(t));
            } catch (IOException e2) {
                throw y.p(this.f5610a, e2, this.f5611b, "Unable to convert " + t + " to RequestBody", new Object[0]);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class d<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5613a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h.h<T, String> f5614b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f5615c;

        d(String str, h.h<T, String> hVar, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.f5613a = str;
            this.f5614b = hVar;
            this.f5615c = z;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            String strA;
            if (t == null || (strA = this.f5614b.a(t)) == null) {
                return;
            }
            rVar.a(this.f5613a, strA, this.f5615c);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class e<T> extends p<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5616a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5617b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h.h<T, String> f5618c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f5619d;

        e(Method method, int i2, h.h<T, String> hVar, boolean z) {
            this.f5616a = method;
            this.f5617b = i2;
            this.f5618c = hVar;
            this.f5619d = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable Map<String, T> map) {
            if (map == null) {
                throw y.o(this.f5616a, this.f5617b, "Field map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw y.o(this.f5616a, this.f5617b, "Field map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw y.o(this.f5616a, this.f5617b, "Field map contained null value for key '" + key + "'.", new Object[0]);
                }
                String strA = this.f5618c.a(value);
                if (strA == null) {
                    throw y.o(this.f5616a, this.f5617b, "Field map value '" + value + "' converted to null by " + this.f5618c.getClass().getName() + " for key '" + key + "'.", new Object[0]);
                }
                rVar.a(key, strA, this.f5619d);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class f<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5620a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h.h<T, String> f5621b;

        f(String str, h.h<T, String> hVar) {
            Objects.requireNonNull(str, "name == null");
            this.f5620a = str;
            this.f5621b = hVar;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            String strA;
            if (t == null || (strA = this.f5621b.a(t)) == null) {
                return;
            }
            rVar.b(this.f5620a, strA);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class g<T> extends p<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5622a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5623b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h.h<T, String> f5624c;

        g(Method method, int i2, h.h<T, String> hVar) {
            this.f5622a = method;
            this.f5623b = i2;
            this.f5624c = hVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable Map<String, T> map) {
            if (map == null) {
                throw y.o(this.f5622a, this.f5623b, "Header map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw y.o(this.f5622a, this.f5623b, "Header map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw y.o(this.f5622a, this.f5623b, "Header map contained null value for key '" + key + "'.", new Object[0]);
                }
                rVar.b(key, this.f5624c.a(value));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class h extends p<f.w> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5625a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5626b;

        h(Method method, int i2) {
            this.f5625a = method;
            this.f5626b = i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable f.w wVar) {
            if (wVar == null) {
                throw y.o(this.f5625a, this.f5626b, "Headers parameter must not be null.", new Object[0]);
            }
            rVar.c(wVar);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class i<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5627a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5628b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final f.w f5629c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final h.h<T, f0> f5630d;

        i(Method method, int i2, f.w wVar, h.h<T, f0> hVar) {
            this.f5627a = method;
            this.f5628b = i2;
            this.f5629c = wVar;
            this.f5630d = hVar;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            if (t == null) {
                return;
            }
            try {
                rVar.d(this.f5629c, this.f5630d.a(t));
            } catch (IOException e2) {
                throw y.o(this.f5627a, this.f5628b, "Unable to convert " + t + " to RequestBody", e2);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class j<T> extends p<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5631a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5632b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h.h<T, f0> f5633c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f5634d;

        j(Method method, int i2, h.h<T, f0> hVar, String str) {
            this.f5631a = method;
            this.f5632b = i2;
            this.f5633c = hVar;
            this.f5634d = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable Map<String, T> map) {
            if (map == null) {
                throw y.o(this.f5631a, this.f5632b, "Part map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw y.o(this.f5631a, this.f5632b, "Part map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw y.o(this.f5631a, this.f5632b, "Part map contained null value for key '" + key + "'.", new Object[0]);
                }
                rVar.d(f.w.g("Content-Disposition", "form-data; name=\"" + key + "\"", "Content-Transfer-Encoding", this.f5634d), this.f5633c.a(value));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class k<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5635a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5636b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f5637c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final h.h<T, String> f5638d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f5639e;

        k(Method method, int i2, String str, h.h<T, String> hVar, boolean z) {
            this.f5635a = method;
            this.f5636b = i2;
            Objects.requireNonNull(str, "name == null");
            this.f5637c = str;
            this.f5638d = hVar;
            this.f5639e = z;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            if (t != null) {
                rVar.f(this.f5637c, this.f5638d.a(t), this.f5639e);
                return;
            }
            throw y.o(this.f5635a, this.f5636b, "Path parameter \"" + this.f5637c + "\" value must not be null.", new Object[0]);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class l<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5640a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final h.h<T, String> f5641b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f5642c;

        l(String str, h.h<T, String> hVar, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.f5640a = str;
            this.f5641b = hVar;
            this.f5642c = z;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            String strA;
            if (t == null || (strA = this.f5641b.a(t)) == null) {
                return;
            }
            rVar.g(this.f5640a, strA, this.f5642c);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class m<T> extends p<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5643a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5644b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final h.h<T, String> f5645c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f5646d;

        m(Method method, int i2, h.h<T, String> hVar, boolean z) {
            this.f5643a = method;
            this.f5644b = i2;
            this.f5645c = hVar;
            this.f5646d = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable Map<String, T> map) {
            if (map == null) {
                throw y.o(this.f5643a, this.f5644b, "Query map was null", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw y.o(this.f5643a, this.f5644b, "Query map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw y.o(this.f5643a, this.f5644b, "Query map contained null value for key '" + key + "'.", new Object[0]);
                }
                String strA = this.f5645c.a(value);
                if (strA == null) {
                    throw y.o(this.f5643a, this.f5644b, "Query map value '" + value + "' converted to null by " + this.f5645c.getClass().getName() + " for key '" + key + "'.", new Object[0]);
                }
                rVar.g(key, strA, this.f5646d);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class n<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final h.h<T, String> f5647a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f5648b;

        n(h.h<T, String> hVar, boolean z) {
            this.f5647a = hVar;
            this.f5648b = z;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            if (t == null) {
                return;
            }
            rVar.g(this.f5647a.a(t), null, this.f5648b);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class o extends p<a0.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final o f5649a = new o();

        private o() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // h.p
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(r rVar, @Nullable a0.b bVar) {
            if (bVar != null) {
                rVar.e(bVar);
            }
        }
    }

    /* JADX INFO: renamed from: h.p$p, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class C0107p extends p<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5650a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5651b;

        C0107p(Method method, int i2) {
            this.f5650a = method;
            this.f5651b = i2;
        }

        @Override // h.p
        void a(r rVar, @Nullable Object obj) {
            if (obj == null) {
                throw y.o(this.f5650a, this.f5651b, "@Url parameter is null.", new Object[0]);
            }
            rVar.m(obj);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class q<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Class<T> f5652a;

        q(Class<T> cls) {
            this.f5652a = cls;
        }

        @Override // h.p
        void a(r rVar, @Nullable T t) {
            rVar.h(this.f5652a, t);
        }
    }

    p() {
    }

    abstract void a(r rVar, @Nullable T t);

    final p<Object> b() {
        return new b();
    }

    final p<Iterable<T>> c() {
        return new a();
    }
}
