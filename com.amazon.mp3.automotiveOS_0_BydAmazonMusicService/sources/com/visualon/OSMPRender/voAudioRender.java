package com.visualon.OSMPRender;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.compose.runtime.ComposerKt;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.visualon.OSMPRender.voAudioDSPClock;
import com.visualon.OSMPUtils.voBluetoothManager;
import com.visualon.OSMPUtils.voLog;
import com.visualon.OSMPUtils.voOSAudioFormat;
import com.visualon.OSMPUtils.voOSAudioRenderFormat;
import com.visualon.OSMPUtils.voOSType;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes7.dex */
public class voAudioRender {
    private static final int AC3_BUFFER_MULTIPLICATION_FACTOR = 2;
    private static final boolean ISAMAZONFAMILY;
    private static final boolean ISFORCEDISABLEDSP;
    private static final boolean IS_TIVO_DEVICE;
    private static final long MAX_BUFFER_DURATION_US = 750000;
    private static final long MIN_BUFFER_DURATION_US = 250000;
    private static final long PASSTHROUGH_BUFFER_DURATION_US = 250000;
    private static final int RESET_THRESHOLD = 2500;
    private static String TAG = "@@@voAudioRender";
    private static final int TRY_LOCK_TIME_OUT = 1500;
    static int maxSampleRates;
    private final Handler handler;
    private voAudioDSPClock mAudioDSPClock;
    private AudioTrack mAudioTrack;
    private ByteBuffer mByteBuffer;
    private int mChannels;
    private ReentrantLock mCompensateLock;
    private int mFormat;
    private ReentrantLock mLock;
    private boolean mNotifyAudioFailed;
    private onAudioRenderListener mPlayer;
    private int mRenderFormat;
    private int mSampleBit;
    private int mSampleRate;
    private boolean mSendRenderStart;
    private RenderStatus mStatus;
    Thread mThreadPlayback;
    private boolean mbWrite;
    runPlayback mrunPlayback;
    public static final int RENDER_FORMAT_PCM = voOSType.VOOSMP_AUDIO_CODINGTYPE.VOOSMP_AUDIO_CodingPCM.ordinal();
    public static final int RENDER_FORMAT_AC3 = voOSType.VOOSMP_AUDIO_CODINGTYPE.VOOSMP_AUDIO_CodingAC3.ordinal();
    public static final int RENDER_FORMAT_EAC3 = voOSType.VOOSMP_AUDIO_CODINGTYPE.VOOSMP_AUDIO_CodingEAC3.ordinal();
    private boolean bDSPSetOnce = false;
    private boolean OPEN_TRACK = false;
    private int ENCODING_AC3 = -1;
    private int ENCODING_EAC3 = -1;
    private float mLeftVolume = -1.0f;
    private float mRightVolume = -1.0f;
    public boolean m_bluetoothEnabled = false;
    private boolean mEnableDSPClock = false;
    private boolean m_bHdmiDisconnect = false;
    private long hdmiDisconnectStartTime = 0;
    private boolean mReconfigAudioTrack = false;
    private boolean mbSeeking = false;
    private int mAudioSessionID = 0;
    private UNSTABLE_REASON mTriggerReason = UNSTABLE_REASON.NONE;
    private long mTransientTime = System.currentTimeMillis();
    private long mRecalcTime = System.currentTimeMillis();
    private int mResetAudioDSPClockCount = 0;
    private long mLastAudioWriteFailedTime = 0;
    private int mAudioWriteErrorCnt = 0;
    private Method mGetLatencyMethod = null;
    private voAudioDSPClock.OnPlaybackHeadPosErrorListener onPlaybackHeadPosErrorListener = new voAudioDSPClock.OnPlaybackHeadPosErrorListener() { // from class: com.visualon.OSMPRender.voAudioRender.1
        @Override // com.visualon.OSMPRender.voAudioDSPClock.OnPlaybackHeadPosErrorListener
        public void OnPlaybackHeadPosError() {
            voAudioRender.this.mPlayer.onAudioRenderEvent(voOSType.VOOSMP_CB_Auido_Seek_Failed, 200, 0, null);
        }
    };
    private long mNativeContext = 0;

    private enum RenderStatus {
        STOP,
        PLAY,
        PAUSE
    }

    public enum UNSTABLE_REASON {
        NONE,
        SEEK,
        CONNECTION_STATE_CHANGE
    }

    public interface onAudioRenderListener {
        long GetAudioData(byte[] bArr);

        int onAudioRenderEvent(int i, int i2, int i3, Object obj);

        voOSAudioFormat onGetAudioFormat();

        Object onGetAudioParam(long j);

        int onGetFormatID();

        long onSetAudioParam(int i, Object obj);
    }

