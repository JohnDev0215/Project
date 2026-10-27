package com.google.android.exoplayer2.upstream;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes6.dex */
public final class ContentDataSource implements DataSource {
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener<? super ContentDataSource> listener;
    private boolean opened;
    private final ContentResolver resolver;
    private Uri uri;

    public static class ContentDataSourceException extends IOException {
        public ContentDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public ContentDataSource(Context context, TransferListener<? super ContentDataSource> transferListener) {
        this.resolver = context.getContentResolver();
        this.listener = transferListener;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws ContentDataSourceException {
        try {
            Uri uri = dataSpec.uri;
            this.uri = uri;
            AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = this.resolver.openAssetFileDescriptor(uri, "r");
            this.assetFileDescriptor = assetFileDescriptorOpenAssetFileDescriptor;
            if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                throw new FileNotFoundException("Could not open file descriptor for: " + this.uri);
            }
            this.inputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
            long startOffset = this.assetFileDescriptor.getStartOffset();
            long jSkip = this.inputStream.skip(dataSpec.position + startOffset) - startOffset;
            if (jSkip != dataSpec.position) {
                throw new EOFException();
            }
            long j = dataSpec.length;
            if (j != -1) {
                this.bytesRemaining = j;
            } else {
                long length = this.assetFileDescriptor.getLength();
                if (length == -1) {
                    long jAvailable = this.inputStream.available();
                    this.bytesRemaining = jAvailable;
                    if (jAvailable == 0) {
                        this.bytesRemaining = -1L;
                    }
                } else {
                    this.bytesRemaining = length - jSkip;
                }
            }
            this.opened = true;
            TransferListener<? super ContentDataSource> transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferStart(this, dataSpec);
            }
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new ContentDataSourceException(e);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws ContentDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, i2);
            } catch (IOException e) {
                throw new ContentDataSourceException(e);
            }
        }
        int i3 = this.inputStream.read(bArr, i, i2);
        if (i3 == -1) {
            if (this.bytesRemaining == -1) {
                return -1;
            }
            throw new ContentDataSourceException(new EOFException());
        }
        long j2 = this.bytesRemaining;
        if (j2 != -1) {
            this.bytesRemaining = j2 - ((long) i3);
        }
        TransferListener<? super ContentDataSource> transferListener = this.listener;
        if (transferListener != null) {
            transferListener.onBytesTransferred(this, i3);
        }
        return i3;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws ContentDataSourceException {
        this.uri = null;
        try {
            try {
                InputStream inputStream = this.inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                this.inputStream = null;
                try {
                    try {
                        AssetFileDescriptor assetFileDescriptor = this.assetFileDescriptor;
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                    } catch (IOException e) {
                        throw new ContentDataSourceException(e);
                    }
                } finally {
                    this.assetFileDescriptor = null;
                    if (this.opened) {
                        this.opened = false;
                        TransferListener<? super ContentDataSource> transferListener = this.listener;
                        if (transferListener != null) {
                            transferListener.onTransferEnd(this);
                        }
                    }
                }
            } catch (IOException e2) {
                throw new ContentDataSourceException(e2);
            }
        } catch (Throwable th) {
            this.inputStream = null;
            try {
                try {
                    AssetFileDescriptor assetFileDescriptor2 = this.assetFileDescriptor;
                    if (assetFileDescriptor2 != null) {
                        assetFileDescriptor2.close();
                    }
                    this.assetFileDescriptor = null;
                    if (this.opened) {
                        this.opened = false;
                        TransferListener<? super ContentDataSource> transferListener2 = this.listener;
                        if (transferListener2 != null) {
                            transferListener2.onTransferEnd(this);
                        }
                    }
                    throw th;
                } finally {
                    this.assetFileDescriptor = null;
                    if (this.opened) {
                        this.opened = false;
                        TransferListener<? super ContentDataSource> transferListener3 = this.listener;
                        if (transferListener3 != null) {
                            transferListener3.onTransferEnd(this);
                        }
                    }
                }
            } catch (IOException e3) {
                throw new ContentDataSourceException(e3);
            }
        }
    }
}
