package k3;

import android.os.RemoteException;
import com.stingray.karaoke.car.player.PlayerService;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class B0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ I0 f17721m;

    public /* synthetic */ B0(I0 i02, int i7) {
        this.l = i7;
        this.f17721m = i02;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                I0 i02 = this.f17721m;
                G0 g02 = i02.f17813v;
                if (g02 != null) {
                    i02.f17811t.H(g02);
                    return;
                }
                return;
            case 1:
                I0 i03 = this.f17721m;
                e7.f fVar = i03.f17814w;
                if (fVar != null) {
                    ((PlayerService) fVar.f14480m).j(i03.f17804k, false);
                    return;
                }
                return;
            case 2:
                I0 i04 = this.f17721m;
                synchronized (i04.f17796a) {
                    try {
                        if (i04.f17817z) {
                            return;
                        }
                        final B1 b1C1 = i04.f17811t.c1();
                        if (!i04.c.hasMessages(1) && n1.d(b1C1, i04.f17810s.c)) {
                            B8.t tVar = i04.f17800g.f18014e;
                            d5.I iU = tVar.U();
                            for (int i7 = 0; i7 < iU.size(); i7++) {
                                final C1679y0 c1679y0 = (C1679y0) iU.get(i7);
                                final boolean zB0 = tVar.b0(c1679y0, 16);
                                final boolean zB02 = tVar.b0(c1679y0, 17);
                                i04.b(c1679y0, new H0() { // from class: k3.D0
                                    @Override // k3.H0
                                    public final void c(InterfaceC1677x0 interfaceC1677x0, int i10) {
                                        interfaceC1677x0.e(i10, b1C1, zB0, zB02, c1679y0.c);
                                    }
                                });
                            }
                            try {
                                i04.f17801h.f17845h.e(0, b1C1, true, true, 0);
                            } catch (RemoteException e10) {
                                AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e10);
                            }
                            break;
                        }
                        i04.s();
                        return;
                    } finally {
                    }
                }
            default:
                this.f17721m.s();
                return;
        }
    }
}
