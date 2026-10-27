package defpackage;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.signin.internal.zzm;

/* JADX INFO: renamed from: Fd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0093Fd extends AbstractC0326Sd {
    public final /* synthetic */ C1773xd b;
    public final /* synthetic */ zzm c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0093Fd(InterfaceC0290Qd interfaceC0290Qd, C1773xd c1773xd, zzm zzmVar) {
        super(interfaceC0290Qd);
        this.b = c1773xd;
        this.c = zzmVar;
    }

    @Override // defpackage.AbstractC0326Sd
    public final void a() {
        C1773xd c1773xd = this.b;
        zzm zzmVar = this.c;
        boolean z = false;
        if (c1773xd.o(0)) {
            ConnectionResult connectionResult = zzmVar.t;
            if (!connectionResult.f()) {
                if (c1773xd.l && !connectionResult.e()) {
                    z = true;
                }
                if (!z) {
                    c1773xd.n(connectionResult);
                    return;
                } else {
                    c1773xd.k();
                    c1773xd.i();
                    return;
                }
            }
            zzam zzamVar = zzmVar.u;
            ConnectionResult connectionResult2 = zzamVar.u;
            if (connectionResult2.f()) {
                c1773xd.n = true;
                c1773xd.o = zzamVar.b();
                c1773xd.p = zzamVar.v;
                c1773xd.q = zzamVar.w;
                c1773xd.i();
                return;
            }
            String strValueOf = String.valueOf(connectionResult2);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 48);
            sb.append("Sign-in succeeded with resolve account failure: ");
            sb.append(strValueOf);
            Log.wtf("GACConnecting", sb.toString(), new Exception());
            c1773xd.n(connectionResult2);
        }
    }
}
