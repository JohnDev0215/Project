package com.spotify.eventsender.droppedevents;

import com.google.gson.b;
import java.io.IOException;
import p.gk0;
import p.qb0;
import p.t71;
import p.v03;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class EventStatesCounterAdapter extends b {
    @Override // com.google.gson.b
    public final Object b(t71 t71Var) throws IOException {
        v03.j(t71Var, "input");
        gk0 gk0Var = new gk0();
        t71Var.c();
        while (t71Var.hasNext()) {
            if (v03.b(t71Var.r0(), "c")) {
                t71Var.b();
                while (t71Var.hasNext()) {
                    t71Var.b();
                    if (!t71Var.hasNext()) {
                        throw new IllegalStateException("No index in saved state".toString());
                    }
                    int iD0 = t71Var.D0();
                    if (!t71Var.hasNext()) {
                        throw new IllegalStateException("No count in saved state".toString());
                    }
                    long jZ = t71Var.Z();
                    t71Var.E();
                    gk0Var.a[iD0] = new qb0(jZ, 0L);
                }
                t71Var.E();
            }
        }
        t71Var.J();
        return gk0Var;
    }

    @Override // com.google.gson.b
    public final void c(z71 z71Var, Object obj) throws IOException {
        gk0 gk0Var = (gk0) obj;
        v03.j(z71Var, "out");
        v03.j(gk0Var, "value");
        z71Var.i();
        z71Var.X("c");
        z71Var.c();
        qb0[] qb0VarArr = gk0Var.a;
        int length = qb0VarArr.length;
        for (int i = 0; i < length; i++) {
            qb0 qb0Var = qb0VarArr[i];
            if (qb0Var.b() != 0) {
                z71Var.c();
                z71Var.K0(Integer.valueOf(i));
                z71Var.I0(qb0Var.b());
                z71Var.E();
            }
        }
        z71Var.E();
        z71Var.J();
    }
}
