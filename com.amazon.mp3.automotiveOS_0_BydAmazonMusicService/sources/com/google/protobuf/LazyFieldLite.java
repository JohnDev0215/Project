package com.google.protobuf;

/* JADX INFO: loaded from: classes7.dex */
public class LazyFieldLite {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
    private ByteString delayedBytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile ByteString memoizedBytes;
    protected volatile MessageLite value;

    public int hashCode() {
        return 1;
    }

    public LazyFieldLite(ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        checkArguments(extensionRegistry, bytes);
        this.extensionRegistry = extensionRegistry;
        this.delayedBytes = bytes;
    }

    public LazyFieldLite() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LazyFieldLite)) {
            return false;
        }
        LazyFieldLite lazyFieldLite = (LazyFieldLite) o;
        MessageLite messageLite = this.value;
        MessageLite messageLite2 = lazyFieldLite.value;
        if (messageLite == null && messageLite2 == null) {
            return toByteString().equals(lazyFieldLite.toByteString());
        }
        if (messageLite != null && messageLite2 != null) {
            return messageLite.equals(messageLite2);
        }
        if (messageLite != null) {
            return messageLite.equals(lazyFieldLite.getValue(messageLite.getDefaultInstanceForType()));
        }
        return getValue(messageLite2.getDefaultInstanceForType()).equals(messageLite2);
    }

    public MessageLite getValue(MessageLite defaultInstance) {
        ensureInitialized(defaultInstance);
        return this.value;
    }

    public MessageLite setValue(MessageLite value) {
        MessageLite messageLite = this.value;
        this.delayedBytes = null;
        this.memoizedBytes = null;
        this.value = value;
        return messageLite;
    }

    public int getSerializedSize() {
        if (this.memoizedBytes != null) {
            return this.memoizedBytes.size();
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString.size();
        }
        if (this.value != null) {
            return this.value.getSerializedSize();
        }
        return 0;
    }

    public ByteString toByteString() {
        if (this.memoizedBytes != null) {
            return this.memoizedBytes;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString;
        }
        synchronized (this) {
            if (this.memoizedBytes != null) {
                return this.memoizedBytes;
            }
            if (this.value == null) {
                this.memoizedBytes = ByteString.EMPTY;
            } else {
                this.memoizedBytes = this.value.toByteString();
            }
            return this.memoizedBytes;
        }
    }

    protected void ensureInitialized(MessageLite defaultInstance) {
        if (this.value != null) {
            return;
        }
        synchronized (this) {
            if (this.value != null) {
                return;
            }
            try {
                if (this.delayedBytes != null) {
                    this.value = defaultInstance.getParserForType().parseFrom(this.delayedBytes, this.extensionRegistry);
                    this.memoizedBytes = this.delayedBytes;
                } else {
                    this.value = defaultInstance;
                    this.memoizedBytes = ByteString.EMPTY;
                }
            } catch (InvalidProtocolBufferException unused) {
                this.value = defaultInstance;
                this.memoizedBytes = ByteString.EMPTY;
            }
        }
    }

    private static void checkArguments(ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        if (extensionRegistry == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        }
        if (bytes == null) {
            throw new NullPointerException("found null ByteString");
        }
    }
}
