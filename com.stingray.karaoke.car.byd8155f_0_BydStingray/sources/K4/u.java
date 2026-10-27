package k4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class u implements com.bumptech.glide.load.data.e, com.bumptech.glide.load.data.d {
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final P f18210m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f18211n;
    public com.bumptech.glide.f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public com.bumptech.glide.load.data.d f18212p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f18213q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f18214r;

    public u(ArrayList arrayList, P p10) {
        this.f18210m = p10;
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        this.l = arrayList;
        this.f18211n = 0;
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        return ((com.bumptech.glide.load.data.e) this.l.get(0)).a();
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        List list = this.f18213q;
        if (list != null) {
            this.f18210m.B(list);
        }
        this.f18213q = null;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.load.data.e) it.next()).b();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void c(Exception exc) {
        List list = this.f18213q;
        A4.h.c(list, "Argument must not be null");
        list.add(exc);
        g();
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        this.f18214r = true;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.load.data.e) it.next()).cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void d(Object obj) {
        if (obj != null) {
            this.f18212p.d(obj);
        } else {
            g();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        return ((com.bumptech.glide.load.data.e) this.l.get(0)).e();
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, com.bumptech.glide.load.data.d dVar) {
        this.o = fVar;
        this.f18212p = dVar;
        this.f18213q = (List) this.f18210m.i();
        ((com.bumptech.glide.load.data.e) this.l.get(this.f18211n)).f(fVar, this);
        if (this.f18214r) {
            cancel();
        }
    }

    public final void g() {
        if (this.f18214r) {
            return;
        }
        if (this.f18211n < this.l.size() - 1) {
            this.f18211n++;
            f(this.o, this.f18212p);
        } else {
            A4.h.b(this.f18213q);
            this.f18212p.c(new g4.t("Fetch failed", new ArrayList(this.f18213q)));
        }
    }
}
