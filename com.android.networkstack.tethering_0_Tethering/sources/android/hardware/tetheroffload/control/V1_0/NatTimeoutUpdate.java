package android.hardware.tetheroffload.control.V1_0;

import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class NatTimeoutUpdate {
    public IPv4AddrPortPair src = new IPv4AddrPortPair();
    public IPv4AddrPortPair dst = new IPv4AddrPortPair();
    public int proto = 0;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != NatTimeoutUpdate.class) {
            return false;
        }
        NatTimeoutUpdate natTimeoutUpdate = (NatTimeoutUpdate) obj;
        return HidlSupport.deepEquals(this.src, natTimeoutUpdate.src) && HidlSupport.deepEquals(this.dst, natTimeoutUpdate.dst) && this.proto == natTimeoutUpdate.proto;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.src)), Integer.valueOf(HidlSupport.deepHashCode(this.dst)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.proto))));
    }

    public final String toString() {
        return "{.src = " + this.src + ", .dst = " + this.dst + ", .proto = " + NetworkProtocol.toString(this.proto) + "}";
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(56L), 0L);
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.src.readEmbeddedFromParcel(hwParcel, hwBlob, 0 + j);
        this.dst.readEmbeddedFromParcel(hwParcel, hwBlob, 24 + j);
        this.proto = hwBlob.getInt32(j + 48);
    }
}
