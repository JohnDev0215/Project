package b.b.g.d;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: INSModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return true;
    }

    public int b() {
        int autoType = BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).getAutoType();
        BydLogHelper.logD(i.class.getSimpleName(), "INSModelStudy  getNavMode carType : " + autoType);
        return (autoType == 143 || autoType == 224 || autoType == 223 || autoType == 180) ? 2 : 1;
    }

    public boolean c() {
        return b.b.g.n.q.b.c().g();
    }

    public boolean d() {
        return u.I().J() == 1;
    }

    public boolean e() {
        if (b.b.a.i.a.b() == 180) {
            return f();
        }
        return true;
    }

    public boolean f() {
        return u.I().Q() == 7;
    }

    public boolean g() {
        return m.c();
    }
}
