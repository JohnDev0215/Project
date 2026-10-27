package com.android.bluetooth.sap;

import android.hardware.radio.V1_0.ISap;
import android.os.RemoteException;
import android.util.Log;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.android.btsap.SapApi;

/* JADX INFO: loaded from: classes.dex */
public class SapMessage {
    public static final int CON_STATUS_ERROR_CONNECTION = 1;
    public static final int CON_STATUS_ERROR_MAX_MSG_SIZE_TOO_SMALL = 3;
    public static final int CON_STATUS_ERROR_MAX_MSG_SIZE_UNSUPPORTED = 2;
    public static final int CON_STATUS_OK = 0;
    public static final int CON_STATUS_OK_ONGOING_CALL = 4;
    public static final boolean DEBUG = false;
    public static final int DISC_FORCED = 256;
    public static final int DISC_GRACEFULL = 0;
    public static final int DISC_IMMEDIATE = 1;
    public static final int DISC_RFCOMM = 257;
    public static final int ID_CONNECT_REQ = 0;
    public static final int ID_CONNECT_RESP = 1;
    public static final int ID_DISCONNECT_IND = 4;
    public static final int ID_DISCONNECT_REQ = 2;
    public static final int ID_DISCONNECT_RESP = 3;
    public static final int ID_ERROR_RESP = 18;
    public static final int ID_POWER_SIM_OFF_REQ = 9;
    public static final int ID_POWER_SIM_OFF_RESP = 10;
    public static final int ID_POWER_SIM_ON_REQ = 11;
    public static final int ID_POWER_SIM_ON_RESP = 12;
    public static final int ID_RESET_SIM_REQ = 13;
    public static final int ID_RESET_SIM_RESP = 14;
    public static final int ID_RIL_BASE = 256;
    public static final int ID_RIL_GET_SIM_STATUS_REQ = 512;
    public static final int ID_RIL_SIM_ACCESS_TEST_REQ = 513;
    public static final int ID_RIL_SIM_ACCESS_TEST_RESP = 514;
    public static final int ID_RIL_UNKNOWN = 511;
    public static final int ID_RIL_UNSOL_CONNECTED = 256;
    public static final int ID_RIL_UNSOL_DISCONNECT_IND = 258;
    public static final int ID_SET_TRANSPORT_PROTOCOL_REQ = 19;
    public static final int ID_SET_TRANSPORT_PROTOCOL_RESP = 20;
    public static final int ID_STATUS_IND = 17;
    public static final int ID_TRANSFER_APDU_REQ = 5;
    public static final int ID_TRANSFER_APDU_RESP = 6;
    public static final int ID_TRANSFER_ATR_REQ = 7;
    public static final int ID_TRANSFER_ATR_RESP = 8;
    public static final int ID_TRANSFER_CARD_READER_STATUS_REQ = 15;
    public static final int ID_TRANSFER_CARD_READER_STATUS_RESP = 16;
    public static final int INVALID_VALUE = -1;
    public static final int PARAM_ATR_ID = 6;
    public static final int PARAM_CARD_READER_STATUS_ID = 7;
    public static final int PARAM_CARD_READER_STATUS_LENGTH = 1;
    public static final int PARAM_COMMAND_APDU7816_ID = 16;
    public static final int PARAM_COMMAND_APDU_ID = 4;
    public static final int PARAM_CONNECTION_STATUS_ID = 1;
    public static final int PARAM_CONNECTION_STATUS_LENGTH = 1;
    public static final int PARAM_DISCONNECT_TYPE_ID = 3;
    public static final int PARAM_DISCONNECT_TYPE_LENGTH = 1;
    public static final int PARAM_MAX_MSG_SIZE_ID = 0;
    public static final int PARAM_MAX_MSG_SIZE_LENGTH = 2;
    public static final int PARAM_RESPONSE_APDU_ID = 5;
    public static final int PARAM_RESULT_CODE_ID = 2;
    public static final int PARAM_RESULT_CODE_LENGTH = 1;
    public static final int PARAM_STATUS_CHANGE_ID = 8;
    public static final int PARAM_STATUS_CHANGE_LENGTH = 1;
    public static final int PARAM_TRANSPORT_PROTOCOL_ID = 9;
    public static final int PARAM_TRANSPORT_PROTOCOL_LENGTH = 1;
    static final int RESPONSE_SOLICITED = 0;
    static final int RESPONSE_UNSOLICITED = 1;
    public static final int RESULT_ERROR_CARD_NOT_ACCESSIBLE = 2;
    public static final int RESULT_ERROR_CARD_POWERED_OFF = 3;
    public static final int RESULT_ERROR_CARD_POWERED_ON = 5;
    public static final int RESULT_ERROR_CARD_REMOVED = 4;
    public static final int RESULT_ERROR_DATA_NOT_AVAILABLE = 6;
    public static final int RESULT_ERROR_NOT_SUPPORTED = 7;
    public static final int RESULT_ERROR_NO_REASON = 1;
    public static final int RESULT_OK = 0;
    public static final int STATUS_CARD_INSERTED = 4;
    public static final int STATUS_CARD_NOT_ACCESSIBLE = 2;
    public static final int STATUS_CARD_REMOVED = 3;
    public static final int STATUS_CARD_RESET = 1;
    public static final int STATUS_RECOVERED = 5;
    public static final int STATUS_UNKNOWN_ERROR = 0;
    public static final String TAG = "SapMessage";
    public static final boolean TEST = false;
    public static final int TEST_MODE_DISABLE = 0;
    public static final int TEST_MODE_ENABLE = 1;
    public static final int TRANS_PROTO_T0 = 0;
    public static final int TRANS_PROTO_T1 = 1;
    public static final boolean VERBOSE = false;
    static AtomicInteger sNextSerial = new AtomicInteger(1);
    static Map<Integer, Integer> sOngoingRequests = new Hashtable();
    private byte[] mApdu;
    private byte[] mApdu7816;
    private byte[] mApduResp;
    private byte[] mAtr;
    private int mCardReaderStatus;
    private boolean mClearRilQueue;
    private int mConnectionStatus;
    private int mDisconnectionType;
    private int mMaxMsgSize;
    private int mMsgType;
    private int mResultCode;
    private boolean mSendToRil;
    private int mStatusChange;
    private int mTestMode;
    private int mTransportProtocol;

