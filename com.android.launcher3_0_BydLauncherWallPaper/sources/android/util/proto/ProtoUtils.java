package android.util.proto;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class ProtoUtils {
    public static void toAggStatsProto(ProtoOutputStream protoOutputStream, long j, long j2, long j3, long j4, int i, int i2) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1112396529665L, j2);
        protoOutputStream.write(1112396529666L, j3);
        protoOutputStream.write(1112396529667L, j4);
        protoOutputStream.write(1120986464260L, i);
        protoOutputStream.write(1120986464261L, i2);
        protoOutputStream.end(jStart);
    }

    public static void toAggStatsProto(ProtoOutputStream protoOutputStream, long j, long j2, long j3, long j4) {
        toAggStatsProto(protoOutputStream, j, j2, j3, j4, 0, 0);
    }

    public static void toDuration(ProtoOutputStream protoOutputStream, long j, long j2, long j3) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1112396529665L, j2);
        protoOutputStream.write(1112396529666L, j3);
        protoOutputStream.end(jStart);
    }

    public static void writeBitWiseFlagsToProtoEnum(ProtoOutputStream protoOutputStream, long j, int i, int[] iArr, int[] iArr2) {
        if (iArr2.length != iArr.length) {
            throw new IllegalArgumentException("The length of origEnums must match protoEnums");
        }
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] == 0 && i == 0) {
                protoOutputStream.write(j, iArr2[i2]);
                return;
            } else {
                if ((iArr[i2] & i) != 0) {
                    protoOutputStream.write(j, iArr2[i2]);
                }
            }
        }
    }

    public static String currentFieldToString(ProtoInputStream protoInputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int fieldNumber = protoInputStream.getFieldNumber();
        int wireType = protoInputStream.getWireType();
        sb.append("Offset : 0x" + Integer.toHexString(protoInputStream.getOffset()));
        sb.append("\nField Number : 0x" + Integer.toHexString(protoInputStream.getFieldNumber()));
        sb.append("\nWire Type : ");
        if (wireType == 0) {
            sb.append("varint");
            sb.append("\nField Value : 0x" + Long.toHexString(protoInputStream.readLong(ProtoStream.makeFieldId(fieldNumber, 1112396529664L))));
        } else if (wireType == 1) {
            sb.append("fixed64");
            sb.append("\nField Value : 0x" + Long.toHexString(protoInputStream.readLong(ProtoStream.makeFieldId(fieldNumber, 1125281431552L))));
        } else if (wireType == 2) {
            sb.append("length delimited");
            sb.append("\nField Bytes : " + protoInputStream.readBytes(ProtoStream.makeFieldId(fieldNumber, 1151051235328L)));
        } else if (wireType == 3) {
            sb.append("start group");
        } else if (wireType == 4) {
            sb.append("end group");
        } else if (wireType == 5) {
            sb.append("fixed32");
            sb.append("\nField Value : 0x" + Integer.toHexString(protoInputStream.readInt(ProtoStream.makeFieldId(fieldNumber, 1129576398848L))));
        } else {
            sb.append("unknown(" + protoInputStream.getWireType() + ")");
        }
        return sb.toString();
    }
}
