package b.b.g.n.l;

import android.os.SystemProperties;
import android.text.TextUtils;
import b.b.g.n.q.g;
import b.b.g.n.q.k;
import com.byd.carsettings.ui.fragment.dipilot.ParkAssistFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: ParkAssistPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends b<ParkAssistFragment> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k f1165e = k.c();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final g f1166f = g.g();

    public int h() {
        return this.f1165e.g();
    }

    public boolean i() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.camera.isp", "-1");
        String str2 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.avm.name", "-1");
        if (TextUtils.equals(str, "-1") || TextUtils.equals(str2, "-1")) {
            return false;
        }
        int iI0 = this.f1161b.i0();
        return iI0 == 1 || iI0 == 2;
    }

    public boolean j() {
        int iA = this.f1162c.a();
        BydLogHelper.logD("configAutoStudy", "isCarEV : " + iA);
        if (iA == 47 || iA == 54 || iA == 58) {
            return false;
        }
        return (iA == 73 && this.f1166f.f() == 1) ? false : true;
    }

    public void l() {
        this.f1165e.j(1);
    }
}
