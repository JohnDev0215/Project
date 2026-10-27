package com.amazon.mp3.service.metrics.mts;

import android.content.Context;
import android.os.Environment;
import com.amazon.mp3.playback.service.streaming.StreamCache;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DeviceUtil;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider;

/* JADX INFO: loaded from: classes4.dex */
public class MusicDeviceInfoProvider implements DeviceInfoProvider {
    private final int mBatteryPercentage;
    private final boolean mIsBackgroundRefreshEnabled;
    private final boolean mIsCharging;
    private final boolean mIsWifiConnected;
    private final float mRemainingDiskGb;
    private final float mRemainingStreamingCacheGb;
    private final float mTotalDiskGb;
    private final float mTotalStreamingCacheGb;

    public MusicDeviceInfoProvider(Context context) {
        double dPow = Math.pow(2.0d, 30.0d);
        StorageLocationFileManager storageLocationFileManagerCreate = StorageLocationFileManager.create(context, Environment.DIRECTORY_MUSIC);
        this.mIsCharging = DeviceUtil.isCharging(context);
        this.mBatteryPercentage = DeviceUtil.getBatteryPercentage(context);
        this.mIsWifiConnected = ConnectivityUtil.isWifiConnected();
        this.mIsBackgroundRefreshEnabled = ConnectivityUtil.isBackgroundDataEnabled();
        this.mTotalDiskGb = (float) (getTotalSpace(storageLocationFileManagerCreate, context) / dPow);
        this.mRemainingDiskGb = (float) (getFreeSpace(storageLocationFileManagerCreate, context) / dPow);
        this.mTotalStreamingCacheGb = (float) (StreamCache.getInstance(context).getMaxCacheSize() / dPow);
        this.mRemainingStreamingCacheGb = (float) (StreamCache.getInstance(context).getRemainingCacheSize() / dPow);
    }

    private long getTotalSpace(StorageLocationFileManager storageLocationFileManager, Context context) {
        return storageLocationFileManager.getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PUBLIC).getTotalSpace();
    }

    private long getFreeSpace(StorageLocationFileManager storageLocationFileManager, Context context) {
        return storageLocationFileManager.getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PUBLIC).getFreeSpace();
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public boolean isCharging() {
        return this.mIsCharging;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public int getBatteryPercentage() {
        return this.mBatteryPercentage;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public boolean isWifiConnected() {
        return this.mIsWifiConnected;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public boolean isBackgroundAppRefreshEnabled() {
        return this.mIsBackgroundRefreshEnabled;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public float getTotalDiskGb() {
        return this.mTotalDiskGb;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public float getRemainingDiskGb() {
        return this.mRemainingDiskGb;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public float getTotalStreamingCacheGb() {
        return this.mTotalStreamingCacheGb;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.deviceInfo.DeviceInfoProvider
    public float getRemainingStreamingCacheGb() {
        return this.mRemainingStreamingCacheGb;
    }
}
