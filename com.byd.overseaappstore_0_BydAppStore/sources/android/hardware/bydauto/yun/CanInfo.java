package android.hardware.bydauto.yun;

import java.util.Arrays;
import java.util.Objects;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class CanInfo {
    public static final int S_HASH_CODE = 31;
    private byte[] canId;
    private byte childId;
    private byte[] data;
    private byte[] mask;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CanInfo)) {
            return false;
        }
        CanInfo canInfo = (CanInfo) obj;
        return this.childId == canInfo.childId && Arrays.equals(this.canId, canInfo.canId);
    }

    public byte[] getCanId() {
        return this.canId;
    }

    public byte getChildId() {
        return this.childId;
    }

    public byte[] getData() {
        return this.data;
    }

    public byte[] getMask() {
        return this.mask;
    }

    public int hashCode() {
        return Arrays.hashCode(this.canId) + (Objects.hash(Byte.valueOf(this.childId)) * 31);
    }

    public void setCanId(byte[] bArr) {
        this.canId = bArr;
    }

    public void setChildId(byte b) {
        this.childId = b;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setMask(byte[] bArr) {
        this.mask = bArr;
    }

    public String toString() {
        StringBuilder sbK = e.k("CanInfo{canId=");
        sbK.append(Arrays.toString(this.canId));
        sbK.append(", childId=");
        sbK.append((int) this.childId);
        sbK.append(", mask=");
        sbK.append(Arrays.toString(this.mask));
        sbK.append(", data=");
        sbK.append(Arrays.toString(this.data));
        sbK.append('}');
        return sbK.toString();
    }
}
