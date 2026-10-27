package GGW.GGW.GGW.hHWh4GGG;

import android.car.Car;
import android.content.Context;
import android.os.PowerManager;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPowerManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkPowerManager.class, singleton = true)
public class Gg4HHGg44 implements IDiLinkPowerManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PowerManager f61a;

    public Gg4HHGg44(Context context) {
        this.f61a = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void dumpWakeLock() {
        this.f61a.dumpWakeLock();
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void setBacklightMode(int i) {
        this.f61a.setBacklightMode(i);
    }
}
