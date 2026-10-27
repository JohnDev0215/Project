package p2;

import q2.C2171b;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f20757m;

    public /* synthetic */ p(Object obj, int i7) {
        this.l = i7;
        this.f20757m = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                v vVar = (v) this.f20757m;
                if (vVar.f20817h0 >= 300000) {
                    ((x) vVar.f20826r.f18608m).f20849Z0 = true;
                    vVar.f20817h0 = 0L;
                    return;
                }
                return;
            case 1:
                q2.e eVar = (q2.e) this.f20757m;
                if (eVar.f21185n) {
                    return;
                }
                q2.h hVar = eVar.f21184m;
                if (hVar != null) {
                    hVar.d(eVar.l);
                }
                eVar.o.f21195n.remove(eVar);
                eVar.f21185n = true;
                return;
            case 2:
                ((C2171b) this.f20757m).d(null);
                return;
            case 3:
                s2.f fVar = (s2.f) this.f20757m;
                synchronized (fVar.f22079a) {
                    try {
                        if (fVar.f22088m) {
                            return;
                        }
                        long j3 = fVar.l - 1;
                        fVar.l = j3;
                        if (j3 > 0) {
                            return;
                        }
                        if (j3 >= 0) {
                            fVar.a();
                            return;
                        }
                        IllegalStateException illegalStateException = new IllegalStateException();
                        synchronized (fVar.f22079a) {
                            fVar.f22089n = illegalStateException;
                            break;
                        }
                        return;
                    } finally {
                    }
                }
            default:
                z2.l lVar = (z2.l) this.f20757m;
                lVar.f24339m--;
                return;
        }
    }
}
