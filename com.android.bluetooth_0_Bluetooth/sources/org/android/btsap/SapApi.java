package org.android.btsap;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class SapApi {
    public static final int REQUEST = 1;
    public static final int RESPONSE = 2;
    public static final int RIL_E_CANCELLED = 4;
    public static final int RIL_E_GENERIC_FAILURE = 2;
    public static final int RIL_E_INVALID_PARAMETER = 5;
    public static final int RIL_E_RADIO_NOT_AVAILABLE = 1;
    public static final int RIL_E_REQUEST_NOT_SUPPORTED = 3;
    public static final int RIL_E_SUCCESS = 0;
    public static final int RIL_E_UNUSED = 6;
    public static final int RIL_SIM_SAP_APDU = 3;
    public static final int RIL_SIM_SAP_CONNECT = 1;
    public static final int RIL_SIM_SAP_DISCONNECT = 2;
    public static final int RIL_SIM_SAP_ERROR_RESP = 9;
    public static final int RIL_SIM_SAP_POWER = 5;
    public static final int RIL_SIM_SAP_RESET_SIM = 6;
    public static final int RIL_SIM_SAP_SET_TRANSFER_PROTOCOL = 10;
    public static final int RIL_SIM_SAP_STATUS = 7;
    public static final int RIL_SIM_SAP_TRANSFER_ATR = 4;
    public static final int RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS = 8;
    public static final int UNKNOWN = 0;
    public static final int UNKNOWN_REQ = 0;
    public static final int UNSOL_RESPONSE = 3;

    private SapApi() {
    }

    public static final class MsgHeader extends MessageMicro {
        public static final int ERROR_FIELD_NUMBER = 4;
        public static final int ID_FIELD_NUMBER = 3;
        public static final int PAYLOAD_FIELD_NUMBER = 5;
        public static final int TOKEN_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 2;
        private boolean hasError;
        private boolean hasId;
        private boolean hasPayload;
        private boolean hasToken;
        private boolean hasType;
        private int token_ = 0;
        private int type_ = 0;
        private int id_ = 0;
        private int error_ = 0;
        private ByteStringMicro payload_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public int getToken() {
            return this.token_;
        }

        public boolean hasToken() {
            return this.hasToken;
        }

        public MsgHeader setToken(int value) {
            this.hasToken = true;
            this.token_ = value;
            return this;
        }

        public MsgHeader clearToken() {
            this.hasToken = false;
            this.token_ = 0;
            return this;
        }

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public MsgHeader setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public MsgHeader clearType() {
            this.hasType = false;
            this.type_ = 0;
            return this;
        }

        public boolean hasId() {
            return this.hasId;
        }

        public int getId() {
            return this.id_;
        }

        public MsgHeader setId(int value) {
            this.hasId = true;
            this.id_ = value;
            return this;
        }

        public MsgHeader clearId() {
            this.hasId = false;
            this.id_ = 0;
            return this;
        }

        public boolean hasError() {
            return this.hasError;
        }

        public int getError() {
            return this.error_;
        }

        public MsgHeader setError(int value) {
            this.hasError = true;
            this.error_ = value;
            return this;
        }

        public MsgHeader clearError() {
            this.hasError = false;
            this.error_ = 0;
            return this;
        }

        public ByteStringMicro getPayload() {
            return this.payload_;
        }

        public boolean hasPayload() {
            return this.hasPayload;
        }

        public MsgHeader setPayload(ByteStringMicro value) {
            this.hasPayload = true;
            this.payload_ = value;
            return this;
        }

        public MsgHeader clearPayload() {
            this.hasPayload = false;
            this.payload_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final MsgHeader clear() {
            clearToken();
            clearType();
            clearId();
            clearError();
            clearPayload();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasToken && this.hasType && this.hasId && this.hasError && this.hasPayload;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasToken()) {
                output.writeFixed32(1, getToken());
            }
            if (hasType()) {
                output.writeInt32(2, getType());
            }
            if (hasId()) {
                output.writeInt32(3, getId());
            }
            if (hasError()) {
                output.writeInt32(4, getError());
            }
            if (hasPayload()) {
                output.writeBytes(5, getPayload());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasToken() ? 0 + CodedOutputStreamMicro.computeFixed32Size(1, getToken()) : 0;
            if (hasType()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getType());
            }
            if (hasId()) {
                size += CodedOutputStreamMicro.computeInt32Size(3, getId());
            }
            if (hasError()) {
                size += CodedOutputStreamMicro.computeInt32Size(4, getError());
            }
            if (hasPayload()) {
                size += CodedOutputStreamMicro.computeBytesSize(5, getPayload());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public MsgHeader mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 13) {
                    setToken(input.readFixed32());
                } else if (tag == 16) {
                    setType(input.readInt32());
                } else if (tag == 24) {
                    setId(input.readInt32());
                } else if (tag == 32) {
                    setError(input.readInt32());
                } else if (tag != 42) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setPayload(input.readBytes());
                }
            }
        }

        public static MsgHeader parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (MsgHeader) new MsgHeader().mergeFrom(data);
        }

        public static MsgHeader parseFrom(CodedInputStreamMicro input) throws IOException {
            return new MsgHeader().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_CONNECT_REQ extends MessageMicro {
        public static final int MAX_MESSAGE_SIZE_FIELD_NUMBER = 1;
        private boolean hasMaxMessageSize;
        private int maxMessageSize_ = 0;
        private int cachedSize = -1;

        public int getMaxMessageSize() {
            return this.maxMessageSize_;
        }

        public boolean hasMaxMessageSize() {
            return this.hasMaxMessageSize;
        }

        public RIL_SIM_SAP_CONNECT_REQ setMaxMessageSize(int value) {
            this.hasMaxMessageSize = true;
            this.maxMessageSize_ = value;
            return this;
        }

        public RIL_SIM_SAP_CONNECT_REQ clearMaxMessageSize() {
            this.hasMaxMessageSize = false;
            this.maxMessageSize_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_CONNECT_REQ clear() {
            clearMaxMessageSize();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasMaxMessageSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasMaxMessageSize()) {
                output.writeInt32(1, getMaxMessageSize());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasMaxMessageSize() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getMaxMessageSize()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_CONNECT_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setMaxMessageSize(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_CONNECT_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_CONNECT_REQ) new RIL_SIM_SAP_CONNECT_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_CONNECT_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_CONNECT_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_CONNECT_RSP extends MessageMicro {
        public static final int MAX_MESSAGE_SIZE_FIELD_NUMBER = 2;
        public static final int RESPONSE_FIELD_NUMBER = 1;
        public static final int RIL_E_SAP_CONNECT_FAILURE = 1;
        public static final int RIL_E_SAP_CONNECT_OK_CALL_ONGOING = 4;
        public static final int RIL_E_SAP_MSG_SIZE_TOO_LARGE = 2;
        public static final int RIL_E_SAP_MSG_SIZE_TOO_SMALL = 3;
        public static final int RIL_E_SUCCESS = 0;
        private boolean hasMaxMessageSize;
        private boolean hasResponse;
        private int response_ = 0;
        private int maxMessageSize_ = 0;
        private int cachedSize = -1;

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_CONNECT_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_CONNECT_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public int getMaxMessageSize() {
            return this.maxMessageSize_;
        }

        public boolean hasMaxMessageSize() {
            return this.hasMaxMessageSize;
        }

        public RIL_SIM_SAP_CONNECT_RSP setMaxMessageSize(int value) {
            this.hasMaxMessageSize = true;
            this.maxMessageSize_ = value;
            return this;
        }

        public RIL_SIM_SAP_CONNECT_RSP clearMaxMessageSize() {
            this.hasMaxMessageSize = false;
            this.maxMessageSize_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_CONNECT_RSP clear() {
            clearResponse();
            clearMaxMessageSize();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasResponse()) {
                output.writeInt32(1, getResponse());
            }
            if (hasMaxMessageSize()) {
                output.writeInt32(2, getMaxMessageSize());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasResponse() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponse()) : 0;
            if (hasMaxMessageSize()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getMaxMessageSize());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_CONNECT_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    setResponse(input.readInt32());
                } else if (tag != 16) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setMaxMessageSize(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_CONNECT_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_CONNECT_RSP) new RIL_SIM_SAP_CONNECT_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_CONNECT_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_CONNECT_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_DISCONNECT_REQ extends MessageMicro {
        private int cachedSize = -1;

        public final RIL_SIM_SAP_DISCONNECT_REQ clear() {
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) {
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            this.cachedSize = 0;
            return 0;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_DISCONNECT_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
            } while (parseUnknownField(input, tag));
            return this;
        }

        public static RIL_SIM_SAP_DISCONNECT_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_DISCONNECT_REQ) new RIL_SIM_SAP_DISCONNECT_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_DISCONNECT_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_DISCONNECT_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_DISCONNECT_RSP extends MessageMicro {
        private int cachedSize = -1;

        public final RIL_SIM_SAP_DISCONNECT_RSP clear() {
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) {
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            this.cachedSize = 0;
            return 0;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_DISCONNECT_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
            } while (parseUnknownField(input, tag));
            return this;
        }

        public static RIL_SIM_SAP_DISCONNECT_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_DISCONNECT_RSP) new RIL_SIM_SAP_DISCONNECT_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_DISCONNECT_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_DISCONNECT_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_DISCONNECT_IND extends MessageMicro {
        public static final int DISCONNECTTYPE_FIELD_NUMBER = 1;
        public static final int RIL_S_DISCONNECT_TYPE_GRACEFUL = 0;
        public static final int RIL_S_DISCONNECT_TYPE_IMMEDIATE = 1;
        private boolean hasDisconnectType;
        private int disconnectType_ = 0;
        private int cachedSize = -1;

        public boolean hasDisconnectType() {
            return this.hasDisconnectType;
        }

        public int getDisconnectType() {
            return this.disconnectType_;
        }

        public RIL_SIM_SAP_DISCONNECT_IND setDisconnectType(int value) {
            this.hasDisconnectType = true;
            this.disconnectType_ = value;
            return this;
        }

        public RIL_SIM_SAP_DISCONNECT_IND clearDisconnectType() {
            this.hasDisconnectType = false;
            this.disconnectType_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_DISCONNECT_IND clear() {
            clearDisconnectType();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasDisconnectType;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasDisconnectType()) {
                output.writeInt32(1, getDisconnectType());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasDisconnectType() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getDisconnectType()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_DISCONNECT_IND mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setDisconnectType(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_DISCONNECT_IND parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_DISCONNECT_IND) new RIL_SIM_SAP_DISCONNECT_IND().mergeFrom(data);
        }

        public static RIL_SIM_SAP_DISCONNECT_IND parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_DISCONNECT_IND().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_APDU_REQ extends MessageMicro {
        public static final int COMMAND_FIELD_NUMBER = 2;
        public static final int RIL_TYPE_APDU = 0;
        public static final int RIL_TYPE_APDU7816 = 1;
        public static final int TYPE_FIELD_NUMBER = 1;
        private boolean hasCommand;
        private boolean hasType;
        private int type_ = 0;
        private ByteStringMicro command_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public RIL_SIM_SAP_APDU_REQ setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public RIL_SIM_SAP_APDU_REQ clearType() {
            this.hasType = false;
            this.type_ = 0;
            return this;
        }

        public ByteStringMicro getCommand() {
            return this.command_;
        }

        public boolean hasCommand() {
            return this.hasCommand;
        }

        public RIL_SIM_SAP_APDU_REQ setCommand(ByteStringMicro value) {
            this.hasCommand = true;
            this.command_ = value;
            return this;
        }

        public RIL_SIM_SAP_APDU_REQ clearCommand() {
            this.hasCommand = false;
            this.command_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final RIL_SIM_SAP_APDU_REQ clear() {
            clearType();
            clearCommand();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasType && this.hasCommand;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasType()) {
                output.writeInt32(1, getType());
            }
            if (hasCommand()) {
                output.writeBytes(2, getCommand());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasType() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getType()) : 0;
            if (hasCommand()) {
                size += CodedOutputStreamMicro.computeBytesSize(2, getCommand());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_APDU_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    setType(input.readInt32());
                } else if (tag != 18) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setCommand(input.readBytes());
                }
            }
        }

        public static RIL_SIM_SAP_APDU_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_APDU_REQ) new RIL_SIM_SAP_APDU_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_APDU_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_APDU_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_APDU_RSP extends MessageMicro {
        public static final int APDURESPONSE_FIELD_NUMBER = 3;
        public static final int RESPONSE_FIELD_NUMBER = 2;
        public static final int RIL_E_GENERIC_FAILURE = 1;
        public static final int RIL_E_SIM_ABSENT = 4;
        public static final int RIL_E_SIM_ALREADY_POWERED_OFF = 3;
        public static final int RIL_E_SIM_NOT_READY = 2;
        public static final int RIL_E_SUCCESS = 0;
        public static final int RIL_TYPE_APDU = 0;
        public static final int RIL_TYPE_APDU7816 = 1;
        public static final int TYPE_FIELD_NUMBER = 1;
        private boolean hasApduResponse;
        private boolean hasResponse;
        private boolean hasType;
        private int type_ = 0;
        private int response_ = 0;
        private ByteStringMicro apduResponse_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public boolean hasType() {
            return this.hasType;
        }

        public int getType() {
            return this.type_;
        }

        public RIL_SIM_SAP_APDU_RSP setType(int value) {
            this.hasType = true;
            this.type_ = value;
            return this;
        }

        public RIL_SIM_SAP_APDU_RSP clearType() {
            this.hasType = false;
            this.type_ = 0;
            return this;
        }

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_APDU_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_APDU_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public ByteStringMicro getApduResponse() {
            return this.apduResponse_;
        }

        public boolean hasApduResponse() {
            return this.hasApduResponse;
        }

        public RIL_SIM_SAP_APDU_RSP setApduResponse(ByteStringMicro value) {
            this.hasApduResponse = true;
            this.apduResponse_ = value;
            return this;
        }

        public RIL_SIM_SAP_APDU_RSP clearApduResponse() {
            this.hasApduResponse = false;
            this.apduResponse_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final RIL_SIM_SAP_APDU_RSP clear() {
            clearType();
            clearResponse();
            clearApduResponse();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasType && this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasType()) {
                output.writeInt32(1, getType());
            }
            if (hasResponse()) {
                output.writeInt32(2, getResponse());
            }
            if (hasApduResponse()) {
                output.writeBytes(3, getApduResponse());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasType() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getType()) : 0;
            if (hasResponse()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getResponse());
            }
            if (hasApduResponse()) {
                size += CodedOutputStreamMicro.computeBytesSize(3, getApduResponse());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_APDU_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    setType(input.readInt32());
                } else if (tag == 16) {
                    setResponse(input.readInt32());
                } else if (tag != 26) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setApduResponse(input.readBytes());
                }
            }
        }

        public static RIL_SIM_SAP_APDU_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_APDU_RSP) new RIL_SIM_SAP_APDU_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_APDU_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_APDU_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_TRANSFER_ATR_REQ extends MessageMicro {
        private int cachedSize = -1;

        public final RIL_SIM_SAP_TRANSFER_ATR_REQ clear() {
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) {
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            this.cachedSize = 0;
            return 0;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_TRANSFER_ATR_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
            } while (parseUnknownField(input, tag));
            return this;
        }

        public static RIL_SIM_SAP_TRANSFER_ATR_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_TRANSFER_ATR_REQ) new RIL_SIM_SAP_TRANSFER_ATR_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_TRANSFER_ATR_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_TRANSFER_ATR_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_TRANSFER_ATR_RSP extends MessageMicro {
        public static final int ATR_FIELD_NUMBER = 2;
        public static final int RESPONSE_FIELD_NUMBER = 1;
        public static final int RIL_E_GENERIC_FAILURE = 1;
        public static final int RIL_E_SIM_ABSENT = 4;
        public static final int RIL_E_SIM_ALREADY_POWERED_OFF = 3;
        public static final int RIL_E_SIM_ALREADY_POWERED_ON = 18;
        public static final int RIL_E_SIM_DATA_NOT_AVAILABLE = 6;
        public static final int RIL_E_SUCCESS = 0;
        private boolean hasAtr;
        private boolean hasResponse;
        private int response_ = 0;
        private ByteStringMicro atr_ = ByteStringMicro.EMPTY;
        private int cachedSize = -1;

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_TRANSFER_ATR_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_TRANSFER_ATR_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public ByteStringMicro getAtr() {
            return this.atr_;
        }

        public boolean hasAtr() {
            return this.hasAtr;
        }

        public RIL_SIM_SAP_TRANSFER_ATR_RSP setAtr(ByteStringMicro value) {
            this.hasAtr = true;
            this.atr_ = value;
            return this;
        }

        public RIL_SIM_SAP_TRANSFER_ATR_RSP clearAtr() {
            this.hasAtr = false;
            this.atr_ = ByteStringMicro.EMPTY;
            return this;
        }

        public final RIL_SIM_SAP_TRANSFER_ATR_RSP clear() {
            clearResponse();
            clearAtr();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasResponse()) {
                output.writeInt32(1, getResponse());
            }
            if (hasAtr()) {
                output.writeBytes(2, getAtr());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasResponse() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponse()) : 0;
            if (hasAtr()) {
                size += CodedOutputStreamMicro.computeBytesSize(2, getAtr());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_TRANSFER_ATR_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    setResponse(input.readInt32());
                } else if (tag != 18) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setAtr(input.readBytes());
                }
            }
        }

        public static RIL_SIM_SAP_TRANSFER_ATR_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_TRANSFER_ATR_RSP) new RIL_SIM_SAP_TRANSFER_ATR_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_TRANSFER_ATR_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_TRANSFER_ATR_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_POWER_REQ extends MessageMicro {
        public static final int STATE_FIELD_NUMBER = 1;
        private boolean hasState;
        private boolean state_ = false;
        private int cachedSize = -1;

        public boolean getState() {
            return this.state_;
        }

        public boolean hasState() {
            return this.hasState;
        }

        public RIL_SIM_SAP_POWER_REQ setState(boolean value) {
            this.hasState = true;
            this.state_ = value;
            return this;
        }

        public RIL_SIM_SAP_POWER_REQ clearState() {
            this.hasState = false;
            this.state_ = false;
            return this;
        }

        public final RIL_SIM_SAP_POWER_REQ clear() {
            clearState();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasState;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasState()) {
                output.writeBool(1, getState());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasState() ? 0 + CodedOutputStreamMicro.computeBoolSize(1, getState()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_POWER_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setState(input.readBool());
                }
            }
        }

        public static RIL_SIM_SAP_POWER_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_POWER_REQ) new RIL_SIM_SAP_POWER_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_POWER_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_POWER_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_POWER_RSP extends MessageMicro {
        public static final int RESPONSE_FIELD_NUMBER = 1;
        public static final int RIL_E_GENERIC_FAILURE = 2;
        public static final int RIL_E_SIM_ABSENT = 11;
        public static final int RIL_E_SIM_ALREADY_POWERED_OFF = 17;
        public static final int RIL_E_SIM_ALREADY_POWERED_ON = 18;
        public static final int RIL_E_SUCCESS = 0;
        private boolean hasResponse;
        private int response_ = 0;
        private int cachedSize = -1;

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_POWER_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_POWER_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_POWER_RSP clear() {
            clearResponse();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasResponse()) {
                output.writeInt32(1, getResponse());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasResponse() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponse()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_POWER_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setResponse(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_POWER_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_POWER_RSP) new RIL_SIM_SAP_POWER_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_POWER_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_POWER_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_RESET_SIM_REQ extends MessageMicro {
        private int cachedSize = -1;

        public final RIL_SIM_SAP_RESET_SIM_REQ clear() {
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) {
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            this.cachedSize = 0;
            return 0;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_RESET_SIM_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
            } while (parseUnknownField(input, tag));
            return this;
        }

        public static RIL_SIM_SAP_RESET_SIM_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_RESET_SIM_REQ) new RIL_SIM_SAP_RESET_SIM_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_RESET_SIM_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_RESET_SIM_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_RESET_SIM_RSP extends MessageMicro {
        public static final int RESPONSE_FIELD_NUMBER = 1;
        public static final int RIL_E_GENERIC_FAILURE = 2;
        public static final int RIL_E_SIM_ABSENT = 11;
        public static final int RIL_E_SIM_ALREADY_POWERED_OFF = 17;
        public static final int RIL_E_SIM_NOT_READY = 16;
        public static final int RIL_E_SUCCESS = 0;
        private boolean hasResponse;
        private int response_ = 0;
        private int cachedSize = -1;

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_RESET_SIM_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_RESET_SIM_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_RESET_SIM_RSP clear() {
            clearResponse();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasResponse()) {
                output.writeInt32(1, getResponse());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasResponse() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponse()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_RESET_SIM_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setResponse(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_RESET_SIM_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_RESET_SIM_RSP) new RIL_SIM_SAP_RESET_SIM_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_RESET_SIM_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_RESET_SIM_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_STATUS_IND extends MessageMicro {
        public static final int RIL_SIM_STATUS_CARD_INSERTED = 4;
        public static final int RIL_SIM_STATUS_CARD_NOT_ACCESSIBLE = 2;
        public static final int RIL_SIM_STATUS_CARD_REMOVED = 3;
        public static final int RIL_SIM_STATUS_CARD_RESET = 1;
        public static final int RIL_SIM_STATUS_RECOVERED = 5;
        public static final int RIL_SIM_STATUS_UNKNOWN_ERROR = 0;
        public static final int STATUSCHANGE_FIELD_NUMBER = 1;
        private boolean hasStatusChange;
        private int statusChange_ = 0;
        private int cachedSize = -1;

        public boolean hasStatusChange() {
            return this.hasStatusChange;
        }

        public int getStatusChange() {
            return this.statusChange_;
        }

        public RIL_SIM_SAP_STATUS_IND setStatusChange(int value) {
            this.hasStatusChange = true;
            this.statusChange_ = value;
            return this;
        }

        public RIL_SIM_SAP_STATUS_IND clearStatusChange() {
            this.hasStatusChange = false;
            this.statusChange_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_STATUS_IND clear() {
            clearStatusChange();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasStatusChange;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasStatusChange()) {
                output.writeInt32(1, getStatusChange());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasStatusChange() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getStatusChange()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_STATUS_IND mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setStatusChange(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_STATUS_IND parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_STATUS_IND) new RIL_SIM_SAP_STATUS_IND().mergeFrom(data);
        }

        public static RIL_SIM_SAP_STATUS_IND parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_STATUS_IND().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ extends MessageMicro {
        private int cachedSize = -1;

        public final RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ clear() {
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) {
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            this.cachedSize = 0;
            return 0;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
            } while (parseUnknownField(input, tag));
            return this;
        }

        public static RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ) new RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP extends MessageMicro {
        public static final int CARDREADERSTATUS_FIELD_NUMBER = 2;
        public static final int RESPONSE_FIELD_NUMBER = 1;
        public static final int RIL_E_GENERIC_FAILURE = 2;
        public static final int RIL_E_SIM_DATA_NOT_AVAILABLE = 6;
        public static final int RIL_E_SUCCESS = 0;
        private boolean hasCardReaderStatus;
        private boolean hasResponse;
        private int response_ = 0;
        private int cardReaderStatus_ = 0;
        private int cachedSize = -1;

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public int getCardReaderStatus() {
            return this.cardReaderStatus_;
        }

        public boolean hasCardReaderStatus() {
            return this.hasCardReaderStatus;
        }

        public RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP setCardReaderStatus(int value) {
            this.hasCardReaderStatus = true;
            this.cardReaderStatus_ = value;
            return this;
        }

        public RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP clearCardReaderStatus() {
            this.hasCardReaderStatus = false;
            this.cardReaderStatus_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP clear() {
            clearResponse();
            clearCardReaderStatus();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasResponse()) {
                output.writeInt32(1, getResponse());
            }
            if (hasCardReaderStatus()) {
                output.writeInt32(2, getCardReaderStatus());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasResponse() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponse()) : 0;
            if (hasCardReaderStatus()) {
                size += CodedOutputStreamMicro.computeInt32Size(2, getCardReaderStatus());
            }
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag == 8) {
                    setResponse(input.readInt32());
                } else if (tag != 16) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setCardReaderStatus(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP) new RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_TRANSFER_CARD_READER_STATUS_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_ERROR_RSP extends MessageMicro {
        private int cachedSize = -1;

        public final RIL_SIM_SAP_ERROR_RSP clear() {
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) {
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            this.cachedSize = 0;
            return 0;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_ERROR_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
            } while (parseUnknownField(input, tag));
            return this;
        }

        public static RIL_SIM_SAP_ERROR_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_ERROR_RSP) new RIL_SIM_SAP_ERROR_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_ERROR_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_ERROR_RSP().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ extends MessageMicro {
        public static final int PROTOCOL_FIELD_NUMBER = 1;
        public static final int t0 = 0;
        public static final int t1 = 1;
        private boolean hasProtocol;
        private int protocol_ = 0;
        private int cachedSize = -1;

        public boolean hasProtocol() {
            return this.hasProtocol;
        }

        public int getProtocol() {
            return this.protocol_;
        }

        public RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ setProtocol(int value) {
            this.hasProtocol = true;
            this.protocol_ = value;
            return this;
        }

        public RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ clearProtocol() {
            this.hasProtocol = false;
            this.protocol_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ clear() {
            clearProtocol();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasProtocol;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasProtocol()) {
                output.writeInt32(1, getProtocol());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasProtocol() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getProtocol()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setProtocol(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ) new RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ().mergeFrom(data);
        }

        public static RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_REQ().mergeFrom(input);
        }
    }

    public static final class RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP extends MessageMicro {
        public static final int RESPONSE_FIELD_NUMBER = 1;
        public static final int RIL_E_GENERIC_FAILURE = 2;
        public static final int RIL_E_SIM_ABSENT = 11;
        public static final int RIL_E_SIM_ALREADY_POWERED_OFF = 17;
        public static final int RIL_E_SIM_NOT_READY = 16;
        public static final int RIL_E_SUCCESS = 0;
        private boolean hasResponse;
        private int response_ = 0;
        private int cachedSize = -1;

        public boolean hasResponse() {
            return this.hasResponse;
        }

        public int getResponse() {
            return this.response_;
        }

        public RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP setResponse(int value) {
            this.hasResponse = true;
            this.response_ = value;
            return this;
        }

        public RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP clearResponse() {
            this.hasResponse = false;
            this.response_ = 0;
            return this;
        }

        public final RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP clear() {
            clearResponse();
            this.cachedSize = -1;
            return this;
        }

        public final boolean isInitialized() {
            return this.hasResponse;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public void writeTo(CodedOutputStreamMicro output) throws IOException {
            if (hasResponse()) {
                output.writeInt32(1, getResponse());
            }
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getCachedSize() {
            if (this.cachedSize < 0) {
                getSerializedSize();
            }
            return this.cachedSize;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public int getSerializedSize() {
            int size = hasResponse() ? 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponse()) : 0;
            this.cachedSize = size;
            return size;
        }

        @Override // com.google.protobuf.micro.MessageMicro
        public RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP mergeFrom(CodedInputStreamMicro input) throws IOException {
            while (true) {
                int tag = input.readTag();
                if (tag == 0) {
                    return this;
                }
                if (tag != 8) {
                    if (!parseUnknownField(input, tag)) {
                        return this;
                    }
                } else {
                    setResponse(input.readInt32());
                }
            }
        }

        public static RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP parseFrom(byte[] data) throws InvalidProtocolBufferMicroException {
            return (RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP) new RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP().mergeFrom(data);
        }

        public static RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP parseFrom(CodedInputStreamMicro input) throws IOException {
            return new RIL_SIM_SAP_SET_TRANSFER_PROTOCOL_RSP().mergeFrom(input);
        }
    }
}
