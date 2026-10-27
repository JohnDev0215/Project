package org.apache.tools.zip;

import com.ts.lib.car.VehicleVendorProperty;

/* JADX INFO: loaded from: classes.dex */
public final class ZipLong implements Cloneable {
    private long value;

    public ZipLong(long value) {
        this.value = value;
    }

    public ZipLong(byte[] bytes) {
        this(bytes, 0);
    }

    public ZipLong(byte[] bytes, int offset) {
        long j = ((long) (bytes[offset + 3] << 24)) & 4278190080L;
        this.value = j;
        long j2 = j + ((long) ((bytes[offset + 2] << 16) & VehicleVendorProperty.VehiclePropertyType.MASK));
        this.value = j2;
        long j3 = j2 + ((long) ((bytes[offset + 1] << 8) & 65280));
        this.value = j3;
        this.value = j3 + ((long) (bytes[offset] & 255));
    }

    public byte[] getBytes() {
        long j = this.value;
        byte[] result = {(byte) (255 & j), (byte) ((65280 & j) >> 8), (byte) ((16711680 & j) >> 16), (byte) ((j & 4278190080L) >> 24)};
        return result;
    }

    public long getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        return o != null && (o instanceof ZipLong) && this.value == ((ZipLong) o).getValue();
    }

    public int hashCode() {
        return (int) this.value;
    }
}
