package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.RrRRrrrQr;

import android.content.Context;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkWatermarkRenderManager.class, singleton = true)
public class R9R implements IDiLinkWatermarkRenderManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager
    public IDiLinkWatermarkRender create(Context context) {
        throw new UnsupportedTargetPlatformException("rcs 没有 WatermarkRender 类！");
    }
}
