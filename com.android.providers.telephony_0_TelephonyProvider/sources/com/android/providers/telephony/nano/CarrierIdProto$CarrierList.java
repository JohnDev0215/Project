package com.android.providers.telephony.nano;

import com.android.providers.telephony.protobuf.nano.CodedInputByteBufferNano;
import com.android.providers.telephony.protobuf.nano.CodedOutputByteBufferNano;
import com.android.providers.telephony.protobuf.nano.ExtendableMessageNano;
import com.android.providers.telephony.protobuf.nano.InvalidProtocolBufferNanoException;
import com.android.providers.telephony.protobuf.nano.MessageNano;
import com.android.providers.telephony.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class CarrierIdProto$CarrierList extends ExtendableMessageNano<CarrierIdProto$CarrierList> {
    public CarrierIdProto$CarrierId[] carrierId;
    public int version;

    @Override // com.android.providers.telephony.protobuf.nano.MessageNano
    public /* bridge */ /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        mergeFrom(codedInputByteBufferNano);
        return this;
    }

    public CarrierIdProto$CarrierList() {
        clear();
    }

    public CarrierIdProto$CarrierList clear() {
        this.carrierId = CarrierIdProto$CarrierId.emptyArray();
        this.version = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.android.providers.telephony.protobuf.nano.ExtendableMessageNano, com.android.providers.telephony.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr = this.carrierId;
        if (carrierIdProto$CarrierIdArr != null && carrierIdProto$CarrierIdArr.length > 0) {
            int i = 0;
            while (true) {
                CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr2 = this.carrierId;
                if (i >= carrierIdProto$CarrierIdArr2.length) {
                    break;
                }
                CarrierIdProto$CarrierId carrierIdProto$CarrierId = carrierIdProto$CarrierIdArr2[i];
                if (carrierIdProto$CarrierId != null) {
                    codedOutputByteBufferNano.writeMessage(1, carrierIdProto$CarrierId);
                }
                i++;
            }
        }
        int i2 = this.version;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(2, i2);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.android.providers.telephony.protobuf.nano.ExtendableMessageNano, com.android.providers.telephony.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr = this.carrierId;
        if (carrierIdProto$CarrierIdArr != null && carrierIdProto$CarrierIdArr.length > 0) {
            int i = 0;
            while (true) {
                CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr2 = this.carrierId;
                if (i >= carrierIdProto$CarrierIdArr2.length) {
                    break;
                }
                CarrierIdProto$CarrierId carrierIdProto$CarrierId = carrierIdProto$CarrierIdArr2[i];
                if (carrierIdProto$CarrierId != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(1, carrierIdProto$CarrierId);
                }
                i++;
            }
        }
        int i2 = this.version;
        return i2 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(2, i2) : iComputeSerializedSize;
    }

    @Override // com.android.providers.telephony.protobuf.nano.MessageNano
    public CarrierIdProto$CarrierList mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int tag = codedInputByteBufferNano.readTag();
            if (tag == 0) {
                return this;
            }
            if (tag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr = this.carrierId;
                int length = carrierIdProto$CarrierIdArr == null ? 0 : carrierIdProto$CarrierIdArr.length;
                int i = repeatedFieldArrayLength + length;
                CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr2 = new CarrierIdProto$CarrierId[i];
                if (length != 0) {
                    System.arraycopy(this.carrierId, 0, carrierIdProto$CarrierIdArr2, 0, length);
                }
                while (length < i - 1) {
                    carrierIdProto$CarrierIdArr2[length] = new CarrierIdProto$CarrierId();
                    codedInputByteBufferNano.readMessage(carrierIdProto$CarrierIdArr2[length]);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                carrierIdProto$CarrierIdArr2[length] = new CarrierIdProto$CarrierId();
                codedInputByteBufferNano.readMessage(carrierIdProto$CarrierIdArr2[length]);
                this.carrierId = carrierIdProto$CarrierIdArr2;
            } else if (tag != 16) {
                if (!storeUnknownField(codedInputByteBufferNano, tag)) {
                    return this;
                }
            } else {
                this.version = codedInputByteBufferNano.readInt32();
            }
        }
    }

    public static CarrierIdProto$CarrierList parseFrom(byte[] bArr) throws InvalidProtocolBufferNanoException {
        CarrierIdProto$CarrierList carrierIdProto$CarrierList = new CarrierIdProto$CarrierList();
        MessageNano.mergeFrom(carrierIdProto$CarrierList, bArr);
        return carrierIdProto$CarrierList;
    }
}
