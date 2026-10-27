package mNTT77n7n.mNTT77n7n.mNTT77n7n.mT;

import android.car.Car;
import android.content.Context;
import android.os.PowerManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPowerManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkPowerManager.class, singleton = true)
public class nmMNN77m implements IDiLinkPowerManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PowerManager f3163a;

    public nmMNN77m(Context context) {
        this.f3163a = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void dumpWakeLock() {
        this.f3163a.dumpWakeLock();
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void setBacklightMode(int i) {
        this.f3163a.setBacklightMode(i);
    }
}
