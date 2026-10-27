package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.Callback;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class b0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y5 f234a;
    public final /* synthetic */ CentralAccountManagerCommunication b;

    /* JADX INFO: compiled from: DCP */
    public class a implements Callback {
        public a(b0 b0Var) {
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            Log.e(ga.a(CentralAccountManagerCommunication.g), "Failed to deregister the device after detecting child device type change.");
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            Log.i(ga.a(CentralAccountManagerCommunication.g), "Device was deregistered due to the child device type change.");
        }
    }

    public b0(CentralAccountManagerCommunication centralAccountManagerCommunication, y5 y5Var) {
        this.b = centralAccountManagerCommunication;
        this.f234a = y5Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.a(new a(this), this.f234a, (Bundle) null);
    }
}
