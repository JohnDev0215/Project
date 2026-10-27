package com.squareup.moshi;

import com.squareup.moshi.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
final class b extends f<Object> {
    public static final f.e c = new a();
    private final Class<?> a;
    private final f<Object> b;

    public class a implements f.e {
        @Override // com.squareup.moshi.f.e
        public f<?> a(Type type, Set<? extends Annotation> set, o oVar) {
            Type typeA = zo2.a(type);
            if (typeA != null && set.isEmpty()) {
                return new b(zo2.g(typeA), oVar.d(typeA)).nullSafe();
            }
            return null;
        }
    }

    public b(Class<?> cls, f<Object> fVar) {
        this.a = cls;
        this.b = fVar;
    }

    @Override // com.squareup.moshi.f
    public Object fromJson(JsonReader jsonReader) {
        ArrayList arrayList = new ArrayList();
        jsonReader.b();
        while (jsonReader.hasNext()) {
            arrayList.add(this.b.fromJson(jsonReader));
        }
        jsonReader.i();
        Object objNewInstance = Array.newInstance(this.a, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Object obj) {
        mVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.b.toJson(mVar, Array.get(obj, i));
        }
        mVar.E();
    }

    public String toString() {
        return this.b + ".array()";
    }
}
