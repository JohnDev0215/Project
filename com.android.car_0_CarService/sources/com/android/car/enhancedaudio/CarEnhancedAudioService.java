package com.android.car.enhancedaudio;

import android.car.media.CarAudioControlFilter;
import android.car.media.CarAudioPatchHandle;
import android.car.media.ICarAudioControl;
import android.car.media.ICarEnhancedAudio;
import android.car.media.ICarEqualizer;
import android.content.Context;
import android.media.AudioDeviceAttributes;
import android.media.AudioFocusInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.SparseIntArray;
import com.android.car.CarServiceBase;
import com.ts.car.core.FoundationProvider;
import java.io.PrintWriter;
import java.util.List;
import ts.car.service.enhancedaudio.IAudioControl;
import ts.car.service.enhancedaudio.ICarServiceCallback;
import ts.car.service.enhancedaudio.ICore;
import ts.car.service.enhancedaudio.IEqualizer;

/* JADX INFO: loaded from: classes3.dex */
public class CarEnhancedAudioService extends ICarEnhancedAudio.Stub implements CarServiceBase {
    private IAudioControl mAudioControl;
    private ICarAudioControl mCarAudioControlImpl;
    private ICarEqualizer mCarEqualizerImpl;
    private ICarServiceCallback mCarServiceCallback = new ICarServiceCallback() { // from class: com.android.car.enhancedaudio.CarEnhancedAudioService.1
        public int getAudioZoneIdForOccupant(int occupantZoneId) {
            return 0;
        }

        public int getOccupantZoneIdForUserId(int userId) {
            return 0;
        }

        public int getDriverUserId() {
            return 0;
        }

        public int getUserForOccupant(int occupantZoneId) {
            return 0;
        }

        public void setAudioZoneIdsForOccupantZoneIds(SparseIntArray audioZoneIdToOccupantZoneMapping) {
        }
    };
    private final Context mContext;
    private ICore mCore;
    private IEqualizer mEqualizer;

    public CarEnhancedAudioService(Context context) {
        this.mContext = context;
        ICore iCore = (ICore) FoundationProvider.create(ICore.class, new Object[]{context});
        this.mCore = iCore;
        if (iCore != null) {
            this.mAudioControl = iCore.getAudioControlInstance();
            this.mEqualizer = this.mCore.getEqualizerInstance();
        }
        this.mCarAudioControlImpl = new CarAudioControlImpl();
        this.mCarEqualizerImpl = new CarEqualizerImpl();
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        checkFsdkNonNull();
        this.mCore.init(this.mCarServiceCallback);
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        checkFsdkNonNull();
        this.mCore.release();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        checkFsdkNonNull();
        this.mCore.dump(writer);
    }

    public boolean isDynamicRoutingEnabled() {
        checkFsdkNonNull();
        return this.mCore.isAudioFeatureEnabled(1);
    }

    public void setGroupVolume(int zoneId, int groupId, int index, int flags) {
        checkFsdkNonNull();
        this.mCore.setGroupVolume(zoneId, groupId, index, flags);
    }

    public int getGroupMaxVolume(int zoneId, int groupId) {
        checkFsdkNonNull();
        return this.mCore.getGroupMaxVolume(zoneId, groupId);
    }

    public int getGroupMinVolume(int zoneId, int groupId) {
        checkFsdkNonNull();
        return this.mCore.getGroupMinVolume(zoneId, groupId);
    }

    public int getGroupVolume(int zoneId, int groupId) {
        checkFsdkNonNull();
        return this.mCore.getGroupVolume(zoneId, groupId);
    }

    public void setFadeTowardFront(float value) {
        checkFsdkNonNull();
        this.mCore.setFadeTowardFront(value);
    }

    public void setBalanceTowardRight(float value) {
        checkFsdkNonNull();
        this.mCore.setBalanceTowardRight(value);
    }

    public String[] getExternalSources() {
        checkFsdkNonNull();
        return this.mCore.getExternalSources();
    }

    public CarAudioPatchHandle createAudioPatch(String sourceAddress, int usage, int gainInMillibels) {
        checkFsdkNonNull();
        return this.mCore.createAudioPatch(sourceAddress, usage, gainInMillibels);
    }

    public void releaseAudioPatch(CarAudioPatchHandle carPatch) {
        checkFsdkNonNull();
        this.mCore.releaseAudioPatch(carPatch);
    }

    public int getVolumeGroupCount(int zoneId) {
        checkFsdkNonNull();
        return this.mCore.getVolumeGroupCount(zoneId);
    }

    public int getVolumeGroupIdForUsage(int zoneId, int usage) {
        checkFsdkNonNull();
        return this.mCore.getVolumeGroupIdForUsage(zoneId, usage);
    }

