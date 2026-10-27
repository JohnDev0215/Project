package com.stingray.drivemodedetector;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/drivemodedetector/DriveModeListener;", "", "Lcom/stingray/drivemodedetector/DriveMode;", "driveMode", "Lm7/q;", "onDriveModeChanged", "(Lcom/stingray/drivemodedetector/DriveMode;)V", "", "isPassengerPresent", "onPassengerDetectionChanged", "(Z)V", "drivemodedetector_byd8155fRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DriveModeListener {
    void onDriveModeChanged(DriveMode driveMode);

    void onPassengerDetectionChanged(boolean isPassengerPresent);
}
