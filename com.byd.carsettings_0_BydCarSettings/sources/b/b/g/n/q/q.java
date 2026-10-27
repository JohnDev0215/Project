package b.b.g.n.q;

import android.hardware.bydauto.test.BYDAutoTestDevice;

/* JADX INFO: compiled from: BYDAutoTestDeViceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile q f1295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoTestDevice f1296b;

    public q() {
        c();
    }

    public static q b() {
        if (f1295a == null) {
            synchronized (q.class) {
                if (f1295a == null) {
                    f1295a = new q();
                }
            }
        }
        return f1295a;
    }

    public int a() {
        BYDAutoTestDevice bYDAutoTestDevice = this.f1296b;
        if (bYDAutoTestDevice != null) {
            return bYDAutoTestDevice.getAutoAudioSource();
        }
        return 0;
    }

    public void c() {
        if (this.f1296b == null) {
            this.f1296b = BYDAutoTestDevice.getInstance(b.b.a.a.a.b().a());
        }
    }
}
