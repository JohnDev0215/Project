package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public final class AdtsExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.ts.AdtsExtractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new AdtsExtractor()};
        }
    };
    private static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private final long firstSampleTimestampUs;
    private final ParsableByteArray packetBuffer;
    private final AdtsReader reader;
    private boolean startedPacket;

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    public AdtsExtractor() {
        this(0L);
    }

    public AdtsExtractor(long j) {
        this.firstSampleTimestampUs = j;
        this.reader = new AdtsReader(true);
        this.packetBuffer = new ParsableByteArray(200);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.data);
        int i = 0;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 10);
            parsableByteArray.setPosition(0);
            if (parsableByteArray.readUnsignedInt24() != ID3_TAG) {
                break;
            }
            parsableByteArray.skipBytes(3);
            int synchSafeInt = parsableByteArray.readSynchSafeInt();
            i += synchSafeInt + 10;
            extractorInput.advancePeekPosition(synchSafeInt);
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i);
        int i2 = 0;
        int i3 = 0;
        int i4 = i;
        while (true) {
            extractorInput.peekFully(parsableByteArray.data, 0, 2);
            parsableByteArray.setPosition(0);
            if ((parsableByteArray.readUnsignedShort() & 65526) != 65520) {
                extractorInput.resetPeekPosition();
                i4++;
                if (i4 - i >= 8192) {
                    return false;
                }
                extractorInput.advancePeekPosition(i4);
                i2 = 0;
                i3 = 0;
            } else {
                i2++;
                if (i2 >= 4 && i3 > 188) {
                    return true;
                }
                extractorInput.peekFully(parsableByteArray.data, 0, 4);
                parsableBitArray.setPosition(14);
                int bits = parsableBitArray.readBits(13);
                if (bits <= 6) {
                    return false;
                }
                extractorInput.advancePeekPosition(bits - 6);
                i3 += bits;
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.reader.createTracks(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.endTracks();
        extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.startedPacket = false;
        this.reader.seek();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        int i = extractorInput.read(this.packetBuffer.data, 0, 200);
        if (i == -1) {
            return -1;
        }
        this.packetBuffer.setPosition(0);
        this.packetBuffer.setLimit(i);
        if (!this.startedPacket) {
            this.reader.packetStarted(this.firstSampleTimestampUs, true);
            this.startedPacket = true;
        }
        this.reader.consume(this.packetBuffer);
        return 0;
    }
}
