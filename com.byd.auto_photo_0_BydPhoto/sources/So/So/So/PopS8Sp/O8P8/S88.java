package So.So.So.PopS8Sp.O8P8;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.window.TaskSnapshot;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;
import com.byd.dilink.app.am.IDiLinkTaskSnapShotManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkTaskSnapShotManager.class, singleton = true)
public class S88 implements IDiLinkTaskSnapShotManager {
    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShotManager
    public IDiLinkTaskSnapShot create(long j, ComponentName componentName, HardwareBuffer hardwareBuffer, ColorSpace colorSpace, int i, int i2, Point point, Rect rect, Rect rect2, boolean z, boolean z2, int i3, int i4, boolean z3, boolean z4, boolean z5) {
        return new c(new TaskSnapshot(j, componentName, hardwareBuffer, colorSpace, i, i2, point, rect, rect2, z, z2, i3, i4, z3, z4));
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShotManager
    public IDiLinkTaskSnapShot create(ComponentName componentName, IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace, int i, Rect rect, boolean z, float f, boolean z2, int i2, int i3, boolean z3) {
        throw new UnsupportedTargetPlatformException("IDiLinkTaskSnapShot create在FSE不支持，请更改为其他create方法!");
    }
}
