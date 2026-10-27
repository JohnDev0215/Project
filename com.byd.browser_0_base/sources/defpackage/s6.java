package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: AppLaunchDrawBlocker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s6 implements kr2 {
    public final /* synthetic */ t6 e;

    public s6(t6 t6Var) {
        this.e = t6Var;
    }

    @Override // defpackage.kr2
    public final void l() {
        this.e.getClass();
        SharedPreferencesManager.getInstance().l("Chrome.AppLaunch.SearchEngineHadLogo", oj2.q(Profile.d()).b());
    }

    @Override // defpackage.kr2
    public final void j() {
    }
}
