package android.media;

import android.annotation.SystemApi;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioMetadata;
import android.media.AudioRouting;
import android.media.Utils;
import android.media.VolumeShaper;
import android.opengl.GLES30;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.NioUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrack extends PlayerBase implements AudioRouting, VolumeAutomation {
    private static final int AUDIO_OUTPUT_FLAG_DEEP_BUFFER = 8;
    private static final int AUDIO_OUTPUT_FLAG_FAST = 4;
    public static final int DUAL_MONO_MODE_LL = 2;
    public static final int DUAL_MONO_MODE_LR = 1;
    public static final int DUAL_MONO_MODE_OFF = 0;
    public static final int DUAL_MONO_MODE_RR = 3;
    public static final int ENCAPSULATION_METADATA_TYPE_DVB_AD_DESCRIPTOR = 2;
    public static final int ENCAPSULATION_METADATA_TYPE_FRAMEWORK_TUNER = 1;
    public static final int ENCAPSULATION_METADATA_TYPE_NONE = 0;
    public static final int ENCAPSULATION_MODE_ELEMENTARY_STREAM = 1;

    @SystemApi
    public static final int ENCAPSULATION_MODE_HANDLE = 2;
    public static final int ENCAPSULATION_MODE_NONE = 0;
    public static final int ERROR = -1;
    public static final int ERROR_BAD_VALUE = -2;
    public static final int ERROR_DEAD_OBJECT = -6;
    public static final int ERROR_INVALID_OPERATION = -3;
    private static final int ERROR_NATIVESETUP_AUDIOSYSTEM = -16;
    private static final int ERROR_NATIVESETUP_INVALIDCHANNELMASK = -17;
    private static final int ERROR_NATIVESETUP_INVALIDFORMAT = -18;
    private static final int ERROR_NATIVESETUP_INVALIDSTREAMTYPE = -19;
    private static final int ERROR_NATIVESETUP_NATIVEINITFAILED = -20;
    public static final int ERROR_WOULD_BLOCK = -7;
    private static final float GAIN_MAX = 1.0f;
    private static final float GAIN_MIN = 0.0f;
    private static final float HEADER_V2_SIZE_BYTES = 20.0f;
    private static final float MAX_AUDIO_DESCRIPTION_MIX_LEVEL = 48.0f;
    public static final int MODE_STATIC = 0;
    public static final int MODE_STREAM = 1;
    private static final int NATIVE_EVENT_CAN_WRITE_MORE_DATA = 9;
    private static final int NATIVE_EVENT_CODEC_FORMAT_CHANGE = 100;
    private static final int NATIVE_EVENT_MARKER = 3;
    private static final int NATIVE_EVENT_NEW_IAUDIOTRACK = 6;
    private static final int NATIVE_EVENT_NEW_POS = 4;
    private static final int NATIVE_EVENT_STREAM_END = 7;
    public static final int PERFORMANCE_MODE_LOW_LATENCY = 1;
    public static final int PERFORMANCE_MODE_NONE = 0;
    public static final int PERFORMANCE_MODE_POWER_SAVING = 2;
    public static final int PLAYSTATE_PAUSED = 2;
    private static final int PLAYSTATE_PAUSED_STOPPING = 5;
    public static final int PLAYSTATE_PLAYING = 3;
    public static final int PLAYSTATE_STOPPED = 1;
    private static final int PLAYSTATE_STOPPING = 4;
    public static final int STATE_INITIALIZED = 1;
    public static final int STATE_NO_STATIC_DATA = 2;
    public static final int STATE_UNINITIALIZED = 0;
    public static final int SUCCESS = 0;
    private static final int SUPPORTED_OUT_CHANNELS = 7420;
    private static final String TAG = "android.media.AudioTrack";
    public static final int WRITE_BLOCKING = 0;
    public static final int WRITE_NON_BLOCKING = 1;
    private int mAudioFormat;
    private int mAvSyncBytesRemaining;
    private ByteBuffer mAvSyncHeader;
    private int mChannelConfiguration;
    private int mChannelCount;
    private int mChannelIndexMask;
    private int mChannelMask;
    private final Utils.ListenerList<AudioMetadataReadMap> mCodecFormatChangedListeners;
    private AudioAttributes mConfiguredAudioAttributes;
    private int mDataLoadMode;
    private NativePositionEventHandlerDelegate mEventHandlerDelegate;
    private final Looper mInitializationLooper;
    private long mJniData;
    private int mNativeBufferSizeInBytes;
    private int mNativeBufferSizeInFrames;
    protected long mNativeTrackInJavaObj;
    private int mOffloadDelayFrames;
    private boolean mOffloadEosPending;
    private int mOffloadPaddingFrames;
    private boolean mOffloaded;
    private int mOffset;
    private int mPlayState;
    private final Object mPlayStateLock;
    private AudioDeviceInfo mPreferredDevice;
    private ArrayMap<AudioRouting.OnRoutingChangedListener, NativeRoutingEventHandlerDelegate> mRoutingChangeListeners;
    private int mSampleRate;
    private int mSessionId;
    private int mState;
    private LinkedList<StreamEventCbInfo> mStreamEventCbInfoList;
    private final Object mStreamEventCbLock;
    private volatile StreamEventHandler mStreamEventHandler;
    private HandlerThread mStreamEventHandlerThread;
    private int mStreamType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DualMonoMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EncapsulationMetadataType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EncapsulationMode {
    }

    public interface OnCodecFormatChangedListener {
        void onCodecFormatChanged(AudioTrack audioTrack, AudioMetadataReadMap audioMetadataReadMap);
    }

    public interface OnPlaybackPositionUpdateListener {
        void onMarkerReached(AudioTrack audioTrack);

        void onPeriodicNotification(AudioTrack audioTrack);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PerformanceMode {
    }

    public static abstract class StreamEventCallback {
        public void onDataRequest(AudioTrack audioTrack, int i) {
        }

        public void onPresentationEnded(AudioTrack audioTrack) {
        }

        public void onTearDown(AudioTrack audioTrack) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransferMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WriteMode {
    }

    public static float getMaxVolume() {
        return 1.0f;
    }

    public static float getMinVolume() {
        return 0.0f;
    }

    private static boolean isValidDualMonoMode(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3;
    }

    private native int native_applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation);

    private final native int native_attachAuxEffect(int i);

    private final native void native_disableDeviceCallback();

    private final native void native_enableDeviceCallback();

    private final native void native_finalize();

    private final native void native_flush();

    private native PersistableBundle native_getMetrics();

    private native int native_getPortId();

    private final native int native_getRoutedDeviceId();

    private native VolumeShaper.State native_getVolumeShaperState(int i);

    private native int native_get_audio_description_mix_level_db(float[] fArr);

    private final native int native_get_buffer_capacity_frames();

    private final native int native_get_buffer_size_frames();

    private native int native_get_dual_mono_mode(int[] iArr);

    private final native int native_get_flags();

    private final native int native_get_latency();

    private final native int native_get_marker_pos();

    private static final native int native_get_min_buff_size(int i, int i2, int i3);

    private static final native int native_get_output_sample_rate(int i);

    private final native PlaybackParams native_get_playback_params();

    private final native int native_get_playback_rate();

    private final native int native_get_pos_update_period();

    private final native int native_get_position();

    private final native int native_get_timestamp(long[] jArr);

    private final native int native_get_underrun_count();

    private static native boolean native_is_direct_output_supported(int i, int i2, int i3, int i4, int i5, int i6, int i7);

    private final native void native_pause();

    private final native int native_reload_static();

    private final native int native_setAuxEffectSendLevel(float f);

    private final native boolean native_setOutputDevice(int i);

    private final native int native_setPresentation(int i, int i2);

    private final native void native_setVolume(float f, float f2);

    private native int native_set_audio_description_mix_level_db(float f);

    private final native int native_set_buffer_size_frames(int i);

    private native void native_set_delay_padding(int i, int i2);

    private native int native_set_dual_mono_mode(int i);

    private final native int native_set_loop(int i, int i2, int i3);

    private final native int native_set_marker_pos(int i);

    private final native void native_set_playback_params(PlaybackParams playbackParams);

    private final native int native_set_playback_rate(int i);

    private final native int native_set_pos_update_period(int i);

    private final native int native_set_position(int i);

    private final native int native_setup(Object obj, Object obj2, int[] iArr, int i, int i2, int i3, int i4, int i5, int[] iArr2, long j, boolean z, int i6, Object obj3, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void native_start();

    private final native void native_stop();

    private final native int native_write_byte(byte[] bArr, int i, int i2, int i3, boolean z);

    private final native int native_write_float(float[] fArr, int i, int i2, int i3, boolean z);

    private final native int native_write_native_bytes(ByteBuffer byteBuffer, int i, int i2, int i3, boolean z);

    private final native int native_write_short(short[] sArr, int i, int i2, int i3, boolean z);

    public final native void native_release();

    public AudioTrack(int i, int i2, int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        this(i, i2, i3, i4, i5, i6, 0);
    }

    public AudioTrack(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        this(new AudioAttributes.Builder().setLegacyStreamType(i).build(), new AudioFormat.Builder().setChannelMask(i3).setEncoding(i4).setSampleRate(i2).build(), i5, i6, i7);
        deprecateStreamTypeForPlayback(i, "AudioTrack", "AudioTrack()");
    }

    public AudioTrack(AudioAttributes audioAttributes, AudioFormat audioFormat, int i, int i2, int i3) throws IllegalArgumentException {
        this(audioAttributes, audioFormat, i, i2, i3, false, 0, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x017c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private AudioTrack(android.media.AudioAttributes r22, android.media.AudioFormat r23, int r24, int r25, int r26, boolean r27, int r28, android.media.AudioTrack.TunerConfiguration r29) throws java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioTrack.<init>(android.media.AudioAttributes, android.media.AudioFormat, int, int, int, boolean, int, android.media.AudioTrack$TunerConfiguration):void");
    }

    AudioTrack(long j) {
        super(new AudioAttributes.Builder().build(), 1);
        this.mState = 0;
        this.mPlayState = 1;
        this.mOffloadEosPending = false;
        this.mPlayStateLock = new Object();
        this.mNativeBufferSizeInBytes = 0;
        this.mNativeBufferSizeInFrames = 0;
        this.mChannelCount = 1;
        this.mChannelMask = 4;
        this.mStreamType = 3;
        this.mDataLoadMode = 1;
        this.mChannelConfiguration = 4;
        this.mChannelIndexMask = 0;
        this.mSessionId = 0;
        this.mAvSyncHeader = null;
        this.mAvSyncBytesRemaining = 0;
        this.mOffset = 0;
        this.mOffloaded = false;
        this.mOffloadDelayFrames = 0;
        this.mOffloadPaddingFrames = 0;
        this.mPreferredDevice = null;
        this.mRoutingChangeListeners = new ArrayMap<>();
        this.mCodecFormatChangedListeners = new Utils.ListenerList<>();
        this.mStreamEventCbLock = new Object();
        this.mStreamEventCbInfoList = new LinkedList<>();
        this.mNativeTrackInJavaObj = 0L;
        this.mJniData = 0L;
        Looper looperMyLooper = Looper.myLooper();
        this.mInitializationLooper = looperMyLooper == null ? Looper.getMainLooper() : looperMyLooper;
        if (j != 0) {
            baseRegisterPlayer();
            deferred_connect(j);
        } else {
            this.mState = 0;
        }
    }

    void deferred_connect(long j) {
        if (this.mState != 1) {
            int[] iArr = {0};
            int iNative_setup = native_setup(new WeakReference(this), null, new int[]{0}, 0, 0, 0, 0, 0, iArr, j, false, 0, null, "");
            if (iNative_setup != 0) {
                loge("Error code " + iNative_setup + " when initializing AudioTrack.");
            } else {
                this.mSessionId = iArr[0];
                this.mState = 1;
            }
        }
    }

    @SystemApi
    public static class TunerConfiguration {
        private final int mContentId;
        private final int mSyncId;

        public TunerConfiguration(int i, int i2) {
            if (i < 1) {
                throw new IllegalArgumentException("contentId " + i + " must be positive");
            }
            if (i2 < 1) {
                throw new IllegalArgumentException("syncId " + i2 + " must be positive");
            }
            this.mContentId = i;
            this.mSyncId = i2;
        }

        public int getContentId() {
            return this.mContentId;
        }

        public int getSyncId() {
            return this.mSyncId;
        }
    }

    public static class Builder {
        private AudioAttributes mAttributes;
        private int mBufferSizeInBytes;
        private AudioFormat mFormat;
        private TunerConfiguration mTunerConfiguration;
        private int mEncapsulationMode = 0;
        private int mSessionId = 0;
        private int mMode = 1;
        private int mPerformanceMode = 0;
        private boolean mOffload = false;

        public Builder setAudioAttributes(AudioAttributes audioAttributes) throws IllegalArgumentException {
            if (audioAttributes == null) {
                throw new IllegalArgumentException("Illegal null AudioAttributes argument");
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

        public Builder setEncapsulationMode(int i) {
            if (i == 0 || i == 1 || i == 2) {
                this.mEncapsulationMode = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid encapsulation mode " + i);
        }

        public Builder setTransferMode(int i) throws IllegalArgumentException {
            if (i == 0 || i == 1) {
                this.mMode = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid transfer mode " + i);
        }

        public Builder setSessionId(int i) throws IllegalArgumentException {
            if (i != 0 && i < 1) {
                throw new IllegalArgumentException("Invalid audio session ID " + i);
            }
            this.mSessionId = i;
            return this;
        }

        public Builder setPerformanceMode(int i) {
            if (i == 0 || i == 1 || i == 2) {
                this.mPerformanceMode = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid performance mode " + i);
        }

        public Builder setOffloadedPlayback(boolean z) {
            this.mOffload = z;
            return this;
        }

        @SystemApi
        public Builder setTunerConfiguration(TunerConfiguration tunerConfiguration) {
            if (tunerConfiguration == null) {
                throw new IllegalArgumentException("tunerConfiguration is null");
            }
            this.mTunerConfiguration = tunerConfiguration;
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.media.AudioTrack build() throws java.lang.UnsupportedOperationException {
            /*
                Method dump skipped, instruction units count: 236
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.media.AudioTrack.Builder.build():android.media.AudioTrack");
        }
    }

    public void setOffloadDelayPadding(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Illegal negative padding");
        }
        if (i < 0) {
            throw new IllegalArgumentException("Illegal negative delay");
        }
        if (!this.mOffloaded) {
            throw new IllegalStateException("Illegal use of delay/padding on non-offloaded track");
        }
        if (this.mState == 0) {
            throw new IllegalStateException("Uninitialized track");
        }
        this.mOffloadDelayFrames = i;
        this.mOffloadPaddingFrames = i2;
        native_set_delay_padding(i, i2);
    }

    public int getOffloadDelay() {
        if (!this.mOffloaded) {
            throw new IllegalStateException("Illegal query of delay on non-offloaded track");
        }
        if (this.mState == 0) {
            throw new IllegalStateException("Illegal query of delay on uninitialized track");
        }
        return this.mOffloadDelayFrames;
    }

    public int getOffloadPadding() {
        if (!this.mOffloaded) {
            throw new IllegalStateException("Illegal query of padding on non-offloaded track");
        }
        if (this.mState == 0) {
            throw new IllegalStateException("Illegal query of padding on uninitialized track");
        }
        return this.mOffloadPaddingFrames;
    }

    public void setOffloadEndOfStream() {
        if (!this.mOffloaded) {
            throw new IllegalStateException("EOS not supported on non-offloaded track");
        }
        if (this.mState == 0) {
            throw new IllegalStateException("Uninitialized track");
        }
        if (this.mPlayState != 3) {
            throw new IllegalStateException("EOS not supported if not playing");
        }
        synchronized (this.mStreamEventCbLock) {
            if (this.mStreamEventCbInfoList.size() == 0) {
                throw new IllegalStateException("EOS not supported without StreamEventCallback");
            }
        }
        synchronized (this.mPlayStateLock) {
            native_stop();
            this.mOffloadEosPending = true;
            this.mPlayState = 4;
        }
    }

    public boolean isOffloadedPlayback() {
        return this.mOffloaded;
    }

    public static boolean isDirectPlaybackSupported(AudioFormat audioFormat, AudioAttributes audioAttributes) {
        if (audioFormat == null) {
            throw new IllegalArgumentException("Illegal null AudioFormat argument");
        }
        if (audioAttributes == null) {
            throw new IllegalArgumentException("Illegal null AudioAttributes argument");
        }
        return native_is_direct_output_supported(audioFormat.getEncoding(), audioFormat.getSampleRate(), audioFormat.getChannelMask(), audioFormat.getChannelIndexMask(), audioAttributes.getContentType(), audioAttributes.getUsage(), audioAttributes.getFlags());
    }

    private static boolean isValidAudioDescriptionMixLevel(float f) {
        return !Float.isNaN(f) && f <= MAX_AUDIO_DESCRIPTION_MIX_LEVEL;
    }

    public boolean setAudioDescriptionMixLeveldB(float f) {
        if (isValidAudioDescriptionMixLevel(f)) {
            return native_set_audio_description_mix_level_db(f) == 0;
        }
        throw new IllegalArgumentException("level is out of range" + f);
    }

    public float getAudioDescriptionMixLeveldB() {
        float[] fArr = {Float.NEGATIVE_INFINITY};
        try {
            if (native_get_audio_description_mix_level_db(fArr) == 0) {
                if (!Float.isNaN(fArr[0])) {
                    return fArr[0];
                }
            }
        } catch (Exception unused) {
        }
        return Float.NEGATIVE_INFINITY;
    }

    public boolean setDualMonoMode(int i) {
        if (isValidDualMonoMode(i)) {
            return native_set_dual_mono_mode(i) == 0;
        }
        throw new IllegalArgumentException("Invalid Dual Mono mode " + i);
    }

    public int getDualMonoMode() {
        int[] iArr = {0};
        try {
            if (native_get_dual_mono_mode(iArr) == 0) {
                if (isValidDualMonoMode(iArr[0])) {
                    return iArr[0];
                }
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldEnablePowerSaving(AudioAttributes audioAttributes, AudioFormat audioFormat, int i, int i2) {
        int allFlags = audioAttributes.getAllFlags() & 792;
        if ((audioAttributes == null || (allFlags == 0 && audioAttributes.getUsage() == 1 && (audioAttributes.getContentType() == 0 || audioAttributes.getContentType() == 2 || audioAttributes.getContentType() == 3))) && audioFormat != null && audioFormat.getSampleRate() != 0 && AudioFormat.isEncodingLinearPcm(audioFormat.getEncoding()) && AudioFormat.isValidEncoding(audioFormat.getEncoding()) && audioFormat.getChannelCount() >= 1 && i2 == 1) {
            return i == 0 || ((long) i) >= (((((long) audioFormat.getChannelCount()) * 100) * ((long) AudioFormat.getBytesPerSample(audioFormat.getEncoding()))) * ((long) audioFormat.getSampleRate())) / 1000;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void audioParamCheck(int r5, int r6, int r7, int r8, int r9) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioTrack.audioParamCheck(int, int, int, int, int):void");
    }

    private static boolean isMultichannelConfigSupported(int i) {
        if ((i & SUPPORTED_OUT_CHANNELS) != i) {
            loge("Channel configuration features unsupported channels");
            return false;
        }
        int iChannelCountFromOutChannelMask = AudioFormat.channelCountFromOutChannelMask(i);
        if (iChannelCountFromOutChannelMask > AudioSystem.OUT_CHANNEL_COUNT_MAX) {
            loge("Channel configuration contains too many channels " + iChannelCountFromOutChannelMask + ">" + AudioSystem.OUT_CHANNEL_COUNT_MAX);
            return false;
        }
        if ((i & 12) != 12) {
            loge("Front channels must be present in multichannel configurations");
            return false;
        }
        int i2 = i & 192;
        if (i2 != 0 && i2 != 192) {
            loge("Rear channels can't be used independently");
            return false;
        }
        int i3 = i & GLES30.GL_COLOR;
        if (i3 == 0 || i3 == 6144) {
            return true;
        }
        loge("Side channels can't be used independently");
        return false;
    }

    private void audioBuffSizeCheck(int i) {
        int bytesPerSample = AudioFormat.isEncodingLinearFrames(this.mAudioFormat) ? this.mChannelCount * AudioFormat.getBytesPerSample(this.mAudioFormat) : 1;
        if (i % bytesPerSample != 0 || i < 1) {
            throw new IllegalArgumentException("Invalid audio buffer size.");
        }
        this.mNativeBufferSizeInBytes = i;
        this.mNativeBufferSizeInFrames = i / bytesPerSample;
    }

    public void release() {
        synchronized (this.mStreamEventCbLock) {
            endStreamEventHandling();
        }
        try {
            stop();
        } catch (IllegalStateException unused) {
        }
        baseRelease();
        native_release();
        synchronized (this.mPlayStateLock) {
            this.mState = 0;
            this.mPlayState = 1;
            this.mPlayStateLock.notify();
        }
    }

    protected void finalize() {
        baseRelease();
        native_finalize();
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public int getPlaybackRate() {
        return native_get_playback_rate();
    }

    public PlaybackParams getPlaybackParams() {
        return native_get_playback_params();
    }

    public AudioAttributes getAudioAttributes() {
        AudioAttributes audioAttributes;
        if (this.mState == 0 || (audioAttributes = this.mConfiguredAudioAttributes) == null) {
            throw new IllegalStateException("track not initialized");
        }
        return audioAttributes;
    }

    public int getAudioFormat() {
        return this.mAudioFormat;
    }

    public int getStreamType() {
        return this.mStreamType;
    }

    public int getChannelConfiguration() {
        return this.mChannelConfiguration;
    }

    public AudioFormat getFormat() {
        AudioFormat.Builder encoding = new AudioFormat.Builder().setSampleRate(this.mSampleRate).setEncoding(this.mAudioFormat);
        int i = this.mChannelConfiguration;
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

    public int getPlayState() {
        synchronized (this.mPlayStateLock) {
            int i = this.mPlayState;
            if (i == 4) {
                return 3;
            }
            if (i != 5) {
                return i;
            }
            return 2;
        }
    }

    public int getBufferSizeInFrames() {
        return native_get_buffer_size_frames();
    }

    public int setBufferSizeInFrames(int i) {
        if (this.mDataLoadMode == 0 || this.mState == 0) {
            return -3;
        }
        if (i < 0) {
            return -2;
        }
        return native_set_buffer_size_frames(i);
    }

    public int getBufferCapacityInFrames() {
        return native_get_buffer_capacity_frames();
    }

    @Deprecated
    protected int getNativeFrameCount() {
        return native_get_buffer_capacity_frames();
    }

    public int getNotificationMarkerPosition() {
        return native_get_marker_pos();
    }

    public int getPositionNotificationPeriod() {
        return native_get_pos_update_period();
    }

    public int getPlaybackHeadPosition() {
        return native_get_position();
    }

    public int getLatency() {
        return native_get_latency();
    }

    public int getUnderrunCount() {
        return native_get_underrun_count();
    }

    public int getPerformanceMode() {
        int iNative_get_flags = native_get_flags();
        if ((iNative_get_flags & 4) != 0) {
            return 1;
        }
        return (iNative_get_flags & 8) != 0 ? 2 : 0;
    }

    public static int getNativeOutputSampleRate(int i) {
        return native_get_output_sample_rate(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getMinBufferSize(int r3, int r4, int r5) {
        /*
            r0 = 2
            r1 = -2
            if (r4 == r0) goto L1f
            r2 = 3
            if (r4 == r2) goto L20
            r2 = 4
            if (r4 == r2) goto L1f
            r2 = 12
            if (r4 == r2) goto L20
            boolean r0 = isMultichannelConfigSupported(r4)
            if (r0 != 0) goto L1a
            java.lang.String r3 = "getMinBufferSize(): Invalid channel configuration."
            loge(r3)
            return r1
        L1a:
            int r0 = android.media.AudioFormat.channelCountFromOutChannelMask(r4)
            goto L20
        L1f:
            r0 = 1
        L20:
            boolean r4 = android.media.AudioFormat.isPublicEncoding(r5)
            if (r4 != 0) goto L2c
            java.lang.String r3 = "getMinBufferSize(): Invalid audio format."
            loge(r3)
            return r1
        L2c:
            r4 = 4000(0xfa0, float:5.605E-42)
            if (r3 < r4) goto L43
            r4 = 192000(0x2ee00, float:2.6905E-40)
            if (r3 <= r4) goto L36
            goto L43
        L36:
            int r3 = native_get_min_buff_size(r3, r0, r5)
            if (r3 > 0) goto L42
            java.lang.String r3 = "getMinBufferSize(): error querying hardware"
            loge(r3)
            r3 = -1
        L42:
            return r3
        L43:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getMinBufferSize(): "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = " Hz is not a supported sample rate."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            loge(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.media.AudioTrack.getMinBufferSize(int, int, int):int");
    }

    public int getAudioSessionId() {
        return this.mSessionId;
    }

    public boolean getTimestamp(AudioTimestamp audioTimestamp) {
        if (audioTimestamp == null) {
            throw new IllegalArgumentException();
        }
        long[] jArr = new long[2];
        if (native_get_timestamp(jArr) != 0) {
            return false;
        }
        audioTimestamp.framePosition = jArr[0];
        audioTimestamp.nanoTime = jArr[1];
        return true;
    }

    public int getTimestampWithStatus(AudioTimestamp audioTimestamp) {
        if (audioTimestamp == null) {
            throw new IllegalArgumentException();
        }
        long[] jArr = new long[2];
        int iNative_get_timestamp = native_get_timestamp(jArr);
        audioTimestamp.framePosition = jArr[0];
        audioTimestamp.nanoTime = jArr[1];
        return iNative_get_timestamp;
    }

    public PersistableBundle getMetrics() {
        return native_getMetrics();
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener) {
        setPlaybackPositionUpdateListener(onPlaybackPositionUpdateListener, null);
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener, Handler handler) {
        if (onPlaybackPositionUpdateListener != null) {
            this.mEventHandlerDelegate = new NativePositionEventHandlerDelegate(this, onPlaybackPositionUpdateListener, handler);
        } else {
            this.mEventHandlerDelegate = null;
        }
    }

    private static float clampGainOrLevel(float f) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException();
        }
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > 1.0f) {
            return 1.0f;
        }
        return f;
    }

    @Deprecated
    public int setStereoVolume(float f, float f2) {
        if (this.mState == 0) {
            return -3;
        }
        baseSetVolume(f, f2);
        return 0;
    }

    @Override // android.media.PlayerBase
    void playerSetVolume(boolean z, float f, float f2) {
        if (z) {
            f = 0.0f;
        }
        float fClampGainOrLevel = clampGainOrLevel(f);
        if (z) {
            f2 = 0.0f;
        }
        native_setVolume(fClampGainOrLevel, clampGainOrLevel(f2));
    }

    public int setVolume(float f) {
        return setStereoVolume(f, f);
    }

    @Override // android.media.PlayerBase
    int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation) {
        return native_applyVolumeShaper(configuration, operation);
    }

    @Override // android.media.PlayerBase
    VolumeShaper.State playerGetVolumeShaperState(int i) {
        return native_getVolumeShaperState(i);
    }

    @Override // android.media.VolumeAutomation
    public VolumeShaper createVolumeShaper(VolumeShaper.Configuration configuration) {
        return new VolumeShaper(configuration, this);
    }

    public int setPlaybackRate(int i) {
        if (this.mState != 1) {
            return -3;
        }
        if (i <= 0) {
            return -2;
        }
        return native_set_playback_rate(i);
    }

    public void setPlaybackParams(PlaybackParams playbackParams) {
        if (playbackParams == null) {
            throw new IllegalArgumentException("params is null");
        }
        native_set_playback_params(playbackParams);
    }

    public int setNotificationMarkerPosition(int i) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_marker_pos(i);
    }

    public int setPositionNotificationPeriod(int i) {
        if (this.mState == 0) {
            return -3;
        }
        return native_set_pos_update_period(i);
    }

    public int setPlaybackHeadPosition(int i) {
        if (this.mDataLoadMode == 1 || this.mState == 0 || getPlayState() == 3) {
            return -3;
        }
        if (i < 0 || i > this.mNativeBufferSizeInFrames) {
            return -2;
        }
        return native_set_position(i);
    }

    public int setLoopPoints(int i, int i2, int i3) {
        int i4;
        if (this.mDataLoadMode == 1 || this.mState == 0 || getPlayState() == 3) {
            return -3;
        }
        if (i3 != 0 && (i < 0 || i >= (i4 = this.mNativeBufferSizeInFrames) || i >= i2 || i2 > i4)) {
            return -2;
        }
        return native_set_loop(i, i2, i3);
    }

    public int setPresentation(AudioPresentation audioPresentation) {
        if (audioPresentation == null) {
            throw new IllegalArgumentException("audio presentation is null");
        }
        return native_setPresentation(audioPresentation.getPresentationId(), audioPresentation.getProgramId());
    }

    @Deprecated
    protected void setState(int i) {
        this.mState = i;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.media.AudioTrack$1] */
    public void play() throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("play() called on uninitialized AudioTrack.");
        }
        final int startDelayMs = getStartDelayMs();
        if (startDelayMs == 0) {
            startImpl();
        } else {
            new Thread() { // from class: android.media.AudioTrack.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(startDelayMs);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    AudioTrack.this.baseSetStartDelayMs(0);
                    try {
                        AudioTrack.this.startImpl();
                    } catch (IllegalStateException unused) {
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startImpl() {
        synchronized (this.mPlayStateLock) {
            baseStart();
            native_start();
            if (this.mPlayState == 5) {
                this.mPlayState = 4;
            } else {
                this.mPlayState = 3;
                this.mOffloadEosPending = false;
            }
        }
    }

    public void stop() throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("stop() called on uninitialized AudioTrack.");
        }
        synchronized (this.mPlayStateLock) {
            native_stop();
            baseStop();
            if (this.mOffloaded && this.mPlayState != 5) {
                this.mPlayState = 4;
            } else {
                this.mPlayState = 1;
                this.mOffloadEosPending = false;
                this.mAvSyncHeader = null;
                this.mAvSyncBytesRemaining = 0;
                this.mPlayStateLock.notify();
            }
        }
    }

    public void pause() throws IllegalStateException {
        if (this.mState != 1) {
            throw new IllegalStateException("pause() called on uninitialized AudioTrack.");
        }
        synchronized (this.mPlayStateLock) {
            native_pause();
            basePause();
            if (this.mPlayState == 4) {
                this.mPlayState = 5;
            } else {
                this.mPlayState = 2;
            }
        }
    }

    public void flush() {
        if (this.mState == 1) {
            native_flush();
            this.mAvSyncHeader = null;
            this.mAvSyncBytesRemaining = 0;
        }
    }

    public int write(byte[] bArr, int i, int i2) {
        return write(bArr, i, i2, 0);
    }

    public int write(byte[] bArr, int i, int i2, int i3) {
        int i4;
        if (this.mState == 0 || this.mAudioFormat == 4) {
            return -3;
        }
        if (i3 != 0 && i3 != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        if (bArr == null || i < 0 || i2 < 0 || (i4 = i + i2) < 0 || i4 > bArr.length) {
            return -2;
        }
        if (!blockUntilOffloadDrain(i3)) {
            return 0;
        }
        int iNative_write_byte = native_write_byte(bArr, i, i2, this.mAudioFormat, i3 == 0);
        if (this.mDataLoadMode == 0 && this.mState == 2 && iNative_write_byte > 0) {
            this.mState = 1;
        }
        return iNative_write_byte;
    }

    public int write(short[] sArr, int i, int i2) {
        return write(sArr, i, i2, 0);
    }

    public int write(short[] sArr, int i, int i2, int i3) {
        int i4;
        if (this.mState == 0 || this.mAudioFormat == 4) {
            return -3;
        }
        if (i3 != 0 && i3 != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        if (sArr == null || i < 0 || i2 < 0 || (i4 = i + i2) < 0 || i4 > sArr.length) {
            return -2;
        }
        if (!blockUntilOffloadDrain(i3)) {
            return 0;
        }
        int iNative_write_short = native_write_short(sArr, i, i2, this.mAudioFormat, i3 == 0);
        if (this.mDataLoadMode == 0 && this.mState == 2 && iNative_write_short > 0) {
            this.mState = 1;
        }
        return iNative_write_short;
    }

    public int write(float[] fArr, int i, int i2, int i3) {
        int i4;
        if (this.mState == 0) {
            Log.e(TAG, "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if (this.mAudioFormat != 4) {
            Log.e(TAG, "AudioTrack.write(float[] ...) requires format ENCODING_PCM_FLOAT");
            return -3;
        }
        if (i3 != 0 && i3 != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        if (fArr == null || i < 0 || i2 < 0 || (i4 = i + i2) < 0 || i4 > fArr.length) {
            Log.e(TAG, "AudioTrack.write() called with invalid array, offset, or size");
            return -2;
        }
        if (!blockUntilOffloadDrain(i3)) {
            return 0;
        }
        int iNative_write_float = native_write_float(fArr, i, i2, this.mAudioFormat, i3 == 0);
        if (this.mDataLoadMode == 0 && this.mState == 2 && iNative_write_float > 0) {
            this.mState = 1;
        }
        return iNative_write_float;
    }

    public int write(ByteBuffer byteBuffer, int i, int i2) {
        int iNative_write_byte;
        if (this.mState == 0) {
            Log.e(TAG, "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if (i2 != 0 && i2 != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        if (byteBuffer == null || i < 0 || i > byteBuffer.remaining()) {
            Log.e(TAG, "AudioTrack.write() called with invalid size (" + i + ") value");
            return -2;
        }
        if (!blockUntilOffloadDrain(i2)) {
            return 0;
        }
        if (byteBuffer.isDirect()) {
            iNative_write_byte = native_write_native_bytes(byteBuffer, byteBuffer.position(), i, this.mAudioFormat, i2 == 0);
        } else {
            iNative_write_byte = native_write_byte(NioUtils.unsafeArray(byteBuffer), NioUtils.unsafeArrayOffset(byteBuffer) + byteBuffer.position(), i, this.mAudioFormat, i2 == 0);
        }
        if (this.mDataLoadMode == 0 && this.mState == 2 && iNative_write_byte > 0) {
            this.mState = 1;
        }
        if (iNative_write_byte > 0) {
            byteBuffer.position(byteBuffer.position() + iNative_write_byte);
        }
        return iNative_write_byte;
    }

    public int write(ByteBuffer byteBuffer, int i, int i2, long j) {
        if (this.mState == 0) {
            Log.e(TAG, "AudioTrack.write() called in invalid state STATE_UNINITIALIZED");
            return -3;
        }
        if (i2 != 0 && i2 != 1) {
            Log.e(TAG, "AudioTrack.write() called with invalid blocking mode");
            return -2;
        }
        if (this.mDataLoadMode != 1) {
            Log.e(TAG, "AudioTrack.write() with timestamp called for non-streaming mode track");
            return -3;
        }
        if ((this.mAttributes.getFlags() & 16) == 0) {
            Log.d(TAG, "AudioTrack.write() called on a regular AudioTrack. Ignoring pts...");
            return write(byteBuffer, i, i2);
        }
        if (byteBuffer == null || i < 0 || i > byteBuffer.remaining()) {
            Log.e(TAG, "AudioTrack.write() called with invalid size (" + i + ") value");
            return -2;
        }
        if (!blockUntilOffloadDrain(i2)) {
            return 0;
        }
        if (this.mAvSyncHeader == null) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.mOffset);
            this.mAvSyncHeader = byteBufferAllocate;
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            this.mAvSyncHeader.putInt(1431633922);
        }
        if (this.mAvSyncBytesRemaining == 0) {
            this.mAvSyncHeader.putInt(4, i);
            this.mAvSyncHeader.putLong(8, j);
            this.mAvSyncHeader.putInt(16, this.mOffset);
            this.mAvSyncHeader.position(0);
            this.mAvSyncBytesRemaining = i;
        }
        if (this.mAvSyncHeader.remaining() != 0) {
            ByteBuffer byteBuffer2 = this.mAvSyncHeader;
            int iWrite = write(byteBuffer2, byteBuffer2.remaining(), i2);
            if (iWrite < 0) {
                Log.e(TAG, "AudioTrack.write() could not write timestamp header!");
                this.mAvSyncHeader = null;
                this.mAvSyncBytesRemaining = 0;
                return iWrite;
            }
            if (this.mAvSyncHeader.remaining() > 0) {
                Log.v(TAG, "AudioTrack.write() partial timestamp header written.");
                return 0;
            }
        }
        int iWrite2 = write(byteBuffer, Math.min(this.mAvSyncBytesRemaining, i), i2);
        if (iWrite2 < 0) {
            Log.e(TAG, "AudioTrack.write() could not write audio data!");
            this.mAvSyncHeader = null;
            this.mAvSyncBytesRemaining = 0;
            return iWrite2;
        }
        this.mAvSyncBytesRemaining -= iWrite2;
        return iWrite2;
    }

    public int reloadStaticData() {
        if (this.mDataLoadMode == 1 || this.mState != 1) {
            return -3;
        }
        return native_reload_static();
    }

    private boolean blockUntilOffloadDrain(int i) {
        synchronized (this.mPlayStateLock) {
            while (true) {
                int i2 = this.mPlayState;
                if (i2 != 4 && i2 != 5) {
                    return true;
                }
                if (i == 1) {
                    return false;
                }
                try {
                    this.mPlayStateLock.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public int attachAuxEffect(int i) {
        if (this.mState == 0) {
            return -3;
        }
        return native_attachAuxEffect(i);
    }

    public int setAuxEffectSendLevel(float f) {
        if (this.mState == 0) {
            return -3;
        }
        return baseSetAuxEffectSendLevel(f);
    }

    @Override // android.media.PlayerBase
    int playerSetAuxEffectSendLevel(boolean z, float f) {
        if (z) {
            f = 0.0f;
        }
        return native_setAuxEffectSendLevel(clampGainOrLevel(f)) == 0 ? 0 : -1;
    }

    @Override // android.media.AudioRouting
    public boolean setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        if (audioDeviceInfo != null && !audioDeviceInfo.isSink()) {
            return false;
        }
        boolean zNative_setOutputDevice = native_setOutputDevice(audioDeviceInfo != null ? audioDeviceInfo.getId() : 0);
        if (zNative_setOutputDevice) {
            synchronized (this) {
                this.mPreferredDevice = audioDeviceInfo;
            }
        }
        return zNative_setOutputDevice;
    }

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getPreferredDevice() {
        AudioDeviceInfo audioDeviceInfo;
        synchronized (this) {
            audioDeviceInfo = this.mPreferredDevice;
        }
        return audioDeviceInfo;
    }

    @Override // android.media.AudioRouting
    public AudioDeviceInfo getRoutedDevice() {
        int iNative_getRoutedDeviceId = native_getRoutedDeviceId();
        if (iNative_getRoutedDeviceId == 0) {
            return null;
        }
        AudioDeviceInfo[] devicesStatic = AudioManager.getDevicesStatic(2);
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
            }
            testDisableNativeRoutingCallbacksLocked();
        }
    }

    @Deprecated
    public interface OnRoutingChangedListener extends AudioRouting.OnRoutingChangedListener {
        void onRoutingChanged(AudioTrack audioTrack);

        @Override // android.media.AudioRouting.OnRoutingChangedListener
        default void onRoutingChanged(AudioRouting audioRouting) {
            if (audioRouting instanceof AudioTrack) {
                onRoutingChanged((AudioTrack) audioRouting);
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

    public void addOnCodecFormatChangedListener(Executor executor, final OnCodecFormatChangedListener onCodecFormatChangedListener) {
        this.mCodecFormatChangedListeners.add(onCodecFormatChangedListener, executor, new Utils.ListenerList.Listener() { // from class: android.media.-$$Lambda$AudioTrack$m1mANL-24-e4gyI1Yh6U1BrVJaw
            @Override // android.media.Utils.ListenerList.Listener
            public final void onEvent(int i, Object obj) {
                this.f$0.lambda$addOnCodecFormatChangedListener$0$AudioTrack(onCodecFormatChangedListener, i, (AudioMetadataReadMap) obj);
            }
        });
    }

    public /* synthetic */ void lambda$addOnCodecFormatChangedListener$0$AudioTrack(OnCodecFormatChangedListener onCodecFormatChangedListener, int i, AudioMetadataReadMap audioMetadataReadMap) {
        onCodecFormatChangedListener.onCodecFormatChanged(this, audioMetadataReadMap);
    }

    public void removeOnCodecFormatChangedListener(OnCodecFormatChangedListener onCodecFormatChangedListener) {
        this.mCodecFormatChangedListeners.remove(onCodecFormatChangedListener);
    }

    public void registerStreamEventCallback(Executor executor, StreamEventCallback streamEventCallback) {
        if (streamEventCallback == null) {
            throw new IllegalArgumentException("Illegal null StreamEventCallback");
        }
        if (!this.mOffloaded) {
            throw new IllegalStateException("Cannot register StreamEventCallback on non-offloaded AudioTrack");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Illegal null Executor for the StreamEventCallback");
        }
        synchronized (this.mStreamEventCbLock) {
            Iterator<StreamEventCbInfo> it = this.mStreamEventCbInfoList.iterator();
            while (it.hasNext()) {
                if (it.next().mStreamEventCb == streamEventCallback) {
                    throw new IllegalArgumentException("StreamEventCallback already registered");
                }
            }
            beginStreamEventHandling();
            this.mStreamEventCbInfoList.add(new StreamEventCbInfo(executor, streamEventCallback));
        }
    }

    public void unregisterStreamEventCallback(StreamEventCallback streamEventCallback) {
        if (streamEventCallback == null) {
            throw new IllegalArgumentException("Illegal null StreamEventCallback");
        }
        if (!this.mOffloaded) {
            throw new IllegalStateException("No StreamEventCallback on non-offloaded AudioTrack");
        }
        synchronized (this.mStreamEventCbLock) {
            for (StreamEventCbInfo streamEventCbInfo : this.mStreamEventCbInfoList) {
                if (streamEventCbInfo.mStreamEventCb == streamEventCallback) {
                    this.mStreamEventCbInfoList.remove(streamEventCbInfo);
                    if (this.mStreamEventCbInfoList.size() == 0) {
                        endStreamEventHandling();
                    }
                }
            }
            throw new IllegalArgumentException("StreamEventCallback was not registered");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class StreamEventCbInfo {
        final StreamEventCallback mStreamEventCb;
        final Executor mStreamEventExec;

        StreamEventCbInfo(Executor executor, StreamEventCallback streamEventCallback) {
            this.mStreamEventExec = executor;
            this.mStreamEventCb = streamEventCallback;
        }
    }

    void handleStreamEventFromNative(int i, int i2) {
        if (this.mStreamEventHandler == null) {
            return;
        }
        if (i == 6) {
            this.mStreamEventHandler.sendMessage(this.mStreamEventHandler.obtainMessage(6));
            return;
        }
        if (i == 7) {
            this.mStreamEventHandler.sendMessage(this.mStreamEventHandler.obtainMessage(7));
        } else {
            if (i != 9) {
                return;
            }
            this.mStreamEventHandler.removeMessages(9);
            this.mStreamEventHandler.sendMessage(this.mStreamEventHandler.obtainMessage(9, i2, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class StreamEventHandler extends Handler {
        StreamEventHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(final Message message) {
            synchronized (AudioTrack.this.mStreamEventCbLock) {
                if (message.what == 7) {
                    synchronized (AudioTrack.this.mPlayStateLock) {
                        if (AudioTrack.this.mPlayState == 4) {
                            if (AudioTrack.this.mOffloadEosPending) {
                                AudioTrack.this.native_start();
                                AudioTrack.this.mPlayState = 3;
                            } else {
                                AudioTrack.this.mAvSyncHeader = null;
                                AudioTrack.this.mAvSyncBytesRemaining = 0;
                                AudioTrack.this.mPlayState = 1;
                            }
                            AudioTrack.this.mOffloadEosPending = false;
                            AudioTrack.this.mPlayStateLock.notify();
                        }
                    }
                }
                if (AudioTrack.this.mStreamEventCbInfoList.size() == 0) {
                    return;
                }
                LinkedList<StreamEventCbInfo> linkedList = new LinkedList(AudioTrack.this.mStreamEventCbInfoList);
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    for (final StreamEventCbInfo streamEventCbInfo : linkedList) {
                        int i = message.what;
                        if (i == 6) {
                            streamEventCbInfo.mStreamEventExec.execute(new Runnable() { // from class: android.media.-$$Lambda$AudioTrack$StreamEventHandler$06Mprse-JJzRB7C5adJlCiZ8avs
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.lambda$handleMessage$1$AudioTrack$StreamEventHandler(streamEventCbInfo);
                                }
                            });
                        } else if (i == 7) {
                            streamEventCbInfo.mStreamEventExec.execute(new Runnable() { // from class: android.media.-$$Lambda$AudioTrack$StreamEventHandler$QOQEyNg_RVDCd3Afz9WmYLNVHEc
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.lambda$handleMessage$2$AudioTrack$StreamEventHandler(streamEventCbInfo);
                                }
                            });
                        } else if (i == 9) {
                            streamEventCbInfo.mStreamEventExec.execute(new Runnable() { // from class: android.media.-$$Lambda$AudioTrack$StreamEventHandler$Mo7a9KAALomEpq3X84zCsUVsUE8
                                @Override // java.lang.Runnable
                                public final void run() {
                                    this.f$0.lambda$handleMessage$0$AudioTrack$StreamEventHandler(streamEventCbInfo, message);
                                }
                            });
                        }
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$AudioTrack$StreamEventHandler(StreamEventCbInfo streamEventCbInfo, Message message) {
            streamEventCbInfo.mStreamEventCb.onDataRequest(AudioTrack.this, message.arg1);
        }

        public /* synthetic */ void lambda$handleMessage$1$AudioTrack$StreamEventHandler(StreamEventCbInfo streamEventCbInfo) {
            streamEventCbInfo.mStreamEventCb.onTearDown(AudioTrack.this);
        }

        public /* synthetic */ void lambda$handleMessage$2$AudioTrack$StreamEventHandler(StreamEventCbInfo streamEventCbInfo) {
            streamEventCbInfo.mStreamEventCb.onPresentationEnded(AudioTrack.this);
        }
    }

    private void beginStreamEventHandling() {
        if (this.mStreamEventHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("android.media.AudioTrack.StreamEvent");
            this.mStreamEventHandlerThread = handlerThread;
            handlerThread.start();
            Looper looper = this.mStreamEventHandlerThread.getLooper();
            if (looper != null) {
                this.mStreamEventHandler = new StreamEventHandler(looper);
            }
        }
    }

    private void endStreamEventHandling() {
        HandlerThread handlerThread = this.mStreamEventHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mStreamEventHandlerThread = null;
        }
    }

    private class NativePositionEventHandlerDelegate {
        private final Handler mHandler;

        NativePositionEventHandlerDelegate(final AudioTrack audioTrack, final OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener, Handler handler) {
            Looper looper;
            if (handler == null) {
                looper = AudioTrack.this.mInitializationLooper;
            } else {
                looper = handler.getLooper();
            }
            Looper looper2 = looper;
            if (looper2 != null) {
                this.mHandler = new Handler(looper2) { // from class: android.media.AudioTrack.NativePositionEventHandlerDelegate.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        if (audioTrack == null) {
                            return;
                        }
                        int i = message.what;
                        if (i == 3) {
                            OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener2 = onPlaybackPositionUpdateListener;
                            if (onPlaybackPositionUpdateListener2 != null) {
                                onPlaybackPositionUpdateListener2.onMarkerReached(audioTrack);
                                return;
                            }
                            return;
                        }
                        if (i != 4) {
                            AudioTrack.loge("Unknown native event type: " + message.what);
                            return;
                        }
                        OnPlaybackPositionUpdateListener onPlaybackPositionUpdateListener3 = onPlaybackPositionUpdateListener;
                        if (onPlaybackPositionUpdateListener3 != null) {
                            onPlaybackPositionUpdateListener3.onPeriodicNotification(audioTrack);
                        }
                    }
                };
            } else {
                this.mHandler = null;
            }
        }

        Handler getHandler() {
            return this.mHandler;
        }
    }

    @Override // android.media.PlayerBase
    void playerStart() {
        play();
    }

    @Override // android.media.PlayerBase
    void playerPause() {
        pause();
    }

    @Override // android.media.PlayerBase
    void playerStop() {
        stop();
    }

    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        Handler handler;
        AudioTrack audioTrack = (AudioTrack) ((WeakReference) obj).get();
        if (audioTrack == null) {
            return;
        }
        if (i == 1000) {
            audioTrack.broadcastRoutingChange();
            return;
        }
        if (i == 100) {
            ByteBuffer byteBuffer = (ByteBuffer) obj2;
            byteBuffer.order(ByteOrder.nativeOrder());
            byteBuffer.rewind();
            AudioMetadata.BaseMap baseMapFromByteBuffer = AudioMetadata.fromByteBuffer(byteBuffer);
            if (baseMapFromByteBuffer == null) {
                Log.e(TAG, "Unable to get audio metadata from byte buffer");
                return;
            } else {
                audioTrack.mCodecFormatChangedListeners.notify(0, baseMapFromByteBuffer);
                return;
            }
        }
        if (i == 9 || i == 6 || i == 7) {
            audioTrack.handleStreamEventFromNative(i, i2);
            return;
        }
        NativePositionEventHandlerDelegate nativePositionEventHandlerDelegate = audioTrack.mEventHandlerDelegate;
        if (nativePositionEventHandlerDelegate == null || (handler = nativePositionEventHandlerDelegate.getHandler()) == null) {
            return;
        }
        handler.sendMessage(handler.obtainMessage(i, i2, i3, obj2));
    }

    private static void logd(String str) {
        Log.d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String str) {
        Log.e(TAG, str);
    }

    public static final class MetricsConstants {
        public static final String ATTRIBUTES = "android.media.audiotrack.attributes";

        @Deprecated
        public static final String CHANNELMASK = "android.media.audiorecord.channelmask";
        public static final String CHANNEL_MASK = "android.media.audiotrack.channelMask";
        public static final String CONTENTTYPE = "android.media.audiotrack.type";
        public static final String ENCODING = "android.media.audiotrack.encoding";
        public static final String FRAME_COUNT = "android.media.audiotrack.frameCount";
        private static final String MM_PREFIX = "android.media.audiotrack.";
        public static final String PORT_ID = "android.media.audiotrack.portId";

        @Deprecated
        public static final String SAMPLERATE = "android.media.audiorecord.samplerate";
        public static final String SAMPLE_RATE = "android.media.audiotrack.sampleRate";
        public static final String STREAMTYPE = "android.media.audiotrack.streamtype";
        public static final String USAGE = "android.media.audiotrack.usage";

        private MetricsConstants() {
        }
    }
}
