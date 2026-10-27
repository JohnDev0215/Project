package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
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
/* JADX INFO: loaded from: classes3.dex */
public final class i implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.c.a.r.b f2269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.c.a.c f2270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.c.a.r.c f2271c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f2272d;

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Field f2273d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f2274e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ o f2275f;
        public final /* synthetic */ b.c.a.d g;
        public final /* synthetic */ b.c.a.s.a h;
        public final /* synthetic */ boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, boolean z, boolean z2, Field field, boolean z3, o oVar, b.c.a.d dVar, b.c.a.s.a aVar, boolean z4) {
            super(str, z, z2);
            this.f2273d = field;
            this.f2274e = z3;
            this.f2275f = oVar;
            this.g = dVar;
            this.h = aVar;
            this.i = z4;
        }

        @Override // b.c.a.r.i.i.c
        public void a(b.c.a.t.a aVar, Object obj) throws IllegalAccessException, IOException {
            Object objB = this.f2275f.b(aVar);
            if (objB == null && this.i) {
                return;
            }
            this.f2273d.set(obj, objB);
        }

        @Override // b.c.a.r.i.i.c
        public void b(b.c.a.t.b bVar, Object obj) throws IllegalAccessException, IOException {
            (this.f2274e ? this.f2275f : new m(this.g, this.f2275f, this.h.f())).d(bVar, this.f2273d.get(obj));
        }

        @Override // b.c.a.r.i.i.c
        public boolean c(Object obj) throws IllegalAccessException, IOException {
            return this.f2279b && this.f2273d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.c.a.r.e<T> f2276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, c> f2277b;

        public b(b.c.a.r.e<T> eVar, Map<String, c> map) {
            this.f2276a = eVar;
            this.f2277b = map;
        }

        @Override // b.c.a.o
        public T b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            T tA = this.f2276a.a();
            try {
                aVar.e();
                while (aVar.n()) {
                    c cVar = this.f2277b.get(aVar.x());
                    if (cVar == null || !cVar.f2280c) {
                        aVar.R();
                    } else {
                        cVar.a(aVar, tA);
                    }
                }
                aVar.j();
                return tA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException(e3);
            }
        }

        @Override // b.c.a.o
        public void d(b.c.a.t.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.s();
                return;
            }
            bVar.f();
            try {
                for (c cVar : this.f2277b.values()) {
                    if (cVar.c(t)) {
                        bVar.o(cVar.f2278a);
                        cVar.b(bVar, t);
                    }
                }
                bVar.i();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f2278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f2280c;

        public c(String str, boolean z, boolean z2) {
            this.f2278a = str;
            this.f2279b = z;
            this.f2280c = z2;
        }

        public abstract void a(b.c.a.t.a aVar, Object obj) throws IllegalAccessException, IOException;

        public abstract void b(b.c.a.t.b bVar, Object obj) throws IllegalAccessException, IOException;

        public abstract boolean c(Object obj) throws IllegalAccessException, IOException;
    }

    public i(b.c.a.r.b bVar, b.c.a.c cVar, b.c.a.r.c cVar2, d dVar) {
        this.f2269a = bVar;
        this.f2270b = cVar;
        this.f2271c = cVar2;
        this.f2272d = dVar;
    }

    public static boolean d(Field field, boolean z, b.c.a.r.c cVar) {
        return (cVar.c(field.getType(), z) || cVar.d(field, z)) ? false : true;
    }

    @Override // b.c.a.p
    public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
        Class<? super T> clsD = aVar.d();
        if (Object.class.isAssignableFrom(clsD)) {
            return new b(this.f2269a.a(aVar), e(dVar, aVar, clsD));
        }
        return null;
    }

    public final c b(b.c.a.d dVar, Field field, String str, b.c.a.s.a<?> aVar, boolean z, boolean z2) {
        boolean zB = b.c.a.r.f.b(aVar.d());
        b.c.a.q.b bVar = (b.c.a.q.b) field.getAnnotation(b.c.a.q.b.class);
        o<?> oVarB = bVar != null ? this.f2272d.b(this.f2269a, dVar, aVar, bVar) : null;
        boolean z3 = oVarB != null;
        if (oVarB == null) {
            oVarB = dVar.k(aVar);
        }
        return new a(str, z, z2, field, z3, oVarB, dVar, aVar, zB);
    }

    public boolean c(Field field, boolean z) {
        return d(field, z, this.f2271c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public final Map<String, c> e(b.c.a.d dVar, b.c.a.s.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeF = aVar.f();
        b.c.a.s.a<?> aVarB = aVar;
        Class<?> clsD = cls;
        while (clsD != Object.class) {
            Field[] declaredFields = clsD.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
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
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(dVar, field, str, b.c.a.s.a.b(typeP), z2, zC2)) : cVar2;
                        zC = z2;
                        listF = list;
                        field = field2;
                        z = false;
                        r3 = (r20 == true ? 1 : 0) + 1;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeF + " declares multiple JSON fields named " + cVar3.f2278a);
                    }
                }
                i++;
                z = false;
            }
            aVarB = b.c.a.s.a.b(C$Gson$Types.p(aVarB.f(), clsD, clsD.getGenericSuperclass()));
            clsD = aVarB.d();
        }
        return linkedHashMap;
    }

    public final List<String> f(Field field) {
        b.c.a.q.c cVar = (b.c.a.q.c) field.getAnnotation(b.c.a.q.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f2270b.translateName(field));
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
