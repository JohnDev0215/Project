package org.tukaani.xz.index;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.zip.CheckedInputStream;
import org.tukaani.xz.CorruptedInputException;
import org.tukaani.xz.XZIOException;
import org.tukaani.xz.check.CRC32;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.check.SHA256;
import org.tukaani.xz.common.DecoderUtil;

/* JADX INFO: loaded from: classes2.dex */
public class IndexHash extends IndexBase {
    private Check hash;

    @Override // org.tukaani.xz.index.IndexBase
    public /* bridge */ /* synthetic */ long getIndexSize() {
        return super.getIndexSize();
    }

    @Override // org.tukaani.xz.index.IndexBase
    public /* bridge */ /* synthetic */ long getStreamSize() {
        return super.getStreamSize();
    }

    public IndexHash() {
        super(new CorruptedInputException());
        try {
            this.hash = new SHA256();
        } catch (NoSuchAlgorithmException e) {
            this.hash = new CRC32();
        }
    }

    @Override // org.tukaani.xz.index.IndexBase
    public void add(long unpaddedSize, long uncompressedSize) throws XZIOException {
        super.add(unpaddedSize, uncompressedSize);
        ByteBuffer buf = ByteBuffer.allocate(16);
        buf.putLong(unpaddedSize);
        buf.putLong(uncompressedSize);
        this.hash.update(buf.array());
    }

    public void validate(InputStream in) throws IOException {
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(0);
        CheckedInputStream inChecked = new CheckedInputStream(in, crc32);
        long storedRecordCount = DecoderUtil.decodeVLI(inChecked);
        if (storedRecordCount != this.recordCount) {
            throw new CorruptedInputException("XZ Block Header or the start of XZ Index is corrupt");
        }
        IndexHash stored = new IndexHash();
        long i = 0;
        while (i < this.recordCount) {
            long unpaddedSize = DecoderUtil.decodeVLI(inChecked);
            long uncompressedSize = DecoderUtil.decodeVLI(inChecked);
            try {
                stored.add(unpaddedSize, uncompressedSize);
                long storedRecordCount2 = storedRecordCount;
                long storedRecordCount3 = stored.blocksSum;
                long unpaddedSize2 = this.blocksSum;
                if (storedRecordCount3 <= unpaddedSize2 && stored.uncompressedSum <= this.uncompressedSum && stored.indexListSize <= this.indexListSize) {
                    i++;
                    storedRecordCount = storedRecordCount2;
                } else {
                    throw new CorruptedInputException("XZ Index is corrupt");
                }
            } catch (XZIOException e) {
                throw new CorruptedInputException("XZ Index is corrupt");
            }
        }
        if (stored.blocksSum != this.blocksSum || stored.uncompressedSum != this.uncompressedSum || stored.indexListSize != this.indexListSize || !Arrays.equals(stored.hash.finish(), this.hash.finish())) {
            throw new CorruptedInputException("XZ Index is corrupt");
        }
        DataInputStream inData = new DataInputStream(inChecked);
        for (int i2 = getIndexPaddingSize(); i2 > 0; i2--) {
            if (inData.readUnsignedByte() != 0) {
                throw new CorruptedInputException("XZ Index is corrupt");
            }
        }
        long value = crc32.getValue();
        for (int i3 = 0; i3 < 4; i3++) {
            if (((value >>> (i3 * 8)) & 255) != inData.readUnsignedByte()) {
                throw new CorruptedInputException("XZ Index is corrupt");
            }
        }
    }
}
