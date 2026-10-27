package BA1aabBX.BA1aabBX.BA1aabBX.bBB;

import android.app.vehicle.CustomVehicleConfig;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCustomVehicleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkCustomVehicleConfig.class, singleton = true)
public class aba1Aab1b implements IDiLinkCustomVehicleConfig {
    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getDeviceType(Context context) {
        return CustomVehicleConfig.getInstance(context).getDeviceType();
    }

    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getVehicleConfigValue(Context context, int i) {
        return CustomVehicleConfig.getInstance(context).getVehicleConfigValue(i);
    }
}
