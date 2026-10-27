package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ IBootstrapSSOService f487a;
    public final /* synthetic */ n2 b;

    public p2(n2 n2Var, IBootstrapSSOService iBootstrapSSOService) {
        this.b = n2Var;
        this.f487a = iBootstrapSSOService;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("appPackageName", this.b.f447a.getPackageName());
            if (!e.a(this.b.h)) {
                bundle.putStringArrayList(MAPAccountManager.KEY_SSO_REQUIRED_ADDITIONAL_DATA, this.b.h);
            }
            Bundle bundleBootstrapForPackage = this.f487a.bootstrapForPackage(bundle);
            this.b.d.cancel();
            n2 n2Var = this.b;
            if (n2Var.f.getAndSet(false)) {
                n2Var.f447a.unbindService(n2Var.j);
            }
            if (n2Var.e.getAndSet(true)) {
                return;
            }
            boolean z = bundleBootstrapForPackage.getBoolean("bootstrapSuccess", false);
            bundleBootstrapForPackage.remove("bootstrapSuccess");
            if (z) {
                n2Var.b.onSuccess(bundleBootstrapForPackage);
            } else {
                n2Var.b.onError(bundleBootstrapForPackage);
            }
        } catch (RemoteException e) {
            Log.e(ga.a(n2.k), "Unexpected error from service", e);
            this.b.a(MAPAccountManager.BootstrapError.SERVICE_ERROR, "Unexpected error from service: " + e.getMessage());
        }
    }
}
