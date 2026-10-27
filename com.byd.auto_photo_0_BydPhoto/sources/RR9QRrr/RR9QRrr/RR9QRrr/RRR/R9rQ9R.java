package RR9QRrr.RR9QRrr.RR9QRrr.RRR;

import android.car.Car;
import android.content.Context;
import android.os.PowerManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPowerManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkPowerManager.class, singleton = true)
public class R9rQ9R implements IDiLinkPowerManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PowerManager f107a;

    public R9rQ9R(Context context) {
        this.f107a = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void dumpWakeLock() {
        this.f107a.dumpWakeLock();
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void setBacklightMode(int i) {
        this.f107a.setBacklightMode(i);
    }
}
