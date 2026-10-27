package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicMemoryStoreConfig extends MediaConfigBase {
    public static final DynamicMemoryStoreConfig INSTANCE = new DynamicMemoryStoreConfig();
    private final ConfigurationValue<Integer> mNativeMemoryAllocationForAudioInMegabytes = newIntConfigValue("playback_nativeMemoryAllocationForAudioInMegabytes", 20);
    private final ConfigurationValue<Integer> mNativeMemoryAllocationGapBetweenMaxAndMinInMegabytes = newIntConfigValue("playback_nativeMemoryAllocationGapBetweenMaxAndMinInMegabytes", 40);
    private final ConfigurationValue<Boolean> mCheckHeapVersion = newBooleanConfigValue("playback_CheckHeapVersion", true);
    private final ConfigurationValue<Boolean> mCheckThreadInterrupt = newBooleanConfigValue("playback_CheckThreadInterrupt", true);
    private final ConfigurationValue<Boolean> mCopyStreamCheckContentActive = newBooleanConfigValue("playback_CopyStreamCheckContentActive", true);

    private DynamicMemoryStoreConfig() {
    }

    public int getNativeMemoryAllocationForAudioInMegabytes() {
        return this.mNativeMemoryAllocationForAudioInMegabytes.getValue().intValue();
    }

    public int getNativeMemoryAllocationGapBetweenMaxAndMinInMegabytes() {
        return this.mNativeMemoryAllocationGapBetweenMaxAndMinInMegabytes.getValue().intValue();
    }

    public boolean getCheckHeapVersion() {
        return this.mCheckHeapVersion.getValue().booleanValue();
    }

    public boolean getCheckThreadInterrupt() {
        return this.mCheckThreadInterrupt.getValue().booleanValue();
    }

    public boolean getCopyStreamCheckContentActive() {
        return this.mCopyStreamCheckContentActive.getValue().booleanValue();
    }
}
