package com.stingray.drivemodedetector;

import A7.m;
import android.app.Activity;
import android.content.Context;
import com.stingray.drivemodedetector.IDriveModeDetector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import k.AbstractC1582b;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001d\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\bH$¢\u0006\u0004\b\u001f\u0010\u0010J\u000f\u0010 \u001a\u00020\bH$¢\u0006\u0004\b \u0010\u0010J\u000f\u0010!\u001a\u00020\bH&¢\u0006\u0004\b!\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b%\u0010\"\u001a\u0004\b&\u0010$\"\u0004\b'\u0010\u0005R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00110(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*¨\u0006+"}, d2 = {"Lcom/stingray/drivemodedetector/DriveModeDetectorBase;", "Lcom/stingray/drivemodedetector/IDriveModeDetector;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/stingray/drivemodedetector/DriveMode;", "driveMode", "Lm7/q;", "onDriveModeChanged", "(Lcom/stingray/drivemodedetector/DriveMode;)V", "", "isPassengerPresent", "onPassengerDetectionChanged", "(Z)V", "recheckDriveMode", "()V", "Lcom/stingray/drivemodedetector/DriveModeListener;", "listener", "addListener", "(Lcom/stingray/drivemodedetector/DriveModeListener;)V", "removeListener", "Landroid/app/Activity;", "activity", "checkForPermissions", "(Landroid/app/Activity;)Z", "Lk/b;", "", "activityResultLauncher", "askForPermissions", "(Lk/b;)V", "registerOnDriveModeChange", "unregisterOnDriveModeChange", "initialize", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "currentActivityContext", "getCurrentActivityContext", "setCurrentActivityContext", "", "listeners", "Ljava/util/List;", "drivemodedetector_byd8155fRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class DriveModeDetectorBase implements IDriveModeDetector {
    private final Context context;
    private Context currentActivityContext;
    private final List<DriveModeListener> listeners;

    public DriveModeDetectorBase(Context context) {
        m.f(context, "context");
        this.context = context;
        this.listeners = new ArrayList();
    }

    @Override // com.stingray.drivemodedetector.IDriveModeDetector
    public void addListener(DriveModeListener listener) {
        m.f(listener, "listener");
        if (this.listeners.isEmpty()) {
            registerOnDriveModeChange();
        }
        this.listeners.add(listener);
    }

    public void askForPermissions(AbstractC1582b activityResultLauncher) {
        m.f(activityResultLauncher, "activityResultLauncher");
    }

    public boolean checkForPermissions(Activity activity) {
        m.f(activity, "activity");
        return true;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.stingray.drivemodedetector.IDriveModeDetector
    public Context getCurrentActivityContext() {
        return this.currentActivityContext;
    }

    public abstract void initialize();

    @Override // com.stingray.drivemodedetector.IDriveModeDetector
    public boolean isPassengerPresent() {
        return IDriveModeDetector.DefaultImpls.isPassengerPresent(this);
    }

    public final void onDriveModeChanged(DriveMode driveMode) {
        m.f(driveMode, "driveMode");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((DriveModeListener) it.next()).onDriveModeChanged(driveMode);
        }
    }

    public final void onPassengerDetectionChanged(boolean isPassengerPresent) {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((DriveModeListener) it.next()).onPassengerDetectionChanged(isPassengerPresent);
        }
    }

    public final void recheckDriveMode() {
        onDriveModeChanged(getDriveMode());
    }

    public abstract void registerOnDriveModeChange();

    @Override // com.stingray.drivemodedetector.IDriveModeDetector
    public void removeListener(DriveModeListener listener) {
        m.f(listener, "listener");
        this.listeners.remove(listener);
        if (this.listeners.isEmpty()) {
            unregisterOnDriveModeChange();
        }
    }

    @Override // com.stingray.drivemodedetector.IDriveModeDetector
    public void setCurrentActivityContext(Context context) {
        this.currentActivityContext = context;
    }

    public abstract void unregisterOnDriveModeChange();
}
