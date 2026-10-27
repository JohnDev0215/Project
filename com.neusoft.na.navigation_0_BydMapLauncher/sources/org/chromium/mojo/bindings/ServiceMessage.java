package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceMessage extends Message {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final MessageHeader mHeader;
    private Message mPayload;

    @Override // org.chromium.mojo.bindings.Message
    public ServiceMessage asServiceMessage() {
        return this;
    }

    public ServiceMessage(Message message, MessageHeader messageHeader) {
        super(message.getData(), message.getHandles());
        this.mHeader = messageHeader;
    }

    ServiceMessage(Message message) {
        this(message, new MessageHeader(message));
    }

    public MessageHeader getHeader() {
        return this.mHeader;
    }

    public Message getPayload() {
        if (this.mPayload == null) {
            ByteBuffer byteBufferSlice = ((ByteBuffer) getData().position(getHeader().getSize())).slice();
            byteBufferSlice.order(ByteOrder.LITTLE_ENDIAN);
            this.mPayload = new Message(byteBufferSlice, getHandles());
        }
        return this.mPayload;
    }

    void setRequestId(long j) {
        this.mHeader.setRequestId(getData(), j);
    }
}
