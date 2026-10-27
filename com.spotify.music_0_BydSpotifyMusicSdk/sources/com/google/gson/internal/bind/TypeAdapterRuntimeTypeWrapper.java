package com.google.gson.internal.bind;

import com.google.gson.b;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
final class TypeAdapterRuntimeTypeWrapper<T> extends b {
    public final com.google.gson.a a;
    public final b b;
    public final Type c;

    public TypeAdapterRuntimeTypeWrapper(com.google.gson.a aVar, b bVar, Type type) {
        this.a = aVar;
        this.b = bVar;
        this.c = type;
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) {
        return this.b.b(t71Var);
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) {
        Type type = this.c;
        Type type2 = (obj == null || !(type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) ? type : obj.getClass();
        b bVar = this.b;
        if (type2 != type) {
            b bVarD = this.a.d(new qo2(type2));
            if (!(bVarD instanceof ReflectiveTypeAdapterFactory.Adapter) || (bVar instanceof ReflectiveTypeAdapterFactory.Adapter)) {
                bVar = bVarD;
            }
        }
        bVar.c(z71Var, obj);
    }
}
