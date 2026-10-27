package jViVV55i.jViVV55i.jViVV55i.jJjIJ;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;
import jViVV55i.jViVV55i.jViVV55i.ViiVJJIi.VIJJ.a;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class jViVV55i implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        return Bitmap.wrapHardwareBuffer(((a) iDiLinkGraphicBuffer).a(), colorSpace);
    }
}
