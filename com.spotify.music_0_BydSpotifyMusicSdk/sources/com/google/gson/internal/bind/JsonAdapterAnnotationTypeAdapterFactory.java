package com.google.gson.internal.bind;

import com.google.gson.b;
import p.a71;
import p.lo2;
import p.qo2;
import p.x42;

/* JADX INFO: loaded from: classes.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements lo2 {
    public final x42 c;

    public JsonAdapterAnnotationTypeAdapterFactory(x42 x42Var) {
        this.c = x42Var;
    }

    public static b b(x42 x42Var, com.google.gson.a aVar, qo2 qo2Var, a71 a71Var) {
        b bVarA;
        Object objK = x42Var.d(new qo2(a71Var.value())).k();
        if (objK instanceof b) {
            bVarA = (b) objK;
        } else {
            if (!(objK instanceof lo2)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objK.getClass().getName() + " as a @JsonAdapter for " + qo2Var.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            bVarA = ((lo2) objK).a(aVar, qo2Var);
        }
        return (bVarA == null || !a71Var.nullSafe()) ? bVarA : bVarA.a();
    }

    @Override // p.lo2
    public final b a(com.google.gson.a aVar, qo2 qo2Var) {
        a71 a71Var = (a71) qo2Var.a.getAnnotation(a71.class);
        if (a71Var == null) {
            return null;
        }
        return b(this.c, aVar, qo2Var, a71Var);
    }
}
