package h3;

import android.content.Context;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.cpaa.carplay.CarPlayManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import y2.b;

/* JADX INFO: compiled from: CpaaDeviceManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public y2.b f5335a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CarPlayManager f5336b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public AndroidAutoManager f5337c;

    /* JADX INFO: compiled from: CpaaDeviceManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static c f5338a = new c(null);

        public static c a() {
            return f5338a;
        }
    }

    public /* synthetic */ c(b bVar) {
        this();
    }

    public boolean a(Context context) {
        if (CarPlayManager.getInstance(context).isCarPlayConnected() || CarPlayManager.getInstance(context).isCarPlayConnecting()) {
            List<CarPlayDevice> carPlayDevices = CarPlayManager.getInstance(context).getCarPlayDevices();
            for (int i8 = 0; i8 < carPlayDevices.size(); i8++) {
                CarPlayDevice carPlayDevice = carPlayDevices.get(i8);
                if (carPlayDevice.getDeviceStatus() == 4) {
                    g5.a.i("disconnect cp bluetooth device:" + carPlayDevice.getDeviceName());
                    CarPlayManager.getInstance(context).disconnectCarPlay(carPlayDevice.getBtAddr());
                    return true;
                }
            }
        } else if (AndroidAutoManager.getInstance(context).isAndroidAutoConnected() || AndroidAutoManager.getInstance(context).isAndroidAutoConnecting()) {
            List<AndroidAutoDevice> androidAutoDevices = AndroidAutoManager.getInstance(context).getAndroidAutoDevices();
            for (int i9 = 0; i9 < androidAutoDevices.size(); i9++) {
                AndroidAutoDevice androidAutoDevice = androidAutoDevices.get(i9);
                if (androidAutoDevice.getDeviceStatus() == 4) {
                    if (androidAutoDevice.getProjectionConnectType() == 1) {
                        g5.a.i("disconnect AA usb device:" + androidAutoDevice.getDeviceName());
                        AndroidAutoManager.getInstance(context).disconnectAndroidAuto(androidAutoDevice.getUsbSerialNumber());
                    } else if (androidAutoDevice.getProjectionConnectType() == 2) {
                        g5.a.i("disconnect AA bluetooth device:" + androidAutoDevice.getDeviceName());
                        AndroidAutoManager.getInstance(context).disconnectAndroidAuto(androidAutoDevice.getBtAddr());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public int b() {
        AndroidAutoManager androidAutoManager = this.f5337c;
        if (androidAutoManager != null) {
            return androidAutoManager.getAndroidAutoConnectionStatus();
        }
        return 0;
    }

    public List<AndroidAutoDevice> c() {
        return this.f5337c.getAndroidAutoDevices();
    }

    public List<CarPlayDevice> d() {
        return this.f5336b.getCarPlayDevices();
    }

    public List<i3.a> e() {
        ArrayList arrayList = new ArrayList();
        Iterator<AndroidAutoDevice> it = this.f5337c.getAndroidAutoDevices().iterator();
        while (it.hasNext()) {
            arrayList.add(new i3.a(it.next()));
        }
        Iterator<CarPlayDevice> it2 = this.f5336b.getCarPlayDevices().iterator();
        while (it2.hasNext()) {
            arrayList.add(new i3.a(it2.next()));
        }
        return arrayList;
    }

    public void f(Context context) {
        CarPlayManager carPlayManager = CarPlayManager.getInstance(context);
        this.f5336b = carPlayManager;
        carPlayManager.init();
        AndroidAutoManager androidAutoManager = AndroidAutoManager.getInstance(context);
        this.f5337c = androidAutoManager;
        androidAutoManager.init();
        y2.b bVarA = b.o.a();
        this.f5335a = bVarA;
        bVarA.Q(context);
    }

    public void g(Context context) {
        CarPlayManager carPlayManager = this.f5336b;
        if (carPlayManager != null) {
            carPlayManager.unRegister();
        }
        AndroidAutoManager androidAutoManager = this.f5337c;
        if (androidAutoManager != null) {
            androidAutoManager.unRegister();
        }
        y2.b bVar = this.f5335a;
        if (bVar != null) {
            bVar.C0();
        }
    }

    public void h() {
        AndroidAutoManager androidAutoManager = this.f5337c;
        if (androidAutoManager != null) {
            androidAutoManager.startAndroidAuto();
        }
    }

    public void i() {
        AndroidAutoManager androidAutoManager = this.f5337c;
        if (androidAutoManager != null) {
            androidAutoManager.startAndroidAutoMusic();
        }
    }

    public c() {
    }
}
