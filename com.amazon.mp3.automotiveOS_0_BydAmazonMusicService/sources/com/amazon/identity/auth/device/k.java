package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class k implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y5 f401a;
    public final /* synthetic */ Bundle b;
    public final /* synthetic */ Callback c;
    public final /* synthetic */ g d;

    public k(g gVar, y5 y5Var, Bundle bundle, Callback callback) {
        this.d = gVar;
        this.f401a = y5Var;
        this.b = bundle;
        this.c = callback;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        this.c.onError(bundle);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        Set<String> setC = this.d.e.c();
        s2 s2Var = new s2(null);
        this.d.a(setC, s2Var, this.f401a, this.b);
        try {
            if (!xa.a()) {
                s2Var.get();
            }
        } catch (MAPCallbackErrorException e) {
            Log.e(ga.a(g.r), "MAP Error calling deregisterAllAccountsManually. Error: " + e.c(e.getErrorBundle()), e);
        } catch (InterruptedException e2) {
            Log.e(ga.a(g.r), "InterruptedException calling deregisterAllAccountsManually.", e2);
        } catch (ExecutionException e3) {
            Log.e(ga.a(g.r), "ExecutionException calling deregisterAllAccountsManually", e3);
        }
        this.c.onSuccess(bundle);
    }
}
