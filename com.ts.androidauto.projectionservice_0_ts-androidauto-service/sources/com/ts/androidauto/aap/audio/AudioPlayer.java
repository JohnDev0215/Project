package com.ts.androidauto.aap.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.AudioSink;
import com.ts.androidauto.aap.AapController;
import com.ts.androidauto.aap.audiofocus.AapAudioFocusManager;
import com.ts.androidauto.aap.common.AapConfig;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AudioPlayer implements AudioTrack.OnPlaybackPositionUpdateListener {
    private static final int AUDIO_PROFILE_SHIFT_LEFT_3 = 3;
    private static final int AUDIO_UNDERFLOW_NOTIFICATION = 1;
    private static final int AUDIO_UNDERFLOW_WAIT_TIME_MS = 400;
    private static final int CHANNEL_CONFIG_SHIFT_LEFT_3 = 3;
    private static final int DECODED_BUFFER_SIZE = 8192;
    private static final long DEQUEUE_INPUT_BUFFER_TIMEOUT_US = 64000;
    private static final long DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 64000;
    private static final long MAX_STOP_WAIT_MS = 1000;
    private static final int NUMBER_4 = 4;
    private static final int NUM_AUDIO_BUFFERS_TO_START_PLAY_WIRE = 3;
    private static final int NUM_AUDIO_BUFFERS_TO_START_PLAY_WIRELESS = 3;
    private static final int NUM_AUDIO_SKIP_AAC_MEDIA_WIRELESS = 3;
    private static final int NUM_AUDIO_TRACK_WAIT_MEDIA_WIRELESS = 1;
    private static final int NUM_AUDIO_TRACK_WAIT_WIRE = 1;
    private static final int NUM_AUDIO_TRACK_WAIT_WIRELESS = 3;
    private static final int SAMPLES_FRAME_SIZE_1024 = 1024;
    private static final int SAMPLES_FRAME_SIZE_2048 = 2048;
    private static final int SAMPLE_INDEX_MASK = 128;
    private static final int SAMPLE_INDEX_SHIFT_LEFT_7 = 7;
    private static final int SAMPLING_RATE_11025 = 11025;
    private static final int SAMPLING_RATE_12000 = 12000;
    private static final int SAMPLING_RATE_16000 = 16000;
    private static final int SAMPLING_RATE_22050 = 22050;
    private static final int SAMPLING_RATE_24000 = 24000;
    private static final int SAMPLING_RATE_32000 = 32000;
    private static final int SAMPLING_RATE_44100 = 44100;
    private static final int SAMPLING_RATE_48000 = 48000;
    private static final int SAMPLING_RATE_64000 = 64000;
    private static final int SAMPLING_RATE_8000 = 8000;
    private static final int SAMPLING_RATE_88200 = 88200;
    private static final int SAMPLING_RATE_96000 = 96000;
    private static final String TAG = "AAP" + AudioPlayer.class.getSimpleName();
    private static final int WAIT_TIME_MS_1 = 1;
    public static final String WIERLESS_GUIDANCE_NAVI_PATH = "/sdcard/dumpAapAudio/wirelessGuidanceNavi_stream.pcm";
    public static final String WIERLESS_GUIDANCE_VR_PATH = "/sdcard/dumpAapAudio/wirelessGuidanceVr_stream.pcm";
    public static final String WIERLESS_MEDIA_NAVI_PATH = "/sdcard/dumpAapAudio/wirelessMediaNavi_stream.pcm";
    public static final String WIERLESS_MEDIA_PATH = "/sdcard/dumpAapAudio/wirelessMedia_stream.pcm";
    public static final String WIERLESS_MEDIA_VR_PATH = "/sdcard/dumpAapAudio/wirelessMediaVr_stream.pcm";
    public static final String WIER_GUIDANCE_NAVI_PATH = "/sdcard/dumpAapAudio/wireGuidanceNavi_stream.pcm";
    public static final String WIER_GUIDANCE_VR_PATH = "/sdcard/dumpAapAudio/wireGuidanceVr_stream.pcm";
    public static final String WIER_MEDIA_NAVI_PATH = "/sdcard/dumpAapAudio/wireMediaNavi_stream.pcm";
    public static final String WIER_MEDIA_PATH = "/sdcard/dumpAapAudio/wireMedia_stream.pcm";
    public static final String WIER_MEDIA_VR_PATH = "/sdcard/dumpAapAudio/wireMediaVr_stream.pcm";
    private int mAudioFormat;
    private AudioPlayThread mAudioPlayThread;
    private AudioSink mAudioSink;
    private AudioTrack mAudioTrack;
    private int mAudioType;
    private AudioUnderflowHandler mAudioUnderflowHandler;
    private int mChannelConfig;
    private byte[] mCodecConfig;
    private boolean mIsDumpAudio;
    private int mNumAudioByffersToStartPlay;
    private int mSamplingRate;
    private int mStream;
    private int mStreamType;
    private AapConfig.ValidAudioFocusType mValidFocusType;
    private int mReceiveDataCount = 0;
    private int mNumDataReceive = 0;
    private final ConcurrentLinkedQueue<AudioBuffer> mQ = new ConcurrentLinkedQueue<>();
    private boolean mIsNeedWaitStart = false;
    private boolean mIsStopAudioTrack = false;
    private Object mAudioTrackLock = new Object();
    private int mSessionId = 1;
    private boolean mIsOnStart = false;
    private RandomAccessFile mRandomFile = null;
    private FileChannel mFileChannel = null;
    private boolean mAudioPlayerStarted = false;
    private volatile boolean mReleased = false;
    private boolean mIsStopAudio = false;

    static /* synthetic */ int access$1608(AudioPlayer x0) {
        int i = x0.mReceiveDataCount;
        x0.mReceiveDataCount = i + 1;
        return i;
    }

    public AudioPlayer(int stream, int audioType) {
        this.mAudioType = 0;
        this.mStream = 0;
        this.mValidFocusType = AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_INVALID;
        this.mIsDumpAudio = false;
        File audioFile = new File("/sdcard/dumpaudio");
        if (audioFile.exists()) {
            LogUtil.debug(TAG, "Enable dumping audio sink data");
            this.mIsDumpAudio = true;
            File fileDir = new File("/sdcard/dumpAapAudio");
            if (!fileDir.exists() && !fileDir.mkdir()) {
                LogUtil.error(TAG, "init mkdir fail.");
            }
        }
        this.mStream = stream;
        this.mAudioType = audioType;
        if (stream == 100) {
            if (audioType == 200) {
                this.mStreamType = 3;
                this.mSamplingRate = 48000;
                this.mChannelConfig = 12;
                this.mAudioFormat = 2;
                this.mValidFocusType = AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_MEDIA;
            } else if (audioType == 201) {
                this.mStreamType = 5;
                this.mSamplingRate = 48000;
                this.mChannelConfig = 12;
                this.mAudioFormat = 2;
                this.mValidFocusType = AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_NAVI;
            } else if (audioType == 202) {
                this.mStreamType = 5;
                this.mSamplingRate = 48000;
                this.mChannelConfig = 12;
                this.mAudioFormat = 2;
                LogUtil.info(TAG, "audioType " + audioType + " mStreamType" + this.mStreamType + " mSamplingRate" + this.mSamplingRate + " mChannelConfig" + this.mChannelConfig + " mAudioFormat" + this.mAudioFormat);
                this.mValidFocusType = AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_VR;
            }
        } else if (stream == 101) {
            if (audioType == 201) {
                this.mStreamType = 5;
                this.mSamplingRate = 48000;
                this.mChannelConfig = 4;
                this.mAudioFormat = 2;
                this.mValidFocusType = AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_NAVI;
            } else if (audioType == 202) {
                this.mStreamType = 5;
                this.mSamplingRate = 48000;
                this.mChannelConfig = 4;
                this.mAudioFormat = 2;
                this.mValidFocusType = AapConfig.ValidAudioFocusType.VALID_AUDIO_FOCUS_VR;
            }
        }
        this.mAudioUnderflowHandler = new AudioUnderflowHandler(Looper.getMainLooper());
    }

    public synchronized void registerAudioSink(AudioSink audioSink) {
        this.mAudioSink = audioSink;
    }

    public synchronized void setUp(int mediaCodecType, AudioAttributes attributes, Context context) {
        this.mIsOnStart = false;
        this.mIsNeedWaitStart = false;
        this.mNumDataReceive = 0;
        LogUtil.info(TAG, "audio onSetUp " + mediaCodecType + " start");
        if (mediaCodecType == Protos.MediaCodecType.MEDIA_CODEC_AUDIO_PCM.getNumber()) {
            this.mAudioPlayThread = new PcmPlayThread();
            this.mNumAudioByffersToStartPlay = 3;
            LogUtil.info(TAG, "audio onSetUp wired wait buffer:" + this.mNumAudioByffersToStartPlay);
        } else if (mediaCodecType == Protos.MediaCodecType.MEDIA_CODEC_AUDIO_AAC_LC.getNumber()) {
            this.mAudioPlayThread = new AacPlayThread();
            this.mNumAudioByffersToStartPlay = 3;
            LogUtil.info(TAG, "audio onSetUp wireless wait buffer:" + this.mNumAudioByffersToStartPlay);
        } else {
            LogUtil.info(TAG, "Unsupported media codec type: " + mediaCodecType);
        }
        createAudioTrack(attributes, context);
        this.mAudioPlayThread.start();
        this.mReleased = false;
    }

    private void createAudioTrack(AudioAttributes attributes, Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int frameSize = getFrameSizeInSamples();
        int minBufferSize = Math.max(AudioTrack.getMinBufferSize(this.mSamplingRate, this.mChannelConfig, this.mAudioFormat), frameSize * 4 * (this.mChannelConfig == 12 ? 2 : 1));
        LogUtil.info(TAG, "createAudioTrack attributes" + attributes + " mStreamType" + this.mStreamType + " mSamplingRate" + this.mSamplingRate + " mChannelConfig" + this.mChannelConfig + " mAudioFormat" + this.mAudioFormat + " minBufferSize" + minBufferSize);
        try {
            this.mAudioTrack = new AudioTrack.Builder().setAudioAttributes(attributes).setAudioFormat(new AudioFormat.Builder().setEncoding(this.mAudioFormat).setSampleRate(this.mSamplingRate).setChannelMask(this.mChannelConfig).build()).setBufferSizeInBytes(minBufferSize).setTransferMode(1).setSessionId(audioManager.generateAudioSessionId()).setPerformanceMode(1).build();
        } catch (Exception ex) {
            LogUtil.error(TAG, "createAudioTrack error!");
            ex.printStackTrace();
        }
        LogUtil.info(TAG, "AudioTrack minbuffer " + minBufferSize);
    }

    public synchronized void onStart(int sessionId) {
        LogUtil.info(TAG, "audio onStart mStreamType  audioType:" + this.mAudioType + " StreamType:" + this.mStreamType + " sessionId:" + sessionId);
        if (this.mQ.size() != 0) {
            LogUtil.debug(TAG, "onStart clear mQ size " + this.mQ.size());
        }
        if (this.mIsOnStart) {
            LogUtil.info(TAG, "onStart is already play audioType is " + this.mAudioType);
            return;
        }
        this.mReceiveDataCount = 0;
        this.mNumDataReceive = 0;
        this.mIsOnStart = true;
        boolean isPlaying = this.mAudioPlayThread.isPlaying();
        LogUtil.info(TAG, "onStart isPlaying:" + isPlaying);
        if (!isPlaying) {
            this.mIsNeedWaitStart = true;
        } else {
            this.mIsNeedWaitStart = false;
        }
        LogUtil.info(TAG, "audio onStart end mIsNeedWaitStart:" + this.mIsNeedWaitStart);
    }

    public synchronized void onStop(int sessionId) {
        LogUtil.info(TAG, "audio onStop  audioType:" + this.mAudioType + " StreamType:" + this.mStreamType);
        if (this.mAudioPlayThread != null) {
            if (!this.mIsOnStart) {
                LogUtil.info(TAG, "Media is already pause return ");
                return;
            }
            boolean isPlaying = this.mAudioPlayThread.isPlaying();
            LogUtil.info(TAG, "onStop isPlaying: " + isPlaying);
            if (!this.mIsOnStart) {
                LogUtil.info(TAG, "Judge play status second because playlockMedia is already pause audioType is " + this.mAudioType);
                return;
            }
            this.mIsOnStart = false;
            if (isPlaying) {
                this.mIsStopAudio = true;
                if (!this.mReleased && this.mAudioPlayerStarted) {
                    this.mAudioPlayerStarted = false;
                    safeStop(false);
                    LogUtil.info(TAG, "stream stopped type:" + this.mStreamType);
                }
            }
            LogUtil.info(TAG, "audio onStop end ");
        }
    }

    public void stopAudioTrack() {
        if (this.mAudioTrack != null) {
            LogUtil.debug(TAG, "Flush and stop audio track");
            this.mIsStopAudioTrack = true;
            this.mAudioTrack.pause();
            this.mAudioTrack.flush();
            this.mAudioTrack.stop();
        }
    }

    public synchronized void onShutdown() {
        LogUtil.debug(TAG, "onShutdown onStop audio and release");
        this.mIsStopAudio = true;
        if (!this.mReleased && this.mAudioPlayerStarted) {
            this.mAudioPlayerStarted = false;
            safeStop(false);
            LogUtil.info(TAG, "stream stopped type:" + this.mStreamType);
        }
        this.mReleased = true;
    }

    public synchronized void feedAudioData(int sessionId, long timestamp, byte[] data) {
        this.mIsStopAudio = false;
        AudioBuffer audioBuffer = new AudioBuffer();
        audioBuffer.presentationTimeUs = timestamp;
        audioBuffer.payload = data;
        receiveAudio(audioBuffer);
        long[] timestamps = {System.nanoTime()};
        this.mAudioSink.ackFrames(sessionId, timestamps);
        this.mSessionId = sessionId;
    }

    private void receiveAudio(AudioBuffer buffer) {
        if (this.mReleased) {
            return;
        }
        if (buffer != null && buffer.payload != null) {
            this.mNumDataReceive++;
            this.mQ.offer(buffer);
            if (AapAudioFocusManager.getInstance().hasValidAudioFocus(this.mValidFocusType) && this.mIsNeedWaitStart && this.mNumDataReceive >= this.mNumAudioByffersToStartPlay) {
                LogUtil.info(TAG, "doReceive need start audio audioType:" + this.mAudioType + " StreamType:" + this.mStreamType + " mNumDataReceive:" + this.mNumDataReceive);
                if (!this.mReleased) {
                    this.mAudioPlayerStarted = true;
                    safePlay();
                }
                this.mIsNeedWaitStart = false;
            }
        }
        resetTime();
    }

    public synchronized void onCodecConfig(byte[] data) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onCodecConfig ");
        sb.append(data.length);
        sb.append(" ");
        sb.append((int) (data.length >= 1 ? data[0] : (byte) -1));
        sb.append(" ");
        sb.append((int) (data.length >= 2 ? data[1] : (byte) -1));
        LogUtil.info(str, sb.toString());
        AudioBuffer audioBuffer = new AudioBuffer();
        this.mCodecConfig = data;
        audioBuffer.presentationTimeUs = 0L;
        audioBuffer.payload = null;
        receiveAudio(audioBuffer);
    }

    public synchronized void tearDown() {
        LogUtil.info(TAG, "tearDown is invoke");
        if (!this.mQ.isEmpty()) {
            LogUtil.debug(TAG, "tearDown clear mQ size " + this.mQ.size());
            this.mQ.clear();
        }
        this.mIsOnStart = false;
        safeStop(true);
        onShutdown();
        if (this.mAudioPlayThread != null) {
            LogUtil.info(TAG, "tearDown is release mAudioPlayThread");
            this.mAudioPlayThread.release();
            this.mAudioPlayThread = null;
        }
        synchronized (this.mAudioTrackLock) {
            if (this.mAudioTrack != null) {
                LogUtil.info(TAG, "tearDown is release mAudioTrack");
                this.mAudioTrack.release();
                this.mAudioTrack = null;
            }
        }
        LogUtil.info(TAG, "tearDown is end");
    }

    private void safePlay() {
        LogUtil.info(TAG, "safePlay start");
        AudioPlayThread audioPlayThread = this.mAudioPlayThread;
        if (audioPlayThread != null && !audioPlayThread.isPlaying()) {
            this.mAudioPlayThread.startPlaying();
        }
        LogUtil.info(TAG, "safePlay end");
    }

    private void safeStop(boolean flush) {
        AudioPlayThread audioPlayThread = this.mAudioPlayThread;
        if (audioPlayThread != null && audioPlayThread.isPlaying()) {
            LogUtil.debug(TAG, "stop for stream " + this.mStreamType + " flush:" + flush);
            this.mAudioPlayThread.stopPlaying();
            if (flush) {
                this.mQ.clear();
            }
        }
    }

    private int getFrameSizeInSamples() {
        if (this.mStreamType == 3) {
            return 2048;
        }
        return 1024;
    }

    private class AudioBuffer {
        public byte[] payload;
        public long presentationTimeUs;

        private AudioBuffer() {
            this.presentationTimeUs = 0L;
        }
    }

    public void saveAudioData(String path, byte[] dataToWrite, int size) {
        if (path != null) {
            try {
                if (this.mRandomFile == null) {
                    LogUtil.warning(TAG, "Test: new file");
                    RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
                    this.mRandomFile = randomAccessFile;
                    FileChannel channel = randomAccessFile.getChannel();
                    this.mFileChannel = channel;
                    channel.truncate(0L);
                }
                this.mRandomFile.write(dataToWrite, 0, size);
            } catch (FileNotFoundException exception) {
                LogUtil.warning(TAG, "Test: Save meida File FileNotFoundException:" + exception.getMessage());
            } catch (IOException exception2) {
                LogUtil.warning(TAG, "Test: Save meida File IOException:" + exception2.getMessage());
            } catch (Exception exception3) {
                LogUtil.warning(TAG, "Test: Save meida File Exception:" + exception3.getMessage());
            }
        }
    }

    public void closeAudioFile() {
        try {
            if (this.mRandomFile != null) {
                this.mRandomFile.close();
                LogUtil.info(TAG, "closeAudioFile mRandomFile");
                this.mRandomFile = null;
            }
            if (this.mFileChannel != null) {
                this.mFileChannel.close();
                this.mFileChannel = null;
            }
        } catch (IOException exception) {
            LogUtil.warning(TAG, "closeAudioFile IOException:" + exception.getMessage());
            exception.printStackTrace();
        } catch (Exception exception2) {
            LogUtil.warning(TAG, "closeAudioFile Exception:" + exception2.getMessage());
            exception2.printStackTrace();
        }
    }

    private abstract class AudioPlayThread extends Thread {
        private static final int WAIT_TIME_MS = 50;
        private boolean mIsInStopPlaying;
        private boolean mIsPlaying;
        private boolean mIsStartInStopingTimeout;
        private boolean mShouldQuit;
        protected final Semaphore mWaitStopEnd;

        public AudioPlayThread(String threadName) {
            super(threadName);
            this.mShouldQuit = false;
            this.mIsPlaying = false;
            this.mIsInStopPlaying = false;
            this.mIsStartInStopingTimeout = false;
            this.mWaitStopEnd = new Semaphore(0);
        }

        protected boolean shouldQuit() {
            return this.mShouldQuit;
        }

        protected boolean isPlaying() {
            return this.mIsPlaying;
        }

        public synchronized void startPlaying() {
            LogUtil.info(AudioPlayer.TAG, "startPlaying  StreamType:" + AudioPlayer.this.mStreamType + " audioType:" + AudioPlayer.this.mAudioType);
            LogUtil.info(AudioPlayer.TAG, " mIsInStopPlaying: " + this.mIsInStopPlaying + " mIsPlaying: " + this.mIsPlaying + " thread id :" + Thread.currentThread().getId());
            if (this.mIsInStopPlaying) {
                try {
                    boolean isAcquire = this.mWaitStopEnd.tryAcquire(AudioPlayer.MAX_STOP_WAIT_MS, TimeUnit.MILLISECONDS);
                    LogUtil.info(AudioPlayer.TAG, "startPlaying wait stop end isAcquire:" + isAcquire);
                    if (isAcquire) {
                        this.mIsPlaying = true;
                        notify();
                    } else {
                        this.mIsStartInStopingTimeout = true;
                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            } else {
                this.mIsPlaying = true;
                notify();
            }
        }

        public synchronized void stopPlaying() {
            LogUtil.debug(AudioPlayer.TAG, "stopPlaying mStreamType:" + AudioPlayer.this.mStreamType + " mIsStartInStopingTimeout:" + this.mIsStartInStopingTimeout);
            LogUtil.debug(AudioPlayer.TAG, "stopPlaying mIsOnStart:" + AudioPlayer.this.mIsOnStart + " mIsPlaying:" + this.mIsPlaying + " thread id :" + Thread.currentThread().getId());
            AudioPlayer.this.mNumDataReceive = 0;
            if (AudioPlayer.this.mIsOnStart && this.mIsPlaying) {
                AudioPlayer.this.mIsNeedWaitStart = true;
            }
            synchronized (this) {
                try {
                    if (!this.mIsPlaying) {
                        try {
                            LogUtil.debug(AudioPlayer.TAG, "stopPlaying not start return,  mStreamType:" + AudioPlayer.this.mStreamType);
                            this.mIsInStopPlaying = false;
                            this.mWaitStopEnd.release();
                            return;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                    this.mIsInStopPlaying = true;
                    LogUtil.debug(AudioPlayer.TAG, "stopPlaying start mStreamType:" + AudioPlayer.this.mStreamType);
                    this.mIsPlaying = false;
                    notify();
                    LogUtil.debug(AudioPlayer.TAG, "stopPlaying after mStopWait mStreamType:" + AudioPlayer.this.mStreamType + " mIsStartInStopingTimeout: " + this.mIsStartInStopingTimeout);
                    this.mIsInStopPlaying = false;
                    this.mWaitStopEnd.release();
                    if (this.mIsStartInStopingTimeout) {
                        this.mIsStartInStopingTimeout = false;
                        this.mIsPlaying = true;
                        notify();
                    }
                    LogUtil.debug(AudioPlayer.TAG, "stopPlaying end : mStreamType:" + AudioPlayer.this.mStreamType + " mIsPlaying:" + this.mIsPlaying + " thread id :" + Thread.currentThread().getId());
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }

        public void release() {
            synchronized (this) {
                this.mIsPlaying = false;
                this.mShouldQuit = true;
                LogUtil.info(AudioPlayer.TAG, "release mShouldQuit " + this.mShouldQuit);
                notifyAll();
            }
            SystemClock.sleep(50L);
            synchronized (this) {
                try {
                    notifyAll();
                } catch (IllegalMonitorStateException exception) {
                    LogUtil.info(AudioPlayer.TAG, "release notify exception:" + exception.toString());
                    exception.printStackTrace();
                }
            }
            try {
                join();
            } catch (InterruptedException exception2) {
                exception2.printStackTrace();
            }
            LogUtil.info(AudioPlayer.TAG, "release end join");
        }

        protected void dumpAudioData(AudioBuffer audioBuffer) {
            if (audioBuffer != null && AudioPlayer.this.mIsDumpAudio) {
                if (AudioPlayer.this.mStream == 100) {
                    if (AudioPlayer.this.mAudioType != 200) {
                        if (AudioPlayer.this.mAudioType != 201) {
                            if (AudioPlayer.this.mAudioType == 202) {
                                AudioPlayer.this.saveAudioData(AudioPlayer.WIER_MEDIA_VR_PATH, audioBuffer.payload, audioBuffer.payload.length);
                                return;
                            }
                            return;
                        }
                        AudioPlayer.this.saveAudioData(AudioPlayer.WIER_MEDIA_NAVI_PATH, audioBuffer.payload, audioBuffer.payload.length);
                        return;
                    }
                    AudioPlayer.this.saveAudioData(AudioPlayer.WIER_MEDIA_PATH, audioBuffer.payload, audioBuffer.payload.length);
                    return;
                }
                if (AudioPlayer.this.mStream == 101) {
                    if (AudioPlayer.this.mAudioType != 201) {
                        if (AudioPlayer.this.mAudioType == 202) {
                            AudioPlayer.this.saveAudioData(AudioPlayer.WIER_GUIDANCE_VR_PATH, audioBuffer.payload, audioBuffer.payload.length);
                            return;
                        }
                        return;
                    }
                    AudioPlayer.this.saveAudioData(AudioPlayer.WIER_GUIDANCE_NAVI_PATH, audioBuffer.payload, audioBuffer.payload.length);
                }
            }
        }

        protected void dumpCacheAudioData(byte[] data, int bufferSize) {
            if (data != null && AudioPlayer.this.mIsDumpAudio) {
                if (AudioPlayer.this.mStream == 100) {
                    if (AudioPlayer.this.mAudioType != 200) {
                        if (AudioPlayer.this.mAudioType != 201) {
                            if (AudioPlayer.this.mAudioType == 202) {
                                AudioPlayer.this.saveAudioData(AudioPlayer.WIERLESS_MEDIA_VR_PATH, data, bufferSize);
                                return;
                            }
                            return;
                        }
                        AudioPlayer.this.saveAudioData(AudioPlayer.WIERLESS_MEDIA_NAVI_PATH, data, bufferSize);
                        return;
                    }
                    AudioPlayer.this.saveAudioData(AudioPlayer.WIERLESS_MEDIA_PATH, data, bufferSize);
                    return;
                }
                if (AudioPlayer.this.mStream == 101) {
                    if (AudioPlayer.this.mAudioType != 201) {
                        if (AudioPlayer.this.mAudioType == 202) {
                            AudioPlayer.this.saveAudioData(AudioPlayer.WIERLESS_GUIDANCE_VR_PATH, data, bufferSize);
                            return;
                        }
                        return;
                    }
                    AudioPlayer.this.saveAudioData(AudioPlayer.WIERLESS_GUIDANCE_NAVI_PATH, data, bufferSize);
                }
            }
        }
    }

    private class PcmPlayThread extends AudioPlayThread {
        public PcmPlayThread() {
            super("Pcm-" + AudioPlayer.this.mStreamType);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-16);
            LogUtil.info(AudioPlayer.TAG, "PCM thread starts " + AudioPlayer.this.mStreamType + " " + this);
            while (true) {
                synchronized (this) {
                    if (shouldQuit()) {
                        LogUtil.info(AudioPlayer.TAG, "PCM thread ends " + AudioPlayer.this.mStreamType + " " + this);
                        return;
                    }
                    LogUtil.info(AudioPlayer.TAG, "PCM thread wait start");
                    try {
                        wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    LogUtil.info(AudioPlayer.TAG, "PCM thread wait end");
                }
                boolean started = false;
                int buffersWritten = 0;
                while (!shouldQuit() && (isPlaying() || AudioPlayer.this.mQ.peek() != null)) {
                    if (AudioPlayer.this.mQ.isEmpty()) {
                        try {
                            sleep(1L);
                        } catch (InterruptedException exception2) {
                            exception2.printStackTrace();
                        }
                    }
                    AudioBuffer audioBuffer = (AudioBuffer) AudioPlayer.this.mQ.poll();
                    if (audioBuffer != null) {
                        dumpAudioData(audioBuffer);
                        synchronized (AudioPlayer.this.mAudioTrackLock) {
                            if (AudioPlayer.this.mAudioTrack != null) {
                                buffersWritten++;
                                if (!started && buffersWritten == 1) {
                                    LogUtil.info(AudioPlayer.TAG, "start play mStreamType:" + AudioPlayer.this.mStreamType);
                                    AudioPlayer.this.mAudioTrack.play();
                                    LogUtil.info(AudioPlayer.TAG, "end play mStreamType:" + AudioPlayer.this.mStreamType);
                                    started = true;
                                    AudioPlayer.this.mIsStopAudioTrack = false;
                                }
                                if (!AudioPlayer.this.mIsStopAudioTrack || AudioPlayer.this.mStream != 100) {
                                    AudioPlayer.this.mAudioTrack.write(audioBuffer.payload, 0, audioBuffer.payload.length);
                                } else {
                                    LogUtil.info(AudioPlayer.TAG, "PCM Media audiotrack is stop continue, mStreamType:" + AudioPlayer.this.mStreamType);
                                }
                            }
                        }
                    }
                }
                LogUtil.info(AudioPlayer.TAG, "PCM thread poll-while end. mQ.size()" + AudioPlayer.this.mQ.size());
                synchronized (AudioPlayer.this.mAudioTrackLock) {
                    if (AudioPlayer.this.mAudioTrack != null) {
                        if (!started && AapController.getInstance().getLinkStatus() == AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED) {
                            AudioPlayer.this.mAudioTrack.play();
                        }
                        LogUtil.info(AudioPlayer.TAG, "pcm stop audiotrack");
                        AudioPlayer.this.mIsStopAudioTrack = false;
                        AudioPlayer.this.mAudioTrack.stop();
                    }
                }
                if (!AudioPlayer.this.mQ.isEmpty()) {
                    LogUtil.warning(AudioPlayer.TAG, "data still left in Q, clearing " + AudioPlayer.this.mQ.size());
                    AudioPlayer.this.mQ.clear();
                }
            }
        }
    }

    private class AacPlayThread extends AudioPlayThread {
        private MediaFormat audioFormat;
        MediaCodec.BufferInfo bufferInfo;
        int buffersWritten;
        private MediaCodec mAacDecoder;
        private final byte[] mDecodedBuffer;
        int outputBufferIndex;
        boolean started;

        public AacPlayThread() {
            super("Aac-" + AudioPlayer.this.mStreamType);
            this.mDecodedBuffer = new byte[8192];
            this.outputBufferIndex = 0;
            this.buffersWritten = 0;
            this.started = false;
        }

        private MediaFormat makeAacCodecSpecificData(int audioProfile, int sampleRate, int channelConfig) {
            int[] samplingFreq = {AudioPlayer.SAMPLING_RATE_96000, AudioPlayer.SAMPLING_RATE_88200, AudioPlayer.SAMPLING_RATE_64000, 48000, AudioPlayer.SAMPLING_RATE_44100, AudioPlayer.SAMPLING_RATE_32000, AudioPlayer.SAMPLING_RATE_24000, AudioPlayer.SAMPLING_RATE_22050, AudioPlayer.SAMPLING_RATE_16000, AudioPlayer.SAMPLING_RATE_12000, AudioPlayer.SAMPLING_RATE_11025, AudioPlayer.SAMPLING_RATE_8000};
            MediaFormat format = new MediaFormat();
            format.setString("mime", "audio/mp4a-latm");
            format.setInteger("sample-rate", sampleRate);
            format.setInteger("channel-count", channelConfig);
            int sampleIndex = -1;
            for (int i = 0; i < samplingFreq.length; i++) {
                if (samplingFreq[i] == sampleRate) {
                    LogUtil.info("TAG", "kSamplingFreq " + samplingFreq[i] + " i : " + i);
                    sampleIndex = i;
                }
            }
            if (sampleIndex == -1) {
                return null;
            }
            ByteBuffer csd = ByteBuffer.allocate(2);
            csd.put((byte) ((audioProfile << 3) | (sampleIndex >> 1)));
            csd.position(1);
            csd.put((byte) (((byte) ((sampleIndex << 7) & 128)) | (channelConfig << 3)));
            csd.flip();
            format.setByteBuffer("csd-0", csd);
            for (int k = 0; k < csd.capacity(); k++) {
                LogUtil.info("TAG", "csd : " + ((int) csd.array()[k]));
            }
            return format;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-16);
            LogUtil.info(AudioPlayer.TAG, "AAC thread starts " + AudioPlayer.this.mStreamType + " " + this);
            this.audioFormat = makeAacCodecSpecificData(2, AudioPlayer.this.mSamplingRate, AudioPlayer.this.mChannelConfig == 12 ? 2 : 1);
            try {
                this.mAacDecoder = MediaCodec.createDecoderByType("audio/mp4a-latm");
            } catch (Exception exception) {
                LogUtil.error(AudioPlayer.TAG, "AAC create exception " + exception.toString());
            }
            this.mAacDecoder.configure(this.audioFormat, (Surface) null, (MediaCrypto) null, 0);
            this.mAacDecoder.start();
            while (true) {
                synchronized (this) {
                    if (!shouldQuit()) {
                        LogUtil.info(AudioPlayer.TAG, "AAC thread wait start");
                        try {
                            wait();
                        } catch (InterruptedException exception2) {
                            exception2.printStackTrace();
                        }
                        LogUtil.info(AudioPlayer.TAG, "AAC thread wait end");
                    } else {
                        this.mAacDecoder.stop();
                        this.mAacDecoder.release();
                        return;
                    }
                }
                aacPlayLoop();
                if (this.started) {
                    LogUtil.info(AudioPlayer.TAG, "Aac Cache mAudioTrack stop start");
                    synchronized (AudioPlayer.this.mAudioTrackLock) {
                        if (AudioPlayer.this.mAudioTrack != null) {
                            AudioPlayer.this.mIsStopAudioTrack = false;
                            AudioPlayer.this.mAudioTrack.stop();
                        }
                    }
                    this.started = false;
                    this.buffersWritten = 0;
                    LogUtil.info(AudioPlayer.TAG, "Aac Cache mAudioTrack stop end");
                }
                if (!AudioPlayer.this.mQ.isEmpty()) {
                    LogUtil.info(AudioPlayer.TAG, "Aac mQ clear buffer");
                    AudioPlayer.this.mQ.clear();
                }
            }
        }

        private void aacPlayLoop() {
            boolean codecConfigSet = false;
            this.outputBufferIndex = 0;
            this.bufferInfo = new MediaCodec.BufferInfo();
            this.buffersWritten = 0;
            this.started = false;
            while (!shouldQuit()) {
                if (isPlaying() || AudioPlayer.this.mQ.peek() != null) {
                    AudioBuffer audioBuffer = (AudioBuffer) AudioPlayer.this.mQ.poll();
                    if (audioBuffer != null) {
                        byte[] payload = audioBuffer.payload;
                        boolean isCodecConfig = false;
                        if (payload == null) {
                            if (codecConfigSet || AudioPlayer.this.mCodecConfig == null) {
                                LogUtil.info(AudioPlayer.TAG, "payload null, codecconfigset:" + codecConfigSet + " codecConfig:" + Arrays.toString(AudioPlayer.this.mCodecConfig));
                            } else {
                                payload = AudioPlayer.this.mCodecConfig;
                                codecConfigSet = true;
                                isCodecConfig = true;
                            }
                        }
                        if (Log.isLoggable(AudioPlayer.TAG, 2)) {
                            LogUtil.verbose(AudioPlayer.TAG, "payload " + Arrays.toString(payload) + " isCodecConfig:" + isCodecConfig);
                        }
                        int inputBufferIndex = -1;
                        try {
                            inputBufferIndex = this.mAacDecoder.dequeueInputBuffer(64000L);
                        } catch (IllegalStateException exception) {
                            exception.printStackTrace();
                        }
                        if (inputBufferIndex < 0) {
                            LogUtil.warning(AudioPlayer.TAG, "No input buffer available " + AudioPlayer.this.mStreamType);
                        } else {
                            ByteBuffer inputBuffer = this.mAacDecoder.getInputBuffer(inputBufferIndex);
                            inputBuffer.clear();
                            inputBuffer.put(payload);
                            try {
                                this.mAacDecoder.queueInputBuffer(inputBufferIndex, 0, payload.length, audioBuffer.presentationTimeUs, isCodecConfig ? 2 : 0);
                                boolean outResult = outputHandle();
                                if (!outResult) {
                                    return;
                                }
                            } catch (IllegalStateException ilException) {
                                LogUtil.warning(AudioPlayer.TAG, "queueInputBuffer exception: " + ilException.toString());
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }

        private boolean outputHandle() {
            try {
                int iDequeueOutputBuffer = this.mAacDecoder.dequeueOutputBuffer(this.bufferInfo, 64000L);
                this.outputBufferIndex = iDequeueOutputBuffer;
                if (iDequeueOutputBuffer < 0) {
                    LogUtil.info(AudioPlayer.TAG, "outputHandle outputBufferIndex < 0");
                    if (!AudioPlayer.this.mIsOnStart || shouldQuit()) {
                        try {
                            LogUtil.info(AudioPlayer.TAG, "AAC flush codec mStreamType:" + AudioPlayer.this.mStreamType);
                            this.mAacDecoder.flush();
                        } catch (IllegalStateException exception) {
                            exception.printStackTrace();
                        }
                        this.buffersWritten = 0;
                        this.started = false;
                    }
                } else {
                    ByteBuffer decodedData = this.mAacDecoder.getOutputBuffer(iDequeueOutputBuffer);
                    decodedData.position(this.bufferInfo.offset);
                    decodedData.limit(this.bufferInfo.offset + this.bufferInfo.size);
                    if ((this.bufferInfo.flags & 2) != 0) {
                        LogUtil.debug(AudioPlayer.TAG, "config from AAC decoder outputBufferIndex:" + this.outputBufferIndex);
                        this.mAacDecoder.releaseOutputBuffer(this.outputBufferIndex, false);
                    } else if ((this.bufferInfo.flags & 4) != 0) {
                        LogUtil.debug(AudioPlayer.TAG, "EOS from AAC decoder outputBufferIndex:" + this.outputBufferIndex);
                    } else {
                        decodedData.get(this.mDecodedBuffer, 0, this.bufferInfo.size);
                        this.mAacDecoder.releaseOutputBuffer(this.outputBufferIndex, false);
                        if (AudioPlayer.this.mStream == 100 && AudioPlayer.this.mReceiveDataCount < 3) {
                            AudioPlayer.access$1608(AudioPlayer.this);
                            LogUtil.info(AudioPlayer.TAG, "Aac Thread media output offer skip,mReceiveDataCount=" + AudioPlayer.this.mReceiveDataCount);
                            return true;
                        }
                        int i = this.buffersWritten;
                        if (i < 3) {
                            this.buffersWritten = i + 1;
                        }
                        if (!this.started && this.buffersWritten == 1 && AudioPlayer.this.mAudioTrack != null) {
                            LogUtil.info(AudioPlayer.TAG, "Cache start play media mStreamType:" + AudioPlayer.this.mStreamType);
                            AudioPlayer.this.mAudioTrack.play();
                            this.started = true;
                            AudioPlayer.this.mIsStopAudioTrack = false;
                            LogUtil.info(AudioPlayer.TAG, "Cache end play media mStreamType:" + AudioPlayer.this.mStreamType);
                        }
                        if (!AudioPlayer.this.mIsStopAudioTrack || AudioPlayer.this.mStream != 100) {
                            if (AudioPlayer.this.mAudioTrack != null) {
                                byte[] data = Arrays.copyOf(this.mDecodedBuffer, this.bufferInfo.size);
                                dumpCacheAudioData(data, this.bufferInfo.size);
                                AudioPlayer.this.mAudioTrack.write(data, 0, this.bufferInfo.size);
                            }
                        } else {
                            LogUtil.info(AudioPlayer.TAG, "AAC Media audiotrack is stop continue, mStreamType:" + AudioPlayer.this.mStreamType);
                        }
                    }
                }
                return true;
            } catch (IllegalStateException exception2) {
                LogUtil.info(AudioPlayer.TAG, "Aac output exception " + exception2.toString());
                exception2.printStackTrace();
                return false;
            }
        }
    }

    private class AudioUnderflowHandler extends Handler {
        public AudioUnderflowHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                LogUtil.debug(AudioPlayer.TAG, "mIsStopAudio:" + AudioPlayer.this.mIsStopAudio);
                if (!AudioPlayer.this.mIsStopAudio) {
                    AudioPlayer.this.mAudioSink.sendUnderflowNotification(AudioPlayer.this.mSessionId);
                } else {
                    removeMessages(1);
                }
            }
        }
    }

    private void resetTime() {
        AudioUnderflowHandler audioUnderflowHandler = this.mAudioUnderflowHandler;
        if (audioUnderflowHandler != null) {
            audioUnderflowHandler.removeMessages(1);
            this.mAudioUnderflowHandler.sendEmptyMessageDelayed(1, 400L);
        }
    }

    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
    public void onMarkerReached(AudioTrack track) {
    }

    @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
    public void onPeriodicNotification(AudioTrack track) {
    }
}
