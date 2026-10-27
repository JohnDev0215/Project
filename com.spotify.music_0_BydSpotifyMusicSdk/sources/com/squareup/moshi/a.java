package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import p.ds2;
import p.ly0;
import p.u4;
import p.zl2;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
final class a implements f.e {
    private final List<f> a;
    private final List<f> b;

    /* JADX INFO: renamed from: com.squareup.moshi.a$a, reason: collision with other inner class name */
    public class C0006a extends com.squareup.moshi.f<Object> {
        final /* synthetic */ f a;
        final /* synthetic */ com.squareup.moshi.f b;
        final /* synthetic */ o c;
        final /* synthetic */ f d;
        final /* synthetic */ Set e;
        final /* synthetic */ Type f;

        public C0006a(f fVar, com.squareup.moshi.f fVar2, o oVar, f fVar3, Set set, Type type) {
            this.a = fVar;
            this.b = fVar2;
            this.c = oVar;
            this.d = fVar3;
            this.e = set;
            this.f = type;
        }

        @Override // com.squareup.moshi.f
        public Object fromJson(JsonReader jsonReader) throws IOException {
            f fVar = this.d;
            if (fVar == null) {
                return this.b.fromJson(jsonReader);
            }
            if (!fVar.g && jsonReader.H0() == JsonReader.Token.NULL) {
                jsonReader.o0();
                return null;
            }
            try {
                return this.d.b(this.c, jsonReader);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                throw new JsonDataException(cause + " at " + jsonReader.J(), cause);
            }
        }

        @Override // com.squareup.moshi.f
        public void toJson(m mVar, Object obj) throws IOException {
            f fVar = this.a;
            if (fVar == null) {
                this.b.toJson(mVar, obj);
                return;
            }
            if (!fVar.g && obj == null) {
                mVar.K0();
                return;
            }
            try {
                fVar.e(this.c, mVar, obj);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                throw new JsonDataException(cause + " at " + mVar.E0(), cause);
            }
        }

        public String toString() {
            return "JsonAdapter" + this.e + "(" + this.f + ")";
        }
    }

    public class b extends f {
        public b(Type type, Set set, Object obj, Method method, int i, int i2, boolean z) {
            super(type, set, obj, method, i, i2, z);
        }

        @Override // com.squareup.moshi.a.f
        public void e(o oVar, m mVar, Object obj) {
            d(mVar, obj);
        }
    }

    public class c extends f {
        private com.squareup.moshi.f<Object> h;
        final /* synthetic */ Type[] i;
        final /* synthetic */ Type j;
        final /* synthetic */ Set k;
        final /* synthetic */ Set l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Type type, Set set, Object obj, Method method, int i, int i2, boolean z, Type[] typeArr, Type type2, Set set2, Set set3) {
            super(type, set, obj, method, i, i2, z);
            this.i = typeArr;
            this.j = type2;
            this.k = set2;
            this.l = set3;
        }

        @Override // com.squareup.moshi.a.f
        public void a(o oVar, f.e eVar) {
            super.a(oVar, eVar);
            this.h = (zo2.d(this.i[0], this.j) && this.k.equals(this.l)) ? oVar.i(eVar, this.j, this.l) : oVar.e(this.j, this.l);
        }

