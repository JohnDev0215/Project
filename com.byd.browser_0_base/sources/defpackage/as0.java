package defpackage;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: GSAServiceClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class as0 {
    public final Messenger a;
    public final b b;
    public final a c;
    public final a8 d;
    public final Context e;
    public final Callback<Bundle> f;
    public Messenger g;

    /* JADX INFO: compiled from: GSAServiceClient.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            as0 as0Var = as0.this;
            if (as0Var.e == null) {
                return;
            }
            as0Var.g = new Messenger(iBinder);
            try {
                Message messageObtain = Message.obtain((Handler) null, 2);
                messageObtain.replyTo = as0Var.a;
                as0Var.d.getClass();
                Bundle bundle = new Bundle();
                bundle.putString("ssb_service:ssb_package_name", as0Var.e.getPackageName());
                bundle.putBoolean("ssb_service:chrome_holds_account_update_permission", c6.a(Process.myPid(), Process.myUid(), xz.a, "com.google.android.apps.now.CURRENT_ACCOUNT_ACCESS") == 0);
                messageObtain.setData(bundle);
                as0Var.g.send(messageObtain);
            } catch (RemoteException e) {
                Log.w("GSAServiceConnection", "GSAServiceConnection - remote call failed", e);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            as0.this.g = null;
        }
    }

    /* JADX INFO: compiled from: GSAServiceClient.java */
    @SuppressLint({"HandlerLeak"})
    public class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 3) {
                super.handleMessage(message);
                return;
            }
            as0 as0Var = as0.this;
            if (as0Var.g == null) {
                return;
            }
            Bundle bundle = (Bundle) message.obj;
            bundle.getByteArray("ssb_service:ssb_state");
            as0Var.d.getClass();
            oj2.U(0, 2, "Search.GsaAccountChangeNotificationSource");
            bs0.a().d();
            Callback<Bundle> callback = as0Var.f;
            if (callback != null) {
                callback.onResult(bundle);
            }
        }
    }

    public as0(Context context, zr0 zr0Var, a8 a8Var) {
        this.e = context.getApplicationContext();
        this.f = zr0Var;
        b bVar = new b();
        this.b = bVar;
        this.a = new Messenger(bVar);
        this.c = new a();
        this.d = a8Var;
    }

    public final void a() {
        if (this.g != null) {
            Log.e("GSAServiceClient", "Already connected.");
        }
        this.e.bindService(new Intent("com.google.android.ssb.action.SSB_SERVICE").setPackage("com.google.android.googlequicksearchbox"), this.c, 5);
    }
}
