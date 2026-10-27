package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public final class QueryVersion extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 8;
    private static final DataHeader[] VERSION_ARRAY;

    static {
        DataHeader dataHeader = new DataHeader(8, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private QueryVersion(int i) {
        super(8, i);
    }

    public QueryVersion() {
        this(0);
    }

    public static QueryVersion deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static QueryVersion decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        return new QueryVersion(decoder.readAndValidateDataHeader(VERSION_ARRAY).elementsOrVersion);
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public int hashCode() {
        return getClass().hashCode() + 31;
    }
}
