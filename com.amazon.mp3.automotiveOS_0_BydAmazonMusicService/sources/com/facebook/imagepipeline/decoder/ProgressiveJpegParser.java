package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteArrayBufferedInputStream;
import com.facebook.common.util.StreamUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
public class ProgressiveJpegParser {
    private final ByteArrayPool mByteArrayPool;
    private boolean mEndMarkerRead;
    private int mBytesParsed = 0;
    private int mLastByteRead = 0;
    private int mNextFullScanNumber = 0;
    private int mBestScanEndOffset = 0;
    private int mBestScanNumber = 0;
    private int mParserState = 0;

    private static boolean doesMarkerStartSegment(int markerSecondByte) {
        if (markerSecondByte == 1) {
            return false;
        }
        if (markerSecondByte < 208 || markerSecondByte > 215) {
            return (markerSecondByte == 217 || markerSecondByte == 216) ? false : true;
        }
        return false;
    }

    public ProgressiveJpegParser(ByteArrayPool byteArrayPool) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
    }

    public boolean parseMoreData(final EncodedImage encodedImage) {
        if (this.mParserState == 6 || encodedImage.getSize() <= this.mBytesParsed) {
            return false;
        }
        PooledByteArrayBufferedInputStream pooledByteArrayBufferedInputStream = new PooledByteArrayBufferedInputStream(encodedImage.getInputStreamOrThrow(), this.mByteArrayPool.get(16384), this.mByteArrayPool);
        try {
            StreamUtil.skip(pooledByteArrayBufferedInputStream, this.mBytesParsed);
            return doParseMoreData(pooledByteArrayBufferedInputStream);
        } catch (IOException e) {
            Throwables.propagate(e);
            return false;
        } finally {
            Closeables.closeQuietly(pooledByteArrayBufferedInputStream);
        }
    }

    private boolean doParseMoreData(final InputStream inputStream) throws Throwable {
        int i;
        int i2 = this.mBestScanNumber;
        while (this.mParserState != 6 && (i = inputStream.read()) != -1) {
            try {
                int i3 = this.mBytesParsed + 1;
                this.mBytesParsed = i3;
                if (this.mEndMarkerRead) {
                    this.mParserState = 6;
                    this.mEndMarkerRead = false;
                    return false;
                }
                int i4 = this.mParserState;
                if (i4 != 0) {
                    if (i4 != 1) {
                        if (i4 != 2) {
                            if (i4 != 3) {
                                if (i4 == 4) {
                                    this.mParserState = 5;
                                } else if (i4 == 5) {
                                    int i5 = ((this.mLastByteRead << 8) + i) - 2;
                                    StreamUtil.skip(inputStream, i5);
                                    this.mBytesParsed += i5;
                                    this.mParserState = 2;
                                } else {
                                    Preconditions.checkState(false);
                                }
                            } else if (i == 255) {
                                this.mParserState = 3;
                            } else if (i == 0) {
                                this.mParserState = 2;
                            } else if (i == 217) {
                                this.mEndMarkerRead = true;
                                newScanOrImageEndFound(i3 - 2);
                                this.mParserState = 2;
                            } else {
                                if (i == 218) {
                                    newScanOrImageEndFound(i3 - 2);
                                }
                                if (doesMarkerStartSegment(i)) {
                                    this.mParserState = 4;
                                } else {
                                    this.mParserState = 2;
                                }
                            }
                        } else if (i == 255) {
                            this.mParserState = 3;
                        }
                    } else if (i == 216) {
                        this.mParserState = 2;
                    } else {
                        this.mParserState = 6;
                    }
                } else if (i == 255) {
                    this.mParserState = 1;
                } else {
                    this.mParserState = 6;
                }
                this.mLastByteRead = i;
            } catch (IOException e) {
                Throwables.propagate(e);
            }
            return (this.mParserState == 6 || this.mBestScanNumber == i2) ? false : true;
        }
        if (this.mParserState == 6) {
            return false;
        }
    }

    private void newScanOrImageEndFound(int offset) {
        int i = this.mNextFullScanNumber;
        if (i > 0) {
            this.mBestScanEndOffset = offset;
        }
        this.mNextFullScanNumber = i + 1;
        this.mBestScanNumber = i;
    }

    public int getBestScanEndOffset() {
        return this.mBestScanEndOffset;
    }

    public int getBestScanNumber() {
        return this.mBestScanNumber;
    }

    public boolean isEndMarkerRead() {
        return this.mEndMarkerRead;
    }
}
