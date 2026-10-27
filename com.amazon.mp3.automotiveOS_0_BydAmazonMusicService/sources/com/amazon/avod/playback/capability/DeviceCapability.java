package com.amazon.avod.playback.capability;

import com.google.common.base.MoreObjects;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceCapability {
    public static final int DEFAULT_CORE_FREQUENCY_IN_MHZ = 1200;
    public static final int DEFAULT_NUMBER_OF_CORES = 2;
    private final int mCoreFrequency;
    private final boolean mHardwareAcceleration;
    private final boolean mNeonSupport;
    private final int mNumberOfCores;

    public DeviceCapability(int i, int i2, boolean z, boolean z2) {
        this.mNumberOfCores = i <= 0 ? 2 : i;
        this.mCoreFrequency = i2 <= 0 ? DEFAULT_CORE_FREQUENCY_IN_MHZ : i2;
        this.mNeonSupport = z;
        this.mHardwareAcceleration = z2;
    }

    public int getNumberOfCores() {
        return this.mNumberOfCores;
    }

    public int getCoreFrequency() {
        return this.mCoreFrequency;
    }

    public boolean getNeonSupport() {
        return this.mNeonSupport;
    }

    public boolean getHardwareAcceleration() {
        return this.mHardwareAcceleration;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("Number of cores", this.mNumberOfCores).add("Core frequency", this.mCoreFrequency).add("NEON support", this.mNeonSupport).add("Hardware accelerated", this.mHardwareAcceleration).toString();
    }
}
