package com.google.gson.internal.k;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.internal.b f4081b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.google.gson.c f4082c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.gson.internal.c f4083d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e f4084e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.google.gson.internal.l.b f4085f = com.google.gson.internal.l.b.a();

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Field f4086d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f4087e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ p f4088f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ com.google.gson.d f4089g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ com.google.gson.s.a f4090h;
        final /* synthetic */ boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, boolean z, boolean z2, Field field, boolean z3, p pVar, com.google.gson.d dVar, com.google.gson.s.a aVar, boolean z4) {
            super(str, z, z2);
            this.f4086d = field;
            this.f4087e = z3;
            this.f4088f = pVar;
            this.f4089g = dVar;
            this.f4090h = aVar;
            this.i = z4;
        }

        @Override // com.google.gson.internal.k.k.c
        void a(com.google.gson.stream.a aVar, Object obj) throws IllegalAccessException, IOException {
            Object objB = this.f4088f.b(aVar);
            if (objB == null && this.i) {
                return;
            }
            this.f4086d.set(obj, objB);
        }

        @Override // com.google.gson.internal.k.k.c
        void b(com.google.gson.stream.b bVar, Object obj) throws IllegalAccessException, IOException {
            (this.f4087e ? this.f4088f : new m(this.f4089g, this.f4088f, this.f4090h.e())).d(bVar, this.f4086d.get(obj));
        }

        @Override // com.google.gson.internal.k.k.c
        public boolean c(Object obj) throws IllegalAccessException, IOException {
            return this.f4094b && this.f4086d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final com.google.gson.internal.f<T> f4091a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Map<String, c> f4092b;

        b(com.google.gson.internal.f<T> fVar, Map<String, c> map) {
            this.f4091a = fVar;
            this.f4092b = map;
        }

        @Override // com.google.gson.p
        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            T tA = this.f4091a.a();
            try {
                aVar.b();
                while (aVar.j()) {
                    c cVar = this.f4092b.get(aVar.r());
                    if (cVar == null || !cVar.f4095c) {
                        aVar.H();
                    } else {
                        cVar.a(aVar, tA);
                    }
                }
                aVar.g();
                return tA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException(e3);
            }
        }

        @Override // com.google.gson.p
        public void d(com.google.gson.stream.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.m();
                return;
            }
            bVar.d();
            try {
                for (c cVar : this.f4092b.values()) {
                    if (cVar.c(t)) {
                        bVar.k(cVar.f4093a);
                        cVar.b(bVar, t);
                    }
                }
                bVar.g();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f4093a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f4094b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f4095c;

        protected c(String str, boolean z, boolean z2) {
            this.f4093a = str;
            this.f4094b = z;
            this.f4095c = z2;
        }

        abstract void a(com.google.gson.stream.a aVar, Object obj) throws IllegalAccessException, IOException;

        abstract void b(com.google.gson.stream.b bVar, Object obj) throws IllegalAccessException, IOException;

        abstract boolean c(Object obj) throws IllegalAccessException, IOException;
    }

    public k(com.google.gson.internal.b bVar, com.google.gson.c cVar, com.google.gson.internal.c cVar2, e eVar) {
        this.f4081b = bVar;
        this.f4082c = cVar;
        this.f4083d = cVar2;
        this.f4084e = eVar;
    }

    private c b(com.google.gson.d dVar, Field field, String str, com.google.gson.s.a<?> aVar, boolean z, boolean z2) {
        boolean zA = com.google.gson.internal.h.a(aVar.c());
        com.google.gson.r.b bVar = (com.google.gson.r.b) field.getAnnotation(com.google.gson.r.b.class);
        p<?> pVarB = bVar != null ? this.f4084e.b(this.f4081b, dVar, aVar, bVar) : null;
        boolean z3 = pVarB != null;
        if (pVarB == null) {
            pVarB = dVar.k(aVar);
        }
        return new a(str, z, z2, field, z3, pVarB, dVar, aVar, zA);
    }

    static boolean d(Field field, boolean z, com.google.gson.internal.c cVar) {
        return (cVar.c(field.getType(), z) || cVar.g(field, z)) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7 */
    private Map<String, c> e(com.google.gson.d dVar, com.google.gson.s.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeE = aVar.e();
        com.google.gson.s.a<?> aVarB = aVar;
        Class<?> clsC = cls;
        while (clsC != Object.class) {
            Field[] declaredFields = clsC.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean zC = c(field, true);
                boolean zC2 = c(field, z);
                if (zC || zC2) {
                    this.f4085f.b(field);
                    Type typeP = C$Gson$Types.p(aVarB.e(), clsC, field.getGenericType());
                    List<String> listF = f(field);
                    int size = listF.size();
                    c cVar = null;
                    ?? r2 = z;
                    while (r2 < size) {
                        String str = listF.get(r2);
                        boolean z2 = r2 != 0 ? z : zC;
                        ?? r20 = r2;
                        c cVar2 = cVar;
                        int i2 = size;
                        List<String> list = listF;
                        Field field2 = field;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(dVar, field, str, com.google.gson.s.a.b(typeP), z2, zC2)) : cVar2;
                        zC = z2;
                        listF = list;
                        size = i2;
                        field = field2;
                        z = false;
                        r2 = (r20 == true ? 1 : 0) + 1;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeE + " declares multiple JSON fields named " + cVar3.f4093a);
                    }
                }
                i++;
                z = false;
            }
            aVarB = com.google.gson.s.a.b(C$Gson$Types.p(aVarB.e(), clsC, clsC.getGenericSuperclass()));
            clsC = aVarB.c();
        }
        return linkedHashMap;
    }

    private List<String> f(Field field) {
        com.google.gson.r.c cVar = (com.google.gson.r.c) field.getAnnotation(com.google.gson.r.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f4082c.translateName(field));
        }
        String strValue = cVar.value();
        String[] strArrAlternate = cVar.alternate();
        if (strArrAlternate.length == 0) {
            return Collections.singletonList(strValue);
        }
        ArrayList arrayList = new ArrayList(strArrAlternate.length + 1);
        arrayList.add(strValue);
        for (String str : strArrAlternate) {
            arrayList.add(str);
        }
        return arrayList;
    }

    @Override // com.google.gson.q
    public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        if (Object.class.isAssignableFrom(clsC)) {
            return new b(this.f4081b.a(aVar), e(dVar, aVar, clsC));
        }
        return null;
    }

    public boolean c(Field field, boolean z) {
        return d(field, z, this.f4083d);
    }
}
