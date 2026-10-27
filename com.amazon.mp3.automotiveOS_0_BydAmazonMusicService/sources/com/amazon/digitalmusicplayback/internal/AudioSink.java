package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AudioSampleFormat;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
class AudioSink {
    private static final int MAX_CONSECUTIVE_WRITE_ERRORS = 200;
    private static final int NOT_CONSUMING_BUFFER_THRESHOLD_MS = 2000;
    private static final long RECOVERY_SILENCE_LENGTH_MS = 1000;
    private static final long RECOVERY_SWITCH_GAP_MS = 2000;
    private static final long TRACK_SWITCH_GAP_MS = 1000;
    private static final AudioSampleFormat UNUSED_SAMPLE_FORMAT = AudioSampleFormat.SAMPLEFORMATU8P;
    private final Context mApplicationContext;
    private final AudioBuffer mAudioBuffer;
    private final AudioDataSource mAudioDataSource;
    private int mConsecutiveWriteErrors;
    private final DriverEventReporter mDriverEventReporter;
    private int mLastWritePlaybackPosition;
    private long mLastWriteTime;
    private final LoggingFacade.Logger mLogger;
    private final OutputDeviceWatcher mOutputDeviceWatcher;
    private String mPreviousException;
    private long mRecoveryStartTime;
    private AudioFormat mSelectedFormat;
    private boolean mStalled;
    private State mState;
    private TrackRef mTrackRef;
    private AudioSampleFormat mRecoveringFromSampleFormat = UNUSED_SAMPLE_FORMAT;
    private float mVolume = 1.0f;

    private enum State {
        IDLE,
        MAIN_PREPARING_TRACK,
        MAIN_PLAYING,
        MAIN_FINISHING,
        MAIN_RELEASE_IN_PROGRESS,
        MAIN_GAP,
        RECOVERY_BEGIN,
        RECOVERY_TAKEOVER_IN_PROGRESS,
        RECOVERY_PREPARING_TRACK,
        RECOVERY_PLAYING_SILENCE,
        RECOVERY_RELEASE_IN_PROGRESS,
        UNRECOVERABLE
    }

