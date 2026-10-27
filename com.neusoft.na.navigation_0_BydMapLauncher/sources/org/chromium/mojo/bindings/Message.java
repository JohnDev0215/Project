package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.mojo.system.Handle;

/* JADX INFO: loaded from: classes3.dex */
public class Message {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ByteBuffer mBuffer;
    private final List<? extends Handle> mHandle;
    private ServiceMessage mWithHeader = null;

    public Message(ByteBuffer byteBuffer, List<? extends Handle> list) {
        this.mBuffer = byteBuffer;
        this.mHandle = list;
    }

    public ByteBuffer getData() {
        return this.mBuffer;
    }

    public List<? extends Handle> getHandles() {
        return this.mHandle;
    }

    public ServiceMessage asServiceMessage() {
        if (this.mWithHeader == null) {
            this.mWithHeader = new ServiceMessage(this);
        }
        return this.mWithHeader;
    }
}
