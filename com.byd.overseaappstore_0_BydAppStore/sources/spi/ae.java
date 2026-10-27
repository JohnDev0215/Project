package spi;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spi.al;

/* JADX INFO: loaded from: classes.dex */
public final class ae<K extends al, V> {
    public final a<K, V> a = new a<>();
    public final Map<K, a<K, V>> b = new HashMap();

    public static class a<K, V> {
        public final K a;
        public List<V> b;
        public a<K, V> c;
        public a<K, V> d;

        public a() {
            this(null);
        }

        public a(K k) {
            this.d = this;
            this.c = this;
            this.a = k;
        }

        @Nullable
        public final V a() {
            List<V> list = this.b;
            int size = list != null ? list.size() : 0;
            if (size > 0) {
                return (V) this.b.remove(size - 1);
            }
            return null;
        }
    }

    public static <K, V> void c(a<K, V> aVar) {
        a<K, V> aVar2 = aVar.d;
        aVar2.c = aVar.c;
        aVar.c.d = aVar2;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Nullable
    public final V a(K k) {
        a<K, V> aVar = this.b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        c(aVar);
        a<K, V> aVar2 = this.a;
        aVar.d = aVar2;
        a<K, V> aVar3 = aVar2.c;
        aVar.c = aVar3;
        aVar3.d = aVar;
        aVar.d.c = aVar;
        return aVar.a();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void b(K k, V v) {
        a<K, V> aVar = this.b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            c(aVar);
            a<K, V> aVar2 = this.a;
            aVar.d = aVar2.d;
            aVar.c = aVar2;
            aVar2.d = aVar;
            aVar.d.c = aVar;
            this.b.put(k, aVar);
        } else {
            k.a();
        }
        if (aVar.b == null) {
            aVar.b = new ArrayList();
        }
        aVar.b.add(v);
    }

    @Nullable
    public final V d() {
        a aVar = this.a;
        while (true) {
            aVar = aVar.d;
            if (aVar.equals(this.a)) {
                return null;
            }
            V v = (V) aVar.a();
            if (v != null) {
                return v;
            }
            c(aVar);
            this.b.remove(aVar.a);
            ((al) aVar.a).a();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (a aVar = this.a.c; !aVar.equals(this.a); aVar = aVar.c) {
            z = true;
            sb.append('{');
            sb.append(aVar.a);
            sb.append(':');
            List<V> list = aVar.b;
            sb.append(list != null ? list.size() : 0);
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
