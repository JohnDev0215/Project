package lkl6Kl.lkl6Kl.lkl6Kl.UkLK.K6;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkWatermarkRenderManager.class, singleton = true)
public class k66lUkK implements IDiLinkWatermarkRenderManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager
    public IDiLinkWatermarkRender create(Context context) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 WatermarkRender 类！");
    }
}
