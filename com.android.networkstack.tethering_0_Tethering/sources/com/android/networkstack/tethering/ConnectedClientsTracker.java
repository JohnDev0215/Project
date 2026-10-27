package com.android.networkstack.tethering;

import android.net.MacAddress;
import android.net.TetheredClient;
import android.net.ip.IpServer;
import android.net.wifi.WifiClient;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedClientsTracker {
    private final Clock mClock;
    private List<TetheredClient> mLastTetheredClients;
    private List<WifiClient> mLastWifiClients;

    static class Clock {
        Clock() {
        }

        public long elapsedRealtime() {
            return SystemClock.elapsedRealtime();
        }
    }

    public ConnectedClientsTracker() {
        this(new Clock());
    }

    ConnectedClientsTracker(Clock clock) {
        this.mLastWifiClients = Collections.emptyList();
        this.mLastTetheredClients = Collections.emptyList();
        this.mClock = clock;
    }

    public boolean updateConnectedClients(Iterable<IpServer> iterable, List<WifiClient> list) {
        long jElapsedRealtime = this.mClock.elapsedRealtime();
        if (list != null) {
            this.mLastWifiClients = list;
        }
        Set<MacAddress> clientMacs = getClientMacs(this.mLastWifiClients);
        HashMap map = new HashMap();
        Iterator<IpServer> it = iterable.iterator();
        while (it.hasNext()) {
            for (TetheredClient tetheredClient : it.next().getAllLeases()) {
                if (tetheredClient.getTetheringType() != 0 || clientMacs.contains(tetheredClient.getMacAddress())) {
                    TetheredClient tetheredClientPruneExpired = pruneExpired(tetheredClient, jElapsedRealtime);
                    if (tetheredClientPruneExpired != null) {
                        addLease(map, tetheredClientPruneExpired);
                    }
                }
            }
        }
        Iterator<MacAddress> it2 = clientMacs.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            MacAddress next = it2.next();
            if (!map.containsKey(next)) {
                map.put(next, new TetheredClient(next, Collections.emptyList(), 0));
            }
        }
        HashSet hashSet = new HashSet(map.values());
        boolean z = (hashSet.size() == this.mLastTetheredClients.size() && hashSet.containsAll(this.mLastTetheredClients)) ? false : true;
        this.mLastTetheredClients = Collections.unmodifiableList(new ArrayList(hashSet));
        return z;
    }

    private static void addLease(Map<MacAddress, TetheredClient> map, TetheredClient tetheredClient) {
        TetheredClient orDefault = map.getOrDefault(tetheredClient.getMacAddress(), tetheredClient);
        if (orDefault == tetheredClient) {
            map.put(tetheredClient.getMacAddress(), tetheredClient);
        } else {
            map.put(tetheredClient.getMacAddress(), orDefault.addAddresses(tetheredClient));
        }
    }

    public List<TetheredClient> getLastTetheredClients() {
        return this.mLastTetheredClients;
    }

    private static boolean hasExpiredAddress(List<TetheredClient.AddressInfo> list, long j) {
        Iterator<TetheredClient.AddressInfo> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getExpirationTime() <= j) {
                return true;
            }
        }
        return false;
    }

    private static TetheredClient pruneExpired(TetheredClient tetheredClient, long j) {
        List<TetheredClient.AddressInfo> addresses = tetheredClient.getAddresses();
        if (addresses.size() == 0) {
            return null;
        }
        if (!hasExpiredAddress(addresses, j)) {
            return tetheredClient;
        }
        ArrayList arrayList = new ArrayList(addresses.size() - 1);
        for (TetheredClient.AddressInfo addressInfo : addresses) {
            if (addressInfo.getExpirationTime() > j) {
                arrayList.add(addressInfo);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return new TetheredClient(tetheredClient.getMacAddress(), arrayList, tetheredClient.getTetheringType());
    }

    private static Set<MacAddress> getClientMacs(List<WifiClient> list) {
        HashSet hashSet = new HashSet(list.size());
        Iterator<WifiClient> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getMacAddress());
        }
        return hashSet;
    }
}
