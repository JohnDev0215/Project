package com.google.gson;

import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
class Gson$FutureTypeAdapter<T> extends b {
    public b a;

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) {
        b bVar = this.a;
        if (bVar != null) {
            return bVar.b(t71Var);
        }
        throw new IllegalStateException();
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) {
        b bVar = this.a;
        if (bVar == null) {
            throw new IllegalStateException();
        }
        bVar.c(z71Var, obj);
    }
}
