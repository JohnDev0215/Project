package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.e3E3ZeZ3E;

import android.content.Context;
import android.hardware.watermark.WatermarkRender;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWatermarkRenderManager.class, singleton = true)
public class fFFZ implements IDiLinkWatermarkRenderManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager
    public IDiLinkWatermarkRender create(Context context) {
        WatermarkRender watermarkRenderCreate = WatermarkRender.create(context);
        if (watermarkRenderCreate == null) {
            return null;
        }
        return new a(watermarkRenderCreate);
    }
}
