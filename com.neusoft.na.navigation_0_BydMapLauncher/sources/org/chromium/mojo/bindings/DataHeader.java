package org.chromium.mojo.bindings;

/* JADX INFO: loaded from: classes3.dex */
public final class DataHeader {
    public static final int ELEMENTS_OR_VERSION_OFFSET = 4;
    public static final int HEADER_SIZE = 8;
    public static final int SIZE_OFFSET = 0;
    public final int elementsOrVersion;
    public final int size;

    public DataHeader(int i, int i2) {
        this.size = i;
        this.elementsOrVersion = i2;
    }

    public int hashCode() {
        return ((this.elementsOrVersion + 31) * 31) + this.size;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DataHeader dataHeader = (DataHeader) obj;
        return this.elementsOrVersion == dataHeader.elementsOrVersion && this.size == dataHeader.size;
    }
}