    public SapMessage(int msgType) {
        this.mSendToRil = false;
        this.mClearRilQueue = false;
        this.mMsgType = -1;
        this.mMaxMsgSize = -1;
        this.mConnectionStatus = -1;
        this.mResultCode = -1;
        this.mDisconnectionType = -1;
        this.mCardReaderStatus = -1;
        this.mStatusChange = -1;
        this.mTransportProtocol = -1;
        this.mTestMode = -1;
        this.mApdu = null;
        this.mApdu7816 = null;
        this.mApduResp = null;
        this.mAtr = null;
        this.mMsgType = msgType;
    }

    private static void resetPendingRilMessages() {
        int numMessages = sOngoingRequests.size();
        if (numMessages != 0) {
            Log.w(TAG, "Clearing message queue with size: " + numMessages);
            sOngoingRequests.clear();
        }
    }

    public static int getNumPendingRilMessages() {
        return sOngoingRequests.size();
    }

    public int getMsgType() {
        return this.mMsgType;
    }

    public void setMsgType(int msgType) {
        this.mMsgType = msgType;
    }

    public int getMaxMsgSize() {
        return this.mMaxMsgSize;
    }

    public void setMaxMsgSize(int maxMsgSize) {
        this.mMaxMsgSize = maxMsgSize;
    }

    public int getConnectionStatus() {
        return this.mConnectionStatus;
    }

