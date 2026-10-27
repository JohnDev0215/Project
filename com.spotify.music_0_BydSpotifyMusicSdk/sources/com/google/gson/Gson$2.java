package com.google.gson;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
class Gson$2 extends b {
    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        if (t71Var.K0() != JsonToken.q) {
            return Float.valueOf((float) t71Var.T());
        }
        t71Var.z0();
        return null;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            z71Var.E0();
        } else {
            a.a(number.floatValue());
            z71Var.K0(number);
        }
    }
}
