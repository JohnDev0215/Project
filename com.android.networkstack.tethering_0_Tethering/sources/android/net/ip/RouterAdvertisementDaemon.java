package android.net.ip;

import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.TrafficStats;
import android.net.util.InterfaceParams;
import android.net.util.SocketUtils;
import android.net.util.TetheringUtils;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructTimeval;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public class RouterAdvertisementDaemon {
    private static final String TAG = "RouterAdvertisementDaemon";
    private final InetSocketAddress mAllNodes;
    private final InterfaceParams mInterface;
    private volatile MulticastTransmitter mMulticastTransmitter;
    private int mRaLength;
    private RaParams mRaParams;
    private volatile FileDescriptor mSocket;
    private volatile UnicastResponder mUnicastResponder;
    private static final byte ICMPV6_ND_ROUTER_SOLICIT = asByte(133);
    private static final byte ICMPV6_ND_ROUTER_ADVERT = asByte(134);
    private static final byte[] ALL_NODES = {-1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    private final Object mLock = new Object();
    private final byte[] mRA = new byte[1280];
    private final DeprecatedInfoTracker mDeprecatedInfoTracker = new DeprecatedInfoTracker();

    private static byte asByte(int i) {
        return (byte) i;
    }

    private static short asShort(int i) {
        return (short) i;
    }

    public static class RaParams {
        public boolean hasDefaultRoute = false;
        public byte hopLimit = 65;
        public int mtu = 1280;
        public HashSet<IpPrefix> prefixes = new HashSet<>();
        public HashSet<Inet6Address> dnses = new HashSet<>();

        public static RaParams getDeprecatedRaParams(RaParams raParams, RaParams raParams2) {
            RaParams raParams3 = new RaParams();
            if (raParams != null) {
                for (IpPrefix ipPrefix : raParams.prefixes) {
                    if (raParams2 == null || !raParams2.prefixes.contains(ipPrefix)) {
                        raParams3.prefixes.add(ipPrefix);
                    }
                }
                for (Inet6Address inet6Address : raParams.dnses) {
                    if (raParams2 == null || !raParams2.dnses.contains(inet6Address)) {
                        raParams3.dnses.add(inet6Address);
                    }
                }
            }
            return raParams3;
        }
    }

    private static class DeprecatedInfoTracker {
        private final HashMap<Inet6Address, Integer> mDnses;
        private final HashMap<IpPrefix, Integer> mPrefixes;

        private DeprecatedInfoTracker() {
            this.mPrefixes = new HashMap<>();
            this.mDnses = new HashMap<>();
        }

        Set<IpPrefix> getPrefixes() {
            return this.mPrefixes.keySet();
        }

        void putPrefixes(Set<IpPrefix> set) {
            Iterator<IpPrefix> it = set.iterator();
            while (it.hasNext()) {
                this.mPrefixes.put(it.next(), 5);
            }
        }

        void removePrefixes(Set<IpPrefix> set) {
            Iterator<IpPrefix> it = set.iterator();
            while (it.hasNext()) {
                this.mPrefixes.remove(it.next());
            }
        }

        Set<Inet6Address> getDnses() {
            return this.mDnses.keySet();
        }

        void putDnses(Set<Inet6Address> set) {
            Iterator<Inet6Address> it = set.iterator();
            while (it.hasNext()) {
                this.mDnses.put(it.next(), 5);
            }
        }

        void removeDnses(Set<Inet6Address> set) {
            Iterator<Inet6Address> it = set.iterator();
            while (it.hasNext()) {
                this.mDnses.remove(it.next());
            }
        }

        boolean isEmpty() {
            return this.mPrefixes.isEmpty() && this.mDnses.isEmpty();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean decrementCounters() {
            return decrementCounter(this.mDnses) | decrementCounter(this.mPrefixes);
        }

        private <T> boolean decrementCounter(HashMap<T, Integer> map) {
            Iterator<Map.Entry<T, Integer>> it = map.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry<T, Integer> next = it.next();
                if (next.getValue().intValue() == 0) {
                    it.remove();
                    z = true;
                } else {
                    next.setValue(Integer.valueOf(next.getValue().intValue() - 1));
                }
            }
            return z;
        }
    }

    public RouterAdvertisementDaemon(InterfaceParams interfaceParams) {
        this.mInterface = interfaceParams;
        this.mAllNodes = new InetSocketAddress(getAllNodesForScopeId(this.mInterface.index), 0);
    }

    public void buildNewRa(RaParams raParams, RaParams raParams2) {
        synchronized (this.mLock) {
            if (raParams != null) {
                try {
                    this.mDeprecatedInfoTracker.putPrefixes(raParams.prefixes);
                    this.mDeprecatedInfoTracker.putDnses(raParams.dnses);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (raParams2 != null) {
                this.mDeprecatedInfoTracker.removePrefixes(raParams2.prefixes);
                this.mDeprecatedInfoTracker.removeDnses(raParams2.dnses);
            }
            this.mRaParams = raParams2;
            assembleRaLocked();
        }
        maybeNotifyMulticastTransmitter();
    }

    public boolean start() {
        if (!createSocket()) {
            return false;
        }
        this.mMulticastTransmitter = new MulticastTransmitter();
        this.mMulticastTransmitter.start();
        this.mUnicastResponder = new UnicastResponder();
        this.mUnicastResponder.start();
        return true;
    }

    public void stop() {
        closeSocket();
        maybeNotifyMulticastTransmitter();
        this.mMulticastTransmitter = null;
        this.mUnicastResponder = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void assembleRaLocked() {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.ip.RouterAdvertisementDaemon.assembleRaLocked():void");
    }

    private void maybeNotifyMulticastTransmitter() {
        MulticastTransmitter multicastTransmitter = this.mMulticastTransmitter;
        if (multicastTransmitter != null) {
            multicastTransmitter.hup();
        }
    }

    private static Inet6Address getAllNodesForScopeId(int i) {
        try {
            return Inet6Address.getByAddress("ff02::1", ALL_NODES, i);
        } catch (UnknownHostException e) {
            Log.wtf(TAG, "Failed to construct ff02::1 InetAddress: " + e);
            return null;
        }
    }

    private static void putHeader(ByteBuffer byteBuffer, boolean z, byte b) {
        byteBuffer.put(ICMPV6_ND_ROUTER_ADVERT).put(asByte(0)).putShort(asShort(0)).put(b).put(z ? asByte(8) : asByte(0)).putShort(z ? asShort(3600) : asShort(0)).putInt(0).putInt(0);
    }

    private static void putSlla(ByteBuffer byteBuffer, byte[] bArr) {
        if (bArr == null || bArr.length != 6) {
            return;
        }
        byteBuffer.put((byte) 1).put((byte) 1).put(bArr);
    }

    private static void putMtu(ByteBuffer byteBuffer, int i) {
        ByteBuffer byteBufferPutShort = byteBuffer.put((byte) 5).put((byte) 1).putShort(asShort(0));
        if (i < 1280) {
            i = 1280;
        }
        byteBufferPutShort.putInt(i);
    }

    private static void putPio(ByteBuffer byteBuffer, IpPrefix ipPrefix, int i, int i2) {
        int prefixLength = ipPrefix.getPrefixLength();
        if (prefixLength != 64) {
            return;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > i) {
            i2 = i;
        }
        byteBuffer.put((byte) 3).put((byte) 4).put(asByte(prefixLength)).put(asByte(192)).putInt(i).putInt(i2).putInt(0).put(ipPrefix.getAddress().getAddress());
    }

    private static void putRdnss(ByteBuffer byteBuffer, Set<Inet6Address> set, int i) {
        HashSet hashSet = new HashSet();
        for (Inet6Address inet6Address : set) {
            if (new LinkAddress(inet6Address, 64).isGlobalPreferred()) {
                hashSet.add(inet6Address);
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        byteBuffer.put((byte) 25).put(asByte((set.size() * 2) + 1)).putShort(asShort(0)).putInt(i);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            byteBuffer.put(((Inet6Address) it.next()).getAddress());
        }
    }

    private boolean createSocket() {
        int andSetThreadStatsTag = TrafficStats.getAndSetThreadStatsTag(-510);
        try {
            try {
                this.mSocket = Os.socket(OsConstants.AF_INET6, OsConstants.SOCK_RAW, OsConstants.IPPROTO_ICMPV6);
                Os.setsockoptTimeval(this.mSocket, OsConstants.SOL_SOCKET, OsConstants.SO_SNDTIMEO, StructTimeval.fromMillis(300L));
                SocketUtils.bindSocketToInterface(this.mSocket, this.mInterface.name);
                TetheringUtils.setupRaSocket(this.mSocket, this.mInterface.index);
                TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
                return true;
            } catch (ErrnoException | IOException e) {
                Log.e(TAG, "Failed to create RA daemon socket: " + e);
                TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
                return false;
            }
        } catch (Throwable th) {
            TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
            throw th;
        }
    }

    private void closeSocket() {
        if (this.mSocket != null) {
            try {
                SocketUtils.closeSocket(this.mSocket);
            } catch (IOException unused) {
            }
        }
        this.mSocket = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSocketValid() {
        FileDescriptor fileDescriptor = this.mSocket;
        return fileDescriptor != null && fileDescriptor.valid();
    }

    private boolean isSuitableDestination(InetSocketAddress inetSocketAddress) {
        if (this.mAllNodes.equals(inetSocketAddress)) {
            return true;
        }
        InetAddress address = inetSocketAddress.getAddress();
        return (address instanceof Inet6Address) && address.isLinkLocalAddress() && ((Inet6Address) address).getScopeId() == this.mInterface.index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeSendRA(InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress == null || !isSuitableDestination(inetSocketAddress)) {
            inetSocketAddress = this.mAllNodes;
        }
        try {
            synchronized (this.mLock) {
                if (this.mRaLength < 16) {
                    return;
                }
                Os.sendto(this.mSocket, this.mRA, 0, this.mRaLength, 0, inetSocketAddress);
                Log.d(TAG, "RA sendto " + inetSocketAddress.getAddress().getHostAddress());
            }
        } catch (ErrnoException | SocketException e) {
            if (isSocketValid()) {
                Log.e(TAG, "sendto error: " + e);
            }
        }
    }

    private final class UnicastResponder extends Thread {
        private final byte[] mSolicitation;
        private final InetSocketAddress mSolicitor;

        private UnicastResponder() {
            this.mSolicitor = new InetSocketAddress(0);
            this.mSolicitation = new byte[1280];
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (RouterAdvertisementDaemon.this.isSocketValid()) {
                try {
                    if (Os.recvfrom(RouterAdvertisementDaemon.this.mSocket, this.mSolicitation, 0, this.mSolicitation.length, 0, this.mSolicitor) >= 1 && this.mSolicitation[0] == RouterAdvertisementDaemon.ICMPV6_ND_ROUTER_SOLICIT) {
                        RouterAdvertisementDaemon.this.maybeSendRA(this.mSolicitor);
                    }
                } catch (ErrnoException | SocketException e) {
                    if (RouterAdvertisementDaemon.this.isSocketValid()) {
                        Log.e(RouterAdvertisementDaemon.TAG, "recvfrom error: " + e);
                    }
                }
            }
        }
    }

    private final class MulticastTransmitter extends Thread {
        private final Random mRandom;
        private final AtomicInteger mUrgentAnnouncements;

        private MulticastTransmitter() {
            this.mRandom = new Random();
            this.mUrgentAnnouncements = new AtomicInteger(0);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (RouterAdvertisementDaemon.this.isSocketValid()) {
                try {
                    Thread.sleep(getNextMulticastTransmitDelayMs());
                } catch (InterruptedException unused) {
                }
                RouterAdvertisementDaemon routerAdvertisementDaemon = RouterAdvertisementDaemon.this;
                routerAdvertisementDaemon.maybeSendRA(routerAdvertisementDaemon.mAllNodes);
                synchronized (RouterAdvertisementDaemon.this.mLock) {
                    if (RouterAdvertisementDaemon.this.mDeprecatedInfoTracker.decrementCounters()) {
                        RouterAdvertisementDaemon.this.assembleRaLocked();
                    }
                }
            }
        }

        public void hup() {
            this.mUrgentAnnouncements.set(4);
            interrupt();
        }

        private int getNextMulticastTransmitDelaySec() {
            synchronized (RouterAdvertisementDaemon.this.mLock) {
                if (RouterAdvertisementDaemon.this.mRaLength < 16) {
                    return 86400;
                }
                boolean z = !RouterAdvertisementDaemon.this.mDeprecatedInfoTracker.isEmpty();
                if (this.mUrgentAnnouncements.getAndDecrement() > 0 || z) {
                    return 3;
                }
                return this.mRandom.nextInt(300) + 300;
            }
        }

        private long getNextMulticastTransmitDelayMs() {
            return ((long) getNextMulticastTransmitDelaySec()) * 1000;
        }
    }
}
