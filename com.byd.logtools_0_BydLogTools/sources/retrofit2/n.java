package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* JADX INFO: compiled from: ParameterHandler.java */
/* JADX INFO: loaded from: classes.dex */
abstract class n<T> {

    /* JADX INFO: compiled from: ParameterHandler.java */
    class a extends n<Iterable<T>> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable Iterable<T> iterable) throws IOException {
            if (iterable == null) {
                return;
            }
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                n.this.a(pVar, it.next());
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    class b extends n<Object> {
        b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable Object obj) throws IOException {
            if (obj == null) {
                return;
            }
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                n.this.a(pVar, Array.get(obj, i));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class c<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5245a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5246b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final retrofit2.h<T, RequestBody> f5247c;

        c(Method method, int i, retrofit2.h<T, RequestBody> hVar) {
            this.f5245a = method;
            this.f5246b = i;
            this.f5247c = hVar;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) {
            if (t == null) {
                throw w.o(this.f5245a, this.f5246b, "Body parameter value must not be null.", new Object[0]);
            }
            try {
                pVar.l(this.f5247c.a(t));
            } catch (IOException e2) {
                throw w.p(this.f5245a, e2, this.f5246b, "Unable to convert " + t + " to RequestBody", new Object[0]);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class d<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5248a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final retrofit2.h<T, String> f5249b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f5250c;

        d(String str, retrofit2.h<T, String> hVar, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.f5248a = str;
            this.f5249b = hVar;
            this.f5250c = z;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) throws IOException {
            String strA;
            if (t == null || (strA = this.f5249b.a(t)) == null) {
                return;
            }
            pVar.a(this.f5248a, strA, this.f5250c);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class e<T> extends n<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5251a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5252b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final retrofit2.h<T, String> f5253c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f5254d;

        e(Method method, int i, retrofit2.h<T, String> hVar, boolean z) {
            this.f5251a = method;
            this.f5252b = i;
            this.f5253c = hVar;
            this.f5254d = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw w.o(this.f5251a, this.f5252b, "Field map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw w.o(this.f5251a, this.f5252b, "Field map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw w.o(this.f5251a, this.f5252b, "Field map contained null value for key '" + key + "'.", new Object[0]);
                }
                String strA = this.f5253c.a(value);
                if (strA == null) {
                    throw w.o(this.f5251a, this.f5252b, "Field map value '" + value + "' converted to null by " + this.f5253c.getClass().getName() + " for key '" + key + "'.", new Object[0]);
                }
                pVar.a(key, strA, this.f5254d);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class f<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5255a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final retrofit2.h<T, String> f5256b;

        f(String str, retrofit2.h<T, String> hVar) {
            Objects.requireNonNull(str, "name == null");
            this.f5255a = str;
            this.f5256b = hVar;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) throws IOException {
            String strA;
            if (t == null || (strA = this.f5256b.a(t)) == null) {
                return;
            }
            pVar.b(this.f5255a, strA);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class g<T> extends n<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5257a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5258b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final retrofit2.h<T, String> f5259c;

        g(Method method, int i, retrofit2.h<T, String> hVar) {
            this.f5257a = method;
            this.f5258b = i;
            this.f5259c = hVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw w.o(this.f5257a, this.f5258b, "Header map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw w.o(this.f5257a, this.f5258b, "Header map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw w.o(this.f5257a, this.f5258b, "Header map contained null value for key '" + key + "'.", new Object[0]);
                }
                pVar.b(key, this.f5259c.a(value));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class h extends n<Headers> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5260a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5261b;

        h(Method method, int i) {
            this.f5260a = method;
            this.f5261b = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable Headers headers) {
            if (headers == null) {
                throw w.o(this.f5260a, this.f5261b, "Headers parameter must not be null.", new Object[0]);
            }
            pVar.c(headers);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class i<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5262a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5263b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Headers f5264c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final retrofit2.h<T, RequestBody> f5265d;

        i(Method method, int i, Headers headers, retrofit2.h<T, RequestBody> hVar) {
            this.f5262a = method;
            this.f5263b = i;
            this.f5264c = headers;
            this.f5265d = hVar;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) {
            if (t == null) {
                return;
            }
            try {
                pVar.d(this.f5264c, this.f5265d.a(t));
            } catch (IOException e2) {
                throw w.o(this.f5262a, this.f5263b, "Unable to convert " + t + " to RequestBody", e2);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class j<T> extends n<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5267b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final retrofit2.h<T, RequestBody> f5268c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final String f5269d;

        j(Method method, int i, retrofit2.h<T, RequestBody> hVar, String str) {
            this.f5266a = method;
            this.f5267b = i;
            this.f5268c = hVar;
            this.f5269d = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw w.o(this.f5266a, this.f5267b, "Part map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw w.o(this.f5266a, this.f5267b, "Part map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw w.o(this.f5266a, this.f5267b, "Part map contained null value for key '" + key + "'.", new Object[0]);
                }
                pVar.d(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\"", "Content-Transfer-Encoding", this.f5269d), this.f5268c.a(value));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class k<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5270a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5271b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f5272c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final retrofit2.h<T, String> f5273d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f5274e;

        k(Method method, int i, String str, retrofit2.h<T, String> hVar, boolean z) {
            this.f5270a = method;
            this.f5271b = i;
            Objects.requireNonNull(str, "name == null");
            this.f5272c = str;
            this.f5273d = hVar;
            this.f5274e = z;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) throws IOException {
            if (t != null) {
                pVar.f(this.f5272c, this.f5273d.a(t), this.f5274e);
                return;
            }
            throw w.o(this.f5270a, this.f5271b, "Path parameter \"" + this.f5272c + "\" value must not be null.", new Object[0]);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class l<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f5275a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final retrofit2.h<T, String> f5276b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f5277c;

        l(String str, retrofit2.h<T, String> hVar, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.f5275a = str;
            this.f5276b = hVar;
            this.f5277c = z;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) throws IOException {
            String strA;
            if (t == null || (strA = this.f5276b.a(t)) == null) {
                return;
            }
            pVar.g(this.f5275a, strA, this.f5277c);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class m<T> extends n<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final retrofit2.h<T, String> f5280c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final boolean f5281d;

        m(Method method, int i, retrofit2.h<T, String> hVar, boolean z) {
            this.f5278a = method;
            this.f5279b = i;
            this.f5280c = hVar;
            this.f5281d = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw w.o(this.f5278a, this.f5279b, "Query map was null", new Object[0]);
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw w.o(this.f5278a, this.f5279b, "Query map contained null key.", new Object[0]);
                }
                T value = entry.getValue();
                if (value == null) {
                    throw w.o(this.f5278a, this.f5279b, "Query map contained null value for key '" + key + "'.", new Object[0]);
                }
                String strA = this.f5280c.a(value);
                if (strA == null) {
                    throw w.o(this.f5278a, this.f5279b, "Query map value '" + value + "' converted to null by " + this.f5280c.getClass().getName() + " for key '" + key + "'.", new Object[0]);
                }
                pVar.g(key, strA, this.f5281d);
            }
        }
    }

    /* JADX INFO: renamed from: retrofit2.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class C0099n<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final retrofit2.h<T, String> f5282a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f5283b;

        C0099n(retrofit2.h<T, String> hVar, boolean z) {
            this.f5282a = hVar;
            this.f5283b = z;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) throws IOException {
            if (t == null) {
                return;
            }
            pVar.g(this.f5282a.a(t), null, this.f5283b);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class o extends n<MultipartBody.Part> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final o f5284a = new o();

        private o() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.n
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(retrofit2.p pVar, @Nullable MultipartBody.Part part) {
            if (part != null) {
                pVar.e(part);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class p extends n<Object> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f5285a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5286b;

        p(Method method, int i) {
            this.f5285a = method;
            this.f5286b = i;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable Object obj) {
            if (obj == null) {
                throw w.o(this.f5285a, this.f5286b, "@Url parameter is null.", new Object[0]);
            }
            pVar.m(obj);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    static final class q<T> extends n<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Class<T> f5287a;

        q(Class<T> cls) {
            this.f5287a = cls;
        }

        @Override // retrofit2.n
        void a(retrofit2.p pVar, @Nullable T t) {
            pVar.h(this.f5287a, t);
        }
    }

    n() {
    }

    abstract void a(retrofit2.p pVar, @Nullable T t) throws IOException;

    final n<Object> b() {
        return new b();
    }

    final n<Iterable<T>> c() {
        return new a();
    }
}
