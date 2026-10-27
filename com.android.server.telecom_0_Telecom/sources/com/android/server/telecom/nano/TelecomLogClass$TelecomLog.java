package com.android.server.telecom.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomLogClass$TelecomLog extends MessageNano {
    private int bitField0_;
    public TelecomLogClass$CallLog[] callLogs;
    private int carrierId_;
    private String hardwareRevision_;
    public TelecomLogClass$LogSessionTiming[] sessionTimings;

    public TelecomLogClass$TelecomLog setHardwareRevision(String str) {
        if (str == null) {
            throw null;
        }
        this.hardwareRevision_ = str;
        this.bitField0_ |= 1;
        return this;
    }

    public TelecomLogClass$TelecomLog setCarrierId(int i) {
        this.carrierId_ = i;
        this.bitField0_ |= 2;
        return this;
    }

    public TelecomLogClass$TelecomLog() {
        clear();
    }

    public TelecomLogClass$TelecomLog clear() {
        this.bitField0_ = 0;
        this.callLogs = TelecomLogClass$CallLog.emptyArray();
        this.sessionTimings = TelecomLogClass$LogSessionTiming.emptyArray();
        this.hardwareRevision_ = "";
        this.carrierId_ = 0;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        TelecomLogClass$CallLog[] telecomLogClass$CallLogArr = this.callLogs;
        int i = 0;
        if (telecomLogClass$CallLogArr != null && telecomLogClass$CallLogArr.length > 0) {
            int i2 = 0;
            while (true) {
                TelecomLogClass$CallLog[] telecomLogClass$CallLogArr2 = this.callLogs;
                if (i2 >= telecomLogClass$CallLogArr2.length) {
                    break;
                }
                TelecomLogClass$CallLog telecomLogClass$CallLog = telecomLogClass$CallLogArr2[i2];
                if (telecomLogClass$CallLog != null) {
                    codedOutputByteBufferNano.writeMessage(1, telecomLogClass$CallLog);
                }
                i2++;
            }
        }
        TelecomLogClass$LogSessionTiming[] telecomLogClass$LogSessionTimingArr = this.sessionTimings;
        if (telecomLogClass$LogSessionTimingArr != null && telecomLogClass$LogSessionTimingArr.length > 0) {
            while (true) {
                TelecomLogClass$LogSessionTiming[] telecomLogClass$LogSessionTimingArr2 = this.sessionTimings;
                if (i >= telecomLogClass$LogSessionTimingArr2.length) {
                    break;
                }
                TelecomLogClass$LogSessionTiming telecomLogClass$LogSessionTiming = telecomLogClass$LogSessionTimingArr2[i];
                if (telecomLogClass$LogSessionTiming != null) {
                    codedOutputByteBufferNano.writeMessage(2, telecomLogClass$LogSessionTiming);
                }
                i++;
            }
        }
        if ((this.bitField0_ & 1) != 0) {
            codedOutputByteBufferNano.writeString(3, this.hardwareRevision_);
        }
        if ((this.bitField0_ & 2) != 0) {
            codedOutputByteBufferNano.writeInt32(4, this.carrierId_);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.google.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        TelecomLogClass$CallLog[] telecomLogClass$CallLogArr = this.callLogs;
        int i = 0;
        if (telecomLogClass$CallLogArr != null && telecomLogClass$CallLogArr.length > 0) {
            int i2 = 0;
            while (true) {
                TelecomLogClass$CallLog[] telecomLogClass$CallLogArr2 = this.callLogs;
                if (i2 >= telecomLogClass$CallLogArr2.length) {
                    break;
                }
                TelecomLogClass$CallLog telecomLogClass$CallLog = telecomLogClass$CallLogArr2[i2];
                if (telecomLogClass$CallLog != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, telecomLogClass$CallLog);
                }
                i2++;
            }
        }
        TelecomLogClass$LogSessionTiming[] telecomLogClass$LogSessionTimingArr = this.sessionTimings;
        if (telecomLogClass$LogSessionTimingArr != null && telecomLogClass$LogSessionTimingArr.length > 0) {
            while (true) {
                TelecomLogClass$LogSessionTiming[] telecomLogClass$LogSessionTimingArr2 = this.sessionTimings;
                if (i >= telecomLogClass$LogSessionTimingArr2.length) {
                    break;
                }
                TelecomLogClass$LogSessionTiming telecomLogClass$LogSessionTiming = telecomLogClass$LogSessionTimingArr2[i];
                if (telecomLogClass$LogSessionTiming != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(2, telecomLogClass$LogSessionTiming);
                }
                i++;
            }
        }
        if ((this.bitField0_ & 1) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.hardwareRevision_);
        }
        return (this.bitField0_ & 2) != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(4, this.carrierId_) : iComputeSerializedSize;
    }
}