        @Override // com.squareup.moshi.a.f
        public void e(o oVar, m mVar, Object obj) {
            this.h.toJson(mVar, c(obj));
        }
    }

    public class d extends f {
        public d(Type type, Set set, Object obj, Method method, int i, int i2, boolean z) {
            super(type, set, obj, method, i, i2, z);
        }

        @Override // com.squareup.moshi.a.f
        public Object b(o oVar, JsonReader jsonReader) {
            return c(jsonReader);
        }
    }

    public class e extends f {
        com.squareup.moshi.f<Object> h;
        final /* synthetic */ Type[] i;
        final /* synthetic */ Type j;
        final /* synthetic */ Set k;
        final /* synthetic */ Set l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Type type, Set set, Object obj, Method method, int i, int i2, boolean z, Type[] typeArr, Type type2, Set set2, Set set3) {
            super(type, set, obj, method, i, i2, z);
            this.i = typeArr;
            this.j = type2;
            this.k = set2;
            this.l = set3;
        }

        @Override // com.squareup.moshi.a.f
        public void a(o oVar, f.e eVar) {
            super.a(oVar, eVar);
            this.h = (zo2.d(this.i[0], this.j) && this.k.equals(this.l)) ? oVar.i(eVar, this.i[0], this.k) : oVar.e(this.i[0], this.k);
        }

        @Override // com.squareup.moshi.a.f
        public Object b(o oVar, JsonReader jsonReader) {
            return c(this.h.fromJson(jsonReader));
        }
    }

    public static abstract class f {
        final Type a;
        final Set<? extends Annotation> b;
        final Object c;
        final Method d;
        final int e;
        final com.squareup.moshi.f<?>[] f;
        final boolean g;

        public f(Type type, Set<? extends Annotation> set, Object obj, Method method, int i, int i2, boolean z) {
            this.a = ds2.a(type);
            this.b = set;
            this.c = obj;
            this.d = method;
            this.e = i2;
            this.f = new com.squareup.moshi.f[i - i2];
            this.g = z;
        }

        public void a(o oVar, f.e eVar) {
            if (this.f.length > 0) {
                Type[] genericParameterTypes = this.d.getGenericParameterTypes();
                Annotation[][] parameterAnnotations = this.d.getParameterAnnotations();
                int length = genericParameterTypes.length;
                for (int i = this.e; i < length; i++) {
                    Type type = ((ParameterizedType) genericParameterTypes[i]).getActualTypeArguments()[0];
                    Set<? extends Annotation> setL = ds2.l(parameterAnnotations[i]);
                    this.f[i - this.e] = (zo2.d(this.a, type) && this.b.equals(setL)) ? oVar.i(eVar, type, setL) : oVar.e(type, setL);
                }
            }
        }

        public Object b(o oVar, JsonReader jsonReader) {
            throw new AssertionError();
        }

        public Object c(Object obj) {
            com.squareup.moshi.f<?>[] fVarArr = this.f;
            Object[] objArr = new Object[fVarArr.length + 1];
            objArr[0] = obj;
            System.arraycopy(fVarArr, 0, objArr, 1, fVarArr.length);
            try {
                return this.d.invoke(this.c, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        public Object d(Object obj, Object obj2) {
            com.squareup.moshi.f<?>[] fVarArr = this.f;
            Object[] objArr = new Object[fVarArr.length + 2];
            objArr[0] = obj;
            objArr[1] = obj2;
            System.arraycopy(fVarArr, 0, objArr, 2, fVarArr.length);
            try {
                return this.d.invoke(this.c, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        public void e(o oVar, m mVar, Object obj) {
            throw new AssertionError();
        }
    }

    public a(List<f> list, List<f> list2) {
        this.a = list;
        this.b = list2;
    }

    public static f b(Object obj, Method method) {
        method.setAccessible(true);
        Type genericReturnType = method.getGenericReturnType();
        Set<? extends Annotation> setK = ds2.k(method);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 1 && genericParameterTypes[0] == JsonReader.class && genericReturnType != Void.TYPE && e(1, genericParameterTypes)) {
            return new d(genericReturnType, setK, obj, method, genericParameterTypes.length, 1, true);
        }
        if (genericParameterTypes.length == 1 && genericReturnType != Void.TYPE) {
            return new e(genericReturnType, setK, obj, method, genericParameterTypes.length, 1, ds2.f(parameterAnnotations[0]), genericParameterTypes, genericReturnType, ds2.l(parameterAnnotations[0]), setK);
        }
        throw new IllegalArgumentException("Unexpected signature for " + method + ".\n@FromJson method signatures may have one of the following structures:\n    <any access modifier> R fromJson(JsonReader jsonReader) throws <any>;\n    <any access modifier> R fromJson(JsonReader jsonReader, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R fromJson(T value) throws <any>;\n");
    }

    private static f c(List<f> list, Type type, Set<? extends Annotation> set) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            f fVar = list.get(i);
            if (zo2.d(fVar.a, type) && fVar.b.equals(set)) {
                return fVar;
            }
        }
        return null;
    }

    public static a d(Object obj) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Method method : superclass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(zl2.class)) {
                    f fVarF = f(obj, method);
                    f fVarC = c(arrayList, fVarF.a, fVarF.b);
                    if (fVarC != null) {
                        throw new IllegalArgumentException("Conflicting @ToJson methods:\n    " + fVarC.d + "\n    " + fVarF.d);
                    }
                    arrayList.add(fVarF);
                }
                if (method.isAnnotationPresent(ly0.class)) {
                    f fVarB = b(obj, method);
                    f fVarC2 = c(arrayList2, fVarB.a, fVarB.b);
                    if (fVarC2 != null) {
                        throw new IllegalArgumentException("Conflicting @FromJson methods:\n    " + fVarC2.d + "\n    " + fVarB.d);
                    }
                    arrayList2.add(fVarB);
                }
            }
        }
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            throw new IllegalArgumentException("Expected at least one @ToJson or @FromJson method on ".concat(obj.getClass().getName()));
        }
        return new a(arrayList, arrayList2);
    }

    private static boolean e(int i, Type[] typeArr) {
        int length = typeArr.length;
        while (i < length) {
            Type type = typeArr[i];
            if (!(type instanceof ParameterizedType) || ((ParameterizedType) type).getRawType() != com.squareup.moshi.f.class) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static f f(Object obj, Method method) {
        method.setAccessible(true);
        Type genericReturnType = method.getGenericReturnType();
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 2 && genericParameterTypes[0] == m.class && genericReturnType == Void.TYPE && e(2, genericParameterTypes)) {
            return new b(genericParameterTypes[1], ds2.l(parameterAnnotations[1]), obj, method, genericParameterTypes.length, 2, true);
        }
        if (genericParameterTypes.length != 1 || genericReturnType == Void.TYPE) {
            throw new IllegalArgumentException("Unexpected signature for " + method + ".\n@ToJson method signatures may have one of the following structures:\n    <any access modifier> void toJson(JsonWriter writer, T value) throws <any>;\n    <any access modifier> void toJson(JsonWriter writer, T value, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R toJson(T value) throws <any>;\n");
        }
        Set<? extends Annotation> setK = ds2.k(method);
        Set<? extends Annotation> setL = ds2.l(parameterAnnotations[0]);
        return new c(genericParameterTypes[0], setL, obj, method, genericParameterTypes.length, 1, ds2.f(parameterAnnotations[0]), genericParameterTypes, genericReturnType, setL, setK);
    }

    @Override // com.squareup.moshi.f.e
    public com.squareup.moshi.f<?> a(Type type, Set<? extends Annotation> set, o oVar) {
        f fVarC = c(this.a, type, set);
        f fVarC2 = c(this.b, type, set);
        com.squareup.moshi.f fVarI = null;
        if (fVarC == null && fVarC2 == null) {
            return null;
        }
        if (fVarC == null || fVarC2 == null) {
            try {
                fVarI = oVar.i(this, type, set);
            } catch (IllegalArgumentException e2) {
                StringBuilder sbP = u4.p("No ", fVarC == null ? "@ToJson" : "@FromJson", " adapter for ");
                sbP.append(ds2.u(type, set));
                throw new IllegalArgumentException(sbP.toString(), e2);
            }
        }
        com.squareup.moshi.f fVar = fVarI;
        if (fVarC != null) {
            fVarC.a(oVar, this);
        }
        if (fVarC2 != null) {
            fVarC2.a(oVar, this);
        }
        return new C0006a(fVarC, fVar, oVar, fVarC2, set, type);
    }
}
