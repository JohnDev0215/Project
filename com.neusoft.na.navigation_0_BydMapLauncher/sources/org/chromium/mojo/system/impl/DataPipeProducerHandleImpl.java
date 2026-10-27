package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.DataPipe;
import org.chromium.mojo.system.ResultAnd;

/* JADX INFO: loaded from: classes3.dex */
class DataPipeProducerHandleImpl extends HandleBase implements DataPipe.ProducerHandle {
    DataPipeProducerHandleImpl(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    DataPipeProducerHandleImpl(HandleBase handleBase) {
        super(handleBase);
    }

    @Override // org.chromium.mojo.system.Handle
    public DataPipe.ProducerHandle pass() {
        return new DataPipeProducerHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.DataPipe.ProducerHandle
    public ResultAnd<Integer> writeData(ByteBuffer byteBuffer, DataPipe.WriteFlags writeFlags) {
        return this.mCore.writeData(this, byteBuffer, writeFlags);
    }

    @Override // org.chromium.mojo.system.DataPipe.ProducerHandle
    public ByteBuffer beginWriteData(int i, DataPipe.WriteFlags writeFlags) {
        return this.mCore.beginWriteData(this, i, writeFlags);
    }

    @Override // org.chromium.mojo.system.DataPipe.ProducerHandle
    public void endWriteData(int i) {
        this.mCore.endWriteData(this, i);
    }
}
