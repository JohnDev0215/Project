package com.android.server.telecom.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomLogClass$LogSessionTiming extends MessageNano {
    private static volatile TelecomLogClass$LogSessionTiming[] _emptyArray;
    private int bitField0_;
    private int sessionEntryPoint_;
    private long timeMillis_;

    public static TelecomLogClass$LogSessionTiming[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TelecomLogClass$LogSessionTiming[0];
                }
            }
        }
        return _emptyArray;
    }

    public TelecomLogClass$LogSessionTiming setSessionEntryPoint(int i) {
        this.sessionEntryPoint_ = i;
        this.bitField0_ |= 1;
        return this;
    }

    public TelecomLogClass$LogSessionTiming setTimeMillis(long j) {
        this.timeMillis_ = j;
        this.bitField0_ |= 2;
        return this;
    }

    public TelecomLogClass$LogSessionTiming() {
        clear();
    }

    public TelecomLogClass$LogSessionTiming clear() {
        this.bitField0_ = 0;
        this.sessionEntryPoint_ = 0;
        this.timeMillis_ = 0L;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if ((this.bitField0_ & 1) != 0) {
            codedOutputByteBufferNano.writeInt32(1, this.sessionEntryPoint_);
        }
        if ((this.bitField0_ & 2) != 0) {
            codedOutputByteBufferNano.writeInt64(2, this.timeMillis_);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.google.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        if ((this.bitField0_ & 1) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, this.sessionEntryPoint_);
        }
        return (this.bitField0_ & 2) != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt64Size(2, this.timeMillis_) : iComputeSerializedSize;
    }
}
