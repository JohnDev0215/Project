package com.google.gson.internal.bind;

import com.google.gson.b;
import p.lo2;
import p.qo2;

/* JADX INFO: loaded from: classes.dex */
class TypeAdapters$30 implements lo2 {
    public final /* synthetic */ Class c;
    public final /* synthetic */ b e;

    public TypeAdapters$30(Class cls, b bVar) {
        this.c = cls;
        this.e = bVar;
    }

    @Override // p.lo2
    public final b a(com.google.gson.a aVar, qo2 qo2Var) {
        if (qo2Var.a == this.c) {
            return this.e;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.c.getName() + ",adapter=" + this.e + "]";
    }
}
