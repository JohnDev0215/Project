package com.byd.music.view.spectrum;

/* JADX INFO: loaded from: classes5.dex */
public class PipedAudioStream {
    private static PipeDecoder pipeDecoder = new PipeDecoder();
    private final PipeDecoder decoder = pipeDecoder;
    private final String resource;

    public static void setDecoder(PipeDecoder pipeDecoder2) {
        pipeDecoder = pipeDecoder2;
    }

    public PipedAudioStream(String str) {
        this.resource = AudioResourceUtils.sanitizeResource(str);
    }

    public TarsosDSPAudioInputStream getMonoStream(int i, double d) {
        return getMonoStream(i, d, -1.0d);
    }

    private TarsosDSPAudioFormat getTargetFormat(int i) {
        return new TarsosDSPAudioFormat(i, 16, 1, true, false);
    }

    public TarsosDSPAudioInputStream getMonoStream(int i, double d, double d2) {
        return new UniversalAudioInputStream(this.decoder.getDecodedStream(this.resource, i, d, d2), getTargetFormat(i));
    }
}
