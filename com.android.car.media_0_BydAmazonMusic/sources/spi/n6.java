package spi;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/* JADX INFO: compiled from: MapTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n6 implements ob {
    public final o2 a;
    public final boolean b;

    /* JADX INFO: compiled from: MapTypeAdapterFactory.java */
    public final class a<K, V> extends nb<Map<K, V>> {
        public final nb<K> a;
        public final nb<V> b;
        public final b8<? extends Map<K, V>> c;

        public a(j4 j4Var, Type type, nb<K> nbVar, Type type2, nb<V> nbVar2, b8<? extends Map<K, V>> b8Var) {
            this.a = new pb(j4Var, nbVar, type);
            this.b = new pb(j4Var, nbVar2, type2);
            this.c = b8Var;
        }

        @Override // spi.nb
        public final Object a(o5 o5Var) throws IOException {
            s5 s5VarV = o5Var.v();
            if (s5VarV == s5.NULL) {
                o5Var.r();
                return null;
            }
            Map<K, V> mapA = this.c.a();
            if (s5VarV == s5.BEGIN_ARRAY) {
                o5Var.a();
                while (o5Var.i()) {
                    o5Var.a();
                    K kA = this.a.a(o5Var);
                    if (mapA.put(kA, this.b.a(o5Var)) != null) {
                        throw new r5("duplicate key: " + kA);
                    }
                    o5Var.e();
                }
                o5Var.e();
            } else {
                o5Var.b();
                while (o5Var.i()) {
                    Objects.requireNonNull(p5.a);
                    if (o5Var instanceof t5) {
                        t5 t5Var = (t5) o5Var;
                        t5Var.C(s5.NAME);
                        Map.Entry entry = (Map.Entry) ((Iterator) t5Var.D()).next();
                        t5Var.F(entry.getValue());
                        t5Var.F(new n5((String) entry.getKey()));
                    } else {
                        int iD = o5Var.h;
                        if (iD == 0) {
                            iD = o5Var.d();
                        }
                        if (iD == 13) {
                            o5Var.h = 9;
                        } else if (iD == 12) {
                            o5Var.h = 8;
                        } else {
                            if (iD != 14) {
                                StringBuilder sbK = c.k("Expected a name but was ");
                                sbK.append(o5Var.v());
                                sbK.append(o5Var.k());
                                throw new IllegalStateException(sbK.toString());
                            }
                            o5Var.h = 10;
                        }
                    }
                    K kA2 = this.a.a(o5Var);
                    if (mapA.put(kA2, this.b.a(o5Var)) != null) {
                        throw new r5("duplicate key: " + kA2);
                    }
                }
                o5Var.f();
            }
            return mapA;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // spi.nb
        public final void b(v5 v5Var, Object obj) throws IOException {
            String strD;
            Map map = (Map) obj;
            if (map == null) {
                v5Var.i();
                return;
            }
            if (!n6.this.b) {
                v5Var.c();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    v5Var.g(String.valueOf(entry.getKey()));
                    this.b.b(v5Var, entry.getValue());
                }
                v5Var.f();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                nb<K> nbVar = this.a;
                K key = entry2.getKey();
                Objects.requireNonNull(nbVar);
                try {
                    u5 u5Var = new u5();
                    nbVar.b(u5Var, key);
                    if (!u5Var.l.isEmpty()) {
                        throw new IllegalStateException("Expected one JSON element but was " + u5Var.l);
                    }
                    i5 i5Var = u5Var.n;
                    arrayList.add(i5Var);
                    arrayList2.add(entry2.getValue());
                    Objects.requireNonNull(i5Var);
                    z |= (i5Var instanceof g5) || (i5Var instanceof l5);
                } catch (IOException e) {
                    throw new j5(e);
                }
            }
            if (z) {
                v5Var.b();
                int size = arrayList.size();
                while (i < size) {
                    v5Var.b();
                    ia.a((i5) arrayList.get(i), v5Var);
                    this.b.b(v5Var, (V) arrayList2.get(i));
                    v5Var.e();
                    i++;
                }
                v5Var.e();
                return;
            }
            v5Var.c();
            int size2 = arrayList.size();
            while (i < size2) {
                i5 i5Var2 = (i5) arrayList.get(i);
                Objects.requireNonNull(i5Var2);
                if (i5Var2 instanceof n5) {
                    n5 n5VarA = i5Var2.a();
                    Object obj2 = n5VarA.a;
                    if (obj2 instanceof Number) {
                        strD = String.valueOf(n5VarA.c());
                    } else if (obj2 instanceof Boolean) {
                        strD = Boolean.toString(n5VarA.b());
                    } else {
                        if (!(obj2 instanceof String)) {
                            throw new AssertionError();
                        }
                        strD = n5VarA.d();
                    }
                } else {
                    if (!(i5Var2 instanceof k5)) {
                        throw new AssertionError();
                    }
                    strD = "null";
                }
                v5Var.g(strD);
                this.b.b(v5Var, (V) arrayList2.get(i));
                i++;
            }
            v5Var.f();
        }
    }

    public n6(o2 o2Var, boolean z) {
        this.a = o2Var;
        this.b = z;
    }

    @Override // spi.ob
    public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
        Type[] actualTypeArguments;
        Type type = vbVar.b;
        if (!Map.class.isAssignableFrom(vbVar.a)) {
            return null;
        }
        Class<?> clsE = b.e(type);
        if (type == Properties.class) {
            actualTypeArguments = new Type[]{String.class, String.class};
        } else {
            Type typeF = b.f(type, clsE, Map.class);
            actualTypeArguments = typeF instanceof ParameterizedType ? ((ParameterizedType) typeF).getActualTypeArguments() : new Type[]{Object.class, Object.class};
        }
        Type type2 = actualTypeArguments[0];
        return new a(j4Var, actualTypeArguments[0], (type2 == Boolean.TYPE || type2 == Boolean.class) ? qb.f : j4Var.e(new vb<>(type2)), actualTypeArguments[1], j4Var.e(new vb<>(actualTypeArguments[1])), this.a.a(vbVar));
    }
}
