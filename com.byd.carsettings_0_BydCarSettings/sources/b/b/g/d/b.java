package b.b.g.d;

import android.hardware.bydauto.ac.BYDAutoAcDevice;
import b.b.g.n.q.u;

/* JADX INFO: compiled from: AirModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return true;
    }

    public boolean b() {
        int iA = b.b.g.n.q.m.p0().a();
        return iA == 1 || iA == 2;
    }

    public boolean c() {
        return BYDAutoAcDevice.getInstance(b.b.a.a.a.b().a()).getAcWarmTypeOnlineState() == 3;
    }

    public boolean d() {
        return u.I().h0() == 1;
    }

    public boolean e() {
        return true;
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return u.I().K() == 2;
    }

    public boolean h() {
        int iO0 = b.b.g.n.q.m.p0().O0();
        return iO0 >= 1 && iO0 <= 4;
    }

    public boolean i() {
        int acRemoteCtrlTime = BYDAutoAcDevice.getInstance(b.b.a.a.a.b().a()).getAcRemoteCtrlTime();
        return acRemoteCtrlTime >= 1 && acRemoteCtrlTime <= 5;
    }

    public boolean j() {
        return (b.b.g.n.q.b.c().e(2) == 0 || d()) ? false : true;
    }
}
