package PVUVUPv.PVUVUPv.PVUVUPv.VUUUVPvVV;

import android.app.PlatformConfig;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkPlatformConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkPlatformConfig.class, singleton = true)
public class VU2UVPP implements IDiLinkPlatformConfig {
    @Override // com.byd.dilink.app.IDiLinkPlatformConfig
    public int getDeviceType(Context context) {
        return PlatformConfig.getInstance(context).getDeviceType();
    }

    @Override // com.byd.dilink.app.IDiLinkPlatformConfig
    public String getUiGeneration(Context context) {
        return PlatformConfig.getInstance(context).getUiGeneration();
    }

    @Override // com.byd.dilink.app.IDiLinkPlatformConfig
    public boolean isUiRGeneration(Context context) {
        return PlatformConfig.getInstance(context).isUiRGeneration();
    }
}
