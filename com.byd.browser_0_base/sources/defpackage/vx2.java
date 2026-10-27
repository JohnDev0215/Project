package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;

/* JADX INFO: compiled from: SyncConsentFragmentBase.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vx2 implements SigninManager.a {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ Runnable b;
    public final /* synthetic */ SyncConsentFragmentBase c;

    public vx2(SyncConsentFragmentBase syncConsentFragmentBase, boolean z, Runnable runnable) {
        this.c = syncConsentFragmentBase;
        this.a = z;
        this.b = runnable;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.a
    public final void a() {
        N.MnEYaN9w(Profile.d(), true);
        sk skVar = bq.a;
        if (N.M09VlOh_("TangibleSync")) {
            int i = SyncConsentFragmentBase.r0;
            N.M37SqSAy("TangibleSync", "group_id", 1);
        }
        this.c.J0(this.a);
        this.b.run();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.a
    public final void b() {
        this.b.run();
    }
}
