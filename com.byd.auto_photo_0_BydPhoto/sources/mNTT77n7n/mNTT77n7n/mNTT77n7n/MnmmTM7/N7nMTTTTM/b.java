package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7.N7nMTTTTM;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.window.TaskSnapshot;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;

/* JADX INFO: loaded from: classes2.dex */
public class b implements IDiLinkTaskSnapShot {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TaskSnapshot f3141a;

    public b(TaskSnapshot taskSnapshot) {
        if (taskSnapshot == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("taskSnapshot is null");
        }
        this.f3141a = taskSnapshot;
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getAppearance() {
        return this.f3141a.getAppearance();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ColorSpace getColorSpace() {
        return this.f3141a.getColorSpace();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public HardwareBuffer getHardwareBuffer() {
        return this.f3141a.getHardwareBuffer();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public long getId() {
        return this.f3141a.getId();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public Rect getLetterboxInsets() {
        return this.f3141a.getLetterboxInsets();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getRotation() {
        return this.f3141a.getRotation();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public IDiLinkGraphicBuffer getSnapshot() {
        return new c(this.f3141a.getSnapshot());
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ComponentName getTopActivityComponent() {
        return this.f3141a.getTopActivityComponent();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getWindowingMode() {
        return this.f3141a.getWindowingMode();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean hasImeSurface() {
        return this.f3141a.hasImeSurface();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean isTranslucent() {
        return this.f3141a.isTranslucent();
    }

    @NonNull
    public String toString() {
        return this.f3141a.toString();
    }
}
