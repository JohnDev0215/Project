package org.chromium.mojo.bindings.pipecontrol;

import org.chromium.mojo.bindings.BindingsHelper;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

/* JADX INFO: loaded from: classes3.dex */
public final class AssociatedEndpointClosedBeforeSentEvent extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int id;

    static {
        DataHeader dataHeader = new DataHeader(16, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private AssociatedEndpointClosedBeforeSentEvent(int i) {
        super(16, i);
    }

    public AssociatedEndpointClosedBeforeSentEvent() {
        this(0);
    }

    public static AssociatedEndpointClosedBeforeSentEvent deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static AssociatedEndpointClosedBeforeSentEvent decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        AssociatedEndpointClosedBeforeSentEvent associatedEndpointClosedBeforeSentEvent = new AssociatedEndpointClosedBeforeSentEvent(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            associatedEndpointClosedBeforeSentEvent.id = decoder.readInt(8);
        }
        return associatedEndpointClosedBeforeSentEvent;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO).encode(this.id, 8);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.id == ((AssociatedEndpointClosedBeforeSentEvent) obj).id;
    }

    public int hashCode() {
        return ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.id);
    }
}
