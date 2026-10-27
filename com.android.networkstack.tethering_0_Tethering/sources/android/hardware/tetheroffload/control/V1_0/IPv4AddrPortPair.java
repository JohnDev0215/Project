package android.hardware.tetheroffload.control.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IPv4AddrPortPair {
    public String addr = new String();
    public short port = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != IPv4AddrPortPair.class) {
            return false;
        }
        IPv4AddrPortPair iPv4AddrPortPair = (IPv4AddrPortPair) obj;
        return HidlSupport.deepEquals(this.addr, iPv4AddrPortPair.addr) && this.port == iPv4AddrPortPair.port;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.addr)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.port))));
    }

    public final String toString() {
        return "{.addr = " + this.addr + ", .port = " + ((int) this.port) + "}";
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        long j2 = j + 0;
        this.addr = hwBlob.getString(j2);
        hwParcel.readEmbeddedBuffer(r6.getBytes().length + 1, hwBlob.handle(), j2 + 0, false);
        this.port = hwBlob.getInt16(j + 16);
    }
}
