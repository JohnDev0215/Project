package com.amazon.identity.auth.accounts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.amazon.dcp.sso.ISubAuthenticator;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.j1;
import com.amazon.identity.auth.device.q0;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class SubAuthenticatorConnection {
    public static final String i = "com.amazon.identity.auth.accounts.SubAuthenticatorConnection";
    public static final long j = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j1 f153a;
    public final Context b;
    public CurrentState d;
    public d e;
    public ISubAuthenticator f;
    public boolean g;
    public ServiceConnection c = new a();
    public Object h = new Object[0];

    /* JADX INFO: compiled from: DCP */
    public enum CurrentState {
        Unbound,
        Binding,
        Bound
    }

    /* JADX INFO: compiled from: DCP */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            d dVar;
            SubAuthenticatorConnection.this.g = true;
            synchronized (SubAuthenticatorConnection.this.h) {
                SubAuthenticatorConnection subAuthenticatorConnection = SubAuthenticatorConnection.this;
                subAuthenticatorConnection.d = CurrentState.Bound;
                subAuthenticatorConnection.f = ISubAuthenticator.Stub.asInterface(iBinder);
                SubAuthenticatorConnection subAuthenticatorConnection2 = SubAuthenticatorConnection.this;
                dVar = subAuthenticatorConnection2.e;
                String str = SubAuthenticatorConnection.i;
                String.format("Connected to SubAuthenticator in package %s.", subAuthenticatorConnection2.f153a.f390a);
                ga.a(str);
            }
            if (dVar != null) {
                q0.h hVar = (q0.h) dVar;
                hVar.e.set(true);
                hVar.asyncOperationComplete();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            d dVar;
            SubAuthenticatorConnection subAuthenticatorConnection = SubAuthenticatorConnection.this;
            subAuthenticatorConnection.c = null;
            synchronized (subAuthenticatorConnection.h) {
                SubAuthenticatorConnection subAuthenticatorConnection2 = SubAuthenticatorConnection.this;
                subAuthenticatorConnection2.d = CurrentState.Unbound;
                dVar = subAuthenticatorConnection2.e;
                subAuthenticatorConnection2.f = null;
                String str = SubAuthenticatorConnection.i;
                String.format("Disconnected from SubAuthenticator in package %s.", subAuthenticatorConnection2.f153a.f390a);
                ga.a(str);
            }
            if (dVar != null) {
                SubAuthenticatorConnection subAuthenticatorConnection3 = SubAuthenticatorConnection.this;
                String str2 = q0.f;
                String str3 = subAuthenticatorConnection3.f153a.f390a;
                ga.a(str2);
                ((q0.h) dVar).e.set(false);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SubAuthenticatorConnection.this.g) {
                return;
            }
            String str = SubAuthenticatorConnection.i;
            Log.e(ga.a(str), String.format("Application tried to bind to SubAuthenticator but Service timed out.", new Object[0]));
            q0.h hVar = (q0.h) SubAuthenticatorConnection.this.e;
            hVar.getClass();
            Log.e(ga.a(q0.f), "SubAuth Connection timeout");
            hVar.e.set(false);
            hVar.asyncOperationComplete();
            SubAuthenticatorConnection.this.a();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface c {
    }

    /* JADX INFO: compiled from: DCP */
    public interface d {
    }

    public SubAuthenticatorConnection(j1 j1Var, Context context) {
        if (j1Var == null) {
            throw new IllegalArgumentException("SubAuthenticatorDescription cannot be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.f153a = j1Var;
        this.b = context;
        this.d = CurrentState.Unbound;
        this.g = false;
    }

    public boolean a(d dVar) {
        boolean zBindService;
        synchronized (this.h) {
            if (this.d != CurrentState.Unbound) {
                throw new IllegalStateException("Cannot open a connection to the service because we are currently connecting or have already connected to the service.");
            }
            if (this.c == null) {
                throw new IllegalStateException("Attempted to reuse a SubAuthenticatorConnection.  openConnection can only be executed once.");
            }
            this.d = CurrentState.Binding;
            this.e = dVar;
            Intent intent = new Intent("com.amazon.dcp.sso.AccountSubAuthenticator");
            j1 j1Var = this.f153a;
            intent.setComponent(j1Var.b == null ? null : new ComponentName(j1Var.f390a, j1Var.b));
            try {
                zBindService = this.b.bindService(intent, this.c, 5);
            } catch (SecurityException e) {
                Log.e(ga.a(i), String.format("Unable to talk to package because of SecurityException : %s", e.getMessage()));
                zBindService = false;
            }
            if (zBindService) {
                new Handler(Looper.getMainLooper()).postDelayed(new b(), j);
                return zBindService;
            }
            this.d = CurrentState.Unbound;
            Log.e(ga.a(i), String.format("Application tried to bind to SubAuthenticator Service and failed.", new Object[0]));
            return false;
        }
    }

    public void a() {
        synchronized (this.h) {
            if (this.d != CurrentState.Bound) {
                Log.e(ga.a(i), "Cannot close the connection because it was not connected");
                return;
            }
            ServiceConnection serviceConnection = this.c;
            if (serviceConnection != null) {
                try {
                    this.b.unbindService(serviceConnection);
                } catch (IllegalArgumentException unused) {
                    String str = i;
                    Log.w(ga.a(str), String.format("IllegalArgumentException is received during unbinding from Subauthenticator package, Ignored.", new Object[0]));
                }
                this.c = null;
            }
            this.d = CurrentState.Unbound;
        }
    }

    public final void a(c cVar) {
        if (cVar == null) {
            return;
        }
        Context context = this.b;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("ErrorConnectingToSubAuth", "string", context.getPackageName());
        if (identifier != 0) {
            ((q0.i) cVar).a(this, -1, String.format(resources.getString(identifier), this.f153a.f390a));
        } else {
            Log.w(ga.a(ResourceHelper.f620a), String.format("The String resource %s has not been found", "ErrorConnectingToSubAuth"));
            throw new ResourceHelper.ResourceNotFoundException(String.format("String Resource %s not found", "ErrorConnectingToSubAuth"));
        }
    }
}
