package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public final class RunMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public QueryVersion queryVersion;
    public int reserved0;
    public int reserved1;

    static {
        DataHeader dataHeader = new DataHeader(24, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private RunMessageParams(int i) {
        super(24, i);
    }

    public RunMessageParams() {
        this(0);
    }

    public static RunMessageParams deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static RunMessageParams decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        RunMessageParams runMessageParams = new RunMessageParams(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runMessageParams.reserved0 = decoder.readInt(8);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runMessageParams.reserved1 = decoder.readInt(12);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runMessageParams.queryVersion = QueryVersion.decode(decoder.readPointer(16, false));
        }
        return runMessageParams;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        Encoder encoderAtDataOffset = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        encoderAtDataOffset.encode(this.reserved0, 8);
        encoderAtDataOffset.encode(this.reserved1, 12);
        encoderAtDataOffset.encode((Struct) this.queryVersion, 16, false);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RunMessageParams runMessageParams = (RunMessageParams) obj;
        return this.reserved0 == runMessageParams.reserved0 && this.reserved1 == runMessageParams.reserved1 && BindingsHelper.equals(this.queryVersion, runMessageParams.queryVersion);
    }

    public int hashCode() {
        return ((((((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.reserved0)) * 31) + BindingsHelper.hashCode(this.reserved1)) * 31) + BindingsHelper.hashCode(this.queryVersion);
    }
}
