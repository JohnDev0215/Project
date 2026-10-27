package com.google.security.cryptauth.lib.securegcm;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.security.cryptauth.lib.securegcm.UkeyProto;
import com.google.security.cryptauth.lib.securemessage.CryptoOps;
import com.google.security.cryptauth.lib.securemessage.PublicKeyProtoUtil;
import com.google.security.cryptauth.lib.securemessage.SecureMessageProto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class Ukey2Handshake {
    private static final String NEXT_PROTOCOL = "AES_256_CBC-HMAC_SHA256";
    private static final int NONCE_LENGTH_IN_BYTES = 32;
    private static final String UTF_8 = "UTF-8";
    public static final int VERSION = 1;
    private SecretKey derivedSecretKey;
    private final HandshakeCipher handshakeCipher;
    private final HandshakeRole handshakeRole;
    private InternalState handshakeState;
    private final KeyPair ourKeyPair;
    private byte[] rawMessage1;
    private byte[] rawMessage2;
    private final HashMap<HandshakeCipher, byte[]> rawMessage3Map = new HashMap<>();
    private byte[] theirCommitment;
    private PublicKey theirPublicKey;

    private enum HandshakeRole {
        CLIENT,
        SERVER
    }

    private enum InternalState {
        CLIENT_START,
        CLIENT_WAITING_FOR_SERVER_INIT,
        CLIENT_AFTER_SERVER_INIT,
        SERVER_START,
        SERVER_AFTER_CLIENT_INIT,
        SERVER_WAITING_FOR_CLIENT_FINISHED,
        HANDSHAKE_VERIFICATION_NEEDED,
        HANDSHAKE_VERIFICATION_IN_PROGRESS,
        HANDSHAKE_FINISHED,
        HANDSHAKE_ALREADY_USED,
        HANDSHAKE_ERROR
    }

    public enum State {
        IN_PROGRESS,
        VERIFICATION_NEEDED,
        VERIFICATION_IN_PROGRESS,
        FINISHED,
        ALREADY_USED,
        ERROR
    }

    public static Ukey2Handshake forInitiator(HandshakeCipher cipher) throws HandshakeException {
        return new Ukey2Handshake(InternalState.CLIENT_START, cipher);
    }

    public static Ukey2Handshake forResponder(HandshakeCipher cipher) throws HandshakeException {
        return new Ukey2Handshake(InternalState.SERVER_START, cipher);
    }

    public enum HandshakeCipher {
        P256_SHA512(UkeyProto.Ukey2HandshakeCipher.P256_SHA512);

        private final UkeyProto.Ukey2HandshakeCipher value;

        HandshakeCipher(UkeyProto.Ukey2HandshakeCipher value) {
            if (value == UkeyProto.Ukey2HandshakeCipher.P256_SHA512) {
                this.value = value;
                return;
            }
            throw new IllegalArgumentException("Unknown cipher value: " + value);
        }

        public UkeyProto.Ukey2HandshakeCipher getValue() {
            return this.value;
        }
    }

    public static class AlertException extends Exception {
        private final UkeyProto.Ukey2Alert alertMessageToSend;

        public AlertException(String alertMessageToLog, UkeyProto.Ukey2Alert alertMessageToSend) {
            super(alertMessageToLog);
            this.alertMessageToSend = alertMessageToSend;
        }

        public byte[] getAlertMessageToSend() {
            return this.alertMessageToSend.toByteArray();
        }
    }

    private Ukey2Handshake(InternalState state, HandshakeCipher cipher) throws HandshakeException {
        if (cipher == null) {
            throwIllegalArgumentException("Invalid handshake cipher");
        }
        this.handshakeCipher = cipher;
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[state.ordinal()];
        if (i == 1) {
            this.handshakeRole = HandshakeRole.CLIENT;
        } else if (i == 2) {
            this.handshakeRole = HandshakeRole.SERVER;
        } else {
            throwIllegalStateException("Invalid handshake state");
            this.handshakeRole = null;
        }
        this.handshakeState = state;
        this.ourKeyPair = genKeyPair(cipher);
    }

    public byte[] getNextHandshakeMessage() throws HandshakeException {
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[this.handshakeState.ordinal()];
        if (i == 1) {
            this.rawMessage1 = makeUkey2Message(UkeyProto.Ukey2Message.Type.CLIENT_INIT, makeClientInitMessage());
            this.handshakeState = InternalState.CLIENT_WAITING_FOR_SERVER_INIT;
            return this.rawMessage1;
        }
        if (i == 3) {
            this.rawMessage2 = makeUkey2Message(UkeyProto.Ukey2Message.Type.SERVER_INIT, makeServerInitMessage());
            this.handshakeState = InternalState.SERVER_WAITING_FOR_CLIENT_FINISHED;
            return this.rawMessage2;
        }
        if (i == 4) {
            if (!this.rawMessage3Map.containsKey(this.handshakeCipher)) {
                throwIllegalStateException("Client state is CLIENT_AFTER_SERVER_INIT, and cipher is " + this.handshakeCipher + ", but no corresponding raw client finished message has been generated");
            }
            this.handshakeState = InternalState.HANDSHAKE_VERIFICATION_NEEDED;
            return this.rawMessage3Map.get(this.handshakeCipher);
        }
        throwIllegalStateException("Cannot get next message in state: " + this.handshakeState);
        return null;
    }

    public byte[] getVerificationString(int byteLength) throws HandshakeException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        if (byteLength < 1 || byteLength > 32) {
            throwIllegalArgumentException("Minimum length is 1 byte, max is 32 bytes");
        }
        if (this.handshakeState != InternalState.HANDSHAKE_VERIFICATION_NEEDED) {
            throwIllegalStateException("Unexpected state: " + this.handshakeState);
        }
        try {
            this.derivedSecretKey = EnrollmentCryptoOps.doKeyAgreement(this.ourKeyPair.getPrivate(), this.theirPublicKey);
        } catch (InvalidKeyException e) {
            throwHandshakeException(e);
        }
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            byteStream.write(this.rawMessage1);
            byteStream.write(this.rawMessage2);
        } catch (IOException e2) {
            throwHandshakeException(e2);
        }
        byte[] info = byteStream.toByteArray();
        byte[] salt = null;
        try {
            salt = "UKEY2 v1 auth".getBytes(UTF_8);
        } catch (UnsupportedEncodingException e3) {
            throwHandshakeException(e3);
        }
        byte[] authString = null;
        try {
            authString = CryptoOps.hkdf(this.derivedSecretKey, salt, info);
        } catch (InvalidKeyException | NoSuchAlgorithmException e4) {
            throwHandshakeException(e4);
        }
        this.handshakeState = InternalState.HANDSHAKE_VERIFICATION_IN_PROGRESS;
        return Arrays.copyOf(authString, byteLength);
    }

    public void verifyHandshake() {
        if (this.handshakeState != InternalState.HANDSHAKE_VERIFICATION_IN_PROGRESS) {
            throwIllegalStateException("Unexpected state: " + this.handshakeState);
        }
        this.handshakeState = InternalState.HANDSHAKE_FINISHED;
    }

    public void parseHandshakeMessage(byte[] handshakeMessage) throws HandshakeException, AlertException {
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[this.handshakeState.ordinal()];
        if (i == 2) {
            parseMessage1(handshakeMessage);
            this.handshakeState = InternalState.SERVER_AFTER_CLIENT_INIT;
            return;
        }
        if (i == 5) {
            parseMessage2(handshakeMessage);
            this.handshakeState = InternalState.CLIENT_AFTER_SERVER_INIT;
        } else if (i == 6) {
            parseMessage3(handshakeMessage);
            this.handshakeState = InternalState.HANDSHAKE_VERIFICATION_NEEDED;
        } else {
            throwIllegalStateException("Cannot parse message in state " + this.handshakeState);
        }
    }

    public State getHandshakeState() {
        switch (AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[this.handshakeState.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return State.IN_PROGRESS;
            case 7:
                return State.ERROR;
            case 8:
                return State.VERIFICATION_NEEDED;
            case 9:
                return State.VERIFICATION_IN_PROGRESS;
            case 10:
                return State.FINISHED;
            case 11:
                return State.ALREADY_USED;
            default:
                throwIllegalStateException("Unknown state");
                return null;
        }
    }

    public D2DConnectionContext toConnectionContext() throws HandshakeException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[this.handshakeState.ordinal()];
        if (i == 7) {
            throwIllegalStateException("Cannot make context; handshake had error");
            return null;
        }
        if (i == 8) {
            throwIllegalStateException("Handshake not verified, cannot create context");
            return null;
        }
        if (i != 10) {
            if (i == 11) {
                throwIllegalStateException("Cannot reuse handshake context; is has already been used");
                return null;
            }
            throwIllegalStateException("Handshake is not complete; cannot create connection context");
        }
        if (this.derivedSecretKey == null) {
            throwIllegalStateException("Unexpected state error: derived key is null");
        }
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            byteStream.write(this.rawMessage1);
            byteStream.write(this.rawMessage2);
        } catch (IOException e) {
            throwHandshakeException(e);
        }
        byte[] info = byteStream.toByteArray();
        byte[] salt = null;
        try {
            salt = "UKEY2 v1 next".getBytes(UTF_8);
        } catch (UnsupportedEncodingException e2) {
            throwHandshakeException(e2);
        }
        SecretKey nextProtocolKey = null;
        try {
            nextProtocolKey = new SecretKeySpec(CryptoOps.hkdf(this.derivedSecretKey, salt, info), "AES");
        } catch (InvalidKeyException | NoSuchAlgorithmException e3) {
            throwHandshakeException(e3);
        }
        SecretKey clientKey = null;
        SecretKey serverKey = null;
        try {
            clientKey = D2DCryptoOps.deriveNewKeyForPurpose(nextProtocolKey, "client");
            serverKey = D2DCryptoOps.deriveNewKeyForPurpose(nextProtocolKey, "server");
        } catch (InvalidKeyException | NoSuchAlgorithmException e4) {
            throwHandshakeException(e4);
        }
        this.handshakeState = InternalState.HANDSHAKE_ALREADY_USED;
        return new D2DConnectionContextV1(this.handshakeRole == HandshakeRole.CLIENT ? clientKey : serverKey, this.handshakeRole == HandshakeRole.CLIENT ? serverKey : clientKey, 0, 0);
    }

    private byte[] makeClientInitMessage() throws HandshakeException {
        UkeyProto.Ukey2ClientInit.Builder clientInit = UkeyProto.Ukey2ClientInit.newBuilder();
        clientInit.setVersion(1);
        clientInit.setRandom(ByteString.copyFrom(generateRandomNonce()));
        clientInit.setNextProtocol(NEXT_PROTOCOL);
        clientInit.addCipherCommitments(generateP256SHA512Commitment());
        return clientInit.build().toByteArray();
    }

    private byte[] makeServerInitMessage() {
        UkeyProto.Ukey2ServerInit.Builder serverInit = UkeyProto.Ukey2ServerInit.newBuilder();
        serverInit.setVersion(1);
        serverInit.setRandom(ByteString.copyFrom(generateRandomNonce()));
        serverInit.setHandshakeCipher(this.handshakeCipher.getValue());
        serverInit.setPublicKey(PublicKeyProtoUtil.encodePublicKey(this.ourKeyPair.getPublic()).toByteString());
        return serverInit.build().toByteArray();
    }

    private KeyPair genKeyPair(HandshakeCipher cipher) throws HandshakeException {
        if (AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$HandshakeCipher[cipher.ordinal()] == 1) {
            return PublicKeyProtoUtil.generateEcP256KeyPair();
        }
        throwHandshakeException("unknown cipher: " + cipher);
        return null;
    }

    private void parseMessage1(byte[] handshakeMessage) throws InvalidProtocolBufferException, HandshakeException, AlertException {
        UkeyProto.Ukey2Message message = null;
        try {
            message = UkeyProto.Ukey2Message.parseFrom(handshakeMessage);
        } catch (InvalidProtocolBufferException e) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE, "Can't parse message 1 " + e.getMessage());
        }
        if (!message.hasMessageType() || message.getMessageType() != UkeyProto.Ukey2Message.Type.CLIENT_INIT) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_TYPE, "Expected, but did not find ClientInit message type");
        }
        if (!message.hasMessageData()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_DATA, "Expected message data, but didn't find it");
        }
        UkeyProto.Ukey2ClientInit clientInit = null;
        try {
            clientInit = UkeyProto.Ukey2ClientInit.parseFrom(message.getMessageData());
        } catch (InvalidProtocolBufferException e2) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_DATA, "Can't parse message data into ClientInit");
        }
        if (!clientInit.hasVersion()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_VERSION, "ClientInit missing version");
        }
        if (clientInit.getVersion() != 1) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_VERSION, "ClientInit version mismatch");
        }
        if (!clientInit.hasRandom()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_RANDOM, "ClientInit missing random");
        }
        if (clientInit.getRandom().toByteArray().length != 32) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_RANDOM, "ClientInit has incorrect nonce length");
        }
        List<UkeyProto.Ukey2ClientInit.CipherCommitment> commitments = clientInit.getCipherCommitmentsList();
        if (commitments.isEmpty()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_HANDSHAKE_CIPHER, "ClientInit is missing cipher commitments");
        }
        for (UkeyProto.Ukey2ClientInit.CipherCommitment commitment : commitments) {
            if (!commitment.hasHandshakeCipher() || !commitment.hasCommitment()) {
                throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_HANDSHAKE_CIPHER, "ClientInit has improperly formatted cipher commitment");
            }
            if (commitment.getHandshakeCipher() == this.handshakeCipher.getValue()) {
                this.theirCommitment = commitment.getCommitment().toByteArray();
            }
        }
        if (this.theirCommitment == null) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_HANDSHAKE_CIPHER, "No acceptable commitments found");
        }
        if (!clientInit.hasNextProtocol() || !NEXT_PROTOCOL.equals(clientInit.getNextProtocol())) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_NEXT_PROTOCOL, "Incorrect next protocol");
        }
        this.rawMessage1 = handshakeMessage;
    }

    private void parseMessage2(byte[] handshakeMessage) throws InvalidProtocolBufferException, HandshakeException, AlertException {
        UkeyProto.Ukey2Message message = null;
        try {
            message = UkeyProto.Ukey2Message.parseFrom(handshakeMessage);
        } catch (InvalidProtocolBufferException e) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE, "Can't parse message 2 " + e.getMessage());
        }
        if (!message.hasMessageType()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_TYPE, "Expected, but did not find message type");
        }
        if (message.getMessageType() == UkeyProto.Ukey2Message.Type.ALERT) {
            this.handshakeState = InternalState.HANDSHAKE_ERROR;
            throwHandshakeMessageFromAlertMessage(message);
        }
        if (message.getMessageType() != UkeyProto.Ukey2Message.Type.SERVER_INIT) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_TYPE, "Expected, but did not find SERVER_INIT message type");
        }
        if (!message.hasMessageData()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_DATA, "Expected message data, but didn't find it");
        }
        UkeyProto.Ukey2ServerInit serverInit = null;
        try {
            serverInit = UkeyProto.Ukey2ServerInit.parseFrom(message.getMessageData());
        } catch (InvalidProtocolBufferException e2) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_DATA, "Can't parse message data into ServerInit");
        }
        if (!serverInit.hasVersion()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_VERSION, "ServerInit missing version");
        }
        if (serverInit.getVersion() != 1) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_VERSION, "ServerInit version mismatch");
        }
        if (!serverInit.hasRandom()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_RANDOM, "ServerInit missing random");
        }
        if (serverInit.getRandom().toByteArray().length != 32) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_RANDOM, "ServerInit has incorrect nonce length");
        }
        if (!serverInit.hasHandshakeCipher()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_HANDSHAKE_CIPHER, "No handshake cipher found");
        }
        HandshakeCipher serverCipher = null;
        HandshakeCipher[] handshakeCipherArrValues = HandshakeCipher.values();
        int length = handshakeCipherArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            HandshakeCipher cipher = handshakeCipherArrValues[i];
            if (cipher.getValue() != serverInit.getHandshakeCipher()) {
                i++;
            } else {
                serverCipher = cipher;
                break;
            }
        }
        if (serverCipher == null || serverCipher != this.handshakeCipher) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_HANDSHAKE_CIPHER, "No acceptable handshake cipher found");
        }
        if (!serverInit.hasPublicKey()) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_PUBLIC_KEY, "No public key found in ServerInit");
        }
        this.theirPublicKey = parseP256PublicKey(serverInit.getPublicKey().toByteArray());
        this.rawMessage2 = handshakeMessage;
    }

    private void parseMessage3(byte[] handshakeMessage) throws InvalidProtocolBufferException, HandshakeException {
        UkeyProto.Ukey2Message message = null;
        try {
            message = UkeyProto.Ukey2Message.parseFrom(handshakeMessage);
        } catch (InvalidProtocolBufferException e) {
            throwHandshakeException("Can't parse message 3", e);
        }
        if (!message.hasMessageType()) {
            throw new HandshakeException("Expected, but did not find message type");
        }
        if (message.getMessageType() == UkeyProto.Ukey2Message.Type.ALERT) {
            throwHandshakeMessageFromAlertMessage(message);
        }
        if (message.getMessageType() != UkeyProto.Ukey2Message.Type.CLIENT_FINISH) {
            throwHandshakeException("Expected, but did not find CLIENT_FINISH message type");
        }
        verifyCommitment(handshakeMessage);
        if (!message.hasMessageData()) {
            throwHandshakeException("Expected message data, but didn't find it");
        }
        UkeyProto.Ukey2ClientFinished clientFinished = null;
        try {
            clientFinished = UkeyProto.Ukey2ClientFinished.parseFrom(message.getMessageData());
        } catch (InvalidProtocolBufferException e2) {
            throwHandshakeException(e2);
        }
        if (!clientFinished.hasPublicKey()) {
            throwHandshakeException("No public key found in ClientFinished");
        }
        try {
            this.theirPublicKey = parseP256PublicKey(clientFinished.getPublicKey().toByteArray());
        } catch (AlertException e3) {
            throwHandshakeException(e3);
        }
    }

    private void verifyCommitment(byte[] handshakeMessage) throws HandshakeException {
        byte[] actualClientFinishHash = null;
        if (AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$HandshakeCipher[this.handshakeCipher.ordinal()] == 1) {
            actualClientFinishHash = sha512(handshakeMessage);
        } else {
            throwIllegalStateException("Unexpected handshakeCipher");
        }
        if (!MessageDigest.isEqual(actualClientFinishHash, this.theirCommitment)) {
            throwHandshakeException("Commitment does not match");
        }
    }

    private void throwHandshakeMessageFromAlertMessage(UkeyProto.Ukey2Message message) throws InvalidProtocolBufferException, HandshakeException {
        if (message.hasMessageData()) {
            UkeyProto.Ukey2Alert alert = null;
            try {
                alert = UkeyProto.Ukey2Alert.parseFrom(message.getMessageData());
            } catch (InvalidProtocolBufferException e) {
                throwHandshakeException("Cannot parse alert message", e);
            }
            if (alert.hasType() && alert.hasErrorMessage()) {
                throwHandshakeException("Received Alert message. Type: " + alert.getType() + " Error Message: " + alert.getErrorMessage());
            } else if (alert.hasType()) {
                throwHandshakeException("Received Alert message. Type: " + alert.getType());
            }
        }
        throwHandshakeException("Received empty Alert Message");
    }

    private PublicKey parseP256PublicKey(byte[] encodedPublicKey) throws HandshakeException, AlertException {
        try {
            return PublicKeyProtoUtil.parsePublicKey(SecureMessageProto.GenericPublicKey.parseFrom(encodedPublicKey));
        } catch (InvalidProtocolBufferException | InvalidKeySpecException e) {
            throwAlertException(UkeyProto.Ukey2Alert.AlertType.BAD_PUBLIC_KEY, "Cannot parse public key: " + e.getMessage());
            return null;
        }
    }

    private UkeyProto.Ukey2ClientInit.CipherCommitment generateP256SHA512Commitment() throws HandshakeException {
        if (!this.rawMessage3Map.containsKey(HandshakeCipher.P256_SHA512)) {
            generateP256SHA512ClientFinished(this.ourKeyPair);
        }
        UkeyProto.Ukey2ClientInit.CipherCommitment.Builder cipherCommitment = UkeyProto.Ukey2ClientInit.CipherCommitment.newBuilder();
        cipherCommitment.setHandshakeCipher(UkeyProto.Ukey2HandshakeCipher.P256_SHA512);
        cipherCommitment.setCommitment(ByteString.copyFrom(sha512(this.rawMessage3Map.get(HandshakeCipher.P256_SHA512))));
        return cipherCommitment.build();
    }

    private UkeyProto.Ukey2ClientFinished generateP256SHA512ClientFinished(KeyPair p256KeyPair) {
        byte[] encodedKey = PublicKeyProtoUtil.encodePublicKey(p256KeyPair.getPublic()).toByteArray();
        UkeyProto.Ukey2ClientFinished.Builder clientFinished = UkeyProto.Ukey2ClientFinished.newBuilder();
        clientFinished.setPublicKey(ByteString.copyFrom(encodedKey));
        this.rawMessage3Map.put(HandshakeCipher.P256_SHA512, makeUkey2Message(UkeyProto.Ukey2Message.Type.CLIENT_FINISH, clientFinished.build().toByteArray()));
        return clientFinished.build();
    }

    private byte[] makeUkey2Message(UkeyProto.Ukey2Message.Type messageType, byte[] messageData) {
        UkeyProto.Ukey2Message.Builder message = UkeyProto.Ukey2Message.newBuilder();
        int i = AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Message$Type[messageType.ordinal()];
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            throwIllegalArgumentException("Invalid message type: " + messageType);
        }
        message.setMessageType(messageType);
        if (messageType != UkeyProto.Ukey2Message.Type.ALERT) {
            if (messageData == null || messageData.length == 0) {
                throwIllegalArgumentException("Cannot send empty message data for non-alert messages");
            }
            message.setMessageData(ByteString.copyFrom(messageData));
        }
        return message.build().toByteArray();
    }

    /* JADX INFO: renamed from: com.google.security.cryptauth.lib.securegcm.Ukey2Handshake$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$HandshakeCipher;
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState;
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType;
        static final /* synthetic */ int[] $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Message$Type;

        static {
            int[] iArr = new int[UkeyProto.Ukey2Alert.AlertType.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType = iArr;
            try {
                iArr[UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.INCORRECT_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_MESSAGE_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_VERSION.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_RANDOM.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_HANDSHAKE_CIPHER.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_NEXT_PROTOCOL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.BAD_PUBLIC_KEY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[UkeyProto.Ukey2Alert.AlertType.INTERNAL_ERROR.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            int[] iArr2 = new int[UkeyProto.Ukey2Message.Type.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Message$Type = iArr2;
            try {
                iArr2[UkeyProto.Ukey2Message.Type.ALERT.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Message$Type[UkeyProto.Ukey2Message.Type.CLIENT_INIT.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Message$Type[UkeyProto.Ukey2Message.Type.SERVER_INIT.ordinal()] = 3;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Message$Type[UkeyProto.Ukey2Message.Type.CLIENT_FINISH.ordinal()] = 4;
            } catch (NoSuchFieldError e14) {
            }
            int[] iArr3 = new int[HandshakeCipher.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$HandshakeCipher = iArr3;
            try {
                iArr3[HandshakeCipher.P256_SHA512.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            int[] iArr4 = new int[InternalState.values().length];
            $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState = iArr4;
            try {
                iArr4[InternalState.CLIENT_START.ordinal()] = 1;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.SERVER_START.ordinal()] = 2;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.SERVER_AFTER_CLIENT_INIT.ordinal()] = 3;
            } catch (NoSuchFieldError e18) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.CLIENT_AFTER_SERVER_INIT.ordinal()] = 4;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.CLIENT_WAITING_FOR_SERVER_INIT.ordinal()] = 5;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.SERVER_WAITING_FOR_CLIENT_FINISHED.ordinal()] = 6;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.HANDSHAKE_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError e22) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.HANDSHAKE_VERIFICATION_NEEDED.ordinal()] = 8;
            } catch (NoSuchFieldError e23) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.HANDSHAKE_VERIFICATION_IN_PROGRESS.ordinal()] = 9;
            } catch (NoSuchFieldError e24) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.HANDSHAKE_FINISHED.ordinal()] = 10;
            } catch (NoSuchFieldError e25) {
            }
            try {
                $SwitchMap$com$google$security$cryptauth$lib$securegcm$Ukey2Handshake$InternalState[InternalState.HANDSHAKE_ALREADY_USED.ordinal()] = 11;
            } catch (NoSuchFieldError e26) {
            }
        }
    }

    private UkeyProto.Ukey2Alert makeAlertMessage(UkeyProto.Ukey2Alert.AlertType alertType, @Nullable String loggableAdditionalData) throws HandshakeException {
        switch (AnonymousClass1.$SwitchMap$com$google$security$cryptauth$lib$securegcm$UkeyProto$Ukey2Alert$AlertType[alertType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                break;
            default:
                throwHandshakeException("Unknown alert type: " + alertType);
                break;
        }
        UkeyProto.Ukey2Alert.Builder alert = UkeyProto.Ukey2Alert.newBuilder();
        alert.setType(alertType);
        if (loggableAdditionalData != null) {
            alert.setErrorMessage(loggableAdditionalData);
        }
        return alert.build();
    }

    private static byte[] generateRandomNonce() {
        SecureRandom rng = new SecureRandom();
        byte[] randomNonce = new byte[32];
        rng.nextBytes(randomNonce);
        return randomNonce;
    }

    private byte[] sha512(byte[] input) throws HandshakeException {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            return sha512.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throwHandshakeException("No security provider initialized yet?", e);
            return null;
        }
    }

    private void throwAlertException(UkeyProto.Ukey2Alert.AlertType alertType, String alertLogStatement) throws HandshakeException, AlertException {
        this.handshakeState = InternalState.HANDSHAKE_ERROR;
        throw new AlertException(alertLogStatement, makeAlertMessage(alertType, alertLogStatement));
    }

    private void throwHandshakeException(String logMessage) throws HandshakeException {
        this.handshakeState = InternalState.HANDSHAKE_ERROR;
        throw new HandshakeException(logMessage);
    }

    private void throwHandshakeException(Exception e) throws HandshakeException {
        this.handshakeState = InternalState.HANDSHAKE_ERROR;
        throw new HandshakeException(e);
    }

    private void throwHandshakeException(String logMessage, Exception e) throws HandshakeException {
        this.handshakeState = InternalState.HANDSHAKE_ERROR;
        throw new HandshakeException(logMessage, e);
    }

    private void throwIllegalStateException(String logMessage) {
        this.handshakeState = InternalState.HANDSHAKE_ERROR;
        throw new IllegalStateException(logMessage);
    }

    private void throwIllegalArgumentException(String logMessage) {
        this.handshakeState = InternalState.HANDSHAKE_ERROR;
        throw new IllegalArgumentException(logMessage);
    }
}
