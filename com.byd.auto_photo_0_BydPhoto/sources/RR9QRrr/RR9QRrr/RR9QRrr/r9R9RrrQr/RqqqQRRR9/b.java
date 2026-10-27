package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr.RqqqQRRR9;

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
public class b implements IDiLinkTaskSnapShot {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ActivityManager.TaskSnapshot f134a;

    public b(ActivityManager.TaskSnapshot taskSnapshot) {
        if (taskSnapshot == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("taskSnapshot is null");
        }
        this.f134a = taskSnapshot;
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getAppearance() {
        throw new UnsupportedTargetPlatformException("rcs 没有 TaskSnapshot.getAppearance() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ColorSpace getColorSpace() {
        return this.f134a.getColorSpace();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public HardwareBuffer getHardwareBuffer() {
        throw new UnsupportedTargetPlatformException("rcs 没有 TaskSnapshot.getHardwareBuffer() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public long getId() {
        throw new UnsupportedTargetPlatformException("rcs 没有 TaskSnapshot.getId() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public Rect getLetterboxInsets() {
        throw new UnsupportedTargetPlatformException("rcs 没有 TaskSnapshot.getLetterboxInsets() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getRotation() {
        throw new UnsupportedTargetPlatformException("rcs 没有 TaskSnapshot.getRotation() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public IDiLinkGraphicBuffer getSnapshot() {
        return new a(this.f134a.getSnapshot());
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ComponentName getTopActivityComponent() {
        return this.f134a.getTopActivityComponent();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getWindowingMode() {
        return this.f134a.getWindowingMode();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean hasImeSurface() {
        throw new UnsupportedTargetPlatformException("rcs 没有 TaskSnapshot.hasImeSurface() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean isTranslucent() {
        return this.f134a.isTranslucent();
    }

    @NonNull
    public String toString() {
        return this.f134a.toString();
    }
}
