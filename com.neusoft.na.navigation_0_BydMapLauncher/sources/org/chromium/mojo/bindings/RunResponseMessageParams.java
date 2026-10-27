package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public final class RunResponseMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public QueryVersionResult queryVersionResult;
    public int reserved0;
    public int reserved1;

    static {
        DataHeader dataHeader = new DataHeader(24, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private RunResponseMessageParams(int i) {
        super(24, i);
    }

    public RunResponseMessageParams() {
        this(0);
    }

    public static RunResponseMessageParams deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static RunResponseMessageParams decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        RunResponseMessageParams runResponseMessageParams = new RunResponseMessageParams(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runResponseMessageParams.reserved0 = decoder.readInt(8);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runResponseMessageParams.reserved1 = decoder.readInt(12);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runResponseMessageParams.queryVersionResult = QueryVersionResult.decode(decoder.readPointer(16, false));
        }
        return runResponseMessageParams;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        Encoder encoderAtDataOffset = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        encoderAtDataOffset.encode(this.reserved0, 8);
        encoderAtDataOffset.encode(this.reserved1, 12);
        encoderAtDataOffset.encode((Struct) this.queryVersionResult, 16, false);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RunResponseMessageParams runResponseMessageParams = (RunResponseMessageParams) obj;
        return this.reserved0 == runResponseMessageParams.reserved0 && this.reserved1 == runResponseMessageParams.reserved1 && BindingsHelper.equals(this.queryVersionResult, runResponseMessageParams.queryVersionResult);
    }

    public int hashCode() {
        return ((((((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.reserved0)) * 31) + BindingsHelper.hashCode(this.reserved1)) * 31) + BindingsHelper.hashCode(this.queryVersionResult);
    }
}
