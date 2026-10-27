package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FFZf3;

import android.content.Context;
import android.os.BydPowerManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.IDiLinkPowerManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkPowerManager.class, singleton = true)
public class e3E3ZeZ3E implements IDiLinkPowerManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydPowerManager f3043a;

    public e3E3ZeZ3E(Context context) {
        if (DiLink.isPlatform(DiLink.VERSION_NAME_5_1, true)) {
            this.f3043a = BydPowerManager.getInstance(context);
        }
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void dumpWakeLock() {
        this.f3043a.dumpWakeLock();
    }

    @Override // com.byd.dilink.os.IDiLinkPowerManager
    public void setBacklightMode(int i) {
        this.f3043a.setBacklightMode(i);
    }
}
