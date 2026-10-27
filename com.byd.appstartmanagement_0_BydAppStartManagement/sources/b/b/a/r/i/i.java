package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
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
/* JADX INFO: loaded from: classes2.dex */
public final class i implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.a.r.b f453b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.b.a.c f454c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b.b.a.r.c f455d;
    public final d e;

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Field f456d;
        public final /* synthetic */ boolean e;
        public final /* synthetic */ o f;
        public final /* synthetic */ b.b.a.d g;
        public final /* synthetic */ b.b.a.s.a h;
        public final /* synthetic */ boolean i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, boolean z, boolean z2, Field field, boolean z3, o oVar, b.b.a.d dVar, b.b.a.s.a aVar, boolean z4) {
            super(str, z, z2);
            this.f456d = field;
            this.e = z3;
            this.f = oVar;
            this.g = dVar;
            this.h = aVar;
            this.i = z4;
        }

        @Override // b.b.a.r.i.i.c
        public void a(b.b.a.t.a aVar, Object obj) throws IllegalAccessException, IOException {
            Object objB = this.f.b(aVar);
            if (objB == null && this.i) {
                return;
            }
            this.f456d.set(obj, objB);
        }

        @Override // b.b.a.r.i.i.c
        public void b(b.b.a.t.b bVar, Object obj) throws IllegalAccessException, IOException {
            (this.e ? this.f : new m(this.g, this.f, this.h.f())).d(bVar, this.f456d.get(obj));
        }

        @Override // b.b.a.r.i.i.c
        public boolean c(Object obj) throws IllegalAccessException, IOException {
            return this.f460b && this.f456d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b.b.a.r.e<T> f457a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, c> f458b;

        public b(b.b.a.r.e<T> eVar, Map<String, c> map) {
            this.f457a = eVar;
            this.f458b = map;
        }

        @Override // b.b.a.o
        public T b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            T tA = this.f457a.a();
            try {
                aVar.c();
                while (aVar.k()) {
                    c cVar = this.f458b.get(aVar.s());
                    if (cVar == null || !cVar.f461c) {
                        aVar.I();
                    } else {
                        cVar.a(aVar, tA);
                    }
                }
                aVar.h();
                return tA;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.b.a.o
        public void d(b.b.a.t.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.n();
                return;
            }
            bVar.d();
            try {
                for (c cVar : this.f458b.values()) {
                    if (cVar.c(t)) {
                        bVar.l(cVar.f459a);
                        cVar.b(bVar, t);
                    }
                }
                bVar.g();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f459a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f460b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f461c;

        public c(String str, boolean z, boolean z2) {
            this.f459a = str;
            this.f460b = z;
            this.f461c = z2;
        }

        public abstract void a(b.b.a.t.a aVar, Object obj) throws IllegalAccessException, IOException;

        public abstract void b(b.b.a.t.b bVar, Object obj) throws IllegalAccessException, IOException;

        public abstract boolean c(Object obj) throws IllegalAccessException, IOException;
    }

    public i(b.b.a.r.b bVar, b.b.a.c cVar, b.b.a.r.c cVar2, d dVar) {
        this.f453b = bVar;
        this.f454c = cVar;
        this.f455d = cVar2;
        this.e = dVar;
    }

    public static boolean d(Field field, boolean z, b.b.a.r.c cVar) {
        return (cVar.c(field.getType(), z) || cVar.d(field, z)) ? false : true;
    }

    @Override // b.b.a.p
    public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
        Class<? super T> clsD = aVar.d();
        if (Object.class.isAssignableFrom(clsD)) {
            return new b(this.f453b.a(aVar), e(dVar, aVar, clsD));
        }
        return null;
    }

    public final c b(b.b.a.d dVar, Field field, String str, b.b.a.s.a<?> aVar, boolean z, boolean z2) {
        boolean zB = b.b.a.r.f.b(aVar.d());
        b.b.a.q.b bVar = (b.b.a.q.b) field.getAnnotation(b.b.a.q.b.class);
        o<?> oVarB = bVar != null ? this.e.b(this.f453b, dVar, aVar, bVar) : null;
        boolean z3 = oVarB != null;
        if (oVarB == null) {
            oVarB = dVar.k(aVar);
        }
        return new a(str, z, z2, field, z3, oVarB, dVar, aVar, zB);
    }

    public boolean c(Field field, boolean z) {
        return d(field, z, this.f455d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public final Map<String, c> e(b.b.a.d dVar, b.b.a.s.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeF = aVar.f();
        b.b.a.s.a<?> aVarB = aVar;
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
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(dVar, field, str, b.b.a.s.a.b(typeP), z2, zC2)) : cVar2;
                        zC = z2;
                        listF = list;
                        field = field2;
                        z = false;
                        r3 = (r20 == true ? 1 : 0) + 1;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeF + " declares multiple JSON fields named " + cVar3.f459a);
                    }
                }
                i++;
                z = false;
            }
            aVarB = b.b.a.s.a.b(C$Gson$Types.p(aVarB.f(), clsD, clsD.getGenericSuperclass()));
            clsD = aVarB.d();
        }
        return linkedHashMap;
    }

    public final List<String> f(Field field) {
        b.b.a.q.c cVar = (b.b.a.q.c) field.getAnnotation(b.b.a.q.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f454c.translateName(field));
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
