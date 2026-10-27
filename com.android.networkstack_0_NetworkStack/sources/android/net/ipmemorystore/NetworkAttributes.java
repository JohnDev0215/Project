package android.net.ipmemorystore;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes.dex */
public class NetworkAttributes {
    public static final float TOTAL_WEIGHT = 850.0f;
    public final Inet4Address assignedV4Address;
    public final Long assignedV4AddressExpiry;
    public final String cluster;
    public final List<InetAddress> dnsAddresses;
    public final Integer mtu;

    public NetworkAttributes(Inet4Address inet4Address, Long l, String str, List<InetAddress> list, Integer num) {
        if (num != null && num.intValue() < 0) {
            throw new IllegalArgumentException("MTU can't be negative");
        }
        if (l != null && l.longValue() <= 0) {
            throw new IllegalArgumentException("lease expiry can't be negative or zero");
        }
        this.assignedV4Address = inet4Address;
        this.assignedV4AddressExpiry = l;
        this.cluster = str;
        this.dnsAddresses = list == null ? null : Collections.unmodifiableList(new ArrayList(list));
        this.mtu = num;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NetworkAttributes(NetworkAttributesParcelable networkAttributesParcelable) {
        Inet4Address inet4Address = (Inet4Address) getByAddressOrNull(networkAttributesParcelable.assignedV4Address);
        long j = networkAttributesParcelable.assignedV4AddressExpiry;
        Long lValueOf = j > 0 ? Long.valueOf(j) : null;
        String str = networkAttributesParcelable.cluster;
        List<InetAddress> listBlobArrayToInetAddressList = blobArrayToInetAddressList(networkAttributesParcelable.dnsAddresses);
        int i = networkAttributesParcelable.mtu;
        this(inet4Address, lValueOf, str, listBlobArrayToInetAddressList, i >= 0 ? Integer.valueOf(i) : null);
    }

    private static InetAddress getByAddressOrNull(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private static List<InetAddress> blobArrayToInetAddressList(Blob[] blobArr) {
        if (blobArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(blobArr.length);
        for (Blob blob : blobArr) {
            InetAddress byAddressOrNull = getByAddressOrNull(blob.data);
            if (byAddressOrNull != null) {
                arrayList.add(byAddressOrNull);
            }
        }
        return arrayList;
    }

    private static Blob[] inetAddressListToBlobArray(List<InetAddress> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            InetAddress inetAddress = list.get(i);
            if (inetAddress != null) {
                Blob blob = new Blob();
                blob.data = inetAddress.getAddress();
                arrayList.add(blob);
            }
        }
        return (Blob[]) arrayList.toArray(new Blob[0]);
    }

    public NetworkAttributesParcelable toParcelable() {
        NetworkAttributesParcelable networkAttributesParcelable = new NetworkAttributesParcelable();
        Inet4Address inet4Address = this.assignedV4Address;
        networkAttributesParcelable.assignedV4Address = inet4Address == null ? null : inet4Address.getAddress();
        Long l = this.assignedV4AddressExpiry;
        networkAttributesParcelable.assignedV4AddressExpiry = l == null ? 0L : l.longValue();
        networkAttributesParcelable.cluster = this.cluster;
        networkAttributesParcelable.dnsAddresses = inetAddressListToBlobArray(this.dnsAddresses);
        Integer num = this.mtu;
        networkAttributesParcelable.mtu = num == null ? -1 : num.intValue();
        return networkAttributesParcelable;
    }

    private float samenessContribution(float f, Object obj, Object obj2) {
        if (obj == null) {
            if (obj2 == null) {
                return 0.25f * f;
            }
            return 0.0f;
        }
        if (Objects.equals(obj, obj2)) {
            return f;
        }
        return 0.0f;
    }

    public float getNetworkGroupSamenessConfidence(NetworkAttributes networkAttributes) {
        float fSamenessContribution = samenessContribution(300.0f, this.assignedV4Address, networkAttributes.assignedV4Address) + samenessContribution(0.0f, this.assignedV4AddressExpiry, networkAttributes.assignedV4AddressExpiry) + samenessContribution(300.0f, this.cluster, networkAttributes.cluster) + samenessContribution(200.0f, this.dnsAddresses, networkAttributes.dnsAddresses) + samenessContribution(50.0f, this.mtu, networkAttributes.mtu);
        return fSamenessContribution < 520.0f ? fSamenessContribution / 1040.0f : (((fSamenessContribution - 520.0f) / 330.0f) / 2.0f) + 0.5f;
    }

    public static class Builder {
        private Inet4Address mAssignedAddress;
        private Long mAssignedAddressExpiry;
        private String mCluster;
        private List<InetAddress> mDnsAddresses;
        private Integer mMtu;

        public Builder setAssignedV4Address(Inet4Address inet4Address) {
            this.mAssignedAddress = inet4Address;
            return this;
        }

        public Builder setAssignedV4AddressExpiry(Long l) {
            if (l != null && l.longValue() <= 0) {
                throw new IllegalArgumentException("lease expiry can't be negative or zero");
            }
            this.mAssignedAddressExpiry = l;
            return this;
        }

        public Builder setCluster(String str) {
            this.mCluster = str;
            return this;
        }

        public Builder setDnsAddresses(List<InetAddress> list) {
            if (list != null) {
                Iterator<InetAddress> it = list.iterator();
                while (it.hasNext()) {
                    if (it.next() == null) {
                        throw new IllegalArgumentException("Null DNS address");
                    }
                }
            }
            this.mDnsAddresses = list;
            return this;
        }

        public Builder setMtu(Integer num) {
            if (num != null && num.intValue() < 0) {
                throw new IllegalArgumentException("MTU can't be negative");
            }
            this.mMtu = num;
            return this;
        }

        public NetworkAttributes build() {
            return new NetworkAttributes(this.mAssignedAddress, this.mAssignedAddressExpiry, this.mCluster, this.mDnsAddresses, this.mMtu);
        }
    }

    public boolean isEmpty() {
        return this.assignedV4Address == null && this.assignedV4AddressExpiry == null && this.cluster == null && this.dnsAddresses == null && this.mtu == null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NetworkAttributes)) {
            return false;
        }
        NetworkAttributes networkAttributes = (NetworkAttributes) obj;
        return Objects.equals(this.assignedV4Address, networkAttributes.assignedV4Address) && Objects.equals(this.assignedV4AddressExpiry, networkAttributes.assignedV4AddressExpiry) && Objects.equals(this.cluster, networkAttributes.cluster) && Objects.equals(this.dnsAddresses, networkAttributes.dnsAddresses) && Objects.equals(this.mtu, networkAttributes.mtu);
    }

