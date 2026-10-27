package android.hardware.bydauto.yun;

import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CanInfo {
    public static final int S_HASH_CODE = 31;
    private byte[] canId;
    private byte childId;
    private byte[] data;
    private byte[] mask;

    public byte[] getCanId() {
        return this.canId;
    }

    public void setCanId(byte[] canId) {
        this.canId = canId;
    }

    public byte getChildId() {
        return this.childId;
    }

    public void setChildId(byte childId) {
        this.childId = childId;
    }

    public byte[] getMask() {
        return this.mask;
    }

    public void setMask(byte[] mask) {
        this.mask = mask;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int hashCode() {
        int result = Objects.hash(Byte.valueOf(this.childId));
        return (result * 31) + Arrays.hashCode(this.canId);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CanInfo)) {
            return false;
        }
        CanInfo canInfo = (CanInfo) o;
        return this.childId == canInfo.childId && Arrays.equals(this.canId, canInfo.canId);
    }

    public String toString() {
        return "CanInfo{canId=" + Arrays.toString(this.canId) + ", childId=" + ((int) this.childId) + ", mask=" + Arrays.toString(this.mask) + ", data=" + Arrays.toString(this.data) + '}';
    }
}
