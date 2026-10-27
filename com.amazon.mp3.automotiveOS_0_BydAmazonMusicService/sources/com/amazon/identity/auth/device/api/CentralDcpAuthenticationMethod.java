package com.amazon.identity.auth.device.api;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.dcp.sso.IRequestAuthenticationMethod;
import com.amazon.dcp.sso.ReturnValueOrError;
import com.amazon.identity.auth.device.a0;
import com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.cb;
import com.amazon.identity.auth.device.d7;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ea;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.p5;
import com.amazon.identity.auth.device.r4;
import com.amazon.identity.auth.device.r9;
import com.amazon.identity.auth.device.s2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.u5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.auth.request.NoCredentialsException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class CentralDcpAuthenticationMethod extends AuthenticationMethod implements ADPCorpusSigningAuthenticationMethod {
    public CentralDcpAuthenticationMethod(Context context, String str, AuthenticationType authenticationType) {
        super(context, str, authenticationType);
    }

    public void a(ReturnValueOrError returnValueOrError, final s2 s2Var, byte[] bArr, Bundle bundle) {
        if (returnValueOrError == null) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Connection to SSO timeout.");
            e.a(s2Var, 1, "Connection to SSO timeout.");
            return;
        }
        if (!returnValueOrError.isError()) {
            Bundle response = returnValueOrError.getResponse();
            String string = response.getString("token");
            String string2 = response.getString("digest");
            Bundle bundle2 = new Bundle();
            bundle2.putString("adp_token", string);
            bundle2.putString(ADPCorpusSigningAuthenticationMethod.KEY_ADP_AUTH_SIGNATURE, string2);
            s2Var.onSuccess(bundle2);
            return;
        }
        String responseMessage = returnValueOrError.getResponseMessage();
        int responseCode = returnValueOrError.getResponseCode();
        if (responseCode == 2) {
            if (bundle == null || !bundle.getBoolean(ADPCorpusSigningAuthenticationMethod.SignCorpusOption.USE_FALL_BACK_CREDENTIALS, false)) {
                e.a(s2Var, 2, responseMessage);
                return;
            } else {
                a(bArr, this.f198a.getAccount(), (Bundle) null, new Callback(this) { // from class: com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod.3
                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onError(Bundle bundle3) {
                        s2Var.onError(bundle3);
                    }

                    @Override // com.amazon.identity.auth.device.api.Callback
                    public void onSuccess(Bundle bundle3) {
                        s2Var.onSuccess(bundle3);
                    }
                });
                return;
            }
        }
        if (responseCode == 3 || responseCode == 4) {
            e.a(s2Var, 3, responseMessage);
        } else if (responseCode != 6) {
            e.a(s2Var, 5, responseMessage);
        } else {
            e.a(s2Var, 1, responseMessage);
        }
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    public MAPFuture<Bundle> getAuthenticationBundle(final Uri uri, final String str, final Map map, final byte[] bArr, s2 s2Var) throws IOException {
        Bundle bundle;
        a("You cannot authenticate this request on the main thread!");
        ComponentName componentNameA = p5.a(this.b, "com.amazon.dcp.sso.AuthenticatedRequestService", p5.b);
        a0 a0Var = componentNameA == null ? null : new a0(componentNameA);
        if (a0Var == null) {
            AuthenticationMethod.a(s2Var, 1, "A Central device credential location cannot be found", null);
            throw new AuthenticatedURLConnection.AuthenticationFailureIOException("A Central device credential location cannot be found");
        }
        final AtomicReference atomicReference = new AtomicReference();
        final de deVarB = yd.b("CentralDcpAuthenticationMethod", "bind");
        t5 t5Var = this.b;
        Intent intent = new Intent();
        intent.setAction(AccountConstants.INTENT_ACTION_GET_DEVICE_CREDENTIALS);
        ComponentName componentName = a0Var.f489a;
        if (componentName != null) {
            intent.setComponent(componentName);
        }
        new u5(t5Var, intent, 1) { // from class: com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod.1
            public final ReturnValueOrError a(Uri uri2, String str2, Map map2, byte[] bArr2, IBinder iBinder) throws RemoteException {
                Map map3;
                byte[] bArr3;
                ReturnValueOrError authenticationParametersForRequest;
                IRequestAuthenticationMethod iRequestAuthenticationMethodAsInterface = IRequestAuthenticationMethod.Stub.asInterface(iBinder);
                CentralDcpAuthenticationMethod centralDcpAuthenticationMethod = CentralDcpAuthenticationMethod.this;
                String str3 = centralDcpAuthenticationMethod.d;
                String str4 = centralDcpAuthenticationMethod.c;
                Map map4 = Collections.EMPTY_MAP;
                byte[] bArr4 = new byte[0];
                if (AuthenticationType.OAuth.getValue().equals(str3)) {
                    map3 = map4;
                    bArr3 = bArr4;
                } else {
                    map3 = map2;
                    bArr3 = bArr2;
                }
                if (CentralDcpAuthenticationMethod.this.e.a(Feature.DirectedIdSupported)) {
                    authenticationParametersForRequest = iRequestAuthenticationMethodAsInterface.getAuthenticationParametersForRequestByDirectedId(str3, str4, uri2, str2, map3, bArr3);
                } else {
                    Account accountA = r9.a(CentralDcpAuthenticationMethod.this.b, str4);
                    authenticationParametersForRequest = iRequestAuthenticationMethodAsInterface.getAuthenticationParametersForRequest(str3, accountA != null ? accountA.type : null, accountA != null ? accountA.name : null, uri2, str2, map3, bArr3);
                }
                ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod");
                return authenticationParametersForRequest;
            }

            @Override // com.amazon.identity.auth.device.u5
            public void useService(IBinder iBinder) {
                deVarB.c();
                de deVarB2 = yd.b("CentralDcpAuthenticationMethod", "getAuthenticationParams");
                try {
                    try {
                        try {
                            atomicReference.set(a(uri, str, map, bArr, iBinder));
                            deVarB2.c();
                            doneUsingService();
                        } catch (RuntimeException e) {
                            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Unknown error during getAuthenticationBundle execution.", e);
                            atomicReference.set(new ReturnValueOrError(5, e.toString()));
                            deVarB2.c();
                            doneUsingService();
                        }
                    } catch (RemoteException e2) {
                        Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Could not receive request authentication from dcp!", e2);
                        atomicReference.set(new ReturnValueOrError(6, "Connection to DCP has been lost"));
                        deVarB2.c();
                        doneUsingService();
                    }
                } catch (Throwable th) {
                    atomicReference.set(null);
                    deVarB2.c();
                    doneUsingService();
                    throw th;
                }
            }
        }.run(Long.valueOf(cb.f270a), TimeUnit.MILLISECONDS, "GetAuthenticationParameters");
        ReturnValueOrError returnValueOrError = (ReturnValueOrError) atomicReference.get();
        if (returnValueOrError == null) {
            AuthenticationMethod.a(s2Var, 1, "Connection to SSO timeout", null);
            throw new AuthenticatedURLConnection.AuthenticationFailureIOException("Connection to SSO timeout");
        }
        if (!returnValueOrError.isError()) {
            Bundle response = returnValueOrError.getResponse();
            if (s2Var != null && response != null) {
                s2Var.onSuccess(response);
            }
            return s2Var;
        }
        String responseMessage = returnValueOrError.getResponseMessage();
        int responseCode = returnValueOrError.getResponseCode();
        if (responseCode == 2) {
            Bundle response2 = returnValueOrError.getResponse();
            if (response2 == null || (bundle = response2.getBundle("com.amazon.identity.mobi.account.recover.context")) == null) {
                ea.a(this.b, responseMessage);
                AuthenticationMethod.a(s2Var, 2, responseMessage, null);
                throw new NoCredentialsException(responseMessage);
            }
            AuthenticationMethod.a(s2Var, 2, responseMessage, d7.a(bundle).c());
            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Account is in bad state, throwing AccountNeedsRecoveryException to help account recovery");
            throw new AuthenticatedURLConnection.AccountNeedsRecoveryException(responseMessage, bundle);
        }
        if (responseCode == 3) {
            AuthenticationMethod.a(s2Var, 4, responseMessage, null);
            throw new IllegalArgumentException(responseMessage);
        }
        if (responseCode == 5) {
            AuthenticationMethod.a(s2Var, 6, responseMessage, null);
            throw new RuntimeException(responseMessage);
        }
        if (responseCode != 6) {
            AuthenticationMethod.a(s2Var, 6, responseMessage, null);
            throw new IllegalArgumentException(responseMessage);
        }
        AuthenticationMethod.a(s2Var, 1, responseMessage, null);
        throw new AuthenticatedURLConnection.AuthenticationFailureIOException(responseMessage);
    }

    @Override // com.amazon.identity.auth.device.api.ADPCorpusSigningAuthenticationMethod
    public MAPFuture<Bundle> signCorpus(byte[] bArr, Bundle bundle, Callback callback) {
        return a(bArr, this.c, bundle, callback);
    }

    public CentralDcpAuthenticationMethod(Context context, String str, String str2) {
        super(context, str, str2);
    }

    public final void a(String str) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            throw new IllegalStateException(str);
        }
    }

    public final MAPFuture<Bundle> a(final byte[] bArr, final String str, final Bundle bundle, final Callback callback) {
        a("You cannot authenticate this corpus on the main thread!");
        final s2 s2Var = new s2(callback);
        if (bArr == null) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Cannot sign a null corpus.");
            e.a(s2Var, 3, "Cannot sign a null corpus.");
            return s2Var;
        }
        String str2 = this.d;
        AuthenticationType authenticationType = AuthenticationType.ADPAuthenticator;
        if (!authenticationType.getValue().equals(str2)) {
            String str3 = String.format("Only support authentication type: %s. Cannot sign the corpus with invalid authentication type: %s", authenticationType.getValue(), str2);
            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), str3);
            e.a(s2Var, 3, str3);
            return s2Var;
        }
        ComponentName componentNameA = p5.a(this.b, "com.amazon.dcp.sso.AuthenticatedRequestService", p5.b);
        a0 a0Var = componentNameA == null ? null : new a0(componentNameA);
        if (a0Var == null) {
            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "A Central device credential location cannot be found.");
            e.a(s2Var, 1, "A Central device credential location cannot be found.");
        } else {
            final de deVarB = yd.b("CentralDcpAuthenticationMethod", "bind");
            t5 t5Var = this.b;
            Intent intent = new Intent();
            intent.setAction(AccountConstants.INTENT_ACTION_GET_DEVICE_CREDENTIALS);
            ComponentName componentName = a0Var.f489a;
            if (componentName != null) {
                intent.setComponent(componentName);
            }
            if (!new r4(t5Var, intent, 1) { // from class: com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod.2
                public final ReturnValueOrError a(byte[] bArr2, IBinder iBinder) throws RemoteException {
                    ReturnValueOrError returnValueOrErrorSignCorpus;
                    IRequestAuthenticationMethod iRequestAuthenticationMethodAsInterface = IRequestAuthenticationMethod.Stub.asInterface(iBinder);
                    if (CentralDcpAuthenticationMethod.this.e.a(Feature.DirectedIdSupported)) {
                        returnValueOrErrorSignCorpus = iRequestAuthenticationMethodAsInterface.signCorpusByDirectedId("SHA256WithRSA", str, bArr2);
                    } else {
                        Account accountA = r9.a(CentralDcpAuthenticationMethod.this.b, str);
                        returnValueOrErrorSignCorpus = iRequestAuthenticationMethodAsInterface.signCorpus("SHA256WithRSA", accountA != null ? accountA.type : null, accountA != null ? accountA.name : null, bArr2);
                    }
                    ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod");
                    return returnValueOrErrorSignCorpus;
                }

                @Override // com.amazon.identity.auth.device.r4
                public void serviceDisconnected() {
                    Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "AuthenticatedRequestService is disconnected. It's probably crashed.");
                    e.a(s2Var, 1, "AuthenticatedRequestService is disconnected. It's probably crashed.");
                }

                @Override // com.amazon.identity.auth.device.r4
                public void useService(IBinder iBinder) {
                    deVarB.c();
                    de deVarB2 = yd.b("CentralDcpAuthenticationMethod", "signCorpusByDirectedIdWrapper");
                    try {
                        try {
                            try {
                                CentralDcpAuthenticationMethod.this.a(a(bArr, iBinder), s2Var, bArr, bundle);
                            } catch (RemoteException e) {
                                Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Cannot receive corpus authentication from dcp! Connection to DCP has been lost.", e);
                                e.a(s2Var, 1, "Cannot receive corpus authentication from dcp! Connection to DCP has been lost.");
                            }
                        } catch (RuntimeException e2) {
                            Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Unknown error during signCorpus execution.", e2);
                            e.a(s2Var, 5, e2.toString());
                        }
                    } finally {
                        deVarB2.c();
                        unbind();
                    }
                }
            }.call()) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.CentralDcpAuthenticationMethod"), "Failed to bind to AuthenticatedRequestService.");
                e.a(s2Var, 1, "Failed to bind to AuthenticatedRequestService.");
            }
        }
        return s2Var;
    }
}
