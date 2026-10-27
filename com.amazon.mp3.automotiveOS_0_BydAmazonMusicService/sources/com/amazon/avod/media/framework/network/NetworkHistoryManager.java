package com.amazon.avod.media.framework.network;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface NetworkHistoryManager {
    @Nullable
    NetworkHistory getCurrentNetworkHistory();

    String getCurrentNetworkName();

    void setNetworkInfo(int i, @Nullable ByteBuffer byteBuffer, long j, long j2);
}
