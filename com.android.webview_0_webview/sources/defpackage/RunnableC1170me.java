package defpackage;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.signin.internal.zzm;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: me, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1170me implements Runnable {
    public final /* synthetic */ zzm s;
    public final /* synthetic */ BinderC1059ke t;

    public RunnableC1170me(BinderC1059ke binderC1059ke, zzm zzmVar) {
        this.t = binderC1059ke;
        this.s = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BinderC1059ke binderC1059ke = this.t;
        zzm zzmVar = this.s;
        Objects.requireNonNull(binderC1059ke);
        ConnectionResult connectionResult = zzmVar.t;
        if (connectionResult.f()) {
            zzam zzamVar = zzmVar.u;
            ConnectionResult connectionResult2 = zzamVar.u;
            if (!connectionResult2.f()) {
                String strValueOf = String.valueOf(connectionResult2);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 48);
                sb.append("Sign-in succeeded with resolve account failure: ");
                sb.append(strValueOf);
                Log.wtf("SignInCoordinator", sb.toString(), new Exception());
                ((C0451Zc) binderC1059ke.g).b(connectionResult2);
                ((BaseGmsClient) binderC1059ke.f).disconnect();
                return;
            }
            InterfaceC1115le interfaceC1115le = binderC1059ke.g;
            InterfaceC0502af interfaceC0502afB = zzamVar.b();
            Set set = binderC1059ke.d;
            C0451Zc c0451Zc = (C0451Zc) interfaceC1115le;
            Objects.requireNonNull(c0451Zc);
            if (interfaceC0502afB == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                c0451Zc.b(new ConnectionResult(4));
            } else {
                c0451Zc.c = interfaceC0502afB;
                c0451Zc.d = set;
                if (c0451Zc.e) {
                    ((BaseGmsClient) c0451Zc.a).h(interfaceC0502afB, set);
                }
            }
        } else {
            ((C0451Zc) binderC1059ke.g).b(connectionResult);
        }
        ((BaseGmsClient) binderC1059ke.f).disconnect();
    }
}
