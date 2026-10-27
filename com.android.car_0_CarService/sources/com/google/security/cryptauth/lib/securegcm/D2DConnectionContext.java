package com.google.security.cryptauth.lib.securegcm;

import android.hardware.automotive.vehicle.V2_0.VehiclePropertyType;
import androidx.core.view.MotionEventCompat;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto;
import com.google.security.cryptauth.lib.securegcm.TransportCryptoOps;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public abstract class D2DConnectionContext {
    private static final String UTF8 = "UTF-8";
    private final int protocolVersion;

    abstract SecretKey getDecodeKey();

    abstract SecretKey getEncodeKey();

    abstract int getSequenceNumberForDecoding();

    abstract int getSequenceNumberForEncoding();

    public abstract byte[] getSessionUnique() throws NoSuchAlgorithmException;

    protected abstract void incrementSequenceNumberForDecoding();

    protected abstract void incrementSequenceNumberForEncoding();

    public abstract byte[] saveSession();

    protected D2DConnectionContext(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public byte[] encodeMessageToPeer(byte[] payload) {
        incrementSequenceNumberForEncoding();
        DeviceToDeviceMessagesProto.DeviceToDeviceMessage message = createDeviceToDeviceMessage(payload, getSequenceNumberForEncoding());
        try {
            return D2DCryptoOps.signcryptPayload(new TransportCryptoOps.Payload(TransportCryptoOps.PayloadType.DEVICE_TO_DEVICE_MESSAGE, message.toByteArray()), getEncodeKey());
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    public byte[] encodeMessageToPeer(String payload) {
        try {
            return encodeMessageToPeer(payload.getBytes(UTF8));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decodeMessageFromPeer(byte[] message) throws SignatureException {
        try {
            TransportCryptoOps.Payload payload = D2DCryptoOps.verifydecryptPayload(message, getDecodeKey());
            if (!TransportCryptoOps.PayloadType.DEVICE_TO_DEVICE_MESSAGE.equals(payload.getPayloadType())) {
                throw new SignatureException("wrong message type in device-to-device message");
            }
            DeviceToDeviceMessagesProto.DeviceToDeviceMessage messageProto = DeviceToDeviceMessagesProto.DeviceToDeviceMessage.parseFrom(payload.getMessage());
            incrementSequenceNumberForDecoding();
            if (messageProto.getSequenceNumber() != getSequenceNumberForDecoding()) {
                throw new SignatureException("Incorrect sequence number");
            }
            return messageProto.getMessage().toByteArray();
        } catch (InvalidProtocolBufferException e) {
            throw new SignatureException(e);
        } catch (InvalidKeyException e2) {
            throw new SignatureException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        }
    }

    public String decodeMessageFromPeerAsString(byte[] message) throws SignatureException {
        try {
            return new String(decodeMessageFromPeer(message), UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    static DeviceToDeviceMessagesProto.DeviceToDeviceMessage createDeviceToDeviceMessage(byte[] message, int sequenceNumber) {
        DeviceToDeviceMessagesProto.DeviceToDeviceMessage.Builder deviceToDeviceMessage = DeviceToDeviceMessagesProto.DeviceToDeviceMessage.newBuilder();
        deviceToDeviceMessage.setSequenceNumber(sequenceNumber);
        deviceToDeviceMessage.setMessage(ByteString.copyFrom(message));
        return deviceToDeviceMessage.build();
    }

    public static D2DConnectionContext fromSavedSession(byte[] savedSessionInfo) {
        if (savedSessionInfo == null || savedSessionInfo.length == 0) {
            throw new IllegalArgumentException("savedSessionInfo null or too short");
        }
        int protocolVersion = savedSessionInfo[0] & UByte.MAX_VALUE;
        if (protocolVersion == 0) {
            if (savedSessionInfo.length != 37) {
                throw new IllegalArgumentException("Incorrect data length (" + savedSessionInfo.length + ") for v0 protocol");
            }
            int sequenceNumber = bytesToSignedInt(Arrays.copyOfRange(savedSessionInfo, 1, 5));
            SecretKey sharedKey = new SecretKeySpec(Arrays.copyOfRange(savedSessionInfo, 5, 37), "AES");
            return new D2DConnectionContextV0(sharedKey, sequenceNumber);
        }
        if (protocolVersion == 1) {
            if (savedSessionInfo.length != 73) {
                throw new IllegalArgumentException("Incorrect data length for v1 protocol");
            }
            int encodeSequenceNumber = bytesToSignedInt(Arrays.copyOfRange(savedSessionInfo, 1, 5));
            int decodeSequenceNumber = bytesToSignedInt(Arrays.copyOfRange(savedSessionInfo, 5, 9));
            SecretKey encodeKey = new SecretKeySpec(Arrays.copyOfRange(savedSessionInfo, 9, 41), "AES");
            SecretKey decodeKey = new SecretKeySpec(Arrays.copyOfRange(savedSessionInfo, 41, 73), "AES");
            return new D2DConnectionContextV1(encodeKey, decodeKey, encodeSequenceNumber, decodeSequenceNumber);
        }
        throw new IllegalArgumentException("Cannot rebuild context, unkown protocol version: " + protocolVersion);
    }

    static int bytesToSignedInt(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Expected 4 bytes to encode int, but got: " + bytes.length + " bytes");
        }
        return ((bytes[0] << 24) & (-16777216)) | ((bytes[1] << 16) & VehiclePropertyType.MASK) | ((bytes[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bytes[3] & UByte.MAX_VALUE);
    }

    static byte[] signedIntToBytes(int val) {
        byte[] bytes = {(byte) ((val >> 24) & 255), (byte) ((val >> 16) & 255), (byte) ((val >> 8) & 255), (byte) (val & 255)};
        return bytes;
    }
}
