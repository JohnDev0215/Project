package com.android.car.systeminterface;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.car.CarPowerManagementService;
import com.android.car.procfsinspector.ProcessInfo;
import com.android.car.storagemonitoring.LifetimeWriteInfoProvider;
import com.android.car.storagemonitoring.UidIoStatsProvider;
import com.android.car.storagemonitoring.WearInformationProvider;
import com.android.car.systeminterface.ActivityManagerInterface;
import com.android.car.systeminterface.DisplayInterface;
import com.android.car.systeminterface.IOInterface;
import com.android.car.systeminterface.StorageMonitoringInterface;
import com.android.car.systeminterface.SystemStateInterface;
import com.android.car.systeminterface.TimeInterface;
import com.android.car.systeminterface.WakeLockInterface;
import com.android.internal.car.ICarServiceHelper;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class SystemInterface implements ActivityManagerInterface, DisplayInterface, IOInterface, StorageMonitoringInterface, SystemStateInterface, TimeInterface, WakeLockInterface {
    private final ActivityManagerInterface mActivityManagerInterface;
    private final DisplayInterface mDisplayInterface;
    private final IOInterface mIOInterface;
    private final StorageMonitoringInterface mStorageMonitoringInterface;
    private final SystemStateInterface mSystemStateInterface;
    private final TimeInterface mTimeInterface;
    private final WakeLockInterface mWakeLockInterface;

    SystemInterface(ActivityManagerInterface activityManagerInterface, DisplayInterface displayInterface, IOInterface ioInterface, StorageMonitoringInterface storageMonitoringInterface, SystemStateInterface systemStateInterface, TimeInterface timeInterface, WakeLockInterface wakeLockInterface) {
        this.mActivityManagerInterface = activityManagerInterface;
        this.mDisplayInterface = displayInterface;
        this.mIOInterface = ioInterface;
        this.mStorageMonitoringInterface = storageMonitoringInterface;
        this.mSystemStateInterface = systemStateInterface;
        this.mTimeInterface = timeInterface;
        this.mWakeLockInterface = wakeLockInterface;
    }

    public ActivityManagerInterface getActivityManagerInterface() {
        return this.mActivityManagerInterface;
    }

    public DisplayInterface getDisplayInterface() {
        return this.mDisplayInterface;
    }

    public IOInterface getIOInterface() {
        return this.mIOInterface;
    }

    public SystemStateInterface getSystemStateInterface() {
        return this.mSystemStateInterface;
    }

    public TimeInterface getTimeInterface() {
        return this.mTimeInterface;
    }

    public WakeLockInterface getWakeLockInterface() {
        return this.mWakeLockInterface;
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public void setCarServiceHelper(ICarServiceHelper helper) {
        this.mSystemStateInterface.setCarServiceHelper(helper);
    }

    @Override // com.android.car.systeminterface.ActivityManagerInterface
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        this.mActivityManagerInterface.sendBroadcastAsUser(intent, user);
    }

    @Override // com.android.car.systeminterface.IOInterface
    public File getSystemCarDir() {
        return this.mIOInterface.getSystemCarDir();
    }

    @Override // com.android.car.systeminterface.WakeLockInterface
    public void releaseAllWakeLocks() {
        this.mWakeLockInterface.releaseAllWakeLocks();
    }

    @Override // com.android.car.systeminterface.WakeLockInterface
    public void switchToPartialWakeLock() {
        this.mWakeLockInterface.switchToPartialWakeLock();
    }

    @Override // com.android.car.systeminterface.WakeLockInterface
    public void switchToFullWakeLock() {
        this.mWakeLockInterface.switchToFullWakeLock();
    }

    @Override // com.android.car.systeminterface.TimeInterface
    public long getUptime() {
        return this.mTimeInterface.getUptime();
    }

    @Override // com.android.car.systeminterface.TimeInterface
    public long getUptime(boolean includeDeepSleepTime) {
        return this.mTimeInterface.getUptime(includeDeepSleepTime);
    }

    @Override // com.android.car.systeminterface.TimeInterface
    public void scheduleAction(Runnable r, long delayMs) {
        this.mTimeInterface.scheduleAction(r, delayMs);
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public List<ProcessInfo> getRunningProcesses() {
        return this.mSystemStateInterface.getRunningProcesses();
    }

    @Override // com.android.car.systeminterface.TimeInterface
    public void cancelAllActions() {
        this.mTimeInterface.cancelAllActions();
    }

    @Override // com.android.car.systeminterface.DisplayInterface
    public void setDisplayBrightness(int brightness) {
        this.mDisplayInterface.setDisplayBrightness(brightness);
    }

    @Override // com.android.car.systeminterface.DisplayInterface
    public void setDisplayState(boolean on) {
        this.mDisplayInterface.setDisplayState(on);
    }

    @Override // com.android.car.systeminterface.DisplayInterface
    public void startDisplayStateMonitoring(CarPowerManagementService service) {
        this.mDisplayInterface.startDisplayStateMonitoring(service);
    }

    @Override // com.android.car.systeminterface.DisplayInterface
    public void stopDisplayStateMonitoring() {
        this.mDisplayInterface.stopDisplayStateMonitoring();
    }

    @Override // com.android.car.systeminterface.StorageMonitoringInterface
    public WearInformationProvider[] getFlashWearInformationProviders() {
        return this.mStorageMonitoringInterface.getFlashWearInformationProviders();
    }

    @Override // com.android.car.systeminterface.StorageMonitoringInterface
    public UidIoStatsProvider getUidIoStatsProvider() {
        return this.mStorageMonitoringInterface.getUidIoStatsProvider();
    }

    @Override // com.android.car.systeminterface.StorageMonitoringInterface
    public LifetimeWriteInfoProvider getLifetimeWriteInfoProvider() {
        return this.mStorageMonitoringInterface.getLifetimeWriteInfoProvider();
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public void shutdown() {
        this.mSystemStateInterface.shutdown();
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public boolean enterDeepSleep() {
        return this.mSystemStateInterface.enterDeepSleep();
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public void scheduleActionForBootCompleted(Runnable action, Duration delay) {
        this.mSystemStateInterface.scheduleActionForBootCompleted(action, delay);
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public boolean isWakeupCausedByTimer() {
        return this.mSystemStateInterface.isWakeupCausedByTimer();
    }

    @Override // com.android.car.systeminterface.SystemStateInterface
    public boolean isSystemSupportingDeepSleep() {
        return this.mSystemStateInterface.isSystemSupportingDeepSleep();
    }

    @Override // com.android.car.systeminterface.DisplayInterface
    public void refreshDisplayBrightness() {
        this.mDisplayInterface.refreshDisplayBrightness();
    }

    public static final class Builder {
        private ActivityManagerInterface mActivityManagerInterface;
        private DisplayInterface mDisplayInterface;
        private IOInterface mIOInterface;
        private StorageMonitoringInterface mStorageMonitoringInterface;
        private SystemStateInterface mSystemStateInterface;
        private TimeInterface mTimeInterface;
        private WakeLockInterface mWakeLockInterface;

        private Builder() {
        }

        public static Builder newSystemInterface() {
            return new Builder();
        }

        public static Builder defaultSystemInterface(Context context) {
            Objects.requireNonNull(context);
            Builder builder = newSystemInterface();
            builder.withActivityManagerInterface(new ActivityManagerInterface.DefaultImpl(context));
            builder.withWakeLockInterface(new WakeLockInterface.DefaultImpl(context));
            builder.withDisplayInterface(new DisplayInterface.DefaultImpl(context, builder.mWakeLockInterface));
            builder.withIOInterface(new IOInterface.DefaultImpl(context));
            builder.withStorageMonitoringInterface(new StorageMonitoringInterface.DefaultImpl());
            builder.withSystemStateInterface(new SystemStateInterface.DefaultImpl(context));
            return builder.withTimeInterface(new TimeInterface.DefaultImpl());
        }

        public static Builder fromBuilder(Builder otherBuilder) {
            return newSystemInterface().withActivityManagerInterface(otherBuilder.mActivityManagerInterface).withDisplayInterface(otherBuilder.mDisplayInterface).withIOInterface(otherBuilder.mIOInterface).withStorageMonitoringInterface(otherBuilder.mStorageMonitoringInterface).withSystemStateInterface(otherBuilder.mSystemStateInterface).withTimeInterface(otherBuilder.mTimeInterface).withWakeLockInterface(otherBuilder.mWakeLockInterface);
        }

        public Builder withActivityManagerInterface(ActivityManagerInterface activityManagerInterface) {
            this.mActivityManagerInterface = activityManagerInterface;
            return this;
        }

        public Builder withDisplayInterface(DisplayInterface displayInterface) {
            this.mDisplayInterface = displayInterface;
            return this;
        }

        public Builder withIOInterface(IOInterface ioInterface) {
            this.mIOInterface = ioInterface;
            return this;
        }

        public Builder withStorageMonitoringInterface(StorageMonitoringInterface storageMonitoringInterface) {
            this.mStorageMonitoringInterface = storageMonitoringInterface;
            return this;
        }

        public Builder withSystemStateInterface(SystemStateInterface systemStateInterface) {
            this.mSystemStateInterface = systemStateInterface;
            return this;
        }

        public Builder withTimeInterface(TimeInterface timeInterface) {
            this.mTimeInterface = timeInterface;
            return this;
        }

        public Builder withWakeLockInterface(WakeLockInterface wakeLockInterface) {
            this.mWakeLockInterface = wakeLockInterface;
            return this;
        }

        public SystemInterface build() {
            ActivityManagerInterface activityManagerInterface = this.mActivityManagerInterface;
            Objects.requireNonNull(activityManagerInterface);
            ActivityManagerInterface activityManagerInterface2 = activityManagerInterface;
            DisplayInterface displayInterface = this.mDisplayInterface;
            Objects.requireNonNull(displayInterface);
            DisplayInterface displayInterface2 = displayInterface;
            IOInterface iOInterface = this.mIOInterface;
            Objects.requireNonNull(iOInterface);
            IOInterface iOInterface2 = iOInterface;
            StorageMonitoringInterface storageMonitoringInterface = this.mStorageMonitoringInterface;
            Objects.requireNonNull(storageMonitoringInterface);
            StorageMonitoringInterface storageMonitoringInterface2 = storageMonitoringInterface;
            SystemStateInterface systemStateInterface = this.mSystemStateInterface;
            Objects.requireNonNull(systemStateInterface);
            SystemStateInterface systemStateInterface2 = systemStateInterface;
            TimeInterface timeInterface = this.mTimeInterface;
            Objects.requireNonNull(timeInterface);
            TimeInterface timeInterface2 = timeInterface;
            WakeLockInterface wakeLockInterface = this.mWakeLockInterface;
            Objects.requireNonNull(wakeLockInterface);
            return new SystemInterface(activityManagerInterface2, displayInterface2, iOInterface2, storageMonitoringInterface2, systemStateInterface2, timeInterface2, wakeLockInterface);
        }
    }
}
