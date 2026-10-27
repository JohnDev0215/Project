package com.amazon.digitalmusicplayback;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Player {
    public abstract void clearCache();

    public abstract void clearRequestSigningCredentials();

    public abstract PlayResponse enqueue(SequenceEntry sequenceEntry, ArrayList<String> arrayList);

    public abstract FrequencySettings getBassSettings();

    public abstract AudioAttributes getCurrentAudioAttributes();

    public abstract long getCurrentTime();

    public abstract DownloadCoordinator getDownloadCoordinator(DownloadCoordinatorListener downloadCoordinatorListener, DownloadCoordinatorConfig downloadCoordinatorConfig);

    public abstract ArrayList<EqualizerSettings> getEqualizerPresets();

    public abstract EqualizerSettings getEqualizerSettings();

    public abstract Long getLiveStreamCurrentTime();

    public abstract Long getLiveStreamStartTime();

    public abstract PlayerLogLevel getLogLevel();

    public abstract DeviceCapability getMaximumDeviceCapability();

    public abstract QualitySetting getOTAAudioQualitySetting();

    public abstract OutputDeviceAttributes getOutputDeviceAttributes();

    public abstract int getPlayingBitrate();

    public abstract int getQueueSizeHint();

    public abstract double getTempo();

    public abstract long getTrackDuration();

    public abstract long getTrackProgressNotificationPeriod();

    public abstract FrequencySettings getTrebleSettings();

    public abstract double getVolume();

    public abstract QualitySetting getWifiAudioQualitySetting();

    public abstract EngineError initialize(PlayerConfig playerConfig, ClientInfo clientInfo, EqualizerFormat equalizerFormat);

    public abstract boolean is24BitEnabled();

    public abstract void logCacheContents();

    public abstract boolean loudnessNormalizationEnabled();

    public abstract boolean muted();

    public abstract void nativeCrash();

    public abstract boolean offlineMode();

    public abstract boolean paused();

    public abstract PlayResponse play(ArrayList<String> arrayList);

    public abstract void playNext(SequenceEntry sequenceEntry);

    public abstract PlayResponse prebuffer(ArrayList<String> arrayList);

    public abstract boolean prioritizeDownloadsPlayback();

    public abstract void refreshRequestSigningCredentials();

    public abstract void removeCachedTrack(String str);

    public abstract void reset();

    public abstract void resumeAndTerminateRemoteStream(String str);

    public abstract void seek(SequenceEntry sequenceEntry, long j);

    public abstract void set24BitEnabled(boolean z);

    public abstract void setCustomerBenefits(EnumSet<CustomerBenefitFlags> enumSet);

    public abstract void setExclusiveMode(boolean z);

    public abstract void setLogLevel(PlayerLogLevel playerLogLevel);

    public abstract void setLoudnessNormalizationMode(boolean z);

    public abstract void setMuted(boolean z);

    public abstract void setNetworkStallTimeout(long j);

    public abstract void setOTAAudioQualitySetting(QualitySetting qualitySetting);

    public abstract void setOfflineMode(boolean z);

    public abstract void setOutputDevice(String str);

    public abstract EngineError setPaused(boolean z);

    public abstract void setPrioritizeDownloadsPlayback(boolean z);

    public abstract void setStreamOnWifiOnly(boolean z);

    public abstract void setTempo(double d);

    public abstract void setThreeDMode(boolean z);

    public abstract void setTrackProgressNotificationPeriod(long j);

    public abstract void setVolume(double d);

    public abstract void setWifiAudioQualitySetting(QualitySetting qualitySetting);

    public abstract void shutdown(Boolean bool);

    public abstract boolean streamOnWifiOnly();

    public abstract boolean threeDMode();

    public abstract void updateBassSettings(short s);

    public abstract void updateEqualizerSettings(ArrayList<EqualizerUpdate> arrayList);

    public abstract void updateTrebleSettings(short s);

    public static boolean isPlayable(String str) {
        return CppProxy.isPlayable(str);
    }

    public static String libraryVersion() {
        return CppProxy.libraryVersion();
    }

    private static final class CppProxy extends Player {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        public static native boolean isPlayable(String str);

        public static native String libraryVersion();

        private native void nativeDestroy(long j);

        private native void native_clearCache(long j);

        private native void native_clearRequestSigningCredentials(long j);

        private native PlayResponse native_enqueue(long j, SequenceEntry sequenceEntry, ArrayList<String> arrayList);

        private native FrequencySettings native_getBassSettings(long j);

        private native AudioAttributes native_getCurrentAudioAttributes(long j);

        private native long native_getCurrentTime(long j);

        private native DownloadCoordinator native_getDownloadCoordinator(long j, DownloadCoordinatorListener downloadCoordinatorListener, DownloadCoordinatorConfig downloadCoordinatorConfig);

        private native ArrayList<EqualizerSettings> native_getEqualizerPresets(long j);

        private native EqualizerSettings native_getEqualizerSettings(long j);

        private native Long native_getLiveStreamCurrentTime(long j);

        private native Long native_getLiveStreamStartTime(long j);

        private native PlayerLogLevel native_getLogLevel(long j);

        private native DeviceCapability native_getMaximumDeviceCapability(long j);

        private native QualitySetting native_getOTAAudioQualitySetting(long j);

        private native OutputDeviceAttributes native_getOutputDeviceAttributes(long j);

        private native int native_getPlayingBitrate(long j);

        private native int native_getQueueSizeHint(long j);

        private native double native_getTempo(long j);

        private native long native_getTrackDuration(long j);

        private native long native_getTrackProgressNotificationPeriod(long j);

        private native FrequencySettings native_getTrebleSettings(long j);

        private native double native_getVolume(long j);

        private native QualitySetting native_getWifiAudioQualitySetting(long j);

        private native EngineError native_initialize(long j, PlayerConfig playerConfig, ClientInfo clientInfo, EqualizerFormat equalizerFormat);

        private native boolean native_is24BitEnabled(long j);

        private native void native_logCacheContents(long j);

        private native boolean native_loudnessNormalizationEnabled(long j);

        private native boolean native_muted(long j);

        private native void native_nativeCrash(long j);

        private native boolean native_offlineMode(long j);

        private native boolean native_paused(long j);

        private native PlayResponse native_play(long j, ArrayList<String> arrayList);

        private native void native_playNext(long j, SequenceEntry sequenceEntry);

        private native PlayResponse native_prebuffer(long j, ArrayList<String> arrayList);

        private native boolean native_prioritizeDownloadsPlayback(long j);

        private native void native_refreshRequestSigningCredentials(long j);

        private native void native_removeCachedTrack(long j, String str);

        private native void native_reset(long j);

        private native void native_resumeAndTerminateRemoteStream(long j, String str);

        private native void native_seek(long j, SequenceEntry sequenceEntry, long j2);

        private native void native_set24BitEnabled(long j, boolean z);

        private native void native_setCustomerBenefits(long j, EnumSet<CustomerBenefitFlags> enumSet);

        private native void native_setExclusiveMode(long j, boolean z);

        private native void native_setLogLevel(long j, PlayerLogLevel playerLogLevel);

        private native void native_setLoudnessNormalizationMode(long j, boolean z);

        private native void native_setMuted(long j, boolean z);

        private native void native_setNetworkStallTimeout(long j, long j2);

        private native void native_setOTAAudioQualitySetting(long j, QualitySetting qualitySetting);

        private native void native_setOfflineMode(long j, boolean z);

        private native void native_setOutputDevice(long j, String str);

        private native EngineError native_setPaused(long j, boolean z);

        private native void native_setPrioritizeDownloadsPlayback(long j, boolean z);

        private native void native_setStreamOnWifiOnly(long j, boolean z);

        private native void native_setTempo(long j, double d);

        private native void native_setThreeDMode(long j, boolean z);

        private native void native_setTrackProgressNotificationPeriod(long j, long j2);

        private native void native_setVolume(long j, double d);

        private native void native_setWifiAudioQualitySetting(long j, QualitySetting qualitySetting);

        private native void native_shutdown(long j, Boolean bool);

        private native boolean native_streamOnWifiOnly(long j);

        private native boolean native_threeDMode(long j);

        private native void native_updateBassSettings(long j, short s);

        private native void native_updateEqualizerSettings(long j, ArrayList<EqualizerUpdate> arrayList);

        private native void native_updateTrebleSettings(long j, short s);

        static {
            try {
                int i = PlayerFactory.$r8$clinit;
            } catch (ClassNotFoundException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
        }

        public void _djinni_private_destroy() {
            if (this.destroyed.getAndSet(true)) {
                return;
            }
            nativeDestroy(this.nativeRef);
        }

        protected void finalize() throws Throwable {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public DownloadCoordinator getDownloadCoordinator(DownloadCoordinatorListener downloadCoordinatorListener, DownloadCoordinatorConfig downloadCoordinatorConfig) {
            return native_getDownloadCoordinator(this.nativeRef, downloadCoordinatorListener, downloadCoordinatorConfig);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public EngineError initialize(PlayerConfig playerConfig, ClientInfo clientInfo, EqualizerFormat equalizerFormat) {
            return native_initialize(this.nativeRef, playerConfig, clientInfo, equalizerFormat);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public PlayResponse play(ArrayList<String> arrayList) {
            return native_play(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public PlayResponse enqueue(SequenceEntry sequenceEntry, ArrayList<String> arrayList) {
            return native_enqueue(this.nativeRef, sequenceEntry, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public PlayResponse prebuffer(ArrayList<String> arrayList) {
            return native_prebuffer(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void playNext(SequenceEntry sequenceEntry) {
            native_playNext(this.nativeRef, sequenceEntry);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void seek(SequenceEntry sequenceEntry, long j) {
            native_seek(this.nativeRef, sequenceEntry, j);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void shutdown(Boolean bool) {
            native_shutdown(this.nativeRef, bool);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public double getVolume() {
            return native_getVolume(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setVolume(double d) {
            native_setVolume(this.nativeRef, d);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public int getQueueSizeHint() {
            return native_getQueueSizeHint(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public long getCurrentTime() {
            return native_getCurrentTime(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean paused() {
            return native_paused(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public EngineError setPaused(boolean z) {
            return native_setPaused(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public int getPlayingBitrate() {
            return native_getPlayingBitrate(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public long getTrackDuration() {
            return native_getTrackDuration(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void reset() {
            native_reset(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public Long getLiveStreamCurrentTime() {
            return native_getLiveStreamCurrentTime(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public Long getLiveStreamStartTime() {
            return native_getLiveStreamStartTime(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public PlayerLogLevel getLogLevel() {
            return native_getLogLevel(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setLogLevel(PlayerLogLevel playerLogLevel) {
            native_setLogLevel(this.nativeRef, playerLogLevel);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setWifiAudioQualitySetting(QualitySetting qualitySetting) {
            native_setWifiAudioQualitySetting(this.nativeRef, qualitySetting);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public QualitySetting getWifiAudioQualitySetting() {
            return native_getWifiAudioQualitySetting(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setOTAAudioQualitySetting(QualitySetting qualitySetting) {
            native_setOTAAudioQualitySetting(this.nativeRef, qualitySetting);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public QualitySetting getOTAAudioQualitySetting() {
            return native_getOTAAudioQualitySetting(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void refreshRequestSigningCredentials() {
            native_refreshRequestSigningCredentials(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void clearRequestSigningCredentials() {
            native_clearRequestSigningCredentials(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setTrackProgressNotificationPeriod(long j) {
            native_setTrackProgressNotificationPeriod(this.nativeRef, j);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void resumeAndTerminateRemoteStream(String str) {
            native_resumeAndTerminateRemoteStream(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public long getTrackProgressNotificationPeriod() {
            return native_getTrackProgressNotificationPeriod(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setNetworkStallTimeout(long j) {
            native_setNetworkStallTimeout(this.nativeRef, j);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean muted() {
            return native_muted(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setMuted(boolean z) {
            native_setMuted(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setStreamOnWifiOnly(boolean z) {
            native_setStreamOnWifiOnly(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean streamOnWifiOnly() {
            return native_streamOnWifiOnly(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setOfflineMode(boolean z) {
            native_setOfflineMode(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean offlineMode() {
            return native_offlineMode(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public EqualizerSettings getEqualizerSettings() {
            return native_getEqualizerSettings(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public ArrayList<EqualizerSettings> getEqualizerPresets() {
            return native_getEqualizerPresets(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void updateEqualizerSettings(ArrayList<EqualizerUpdate> arrayList) {
            native_updateEqualizerSettings(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setLoudnessNormalizationMode(boolean z) {
            native_setLoudnessNormalizationMode(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean loudnessNormalizationEnabled() {
            return native_loudnessNormalizationEnabled(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public FrequencySettings getBassSettings() {
            return native_getBassSettings(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void updateBassSettings(short s) {
            native_updateBassSettings(this.nativeRef, s);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public FrequencySettings getTrebleSettings() {
            return native_getTrebleSettings(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void updateTrebleSettings(short s) {
            native_updateTrebleSettings(this.nativeRef, s);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public DeviceCapability getMaximumDeviceCapability() {
            return native_getMaximumDeviceCapability(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public AudioAttributes getCurrentAudioAttributes() {
            return native_getCurrentAudioAttributes(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public OutputDeviceAttributes getOutputDeviceAttributes() {
            return native_getOutputDeviceAttributes(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setOutputDevice(String str) {
            native_setOutputDevice(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setExclusiveMode(boolean z) {
            native_setExclusiveMode(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setCustomerBenefits(EnumSet<CustomerBenefitFlags> enumSet) {
            native_setCustomerBenefits(this.nativeRef, enumSet);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void set24BitEnabled(boolean z) {
            native_set24BitEnabled(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean is24BitEnabled() {
            return native_is24BitEnabled(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void clearCache() {
            native_clearCache(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void removeCachedTrack(String str) {
            native_removeCachedTrack(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void logCacheContents() {
            native_logCacheContents(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setThreeDMode(boolean z) {
            native_setThreeDMode(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean threeDMode() {
            return native_threeDMode(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setTempo(double d) {
            native_setTempo(this.nativeRef, d);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public double getTempo() {
            return native_getTempo(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void setPrioritizeDownloadsPlayback(boolean z) {
            native_setPrioritizeDownloadsPlayback(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public boolean prioritizeDownloadsPlayback() {
            return native_prioritizeDownloadsPlayback(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.Player
        public void nativeCrash() {
            native_nativeCrash(this.nativeRef);
        }
    }
}
