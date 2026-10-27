package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class k0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ kc f402a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ ISubAuthenticatorResponse d;
    public final /* synthetic */ n0 e;

    public k0(n0 n0Var, kc kcVar, String str, String str2, ISubAuthenticatorResponse iSubAuthenticatorResponse) {
        this.e = n0Var;
        this.f402a = kcVar;
        this.b = str;
        this.c = str2;
        this.d = iSubAuthenticatorResponse;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.e.a(this.f402a, this.b, this.c);
        if (this.d != null) {
            Log.i(ga.a(n0.A), "Callback with success after storing tokens for the child app.");
            this.e.a(this.d);
        }
    }
}
