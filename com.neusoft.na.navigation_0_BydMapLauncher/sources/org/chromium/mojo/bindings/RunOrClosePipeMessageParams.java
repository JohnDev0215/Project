package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public final class RunOrClosePipeMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public RequireVersion requireVersion;
    public int reserved0;
    public int reserved1;

    static {
        DataHeader dataHeader = new DataHeader(24, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private RunOrClosePipeMessageParams(int i) {
        super(24, i);
    }

    public RunOrClosePipeMessageParams() {
        this(0);
    }

    public static RunOrClosePipeMessageParams deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static RunOrClosePipeMessageParams decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        RunOrClosePipeMessageParams runOrClosePipeMessageParams = new RunOrClosePipeMessageParams(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runOrClosePipeMessageParams.reserved0 = decoder.readInt(8);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runOrClosePipeMessageParams.reserved1 = decoder.readInt(12);
        }
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            runOrClosePipeMessageParams.requireVersion = RequireVersion.decode(decoder.readPointer(16, false));
        }
        return runOrClosePipeMessageParams;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        Encoder encoderAtDataOffset = encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO);
        encoderAtDataOffset.encode(this.reserved0, 8);
        encoderAtDataOffset.encode(this.reserved1, 12);
        encoderAtDataOffset.encode((Struct) this.requireVersion, 16, false);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RunOrClosePipeMessageParams runOrClosePipeMessageParams = (RunOrClosePipeMessageParams) obj;
        return this.reserved0 == runOrClosePipeMessageParams.reserved0 && this.reserved1 == runOrClosePipeMessageParams.reserved1 && BindingsHelper.equals(this.requireVersion, runOrClosePipeMessageParams.requireVersion);
    }

    public int hashCode() {
        return ((((((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.reserved0)) * 31) + BindingsHelper.hashCode(this.reserved1)) * 31) + BindingsHelper.hashCode(this.requireVersion);
    }
}
