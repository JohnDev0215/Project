package io.sentry.android.core;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class SystemEventsBreadcrumbsIntegration implements io.sentry.Z, Closeable {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c0 f16107m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SentryAndroidOptions f16108n;
    public final ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16109p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f16110q;

    public SystemEventsBreadcrumbsIntegration(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("android.appwidget.action.APPWIDGET_DELETED");
        arrayList.add("android.appwidget.action.APPWIDGET_DISABLED");
        arrayList.add("android.appwidget.action.APPWIDGET_ENABLED");
        arrayList.add("android.appwidget.action.APPWIDGET_HOST_RESTORED");
        arrayList.add("android.appwidget.action.APPWIDGET_RESTORED");
        arrayList.add("android.appwidget.action.APPWIDGET_UPDATE");
        arrayList.add("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS");
        arrayList.add("android.intent.action.ACTION_POWER_CONNECTED");
        arrayList.add("android.intent.action.ACTION_POWER_DISCONNECTED");
        arrayList.add("android.intent.action.ACTION_SHUTDOWN");
        arrayList.add("android.intent.action.AIRPLANE_MODE");
        arrayList.add("android.intent.action.BATTERY_LOW");
        arrayList.add("android.intent.action.BATTERY_OKAY");
        arrayList.add("android.intent.action.BATTERY_CHANGED");
        arrayList.add("android.intent.action.BOOT_COMPLETED");
        arrayList.add("android.intent.action.CAMERA_BUTTON");
        arrayList.add("android.intent.action.CONFIGURATION_CHANGED");
        arrayList.add("android.intent.action.CONTENT_CHANGED");
        arrayList.add("android.intent.action.DATE_CHANGED");
        arrayList.add("android.intent.action.DEVICE_STORAGE_LOW");
        arrayList.add("android.intent.action.DEVICE_STORAGE_OK");
        arrayList.add("android.intent.action.DOCK_EVENT");
        arrayList.add("android.intent.action.DREAMING_STARTED");
        arrayList.add("android.intent.action.DREAMING_STOPPED");
        arrayList.add("android.intent.action.INPUT_METHOD_CHANGED");
        arrayList.add("android.intent.action.LOCALE_CHANGED");
        arrayList.add("android.intent.action.REBOOT");
        arrayList.add("android.intent.action.SCREEN_OFF");
        arrayList.add("android.intent.action.SCREEN_ON");
        arrayList.add("android.intent.action.TIMEZONE_CHANGED");
        arrayList.add("android.intent.action.TIME_SET");
        arrayList.add("android.os.action.DEVICE_IDLE_MODE_CHANGED");
        arrayList.add("android.os.action.POWER_SAVE_MODE_CHANGED");
        arrayList.add("android.intent.action.APP_ERROR");
        arrayList.add("android.intent.action.BUG_REPORT");
        arrayList.add("android.intent.action.MEDIA_BAD_REMOVAL");
        arrayList.add("android.intent.action.MEDIA_MOUNTED");
        arrayList.add("android.intent.action.MEDIA_UNMOUNTABLE");
        arrayList.add("android.intent.action.MEDIA_UNMOUNTED");
        this.f16109p = false;
        this.f16110q = new Object();
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        this.o = arrayList;
    }

    public final void b(SentryAndroidOptions sentryAndroidOptions) {
        this.f16107m = new c0(sentryAndroidOptions);
        IntentFilter intentFilter = new IntentFilter();
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            intentFilter.addAction((String) it.next());
        }
        try {
            Context context = this.l;
            c0 c0Var = this.f16107m;
            h6.e.q(sentryAndroidOptions.getLogger(), "The ILogger object is required.");
            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver(c0Var, intentFilter, 2);
            } else {
                context.registerReceiver(c0Var, intentFilter);
            }
            sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "SystemEventsBreadcrumbsIntegration installed.", new Object[0]);
            n7.z.c("SystemEventsBreadcrumbs");
        } catch (Throwable th) {
            sentryAndroidOptions.setEnableSystemEventBreadcrumbs(false);
            sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed to initialize SystemEventsBreadcrumbsIntegration.", th);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f16110q) {
            this.f16109p = true;
        }
        c0 c0Var = this.f16107m;
        if (c0Var != null) {
            this.l.unregisterReceiver(c0Var);
            this.f16107m = null;
            SentryAndroidOptions sentryAndroidOptions = this.f16108n;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "SystemEventsBreadcrumbsIntegration remove.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16108n = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "SystemEventsBreadcrumbsIntegration enabled: %s", Boolean.valueOf(this.f16108n.isEnableSystemEventBreadcrumbs()));
        if (this.f16108n.isEnableSystemEventBreadcrumbs()) {
            try {
                f12.getExecutorService().submit(new r(this, 3, f12));
            } catch (Throwable th) {
                f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to start SystemEventsBreadcrumbsIntegration on executor thread.", th);
            }
        }
    }
}
