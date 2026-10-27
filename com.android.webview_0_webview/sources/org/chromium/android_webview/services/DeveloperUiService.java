package org.chromium.android_webview.services;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.IBinder;
import defpackage.AbstractBinderC0357Tq;
import defpackage.AbstractC0214Lq;
import defpackage.AbstractC1077kw;
import defpackage.BinderC1073ks;
import defpackage.C0142Hq;
import defpackage.Es;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DeveloperUiService extends Service {
    public static final Object u = new Object();
    public static Map v = new HashMap();
    public static final Map w = Es.e().g();
    public boolean s;
    public final AbstractBinderC0357Tq t = new BinderC1073ks(this);

    public static void a(DeveloperUiService developerUiService, Map map, Map map2) {
        Objects.requireNonNull(developerUiService);
        for (String str : map.keySet()) {
            if (w.containsKey(str)) {
                Es.e().b(str, (String) w.get(str));
            } else if (Es.e().h(str)) {
                Es.e().k(str);
            }
        }
        new C0142Hq(AbstractC0214Lq.a).a(map2);
    }

    public static void b(DeveloperUiService developerUiService) {
        Objects.requireNonNull(developerUiService);
        synchronized (u) {
            if (developerUiService.s) {
                developerUiService.s = false;
                developerUiService.getPackageManager().setComponentEnabledSetting(new ComponentName(developerUiService, "org.chromium.android_webview.devui.DeveloperModeState"), 0, 1);
                developerUiService.stopForeground(true);
                developerUiService.stopSelf();
            }
        }
    }

    public static void c(DeveloperUiService developerUiService) {
        Objects.requireNonNull(developerUiService);
        synchronized (u) {
            if (developerUiService.s) {
                return;
            }
            developerUiService.startService(new Intent(developerUiService, (Class<?>) DeveloperUiService.class));
            developerUiService.d();
            developerUiService.getPackageManager().setComponentEnabledSetting(new ComponentName(developerUiService, "org.chromium.android_webview.devui.DeveloperModeState"), 1, 1);
            developerUiService.s = true;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration configuration) {
        AbstractC1077kw.c();
        return super.createConfigurationContext(configuration);
    }

    public final void d() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("DevUiChannel", "WebView DevTools alerts", 2);
            notificationChannel.enableVibration(false);
            notificationChannel.enableLights(false);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), "org.chromium.android_webview.devui.MainActivity");
        intent.putExtra("fragment-id", 2);
        Notification.Builder ticker = (i >= 26 ? new Notification.Builder(this, "DevUiChannel") : new Notification.Builder(this)).setContentTitle("WARNING: experimental WebView features enabled").setContentText("Tap to see experimental features.").setSmallIcon(R.drawable.stat_notify_error).setContentIntent(PendingIntent.getActivity(this, 0, intent, 0)).setOngoing(true).setVisibility(1).setTicker("Experimental WebView features enabled");
        if (i < 26) {
            ticker = ticker.setDefaults(0).setPriority(-1);
        }
        startForeground(1, ticker.build());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        AbstractC1077kw.c();
        return super.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AbstractC1077kw.c();
        return super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        AbstractC1077kw.c();
        return super.getTheme();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.t;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
