package android.net.util;

import android.net.InetAddresses;
import android.net.Network;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.android.internal.midi.MidiConstants;
import com.android.internal.util.BitUtils;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DnsUtils {
    private static final int CHAR_BIT = 8;
    public static final int IPV6_ADDR_SCOPE_GLOBAL = 14;
    public static final int IPV6_ADDR_SCOPE_LINKLOCAL = 2;
    public static final int IPV6_ADDR_SCOPE_NODELOCAL = 1;
    public static final int IPV6_ADDR_SCOPE_SITELOCAL = 5;
    private static final String TAG = "DnsUtils";
    private static final Comparator<SortableAddress> sRfc6724Comparator = new Rfc6724Comparator();

    public static class Rfc6724Comparator implements Comparator<SortableAddress> {
        @Override // java.util.Comparator
        public int compare(SortableAddress sortableAddress, SortableAddress sortableAddress2) {
            int i;
            int i2;
            if (sortableAddress.hasSrcAddr != sortableAddress2.hasSrcAddr) {
                i = sortableAddress2.hasSrcAddr;
                i2 = sortableAddress.hasSrcAddr;
            } else if (sortableAddress.scopeMatch != sortableAddress2.scopeMatch) {
                i = sortableAddress2.scopeMatch;
                i2 = sortableAddress.scopeMatch;
            } else if (sortableAddress.labelMatch != sortableAddress2.labelMatch) {
                i = sortableAddress2.labelMatch;
                i2 = sortableAddress.labelMatch;
            } else if (sortableAddress.precedence != sortableAddress2.precedence) {
                i = sortableAddress2.precedence;
                i2 = sortableAddress.precedence;
            } else {
                if (sortableAddress.scope != sortableAddress2.scope) {
                    return sortableAddress.scope - sortableAddress2.scope;
                }
                if (sortableAddress.prefixMatchLen == sortableAddress2.prefixMatchLen) {
                    return 0;
                }
                i = sortableAddress2.prefixMatchLen;
                i2 = sortableAddress.prefixMatchLen;
            }
            return i - i2;
        }
    }

    public static class SortableAddress {
        public final InetAddress address;
        public final int hasSrcAddr;
        public final int label;
        public final int labelMatch;
        public final int precedence;
        public final int prefixMatchLen;
        public final int scope;
        public final int scopeMatch;

        public SortableAddress(InetAddress inetAddress, InetAddress inetAddress2) {
            this.address = inetAddress;
            this.hasSrcAddr = inetAddress2 != null ? 1 : 0;
            this.label = DnsUtils.findLabel(inetAddress);
            this.scope = DnsUtils.findScope(inetAddress);
            this.precedence = DnsUtils.findPrecedence(inetAddress);
            this.labelMatch = (inetAddress2 == null || this.label != DnsUtils.findLabel(inetAddress2)) ? 0 : 1;
            this.scopeMatch = (inetAddress2 == null || this.scope != DnsUtils.findScope(inetAddress2)) ? 0 : 1;
            if (DnsUtils.isIpv6Address(inetAddress) && DnsUtils.isIpv6Address(inetAddress2)) {
                this.prefixMatchLen = DnsUtils.compareIpv6PrefixMatchLen(inetAddress2, inetAddress);
            } else {
                this.prefixMatchLen = 0;
            }
        }
    }

    public static List<InetAddress> rfc6724Sort(Network network, List<InetAddress> list) {
        ArrayList arrayList = new ArrayList();
        for (InetAddress inetAddress : list) {
            arrayList.add(new SortableAddress(inetAddress, findSrcAddress(network, inetAddress)));
        }
        Collections.sort(arrayList, sRfc6724Comparator);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((SortableAddress) it.next()).address);
        }
        return arrayList2;
    }

    private static InetAddress findSrcAddress(Network network, InetAddress inetAddress) {
        int i;
        if (isIpv4Address(inetAddress)) {
            i = OsConstants.AF_INET;
        } else {
            if (isIpv6Address(inetAddress)) {
                i = OsConstants.AF_INET6;
            }
            return null;
        }
        try {
            FileDescriptor fileDescriptorSocket = Os.socket(i, OsConstants.SOCK_DGRAM, OsConstants.IPPROTO_UDP);
            if (network != null) {
                try {
                    network.bindSocket(fileDescriptorSocket);
                } catch (ErrnoException | IOException unused) {
                    return null;
                } finally {
                    IoUtils.closeQuietly(fileDescriptorSocket);
                }
            }
            Os.connect(fileDescriptorSocket, new InetSocketAddress(inetAddress, 0));
            return ((InetSocketAddress) Os.getsockname(fileDescriptorSocket)).getAddress();
        } catch (ErrnoException e) {
            Log.e(TAG, "findSrcAddress:" + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int findLabel(InetAddress inetAddress) {
        if (isIpv4Address(inetAddress)) {
            return 4;
        }
        if (isIpv6Address(inetAddress)) {
            if (inetAddress.isLoopbackAddress()) {
                return 0;
            }
            if (isIpv6Address6To4(inetAddress)) {
                return 2;
            }
            if (isIpv6AddressTeredo(inetAddress)) {
                return 5;
            }
            if (isIpv6AddressULA(inetAddress)) {
                return 13;
            }
            if (((Inet6Address) inetAddress).isIPv4CompatibleAddress()) {
                return 3;
            }
            if (inetAddress.isSiteLocalAddress()) {
                return 11;
            }
            if (isIpv6Address6Bone(inetAddress)) {
                return 12;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isIpv6Address(InetAddress inetAddress) {
        return inetAddress instanceof Inet6Address;
    }

    private static boolean isIpv4Address(InetAddress inetAddress) {
        return inetAddress instanceof Inet4Address;
    }

    private static boolean isIpv6Address6To4(InetAddress inetAddress) {
        if (!isIpv6Address(inetAddress)) {
            return false;
        }
        byte[] address = inetAddress.getAddress();
        return address[0] == 32 && address[1] == 2;
    }

    private static boolean isIpv6AddressTeredo(InetAddress inetAddress) {
        if (!isIpv6Address(inetAddress)) {
            return false;
        }
        byte[] address = inetAddress.getAddress();
        return address[0] == 32 && address[1] == 1 && address[2] == 0 && address[3] == 0;
    }

    private static boolean isIpv6AddressULA(InetAddress inetAddress) {
        return isIpv6Address(inetAddress) && (inetAddress.getAddress()[0] & MidiConstants.STATUS_ACTIVE_SENSING) == 252;
    }

    private static boolean isIpv6Address6Bone(InetAddress inetAddress) {
        if (!isIpv6Address(inetAddress)) {
            return false;
        }
        byte[] address = inetAddress.getAddress();
        return address[0] == 63 && address[1] == -2;
    }

    private static int getIpv6MulticastScope(InetAddress inetAddress) {
        if (isIpv6Address(inetAddress)) {
            return inetAddress.getAddress()[1] & MidiConstants.STATUS_CHANNEL_MASK;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int findScope(InetAddress inetAddress) {
        if (isIpv6Address(inetAddress)) {
            if (inetAddress.isMulticastAddress()) {
                return getIpv6MulticastScope(inetAddress);
            }
            if (inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) {
                return 2;
            }
            return inetAddress.isSiteLocalAddress() ? 5 : 14;
        }
        if (isIpv4Address(inetAddress)) {
            return (inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) ? 2 : 14;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int findPrecedence(InetAddress inetAddress) {
        if (isIpv4Address(inetAddress)) {
            return 35;
        }
        if (isIpv6Address(inetAddress)) {
            if (inetAddress.isLoopbackAddress()) {
                return 50;
            }
            if (isIpv6Address6To4(inetAddress)) {
                return 30;
            }
            if (isIpv6AddressTeredo(inetAddress)) {
                return 5;
            }
            if (isIpv6AddressULA(inetAddress)) {
                return 3;
            }
            if (!((Inet6Address) inetAddress).isIPv4CompatibleAddress() && !inetAddress.isSiteLocalAddress() && !isIpv6Address6Bone(inetAddress)) {
                return 40;
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int compareIpv6PrefixMatchLen(InetAddress inetAddress, InetAddress inetAddress2) {
        byte[] address = inetAddress.getAddress();
        byte[] address2 = inetAddress2.getAddress();
        if (address.length != address2.length) {
            return 0;
        }
        for (int i = 0; i < address2.length; i++) {
            if (address[i] != address2[i]) {
                return (i * 8) + (Integer.numberOfLeadingZeros(BitUtils.uint8(address[i]) ^ BitUtils.uint8(address2[i])) - 24);
            }
        }
        return address2.length * 8;
    }

    public static boolean haveIpv4(Network network) {
        return checkConnectivity(network, OsConstants.AF_INET, new InetSocketAddress(InetAddresses.parseNumericAddress("8.8.8.8"), 0));
    }

    public static boolean haveIpv6(Network network) {
        return checkConnectivity(network, OsConstants.AF_INET6, new InetSocketAddress(InetAddresses.parseNumericAddress("2000::"), 0));
    }

    private static boolean checkConnectivity(Network network, int i, SocketAddress socketAddress) {
        try {
            FileDescriptor fileDescriptorSocket = Os.socket(i, OsConstants.SOCK_DGRAM, OsConstants.IPPROTO_UDP);
            if (network != null) {
                try {
                    network.bindSocket(fileDescriptorSocket);
                } catch (ErrnoException | IOException unused) {
                    IoUtils.closeQuietly(fileDescriptorSocket);
                    return false;
                } catch (Throwable th) {
                    IoUtils.closeQuietly(fileDescriptorSocket);
                    throw th;
                }
            }
            Os.connect(fileDescriptorSocket, socketAddress);
            IoUtils.closeQuietly(fileDescriptorSocket);
            return true;
        } catch (ErrnoException unused2) {
        }
    }
}