    public int hashCode() {
        return Objects.hash(this.assignedV4Address, this.assignedV4AddressExpiry, this.cluster, this.dnsAddresses, this.mtu);
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" ", "{", "}");
        ArrayList arrayList = new ArrayList();
        if (this.assignedV4Address != null) {
            stringJoiner.add("assignedV4Addr :");
            stringJoiner.add(IpMask.maskIpMiddleString(this.assignedV4Address.toString()));
        } else {
            arrayList.add("assignedV4Addr");
        }
        if (this.assignedV4AddressExpiry != null) {
            stringJoiner.add("assignedV4AddressExpiry :");
            stringJoiner.add(this.assignedV4AddressExpiry.toString());
        } else {
            arrayList.add("assignedV4AddressExpiry");
        }
        if (this.cluster != null) {
            stringJoiner.add("cluster :");
            stringJoiner.add(this.cluster);
        } else {
            arrayList.add("cluster");
        }
        if (this.dnsAddresses != null) {
            stringJoiner.add("dnsAddr : [");
            Iterator<InetAddress> it = this.dnsAddresses.iterator();
            while (it.hasNext()) {
                stringJoiner.add(it.next().getHostAddress());
            }
            stringJoiner.add("]");
        } else {
            arrayList.add("dnsAddr");
        }
        if (this.mtu != null) {
            stringJoiner.add("mtu :");
            stringJoiner.add(this.mtu.toString());
        } else {
            arrayList.add("mtu");
        }
        if (!arrayList.isEmpty()) {
            stringJoiner.add("; Null fields : [");
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                stringJoiner.add((String) it2.next());
            }
            stringJoiner.add("]");
        }
        return stringJoiner.toString();
    }
}
