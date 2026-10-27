package org.apache.tools.zip;

/* JADX INFO: loaded from: classes.dex */
public final class ZipShort implements Cloneable {
    private int value;

    public ZipShort(int value) {
        this.value = value;
    }

    public ZipShort(byte[] bytes) {
        this(bytes, 0);
    }

    public ZipShort(byte[] bytes, int offset) {
        int i = (bytes[offset + 1] << 8) & 65280;
        this.value = i;
        this.value = i + (bytes[offset] & 255);
    }

    public byte[] getBytes() {
        int i = this.value;
        byte[] result = {(byte) (i & 255), (byte) ((i & 65280) >> 8)};
        return result;
    }

    public int getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        return o != null && (o instanceof ZipShort) && this.value == ((ZipShort) o).getValue();
    }

    public int hashCode() {
        return this.value;
    }
}
