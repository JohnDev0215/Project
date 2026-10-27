package GGW.GGW.GGW.W4H;

import android.app.vehicle.CustomVehicleConfig;
import android.content.Context;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCustomVehicleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkCustomVehicleConfig.class, singleton = true)
public class WWHH implements IDiLinkCustomVehicleConfig {
    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getDeviceType(Context context) {
        return CustomVehicleConfig.getInstance(context).getDeviceType();
    }

    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getVehicleConfigValue(Context context, int i) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 getVehicleConfigValue 方法！");
    }
}
