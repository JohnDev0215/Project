package com.amazon.mp3.det.minidump;

import java.io.DataInput;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes3.dex */
public class ModuleStream {
    private int checkSum;
    private long imageBase;
    private int imageSize;
    private String moduleName;
    private int timeStamp;
    private final RVA nameRva = new RVA();
    private final VersionInfo versionInfo = new VersionInfo();
    private final LocationDescription cvRecordLd = new LocationDescription();
    private final LocationDescription miscRecordLd = new LocationDescription();
    private final long[] reserved = new long[2];

    static class VersionInfo {
        private final int[] fields = new int[Field.values().length];

        public enum Field {
            dwSignature,
            dwStructVersion,
            dwFileVersionMS,
            dwFileVersionLS,
            dwProductVersionMS,
            dwProductVersionLS,
            dwFileFlagsMask,
            dwFileFlags,
            dwFileOS,
            dwFileType,
            dwFileSubtype,
            dwFileDateMS,
            dwFileDateLS
        }

        VersionInfo() {
        }

        public void readExternal(DataInput dataInput, FileChannel fileChannel) throws IOException {
            int i = 0;
            while (true) {
                int[] iArr = this.fields;
                if (i >= iArr.length) {
                    return;
                }
                iArr[i] = dataInput.readInt();
                i++;
            }
        }
    }

    public void readExternal(DataInput dataInput, FileChannel fileChannel) throws IOException {
        this.imageBase = dataInput.readLong();
        this.imageSize = dataInput.readInt();
        this.checkSum = dataInput.readInt();
        this.timeStamp = dataInput.readInt();
        this.nameRva.readExternal(dataInput, fileChannel);
        this.versionInfo.readExternal(dataInput, fileChannel);
        this.cvRecordLd.readExternal(dataInput, fileChannel);
        this.miscRecordLd.readExternal(dataInput, fileChannel);
        this.reserved[0] = dataInput.readLong();
        this.reserved[1] = dataInput.readLong();
    }

    public void readName(DataInput dataInput, FileChannel connection) throws IOException {
        this.nameRva.navigate(connection);
        int i = dataInput.readInt() >> 1;
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = dataInput.readChar();
        }
        this.moduleName = new String(cArr);
    }

    public LocationDescription getCvRecordLd() {
        return this.cvRecordLd;
    }

    public String toString() {
        return this.moduleName + ": " + String.format("%08x", Integer.valueOf((int) this.imageBase)) + ":" + String.format("%08x", Integer.valueOf((int) (this.imageBase + ((long) this.imageSize)))) + ", [crc " + String.format("0x%08x", Integer.valueOf(this.checkSum)) + ']';
    }
}
