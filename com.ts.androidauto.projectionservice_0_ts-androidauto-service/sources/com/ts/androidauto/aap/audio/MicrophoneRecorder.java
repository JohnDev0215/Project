package com.ts.androidauto.aap.audio;

import android.media.AudioRecord;
import android.os.SystemClock;
import com.google.android.projection.protocol.AudioSource;
import com.google.android.projection.protocol.GalReceiver;
import com.ts.androidauto.aap.audiofocus.StateManager;
import com.ts.androidauto.aap.sink.GalIntegration;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.common.ResourceConst;
import com.ts.androidauto.fsdk.interfaces.audio.IAudioControllerAdapter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class MicrophoneRecorder implements AudioSource.AudioSourceListener {
    private static final int BIT_16 = 16;
    private static final int BIT_8 = 8;
    private static final int BUFFER_SIZE = 2048;
    private static final int CAPTURE_BUFFER_SIZE = 4096;
    private static final int DELAY_RECORD_TIME_MS = 150;
    private static final int NUMBER_8 = 8;
    private static final int NUMBER_OF_BITS = 2;
    private static final int NUMBER_OF_CHANNELS = 16;
    private static final int REQUEST_WAIT_TIME_MS = 500;
    private static final int SAMPLING_RATE = 16000;
    private static final int WAIT_MIC_TIME = 50;
    private AudioSource mGalAudioSource;
    private static final String TAG = "AAP" + MicrophoneRecorder.class.getSimpleName();
    private static MicrophoneRecorder mMicrophoneRecorder = null;
    private AudioSourceThread mAudioThread = null;
    private int mSessionId = 0;
    private int mMaxUnacked = 0;
    private Semaphore mUnackedFrames = null;
    private IAudioControllerAdapter mAudioControllerAdapter = null;
    private AapAudioManager mAapAudioManager = null;
    private GalIntegration mGal = null;
    private Semaphore mWaitRecordRelease = new Semaphore(0);
    private boolean mRecorderStreamSwitcher = false;

    public static synchronized MicrophoneRecorder getInstance() {
        if (mMicrophoneRecorder == null) {
            mMicrophoneRecorder = new MicrophoneRecorder();
        }
        return mMicrophoneRecorder;
    }

    private MicrophoneRecorder() {
        GalIntegration galIntegration = this.mGal;
        if (galIntegration != null) {
            setGalAudioSource(galIntegration.audioSource);
        }
    }

    public void init(GalIntegration gal, IAudioControllerAdapter audioControllerAdapter) {
        LogUtil.info(TAG, "Init aap audio micphone recorder.");
        this.mGal = gal;
        this.mAudioControllerAdapter = audioControllerAdapter;
        this.mAudioThread = new AudioSourceThread();
        this.mAapAudioManager = AapAudioManager.getInstance();
    }

    public void setGalAudioSource(AudioSource galAudioSource) {
        LogUtil.info(TAG, "setGalAudioSource is invoke: " + this.mGalAudioSource);
        this.mGalAudioSource = galAudioSource;
    }

    public int getSamplingRate() {
        return SAMPLING_RATE;
    }

    public int getNumberOfChannels() {
        return 1;
    }

    public void setRecorderStreamSwitcher(boolean switcher) {
        this.mRecorderStreamSwitcher = switcher;
    }

    public int getNumberOfBits() {
        return 16;
    }

    class AudioSourceThread extends Thread {
        private static final long ACK_TIMEOUT = 500;
        private static final long JOIN_TIMEOUT = 1000;
        private final Object mPauseLock = new Object();
        private final Object mRecordReleseLock = new Object();
        private volatile boolean mPaused = true;
        private volatile boolean mCheckResult = false;
        private volatile boolean mFinished = false;
        private AudioRecord record = null;

        AudioSourceThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int framesSent = 0;
            LogUtil.info(MicrophoneRecorder.TAG, "recording BUFFER_SIZE = 2048 mFinished:" + this.mFinished + " mPaused:" + this.mPaused + " record:" + this.record);
            while (!this.mFinished) {
                if (!this.mPaused && this.record == null) {
                    LogUtil.info(MicrophoneRecorder.TAG, "start recording");
                    this.record = new AudioRecord(MicrophoneRecorder.this.mAudioControllerAdapter.getMicSourceId(), MicrophoneRecorder.SAMPLING_RATE, 16, 2, 16384);
                    LogUtil.info(MicrophoneRecorder.TAG, "start recording sleep start");
                    SystemClock.sleep(150L);
                    LogUtil.info(MicrophoneRecorder.TAG, "start recording sleep end");
                    AudioRecord audioRecord = this.record;
                    if (audioRecord == null || audioRecord.getState() != 1) {
                        LogUtil.info(MicrophoneRecorder.TAG, "Initialized record error!");
                        this.mCheckResult = false;
                    } else {
                        this.record.startRecording();
                        this.mCheckResult = true;
                    }
                    framesSent = 0;
                }
                int framesSent2 = audioRecordLoop(this.record, framesSent);
                synchronized (this.mPauseLock) {
                    if (!this.mPaused && !this.mCheckResult && !this.mFinished) {
                        LogUtil.info(MicrophoneRecorder.TAG, "MicManager check fail, pause record mCheckResult: " + this.mCheckResult);
                        this.mPaused = true;
                    }
                }
                synchronized (this.mRecordReleseLock) {
                    if (this.mPaused && this.record != null) {
                        LogUtil.info(MicrophoneRecorder.TAG, "stopping, sent frames " + framesSent2);
                        if (this.record.getState() == 1) {
                            this.record.stop();
                        }
                        this.record.release();
                        this.record = null;
                        MicrophoneRecorder.this.mWaitRecordRelease.release();
                    }
                }
                synchronized (this.mPauseLock) {
                    while (this.mPaused && !this.mFinished) {
                        try {
                            this.mPauseLock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                framesSent = framesSent2;
            }
            LogUtil.debug(MicrophoneRecorder.TAG, "Reached end of AudioThread");
        }

        private int audioRecordLoop(AudioRecord record, int framesSent) {
            while (!this.mPaused && record != null && this.mCheckResult) {
                byte[] buf = new byte[2048];
                int toRead = 2048;
                while (true) {
                    if (toRead <= 0 || isInterrupted() || this.mPaused) {
                        break;
                    }
                    int len = record.read(buf, 2048 - toRead, toRead);
                    if (len < 0) {
                        LogUtil.info(MicrophoneRecorder.TAG, "Error reading from AudioRecord " + len + " while trying to read " + (2048 - toRead));
                        break;
                    }
                    toRead -= len;
                }
                if (this.mFinished || this.mPaused) {
                    break;
                }
                if (MicrophoneRecorder.this.mAapAudioManager != null && MicrophoneRecorder.this.mRecorderStreamSwitcher) {
                    AapAudioManager aapAudioManager = MicrophoneRecorder.this.mAapAudioManager;
                    AapAudioManager unused = MicrophoneRecorder.this.mAapAudioManager;
                    aapAudioManager.saveDataToFile(AapAudioManager.RECORDER_PATH, buf);
                }
                MicrophoneRecorder.this.mGalAudioSource.send(0L, buf);
                framesSent++;
                if (this.mFinished) {
                    break;
                }
                try {
                    if (!MicrophoneRecorder.this.mUnackedFrames.tryAcquire(ACK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                        LogUtil.warning(MicrophoneRecorder.TAG, "mic ACK timeout, move on");
                    }
                } catch (InterruptedException e) {
                    LogUtil.error(MicrophoneRecorder.TAG, "Dropping audio frame.");
                }
            }
            return framesSent;
        }

        public void onPause() {
            LogUtil.debug(MicrophoneRecorder.TAG, "AudioSourceThread onPause is: " + this.mPaused + " mCheckResult:" + this.mCheckResult);
            if (!this.mCheckResult && this.mPaused) {
                LogUtil.debug(MicrophoneRecorder.TAG, "AudioSourceThread is already pause return ");
                return;
            }
            synchronized (this.mPauseLock) {
                this.mPaused = true;
                this.mCheckResult = false;
                try {
                    if (MicrophoneRecorder.this.mWaitRecordRelease.tryAcquire(ACK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                        LogUtil.debug(MicrophoneRecorder.TAG, "AudioSourceThread record is released!");
                    } else {
                        synchronized (this.mRecordReleseLock) {
                            LogUtil.debug(MicrophoneRecorder.TAG, "mWaitRecordRelease wait timeout: " + this.record);
                            if (this.record != null) {
                                if (this.record.getState() == 1) {
                                    this.record.stop();
                                }
                                this.record.release();
                                this.record = null;
                            }
                        }
                    }
                } catch (InterruptedException exception) {
                    LogUtil.error(MicrophoneRecorder.TAG, "mic record exception:" + exception.toString());
                }
            }
        }

        public void onResume() {
            LogUtil.debug(MicrophoneRecorder.TAG, "AudioSourceThread onResume is invoke!");
            synchronized (this.mPauseLock) {
                this.mPaused = false;
                this.mPauseLock.notifyAll();
            }
        }

        public void shutdown() {
            LogUtil.debug(MicrophoneRecorder.TAG, "AudioSourceThread shutdown is invoke!");
            this.mFinished = true;
            this.mPaused = true;
            this.mCheckResult = false;
            interrupt();
            synchronized (this.mPauseLock) {
                this.mPauseLock.notifyAll();
            }
            try {
                join(JOIN_TIMEOUT);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override // com.google.android.projection.protocol.AudioSource.AudioSourceListener
    public int microphoneRequestCallback(boolean open, boolean ancEnabled, boolean ecEnabled, int maxUnacked) {
        IAudioControllerAdapter iAudioControllerAdapter;
        LogUtil.debug(TAG, "microphoneRequestCallback open:" + open + " maxUnacked:" + maxUnacked + " mAudioThread:" + this.mAudioThread);
        this.mMaxUnacked = maxUnacked;
        if (open) {
            boolean hasMicFocus = StateManager.getInstance().getMicFocusState();
            LogUtil.debug(TAG, "MicPhone hasMicFocus: " + hasMicFocus);
            if (hasMicFocus) {
                startRecord();
            } else {
                LogUtil.debug(TAG, "MicPhone request mic focus.");
                int result = -1;
                IAudioControllerAdapter iAudioControllerAdapter2 = this.mAudioControllerAdapter;
                if (iAudioControllerAdapter2 != null) {
                    result = iAudioControllerAdapter2.requestMicFocus(ResourceConst.MicFocusRequest.MICFOCUS_REQUEST_ANDROIDAUTO_VR);
                    LogUtil.debug(TAG, "requestMicFocus request : " + result);
                }
                if (result == ResourceConst.MicFocusReqResult.MICFOCUS_REQUEST_GRANTED.ordinal()) {
                    StateManager.getInstance().setMicFocusState(true);
                    startRecord();
                } else {
                    LogUtil.debug(TAG, "microphoneRequestCallback can not get mic focus.");
                    StateManager.getInstance().setMicFocusState(false);
                }
            }
        } else {
            AudioSourceThread audioSourceThread = this.mAudioThread;
            if (audioSourceThread != null) {
                audioSourceThread.onPause();
            }
            boolean hasMicFocus2 = StateManager.getInstance().getMicFocusState();
            int vrSessionState = StateManager.getInstance().getVoiceSessionState();
            LogUtil.debug(TAG, "releaseMicFocus hasMicFocus: " + hasMicFocus2 + " vrSessionState:" + vrSessionState);
            if (hasMicFocus2 && vrSessionState == GalReceiver.VOICE_RECOGNITION_STATE_ENDED && (iAudioControllerAdapter = this.mAudioControllerAdapter) != null) {
                int ret = iAudioControllerAdapter.requestMicFocus(ResourceConst.MicFocusRequest.MICFOCUS_RELEASE_ANDROIDAUTO_VR);
                LogUtil.debug(TAG, "releaseMicFocus result: " + ret);
                if (ret == ResourceConst.MicFocusReqResult.MICFOCUS_REQUEST_GRANTED.ordinal()) {
                    StateManager.getInstance().setMicFocusState(false);
                }
            }
        }
        int i = this.mSessionId;
        this.mSessionId = i + 1;
        return i;
    }

    public void startRecord() {
        if (this.mUnackedFrames == null) {
            LogUtil.debug(TAG, "startRecord create Semaphore with " + this.mMaxUnacked);
            this.mUnackedFrames = new Semaphore(this.mMaxUnacked);
        }
        AudioSourceThread audioSourceThread = this.mAudioThread;
        if (audioSourceThread != null && !audioSourceThread.isAlive()) {
            LogUtil.debug(TAG, "startRecord mAudioThread start ");
            this.mAudioThread.start();
        }
        if (this.mAudioThread != null) {
            LogUtil.debug(TAG, "startRecord mAudioThread onResume ");
            this.mAudioThread.onResume();
        }
    }

    public void stop() {
        LogUtil.debug(TAG, "stop is invoke!");
        AudioSourceThread audioSourceThread = this.mAudioThread;
        if (audioSourceThread != null) {
            audioSourceThread.shutdown();
            this.mAudioThread = null;
        }
    }

    @Override // com.google.android.projection.protocol.AudioSource.AudioSourceListener
    public void ackCallback(int sessionId, int ack) {
        this.mUnackedFrames.release(ack);
    }
}
