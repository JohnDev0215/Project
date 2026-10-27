package android.net.dhcp;

import android.net.LinkAddress;
import android.util.ArraySet;
import com.android.networkstack.tethering.util.Inet4AddressUtils;
import java.net.Inet4Address;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class DhcpServingParamsParcelExt extends DhcpServingParamsParcel {
    public DhcpServingParamsParcelExt setServerAddr(LinkAddress linkAddress) {
        this.serverAddr = Inet4AddressUtils.inet4AddressToIntHTH((Inet4Address) linkAddress.getAddress());
        this.serverAddrPrefixLength = linkAddress.getPrefixLength();
        return this;
    }

    public DhcpServingParamsParcelExt setDefaultRouters(Set<Inet4Address> set) {
        this.defaultRouters = toIntArray(set);
        return this;
    }

    public DhcpServingParamsParcelExt setDefaultRouters(Inet4Address... inet4AddressArr) {
        setDefaultRouters(newArraySet(inet4AddressArr));
        return this;
    }

    public DhcpServingParamsParcelExt setDnsServers(Set<Inet4Address> set) {
        this.dnsServers = toIntArray(set);
        return this;
    }

    public DhcpServingParamsParcelExt setDnsServers(Inet4Address... inet4AddressArr) {
        setDnsServers(newArraySet(inet4AddressArr));
        return this;
    }

    public DhcpServingParamsParcelExt setDhcpLeaseTimeSecs(long j) {
        this.dhcpLeaseTimeSecs = j;
        return this;
    }

    public DhcpServingParamsParcelExt setMetered(boolean z) {
        this.metered = z;
        return this;
    }

    public DhcpServingParamsParcelExt setSingleClientAddr(Inet4Address inet4Address) {
        this.singleClientAddr = inet4Address == null ? 0 : Inet4AddressUtils.inet4AddressToIntHTH(inet4Address);
        return this;
    }

    public DhcpServingParamsParcelExt setChangePrefixOnDecline(boolean z) {
        this.changePrefixOnDecline = z;
        return this;
    }

    private static int[] toIntArray(Collection<Inet4Address> collection) {
        int[] iArr = new int[collection.size()];
        Iterator<Inet4Address> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = Inet4AddressUtils.inet4AddressToIntHTH(it.next());
            i++;
        }
        return iArr;
    }

    private static ArraySet<Inet4Address> newArraySet(Inet4Address... inet4AddressArr) {
        ArraySet<Inet4Address> arraySet = new ArraySet<>(inet4AddressArr.length);
        Collections.addAll(arraySet, inet4AddressArr);
        return arraySet;
    }
}
