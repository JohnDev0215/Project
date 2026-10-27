package com.qtxa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import com.qtxa.qtxadefend.w;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class HandlerModuleReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f159a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f160b;
    private static int c;
    private static LinkedList<Class> d = new LinkedList<>();

    public static void a(Context context) {
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
            if (activityInfoArr == null) {
                w.d("findModule......error:" + d.size());
                return;
            }
            for (ActivityInfo activityInfo : activityInfoArr) {
                w.d("module : " + activityInfo.name);
                try {
                    Class<?> cls = Class.forName(activityInfo.name);
                    if (HandlerModuleReceiver.class.isAssignableFrom(cls) && !d.contains(cls)) {
                        d.add(cls);
                        w.d("add module : " + activityInfo.name);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int size = d.size();
            f160b = size;
            c = size;
            w.d("findModule......ok" + d.size());
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    private static void g(Context context, String str) {
        String string = UUID.randomUUID().toString();
        f159a = string;
        Intent intent = new Intent("com.qtxa.hidps.MODULE_CONTROL");
        intent.putExtra("action", str);
        intent.putExtra("token", string);
        w.d("sendOrderedBroadcast : " + d.size());
        Iterator<Class> it = d.iterator();
        int i = 0;
        while (it.hasNext()) {
            try {
                HandlerModuleReceiver handlerModuleReceiver = (HandlerModuleReceiver) it.next().newInstance();
                w.d("sendOrderedBroadcast to " + handlerModuleReceiver.getClass().getName() + " : " + str + " " + i + "  " + d.size());
                handlerModuleReceiver.onReceive(context, intent);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                w.d("sendOrderedBroadcast to " + e.toString() + "  " + i);
            }
        }
    }

    public static void h(Context context) {
        g(context, "com.qtxa.hidps.module.start");
        w.d("startModule......");
    }

    public static void i(Context context) {
        g(context, "com.qtxa.hidps.module.stop");
        w.d("stopModule......");
    }

    public void b(Context context, Intent intent) {
    }

    public void c(Context context, Intent intent) {
    }

    public abstract void d(Context context, Intent intent);

    public abstract void e(Context context, Intent intent);

    public void f(Context context, Intent intent) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("token");
        String stringExtra2 = intent.getStringExtra("action");
        w.d("HandlerModuleReceiver$" + getClass().getName() + ":: " + stringExtra2 + " total : " + f160b);
        if ("com.qtxa.hidps.module.find".equals(stringExtra2)) {
            int i = f160b + 1;
            f160b = i;
            c = i;
            return;
        }
        if (stringExtra == null || !stringExtra.equals(f159a)) {
            w.d("HandlerModuleReceiver:: token " + stringExtra + " cter " + f159a);
            return;
        }
        int i2 = c - 1;
        c = i2;
        if (i2 <= 0) {
            f159a = null;
            c = f160b;
        }
        if ("com.qtxa.hidps.module.start".equals(stringExtra2)) {
            d(context, intent);
            return;
        }
        if ("com.qtxa.hidps.module.stop".equals(stringExtra2)) {
            e(context, intent);
            return;
        }
        if ("com.qtxa.hidps.module.wait".equals(stringExtra2)) {
            f(context, intent);
        } else if ("com.qtxa.hidps.module.ids".equals(stringExtra2)) {
            b(context, intent);
        } else if ("com.qtxa.hidps.module.ips".equals(stringExtra2)) {
            c(context, intent);
        }
    }
}
