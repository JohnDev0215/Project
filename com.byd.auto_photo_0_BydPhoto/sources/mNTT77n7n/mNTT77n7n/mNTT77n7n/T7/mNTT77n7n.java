package mNTT77n7n.mNTT77n7n.mNTT77n7n.T7;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class mNTT77n7n implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        throw new UnsupportedTargetPlatformException("DiR 不存在此接口！");
    }
}
