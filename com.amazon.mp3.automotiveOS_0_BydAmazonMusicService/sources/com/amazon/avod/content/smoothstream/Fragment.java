package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Fragment {
    @Nullable
    String getCodecPrivateData();

    @Nullable
    DirectBuffer getDirectBuffer();

    SampleEncryptionInfo getEncryptionInfo(int i);

    @Nonnull
    Collection<EventMessage> getEventMessages();

    long getFragmentPresentationTime();

    long getPresentationTime(int i);

    @Nullable
    ProtectionHeader getPssh(boolean z);

    int getSampleCount();

    ByteBuffer getSampleData(int i);

    long getSampleDuration(int i);

    long getSampleSize(int i);

    void release(@Nullable SurgingResourcePool<DirectBuffer> surgingResourcePool);
}
