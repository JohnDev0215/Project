package com.visualon.OSMPRender;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.os.Build;
import android.os.SystemClock;
import com.visualon.OSMPUtils.voLog;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes7.dex */
public class voAudioDSPClock {
    private static final boolean IS_DNA_TV = Build.MODEL.equals("DNA Android TV");
    private static final long MAX_AUDIO_LATENCY_US = 5000000;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    private static final int MAX_PLAYHEADPOS_OFFSET_CNT = 10;
    private static final int MIN_PLAYHEADPOS_OFFSET_SAMPLE_INTERVAL_US = 30000;
    private static final String TAG = "@@@voAudioDSPClock";
    private static final long TIME_UNSET = -9223372036854775807L;
    private static long mLastErrorPlaybackPosTime;
    private static int mRecordErrorPlaybackPosNum;
    private AudioTimestampPoller audioTimestampPoller;
    private int mAC3Bitrate;
    private Object mAudioTimestamp;
    private int mBufferSize;
    private long mForceResetWorkaroundTimeMs;
    private Method mGetLatencyMethod;
    private Method mGetPlaybackHeadPosition;
    private int mHeadPositionOffsetCnt;
    private long[] mHeadPositionOffsets;
    private long mHeadWrapCnt;
    private boolean mIsAC3;
    private long mLastFramePosition;
    private long mLastGetHeadPositionSampleTimeUs;
    private long mLastRawHeadPosition;
    private long mLatencyTimeUs;
    private int mNextHeadPositionOffsetIndex;
    private long mSmoothedHeadPositionOffsetUs;
    private OnPlaybackHeadPosErrorListener onPlaybackHeadPosErrorListener;
    private AudioTrack mAudioTrack = null;
    private int mSampleRate = 48000;
    private int mChannelCnt = 2;
    private ReentrantLock mMutex = new ReentrantLock();
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private TimerTask mAfterFlushTask = null;
    private long mDSPTime = 0;
    private int mTimerDuration = 35;
    private boolean mIsUseTimer = false;
    private boolean mAvailableCheckHeadPos = true;
    private long mRecordHeadPosUs = 0;
    public boolean mbIsBuffering = false;

    public interface OnPlaybackHeadPosErrorListener {
        void OnPlaybackHeadPosError();
    }

