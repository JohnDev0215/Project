package androidx.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.util.Log;
import java.util.List;
import java.util.Objects;
import p.cg1;
import p.ch1;
import p.gu0;
import p.rz;
import p.sz;
import p.tf1;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public class MediaButtonReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int a = 0;

    public static ComponentName a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices.size() == 1) {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            return new ComponentName(serviceInfo.packageName, serviceInfo.name);
        }
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        StringBuilder sbP = u4.p("Expected 1 service that handles ", str, ", found ");
        sbP.append(listQueryIntentServices.size());
        throw new IllegalStateException(sbP.toString());
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Objects.toString(intent);
            return;
        }
        ComponentName componentNameA = a(context, "android.intent.action.MEDIA_BUTTON");
        if (componentNameA != null) {
            intent.setComponent(componentNameA);
            try {
                Object obj = sz.a;
                rz.b(context, intent);
                return;
            } catch (IllegalStateException e) {
                if (Build.VERSION.SDK_INT < 31 || !ch1.b(e)) {
                    throw e;
                }
                Log.e("MediaButtonReceiver", "caught exception when trying to start a foreground service from the background: " + ch1.a(e).getMessage());
                return;
            }
        }
        ComponentName componentNameA2 = a(context, "android.media.browse.MediaBrowserService");
        if (componentNameA2 == null) {
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
        BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        Context applicationContext = context.getApplicationContext();
        gu0 gu0Var = new gu0();
        gu0Var.a = new tf1(gu0Var);
        gu0Var.c = applicationContext;
        gu0Var.d = intent;
        gu0Var.e = pendingResultGoAsync;
        cg1 cg1Var = new cg1(applicationContext, componentNameA2, gu0Var);
        gu0Var.f = cg1Var;
        cg1Var.a.b.connect();
    }
}
