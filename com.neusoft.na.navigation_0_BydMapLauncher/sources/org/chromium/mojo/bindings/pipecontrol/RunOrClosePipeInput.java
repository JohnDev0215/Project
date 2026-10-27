package org.chromium.mojo.bindings.pipecontrol;

import org.chromium.mojo.bindings.BindingsHelper;
import org.chromium.mojo.bindings.DataHeader;
import org.chromium.mojo.bindings.Decoder;
import org.chromium.mojo.bindings.Encoder;
import org.chromium.mojo.bindings.Message;
import org.chromium.mojo.bindings.Struct;
import org.chromium.mojo.bindings.Union;

/* JADX INFO: loaded from: classes3.dex */
public final class RunOrClosePipeInput extends Union {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private AssociatedEndpointClosedBeforeSentEvent mAssociatedEndpointClosedBeforeSentEvent;
    private PeerAssociatedEndpointClosedEvent mPeerAssociatedEndpointClosedEvent;
    private int mTag_ = -1;

    public static final class Tag {
        public static final int AssociatedEndpointClosedBeforeSentEvent = 1;
        public static final int PeerAssociatedEndpointClosedEvent = 0;
    }

    public int which() {
        return this.mTag_;
    }

    public boolean isUnknown() {
        return this.mTag_ == -1;
    }

    public void setPeerAssociatedEndpointClosedEvent(PeerAssociatedEndpointClosedEvent peerAssociatedEndpointClosedEvent) {
        this.mTag_ = 0;
        this.mPeerAssociatedEndpointClosedEvent = peerAssociatedEndpointClosedEvent;
    }

    public PeerAssociatedEndpointClosedEvent getPeerAssociatedEndpointClosedEvent() {
        return this.mPeerAssociatedEndpointClosedEvent;
    }

    public void setAssociatedEndpointClosedBeforeSentEvent(AssociatedEndpointClosedBeforeSentEvent associatedEndpointClosedBeforeSentEvent) {
        this.mTag_ = 1;
        this.mAssociatedEndpointClosedBeforeSentEvent = associatedEndpointClosedBeforeSentEvent;
    }

    public AssociatedEndpointClosedBeforeSentEvent getAssociatedEndpointClosedBeforeSentEvent() {
        return this.mAssociatedEndpointClosedBeforeSentEvent;
    }

    @Override // org.chromium.mojo.bindings.Union
    protected final void encode(Encoder encoder, int i) {
        encoder.encode(16, i);
        encoder.encode(this.mTag_, i + 4);
        int i2 = this.mTag_;
        if (i2 == 0) {
            encoder.encode((Struct) this.mPeerAssociatedEndpointClosedEvent, i + 8, false);
        } else {
            if (i2 != 1) {
                return;
            }
            encoder.encode((Struct) this.mAssociatedEndpointClosedBeforeSentEvent, i + 8, false);
        }
    }

    public static RunOrClosePipeInput deserialize(Message message) {
        return decode(new Decoder(message).decoderForSerializedUnion(), 0);
    }

    public static final RunOrClosePipeInput decode(Decoder decoder, int i) {
        DataHeader dataHeaderForUnion = decoder.readDataHeaderForUnion(i);
        if (dataHeaderForUnion.size == 0) {
            return null;
        }
        RunOrClosePipeInput runOrClosePipeInput = new RunOrClosePipeInput();
        int i2 = dataHeaderForUnion.elementsOrVersion;
        if (i2 == 0) {
            runOrClosePipeInput.mPeerAssociatedEndpointClosedEvent = PeerAssociatedEndpointClosedEvent.decode(decoder.readPointer(i + 8, false));
            runOrClosePipeInput.mTag_ = 0;
        } else if (i2 == 1) {
            runOrClosePipeInput.mAssociatedEndpointClosedBeforeSentEvent = AssociatedEndpointClosedBeforeSentEvent.decode(decoder.readPointer(i + 8, false));
            runOrClosePipeInput.mTag_ = 1;
        }
        return runOrClosePipeInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RunOrClosePipeInput runOrClosePipeInput = (RunOrClosePipeInput) obj;
        int i = this.mTag_;
        if (i != runOrClosePipeInput.mTag_) {
            return false;
        }
        if (i == 0) {
            return BindingsHelper.equals(this.mPeerAssociatedEndpointClosedEvent, runOrClosePipeInput.mPeerAssociatedEndpointClosedEvent);
        }
        if (i != 1) {
            return false;
        }
        return BindingsHelper.equals(this.mAssociatedEndpointClosedBeforeSentEvent, runOrClosePipeInput.mAssociatedEndpointClosedBeforeSentEvent);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        int iHashCode2 = ((getClass().hashCode() + 31) * 31) + BindingsHelper.hashCode(this.mTag_);
        int i2 = this.mTag_;
        if (i2 == 0) {
            i = iHashCode2 * 31;
            iHashCode = BindingsHelper.hashCode(this.mPeerAssociatedEndpointClosedEvent);
        } else {
            if (i2 != 1) {
                return iHashCode2;
            }
            i = iHashCode2 * 31;
            iHashCode = BindingsHelper.hashCode(this.mAssociatedEndpointClosedBeforeSentEvent);
        }
        return i + iHashCode;
    }
}
