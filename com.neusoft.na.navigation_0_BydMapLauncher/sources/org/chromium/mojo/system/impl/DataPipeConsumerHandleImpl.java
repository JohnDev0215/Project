package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: loaded from: classes3.dex */
class DataPipeConsumerHandleImpl extends HandleBase implements DataPipe.ConsumerHandle {
    DataPipeConsumerHandleImpl(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    DataPipeConsumerHandleImpl(HandleBase handleBase) {
        super(handleBase);
    }

    @Override // org.chromium.mojo.system.Handle
    public DataPipe.ConsumerHandle pass() {
        return new DataPipeConsumerHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public int discardData(int i, DataPipe.ReadFlags readFlags) {
        return this.mCore.discardData(this, i, readFlags);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public ResultAnd<Integer> readData(ByteBuffer byteBuffer, DataPipe.ReadFlags readFlags) {
        return this.mCore.readData(this, byteBuffer, readFlags);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public ByteBuffer beginReadData(int i, DataPipe.ReadFlags readFlags) {
        return this.mCore.beginReadData(this, i, readFlags);
    }

    @Override // org.chromium.mojo.system.DataPipe.ConsumerHandle
    public void endReadData(int i) {
        this.mCore.endReadData(this, i);
    }
}
