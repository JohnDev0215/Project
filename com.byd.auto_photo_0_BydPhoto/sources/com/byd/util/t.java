package com.byd.util;

import android.car.Car;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class t {
    public static void a(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
        try {
            Method declaredMethod = powerManager.getClass().getDeclaredMethod("turnBacklightOn", Long.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(powerManager, Long.valueOf(SystemClock.uptimeMillis()));
            w.c("photo turnBacklightOn !");
        } catch (Exception e) {
            e.printStackTrace();
            w.c("turnBacklightOn exception : " + e.getMessage());
        }
    }

    public static void b(Context context) {
        String str;
        String str2;
        v vVarD;
        String str3;
        String str4;
        try {
            int iP = com.byd.share.a.s(context).p();
            boolean zA = e.A();
            if (zA && iP == 2) {
                str = "com.byd.auto.screensaver";
                str2 = "com.byd.auto.screensaver.CLOSE_AUTO_SCREEN_SAVER";
                vVarD = v.d();
                str3 = "sys.isinaudioscreen";
            } else {
                str = "com.byd.screensaver";
                str2 = "CLOSE_SCREEN_CLOCK";
                vVarD = v.d();
                str3 = "sys.isinscrclock";
            }
            boolean zC = vVarD.c(str3, false);
            w.c("isinscrclock = " + zC);
            if (zC) {
                Intent intent = new Intent(str2);
                intent.setPackage(str);
                context.getApplicationContext().sendBroadcast(intent);
                str4 = "0 -> sendBroadcast " + str2;
            } else {
                context.getApplicationContext().sendBroadcast(new Intent("PIC_CLOSE_SCREEN_CLOCK"));
                str4 = "1 -> sendBroadcast PIC_CLOSE_SCREEN_CLOCK";
            }
            w.c(str4);
            if (zA) {
                context.getApplicationContext().sendBroadcast(new Intent("android.intent.action.CLOSE_AUDIO_SCREEN"));
                w.c("2 -> sendBroadcast android.intent.action.CLOSE_AUDIO_SCREEN");
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.c("closeLock Exception" + e.getMessage());
        }
    }
}
