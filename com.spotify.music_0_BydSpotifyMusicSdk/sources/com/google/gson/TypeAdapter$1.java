package com.google.gson;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
class TypeAdapter$1 extends b {
    public final /* synthetic */ b a;

    public TypeAdapter$1(b bVar) {
        this.a = bVar;
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        if (t71Var.K0() != JsonToken.q) {
            return this.a.b(t71Var);
        }
        t71Var.z0();
        return null;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        if (obj == null) {
            z71Var.E0();
        } else {
            this.a.c(z71Var, obj);
        }
    }
}
