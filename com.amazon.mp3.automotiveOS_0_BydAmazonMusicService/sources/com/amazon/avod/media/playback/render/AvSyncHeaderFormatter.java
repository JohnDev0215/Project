package com.amazon.avod.media.playback.render;

import com.amazon.avod.media.TimeSpan;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class AvSyncHeaderFormatter {
    private final ByteBuffer mAvSyncHeader;
    private final int mAvSyncHeaderSize;
    private final int mBuildVersion;

    AvSyncHeaderFormatter(int i) {
        this.mBuildVersion = i;
        int i2 = i >= 28 ? 20 : 16;
        this.mAvSyncHeaderSize = i2;
        this.mAvSyncHeader = ByteBuffer.allocate(i2);
    }

    void setAvSyncHeader(int i, long j) {
        if (this.mBuildVersion >= 28) {
            setAvSyncHeaderV2(i, j);
        } else {
            setAvSyncHeaderV1(i, j);
        }
    }

    private void setAvSyncHeaderV1(int i, long j) {
        this.mAvSyncHeader.clear();
        this.mAvSyncHeader.order(ByteOrder.BIG_ENDIAN);
        this.mAvSyncHeader.putInt(1431633921);
        this.mAvSyncHeader.putInt(i);
        this.mAvSyncHeader.putLong(TimeSpan.fromMicroseconds(j).getTotalNanoSeconds());
        this.mAvSyncHeader.clear();
    }

    private void setAvSyncHeaderV2(int i, long j) {
        this.mAvSyncHeader.clear();
        this.mAvSyncHeader.order(ByteOrder.BIG_ENDIAN);
        this.mAvSyncHeader.putInt(1431633922);
        this.mAvSyncHeader.putInt(i);
        this.mAvSyncHeader.putLong(TimeSpan.fromMicroseconds(j).getTotalNanoSeconds());
        this.mAvSyncHeader.putInt(20);
        this.mAvSyncHeader.clear();
    }

    @Nonnull
    ByteBuffer getAvSyncHeader() {
        return this.mAvSyncHeader;
    }

    int getAvSyncHeaderSize() {
        return this.mAvSyncHeaderSize;
    }
}
