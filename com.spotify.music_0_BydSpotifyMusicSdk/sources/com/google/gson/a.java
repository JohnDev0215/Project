package com.google.gson;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import p.am2;
import p.lo2;
import p.qo2;
import p.t71;
import p.x42;
import p.yt0;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final ThreadLocal a;
    public final ConcurrentHashMap b;
    public final x42 c;
    public final JsonAdapterAnnotationTypeAdapterFactory d;
    public final List e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;

    static {
        new qo2(Object.class);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a() {
        Excluder excluder = Excluder.n;
        FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.c;
        Map mapEmptyMap = Collections.emptyMap();
        LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.c;
        Collections.emptyList();
        Collections.emptyList();
        this(excluder, fieldNamingPolicy, mapEmptyMap, false, longSerializationPolicy, Collections.emptyList(), ToNumberPolicy.c, ToNumberPolicy.e);
    }

    public static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public final Object b(Class cls, String str) {
        Object objC = c(cls, str);
        if (cls == Integer.TYPE) {
            cls = Integer.class;
        } else if (cls == Float.TYPE) {
            cls = Float.class;
        } else if (cls == Byte.TYPE) {
            cls = Byte.class;
        } else if (cls == Double.TYPE) {
            cls = Double.class;
        } else if (cls == Long.TYPE) {
            cls = Long.class;
        } else if (cls == Character.TYPE) {
            cls = Character.class;
        } else if (cls == Boolean.TYPE) {
            cls = Boolean.class;
        } else if (cls == Short.TYPE) {
            cls = Short.class;
        } else if (cls == Void.TYPE) {
            cls = Void.class;
        }
        return cls.cast(objC);
    }

    public final Object c(Type type, String str) {
        Object objB = null;
        if (str == null) {
            return null;
        }
        t71 t71Var = new t71(new StringReader(str));
        boolean z = this.i;
        boolean z2 = true;
        t71Var.e = true;
        try {
            try {
                try {
                    t71Var.K0();
                    z2 = false;
                    objB = d(new qo2(type)).b(t71Var);
                } catch (EOFException e) {
                    if (!z2) {
                        throw new JsonSyntaxException(e);
                    }
                } catch (IOException e2) {
                    throw new JsonSyntaxException(e2);
                }
                if (objB != null) {
                    try {
                        if (t71Var.K0() != JsonToken.r) {
                            throw new JsonIOException("JSON document was not fully consumed.");
                        }
                    } catch (MalformedJsonException e3) {
                        throw new JsonSyntaxException(e3);
                    } catch (IOException e4) {
                        throw new JsonIOException(e4);
                    }
                }
                return objB;
            } catch (AssertionError e5) {
                AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.9): " + e5.getMessage());
                assertionError.initCause(e5);
                throw assertionError;
            } catch (IllegalStateException e6) {
                throw new JsonSyntaxException(e6);
            }
        } finally {
            t71Var.e = z;
        }
    }

    public final b d(qo2 qo2Var) {
        boolean z;
        ConcurrentHashMap concurrentHashMap = this.b;
        b bVar = (b) concurrentHashMap.get(qo2Var);
        if (bVar != null) {
            return bVar;
        }
        ThreadLocal threadLocal = this.a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
            z = true;
        } else {
            z = false;
        }
        Gson$FutureTypeAdapter gson$FutureTypeAdapter = (Gson$FutureTypeAdapter) map.get(qo2Var);
        if (gson$FutureTypeAdapter != null) {
            return gson$FutureTypeAdapter;
        }
        try {
            Gson$FutureTypeAdapter gson$FutureTypeAdapter2 = new Gson$FutureTypeAdapter();
            map.put(qo2Var, gson$FutureTypeAdapter2);
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                b bVarA = ((lo2) it.next()).a(this, qo2Var);
                if (bVarA != null) {
                    if (gson$FutureTypeAdapter2.a != null) {
                        throw new AssertionError();
                    }
                    gson$FutureTypeAdapter2.a = bVarA;
                    concurrentHashMap.put(qo2Var, bVarA);
                    map.remove(qo2Var);
                    if (z) {
                        threadLocal.remove();
                    }
                    return bVarA;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.9) cannot handle " + qo2Var);
        } catch (Throwable th) {
            map.remove(qo2Var);
            if (z) {
                threadLocal.remove();
            }
            throw th;
        }
    }

    public final b e(lo2 lo2Var, qo2 qo2Var) {
        List<lo2> list = this.e;
        if (!list.contains(lo2Var)) {
            lo2Var = this.d;
        }
        boolean z = false;
        for (lo2 lo2Var2 : list) {
            if (z) {
                b bVarA = lo2Var2.a(this, qo2Var);
                if (bVarA != null) {
                    return bVarA;
                }
            } else if (lo2Var2 == lo2Var) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + qo2Var);
    }

    public final String toString() {
        return "{serializeNulls:" + this.f + ",factories:" + this.e + ",instanceCreators:" + this.c + "}";
    }

    public a(Excluder excluder, yt0 yt0Var, Map map, boolean z, LongSerializationPolicy longSerializationPolicy, List list, am2 am2Var, am2 am2Var2) {
        final b bVar;
        lo2 lo2VarD;
        this.a = new ThreadLocal();
        this.b = new ConcurrentHashMap();
        x42 x42Var = new x42(map);
        this.c = x42Var;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add(com.google.gson.internal.bind.a.z);
        arrayList.add(ObjectTypeAdapter.d(am2Var));
        arrayList.add(excluder);
        arrayList.addAll(list);
        arrayList.add(com.google.gson.internal.bind.a.o);
        arrayList.add(com.google.gson.internal.bind.a.g);
        arrayList.add(com.google.gson.internal.bind.a.d);
        arrayList.add(com.google.gson.internal.bind.a.e);
        arrayList.add(com.google.gson.internal.bind.a.f);
        if (longSerializationPolicy == LongSerializationPolicy.c) {
            bVar = com.google.gson.internal.bind.a.k;
        } else {
            bVar = new b() { // from class: com.google.gson.Gson$3
                @Override // com.google.gson.b
                public final Object b(t71 t71Var) throws IOException {
                    if (t71Var.K0() != JsonToken.q) {
                        return Long.valueOf(t71Var.Z());
                    }
                    t71Var.z0();
                    return null;
                }

                @Override // com.google.gson.b
                public final void c(z71 z71Var, Object obj) throws IOException {
                    Number number = (Number) obj;
                    if (number == null) {
                        z71Var.E0();
                    } else {
                        z71Var.L0(number.toString());
                    }
                }
            };
        }
        arrayList.add(com.google.gson.internal.bind.a.b(Long.TYPE, Long.class, bVar));
        arrayList.add(com.google.gson.internal.bind.a.b(Double.TYPE, Double.class, new Gson$1()));
        arrayList.add(com.google.gson.internal.bind.a.b(Float.TYPE, Float.class, new Gson$2()));
        if (am2Var2 == ToNumberPolicy.e) {
            lo2VarD = NumberTypeAdapter.b;
        } else {
            lo2VarD = NumberTypeAdapter.d(am2Var2);
        }
        arrayList.add(lo2VarD);
        arrayList.add(com.google.gson.internal.bind.a.h);
        arrayList.add(com.google.gson.internal.bind.a.i);
        arrayList.add(com.google.gson.internal.bind.a.a(AtomicLong.class, new TypeAdapter$1(new b() { // from class: com.google.gson.Gson$4
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) {
                return new AtomicLong(((Number) bVar.b(t71Var)).longValue());
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) {
                bVar.c(z71Var, Long.valueOf(((AtomicLong) obj).get()));
            }
        })));
        arrayList.add(com.google.gson.internal.bind.a.a(AtomicLongArray.class, new TypeAdapter$1(new b() { // from class: com.google.gson.Gson$5
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                ArrayList arrayList2 = new ArrayList();
                t71Var.b();
                while (t71Var.hasNext()) {
                    arrayList2.add(Long.valueOf(((Number) bVar.b(t71Var)).longValue()));
                }
                t71Var.E();
                int size = arrayList2.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList2.get(i)).longValue());
                }
                return atomicLongArray;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
                z71Var.c();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    bVar.c(z71Var, Long.valueOf(atomicLongArray.get(i)));
                }
                z71Var.E();
            }
        })));
        arrayList.add(com.google.gson.internal.bind.a.j);
        arrayList.add(com.google.gson.internal.bind.a.l);
        arrayList.add(com.google.gson.internal.bind.a.f39p);
        arrayList.add(com.google.gson.internal.bind.a.q);
        arrayList.add(com.google.gson.internal.bind.a.a(BigDecimal.class, com.google.gson.internal.bind.a.m));
        arrayList.add(com.google.gson.internal.bind.a.a(BigInteger.class, com.google.gson.internal.bind.a.n));
        arrayList.add(com.google.gson.internal.bind.a.r);
        arrayList.add(com.google.gson.internal.bind.a.s);
        arrayList.add(com.google.gson.internal.bind.a.u);
        arrayList.add(com.google.gson.internal.bind.a.v);
        arrayList.add(com.google.gson.internal.bind.a.x);
        arrayList.add(com.google.gson.internal.bind.a.t);
        arrayList.add(com.google.gson.internal.bind.a.b);
        arrayList.add(DateTypeAdapter.b);
        arrayList.add(com.google.gson.internal.bind.a.w);
        if (com.google.gson.internal.sql.a.a) {
            arrayList.add(com.google.gson.internal.sql.a.c);
            arrayList.add(com.google.gson.internal.sql.a.b);
            arrayList.add(com.google.gson.internal.sql.a.d);
        }
        arrayList.add(ArrayTypeAdapter.c);
        arrayList.add(com.google.gson.internal.bind.a.a);
        arrayList.add(new CollectionTypeAdapterFactory(x42Var));
        arrayList.add(new MapTypeAdapterFactory(x42Var, z));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(x42Var);
        this.d = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(com.google.gson.internal.bind.a.A);
        arrayList.add(new ReflectiveTypeAdapterFactory(x42Var, yt0Var, excluder, jsonAdapterAnnotationTypeAdapterFactory));
        this.e = Collections.unmodifiableList(arrayList);
    }
}
