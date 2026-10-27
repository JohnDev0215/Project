package So.So.So.SpP;

import android.car.Car;
import android.content.Context;
import android.os.PowerManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPowerManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkPowerManager.class, singleton = true)
public class PoOSp implements IDiLinkPowerManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PowerManager f149a;

    public PoOSp(Context context) {
        this.f149a = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void dumpWakeLock() {
        this.f149a.dumpWakeLock();
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void setBacklightMode(int i) {
        this.f149a.setBacklightMode(i);
    }
}
