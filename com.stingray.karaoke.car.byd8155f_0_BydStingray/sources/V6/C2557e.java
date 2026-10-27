package v6;

import U5.i;
import java.util.ArrayList;
import java.util.Iterator;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: v6.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2557e implements w6.d {
    public final w6.c l = new w6.c(Boolean.FALSE);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f23028m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w6.c f23029n = new w6.c(-1);
    public final w6.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public w6.b f23030p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final w6.c f23031q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f23032r;

    public C2557e() {
        Float fValueOf = Float.valueOf(0.0f);
        this.o = new w6.c(fValueOf);
        this.f23030p = new w6.b();
        this.f23031q = new w6.c(fValueOf);
        this.f23032r = "";
    }

    public final void a(w6.b bVar) {
        this.f23030p = bVar;
        this.f23032r = AbstractC1998m.f0(bVar, "", null, null, new i(11), 30);
        this.f23028m = new ArrayList();
        Iterator it = bVar.l.iterator();
        while (it.hasNext()) {
            this.f23028m.addAll(((C2559g) it.next()).f23036n);
        }
    }

    @Override // w6.d
    public final boolean d() {
        return this.f23029n.f23341m || this.f23030p.d() || this.o.f23341m || this.f23031q.f23341m || this.l.f23341m;
    }

    @Override // w6.d
    public final void l() {
        this.f23029n.f23341m = false;
        this.f23030p.l();
        this.o.f23341m = false;
        this.f23031q.f23341m = false;
        this.l.f23341m = false;
    }
}
