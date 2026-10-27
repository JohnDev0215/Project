package BA1aabBX.BA1aabBX.BA1aabBX.AaAX;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class BA1aabBX implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        throw new UnsupportedTargetPlatformException("Di5.0 不存在此接口！");
    }
}