    public void setConnectionStatus(int connectionStatus) {
        this.mConnectionStatus = connectionStatus;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int resultCode) {
        this.mResultCode = resultCode;
    }

    public int getDisconnectionType() {
        return this.mDisconnectionType;
    }

    public void setDisconnectionType(int disconnectionType) {
        this.mDisconnectionType = disconnectionType;
    }

    public int getCardReaderStatus() {
        return this.mCardReaderStatus;
    }

    public void setCardReaderStatus(int cardReaderStatus) {
        this.mCardReaderStatus = cardReaderStatus;
    }

    public int getStatusChange() {
        return this.mStatusChange;
    }

    public void setStatusChange(int statusChange) {
        this.mStatusChange = statusChange;
    }

    public int getTransportProtocol() {
        return this.mTransportProtocol;
    }

    public void setTransportProtocol(int transportProtocol) {
        this.mTransportProtocol = transportProtocol;
    }

    public byte[] getApdu() {
        return this.mApdu;
    }

    public void setApdu(byte[] apdu) {
        this.mApdu = apdu;
    }

    public byte[] getApdu7816() {
        return this.mApdu7816;
    }

    public void setApdu7816(byte[] apdu) {
        this.mApdu7816 = apdu;
    }

    public byte[] getApduResp() {
        return this.mApduResp;
    }

    public void setApduResp(byte[] apduResp) {
        this.mApduResp = apduResp;
    }

    public byte[] getAtr() {
        return this.mAtr;
    }

    public void setAtr(byte[] atr) {
        this.mAtr = atr;
    }

    public boolean getSendToRil() {
        return this.mSendToRil;
    }

    public void setSendToRil(boolean sendToRil) {
        this.mSendToRil = sendToRil;
    }

    public boolean getClearRilQueue() {
        return this.mClearRilQueue;
    }

    public void setClearRilQueue(boolean clearRilQueue) {
        this.mClearRilQueue = clearRilQueue;
    }

    public int getTestMode() {
        return this.mTestMode;
    }

    public void setTestMode(int testMode) {
        this.mTestMode = testMode;
    }

    private int getParamCount() {
        int paramCount = 0;
        if (this.mMaxMsgSize != -1) {
            paramCount = 0 + 1;
        }
        if (this.mConnectionStatus != -1) {
            paramCount++;
        }
        if (this.mResultCode != -1) {
            paramCount++;
        }
        if (this.mDisconnectionType != -1) {
            paramCount++;
        }
        if (this.mCardReaderStatus != -1) {
            paramCount++;
        }
        if (this.mStatusChange != -1) {
            paramCount++;
        }
        if (this.mTransportProtocol != -1) {
            paramCount++;
        }
        if (this.mApdu != null) {
            paramCount++;
        }
        if (this.mApdu7816 != null) {
            paramCount++;
        }
        if (this.mApduResp != null) {
            paramCount++;
        }
        if (this.mAtr != null) {
            return paramCount + 1;
        }
        return paramCount;
    }

    public static SapMessage readMessage(int requestType, InputStream is) {
        SapMessage newMessage = new SapMessage(requestType);
        try {
            int paramCount = is.read();
            skip(is, 2);
            if (paramCount > 0) {
                if (!newMessage.parseParameters(paramCount, is)) {
                    return null;
                }
            }
            if (requestType != 0) {
                if (requestType != 2) {
                    if (requestType != 5) {
                        if (requestType == 7 || requestType == 9 || requestType == 11 || requestType == 13 || requestType == 15) {
                            newMessage.setSendToRil(true);
                        } else {
                            if (requestType != 19) {
                                Log.e(TAG, "Unknown request type");
                                return null;
                            }
                            if (newMessage.getTransportProtocol() == -1) {
                                Log.e(TAG, "Missing TransportProtocol parameter in SET_TRANSPORT_PROTOCOL_REQ");
                                return null;
                            }
                            newMessage.setSendToRil(true);
                        }
                    } else {
                        if (newMessage.getApdu() == null && newMessage.getApdu7816() == null) {
                            Log.e(TAG, "Missing Apdu parameter in TRANSFER_APDU_REQ");
                            return null;
                        }
                        newMessage.setSendToRil(true);
                    }
                }
            } else if (newMessage.getMaxMsgSize() == -1) {
                Log.e(TAG, "Missing MaxMsgSize parameter in CONNECT_REQ");
                return null;
            }
            return newMessage;
        } catch (IOException e) {
            Log.w(TAG, e);
            return null;
        }
    }

