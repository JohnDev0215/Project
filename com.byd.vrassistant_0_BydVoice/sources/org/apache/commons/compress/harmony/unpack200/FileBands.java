package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* JADX INFO: loaded from: classes4.dex */
public class FileBands extends BandSet {
    private final String[] cpUTF8;
    private byte[][] fileBits;
    private int[] fileModtime;
    private String[] fileName;
    private int[] fileOptions;
    private long[] fileSize;
    private InputStream in;

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() {
    }

    public FileBands(Segment segment) {
        super(segment);
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream inputStream) throws Pack200Exception, IOException {
        int numberOfFiles = this.header.getNumberOfFiles();
        SegmentOptions options = this.header.getOptions();
        this.fileName = parseReferences("file_name", inputStream, Codec.UNSIGNED5, numberOfFiles, this.cpUTF8);
        this.fileSize = parseFlags("file_size", inputStream, numberOfFiles, Codec.UNSIGNED5, options.hasFileSizeHi());
        if (options.hasFileModtime()) {
            this.fileModtime = decodeBandInt("file_modtime", inputStream, Codec.DELTA5, numberOfFiles);
        } else {
            this.fileModtime = new int[numberOfFiles];
        }
        if (options.hasFileOptions()) {
            this.fileOptions = decodeBandInt("file_options", inputStream, Codec.UNSIGNED5, numberOfFiles);
        } else {
            this.fileOptions = new int[numberOfFiles];
        }
        this.in = inputStream;
    }

    public void processFileBits() throws Pack200Exception, IOException {
        int numberOfFiles = this.header.getNumberOfFiles();
        this.fileBits = new byte[numberOfFiles][];
        for (int i = 0; i < numberOfFiles; i++) {
            int i2 = (int) this.fileSize[i];
            byte[] bArr = new byte[i2];
            this.fileBits[i] = bArr;
            int i3 = this.in.read(bArr);
            if (i2 != 0 && i3 < i2) {
                throw new Pack200Exception("Expected to read " + i2 + " bytes but read " + i3);
            }
        }
    }

    public byte[][] getFileBits() {
        return this.fileBits;
    }

    public int[] getFileModtime() {
        return this.fileModtime;
    }

    public String[] getFileName() {
        return this.fileName;
    }

    public int[] getFileOptions() {
        return this.fileOptions;
    }

    public long[] getFileSize() {
        return this.fileSize;
    }
}
