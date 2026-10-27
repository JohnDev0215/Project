package jViVV55i.jViVV55i.jViVV55i.ViiVJJIi.VIJJ;

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

/* JADX INFO: loaded from: classes2.dex */
public class b implements IDiLinkTaskSnapShot {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ActivityManager.TaskSnapshot f3060a;

    public b(ActivityManager.TaskSnapshot taskSnapshot) {
        if (taskSnapshot == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("taskSnapshot is null");
        }
        this.f3060a = taskSnapshot;
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getAppearance() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 TaskSnapshot.hasImeSurface() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ColorSpace getColorSpace() {
        return this.f3060a.getColorSpace();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public HardwareBuffer getHardwareBuffer() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 TaskSnapshot.getHardwareBuffer() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public long getId() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 TaskSnapshot.getId() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public Rect getLetterboxInsets() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 TaskSnapshot.getLetterboxInsets() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getRotation() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 TaskSnapshot.getRotation() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public IDiLinkGraphicBuffer getSnapshot() {
        return new a(this.f3060a.getSnapshot());
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ComponentName getTopActivityComponent() {
        return this.f3060a.getTopActivityComponent();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getWindowingMode() {
        return this.f3060a.getWindowingMode();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean hasImeSurface() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 TaskSnapshot.hasImeSurface() 方法！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean isTranslucent() {
        return this.f3060a.isTranslucent();
    }

    @NonNull
    public String toString() {
        return this.f3060a.toString();
    }
}
