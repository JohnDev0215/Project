package com.amazon.identity.auth.accounts;

import android.accounts.Account;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.device.api.MAPInit;
import com.amazon.identity.auth.device.b;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.j5;
import com.amazon.identity.auth.device.la;
import com.amazon.identity.auth.device.n0;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPSubAuthenticatorService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f146a;

    /* JADX INFO: compiled from: DCP */
    public class a extends b {
        public n0 d;

        public a(Context context) {
            super(context);
        }

        @Override // com.amazon.identity.auth.device.b
        public void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            a().getAuthToken(iSubAuthenticatorResponse, account.type, account.name, str, bundle, iAmazonAccountAuthenticator);
        }

        @Override // com.amazon.identity.auth.device.b
        public void b(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            a().updateAuthToken(iSubAuthenticatorResponse, account.type, account.name, str, bundle, iAmazonAccountAuthenticator);
        }

        @Override // com.amazon.identity.auth.device.b
        public void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account) {
            a().getAccountRemovalAllowed(iSubAuthenticatorResponse, account.type, account.name);
        }

        public final synchronized n0 a() {
            if (this.d == null) {
                MAPSubAuthenticatorService mAPSubAuthenticatorService = MAPSubAuthenticatorService.this;
                String str = n0.z;
                String packageName = mAPSubAuthenticatorService.getPackageName();
                j5 j5VarA = MAPApplicationInformationQueryer.a(mAPSubAuthenticatorService).a(packageName);
                if (j5VarA != null) {
                    try {
                        this.d = new n0(mAPSubAuthenticatorService, packageName, j5VarA.c(), j5VarA.d(), la.a(mAPSubAuthenticatorService, packageName), true);
                    } catch (RemoteMAPException e) {
                        throw new IllegalStateException("Could not construct DMSSubAuthenticator for this package (" + packageName + ") because we couldn't query its MAP info provider", e);
                    }
                } else {
                    throw new IllegalStateException("Could not construct DMSSubAuthenticator for this package (" + packageName + ") because it's not properly integrated with MAP");
                }
            }
            return this.d;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f146a.b.asBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        MAPInit.getInstance(this).initialize();
        this.f146a = new a(this);
    }
}
