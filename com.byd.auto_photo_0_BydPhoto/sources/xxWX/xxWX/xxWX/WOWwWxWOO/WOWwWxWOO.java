package xxWX.xxWX.xxWX.WOWwWxWOO;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCustomVehicleConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkCustomVehicleConfig.class, singleton = true)
public class WOWwWxWOO implements IDiLinkCustomVehicleConfig {
    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getDeviceType(Context context) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有CustomVehicleConfig类!");
    }

    @Override // com.byd.dilink.app.IDiLinkCustomVehicleConfig
    public int getVehicleConfigValue(Context context, int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有CustomVehicleConfig类!");
    }
}
