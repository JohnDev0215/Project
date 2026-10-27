package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;
import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import p.am2;
import p.lo2;
import p.qo2;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class NumberTypeAdapter extends b {
    public static final lo2 b = d(ToNumberPolicy.e);
    public final am2 a;

    public NumberTypeAdapter(am2 am2Var) {
        this.a = am2Var;
    }

    public static lo2 d(am2 am2Var) {
        return new lo2() { // from class: com.google.gson.internal.bind.NumberTypeAdapter.1
            @Override // p.lo2
            public final b a(com.google.gson.a aVar, qo2 qo2Var) {
                if (qo2Var.a == Number.class) {
                    return NumberTypeAdapter.this;
                }
                return null;
            }
        };
    }

    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        JsonToken jsonTokenK0 = t71Var.K0();
        int iOrdinal = jsonTokenK0.ordinal();
        if (iOrdinal == 5 || iOrdinal == 6) {
            return this.a.a(t71Var);
        }
        if (iOrdinal == 8) {
            t71Var.z0();
            return null;
        }
        throw new JsonSyntaxException("Expecting number, got: " + jsonTokenK0);
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        z71Var.K0((Number) obj);
    }
}
