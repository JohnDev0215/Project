package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;

/* JADX INFO: compiled from: GSAAccountChangeListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class yr0 {
    public int a;
    public as0 b;
    public boolean c;

    /* JADX INFO: compiled from: GSAAccountChangeListener.java */
    public class a extends BroadcastReceiver {
        public final /* synthetic */ a8 a;

        public a(a8 a8Var) {
            this.a = a8Var;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            Uri data = intent.getData();
            if (data != null && "com.google.android.googlequicksearchbox".equals(data.getEncodedSchemeSpecificPart())) {
                Context applicationContext = context.getApplicationContext();
                bs0.a().d();
                yr0 yr0Var = yr0.this;
                yr0Var.b = null;
                as0 as0Var = new as0(applicationContext, new zr0(yr0Var), this.a);
                yr0Var.b = as0Var;
                as0Var.a();
            }
        }
    }

    /* JADX INFO: compiled from: GSAAccountChangeListener.java */
    public static class b extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("com.google.android.apps.now.account_update_broadcast".equals(intent.getAction())) {
                intent.getStringExtra("account_name");
                oj2.U(1, 2, "Search.GsaAccountChangeNotificationSource");
                bs0.a().d();
            }
        }
    }

    public yr0(Context context, a8 a8Var) {
        Context applicationContext = context.getApplicationContext();
        applicationContext.registerReceiver(new b(), new IntentFilter("com.google.android.apps.now.account_update_broadcast"), "com.google.android.apps.now.CURRENT_ACCOUNT_ACCESS", null, 2);
        as0 as0Var = new as0(applicationContext, new zr0(this), a8Var);
        this.b = as0Var;
        as0Var.a();
        a aVar = new a(a8Var);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        xz.g(context, aVar, intentFilter);
    }
}
