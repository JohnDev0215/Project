package sst.sst.sst.StsT;

import android.app.PlatformConfig;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkPlatformConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkPlatformConfig.class, singleton = true)
public class Q1ttST implements IDiLinkPlatformConfig {
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
