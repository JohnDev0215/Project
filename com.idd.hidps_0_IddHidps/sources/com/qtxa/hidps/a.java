package com.qtxa.hidps;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.qtxa.device.a;
import com.qtxa.qtxadefend.h;
import com.qtxa.qtxadefend.w;
import com.qtxa.receiver.HandlerModuleReceiver;
import com.qtxa.report.JniQtxaManager;
import com.qtxa.service.HandlerRequestService;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static a j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f55a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f56b;
    private boolean c;
    private com.qtxa.device.a d;
    private JniQtxaManager.RegisterCallback e = new C0009a();
    TimerTask f = new b();
    private Timer g = new Timer();
    int h = 0;
    private a.b i = new c();

    /* JADX INFO: renamed from: com.qtxa.hidps.a$a, reason: collision with other inner class name */
    class C0009a implements JniQtxaManager.RegisterCallback {

        /* JADX INFO: renamed from: com.qtxa.hidps.a$a$a, reason: collision with other inner class name */
        class RunnableC0010a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f58a;

            RunnableC0010a(int i) {
                this.f58a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f58a == 3) {
                    if (a.this.c) {
                        HandlerModuleReceiver.i(a.this.f55a);
                        a.this.c = false;
                        return;
                    }
                    return;
                }
                if (a.this.c) {
                    return;
                }
                HandlerModuleReceiver.h(a.this.f55a);
                a.this.c = true;
                a.this.f55a.startService(new Intent(a.this.f55a, (Class<?>) HandlerRequestService.class));
                h.g(a.this.f55a);
            }
        }

        C0009a() {
        }

        @Override // com.qtxa.report.JniQtxaManager.RegisterCallback
        public void callback(int i, String str, byte[] bArr) {
            w.d("register callback.... state::" + i + " describe:: " + str);
            a.this.f56b.post(new RunnableC0010a(i));
        }
    }

    class b extends TimerTask {
        b() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            a.this.j();
        }
    }

    class c implements a.b {

        /* JADX INFO: renamed from: com.qtxa.hidps.a$c$a, reason: collision with other inner class name */
        class RunnableC0011a implements Runnable {
            RunnableC0011a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar;
                int i;
                String vin = a.this.d.getVIN();
                String sn = a.this.d.getSN();
                w.d(Thread.currentThread().getName() + " vin : " + vin + " sn : " + sn);
                if ((TextUtils.isEmpty(sn) || TextUtils.isEmpty(vin)) && (i = (aVar = a.this).h) < 10) {
                    aVar.h = i + 1;
                    a.a.b.a.a().postDelayed(this, 1000L);
                } else {
                    JniQtxaManager.java_setVinSn(vin, sn);
                    JniQtxaManager.java_queryRegister(1, a.this.e);
                    a.this.g.schedule(a.this.f, 0L, 30000L);
                }
            }
        }

        c() {
        }

        @Override // com.qtxa.device.a.b
        public void a(String str) {
            w.d("onSuccess:: " + str);
            a.a.b.a.a().post(new RunnableC0011a());
        }
    }

    private a(Context context) {
        this.f55a = context;
    }

    public static a h(Context context) {
        if (j == null) {
            synchronized (a.class) {
                a aVar = new a(context);
                j = aVar;
                aVar.i();
            }
        }
        return j;
    }

    public void i() {
        w.d("QtHidpsStart1");
        HandlerModuleReceiver.a(this.f55a);
        this.f56b = new Handler(Looper.getMainLooper());
        this.d = com.qtxa.device.a.getInstance();
        w.d("QtHidpsStart2");
        this.d.init(this.f55a, this.i);
        w.d("vin : " + this.d.getVIN() + " sn : " + this.d.getSN());
        this.i.a("test");
        w.d("application onCreate......");
    }

    public void j() {
        String json = this.d.getJson();
        JniQtxaManager.java_setVinSn(this.d.getVIN(), this.d.getSN());
        JniQtxaManager.java_sendJson(json);
    }
}
