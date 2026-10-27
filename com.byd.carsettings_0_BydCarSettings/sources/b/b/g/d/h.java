package b.b.g.d;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import b.b.g.r.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: HudModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return !b() && c();
    }

    public boolean b() {
        boolean z = u.b(b.b.a.a.a.b().a(), "car_set_first_in", 0) == 0;
        BydLogHelper.logD(h.class.getSimpleName(), "isFirstLoad  :  ,isFirstIn " + z);
        return z;
    }

    public final boolean c() {
        int iHasMessage = BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).hasMessage(156);
        BydLogHelper.logE(h.class.getSimpleName(), "showHud  :  ,state " + iHasMessage);
        return iHasMessage == 1;
    }
}
