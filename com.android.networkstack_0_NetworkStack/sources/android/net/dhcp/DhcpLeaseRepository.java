package android.net.dhcp;

import android.net.IpPrefix;
import android.net.MacAddress;
import android.net.dhcp.DhcpServer;
import android.net.util.SharedLog;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.ArrayMap;
import com.android.networkstack.util.Inet4AddressUtils;
import com.android.server.util.NetworkStackConstants;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
class DhcpLeaseRepository {
    private Inet4Address mClientAddr;
    private final DhcpServer.Clock mClock;
    private long mLeaseTimeMs;
    private final SharedLog mLog;
    private int mNumAddresses;
    private IpPrefix mPrefix;
    private int mPrefixLength;
    private Set<Inet4Address> mReservedAddrs;
    private int mSubnetAddr;
    private int mSubnetMask;
    private long mNextExpirationCheck = Long.MAX_VALUE;
    private RemoteCallbackList<IDhcpEventCallbacks> mEventCallbacks = new RemoteCallbackList<>();
    private final ArrayMap<Inet4Address, DhcpLease> mCommittedLeases = new ArrayMap<>();
    private final LinkedHashMap<Inet4Address, Long> mDeclinedAddrs = new LinkedHashMap<>();

    static class DhcpLeaseException extends Exception {
        DhcpLeaseException(String str) {
            super(str);
        }
    }

    static class OutOfAddressesException extends DhcpLeaseException {
        OutOfAddressesException(String str) {
            super(str);
        }
    }

    static class InvalidAddressException extends DhcpLeaseException {
        InvalidAddressException(String str) {
            super(str);
        }
    }

    static class InvalidSubnetException extends DhcpLeaseException {
        InvalidSubnetException(String str) {
            super(str);
        }
    }

    DhcpLeaseRepository(IpPrefix ipPrefix, Set<Inet4Address> set, long j, Inet4Address inet4Address, SharedLog sharedLog, DhcpServer.Clock clock) {
        this.mLog = sharedLog;
        this.mClock = clock;
        this.mClientAddr = inet4Address;
        updateParams(ipPrefix, set, j, inet4Address);
    }

    public void updateParams(IpPrefix ipPrefix, Set<Inet4Address> set, long j, Inet4Address inet4Address) {
        this.mPrefix = ipPrefix;
        this.mReservedAddrs = Collections.unmodifiableSet(new HashSet(set));
        int prefixLength = ipPrefix.getPrefixLength();
        this.mPrefixLength = prefixLength;
        this.mSubnetMask = Inet4AddressUtils.prefixLengthToV4NetmaskIntHTH(prefixLength);
        this.mSubnetAddr = Inet4AddressUtils.inet4AddressToIntHTH((Inet4Address) ipPrefix.getAddress()) & this.mSubnetMask;
        this.mNumAddresses = inet4Address == null ? 1 << (32 - ipPrefix.getPrefixLength()) : 1;
        this.mLeaseTimeMs = j;
        this.mClientAddr = inet4Address;
        cleanMap(this.mDeclinedAddrs);
        if (cleanMap(this.mCommittedLeases)) {
            notifyLeasesChanged();
        }
    }

