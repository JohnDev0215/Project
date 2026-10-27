package org.chromium.mojo.bindings.pipecontrol;

import org.chromium.mojo.bindings.BindingsHelper;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;

/* JADX INFO: loaded from: classes3.dex */
public final class PeerAssociatedEndpointClosedEvent extends Struct {
    private static final DataHeader DEFAULT_STRUCT_INFO;
    private static final int STRUCT_SIZE = 16;
    private static final DataHeader[] VERSION_ARRAY;
    public int id;

    static {
        DataHeader dataHeader = new DataHeader(16, 0);
        VERSION_ARRAY = new DataHeader[]{dataHeader};
        DEFAULT_STRUCT_INFO = dataHeader;
    }

    private PeerAssociatedEndpointClosedEvent(int i) {
        super(16, i);
    }

    public PeerAssociatedEndpointClosedEvent() {
        this(0);
    }

    public static PeerAssociatedEndpointClosedEvent deserialize(Message message) {
        return decode(new Decoder(message));
    }

    public static PeerAssociatedEndpointClosedEvent decode(Decoder decoder) {
        if (decoder == null) {
            return null;
        }
        DataHeader andValidateDataHeader = decoder.readAndValidateDataHeader(VERSION_ARRAY);
        PeerAssociatedEndpointClosedEvent peerAssociatedEndpointClosedEvent = new PeerAssociatedEndpointClosedEvent(andValidateDataHeader.elementsOrVersion);
        if (andValidateDataHeader.elementsOrVersion >= 0) {
            peerAssociatedEndpointClosedEvent.id = decoder.readInt(8);
        }
        return peerAssociatedEndpointClosedEvent;
    }

    @Override // org.chromium.mojo.bindings.Struct
    protected final void encode(Encoder encoder) {
        encoder.getEncoderAtDataOffset(DEFAULT_STRUCT_INFO).encode(this.id, 8);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.id == ((PeerAssociatedEndpointClosedEvent) obj).id;
    }

    public int hashCode() {
        return ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.id);
    }
}
