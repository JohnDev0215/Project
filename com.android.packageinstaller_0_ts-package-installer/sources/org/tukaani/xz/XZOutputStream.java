package org.tukaani.xz;

import java.io.IOException;
import java.io.OutputStream;
import org.tukaani.xz.check.Check;
import org.tukaani.xz.common.EncoderUtil;
import org.tukaani.xz.common.StreamFlags;
import org.tukaani.xz.index.IndexEncoder;

/* JADX INFO: loaded from: classes2.dex */
public class XZOutputStream extends FinishableOutputStream {
    private final ArrayCache arrayCache;
    private BlockOutputStream blockEncoder;
    private final Check check;
    private IOException exception;
    private FilterEncoder[] filters;
    private boolean filtersSupportFlushing;
    private boolean finished;
    private final IndexEncoder index;
    private OutputStream out;
    private final StreamFlags streamFlags;
    private final byte[] tempBuf;

    public XZOutputStream(OutputStream out, FilterOptions filterOptions) throws IOException {
        this(out, filterOptions, 4);
    }

    public XZOutputStream(OutputStream out, FilterOptions filterOptions, ArrayCache arrayCache) throws IOException {
        this(out, filterOptions, 4, arrayCache);
    }

    public XZOutputStream(OutputStream out, FilterOptions filterOptions, int checkType) throws IOException {
        this(out, new FilterOptions[]{filterOptions}, checkType);
    }

    public XZOutputStream(OutputStream out, FilterOptions filterOptions, int checkType, ArrayCache arrayCache) throws IOException {
        this(out, new FilterOptions[]{filterOptions}, checkType, arrayCache);
    }

    public XZOutputStream(OutputStream out, FilterOptions[] filterOptions) throws IOException {
        this(out, filterOptions, 4);
    }

    public XZOutputStream(OutputStream out, FilterOptions[] filterOptions, ArrayCache arrayCache) throws IOException {
        this(out, filterOptions, 4, arrayCache);
    }

    public XZOutputStream(OutputStream out, FilterOptions[] filterOptions, int checkType) throws IOException {
        this(out, filterOptions, checkType, ArrayCache.getDefaultCache());
    }

    public XZOutputStream(OutputStream out, FilterOptions[] filterOptions, int checkType, ArrayCache arrayCache) throws IOException {
        this.streamFlags = new StreamFlags();
        this.index = new IndexEncoder();
        this.blockEncoder = null;
        this.exception = null;
        this.finished = false;
        this.tempBuf = new byte[1];
        this.arrayCache = arrayCache;
        this.out = out;
        updateFilters(filterOptions);
        this.streamFlags.checkType = checkType;
        this.check = Check.getInstance(checkType);
        encodeStreamHeader();
    }

    public void updateFilters(FilterOptions filterOptions) throws XZIOException {
        FilterOptions[] opts = {filterOptions};
        updateFilters(opts);
    }

    public void updateFilters(FilterOptions[] filterOptions) throws XZIOException {
        if (this.blockEncoder != null) {
            throw new UnsupportedOptionsException("Changing filter options in the middle of a XZ Block not implemented");
        }
        if (filterOptions.length < 1 || filterOptions.length > 4) {
            throw new UnsupportedOptionsException("XZ filter chain must be 1-4 filters");
        }
        this.filtersSupportFlushing = true;
        FilterEncoder[] newFilters = new FilterEncoder[filterOptions.length];
        for (int i = 0; i < filterOptions.length; i++) {
            newFilters[i] = filterOptions[i].getFilterEncoder();
            this.filtersSupportFlushing &= newFilters[i].supportsFlushing();
        }
        RawCoder.validate(newFilters);
        this.filters = newFilters;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        byte[] bArr = this.tempBuf;
        bArr[0] = (byte) b;
        write(bArr, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        if (off < 0 || len < 0 || off + len < 0 || off + len > buf.length) {
            throw new IndexOutOfBoundsException();
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        try {
            if (this.blockEncoder == null) {
                this.blockEncoder = new BlockOutputStream(this.out, this.filters, this.check, this.arrayCache);
            }
            this.blockEncoder.write(buf, off, len);
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    public void endBlock() throws IOException {
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        BlockOutputStream blockOutputStream = this.blockEncoder;
        if (blockOutputStream != null) {
            try {
                blockOutputStream.finish();
                this.index.add(this.blockEncoder.getUnpaddedSize(), this.blockEncoder.getUncompressedSize());
                this.blockEncoder = null;
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
        if (this.finished) {
            throw new XZIOException("Stream finished or closed");
        }
        try {
            if (this.blockEncoder != null) {
                if (this.filtersSupportFlushing) {
                    this.blockEncoder.flush();
                    return;
                } else {
                    endBlock();
                    this.out.flush();
                    return;
                }
            }
            this.out.flush();
        } catch (IOException e) {
            this.exception = e;
            throw e;
        }
    }

    @Override // org.tukaani.xz.FinishableOutputStream
    public void finish() throws IOException {
        if (!this.finished) {
            endBlock();
            try {
                this.index.encode(this.out);
                encodeStreamFooter();
                this.finished = true;
            } catch (IOException e) {
                this.exception = e;
                throw e;
            }
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.out != null) {
            try {
                finish();
            } catch (IOException e) {
            }
            try {
                this.out.close();
            } catch (IOException e2) {
                if (this.exception == null) {
                    this.exception = e2;
                }
            }
            this.out = null;
        }
        IOException iOException = this.exception;
        if (iOException != null) {
            throw iOException;
        }
    }

    private void encodeStreamFlags(byte[] buf, int off) {
        buf[off] = 0;
        buf[off + 1] = (byte) this.streamFlags.checkType;
    }

    private void encodeStreamHeader() throws IOException {
        this.out.write(XZ.HEADER_MAGIC);
        byte[] buf = new byte[2];
        encodeStreamFlags(buf, 0);
        this.out.write(buf);
        EncoderUtil.writeCRC32(this.out, buf);
    }

    private void encodeStreamFooter() throws IOException {
        byte[] buf = new byte[6];
        long backwardSize = (this.index.getIndexSize() / 4) - 1;
        for (int i = 0; i < 4; i++) {
            buf[i] = (byte) (backwardSize >>> (i * 8));
        }
        encodeStreamFlags(buf, 4);
        EncoderUtil.writeCRC32(this.out, buf);
        this.out.write(buf);
        this.out.write(XZ.FOOTER_MAGIC);
    }
}