    private void reScheduleAudioTimer(int i, boolean z) {
        try {
            this.mMutex.lock();
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer.purge();
                this.mTimer = null;
            }
            if (!z) {
                this.mTimer = new Timer();
                TimerTask timerTask = new TimerTask() { // from class: com.visualon.OSMPRender.voAudioDSPClock.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        voAudioDSPClock.this.setDSPTime();
                    }
                };
                this.mTimerTask = timerTask;
                try {
                    long j = i;
                    this.mTimer.scheduleAtFixedRate(timerTask, j, j);
                } catch (Exception e) {
                    e.getStackTrace();
                    this.mTimer = null;
                }
            }
        } finally {
            this.mMutex.unlock();
        }
    }

    private void reScheduleFlushTimer(boolean z) {
        try {
            this.mMutex.lock();
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer.purge();
                this.mTimer = null;
            }
            if (!z) {
                this.mTimer = new Timer();
                TimerTask timerTask = new TimerTask() { // from class: com.visualon.OSMPRender.voAudioDSPClock.2
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        voAudioDSPClock.this.waitForPlay();
                    }
                };
                this.mAfterFlushTask = timerTask;
                try {
                    this.mTimer.schedule(timerTask, 1L, 2L);
                } catch (Exception e) {
                    e.getStackTrace();
                    this.mTimer = null;
                }
            }
        } finally {
            this.mMutex.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForPlay() {
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack != null && audioTrack.getPlayState() == 3) {
            reScheduleFlushTimer(true);
            reScheduleAudioTimer(this.mTimerDuration, false);
        } else {
            this.mDSPTime = 0L;
        }
    }

    public voAudioDSPClock() {
        this.mGetLatencyMethod = null;
        this.mGetPlaybackHeadPosition = null;
        this.mAudioTimestamp = null;
        try {
            this.mAudioTimestamp = new AudioTimestamp();
            this.mMutex.lock();
            this.mForceResetWorkaroundTimeMs = TIME_UNSET;
            this.mHeadPositionOffsets = new long[10];
            try {
                this.mGetPlaybackHeadPosition = AudioTrack.class.getMethod("getPlaybackHeadPosition", null);
            } catch (NoSuchMethodException unused) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Android AudioTrack getPlaybackHeadPosition method does not supported on the device.", new Object[0]);
                }
            }
            try {
                this.mGetLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused2) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Android AudioTrack getLatency method does not supported on the device.", new Object[0]);
                }
            }
            reset();
        } finally {
            this.mMutex.unlock();
        }
    }

    public void startAudioDSPClockTimer() {
        try {
            this.mMutex.lock();
            if (this.mTimer != null) {
                return;
            }
            reScheduleAudioTimer(this.mTimerDuration, false);
            this.mIsUseTimer = true;
        } finally {
            this.mMutex.unlock();
        }
    }

    public void stopAudioDSPClockTimer() {
        try {
            this.mMutex.lock();
            reScheduleAudioTimer(0, true);
            this.mIsUseTimer = false;
        } finally {
            this.mMutex.unlock();
        }
    }

    public void playAudioTrack() {
        try {
            this.mMutex.lock();
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                audioTrack.play();
                this.audioTimestampPoller.reset();
            }
        } finally {
            this.mMutex.unlock();
        }
    }

    public void flush() {
        try {
            this.mMutex.lock();
            reScheduleAudioTimer(0, true);
            this.mDSPTime = 0L;
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "XXX flush", new Object[0]);
            }
            reset();
        } finally {
            if (this.mIsUseTimer) {
                reScheduleFlushTimer(false);
            }
            this.mMutex.unlock();
        }
    }

    public long getDSPTimeStamp() {
        return this.mDSPTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDSPTime() {
        long jFramesToDurationUs;
        long headPosUs;
        try {
            this.mMutex.lock();
            AudioTrack audioTrack = this.mAudioTrack;
            if (audioTrack != null) {
                if (!this.mbIsBuffering && audioTrack.getPlayState() == 3) {
                    updatePlaybackPositionParams();
                }
                long jNanoTime = System.nanoTime() / 1000;
                if (this.audioTimestampPoller.hasTimestamp()) {
                    jFramesToDurationUs = framesToDurationUs(this.audioTimestampPoller.getTimestampPositionFrames());
                    if (this.audioTimestampPoller.isTimestampAdvancing()) {
                        jFramesToDurationUs += jNanoTime - this.audioTimestampPoller.getTimestampSystemTimeUs();
                    }
                } else {
                    if (this.mHeadPositionOffsetCnt == 0) {
                        headPosUs = getHeadPosUs();
                    } else {
                        headPosUs = jNanoTime + this.mSmoothedHeadPositionOffsetUs;
                    }
                    if (!this.mIsAC3) {
                        headPosUs -= this.mLatencyTimeUs;
                    }
                    jFramesToDurationUs = headPosUs;
                }
            } else {
                jFramesToDurationUs = 0;
            }
            long j = jFramesToDurationUs / 1000;
            this.mDSPTime = j;
            if (j < 0) {
                this.mDSPTime = 0L;
            }
        } finally {
            this.mMutex.unlock();
        }
    }

    public long getLatency(long j) {
        long jIntValue;
        AudioTrack audioTrack;
        try {
            this.mMutex.lock();
            Method method = this.mGetLatencyMethod;
            if (method == null || (audioTrack = this.mAudioTrack) == null) {
                jIntValue = 0;
            } else {
                try {
                    jIntValue = ((Integer) method.invoke(audioTrack, null)).intValue();
                } catch (Exception unused) {
                    this.mGetLatencyMethod = null;
                    jIntValue = 0;
                }
            }
            Method method2 = this.mGetLatencyMethod;
            long jFramesToDurationUs = (jIntValue * 1000) - framesToDurationUs(bytesToFrames(this.mBufferSize));
            this.mLatencyTimeUs = jFramesToDurationUs;
            long jMax = Math.max(jFramesToDurationUs, 0L);
            this.mLatencyTimeUs = jMax;
            if (jMax > 5000000) {
                if (voLog.enablePrintLog()) {
                    voLog.e(TAG, "Audio latency too large: " + this.mLatencyTimeUs, new Object[0]);
                }
                this.mLatencyTimeUs = 0L;
            }
            return this.mLatencyTimeUs;
        } finally {
            this.mMutex.unlock();
        }
    }

    private long bytesToFrames(long j) {
        if (this.mIsAC3) {
            int i = this.mAC3Bitrate;
            if (i == 0) {
                return 0L;
            }
            return ((j * 8) * ((long) this.mSampleRate)) / ((long) (i * 1000));
        }
        return j / ((long) (this.mChannelCnt * 2));
    }

    private static int getAC3Bitrate(int i, int i2) {
        return (int) (((double) (((i * 8) * i2) / 1536000)) + 0.5d);
    }

    public voAudioDSPClock setAudioTrack(AudioTrack audioTrack) {
        try {
            this.mMutex.lock();
            this.mAudioTrack = audioTrack;
            this.audioTimestampPoller = new AudioTimestampPoller(audioTrack);
            return this;
        } finally {
            this.mMutex.unlock();
        }
    }

    public voAudioDSPClock reconfig(int i, boolean z, int i2, int i3) {
        try {
            this.mMutex.lock();
            this.mSampleRate = i;
            this.mChannelCnt = i3;
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "channel count is " + this.mChannelCnt, new Object[0]);
            }
            this.mIsAC3 = z;
            this.mBufferSize = i2;
            this.mAC3Bitrate = getAC3Bitrate(i2, i);
            reset();
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "mIsAC3==" + this.mIsAC3, new Object[0]);
            }
            getLatency(0L);
            return this;
        } finally {
            this.mMutex.unlock();
        }
    }

    public void resetWhenPause() {
        try {
            this.mMutex.lock();
            AudioTimestampPoller audioTimestampPoller = this.audioTimestampPoller;
            if (audioTimestampPoller != null) {
                audioTimestampPoller.reset();
            }
            this.mSmoothedHeadPositionOffsetUs = 0L;
            this.mHeadPositionOffsetCnt = 0;
            this.mNextHeadPositionOffsetIndex = 0;
            this.mLastGetHeadPositionSampleTimeUs = 0L;
        } finally {
            this.mMutex.unlock();
        }
    }

    public void startBuffering() {
        this.mbIsBuffering = true;
    }

    public void stopBuffering() {
        this.mbIsBuffering = false;
    }

    private void reset() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "XXX reset", new Object[0]);
        }
        this.mLastRawHeadPosition = 0L;
        this.mHeadWrapCnt = 0L;
        this.mLastFramePosition = 0L;
        this.mLatencyTimeUs = 0L;
        this.mDSPTime = 0L;
        this.mAvailableCheckHeadPos = true;
        this.mRecordHeadPosUs = 0L;
        resetWhenPause();
    }

    private long getHeadPos() {
        long jIntValue;
        int playState = this.mAudioTrack.getPlayState();
        if (playState == 1) {
            return 0L;
        }
        Method method = this.mGetPlaybackHeadPosition;
        if (method != null) {
            try {
                jIntValue = ((long) ((Integer) method.invoke(this.mAudioTrack, null)).intValue()) & 4294967295L;
            } catch (Exception unused) {
                this.mGetPlaybackHeadPosition = null;
                jIntValue = 0;
            }
        } else {
            jIntValue = 0;
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "getPlaybackHeadPosition: rawPos curPlaybackHeadPosition" + jIntValue, new Object[0]);
        }
        if (IS_DNA_TV) {
            if (jIntValue == 0 && this.onPlaybackHeadPosErrorListener != null) {
                if (voLog.enablePrintLog() && mRecordErrorPlaybackPosNum > 50) {
                    voLog.e(TAG, "OnPlaybackHeadPosError , curPlaybackHeadPosition == 0  mRecordErrorPlaybackPosNum = " + mRecordErrorPlaybackPosNum, new Object[0]);
                }
                if (mRecordErrorPlaybackPosNum == 80) {
                    playbackHeadPosError();
                    mRecordErrorPlaybackPosNum = 0;
                }
                mRecordErrorPlaybackPosNum++;
            } else {
                mRecordErrorPlaybackPosNum = 0;
            }
        }
        if (Build.VERSION.SDK_INT <= 28) {
            if (jIntValue == 0 && this.mLastRawHeadPosition > 0 && playState == 3) {
                if (this.mForceResetWorkaroundTimeMs == TIME_UNSET) {
                    this.mForceResetWorkaroundTimeMs = SystemClock.elapsedRealtime();
                }
                return this.mLastRawHeadPosition;
            }
            this.mForceResetWorkaroundTimeMs = TIME_UNSET;
        }
        if (this.mLastRawHeadPosition > jIntValue) {
            this.mHeadWrapCnt++;
        }
        this.mLastRawHeadPosition = jIntValue;
        return jIntValue + (this.mHeadWrapCnt << 32);
    }

    public void setOnPlaybackHeadPosErrorListener(OnPlaybackHeadPosErrorListener onPlaybackHeadPosErrorListener) {
        this.onPlaybackHeadPosErrorListener = onPlaybackHeadPosErrorListener;
    }

    private long getHeadPosUs() {
        long headPos = (getHeadPos() * 1000000) / ((long) this.mSampleRate);
        if (IS_DNA_TV) {
            if (headPos - this.mRecordHeadPosUs > 10000000) {
                playbackHeadPosError();
                if (voLog.enablePrintLog()) {
                    voLog.e(TAG, "OnPlaybackHeadPosError , getHeadPos : curHeadPosUs: " + headPos + "mRecordHeadPosUs:" + this.mRecordHeadPosUs, new Object[0]);
                }
            }
            this.mRecordHeadPosUs = headPos;
        }
        return headPos;
    }

    private void playbackHeadPosError() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (voLog.enablePrintLog()) {
            voLog.e(TAG, "OnPlaybackHeadPosError , playbackHeadPosError :  currentTime ：" + jUptimeMillis + "   lastTime : " + mLastErrorPlaybackPosTime, new Object[0]);
        }
        if (jUptimeMillis - mLastErrorPlaybackPosTime > 500) {
            mLastErrorPlaybackPosTime = jUptimeMillis;
            this.onPlaybackHeadPosErrorListener.OnPlaybackHeadPosError();
        }
    }

    private void updatePlaybackPositionParams() {
        long headPosUs = getHeadPosUs();
        if (headPosUs == 0) {
            return;
        }
        long jNanoTime = System.nanoTime() / 1000;
        voLog.enablePrintLog();
        if (jNanoTime - this.mLastGetHeadPositionSampleTimeUs >= 30000) {
            long[] jArr = this.mHeadPositionOffsets;
            int i = this.mNextHeadPositionOffsetIndex;
            jArr[i] = headPosUs - jNanoTime;
            this.mNextHeadPositionOffsetIndex = (i + 1) % 10;
            int i2 = this.mHeadPositionOffsetCnt;
            if (i2 < 10) {
                this.mHeadPositionOffsetCnt = i2 + 1;
            }
            this.mLastGetHeadPositionSampleTimeUs = jNanoTime;
            this.mSmoothedHeadPositionOffsetUs = 0L;
            int i3 = 0;
            while (true) {
                int i4 = this.mHeadPositionOffsetCnt;
                if (i3 >= i4) {
                    break;
                }
                this.mSmoothedHeadPositionOffsetUs += this.mHeadPositionOffsets[i3] / ((long) i4);
                i3++;
            }
        }
        maybePollAndCheckTimestamp(jNanoTime, headPosUs);
    }

    private void maybePollAndCheckTimestamp(long j, long j2) {
        if (this.audioTimestampPoller.maybePollTimestamp(j)) {
            long timestampSystemTimeUs = this.audioTimestampPoller.getTimestampSystemTimeUs();
            long timestampPositionFrames = this.audioTimestampPoller.getTimestampPositionFrames();
            if (Math.abs(timestampSystemTimeUs - j) > 5000000) {
                this.audioTimestampPoller.rejectTimestamp();
            } else if (Math.abs(framesToDurationUs(timestampPositionFrames) - j2) > 5000000) {
                this.audioTimestampPoller.rejectTimestamp();
            } else {
                this.audioTimestampPoller.acceptTimestamp();
            }
        }
    }

    private long framesToDurationUs(long j) {
        return (j * 1000000) / ((long) this.mSampleRate);
    }

    private long convertDurationUsToFrames(long j) {
        return (j * ((long) this.mSampleRate)) / 1000000;
    }

    private long getAudioTimestampNanoTime() {
        Object obj = this.mAudioTimestamp;
        if (obj != null) {
            return ((AudioTimestamp) obj).nanoTime;
        }
        return 0L;
    }

    private long getAudioTimestampFramePosition() {
        return this.mLastFramePosition;
    }
}
