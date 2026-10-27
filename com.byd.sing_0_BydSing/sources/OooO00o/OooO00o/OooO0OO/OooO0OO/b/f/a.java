package OooO00o.OooO00o.OooO0Oo.OooO0OO.b.f;

import OooO00o.OooO00o.OooO0Oo.OooO0OO.b.b;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import com.android.volley.BuildConfig;
import com.loostone.libserver.version1.entity.MachineInfoConfig;
import com.loostone.libserver.version1.entity.NetUrlConfig;
import com.loostone.libserver.version1.entity.request.statistical.RequestStatisticalMic;

/* JADX INFO: loaded from: classes.dex */
public class a extends b {
    public a(Context context, String str) {
        super(context, str);
        d(Boolean.TRUE);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.b.b
    public OooO00o.OooO00o.OooO0Oo.OooO0OO.c.b i() {
        return new OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a(String.class);
    }

    public void j(UsbDevice usbDevice, String str, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar) {
        RequestStatisticalMic requestStatisticalMic = new RequestStatisticalMic();
        requestStatisticalMic.setVid(usbDevice.getVendorId() + BuildConfig.FLAVOR);
        requestStatisticalMic.setPid(usbDevice.getProductId() + BuildConfig.FLAVOR);
        requestStatisticalMic.setVendor(usbDevice.getManufacturerName());
        requestStatisticalMic.setDeviceName(usbDevice.getProductName());
        try {
            requestStatisticalMic.setSerial(usbDevice.getSerialNumber());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        requestStatisticalMic.setPackageName(str);
        requestStatisticalMic.setBrand(MachineInfoConfig.getBrand());
        requestStatisticalMic.setModel(MachineInfoConfig.getModel());
        g(NetUrlConfig.getAddress(3), requestStatisticalMic, aVar);
    }
}
