package g4;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w4.g f14915m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f14916n;

    public /* synthetic */ m(p pVar, w4.g gVar, int i7) {
        this.l = i7;
        this.f14916n = pVar;
        this.f14915m = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1299c c1299c;
        switch (this.l) {
            case 0:
                w4.g gVar = this.f14915m;
                gVar.f23299b.a();
                synchronized (gVar.c) {
                    synchronized (this.f14916n) {
                        try {
                            o oVar = this.f14916n.l;
                            w4.g gVar2 = this.f14915m;
                            oVar.getClass();
                            if (oVar.l.contains(new n(gVar2, A4.h.f420b))) {
                                p pVar = this.f14916n;
                                w4.g gVar3 = this.f14915m;
                                pVar.getClass();
                                try {
                                    gVar3.j(pVar.B, 5);
                                } finally {
                                }
                            }
                            this.f14916n.d();
                        } finally {
                        }
                        break;
                    }
                }
                return;
            default:
                w4.g gVar4 = this.f14915m;
                gVar4.f23299b.a();
                synchronized (gVar4.c) {
                    synchronized (this.f14916n) {
                        try {
                            o oVar2 = this.f14916n.l;
                            w4.g gVar5 = this.f14915m;
                            oVar2.getClass();
                            if (oVar2.l.contains(new n(gVar5, A4.h.f420b))) {
                                this.f14916n.f14922D.a();
                                p pVar2 = this.f14916n;
                                w4.g gVar6 = this.f14915m;
                                pVar2.getClass();
                                try {
                                    gVar6.k(pVar2.f14922D, pVar2.f14938z, pVar2.f14925G);
                                    this.f14916n.h(this.f14915m);
                                } finally {
                                }
                            }
                            this.f14916n.d();
                        } finally {
                        }
                    }
                }
                return;
        }
    }
}