    private static void read(InputStream is, byte[] buffer) throws IOException {
        int bytesToRead = buffer.length;
        int bytesRead = 0;
        while (bytesRead < bytesToRead) {
            int tmpBytesRead = is.read(buffer, bytesRead, bytesToRead - bytesRead);
            if (tmpBytesRead == -1) {
                throw new IOException("EOS reached while reading a byte array.");
            }
            bytesRead += tmpBytesRead;
        }
    }

    private static void skip(InputStream is, int count) throws IOException {
        for (int i = 0; i < count; i++) {
            is.read();
        }
    }

    private boolean parseParameters(int count, InputStream is) throws IOException {
        boolean success = true;
        int skipLen = 0;
        for (int i = 0; i < count; i++) {
            int paramId = is.read();
            is.read();
            int paramLength = (is.read() << 8) | is.read();
            if (paramLength % 4 != 0) {
                skipLen = 4 - (paramLength % 4);
            }
            if (paramId != 16) {
                switch (paramId) {
                    case 0:
                        if (paramLength != 2) {
                            Log.e(TAG, "Received PARAM_MAX_MSG_SIZE with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            int i2 = is.read();
                            this.mMaxMsgSize = i2;
                            this.mMaxMsgSize = (i2 << 8) | is.read();
                            skip(is, 2);
                        }
                        break;
                    case 1:
                        if (paramLength != 1) {
                            Log.e(TAG, "Received PARAM_CONNECTION_STATUS with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            this.mConnectionStatus = is.read();
                            skip(is, 3);
                        }
                        break;
                    case 2:
                        if (paramLength != 1) {
                            Log.e(TAG, "Received PARAM_RESULT_CODE with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            this.mResultCode = is.read();
                            skip(is, 3);
                        }
                        break;
                    case 3:
                        if (paramLength != 1) {
                            Log.e(TAG, "Received PARAM_DISCONNECT_TYPE_ID with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            this.mDisconnectionType = is.read();
                            skip(is, 3);
                        }
                        break;
                    case 4:
                        byte[] bArr = new byte[paramLength];
                        this.mApdu = bArr;
                        read(is, bArr);
                        skip(is, skipLen);
                        break;
                    case 5:
                        byte[] bArr2 = new byte[paramLength];
                        this.mApduResp = bArr2;
                        read(is, bArr2);
                        skip(is, skipLen);
                        break;
                    case 6:
                        byte[] bArr3 = new byte[paramLength];
                        this.mAtr = bArr3;
                        read(is, bArr3);
                        skip(is, skipLen);
                        break;
                    case 7:
                        if (paramLength != 1) {
                            Log.e(TAG, "Received PARAM_CARD_READER_STATUS with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            this.mCardReaderStatus = is.read();
                            skip(is, 3);
                        }
                        break;
                    case 8:
                        if (paramLength != 1) {
                            Log.e(TAG, "Received PARAM_STATUS_CHANGE with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            this.mStatusChange = is.read();
                            skip(is, 3);
                        }
                        break;
                    case 9:
                        if (paramLength != 1) {
                            Log.e(TAG, "Received PARAM_TRANSPORT_PROTOCOL with wrong length: " + paramLength + " skipping this parameter.");
                            skip(is, paramLength + skipLen);
                            success = false;
                        } else {
                            this.mTransportProtocol = is.read();
                            skip(is, 3);
                        }
                        break;
                    default:
                        Log.e(TAG, "Received unknown parameter ID: " + paramId + " length: " + paramLength + " skipping this parameter.");
                        skip(is, paramLength + skipLen);
                        break;
                }
            } else {
                byte[] bArr4 = new byte[paramLength];
                this.mApdu7816 = bArr4;
                read(is, bArr4);
                skip(is, skipLen);
            }
        }
        return success;
    }

    private static void writeParameter(OutputStream os, int id, int value, int length) throws IOException {
        os.write(id);
        os.write(0);
        os.write(0);
        os.write(length);
        if (length == 1) {
            os.write(value & 255);
            os.write(0);
            os.write(0);
            os.write(0);
            return;
        }
        if (length == 2) {
            os.write((value >> 8) & 255);
            os.write(value & 255);
            os.write(0);
            os.write(0);
            return;
        }
        throw new IOException("Unable to write value of length: " + length);
    }

    private static void writeParameter(OutputStream os, int id, byte[] value) throws IOException {
        os.write(id);
        os.write(0);
        os.write((value.length >> 8) & 255);
        os.write(value.length & 255);
        os.write(value);
        if (value.length % 4 != 0) {
            for (int i = 0; i < 4 - (value.length % 4); i++) {
                os.write(0);
            }
        }
    }

    public void write(OutputStream os) throws IOException {
        os.write(this.mMsgType);
        os.write(getParamCount());
        os.write(0);
        os.write(0);
        int i = this.mConnectionStatus;
        if (i != -1) {
            writeParameter(os, 1, i, 1);
        }
        int i2 = this.mMaxMsgSize;
        if (i2 != -1) {
            writeParameter(os, 0, i2, 2);
        }
        int i3 = this.mResultCode;
        if (i3 != -1) {
            writeParameter(os, 2, i3, 1);
        }
        int i4 = this.mDisconnectionType;
        if (i4 != -1) {
            writeParameter(os, 3, i4, 1);
        }
        int i5 = this.mCardReaderStatus;
        if (i5 != -1) {
            writeParameter(os, 7, i5, 1);
        }
        int i6 = this.mStatusChange;
        if (i6 != -1) {
            writeParameter(os, 8, i6, 1);
        }
        int i7 = this.mTransportProtocol;
        if (i7 != -1) {
            writeParameter(os, 9, i7, 1);
        }
        byte[] bArr = this.mApdu;
        if (bArr != null) {
            writeParameter(os, 4, bArr);
        }
        byte[] bArr2 = this.mApdu7816;
        if (bArr2 != null) {
            writeParameter(os, 16, bArr2);
        }
        byte[] bArr3 = this.mApduResp;
        if (bArr3 != null) {
            writeParameter(os, 5, bArr3);
        }
        byte[] bArr4 = this.mAtr;
        if (bArr4 != null) {
            writeParameter(os, 6, bArr4);
        }
    }

    private void writeLength(int length, CodedOutputStreamMicro out) throws IOException {
        byte[] dataLength = {0, 0, (byte) ((length >> 8) & 255), (byte) (length & 255)};
        out.writeRawBytes(dataLength);
    }

    private ArrayList<Byte> primitiveArrayToContainerArrayList(byte[] arr) {
        ArrayList<Byte> arrayList = new ArrayList<>(arr.length);
        for (byte b : arr) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    public void send(ISap sapProxy) throws RemoteException, RuntimeException {
        int type;
        ArrayList<Byte> command;
        int transportProtocol;
        int rilSerial = sNextSerial.getAndIncrement();
        Log.e(TAG, "callISapReq: called for mMsgType " + this.mMsgType + " rilSerial " + rilSerial);
        if (this.mClearRilQueue) {
            resetPendingRilMessages();
        }
        sOngoingRequests.put(Integer.valueOf(rilSerial), Integer.valueOf(this.mMsgType));
        int i = this.mMsgType;
        if (i == 0) {
            sapProxy.connectReq(rilSerial, this.mMaxMsgSize);
            return;
        }
        if (i == 2) {
            sapProxy.disconnectReq(rilSerial);
            return;
        }
        if (i == 5) {
            byte[] bArr = this.mApdu;
            if (bArr != null) {
                type = 0;
                command = primitiveArrayToContainerArrayList(bArr);
            } else {
                byte[] bArr2 = this.mApdu7816;
                if (bArr2 != null) {
                    type = 1;
                    command = primitiveArrayToContainerArrayList(bArr2);
                } else {
                    Log.e(TAG, "Missing Apdu parameter in TRANSFER_APDU_REQ");
                    throw new IllegalArgumentException();
                }
            }
            sapProxy.apduReq(rilSerial, type, command);
            return;
        }
        if (i == 7) {
            sapProxy.transferAtrReq(rilSerial);
            return;
        }
        if (i == 9) {
            sapProxy.powerReq(rilSerial, false);
            return;
        }
        if (i == 11) {
            sapProxy.powerReq(rilSerial, true);
            return;
        }
        if (i == 13) {
            sapProxy.resetSimReq(rilSerial);
            return;
        }
        if (i == 15) {
            sapProxy.transferCardReaderStatusReq(rilSerial);
            return;
        }
        if (i == 19) {
            int transportProtocol2 = this.mTransportProtocol;
            if (transportProtocol2 == 0) {
                transportProtocol = 0;
            } else if (transportProtocol2 == 1) {
                transportProtocol = 1;
            } else {
                Log.e(TAG, "Missing or invalid TransportProtocol parameter in SET_TRANSPORT_PROTOCOL_REQ: " + this.mTransportProtocol);
                throw new IllegalArgumentException();
            }
            sapProxy.setTransferProtocolReq(rilSerial, transportProtocol);
            return;
        }
        Log.e(TAG, "Unknown request type");
        throw new IllegalArgumentException();
    }

    public static SapMessage newInstance(SapApi.MsgHeader msg) throws IOException {
        return new SapMessage(msg);
    }

    private SapMessage(SapApi.MsgHeader msg) throws IOException {
        this.mSendToRil = false;
        this.mClearRilQueue = false;
        this.mMsgType = -1;
        this.mMaxMsgSize = -1;
        this.mConnectionStatus = -1;
        this.mResultCode = -1;
        this.mDisconnectionType = -1;
        this.mCardReaderStatus = -1;
        this.mStatusChange = -1;
        this.mTransportProtocol = -1;
        this.mTestMode = -1;
        this.mApdu = null;
        this.mApdu7816 = null;
        this.mApduResp = null;
        this.mAtr = null;
        try {
            int type = msg.getType();
            if (type == 2) {
                createSolicited(msg);
            } else {
                if (type == 3) {
                    createUnsolicited(msg);
                    return;
                }
                throw new IOException("Wrong msg header received: Type: " + msg.getType());
            }
        } catch (InvalidProtocolBufferMicroException e) {
            Log.w(TAG, "Error occured parsing a RIL message", e);
            throw new IOException("Error occured parsing a RIL message");
        }
    }

    private void createUnsolicited(SapApi.MsgHeader msg) throws IOException {
        int id = msg.getId();
        if (id == 2) {
            SapApi.RIL_SIM_SAP_DISCONNECT_IND indMsg = SapApi.RIL_SIM_SAP_DISCONNECT_IND.parseFrom(msg.getPayload().toByteArray());
            this.mMsgType = 258;
            if (indMsg.hasDisconnectType()) {
                setDisconnectionType(indMsg.getDisconnectType());
                return;
            } else {
                this.mMsgType = 511;
                return;
            }
        }
        if (id == 7) {
            SapApi.RIL_SIM_SAP_STATUS_IND indMsg2 = SapApi.RIL_SIM_SAP_STATUS_IND.parseFrom(msg.getPayload().toByteArray());
            this.mMsgType = 17;
            if (indMsg2.hasStatusChange()) {
                setStatusChange(indMsg2.getStatusChange());
                return;
            } else {
                this.mMsgType = 511;
                return;
            }
        }
        this.mMsgType = 511;
    }

    private void createSolicited(SapApi.MsgHeader msg) throws IOException {
        if (!msg.hasToken()) {
            throw new IOException("Token is missing");
        }
        if (!msg.hasError()) {
            throw new IOException("Error code is missing");
        }
        int serial = msg.getToken();
        int error = msg.getError();
        Integer reqType = sOngoingRequests.remove(Integer.valueOf(serial));
        if (reqType != null) {
            this.mResultCode = mapRilErrorCode(error);
            int iIntValue = reqType.intValue();
            if (iIntValue == 0) {
                SapApi.RIL_SIM_SAP_CONNECT_RSP resMsg = SapApi.RIL_SIM_SAP_CONNECT_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 1;
                if (resMsg.hasMaxMessageSize()) {
                    this.mMaxMsgSize = resMsg.getMaxMessageSize();
                }
                int response = resMsg.getResponse();
                if (response == 0) {
                    this.mConnectionStatus = 0;
                } else if (response == 1) {
                    this.mConnectionStatus = 1;
                } else if (response == 2) {
                    this.mConnectionStatus = 2;
                } else if (response == 3) {
                    this.mConnectionStatus = 3;
                } else if (response == 4) {
                    this.mConnectionStatus = 4;
                } else {
                    this.mConnectionStatus = 1;
                }
                this.mResultCode = -1;
                return;
            }
            if (iIntValue == 2) {
                this.mMsgType = 3;
                this.mResultCode = -1;
                return;
            }
            if (iIntValue == 5) {
                SapApi.RIL_SIM_SAP_APDU_RSP resMsg2 = SapApi.RIL_SIM_SAP_APDU_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 6;
                int response2 = resMsg2.getResponse();
                if (response2 == 0) {
                    this.mResultCode = 0;
                    if (resMsg2.hasApduResponse()) {
                        this.mApduResp = resMsg2.getApduResponse().toByteArray();
                        return;
                    }
                    return;
                }
                if (response2 == 1) {
                    this.mResultCode = 1;
                    return;
                }
                if (response2 == 2) {
                    this.mResultCode = 4;
                    return;
                }
                if (response2 == 3) {
                    this.mResultCode = 3;
                    return;
                } else if (response2 == 4) {
                    this.mResultCode = 2;
                    return;
                } else {
                    this.mResultCode = 1;
                    return;
                }
            }
            if (iIntValue == 7) {
                SapApi.RIL_SIM_SAP_TRANSFER_ATR_RSP resMsg3 = SapApi.RIL_SIM_SAP_TRANSFER_ATR_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 8;
                if (resMsg3.hasAtr()) {
                    this.mAtr = resMsg3.getAtr().toByteArray();
                }
                int response3 = resMsg3.getResponse();
                if (response3 == 0) {
                    this.mResultCode = 0;
                    return;
                }
                if (response3 == 1) {
                    this.mResultCode = 1;
                    return;
                }
                if (response3 == 3) {
                    this.mResultCode = 3;
                    return;
                }
                if (response3 == 4) {
                    this.mResultCode = 2;
                    return;
                }
                if (response3 == 6) {
                    this.mResultCode = 6;
                    return;
                } else if (response3 == 18) {
                    this.mResultCode = 5;
                    return;
                } else {
                    this.mResultCode = 1;
                    return;
                }
            }
            if (iIntValue == 9) {
                SapApi.RIL_SIM_SAP_POWER_RSP resMsg4 = SapApi.RIL_SIM_SAP_POWER_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 10;
                int response4 = resMsg4.getResponse();
                if (response4 == 0) {
                    this.mResultCode = 0;
                    return;
                }
                if (response4 == 2) {
                    this.mResultCode = 1;
                    return;
                }
                if (response4 == 11) {
                    this.mResultCode = 2;
                    return;
                }
                if (response4 == 17) {
                    this.mResultCode = 3;
                    return;
                } else if (response4 != 18) {
                    this.mResultCode = 1;
                    return;
                } else {
                    this.mResultCode = 5;
                    return;
                }
            }
            if (iIntValue == 11) {
                SapApi.RIL_SIM_SAP_POWER_RSP resMsg5 = SapApi.RIL_SIM_SAP_POWER_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 12;
                int response5 = resMsg5.getResponse();
                if (response5 == 0) {
                    this.mResultCode = 0;
                    return;
                }
                if (response5 == 2) {
                    this.mResultCode = 1;
                    return;
                }
                if (response5 == 11) {
                    this.mResultCode = 2;
                    return;
                }
                if (response5 == 17) {
                    this.mResultCode = 3;
                    return;
                } else if (response5 != 18) {
                    this.mResultCode = 1;
                    return;
                } else {
                    this.mResultCode = 5;
                    return;
                }
            }
            if (iIntValue == 13) {
                SapApi.RIL_SIM_SAP_RESET_SIM_RSP resMsg6 = SapApi.RIL_SIM_SAP_RESET_SIM_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 14;
                int response6 = resMsg6.getResponse();
                if (response6 == 0) {
                    this.mResultCode = 0;
                    return;
                }
                if (response6 == 2) {
                    this.mResultCode = 1;
                    return;
                }
                if (response6 == 11) {
                    this.mResultCode = 2;
                    return;
                } else if (response6 != 17) {
                    this.mResultCode = 1;
                    return;
                } else {
                    this.mResultCode = 3;
                    return;
                }
            }
            if (iIntValue == 15) {
                SapApi.RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP resMsg7 = SapApi.RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP.parseFrom(msg.getPayload().toByteArray());
                this.mMsgType = 16;
                int response7 = resMsg7.getResponse();
                if (response7 == 0) {
                    this.mResultCode = 0;
                    if (resMsg7.hasCardReaderStatus()) {
                        this.mCardReaderStatus = resMsg7.getCardReaderStatus();
                        return;
                    } else {
                        this.mResultCode = 6;
                        return;
                    }
                }
                if (response7 == 2) {
                    this.mResultCode = 1;
                    return;
                } else if (response7 != 6) {
                    this.mResultCode = 1;
                    return;
                } else {
                    this.mResultCode = 6;
                    return;
                }
            }
            if (iIntValue != 19) {
                if (iIntValue == 513) {
                    this.mMsgType = ID_RIL_SIM_ACCESS_TEST_RESP;
                    return;
                }
                Log.e(TAG, "Unknown request type: " + reqType);
                return;
            }
            SapApi.RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP resMsg8 = SapApi.RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP.parseFrom(msg.getPayload().toByteArray());
            this.mMsgType = 20;
            int response8 = resMsg8.getResponse();
            if (response8 == 0) {
                this.mResultCode = 0;
                return;
            }
            if (response8 == 2) {
                this.mResultCode = 7;
                return;
            }
            if (response8 == 11) {
                this.mResultCode = 2;
                return;
            }
            if (response8 == 16) {
                this.mResultCode = 4;
                return;
            } else if (response8 == 17) {
                this.mResultCode = 3;
                return;
            } else {
                this.mResultCode = 7;
                return;
            }
        }
        Log.w(TAG, "Solicited response received on a command not initiated - ignoring.");
    }

    private static int mapRilErrorCode(int rilErrorCode) {
        if (rilErrorCode == 0) {
            return 0;
        }
        if (rilErrorCode != 1) {
            return rilErrorCode != 3 ? 1 : 7;
        }
        return 2;
    }

    public static String getMsgTypeName(int msgType) {
        return null;
    }
}
