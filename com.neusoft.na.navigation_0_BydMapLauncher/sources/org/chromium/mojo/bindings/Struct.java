package org.chromium.mojo.bindings;

import org.chromium.mojo.system.Core;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Struct {
    private final int mEncodedBaseSize;
    private final int mVersion;

    protected abstract void encode(Encoder encoder);

    protected Struct(int i, int i2) {
        this.mEncodedBaseSize = i;
        this.mVersion = i2;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public Message serialize(Core core) {
        Encoder encoder = new Encoder(core, this.mEncodedBaseSize);
        encode(encoder);
        return encoder.getMessage();
    }

    public ServiceMessage serializeWithHeader(Core core, MessageHeader messageHeader) {
        Encoder encoder = new Encoder(core, this.mEncodedBaseSize + messageHeader.getSize());
        messageHeader.encode(encoder);
        encode(encoder);
        return new ServiceMessage(encoder.getMessage(), messageHeader);
    }
}
