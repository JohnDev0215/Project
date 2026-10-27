package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.DeviceToDeviceMessagesProto;
import com.google.security.cryptauth.lib.securegcm.Ed25519;
import com.google.security.cryptauth.lib.securegcm.TransportCryptoOps;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public class D2DSpakeEd25519Handshake implements D2DHandshakeContext {
    public static final int MIN_PASSWORD_LENGTH = 4;
    private static final int POINT_SIZE_BITS = 256;
    private static final String SHA256 = "SHA-256";
    private State handshakeState;
    private BigInteger[] ourCommitmentPointAffine;
    private BigInteger[] ourCommitmentPointExtended;
    private BigInteger passwordHash;
    private BigInteger[] pointX;
    private byte[] sharedKey;
    private BigInteger[] theirCommitmentPointAffine;
    private BigInteger[] theirCommitmentPointExtended;
    private BigInteger valueX;
    static final BigInteger[] KM = {new BigInteger(new byte[]{25, -127, -5, 67, -15, 3, 41, 14, -49, -105, 114, 2, 45, -72, -79, -101, -6, -13, -119, 5, 126, -39, 30, -124, -122, -21, 54, -121, 99, 67, 89, 37}), new BigInteger(new byte[]{10, 113, 76, 52, -13, -75, -120, -86, -55, 47, -46, 88, 120, -124, -94, 9, 100, -3, 53, 26, 31, 20, 125, 92, 75, -65, 92, 47, 55, -89, 124, 54}), BigInteger.ONE, new BigInteger(new byte[]{4, -113, -63, -50, -27, -125, -103, 37, -27, -101, ByteCompanionObject.MIN_VALUE, -22, -83, -126, -84, 10, 60, -2, -59, 96, -109, 89, -117, 72, 68, -35, 42, 62, 36, 93, -120, 51})};
    static final BigInteger[] KN = {new BigInteger(new byte[]{32, 26, 24, 79, 71, -39, -89, -105, 56, -111, -47, 72, -29, -47, -56, 100, -40, 8, 69, 71, 19, 28, 44, 28, -17, -73, -18, -67, 38, -58, 53, 103}), new BigInteger(new byte[]{109, -94, -45, -79, -114, -60, -7, -86, 59, 8, -29, -100, -103, 124, -40, -65, 110, -103, 72, -1, -44, -2, -1, -20, -81, -115, -48, -77, -42, 72, -73, -24}), BigInteger.ONE, new BigInteger(new byte[]{22, 64, -19, 90, 84, -6, 11, 7, 34, -122, -23, -46, 47, 70, 71, 99, -5, -10, 13, 121, 29, 55, -71, 9, 59, 88, 77, -12, -55, -107, -9, -127})};
    static final BigInteger[] B = {new BigInteger(new byte[]{33, 105, 54, -45, -51, 110, 83, -2, -64, -92, -30, 49, -3, -42, -36, 92, 105, 44, -57, 96, -107, 37, -89, -78, -55, 86, 45, 96, -113, 37, -43, 26}), new BigInteger(new byte[]{102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 102, 88}), BigInteger.ONE, new BigInteger(new byte[]{103, -121, 95, 15, -41, -117, 118, 101, 102, -22, 78, -114, 100, -85, -29, 125, 32, -16, -97, ByteCompanionObject.MIN_VALUE, 119, 81, 82, -11, 109, -34, -118, -77, -91, -73, -35, -93})};

    private enum State {
        INITIATOR_START,
        INITIATOR_WAITING_FOR_RESPONDER_COMMITMENT,
        INITIATOR_AFTER_RESPONDER_COMMITMENT,
        INITIATOR_WAITING_FOR_RESPONDER_HASH,
        RESPONDER_START,
        RESPONDER_AFTER_INITIATOR_COMMITMENT,
        RESPONDER_WAITING_FOR_INITIATOR_HASH,
        RESPONDER_AFTER_INITIATOR_HASH,
        HANDSHAKE_FINISHED,
        HANDSHAKE_ALREADY_USED
    }

    public static D2DSpakeEd25519Handshake forInitiator(byte[] password) throws HandshakeException {
        return new D2DSpakeEd25519Handshake(State.INITIATOR_START, password);
    }

    public static D2DSpakeEd25519Handshake forResponder(byte[] password) throws HandshakeException {
        return new D2DSpakeEd25519Handshake(State.RESPONDER_START, password);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0041, code lost:
    
        throw new com.google.security.cryptauth.lib.securegcm.HandshakeException("Could not make public key point", r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    D2DSpakeEd25519Handshake(com.google.security.cryptauth.lib.securegcm.D2DSpakeEd25519Handshake.State r4, byte[] r5) throws com.google.security.cryptauth.lib.securegcm.HandshakeException {
        /*
            r3 = this;
            r3.<init>()
            if (r5 == 0) goto L42
            int r0 = r5.length
            r1 = 4
            if (r0 < r1) goto L42
            r3.handshakeState = r4
            java.math.BigInteger r0 = new java.math.BigInteger
            r1 = 1
            byte[] r2 = hash(r5)
            r0.<init>(r1, r2)
            r3.passwordHash = r0
        L17:
            java.math.BigInteger r0 = new java.math.BigInteger
            r1 = 256(0x100, float:3.59E-43)
            java.security.SecureRandom r2 = new java.security.SecureRandom
            r2.<init>()
            r0.<init>(r1, r2)
            r3.valueX = r0
            java.math.BigInteger r1 = java.math.BigInteger.ZERO
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L17
            java.math.BigInteger[] r0 = com.google.security.cryptauth.lib.securegcm.D2DSpakeEd25519Handshake.B     // Catch: com.google.security.cryptauth.lib.securegcm.Ed25519.Ed25519Exception -> L39
            java.math.BigInteger r1 = r3.valueX     // Catch: com.google.security.cryptauth.lib.securegcm.Ed25519.Ed25519Exception -> L39
            java.math.BigInteger[] r0 = com.google.security.cryptauth.lib.securegcm.Ed25519.scalarMultiplyExtendedPoint(r0, r1)     // Catch: com.google.security.cryptauth.lib.securegcm.Ed25519.Ed25519Exception -> L39
            r3.pointX = r0     // Catch: com.google.security.cryptauth.lib.securegcm.Ed25519.Ed25519Exception -> L39
            return
        L39:
            r0 = move-exception
            com.google.security.cryptauth.lib.securegcm.HandshakeException r1 = new com.google.security.cryptauth.lib.securegcm.HandshakeException
            java.lang.String r2 = "Could not make public key point"
            r1.<init>(r2, r0)
            throw r1
        L42:
            com.google.security.cryptauth.lib.securegcm.HandshakeException r0 = new com.google.security.cryptauth.lib.securegcm.HandshakeException
            java.lang.String r1 = "Passwords must be at least 4 bytes"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.security.cryptauth.lib.securegcm.D2DSpakeEd25519Handshake.<init>(com.google.security.cryptauth.lib.securegcm.D2DSpakeEd25519Handshake$State, byte[]):void");
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public boolean isHandshakeComplete() {
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DSpakeEd25519Handshake$State[this.handshakeState.ordinal()];
        return i == 1 || i == 2;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public byte[] getNextHandshakeMessage() throws HandshakeException {
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DSpakeEd25519Handshake$State[this.handshakeState.ordinal()];
        if (i == 3) {
            byte[] nextMessage = makeCommitmentPointMessage(true);
            this.handshakeState = State.INITIATOR_WAITING_FOR_RESPONDER_COMMITMENT;
            return nextMessage;
        }
        if (i == 4) {
            byte[] nextMessage2 = makeCommitmentPointMessage(false);
            this.handshakeState = State.RESPONDER_WAITING_FOR_INITIATOR_HASH;
            return nextMessage2;
        }
        if (i == 5) {
            byte[] nextMessage3 = makeSharedKeyHashMessage(true, null);
            this.handshakeState = State.INITIATOR_WAITING_FOR_RESPONDER_HASH;
            return nextMessage3;
        }
        if (i == 6) {
            byte[] nextMessage4 = makeSharedKeyHashMessage(false, null);
            this.handshakeState = State.HANDSHAKE_FINISHED;
            return nextMessage4;
        }
        throw new HandshakeException("Cannot get next message in state: " + this.handshakeState);
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public byte[] getNextHandshakeMessage(byte[] payload) throws HandshakeException {
        if (AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$D2DSpakeEd25519Handshake$State[this.handshakeState.ordinal()] == 6) {
            byte[] nextMessage = makeSharedKeyHashMessage(false, payload);
            this.handshakeState = State.HANDSHAKE_FINISHED;
            return nextMessage;
        }
        throw new HandshakeException("Cannot send handshake message with payload in state: " + this.handshakeState);
    }

    private byte[] makeCommitmentPointMessage(boolean isInitiator) throws HandshakeException {
        try {
            BigInteger[] bigIntegerArrScalarMultiplyExtendedPoint = Ed25519.scalarMultiplyExtendedPoint(isInitiator ? KM : KN, this.passwordHash);
            this.ourCommitmentPointExtended = bigIntegerArrScalarMultiplyExtendedPoint;
            BigInteger[] bigIntegerArrAddExtendedPoints = Ed25519.addExtendedPoints(bigIntegerArrScalarMultiplyExtendedPoint, this.pointX);
            this.ourCommitmentPointExtended = bigIntegerArrAddExtendedPoints;
            this.ourCommitmentPointAffine = Ed25519.toAffine(bigIntegerArrAddExtendedPoints);
            int i = 1;
            DeviceToDeviceMessagesProto.SpakeHandshakeMessage.Builder ecPoint = DeviceToDeviceMessagesProto.SpakeHandshakeMessage.newBuilder().setEcPoint(DeviceToDeviceMessagesProto.EcPoint.newBuilder().setCurve(DeviceToDeviceMessagesProto.Curve.ED_25519).setX(ByteString.copyFrom(this.ourCommitmentPointAffine[0].toByteArray())).setY(ByteString.copyFrom(this.ourCommitmentPointAffine[1].toByteArray())).build());
            if (!isInitiator) {
                i = 2;
            }
            return ecPoint.setFlowNumber(i).build().toByteArray();
        } catch (Ed25519.Ed25519Exception e) {
            throw new HandshakeException("Could not make commitment point message", e);
        }
    }

    private void makeSharedKey(boolean isInitiator) throws HandshakeException {
        if (this.handshakeState != State.RESPONDER_START && this.handshakeState != State.INITIATOR_WAITING_FOR_RESPONDER_COMMITMENT) {
            throw new HandshakeException("Cannot make shared key in state: " + this.handshakeState);
        }
        try {
            BigInteger[] kNMP = Ed25519.scalarMultiplyExtendedPoint(isInitiator ? KN : KM, this.passwordHash);
            BigInteger[] theirPublicKey = Ed25519.subtractExtendedPoints(this.theirCommitmentPointExtended, kNMP);
            BigInteger[] sharedKeyPoint = Ed25519.scalarMultiplyExtendedPoint(theirPublicKey, this.valueX);
            this.sharedKey = hash(pointToByteArray(Ed25519.toAffine(sharedKeyPoint)));
        } catch (Ed25519.Ed25519Exception e) {
            throw new HandshakeException("Error computing shared key", e);
        }
    }

    private byte[] makeSharedKeyHashMessage(boolean isInitiator, byte[] payload) throws HandshakeException {
        DeviceToDeviceMessagesProto.SpakeHandshakeMessage.Builder handshakeMessage = DeviceToDeviceMessagesProto.SpakeHandshakeMessage.newBuilder().setHashValue(ByteString.copyFrom(computeOurKeyHash(isInitiator))).setFlowNumber(isInitiator ? 3 : 4);
        if (canSendPayloadInHandshakeMessage() && payload != null) {
            DeviceToDeviceMessagesProto.DeviceToDeviceMessage deviceToDeviceMessage = D2DConnectionContext.createDeviceToDeviceMessage(payload, 1);
            try {
                handshakeMessage.setPayload(ByteString.copyFrom(D2DCryptoOps.signcryptPayload(new TransportCryptoOps.Payload(TransportCryptoOps.PayloadType.DEVICE_TO_DEVICE_RESPONDER_HELLO_PAYLOAD, deviceToDeviceMessage.toByteArray()), new SecretKeySpec(this.sharedKey, "AES"))));
            } catch (InvalidKeyException | NoSuchAlgorithmException e) {
                throw new HandshakeException("Cannot set payload", e);
            }
        }
        return handshakeMessage.build().toByteArray();
    }

    private byte[] computeOurKeyHash(boolean z) throws HandshakeException {
        return hash(concat(new byte[]{(byte) (!z ? 1 : 0)}, pointToByteArray(this.theirCommitmentPointAffine), pointToByteArray(this.ourCommitmentPointAffine), this.sharedKey));
    }

    private byte[] computeTheirKeyHash(boolean z) throws HandshakeException {
        return hash(concat(new byte[]{z ? (byte) 1 : (byte) 0}, pointToByteArray(this.ourCommitmentPointAffine), pointToByteArray(this.theirCommitmentPointAffine), this.sharedKey));
    }

    private byte[] pointToByteArray(BigInteger[] p) {
        return concat(p[0].toByteArray(), p[1].toByteArray());
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public boolean canSendPayloadInHandshakeMessage() {
        return this.handshakeState == State.RESPONDER_AFTER_INITIATOR_HASH;
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public byte[] parseHandshakeMessage(byte[] handshakeMessage) throws HandshakeException {
        if (handshakeMessage == null || handshakeMessage.length == 0) {
            throw new HandshakeException("Handshake message too short");
        }
        byte[] payload = new byte[0];
        switch (this.handshakeState) {
            case RESPONDER_START:
                parseCommitmentMessage(handshakeMessage, false);
                makeSharedKey(false);
                this.handshakeState = State.RESPONDER_AFTER_INITIATOR_COMMITMENT;
                return payload;
            case INITIATOR_WAITING_FOR_RESPONDER_COMMITMENT:
                parseCommitmentMessage(handshakeMessage, true);
                makeSharedKey(true);
                this.handshakeState = State.INITIATOR_AFTER_RESPONDER_COMMITMENT;
                return payload;
            case RESPONDER_WAITING_FOR_INITIATOR_HASH:
                parseHashMessage(handshakeMessage, false);
                this.handshakeState = State.RESPONDER_AFTER_INITIATOR_HASH;
                return payload;
            case INITIATOR_WAITING_FOR_RESPONDER_HASH:
                byte[] payload2 = parseHashMessage(handshakeMessage, true);
                this.handshakeState = State.HANDSHAKE_FINISHED;
                return payload2;
            default:
                throw new HandshakeException("Cannot parse message in state: " + this.handshakeState);
        }
    }

    private byte[] parseHashMessage(byte[] handshakeMessage, boolean isInitiator) throws HandshakeException {
        try {
            DeviceToDeviceMessagesProto.SpakeHandshakeMessage hashMessage = DeviceToDeviceMessagesProto.SpakeHandshakeMessage.parseFrom(handshakeMessage);
            if (!hashMessage.hasFlowNumber()) {
                throw new HandshakeException("Hash message missing flow number");
            }
            int expectedFlowNumber = isInitiator ? 4 : 3;
            int actualFlowNumber = hashMessage.getFlowNumber();
            if (actualFlowNumber != expectedFlowNumber) {
                throw new HandshakeException("Hash message has flow number " + actualFlowNumber + ", but expected flow number " + expectedFlowNumber);
            }
            if (!hashMessage.hasHashValue()) {
                throw new HandshakeException("Hash message missing hash value");
            }
            byte[] theirHash = hashMessage.getHashValue().toByteArray();
            byte[] theirCorrectHash = computeTheirKeyHash(isInitiator);
            if (!constantTimeArrayEquals(theirCorrectHash, theirHash)) {
                throw new HandshakeException("Hash message had incorrect hash value");
            }
            if (isInitiator && hashMessage.hasPayload()) {
                try {
                    DeviceToDeviceMessagesProto.DeviceToDeviceMessage message = D2DCryptoOps.decryptResponderHelloMessage(new SecretKeySpec(this.sharedKey, "AES"), hashMessage.getPayload().toByteArray());
                    if (message.getSequenceNumber() != 1) {
                        throw new HandshakeException("Incorrect sequence number in responder hello");
                    }
                    return message.getMessage().toByteArray();
                } catch (SignatureException e) {
                    throw new HandshakeException("Error recovering payload from hash message", e);
                }
            }
            return new byte[0];
        } catch (InvalidProtocolBufferException e2) {
            throw new HandshakeException("Could not parse hash message", e2);
        }
    }

    private void parseCommitmentMessage(byte[] handshakeMessage, boolean isInitiator) throws HandshakeException {
        try {
            DeviceToDeviceMessagesProto.SpakeHandshakeMessage commitmentMessage = DeviceToDeviceMessagesProto.SpakeHandshakeMessage.parseFrom(handshakeMessage);
            if (!commitmentMessage.hasFlowNumber()) {
                throw new HandshakeException("Commitment message missing flow number");
            }
            if (commitmentMessage.getFlowNumber() != (isInitiator ? 2 : 1)) {
                throw new HandshakeException("Commitment message has wrong flow number");
            }
            if (!commitmentMessage.hasEcPoint()) {
                throw new HandshakeException("Commitment message missing point");
            }
            DeviceToDeviceMessagesProto.EcPoint commitmentPoint = commitmentMessage.getEcPoint();
            if (!commitmentPoint.hasCurve() || commitmentPoint.getCurve() != DeviceToDeviceMessagesProto.Curve.ED_25519) {
                throw new HandshakeException("Commitment message has wrong curve");
            }
            if (!commitmentPoint.hasX()) {
                throw new HandshakeException("Commitment point missing x coordinate");
            }
            if (!commitmentPoint.hasY()) {
                throw new HandshakeException("Commitment point missing y coordinate");
            }
            BigInteger[] bigIntegerArr = {new BigInteger(commitmentPoint.getX().toByteArray()), new BigInteger(commitmentPoint.getY().toByteArray())};
            this.theirCommitmentPointAffine = bigIntegerArr;
            try {
                Ed25519.validateAffinePoint(bigIntegerArr);
                this.theirCommitmentPointExtended = Ed25519.toExtended(this.theirCommitmentPointAffine);
            } catch (Ed25519.Ed25519Exception e) {
                throw new HandshakeException("Error validating their commitment point", e);
            }
        } catch (InvalidProtocolBufferException e2) {
            throw new HandshakeException("Could not parse commitment message", e2);
        }
    }

    @Override // com.google.security.cryptauth.lib.securegcm.D2DHandshakeContext
    public D2DConnectionContext toConnectionContext() throws HandshakeException {
        if (this.handshakeState == State.HANDSHAKE_ALREADY_USED) {
            throw new HandshakeException("Cannot reuse handshake context; is has already been used");
        }
        if (!isHandshakeComplete()) {
            throw new HandshakeException("Handshake is not complete; cannot create connection context");
        }
        this.handshakeState = State.HANDSHAKE_ALREADY_USED;
        return new D2DConnectionContextV0(new SecretKeySpec(this.sharedKey, "AES"), 1);
    }

    private static byte[] concat(byte[]... arrays) {
        int length = 0;
        for (byte[] bArr : arrays) {
            length += bArr.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    private static byte[] hash(byte[] message) throws HandshakeException {
        try {
            return MessageDigest.getInstance(SHA256).digest(message);
        } catch (NoSuchAlgorithmException e) {
            throw new HandshakeException("Error performing hash", e);
        }
    }

    private static boolean constantTimeArrayEquals(byte[] a, byte[] b) {
        if (a == null || b == null) {
            return a == b;
        }
        if (a.length != b.length) {
            return false;
        }
        byte result = 0;
        for (int i = 0; i < b.length; i++) {
            result = (byte) ((a[i] ^ b[i]) | result);
        }
        return result == 0;
    }
}
