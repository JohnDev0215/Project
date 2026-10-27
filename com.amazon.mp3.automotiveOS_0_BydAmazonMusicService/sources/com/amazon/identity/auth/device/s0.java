package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.q0;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class s0 implements p.b {
    public s0(q0.d dVar) {
    }

    @Override // com.amazon.identity.auth.device.p.b
    public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
        Log.e(ga.a(q0.f), String.format("Deregister Failure for Overriding DSN Child Device Type: %s", registrationError.name()));
    }

    @Override // com.amazon.identity.auth.device.p.b
    public void a(String str, String str2, Bundle bundle) {
        String str3 = q0.f;
        String.format("Deregister Succeeded for Overriding DSN Child Device Type. directedId %s", str);
        ga.a(str3);
    }
}
