package com.google.gson.internal.bind;

import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import p.lo2;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class ArrayTypeAdapter<E> extends b {
    public static final lo2 c = new lo2() { // from class: com.google.gson.internal.bind.ArrayTypeAdapter.1
        @Override // p.lo2
        public final b a(com.google.gson.a aVar, qo2 qo2Var) {
            Type type = qo2Var.b;
            boolean z = type instanceof GenericArrayType;
            if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
                return null;
            }
            Type genericComponentType = z ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
            return new ArrayTypeAdapter(aVar, aVar.d(new qo2(genericComponentType)), com.google.gson.internal.a.f(genericComponentType));
        }
    };
    public final Class a;
    public final b b;

    public ArrayTypeAdapter(com.google.gson.a aVar, b bVar, Class cls) {
        this.b = new TypeAdapterRuntimeTypeWrapper(aVar, bVar, cls);
        this.a = cls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        if (t71Var.K0() == JsonToken.q) {
            t71Var.z0();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        t71Var.b();
        while (t71Var.hasNext()) {
            arrayList.add(((TypeAdapterRuntimeTypeWrapper) this.b).b.b(t71Var));
        }
        t71Var.E();
        int size = arrayList.size();
        Object objNewInstance = Array.newInstance((Class<?>) this.a, size);
        for (int i = 0; i < size; i++) {
            Array.set(objNewInstance, i, arrayList.get(i));
        }
        return objNewInstance;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        if (obj == null) {
            z71Var.E0();
            return;
        }
        z71Var.c();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.b.c(z71Var, Array.get(obj, i));
        }
        z71Var.E();
    }
}
