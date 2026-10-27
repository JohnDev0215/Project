package com.byd.music.view.spectrum;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AudioEvent {
    private byte[] byteBuffer;
    private long bytesProcessed;
    private int bytesProcessing;
    private final TarsosDSPAudioFloatConverter converter;
    private float[] floatBuffer;
    private final TarsosDSPAudioFormat format;
    private long frameLength;
    private int overlap = 0;

    public AudioEvent(TarsosDSPAudioFormat tarsosDSPAudioFormat) {
        this.format = tarsosDSPAudioFormat;
        this.converter = TarsosDSPAudioFloatConverter.getConverter(tarsosDSPAudioFormat);
    }

    public float getSampleRate() {
        return this.format.getSampleRate();
    }

    public int getBufferSize() {
        return getFloatBuffer().length;
    }

    public long getFrameLength() {
        return this.frameLength;
    }

    public int getOverlap() {
        return this.overlap;
    }

    public void setOverlap(int i) {
        this.overlap = i;
    }

    public void setBytesProcessed(long j) {
        this.bytesProcessed = j;
    }

    public double getTimeStamp() {
        return (this.bytesProcessed / ((long) this.format.getFrameSize())) / this.format.getSampleRate();
    }

    public double getEndTimeStamp() {
        return ((this.bytesProcessed + ((long) this.bytesProcessing)) / ((long) this.format.getFrameSize())) / this.format.getSampleRate();
    }

    public long getSamplesProcessed() {
        return this.bytesProcessed / ((long) this.format.getFrameSize());
    }

    public double getProgress() {
        return (this.bytesProcessed / ((long) this.format.getFrameSize())) / this.frameLength;
    }

    public byte[] getByteBuffer() {
        int length = getFloatBuffer().length * this.format.getFrameSize();
        byte[] bArr = this.byteBuffer;
        if (bArr == null || bArr.length != length) {
            this.byteBuffer = new byte[length];
        }
        this.converter.toByteArray(getFloatBuffer(), this.byteBuffer);
        return this.byteBuffer;
    }

    public void setFloatBuffer(float[] fArr) {
        this.floatBuffer = fArr;
    }

    public float[] getFloatBuffer() {
        return this.floatBuffer;
    }

    public double getRMS() {
        return calculateRMS(this.floatBuffer);
    }

    public double getdBSPL() {
        return soundPressureLevel(this.floatBuffer);
    }

    public static double calculateRMS(float[] fArr) {
        double d = 0.0d;
        for (int i = 0; i < fArr.length; i++) {
            d += (double) (fArr[i] * fArr[i]);
        }
        return Math.sqrt(d / Double.valueOf(fArr.length).doubleValue());
    }

    public void clearFloatBuffer() {
        Arrays.fill(this.floatBuffer, 0.0f);
    }

    private static double soundPressureLevel(float[] fArr) {
        return linearToDecibel(calculateRMS(fArr));
    }

    private static double linearToDecibel(double d) {
        return Math.log10(d) * 20.0d;
    }

    public boolean isSilence(double d) {
        return soundPressureLevel(this.floatBuffer) < d;
    }

    public void setBytesProcessing(int i) {
        this.bytesProcessing = i;
    }
}
