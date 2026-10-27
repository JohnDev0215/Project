package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface Mp4FragmentParser {

    public interface EventMessageListener {
        void onEventMessage(@Nonnull Collection<EventMessage> collection);
    }

    void addEventMessageListener(@Nonnull EventMessageListener eventMessageListener);

    @Nonnull
    Fragment parse(ByteBuffer byteBuffer, boolean z) throws ContentException;

    @Nonnull
    Fragment parse(byte[] bArr, int i, int i2, boolean z, @Nonnull DirectBuffer directBuffer) throws ContentException;

    void release();

    void removeEventMessageListener(@Nonnull EventMessageListener eventMessageListener);

    boolean validate(ByteBuffer byteBuffer, boolean z) throws ContentException;

    boolean validate(byte[] bArr, int i, int i2, boolean z, boolean z2, DirectBuffer directBuffer) throws ContentException;
}
