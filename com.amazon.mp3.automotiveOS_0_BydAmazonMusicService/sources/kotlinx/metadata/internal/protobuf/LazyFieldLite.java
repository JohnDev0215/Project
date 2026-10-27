package kotlinx.metadata.internal.protobuf;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public class LazyFieldLite {
    private ByteString bytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty = false;
    protected volatile MessageLite value;

    public MessageLite getValue(MessageLite messageLite) {
        ensureInitialized(messageLite);
        return this.value;
    }

    public int getSerializedSize() {
        if (this.isDirty) {
            return this.value.getSerializedSize();
        }
        return this.bytes.size();
    }

    protected void ensureInitialized(MessageLite messageLite) {
        if (this.value != null) {
            return;
        }
        synchronized (this) {
            if (this.value != null) {
                return;
            }
            try {
                if (this.bytes != null) {
                    this.value = messageLite.getParserForType().parseFrom(this.bytes, this.extensionRegistry);
                } else {
                    this.value = messageLite;
                }
            } catch (IOException unused) {
            }
        }
    }
}
