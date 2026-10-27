package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: DownloadManagerCoordinatorFactoryHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class rc0 {
    public static uc0 a(Activity activity, wc0 wc0Var, wp2 wp2Var, bi1 bi1Var) {
        OTRProfileID oTRProfileID = OTRProfileID.b;
        OTRProfileID oTRProfileID2 = wc0Var.a;
        Profile profileE = oTRProfileID2 != null ? Profile.d().e(oTRProfileID2) : Profile.d();
        return new uc0(activity, wc0Var, new p32(), new m62(1), wp2Var, bi1Var, oj2.t(profileE), new rk0(profileE), a8.z(), ys0.a);
    }
}
