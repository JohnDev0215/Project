package sst.sst.sst.StsT;

import android.app.vehicle.CustomVehicleConfig;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCustomVehicleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkCustomVehicleConfig.class, singleton = true)
public class tQSQQT1sT implements IDiLinkCustomVehicleConfig {
    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getDeviceType(Context context) {
        return CustomVehicleConfig.getInstance(context).getDeviceType();
    }

    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getVehicleConfigValue(Context context, int i) {
        throw new UnsupportedTargetPlatformException("ries没有 getVehicleConfigValue 方法！");
    }
}
