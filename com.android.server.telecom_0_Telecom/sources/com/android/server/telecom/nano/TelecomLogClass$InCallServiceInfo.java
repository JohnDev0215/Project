package com.android.server.telecom.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomLogClass$InCallServiceInfo extends MessageNano {
    private static volatile TelecomLogClass$InCallServiceInfo[] _emptyArray;
    private int bitField0_;
    private long boundDurationMillis_;
    private String inCallServiceName_;
    private int inCallServiceType_;
    private boolean isNullBinding_;

    public static TelecomLogClass$InCallServiceInfo[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TelecomLogClass$InCallServiceInfo[0];
                }
            }
        }
        return _emptyArray;
    }

    public String getInCallServiceName() {
        return this.inCallServiceName_;
    }

    public TelecomLogClass$InCallServiceInfo setInCallServiceName(String str) {
        if (str == null) {
            throw null;
        }
        this.inCallServiceName_ = str;
        this.bitField0_ |= 1;
        return this;
    }

    public int getInCallServiceType() {
        return this.inCallServiceType_;
    }

    public TelecomLogClass$InCallServiceInfo setInCallServiceType(int i) {
        this.inCallServiceType_ = i;
        this.bitField0_ |= 2;
        return this;
    }

    public long getBoundDurationMillis() {
        return this.boundDurationMillis_;
    }

    public TelecomLogClass$InCallServiceInfo setBoundDurationMillis(long j) {
        this.boundDurationMillis_ = j;
        this.bitField0_ |= 4;
        return this;
    }

    public boolean getIsNullBinding() {
        return this.isNullBinding_;
    }

    public TelecomLogClass$InCallServiceInfo setIsNullBinding(boolean z) {
        this.isNullBinding_ = z;
        this.bitField0_ |= 8;
        return this;
    }

    public TelecomLogClass$InCallServiceInfo() {
        clear();
    }

    public TelecomLogClass$InCallServiceInfo clear() {
        this.bitField0_ = 0;
        this.inCallServiceName_ = "";
        this.inCallServiceType_ = 9999;
        this.boundDurationMillis_ = 0L;
        this.isNullBinding_ = false;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if ((this.bitField0_ & 1) != 0) {
            codedOutputByteBufferNano.writeString(1, this.inCallServiceName_);
        }
        if ((this.bitField0_ & 2) != 0) {
            codedOutputByteBufferNano.writeInt32(2, this.inCallServiceType_);
        }
        if ((this.bitField0_ & 4) != 0) {
            codedOutputByteBufferNano.writeInt64(3, this.boundDurationMillis_);
        }
        if ((this.bitField0_ & 8) != 0) {
            codedOutputByteBufferNano.writeBool(4, this.isNullBinding_);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.google.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        if ((this.bitField0_ & 1) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.inCallServiceName_);
        }
        if ((this.bitField0_ & 2) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(2, this.inCallServiceType_);
        }
        if ((this.bitField0_ & 4) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(3, this.boundDurationMillis_);
        }
        return (this.bitField0_ & 8) != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeBoolSize(4, this.isNullBinding_) : iComputeSerializedSize;
    }
}
