package lkl6Kl.lkl6Kl.lkl6Kl.K6;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkPlatformConfig;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkPlatformConfig.class, singleton = true)
public class UkLK implements IDiLinkPlatformConfig {
    @Override // com.byd.dilink.app.IDiLinkPlatformConfig
    public int getDeviceType(Context context) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 PlatformConfig 类！");
    }

    @Override // com.byd.dilink.app.IDiLinkPlatformConfig
    public String getUiGeneration(Context context) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 PlatformConfig 类！");
    }

    @Override // com.byd.dilink.app.IDiLinkPlatformConfig
    public boolean isUiRGeneration(Context context) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 PlatformConfig 类！");
    }
}