    AudioSink(@NonNull LoggingFacade loggingFacade, @NonNull Context context, @NonNull OutputDeviceWatcher outputDeviceWatcher, @NonNull AudioDataSource audioDataSource, @NonNull DriverEventReporter driverEventReporter, @NonNull PlatformSystemInformation platformSystemInformation) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mAudioBuffer = new AudioBuffer(loggingFacade, context, audioDataSource, driverEventReporter);
        this.mAudioDataSource = audioDataSource;
        this.mDriverEventReporter = driverEventReporter;
        this.mTrackRef = new TrackRef(loggingFacade, context, outputDeviceWatcher, driverEventReporter, platformSystemInformation);
        this.mOutputDeviceWatcher = outputDeviceWatcher;
    }

    public synchronized void activate() {
        this.mTrackRef.acquireAudioSessionId();
        this.mRecoveringFromSampleFormat = UNUSED_SAMPLE_FORMAT;
        State state = State.MAIN_GAP;
        this.mState = state;
        this.mLogger.info("activate() -> %s", state);
    }

    public synchronized boolean heartbeat() {
        State stateAct;
        try {
            stateAct = act();
        } catch (Exception e) {
            String string = e.toString();
            if (!string.equals(this.mPreviousException)) {
                this.mPreviousException = string;
                this.mLogger.error("%s threw %s", this.mState, e);
                this.mDriverEventReporter.asyncMetricUnhandledException(e, "act");
            } else {
                onMissingAudioData(true);
            }
            stateAct = State.IDLE;
        }
        if (stateAct == State.IDLE) {
            return false;
        }
        State state = this.mState;
        if (stateAct == state) {
            this.mDriverEventReporter.asyncMetricAndLogProgrammingError(this.mLogger, "heartbeat", "State should never switch to itself: %s", stateAct);
            return false;
        }
        if (stateAct == State.UNRECOVERABLE) {
            this.mDriverEventReporter.asyncMetricUnrecoverableState(state.toString());
        }
        this.mLogger.info("%s", stateAct);
        this.mState = stateAct;
        return true;
    }

    public synchronized void deactivate() {
        this.mTrackRef.asyncRelease();
        this.mAudioBuffer.makeAudioBufferEmpty();
        State state = State.IDLE;
        this.mState = state;
        this.mLogger.info("deactivate() -> %s", state);
    }

    public synchronized void setVolume(float f) {
        float f2 = this.mVolume;
        if (f2 != f) {
            this.mLogger.info("setVolume %s -> %s", Float.valueOf(f2), Float.valueOf(f));
            this.mVolume = f;
            if (!this.mTrackRef.isNull()) {
                this.mTrackRef.get().setVolume(this.mVolume);
            }
        }
    }

    public synchronized float getVolume() {
        return this.mVolume;
    }

    /* JADX INFO: renamed from: com.amazon.digitalmusicplayback.internal.AudioSink$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State = iArr;
            try {
                iArr[State.MAIN_PREPARING_TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.MAIN_PLAYING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.MAIN_FINISHING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.MAIN_RELEASE_IN_PROGRESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.MAIN_GAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.RECOVERY_BEGIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.RECOVERY_TAKEOVER_IN_PROGRESS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.RECOVERY_PREPARING_TRACK.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.RECOVERY_PLAYING_SILENCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.RECOVERY_RELEASE_IN_PROGRESS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.UNRECOVERABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[State.IDLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private synchronized State act() {
        switch (AnonymousClass1.$SwitchMap$com$amazon$digitalmusicplayback$internal$AudioSink$State[this.mState.ordinal()]) {
            case 1:
                if (!this.mTrackRef.isNull()) {
                    this.mTrackRef.asyncRelease();
                    return State.MAIN_RELEASE_IN_PROGRESS;
                }
                if (!selectDifferentAudioFormat()) {
                    return State.IDLE;
                }
                this.mTrackRef.createWithFormat(this.mSelectedFormat);
                if (this.mTrackRef.isNull()) {
                    return State.RECOVERY_BEGIN;
                }
                this.mTrackRef.get().setVolume(this.mVolume);
                if (this.mRecoveringFromSampleFormat == this.mTrackRef.audioFormat().getSampleFormat()) {
                    this.mDriverEventReporter.asyncMetricSuccessfulRecovery(this.mRecoveringFromSampleFormat);
                    this.mRecoveringFromSampleFormat = UNUSED_SAMPLE_FORMAT;
                }
                this.mStalled = true;
                this.mConsecutiveWriteErrors = 0;
                this.mLastWriteTime = System.currentTimeMillis();
                this.mLastWritePlaybackPosition = 0;
                return State.MAIN_PLAYING;
            case 2:
                if (this.mTrackRef.isNull()) {
                    return State.MAIN_PREPARING_TRACK;
                }
                if (this.mTrackRef.get().getPlayState() == 3 && this.mOutputDeviceWatcher.hasOutputDeviceChanged()) {
                    this.mLogger.info("Output device change detected", new Object[0]);
                    this.mOutputDeviceWatcher.resetIgnoredFormats();
                    return State.MAIN_FINISHING;
                }
                if (this.mAudioBuffer.remaining() == 0) {
                    long jFillAudioBuffer = this.mAudioBuffer.fillAudioBuffer(this.mTrackRef.audioFormat(), this.mTrackRef.androidFormat(), this.mTrackRef.transferSizeBytes());
                    if (jFillAudioBuffer == -1 && selectDifferentAudioFormat()) {
                        if (this.mTrackRef.get().getPlayState() == 3) {
                            return State.MAIN_FINISHING;
                        }
                        this.mTrackRef.asyncRelease();
                        return State.MAIN_RELEASE_IN_PROGRESS;
                    }
                    if (jFillAudioBuffer <= 0) {
                        onMissingAudioData(true);
                        return State.IDLE;
                    }
                }
                if (this.mAudioBuffer.remaining() > 0) {
                    int iRemaining = this.mAudioBuffer.remaining();
                    int iWriteToAudioTrack = this.mAudioBuffer.writeToAudioTrack(this.mTrackRef.get());
                    if (iWriteToAudioTrack == -6) {
                        this.mLogger.info("Recreating audio track because it is dead", new Object[0]);
                        this.mTrackRef.asyncRelease();
                        return State.MAIN_RELEASE_IN_PROGRESS;
                    }
                    if (iWriteToAudioTrack < 0) {
                        if (this.mTrackRef.get().getState() != 1) {
                            this.mLogger.error("Recreating AudioTrack because it is no longer initialized", new Object[0]);
                            this.mDriverEventReporter.asyncMetricNoLongerInitialized(this.mTrackRef.audioFormat().getSampleFormat(), this.mLastWritePlaybackPosition);
                            this.mTrackRef.asyncRelease();
                            return State.MAIN_RELEASE_IN_PROGRESS;
                        }
                        int i = this.mConsecutiveWriteErrors + 1;
                        this.mConsecutiveWriteErrors = i;
                        if (i > 200) {
                            this.mLogger.error("Recreating AudioTrack after too many consecutive write errors %s > %s", Integer.valueOf(i), 200);
                            this.mDriverEventReporter.asyncMetricConsecutiveWriteErrors(this.mTrackRef.audioFormat().getSampleFormat(), this.mLastWritePlaybackPosition);
                            this.mTrackRef.asyncRelease();
                            return State.MAIN_RELEASE_IN_PROGRESS;
                        }
                        if (iWriteToAudioTrack != -100) {
                            this.mLogger.error("AudioTrack write error: %d", Integer.valueOf(iWriteToAudioTrack));
                        }
                        onMissingAudioData(true);
                        return State.IDLE;
                    }
                    this.mConsecutiveWriteErrors = 0;
                    if (this.mAudioBuffer.remaining() == iRemaining) {
                        if (this.mTrackRef.get().getPlayState() == 3) {
                            if (this.mLastWritePlaybackPosition == this.mTrackRef.get().getPlaybackHeadPosition()) {
                                long jCurrentTimeMillis = System.currentTimeMillis() - this.mLastWriteTime;
                                if (jCurrentTimeMillis >= RECOVERY_SWITCH_GAP_MS) {
                                    this.mLogger.error("AudioTrack consumed data but has not played it for %s ms", Long.valueOf(jCurrentTimeMillis));
                                    this.mDriverEventReporter.asyncMetricNonProgression(this.mTrackRef.audioFormat().getSampleFormat(), jCurrentTimeMillis);
                                    return State.RECOVERY_BEGIN;
                                }
                            } else {
                                this.mLastWriteTime = System.currentTimeMillis();
                                this.mLastWritePlaybackPosition = this.mTrackRef.get().getPlaybackHeadPosition();
                            }
                        }
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - this.mLastWriteTime;
                        if (jCurrentTimeMillis2 >= RECOVERY_SWITCH_GAP_MS) {
                            this.mLogger.error("AudioTrack has not been accepting data for %s ms", Long.valueOf(jCurrentTimeMillis2));
                            this.mDriverEventReporter.asyncMetricNonConsumption(this.mTrackRef.audioFormat().getSampleFormat(), jCurrentTimeMillis2);
                            this.mTrackRef.asyncRelease();
                            return State.MAIN_RELEASE_IN_PROGRESS;
                        }
                        return State.IDLE;
                    }
                    if (this.mTrackRef.get().getPlayState() != 3) {
                        this.mTrackRef.play();
                        this.mOutputDeviceWatcher.checkAudioTrack(this.mTrackRef.get());
                    }
                    this.mLastWriteTime = System.currentTimeMillis();
                    this.mLastWritePlaybackPosition = this.mTrackRef.get().getPlaybackHeadPosition();
                    onMissingAudioData(false);
                }
                this.mPreviousException = null;
                return State.IDLE;
            case 3:
                if (!this.mTrackRef.isNull() && this.mTrackRef.get().getPlayState() == 3 && this.mLastWritePlaybackPosition == this.mTrackRef.get().getPlaybackHeadPosition() && System.currentTimeMillis() - this.mLastWriteTime < RECOVERY_SWITCH_GAP_MS) {
                    return State.IDLE;
                }
                this.mTrackRef.asyncRelease();
                return State.MAIN_RELEASE_IN_PROGRESS;
            case 4:
                return this.mTrackRef.isNull() ? State.MAIN_GAP : State.IDLE;
            case 5:
                return System.currentTimeMillis() - this.mTrackRef.lastReleaseTime() >= 1000 ? State.MAIN_PREPARING_TRACK : State.IDLE;
            case 6:
                this.mTrackRef.asyncRelease();
                return State.RECOVERY_TAKEOVER_IN_PROGRESS;
            case 7:
                return (!this.mTrackRef.isNull() || System.currentTimeMillis() - this.mTrackRef.lastReleaseTime() < RECOVERY_SWITCH_GAP_MS) ? State.IDLE : State.RECOVERY_PREPARING_TRACK;
            case 8:
                if (this.mRecoveringFromSampleFormat == this.mSelectedFormat.getSampleFormat()) {
                    this.mOutputDeviceWatcher.ignoreSampleFormat(this.mSelectedFormat.getSampleFormat());
                    this.mDriverEventReporter.asyncMetricFailedRecovery(this.mSelectedFormat.getSampleFormat());
                } else {
                    this.mDriverEventReporter.asyncMetricBeginRecovery(this.mSelectedFormat.getSampleFormat());
                    this.mRecoveringFromSampleFormat = this.mSelectedFormat.getSampleFormat();
                }
                this.mTrackRef.acquireAudioSessionId();
                this.mTrackRef.createWithFormat(AudioFormats.kNativeFormat);
                if (this.mTrackRef.isNull()) {
                    return State.UNRECOVERABLE;
                }
                this.mAudioBuffer.addSilenceToAudioTrack(this.mTrackRef.get());
                if (!this.mTrackRef.play()) {
                    return State.RECOVERY_BEGIN;
                }
                this.mOutputDeviceWatcher.checkAudioTrack(this.mTrackRef.get());
                this.mRecoveryStartTime = System.currentTimeMillis();
                return State.RECOVERY_PLAYING_SILENCE;
            case 9:
                if (System.currentTimeMillis() - this.mRecoveryStartTime < 1000) {
                    this.mAudioBuffer.addSilenceToAudioTrack(this.mTrackRef.get());
                    return State.IDLE;
                }
                this.mTrackRef.asyncRelease();
                return State.RECOVERY_RELEASE_IN_PROGRESS;
            case 10:
                return this.mTrackRef.isNull() ? State.MAIN_PREPARING_TRACK : State.IDLE;
            case 11:
                if (this.mOutputDeviceWatcher.hasOutputDeviceChanged()) {
                    this.mLogger.info("Output device change detected, leaving unrecoverable state", new Object[0]);
                    this.mOutputDeviceWatcher.resetIgnoredFormats();
                    return State.MAIN_PREPARING_TRACK;
                }
                return State.IDLE;
            case 12:
                return State.IDLE;
            default:
                this.mDriverEventReporter.asyncMetricAndLogProgrammingError(this.mLogger, "act", "Unhandled state: %s", this.mState);
                return State.MAIN_PREPARING_TRACK;
        }
    }

    private void onMissingAudioData(boolean z) {
        if (z != this.mStalled) {
            this.mStalled = z;
            this.mDriverEventReporter.asyncReportStalled(z);
        }
        if (z) {
            this.mAudioBuffer.addSilenceToAudioTrack(this.mTrackRef.get());
            this.mLastWriteTime = System.currentTimeMillis();
            this.mLastWritePlaybackPosition = this.mTrackRef.get().getPlaybackHeadPosition();
        }
    }

    private boolean selectDifferentAudioFormat() {
        ArrayList<AudioFormat> supportedFormats = this.mOutputDeviceWatcher.getSupportedFormats();
        int iSelectAudioDataFormat = this.mAudioDataSource.selectAudioDataFormat(supportedFormats);
        if (iSelectAudioDataFormat == -1) {
            return false;
        }
        if (iSelectAudioDataFormat >= 0 && iSelectAudioDataFormat < supportedFormats.size()) {
            this.mSelectedFormat = supportedFormats.get(iSelectAudioDataFormat);
            if (!this.mTrackRef.isNull() && AudioFormats.equals(this.mSelectedFormat, this.mTrackRef.audioFormat())) {
                this.mLogger.warning("Selected format remains the same %s", this.mSelectedFormat);
            } else {
                this.mLogger.info("Selected %s", this.mSelectedFormat);
            }
            return true;
        }
        this.mDriverEventReporter.asyncMetricAndLogProgrammingError(this.mLogger, "selectDifferentAudioFormat", "Selected invalid format index=%d size=%d", Integer.valueOf(iSelectAudioDataFormat), Integer.valueOf(supportedFormats.size()));
        return false;
    }
}
