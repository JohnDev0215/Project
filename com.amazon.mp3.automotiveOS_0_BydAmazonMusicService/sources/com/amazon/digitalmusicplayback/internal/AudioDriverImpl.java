package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.digitalmusicplayback.OutputDevice;
import com.amazon.digitalmusicplayback.OutputDeviceAttributes;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public final class AudioDriverImpl extends AudioDriver {
    private final Context mApplicationContext;
    private AudioSink mAudioSink;
    private DriverEventReporter mDriverEventReporter;
    private final LoggingFacade.Logger mLogger;
    private final OutputDeviceAttributes mOutputDeviceAttributes;
    private OutputDeviceWatcher mOutputDeviceWatcher;
    private final PlatformSystemInformation mPlatformSystemInformation;
    private Thread mThread;
    private final AtomicBoolean mThreadCancelled = new AtomicBoolean(false);

    public AudioDriverImpl(@NonNull LoggingFacade loggingFacade, @NonNull Context context, @NonNull PlatformSystemInformation platformSystemInformation) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mPlatformSystemInformation = platformSystemInformation;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OutputDevice("", ""));
        this.mOutputDeviceAttributes = new OutputDeviceAttributes(arrayList, (OutputDevice) arrayList.get(0), false);
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public ErrorCode init(@NonNull AudioDataSource audioDataSource, @NonNull AudioDriverEventsListener audioDriverEventsListener) {
        if (audioDataSource == null || audioDriverEventsListener == null) {
            this.mLogger.error("AudioDriver initialized with null arguments", new Object[0]);
            return ErrorCode.AUDIODRIVERFAILURE;
        }
        this.mDriverEventReporter = new DriverEventReporter(this.mLogger.getLoggingFacade(), this.mApplicationContext, audioDriverEventsListener);
        this.mOutputDeviceWatcher = new OutputDeviceWatcher(this.mLogger.getLoggingFacade(), this.mApplicationContext, this.mDriverEventReporter, this.mPlatformSystemInformation);
        this.mAudioSink = new AudioSink(this.mLogger.getLoggingFacade(), this.mApplicationContext, this.mOutputDeviceWatcher, audioDataSource, this.mDriverEventReporter, this.mPlatformSystemInformation);
        return ErrorCode.NOERROR;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public synchronized ErrorCode start() {
        if (this.mAudioSink == null) {
            this.mLogger.error("start: attempting to start uninitialized driver", new Object[0]);
            return ErrorCode.ENGINENOTINITIALIZED;
        }
        if (this.mThread != null) {
            this.mLogger.info("start: the driver is already playing", new Object[0]);
            return ErrorCode.NOERROR;
        }
        try {
            this.mLogger.info("starting AudioDriver", new Object[0]);
            this.mAudioSink.activate();
            this.mThreadCancelled.set(false);
            Thread thread = new Thread(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.AudioDriverImpl$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.driverThread();
                }
            }, "kDriverThread");
            this.mThread = thread;
            thread.setDaemon(true);
            this.mThread.setPriority(9);
            this.mThread.start();
            return ErrorCode.NOERROR;
        } catch (Exception e) {
            this.mLogger.error("Could not start audio driver: %s", e);
            this.mDriverEventReporter.asyncMetricUnhandledException(e, AsmString.METHOD_FIREBASE_PERF_TRACE_START);
            return ErrorCode.AUDIODRIVERFAILURE;
        }
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public ErrorCode stop() {
        Thread thread;
        if (this.mAudioSink == null) {
            return ErrorCode.ENGINENOTINITIALIZED;
        }
        this.mLogger.info("Begin stopping AudioDriver", new Object[0]);
        try {
            this.mAudioSink.deactivate();
            synchronized (this) {
                thread = this.mThread;
                this.mThread = null;
            }
            if (thread != null) {
                this.mLogger.debug("Stopping the thread", new Object[0]);
                try {
                    this.mThreadCancelled.set(true);
                    thread.join();
                } catch (InterruptedException unused) {
                    this.mLogger.warning("Interrupted while waiting for audio driver thread to finish, tid=%d", Long.valueOf(thread.getId()));
                }
            }
            this.mLogger.info("Full stop reached", new Object[0]);
            return ErrorCode.NOERROR;
        } catch (Exception e) {
            this.mLogger.error("Could not deactivate AudioSink: %s", e);
            this.mDriverEventReporter.asyncMetricUnhandledException(e, "deactivate");
            return ErrorCode.AUDIODRIVERFAILURE;
        }
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public synchronized ArrayList<AudioFormat> getSupportedFormats() {
        if (this.mAudioSink == null) {
            return new ArrayList<>();
        }
        if (this.mThread == null && this.mOutputDeviceWatcher.hasOutputDeviceChanged()) {
            this.mOutputDeviceWatcher.checkAudioTrack(null);
        }
        return this.mOutputDeviceWatcher.getSupportedFormats();
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public synchronized ErrorCode setVolume(double d) {
        AudioSink audioSink = this.mAudioSink;
        if (audioSink != null) {
            audioSink.setVolume((float) d);
            return ErrorCode.NOERROR;
        }
        return ErrorCode.ENGINENOTINITIALIZED;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public synchronized double getVolume() {
        return this.mAudioSink != null ? r0.getVolume() : 0.0d;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public synchronized ErrorCode setOutputDevice(String str) {
        return ErrorCode.UNSUPPORTEDDEVICETYPE;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public OutputDeviceAttributes getOutputDeviceAttributes() {
        return this.mOutputDeviceAttributes;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public ErrorCode setExclusiveMode(boolean z) {
        return ErrorCode.UNSUPPORTEDDEVICETYPE;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public synchronized ErrorCode setHiResOutputEnabled(boolean z) {
        if (this.mAudioSink == null) {
            return ErrorCode.ENGINENOTINITIALIZED;
        }
        if (this.mPlatformSystemInformation.isRunningOnTV()) {
            this.mOutputDeviceWatcher.setHiResOutputEnabled(z);
            return ErrorCode.NOERROR;
        }
        this.mLogger.error("setHiResOutputEnabled(%s): this API is only supported when running on TVs", Boolean.valueOf(z));
        return ErrorCode.UNSUPPORTEDDEVICETYPE;
    }

    @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
    public boolean isHiResOutputEnabled() {
        if (this.mAudioSink != null) {
            return this.mOutputDeviceWatcher.isHiResOutputEnabled();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void driverThread() {
        while (!this.mThreadCancelled.get()) {
            if (!this.mAudioSink.heartbeat()) {
                try {
                    Thread.currentThread();
                    Thread.sleep(10L);
                } catch (InterruptedException unused) {
                    this.mLogger.debug("Idle sleep interrupted", new Object[0]);
                }
            }
        }
    }
}
