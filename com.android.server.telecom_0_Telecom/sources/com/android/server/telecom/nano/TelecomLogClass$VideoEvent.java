package com.android.server.telecom.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomLogClass$VideoEvent extends MessageNano {
    private static volatile TelecomLogClass$VideoEvent[] _emptyArray;
    private int bitField0_;
    private int eventName_;
    private long timeSinceLastEventMillis_;
    private int videoState_;

    public static TelecomLogClass$VideoEvent[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TelecomLogClass$VideoEvent[0];
                }
            }
        }
        return _emptyArray;
    }

    public int getEventName() {
        return this.eventName_;
    }

    public TelecomLogClass$VideoEvent setEventName(int i) {
        this.eventName_ = i;
        this.bitField0_ |= 1;
        return this;
    }

    public long getTimeSinceLastEventMillis() {
        return this.timeSinceLastEventMillis_;
    }

    public TelecomLogClass$VideoEvent setTimeSinceLastEventMillis(long j) {
        this.timeSinceLastEventMillis_ = j;
        this.bitField0_ |= 2;
        return this;
    }

    public int getVideoState() {
        return this.videoState_;
    }

    public TelecomLogClass$VideoEvent setVideoState(int i) {
        this.videoState_ = i;
        this.bitField0_ |= 4;
        return this;
    }

    public TelecomLogClass$VideoEvent() {
        clear();
    }

    public TelecomLogClass$VideoEvent clear() {
        this.bitField0_ = 0;
        this.eventName_ = 9999;
        this.timeSinceLastEventMillis_ = 0L;
        this.videoState_ = 0;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if ((this.bitField0_ & 1) != 0) {
            codedOutputByteBufferNano.writeInt32(1, this.eventName_);
        }
        if ((this.bitField0_ & 2) != 0) {
            codedOutputByteBufferNano.writeInt64(2, this.timeSinceLastEventMillis_);
        }
        if ((this.bitField0_ & 4) != 0) {
            codedOutputByteBufferNano.writeInt32(3, this.videoState_);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.google.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        if ((this.bitField0_ & 1) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, this.eventName_);
        }
        if ((this.bitField0_ & 2) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(2, this.timeSinceLastEventMillis_);
        }
        return (this.bitField0_ & 4) != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(3, this.videoState_) : iComputeSerializedSize;
    }
}
