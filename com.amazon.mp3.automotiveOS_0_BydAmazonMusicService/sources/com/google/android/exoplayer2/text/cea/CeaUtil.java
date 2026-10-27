package com.google.android.exoplayer2.text.cea;

import android.util.Log;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

/* JADX INFO: loaded from: classes6.dex */
public final class CeaUtil {
    public static void consume(long j, ParsableByteArray parsableByteArray, TrackOutput[] trackOutputArr, ParsableByteArray parsableByteArray2) {
        parsableByteArray2.reset(parsableByteArray.bytesLeft());
        int i = 0;
        while (parsableByteArray.bytesLeft() > 1) {
            int non255TerminatedValue = readNon255TerminatedValue(parsableByteArray);
            int non255TerminatedValue2 = readNon255TerminatedValue(parsableByteArray);
            if (non255TerminatedValue2 == -1 || non255TerminatedValue2 > parsableByteArray.bytesLeft()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                parsableByteArray.setPosition(parsableByteArray.limit());
            } else if (isSeiMessageCea608(non255TerminatedValue, non255TerminatedValue2, parsableByteArray)) {
                parsableByteArray.skipBytes(8);
                int unsignedByte = parsableByteArray.readUnsignedByte();
                boolean z = (unsignedByte & 64) == 64;
                int i2 = unsignedByte & 32;
                parsableByteArray.skipBytes(1);
                int i3 = (unsignedByte & 31) * 3;
                if (z && i2 == 0) {
                    System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), parsableByteArray2.data, i, i3);
                    i += i3;
                    parsableByteArray.skipBytes(i3);
                }
                parsableByteArray.skipBytes(non255TerminatedValue2 - (i3 + 10));
            } else {
                parsableByteArray.skipBytes(non255TerminatedValue2);
            }
        }
        if (i != 0) {
            for (TrackOutput trackOutput : trackOutputArr) {
                parsableByteArray2.setPosition(0);
                trackOutput.sampleData(parsableByteArray2, i);
                trackOutput.sampleMetadata(j, 1, i, 0, null);
            }
        }
    }

    private static int readNon255TerminatedValue(ParsableByteArray parsableByteArray) {
        int i = 0;
        while (parsableByteArray.bytesLeft() != 0) {
            int unsignedByte = parsableByteArray.readUnsignedByte();
            i += unsignedByte;
            if (unsignedByte != 255) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isSeiMessageCea608(int i, int i2, ParsableByteArray parsableByteArray) {
        if (i != 4 || i2 < 8) {
            return false;
        }
        int position = parsableByteArray.getPosition();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int i3 = parsableByteArray.readInt();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(position);
        return unsignedByte == 181 && unsignedShort == 49 && i3 == 1195456820 && unsignedByte2 == 3;
    }

    private CeaUtil() {
    }
}
