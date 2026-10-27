package com.android.providers.telephony.nano;

import com.android.providers.telephony.protobuf.nano.CodedInputByteBufferNano;
import com.android.providers.telephony.protobuf.nano.CodedOutputByteBufferNano;
import com.android.providers.telephony.protobuf.nano.ExtendableMessageNano;
import com.android.providers.telephony.protobuf.nano.InternalNano;
import com.android.providers.telephony.protobuf.nano.MessageNano;
import com.android.providers.telephony.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class CarrierIdProto$CarrierId extends ExtendableMessageNano<CarrierIdProto$CarrierId> {
    private static volatile CarrierIdProto$CarrierId[] _emptyArray;
    public int canonicalId;
    public CarrierIdProto$CarrierAttribute[] carrierAttribute;
    public String carrierName;
    public int parentCanonicalId;

    @Override // com.android.providers.telephony.protobuf.nano.MessageNano
    public /* bridge */ /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        mergeFrom(codedInputByteBufferNano);
        return this;
    }

    public static CarrierIdProto$CarrierId[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                if (_emptyArray == null) {
                    _emptyArray = new CarrierIdProto$CarrierId[0];
                }
            }
        }
        return _emptyArray;
    }

    public CarrierIdProto$CarrierId() {
        clear();
    }

    public CarrierIdProto$CarrierId clear() {
        this.canonicalId = 0;
        this.carrierName = "";
        this.carrierAttribute = CarrierIdProto$CarrierAttribute.emptyArray();
        this.parentCanonicalId = 0;
        this.unknownFieldData = null;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.android.providers.telephony.protobuf.nano.ExtendableMessageNano, com.android.providers.telephony.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        int i = this.canonicalId;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        if (!this.carrierName.equals("")) {
            codedOutputByteBufferNano.writeString(2, this.carrierName);
        }
        CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr = this.carrierAttribute;
        if (carrierIdProto$CarrierAttributeArr != null && carrierIdProto$CarrierAttributeArr.length > 0) {
            int i2 = 0;
            while (true) {
                CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr2 = this.carrierAttribute;
                if (i2 >= carrierIdProto$CarrierAttributeArr2.length) {
                    break;
                }
                CarrierIdProto$CarrierAttribute carrierIdProto$CarrierAttribute = carrierIdProto$CarrierAttributeArr2[i2];
                if (carrierIdProto$CarrierAttribute != null) {
                    codedOutputByteBufferNano.writeMessage(3, carrierIdProto$CarrierAttribute);
                }
                i2++;
            }
        }
        int i3 = this.parentCanonicalId;
        if (i3 != 0) {
            codedOutputByteBufferNano.writeInt32(4, i3);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.android.providers.telephony.protobuf.nano.ExtendableMessageNano, com.android.providers.telephony.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        int i = this.canonicalId;
        if (i != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
        }
        if (!this.carrierName.equals("")) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.carrierName);
        }
        CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr = this.carrierAttribute;
        if (carrierIdProto$CarrierAttributeArr != null && carrierIdProto$CarrierAttributeArr.length > 0) {
            int i2 = 0;
            while (true) {
                CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr2 = this.carrierAttribute;
                if (i2 >= carrierIdProto$CarrierAttributeArr2.length) {
                    break;
                }
                CarrierIdProto$CarrierAttribute carrierIdProto$CarrierAttribute = carrierIdProto$CarrierAttributeArr2[i2];
                if (carrierIdProto$CarrierAttribute != null) {
                    iComputeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(3, carrierIdProto$CarrierAttribute);
                }
                i2++;
            }
        }
        int i3 = this.parentCanonicalId;
        return i3 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(4, i3) : iComputeSerializedSize;
    }

    @Override // com.android.providers.telephony.protobuf.nano.MessageNano
    public CarrierIdProto$CarrierId mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int tag = codedInputByteBufferNano.readTag();
            if (tag == 0) {
                return this;
            }
            if (tag == 8) {
                this.canonicalId = codedInputByteBufferNano.readInt32();
            } else if (tag == 18) {
                this.carrierName = codedInputByteBufferNano.readString();
            } else if (tag == 26) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 26);
                CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr = this.carrierAttribute;
                int length = carrierIdProto$CarrierAttributeArr == null ? 0 : carrierIdProto$CarrierAttributeArr.length;
                int i = repeatedFieldArrayLength + length;
                CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr2 = new CarrierIdProto$CarrierAttribute[i];
                if (length != 0) {
                    System.arraycopy(this.carrierAttribute, 0, carrierIdProto$CarrierAttributeArr2, 0, length);
                }
                while (length < i - 1) {
                    carrierIdProto$CarrierAttributeArr2[length] = new CarrierIdProto$CarrierAttribute();
                    codedInputByteBufferNano.readMessage(carrierIdProto$CarrierAttributeArr2[length]);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                carrierIdProto$CarrierAttributeArr2[length] = new CarrierIdProto$CarrierAttribute();
                codedInputByteBufferNano.readMessage(carrierIdProto$CarrierAttributeArr2[length]);
                this.carrierAttribute = carrierIdProto$CarrierAttributeArr2;
            } else if (tag != 32) {
                if (!storeUnknownField(codedInputByteBufferNano, tag)) {
                    return this;
                }
            } else {
                this.parentCanonicalId = codedInputByteBufferNano.readInt32();
            }
        }
    }
}
