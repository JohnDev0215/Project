package com.amazon.podcast.transcription;

import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.scrublib.TranscriptImplementation;
import com.google.android.exoplayer2.audio.AudioProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastAudioProcessor implements AudioProcessor {
    private static final Logger logger = LoggerFactory.getLogger("PodcastAudioProcessor");
    private int channelCount;
    private int encoding;
    private boolean inputEnded;
    private boolean isActive;
    private ByteBuffer outputBuffer;
    private Playback playback;
    private ByteBuffer processBuffer;
    private int sampleRateHz;
    private TranscriptImplementation transcriptImplementation;

    public PodcastAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.processBuffer = byteBuffer;
        this.outputBuffer = byteBuffer;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.playback = Podcast.getPlayback();
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(final int sampleRateHz, final int channelCount, final int encoding) throws AudioProcessor.UnhandledFormatException {
        logger.debug("Configure");
        if (encoding != 2) {
            throw new AudioProcessor.UnhandledFormatException(sampleRateHz, channelCount, encoding);
        }
        this.sampleRateHz = sampleRateHz;
        this.channelCount = channelCount;
        this.encoding = encoding;
        boolean z = this.isActive;
        this.isActive = true;
        return !z;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        logger.debug("isActive");
        return this.isActive;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        logger.debug("getOutputChannelCount");
        return this.channelCount;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        logger.debug("getOutputEncoding");
        return this.encoding;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i = this.channelCount;
        int i2 = (iLimit - iPosition) / (i * 2);
        int i3 = i * i2 * 2;
        final double[] dArr = new double[i2];
        if (this.processBuffer.capacity() < i3) {
            this.processBuffer = ByteBuffer.allocateDirect(i3).order(ByteOrder.nativeOrder());
        } else {
            this.processBuffer.clear();
        }
        int i4 = 0;
        while (iPosition < iLimit) {
            int i5 = this.channelCount;
            short s = 0;
            for (int i6 = 0; i6 < i5; i6++) {
                s = byteBuffer.getShort((i6 * 2) + iPosition);
                this.processBuffer.putShort(s);
            }
            dArr[i4] = s;
            i4++;
            iPosition += this.channelCount * 2;
        }
        byteBuffer.position(iLimit);
        this.processBuffer.flip();
        this.outputBuffer = this.processBuffer;
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.transcription.PodcastAudioProcessor.1
            @Override // java.lang.Runnable
            public void run() {
                PodcastAudioProcessor podcastAudioProcessor = PodcastAudioProcessor.this;
                podcastAudioProcessor.transcriptImplementation = podcastAudioProcessor.playback.getTranscriptImplementation();
                if (PodcastAudioProcessor.this.transcriptImplementation == null) {
                    return;
                }
                PodcastAudioProcessor.this.transcriptImplementation.putData(dArr);
            }
        });
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        logger.debug("queueEndOfStream");
        this.inputEnded = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        return this.outputBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        logger.debug("isEnded");
        return this.inputEnded;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        logger.debug("flush");
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        logger.debug("reset");
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.outputBuffer = byteBuffer;
        this.inputEnded = false;
        this.processBuffer = byteBuffer;
        this.channelCount = -1;
        this.encoding = -1;
        this.sampleRateHz = -1;
    }
}
