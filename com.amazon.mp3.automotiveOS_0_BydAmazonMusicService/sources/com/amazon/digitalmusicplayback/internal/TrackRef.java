package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes2.dex */
class TrackRef {
    public static final AudioAttributes COMMON_MUSIC_ATTRIBUTES = new AudioAttributes.Builder().setUsage(1).setContentType(2).build();
    private static final int INTERNAL_BUFFER_SIZE_MULTIPLIER = 2;
    private static final int MAX_BUFFER_SIZE = 921600;
    private static final int MIN_TRANSFER_CHUNK_SIZE = 49184;
    private AudioFormat mAndroidFormat;
    private final Context mApplicationContext;
    private AudioTrack mAudioTrack;
    private com.amazon.digitalmusicplayback.AudioFormat mAudioTrackFormat;
    private final DriverEventReporter mDriverEventReporter;
    private final LoggingFacade.Logger mLogger;
    private final OutputDeviceWatcher mOutputDeviceWatcher;
    private final PlatformSystemInformation mPlatformSystemInformation;
    private int mAudioSessionId = -1;
    private long mAudioTrackLastReleaseTime = 0;
    private int mTransferSizeBytes = MIN_TRANSFER_CHUNK_SIZE;

    TrackRef(@NonNull LoggingFacade loggingFacade, @NonNull Context context, @NonNull OutputDeviceWatcher outputDeviceWatcher, @NonNull DriverEventReporter driverEventReporter, @NonNull PlatformSystemInformation platformSystemInformation) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mOutputDeviceWatcher = outputDeviceWatcher;
        this.mDriverEventReporter = driverEventReporter;
        this.mPlatformSystemInformation = platformSystemInformation;
    }

    public synchronized void acquireAudioSessionId() {
        AudioManager audioManager = (AudioManager) this.mApplicationContext.getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO);
        if (audioManager != null) {
            this.mAudioSessionId = audioManager.generateAudioSessionId();
        }
    }

    public synchronized void asyncRelease() {
        if (this.mAudioTrack == null) {
            return;
        }
        this.mLogger.info("Begin releasing AudioTrack", new Object[0]);
        final AudioTrack audioTrack = this.mAudioTrack;
        Thread thread = new Thread(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.TrackRef$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$asyncRelease$0(audioTrack);
            }
        }, "kDriverReleaseThread");
        thread.setDaemon(true);
        thread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$asyncRelease$0(AudioTrack audioTrack) {
        releaseAudioTrack(audioTrack);
        completeReleasingAudioTrack();
    }

    public synchronized AudioFormat androidFormat() {
        return this.mAndroidFormat;
    }

    public synchronized com.amazon.digitalmusicplayback.AudioFormat audioFormat() {
        return this.mAudioTrackFormat;
    }

    public synchronized void createWithFormat(com.amazon.digitalmusicplayback.AudioFormat audioFormat) {
        AudioFormat androidFormat;
        int minBufferSize;
        int iMax;
        AudioTrack audioTrackBuild;
        this.mLogger.info("Creating AudioTrack with %s", audioFormat);
        try {
            androidFormat = this.mOutputDeviceWatcher.toAndroidFormat(audioFormat);
            minBufferSize = AudioTrack.getMinBufferSize(androidFormat.getSampleRate(), androidFormat.getChannelMask(), androidFormat.getEncoding());
            if (minBufferSize <= 0) {
                this.mLogger.error("AudioTrack.getMinBufferSize returned %s", Integer.valueOf(minBufferSize));
                minBufferSize = 24592;
            }
            iMax = Math.max(minBufferSize, Math.min(MAX_BUFFER_SIZE, minBufferSize * 2));
            int i = Build.VERSION.SDK_INT;
            AudioTrack.Builder audioFormat2 = new AudioTrack.Builder().setAudioAttributes(COMMON_MUSIC_ATTRIBUTES).setAudioFormat(androidFormat);
            audioFormat2.setBufferSizeInBytes(iMax);
            int i2 = this.mAudioSessionId;
            if (i2 >= 0) {
                audioFormat2.setSessionId(i2);
            }
            if (i >= 26 && !this.mPlatformSystemInformation.isRunningOnTV()) {
                audioFormat2.setPerformanceMode(2);
            }
            audioTrackBuild = audioFormat2.build();
            this.mAudioTrack = audioTrackBuild;
        } catch (Exception e) {
            this.mLogger.error("Cannot create AudioTrack with %s -- %s", audioFormat, e);
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                audioTrack.release();
                this.mAudioTrack = null;
            }
        }
        if (audioTrackBuild != null && audioTrackBuild.getState() == 1) {
            this.mTransferSizeBytes = Math.max(minBufferSize, MIN_TRANSFER_CHUNK_SIZE);
            this.mLogger.info("Internal buffer size %s, transfer chunk size %s", Integer.valueOf(iMax), Integer.valueOf(this.mTransferSizeBytes));
            this.mAndroidFormat = androidFormat;
            this.mAudioTrackFormat = audioFormat;
            return;
        }
        this.mLogger.error("AudioTrack failed to initialize with %s", audioFormat);
        this.mAudioTrack.release();
        this.mAudioTrack = null;
    }

    public synchronized AudioTrack get() {
        return this.mAudioTrack;
    }

    public synchronized boolean isNull() {
        return this.mAudioTrack == null;
    }

    public synchronized long lastReleaseTime() {
        return this.mAudioTrackLastReleaseTime;
    }

    public synchronized boolean play() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null) {
            return false;
        }
        audioTrack.play();
        return this.mAudioTrack.getPlayState() == 3;
    }

    public synchronized int transferSizeBytes() {
        return this.mTransferSizeBytes;
    }

    private synchronized void completeReleasingAudioTrack() {
        if (this.mAudioTrack != null) {
            this.mLogger.info("End releasing AudioTrack", new Object[0]);
        }
        this.mAudioTrack = null;
        this.mAudioTrackLastReleaseTime = System.currentTimeMillis();
    }

    private void releaseAudioTrack(AudioTrack audioTrack) {
        if (audioTrack == null) {
            return;
        }
        synchronized (audioTrack) {
            if (audioTrack.getState() != 0) {
                try {
                    if (audioTrack.getPlayState() == 3) {
                        audioTrack.pause();
                    }
                    audioTrack.flush();
                } catch (Exception e) {
                    this.mLogger.warning("When releasing audio track %s", e);
                    this.mDriverEventReporter.asyncMetricUnhandledException(e, "releaseAudioTrack");
                }
                audioTrack.release();
            } else {
                audioTrack.release();
            }
        }
    }
}
