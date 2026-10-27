package defpackage;

import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: NewTabPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hm1 implements e23 {
    public final /* synthetic */ Profile e;
    public final /* synthetic */ WindowAndroid f;
    public final /* synthetic */ h g;

    public hm1(h hVar, Profile profile, WindowAndroid windowAndroid) {
        this.g = hVar;
        this.e = profile;
        this.f = windowAndroid;
    }

    @Override // defpackage.e23
    public final void g() {
        ca caVarA = ca.a(this.f);
        h hVar = this.g;
        hVar.v(this.e, caVarA);
        hVar.G.l(this);
    }
}
