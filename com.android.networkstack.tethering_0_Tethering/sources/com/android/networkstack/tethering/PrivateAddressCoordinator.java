package com.android.networkstack.tethering;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.ip.IpServer;
import android.net.util.PrefixUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.networkstack.tethering.util.IndentingPrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class PrivateAddressCoordinator {
    private final ConnectivityManager mConnectivityMgr;
    private final ArraySet<IpServer> mDownstreams = new ArraySet<>();
    private final ArrayMap<Network, List<IpPrefix>> mUpstreamPrefixMap = new ArrayMap<>();
    private final IpPrefix mTetheringPrefix = new IpPrefix("192.168.0.0/16");

    public PrivateAddressCoordinator(Context context) {
        this.mConnectivityMgr = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public void updateUpstreamPrefix(Network network, LinkProperties linkProperties) {
        ArrayList<IpPrefix> ipv4Prefixes = getIpv4Prefixes(linkProperties.getAllLinkAddresses());
        if (ipv4Prefixes.isEmpty()) {
            removeUpstreamPrefix(network);
        } else {
            this.mUpstreamPrefixMap.put(network, ipv4Prefixes);
            handleMaybePrefixConflict(ipv4Prefixes);
        }
    }

    private ArrayList<IpPrefix> getIpv4Prefixes(List<LinkAddress> list) {
        ArrayList<IpPrefix> arrayList = new ArrayList<>();
        for (LinkAddress linkAddress : list) {
            if (linkAddress.isIpv4()) {
                arrayList.add(PrefixUtils.asIpPrefix(linkAddress));
            }
        }
        return arrayList;
    }

    private void handleMaybePrefixConflict(List<IpPrefix> list) {
        for (IpServer ipServer : this.mDownstreams) {
            IpPrefix downstreamPrefix = getDownstreamPrefix(ipServer);
            if (downstreamPrefix != null) {
                Iterator<IpPrefix> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (isConflictPrefix(it.next(), downstreamPrefix)) {
                            ipServer.sendMessage(IpServer.CMD_NOTIFY_PREFIX_CONFLICT);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void removeUpstreamPrefix(Network network) {
        this.mUpstreamPrefixMap.remove(network);
    }

    private void maybeRemoveDeprectedUpstreams() {
        if (!this.mDownstreams.isEmpty() || this.mUpstreamPrefixMap.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        List listAsList = Arrays.asList(this.mConnectivityMgr.getAllNetworks());
        for (int i = 0; i < this.mUpstreamPrefixMap.size(); i++) {
            Network networkKeyAt = this.mUpstreamPrefixMap.keyAt(i);
            if (!listAsList.contains(networkKeyAt)) {
                arrayList.add(networkKeyAt);
            }
        }
        this.mUpstreamPrefixMap.removeAll(arrayList);
    }

    public LinkAddress requestDownstreamAddress(IpServer ipServer) {
        maybeRemoveDeprectedUpstreams();
        byte[] rawAddress = this.mTetheringPrefix.getRawAddress();
        int randomSubAddr = getRandomSubAddr();
        int i = (randomSubAddr >> 8) & 255;
        rawAddress[3] = getSanitizedAddressSuffix(randomSubAddr, 0, 1, -1);
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = (i + i2) & 255;
            if (i3 != 44 && i3 != 118) {
                rawAddress[2] = (byte) i3;
                try {
                    InetAddress byAddress = InetAddress.getByAddress(rawAddress);
                    IpPrefix ipPrefix = new IpPrefix(byAddress, 24);
                    if (!isDownstreamPrefixInUse(ipPrefix) && !isConflictWithUpstream(ipPrefix)) {
                        this.mDownstreams.add(ipServer);
                        return new LinkAddress(byAddress, 24);
                    }
                } catch (UnknownHostException e) {
                    throw new IllegalStateException("Invalid address, shouldn't happen.", e);
                }
            }
        }
        return null;
    }

    public int getRandomSubAddr() {
        return new Random().nextInt() & 65535;
    }

    private byte getSanitizedAddressSuffix(int i, byte... bArr) {
        byte b = (byte) (i & 255);
        for (byte b2 : bArr) {
            if (b == b2) {
                return (byte) 42;
            }
        }
        return b;
    }

    public void releaseDownstream(IpServer ipServer) {
        this.mDownstreams.remove(ipServer);
    }

    private boolean isConflictWithUpstream(IpPrefix ipPrefix) {
        for (int i = 0; i < this.mUpstreamPrefixMap.size(); i++) {
            Iterator<IpPrefix> it = this.mUpstreamPrefixMap.valueAt(i).iterator();
            while (it.hasNext()) {
                if (isConflictPrefix(ipPrefix, it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isConflictPrefix(IpPrefix ipPrefix, IpPrefix ipPrefix2) {
        if (ipPrefix2.getPrefixLength() < ipPrefix.getPrefixLength()) {
            return ipPrefix2.contains(ipPrefix.getAddress());
        }
        return ipPrefix.contains(ipPrefix2.getAddress());
    }

    private boolean isDownstreamPrefixInUse(IpPrefix ipPrefix) {
        Iterator<IpServer> it = this.mDownstreams.iterator();
        while (it.hasNext()) {
            if (ipPrefix.equals(getDownstreamPrefix(it.next()))) {
                return true;
            }
        }
        return false;
    }

    private IpPrefix getDownstreamPrefix(IpServer ipServer) {
        LinkAddress address = ipServer.getAddress();
        if (address == null) {
            return null;
        }
        return PrefixUtils.asIpPrefix(address);
    }

    void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("mUpstreamPrefixMap:");
        indentingPrintWriter.increaseIndent();
        for (int i = 0; i < this.mUpstreamPrefixMap.size(); i++) {
            indentingPrintWriter.println(this.mUpstreamPrefixMap.keyAt(i) + " - " + this.mUpstreamPrefixMap.valueAt(i));
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("mDownstreams:");
        indentingPrintWriter.increaseIndent();
        for (IpServer ipServer : this.mDownstreams) {
            indentingPrintWriter.println(ipServer.interfaceType() + " - " + ipServer.getAddress());
        }
        indentingPrintWriter.decreaseIndent();
    }
}
