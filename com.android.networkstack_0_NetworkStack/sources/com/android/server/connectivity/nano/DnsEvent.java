package com.android.server.connectivity.nano;

import com.android.networkstack.protobuf.nano.CodedOutputByteBufferNano;
import com.android.networkstack.protobuf.nano.MessageNano;
import com.android.networkstack.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class DnsEvent extends MessageNano {
    public int[] dnsReturnCode;
    public long[] dnsTime;

    public DnsEvent() {
        clear();
    }

    public DnsEvent clear() {
        this.dnsReturnCode = WireFormatNano.EMPTY_INT_ARRAY;
        this.dnsTime = WireFormatNano.EMPTY_LONG_ARRAY;
        return this;
    }

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        int[] iArr = this.dnsReturnCode;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.dnsReturnCode;
                if (i2 >= iArr2.length) {
                    break;
                }
                codedOutputByteBufferNano.writeInt32(1, iArr2[i2]);
                i2++;
            }
        }
        long[] jArr = this.dnsTime;
        if (jArr != null && jArr.length > 0) {
            while (true) {
                long[] jArr2 = this.dnsTime;
                if (i >= jArr2.length) {
                    break;
                }
                codedOutputByteBufferNano.writeInt64(2, jArr2[i]);
                i++;
            }
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    @Override // com.android.networkstack.protobuf.nano.MessageNano
    protected int computeSerializedSize() {
        int[] iArr;
        int iComputeSerializedSize = super.computeSerializedSize();
        int[] iArr2 = this.dnsReturnCode;
        int i = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i2 = 0;
            int iComputeInt32SizeNoTag = 0;
            while (true) {
                iArr = this.dnsReturnCode;
                if (i2 >= iArr.length) {
                    break;
                }
                iComputeInt32SizeNoTag += CodedOutputByteBufferNano.computeInt32SizeNoTag(iArr[i2]);
                i2++;
            }
            iComputeSerializedSize = iComputeSerializedSize + iComputeInt32SizeNoTag + (iArr.length * 1);
        }
        long[] jArr = this.dnsTime;
        if (jArr == null || jArr.length <= 0) {
            return iComputeSerializedSize;
        }
        int iComputeInt64SizeNoTag = 0;
        while (true) {
            long[] jArr2 = this.dnsTime;
            if (i < jArr2.length) {
                iComputeInt64SizeNoTag += CodedOutputByteBufferNano.computeInt64SizeNoTag(jArr2[i]);
                i++;
            } else {
                return iComputeSerializedSize + iComputeInt64SizeNoTag + (jArr2.length * 1);
            }
        }
    }
}
