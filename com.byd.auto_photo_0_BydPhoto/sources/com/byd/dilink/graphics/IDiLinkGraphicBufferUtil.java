package com.byd.dilink.graphics;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkGraphicBufferUtil {
    Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace);
}
