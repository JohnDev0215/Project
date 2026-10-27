package org.chromium.mojo.bindings;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class MessageHeader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FLAGS_OFFSET = 16;
    private static final int INTERFACE_ID_OFFSET = 8;
    public static final int MESSAGE_EXPECTS_RESPONSE_FLAG = 1;
    public static final int MESSAGE_IS_RESPONSE_FLAG = 2;
    private static final int MESSAGE_WITH_REQUEST_ID_SIZE = 32;
    private static final int MESSAGE_WITH_REQUEST_ID_VERSION = 1;
    public static final int NO_FLAG = 0;
    private static final int REQUEST_ID_OFFSET = 24;
    private static final int SIMPLE_MESSAGE_SIZE = 24;
    private static final int SIMPLE_MESSAGE_VERSION = 0;
    private static final int TYPE_OFFSET = 12;
    private final DataHeader mDataHeader;
    private final int mFlags;
    private long mRequestId;
    private final int mType;
    private static final DataHeader SIMPLE_MESSAGE_STRUCT_INFO = new DataHeader(24, 0);
    private static final DataHeader MESSAGE_WITH_REQUEST_ID_STRUCT_INFO = new DataHeader(32, 1);

    private static boolean mustHaveRequestId(int i) {
        return (i & 3) != 0;
    }

    public MessageHeader(int i) {
        this.mDataHeader = SIMPLE_MESSAGE_STRUCT_INFO;
        this.mType = i;
        this.mFlags = 0;
        this.mRequestId = 0L;
    }

    public MessageHeader(int i, int i2, long j) {
        this.mDataHeader = MESSAGE_WITH_REQUEST_ID_STRUCT_INFO;
        this.mType = i;
        this.mFlags = i2;
        this.mRequestId = j;
    }

    MessageHeader(Message message) {
        Decoder decoder = new Decoder(message);
        DataHeader dataHeader = decoder.readDataHeader();
        this.mDataHeader = dataHeader;
        validateDataHeader(dataHeader);
        if (decoder.readInt(8) != 0) {
            throw new DeserializationException("Non-zero interface ID, expecting zero since associated interfaces are not yet supported.");
        }
        this.mType = decoder.readInt(12);
        int i = decoder.readInt(16);
        this.mFlags = i;
        if (mustHaveRequestId(i)) {
            if (dataHeader.size < 32) {
                throw new DeserializationException("Incorrect message size, expecting at least 32 for a message with a request identifier, but got: " + dataHeader.size);
            }
            this.mRequestId = decoder.readLong(24);
            return;
        }
        this.mRequestId = 0L;
    }

    public int getSize() {
        return this.mDataHeader.size;
    }

    public int getType() {
        return this.mType;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean hasFlag(int i) {
        return (this.mFlags & i) == i;
    }

    public boolean hasRequestId() {
        return mustHaveRequestId(this.mFlags);
    }

    public long getRequestId() {
        return this.mRequestId;
    }

    public void encode(Encoder encoder) {
        encoder.encode(this.mDataHeader);
        encoder.encode(0, 8);
        encoder.encode(getType(), 12);
        encoder.encode(getFlags(), 16);
        if (hasRequestId()) {
            encoder.encode(getRequestId(), 24);
        }
    }

    public boolean validateHeader(int i) {
        return (getFlags() & 3) == i;
    }

    public boolean validateHeader(int i, int i2) {
        return getType() == i && validateHeader(i2);
    }

    public int hashCode() {
        DataHeader dataHeader = this.mDataHeader;
        int iHashCode = ((((dataHeader == null ? 0 : dataHeader.hashCode()) + 31) * 31) + this.mFlags) * 31;
        long j = this.mRequestId;
        return ((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.mType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageHeader messageHeader = (MessageHeader) obj;
        return BindingsHelper.equals(this.mDataHeader, messageHeader.mDataHeader) && this.mFlags == messageHeader.mFlags && this.mRequestId == messageHeader.mRequestId && this.mType == messageHeader.mType;
    }

    void setRequestId(ByteBuffer byteBuffer, long j) {
        byteBuffer.putLong(24, j);
        this.mRequestId = j;
    }

    private static void validateDataHeader(DataHeader dataHeader) {
        if (dataHeader.elementsOrVersion < 0) {
            throw new DeserializationException("Incorrect number of fields, expecting at least 0, but got: " + dataHeader.elementsOrVersion);
        }
        if (dataHeader.size < 24) {
            throw new DeserializationException("Incorrect message size, expecting at least 24, but got: " + dataHeader.size);
        }
        if (dataHeader.elementsOrVersion == 0 && dataHeader.size != 24) {
            throw new DeserializationException("Incorrect message size for a message with 0 fields, expecting 24, but got: " + dataHeader.size);
        }
        if (dataHeader.elementsOrVersion == 1 && dataHeader.size != 32) {
            throw new DeserializationException("Incorrect message size for a message with 1 fields, expecting 32, but got: " + dataHeader.size);
        }
    }
}
