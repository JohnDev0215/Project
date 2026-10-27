package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.dms.BYDAutoDmsDevice;
import android.hardware.bydauto.dms.IDMSListener;

/* JADX INFO: compiled from: BYDDmsDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static t f1301a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoDmsDevice f1302b;

    public static synchronized t b() {
        if (f1301a == null) {
            f1301a = new t();
        }
        return f1301a;
    }

    public void a() {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.getDmsSwitchChanged();
        }
    }

    public void c(Context context) {
        this.f1302b = BYDAutoDmsDevice.getInstance(context);
    }

    public void d() {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.queryAttentionDetect();
        }
    }

    public void e() {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.queryDmsMode();
        }
    }

    public void f() {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.queryDmsServiceStatus();
        }
    }

    public void g() {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.queryFatigueDetect();
        }
    }

    public void h(IDMSListener iDMSListener) {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.registerListener(iDMSListener);
        }
    }

    public void i(int i) {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.setAttentionDetect(i);
        }
    }

    public void j(int i) {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.setDmsMode(i);
        }
    }

    public void k(int i) {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.setDmsSwtichStatus(i);
        }
    }

    public void l(int i) {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.setFatigueDetect(i);
        }
    }

    public void m(IDMSListener iDMSListener) {
        BYDAutoDmsDevice bYDAutoDmsDevice = this.f1302b;
        if (bYDAutoDmsDevice != null) {
            bYDAutoDmsDevice.unregisterListener(iDMSListener);
        }
    }
}
