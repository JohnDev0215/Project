package com.stingray.drivemodedetector;

import android.content.Context;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH&¢\u0006\u0004\b\r\u0010\fR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u000e8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/stingray/drivemodedetector/IDriveModeDetector;", "", "Lcom/stingray/drivemodedetector/DriveMode;", "getDriveMode", "()Lcom/stingray/drivemodedetector/DriveMode;", "", "isPassengerPresent", "()Z", "Lcom/stingray/drivemodedetector/DriveModeListener;", "listener", "Lm7/q;", "addListener", "(Lcom/stingray/drivemodedetector/DriveModeListener;)V", "removeListener", "Landroid/content/Context;", "getCurrentActivityContext", "()Landroid/content/Context;", "setCurrentActivityContext", "(Landroid/content/Context;)V", "currentActivityContext", "drivemodedetector_byd8155fRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface IDriveModeDetector {

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean isPassengerPresent(IDriveModeDetector iDriveModeDetector) {
            return false;
        }
    }

    void addListener(DriveModeListener listener);

    Context getCurrentActivityContext();

    DriveMode getDriveMode();

    boolean isPassengerPresent();

    void removeListener(DriveModeListener listener);

    void setCurrentActivityContext(Context context);
}
