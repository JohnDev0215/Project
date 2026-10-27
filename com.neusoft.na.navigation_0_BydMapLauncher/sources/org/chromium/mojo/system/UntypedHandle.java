package org.chromium.mojo.system;

import org.chromium.mojo.system.DataPipe;

/* JADX INFO: loaded from: classes3.dex */
public interface UntypedHandle extends Handle {
    @Override // org.chromium.mojo.system.Handle
    UntypedHandle pass();

    DataPipe.ConsumerHandle toDataPipeConsumerHandle();

    DataPipe.ProducerHandle toDataPipeProducerHandle();

    MessagePipeHandle toMessagePipeHandle();

    SharedBufferHandle toSharedBufferHandle();
}
