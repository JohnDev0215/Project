package com.android.car.trust;

import android.util.Log;
import com.android.car.BLEStreamProtos.BLEMessageProto;
import com.android.car.BLEStreamProtos.BLEOperationProto;
import com.android.car.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class BLEMessageV1Factory {
    private static final int BOOLEAN_FIELD_ENCODING_SIZE = 1;
    private static final int CONSTANT_HEADER_FIELD_SIZE;
    private static final int FIELD_NUMBER_ENCODING_SIZE = 1;
    private static final int FIXED_32_SIZE = 4;
    private static final int PROTOCOL_VERSION = 1;
    private static final String TAG = "BLEMessageFactory";
    private static final int VERSION_SIZE;

    static {
        int encodedSize = getEncodedSize(1) + 1;
        VERSION_SIZE = encodedSize;
        CONSTANT_HEADER_FIELD_SIZE = encodedSize + 5 + 5;
    }

    private BLEMessageV1Factory() {
    }

    static BLEMessageProto.BLEMessage makeAcknowledgementMessage() {
        return BLEMessageProto.BLEMessage.newBuilder().setVersion(1).setOperation(BLEOperationProto.OperationType.ACK).setPacketNumber(1).setTotalPackets(1).setIsPayloadEncrypted(false).build();
    }

    static BLEMessageProto.BLEMessage makeBLEMessage(byte[] payload, BLEOperationProto.OperationType operation, boolean isPayloadEncrypted) {
        return BLEMessageProto.BLEMessage.newBuilder().setVersion(1).setOperation(operation).setPacketNumber(1).setTotalPackets(1).setIsPayloadEncrypted(isPayloadEncrypted).setPayload(ByteString.copyFrom(payload)).build();
    }

    public static List<BLEMessageProto.BLEMessage> makeBLEMessages(byte[] payload, BLEOperationProto.OperationType operation, int maxSize, boolean isPayloadEncrypted) {
        List<BLEMessageProto.BLEMessage> bleMessages = new ArrayList<>();
        int payloadSize = payload.length;
        int maxPayloadSize = maxSize - getProtoHeaderSize(operation, payloadSize, isPayloadEncrypted);
        if (payloadSize <= maxPayloadSize) {
            bleMessages.add(makeBLEMessage(payload, operation, isPayloadEncrypted));
            return bleMessages;
        }
        int totalPackets = (int) Math.ceil(((double) payloadSize) / ((double) maxPayloadSize));
        int start = 0;
        int end = maxPayloadSize;
        for (int i = 0; i < totalPackets; i++) {
            bleMessages.add(BLEMessageProto.BLEMessage.newBuilder().setVersion(1).setOperation(operation).setPacketNumber(i + 1).setTotalPackets(totalPackets).setIsPayloadEncrypted(isPayloadEncrypted).setPayload(ByteString.copyFrom(Arrays.copyOfRange(payload, start, end))).build());
            start = end;
            end = Math.min(start + maxPayloadSize, payloadSize);
        }
        return bleMessages;
    }

    static int getProtoHeaderSize(BLEOperationProto.OperationType operation, int payloadSize, boolean isPayloadEncrypted) {
        int isPayloadEncryptedFieldSize;
        if (isPayloadEncrypted) {
            isPayloadEncryptedFieldSize = 2;
        } else {
            isPayloadEncryptedFieldSize = 0;
        }
        int operationSize = getEncodedSize(operation.getNumber()) + 1;
        int payloadEncodingSize = getEncodedSize(payloadSize) + 1;
        return CONSTANT_HEADER_FIELD_SIZE + operationSize + isPayloadEncryptedFieldSize + payloadEncodingSize;
    }

    private static int getEncodedSize(int value) {
        if (value < 0) {
            Log.e(TAG, "Get a negative value from proto");
            return 10;
        }
        if ((value & (-128)) == 0) {
            return 1;
        }
        if ((value & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & value) == 0) {
            return 3;
        }
        if (((-268435456) & value) == 0) {
            return 4;
        }
        return 5;
    }
}
