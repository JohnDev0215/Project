package h;

import e.s;
import e.v;
import e.z;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: ParameterHandler.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i<T> {

    /* JADX INFO: compiled from: ParameterHandler.java */
    public class a extends i<Iterable<T>> {
        public a() {
        }

        @Override // h.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(h.k kVar, @Nullable Iterable<T> iterable) throws IOException {
            if (iterable == null) {
                return;
            }
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                i.this.a(kVar, it.next());
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public class b extends i<Object> {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // h.i
        public void a(h.k kVar, @Nullable Object obj) throws IOException {
            if (obj == null) {
                return;
            }
            int length = Array.getLength(obj);
            for (int i2 = 0; i2 < length; i2++) {
                i.this.a(kVar, Array.get(obj, i2));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class c<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h.e<T, z> f5775a;

        public c(h.e<T, z> eVar) {
            this.f5775a = eVar;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) {
            if (t == null) {
                throw new IllegalArgumentException("Body parameter value must not be null.");
            }
            try {
                kVar.j(this.f5775a.a(t));
            } catch (IOException e2) {
                throw new RuntimeException("Unable to convert " + t + " to RequestBody", e2);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class d<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f5776a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h.e<T, String> f5777b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f5778c;

        public d(String str, h.e<T, String> eVar, boolean z) {
            this.f5776a = (String) h.o.b(str, "name == null");
            this.f5777b = eVar;
            this.f5778c = z;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) throws IOException {
            String strA;
            if (t == null || (strA = this.f5777b.a(t)) == null) {
                return;
            }
            kVar.a(this.f5776a, strA, this.f5778c);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class e<T> extends i<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h.e<T, String> f5779a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f5780b;

        public e(h.e<T, String> eVar, boolean z) {
            this.f5779a = eVar;
            this.f5780b = z;
        }

        @Override // h.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(h.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Field map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Field map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Field map contained null value for key '" + key + "'.");
                }
                String strA = this.f5779a.a(value);
                if (strA == null) {
                    throw new IllegalArgumentException("Field map value '" + value + "' converted to null by " + this.f5779a.getClass().getName() + " for key '" + key + "'.");
                }
                kVar.a(key, strA, this.f5780b);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class f<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f5781a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h.e<T, String> f5782b;

        public f(String str, h.e<T, String> eVar) {
            this.f5781a = (String) h.o.b(str, "name == null");
            this.f5782b = eVar;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) throws IOException {
            String strA;
            if (t == null || (strA = this.f5782b.a(t)) == null) {
                return;
            }
            kVar.b(this.f5781a, strA);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class g<T> extends i<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h.e<T, String> f5783a;

        public g(h.e<T, String> eVar) {
            this.f5783a = eVar;
        }

        @Override // h.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(h.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Header map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Header map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Header map contained null value for key '" + key + "'.");
                }
                kVar.b(key, this.f5783a.a(value));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class h<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final s f5784a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h.e<T, z> f5785b;

        public h(s sVar, h.e<T, z> eVar) {
            this.f5784a = sVar;
            this.f5785b = eVar;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) {
            if (t == null) {
                return;
            }
            try {
                kVar.c(this.f5784a, this.f5785b.a(t));
            } catch (IOException e2) {
                throw new RuntimeException("Unable to convert " + t + " to RequestBody", e2);
            }
        }
    }

    /* JADX INFO: renamed from: h.i$i, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class C0101i<T> extends i<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h.e<T, z> f5786a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final String f5787b;

        public C0101i(h.e<T, z> eVar, String str) {
            this.f5786a = eVar;
            this.f5787b = str;
        }

        @Override // h.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(h.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Part map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Part map contained null value for key '" + key + "'.");
                }
                kVar.c(s.e("Content-Disposition", "form-data; name=\"" + key + "\"", "Content-Transfer-Encoding", this.f5787b), this.f5786a.a(value));
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class j<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f5788a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h.e<T, String> f5789b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f5790c;

        public j(String str, h.e<T, String> eVar, boolean z) {
            this.f5788a = (String) h.o.b(str, "name == null");
            this.f5789b = eVar;
            this.f5790c = z;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) throws IOException {
            if (t != null) {
                kVar.e(this.f5788a, this.f5789b.a(t), this.f5790c);
                return;
            }
            throw new IllegalArgumentException("Path parameter \"" + this.f5788a + "\" value must not be null.");
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class k<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f5791a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final h.e<T, String> f5792b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f5793c;

        public k(String str, h.e<T, String> eVar, boolean z) {
            this.f5791a = (String) h.o.b(str, "name == null");
            this.f5792b = eVar;
            this.f5793c = z;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) throws IOException {
            String strA;
            if (t == null || (strA = this.f5792b.a(t)) == null) {
                return;
            }
            kVar.f(this.f5791a, strA, this.f5793c);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class l<T> extends i<Map<String, T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h.e<T, String> f5794a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f5795b;

        public l(h.e<T, String> eVar, boolean z) {
            this.f5794a = eVar;
            this.f5795b = z;
        }

        @Override // h.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(h.k kVar, @Nullable Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Query map was null.");
            }
            for (Map.Entry<String, T> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key == null) {
                    throw new IllegalArgumentException("Query map contained null key.");
                }
                T value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Query map contained null value for key '" + key + "'.");
                }
                String strA = this.f5794a.a(value);
                if (strA == null) {
                    throw new IllegalArgumentException("Query map value '" + value + "' converted to null by " + this.f5794a.getClass().getName() + " for key '" + key + "'.");
                }
                kVar.f(key, strA, this.f5795b);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class m<T> extends i<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h.e<T, String> f5796a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f5797b;

        public m(h.e<T, String> eVar, boolean z) {
            this.f5796a = eVar;
            this.f5797b = z;
        }

        @Override // h.i
        public void a(h.k kVar, @Nullable T t) throws IOException {
            if (t == null) {
                return;
            }
            kVar.f(this.f5796a.a(t), null, this.f5797b);
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class n extends i<v.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final n f5798a = new n();

        @Override // h.i
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(h.k kVar, @Nullable v.b bVar) throws IOException {
            if (bVar != null) {
                kVar.d(bVar);
            }
        }
    }

    /* JADX INFO: compiled from: ParameterHandler.java */
    public static final class o extends i<Object> {
        @Override // h.i
        public void a(h.k kVar, @Nullable Object obj) {
            h.o.b(obj, "@Url parameter is null.");
            kVar.k(obj);
        }
    }

    public abstract void a(h.k kVar, @Nullable T t) throws IOException;

    public final i<Object> b() {
        return new b();
    }

    public final i<Iterable<T>> c() {
        return new a();
    }
}
