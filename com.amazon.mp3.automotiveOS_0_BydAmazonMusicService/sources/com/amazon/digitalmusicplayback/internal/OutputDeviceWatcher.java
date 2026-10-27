package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AudioSampleFormat;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class OutputDeviceWatcher {
    private static final long CHANGE_DETECTION_COOLDOWN_MS = 500;
    private static final String FIREOS_CAPABILITIES_SETTING = "audio_platform_capabilities";
    private final Context mApplicationContext;
    private final DriverEventReporter mDriverEventReporter;
    private boolean mIsHiResOutputEnabled;
    private long mLastChangeDetectionTime;
    private long mLastUsedCooldown;
    private final LoggingFacade.Logger mLogger;
    private final PlatformSystemInformation mPlatformSystemInformation;
    private final boolean mRunningOnFireTV;
    private AudioDeviceInfo mWatchedAudioDevice;
    private boolean mWatchedDeviceSupportsIEC;
    private boolean mBasicDDPSupportsAtmos = false;
    private EnumSet<AudioSampleFormat> mIgnoredSampleFormats = EnumSet.noneOf(AudioSampleFormat.class);
    private boolean mOutputDeviceChanged = false;
    private ArrayList<AudioFormat> mSupportedFormats = new ArrayList<>();

    public OutputDeviceWatcher(@NonNull LoggingFacade loggingFacade, @NonNull Context context, @NonNull DriverEventReporter driverEventReporter, @NonNull PlatformSystemInformation platformSystemInformation) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mDriverEventReporter = driverEventReporter;
        this.mPlatformSystemInformation = platformSystemInformation;
        this.mRunningOnFireTV = context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
        this.mIsHiResOutputEnabled = !platformSystemInformation.isRunningOnTV();
        registerForDeviceChanges();
        registerForFireOSCapabilityChanges();
        checkAudioTrack(null);
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mLastChangeDetectionTime = jCurrentTimeMillis;
        this.mLastUsedCooldown = jCurrentTimeMillis;
    }

    public synchronized void checkAudioTrack(@Nullable AudioTrack audioTrack) {
        AudioDeviceInfo audioDeviceInfo;
        readFireOSCapability();
        AudioDeviceInfo routedDevice = null;
        if (audioTrack != null) {
            routedDevice = audioTrack.getRoutedDevice();
            if (routedDevice == null && audioTrack.getPlayState() == 3) {
                this.mLogger.warning("AudioTrack is playing but has no output device? Will use native format only", new Object[0]);
                this.mDriverEventReporter.asyncMetricNoRoutedDevice();
            }
        } else {
            AudioManager audioManager = (AudioManager) this.mApplicationContext.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
            if (audioManager != null) {
                for (AudioDeviceInfo audioDeviceInfo2 : audioManager.getDevices(2)) {
                    if (audioDeviceInfo2.isSink() && (routedDevice == null || routedDevice.getEncodings().length < audioDeviceInfo2.getEncodings().length)) {
                        routedDevice = audioDeviceInfo2;
                    }
                }
            }
            if (routedDevice == null) {
                this.mLogger.warning("No audio output devices? Assuming native format only", new Object[0]);
                this.mDriverEventReporter.asyncMetricNoOutputDevices();
            }
        }
        if (routedDevice != null && ((audioDeviceInfo = this.mWatchedAudioDevice) == null || audioDeviceInfo.getId() != routedDevice.getId())) {
            this.mLogger.debug("Current device id=%s name='%s'", Integer.valueOf(routedDevice.getId()), routedDevice.getProductName());
            this.mLogger.info("Current device id=%s sampleRates=%s channelCounts=%s masks=%s encodings=%s", Integer.valueOf(routedDevice.getId()), Arrays.toString(routedDevice.getSampleRates()), Arrays.toString(routedDevice.getChannelCounts()), Arrays.toString(routedDevice.getChannelIndexMasks()), Arrays.toString(routedDevice.getEncodings()));
            resetIgnoredFormats();
        }
        this.mWatchedAudioDevice = routedDevice;
        updateSupportedFormats();
        setOutputDeviceChanged(false);
    }

    @NonNull
    public synchronized ArrayList<AudioFormat> getSupportedFormats() {
        return this.mSupportedFormats;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasOutputDeviceChanged() {
        /*
            r4 = this;
            monitor-enter(r4)
            long r0 = r4.mLastUsedCooldown     // Catch: java.lang.Throwable -> L1b
            long r2 = r4.mLastChangeDetectionTime     // Catch: java.lang.Throwable -> L1b
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L18
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L1b
            long r2 = r4.mLastChangeDetectionTime     // Catch: java.lang.Throwable -> L1b
            long r0 = r0 - r2
            r2 = 500(0x1f4, double:2.47E-321)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L18
            r0 = 1
            goto L19
        L18:
            r0 = 0
        L19:
            monitor-exit(r4)
            return r0
        L1b:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.digitalmusicplayback.internal.OutputDeviceWatcher.hasOutputDeviceChanged():boolean");
    }

    public synchronized void ignoreSampleFormat(AudioSampleFormat audioSampleFormat) {
        this.mIgnoredSampleFormats.add(audioSampleFormat);
        updateSupportedFormats();
    }

    public synchronized boolean isHiResOutputEnabled() {
        return this.mIsHiResOutputEnabled;
    }

    public synchronized void resetIgnoredFormats() {
        this.mIgnoredSampleFormats.clear();
    }

    public synchronized void setHiResOutputEnabled(boolean z) {
        if (this.mIsHiResOutputEnabled != z) {
            this.mIsHiResOutputEnabled = z;
            this.mLogger.info("setHiResOutputEnabled -> %s", Boolean.valueOf(z));
            updateSupportedFormats();
        }
    }

    public synchronized android.media.AudioFormat toAndroidFormat(AudioFormat audioFormat) {
        int androidChannelMask;
        int i;
        int i2;
        if (this.mWatchedDeviceSupportsIEC && AudioFormats.isMPEGH(audioFormat.getSampleFormat())) {
            i2 = 13;
            this.mLogger.info("Translated %s -> encoding %s", audioFormat.getSampleFormat(), 13);
            i = 192000;
            androidChannelMask = 12;
        } else {
            int sampleRate = (int) audioFormat.getSampleRate();
            int androidEncoding = AudioFormats.toAndroidEncoding(audioFormat.getSampleFormat());
            androidChannelMask = AudioFormats.toAndroidChannelMask(audioFormat.getChannelLayout());
            i = sampleRate;
            i2 = androidEncoding;
        }
        return new AudioFormat.Builder().setChannelMask(androidChannelMask).setEncoding(i2).setSampleRate(i).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setOutputDeviceChanged(boolean z) {
        if (z) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.mLastChangeDetectionTime <= CHANGE_DETECTION_COOLDOWN_MS) {
            } else {
                this.mLastChangeDetectionTime = jCurrentTimeMillis;
            }
        } else {
            this.mLastUsedCooldown = this.mLastChangeDetectionTime;
        }
    }

    private void updateSupportedFormats() {
        ArrayList<com.amazon.digitalmusicplayback.AudioFormat> arrayList = new ArrayList<>();
        if (this.mWatchedAudioDevice != null) {
            for (com.amazon.digitalmusicplayback.AudioFormat audioFormat : AudioFormats.kAllFormats) {
                if (isSupportedFormat(audioFormat)) {
                    arrayList.add(audioFormat);
                }
            }
            if (this.mRunningOnFireTV && this.mBasicDDPSupportsAtmos) {
                com.amazon.digitalmusicplayback.AudioFormat audioFormat2 = AudioFormats.kBasicDDP;
                if (isSupportedFormat(audioFormat2)) {
                    arrayList.add(audioFormat2);
                }
            }
            this.mWatchedDeviceSupportsIEC = intArrayContains(this.mWatchedAudioDevice.getEncodings(), 13);
        } else {
            this.mWatchedDeviceSupportsIEC = false;
        }
        if (arrayList.isEmpty()) {
            com.amazon.digitalmusicplayback.AudioFormat audioFormat3 = AudioFormats.kNativeFormat;
            arrayList.add(audioFormat3);
            if (!this.mRunningOnFireTV) {
                arrayList.add(new com.amazon.digitalmusicplayback.AudioFormat(audioFormat3.getSampleRate(), audioFormat3.getChannelLayout(), AudioSampleFormat.SAMPLEFORMATFLOAT, audioFormat3.getChannelCount()));
            }
        }
        if (this.mSupportedFormats.equals(arrayList)) {
            return;
        }
        boolean z = !this.mSupportedFormats.isEmpty();
        this.mSupportedFormats = arrayList;
        if (z) {
            this.mDriverEventReporter.asyncReportSupportedFormats(arrayList);
        }
        int i = 0;
        while (i < this.mSupportedFormats.size()) {
            int i2 = i + 1;
            this.mLogger.info("Available format %d of %d: %s", Integer.valueOf(i2), Integer.valueOf(this.mSupportedFormats.size()), this.mSupportedFormats.get(i));
            i = i2;
        }
    }

    private boolean isSupportedFormat(com.amazon.digitalmusicplayback.AudioFormat audioFormat) {
        AudioSampleFormat sampleFormat = audioFormat.getSampleFormat();
        AudioSampleFormat audioSampleFormat = AudioSampleFormat.SAMPLEFORMATS16;
        if (sampleFormat != audioSampleFormat && this.mIgnoredSampleFormats.contains(audioFormat.getSampleFormat())) {
            return false;
        }
        if (AudioFormats.isPCM(audioFormat.getSampleFormat())) {
            if (!this.mIsHiResOutputEnabled && audioFormat.getSampleFormat() != audioSampleFormat) {
                return false;
            }
            if (!this.mIsHiResOutputEnabled && audioFormat.getSampleRate() > 48000) {
                return false;
            }
        } else if (!this.mPlatformSystemInformation.isRunningOnTV()) {
            return false;
        }
        if (!intArrayContains(this.mWatchedAudioDevice.getEncodings(), AudioFormats.toAndroidEncoding(audioFormat.getSampleFormat())) || !intArrayContains(this.mWatchedAudioDevice.getSampleRates(), (int) audioFormat.getSampleRate())) {
            return false;
        }
        if (this.mWatchedAudioDevice.getChannelMasks().length == 0) {
            return intArrayContains(this.mWatchedAudioDevice.getChannelCounts(), audioFormat.getChannelCount());
        }
        return intArrayContains(this.mWatchedAudioDevice.getChannelMasks(), AudioFormats.toAndroidChannelMask(audioFormat.getChannelLayout()));
    }

    static boolean intArrayContains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void readFireOSCapability() {
        boolean z = this.mBasicDDPSupportsAtmos;
        String string = null;
        try {
            string = Settings.Global.getString(this.mApplicationContext.getContentResolver(), FIREOS_CAPABILITIES_SETTING);
            if (string == null) {
                this.mBasicDDPSupportsAtmos = false;
            } else {
                this.mBasicDDPSupportsAtmos = new JSONObject(string).getJSONObject("audiocaps").getJSONObject("atmos").getBoolean("enabled");
            }
        } catch (Exception e) {
            this.mLogger.warning("readFireOSCapability: %s", e);
            this.mDriverEventReporter.asyncMetricUnhandledException(e, "readFireOSCapability");
            this.mBasicDDPSupportsAtmos = false;
        }
        boolean z2 = this.mBasicDDPSupportsAtmos;
        if (z != z2) {
            this.mLogger.info("FireOS Atmos capability -> %s %s=%s", Boolean.valueOf(z2), FIREOS_CAPABILITIES_SETTING, string);
            setOutputDeviceChanged(true);
        }
    }

    private void registerForFireOSCapabilityChanges() {
        if (this.mRunningOnFireTV) {
            try {
                this.mApplicationContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor(FIREOS_CAPABILITIES_SETTING), false, new ContentObserver(new Handler()) { // from class: com.amazon.digitalmusicplayback.internal.OutputDeviceWatcher.1
                    @Override // android.database.ContentObserver
                    public void onChange(boolean z) {
                        OutputDeviceWatcher.this.readFireOSCapability();
                    }
                });
            } catch (Exception e) {
                this.mLogger.warning("registerForFireOSCapabilityChanges: %s", e);
                this.mDriverEventReporter.asyncMetricUnhandledException(e, "registerForFireOSCapabilityChanges");
            }
        }
    }

    private void registerForDeviceChanges() {
        AudioManager audioManager = (AudioManager) this.mApplicationContext.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
        if (audioManager == null) {
            return;
        }
        audioManager.registerAudioDeviceCallback(new AudioDeviceCallback() { // from class: com.amazon.digitalmusicplayback.internal.OutputDeviceWatcher.2
            @Override // android.media.AudioDeviceCallback
            public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
                if (isAnySink(audioDeviceInfoArr)) {
                    OutputDeviceWatcher.this.mLogger.info("onAudioDevicesAdded", new Object[0]);
                    OutputDeviceWatcher.this.setOutputDeviceChanged(true);
                }
            }

            @Override // android.media.AudioDeviceCallback
            public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
                if (isAnySink(audioDeviceInfoArr)) {
                    OutputDeviceWatcher.this.mLogger.info("onAudioDevicesRemoved", new Object[0]);
                    OutputDeviceWatcher.this.setOutputDeviceChanged(true);
                }
            }

            private boolean isAnySink(AudioDeviceInfo[] audioDeviceInfoArr) {
                for (AudioDeviceInfo audioDeviceInfo : audioDeviceInfoArr) {
                    if (audioDeviceInfo.isSink()) {
                        return true;
                    }
                }
                return false;
            }
        }, null);
    }
}
