package android.media;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRouting;
import android.media.IAudioService;
import android.media.audiopolicy.AudioMix;
import android.media.audiopolicy.AudioPolicy;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class AudioRecord implements AudioRouting, MicrophoneDirection, AudioRecordingMonitor, AudioRecordingMonitorClient {
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDCHANNELMASK = -17;
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDFORMAT = -18;
    private static final int AUDIORECORD_ERROR_SETUP_INVALIDSOURCE = -19;
    private static final int AUDIORECORD_ERROR_SETUP_NATIVEINITFAILED = -20;
    private static final int AUDIORECORD_ERROR_SETUP_ZEROFRAMECOUNT = -16;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    private static final int NATIVE_EVENT_MARKER = 2;
    private static final int NATIVE_EVENT_NEW_POS = 3;
    public static final int READ_BLOCKING = 0;
    public static final int READ_NON_BLOCKING = 1;
    public static final int RECORDSTATE_RECORDING = 3;
    public static final int RECORDSTATE_STOPPED = 1;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_UNINITIALIZED = 0;
    public static final String SUBMIX_FIXED_VOLUME = "fixedVolume";
    public static final int SUCCESS = 0;
    private static final String TAG = "android.media.AudioRecord";
    private AudioAttributes mAudioAttributes;
    private AudioPolicy mAudioCapturePolicy;
    private int mAudioFormat;
    private int mChannelCount;
    private int mChannelIndexMask;
    private int mChannelMask;
    private NativeEventHandler mEventHandler;
    private final IBinder mICallBack;
    private Looper mInitializationLooper;
    private boolean mIsSubmixFullVolume;
    private int mNativeBufferSizeInBytes;
    private long mNativeCallbackCookie;
    private long mNativeDeviceCallback;
    private long mNativeRecorderInJavaObj;
    private OnRecordPositionUpdateListener mPositionListener;
    private final Object mPositionListenerLock;
    private AudioDeviceInfo mPreferredDevice;
    private int mRecordSource;
    AudioRecordingMonitorImpl mRecordingInfoImpl;
    private int mRecordingState;
    private final Object mRecordingStateLock;
    private ArrayMap<AudioRouting.OnRoutingChangedListener, NativeRoutingEventHandlerDelegate> mRoutingChangeListeners;
    private int mSampleRate;
    private int mSessionId;
    private int mState;

    public interface OnRecordPositionUpdateListener {
        void onMarkerReached(AudioRecord audioRecord);

        void onPeriodicNotification(AudioRecord audioRecord);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReadMode {
    }

    private final native void native_disableDeviceCallback();

    private final native void native_enableDeviceCallback();

    private final native void native_finalize();

    private native PersistableBundle native_getMetrics();

    private native int native_getPortId();

    private final native int native_getRoutedDeviceId();

    private final native int native_get_active_microphones(ArrayList<MicrophoneInfo> arrayList);

    private final native int native_get_buffer_size_in_frames();

    private final native int native_get_marker_pos();

    private static final native int native_get_min_buff_size(int i, int i2, int i3);

    private final native int native_get_pos_update_period();

    private final native int native_get_timestamp(AudioTimestamp audioTimestamp, int i);

    private final native int native_read_in_byte_array(byte[] bArr, int i, int i2, boolean z);

    private final native int native_read_in_direct_buffer(Object obj, int i, boolean z);

    private final native int native_read_in_float_array(float[] fArr, int i, int i2, boolean z);

    private final native int native_read_in_short_array(short[] sArr, int i, int i2, boolean z);

    private final native boolean native_setInputDevice(int i);

    private final native int native_set_marker_pos(int i);

    private final native int native_set_pos_update_period(int i);

    private native int native_set_preferred_microphone_direction(int i);

    private native int native_set_preferred_microphone_field_dimension(float f);

    private final native int native_setup(Object obj, Object obj2, int[] iArr, int i, int i2, int i3, int i4, int[] iArr2, String str, long j);

    private final native int native_start(int i, int i2);

    private final native void native_stop();

    public final native void native_release();

    public AudioRecord(int i, int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        this(new AudioAttributes.Builder().setInternalCapturePreset(i).build(), new AudioFormat.Builder().setChannelMask(getChannelMaskFromLegacyConfig(i3, true)).setEncoding(i4).setSampleRate(i2).build(), i5, 0);
    }

    @SystemApi
    public AudioRecord(AudioAttributes audioAttributes, AudioFormat audioFormat, int i, int i2) throws IllegalArgumentException {
        this.mState = 0;
        this.mRecordingState = 1;
        this.mRecordingStateLock = new Object();
        this.mPositionListener = null;
        this.mPositionListenerLock = new Object();
        this.mEventHandler = null;
        this.mInitializationLooper = null;
        this.mNativeBufferSizeInBytes = 0;
        this.mSessionId = 0;
        this.mIsSubmixFullVolume = false;
        this.mICallBack = new Binder();
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mPreferredDevice = null;
        this.mRecordingInfoImpl = new AudioRecordingMonitorImpl(this);
        this.mRecordingState = 1;
        if (audioAttributes == null) {
            throw new IllegalArgumentException("Illegal null AudioAttributes");
        }
        if (audioFormat == null) {
            throw new IllegalArgumentException("Illegal null AudioFormat");
        }
        Looper looperMyLooper = Looper.myLooper();
        this.mInitializationLooper = looperMyLooper;
        if (looperMyLooper == null) {
            this.mInitializationLooper = Looper.getMainLooper();
        }
        if (audioAttributes.getCapturePreset() == 8) {
            AudioAttributes.Builder builder = new AudioAttributes.Builder();
            for (String str : audioAttributes.getTags()) {
                if (str.equalsIgnoreCase(SUBMIX_FIXED_VOLUME)) {
                    this.mIsSubmixFullVolume = true;
                    Log.v(TAG, "Will record from REMOTE_SUBMIX at full fixed volume");
                } else {
                    builder.addTag(str);
                }
            }
            builder.setInternalCapturePreset(audioAttributes.getCapturePreset());
            this.mAudioAttributes = builder.build();
        } else {
            this.mAudioAttributes = audioAttributes;
        }
        int sampleRate = audioFormat.getSampleRate();
        audioParamCheck(audioAttributes.getCapturePreset(), sampleRate == 0 ? 0 : sampleRate, (audioFormat.getPropertySetMask() & 1) != 0 ? audioFormat.getEncoding() : 1);
        if ((audioFormat.getPropertySetMask() & 8) != 0) {
            this.mChannelIndexMask = audioFormat.getChannelIndexMask();
            this.mChannelCount = audioFormat.getChannelCount();
        }
        if ((audioFormat.getPropertySetMask() & 4) != 0) {
            this.mChannelMask = getChannelMaskFromLegacyConfig(audioFormat.getChannelMask(), false);
            this.mChannelCount = audioFormat.getChannelCount();
        } else if (this.mChannelIndexMask == 0) {
            int channelMaskFromLegacyConfig = getChannelMaskFromLegacyConfig(1, false);
            this.mChannelMask = channelMaskFromLegacyConfig;
            this.mChannelCount = AudioFormat.channelCountFromInChannelMask(channelMaskFromLegacyConfig);
        }
        audioBuffSizeCheck(i);
        int[] iArr = {this.mSampleRate};
        int[] iArr2 = {i2};
        int iNative_setup = native_setup(new WeakReference(this), this.mAudioAttributes, iArr, this.mChannelMask, this.mChannelIndexMask, this.mAudioFormat, this.mNativeBufferSizeInBytes, iArr2, getCurrentOpPackageName(), 0L);
        if (iNative_setup != 0) {
            loge("Error code " + iNative_setup + " when initializing native AudioRecord object.");
            return;
        }
        this.mSampleRate = iArr[0];
        this.mSessionId = iArr2[0];
        this.mState = 1;
    }

    private String getCurrentOpPackageName() {
        String strCurrentOpPackageName = ActivityThread.currentOpPackageName();
        return strCurrentOpPackageName != null ? strCurrentOpPackageName : "uid:" + Binder.getCallingUid();
    }

    AudioRecord(long j) {
        this.mState = 0;
        this.mRecordingState = 1;
        this.mRecordingStateLock = new Object();
        this.mPositionListener = null;
        this.mPositionListenerLock = new Object();
        this.mEventHandler = null;
        this.mInitializationLooper = null;
        this.mNativeBufferSizeInBytes = 0;
        this.mSessionId = 0;
        this.mIsSubmixFullVolume = false;
        this.mICallBack = new Binder();
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mPreferredDevice = null;
        this.mRecordingInfoImpl = new AudioRecordingMonitorImpl(this);
        this.mNativeRecorderInJavaObj = 0L;
        this.mNativeCallbackCookie = 0L;
        this.mNativeDeviceCallback = 0L;
        if (j != 0) {
            deferred_connect(j);
        } else {
            this.mState = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterAudioPolicyOnRelease(AudioPolicy audioPolicy) {
        this.mAudioCapturePolicy = audioPolicy;
    }

    void deferred_connect(long j) {
        if (this.mState != 1) {
            int[] iArr = {0};
            int iNative_setup = native_setup(new WeakReference(this), null, new int[]{0}, 0, 0, 0, 0, iArr, ActivityThread.currentOpPackageName(), j);
            if (iNative_setup != 0) {
                loge("Error code " + iNative_setup + " when initializing native AudioRecord object.");
            } else {
                this.mSessionId = iArr[0];
                this.mState = 1;
            }
        }
    }

    public static class Builder {
        private static final String ERROR_MESSAGE_SOURCE_MISMATCH = "Cannot both set audio source and set playback capture config";
        private static final int PRIVACY_SENSITIVE_DEFAULT = -1;
        private static final int PRIVACY_SENSITIVE_DISABLED = 0;
        private static final int PRIVACY_SENSITIVE_ENABLED = 1;
        private AudioAttributes mAttributes;
        private AudioPlaybackCaptureConfiguration mAudioPlaybackCaptureConfiguration;
        private int mBufferSizeInBytes;
        private AudioFormat mFormat;
        private int mSessionId = 0;
        private int mPrivacySensitive = -1;

        public Builder setAudioSource(int i) throws IllegalArgumentException {
            Preconditions.checkState(this.mAudioPlaybackCaptureConfiguration == null, ERROR_MESSAGE_SOURCE_MISMATCH);
            if (i < 0 || i > MediaRecorder.getAudioSourceMax()) {
                throw new IllegalArgumentException("Invalid audio source " + i);
            }
            this.mAttributes = new AudioAttributes.Builder().setInternalCapturePreset(i).build();
            return this;
        }

        @SystemApi
        public Builder setAudioAttributes(AudioAttributes audioAttributes) throws IllegalArgumentException {
            if (audioAttributes == null) {
                throw new IllegalArgumentException("Illegal null AudioAttributes argument");
            }
            if (audioAttributes.getCapturePreset() == -1) {
                throw new IllegalArgumentException("No valid capture preset in AudioAttributes argument");
            }
            this.mAttributes = audioAttributes;
            return this;
        }

        public Builder setAudioFormat(AudioFormat audioFormat) throws IllegalArgumentException {
            if (audioFormat == null) {
                throw new IllegalArgumentException("Illegal null AudioFormat argument");
            }
            this.mFormat = audioFormat;
            return this;
        }

        public Builder setBufferSizeInBytes(int i) throws IllegalArgumentException {
            if (i <= 0) {
                throw new IllegalArgumentException("Invalid buffer size " + i);
            }
            this.mBufferSizeInBytes = i;
            return this;
        }

        public Builder setAudioPlaybackCaptureConfig(AudioPlaybackCaptureConfiguration audioPlaybackCaptureConfiguration) {
            Preconditions.checkNotNull(audioPlaybackCaptureConfiguration, "Illegal null AudioPlaybackCaptureConfiguration argument");
            Preconditions.checkState(this.mAttributes == null, ERROR_MESSAGE_SOURCE_MISMATCH);
            this.mAudioPlaybackCaptureConfiguration = audioPlaybackCaptureConfiguration;
            return this;
        }

        public Builder setPrivacySensitive(boolean z) {
            this.mPrivacySensitive = z ? 1 : 0;
            return this;
        }

        @SystemApi
        public Builder setSessionId(int i) throws IllegalArgumentException {
            if (i < 0) {
                throw new IllegalArgumentException("Invalid session ID " + i);
            }
            this.mSessionId = i;
            return this;
        }

        private AudioRecord buildAudioPlaybackCaptureRecord() {
            AudioMix audioMixCreateAudioMix = this.mAudioPlaybackCaptureConfiguration.createAudioMix(this.mFormat);
            AudioPolicy audioPolicyBuild = new AudioPolicy.Builder(null).setMediaProjection(this.mAudioPlaybackCaptureConfiguration.getMediaProjection()).addMix(audioMixCreateAudioMix).build();
            if (AudioManager.registerAudioPolicyStatic(audioPolicyBuild) != 0) {
                throw new UnsupportedOperationException("Error: could not register audio policy");
            }
            AudioRecord audioRecordCreateAudioRecordSink = audioPolicyBuild.createAudioRecordSink(audioMixCreateAudioMix);
            if (audioRecordCreateAudioRecordSink != null) {
                audioRecordCreateAudioRecordSink.unregisterAudioPolicyOnRelease(audioPolicyBuild);
                return audioRecordCreateAudioRecordSink;
            }
            throw new UnsupportedOperationException("Cannot create AudioRecord");
        }

        public AudioRecord build() throws UnsupportedOperationException {
            if (this.mAudioPlaybackCaptureConfiguration != null) {
                return buildAudioPlaybackCaptureRecord();
            }
            AudioFormat audioFormat = this.mFormat;
            if (audioFormat == null) {
                this.mFormat = new AudioFormat.Builder().setEncoding(2).setChannelMask(16).build();
            } else {
                if (audioFormat.getEncoding() == 0) {
                    this.mFormat = new AudioFormat.Builder(this.mFormat).setEncoding(2).build();
                }
                if (this.mFormat.getChannelMask() == 0 && this.mFormat.getChannelIndexMask() == 0) {
                    this.mFormat = new AudioFormat.Builder(this.mFormat).setChannelMask(16).build();
                }
            }
            if (this.mAttributes == null) {
                this.mAttributes = new AudioAttributes.Builder().setInternalCapturePreset(0).build();
            }
            if (this.mPrivacySensitive != -1) {
                int capturePreset = this.mAttributes.getCapturePreset();
                if (capturePreset == 8 || capturePreset == 1998 || capturePreset == 3 || capturePreset == 2 || capturePreset == 4 || capturePreset == 1997) {
                    throw new UnsupportedOperationException("Cannot request private capture with source: " + capturePreset);
                }
                this.mAttributes = new AudioAttributes.Builder(this.mAttributes).setInternalCapturePreset(capturePreset).setPrivacySensitive(this.mPrivacySensitive == 1).build();
            }
            try {
                if (this.mBufferSizeInBytes == 0) {
                    this.mBufferSizeInBytes = this.mFormat.getChannelCount() * AudioFormat.getBytesPerSample(this.mFormat.getEncoding());
                }
                AudioRecord audioRecord = new AudioRecord(this.mAttributes, this.mFormat, this.mBufferSizeInBytes, this.mSessionId);
                if (audioRecord.getState() != 0) {
                    return audioRecord;
                }
                throw new UnsupportedOperationException("Cannot create AudioRecord");
            } catch (IllegalArgumentException e) {
                throw new UnsupportedOperationException(e.getMessage());
            }
        }
    }

    private static int getChannelMaskFromLegacyConfig(int i, boolean z) {
        int i2 = 16;
        if (i != 1 && i != 2) {
            if (i == 3 || i == 12) {
                i2 = 12;
            } else if (i != 16) {
                if (i != 48) {
                    throw new IllegalArgumentException("Unsupported channel configuration.");
                }
                i2 = i;
            }
        }
        if (z || !(i == 2 || i == 3)) {
            return i2;
        }
        throw new IllegalArgumentException("Unsupported deprecated configuration.");
    }

    private void audioParamCheck(int i, int i2, int i3) throws IllegalArgumentException {
        if (i < 0 || (i > MediaRecorder.getAudioSourceMax() && i != 1998 && i != 1997 && i != 1999)) {
            throw new IllegalArgumentException("Invalid audio source " + i);
        }
        this.mRecordSource = i;
        if ((i2 < 4000 || i2 > 192000) && i2 != 0) {
            throw new IllegalArgumentException(i2 + "Hz is not a supported sample rate.");
        }
        this.mSampleRate = i2;
        if (i3 == 1) {
            this.mAudioFormat = 2;
        } else {
            if (i3 == 2 || i3 == 3 || i3 == 4) {
                this.mAudioFormat = i3;
                return;
            }
            throw new IllegalArgumentException("Unsupported sample encoding " + i3 + ". Should be ENCODING_PCM_8BIT, ENCODING_PCM_16BIT, or ENCODING_PCM_FLOAT.");
        }
    }

    private void audioBuffSizeCheck(int i) throws IllegalArgumentException {
        int bytesPerSample = this.mChannelCount * AudioFormat.getBytesPerSample(this.mAudioFormat);
        if (i % bytesPerSample != 0 || i < 1) {
            throw new IllegalArgumentException("Invalid audio buffer size " + i + " (frame size " + bytesPerSample + ")");
        }
        this.mNativeBufferSizeInBytes = i;
    }

    public void release() {
        try {
            stop();
        } catch (IllegalStateException unused) {
        }
        AudioPolicy audioPolicy = this.mAudioCapturePolicy;
        if (audioPolicy != null) {
            AudioManager.unregisterAudioPolicyAsyncStatic(audioPolicy);
            this.mAudioCapturePolicy = null;
        }
        native_release();
        this.mState = 0;
    }

    protected void finalize() {
        release();
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getAudioSource() {
        return this.mRecordSource;
    }

    public int getAudioFormat() {
        return this.mAudioFormat;
    }

    public int getChannelConfiguration() {
        return this.mChannelMask;
    }

    public AudioFormat getFormat() {
        AudioFormat.Builder encoding = new AudioFormat.Builder().setSampleRate(this.mSampleRate).setEncoding(this.mAudioFormat);
        int i = this.mChannelMask;
        if (i != 0) {
            encoding.setChannelMask(i);
        }
        int i2 = this.mChannelIndexMask;
        if (i2 != 0) {
            encoding.setChannelIndexMask(i2);
        }
        return encoding.build();
    }

    public int getChannelCount() {
        return this.mChannelCount;
    }

    public int getState() {
        return this.mState;
    }

    public int getRecordingState() {
        int i;
        synchronized (this.mRecordingStateLock) {
            i = this.mRecordingState;
        }
        return i;
    }

    public int getBufferSizeInFrames() {
        return native_get_buffer_size_in_frames();
    }

    public int getNotificationMarkerPosition() {
        return native_get_marker_pos();
    }

    public int getPositionNotificationPeriod() {
        return native_get_pos_update_period();
    }

    public int getTimestamp(AudioTimestamp audioTimestamp, int i) {
        if (audioTimestamp == null || (i != 1 && i != 0)) {
            throw new IllegalArgumentException();
        }
        return native_get_timestamp(audioTimestamp, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getMinBufferSize(int r4, int r5, int r6) {
        /*
            r0 = -2
            r1 = 2
            r2 = 1
            if (r5 == r2) goto L1c
            if (r5 == r1) goto L1c
            r3 = 3
            if (r5 == r3) goto L1d
            r3 = 12
            if (r5 == r3) goto L1d
            r3 = 16
            if (r5 == r3) goto L1c
            r2 = 48
            if (r5 == r2) goto L1d
            java.lang.String r4 = "getMinBufferSize(): Invalid channel configuration."
            loge(r4)
            return r0
        L1c:
            r1 = r2
        L1d:
            int r4 = native_get_min_buff_size(r4, r1, r6)
            if (r4 != 0) goto L24
            return r0
        L24:
            r5 = -1
            if (r4 != r5) goto L28
            return r5
        L28:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioRecord.getMinBufferSize(int, int, int):int");
    }

    public int getAudioSessionId() {
        return this.mSessionId;
    }

    public boolean isPrivacySensitive() {
        return (this.mAudioAttributes.getAllFlags() & 8192) != 0;
    }

    public void startRecording() throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        }
        synchronized (this.mRecordingStateLock) {
            if (native_start(0, 0) == 0) {
                handleFullVolumeRec(true);
                this.mRecordingState = 3;
            }
        }
    }

    public void startRecording(MediaSyncEvent mediaSyncEvent) throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("startRecording() called on an uninitialized AudioRecord.");
        }
        synchronized (this.mRecordingStateLock) {
            if (native_start(mediaSyncEvent.getType(), mediaSyncEvent.getAudioSessionId()) == 0) {
                handleFullVolumeRec(true);
                this.mRecordingState = 3;
            }
        }
    }

    public void stop() throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("stop() called on an uninitialized AudioRecord.");
        }
        synchronized (this.mRecordingStateLock) {
            handleFullVolumeRec(false);
            native_stop();
            this.mRecordingState = 1;
        }
    }

    private void handleFullVolumeRec(boolean z) {
        if (this.mIsSubmixFullVolume) {
            try {
                IAudioService.Stub.asInterface(ServiceManager.getService("audio")).forceRemoteSubmixFullVolume(z, this.mICallBack);
            } catch (RemoteException e) {
                Log.e(TAG, "Error talking to AudioService when handling full submix volume", e);
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        return read(bArr, i, i2, 0);
    }

    public int read(byte[] bArr, int i, int i2, int i3) {
        int i4;
        if (this.mState != 1 || this.mAudioFormat == 4) {
            return -3;
        }
        if (i3 != 0 && i3 != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        if (bArr == null || i < 0 || i2 < 0 || (i4 = i + i2) < 0 || i4 > bArr.length) {
            return -2;
        }
        return native_read_in_byte_array(bArr, i, i2, i3 == 0);
    }

    public int read(short[] sArr, int i, int i2) {
        return read(sArr, i, i2, 0);
    }

    public int read(short[] sArr, int i, int i2, int i3) {
        int i4;
        if (this.mState != 1 || this.mAudioFormat == 4) {
            return -3;
        }
        if (i3 != 0 && i3 != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        if (sArr == null || i < 0 || i2 < 0 || (i4 = i + i2) < 0 || i4 > sArr.length) {
            return -2;
        }
        return native_read_in_short_array(sArr, i, i2, i3 == 0);
    }

    public int read(float[] fArr, int i, int i2, int i3) {
        int i4;
        if (this.mState == 0) {
            Log.e(TAG, "AudioRecord.read() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if (this.mAudioFormat != 4) {
            Log.e(TAG, "AudioRecord.read(float[] ...) requires format ENCODING_PCM_FLOAT");
            return -3;
        }
        if (i3 != 0 && i3 != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        if (fArr == null || i < 0 || i2 < 0 || (i4 = i + i2) < 0 || i4 > fArr.length) {
            return -2;
        }
        return native_read_in_float_array(fArr, i, i2, i3 == 0);
    }

    public int read(ByteBuffer byteBuffer, int i) {
        return read(byteBuffer, i, 0);
    }

    public int read(ByteBuffer byteBuffer, int i, int i2) {
        if (this.mState != 1) {
            return -3;
        }
        if (i2 != 0 && i2 != 1) {
            Log.e(TAG, "AudioRecord.read() called with invalid blocking mode");
            return -2;
        }
        if (byteBuffer == null || i < 0) {
            return -2;
        }
        return native_read_in_direct_buffer(byteBuffer, i, i2 == 0);
    }

    public PersistableBundle getMetrics() {
        return native_getMetrics();
    }

    public void setRecordPositionUpdateListener(OnRecordPositionUpdateListener onRecordPositionUpdateListener) {
        setRecordPositionUpdateListener(onRecordPositionUpdateListener, null);
    }

    public void setRecordPositionUpdateListener(OnRecordPositionUpdateListener onRecordPositionUpdateListener, Handler handler) {
        synchronized (this.mPositionListenerLock) {
            this.mPositionListener = onRecordPositionUpdateListener;
            if (onRecordPositionUpdateListener == null) {
                this.mEventHandler = null;
            } else if (handler != null) {
                this.mEventHandler = new NativeEventHandler(this, handler.getLooper());
            } else {
                this.mEventHandler = new NativeEventHandler(this, this.mInitializationLooper);
            }
        }
    }

    public int setNotificationMarkerPosition(int i) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_marker_pos(i);
    }

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getRoutedDevice() {
        int iNative_getRoutedDeviceId = native_getRoutedDeviceId();
        if (iNative_getRoutedDeviceId == 0) {
            return null;
        }
        AudioDeviceInfo[] devicesStatic = AudioManager.getDevicesStatic(1);
        for (int i = 0; i < devicesStatic.length; i++) {
            if (devicesStatic[i].getId() == iNative_getRoutedDeviceId) {
                return devicesStatic[i];
            }
        }
        return null;
    }

    private void testEnableNativeRoutingCallbacksLocked() {
        if (this.mRoutingChangeListeners.size() == 0) {
            native_enableDeviceCallback();
        }
    }

    private void testDisableNativeRoutingCallbacksLocked() {
        if (this.mRoutingChangeListeners.size() == 0) {
            native_disableDeviceCallback();
        }
    }

    @Override // android.media.AudioRouting
    public void addOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener onRoutingChangedListener, Handler handler) {
        synchronized (this.mRoutingChangeListeners) {
            if (onRoutingChangedListener != null) {
                if (!this.mRoutingChangeListeners.containsKey(onRoutingChangedListener)) {
                    testEnableNativeRoutingCallbacksLocked();
                    ArrayMap<AudioRouting.OnRoutingChangedListener, NativeRoutingEventHandlerDelegate> arrayMap = this.mRoutingChangeListeners;
                    if (handler == null) {
                        handler = new Handler(this.mInitializationLooper);
                    }
                    arrayMap.put(onRoutingChangedListener, new NativeRoutingEventHandlerDelegate(this, onRoutingChangedListener, handler));
                }
            }
        }
    }

    @Override // android.media.AudioRouting
    public void removeOnRoutingChangedListener(AudioRouting.OnRoutingChangedListener onRoutingChangedListener) {
        synchronized (this.mRoutingChangeListeners) {
            if (this.mRoutingChangeListeners.containsKey(onRoutingChangedListener)) {
                this.mRoutingChangeListeners.remove(onRoutingChangedListener);
                testDisableNativeRoutingCallbacksLocked();
            }
        }
    }

    @Deprecated
    public interface OnRoutingChangedListener extends AudioRouting.OnRoutingChangedListener {
        void onRoutingChanged(AudioRecord audioRecord);

        @Override // android.media.AudioRouting.OnRoutingChangedListener
        default void onRoutingChanged(AudioRouting audioRouting) {
            if (audioRouting instanceof AudioRecord) {
                onRoutingChanged((AudioRecord) audioRouting);
            }
        }
    }

    @Deprecated
    public void addOnRoutingChangedListener(OnRoutingChangedListener onRoutingChangedListener, Handler handler) {
        addOnRoutingChangedListener((AudioRouting.OnRoutingChangedListener) onRoutingChangedListener, handler);
    }

    @Deprecated
    public void removeOnRoutingChangedListener(OnRoutingChangedListener onRoutingChangedListener) {
        removeOnRoutingChangedListener((AudioRouting.OnRoutingChangedListener) onRoutingChangedListener);
    }

    private void broadcastRoutingChange() {
        AudioManager.resetAudioPortGeneration();
        synchronized (this.mRoutingChangeListeners) {
            Iterator<NativeRoutingEventHandlerDelegate> it = this.mRoutingChangeListeners.values().iterator();
            while (it.hasNext()) {
                it.next().notifyClient();
            }
        }
    }

    public int setPositionNotificationPeriod(int i) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_pos_update_period(i);
    }

    @Override // android.media.AudioRouting
    public boolean setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        if (audioDeviceInfo != null && !audioDeviceInfo.isSource()) {
            return false;
        }
        boolean zNative_setInputDevice = native_setInputDevice(audioDeviceInfo != null ? audioDeviceInfo.getId() : 0);
        if (zNative_setInputDevice) {
            synchronized (this) {
                this.mPreferredDevice = audioDeviceInfo;
            }
        }
        return zNative_setInputDevice;
    }

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getPreferredDevice() {
        AudioDeviceInfo audioDeviceInfo;
        synchronized (this) {
            audioDeviceInfo = this.mPreferredDevice;
        }
        return audioDeviceInfo;
    }

    public List<MicrophoneInfo> getActiveMicrophones() throws IOException {
        AudioDeviceInfo routedDevice;
        ArrayList<MicrophoneInfo> arrayList = new ArrayList<>();
        int iNative_get_active_microphones = native_get_active_microphones(arrayList);
        if (iNative_get_active_microphones != 0) {
            if (iNative_get_active_microphones != -3) {
                Log.e(TAG, "getActiveMicrophones failed:" + iNative_get_active_microphones);
            }
            Log.i(TAG, "getActiveMicrophones failed, fallback on routed device info");
        }
        AudioManager.setPortIdForMicrophones(arrayList);
        if (arrayList.size() == 0 && (routedDevice = getRoutedDevice()) != null) {
            MicrophoneInfo microphoneInfoMicrophoneInfoFromAudioDeviceInfo = AudioManager.microphoneInfoFromAudioDeviceInfo(routedDevice);
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < this.mChannelCount; i++) {
                arrayList2.add(new Pair(Integer.valueOf(i), 1));
            }
            microphoneInfoMicrophoneInfoFromAudioDeviceInfo.setChannelMapping(arrayList2);
            arrayList.add(microphoneInfoMicrophoneInfoFromAudioDeviceInfo);
        }
        return arrayList;
    }

    @Override // android.media.AudioRecordingMonitor
    public void registerAudioRecordingCallback(Executor executor, AudioManager.AudioRecordingCallback audioRecordingCallback) {
        this.mRecordingInfoImpl.registerAudioRecordingCallback(executor, audioRecordingCallback);
    }

    @Override // android.media.AudioRecordingMonitor
    public void unregisterAudioRecordingCallback(AudioManager.AudioRecordingCallback audioRecordingCallback) {
        this.mRecordingInfoImpl.unregisterAudioRecordingCallback(audioRecordingCallback);
    }

    @Override // android.media.AudioRecordingMonitor
    public AudioRecordingConfiguration getActiveRecordingConfiguration() {
        return this.mRecordingInfoImpl.getActiveRecordingConfiguration();
    }

    @Override // android.media.AudioRecordingMonitorClient
    public int getPortId() {
        if (this.mNativeRecorderInJavaObj == 0) {
            return 0;
        }
        return native_getPortId();
    }

    @Override // android.media.MicrophoneDirection
    public boolean setPreferredMicrophoneDirection(int i) {
        return native_set_preferred_microphone_direction(i) == 0;
    }

    @Override // android.media.MicrophoneDirection
    public boolean setPreferredMicrophoneFieldDimension(float f) {
        Preconditions.checkArgument(f >= -1.0f && f <= 1.0f, "Argument must fall between -1 & 1 (inclusive)");
        return native_set_preferred_microphone_field_dimension(f) == 0;
    }

    private class NativeEventHandler extends Handler {
        private final AudioRecord mAudioRecord;

        NativeEventHandler(AudioRecord audioRecord, Looper looper) {
            super(looper);
            this.mAudioRecord = audioRecord;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            OnRecordPositionUpdateListener onRecordPositionUpdateListener;
            synchronized (AudioRecord.this.mPositionListenerLock) {
                onRecordPositionUpdateListener = this.mAudioRecord.mPositionListener;
            }
            int i = message.what;
            if (i == 2) {
                if (onRecordPositionUpdateListener != null) {
                    onRecordPositionUpdateListener.onMarkerReached(this.mAudioRecord);
                }
            } else if (i != 3) {
                AudioRecord.loge("Unknown native event type: " + message.what);
            } else if (onRecordPositionUpdateListener != null) {
                onRecordPositionUpdateListener.onPeriodicNotification(this.mAudioRecord);
            }
        }
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        AudioRecord audioRecord = (AudioRecord) ((WeakReference) obj).get();
        if (audioRecord == null) {
            return;
        }
        if (i == 1000) {
            audioRecord.broadcastRoutingChange();
            return;
        }
        NativeEventHandler nativeEventHandler = audioRecord.mEventHandler;
        if (nativeEventHandler != null) {
            audioRecord.mEventHandler.sendMessage(nativeEventHandler.obtainMessage(i, i2, i3, obj2));
        }
    }

    private static void logd(String str) {
        Log.d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String str) {
        Log.e(TAG, str);
    }

    public static final class MetricsConstants {
        public static final String ATTRIBUTES = "android.media.audiorecord.attributes";
        public static final String CHANNELS = "android.media.audiorecord.channels";
        public static final String CHANNEL_MASK = "android.media.audiorecord.channelMask";
        public static final String DURATION_MS = "android.media.audiorecord.durationMs";
        public static final String ENCODING = "android.media.audiorecord.encoding";
        public static final String FRAME_COUNT = "android.media.audiorecord.frameCount";

        @Deprecated
        public static final String LATENCY = "android.media.audiorecord.latency";
        private static final String MM_PREFIX = "android.media.audiorecord.";
        public static final String PORT_ID = "android.media.audiorecord.portId";
        public static final String SAMPLERATE = "android.media.audiorecord.samplerate";
        public static final String SOURCE = "android.media.audiorecord.source";
        public static final String START_COUNT = "android.media.audiorecord.startCount";

        private MetricsConstants() {
        }
    }
}
