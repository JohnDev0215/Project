package GGW.GGW.GGW.HHH4GWh;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class GGW implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        throw new UnsupportedTargetPlatformException("Di6.0 不存在此接口！");
    }
}
