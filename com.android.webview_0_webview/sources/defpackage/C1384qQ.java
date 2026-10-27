package defpackage;

import android.os.RemoteException;
import java.util.Objects;

/* JADX INFO: renamed from: qQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1384qQ extends LN {
    public CP v;

    public C1384qQ(CP cp) {
        this.v = cp;
    }

    @Override // defpackage.LN
    public final boolean b(String str) {
        if (this.v.c() == null) {
            return false;
        }
        return C1185mt.j(this.v.c(), str);
    }

    @Override // defpackage.LN
    public final boolean c(String[] strArr, int i) {
        if (this.v.c() == null) {
            return false;
        }
        CP cp = this.v;
        Objects.requireNonNull(cp);
        try {
            ((TR) cp.a).d(strArr, i);
            return true;
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }

    @Override // defpackage.LN
    public final boolean d(String str) {
        if (this.v.c() == null) {
            return false;
        }
        CP cp = this.v;
        Objects.requireNonNull(cp);
        try {
            return ((TR) cp.a).f(str);
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }
}
