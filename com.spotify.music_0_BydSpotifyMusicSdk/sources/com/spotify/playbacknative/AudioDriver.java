package com.spotify.playbacknative;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AudioDriver implements AudioTrack.OnPlaybackPositionUpdateListener {
    public static final int CORE_SAMPLE_FORMAT_16_BITS_PER_SAMPLE = 16;
    public static final int CORE_SAMPLE_FORMAT_IEEE_FLOAT = 17;
    static final float DUCKING_LOWEST_VOLUME = 0.25f;
    private static final int DUCKING_RAMPDOWN_TIME_MS = 200;
    private static final int DUCKING_RAMPUP_TIME_MS = 1000;
    private static final int DUCKING_RAMP_TIME_DELTA_MS = 50;
    private static final boolean LOGGING_ENABLED = false;
    static final int MESSAGE_SET_VOLUME = 1;
    private static final int MINIMUM_WRITTEN_BEFORE_PLAY = 512;
    public static final int SPOTIFY_MAX_VOLUME = 65535;
    private static AudioDriverCallbacks sAudioDriverCallbacks;
    private int mAndroidSdkUnderrunCount;
    private AudioFeatures mAudioFeatures;
    private Integer mAudioSessionId;
    private AudioTrackAdapterImpl mAudioTrack;
    private int mBufferSizeInBytes;
    private int mBytesPerFrame;
    private int mChannelConfig;
    private boolean mCreateFailed;
    private float mCurrentVolumeGain;
    private float mCurrentVolumeTarget;
    private FloatPcmSupportDeviceSet mFloatPcmSupportDeviceSet;
    private int mFramesBufferedBeforePlay;
    private VolumeHandler mHandler;
    private HandlerThread mHandlerThread;
    private Looper mLooper;
    private int mMarkerFallbackPosition;
    private final Object mMutex;
    private final NativeWrapper mNativeWrapper;
    private boolean mOpen;
    private boolean mPaused;
    private boolean mPlaying;
    private int mSampleFormat;
    private int mSampleRate;
    private long nThis;
    private static final List<AudioDriverListener> sAudioDriverListeners = new CopyOnWriteArrayList();
    private static AudioRouteListenerHandler sAudioRouteListenerHandler = new NoopAudioRouteListenerHandler();
    private static final SparseArray<AudioDriver> sSessionToAudioDriverMap = new SparseArray<>();
    private static Integer sCurrentAudioSession = null;

    public interface AudioDriverCallbacks {
        void abandonAudioFocus();

        boolean maybeAbandonAudioFocus();

        boolean requestAudioFocus(AudioFeatures audioFeatures);
    }

    public interface AudioDriverListener {
        void onAudioTrackCreated(int i);

        void onAudioTrackDestroyed(int i);

        void onFlushComplete(int i);

        void onFlushStart(int i);

        void onPaused(boolean z, Integer num);
    }

    public static abstract class AudioRouteListenerHandler {
        public abstract void attach(AudioTrackAdapter audioTrackAdapter);

        public abstract void detach(AudioTrackAdapter audioTrackAdapter);
    }

    public class DefaultNativeWrapper implements NativeWrapper {
        private DefaultNativeWrapper() {
        }

        @Override // com.spotify.playbacknative.AudioDriver.NativeWrapper
        public void onBufferUnderrun(int i) {
            AudioDriver.this.onBufferUnderrun(i);
        }

        @Override // com.spotify.playbacknative.AudioDriver.NativeWrapper
        public void onDestroy() {
            AudioDriver.this.onDestroy();
        }

        @Override // com.spotify.playbacknative.AudioDriver.NativeWrapper
        public void onFlush() {
            AudioDriver.this.onFlush();
        }
    }

    public interface NativeWrapper {
        void onBufferUnderrun(int i);

        void onDestroy();

        void onFlush();
    }

    public static class NoopAudioRouteListenerHandler extends AudioRouteListenerHandler {
        private NoopAudioRouteListenerHandler() {
        }

        @Override // com.spotify.playbacknative.AudioDriver.AudioRouteListenerHandler
        public void attach(AudioTrackAdapter audioTrackAdapter) {
        }

        @Override // com.spotify.playbacknative.AudioDriver.AudioRouteListenerHandler
        public void detach(AudioTrackAdapter audioTrackAdapter) {
        }
    }

    public static class VolumeHandler extends Handler {
        private final AudioDriver mAudioDriver;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (this.mAudioDriver.mMutex) {
                try {
                    if (message.what == 1) {
                        Float f = (Float) message.obj;
                        if (this.mAudioDriver.mAudioTrack != null) {
                            AudioDriver.setAudioTrackVolume(f.floatValue(), this.mAudioDriver.mAudioTrack.getAudioTrack());
                        }
                        this.mAudioDriver.mCurrentVolumeGain = f.floatValue();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            super.handleMessage(message);
        }

        private VolumeHandler(Looper looper, AudioDriver audioDriver) {
            super(looper);
            this.mAudioDriver = audioDriver;
        }
    }

    public AudioDriver() {
        this.mSampleFormat = 2;
        this.mMutex = new Object();
        this.mCurrentVolumeGain = AudioTrack.getMaxVolume();
        this.mCurrentVolumeTarget = 1.0f;
        this.mPaused = true;
        this.mNativeWrapper = new DefaultNativeWrapper();
    }

    private static void abandonAudioFocus() {
        if (internalAudioFocusHandlingEnabled()) {
            sAudioDriverCallbacks.abandonAudioFocus();
        }
    }

    public static void addListener(AudioDriverListener audioDriverListener) {
        sAudioDriverListeners.add(audioDriverListener);
    }

    private void cancelDucking(int i) {
        this.mHandler.removeMessages(1);
        sendVolumeUpdateDelayed(AudioTrack.getMaxVolume(), i);
    }

    public static void cancelDuckingAudioSession(int i) {
        cancelDuckingAudioSession(i, 0);
    }

    public static void clearListeners() {
        sAudioDriverListeners.clear();
    }

    private AudioAttributes constructAudioAttributes() {
        return new AudioAttributes.Builder().setAllowedCapturePolicy(2).setContentType(this.mAudioFeatures.getAudioType().getAttr()).setUsage(this.mAudioFeatures.getAudioUsage().getAttr()).build();
    }

    private AudioAttributes constructAudioAttributesLollipop() {
        return new AudioAttributes.Builder().setContentType(this.mAudioFeatures.getAudioType().getAttr()).setUsage(this.mAudioFeatures.getAudioUsage().getAttr()).build();
    }

    private AudioTrack constructAudioTrack(Integer num) {
        return new AudioTrack(Build.VERSION.SDK_INT >= 29 ? constructAudioAttributes() : constructAudioAttributesLollipop(), new AudioFormat.Builder().setSampleRate(this.mSampleRate).setChannelMask(this.mChannelConfig).setEncoding(this.mSampleFormat).build(), this.mBufferSizeInBytes, 1, num != null ? num.intValue() : 0);
    }

    private void createAudioTrack() {
        AudioTrack audioTrackConstructAudioTrack = constructAudioTrack(this.mAudioSessionId);
        if (audioTrackConstructAudioTrack.getState() == 0) {
            this.mCreateFailed = true;
            return;
        }
        this.mCreateFailed = LOGGING_ENABLED;
        AudioTrackAdapterImpl audioTrackAdapterImpl = new AudioTrackAdapterImpl(audioTrackConstructAudioTrack);
        this.mAudioTrack = audioTrackAdapterImpl;
        audioTrackAdapterImpl.getAudioTrack().setPlaybackPositionUpdateListener(this, this.mHandler);
        synchronized (this.mMutex) {
            this.mMarkerFallbackPosition = 0;
            this.mAndroidSdkUnderrunCount = 0;
            setAudioTrackVolume(AudioTrack.getMaxVolume() * this.mCurrentVolumeGain, this.mAudioTrack.getAudioTrack());
        }
        this.mAudioSessionId = Integer.valueOf(this.mAudioTrack.getAudioTrack().getAudioSessionId());
        sAudioRouteListenerHandler.attach(this.mAudioTrack);
        sSessionToAudioDriverMap.put(this.mAudioTrack.getAudioTrack().getAudioSessionId(), this);
        sCurrentAudioSession = Integer.valueOf(this.mAudioTrack.getAudioTrack().getAudioSessionId());
        dispatchOnAudioTrackCreated(this.mAudioTrack.getAudioTrack().getAudioSessionId());
    }

    private void destroyAudioTrack() {
        if (this.mAudioTrack == null) {
            return;
        }
        synchronized (this.mMutex) {
            sAudioRouteListenerHandler.detach(this.mAudioTrack);
            dispatchOnAudioTrackDestroyed(this.mAudioTrack.getAudioTrack().getAudioSessionId());
            sCurrentAudioSession = null;
            sSessionToAudioDriverMap.delete(this.mAudioTrack.getAudioTrack().getAudioSessionId());
            this.mPlaying = LOGGING_ENABLED;
            this.mAudioTrack.getAudioTrack().setPlaybackPositionUpdateListener(null, this.mHandler);
            this.mAudioTrack.getAudioTrack().flush();
            this.mAudioTrack.getAudioTrack().stop();
            this.mAudioTrack.getAudioTrack().release();
            this.mAudioTrack = null;
        }
    }

    public static void dispatchOnAudioTrackCreated(int i) {
        Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
        while (it.hasNext()) {
            it.next().onAudioTrackCreated(i);
        }
    }

    public static void dispatchOnAudioTrackDestroyed(int i) {
        Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
        while (it.hasNext()) {
            it.next().onAudioTrackDestroyed(i);
        }
    }

    private boolean floatPcmAllowed() {
        FloatPcmSupportDeviceSet floatPcmSupportDeviceSet = this.mFloatPcmSupportDeviceSet;
        return floatPcmSupportDeviceSet != null ? floatPcmSupportDeviceSet.isFloatPcmSupported() : LOGGING_ENABLED;
    }

    public static Integer getCurrentAudioSession() {
        return sCurrentAudioSession;
    }

    private static boolean internalAudioFocusHandlingEnabled() {
        if (sAudioDriverCallbacks != null) {
            return true;
        }
        return LOGGING_ENABLED;
    }

    private static void maybeAbandonFocus() {
        if (internalAudioFocusHandlingEnabled()) {
            sAudioDriverCallbacks.maybeAbandonAudioFocus();
        }
    }

    private void maybeStartPlayback() {
        if (this.mPaused || !this.mPlaying) {
            return;
        }
        synchronized (this.mMutex) {
            try {
                if (this.mFramesBufferedBeforePlay >= MINIMUM_WRITTEN_BEFORE_PLAY && requestAudioFocus()) {
                    this.mAudioTrack.getAudioTrack().play();
                    this.mFramesBufferedBeforePlay = 0;
                }
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void onBufferUnderrun(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void onDestroy();

    /* JADX INFO: Access modifiers changed from: private */
    public native void onFlush();

    public static void removeListener(AudioDriverListener audioDriverListener) {
        sAudioDriverListeners.remove(audioDriverListener);
    }

    private boolean requestAudioFocus() {
        if (internalAudioFocusHandlingEnabled()) {
            return sAudioDriverCallbacks.requestAudioFocus(this.mAudioFeatures);
        }
        return true;
    }

    private void sendVolumeUpdateDelayed(float f, int i) {
        VolumeHandler volumeHandler = this.mHandler;
        if (volumeHandler == null) {
            return;
        }
        this.mHandler.sendMessageDelayed(volumeHandler.obtainMessage(1, Float.valueOf(f)), i);
    }

    public static void setAudioDriverCallbacks(AudioDriverCallbacks audioDriverCallbacks) {
        sAudioDriverCallbacks = audioDriverCallbacks;
    }

    public static void setAudioRouteListenerHandler(AudioRouteListenerHandler audioRouteListenerHandler) {
        if (audioRouteListenerHandler != null) {
            sAudioRouteListenerHandler = audioRouteListenerHandler;
        } else {
            sAudioRouteListenerHandler = new NoopAudioRouteListenerHandler();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setAudioTrackVolume(float f, AudioTrack audioTrack) {
        Logger.d("Set volume to %f", Float.valueOf(f));
        audioTrack.setVolume(f);
    }

    private static int snapRampTimeToDelta(int i) {
        int i2 = i % DUCKING_RAMP_TIME_DELTA_MS;
        return i2 != 0 ? (i + DUCKING_RAMP_TIME_DELTA_MS) - i2 : i;
    }

    private void startDucking(int i, float f) {
        float f2;
        this.mHandler.removeMessages(1);
        if (f < 0.0f || f > 1.0f) {
            Logger.e("Requested volume must be between 0.0 and 1.0", new Object[0]);
            return;
        }
        float maxVolume = AudioTrack.getMaxVolume();
        synchronized (this.mMutex) {
            f2 = this.mCurrentVolumeGain;
        }
        float f3 = f2 - f;
        int iSnapRampTimeToDelta = snapRampTimeToDelta((int) ((f3 / (maxVolume - f)) * i));
        if (iSnapRampTimeToDelta < 0) {
            return;
        }
        this.mCurrentVolumeTarget = f;
        if (iSnapRampTimeToDelta == 0) {
            sendVolumeUpdateDelayed(f, 0);
            return;
        }
        for (int i2 = 0; i2 <= iSnapRampTimeToDelta; i2 += DUCKING_RAMP_TIME_DELTA_MS) {
            sendVolumeUpdateDelayed((((iSnapRampTimeToDelta - i2) / iSnapRampTimeToDelta) * f3) + f, i2);
        }
    }

    public static void startDuckingAudioSession(int i) {
        startDuckingAudioSession(i, DUCKING_RAMPDOWN_TIME_MS, DUCKING_LOWEST_VOLUME);
    }

    private void stopDucking(int i) {
        float f;
        this.mHandler.removeMessages(1);
        float maxVolume = AudioTrack.getMaxVolume();
        synchronized (this.mMutex) {
            f = this.mCurrentVolumeGain;
        }
        float f2 = this.mCurrentVolumeTarget;
        int iSnapRampTimeToDelta = snapRampTimeToDelta((int) ((1.0f - ((f - f2) / (maxVolume - f2))) * i));
        if (iSnapRampTimeToDelta < 0) {
            return;
        }
        this.mCurrentVolumeTarget = 1.0f;
        if (iSnapRampTimeToDelta == 0) {
            sendVolumeUpdateDelayed(AudioTrack.getMaxVolume(), 0);
            return;
        }
        for (int i2 = 0; i2 <= iSnapRampTimeToDelta; i2 += DUCKING_RAMP_TIME_DELTA_MS) {
            sendVolumeUpdateDelayed(((maxVolume - f) * (i2 / iSnapRampTimeToDelta)) + f, i2);
        }
    }

    public static void stopDuckingAudioSession(int i) {
        stopDuckingAudioSession(i, DUCKING_RAMPUP_TIME_MS);
    }

    public void destroy() {
        this.mPlaying = LOGGING_ENABLED;
        this.mPaused = true;
        this.mOpen = LOGGING_ENABLED;
        Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
        while (it.hasNext()) {
            it.next().onPaused(true, this.mAudioSessionId);
        }
        if (this.mAudioTrack == null) {
            return;
        }
        destroyAudioTrack();
        abandonAudioFocus();
        this.mNativeWrapper.onDestroy();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            try {
                handlerThread.quit();
                handlerThread.join(1000L);
            } catch (InterruptedException e) {
                Logger.w(e, "Interrupted while waiting for notification thread to join", new Object[0]);
            }
        }
        this.mHandlerThread = null;
        this.mHandler = null;
    }

    public void flush() {
        if (!this.mOpen) {
            throw new IllegalStateException("cannot call flush() on destroyed object");
        }
        Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
        while (it.hasNext()) {
            it.next().onFlushStart(this.mAudioSessionId.intValue());
        }
        this.mPlaying = LOGGING_ENABLED;
        this.mFramesBufferedBeforePlay = 0;
        if (this.mCreateFailed) {
            createAudioTrack();
        } else {
            AudioTrackAdapterImpl audioTrackAdapterImpl = this.mAudioTrack;
            if (audioTrackAdapterImpl == null) {
                return;
            }
            audioTrackAdapterImpl.getAudioTrack().pause();
            destroyAudioTrack();
            createAudioTrack();
        }
        this.mNativeWrapper.onFlush();
        Iterator<AudioDriverListener> it2 = sAudioDriverListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onFlushComplete(this.mAudioSessionId.intValue());
        }
    }

    public AudioDriverCallbacks getAudioDriverCallbacks() {
        return sAudioDriverCallbacks;
    }

    public AudioTrack getAudioTrack() {
        AudioTrackAdapterImpl audioTrackAdapterImpl = this.mAudioTrack;
        if (audioTrackAdapterImpl == null) {
            return null;
        }
        return audioTrackAdapterImpl.getAudioTrack();
    }

    public AudioTrackAdapter getAudioTrackAdapter() {
        return this.mAudioTrack;
    }

    public int getFreeSize() {
        if (this.mAudioTrack == null) {
            return 0;
        }
        return this.mBufferSizeInBytes - (getQueuedSize() * this.mBytesPerFrame);
    }

    public int getPosition() {
        int iMax;
        if (this.mAudioTrack == null) {
            return 0;
        }
        synchronized (this.mMutex) {
            iMax = Math.max(0, this.mAudioTrack.getAudioTrack().getPlaybackHeadPosition());
        }
        return iMax;
    }

    public int getQueuedSize() {
        if (this.mAudioTrack == null) {
            return 0;
        }
        synchronized (this.mMutex) {
            try {
                if (!this.mPlaying) {
                    return this.mFramesBufferedBeforePlay;
                }
                int notificationMarkerPosition = this.mAudioTrack.getAudioTrack().getNotificationMarkerPosition();
                if (notificationMarkerPosition == 0) {
                    notificationMarkerPosition = this.mMarkerFallbackPosition;
                }
                return Math.max(0, notificationMarkerPosition - this.mAudioTrack.getAudioTrack().getPlaybackHeadPosition());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getVolume() {
        return SPOTIFY_MAX_VOLUME;
    }

    public VolumeHandler getVolumeHandler() {
        return this.mHandler;
    }

    public boolean isPlaying() {
        if (this.mAudioTrack != null && this.mPlaying && getQueuedSize() > 0) {
            return true;
        }
        return LOGGING_ENABLED;
    }

    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
    public void onMarkerReached(AudioTrack audioTrack) {
        if (this.mOpen) {
            synchronized (this.mMutex) {
                try {
                    if (this.mOpen) {
                        AudioTrackAdapterImpl audioTrackAdapterImpl = this.mAudioTrack;
                        if (audioTrackAdapterImpl != null && audioTrack == audioTrackAdapterImpl.getAudioTrack()) {
                            int underrunCount = audioTrack.getUnderrunCount();
                            int i = this.mAndroidSdkUnderrunCount;
                            if (underrunCount > i) {
                                this.mNativeWrapper.onBufferUnderrun(underrunCount - i);
                                this.mAndroidSdkUnderrunCount = underrunCount;
                            }
                            Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
                            while (it.hasNext()) {
                                it.next().onPaused(true, this.mAudioSessionId);
                            }
                        }
                    }
                } finally {
                }
            }
        }
    }

    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
    public void onPeriodicNotification(AudioTrack audioTrack) {
    }

    public void open(int i, int i2) {
        open(i, i2, 0);
    }

    public void pauseAtShutdown() {
        if (Build.MANUFACTURER.equals("Google")) {
            try {
                AudioTrackAdapterImpl audioTrackAdapterImpl = this.mAudioTrack;
                if (audioTrackAdapterImpl != null) {
                    audioTrackAdapterImpl.getAudioTrack().pause();
                }
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void setPaused(boolean z) {
        this.mPaused = z;
        Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
        while (it.hasNext()) {
            it.next().onPaused(z, this.mAudioSessionId);
        }
        if (this.mAudioTrack == null) {
            return;
        }
        if (!this.mPaused) {
            maybeStartPlayback();
        } else {
            maybeAbandonFocus();
            this.mAudioTrack.getAudioTrack().pause();
        }
    }

    public void setVolume(int i) {
    }

    public void startPlayback() {
        this.mPlaying = true;
        maybeStartPlayback();
        Iterator<AudioDriverListener> it = sAudioDriverListeners.iterator();
        while (it.hasNext()) {
            it.next().onPaused(this.mPaused, this.mAudioSessionId);
        }
    }

    public void stopPlayback() {
        AudioTrackAdapterImpl audioTrackAdapterImpl = this.mAudioTrack;
        if (audioTrackAdapterImpl == null) {
            return;
        }
        audioTrackAdapterImpl.getAudioTrack().pause();
    }

    public int write(byte[] bArr, int i) {
        int iWrite;
        AudioTrackAdapterImpl audioTrackAdapterImpl = this.mAudioTrack;
        if (audioTrackAdapterImpl == null || (iWrite = audioTrackAdapterImpl.getAudioTrack().write(ByteBuffer.wrap(bArr), i, 0)) <= 0) {
            return 0;
        }
        synchronized (this.mMutex) {
            try {
                this.mAudioTrack.getAudioTrack().setNotificationMarkerPosition(this.mAudioTrack.getAudioTrack().getNotificationMarkerPosition() + (iWrite / this.mBytesPerFrame));
                this.mMarkerFallbackPosition += iWrite / this.mBytesPerFrame;
                if (this.mAudioTrack.getAudioTrack().getPlayState() != 3) {
                    this.mFramesBufferedBeforePlay += iWrite / this.mBytesPerFrame;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        maybeStartPlayback();
        return iWrite;
    }

    public static void cancelDuckingAudioSession(int i, int i2) {
        AudioDriver audioDriver = sSessionToAudioDriverMap.get(i);
        if (audioDriver != null) {
            audioDriver.cancelDucking(i2);
        }
    }

    public static void startDuckingAudioSession(int i, int i2, float f) {
        AudioDriver audioDriver = sSessionToAudioDriverMap.get(i);
        if (audioDriver != null) {
            audioDriver.startDucking(i2, f);
        }
    }

    public static void stopDuckingAudioSession(int i, int i2) {
        AudioDriver audioDriver = sSessionToAudioDriverMap.get(i);
        if (audioDriver != null) {
            audioDriver.stopDucking(i2);
        }
    }

    public void open(int i, int i2, int i3) {
        open(i, i2, i3, 0);
    }

    public void open(int i, int i2, int i3, int i4) {
        Process.setThreadPriority(-16);
        this.mSampleRate = i;
        this.mChannelConfig = i2 == 1 ? 4 : 12;
        this.mAudioFeatures = AudioFeatures.fromCoreAudioFeatures(i3, i4);
        int i5 = this.mSampleFormat;
        int i6 = i2 * i5;
        this.mBytesPerFrame = i6;
        int i7 = i6 * i;
        this.mBufferSizeInBytes = i7;
        this.mBufferSizeInBytes = Math.max(i7, AudioTrack.getMinBufferSize(i, this.mChannelConfig, i5));
        if (this.mLooper == null) {
            HandlerThread handlerThread = new HandlerThread("AudioDriverHandlerThread");
            handlerThread.start();
            this.mLooper = handlerThread.getLooper();
            this.mHandlerThread = handlerThread;
        }
        this.mHandler = new VolumeHandler(this.mLooper, this);
        this.mOpen = true;
        createAudioTrack();
        if (this.mAudioTrack != null) {
            synchronized (this.mMutex) {
                this.mAudioTrack.getAudioTrack().setNotificationMarkerPosition(0);
                this.mMarkerFallbackPosition = 0;
                this.mAndroidSdkUnderrunCount = 0;
            }
        }
    }

    public AudioDriver(Context context) {
        this.mSampleFormat = 2;
        this.mMutex = new Object();
        this.mCurrentVolumeGain = AudioTrack.getMaxVolume();
        this.mCurrentVolumeTarget = 1.0f;
        this.mPaused = true;
        this.mNativeWrapper = new DefaultNativeWrapper();
        if (context != null) {
            this.mFloatPcmSupportDeviceSet = new FloatPcmSupportDeviceSet(context);
        }
    }

    public AudioDriver(NativeWrapper nativeWrapper, Looper looper, Context context) {
        this.mSampleFormat = 2;
        this.mMutex = new Object();
        this.mCurrentVolumeGain = AudioTrack.getMaxVolume();
        this.mCurrentVolumeTarget = 1.0f;
        this.mNativeWrapper = nativeWrapper;
        this.mLooper = looper;
        this.mPaused = true;
        this.mFloatPcmSupportDeviceSet = new FloatPcmSupportDeviceSet(context);
    }

    public int open(int i, int i2, int i3, int i4, int i5) {
        if (i3 != 16 && i3 != 17) {
            throw new IllegalArgumentException("Unsupported sample format!");
        }
        if (i3 == 17 && floatPcmAllowed()) {
            this.mSampleFormat = 4;
            open(i, i2, i4, i5);
            return 17;
        }
        open(i, i2, i4, i5);
        return 16;
    }
}
