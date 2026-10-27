package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzak;
import com.google.android.gms.signin.internal.zzk;
import com.google.android.gms.signin.internal.zzm;

/* JADX INFO: renamed from: dj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0674dj extends AbstractC0399We implements InterfaceC1395qj {
    public final boolean C;
    public final C0381Ve D;
    public final Bundle E;
    public Integer F;

    public C0674dj(Context context, Looper looper, C0381Ve c0381Ve, InterfaceC0110Gc interfaceC0110Gc, InterfaceC0128Hc interfaceC0128Hc) {
        C0618cj c0618cj = c0381Ve.g;
        Integer num = c0381Ve.i;
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", c0381Ve.a);
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        if (c0618cj != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        super(context, looper, 44, c0381Ve, interfaceC0110Gc, interfaceC0128Hc);
        this.C = true;
        this.D = c0381Ve;
        this.E = bundle;
        this.F = c0381Ve.i;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public /* synthetic */ IInterface d(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return iInterfaceQueryLocalInterface instanceof InterfaceC0897hj ? (InterfaceC0897hj) iInterfaceQueryLocalInterface : new C1008jj(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Bundle g() {
        if (!this.g.getPackageName().equals(this.D.e)) {
            this.E.putString("com.google.android.gms.signin.internal.realClientPackageName", this.D.e);
        }
        return this.E;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.InterfaceC1882zc
    public int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String j() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String k() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, defpackage.InterfaceC1882zc
    public boolean requiresSignIn() {
        return this.C;
    }

    public final void v(InterfaceC0785fj interfaceC0785fj) {
        Cif.g(interfaceC0785fj, "Expecting a valid ISignInCallbacks");
        try {
            Account account = this.D.a;
            if (account == null) {
                account = new Account("<<default account>>", "com.google");
            }
            zzak zzakVar = new zzak(account, this.F.intValue(), "<<default account>>".equals(account.name) ? C0946ic.a(this.g).b() : null);
            InterfaceC0897hj interfaceC0897hj = (InterfaceC0897hj) i();
            zzk zzkVar = new zzk(zzakVar);
            C1008jj c1008jj = (C1008jj) interfaceC0897hj;
            Parcel parcelF = c1008jj.f();
            AbstractC0383Vg.d(parcelF, zzkVar);
            AbstractC0383Vg.c(parcelF, interfaceC0785fj);
            c1008jj.d(12, parcelF);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                interfaceC0785fj.a(new zzm());
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }
}
