package com.engine.tbox.data;

import android.text.TextUtils;
import com.engine.tbox.core.utils.TypeUtils;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class ActiveCodeV2 {
    private static final int ACTIVE_CODE_LENGTH = 128;
    private int activeResult;
    private long activeTime;
    private int country;
    private long expireTime;
    private String packageName;
    private int region;
    private int providerType = 0;
    private int virtualVin = 0;
    private int providerName = 0;

    public boolean checkValid() {
        return !TextUtils.isEmpty(this.packageName);
    }

    public byte[] toByteArray() {
        String str = this.packageName;
        int len = str == null ? 0 : str.length();
        byte[] array = new byte[128];
        int pos = 0 + 1;
        array[0] = (byte) this.providerType;
        int pos2 = pos + 1;
        array[pos] = (byte) this.virtualVin;
        int pos3 = pos2 + 1;
        array[pos2] = (byte) this.providerName;
        int pos4 = pos3 + 1;
        array[pos3] = (byte) this.activeResult;
        byte[] data = TypeUtils.longToBytes(this.activeTime);
        System.arraycopy(data, 0, array, pos4, 8);
        int pos5 = pos4 + 8;
        byte[] data2 = TypeUtils.longToBytes(this.expireTime);
        System.arraycopy(data2, 0, array, pos5, 8);
        int pos6 = pos5 + 8;
        int pos7 = pos6 + 1;
        array[pos6] = (byte) this.country;
        int pos8 = pos7 + 1;
        array[pos7] = (byte) this.region;
        if (TextUtils.isEmpty(this.packageName)) {
            int i = pos8 + 1;
            array[pos8] = 0;
        } else {
            array[pos8] = (byte) this.packageName.length();
            System.arraycopy(this.packageName.getBytes(StandardCharsets.UTF_8), 0, array, pos8 + 1, len);
        }
        return array;
    }

    public int getProviderType() {
        return this.providerType;
    }

    public void setProviderType(int providerType) {
        this.providerType = providerType;
    }

    public int getVirtualVin() {
        return this.virtualVin;
    }

    public void setVirtualVin(int virtualVin) {
        this.virtualVin = virtualVin;
    }

    public int getProviderName() {
        return this.providerName;
    }

    public void setProviderName(int providerName) {
        this.providerName = providerName;
    }

    public int getActiveResult() {
        return this.activeResult;
    }

    public void setActiveResult(int activeResult) {
        this.activeResult = activeResult;
    }

    public long getActiveTime() {
        return this.activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public int getCountry() {
        return this.country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getRegion() {
        return this.region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String toString() {
        return "ActiveCodeV2 [providerType=" + this.providerType + ", virtualVin=" + this.virtualVin + ", providerName=" + this.providerName + ", activeResult=" + this.activeResult + ", activeTime=" + this.activeTime + ", expireTime=" + this.expireTime + ", country=" + this.country + ", region=" + this.region + ", packageName=" + this.packageName + "]";
    }
}
