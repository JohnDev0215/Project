package g4;

import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d implements g, com.bumptech.glide.load.data.d {
    public final List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h f14854m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f f14855n;
    public int o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public e4.e f14856p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f14857q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f14858r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile k4.p f14859s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public File f14860t;

    public d(List list, h hVar, f fVar) {
        this.l = list;
        this.f14854m = hVar;
        this.f14855n = fVar;
    }

    @Override // com.bumptech.glide.load.data.d
    public final void c(Exception exc) {
        this.f14855n.a(this.f14856p, exc, this.f14859s.c, 3);
    }

    @Override // g4.g
    public final void cancel() {
        k4.p pVar = this.f14859s;
        if (pVar != null) {
            pVar.c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void d(Object obj) {
        this.f14855n.b(this.f14856p, obj, this.f14859s.c, 3, this.f14856p);
    }

    @Override // g4.g
    public final boolean e() {
        while (true) {
            List list = this.f14857q;
            boolean z6 = false;
            if (list != null && this.f14858r < list.size()) {
                this.f14859s = null;
                while (!z6 && this.f14858r < this.f14857q.size()) {
                    List list2 = this.f14857q;
                    int i7 = this.f14858r;
                    this.f14858r = i7 + 1;
                    k4.q qVar = (k4.q) list2.get(i7);
                    File file = this.f14860t;
                    h hVar = this.f14854m;
                    this.f14859s = qVar.a(file, hVar.f14864e, hVar.f14865f, hVar.f14868i);
                    if (this.f14859s != null && this.f14854m.c(this.f14859s.c.a()) != null) {
                        this.f14859s.c.f(this.f14854m.o, this);
                        z6 = true;
                    }
                }
                return z6;
            }
            int i10 = this.o + 1;
            this.o = i10;
            if (i10 >= this.l.size()) {
                return false;
            }
            e4.e eVar = (e4.e) this.l.get(this.o);
            h hVar2 = this.f14854m;
            File fileD = hVar2.f14867h.a().d(new e(eVar, hVar2.f14872n));
            this.f14860t = fileD;
            if (fileD != null) {
                this.f14856p = eVar;
                this.f14857q = this.f14854m.c.a().f(fileD);
                this.f14858r = 0;
            }
        }
    }
}
