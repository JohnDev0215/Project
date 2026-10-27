package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n2 {
    public static final String k = "com.amazon.identity.auth.device.n2";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f447a;
    public final Callback b;
    public final ArrayList<String> h;
    public final HashSet<String> i;
    public final ServiceConnection j = new a();
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final Intent c = new Intent("com.amazon.identity.action.BOOTSTRAP_SSO");
    public final Timer d = new Timer(n2.class.getSimpleName());
    public final AtomicBoolean f = new AtomicBoolean(false);
    public final ExecutorService g = Executors.newFixedThreadPool(1);

    /* JADX INFO: compiled from: DCP */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            n2.this.f.set(true);
            n2 n2Var = n2.this;
            n2Var.g.execute(new p2(n2Var, IBootstrapSSOService.Stub.asInterface(iBinder)));
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            n2.this.f.set(false);
            Log.e(ga.a(n2.k), "Unexpectedly disconnected from service");
            n2.this.a(MAPAccountManager.BootstrapError.SERVICE_ERROR, "Unexpectedly disconnected from service");
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b extends TimerTask {
        public b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            n2.this.a(MAPAccountManager.BootstrapError.TIMEOUT_SERVICE, "No response received from the service");
        }
    }

    public n2(Context context, Bundle bundle, Callback callback) {
        this.f447a = context;
        this.b = callback;
        this.h = bundle.getStringArrayList(MAPAccountManager.KEY_SSO_REQUIRED_ADDITIONAL_DATA);
        Serializable serializable = bundle.getSerializable(MAPAccountManager.KEY_SSO_ALLOWED_HOST_APPS);
        this.i = serializable instanceof HashSet ? (HashSet) serializable : null;
    }

    public final void a(MAPAccountManager.BootstrapError bootstrapError, String str) {
        if (this.f.getAndSet(false)) {
            this.f447a.unbindService(this.j);
        }
        if (this.e.getAndSet(true)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("com.amazon.dcp.sso.ErrorCode", bootstrapError.value());
        bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
        this.b.onError(bundle);
    }
}
