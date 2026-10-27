package com.byd.secretcode.study;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.SystemProperties;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.application.BydApplication;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class SecretCodeApplication extends BydApplication {
    public static Timer b = new Timer();
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    public static boolean f = false;

    public class a extends TimerTask {
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BydLogHelper.logW("SecretCodeApplication", "startTimer set sbr.audio.log.rt 0 ");
            SystemProperties.set("sbr.audio.log.rt", "0");
        }
    }

    public static void c(long j) {
        Timer timer = b;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        b = timer2;
        timer2.schedule(new a(), j);
    }

    public static void d() {
        Timer timer = b;
        if (timer != null) {
            timer.cancel();
        }
    }

    public boolean a(int i) {
        BydLogHelper.logD("TAG", "getQxdmLogStatus type = " + i + ",locationLog = " + c + ",audioLog=" + d + ",networkLog=" + e + ",ecallLog=" + f);
        if (i == 0) {
            return c;
        }
        if (i == 1) {
            return d;
        }
        if (i == 2) {
            return e;
        }
        if (i == 3) {
            return f;
        }
        return false;
    }

    public void b(boolean z, int i) {
        if (i == 0) {
            c = z;
            return;
        }
        if (i == 1) {
            d = z;
        } else if (i == 2) {
            e = z;
        } else if (i == 3) {
            f = z;
        }
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD("SecretCodeApplication", "onCreate");
        BYDAutoTestDevice.getInstance(getApplicationContext());
        BYDAutoBodyworkDevice.getInstance(getApplicationContext());
        SystemProperties.set("sbr.audio.log.rt", "0");
    }
}
