package k6;

import com.stingray.drivemodedetector.DriveMode;
import com.stingray.drivemodedetector.DriveModeListener;

/* JADX INFO: renamed from: k6.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1703k implements DriveModeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C1704l f18271a;

    public C1703k(C1704l c1704l) {
        this.f18271a = c1704l;
    }

    @Override // com.stingray.drivemodedetector.DriveModeListener
    public final void onDriveModeChanged(DriveMode driveMode) {
        A7.m.f(driveMode, "driveMode");
        A7.m.f("DriveModeManager onDriveModeChanged, driveMode: " + driveMode, "text");
        C1704l c1704l = this.f18271a;
        if (driveMode != c1704l.f18278i) {
            c1704l.f18278i = driveMode;
            c1704l.c.l(driveMode);
            C1704l.b(driveMode);
        }
    }

    @Override // com.stingray.drivemodedetector.DriveModeListener
    public final void onPassengerDetectionChanged(boolean z6) {
        A7.m.f("DriveModeManager onPassengerDetectedChanged, isPassengerPresent: " + z6, "text");
        C1704l c1704l = this.f18271a;
        if (z6 != ((Boolean) c1704l.f18274e.getValue()).booleanValue()) {
            c1704l.f18279j = z6;
            c1704l.d();
            C1704l.c(Boolean.valueOf(z6));
        }
    }
}
