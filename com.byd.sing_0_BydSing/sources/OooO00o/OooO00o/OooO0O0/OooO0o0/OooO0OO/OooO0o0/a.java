package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0;

import OooO00o.OooO00o.OooO0o0.OooO0o.OooO;
import a.a.d.c.b;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IVolumeAdjustment f561a;

    public final IVolumeAdjustment b() {
        IVolumeAdjustment iVolumeAdjustment = this.f561a;
        if (iVolumeAdjustment != null) {
            return iVolumeAdjustment;
        }
        h.m("volumeAdjustment");
        throw null;
    }

    public final void c(IVolumeAdjustment iVolumeAdjustment) {
        h.d(iVolumeAdjustment, "<set-?>");
        this.f561a = iVolumeAdjustment;
    }

    public void d() {
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o oooO0o = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a;
        Integer numA = oooO0o.b().a();
        if (numA != null && numA.intValue() == 0) {
            OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().p();
        } else {
            OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().n();
        }
        Integer numA2 = oooO0o.f().a();
        if (numA2 != null && numA2.intValue() == 0) {
            OooO.f471a.a().g();
        } else {
            OooO.f471a.a().f();
        }
    }

    public void e() {
        OooO00o.OooO00o.OooO0o0.OooO0o.OooO0o.f481a.a().p();
        OooO.f471a.a().g();
    }
}
