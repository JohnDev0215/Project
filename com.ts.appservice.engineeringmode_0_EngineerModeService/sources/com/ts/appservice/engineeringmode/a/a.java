package com.ts.appservice.engineeringmode.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteCallbackList;
import com.ts.lib.engineeringmode.IEngineeringSdkCallBack;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    RemoteCallbackList<IEngineeringSdkCallBack> f15a = new RemoteCallbackList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f16b;

    /* JADX INFO: renamed from: com.ts.appservice.engineeringmode.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class RunnableC0003a implements Runnable {
        RunnableC0003a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onSynchronousQNXData(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Bundle f18a;

        b(Bundle bundle) {
            this.f18a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onHwTemperature(this.f18a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f20a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f21b;

        c(int i, boolean z) {
            this.f20a = i;
            this.f21b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onQnxLogStatusChanged(this.f20a, this.f21b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f22a;

        d(boolean z) {
            this.f22a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onBinderDiag(this.f22a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f24a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f25b;

        e(String str, String str2) {
            this.f24a = str;
            this.f25b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onObdReport(this.f24a, this.f25b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f26a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f27b;

        f(int i, int i2) {
            this.f26a = i;
            this.f27b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onTsObdReport(this.f26a, this.f27b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f28a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f29b;

        g(int i, int i2) {
            this.f28a = i;
            this.f29b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onGetOffScreenFlagResult(this.f28a, this.f29b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f30a;

        h(int i) {
            this.f30a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int iBeginBroadcast = a.this.f15a.beginBroadcast();
            for (int i = 0; i < iBeginBroadcast; i++) {
                try {
                    a.this.f15a.getBroadcastItem(i).onEnergyTestChange(this.f30a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            a.this.f15a.finishBroadcast();
        }
    }

    public a() {
        HandlerThread handlerThread = new HandlerThread("com.ts.appservice.engineeringmodeEngineerCallBack");
        handlerThread.start();
        this.f16b = new Handler(handlerThread.getLooper());
    }

    public void a(Bundle bundle) {
        this.f16b.post(new b(bundle));
    }

    public void b() {
        this.f16b.post(new RunnableC0003a());
    }

    public void c(boolean z) {
        this.f16b.post(new d(z));
    }

    public void d() {
        this.f15a.kill();
    }

    public void e(int i) {
        this.f16b.post(new h(i));
    }

    public void f(int i, int i2) {
        this.f16b.post(new g(i, i2));
    }

    public void g(int i, int i2) {
        this.f16b.post(new f(i, i2));
    }

    public void h(String str, String str2) {
        this.f16b.post(new e(str, str2));
    }

    public void i(int i, boolean z) {
        this.f16b.post(new c(i, z));
    }

    public void j(IEngineeringSdkCallBack iEngineeringSdkCallBack) {
        this.f15a.register(iEngineeringSdkCallBack);
    }

    public void k(IEngineeringSdkCallBack iEngineeringSdkCallBack) {
        this.f15a.unregister(iEngineeringSdkCallBack);
    }
}
