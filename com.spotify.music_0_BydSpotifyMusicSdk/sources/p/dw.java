package p;

import java.util.ArrayList;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class dw {
    public final ArrayList a;
    public final ArrayList b;
    public final ArrayList c;
    public final ArrayList d;
    public final ArrayList e;

    public dw(ew ewVar) {
        this.a = ou.o0(ewVar.a);
        this.b = ou.o0(ewVar.b);
        this.c = ou.o0(ewVar.c);
        this.d = ou.o0(ewVar.d);
        this.e = ou.o0(ewVar.e);
    }

    public final void a(vo voVar, Class cls) {
        this.b.add(new Pair(voVar, cls));
    }

    public final void b(pt0 pt0Var, Class cls) {
        this.d.add(new Pair(pt0Var, cls));
    }
}
