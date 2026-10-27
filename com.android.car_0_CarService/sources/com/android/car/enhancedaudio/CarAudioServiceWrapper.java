package com.android.car.enhancedaudio;

import android.car.media.CarAudioPatchHandle;
import android.car.media.ICarAudio;
import android.content.Context;
import android.media.AudioDeviceAttributes;
import android.os.IBinder;
import com.android.car.CarServiceBase;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CarAudioServiceWrapper extends ICarAudio.Stub implements CarServiceBase {
    private static final String TAG = "CarAudioServiceWrapper";
    private CarEnhancedAudioService mCarEnhancedAudioService;
    private final Context mContext;

    public CarAudioServiceWrapper(Context context, CarEnhancedAudioService service) {
        this.mContext = context;
        this.mCarEnhancedAudioService = service;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("Please enter 'dumpsys car_service --services CarEnhancedAudioService'");
    }

    public boolean isDynamicRoutingEnabled() {
        return this.mCarEnhancedAudioService.isDynamicRoutingEnabled();
    }

    public void setGroupVolume(int zoneId, int groupId, int index, int flags) {
        this.mCarEnhancedAudioService.setGroupVolume(zoneId, groupId, index, flags);
    }

    public int getGroupMaxVolume(int zoneId, int groupId) {
        return this.mCarEnhancedAudioService.getGroupMaxVolume(zoneId, groupId);
    }

    public int getGroupMinVolume(int zoneId, int groupId) {
        return this.mCarEnhancedAudioService.getGroupMinVolume(zoneId, groupId);
    }

    public int getGroupVolume(int zoneId, int groupId) {
        return this.mCarEnhancedAudioService.getGroupVolume(zoneId, groupId);
    }

    public void setFadeTowardFront(float value) {
        this.mCarEnhancedAudioService.setFadeTowardFront(value);
    }

    public void setBalanceTowardRight(float value) {
        this.mCarEnhancedAudioService.setBalanceTowardRight(value);
    }

    public String[] getExternalSources() {
        return this.mCarEnhancedAudioService.getExternalSources();
    }

    public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) {
        return this.mCarEnhancedAudioService.createAudioPatch(sourceAddress, usage, gainInMillibels);
    }

    public void releaseAudioPatch(CarAudioPatchHandle carPatch) {
        this.mCarEnhancedAudioService.releaseAudioPatch(carPatch);
    }

    public int getVolumeGroupCount(int zoneId) {
        return this.mCarEnhancedAudioService.getVolumeGroupCount(zoneId);
    }

    public int getVolumeGroupIdForUsage(int zoneId, int usage) {
        return this.mCarEnhancedAudioService.getVolumeGroupIdForUsage(zoneId, usage);
    }

    public int[] getUsagesForVolumeGroupId(int zoneId, int groupId) {
        return this.mCarEnhancedAudioService.getUsagesForVolumeGroupId(zoneId, groupId);
    }

    public int[] getAudioZoneIds() {
        return this.mCarEnhancedAudioService.getAudioZoneIds();
    }

    public int getZoneIdForUid(int uid) {
        return this.mCarEnhancedAudioService.getZoneIdForUid(uid);
    }

    public boolean setZoneIdForUid(int zoneId, int uid) {
        return this.mCarEnhancedAudioService.setZoneIdForUid(zoneId, uid);
    }

    public String getOutputDeviceAddressForUsage(int zoneId, int usage) {
        return this.mCarEnhancedAudioService.getOutputDeviceAddressForUsage(zoneId, usage);
    }

    public List<AudioDeviceAttributes> getInputDevicesForZoneId(int zoneId) {
        return this.mCarEnhancedAudioService.getInputDevicesForZoneId(zoneId);
    }

    public boolean clearZoneIdForUid(int uid) {
        return this.mCarEnhancedAudioService.clearZoneIdForUid(uid);
    }

    public void registerVolumeCallback(IBinder binder) {
        this.mCarEnhancedAudioService.registerVolumeCallback(binder);
    }

    public void unregisterVolumeCallback(IBinder binder) {
        this.mCarEnhancedAudioService.unregisterVolumeCallback(binder);
    }
}
