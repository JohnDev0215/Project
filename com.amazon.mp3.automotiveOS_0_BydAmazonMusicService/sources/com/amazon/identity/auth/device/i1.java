package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.accounts.SubAuthenticatorConnection;
import com.amazon.identity.auth.device.q0;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i1 extends ISubAuthenticatorResponse.Stub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SubAuthenticatorConnection.c f376a;
    public final /* synthetic */ SubAuthenticatorConnection b;

    public i1(SubAuthenticatorConnection subAuthenticatorConnection, SubAuthenticatorConnection.c cVar) {
        this.b = subAuthenticatorConnection;
        this.f376a = cVar;
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
    public void onError(int i, String str) {
        SubAuthenticatorConnection.c cVar = this.f376a;
        if (cVar != null) {
            ((q0.i) cVar).a(this.b, i, str);
        }
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
    public void onResult(Bundle bundle) {
        SubAuthenticatorConnection.c cVar = this.f376a;
        if (cVar != null) {
            q0.i iVar = (q0.i) cVar;
            synchronized (iVar.d) {
                String str = q0.f;
                String.format("SubAuth Deregister Success: Package=%s,", iVar.f.f153a.f390a);
                ga.a(str);
                iVar.e.set(true);
                iVar.asyncOperationComplete();
            }
        }
    }
}
