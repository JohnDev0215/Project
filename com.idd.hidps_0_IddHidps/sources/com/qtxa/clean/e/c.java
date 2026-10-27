package com.qtxa.clean.e;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.qtxa.clean.e.d;
import com.qtxa.clean.sysmonitor.SysMonitor;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class c implements d.c {
    private static c h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d f35b;
    private ActivityManager c;
    private Context g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    List<com.qtxa.clean.a> f34a = new ArrayList();
    private HandlerThread d = null;
    private Handler e = null;
    private SysMonitor f = null;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1 || i == 2) {
                c.this.f(i, (String) message.obj);
            } else {
                if (i != 4096) {
                    return;
                }
                c.this.h(message.arg1);
            }
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f37a;

        static {
            int[] iArr = new int[SysMonitor.SysResourceStatus.values().length];
            f37a = iArr;
            try {
                iArr[SysMonitor.SysResourceStatus.LOW_CPU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f37a[SysMonitor.SysResourceStatus.LOW_MEMORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private c(Context context) {
        this.f35b = null;
        this.g = null;
        Context applicationContext = context.getApplicationContext();
        this.g = applicationContext;
        this.c = (ActivityManager) applicationContext.getSystemService("activity");
        j();
        this.f35b = new d(this.g, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i, String str) {
        boolean zT;
        k("cleanProcessBackground...");
        if (this.f == null) {
            this.f = SysMonitor.h(this.g);
        }
        boolean z = false;
        while (true) {
            zT = this.f.t();
            if (!zT || z) {
                break;
            }
            int i2 = b.f37a[this.f.u().ordinal()];
            if (i2 == 1) {
                i = 2;
            } else if (i2 != 2) {
                z = true;
            } else {
                z = false;
                i = 1;
            }
            if (!z) {
                boolean zG = false;
                for (com.qtxa.clean.e.b bVar : this.f35b.c(this.f.i(), i)) {
                    zG |= g(bVar, false);
                    this.f35b.h(bVar);
                    if (zG) {
                        break;
                    }
                }
                if (!zG) {
                    z = true;
                }
                n();
            }
        }
        k("cleanProcessBackground done: blocking ? " + zT + ", " + z);
    }

    private boolean g(com.qtxa.clean.e.b bVar, boolean z) {
        if (bVar.k()) {
            l("stop package:" + bVar.d());
            this.c.forceStopPackage(bVar.d());
            return true;
        }
        String strH = bVar.h();
        l(strH + " process:" + bVar.f());
        return d.g(bVar.e(), strH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i) {
    }

    public static c i(Context context) {
        if (h == null) {
            synchronized (c.class) {
                h = new c(context);
            }
        }
        return h;
    }

    private void j() {
        HandlerThread handlerThread = new HandlerThread("procclean");
        this.d = handlerThread;
        handlerThread.start();
        this.e = new a(this.d.getLooper());
    }

    public static void k(String str) {
        com.qtxa.hidps.b.b.b("ProcessClean", str);
    }

    public static void l(String str) {
        com.qtxa.hidps.b.b.d("ProcessClean", str);
    }

    private void n() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (System.currentTimeMillis() - jCurrentTimeMillis < 60000) {
            try {
                Thread.sleep(1000L);
            } catch (Throwable th) {
                th.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // com.qtxa.clean.e.d.c
    public int a(String str) {
        int iC = com.qtxa.clean.sysmonitor.c.c(str);
        int i = iC > 1024 ? iC / 1024 : 0;
        k("getMemOfProcess> " + str + ": " + iC + ", " + i);
        return i;
    }

    @Override // com.qtxa.clean.e.d.c
    public int b(String str) {
        double dA = com.qtxa.clean.sysmonitor.c.a(str);
        k("getCpuOfProcess> " + str + ": " + dA);
        return (int) dA;
    }

    public void e(com.qtxa.clean.a aVar) {
        if (aVar == null || this.f34a.contains(aVar)) {
            return;
        }
        this.f34a.add(aVar);
    }

    public Handler m(int i, int i2) {
        if (i == 4096) {
            this.e.removeMessages(4096);
            Message messageObtain = Message.obtain();
            messageObtain.what = 4096;
            messageObtain.arg1 = i2;
            this.e.sendMessageDelayed(messageObtain, 100L);
        }
        return this.e;
    }
}
