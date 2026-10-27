package com.google.android.exoplayer2.audio;

import android.media.AudioFormat;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.exoplayer2.util.Logger;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes6.dex */
public final class DolbyPassthroughAudioTrack extends android.media.AudioTrack {
    private final String TAG;
    private byte[][] audioBuffer;
    private final Logger log;
    private int nextBufferIndex;
    private Semaphore pendingWriteSem;
    private Handler trackHandler;
    private ConditionVariable trackHandlerGate;
    private HandlerThread trackHandlerThread;

    public DolbyPassthroughAudioTrack(android.media.AudioAttributes audioAttributes, AudioFormat audioFormat, int i, int i2, int i3) {
        super(audioAttributes, audioFormat, i, i2, i3);
        String simpleName = DolbyPassthroughAudioTrack.class.getSimpleName();
        this.TAG = simpleName;
        this.trackHandlerThread = null;
        this.trackHandler = null;
        this.trackHandlerGate = null;
        this.pendingWriteSem = null;
        this.audioBuffer = null;
        this.nextBufferIndex = 0;
        this.log = new Logger(Logger.Module.Audio, simpleName);
        initialize();
    }

    public DolbyPassthroughAudioTrack(int i, int i2, int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        this(i, i2, i3, i4, i5, i6, 0);
    }

    public DolbyPassthroughAudioTrack(int i, int i2, int i3, int i4, int i5, int i6, int i7) throws IllegalArgumentException {
        super(i, i2, i3, i4, i5, i6, i7);
        String simpleName = DolbyPassthroughAudioTrack.class.getSimpleName();
        this.TAG = simpleName;
        this.trackHandlerThread = null;
        this.trackHandler = null;
        this.trackHandlerGate = null;
        this.pendingWriteSem = null;
        this.audioBuffer = null;
        this.nextBufferIndex = 0;
        this.log = new Logger(Logger.Module.Audio, simpleName);
        initialize();
    }

