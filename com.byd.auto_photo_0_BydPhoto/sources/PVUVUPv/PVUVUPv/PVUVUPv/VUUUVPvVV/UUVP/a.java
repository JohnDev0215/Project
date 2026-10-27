package PVUVUPv.PVUVUPv.PVUVUPv.VUUUVPvVV.UUVP;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkTaskSnapShot {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ActivityManager.TaskSnapshot f79a;

    public a(ActivityManager.TaskSnapshot taskSnapshot) {
        if (taskSnapshot == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("taskSnapshot is null");
        }
        this.f79a = taskSnapshot;
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getAppearance() {
        throw new UnsupportedTargetPlatformException("RSE 没有 TaskSnapshot.getAppearance() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ColorSpace getColorSpace() {
        return this.f79a.getColorSpace();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public HardwareBuffer getHardwareBuffer() {
        throw new UnsupportedTargetPlatformException("RSE 没有 TaskSnapshot.getHardwareBuffer() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public long getId() {
        throw new UnsupportedTargetPlatformException("RSE 没有 TaskSnapshot.getId() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public Rect getLetterboxInsets() {
        throw new UnsupportedTargetPlatformException("RSE 没有 TaskSnapshot.getLetterboxInsets() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getRotation() {
        throw new UnsupportedTargetPlatformException("RSE 没有 TaskSnapshot.getRotation() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public IDiLinkGraphicBuffer getSnapshot() {
        return new b(this.f79a.getSnapshot());
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ComponentName getTopActivityComponent() {
        return this.f79a.getTopActivityComponent();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getWindowingMode() {
        return this.f79a.getWindowingMode();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean hasImeSurface() {
        throw new UnsupportedTargetPlatformException("RSE 没有 TaskSnapshot.hasImeSurface() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean isTranslucent() {
        return this.f79a.isTranslucent();
    }

    @NonNull
    public String toString() {
        return this.f79a.toString();
    }
}
