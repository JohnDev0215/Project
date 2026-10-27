package n6;

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
import k6.o;
import k6.p;

/* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m6.b f6582d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k6.c f6583e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m6.c f6584f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f6585g;

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Field f6586d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f6587e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ o f6588f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ k6.d f6589g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ p6.a f6590h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ boolean f6591i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, boolean z7, boolean z8, Field field, boolean z9, o oVar, k6.d dVar, p6.a aVar, boolean z10) {
            super(str, z7, z8);
            this.f6586d = field;
            this.f6587e = z9;
            this.f6588f = oVar;
            this.f6589g = dVar;
            this.f6590h = aVar;
            this.f6591i = z10;
        }

        @Override // n6.i.c
        public void a(q6.a aVar, Object obj) throws IllegalAccessException, IOException {
            Object objB = this.f6588f.b(aVar);
            if (objB == null && this.f6591i) {
                return;
            }
            this.f6586d.set(obj, objB);
        }

        @Override // n6.i.c
        public void b(q6.b bVar, Object obj) throws IllegalAccessException, IOException {
            (this.f6587e ? this.f6588f : new m(this.f6589g, this.f6588f, this.f6590h.f())).d(bVar, this.f6586d.get(obj));
        }

        @Override // n6.i.c
        public boolean c(Object obj) throws IllegalAccessException, IOException {
            return this.f6596b && this.f6586d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m6.e<T> f6593a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, c> f6594b;

        public b(m6.e<T> eVar, Map<String, c> map) {
            this.f6593a = eVar;
            this.f6594b = map;
        }

        @Override // k6.o
        public T b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            T tA = this.f6593a.a();
            try {
                aVar.i();
                while (aVar.t()) {
                    c cVar = this.f6594b.get(aVar.I());
                    if (cVar == null || !cVar.f6597c) {
                        aVar.g0();
                    } else {
                        cVar.a(aVar, tA);
                    }
                }
                aVar.q();
                return tA;
            } catch (IllegalAccessException e8) {
                throw new AssertionError(e8);
            } catch (IllegalStateException e9) {
                throw new JsonSyntaxException(e9);
            }
        }

        @Override // k6.o
        public void d(q6.b bVar, T t7) throws IOException {
            if (t7 == null) {
                bVar.B();
                return;
            }
            bVar.j();
            try {
                for (c cVar : this.f6594b.values()) {
                    if (cVar.c(t7)) {
                        bVar.w(cVar.f6595a);
                        cVar.b(bVar, t7);
                    }
                }
                bVar.o();
            } catch (IllegalAccessException e8) {
                throw new AssertionError(e8);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f6595a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f6596b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f6597c;

        public c(String str, boolean z7, boolean z8) {
            this.f6595a = str;
            this.f6596b = z7;
            this.f6597c = z8;
        }

        public abstract void a(q6.a aVar, Object obj) throws IllegalAccessException, IOException;

        public abstract void b(q6.b bVar, Object obj) throws IllegalAccessException, IOException;

        public abstract boolean c(Object obj) throws IllegalAccessException, IOException;
    }

    public i(m6.b bVar, k6.c cVar, m6.c cVar2, d dVar) {
        this.f6582d = bVar;
        this.f6583e = cVar;
        this.f6584f = cVar2;
        this.f6585g = dVar;
    }

    public static boolean d(Field field, boolean z7, m6.c cVar) {
        return (cVar.c(field.getType(), z7) || cVar.d(field, z7)) ? false : true;
    }

    @Override // k6.p
    public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
        Class<? super T> clsD = aVar.d();
        if (Object.class.isAssignableFrom(clsD)) {
            return new b(this.f6582d.a(aVar), e(dVar, aVar, clsD));
        }
        return null;
    }

    public final c b(k6.d dVar, Field field, String str, p6.a<?> aVar, boolean z7, boolean z8) {
        boolean zB = m6.f.b(aVar.d());
        l6.b bVar = (l6.b) field.getAnnotation(l6.b.class);
        o<?> oVarB = bVar != null ? this.f6585g.b(this.f6582d, dVar, aVar, bVar) : null;
        boolean z9 = oVarB != null;
        if (oVarB == null) {
            oVarB = dVar.k(aVar);
        }
        return new a(str, z7, z8, field, z9, oVarB, dVar, aVar, zB);
    }

    public boolean c(Field field, boolean z7) {
        return d(field, z7, this.f6584f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public final Map<String, c> e(k6.d dVar, p6.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeF = aVar.f();
        p6.a<?> aVarB = aVar;
        Class<?> clsD = cls;
        while (clsD != Object.class) {
            Field[] declaredFields = clsD.getDeclaredFields();
            int length = declaredFields.length;
            boolean z7 = false;
            int i8 = 0;
            while (i8 < length) {
                Field field = declaredFields[i8];
                boolean zC = c(field, true);
                boolean zC2 = c(field, z7);
                if (zC || zC2) {
                    field.setAccessible(true);
                    Type typeP = C$Gson$Types.p(aVarB.f(), clsD, field.getGenericType());
                    List<String> listF = f(field);
                    c cVar = null;
                    ?? r32 = z7;
                    while (r32 < listF.size()) {
                        String str = listF.get(r32);
                        boolean z8 = r32 != 0 ? z7 : zC;
                        ?? r20 = r32;
                        c cVar2 = cVar;
                        List<String> list = listF;
                        Field field2 = field;
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(dVar, field, str, p6.a.b(typeP), z8, zC2)) : cVar2;
                        zC = z8;
                        listF = list;
                        field = field2;
                        z7 = false;
                        r32 = (r20 == true ? 1 : 0) + 1;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeF + " declares multiple JSON fields named " + cVar3.f6595a);
                    }
                }
                i8++;
                z7 = false;
            }
            aVarB = p6.a.b(C$Gson$Types.p(aVarB.f(), clsD, clsD.getGenericSuperclass()));
            clsD = aVarB.d();
        }
        return linkedHashMap;
    }

    public final List<String> f(Field field) {
        l6.c cVar = (l6.c) field.getAnnotation(l6.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f6583e.translateName(field));
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
