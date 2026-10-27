package com.google.gson.internal.bind;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import p.b71;
import p.f60;
import p.i71;
import p.lo2;
import p.n71;
import p.p71;
import p.qo2;
import p.r71;
import p.t71;
import p.x42;
import p.x71;
import p.xo1;
import p.z71;
import p.z90;

/* JADX INFO: loaded from: classes.dex */
public final class MapTypeAdapterFactory implements lo2 {
    public final x42 c;
    public final boolean e;

    public final class Adapter<K, V> extends b {
        public final b a;
        public final b b;
        public final xo1 c;

        public Adapter(com.google.gson.a aVar, Type type, b bVar, Type type2, b bVar2, xo1 xo1Var) {
            this.a = new TypeAdapterRuntimeTypeWrapper(aVar, bVar, type);
            this.b = new TypeAdapterRuntimeTypeWrapper(aVar, bVar2, type2);
            this.c = xo1Var;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.gson.b
        public final Object b(t71 t71Var) throws IOException {
            JsonToken jsonTokenK0 = t71Var.K0();
            if (jsonTokenK0 == JsonToken.q) {
                t71Var.z0();
                return null;
            }
            Map map = (Map) this.c.k();
            JsonToken jsonToken = JsonToken.c;
            b bVar = this.b;
            b bVar2 = this.a;
            if (jsonTokenK0 == jsonToken) {
                t71Var.b();
                while (t71Var.hasNext()) {
                    t71Var.b();
                    Object objB = ((TypeAdapterRuntimeTypeWrapper) bVar2).b.b(t71Var);
                    if (map.put(objB, ((TypeAdapterRuntimeTypeWrapper) bVar).b.b(t71Var)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + objB);
                    }
                    t71Var.E();
                }
                t71Var.E();
            } else {
                t71Var.c();
                while (t71Var.hasNext()) {
                    z90.k.getClass();
                    int iQ = t71Var.f185p;
                    if (iQ == 0) {
                        iQ = t71Var.q();
                    }
                    if (iQ == 13) {
                        t71Var.f185p = 9;
                    } else if (iQ == 12) {
                        t71Var.f185p = 8;
                    } else {
                        if (iQ != 14) {
                            throw new IllegalStateException("Expected a name but was " + t71Var.K0() + t71Var.G0());
                        }
                        t71Var.f185p = 10;
                    }
                    Object objB2 = ((TypeAdapterRuntimeTypeWrapper) bVar2).b.b(t71Var);
                    if (map.put(objB2, ((TypeAdapterRuntimeTypeWrapper) bVar).b.b(t71Var)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + objB2);
                    }
                }
                t71Var.J();
            }
            return map;
        }

        @Override // com.google.gson.b
        public final void c(z71 z71Var, Object obj) throws IOException {
            String strB;
            Map map = (Map) obj;
            if (map == null) {
                z71Var.E0();
                return;
            }
            boolean z = MapTypeAdapterFactory.this.e;
            b bVar = this.b;
            if (!z) {
                z71Var.i();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    z71Var.X(String.valueOf(entry.getKey()));
                    bVar.c(z71Var, entry.getValue());
                }
                z71Var.J();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z2 = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                b bVar2 = this.a;
                K key = entry2.getKey();
                bVar2.getClass();
                try {
                    x71 x71Var = new x71();
                    bVar2.c(x71Var, key);
                    ArrayList arrayList3 = x71Var.r;
                    if (!arrayList3.isEmpty()) {
                        throw new IllegalStateException("Expected one JSON element but was " + arrayList3);
                    }
                    i71 i71Var = x71Var.t;
                    arrayList.add(i71Var);
                    arrayList2.add(entry2.getValue());
                    i71Var.getClass();
                    z2 |= (i71Var instanceof b71) || (i71Var instanceof p71);
                } catch (IOException e) {
                    throw new JsonIOException(e);
                }
            }
            if (z2) {
                z71Var.c();
                int size = arrayList.size();
                while (i < size) {
                    z71Var.c();
                    a.y.c(z71Var, (i71) arrayList.get(i));
                    bVar.c(z71Var, arrayList2.get(i));
                    z71Var.E();
                    i++;
                }
                z71Var.E();
                return;
            }
            z71Var.i();
            int size2 = arrayList.size();
            while (i < size2) {
                i71 i71Var2 = (i71) arrayList.get(i);
                i71Var2.getClass();
                boolean z3 = i71Var2 instanceof r71;
                if (z3) {
                    if (!z3) {
                        throw new IllegalStateException("Not a JSON Primitive: " + i71Var2);
                    }
                    r71 r71Var = (r71) i71Var2;
                    Serializable serializable = r71Var.c;
                    if (serializable instanceof Number) {
                        strB = String.valueOf(r71Var.a());
                    } else if (serializable instanceof Boolean) {
                        strB = Boolean.toString(serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(r71Var.b()));
                    } else {
                        if (!(serializable instanceof String)) {
                            throw new AssertionError();
                        }
                        strB = r71Var.b();
                    }
                } else {
                    if (!(i71Var2 instanceof n71)) {
                        throw new AssertionError();
                    }
                    strB = "null";
                }
                z71Var.X(strB);
                bVar.c(z71Var, arrayList2.get(i));
                i++;
            }
            z71Var.J();
        }
    }

    public MapTypeAdapterFactory(x42 x42Var, boolean z) {
        this.c = x42Var;
        this.e = z;
    }

    @Override // p.lo2
    public final b a(com.google.gson.a aVar, qo2 qo2Var) {
        Type[] actualTypeArguments;
        Type type = qo2Var.b;
        if (!Map.class.isAssignableFrom(qo2Var.a)) {
            return null;
        }
        Class clsF = com.google.gson.internal.a.f(type);
        if (type == Properties.class) {
            actualTypeArguments = new Type[]{String.class, String.class};
        } else {
            if (type instanceof WildcardType) {
                type = ((WildcardType) type).getUpperBounds()[0];
            }
            f60.e(Map.class.isAssignableFrom(clsF));
            Type typeG = com.google.gson.internal.a.g(type, clsF, com.google.gson.internal.a.e(type, clsF, Map.class), new HashMap());
            actualTypeArguments = typeG instanceof ParameterizedType ? ((ParameterizedType) typeG).getActualTypeArguments() : new Type[]{Object.class, Object.class};
        }
        Type type2 = actualTypeArguments[0];
        return new Adapter(aVar, actualTypeArguments[0], (type2 == Boolean.TYPE || type2 == Boolean.class) ? a.c : aVar.d(new qo2(type2)), actualTypeArguments[1], aVar.d(new qo2(actualTypeArguments[1])), this.c.d(qo2Var));
    }
}
