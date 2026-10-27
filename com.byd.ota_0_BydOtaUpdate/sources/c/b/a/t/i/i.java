package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
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
public final class i implements r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.b.a.t.b f2978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.b.a.d f2979d;
    public final c.b.a.t.c s;
    public final d x;

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public class a extends c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Field f2980d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f2981e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ q f2982f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ c.b.a.e f2983g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ c.b.a.u.a f2984h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ boolean f2985i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, boolean z, boolean z2, Field field, boolean z3, q qVar, c.b.a.e eVar, c.b.a.u.a aVar, boolean z4) {
            super(str, z, z2);
            this.f2980d = field;
            this.f2981e = z3;
            this.f2982f = qVar;
            this.f2983g = eVar;
            this.f2984h = aVar;
            this.f2985i = z4;
        }

        @Override // c.b.a.t.i.i.c
        public void a(c.b.a.v.a aVar, Object obj) throws IllegalAccessException, IOException {
            Object objB = this.f2982f.b(aVar);
            if (objB == null && this.f2985i) {
                return;
            }
            this.f2980d.set(obj, objB);
        }

        @Override // c.b.a.t.i.i.c
        public void b(c.b.a.v.b bVar, Object obj) throws IllegalAccessException, IOException {
            (this.f2981e ? this.f2982f : new m(this.f2983g, this.f2982f, this.f2984h.e())).d(bVar, this.f2980d.get(obj));
        }

        @Override // c.b.a.t.i.i.c
        public boolean c(Object obj) throws IllegalAccessException, IOException {
            return this.f2989b && this.f2980d.get(obj) != obj;
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static final class b<T> extends q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.a.t.e<T> f2986a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<String, c> f2987b;

        public b(c.b.a.t.e<T> eVar, Map<String, c> map) {
            this.f2986a = eVar;
            this.f2987b = map;
        }

        @Override // c.b.a.q
        public T b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            T tA = this.f2986a.a();
            try {
                aVar.s();
                while (aVar.N()) {
                    c cVar = this.f2987b.get(aVar.V());
                    if (cVar == null || !cVar.f2990c) {
                        aVar.l0();
                    } else {
                        cVar.a(aVar, tA);
                    }
                }
                aVar.K();
                return tA;
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (IllegalStateException e3) {
                throw new JsonSyntaxException(e3);
            }
        }

        @Override // c.b.a.q
        public void d(c.b.a.v.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.P();
                return;
            }
            bVar.v();
            try {
                for (c cVar : this.f2987b.values()) {
                    if (cVar.c(t)) {
                        bVar.N(cVar.f2988a);
                        cVar.b(bVar, t);
                    }
                }
                bVar.J();
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* JADX INFO: compiled from: ReflectiveTypeAdapterFactory.java */
    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f2988a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2989b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f2990c;

        public c(String str, boolean z, boolean z2) {
            this.f2988a = str;
            this.f2989b = z;
            this.f2990c = z2;
        }

        public abstract void a(c.b.a.v.a aVar, Object obj) throws IllegalAccessException, IOException;

        public abstract void b(c.b.a.v.b bVar, Object obj) throws IllegalAccessException, IOException;

        public abstract boolean c(Object obj) throws IllegalAccessException, IOException;
    }

    public i(c.b.a.t.b bVar, c.b.a.d dVar, c.b.a.t.c cVar, d dVar2) {
        this.f2978c = bVar;
        this.f2979d = dVar;
        this.s = cVar;
        this.x = dVar2;
    }

    public static boolean d(Field field, boolean z, c.b.a.t.c cVar) {
        return (cVar.e(field.getType(), z) || cVar.f(field, z)) ? false : true;
    }

    @Override // c.b.a.r
    public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        if (Object.class.isAssignableFrom(clsC)) {
            return new b(this.f2978c.a(aVar), e(eVar, aVar, clsC));
        }
        return null;
    }

    public final c b(c.b.a.e eVar, Field field, String str, c.b.a.u.a<?> aVar, boolean z, boolean z2) {
        boolean zB = c.b.a.t.f.b(aVar.c());
        c.b.a.s.b bVar = (c.b.a.s.b) field.getAnnotation(c.b.a.s.b.class);
        q<?> qVarB = bVar != null ? this.x.b(this.f2978c, eVar, aVar, bVar) : null;
        boolean z3 = qVarB != null;
        if (qVarB == null) {
            qVarB = eVar.m(aVar);
        }
        return new a(str, z, z2, field, z3, qVarB, eVar, aVar, zB);
    }

    public boolean c(Field field, boolean z) {
        return d(field, z, this.s);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v4 */
    public final Map<String, c> e(c.b.a.e eVar, c.b.a.u.a<?> aVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type typeE = aVar.e();
        c.b.a.u.a<?> aVarB = aVar;
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
                    field.setAccessible(true);
                    Type typeP = C$Gson$Types.p(aVarB.e(), clsC, field.getGenericType());
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
                        cVar = cVar2 == null ? (c) linkedHashMap.put(str, b(eVar, field, str, c.b.a.u.a.b(typeP), z2, zC2)) : cVar2;
                        zC = z2;
                        listF = list;
                        field = field2;
                        z = false;
                        r3 = (r20 == true ? 1 : 0) + 1;
                    }
                    c cVar3 = cVar;
                    if (cVar3 != null) {
                        throw new IllegalArgumentException(typeE + " declares multiple JSON fields named " + cVar3.f2988a);
                    }
                }
                i2++;
                z = false;
            }
            aVarB = c.b.a.u.a.b(C$Gson$Types.p(aVarB.e(), clsC, clsC.getGenericSuperclass()));
            clsC = aVarB.c();
        }
        return linkedHashMap;
    }

    public final List<String> f(Field field) {
        c.b.a.s.c cVar = (c.b.a.s.c) field.getAnnotation(c.b.a.s.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.f2979d.translateName(field));
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
