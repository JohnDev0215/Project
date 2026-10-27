package com.google.gson.internal.bind;

import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Date;
import p.t71;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultDateTypeAdapter<T extends Date> extends b {
    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        if (t71Var.K0() == JsonToken.q) {
            t71Var.z0();
            return null;
        }
        t71Var.u();
        throw null;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        if (((Date) obj) != null) {
            throw null;
        }
        z71Var.E0();
    }

    public final String toString() {
        throw null;
    }
}
