package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class b {
    public static final String c = "com.amazon.identity.auth.device.b";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f232a;
    public BinderC0052b b = new BinderC0052b();

    /* JADX INFO: renamed from: com.amazon.identity.auth.device.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DCP */
    public class BinderC0052b extends ISubAuthenticator.Stub {
        public BinderC0052b() {
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator
        public void getAccountRemovalAllowed(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2) throws RemoteException {
            e.a(b.this.f232a);
            b.this.a(iSubAuthenticatorResponse, new Account(str2, str));
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator
        public void getAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            e.a(b.this.f232a);
            if (iSubAuthenticatorResponse == null || iAmazonAccountAuthenticator == null) {
                Log.e(ga.a(b.c), "Invalid parameter passed to getAuthToken");
            } else {
                b.this.a(iSubAuthenticatorResponse, new Account(str2, str), str3, bundle, iAmazonAccountAuthenticator);
            }
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator.Stub, android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            try {
                return super.onTransact(i, parcel, parcel2, i2);
            } catch (RuntimeException e) {
                Log.e(ga.a(b.c), "Unexpected Runtime Exception", e);
                throw e;
            }
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticator
        public void updateAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
            e.a(b.this.f232a);
            if (iSubAuthenticatorResponse == null || iAmazonAccountAuthenticator == null) {
                Log.e(ga.a(b.c), "Invalid parameter passed to updateAuthTokens");
            } else {
                b.this.b(iSubAuthenticatorResponse, new Account(str2, str), str3, bundle, iAmazonAccountAuthenticator);
            }
        }
    }

    public b(Context context) {
        this.f232a = context;
    }

    public abstract void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account);

    public abstract void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator);

    public abstract void b(ISubAuthenticatorResponse iSubAuthenticatorResponse, Account account, String str, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator);
}
