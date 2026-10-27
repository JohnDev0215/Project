package com.amazon.identity.auth.accounts;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.f0;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.g1;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.j5;
import com.amazon.identity.auth.device.la;
import com.amazon.identity.auth.device.n0;
import com.amazon.identity.auth.device.t2;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.y;
import com.amazon.identity.auth.device.y5;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class RegisterChildApplicationAction {
    public static final String c = "com.amazon.identity.auth.accounts.RegisterChildApplicationAction";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f148a;
    public final t2 b = new t2();

    /* JADX INFO: compiled from: DCP */
    public static class NotChildApplicationException extends Exception {
        private static final long serialVersionUID = 1;
    }

    /* JADX INFO: compiled from: DCP */
    public static class a implements ISubAuthenticatorResponse {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Callback f149a;

        public a(Callback callback) {
            this.f149a = callback;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            throw new UnsupportedOperationException("asBinder is not supported in SubAuthenticatorCallbackAdapter");
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
        public void onError(int i, String str) throws RemoteException {
            y.a(this.f149a, MAPError.CommonError.INTERNAL_ERROR, str, i, str, null);
        }

        @Override // com.amazon.dcp.sso.ISubAuthenticatorResponse
        public void onResult(Bundle bundle) throws RemoteException {
            y.a(this.f149a, bundle);
        }
    }

    public RegisterChildApplicationAction(Context context) {
        this.f148a = t5.a(context);
        new HashMap();
    }

    public void a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) throws NotChildApplicationException {
        Long l;
        if (!f0.a(this.f148a, str2)) {
            throw new NotChildApplicationException();
        }
        Callback callbackA = this.b.a(str2, callback);
        if (callbackA == null) {
            Log.i(ga.a(c), String.format("Register child app request already in flight for device type %s", str2));
            return;
        }
        a aVar = new a(callbackA);
        String string = e.a(bundle).getString("override_dsn");
        String str3 = null;
        Long l2 = null;
        for (j5 j5Var : (ArrayList) MAPApplicationInformationQueryer.a(this.f148a).a()) {
            try {
                if (TextUtils.equals(j5Var.c(), str2)) {
                    synchronized (j5Var) {
                        if (j5Var.i == null) {
                            j5Var.i = la.a(j5Var.f392a, j5Var.b);
                        }
                        l = j5Var.i;
                    }
                    if (l != null && (l2 == null || l2.longValue() < l.longValue())) {
                        try {
                            str3 = j5Var.b;
                            l2 = l;
                        } catch (RemoteMAPException e) {
                            e = e;
                            l2 = l;
                            Log.w(ga.a(c), "Couldn't determine device type for " + j5Var.b, e);
                        }
                    }
                } else {
                    continue;
                }
            } catch (RemoteMAPException e2) {
                e = e2;
            }
        }
        String str4 = c;
        Object[] objArr = new Object[3];
        objArr[0] = str2;
        objArr[1] = l2 != null ? Long.toString(l2.longValue()) : "None";
        objArr[2] = str3;
        Log.i(ga.a(str4), String.format("Registering child application with device type %s, version %s, and component id %s", objArr));
        n0 n0Var = new n0(this.f148a, str3, str2, string, l2, true);
        String str5 = n0Var.i;
        if (TextUtils.equals(str5, "A1PY8QQU9P0WJV") || TextUtils.equals(str5, "A17I2SKGZYX7FH") || TextUtils.equals(str5, "A1MPSLFC7L5AFK")) {
            Log.e(ga.a(n0.A), String.format("An Attempt to register an invalid child device type: %s. This is due to wrong integration with MAP.", n0Var.i));
            try {
                aVar.onError(MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "Child Application registration failed due to invalid child device type. This is due to wrong integration with MAP.");
                return;
            } catch (RemoteException unused) {
                Log.e(ga.a(n0.A), "RemoteException on callback error for account not existing.");
                return;
            }
        }
        if (!n0Var.b.a(str)) {
            Log.e(ga.a(n0.A), "An Attempt to register a child device type for a non-existant amazon account. This can happen if the device has been deregistered during this flow.");
            try {
                aVar.onError(MAPAccountManager.RegistrationError.NO_ACCOUNT.value(), "Child Application registration failed due to account not being registered on the device. This can happen if the device has been deregistered during this flow.");
                return;
            } catch (RemoteException unused2) {
                Log.e(ga.a(n0.A), "RemoteException on callback error for account not existing.");
                return;
            }
        }
        if (!f0.a(n0Var.f442a, n0Var.d, str, n0Var.i)) {
            n0Var.a(aVar, new g1(n0Var.f442a), str, y5Var);
        } else {
            Log.i(ga.a(n0.A), String.format("Child Application device type %s is already registered", n0Var.i));
            n0Var.a(aVar);
        }
    }
}
