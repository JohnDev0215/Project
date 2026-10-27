package defpackage;

import J.N;
import android.net.Uri;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: LinkToTextIPHController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z71 extends nh0 {
    public final /* synthetic */ zp1 e;
    public final /* synthetic */ a81 f;

    public z71(a81 a81Var, zp1 zp1Var) {
        this.f = a81Var;
        this.e = zp1Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        sk skVar = bq.a;
        if (N.M09VlOh_("MessagesForAndroidInfrastructure")) {
            String encodedFragment = Uri.parse(gurl.j()).getEncodedFragment();
            if (encodedFragment != null && encodedFragment.contains(":~:text=")) {
                Profile profileD = (Profile) this.e.get();
                if (profileD == null) {
                    profileD = Profile.d();
                }
                if (profileD == null) {
                    return;
                }
                xe3 xe3VarT = oj2.t(profileD);
                this.f.b = xe3VarT;
                if (xe3VarT.wouldTriggerHelpUI("IPH_SharedHighlightingReceiver")) {
                    xm1 xm1Var = new xm1(this, 10, tab);
                    Iterator<RenderFrameHost> it = tab.c().y().c().iterator();
                    while (it.hasNext()) {
                        f83 f83Var = (f83) it.next().e(g83.a);
                        if (f83Var != null) {
                            f83Var.k(new w71(new xm1(xm1Var, 9, f83Var)));
                        }
                    }
                }
            }
        }
    }
}
