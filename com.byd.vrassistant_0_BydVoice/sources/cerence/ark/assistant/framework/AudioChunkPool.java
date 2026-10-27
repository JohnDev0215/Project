package cerence.ark.assistant.framework;

import cerence.ark.assistant.common.Logger;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public class AudioChunkPool {
    private static final String TAG = "AudioChunkPool";
    private final int mChunkSize;
    private final int mMaxChunkSize;
    private final ArrayBlockingQueue<byte[]> mQueue;

    public AudioChunkPool(int i, int i2) {
        this.mChunkSize = i;
        this.mMaxChunkSize = i2;
        this.mQueue = new ArrayBlockingQueue<>(i2);
        reset();
    }

    public byte[] getAudioChunk(byte[] bArr) {
        int length = bArr.length;
        if (length != this.mChunkSize) {
            return bArr;
        }
        try {
            byte[] bArrTake = this.mQueue.take();
            System.arraycopy(bArr, 0, bArrTake, 0, length);
            return bArrTake;
        } catch (InterruptedException e) {
            Logger.error(TAG, "getAudioChunk: exception", e);
            Thread.currentThread().interrupt();
            return bArr;
        }
    }

    public byte[] getAudioChunk(int i) {
        if (i != this.mChunkSize) {
            return new byte[i];
        }
        try {
            return this.mQueue.take();
        } catch (InterruptedException e) {
            Logger.error(TAG, "getAudioChunk: exception", e);
            Thread.currentThread().interrupt();
            return new byte[i];
        }
    }

    public void returnAudioChunk(byte[] bArr) {
        if (bArr.length == this.mChunkSize && !this.mQueue.offer(bArr)) {
            Logger.error(TAG, "returnAudioChunk: the queue add failed");
        }
    }

    public void reset() {
        int size = this.mMaxChunkSize - this.mQueue.size();
        for (int i = 0; i < size; i++) {
            if (!this.mQueue.offer(new byte[this.mChunkSize])) {
                Logger.warn(TAG, "reset: the queue add failed, index = " + i);
            }
        }
    }
}
