package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.d;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.c;
import com.google.gson.internal.h;
import com.google.gson.internal.j;
import com.google.gson.r;
import com.google.gson.t;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ReflectiveTypeAdapterFactory implements t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f4473c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Excluder f4474d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final JsonAdapterAnnotationTypeAdapterFactory f4475e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.google.gson.internal.m.b f4476f = com.google.gson.internal.m.b.a();

    public static final class Adapter<T> extends TypeAdapter<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final h<T> f4477a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Map<String, b> f4478b;

        Adapter(h<T> hVar, Map<String, b> map) {
            this.f4477a = hVar;
            this.f4478b = map;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(com.google.gson.w.a aVar) throws IOException {
            if (aVar.j0() == com.google.gson.w.b.NULL) {
                aVar.f0();
                return null;
            }
            T tA = this.f4477a.a();
            try {
                aVar.d();
                while (aVar.V()) {
                    b bVar = this.f4478b.get(aVar.d0());
                    if (bVar == null || !bVar.f4487c) {
                        aVar.t0();
                    } else {
                        bVar.a(aVar, tA);
                    }
                }
                aVar.S();
                return tA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new r(e3);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(com.google.gson.w.c cVar, T t) throws IOException {
            if (t == null) {
                cVar.Y();
                return;
            }
            cVar.y();
            try {
                for (b bVar : this.f4478b.values()) {
                    if (bVar.c(t)) {
                        cVar.W(bVar.f4485a);
                        bVar.b(cVar, t);
                    }
                }
                cVar.S();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    class a extends b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ Field f4479d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f4480e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ TypeAdapter f4481f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Gson f4482g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ com.google.gson.v.a f4483h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ boolean f4484i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, boolean z, boolean z2, Field field, boolean z3, TypeAdapter typeAdapter, Gson gson, com.google.gson.v.a aVar, boolean z4) {
            super(str, z, z2);
            this.f4479d = field;
            this.f4480e = z3;
            this.f4481f = typeAdapter;
            this.f4482g = gson;
            this.f4483h = aVar;
            this.f4484i = z4;
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.b
        void a(com.google.gson.w.a aVar, Object obj) throws IllegalAccessException {
            Object obj2 = this.f4481f.read(aVar);
            if (obj2 == null && this.f4484i) {
                return;
            }
            this.f4479d.set(obj, obj2);
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.b
        void b(com.google.gson.w.c cVar, Object obj) throws IllegalAccessException {
            (this.f4480e ? this.f4481f : new TypeAdapterRuntimeTypeWrapper(this.f4482g, this.f4481f, this.f4483h.e())).write(cVar, this.f4479d.get(obj));
        }

        @Override // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.b
        public boolean c(Object obj) {
            return this.f4486b && this.f4479d.get(obj) != obj;
        }
    }

    static abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f4485a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f4486b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f4487c;

        protected b(String str, boolean z, boolean z2) {
            this.f4485a = str;
            this.f4486b = z;
            this.f4487c = z2;
        }

        abstract void a(com.google.gson.w.a aVar, Object obj);

        abstract void b(com.google.gson.w.c cVar, Object obj);

        abstract boolean c(Object obj);
    }

    public ReflectiveTypeAdapterFactory(c cVar, d dVar, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.f4472b = cVar;
        this.f4473c = dVar;
        this.f4474d = excluder;
        this.f4475e = jsonAdapterAnnotationTypeAdapterFactory;
    }

    private b a(Gson gson, Field field, String str, com.google.gson.v.a<?> aVar, boolean z, boolean z2) {
        boolean zB = j.b(aVar.c());
        com.google.gson.u.b bVar = (com.google.gson.u.b) field.getAnnotation(com.google.gson.u.b.class);
        TypeAdapter<?> typeAdapterA = bVar != null ? this.f4475e.a(this.f4472b, gson, aVar, bVar) : null;
        boolean z3 = typeAdapterA != null;
        if (typeAdapterA == null) {
            typeAdapterA = gson.k(aVar);
        }
        return new a(str, z, z2, field, z3, typeAdapterA, gson, aVar, zB);
    }

    static boolean d(Field field, boolean z, Excluder excluder) {
        return (excluder.e(field.getType(), z) || excluder.h(field, z)) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v7 */
    private Map<String, b> e(Gson gson, com.google.gson.v.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeE = aVar.e();
        com.google.gson.v.a<?> aVarB = aVar;
        Class<?> clsC = cls;
        while (clsC != Object.class) {
            Field[] declaredFields = clsC.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i2 = 0;
            while (i2 < length) {
                Field field = declaredFields[i2];
                boolean zC = c(field, true);
                boolean zC2 = c(field, z);
                if (zC || zC2) {
                    this.f4476f.b(field);
                    Type typeP = com.google.gson.internal.b.p(aVarB.e(), clsC, field.getGenericType());
                    List<String> listF = f(field);
                    int size = listF.size();
                    b bVar = null;
                    ?? r2 = z;
                    while (r2 < size) {
                        String str = listF.get(r2);
                        boolean z2 = r2 != 0 ? z : zC;
                        ?? r20 = r2;
                        b bVar2 = bVar;
                        int i3 = size;
                        List<String> list = listF;
                        Field field2 = field;
                        bVar = bVar2 == null ? (b) linkedHashMap.put(str, a(gson, field, str, com.google.gson.v.a.b(typeP), z2, zC2)) : bVar2;
                        zC = z2;
                        listF = list;
                        size = i3;
                        field = field2;
                        z = false;
                        r2 = (r20 == true ? 1 : 0) + 1;
                    }
                    b bVar3 = bVar;
                    if (bVar3 != null) {
                        throw new IllegalArgumentException(typeE + " declares multiple JSON fields named " + bVar3.f4485a);
                    }
                }
                i2++;
                z = false;
            }
            aVarB = com.google.gson.v.a.b(com.google.gson.internal.b.p(aVarB.e(), clsC, clsC.getGenericSuperclass()));
            clsC = aVarB.c();
        }
        return linkedHashMap;
    }

    private List<String> f(Field field) {
        com.google.gson.u.c cVar = (com.google.gson.u.c) field.getAnnotation(com.google.gson.u.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f4473c.a(field));
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

    @Override // com.google.gson.t
    public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        if (Object.class.isAssignableFrom(clsC)) {
            return new Adapter(this.f4472b.a(aVar), e(gson, aVar, clsC));
        }
        return null;
    }

    public boolean c(Field field, boolean z) {
        return d(field, z, this.f4474d);
    }
}
