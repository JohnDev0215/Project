package PVUVUPv.PVUVUPv.PVUVUPv.VU2UVPP;

import PVUVUPv.PVUVUPv.PVUVUPv.VUUUVPvVV.UUVP.b;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_RUI, DiLink.VERSION_NAME_RSE_5_0})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class PVUVUPv implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        return Bitmap.wrapHardwareBuffer(((b) iDiLinkGraphicBuffer).a(), colorSpace);
    }
}
