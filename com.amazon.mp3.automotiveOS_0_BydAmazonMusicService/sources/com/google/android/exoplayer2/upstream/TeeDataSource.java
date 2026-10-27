package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public final class TeeDataSource implements DataSource {
    private final DataSink dataSink;
    private final DataSource upstream;

    public TeeDataSource(DataSource dataSource, DataSink dataSink) {
        this.upstream = (DataSource) Assertions.checkNotNull(dataSource);
        this.dataSink = (DataSink) Assertions.checkNotNull(dataSink);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        long jOpen = this.upstream.open(dataSpec);
        if (dataSpec.length == -1 && jOpen != -1) {
            dataSpec = new DataSpec(dataSpec.uri, dataSpec.absoluteStreamPosition, dataSpec.position, jOpen, dataSpec.key, dataSpec.flags);
        }
        this.dataSink.open(dataSpec);
        return jOpen;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.upstream.read(bArr, i, i2);
        if (i3 > 0) {
            this.dataSink.write(bArr, i, i3);
        }
        return i3;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return this.upstream.getUri();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
        try {
            this.upstream.close();
        } finally {
            this.dataSink.close();
        }
    }
}
