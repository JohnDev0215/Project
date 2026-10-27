package org.chromium.mojo.bindings.pipecontrol;

import org.chromium.mojo.bindings.BindingsHelper;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.bindings.Union;

/* JADX INFO: loaded from: classes3.dex */
public final class RunOrClosePipeMessageParams extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 24;
    private static final DataHeader[] VERSION_ARRAY;
    public RunOrClosePipeInput input;

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
            runOrClosePipeMessageParams.input = RunOrClosePipeInput.decode(decoder, 8);
        }
        return runOrClosePipeMessageParams;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO).encode((Union) this.input, 8, false);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && BindingsHelper.equals(this.input, ((RunOrClosePipeMessageParams) obj).input);
    }

    public int hashCode() {
        return ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.input);
    }
}