    public int[] getUsagesForVolumeGroupId(int zoneId, int groupId) {
        checkFsdkNonNull();
        return this.mCore.getUsagesForVolumeGroupId(zoneId, groupId);
    }

    public int[] getAudioZoneIds() {
        checkFsdkNonNull();
        return this.mCore.getAudioZoneIds();
    }

    public int getZoneIdForUid(int uid) {
        checkFsdkNonNull();
        return this.mCore.getZoneIdForUid(uid);
    }

    public boolean setZoneIdForUid(int zoneId, int uid) {
        checkFsdkNonNull();
        return this.mCore.setZoneIdForUid(zoneId, uid);
    }

    public String getOutputDeviceAddressForUsage(int zoneId, int usage) {
        checkFsdkNonNull();
        return this.mCore.getOutputDeviceAddressForUsage(zoneId, usage);
    }

    public List<AudioDeviceAttributes> getInputDevicesForZoneId(int zoneId) {
        checkFsdkNonNull();
        return this.mCore.getInputDevicesForZoneId(zoneId);
    }

    public boolean clearZoneIdForUid(int uid) {
        checkFsdkNonNull();
        return this.mCore.clearZoneIdForUid(uid);
    }

    public void registerVolumeCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.registerVolumeCallback(binder);
    }

    public void unregisterVolumeCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.unregisterVolumeCallback(binder);
    }

    public ICarAudioControl getCarAudioControlInstance() {
        checkFsdkNonNull();
        return this.mCarAudioControlImpl;
    }

    public ICarEqualizer getCarEqualizerInstance() {
        checkFsdkNonNull();
        return this.mCarEqualizerImpl;
    }

    public List<AudioFocusInfo> getAudioFocusHoldersForZoneId(int zoneId) {
        checkFsdkNonNull();
        return this.mCore.getAudioFocusHoldersForZoneId(zoneId);
    }

    public List<AudioFocusInfo> getAudioFocusLosersForZoneId(int zoneId) {
        checkFsdkNonNull();
        return this.mCore.getAudioFocusLosersForZoneId(zoneId);
    }

    public void registerFocusCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.registerFocusCallback(binder);
    }

    public void unregisterFocusCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.unregisterFocusCallback(binder);
    }

    public boolean isPlaybackOnVolumeGroupActive(int audioZoneId, int volumeGroupId) {
        checkFsdkNonNull();
        return this.mCore.isPlaybackOnVolumeGroupActive(audioZoneId, volumeGroupId);
    }

    public void adjustVolume(int zoneId, int adjustment, int flags, int step) {
        checkFsdkNonNull();
        this.mCore.adjustVolume(zoneId, adjustment, flags, step);
    }

    public void adjustSuggestedGroupVolume(int zoneId, int groupId, int adjustment, int flags, int step) {
        checkFsdkNonNull();
        this.mCore.adjustSuggestedGroupVolume(zoneId, groupId, adjustment, flags, step);
    }

    public void adjustSuggestedGroupVolumeToMaximum(int zoneId, int groupId, int flags) {
        checkFsdkNonNull();
        this.mCore.adjustSuggestedGroupVolumeToMaximum(zoneId, groupId, flags);
    }

    public void adjustSuggestedGroupVolumeToMinimum(int zoneId, int groupId, int flags) {
        checkFsdkNonNull();
        this.mCore.adjustSuggestedGroupVolumeToMinimum(zoneId, groupId, flags);
    }

    public void registerGroupMutingCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.registerGroupMutingCallback(binder);
    }

    public void unregisterGroupMutingCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.unregisterGroupMutingCallback(binder);
    }

    public void registerVolumeEventCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.registerVolumeEventCallback(binder);
    }

    public void unregisterVolumeEventCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.unregisterVolumeEventCallback(binder);
    }

    public boolean isVolumeGroupMuted(int zoneId, int groupId) {
        checkFsdkNonNull();
        return this.mCore.isVolumeGroupMuted(zoneId, groupId);
    }

    public void setVolumeGroupMute(int zoneId, int groupId, boolean state, int flags) {
        checkFsdkNonNull();
        this.mCore.setVolumeGroupMute(zoneId, groupId, state, flags);
    }

    public void allowBootVolumeAutoAdjustment(int zoneId, boolean allowed) {
        checkFsdkNonNull();
        this.mCore.allowBootVolumeAutoAdjustment(zoneId, allowed);
    }

    public boolean isBootVolumeAutoAdjustmentAllowed(int zoneId) {
        checkFsdkNonNull();
        return this.mCore.isBootVolumeAutoAdjustmentAllowed(zoneId);
    }

    public void duckAudio(int durationMs, int volumeDb) {
        checkFsdkNonNull();
        this.mCore.duckAudio(durationMs, volumeDb);
    }

    public void unduckAudio(int durationMs) {
        checkFsdkNonNull();
        this.mCore.unduckAudio(durationMs);
    }

    public void setParameters(String keyValuePairs) {
        checkFsdkNonNull();
        this.mCore.setParameters(keyValuePairs);
    }

    public String getParameters(String keys) {
        checkFsdkNonNull();
        return this.mCore.getParameters(keys);
    }

    public void setBalanceTowardRightLevel(int level) throws RemoteException {
        checkFsdkNonNull();
        this.mCore.setBalanceTowardRightLevel(level);
    }

    public int getBalanceTowardRightLevel() throws RemoteException {
        checkFsdkNonNull();
        return this.mCore.getBalanceTowardRightLevel();
    }

    public int[] getBalanceTowardRightLevelRange() throws RemoteException {
        checkFsdkNonNull();
        return this.mCore.getBalanceTowardRightLevelRange();
    }

    public void setFadeTowardFrontLevel(int level) throws RemoteException {
        checkFsdkNonNull();
        this.mCore.setFadeTowardFrontLevel(level);
    }

    public int getFadeTowardFrontLevel() throws RemoteException {
        checkFsdkNonNull();
        return this.mCore.getFadeTowardFrontLevel();
    }

    public int[] getFadeTowardFrontLevelRange() throws RemoteException {
        checkFsdkNonNull();
        return this.mCore.getFadeTowardFrontLevelRange();
    }

    public boolean isBalanceTowardRightSupported() throws RemoteException {
        checkFsdkNonNull();
        return this.mCore.isBalanceTowardRightSupported();
    }

    public boolean isFadeTowardFrontSupported() throws RemoteException {
        checkFsdkNonNull();
        return this.mCore.isFadeTowardFrontSupported();
    }

    public void registerBalanceCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.registerBalanceCallback(binder);
    }

    public void unregisterBalanceCallback(IBinder binder) {
        checkFsdkNonNull();
        this.mCore.unregisterBalanceCallback(binder);
    }

    class CarAudioControlImpl extends ICarAudioControl.Stub {
        CarAudioControlImpl() {
        }

        public int getCount() throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getCount();
        }

        public String getName(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getName(index);
        }

        public int getType(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getType(index);
        }

        public int getFlags(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getFlags(index);
        }

        public void setIntegerValue(int index, int value) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mAudioControl.setIntegerValue(index, value);
        }

        public void setFloatValue(int index, float value) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mAudioControl.setFloatValue(index, value);
        }

        public int getIntegerValue(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getIntegerValue(index);
        }

        public float getFloatValue(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getFloatValue(index);
        }

        public int[] getIntegerValueRange(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getIntegerValueRange(index);
        }

        public float[] getFloatValueRange(int index) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mAudioControl.getFloatValueRange(index);
        }

        public void registerAudioControlCallback(IBinder binder) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mAudioControl.registerAudioControlCallback(binder);
        }

        public void unregisterAudioControlCallback(IBinder binder) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mAudioControl.unregisterAudioControlCallback(binder);
        }

        public void updateAudioControlCallbackFilter(IBinder binder, CarAudioControlFilter filter) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mAudioControl.updateAudioControlCallbackFilter(binder, filter);
        }
    }

    class CarEqualizerImpl extends ICarEqualizer.Stub {
        CarEqualizerImpl() {
        }

        public int getNumberOfBands() throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getNumberOfBands();
        }

        public int[] getBandLevelRange(int band) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getBandLevelRange(band);
        }

        public void setBandLevel(int band, int level) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mEqualizer.setBandLevel(band, level);
        }

        public int getBandLevel(int band) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getBandLevel(band);
        }

        public int getCenterFreq(int band) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getCenterFreq(band);
        }

        public int[] getBandFreqRange(int band) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getBandFreqRange(band);
        }

        public int getBand(int frequency) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getBand(frequency);
        }

        public int getCurrentPreset() throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getCurrentPreset();
        }

        public void usePreset(int preset) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mEqualizer.usePreset(preset);
        }

        public int getNumberOfPresets() throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getNumberOfPresets();
        }

        public String getPresetName(int preset) throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.getPresetName(preset);
        }

        public boolean isPresetSupported() throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.isPresetSupported();
        }

        public boolean isBandSupported() throws RemoteException {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            return CarEnhancedAudioService.this.mEqualizer.isBandSupported();
        }

        public void registerEqualizerCallback(IBinder binder) {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mEqualizer.registerEqualizerCallback(binder);
        }

        public void unregisterEqualizerCallback(IBinder binder) {
            CarEnhancedAudioService.this.checkFsdkNonNull();
            CarEnhancedAudioService.this.mEqualizer.unregisterEqualizerCallback(binder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkFsdkNonNull() {
        if (this.mCore == null || this.mAudioControl == null || this.mEqualizer == null) {
            throw new IllegalArgumentException("No ICore implements.");
        }
    }
}
