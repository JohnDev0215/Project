package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7;

import android.app.vehicle.CustomVehicleConfig;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCustomVehicleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkCustomVehicleConfig.class, singleton = true)
public class nMMnMM implements IDiLinkCustomVehicleConfig {
    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getDeviceType(Context context) {
        return CustomVehicleConfig.getInstance(context).getDeviceType();
    }

    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getVehicleConfigValue(Context context, int i) {
        throw new UnsupportedTargetPlatformException("DiR 没有 getVehicleConfigValue 方法！");
    }
}
