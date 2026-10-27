package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import p.ds2;
import p.u4;
import p.z61;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
final class d<T> extends f<T> {
    public static final f.e d = new a();
    private final c<T> a;
    private final b<?>[] b;
    private final JsonReader.b c;

    public class a implements f.e {
        private void b(o oVar, Type type, Map<String, b<?>> map) {
            z61 z61Var;
            Class<?> clsG = zo2.g(type);
            boolean zJ = ds2.j(clsG);
            for (Field field : clsG.getDeclaredFields()) {
                if (c(zJ, field.getModifiers()) && ((z61Var = (z61) field.getAnnotation(z61.class)) == null || !z61Var.ignore())) {
                    Type typeQ = ds2.q(type, clsG, field.getGenericType());
                    Set<? extends Annotation> setK = ds2.k(field);
                    String name = field.getName();
                    f<T> fVarF = oVar.f(typeQ, setK, name);
                    field.setAccessible(true);
                    String strN = ds2.n(name, z61Var);
                    b<?> bVar = new b<>(strN, field, fVarF);
                    b<?> bVarPut = map.put(strN, bVar);
                    if (bVarPut != null) {
                        throw new IllegalArgumentException("Conflicting fields:\n    " + bVarPut.b + "\n    " + bVar.b);
                    }
                }
            }
        }

        private boolean c(boolean z, int i) {
            if (Modifier.isStatic(i) || Modifier.isTransient(i)) {
                return false;
            }
            return Modifier.isPublic(i) || Modifier.isProtected(i) || !z;
        }

        private void d(Type type, Class<?> cls) {
            Class<?> clsG = zo2.g(type);
            if (cls.isAssignableFrom(clsG)) {
                throw new IllegalArgumentException("No JsonAdapter for " + type + ", you should probably use " + cls.getSimpleName() + " instead of " + clsG.getSimpleName() + " (Moshi only supports the collection interfaces by default) or else register a custom JsonAdapter.");
            }
        }

        @Override // com.squareup.moshi.f.e
        public f<?> a(Type type, Set<? extends Annotation> set, o oVar) {
            if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
                return null;
            }
            Class<?> clsG = zo2.g(type);
            if (clsG.isInterface() || clsG.isEnum() || !set.isEmpty()) {
                return null;
            }
            if (ds2.j(clsG)) {
                d(type, List.class);
                d(type, Set.class);
                d(type, Map.class);
                d(type, Collection.class);
                String str = "Platform " + clsG;
                if (type instanceof ParameterizedType) {
                    str = str + " in " + type;
                }
                throw new IllegalArgumentException(u4.h(str, " requires explicit JsonAdapter to be registered"));
            }
            if (clsG.isAnonymousClass()) {
                throw new IllegalArgumentException("Cannot serialize anonymous class ".concat(clsG.getName()));
            }
            if (clsG.isLocalClass()) {
                throw new IllegalArgumentException("Cannot serialize local class ".concat(clsG.getName()));
            }
            if (clsG.getEnclosingClass() != null && !Modifier.isStatic(clsG.getModifiers())) {
                throw new IllegalArgumentException("Cannot serialize non-static nested class ".concat(clsG.getName()));
            }
            if (Modifier.isAbstract(clsG.getModifiers())) {
                throw new IllegalArgumentException("Cannot serialize abstract class ".concat(clsG.getName()));
            }
            if (ds2.i(clsG)) {
                throw new IllegalArgumentException(u4.d(clsG, new StringBuilder("Cannot serialize Kotlin type "), ". Reflective serialization of Kotlin classes without using kotlin-reflect has undefined and unexpected behavior. Please use KotlinJsonAdapterFactory from the moshi-kotlin artifact or use code gen from the moshi-kotlin-codegen artifact."));
            }
            c cVarA = c.a(clsG);
            TreeMap treeMap = new TreeMap();
            while (type != Object.class) {
                b(oVar, type, treeMap);
                type = zo2.f(type);
            }
            return new d(cVarA, treeMap).nullSafe();
        }
    }

    public static class b<T> {
        final String a;
        final Field b;
        final f<T> c;

        public b(String str, Field field, f<T> fVar) {
            this.a = str;
            this.b = field;
            this.c = fVar;
        }

        public void a(JsonReader jsonReader, Object obj) throws IllegalAccessException {
            this.b.set(obj, this.c.fromJson(jsonReader));
        }

        public void b(m mVar, Object obj) throws IllegalAccessException {
            this.c.toJson(mVar, (T) this.b.get(obj));
        }
    }

    public d(c<T> cVar, Map<String, b<?>> map) {
        this.a = cVar;
        this.b = (b[]) map.values().toArray(new b[map.size()]);
        this.c = JsonReader.b.a((String[]) map.keySet().toArray(new String[map.size()]));
    }

    @Override // com.squareup.moshi.f
    public T fromJson(JsonReader jsonReader) {
        try {
            T tB = this.a.b();
            try {
                jsonReader.c();
                while (jsonReader.hasNext()) {
                    int iM0 = jsonReader.M0(this.c);
                    if (iM0 == -1) {
                        jsonReader.Q0();
                        jsonReader.D();
                    } else {
                        this.b[iM0].a(jsonReader, tB);
                    }
                }
                jsonReader.q();
                return tB;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw ds2.t(e2);
        }
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, T t) {
        try {
            mVar.i();
            for (b<?> bVar : this.b) {
                mVar.J0(bVar.a);
                bVar.b(mVar, t);
            }
            mVar.X();
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public String toString() {
        return "JsonAdapter(" + this.a + ")";
    }
}
