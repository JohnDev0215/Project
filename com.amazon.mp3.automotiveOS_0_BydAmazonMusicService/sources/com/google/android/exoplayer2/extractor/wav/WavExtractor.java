package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public final class WavExtractor implements Extractor, SeekMap {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.wav.WavExtractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new WavExtractor()};
        }
    };
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return WavHeaderReader.peek(extractorInput) != null;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0, 1);
        this.wavHeader = null;
        extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.pendingBytes = 0;
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (this.wavHeader == null) {
            WavHeader wavHeaderPeek = WavHeaderReader.peek(extractorInput);
            this.wavHeader = wavHeaderPeek;
            if (wavHeaderPeek == null) {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
            this.trackOutput.format(Format.createAudioSampleFormat(null, voMimeTypes.VOAUDIO_RAW, null, wavHeaderPeek.getBitrate(), 32768, this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), this.wavHeader.getEncoding(), null, null, 0, null));
            this.bytesPerFrame = this.wavHeader.getBytesPerFrame();
        }
        if (!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(extractorInput, this.wavHeader);
            this.extractorOutput.seekMap(this);
        }
        int iSampleData = this.trackOutput.sampleData(extractorInput, 32768 - this.pendingBytes, true);
        if (iSampleData != -1) {
            this.pendingBytes += iSampleData;
        }
        int i = this.pendingBytes / this.bytesPerFrame;
        if (i > 0) {
            long timeUs = this.wavHeader.getTimeUs(extractorInput.getPosition() - ((long) this.pendingBytes));
            int i2 = i * this.bytesPerFrame;
            int i3 = this.pendingBytes - i2;
            this.pendingBytes = i3;
            this.trackOutput.sampleMetadata(timeUs, 1, i2, i3, null);
        }
        return iSampleData == -1 ? -1 : 0;
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.wavHeader.getDurationUs();
    }

    @Override // com.google.android.exoplayer2.extractor.SeekMap
    public long getPosition(long j) {
        return this.wavHeader.getPosition(j);
    }
}
