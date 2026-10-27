package defpackage;

import defpackage.ny0;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: compiled from: IncognitoLockSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ay0 implements ny0.a {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ by0 c;

    public ay0(by0 by0Var, boolean z, boolean z2) {
        this.c = by0Var;
        this.a = z;
        this.b = z2;
    }

    @Override // ny0.a
    public final void a() {
        PrefService prefServiceK = oj2.k(Profile.d());
        boolean z = this.b;
        prefServiceK.e("incognito.incognito_reauthentication", z);
        oj2.U(z ? 1 : 0, 2, "Android.IncognitoReauth.PrefToggledFromSettingPage");
    }

    @Override // ny0.a
    public final void b() {
        by0 by0Var = this.c;
        by0Var.b = true;
        by0Var.a.Y(this.a);
        by0Var.b = false;
    }

    @Override // ny0.a
    public final void c() {
        by0 by0Var = this.c;
        by0Var.b = true;
        by0Var.a.Y(this.a);
        by0Var.b = false;
    }
}
