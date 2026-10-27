package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: IncognitoProfileDestroyer.java */
/* JADX INFO: loaded from: classes.dex */
public final class gy0 implements hz0 {
    public final y13 a;

    public gy0(a23 a23Var) {
        this.a = a23Var;
    }

    @Override // defpackage.hz0
    public final void q() {
        boolean z;
        Profile profileF;
        if (n3.J()) {
            return;
        }
        Iterator it = dz0.a().a.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (((cz0) it.next()).isActiveModel()) {
                z = true;
                break;
            }
        }
        if (z || (profileF = this.a.g(true).f()) == null) {
            return;
        }
        N.MScIZBOB(profileF.b, profileF);
    }
}