    private int getChannelConfig(int i) {
        switch (i) {
            case 1:
                return 4;
            case 2:
                return 12;
            case 3:
                return 28;
            case 4:
                return ComposerKt.providerMapsKey;
            case 5:
                return 220;
            case 6:
                return 252;
            case 7:
                return 1276;
            case 8:
                return 6396;
            default:
                return 0;
        }
    }

    private native int nativeGetAudioFormat(long j, int i, int i2);

    private native void nativeInit();

    private native boolean nativeSetAudioTrack(long j, AudioTrack audioTrack);

    private native void nativeUninit();

    static {
        String str = Build.MODEL;
        IS_TIVO_DEVICE = str.equals("IPA-1104HDH-01");
        boolean z = true;
        ISAMAZONFAMILY = (new String(Build.MANUFACTURER).equals("Amazon") && new String(str).startsWith("AFT")) || str.equals("SM-G9200");
        if (!str.startsWith("KFTHWI") && !str.startsWith("KFAPWI")) {
            z = false;
        }
        ISFORCEDISABLEDSP = z;
        maxSampleRates = 0;
    }

    private int getAudioTrackLatency() {
        AudioTrack audioTrack;
        int iIntValue;
        Method method = this.mGetLatencyMethod;
        if (method != null && (audioTrack = this.mAudioTrack) != null) {
            try {
                iIntValue = ((Integer) method.invoke(audioTrack, null)).intValue();
            } catch (Exception unused) {
                iIntValue = 0;
            }
            try {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "Get audio latency succeed latency buffer time " + iIntValue + "!", new Object[0]);
                }
            } catch (Exception unused2) {
                if (voLog.enablePrintLog()) {
                    voLog.e(TAG, "Cannot get audio latency from audio system !", new Object[0]);
                }
            }
            return iIntValue;
        }
        if (!voLog.enablePrintLog()) {
            return 0;
        }
        voLog.e(TAG, "Cannot get audio latency from audio system !", new Object[0]);
        return 0;
    }

    public void hdmiDisConnect() {
        this.hdmiDisconnectStartTime = System.nanoTime();
        this.m_bHdmiDisconnect = true;
    }

    public void SetAudioSessionID(int i) {
        this.mAudioSessionID = i;
    }

    public boolean DSPClockEnable() {
        return this.mAudioDSPClock != null;
    }

    private class runPlayback implements Runnable {
        private voAudioRender mAudioRender;

        public runPlayback(voAudioRender voaudiorender) {
            this.mAudioRender = voaudiorender;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mAudioRender.playback();
        }
    }

    public voAudioRender(onAudioRenderListener onaudiorenderlistener) {
        this.mAudioTrack = null;
        this.mAudioDSPClock = null;
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.handler = new Handler(looperMyLooper);
            voLog.v(TAG, "Looper.myLooper()", new Object[0]);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.handler = new Handler(mainLooper);
                voLog.v(TAG, "getMainLooper()", new Object[0]);
            } else {
                Looper.prepare();
                this.handler = new Handler();
            }
        }
        this.mPlayer = onaudiorenderlistener;
        this.mAudioTrack = null;
        this.mSampleRate = 0;
        this.mChannels = 0;
        this.mRenderFormat = 0;
        this.mSampleBit = 0;
        this.mByteBuffer = null;
        this.mStatus = RenderStatus.STOP;
        this.mbWrite = false;
        this.mSendRenderStart = true;
        this.mNotifyAudioFailed = false;
        this.mLock = new ReentrantLock();
        this.mCompensateLock = new ReentrantLock();
        if ((ISAMAZONFAMILY || voBluetoothManager.isBluetoothConnected() || this.mEnableDSPClock) && this.mAudioDSPClock == null && !ISFORCEDISABLEDSP) {
            voAudioDSPClock voaudiodspclock = new voAudioDSPClock();
            this.mAudioDSPClock = voaudiodspclock;
            voaudiodspclock.setOnPlaybackHeadPosErrorListener(this.onPlaybackHeadPosErrorListener);
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "voAudioRender Construct", new Object[0]);
        }
    }

    private long getDSPTimeStamp() {
        long dSPTimeStamp;
        voAudioDSPClock voaudiodspclock;
        this.mCompensateLock.lock();
        try {
            boolean z = this.OPEN_TRACK;
            if (!z && !this.mbSeeking && (voaudiodspclock = this.mAudioDSPClock) != null) {
                dSPTimeStamp = voaudiodspclock.getDSPTimeStamp();
            } else {
                if ((!this.mbSeeking || !z) && voLog.enablePrintLog()) {
                    voLog.d(TAG, "getDSPTimeStamp: mbSeeking :" + this.mbSeeking + "  OPEN_TRACK:" + this.OPEN_TRACK, new Object[0]);
                }
                dSPTimeStamp = 0;
            }
            return dSPTimeStamp;
        } finally {
            this.mCompensateLock.unlock();
        }
    }

    private static int getMaxSampleRate() {
        if (maxSampleRates == 0) {
            int[] iArr = {8000, 11025, 16000, 22050, 32000, 37800, 44056, 44100, 47250, 4800, 50000, 50400, 88200, 96000, 176400, 192000, 352800, 2822400, 5644800};
            int i = 18;
            while (true) {
                if (i >= 0) {
                    int minBufferSize = AudioTrack.getMinBufferSize(iArr[i], 4, 2);
                    if (minBufferSize != -1 && minBufferSize != -2 && minBufferSize > 0) {
                        maxSampleRates = iArr[i];
                        break;
                    }
                    i--;
                } else {
                    break;
                }
            }
        }
        return maxSampleRates;
    }

    public void run() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "run-  mStatus is " + this.mStatus, new Object[0]);
        }
        RenderStatus renderStatus = this.mStatus;
        RenderStatus renderStatus2 = RenderStatus.PLAY;
        if (renderStatus == renderStatus2) {
            return;
        }
        setStatus(renderStatus2);
        if (this.mrunPlayback == null) {
            this.mrunPlayback = new runPlayback(this);
        }
        if (this.mThreadPlayback == null) {
            Thread thread = new Thread(this.mrunPlayback, "vomeAudio Playback");
            this.mThreadPlayback = thread;
            thread.setPriority(9);
            this.mThreadPlayback.start();
        }
        if (voLog.enablePrintLog()) {
            voLog.v(TAG, "run+  mStatus is " + this.mStatus, new Object[0]);
        }
        if (ISAMAZONFAMILY || voBluetoothManager.isBluetoothConnected() || this.mEnableDSPClock) {
            if (voBluetoothManager.isBluetoothConnected()) {
                this.m_bluetoothEnabled = true;
            } else {
                this.m_bluetoothEnabled = false;
            }
            if (this.bDSPSetOnce || ISFORCEDISABLEDSP) {
                return;
            }
            this.mPlayer.onSetAudioParam(89, Long.valueOf(this.mNativeContext));
            this.bDSPSetOnce = true;
        }
    }

    public void pause() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "pause-  mStatus is " + this.mStatus, new Object[0]);
        }
        setStatus(RenderStatus.PAUSE);
        while (this.mbWrite) {
            try {
                Thread.sleep(2L);
                if (voLog.enablePrintLog()) {
                    voLog.w(TAG, "Audio Pause wait for write completed.", new Object[0]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                try {
                    AudioTrack audioTrack = this.mAudioTrack;
                    if (audioTrack != null && audioTrack.getPlayState() == 3) {
                        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
                        if (voaudiodspclock != null) {
                            voaudiodspclock.resetWhenPause();
                        }
                        this.mAudioTrack.pause();
                    }
                    this.mLock.unlock();
                } catch (Throwable th) {
                    this.mLock.unlock();
                    throw th;
                }
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        this.mSendRenderStart = true;
        this.mNotifyAudioFailed = false;
        if (voLog.enablePrintLog()) {
            voLog.v(TAG, "pause+  mStatus is " + this.mStatus, new Object[0]);
        }
    }

    public void resetAudioDSPClock() {
        if (this.mResetAudioDSPClockCount == 0) {
            voLog.d(TAG, "resetAudioDSPClock", new Object[0]);
            voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
            if (voaudiodspclock != null) {
                voaudiodspclock.resetWhenPause();
            }
            this.mLock.lock();
            try {
                try {
                    AudioTrack audioTrack = this.mAudioTrack;
                    if (audioTrack != null) {
                        audioTrack.pause();
                    }
                } catch (Exception e) {
                    if (voLog.enablePrintLog()) {
                        voLog.e(TAG, "AudioTracker pause exception , %s", e.getMessage());
                    }
                }
                this.handler.postDelayed(new Runnable() { // from class: com.visualon.OSMPRender.voAudioRender.2
                    @Override // java.lang.Runnable
                    public void run() {
                        voAudioRender.this.mResetAudioDSPClockCount = 0;
                    }
                }, 2500L);
            } finally {
                this.mLock.unlock();
            }
        }
        this.mResetAudioDSPClockCount++;
    }

    public void resetWhenPause() {
        voLog.i(TAG, "resetWhenPause", new Object[0]);
        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
        if (voaudiodspclock != null) {
            voaudiodspclock.resetWhenPause();
        }
    }

    public void startBuffering() {
        voLog.i(TAG, "startBuffering", new Object[0]);
        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
        if (voaudiodspclock != null) {
            voaudiodspclock.startBuffering();
        }
    }

    public void stopBuffering() {
        voLog.i(TAG, "stopBuffering", new Object[0]);
        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
        if (voaudiodspclock != null) {
            voaudiodspclock.stopBuffering();
        }
    }

    public void stop() {
        ReentrantLock reentrantLock;
        ReentrantLock reentrantLock2;
        if (voLog.enablePrintLog()) {
            voLog.v(TAG, "stop-  mStatus is " + this.mStatus, new Object[0]);
        }
        try {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                try {
                    AudioTrack audioTrack = this.mAudioTrack;
                    if (audioTrack != null) {
                        audioTrack.stop();
                        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
                        if (voaudiodspclock != null) {
                            voaudiodspclock.flush();
                        }
                    }
                    reentrantLock2 = this.mLock;
                } catch (Exception e2) {
                    if (voLog.enablePrintLog()) {
                        voLog.e(TAG, "AudioTracker stop exception , %s", e2.getMessage());
                    }
                    reentrantLock2 = this.mLock;
                }
                reentrantLock2.unlock();
            }
            setStatus(RenderStatus.STOP);
            if (voLog.enablePrintLog()) {
                voLog.v(TAG, "stop  call audio track stop", new Object[0]);
            }
            while (this.mThreadPlayback != null) {
                try {
                    Thread.sleep(2L);
                    if (voLog.enablePrintLog()) {
                        voLog.v(TAG, "Stop, waiting for audio thread quit. sleep 2ms status is " + this.mStatus, new Object[0]);
                    }
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
            this.mSendRenderStart = true;
            this.mNotifyAudioFailed = false;
            if (voLog.enablePrintLog()) {
                voLog.v(TAG, "stop+  mStatus is " + this.mStatus, new Object[0]);
            }
            if (this.bDSPSetOnce) {
                this.mPlayer.onSetAudioParam(89, 0L);
                try {
                    if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                        try {
                            try {
                                voAudioDSPClock voaudiodspclock2 = this.mAudioDSPClock;
                                if (voaudiodspclock2 != null) {
                                    voaudiodspclock2.flush();
                                    this.mAudioDSPClock.stopAudioDSPClockTimer();
                                    this.mAudioDSPClock = null;
                                }
                                reentrantLock = this.mLock;
                            } finally {
                            }
                        } catch (Exception e4) {
                            if (voLog.enablePrintLog()) {
                                voLog.e(TAG, "AudioTracker stop exception , %s", e4.getMessage());
                            }
                            reentrantLock = this.mLock;
                        }
                        reentrantLock.unlock();
                    }
                } catch (InterruptedException e5) {
                    e5.printStackTrace();
                }
            }
            this.bDSPSetOnce = false;
        } finally {
        }
    }

    public void flush() {
        if (voLog.enablePrintLog()) {
            voLog.v(TAG, "flush", new Object[0]);
        }
        try {
            if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                try {
                    AudioTrack audioTrack = this.mAudioTrack;
                    if (audioTrack != null) {
                        audioTrack.flush();
                        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
                        if (voaudiodspclock != null) {
                            voaudiodspclock.flush();
                        }
                    }
                    this.mLock.unlock();
                } catch (Throwable th) {
                    this.mLock.unlock();
                    throw th;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mSendRenderStart = true;
        this.mNotifyAudioFailed = false;
    }

    private void setAC3Value(int i, int i2) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "setAC3Value %d %d", Integer.valueOf(i), Integer.valueOf(i2));
        }
        this.ENCODING_AC3 = i2;
        this.ENCODING_EAC3 = i;
    }

    private boolean isAC3(int i) {
        return this.ENCODING_AC3 == i || this.ENCODING_EAC3 == i;
    }

    private void optimizeHdmiDisconnectPoint(long j) {
        long j2 = ((8 * j) / ((long) this.mSampleRate)) * ((long) this.mChannels) * ((long) this.mSampleBit);
        if ((System.nanoTime() - this.hdmiDisconnectStartTime) / 1000000 < 1500) {
            long jNanoTime = System.nanoTime();
            writeData(this.mByteBuffer, (int) j);
            long jNanoTime2 = (System.nanoTime() - jNanoTime) / 1000000;
            if (jNanoTime2 < j2 / 3) {
                try {
                    Thread.sleep(j2 - jNanoTime2);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        writeData(this.mByteBuffer, (int) j);
        this.m_bHdmiDisconnect = false;
    }

    public void setBluetoothStatus(boolean z) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "BlueToothStatus  : %d ", Boolean.valueOf(z));
        }
        this.m_bluetoothEnabled = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playback() {
        long jGetAudioData;
        voOSAudioFormat voosaudioformatOnGetAudioFormat;
        ReentrantLock reentrantLock;
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "playback started!", new Object[0]);
        }
        this.mThreadPlayback.setPriority(9);
        while (true) {
            RenderStatus renderStatus = this.mStatus;
            RenderStatus renderStatus2 = RenderStatus.PLAY;
            if (renderStatus == renderStatus2 || renderStatus == RenderStatus.PAUSE) {
                if (renderStatus == renderStatus2) {
                    if (System.currentTimeMillis() - this.mTransientTime >= CoroutineLiveDataKt.DEFAULT_TIMEOUT) {
                        this.mTriggerReason = UNSTABLE_REASON.NONE;
                    } else if (System.currentTimeMillis() - this.mRecalcTime > 900) {
                        RecalculateLatencyTime();
                    }
                    this.mbWrite = true;
                    ByteBuffer byteBuffer = this.mByteBuffer;
                    if (byteBuffer == null) {
                        jGetAudioData = this.mPlayer.GetAudioData(null);
                    } else {
                        jGetAudioData = this.mPlayer.GetAudioData(byteBuffer.array());
                    }
                    if ((jGetAudioData == 0 || this.mReconfigAudioTrack) && (voosaudioformatOnGetAudioFormat = this.mPlayer.onGetAudioFormat()) != null && (this.mReconfigAudioTrack || voosaudioformatOnGetAudioFormat.SampleRate() != this.mSampleRate || voosaudioformatOnGetAudioFormat.Channels() != this.mChannels || this.mPlayer.onGetFormatID() != this.mRenderFormat || voosaudioformatOnGetAudioFormat.SampleBits() != this.mSampleBit)) {
                        openTrack(voosaudioformatOnGetAudioFormat.SampleRate(), voosaudioformatOnGetAudioFormat.Channels(), this.mPlayer.onGetFormatID(), voosaudioformatOnGetAudioFormat.SampleBits());
                        if (voLog.enablePrintLog()) {
                            String str = TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("openTrack finished. mAudioTrack : ");
                            sb.append(this.mAudioTrack);
                            sb.append("state : ");
                            AudioTrack audioTrack = this.mAudioTrack;
                            sb.append(audioTrack != null ? audioTrack.getState() : 0);
                            voLog.i(str, sb.toString(), new Object[0]);
                        }
                        AudioTrack audioTrack2 = this.mAudioTrack;
                        if (audioTrack2 == null || audioTrack2.getState() == 0) {
                            try {
                                if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                                    try {
                                        this.mAudioTrack = null;
                                        this.mLock.unlock();
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!this.mNotifyAudioFailed) {
                                int i = this.mFormat;
                                if (i != 2 && i != 3 && i != 4) {
                                    this.mPlayer.onAudioRenderEvent(-2147483632, 0, 0, null);
                                } else {
                                    this.mPlayer.onAudioRenderEvent(voOSType.VOOSMP_CB_Audio_Render_Failed, 0, 0, null);
                                }
                                this.mNotifyAudioFailed = true;
                            }
                            try {
                                Thread.sleep(5L);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            if (voLog.enablePrintLog()) {
                                voLog.e(TAG, "VOOSMP_CB_Audio_Render_Failed", new Object[0]);
                            }
                        }
                        if (nativeSetAudioTrack(this.mNativeContext, this.mAudioTrack)) {
                            voLog.i(TAG, "nativeSetAudioTrack returns true", new Object[0]);
                            voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
                            if (voaudiodspclock != null) {
                                voaudiodspclock.startAudioDSPClockTimer();
                            }
                        } else {
                            voLog.i(TAG, "nativeSetAudioTrack returns false", new Object[0]);
                            voAudioDSPClock voaudiodspclock2 = this.mAudioDSPClock;
                            if (voaudiodspclock2 != null) {
                                voaudiodspclock2.stopAudioDSPClockTimer();
                            }
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "DSP_CLOCK VOOSMP_PID_AUDIO_DSP_CLOCK = NULL", new Object[0]);
                            }
                        }
                        this.mReconfigAudioTrack = false;
                        if (voLog.enablePrintLog()) {
                            voLog.i(TAG, "Ready to write.", new Object[0]);
                        }
                    }
                    if (jGetAudioData > 0) {
                        if (this.m_bHdmiDisconnect) {
                            optimizeHdmiDisconnectPoint(jGetAudioData);
                            voAudioDSPClock voaudiodspclock3 = this.mAudioDSPClock;
                            if (voaudiodspclock3 != null) {
                                voaudiodspclock3.stopAudioDSPClockTimer();
                            }
                        } else {
                            writeData(this.mByteBuffer, (int) jGetAudioData);
                        }
                    }
                    this.mbWrite = false;
                }
                if (this.mStatus == RenderStatus.PAUSE) {
                    try {
                        synchronized (this) {
                            wait();
                        }
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                }
            } else {
                this.mThreadPlayback = null;
                voLog.i(TAG, "playback thread can stop now", new Object[0]);
                try {
                    if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                        try {
                            try {
                                AudioTrack audioTrack3 = this.mAudioTrack;
                                if (audioTrack3 != null && audioTrack3.getPlayState() != 1) {
                                    this.mAudioTrack.stop();
                                    voAudioDSPClock voaudiodspclock4 = this.mAudioDSPClock;
                                    if (voaudiodspclock4 != null) {
                                        voaudiodspclock4.flush();
                                    }
                                }
                                closeTrack();
                                reentrantLock = this.mLock;
                            } finally {
                                this.mLock.unlock();
                            }
                        } catch (Exception e4) {
                            if (voLog.enablePrintLog()) {
                                voLog.e(TAG, "AudioTracker stop or close exception , %s", e4.getMessage());
                            }
                            reentrantLock = this.mLock;
                        }
                        reentrantLock.unlock();
                    }
                } catch (InterruptedException e5) {
                    e5.printStackTrace();
                }
                if (voLog.enablePrintLog()) {
                    voLog.v(TAG, "playbackaudio stopped!", new Object[0]);
                    return;
                }
                return;
            }
        }
    }

    private long writeData(ByteBuffer byteBuffer, int i) {
        int iWriteTunneledData;
        AudioTrack audioTrack = this.mAudioTrack;
        if (audioTrack == null || i <= 0) {
            return 0L;
        }
        if (audioTrack.getPlayState() != 3) {
            voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
            if (voaudiodspclock != null) {
                voaudiodspclock.playAudioTrack();
            } else {
                this.mAudioTrack.play();
            }
            if (this.mSendRenderStart) {
                this.mPlayer.onAudioRenderEvent(33, 0, 0, null);
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "VOOSMP_CB_AudioRenderStart event sent.", new Object[0]);
                }
                this.mSendRenderStart = false;
            }
        }
        if (this.mAudioSessionID == 0) {
            iWriteTunneledData = this.mAudioTrack.write(byteBuffer.array(), 0, i);
        } else {
            iWriteTunneledData = WriteTunneledData(byteBuffer, i);
        }
        if (!IS_TIVO_DEVICE || iWriteTunneledData >= 0) {
            return 0L;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - this.mLastAudioWriteFailedTime <= 200) {
            return 0L;
        }
        this.mLastAudioWriteFailedTime = jUptimeMillis;
        int i2 = this.mAudioWriteErrorCnt;
        if (i2 < 2) {
            this.mAudioWriteErrorCnt = i2 + 1;
            iWriteTunneledData = 0;
        } else {
            this.mAudioWriteErrorCnt = 0;
        }
        this.mPlayer.onAudioRenderEvent(voOSType.VOOSMP_CB_Auido_Write_Error, iWriteTunneledData, 0, null);
        return 0L;
    }

    private int WriteTunneledData(ByteBuffer byteBuffer, int i) {
        onAudioRenderListener onaudiorenderlistener = this.mPlayer;
        if (onaudiorenderlistener == null) {
            return 0;
        }
        long jIntValue = ((Integer) onaudiorenderlistener.onGetAudioParam(65605L)).intValue();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.putInt(1431633921);
        byteBufferAllocate.putInt(i);
        byteBufferAllocate.putLong(jIntValue * 1000000);
        byteBufferAllocate.position(0);
        byteBuffer.clear();
        byteBuffer.position(0);
        this.mAudioTrack.write(byteBufferAllocate, 16, 0);
        return this.mAudioTrack.write(byteBuffer, i, 0);
    }

    public void notifyReconfigAudioTrack() {
        notifyReconfigAudioTrack(UNSTABLE_REASON.NONE);
    }

    public void notifyReconfigAudioTrack(UNSTABLE_REASON unstable_reason) {
        this.mTriggerReason = unstable_reason;
        if (unstable_reason == UNSTABLE_REASON.NONE) {
            this.mTransientTime = System.currentTimeMillis();
            this.mRecalcTime = System.currentTimeMillis();
        }
        if (UNSTABLE_REASON.SEEK == unstable_reason) {
            this.mReconfigAudioTrack = true;
            this.mCompensateLock.lock();
            try {
                this.mbSeeking = true;
            } finally {
                this.mCompensateLock.unlock();
            }
        }
        if (UNSTABLE_REASON.CONNECTION_STATE_CHANGE == unstable_reason || this.mAudioDSPClock == null) {
            this.mReconfigAudioTrack = true;
        }
    }

    public void setSeekComplete() {
        this.mCompensateLock.lock();
        try {
            this.mbSeeking = false;
            voLog.e(TAG, "setSeekComplete mbSeeking = false", new Object[0]);
        } finally {
            this.mCompensateLock.unlock();
        }
    }

    private int openTrack(int i, int i2, int i3, int i4) {
        ReentrantLock reentrantLock;
        int i5;
        int channelConfig;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        this.mCompensateLock.lock();
        try {
            this.OPEN_TRACK = true;
            this.mCompensateLock.unlock();
            try {
                try {
                    if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                        try {
                            closeTrack();
                            int iNativeGetAudioFormat = nativeGetAudioFormat(this.mNativeContext, i3, i4);
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "XXX AudioFormat: %d", Integer.valueOf(iNativeGetAudioFormat));
                            }
                            i5 = iNativeGetAudioFormat == -1 ? 2 : iNativeGetAudioFormat;
                            channelConfig = getChannelConfig(i2);
                        } catch (Exception e) {
                            if (voLog.enablePrintLog()) {
                                voLog.e(TAG, "AudioTrack stop or close exception , %s", e.getMessage());
                            }
                            reentrantLock = this.mLock;
                        }
                        if (channelConfig == 0) {
                            throw new IllegalArgumentException("Unsupported channel count: " + i2);
                        }
                        int minBufferSize = AudioTrack.getMinBufferSize(i, channelConfig, i5);
                        if (minBufferSize != -2 && minBufferSize != -1) {
                            int i11 = minBufferSize * 2;
                            int i12 = i11 < 2048 ? 2048 : i11;
                            if (this.mAudioSessionID != 0) {
                                this.mAudioTrack = new AudioTrack(new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build(), new AudioFormat.Builder().setChannelMask(channelConfig).setEncoding(i5).setSampleRate(i).build(), i12, 1, this.mAudioSessionID);
                                voLog.i(TAG, "AudioTrack sampleRate : " + i + "   channelCount : " + i2, new Object[0]);
                                String str = TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("AudioTrack state : ");
                                sb.append(this.mAudioTrack.getState());
                                voLog.i(str, sb.toString(), new Object[0]);
                            }
                            if (this.mAudioTrack == null) {
                                i6 = i12;
                                i7 = channelConfig;
                                i8 = i5;
                                this.mAudioTrack = new AudioTrack(3, i, channelConfig, i5, i6, 1);
                            } else {
                                i6 = i12;
                                i7 = channelConfig;
                                i8 = i5;
                            }
                            if ((ISAMAZONFAMILY || this.m_bluetoothEnabled || this.mEnableDSPClock) && this.mAudioDSPClock == null && !ISFORCEDISABLEDSP) {
                                voAudioDSPClock voaudiodspclock = new voAudioDSPClock();
                                this.mAudioDSPClock = voaudiodspclock;
                                voaudiodspclock.setOnPlaybackHeadPosErrorListener(this.onPlaybackHeadPosErrorListener);
                                if (!this.bDSPSetOnce && this.mPlayer.onSetAudioParam(89, Long.valueOf(this.mNativeContext)) == 0) {
                                    this.bDSPSetOnce = true;
                                }
                                if (voLog.enablePrintLog()) {
                                    voLog.i(TAG, "Create DSP_CLOCK  voAudioDSPClock", new Object[0]);
                                }
                            }
                            voAudioDSPClock voaudiodspclock2 = this.mAudioDSPClock;
                            if (voaudiodspclock2 != null) {
                                i10 = i8;
                                i9 = i6;
                                voaudiodspclock2.setAudioTrack(this.mAudioTrack).reconfig(i, isAC3(i10), i9, i2);
                            } else {
                                i9 = i6;
                                i10 = i8;
                            }
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "Create AudioTrack, SampleRate " + i + " ,ChannelCount " + i2 + " ,nMinBufSize = " + i9 + " ,audio per sample is = " + i10 + " , nChannelConfig = " + i7, new Object[0]);
                            }
                            voOSAudioRenderFormat voosaudiorenderformat = new voOSAudioRenderFormat(i, i2, i4, i9);
                            onAudioRenderListener onaudiorenderlistener = this.mPlayer;
                            if (onaudiorenderlistener != null) {
                                onaudiorenderlistener.onSetAudioParam(92, voosaudiorenderformat);
                                RecalculateLatencyTime();
                                this.mPlayer.onSetAudioParam(28, 10);
                                if (voLog.enablePrintLog()) {
                                    voLog.i(TAG, "SetParam VOOSMP_PID_AUDIO_STEP_BUFFTIME is 10", new Object[0]);
                                }
                            }
                            float f = this.mLeftVolume;
                            if (f >= 0.0f) {
                                arsetVolume(f, this.mRightVolume);
                            }
                            this.mSampleRate = i;
                            this.mChannels = i2;
                            this.mRenderFormat = i3;
                            this.mSampleBit = i4;
                            this.mFormat = i10;
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "Open AudioTrack before allocate buffer, mSampleRate is %d, mChannels is %d", Integer.valueOf(this.mSampleRate), Integer.valueOf(this.mChannels));
                            }
                            try {
                                this.mByteBuffer = ByteBuffer.allocate(this.mSampleRate * this.mChannels * 3);
                            } catch (Exception e2) {
                                if (voLog.enablePrintLog()) {
                                    voLog.e(TAG, "ByteBuffer.allocate error, message is %s", e2.getMessage());
                                }
                                this.mByteBuffer = null;
                            }
                            if (this.mByteBuffer == null) {
                                if (voLog.enablePrintLog()) {
                                    voLog.e(TAG, "Failed to allocate buffer", new Object[0]);
                                }
                                return -1;
                            }
                            if (voLog.enablePrintLog()) {
                                voLog.i(TAG, "Open AudioTrack before allocate end", new Object[0]);
                            }
                            this.mbSeeking = false;
                            voLog.e(TAG, "mbSeeking = false", new Object[0]);
                            reentrantLock = this.mLock;
                            reentrantLock.unlock();
                        }
                        return -1;
                    }
                } finally {
                    this.mLock.unlock();
                }
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            this.mCompensateLock.lock();
            try {
                this.OPEN_TRACK = false;
                return 0;
            } finally {
            }
        } finally {
        }
    }

    private void closeTrack() {
        if (this.mAudioTrack != null) {
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "closeTrack", new Object[0]);
            }
            nativeSetAudioTrack(this.mNativeContext, null);
            this.mAudioTrack.release();
            voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
            if (voaudiodspclock != null) {
                voaudiodspclock.flush();
                this.mAudioDSPClock.stopAudioDSPClockTimer();
            }
            this.mAudioTrack = null;
            this.mSampleRate = 0;
            this.mChannels = 0;
            this.mByteBuffer = null;
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void arsetVolume(float f, float f2) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "arsetVolume left%.3f; right:%.3f .", Float.valueOf(f), Float.valueOf(f2));
        }
        try {
            if (this.mLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                try {
                    AudioTrack audioTrack = this.mAudioTrack;
                    if (audioTrack != null) {
                        audioTrack.setStereoVolume(f, f2);
                    }
                    this.mLeftVolume = f;
                    this.mRightVolume = f2;
                    this.mLock.unlock();
                } catch (Throwable th) {
                    this.mLock.unlock();
                    throw th;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void setStatus(RenderStatus renderStatus) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "setStatus - , status is " + renderStatus, new Object[0]);
        }
        this.mStatus = renderStatus;
        if (renderStatus == RenderStatus.PLAY || renderStatus == RenderStatus.STOP) {
            notify();
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "setStatus + , mStatus is " + this.mStatus, new Object[0]);
        }
    }

    public void setEnableDSPClock(boolean z) {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "EnableDSPClock : " + z, new Object[0]);
        }
        this.mEnableDSPClock = z;
    }

    public void init() {
        nativeInit();
        this.mPlayer.onSetAudioParam(115, Integer.valueOf(getMaxSampleRate()));
        this.bDSPSetOnce = false;
        try {
            this.mGetLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
        } catch (NoSuchMethodException unused) {
            if (voLog.enablePrintLog()) {
                voLog.e(TAG, "Cannot get audio latency from audio system !", new Object[0]);
            }
        }
    }

    public void unInit() {
        voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
        if (voaudiodspclock != null) {
            voaudiodspclock.stopAudioDSPClockTimer();
        }
        this.mrunPlayback = null;
        this.mPlayer = null;
        nativeUninit();
    }

    public void RecalculateLatencyTime() {
        int iIntValue;
        this.mRecalcTime = System.currentTimeMillis();
        if (this.mPlayer != null) {
            if (!Build.VERSION.RELEASE.startsWith("6.0")) {
                iIntValue = getAudioTrackLatency();
            } else {
                iIntValue = this.mPlayer.onGetAudioParam(91L) != null ? ((Integer) this.mPlayer.onGetAudioParam(91L)).intValue() : 0;
            }
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "renderBufferTime " + iIntValue, new Object[0]);
            }
            boolean z = ISAMAZONFAMILY;
            if (z) {
                if (voLog.enablePrintLog()) {
                    voLog.i(TAG, "It's Amazon family", new Object[0]);
                }
                voAudioDSPClock voaudiodspclock = this.mAudioDSPClock;
                if (voaudiodspclock != null) {
                    iIntValue = ((int) voaudiodspclock.getLatency(iIntValue)) / 1000;
                    if (new String(Build.MODEL).equals("AFTM")) {
                        iIntValue = 150;
                    }
                }
            }
            if (z && !isAC3(this.mFormat)) {
                iIntValue = 0;
            }
            if (voLog.enablePrintLog()) {
                voLog.i(TAG, "VOOSMP_PID_AUDIO_RENDER_LATENCY VOOSMP_PID_AUDIO_REND_BUFFER_TIME is %d", Integer.valueOf(iIntValue));
            }
            this.mPlayer.onSetAudioParam(18, Integer.valueOf(iIntValue));
        }
    }
}
