package kotlinx.metadata.internal.protobuf;

/* JADX INFO: loaded from: classes7.dex */
public class LazyField extends LazyFieldLite {
    private final MessageLite defaultInstance;

    public MessageLite getValue() {
        return getValue(this.defaultInstance);
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public boolean equals(Object obj) {
        return getValue().equals(obj);
    }

    public String toString() {
        return getValue().toString();
    }
}
