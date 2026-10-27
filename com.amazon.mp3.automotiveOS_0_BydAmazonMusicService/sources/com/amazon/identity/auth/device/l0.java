package com.amazon.identity.auth.device;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.kcpsdk.common.ParseError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l0 extends tb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ISubAuthenticatorResponse f413a;
    public final /* synthetic */ String b;
    public final /* synthetic */ n0 c;

    public l0(n0 n0Var, ISubAuthenticatorResponse iSubAuthenticatorResponse, String str) {
        this.c = n0Var;
        this.f413a = iSubAuthenticatorResponse;
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a() {
        try {
            Log.e(ga.a(n0.A), "Authentication failure when updating the credentials for child app.");
            this.f413a.onResult(this.c.a(103, "Authentication error during update credentials"));
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during authentication failure callback for updateCredentials");
        }
    }

    @Override // com.amazon.identity.auth.device.tb
    public void b() {
        try {
            Log.e(ga.a(n0.A), "Update SubAuthenticator Credentials onNetworkFailure");
            yd.b("NetworkError14:DMSSubAuthenticator", new String[0]);
            this.f413a.onError(3, "Network failure");
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during network failure callback for updateCredentials");
        }
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a(ParseError parseError) {
        try {
            Log.e(ga.a(n0.A), "Update SubAuthenticator Credentials onParseError");
            this.f413a.onError(5, "Received bad response");
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during invalid response callback for updateCredentials");
        }
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a(Object obj) {
        Log.i(ga.a(n0.A), "Update credential request succeeded");
        try {
            n0.a(this.c, this.f413a, (kc) obj, this.b);
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during update credentials call");
        }
    }
}
