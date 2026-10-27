package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
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
public final class i implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b.r.b f4241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.b.c f4242c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.b.b.r.c f4243d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f4244e;

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Field f4245d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f4246e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ o f4247f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ c.b.b.d f4248g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ c.b.b.s.a f4249h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ boolean f4250i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, boolean z, boolean z2, Field field, boolean z3, o oVar, c.b.b.d dVar, c.b.b.s.a aVar, boolean z4) {
            super(str, z, z2);
            this.f4245d = field;
            this.f4246e = z3;
            this.f4247f = oVar;
            this.f4248g = dVar;
            this.f4249h = aVar;
            this.f4250i = z4;
        }

        @Override // c.b.b.r.i.i.c
        public void a(c.b.b.t.a aVar, Object obj) throws IllegalAccessException, IOException {
            Object objB = this.f4247f.b(aVar);
            if (objB == null && this.f4250i) {
                return;
            }
            this.f4245d.set(obj, objB);
        }

        @Override // c.b.b.r.i.i.c
        public void b(c.b.b.t.b bVar, Object obj) throws IllegalAccessException, IOException {
            (this.f4246e ? this.f4247f : new m(this.f4248g, this.f4247f, this.f4249h.f())).d(bVar, this.f4245d.get(obj));
        }

        @Override // c.b.b.r.i.i.c
        public boolean c(Object obj) throws IllegalAccessException, IOException {
            return this.f4254b && this.f4245d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.b.r.e<T> f4251a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, c> f4252b;

        public b(c.b.b.r.e<T> eVar, Map<String, c> map) {
            this.f4251a = eVar;
            this.f4252b = map;
        }

        @Override // c.b.b.o
        public T b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            T tA = this.f4251a.a();
            try {
                aVar.O();
                while (aVar.W()) {
                    c cVar = this.f4252b.get(aVar.e0());
                    if (cVar == null || !cVar.f4255c) {
                        aVar.u0();
                    } else {
                        cVar.a(aVar, tA);
                    }
                }
                aVar.T();
                return tA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException(e3);
            }
        }

        @Override // c.b.b.o
        public void d(c.b.b.t.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.Z();
                return;
            }
            bVar.P();
            try {
                for (c cVar : this.f4252b.values()) {
                    if (cVar.c(t)) {
                        bVar.X(cVar.f4253a);
                        cVar.b(bVar, t);
                    }
                }
                bVar.S();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f4253a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f4254b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f4255c;

        public c(String str, boolean z, boolean z2) {
            this.f4253a = str;
            this.f4254b = z;
            this.f4255c = z2;
        }

        public abstract void a(c.b.b.t.a aVar, Object obj) throws IllegalAccessException, IOException;

        public abstract void b(c.b.b.t.b bVar, Object obj) throws IllegalAccessException, IOException;

        public abstract boolean c(Object obj) throws IllegalAccessException, IOException;
    }

    public i(c.b.b.r.b bVar, c.b.b.c cVar, c.b.b.r.c cVar2, d dVar) {
        this.f4241b = bVar;
        this.f4242c = cVar;
        this.f4243d = cVar2;
        this.f4244e = dVar;
    }

    public static boolean d(Field field, boolean z, c.b.b.r.c cVar) {
        return (cVar.c(field.getType(), z) || cVar.d(field, z)) ? false : true;
    }

    @Override // c.b.b.p
    public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
        Class<? super T> clsD = aVar.d();
        if (Object.class.isAssignableFrom(clsD)) {
            return new b(this.f4241b.a(aVar), e(dVar, aVar, clsD));
        }
        return null;
    }

    public final c b(c.b.b.d dVar, Field field, String str, c.b.b.s.a<?> aVar, boolean z, boolean z2) {
        boolean zB = c.b.b.r.f.b(aVar.d());
        c.b.b.q.b bVar = (c.b.b.q.b) field.getAnnotation(c.b.b.q.b.class);
        o<?> oVarB = bVar != null ? this.f4244e.b(this.f4241b, dVar, aVar, bVar) : null;
        boolean z3 = oVarB != null;
        if (oVarB == null) {
            oVarB = dVar.k(aVar);
        }
        return new a(str, z, z2, field, z3, oVarB, dVar, aVar, zB);
    }

    public boolean c(Field field, boolean z) {
        return d(field, z, this.f4243d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public final Map<String, c> e(c.b.b.d dVar, c.b.b.s.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeF = aVar.f();
        c.b.b.s.a<?> aVarB = aVar;
        Class<?> clsD = cls;
        while (clsD != Object.class) {
            Field[] declaredFields = clsD.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i2 = 0;
            while (i2 < length) {
                Field field = declaredFields[i2];
                boolean zC = c(field, true);
                boolean zC2 = c(field, z);
                if (zC || zC2) {
                    field.setAccessible(true);
                    Type typeP = C$Gson$Types.p(aVarB.f(), clsD, field.getGenericType());
                    List<String> listF = f(field);
                    c cVar = null;
                    ?? r3 = z;
                    while (r3 < listF.size()) {
                        String str = listF.get(r3);
                        boolean z2 = r3 != 0 ? z : zC;
                        ?? r20 = r3;
                        c cVar2 = cVar;
                        List<String> list = listF;
                        Field field2 = field;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(dVar, field, str, c.b.b.s.a.b(typeP), z2, zC2)) : cVar2;
                        zC = z2;
                        listF = list;
                        field = field2;
                        z = false;
                        r3 = (r20 == true ? 1 : 0) + 1;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeF + " declares multiple JSON fields named " + cVar3.f4253a);
                    }
                }
                i2++;
                z = false;
            }
            aVarB = c.b.b.s.a.b(C$Gson$Types.p(aVarB.f(), clsD, clsD.getGenericSuperclass()));
            clsD = aVarB.d();
        }
        return linkedHashMap;
    }

    public final List<String> f(Field field) {
        c.b.b.q.c cVar = (c.b.b.q.c) field.getAnnotation(c.b.b.q.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f4242c.translateName(field));
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
}
