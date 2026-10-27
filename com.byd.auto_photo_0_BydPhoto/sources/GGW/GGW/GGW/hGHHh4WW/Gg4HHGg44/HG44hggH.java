package GGW.GGW.GGW.hGHHh4WW.Gg4HHGg44;

import android.content.Context;
import android.hardware.watermark.WatermarkRender;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkWatermarkRenderManager.class, singleton = true)
public class HG44hggH implements IDiLinkWatermarkRenderManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager
    public IDiLinkWatermarkRender create(Context context) {
        WatermarkRender watermarkRenderCreate = WatermarkRender.create(context);
        if (watermarkRenderCreate == null) {
            return null;
        }
        return new c(watermarkRenderCreate);
    }
}
