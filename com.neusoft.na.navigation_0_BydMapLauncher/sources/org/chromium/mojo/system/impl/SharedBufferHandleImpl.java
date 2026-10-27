package org.chromium.mojo.system.impl;

import java.nio.ByteBuffer;
import org.chromium.mojo.system.SharedBufferHandle;

/* JADX INFO: loaded from: classes3.dex */
class SharedBufferHandleImpl extends HandleBase implements SharedBufferHandle {
    SharedBufferHandleImpl(CoreImpl coreImpl, int i) {
        super(coreImpl, i);
    }

    SharedBufferHandleImpl(HandleBase handleBase) {
        super(handleBase);
    }

    @Override // org.chromium.mojo.system.Handle
    public SharedBufferHandle pass() {
        return new SharedBufferHandleImpl(this);
    }

    @Override // org.chromium.mojo.system.SharedBufferHandle
    public SharedBufferHandle duplicate(SharedBufferHandle.DuplicateOptions duplicateOptions) {
        return this.mCore.duplicate(this, duplicateOptions);
    }

    @Override // org.chromium.mojo.system.SharedBufferHandle
    public ByteBuffer map(long j, long j2, SharedBufferHandle.MapFlags mapFlags) {
        return this.mCore.map(this, j, j2, mapFlags);
    }

    @Override // org.chromium.mojo.system.SharedBufferHandle
    public void unmap(ByteBuffer byteBuffer) {
        this.mCore.unmap(byteBuffer);
    }
}
