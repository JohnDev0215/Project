package com.byd.music.view.spectrum;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class AudioDispatcher implements Runnable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getLogger(AudioDispatcher.class.getName());
    private byte[] audioByteBuffer;
    private AudioEvent audioEvent;
    private float[] audioFloatBuffer;
    private final TarsosDSPAudioInputStream audioInputStream;
    private final List<AudioProcessor> audioProcessors = new CopyOnWriteArrayList();
    private int byteOverlap;
    private int byteStepSize;
    private long bytesProcessed;
    private long bytesToSkip;
    private final TarsosDSPAudioFloatConverter converter;
    private int floatOverlap;
    private int floatStepSize;
    private final TarsosDSPAudioFormat format;
    private boolean stopped;
    private boolean zeroPadFirstBuffer;
    private boolean zeroPadLastBuffer;

    public AudioDispatcher(TarsosDSPAudioInputStream tarsosDSPAudioInputStream, int i, int i2) {
        this.audioInputStream = tarsosDSPAudioInputStream;
        this.format = tarsosDSPAudioInputStream.getFormat();
        setStepSizeAndOverlap(i, i2);
        AudioEvent audioEvent = new AudioEvent(this.format);
        this.audioEvent = audioEvent;
        audioEvent.setFloatBuffer(this.audioFloatBuffer);
        this.audioEvent.setOverlap(i2);
        this.converter = TarsosDSPAudioFloatConverter.getConverter(this.format);
        this.stopped = false;
        this.bytesToSkip = 0L;
        this.zeroPadLastBuffer = true;
    }

    public void skip(double d) {
        this.bytesToSkip = Math.round(d * ((double) this.format.getSampleRate())) * ((long) this.format.getFrameSize());
    }

    public void setStepSizeAndOverlap(int i, int i2) {
        float[] fArr = new float[i];
        this.audioFloatBuffer = fArr;
        this.floatOverlap = i2;
        this.floatStepSize = fArr.length - i2;
        this.audioByteBuffer = new byte[fArr.length * this.format.getFrameSize()];
        this.byteOverlap = this.floatOverlap * this.format.getFrameSize();
        this.byteStepSize = this.floatStepSize * this.format.getFrameSize();
    }

    public void setZeroPadFirstBuffer(boolean z) {
        this.zeroPadFirstBuffer = z;
    }

    public void setZeroPadLastBuffer(boolean z) {
        this.zeroPadLastBuffer = z;
    }

    public void addAudioProcessor(AudioProcessor audioProcessor) {
        this.audioProcessors.add(audioProcessor);
        LOG.fine("Added an audioprocessor to the list of processors: " + audioProcessor.toString());
    }

    public void removeAudioProcessor(AudioProcessor audioProcessor) {
        this.audioProcessors.remove(audioProcessor);
        audioProcessor.processingFinished();
        LOG.fine("Remove an audioprocessor to the list of processors: " + audioProcessor.toString());
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.bytesToSkip != 0) {
            skipToStart();
        }
        try {
            this.audioEvent.setBytesProcessed(this.bytesProcessed);
            int nextAudioBlock = readNextAudioBlock();
            while (nextAudioBlock != 0 && !this.stopped) {
                Iterator<AudioProcessor> it = this.audioProcessors.iterator();
                while (it.hasNext() && it.next().process(this.audioEvent)) {
                }
                if (!this.stopped) {
                    long j = this.bytesProcessed + ((long) nextAudioBlock);
                    this.bytesProcessed = j;
                    this.audioEvent.setBytesProcessed(j);
                    try {
                        nextAudioBlock = readNextAudioBlock();
                        this.audioEvent.setOverlap(this.floatOverlap);
                    } catch (IOException e) {
                        String str = "Error while reading audio input stream: " + e.getMessage();
                        LOG.warning(str);
                        throw new Error(str);
                    }
                }
            }
            if (this.stopped) {
                return;
            }
            stop();
        } catch (IOException e2) {
            String str2 = "Error while reading audio input stream: " + e2.getMessage();
            LOG.warning(str2);
            throw new Error(str2);
        }
    }

    private void skipToStart() {
        try {
            if (this.audioInputStream.skip(this.bytesToSkip) != this.bytesToSkip) {
                throw new IOException();
            }
            this.bytesProcessed += this.bytesToSkip;
        } catch (IOException unused) {
            String str = String.format("Did not skip the expected amount of bytes,  %d skipped, %d expected!", 0L, Long.valueOf(this.bytesToSkip));
            LOG.warning(str);
            throw new Error(str);
        }
    }

    public void stop() {
        this.stopped = true;
        Iterator<AudioProcessor> it = this.audioProcessors.iterator();
        while (it.hasNext()) {
            it.next().processingFinished();
        }
        try {
            this.audioInputStream.close();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Closing audio stream error.", (Throwable) e);
        }
    }

    private int readNextAudioBlock() throws IOException {
        int length;
        int i;
        int i2;
        byte[] bArr;
        int i3;
        long j = this.bytesProcessed;
        int i4 = 0;
        boolean z = j == 0 || j == this.bytesToSkip;
        if (z && !this.zeroPadFirstBuffer) {
            length = this.audioByteBuffer.length;
            i2 = 0;
            i = 0;
        } else {
            length = this.byteStepSize;
            i = this.byteOverlap;
            i2 = this.floatOverlap;
        }
        if (!z) {
            float[] fArr = this.audioFloatBuffer;
            int length2 = fArr.length;
            int i5 = this.floatOverlap;
            int i6 = this.floatStepSize;
            if (length2 == i5 + i6) {
                System.arraycopy(fArr, i6, fArr, 0, i5);
            }
        }
        boolean z2 = false;
        int i7 = 0;
        while (!this.stopped && !z2 && i7 < length) {
            try {
                i3 = this.audioInputStream.read(this.audioByteBuffer, i + i7, length - i7);
            } catch (IndexOutOfBoundsException unused) {
                i3 = -1;
            }
            if (i3 == -1) {
                z2 = true;
            } else {
                i7 += i3;
            }
        }
        if (z2) {
            if (this.zeroPadLastBuffer) {
                int i8 = i + i7;
                while (true) {
                    bArr = this.audioByteBuffer;
                    if (i8 >= bArr.length) {
                        break;
                    }
                    bArr[i8] = 0;
                    i8++;
                }
                this.converter.toFloatArray(bArr, i, this.audioFloatBuffer, i2, this.floatStepSize);
            } else {
                byte[] bArr2 = this.audioByteBuffer;
                this.audioByteBuffer = new byte[i + i7];
                while (true) {
                    byte[] bArr3 = this.audioByteBuffer;
                    if (i4 >= bArr3.length) {
                        break;
                    }
                    bArr3[i4] = bArr2[i4];
                    i4++;
                }
                int frameSize = i7 / this.format.getFrameSize();
                float[] fArr2 = new float[(i7 / this.format.getFrameSize()) + i2];
                this.audioFloatBuffer = fArr2;
                this.converter.toFloatArray(this.audioByteBuffer, i, fArr2, i2, frameSize);
            }
        } else if (length == i7) {
            if (z && !this.zeroPadFirstBuffer) {
                TarsosDSPAudioFloatConverter tarsosDSPAudioFloatConverter = this.converter;
                byte[] bArr4 = this.audioByteBuffer;
                float[] fArr3 = this.audioFloatBuffer;
                tarsosDSPAudioFloatConverter.toFloatArray(bArr4, 0, fArr3, 0, fArr3.length);
            } else {
                this.converter.toFloatArray(this.audioByteBuffer, i, this.audioFloatBuffer, i2, this.floatStepSize);
            }
        } else if (!this.stopped) {
            throw new IOException(String.format("The end of the audio stream has not been reached and the number of bytes read (%d) is not equal to the expected amount of bytes(%d).", Integer.valueOf(i7), Integer.valueOf(length)));
        }
        this.audioEvent.setFloatBuffer(this.audioFloatBuffer);
        this.audioEvent.setOverlap(i2);
        return i7;
    }

    public TarsosDSPAudioFormat getFormat() {
        return this.format;
    }

    public float secondsProcessed() {
        return ((this.bytesProcessed / ((long) (this.format.getSampleSizeInBits() / 8))) / this.format.getSampleRate()) / this.format.getChannels();
    }

    public void setAudioFloatBuffer(float[] fArr) {
        this.audioFloatBuffer = fArr;
    }

    public boolean isStopped() {
        return this.stopped;
    }
}
