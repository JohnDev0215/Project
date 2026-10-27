package android.net.dhcp;

import android.net.MacAddress;
import android.net.networkstack.util.HexDump;
import android.net.util.IpMask;
import android.text.TextUtils;
import com.android.networkstack.util.Inet4AddressUtils;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class DhcpLease {
    private final byte[] mClientId;
    private final long mExpTime;
    private final String mHostname;
    private final MacAddress mHwAddr;
    private final Inet4Address mNetAddr;
    private final int mPrefixLength;

    public DhcpLease(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address, int i, long j, String str) {
        this.mClientId = bArr == null ? null : Arrays.copyOf(bArr, bArr.length);
        this.mHwAddr = macAddress;
        this.mNetAddr = inet4Address;
        this.mPrefixLength = i;
        this.mExpTime = j;
        this.mHostname = str;
    }

    public String getHostname() {
        return this.mHostname;
    }

    public Inet4Address getNetAddr() {
        return this.mNetAddr;
    }

    public long getExpTime() {
        return this.mExpTime;
    }

    public DhcpLease renewedLease(long j, String str) {
        byte[] bArr = this.mClientId;
        MacAddress macAddress = this.mHwAddr;
        Inet4Address inet4Address = this.mNetAddr;
        int i = this.mPrefixLength;
        long jMax = Math.max(j, this.mExpTime);
        if (str == null) {
            str = this.mHostname;
        }
        return new DhcpLease(bArr, macAddress, inet4Address, i, jMax, str);
    }

    public boolean matchesClient(byte[] bArr, MacAddress macAddress) {
        byte[] bArr2 = this.mClientId;
        if (bArr2 != null) {
            return Arrays.equals(bArr2, bArr);
        }
        return bArr == null && this.mHwAddr.equals(macAddress);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DhcpLease)) {
            return false;
        }
        DhcpLease dhcpLease = (DhcpLease) obj;
        return Arrays.equals(this.mClientId, dhcpLease.mClientId) && this.mHwAddr.equals(dhcpLease.mHwAddr) && this.mNetAddr.equals(dhcpLease.mNetAddr) && this.mPrefixLength == dhcpLease.mPrefixLength && this.mExpTime == dhcpLease.mExpTime && TextUtils.equals(this.mHostname, dhcpLease.mHostname);
    }

    public int hashCode() {
        return Objects.hash(this.mClientId, this.mHwAddr, this.mNetAddr, Integer.valueOf(this.mPrefixLength), this.mHostname, Long.valueOf(this.mExpTime));
    }

    static String clientIdToString(byte[] bArr) {
        return bArr == null ? "null" : HexDump.toHexString(bArr);
    }

    static String inet4AddrToString(Inet4Address inet4Address) {
        return inet4Address == null ? "null" : inet4Address.getHostAddress();
    }

    public String toString() {
        return String.format("clientId: %s, hwAddr: %s, netAddr: %s/%d, expTime: %d,hostname: %s", clientIdToString(this.mClientId), IpMask.maskMacString(this.mHwAddr.toString()), IpMask.maskIpMiddleString(inet4AddrToString(this.mNetAddr)), Integer.valueOf(this.mPrefixLength), Long.valueOf(this.mExpTime), this.mHostname);
    }

    public DhcpLeaseParcelable toParcelable() {
        DhcpLeaseParcelable dhcpLeaseParcelable = new DhcpLeaseParcelable();
        byte[] bArr = this.mClientId;
        dhcpLeaseParcelable.clientId = bArr == null ? null : Arrays.copyOf(bArr, bArr.length);
        dhcpLeaseParcelable.hwAddr = this.mHwAddr.toByteArray();
        dhcpLeaseParcelable.netAddr = Inet4AddressUtils.inet4AddressToIntHTH(this.mNetAddr);
        dhcpLeaseParcelable.prefixLength = this.mPrefixLength;
        dhcpLeaseParcelable.expTime = this.mExpTime;
        dhcpLeaseParcelable.hostname = this.mHostname;
        return dhcpLeaseParcelable;
    }
}
