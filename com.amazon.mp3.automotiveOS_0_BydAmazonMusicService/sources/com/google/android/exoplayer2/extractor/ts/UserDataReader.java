package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
final class UserDataReader {
    private final List<Format> closedCaptionFormats;
    private final TrackOutput[] outputs;
    private final int USER_DATA_START_CODE = 434;
    private final int USER_DATA_IDENTIFIER_GA94 = 1195456820;
    private final int USER_DATA_TYPE_CODE_MPEG_CC = 3;

    public UserDataReader(List<Format> list) {
        this.closedCaptionFormats = list;
        this.outputs = new TrackOutput[list.size()];
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.outputs.length; i++) {
            trackIdGenerator.generateNewId();
            TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.getTrackId(), 3);
            Format format = this.closedCaptionFormats.get(i);
            String str = format.sampleMimeType;
            Assertions.checkArgument("application/cea-608".equals(str) || "application/cea-708".equals(str), "Invalid closed caption mime type provided: " + str);
            trackOutputTrack.format(Format.createTextSampleFormat(trackIdGenerator.getFormatId(), str, null, -1, format.selectionFlags, format.language, format.accessibilityChannel, null, format.isWideAspectRatio));
            this.outputs[i] = trackOutputTrack;
        }
    }

    public void consume(long j, ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 9) {
            return;
        }
        int i = parsableByteArray.readInt();
        int i2 = parsableByteArray.readInt();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if (i == 434 && i2 == 1195456820 && unsignedByte == 3 && parsableByteArray.bytesLeft() >= 2) {
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            boolean z = (unsignedByte2 & 64) != 0;
            parsableByteArray.skipBytes(1);
            int i3 = (unsignedByte2 & 31) * 3;
            if (!z || i3 == 0) {
                return;
            }
            int position = parsableByteArray.getPosition();
            for (TrackOutput trackOutput : this.outputs) {
                trackOutput.sampleData(parsableByteArray, i3);
                trackOutput.sampleMetadata(j, 1, i3, 0, null);
                parsableByteArray.setPosition(position);
            }
        }
    }
}
