package com.google.gson.internal.bind;

import com.google.gson.b;
import p.lo2;
import p.qo2;

/* JADX INFO: loaded from: classes.dex */
class TypeAdapters$31 implements lo2 {
    public final /* synthetic */ Class c;
    public final /* synthetic */ Class e;
    public final /* synthetic */ b k;

    public TypeAdapters$31(Class cls, Class cls2, b bVar) {
        this.c = cls;
        this.e = cls2;
        this.k = bVar;
    }

    @Override // p.lo2
    public final b a(com.google.gson.a aVar, qo2 qo2Var) {
        Class cls = qo2Var.a;
        if (cls == this.c || cls == this.e) {
            return this.k;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.e.getName() + "+" + this.c.getName() + ",adapter=" + this.k + "]";
    }
}