    private void initialize() {
        this.log.i("initialize");
        this.trackHandlerGate = new ConditionVariable(true);
        this.trackHandlerThread = new HandlerThread("dolbyTrackHandlerThread");
        this.pendingWriteSem = new Semaphore(2);
        this.audioBuffer = new byte[2][];
        this.trackHandlerThread.start();
        this.trackHandler = new Handler(this.trackHandlerThread.getLooper()) { // from class: com.google.android.exoplayer2.audio.DolbyPassthroughAudioTrack.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        int i = message.arg1;
                        int i2 = message.arg2;
                        if (DolbyPassthroughAudioTrack.this.log.allowVerbose()) {
                            DolbyPassthroughAudioTrack.this.log.v("writing to track : size = " + i + " bufferIndex = " + i2);
                        }
                        DolbyPassthroughAudioTrack dolbyPassthroughAudioTrack = DolbyPassthroughAudioTrack.this;
                        DolbyPassthroughAudioTrack.super.write(dolbyPassthroughAudioTrack.audioBuffer[i2], 0, i);
                        if (DolbyPassthroughAudioTrack.this.log.allowVerbose()) {
                            DolbyPassthroughAudioTrack.this.log.v("writing to  track  done");
                        }
                        DolbyPassthroughAudioTrack.this.pendingWriteSem.release();
                        break;
                    case 2:
                        DolbyPassthroughAudioTrack.this.log.i("pausing track");
                        DolbyPassthroughAudioTrack.super.pause();
                        DolbyPassthroughAudioTrack.this.trackHandlerGate.open();
                        break;
                    case 3:
                        DolbyPassthroughAudioTrack.this.log.i("playing track");
                        DolbyPassthroughAudioTrack.super.play();
                        DolbyPassthroughAudioTrack.this.trackHandlerGate.open();
                        break;
                    case 4:
                        DolbyPassthroughAudioTrack.this.log.i("flushing track");
                        DolbyPassthroughAudioTrack.super.flush();
                        DolbyPassthroughAudioTrack.this.trackHandlerGate.open();
                        break;
                    case 5:
                        DolbyPassthroughAudioTrack.this.log.i("stopping track");
                        DolbyPassthroughAudioTrack.super.stop();
                        DolbyPassthroughAudioTrack.this.trackHandlerGate.open();
                        break;
                    case 6:
                        DolbyPassthroughAudioTrack.this.log.i("releasing track");
                        if (DolbyPassthroughAudioTrack.super.getPlayState() != 1) {
                            DolbyPassthroughAudioTrack.this.log.i("not in stopped state...stopping");
                            DolbyPassthroughAudioTrack.super.stop();
                        }
                        DolbyPassthroughAudioTrack.super.release();
                        DolbyPassthroughAudioTrack.this.trackHandlerGate.open();
                        break;
                    default:
                        DolbyPassthroughAudioTrack.this.log.w("unknown message..ignoring!!!");
                        break;
                }
            }
        };
    }

    @Override // android.media.AudioTrack
    public void play() throws IllegalStateException {
        this.log.i(PageUriUtils.DO_PLAY_VALUE);
        this.trackHandlerGate.close();
        Message messageObtainMessage = this.trackHandler.obtainMessage(3);
        if (this.log.allowDebug()) {
            this.log.d("Sending play to DirectTrack handler thread");
        }
        this.trackHandler.sendMessage(messageObtainMessage);
        this.trackHandlerGate.block();
        if (this.log.allowDebug()) {
            this.log.d("DirectTrack Play done");
        }
    }

    @Override // android.media.AudioTrack
    public void pause() throws IllegalStateException {
        this.log.i("pause");
        this.trackHandlerGate.close();
        Message messageObtainMessage = this.trackHandler.obtainMessage(2);
        if (this.log.allowDebug()) {
            this.log.d("Sending pause directtrack handler thread");
        }
        this.trackHandler.sendMessage(messageObtainMessage);
        this.trackHandlerGate.block();
        if (this.log.allowDebug()) {
            this.log.d("Pausing Direct Track Done");
        }
    }

    @Override // android.media.AudioTrack
    public void flush() throws IllegalStateException {
        this.log.i("flush");
        this.trackHandlerGate.close();
        Message messageObtainMessage = this.trackHandler.obtainMessage(4);
        if (this.log.allowDebug()) {
            this.log.d("Sending flush Directtrack handler thread");
        }
        this.trackHandler.sendMessage(messageObtainMessage);
        this.trackHandlerGate.block();
        if (this.log.allowDebug()) {
            this.log.d("Flushing Direct Track Done");
        }
    }

    @Override // android.media.AudioTrack
    public void stop() throws IllegalStateException {
        this.log.i(AsmString.METHOD_FIREBASE_PERF_TRACE_STOP);
        if (getPlayState() == 1) {
            this.log.i("already in stopped state");
            return;
        }
        this.trackHandlerGate.close();
        Message messageObtainMessage = this.trackHandler.obtainMessage(5);
        if (this.log.allowDebug()) {
            this.log.d("Sending stop Directtrack handler thread");
        }
        this.trackHandler.sendMessage(messageObtainMessage);
        this.trackHandlerGate.block();
        if (this.log.allowDebug()) {
            this.log.d("Stopping Direct Track Done");
        }
    }

    @Override // android.media.AudioTrack
    public int write(byte[] bArr, int i, int i2) {
        if (getPlayState() != 3) {
            this.log.w("not in play state..not writing buffer now...");
            return 0;
        }
        if (!this.pendingWriteSem.tryAcquire()) {
            if (this.log.allowVerbose()) {
                this.log.v("pending writes... not writing buffer now");
            }
            return 0;
        }
        byte[] bArr2 = this.audioBuffer[this.nextBufferIndex];
        if (bArr2 == null || bArr2.length < i2) {
            if (this.log.allowVerbose()) {
                this.log.v("Allocating buffer index = " + this.nextBufferIndex + "size = " + i2);
            }
            this.audioBuffer[this.nextBufferIndex] = new byte[i2];
        }
        System.arraycopy(bArr, i, this.audioBuffer[this.nextBufferIndex], 0, i2);
        Message messageObtainMessage = this.trackHandler.obtainMessage(1, i2, this.nextBufferIndex);
        if (this.log.allowVerbose()) {
            this.log.v("Sending buffer to directtrack handler thread");
        }
        this.trackHandler.sendMessage(messageObtainMessage);
        this.nextBufferIndex = (this.nextBufferIndex + 1) % 2;
        return i2;
    }

    @Override // android.media.AudioTrack
    public void release() {
        this.log.i("release");
        this.trackHandlerGate.close();
        Message messageObtainMessage = this.trackHandler.obtainMessage(6);
        if (this.log.allowDebug()) {
            this.log.d("Sending release directtrack handler thread");
        }
        this.trackHandler.sendMessage(messageObtainMessage);
        this.trackHandlerGate.block();
        this.trackHandlerThread.quit();
        this.trackHandlerThread = null;
        this.trackHandler = null;
        this.trackHandlerGate = null;
        this.pendingWriteSem = null;
        this.audioBuffer = null;
        if (this.log.allowDebug()) {
            this.log.d("Release track done");
        }
    }
}
