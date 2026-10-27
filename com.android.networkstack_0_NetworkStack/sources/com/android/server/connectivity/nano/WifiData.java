package com.android.server.connectivity.nano;

import com.android.networkstack.protobuf.nano.CodedOutputByteBufferNano;
import com.android.networkstack.protobuf.nano.MessageNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class WifiData extends MessageNano {
    public int signalStrength;
    public int wifiBand;

    public WifiData() {
        clear();
    }

    public WifiData clear() {
        this.signalStrength = 0;
        this.wifiBand = 0;
        return this;
    }

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        int i = this.signalStrength;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        int i2 = this.wifiBand;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(2, i2);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        int i = this.signalStrength;
        if (i != 0) {
            iComputeSerializedSize += CodedOutputByteBufferNano.computeInt32Size(1, i);
        }
        int i2 = this.wifiBand;
        return i2 != 0 ? iComputeSerializedSize + CodedOutputByteBufferNano.computeInt32Size(2, i2) : iComputeSerializedSize;
    }
}
