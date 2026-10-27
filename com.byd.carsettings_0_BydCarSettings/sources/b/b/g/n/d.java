package b.b.g.n;

import android.content.Context;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import b.b.m.e.g;
import com.byd.carsettings.ui.activity.MovePowerStationActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MovePowerStationPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends g<MovePowerStationActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f982a = d.class.getSimpleName();

    public int A(int i, int i2) {
        return b.b.g.n.q.c.K().h0(i, i2);
    }

    public int B(int i) {
        return b.b.g.n.q.c.K().y0(i);
    }

    public void C() {
        getV().k0(r(), true, true);
    }

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(MovePowerStationActivity movePowerStationActivity) {
        super.bindV(movePowerStationActivity);
        EventBus.getDefault().register(this);
    }

    public int d() {
        return b.b.g.n.q.c.K().c();
    }

    public int e() {
        return b.b.g.n.q.c.K().d();
    }

    public int f() {
        return b.b.g.n.q.c.K().h();
    }

    public String g() {
        int i = b.b.g.n.q.c.K().i();
        if (i >= 205) {
            return "--";
        }
        return i + "";
    }

    public String h() {
        int iJ = b.b.g.n.q.c.K().j();
        if (iJ >= 205) {
            return "--";
        }
        return iJ + "";
    }

    public String i() {
        double dK = b.b.g.n.q.c.K().k();
        return (dK < -400.0d || dK > 0.0d) ? "--" : String.format("%.1f", Double.valueOf(dK));
    }

    public String j() {
        int iQ = b.b.g.n.q.c.K().q();
        if (iQ == 4095) {
            return "--";
        }
        return iQ + "";
    }

    public String l() {
        double dR = b.b.g.n.q.c.K().r();
        return dR < 40.95d ? String.format("%.2f", Double.valueOf(dR)) : "--";
    }

    public String m() {
        double dB = b.b.g.n.q.c.K().B();
        return (dB < -500.0d || dB > 500.0d) ? "--" : String.format("%.1f", Double.valueOf(dB));
    }

    public int n() {
        return b.b.g.n.q.c.K().E();
    }

    public int o() {
        return b.b.g.n.q.c.K().I();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
        }
        switch (aVar.h()) {
            case 140008:
                getV().l0(aVar.c());
                break;
            case 140010:
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = r() == 0 ? 0 : 1;
                }
                getV().k0(iC, !b.b.g.r.e.e(aVar), false);
                break;
            case 140015:
                getV().m0(aVar.c());
                break;
            case 140016:
                getV().j0(aVar.c());
                break;
            case 140018:
                getV().n0(aVar.b());
                break;
        }
    }

    public String q() {
        int iJ = b.b.g.n.q.c.K().J();
        if (iJ >= 4095) {
            return "--";
        }
        return iJ + "";
    }

    public int r() {
        return b.b.g.n.q.c.K().L();
    }

    public String s() {
        double dR = b.b.g.n.q.c.K().R();
        return dR < 40.95d ? String.format("%.2f", Double.valueOf(dR)) : "--";
    }

    public int t() {
        int iS = b.b.g.n.q.c.K().S();
        if (iS < 0 || iS > 100) {
            return -1;
        }
        return iS;
    }

    public int u() {
        return b.b.g.n.q.c.K().U();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }

    public int v() {
        int iV = b.b.g.n.q.c.K().V();
        if (iV <= 0 || iV > 100) {
            return -1;
        }
        return iV;
    }

    public int w() {
        return b.b.g.n.q.c.K().W();
    }

    public int x() {
        int iX = b.b.g.n.q.c.K().X();
        if (iX < 0 || iX > 100) {
            return -1;
        }
        return iX;
    }

    public String y() {
        double dY = b.b.g.n.q.c.K().Y();
        return (dY < 0.0d || dY > 100.0d) ? "--" : String.format("%.1f", Double.valueOf(dY));
    }

    public int z(Context context) {
        int socValue = BYDAutoOtaDevice.getInstance(context).getSocValue();
        if (socValue < 0 || socValue > 100) {
            return -1;
        }
        return socValue;
    }
}
