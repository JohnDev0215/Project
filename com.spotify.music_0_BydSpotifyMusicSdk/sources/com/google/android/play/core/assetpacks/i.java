package com.google.android.play.core.assetpacks;

import p.n13;
import p.o13;
import p.q13;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements q13 {
    public final /* synthetic */ int c = 0;
    public final /* synthetic */ j e;
    public final /* synthetic */ int k;

    public /* synthetic */ i(j jVar, int i) {
        this.e = jVar;
        this.k = i;
    }

    @Override // p.q13
    public final Object a() {
        int i = this.c;
        int i2 = this.k;
        j jVar = this.e;
        switch (i) {
            case 0:
                o13 o13VarB = jVar.b(i2);
                n13 n13Var = o13VarB.c;
                int i3 = n13Var.d;
                if (i3 != 5 && i3 != 6 && i3 != 4) {
                    throw new zzck(String.format("Could not safely delete session %d because it is not in a terminal state.", Integer.valueOf(i2)), i2);
                }
                c cVar = jVar.a;
                int i4 = o13VarB.b;
                long j = n13Var.b;
                String str = n13Var.a;
                if (cVar.c(i4, j, str).exists()) {
                    c.g(cVar.c(i4, j, str));
                }
                int i5 = n13Var.d;
                if ((i5 == 5 || i5 == 6) && cVar.i(i4, j, str).exists()) {
                    c.g(cVar.i(i4, j, str));
                }
                return null;
            default:
                jVar.b(i2).c.d = 5;
                return null;
        }
    }

    public /* synthetic */ i(j jVar, int i, int i2) {
        this.e = jVar;
        this.k = i;
    }
}
