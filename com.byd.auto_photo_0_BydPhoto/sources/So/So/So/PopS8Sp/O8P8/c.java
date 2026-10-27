package So.So.So.PopS8Sp.O8P8;

import android.content.ComponentName;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.hardware.HardwareBuffer;
import android.window.TaskSnapshot;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;

/* JADX INFO: loaded from: classes.dex */
public class c implements IDiLinkTaskSnapShot {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TaskSnapshot f145a;

    public c(TaskSnapshot taskSnapshot) {
        if (taskSnapshot == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("taskSnapshot is null");
        }
        this.f145a = taskSnapshot;
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getAppearance() {
        return this.f145a.getAppearance();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ColorSpace getColorSpace() {
        return this.f145a.getColorSpace();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public HardwareBuffer getHardwareBuffer() {
        return this.f145a.getHardwareBuffer();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public long getId() {
        return this.f145a.getId();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public Rect getLetterboxInsets() {
        return this.f145a.getLetterboxInsets();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getRotation() {
        return this.f145a.getRotation();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public IDiLinkGraphicBuffer getSnapshot() {
        return new a(this.f145a.getSnapshot());
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public ComponentName getTopActivityComponent() {
        return this.f145a.getTopActivityComponent();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public int getWindowingMode() {
        return this.f145a.getWindowingMode();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean hasImeSurface() {
        return this.f145a.hasImeSurface();
    }

    @Override // com.byd.dilink.app.am.IDiLinkTaskSnapShot
    public boolean isTranslucent() {
        return this.f145a.isTranslucent();
    }

    @NonNull
    public String toString() {
        return this.f145a.toString();
    }
}