    private <T> boolean cleanMap(Map<Inet4Address, T> map) {
        Iterator<Map.Entry<Inet4Address, T>> it = map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Inet4Address key = it.next().getKey();
            if (!isValidAddress(key) || this.mReservedAddrs.contains(key)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public DhcpLease getOffer(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address, Inet4Address inet4Address2, String str) throws OutOfAddressesException, InvalidSubnetException {
        long jElapsedRealtime = this.mClock.elapsedRealtime();
        long j = jElapsedRealtime + this.mLeaseTimeMs;
        removeExpiredLeases(jElapsedRealtime);
        checkValidRelayAddr(inet4Address);
        DhcpLease dhcpLeaseFindByClient = findByClient(bArr, macAddress);
        if (dhcpLeaseFindByClient != null) {
            DhcpLease dhcpLeaseRenewedLease = dhcpLeaseFindByClient.renewedLease(j, str);
            this.mLog.log("Offering extended lease " + dhcpLeaseRenewedLease);
            return dhcpLeaseRenewedLease;
        }
        if (inet4Address2 != null && isValidAddress(inet4Address2) && isAvailable(inet4Address2)) {
            DhcpLease dhcpLease = new DhcpLease(bArr, macAddress, inet4Address2, this.mPrefixLength, j, str);
            this.mLog.log("Offering requested lease " + dhcpLease);
            return dhcpLease;
        }
        DhcpLease dhcpLeaseMakeNewOffer = makeNewOffer(bArr, macAddress, j, str);
        this.mLog.log("Offering new generated lease " + dhcpLeaseMakeNewOffer);
        return dhcpLeaseMakeNewOffer;
    }

    public DhcpLease getCommittedLease(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address, String str) throws OutOfAddressesException, InvalidSubnetException {
        DhcpLease offer = getOffer(bArr, macAddress, inet4Address, null, str);
        commitLease(offer);
        return offer;
    }

    private void checkValidRelayAddr(Inet4Address inet4Address) throws InvalidSubnetException {
        if (isIpAddrOutsidePrefix(this.mPrefix, inet4Address)) {
            throw new InvalidSubnetException("Lease requested by relay from outside of subnet");
        }
    }

    private static boolean isIpAddrOutsidePrefix(IpPrefix ipPrefix, Inet4Address inet4Address) {
        return (inet4Address == null || inet4Address.equals(NetworkStackConstants.IPV4_ADDR_ANY) || ipPrefix.contains(inet4Address)) ? false : true;
    }

    private DhcpLease findByClient(byte[] bArr, MacAddress macAddress) {
        for (DhcpLease dhcpLease : this.mCommittedLeases.values()) {
            if (dhcpLease.matchesClient(bArr, macAddress)) {
                return dhcpLease;
            }
        }
        return null;
    }

    public DhcpLease requestLease(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, boolean z, String str) throws InvalidSubnetException, InvalidAddressException {
        long jElapsedRealtime = this.mClock.elapsedRealtime();
        removeExpiredLeases(jElapsedRealtime);
        checkValidRelayAddr(inet4Address2);
        DhcpLease dhcpLeaseFindByClient = findByClient(bArr, macAddress);
        Inet4Address inet4Address4 = inet4Address3 != null ? inet4Address3 : inet4Address;
        if (dhcpLeaseFindByClient != null) {
            if (z && inet4Address3 != null) {
                removeLease(dhcpLeaseFindByClient.getNetAddr(), false);
            } else if (!dhcpLeaseFindByClient.getNetAddr().equals(inet4Address4)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Incorrect address for client in ");
                sb.append(inet4Address3 != null ? "INIT-REBOOT" : "RENEWING/REBINDING");
                throw new InvalidAddressException(sb.toString());
            }
        }
        DhcpLease dhcpLeaseCheckClientAndMakeLease = checkClientAndMakeLease(bArr, macAddress, inet4Address4, str, jElapsedRealtime);
        this.mLog.logf("DHCPREQUEST assignedLease %s, reqAddr=%s, sidSet=%s: created/renewed lease %s", dhcpLeaseFindByClient, DhcpLease.inet4AddrToString(inet4Address3), Boolean.valueOf(z), dhcpLeaseCheckClientAndMakeLease);
        return dhcpLeaseCheckClientAndMakeLease;
    }

    private DhcpLease checkClientAndMakeLease(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address, String str, long j) throws InvalidAddressException {
        DhcpLease dhcpLeaseRenewedLease;
        long j2 = j + this.mLeaseTimeMs;
        DhcpLease orDefault = this.mCommittedLeases.getOrDefault(inet4Address, null);
        if (orDefault != null && !orDefault.matchesClient(bArr, macAddress)) {
            throw new InvalidAddressException("Address in use");
        }
        if (orDefault == null) {
            if (isValidAddress(inet4Address) && !this.mReservedAddrs.contains(inet4Address)) {
                dhcpLeaseRenewedLease = new DhcpLease(bArr, macAddress, inet4Address, this.mPrefixLength, j2, str);
            } else {
                throw new InvalidAddressException("Lease not found and address unavailable");
            }
        } else {
            dhcpLeaseRenewedLease = orDefault.renewedLease(j2, str);
        }
        commitLease(dhcpLeaseRenewedLease);
        return dhcpLeaseRenewedLease;
    }

    private void commitLease(DhcpLease dhcpLease) {
        this.mCommittedLeases.put(dhcpLease.getNetAddr(), dhcpLease);
        maybeUpdateEarliestExpiration(dhcpLease.getExpTime());
        notifyLeasesChanged();
    }

    private void removeLease(Inet4Address inet4Address, boolean z) {
        this.mCommittedLeases.remove(inet4Address);
        if (z) {
            notifyLeasesChanged();
        }
    }

    public boolean releaseLease(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address) {
        DhcpLease orDefault = this.mCommittedLeases.getOrDefault(inet4Address, null);
        if (orDefault == null) {
            this.mLog.w("Could not release unknown lease for " + DhcpLease.inet4AddrToString(inet4Address));
            return false;
        }
        if (orDefault.matchesClient(bArr, macAddress)) {
            this.mLog.log("Released lease " + orDefault);
            removeLease(inet4Address, true);
            return true;
        }
        this.mLog.w(String.format("Not releasing lease %s: does not match client (cid %s, hwAddr %s)", orDefault, DhcpLease.clientIdToString(bArr), macAddress));
        return false;
    }

    private void notifyLeasesChanged() {
        ArrayList arrayList = new ArrayList(this.mCommittedLeases.size());
        Iterator<DhcpLease> it = this.mCommittedLeases.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toParcelable());
        }
        int iBeginBroadcast = this.mEventCallbacks.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                ((IDhcpEventCallbacks) this.mEventCallbacks.getBroadcastItem(i)).onLeasesChanged(arrayList);
            } catch (RemoteException e) {
                this.mLog.e("Could not send lease callback", e);
            }
        }
        this.mEventCallbacks.finishBroadcast();
    }

    void markLeaseDeclined(Inet4Address inet4Address) {
        if (this.mDeclinedAddrs.containsKey(inet4Address) || !isValidAddress(inet4Address)) {
            this.mLog.logf("Not marking %s as declined: already declined or not assignable", DhcpLease.inet4AddrToString(inet4Address));
            return;
        }
        long jElapsedRealtime = this.mClock.elapsedRealtime() + this.mLeaseTimeMs;
        this.mDeclinedAddrs.put(inet4Address, Long.valueOf(jElapsedRealtime));
        this.mLog.logf("Marked %s as declined expiring %d", DhcpLease.inet4AddrToString(inet4Address), Long.valueOf(jElapsedRealtime));
        maybeUpdateEarliestExpiration(jElapsedRealtime);
    }

    public boolean markAndReleaseDeclinedLease(byte[] bArr, MacAddress macAddress, Inet4Address inet4Address) {
        if (!releaseLease(bArr, macAddress, inet4Address)) {
            return false;
        }
        markLeaseDeclined(inet4Address);
        return true;
    }

    public List<DhcpLease> getCommittedLeases() {
        removeExpiredLeases(this.mClock.elapsedRealtime());
        return new ArrayList(this.mCommittedLeases.values());
    }

    public void addLeaseCallbacks(IDhcpEventCallbacks iDhcpEventCallbacks) {
        Objects.requireNonNull(iDhcpEventCallbacks, "Callbacks must be non-null");
        this.mEventCallbacks.register(iDhcpEventCallbacks);
    }

    private void maybeUpdateEarliestExpiration(long j) {
        if (j < this.mNextExpirationCheck) {
            this.mNextExpirationCheck = j;
        }
    }

    private <T> long removeExpired(long j, Map<Inet4Address, T> map, String str, Function<T, Long> function) {
        Iterator<Map.Entry<Inet4Address, T>> it = map.entrySet().iterator();
        long jMin = Long.MAX_VALUE;
        while (it.hasNext()) {
            Map.Entry<Inet4Address, T> next = it.next();
            long jLongValue = function.apply(next.getValue()).longValue();
            if (jLongValue <= j) {
                this.mLog.logf("Removing expired %s lease for %s (expTime=%s, currentTime=%s)", str, next.getKey(), Long.valueOf(jLongValue), Long.valueOf(j));
                it.remove();
            } else {
                jMin = Math.min(jMin, jLongValue);
            }
        }
        return jMin;
    }

    private void removeExpiredLeases(long j) {
        if (j < this.mNextExpirationCheck) {
            return;
        }
        this.mNextExpirationCheck = Math.min(removeExpired(j, this.mCommittedLeases, "committed", new Function() { // from class: android.net.dhcp.-$$Lambda$JzGJaKdJpggc40ifok3mDiod80M
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((DhcpLease) obj).getExpTime());
            }
        }), removeExpired(j, this.mDeclinedAddrs, "declined", Function.identity()));
    }

    private boolean isAvailable(Inet4Address inet4Address) {
        return (this.mReservedAddrs.contains(inet4Address) || this.mCommittedLeases.containsKey(inet4Address)) ? false : true;
    }

    private int getAddrIndex(int i) {
        return (~this.mSubnetMask) & i;
    }

    private int getAddrByIndex(int i) {
        return this.mSubnetAddr | i;
    }

    private int getValidAddress(int i) {
        Inet4Address inet4Address = this.mClientAddr;
        if (inet4Address != null) {
            return Inet4AddressUtils.inet4AddressToIntHTH(inet4Address);
        }
        int addrIndex = getAddrIndex(i);
        int addrByIndex = getAddrByIndex(addrIndex) & 255;
        if (addrByIndex == 255) {
            addrIndex = (addrIndex + 2) % this.mNumAddresses;
        } else if (addrByIndex == 0) {
            addrIndex = (addrIndex + 1) % this.mNumAddresses;
        }
        if (addrIndex == 0 || addrIndex == this.mNumAddresses - 1) {
            addrIndex = 1;
        }
        return getAddrByIndex(addrIndex);
    }

    private boolean isValidAddress(Inet4Address inet4Address) {
        int iInet4AddressToIntHTH = Inet4AddressUtils.inet4AddressToIntHTH(inet4Address);
        return getValidAddress(iInet4AddressToIntHTH) == iInet4AddressToIntHTH;
    }

    private int getNextAddress(int i) {
        return getValidAddress(getAddrByIndex((getAddrIndex(i) + 1) % this.mNumAddresses));
    }

    private int getFirstClientAddress(MacAddress macAddress) {
        int i = 0;
        for (byte b : macAddress.toByteArray()) {
            i += (b << 8) + b + (b << 16);
        }
        return getValidAddress(getAddrByIndex(i % this.mNumAddresses));
    }

    private DhcpLease makeNewOffer(byte[] bArr, MacAddress macAddress, long j, String str) throws OutOfAddressesException {
        int firstClientAddress = getFirstClientAddress(macAddress);
        for (int i = 0; i < this.mNumAddresses; i++) {
            Inet4Address inet4AddressIntToInet4AddressHTH = Inet4AddressUtils.intToInet4AddressHTH(firstClientAddress);
            if (isAvailable(inet4AddressIntToInet4AddressHTH) && !this.mDeclinedAddrs.containsKey(inet4AddressIntToInet4AddressHTH)) {
                return new DhcpLease(bArr, macAddress, inet4AddressIntToInet4AddressHTH, this.mPrefixLength, j, str);
            }
            firstClientAddress = getNextAddress(firstClientAddress);
        }
        Iterator<Inet4Address> it = this.mDeclinedAddrs.keySet().iterator();
        while (it.hasNext()) {
            Inet4Address next = it.next();
            it.remove();
            this.mLog.logf("Out of addresses in address pool: dropped declined addr %s", DhcpLease.inet4AddrToString(next));
            if (isAvailable(next)) {
                return new DhcpLease(bArr, macAddress, next, this.mPrefixLength, j, str);
            }
        }
        throw new OutOfAddressesException("No address available for offer");
    }
}
