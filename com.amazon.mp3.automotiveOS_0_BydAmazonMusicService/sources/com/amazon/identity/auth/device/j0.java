package com.amazon.identity.auth.device;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j0 implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ISubAuthenticatorResponse f389a;
    public final /* synthetic */ String b;
    public final /* synthetic */ n0 c;

    public j0(n0 n0Var, ISubAuthenticatorResponse iSubAuthenticatorResponse, String str) {
        this.c = n0Var;
        this.f389a = iSubAuthenticatorResponse;
        this.b = str;
    }

    public void a() {
        try {
            Log.e(ga.a(n0.A), "Authentication error when registering the child app.");
            this.f389a.onResult(this.c.a(103, "Authentication error during register"));
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during authentication failure callback for registerChildApplication");
        }
    }

    public void b() {
        try {
            Log.e(ga.a(n0.A), "Bad response when registering the child app.");
            this.f389a.onError(5, "Received bad response");
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during invalid response callback for registerChildApplication");
        }
    }

    public void c() {
        try {
            Log.e(ga.a(n0.A), "Network error when registering the child app.");
            yd.b("NetworkError13:DMSSubAuthenticator", new String[0]);
            this.f389a.onError(3, "Network error");
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException during network failure callback for registerChildApplication");
        }
    }

    public void a(kc kcVar) {
        try {
            Log.i(ga.a(n0.A), "Getting response for the child application registration. Storing results.");
            n0.a(this.c, this.f389a, kcVar, this.b);
        } catch (RemoteException unused) {
            Log.e(ga.a(n0.A), "RemoteException when credentials was received for registerChildApplication");
        }
    }
}
