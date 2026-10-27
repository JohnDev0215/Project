package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr;

import android.app.PlatformConfig;
import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkPlatformConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkPlatformConfig.class, singleton = true)
public class RRqRQQQRQ implements IDiLinkPlatformConfig {
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
