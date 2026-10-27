package i3;

import com.byd.cpaa.model.ChannelType;
import com.byd.cpaa.model.CpaaType;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: CpaaDevice.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5475a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CpaaType f5476b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5477c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ChannelType f5478d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f5479e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f5480f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f5481g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f5482h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f5483i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f5484j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f5485k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5486l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5487m;

    public a(AndroidAutoDevice androidAutoDevice) {
        this.f5476b = CpaaType.CAR_PLAY;
        this.f5478d = ChannelType.BT_WIFI;
        this.f5484j = 0L;
        this.f5485k = false;
        this.f5475a = androidAutoDevice.getDeviceName();
        this.f5476b = CpaaType.ANDROID_AUTO;
        this.f5483i = androidAutoDevice.getUsbSerialNumber();
        this.f5480f = androidAutoDevice.getBtAddr();
        this.f5482h = androidAutoDevice.getWifiAddr();
        this.f5486l = androidAutoDevice.isUsbDevice() ? 1 : 2;
        this.f5487m = androidAutoDevice.getDeviceStatus();
        this.f5477c = androidAutoDevice.getDeviceStatus() == 4;
    }

    public String a() {
        return this.f5480f;
    }

    public CpaaType b() {
        return this.f5476b;
    }

    public String toString() {
        return "CpaaDevice{name='" + this.f5475a + "', type=" + this.f5476b + ", connected=" + this.f5477c + ", connectedChannel=" + this.f5478d + ", btName='" + this.f5479e + "', wifiName='" + this.f5481g + "', lastDisconnectedTime=" + this.f5484j + ", hasUsbSignal=" + this.f5485k + ",deviceStatus=" + this.f5487m + '}';
    }

    public a(CarPlayDevice carPlayDevice) {
        CpaaType cpaaType = CpaaType.CAR_PLAY;
        this.f5476b = cpaaType;
        this.f5478d = ChannelType.BT_WIFI;
        this.f5484j = 0L;
        this.f5485k = false;
        this.f5475a = carPlayDevice.getDeviceName();
        this.f5476b = cpaaType;
        this.f5483i = carPlayDevice.getUsbSerialNumber();
        this.f5480f = carPlayDevice.getBtAddr();
        this.f5482h = carPlayDevice.getWifiAddr();
        this.f5486l = carPlayDevice.isUsbDevice() ? 1 : 2;
        this.f5487m = carPlayDevice.getDeviceStatus();
        this.f5477c = carPlayDevice.getDeviceStatus() == 4;
    }
}
