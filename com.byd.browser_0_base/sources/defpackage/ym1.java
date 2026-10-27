package defpackage;

import J.N;
import android.content.Context;
import android.view.ViewStub;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: NewTabPageVideoIPHManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ym1 {
    public final Context a;
    public final xe3 b;
    public final rl3 c;
    public final em3 d;

    public ym1(ViewStub viewStub, Profile profile) {
        sk skVar = bq.a;
        if (N.M09VlOh_("VideoTutorials")) {
            this.a = viewStub.getContext();
            this.b = oj2.t(profile);
            this.c = new rl3(viewStub, dx0.c(profile.g(), ys0.a), new wm1(this, 0), new so(5, this));
            em3 em3Var = (em3) N.MBuXqyoS(profile);
            this.d = em3Var;
            em3Var.d(new wb(5, this));
        }
    }
}
