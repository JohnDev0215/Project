package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import net.bytebuddy.dynamic.ClassFileLocator;
import org.apache.commons.compress.harmony.pack200.Archive;
import org.objectweb.asm.ClassReader;

/* JADX INFO: loaded from: classes4.dex */
public class FileBands extends BandSet {
    private final CpBands cpBands;
    private final List fileList;
    private final CPUTF8[] fileName;
    private final byte[][] file_bits;
    private final int[] file_modtime;
    private int[] file_name;
    private final int[] file_options;
    private final long[] file_size;
    private final PackingOptions options;

    public FileBands(CpBands cpBands, SegmentHeader segmentHeader, PackingOptions packingOptions, Archive.SegmentUnit segmentUnit, int i) {
        super(i, segmentHeader);
        List fileList = segmentUnit.getFileList();
        this.fileList = fileList;
        this.options = packingOptions;
        this.cpBands = cpBands;
        int size = fileList.size();
        this.fileName = new CPUTF8[size];
        this.file_modtime = new int[size];
        this.file_size = new long[size];
        this.file_options = new int[size];
        this.file_bits = new byte[size][];
        int archive_modtime = segmentHeader.getArchive_modtime();
        HashSet hashSet = new HashSet();
        Iterator it = segmentUnit.getClassList().iterator();
        while (it.hasNext()) {
            hashSet.add(((ClassReader) it.next()).getClassName());
        }
        CPUTF8 cPUtf8 = cpBands.getCPUtf8("");
        boolean z = !"keep".equals(packingOptions.getModificationTime());
        int i2 = 0;
        int i3 = Integer.MIN_VALUE;
        int i4 = 0;
        while (i4 < size) {
            Archive.PackingFile packingFile = (Archive.PackingFile) this.fileList.get(i4);
            String name = packingFile.getName();
            if (name.endsWith(ClassFileLocator.CLASS_FILE_EXTENSION) && !packingOptions.isPassFile(name)) {
                int[] iArr = this.file_options;
                iArr[i4] = iArr[i4] | 2;
                if (hashSet.contains(name.substring(i2, name.length() - 6))) {
                    this.fileName[i4] = cPUtf8;
                } else {
                    this.fileName[i4] = cpBands.getCPUtf8(name);
                }
            } else {
                this.fileName[i4] = cpBands.getCPUtf8(name);
            }
            if (packingOptions.isKeepDeflateHint() && packingFile.isDefalteHint()) {
                int[] iArr2 = this.file_options;
                iArr2[i4] = iArr2[i4] | 1;
            }
            this.file_size[i4] = packingFile.getContents().length;
            int modtime = (int) (((packingFile.getModtime() + ((long) TimeZone.getDefault().getRawOffset())) / 1000) - ((long) archive_modtime));
            this.file_modtime[i4] = modtime;
            i3 = i3;
            if (z && i3 < modtime) {
                i3 = modtime;
            }
            this.file_bits[i4] = packingFile.getContents();
            i4++;
            i2 = 0;
        }
        if (z) {
            for (int i5 = 0; i5 < size; i5++) {
                this.file_modtime[i5] = i3;
            }
        }
    }

    public void finaliseBands() {
        this.file_name = new int[this.fileName.length];
        for (int i = 0; i < this.file_name.length; i++) {
            if (this.fileName[i].equals(this.cpBands.getCPUtf8(""))) {
                String name = ((Archive.PackingFile) this.fileList.get(i)).getName();
                if (this.options.isPassFile(name)) {
                    this.fileName[i] = this.cpBands.getCPUtf8(name);
                    int[] iArr = this.file_options;
                    iArr[i] = iArr[i] & (-3);
                }
            }
            this.file_name[i] = this.fileName[i].getIndex();
        }
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
        PackingUtils.log("Writing file bands...");
        byte[] bArrEncodeBandInt = encodeBandInt("file_name", this.file_name, Codec.UNSIGNED5);
        outputStream.write(bArrEncodeBandInt);
        PackingUtils.log("Wrote " + bArrEncodeBandInt.length + " bytes from file_name[" + this.file_name.length + "]");
        byte[] bArrEncodeFlags = encodeFlags("file_size", this.file_size, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_file_size_hi());
        outputStream.write(bArrEncodeFlags);
        PackingUtils.log("Wrote " + bArrEncodeFlags.length + " bytes from file_size[" + this.file_size.length + "]");
        if (this.segmentHeader.have_file_modtime()) {
            byte[] bArrEncodeBandInt2 = encodeBandInt("file_modtime", this.file_modtime, Codec.DELTA5);
            outputStream.write(bArrEncodeBandInt2);
            PackingUtils.log("Wrote " + bArrEncodeBandInt2.length + " bytes from file_modtime[" + this.file_modtime.length + "]");
        }
        if (this.segmentHeader.have_file_options()) {
            byte[] bArrEncodeBandInt3 = encodeBandInt("file_options", this.file_options, Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt3);
            PackingUtils.log("Wrote " + bArrEncodeBandInt3.length + " bytes from file_options[" + this.file_options.length + "]");
        }
        byte[] bArrEncodeBandInt4 = encodeBandInt("file_bits", flatten(this.file_bits), Codec.BYTE1);
        outputStream.write(bArrEncodeBandInt4);
        PackingUtils.log("Wrote " + bArrEncodeBandInt4.length + " bytes from file_bits[" + this.file_bits.length + "]");
    }

    private int[] flatten(byte[][] bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        int[] iArr = new int[length];
        int i = 0;
        for (byte[] bArr3 : bArr) {
            int i2 = 0;
            while (true) {
                if (i2 < bArr3.length) {
                    iArr[i] = bArr3[i2] & 255;
                    i2++;
                    i++;
                }
            }
        }
        return iArr;
    }
}
