package lkl6Kl.lkl6Kl.lkl6Kl.K6.k66lUkK;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;
import com.byd.dilink.app.am.IDiLinkTaskSnapShotManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkTaskSnapShotManager.class, singleton = true)
public class KLLlL6L implements IDiLinkTaskSnapShotManager {
    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShotManager
    public IDiLinkTaskSnapShot create(long j, ComponentName componentName, HardwareBuffer hardwareBuffer, ColorSpace colorSpace, int i, int i2, Point point, Rect rect, Rect rect2, boolean z, boolean z2, int i3, int i4, boolean z3, boolean z4, boolean z5) {
        throw new UnsupportedTargetPlatformException("IDiLinkTaskSnapShot create在Di4.0平台不支持，请更改为其他create方法!");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShotManager
    public IDiLinkTaskSnapShot create(ComponentName componentName, IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace, int i, Rect rect, boolean z, float f, boolean z2, int i2, int i3, boolean z3) {
        return new b(new ActivityManager.TaskSnapshot(componentName, ((a) iDiLinkGraphicBuffer).a(), colorSpace, i, rect, z, f, z2, i2, i3, z3));
    }
}
