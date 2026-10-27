package com.ts.androidauto.aap.video;

import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import com.google.android.projection.common.BufferPool;
import com.google.android.projection.protocol.VideoFrame;
import com.ts.androidauto.aap.common.AapConfig;
import com.ts.androidauto.fsdk.common.LogUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class VideoPlayer {
    private static final int DBG_FRAME_SIZE = 20;
    private static final boolean DBG_TRACE = false;
    private static final int DEQUEUE_BUFFER_FAIL_MAX_RETRY = 10;
    private static final int DEQUEUE_BUFFER_WAIT_TIME_US = 100000;
    private static final int NUMBER_100 = 100;
    private static final int NUMBER_1000 = 1000;
    private static final int RENDERER_STATE_NOT_STARTED = 1;
    private static final int RENDERER_STATE_RUNNING = 2;
    private static final int RENDERER_STATE_STOPPED = 3;
    private static final String TAG = "AAP" + VideoPlayer.class.getSimpleName();
    private MediaCodec mCodec;
    private boolean mDebugLog;
    private DecodeInputThread mDecodeInputThread;
    private DecodeOutputThread mDecodeOutputThread;
    private long mDecodeTimeMillis;
    private final Listener mListener;
    private Surface mSurface;
    private int mVideoSize480P = AapConfig.VIDEO_SIZE_480P;
    private int mVideoSize720P = AapConfig.VIDEO_SIZE_720P;
    private volatile int mState = 1;
    private final Rect mSurfaceFrame = new Rect();
    private Rect mVideoSize = new Rect();
    private int mDecodeCount = 0;
    private long mDecodeTotalTime = 0;
    private final LinkedBlockingQueue<VideoFrame> mPendingInputBuffers = new LinkedBlockingQueue<>();

    public interface Listener {
        void onFrameRendered(int i);

        void onMediaCodecError();

        void onMediaCodecReleased();
    }

    static /* synthetic */ int access$208(VideoPlayer x0) {
        int i = x0.mDecodeCount;
        x0.mDecodeCount = i + 1;
        return i;
    }

    public VideoPlayer(Surface surface, int width, int height, Listener listener) {
        this.mDebugLog = DBG_TRACE;
        this.mListener = listener;
        if (surface != null) {
            updateSurface(surface, width, height);
        }
        File videoFile = new File("/sdcard/debugvideo");
        if (videoFile.exists()) {
            LogUtil.debug(TAG, "Open log for debuging video data");
            this.mDebugLog = true;
        } else {
            LogUtil.debug(TAG, "Disable video data log");
            this.mDebugLog = DBG_TRACE;
        }
    }

    public synchronized void release() {
        if (this.mSurface != null) {
            LogUtil.debug(TAG, "release set mSurface is null.");
            this.mSurface = null;
        }
        this.mState = 3;
        this.mPendingInputBuffers.clear();
        updateCodec();
    }

    public synchronized void prepareStart(int width, int height) {
        LogUtil.info(TAG, "prepareStart is invoke");
        this.mState = 2;
        this.mVideoSize = new Rect(0, 0, width, height);
    }

    public synchronized void start() {
        if (this.mSurfaceFrame.width() > 0 && this.mSurfaceFrame.height() > 0) {
            updateCodec();
        }
    }

    public boolean isStarted() {
        if (this.mState == 2) {
            return true;
        }
        return DBG_TRACE;
    }

    public synchronized void decodeBuffer(VideoFrame frame) {
        if (frame == null) {
            LogUtil.debug(TAG, "Received frame is null.");
            return;
        }
        if (this.mState == 1) {
            LogUtil.debug(TAG, "Received frame , add data to buffer.");
        }
        if (this.mState == 3) {
            LogUtil.info(TAG, "Received frame when shutting down, ignoring.");
            return;
        }
        ackFrame(frame);
        frame.timestamp = SystemClock.elapsedRealtime() * 1000;
        this.mPendingInputBuffers.add(frame);
        if (this.mDebugLog) {
            LogUtil.info(TAG, "Received frame timestamp=" + frame.timestamp + ", pending frames.size=" + this.mPendingInputBuffers.size());
        }
    }

    public synchronized void handleCodecConfig(byte[] data) {
        if (this.mState == 1) {
            LogUtil.debug(TAG, "Received codec config, add data to buffer.");
        }
        if (this.mState == 3) {
            LogUtil.info(TAG, "Received codec config while shutting down. Ignoring.");
            return;
        }
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        VideoFrame frame = new VideoFrame(0, 0L, buffer);
        ackFrame(frame);
        frame.timestamp = SystemClock.elapsedRealtime() * 1000;
        this.mPendingInputBuffers.add(frame);
    }

    private boolean codecShouldBeRunning() {
        if (this.mDebugLog) {
            LogUtil.info(TAG, "codecShouldBeRunning mState " + this.mState + " mSurface :" + this.mSurface);
        }
        if (this.mState != 2 || this.mSurface == null) {
            return DBG_TRACE;
        }
        return true;
    }

    public synchronized void updateSurface(Surface surface, int width, int height) {
        if (surface != null && width > 0 && height > 0) {
            this.mSurface = surface;
            this.mSurfaceFrame.set(0, 0, width, height);
            updateCodec();
        }
    }

    public void updateSurfaceSize(int width, int height) {
        LogUtil.debug(TAG, "updateSurfaceSize width: " + width + " height: " + height + " video size: " + this.mVideoSize);
        this.mSurfaceFrame.set(0, 0, width, height);
    }

    private synchronized void updateCodec() {
        if (this.mVideoSize != null && this.mSurfaceFrame != null && this.mVideoSize.right > 0 && this.mSurfaceFrame.right > 0 && this.mVideoSize.bottom > 0 && this.mSurfaceFrame.bottom > 0) {
            LogUtil.debug(TAG, "start updateCodec " + this.mCodec);
        } else if (this.mCodec == null) {
            LogUtil.debug(TAG, "No enough info to config MediaCodec, skip it");
            return;
        }
        createOrDestoryCodec();
    }

    private void createOrDestoryCodec() {
        LogUtil.debug(TAG, "start updateCodec " + this.mCodec);
        if (this.mCodec == null) {
            if (codecShouldBeRunning()) {
                LogUtil.info(TAG, "mCodec is create. ");
                try {
                    this.mCodec = MediaCodec.createDecoderByType("video/avc");
                    LogUtil.info(TAG, "MediaCodec.create success.");
                } catch (Exception exception) {
                    LogUtil.info(TAG, "Create decode failed. " + exception);
                }
                if (this.mCodec == null) {
                    LogUtil.info(TAG, "Create decode failed ,so do noting. ");
                    return;
                }
                MediaFormat format = MediaFormat.createVideoFormat("video/avc", this.mVideoSize.width(), this.mVideoSize.height());
                format.setInteger("vendor.qti-ext-dec-picture-order.enable", 1);
                format.setInteger("push-blank-buffers-on-shutdown", 1);
                LogUtil.info(TAG, "MediaCodec.createVideoFormat end.");
                try {
                    this.mCodec.configure(format, this.mSurface, (MediaCrypto) null, 0);
                    LogUtil.info(TAG, "MediaCodec.configure success.");
                    this.mCodec.setVideoScalingMode(2);
                    this.mCodec.start();
                    LogUtil.info(TAG, "MediaCodec.start.");
                    DecodeInputThread decodeInputThread = new DecodeInputThread();
                    this.mDecodeInputThread = decodeInputThread;
                    decodeInputThread.start();
                    DecodeOutputThread decodeOutputThread = new DecodeOutputThread();
                    this.mDecodeOutputThread = decodeOutputThread;
                    decodeOutputThread.start();
                } catch (MediaCodec.CodecException codecEx) {
                    LogUtil.error(TAG, "CodecException codecEx: " + codecEx.toString());
                    this.mCodec.release();
                    this.mCodec = null;
                } catch (MediaCodec.CryptoException cryptoEx) {
                    LogUtil.error(TAG, "CryptoException cryptoEx: " + cryptoEx.toString());
                    this.mCodec.release();
                    this.mCodec = null;
                } catch (IllegalArgumentException illegalArgEx) {
                    LogUtil.error(TAG, "IllegalArgumentException illegalArgEx: " + illegalArgEx.toString());
                    this.mCodec.release();
                    this.mCodec = null;
                } catch (IllegalStateException illegalEx) {
                    LogUtil.error(TAG, "IllegalStateException illegalEx: " + illegalEx.toString());
                    this.mCodec.release();
                    this.mCodec = null;
                }
                if (this.mCodec == null && this.mListener != null) {
                    LogUtil.info(TAG, "MediaCodec failed, notify onMediaCodecError. ");
                    this.mListener.onMediaCodecError();
                }
            }
        } else if (!codecShouldBeRunning()) {
            this.mDecodeInputThread.shutdown();
            this.mDecodeInputThread = null;
            this.mDecodeOutputThread.shutdown();
            this.mDecodeOutputThread = null;
            try {
                LogUtil.info(TAG, "mCodec is flushing. ");
                this.mCodec.flush();
                LogUtil.info(TAG, "mCodec is Stopping. ");
                this.mCodec.stop();
                LogUtil.info(TAG, "mCodec is releasing. ");
                this.mCodec.release();
                LogUtil.info(TAG, "mCodec release competed. ");
            } catch (Exception exception2) {
                LogUtil.info(TAG, "Stop decode failed. " + exception2);
            }
            this.mCodec = null;
            Listener listener = this.mListener;
            if (listener != null) {
                listener.onMediaCodecReleased();
            }
        }
        LogUtil.debug(TAG, "end updateCodec mCodec=" + this.mCodec);
    }

    private void ackFrame(VideoFrame frame) {
        if (frame != null) {
            this.mListener.onFrameRendered(frame.sessionId);
        }
    }

    void aapTraceBeginSection(String name) {
    }

    void aapTraceEndSection() {
    }

    private final class DecodeInputThread extends Thread {
        private boolean mShutdownThread;

        public DecodeInputThread() {
            super("ProjectionRenderer.DecodeInputThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int inputBufferIndex;
            boolean dequeFailed;
            int totalToWrite;
            long presentationTimeUs;
            LogUtil.debug(VideoPlayer.TAG, "DecodeInputThread start run");
            VideoPlayer.this.mDecodeTimeMillis = System.currentTimeMillis();
            char c = 0;
            VideoPlayer.this.mDecodeCount = 0;
            VideoPlayer.this.mDecodeTotalTime = 0L;
            int codecConfigFlag = 2;
            VideoFrame frame = null;
            while (true) {
                synchronized (this) {
                    if (this.mShutdownThread) {
                        LogUtil.debug(VideoPlayer.TAG, "DecodeInputThread 1 end run");
                        return;
                    }
                    if (frame == null) {
                        try {
                            VideoFrame frame2 = (VideoFrame) VideoPlayer.this.mPendingInputBuffers.poll(100L, TimeUnit.MILLISECONDS);
                            frame = frame2;
                        } catch (InterruptedException e) {
                            synchronized (this) {
                                if (this.mShutdownThread) {
                                    LogUtil.debug(VideoPlayer.TAG, "DecodeInputThread 2 end run");
                                    return;
                                }
                                LogUtil.info(VideoPlayer.TAG, "Get frame failed.");
                            }
                        }
                        if (frame == null) {
                            continue;
                        }
                    }
                    ByteBuffer buffer = frame.data;
                    int writeOffset = buffer.arrayOffset();
                    int totalToWrite2 = buffer.limit();
                    byte[] bufferArray = buffer.array();
                    long presentationTimeUs2 = frame.timestamp;
                    int totalToWrite3 = totalToWrite2;
                    int codecConfigFlag2 = codecConfigFlag;
                    int codecConfigFlag3 = writeOffset;
                    while (totalToWrite3 > 0) {
                        VideoPlayer.this.aapTraceBeginSection("dequeueInputBuffer");
                        int dequeFails = 0;
                        while (true) {
                            try {
                                int inputBufferIndex2 = VideoPlayer.this.mCodec.dequeueInputBuffer(100000L);
                                inputBufferIndex = inputBufferIndex2;
                            } catch (IllegalStateException exception) {
                                exception.printStackTrace();
                                inputBufferIndex = -1;
                            }
                            if (inputBufferIndex != -1) {
                                dequeFailed = false;
                                break;
                            }
                            dequeFails++;
                            if (dequeFails > 10) {
                                LogUtil.info(VideoPlayer.TAG, "dequeueInputBuffer failed, lost a frame.");
                                if (VideoPlayer.this.mListener != null) {
                                    VideoPlayer.this.mListener.onMediaCodecError();
                                }
                                dequeFailed = true;
                            }
                        }
                        VideoPlayer.this.aapTraceEndSection();
                        if (dequeFailed) {
                            LogUtil.debug(VideoPlayer.TAG, "InputThread 3 end run");
                            return;
                        }
                        synchronized (this) {
                            try {
                                if (this.mShutdownThread) {
                                    try {
                                        LogUtil.debug(VideoPlayer.TAG, "InputThread 4 end run");
                                        return;
                                    } catch (Throwable th) {
                                        exception = th;
                                        while (true) {
                                            try {
                                                throw exception;
                                            } catch (Throwable th2) {
                                                exception = th2;
                                            }
                                        }
                                    }
                                }
                            } catch (Throwable th3) {
                                exception = th3;
                            }
                        }
                        ByteBuffer inputBuffer = VideoPlayer.this.mCodec.getInputBuffer(inputBufferIndex);
                        int toWrite = Math.min(inputBuffer.capacity(), totalToWrite3);
                        inputBuffer.rewind();
                        inputBuffer.put(bufferArray, codecConfigFlag3, toWrite);
                        VideoPlayer.this.aapTraceBeginSection("queueInputBuffer");
                        if (VideoPlayer.this.mDebugLog) {
                            String str = VideoPlayer.TAG;
                            Object[] objArr = new Object[1];
                            objArr[c] = Long.valueOf(presentationTimeUs2);
                            LogUtil.info(str, String.format("Frame inBuf.timestamp: %d", objArr));
                        }
                        try {
                            totalToWrite = totalToWrite3;
                            presentationTimeUs = presentationTimeUs2;
                        } catch (IllegalStateException e2) {
                            exception = e2;
                            totalToWrite = totalToWrite3;
                            presentationTimeUs = presentationTimeUs2;
                        }
                        try {
                            VideoPlayer.this.mCodec.queueInputBuffer(inputBufferIndex, 0, inputBuffer.position(), presentationTimeUs2, codecConfigFlag2);
                            VideoPlayer.this.aapTraceEndSection();
                            if (codecConfigFlag2 > 0) {
                                codecConfigFlag2 = 0;
                            }
                            totalToWrite3 = totalToWrite - toWrite;
                            codecConfigFlag3 += toWrite;
                            presentationTimeUs2 = presentationTimeUs;
                            c = 0;
                        } catch (IllegalStateException e3) {
                            exception = e3;
                            LogUtil.info(VideoPlayer.TAG, "video input exception " + exception.toString());
                            exception.printStackTrace();
                            totalToWrite3 = totalToWrite;
                            presentationTimeUs2 = presentationTimeUs;
                            c = 0;
                        }
                    }
                    BufferPool.returnBuffer(frame.data);
                    frame.data = null;
                    frame = null;
                    if (VideoPlayer.this.mDebugLog) {
                        long currentTimeMillis = System.currentTimeMillis();
                        VideoPlayer.access$208(VideoPlayer.this);
                        long decodeTime = currentTimeMillis - VideoPlayer.this.mDecodeTimeMillis;
                        VideoPlayer.this.mDecodeTotalTime += decodeTime;
                        VideoPlayer.this.mDecodeTimeMillis = currentTimeMillis;
                        if (VideoPlayer.this.mDecodeCount <= 0) {
                            VideoPlayer.this.mDecodeCount = 0;
                            VideoPlayer.this.mDecodeTotalTime = 0L;
                        } else {
                            LogUtil.info(VideoPlayer.TAG, "Frame decoded cost time: " + decodeTime + " ms");
                            LogUtil.info(VideoPlayer.TAG, "Frame decoded average time: ---------" + (VideoPlayer.this.mDecodeTotalTime / ((long) VideoPlayer.this.mDecodeCount)) + " ms");
                        }
                    }
                    codecConfigFlag = codecConfigFlag2;
                    c = 0;
                }
            }
        }

        public void shutdown() {
            LogUtil.debug(VideoPlayer.TAG, "Start shutdown DecodeInputThread");
            synchronized (this) {
                this.mShutdownThread = true;
            }
            try {
                join();
            } catch (InterruptedException e) {
                LogUtil.info(VideoPlayer.TAG, "Blocks the current Thread failed.");
            }
            LogUtil.debug(VideoPlayer.TAG, "End shutdown DecodeInputThread");
        }
    }

    private final class DecodeOutputThread extends Thread {
        private boolean mShutdownThread;

        public DecodeOutputThread() {
            super("ProjectionRenderer.DecodeOutputThread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            LogUtil.debug(VideoPlayer.TAG, "DecodeOutputThread start run");
            MediaCodec.BufferInfo info = new MediaCodec.BufferInfo();
            long outTimeoutUs = 100000;
            while (true) {
                synchronized (this) {
                    if (this.mShutdownThread) {
                        LogUtil.debug(VideoPlayer.TAG, "DecodeOutputThread end run");
                        return;
                    }
                }
                try {
                    int outputBufferIndex = VideoPlayer.this.mCodec.dequeueOutputBuffer(info, outTimeoutUs);
                    if (outputBufferIndex < 0) {
                        if (VideoPlayer.this.mDebugLog) {
                            LogUtil.debug(VideoPlayer.TAG, "DecodeOutputThread outputBufIndex < 0, continue");
                        }
                        outTimeoutUs = 100000;
                    } else {
                        if (VideoPlayer.this.mDebugLog) {
                            LogUtil.info(VideoPlayer.TAG, String.format("DecodeOutputThread outBuf.timestamp=%d", Long.valueOf(info.presentationTimeUs)));
                        }
                        VideoPlayer.this.mCodec.releaseOutputBuffer(outputBufferIndex, true);
                        outTimeoutUs = 1000;
                        if (VideoPlayer.this.mDebugLog) {
                            LogUtil.info(VideoPlayer.TAG, "DecodeOutputThread release.timestamp=" + info.presentationTimeUs + ", delayTime=" + (SystemClock.elapsedRealtime() - (info.presentationTimeUs / 1000)));
                        }
                    }
                } catch (IllegalStateException exception) {
                    LogUtil.info(VideoPlayer.TAG, "DecodeOutputThread deque outBuf catch exception:" + exception.toString());
                    exception.printStackTrace();
                    outTimeoutUs = 100000;
                }
            }
        }

        public void shutdown() {
            LogUtil.debug(VideoPlayer.TAG, "Start shutdown DecodeOutputThread");
            synchronized (this) {
                this.mShutdownThread = true;
            }
            try {
                join();
            } catch (InterruptedException e) {
                LogUtil.info(VideoPlayer.TAG, "Blocks the current Thread failed.");
            }
            LogUtil.debug(VideoPlayer.TAG, "End shutdown DecodeOutputThread");
        }
    }
}
