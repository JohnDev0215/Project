package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.system.OsConstants;
import android.util.Pair;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LinkAddress implements Parcelable {
    public static final Parcelable.Creator<LinkAddress> CREATOR = new Parcelable.Creator<LinkAddress>() { // from class: android.net.LinkAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LinkAddress createFromParcel(Parcel parcel) {
            InetAddress byAddress;
            try {
                byAddress = InetAddress.getByAddress(parcel.createByteArray());
            } catch (UnknownHostException unused) {
                byAddress = null;
            }
            return new LinkAddress(byAddress, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LinkAddress[] newArray(int i) {
            return new LinkAddress[i];
        }
    };

    @SystemApi
    public static final long LIFETIME_PERMANENT = Long.MAX_VALUE;

    @SystemApi
    public static final long LIFETIME_UNKNOWN = -1;
    private InetAddress address;
    private long deprecationTime;
    private long expirationTime;
    private int flags;
    private int prefixLength;
    private int scope;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private static int scopeForUnicastAddress(InetAddress inetAddress) {
        if (inetAddress.isAnyLocalAddress()) {
            return OsConstants.RT_SCOPE_HOST;
        }
        if (inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) {
            return OsConstants.RT_SCOPE_LINK;
        }
        if (!(inetAddress instanceof Inet4Address) && inetAddress.isSiteLocalAddress()) {
            return OsConstants.RT_SCOPE_SITE;
        }
        return OsConstants.RT_SCOPE_UNIVERSE;
    }

    private boolean isIpv6ULA() {
        return isIpv6() && (this.address.getAddress()[0] & (-2)) == -4;
    }

    @SystemApi
    public boolean isIpv6() {
        return this.address instanceof Inet6Address;
    }

    public boolean isIPv6() {
        return isIpv6();
    }

    @SystemApi
    public boolean isIpv4() {
        return this.address instanceof Inet4Address;
    }

    private void init(InetAddress inetAddress, int i, int i2, int i3, long j, long j2) {
        if (inetAddress == null || inetAddress.isMulticastAddress() || i < 0 || (((inetAddress instanceof Inet4Address) && i > 32) || i > 128)) {
            throw new IllegalArgumentException("Bad LinkAddress params " + inetAddress + "/" + i);
        }
        if ((j == -1) != (j2 == -1)) {
            throw new IllegalArgumentException("Must not specify only one of deprecation time and expiration time");
        }
        if (j != -1 && j < 0) {
            throw new IllegalArgumentException("invalid deprecation time " + j);
        }
        if (j2 != -1 && j2 < 0) {
            throw new IllegalArgumentException("invalid expiration time " + j2);
        }
        if (j != -1 && j2 != -1 && j2 < j) {
            throw new IllegalArgumentException("expiration earlier than deprecation (" + j + ", " + j2 + ")");
        }
        this.address = inetAddress;
        this.prefixLength = i;
        this.flags = i2;
        this.scope = i3;
        this.deprecationTime = j;
        this.expirationTime = j2;
    }

    @SystemApi
    public LinkAddress(InetAddress inetAddress, int i, int i2, int i3) {
        init(inetAddress, i, i2, i3, -1L, -1L);
    }

    @SystemApi
    public LinkAddress(InetAddress inetAddress, int i, int i2, int i3, long j, long j2) {
        init(inetAddress, i, i2, i3, j, j2);
    }

    @SystemApi
    public LinkAddress(InetAddress inetAddress, int i) {
        this(inetAddress, i, 0, 0);
        this.scope = scopeForUnicastAddress(inetAddress);
    }

    public LinkAddress(InterfaceAddress interfaceAddress) {
        this(interfaceAddress.getAddress(), interfaceAddress.getNetworkPrefixLength());
    }

    @SystemApi
    public LinkAddress(String str) {
        this(str, 0, 0);
        this.scope = scopeForUnicastAddress(this.address);
    }

    @SystemApi
    public LinkAddress(String str, int i, int i2) {
        Pair<InetAddress, Integer> ipAndMask = NetworkUtils.parseIpAndMask(str);
        init(ipAndMask.first, ipAndMask.second.intValue(), i, i2, -1L, -1L);
    }

    public String toString() {
        return this.address.getHostAddress() + "/" + this.prefixLength;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LinkAddress)) {
            return false;
        }
        LinkAddress linkAddress = (LinkAddress) obj;
        return this.address.equals(linkAddress.address) && this.prefixLength == linkAddress.prefixLength && this.flags == linkAddress.flags && this.scope == linkAddress.scope && this.deprecationTime == linkAddress.deprecationTime && this.expirationTime == linkAddress.expirationTime;
    }

    public int hashCode() {
        return Objects.hash(this.address, Integer.valueOf(this.prefixLength), Integer.valueOf(this.flags), Integer.valueOf(this.scope), Long.valueOf(this.deprecationTime), Long.valueOf(this.expirationTime));
    }

    @SystemApi
    public boolean isSameAddressAs(LinkAddress linkAddress) {
        return linkAddress != null && this.address.equals(linkAddress.address) && this.prefixLength == linkAddress.prefixLength;
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public int getPrefixLength() {
        return this.prefixLength;
    }

    public int getNetworkPrefixLength() {
        return getPrefixLength();
    }

    public int getFlags() {
        int i = this.flags;
        if (this.deprecationTime != -1) {
            if (SystemClock.elapsedRealtime() >= this.deprecationTime) {
                i |= OsConstants.IFA_F_DEPRECATED;
            } else {
                i &= ~OsConstants.IFA_F_DEPRECATED;
            }
        }
        long j = this.expirationTime;
        if (j == Long.MAX_VALUE) {
            return i | OsConstants.IFA_F_PERMANENT;
        }
        return j != -1 ? i & (~OsConstants.IFA_F_PERMANENT) : i;
    }

    public int getScope() {
        return this.scope;
    }

    @SystemApi
    public long getDeprecationTime() {
        return this.deprecationTime;
    }

    @SystemApi
    public long getExpirationTime() {
        return this.expirationTime;
    }

    @SystemApi
    public boolean isGlobalPreferred() {
        int flags = getFlags();
        return this.scope == OsConstants.RT_SCOPE_UNIVERSE && !isIpv6ULA() && ((long) ((OsConstants.IFA_F_DADFAILED | OsConstants.IFA_F_DEPRECATED) & flags)) == 0 && (((long) (OsConstants.IFA_F_TENTATIVE & flags)) == 0 || ((long) (OsConstants.IFA_F_OPTIMISTIC & flags)) != 0);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.address.getAddress());
        parcel.writeInt(this.prefixLength);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.scope);
        parcel.writeLong(this.deprecationTime);
        parcel.writeLong(this.expirationTime);
    }
}
