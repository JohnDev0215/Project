package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public final class QueryVersionResult extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int version;

    static {
        DataHeader dataHeader = new DataHeader(16, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private QueryVersionResult(int i) {
        super(16, i);
    }

    public QueryVersionResult() {
        this(0);
    }

    public static QueryVersionResult deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static QueryVersionResult decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        QueryVersionResult queryVersionResult = new QueryVersionResult(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            queryVersionResult.version = decoder.readInt(8);
        }
        return queryVersionResult;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO).encode(this.version, 8);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.version == ((QueryVersionResult) obj).version;
    }

    public int hashCode() {
        return ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.version);
    }
}
