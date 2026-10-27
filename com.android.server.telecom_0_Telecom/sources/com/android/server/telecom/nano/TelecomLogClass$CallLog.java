package com.android.server.telecom.nano;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomLogClass$CallLog extends MessageNano {
    private static volatile TelecomLogClass$CallLog[] _emptyArray;
    private int bitField0_;
    private long callDurationMillis_;
    public TelecomLogClass$Event[] callEvents;
    private int callSource_;
    private int callTechnologies_;
    private int callTerminationCode_;
    public TelecomLogClass$EventTimingEntry[] callTimings;
    private int connectionProperties_;
    public String[] connectionService;
    public TelecomLogClass$InCallServiceInfo[] inCallServices;
    private boolean isAdditionalCall_;
    private boolean isCreatedFromExistingConnection_;
    private boolean isEmergencyCall_;
    private boolean isInterrupted_;
    private boolean isVideoCall_;
    private long startTime5Min_;
    private int type_;
    public TelecomLogClass$VideoEvent[] videoEvents;

    public static TelecomLogClass$CallLog[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new TelecomLogClass$CallLog[0];
                }
            }
        }
        return _emptyArray;
    }

    public long getStartTime5Min() {
        return this.startTime5Min_;
    }

    public TelecomLogClass$CallLog setStartTime5Min(long j) {
        this.startTime5Min_ = j;
        this.bitField0_ |= 1;
        return this;
    }

    public long getCallDurationMillis() {
        return this.callDurationMillis_;
    }

    public TelecomLogClass$CallLog setCallDurationMillis(long j) {
        this.callDurationMillis_ = j;
        this.bitField0_ |= 2;
        return this;
    }

    public int getType() {
        return this.type_;
    }

    public TelecomLogClass$CallLog setType(int i) {
        this.type_ = i;
        this.bitField0_ |= 4;
        return this;
    }

    public boolean getIsAdditionalCall() {
        return this.isAdditionalCall_;
    }

    public TelecomLogClass$CallLog setIsAdditionalCall(boolean z) {
        this.isAdditionalCall_ = z;
        this.bitField0_ |= 8;
        return this;
    }

    public boolean getIsInterrupted() {
        return this.isInterrupted_;
    }

    public TelecomLogClass$CallLog setIsInterrupted(boolean z) {
        this.isInterrupted_ = z;
        this.bitField0_ |= 16;
        return this;
    }

    public int getCallTechnologies() {
        return this.callTechnologies_;
    }

    public TelecomLogClass$CallLog setCallTechnologies(int i) {
        this.callTechnologies_ = i;
        this.bitField0_ |= 32;
        return this;
    }

    public int getCallTerminationCode() {
        return this.callTerminationCode_;
    }

    public TelecomLogClass$CallLog setCallTerminationCode(int i) {
        this.callTerminationCode_ = i;
        this.bitField0_ |= 64;
        return this;
    }

    public boolean getIsCreatedFromExistingConnection() {
        return this.isCreatedFromExistingConnection_;
    }

    public TelecomLogClass$CallLog setIsCreatedFromExistingConnection(boolean z) {
        this.isCreatedFromExistingConnection_ = z;
        this.bitField0_ |= 128;
        return this;
    }

    public boolean getIsEmergencyCall() {
        return this.isEmergencyCall_;
    }

    public TelecomLogClass$CallLog setIsEmergencyCall(boolean z) {
        this.isEmergencyCall_ = z;
        this.bitField0_ |= 256;
        return this;
    }

    public boolean getIsVideoCall() {
        return this.isVideoCall_;
    }

    public TelecomLogClass$CallLog setIsVideoCall(boolean z) {
        this.isVideoCall_ = z;
        this.bitField0_ |= 512;
        return this;
    }

    public TelecomLogClass$CallLog setConnectionProperties(int i) {
        this.connectionProperties_ = i;
        this.bitField0_ |= 1024;
        return this;
    }

    public int getCallSource() {
        return this.callSource_;
    }

    public TelecomLogClass$CallLog setCallSource(int i) {
        this.callSource_ = i;
        this.bitField0_ |= 2048;
        return this;
    }

    public TelecomLogClass$CallLog() {
        clear();
    }

    public TelecomLogClass$CallLog clear() {
        this.bitField0_ = 0;
        this.startTime5Min_ = 0L;
        this.callDurationMillis_ = 0L;
        this.type_ = 0;
        this.isAdditionalCall_ = false;
        this.isInterrupted_ = false;
        this.callTechnologies_ = 0;
        this.callTerminationCode_ = 0;
        this.connectionService = WireFormatNano.EMPTY_STRING_ARRAY;
        this.isCreatedFromExistingConnection_ = false;
        this.isEmergencyCall_ = false;
        this.callEvents = TelecomLogClass$Event.emptyArray();
        this.callTimings = TelecomLogClass$EventTimingEntry.emptyArray();
        this.isVideoCall_ = false;
        this.videoEvents = TelecomLogClass$VideoEvent.emptyArray();
        this.inCallServices = TelecomLogClass$InCallServiceInfo.emptyArray();
        this.connectionProperties_ = 0;
        this.callSource_ = 0;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        if ((this.bitField0_ & 1) != 0) {
            codedOutputByteBufferNano.writeInt64(1, this.startTime5Min_);
        }
        if ((this.bitField0_ & 2) != 0) {
            codedOutputByteBufferNano.writeInt64(2, this.callDurationMillis_);
        }
        if ((this.bitField0_ & 4) != 0) {
            codedOutputByteBufferNano.writeInt32(3, this.type_);
        }
        if ((this.bitField0_ & 8) != 0) {
            codedOutputByteBufferNano.writeBool(4, this.isAdditionalCall_);
        }
        if ((this.bitField0_ & 16) != 0) {
            codedOutputByteBufferNano.writeBool(5, this.isInterrupted_);
        }
        if ((this.bitField0_ & 32) != 0) {
            codedOutputByteBufferNano.writeInt32(6, this.callTechnologies_);
        }
        if ((this.bitField0_ & 64) != 0) {
            codedOutputByteBufferNano.writeInt32(7, this.callTerminationCode_);
        }
        String[] strArr = this.connectionService;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.connectionService;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    codedOutputByteBufferNano.writeString(9, str);
                }
                i2++;
            }
        }
        if ((this.bitField0_ & 128) != 0) {
            codedOutputByteBufferNano.writeBool(10, this.isCreatedFromExistingConnection_);
        }
        if ((this.bitField0_ & 256) != 0) {
            codedOutputByteBufferNano.writeBool(11, this.isEmergencyCall_);
        }
        TelecomLogClass$Event[] telecomLogClass$EventArr = this.callEvents;
        if (telecomLogClass$EventArr != null && telecomLogClass$EventArr.length > 0) {
            int i3 = 0;
            while (true) {
                TelecomLogClass$Event[] telecomLogClass$EventArr2 = this.callEvents;
                if (i3 >= telecomLogClass$EventArr2.length) {
                    break;
                }
                TelecomLogClass$Event telecomLogClass$Event = telecomLogClass$EventArr2[i3];
                if (telecomLogClass$Event != null) {
                    codedOutputByteBufferNano.writeMessage(12, telecomLogClass$Event);
                }
                i3++;
            }
        }
        TelecomLogClass$EventTimingEntry[] telecomLogClass$EventTimingEntryArr = this.callTimings;
        if (telecomLogClass$EventTimingEntryArr != null && telecomLogClass$EventTimingEntryArr.length > 0) {
            int i4 = 0;
            while (true) {
                TelecomLogClass$EventTimingEntry[] telecomLogClass$EventTimingEntryArr2 = this.callTimings;
                if (i4 >= telecomLogClass$EventTimingEntryArr2.length) {
                    break;
                }
                TelecomLogClass$EventTimingEntry telecomLogClass$EventTimingEntry = telecomLogClass$EventTimingEntryArr2[i4];
                if (telecomLogClass$EventTimingEntry != null) {
                    codedOutputByteBufferNano.writeMessage(13, telecomLogClass$EventTimingEntry);
                }
                i4++;
            }
        }
        if ((this.bitField0_ & 512) != 0) {
            codedOutputByteBufferNano.writeBool(14, this.isVideoCall_);
        }
        TelecomLogClass$VideoEvent[] telecomLogClass$VideoEventArr = this.videoEvents;
        if (telecomLogClass$VideoEventArr != null && telecomLogClass$VideoEventArr.length > 0) {
            int i5 = 0;
            while (true) {
                TelecomLogClass$VideoEvent[] telecomLogClass$VideoEventArr2 = this.videoEvents;
                if (i5 >= telecomLogClass$VideoEventArr2.length) {
                    break;
                }
                TelecomLogClass$VideoEvent telecomLogClass$VideoEvent = telecomLogClass$VideoEventArr2[i5];
                if (telecomLogClass$VideoEvent != null) {
                    codedOutputByteBufferNano.writeMessage(15, telecomLogClass$VideoEvent);
                }
                i5++;
            }
        }
        TelecomLogClass$InCallServiceInfo[] telecomLogClass$InCallServiceInfoArr = this.inCallServices;
        if (telecomLogClass$InCallServiceInfoArr != null && telecomLogClass$InCallServiceInfoArr.length > 0) {
            while (true) {
                TelecomLogClass$InCallServiceInfo[] telecomLogClass$InCallServiceInfoArr2 = this.inCallServices;
                if (i >= telecomLogClass$InCallServiceInfoArr2.length) {
                    break;
                }
                TelecomLogClass$InCallServiceInfo telecomLogClass$InCallServiceInfo = telecomLogClass$InCallServiceInfoArr2[i];
                if (telecomLogClass$InCallServiceInfo != null) {
                    codedOutputByteBufferNano.writeMessage(16, telecomLogClass$InCallServiceInfo);
                }
                i++;
            }
        }
        if ((this.bitField0_ & 1024) != 0) {
            codedOutputByteBufferNano.writeInt32(17, this.connectionProperties_);
        }
        if ((this.bitField0_ & 2048) != 0) {
            codedOutputByteBufferNano.writeInt32(18, this.callSource_);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.google.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        if ((this.bitField0_ & 1) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.startTime5Min_);
        }
        if ((this.bitField0_ & 2) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(2, this.callDurationMillis_);
        }
        if ((this.bitField0_ & 4) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(3, this.type_);
        }
        if ((this.bitField0_ & 8) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(4, this.isAdditionalCall_);
        }
        if ((this.bitField0_ & 16) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(5, this.isInterrupted_);
        }
        if ((this.bitField0_ & 32) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(6, this.callTechnologies_);
        }
        if ((this.bitField0_ & 64) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(7, this.callTerminationCode_);
        }
        String[] strArr = this.connectionService;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            int iComputeStringSizeNoTag = 0;
            int i3 = 0;
            while (true) {
                String[] strArr2 = this.connectionService;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    i3++;
                    iComputeStringSizeNoTag += CodedOutputByteBufferNano.computeStringSizeNoTag(str);
                }
                i2++;
            }
            iComputeSerializedSize = iComputeSerializedSize + iComputeStringSizeNoTag + (i3 * 1);
        }
        if ((this.bitField0_ & 128) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(10, this.isCreatedFromExistingConnection_);
        }
        if ((this.bitField0_ & 256) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(11, this.isEmergencyCall_);
        }
        TelecomLogClass$Event[] telecomLogClass$EventArr = this.callEvents;
        if (telecomLogClass$EventArr != null && telecomLogClass$EventArr.length > 0) {
            int i4 = 0;
            while (true) {
                TelecomLogClass$Event[] telecomLogClass$EventArr2 = this.callEvents;
                if (i4 >= telecomLogClass$EventArr2.length) {
                    break;
                }
                TelecomLogClass$Event telecomLogClass$Event = telecomLogClass$EventArr2[i4];
                if (telecomLogClass$Event != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(12, telecomLogClass$Event);
                }
                i4++;
            }
        }
        TelecomLogClass$EventTimingEntry[] telecomLogClass$EventTimingEntryArr = this.callTimings;
        if (telecomLogClass$EventTimingEntryArr != null && telecomLogClass$EventTimingEntryArr.length > 0) {
            int i5 = 0;
            while (true) {
                TelecomLogClass$EventTimingEntry[] telecomLogClass$EventTimingEntryArr2 = this.callTimings;
                if (i5 >= telecomLogClass$EventTimingEntryArr2.length) {
                    break;
                }
                TelecomLogClass$EventTimingEntry telecomLogClass$EventTimingEntry = telecomLogClass$EventTimingEntryArr2[i5];
                if (telecomLogClass$EventTimingEntry != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(13, telecomLogClass$EventTimingEntry);
                }
                i5++;
            }
        }
        if ((this.bitField0_ & 512) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeBoolSize(14, this.isVideoCall_);
        }
        TelecomLogClass$VideoEvent[] telecomLogClass$VideoEventArr = this.videoEvents;
        if (telecomLogClass$VideoEventArr != null && telecomLogClass$VideoEventArr.length > 0) {
            int i6 = 0;
            while (true) {
                TelecomLogClass$VideoEvent[] telecomLogClass$VideoEventArr2 = this.videoEvents;
                if (i6 >= telecomLogClass$VideoEventArr2.length) {
                    break;
                }
                TelecomLogClass$VideoEvent telecomLogClass$VideoEvent = telecomLogClass$VideoEventArr2[i6];
                if (telecomLogClass$VideoEvent != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(15, telecomLogClass$VideoEvent);
                }
                i6++;
            }
        }
        TelecomLogClass$InCallServiceInfo[] telecomLogClass$InCallServiceInfoArr = this.inCallServices;
        if (telecomLogClass$InCallServiceInfoArr != null && telecomLogClass$InCallServiceInfoArr.length > 0) {
            while (true) {
                TelecomLogClass$InCallServiceInfo[] telecomLogClass$InCallServiceInfoArr2 = this.inCallServices;
                if (i >= telecomLogClass$InCallServiceInfoArr2.length) {
                    break;
                }
                TelecomLogClass$InCallServiceInfo telecomLogClass$InCallServiceInfo = telecomLogClass$InCallServiceInfoArr2[i];
                if (telecomLogClass$InCallServiceInfo != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(16, telecomLogClass$InCallServiceInfo);
                }
                i++;
            }
        }
        if ((this.bitField0_ & 1024) != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(17, this.connectionProperties_);
        }
        return (this.bitField0_ & 2048) != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(18, this.callSource_) : iComputeSerializedSize;
    }
}
