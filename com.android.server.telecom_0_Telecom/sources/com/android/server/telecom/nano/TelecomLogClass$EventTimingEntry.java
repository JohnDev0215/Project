package com.android.server.telecom.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomLogClass$EventTimingEntry extends MessageNano {
    private static volatile TelecomLogClass$EventTimingEntry[] _emptyArray;
    private int bitField0_;
    private long timeMillis_;
    private int timingName_;

    public static TelecomLogClass$EventTimingEntry[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TelecomLogClass$EventTimingEntry[0];
                }
            }
        }
        return _emptyArray;
    }

    public int getTimingName() {
        return this.timingName_;
    }

    public TelecomLogClass$EventTimingEntry setTimingName(int i) {
        this.timingName_ = i;
        this.bitField0_ |= 1;
        return this;
    }

    public long getTimeMillis() {
        return this.timeMillis_;
    }

    public TelecomLogClass$EventTimingEntry setTimeMillis(long j) {
        this.timeMillis_ = j;
        this.bitField0_ |= 2;
        return this;
    }

    public TelecomLogClass$EventTimingEntry() {
        clear();
    }

    public TelecomLogClass$EventTimingEntry clear() {
        this.bitField0_ = 0;
        this.timingName_ = 9999;
        this.timeMillis_ = 0L;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if ((this.bitField0_ & 1) != 0) {
            codedOutputByteBufferNano.writeInt32(1, this.timingName_);
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
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, this.timingName_);
        }
        return (this.bitField0_ & 2) != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt64Size(2, this.timeMillis_) : iComputeSerializedSize;
    }
}
