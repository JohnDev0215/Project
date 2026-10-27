package android.net.apf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NattKeepalivePacketDataParcelable;
import android.net.TcpKeepalivePacketDataParcelable;
import android.net.apf.ApfFilter;
import android.net.apf.ApfGenerator;
import android.net.ip.IpClient;
import android.net.metrics.ApfProgramEvent;
import android.net.metrics.ApfStats;
import android.net.metrics.IpConnectivityLog;
import android.net.metrics.RaEvent;
import android.net.networkstack.util.HexDump;
import android.net.networkstack.util.IndentingPrintWriter;
import android.net.util.InterfaceParams;
import android.net.util.NetworkStackUtils;
import android.net.util.SocketUtils;
import android.os.PowerManager;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.SparseArray;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class ApfFilter {
    private final ApfCapabilities mApfCapabilities;
    private final Context mContext;
    private final String mCountAndDropLabel;
    private final String mCountAndPassLabel;
    private byte[] mDataSnapshot;
    private final boolean mDrop802_3Frames;
    private final int[] mEthTypeBlackList;
    byte[] mHardwareAddress;
    private byte[] mIPv4Address;
    private int mIPv4PrefixLength;
    private boolean mInDozeMode;
    private final InterfaceParams mInterfaceParams;
    private final IpClient.IpClientCallbacksWrapper mIpClientCallback;
    private ApfProgramEvent.Builder mLastInstallEvent;
    private byte[] mLastInstalledProgram;
    private long mLastInstalledProgramMinLifetime;
    private long mLastTimeInstalledProgram;
    private final IpConnectivityLog mMetricsLog;
    private final int mMinRdnssLifetimeSec;
    private boolean mMulticastFilter;
    ReceiveThread mReceiveThread;
    private long mUniqueCounter;
    private static final byte[] ETH_BROADCAST_MAC_ADDRESS = {-1, -1, -1, -1, -1, -1};
    private static final byte[] IPV6_ALL_NODES_ADDRESS = {-1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    private static final byte[] ARP_IPV4_HEADER = {0, 1, 8, 0, 6, 4};
    private final BroadcastReceiver mDeviceIdleReceiver = new BroadcastReceiver() { // from class: android.net.apf.ApfFilter.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.os.action.DEVICE_IDLE_MODE_CHANGED")) {
                ApfFilter.this.setDozeMode(((PowerManager) context.getSystemService("power")).isDeviceIdleMode());
            }
        }
    };
    private ArrayList<Ra> mRas = new ArrayList<>();
    private SparseArray<KeepalivePacket> mKeepalivePackets = new SparseArray<>();
    private int mNumProgramUpdates = 0;
    private int mNumProgramUpdatesAllowingMulticast = 0;

    public static class ApfConfiguration {
        public ApfCapabilities apfCapabilities;
        public int[] ethTypeBlackList;
        public boolean ieee802_3Filter;
        public int minRdnssLifetimeSec;
        public boolean multicastFilter;
    }

    private enum ProcessRaResult {
        MATCH,
        DROPPED,
        PARSE_ERROR,
        ZERO_LIFETIME,
        UPDATE_NEW_RA,
        UPDATE_EXPIRY
    }

    private static int uint8(byte b) {
        return b & 255;
    }

    public enum Counter {
        RESERVED_OOB,
        TOTAL_PACKETS,
        PASSED_ARP,
        PASSED_DHCP,
        PASSED_IPV4,
        PASSED_IPV6_NON_ICMP,
        PASSED_IPV4_UNICAST,
        PASSED_IPV6_ICMP,
        PASSED_IPV6_UNICAST_NON_ICMP,
        PASSED_ARP_NON_IPV4,
        PASSED_ARP_UNKNOWN,
        PASSED_ARP_UNICAST_REPLY,
        PASSED_NON_IP_UNICAST,
        DROPPED_ETH_BROADCAST,
        DROPPED_RA,
        DROPPED_GARP_REPLY,
        DROPPED_ARP_OTHER_HOST,
        DROPPED_IPV4_L2_BROADCAST,
        DROPPED_IPV4_BROADCAST_ADDR,
        DROPPED_IPV4_BROADCAST_NET,
        DROPPED_IPV4_MULTICAST,
        DROPPED_IPV6_ROUTER_SOLICITATION,
        DROPPED_IPV6_MULTICAST_NA,
        DROPPED_IPV6_MULTICAST,
        DROPPED_IPV6_MULTICAST_PING,
        DROPPED_IPV6_NON_ICMP_MULTICAST,
        DROPPED_802_3_FRAME,
        DROPPED_ETHERTYPE_BLACKLISTED,
        DROPPED_ARP_REPLY_SPA_NO_HOST,
        DROPPED_IPV4_KEEPALIVE_ACK,
        DROPPED_IPV6_KEEPALIVE_ACK,
        DROPPED_IPV4_NATT_KEEPALIVE;

        public int offset() {
            return (-ordinal()) * 4;
        }

        public static int totalSize() {
            return (((Counter[]) Counter.class.getEnumConstants()).length - 1) * 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeSetupCounter(ApfGenerator apfGenerator, Counter counter) {
        if (this.mApfCapabilities.hasDataAccess()) {
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R1, counter.offset());
        }
    }

    class ReceiveThread extends Thread {
        private final FileDescriptor mSocket;
        private volatile boolean mStopped;
        private final byte[] mPacket = new byte[1514];
        private final long mStart = SystemClock.elapsedRealtime();
        private int mReceivedRas = 0;
        private int mMatchingRas = 0;
        private int mDroppedRas = 0;
        private int mParseErrors = 0;
        private int mZeroLifetimeRas = 0;
        private int mProgramUpdates = 0;

        public ReceiveThread(FileDescriptor fileDescriptor) {
            this.mSocket = fileDescriptor;
        }

        public void halt() {
            this.mStopped = true;
            NetworkStackUtils.closeSocketQuietly(this.mSocket);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            ApfFilter.this.log("begin monitoring");
            while (!this.mStopped) {
                try {
                    updateStats(ApfFilter.this.processRa(this.mPacket, Os.read(this.mSocket, this.mPacket, 0, this.mPacket.length)));
                } catch (ErrnoException | IOException e) {
                    if (!this.mStopped) {
                        Log.e("ApfFilter", "Read error", e);
                    }
                }
            }
            logStats();
        }

        private void updateStats(ProcessRaResult processRaResult) {
            this.mReceivedRas++;
            switch (AnonymousClass2.$SwitchMap$android$net$apf$ApfFilter$ProcessRaResult[processRaResult.ordinal()]) {
                case 1:
                    this.mMatchingRas++;
                    break;
                case 2:
                    this.mDroppedRas++;
                    break;
                case 3:
                    this.mParseErrors++;
                    break;
                case 4:
                    this.mZeroLifetimeRas++;
                    break;
                case 5:
                    this.mMatchingRas++;
                    this.mProgramUpdates++;
                    break;
                case 6:
                    this.mProgramUpdates++;
                    break;
            }
        }

        private void logStats() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            synchronized (this) {
                ApfFilter.this.mMetricsLog.log(new ApfStats.Builder().setReceivedRas(this.mReceivedRas).setMatchingRas(this.mMatchingRas).setDroppedRas(this.mDroppedRas).setParseErrors(this.mParseErrors).setZeroLifetimeRas(this.mZeroLifetimeRas).setProgramUpdates(this.mProgramUpdates).setDurationMs(jElapsedRealtime - this.mStart).setMaxProgramSize(ApfFilter.this.mApfCapabilities.maximumApfProgramSize).setProgramUpdatesAll(ApfFilter.this.mNumProgramUpdates).setProgramUpdatesAllowingMulticast(ApfFilter.this.mNumProgramUpdatesAllowingMulticast).build());
                ApfFilter.this.logApfProgramEventLocked(jElapsedRealtime / 1000);
            }
        }
    }

    /* JADX INFO: renamed from: android.net.apf.ApfFilter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult;

        static {
            int[] iArr = new int[ProcessRaResult.values().length];
            $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult = iArr;
            try {
                iArr[ProcessRaResult.MATCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult[ProcessRaResult.DROPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult[ProcessRaResult.PARSE_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult[ProcessRaResult.ZERO_LIFETIME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult[ProcessRaResult.UPDATE_EXPIRY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$net$apf$ApfFilter$ProcessRaResult[ProcessRaResult.UPDATE_NEW_RA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    ApfFilter(Context context, ApfConfiguration apfConfiguration, InterfaceParams interfaceParams, IpClient.IpClientCallbacksWrapper ipClientCallbacksWrapper, IpConnectivityLog ipConnectivityLog) {
        ApfCapabilities apfCapabilities = apfConfiguration.apfCapabilities;
        this.mApfCapabilities = apfCapabilities;
        this.mIpClientCallback = ipClientCallbacksWrapper;
        this.mInterfaceParams = interfaceParams;
        this.mMulticastFilter = apfConfiguration.multicastFilter;
        this.mDrop802_3Frames = apfConfiguration.ieee802_3Filter;
        this.mMinRdnssLifetimeSec = apfConfiguration.minRdnssLifetimeSec;
        this.mContext = context;
        if (apfCapabilities.hasDataAccess()) {
            this.mCountAndPassLabel = "countAndPass";
            this.mCountAndDropLabel = "countAndDrop";
        } else {
            this.mCountAndPassLabel = "__PASS__";
            this.mCountAndDropLabel = "__DROP__";
        }
        this.mEthTypeBlackList = filterEthTypeBlackList(apfConfiguration.ethTypeBlackList);
        this.mMetricsLog = ipConnectivityLog;
        maybeStartFilter();
        this.mContext.registerReceiver(this.mDeviceIdleReceiver, new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
    }

    public synchronized void setDataSnapshot(byte[] bArr) {
        this.mDataSnapshot = bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d("ApfFilter", "(" + this.mInterfaceParams.name + "): " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getUniqueNumberLocked() {
        long j = this.mUniqueCounter;
        this.mUniqueCounter = 1 + j;
        return j;
    }

    private static int[] filterEthTypeBlackList(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        int length = iArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = iArr[i];
            if (i2 >= 1536 && i2 <= 65535 && !arrayList.contains(Integer.valueOf(i2))) {
                if (arrayList.size() == 20) {
                    Log.w("ApfFilter", "Passed EthType Black List size too large (" + arrayList.size() + ") using top 20 protocols");
                    break;
                }
                arrayList.add(Integer.valueOf(i2));
            }
            i++;
        }
        return arrayList.stream().mapToInt(new ToIntFunction() { // from class: android.net.apf.-$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
    }

    void maybeStartFilter() {
        try {
            this.mHardwareAddress = this.mInterfaceParams.macAddr.toByteArray();
            synchronized (this) {
                if (this.mApfCapabilities.hasDataAccess()) {
                    this.mIpClientCallback.installPacketFilter(new byte[this.mApfCapabilities.maximumApfProgramSize]);
                }
                installNewProgramLocked();
            }
            FileDescriptor fileDescriptorSocket = Os.socket(OsConstants.AF_PACKET, OsConstants.SOCK_RAW, OsConstants.ETH_P_IPV6);
            Os.bind(fileDescriptorSocket, SocketUtils.makePacketSocketAddress(OsConstants.ETH_P_IPV6, this.mInterfaceParams.index));
            NetworkStackUtils.attachRaFilter(fileDescriptorSocket, this.mApfCapabilities.apfPacketFormat);
            ReceiveThread receiveThread = new ReceiveThread(fileDescriptorSocket);
            this.mReceiveThread = receiveThread;
            receiveThread.start();
        } catch (ErrnoException | SocketException e) {
            Log.e("ApfFilter", "Error starting filter", e);
        }
    }

    protected long currentTimeSeconds() {
        return SystemClock.elapsedRealtime() / 1000;
    }

    public static class InvalidRaException extends Exception {
        public InvalidRaException(String str) {
            super(str);
        }
    }

    private static class PacketSection {
        public final int length;
        public final long lifetime;
        public final int option;
        public final int start;
        public final Type type;

        public enum Type {
            MATCH,
            IGNORE,
            LIFETIME
        }

        PacketSection(int i, int i2, Type type, int i3, long j) {
            this.start = i;
            this.length = i2;
            this.type = type;
            this.option = i3;
            this.lifetime = j;
        }

        public String toString() {
            Type type = this.type;
            return type == Type.LIFETIME ? String.format("%s: (%d, %d) %d %d", type, Integer.valueOf(this.start), Integer.valueOf(this.length), Integer.valueOf(this.option), Long.valueOf(this.lifetime)) : String.format("%s: (%d, %d)", type, Integer.valueOf(this.start), Integer.valueOf(this.length));
        }
    }

    class Ra {
        long mLastSeen;
        long mMinLifetime;
        private final ByteBuffer mPacket;
        private final ArrayList<PacketSection> mPacketSections = new ArrayList<>();
        private final ArrayList<Integer> mPrefixOptionOffsets = new ArrayList<>();
        private final ArrayList<Integer> mRdnssOptionOffsets = new ArrayList<>();
        private final ArrayList<Integer> mRioOptionOffsets = new ArrayList<>();
        int seenCount = 0;

        String getLastMatchingPacket() {
            return HexDump.toHexString(this.mPacket.array(), 0, this.mPacket.capacity(), false);
        }

        private String IPv6AddresstoString(int i) {
            int i2;
            try {
                byte[] bArrArray = this.mPacket.array();
                if (i >= 0 && (i2 = i + 16) <= bArrArray.length && i2 >= i) {
                    return ((Inet6Address) InetAddress.getByAddress(Arrays.copyOfRange(bArrArray, i, i2))).getHostAddress();
                }
            } catch (ClassCastException | UnsupportedOperationException | UnknownHostException unused) {
            }
            return "???";
        }

        private void prefixOptionToString(StringBuffer stringBuffer, int i) {
            stringBuffer.append(String.format("%s/%d %ds/%ds ", IPv6AddresstoString(i + 16), Integer.valueOf(ApfFilter.getUint8(this.mPacket, i + 2)), Long.valueOf(ApfFilter.getUint32(this.mPacket, i + 4)), Long.valueOf(ApfFilter.getUint32(this.mPacket, i + 8))));
        }

        private void rdnssOptionToString(StringBuffer stringBuffer, int i) {
            int uint8 = ApfFilter.getUint8(this.mPacket, i + 1) * 8;
            if (uint8 < 24) {
                return;
            }
            long uint32 = ApfFilter.getUint32(this.mPacket, i + 4);
            int i2 = (uint8 - 8) / 16;
            stringBuffer.append("DNS ");
            stringBuffer.append(uint32);
            stringBuffer.append("s");
            for (int i3 = 0; i3 < i2; i3++) {
                stringBuffer.append(" ");
                stringBuffer.append(IPv6AddresstoString(i + 8 + (i3 * 16)));
            }
            stringBuffer.append(" ");
        }

        private void rioOptionToString(StringBuffer stringBuffer, int i) {
            int uint8 = ApfFilter.getUint8(this.mPacket, i + 1) * 8;
            if (uint8 < 8 || uint8 > 24) {
                return;
            }
            int uint82 = ApfFilter.getUint8(this.mPacket, i + 2);
            long uint32 = ApfFilter.getUint32(this.mPacket, i + 4);
            byte[] bArr = new byte[16];
            System.arraycopy(this.mPacket.array(), i + 8, bArr, 0, uint8 - 8);
            stringBuffer.append("RIO ");
            stringBuffer.append(uint32);
            stringBuffer.append("s ");
            try {
                stringBuffer.append(((Inet6Address) InetAddress.getByAddress(bArr)).getHostAddress());
            } catch (UnknownHostException unused) {
                stringBuffer.append("???");
            }
            stringBuffer.append("/");
            stringBuffer.append(uint82);
            stringBuffer.append(" ");
        }

        public String toString() {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(String.format("RA %s -> %s %ds ", IPv6AddresstoString(22), IPv6AddresstoString(38), Integer.valueOf(ApfFilter.getUint16(this.mPacket, 60))));
                Iterator<Integer> it = this.mPrefixOptionOffsets.iterator();
                while (it.hasNext()) {
                    prefixOptionToString(stringBuffer, it.next().intValue());
                }
                Iterator<Integer> it2 = this.mRdnssOptionOffsets.iterator();
                while (it2.hasNext()) {
                    rdnssOptionToString(stringBuffer, it2.next().intValue());
                }
                Iterator<Integer> it3 = this.mRioOptionOffsets.iterator();
                while (it3.hasNext()) {
                    rioOptionToString(stringBuffer, it3.next().intValue());
                }
                return stringBuffer.toString();
            } catch (IndexOutOfBoundsException | BufferUnderflowException unused) {
                return "<Malformed RA>";
            }
        }

        private void addMatchSection(int i) {
            if (i == 0) {
                return;
            }
            this.mPacketSections.add(new PacketSection(this.mPacket.position(), i, PacketSection.Type.MATCH, 0, 0L));
            ByteBuffer byteBuffer = this.mPacket;
            byteBuffer.position(byteBuffer.position() + i);
        }

        private void addMatchUntil(int i) {
            addMatchSection(i - this.mPacket.position());
        }

        private void addIgnoreSection(int i) {
            this.mPacketSections.add(new PacketSection(this.mPacket.position(), i, PacketSection.Type.IGNORE, 0, 0L));
            ByteBuffer byteBuffer = this.mPacket;
            byteBuffer.position(byteBuffer.position() + i);
        }

        private void addLifetimeSection(int i, int i2, long j) {
            this.mPacketSections.add(new PacketSection(this.mPacket.position(), i, PacketSection.Type.LIFETIME, i2, j));
            ByteBuffer byteBuffer = this.mPacket;
            byteBuffer.position(byteBuffer.position() + i);
        }

        private long add4ByteLifetimeOption(int i, int i2) {
            addMatchSection(4);
            ByteBuffer byteBuffer = this.mPacket;
            long uint32 = ApfFilter.getUint32(byteBuffer, byteBuffer.position());
            addLifetimeSection(4, i, uint32);
            addMatchSection((i2 - 4) - 4);
            return uint32;
        }

        private boolean shouldIgnoreLifetime(int i, long j) {
            return i == 25 && j != 0 && j < ((long) ApfFilter.this.mMinRdnssLifetimeSec);
        }

        private boolean isRelevantLifetime(PacketSection packetSection) {
            return packetSection.type == PacketSection.Type.LIFETIME && !shouldIgnoreLifetime(packetSection.option, packetSection.lifetime);
        }

        Ra(byte[] bArr, int i) throws InvalidRaException {
            if (i < 70) {
                throw new InvalidRaException("Not an ICMP6 router advertisement: too short");
            }
            this.mPacket = ByteBuffer.wrap(Arrays.copyOf(bArr, i));
            this.mLastSeen = ApfFilter.this.currentTimeSeconds();
            if (ApfFilter.getUint16(this.mPacket, 12) != OsConstants.ETH_P_IPV6 || ApfFilter.getUint8(this.mPacket, 20) != OsConstants.IPPROTO_ICMPV6 || ApfFilter.getUint8(this.mPacket, 54) != 134) {
                throw new InvalidRaException("Not an ICMP6 router advertisement");
            }
            RaEvent.Builder builder = new RaEvent.Builder();
            addMatchUntil(15);
            addIgnoreSection(3);
            addMatchUntil(56);
            addIgnoreSection(2);
            addMatchUntil(60);
            long uint16 = ApfFilter.getUint16(this.mPacket, 60);
            addLifetimeSection(2, 0, uint16);
            builder.updateRouterLifetime(uint16);
            addMatchUntil(70);
            while (this.mPacket.hasRemaining()) {
                int iPosition = this.mPacket.position();
                int uint8 = ApfFilter.getUint8(this.mPacket, iPosition);
                int uint82 = ApfFilter.getUint8(this.mPacket, iPosition + 1) * 8;
                if (uint8 == 3) {
                    this.mPrefixOptionOffsets.add(Integer.valueOf(iPosition));
                    addMatchSection(4);
                    ByteBuffer byteBuffer = this.mPacket;
                    long uint32 = ApfFilter.getUint32(byteBuffer, byteBuffer.position());
                    addLifetimeSection(4, 3, uint32);
                    builder.updatePrefixValidLifetime(uint32);
                    ByteBuffer byteBuffer2 = this.mPacket;
                    long uint322 = ApfFilter.getUint32(byteBuffer2, byteBuffer2.position());
                    addLifetimeSection(4, 3, uint322);
                    builder.updatePrefixPreferredLifetime(uint322);
                    addMatchSection(4);
                    addMatchSection(16);
                } else if (uint8 == 31) {
                    builder.updateDnsslLifetime(add4ByteLifetimeOption(uint8, uint82));
                } else if (uint8 == 24) {
                    this.mRioOptionOffsets.add(Integer.valueOf(iPosition));
                    builder.updateRouteInfoLifetime(add4ByteLifetimeOption(uint8, uint82));
                } else if (uint8 == 25) {
                    this.mRdnssOptionOffsets.add(Integer.valueOf(iPosition));
                    builder.updateRdnssLifetime(add4ByteLifetimeOption(uint8, uint82));
                } else {
                    this.mPacket.position(iPosition + uint82);
                }
                if (uint82 <= 0) {
                    throw new InvalidRaException(String.format("Invalid option length opt=%d len=%d", Integer.valueOf(uint8), Integer.valueOf(uint82)));
                }
            }
            this.mMinLifetime = minLifetime();
            ApfFilter.this.mMetricsLog.log(builder.build());
        }

        boolean matches(byte[] bArr, int i) {
            if (i != this.mPacket.capacity()) {
                return false;
            }
            byte[] bArrArray = this.mPacket.array();
            for (PacketSection packetSection : this.mPacketSections) {
                if (packetSection.type == PacketSection.Type.MATCH) {
                    for (int i2 = packetSection.start; i2 < packetSection.start + packetSection.length; i2++) {
                        if (bArr[i2] != bArrArray[i2]) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        long minLifetime() {
            long jMin = Long.MAX_VALUE;
            for (PacketSection packetSection : this.mPacketSections) {
                if (isRelevantLifetime(packetSection)) {
                    jMin = Math.min(jMin, packetSection.lifetime);
                }
            }
            return jMin;
        }

        long currentLifetime() {
            return this.mMinLifetime - (ApfFilter.this.currentTimeSeconds() - this.mLastSeen);
        }

        boolean isExpired() {
            return currentLifetime() <= 0;
        }

        long generateFilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
            String str = "Ra" + ApfFilter.this.getUniqueNumberLocked();
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R0, 14);
            apfGenerator.addJumpIfR0NotEquals(this.mPacket.capacity(), str);
            int iCurrentLifetime = (int) (currentLifetime() / 6);
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R0, 15);
            apfGenerator.addJumpIfR0GreaterThan(iCurrentLifetime, str);
            for (PacketSection packetSection : this.mPacketSections) {
                if (packetSection.type == PacketSection.Type.MATCH) {
                    apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, packetSection.start);
                    ApfGenerator.Register register = ApfGenerator.Register.R0;
                    byte[] bArrArray = this.mPacket.array();
                    int i = packetSection.start;
                    apfGenerator.addJumpIfBytesNotEqual(register, Arrays.copyOfRange(bArrArray, i, packetSection.length + i), str);
                }
                if (isRelevantLifetime(packetSection)) {
                    int i2 = packetSection.length;
                    if (i2 == 2) {
                        apfGenerator.addLoad16(ApfGenerator.Register.R0, packetSection.start);
                    } else if (i2 == 4) {
                        apfGenerator.addLoad32(ApfGenerator.Register.R0, packetSection.start);
                    } else {
                        throw new IllegalStateException("bogus lifetime size " + packetSection.length);
                    }
                    apfGenerator.addJumpIfR0LessThan(iCurrentLifetime, str);
                }
            }
            ApfFilter.this.maybeSetupCounter(apfGenerator, Counter.DROPPED_RA);
            apfGenerator.addJump(ApfFilter.this.mCountAndDropLabel);
            apfGenerator.defineLabel(str);
            return iCurrentLifetime;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class KeepalivePacket {
        abstract void generateFilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException;

        private KeepalivePacket() {
        }
    }

    private class NattKeepaliveResponse extends KeepalivePacket {
        protected final NattKeepaliveResponseData mPacket;
        protected final byte[] mPayload;
        protected final byte[] mPortFingerprint;
        protected final byte[] mSrcDstAddr;

        protected class NattKeepaliveResponseData {
            public final byte[] dstAddress;
            public final int dstPort;
            public final byte[] srcAddress;
            public final int srcPort;

            NattKeepaliveResponseData(NattKeepaliveResponse nattKeepaliveResponse, NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable) {
                this.srcAddress = nattKeepalivePacketDataParcelable.dstAddress;
                this.srcPort = nattKeepalivePacketDataParcelable.dstPort;
                this.dstAddress = nattKeepalivePacketDataParcelable.srcAddress;
                this.dstPort = nattKeepalivePacketDataParcelable.srcPort;
            }
        }

        NattKeepaliveResponse(NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable) {
            super();
            this.mPayload = new byte[]{-1};
            NattKeepaliveResponseData nattKeepaliveResponseData = new NattKeepaliveResponseData(this, nattKeepalivePacketDataParcelable);
            this.mPacket = nattKeepaliveResponseData;
            this.mSrcDstAddr = ApfFilter.concatArrays(nattKeepaliveResponseData.srcAddress, nattKeepaliveResponseData.dstAddress);
            NattKeepaliveResponseData nattKeepaliveResponseData2 = this.mPacket;
            this.mPortFingerprint = generatePortFingerprint(nattKeepaliveResponseData2.srcPort, nattKeepaliveResponseData2.dstPort);
        }

        byte[] generatePortFingerprint(int i, int i2) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            byteBufferAllocate.putShort((short) i);
            byteBufferAllocate.putShort((short) i2);
            return byteBufferAllocate.array();
        }

        @Override // android.net.apf.ApfFilter.KeepalivePacket
        void generateFilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
            String str = "natt_keepalive_filter" + ApfFilter.this.getUniqueNumberLocked();
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 26);
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mSrcDstAddr, str);
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R0, 13);
            apfGenerator.addAdd(8);
            apfGenerator.addSwap();
            apfGenerator.addLoad16(ApfGenerator.Register.R0, 16);
            apfGenerator.addNeg(ApfGenerator.Register.R1);
            apfGenerator.addAddR1();
            apfGenerator.addJumpIfR0NotEquals(1, str);
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R0, 13);
            apfGenerator.addAdd(14);
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mPortFingerprint, str);
            apfGenerator.addAdd(8);
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mPayload, str);
            ApfFilter.this.maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV4_NATT_KEEPALIVE);
            apfGenerator.addJump(ApfFilter.this.mCountAndDropLabel);
            apfGenerator.defineLabel(str);
        }

        public String toString() {
            try {
                return String.format("%s -> %s", NetworkStackUtils.addressAndPortToString(InetAddress.getByAddress(this.mPacket.srcAddress), this.mPacket.srcPort), NetworkStackUtils.addressAndPortToString(InetAddress.getByAddress(this.mPacket.dstAddress), this.mPacket.dstPort));
            } catch (UnknownHostException unused) {
                return "Unknown host";
            }
        }
    }

    private static abstract class TcpKeepaliveAck extends KeepalivePacket {
        protected final TcpKeepaliveAckData mPacket;
        protected final byte[] mPortSeqAckFingerprint;
        protected final byte[] mSrcDstAddr;

        protected static class TcpKeepaliveAckData {
            public final int ack;
            public final byte[] dstAddress;
            public final int dstPort;
            public final int seq;
            public final byte[] srcAddress;
            public final int srcPort;

            TcpKeepaliveAckData(TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) {
                this.srcAddress = tcpKeepalivePacketDataParcelable.dstAddress;
                this.srcPort = tcpKeepalivePacketDataParcelable.dstPort;
                this.dstAddress = tcpKeepalivePacketDataParcelable.srcAddress;
                this.dstPort = tcpKeepalivePacketDataParcelable.srcPort;
                this.seq = tcpKeepalivePacketDataParcelable.ack;
                this.ack = tcpKeepalivePacketDataParcelable.seq + 1;
            }
        }

        TcpKeepaliveAck(TcpKeepaliveAckData tcpKeepaliveAckData, byte[] bArr) {
            super();
            this.mPacket = tcpKeepaliveAckData;
            this.mSrcDstAddr = bArr;
            this.mPortSeqAckFingerprint = generatePortSeqAckFingerprint(tcpKeepaliveAckData.srcPort, tcpKeepaliveAckData.dstPort, tcpKeepaliveAckData.seq, tcpKeepaliveAckData.ack);
        }

        static byte[] generatePortSeqAckFingerprint(int i, int i2, int i3, int i4) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(12);
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            byteBufferAllocate.putShort((short) i);
            byteBufferAllocate.putShort((short) i2);
            byteBufferAllocate.putInt(i3);
            byteBufferAllocate.putInt(i4);
            return byteBufferAllocate.array();
        }

        public String toString() {
            try {
                return String.format("%s -> %s , seq=%d, ack=%d", NetworkStackUtils.addressAndPortToString(InetAddress.getByAddress(this.mPacket.srcAddress), this.mPacket.srcPort), NetworkStackUtils.addressAndPortToString(InetAddress.getByAddress(this.mPacket.dstAddress), this.mPacket.dstPort), Long.valueOf(Integer.toUnsignedLong(this.mPacket.seq)), Long.valueOf(Integer.toUnsignedLong(this.mPacket.ack)));
            } catch (UnknownHostException unused) {
                return "Unknown host";
            }
        }
    }

    private class TcpKeepaliveAckV4 extends TcpKeepaliveAck {
        TcpKeepaliveAckV4(ApfFilter apfFilter, TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) {
            this(new TcpKeepaliveAck.TcpKeepaliveAckData(tcpKeepalivePacketDataParcelable));
        }

        TcpKeepaliveAckV4(TcpKeepaliveAck.TcpKeepaliveAckData tcpKeepaliveAckData) {
            super(tcpKeepaliveAckData, ApfFilter.concatArrays(tcpKeepaliveAckData.srcAddress, tcpKeepaliveAckData.dstAddress));
        }

        @Override // android.net.apf.ApfFilter.KeepalivePacket
        void generateFilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
            String str = "keepalive_ack" + ApfFilter.this.getUniqueNumberLocked();
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 26);
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mSrcDstAddr, str);
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R1, 13);
            apfGenerator.addLoad8Indexed(ApfGenerator.Register.R0, 26);
            apfGenerator.addRightShift(2);
            apfGenerator.addAddR1();
            apfGenerator.addLoad16(ApfGenerator.Register.R1, 16);
            apfGenerator.addNeg(ApfGenerator.Register.R0);
            apfGenerator.addAddR1();
            apfGenerator.addJumpIfR0NotEquals(0, str);
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R1, 13);
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 14);
            apfGenerator.addAddR1();
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mPortSeqAckFingerprint, str);
            ApfFilter.this.maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV4_KEEPALIVE_ACK);
            apfGenerator.addJump(ApfFilter.this.mCountAndDropLabel);
            apfGenerator.defineLabel(str);
        }
    }

    private class TcpKeepaliveAckV6 extends TcpKeepaliveAck {
        TcpKeepaliveAckV6(ApfFilter apfFilter, TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) {
            this(apfFilter, new TcpKeepaliveAck.TcpKeepaliveAckData(tcpKeepalivePacketDataParcelable));
        }

        TcpKeepaliveAckV6(ApfFilter apfFilter, TcpKeepaliveAck.TcpKeepaliveAckData tcpKeepaliveAckData) {
            super(tcpKeepaliveAckData, ApfFilter.concatArrays(tcpKeepaliveAckData.srcAddress, tcpKeepaliveAckData.dstAddress));
        }

        @Override // android.net.apf.ApfFilter.KeepalivePacket
        void generateFilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
            throw new UnsupportedOperationException("IPv6 TCP Keepalive is not supported yet");
        }
    }

    private void generateArpFilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 14);
        maybeSetupCounter(apfGenerator, Counter.PASSED_ARP_NON_IPV4);
        apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, ARP_IPV4_HEADER, this.mCountAndPassLabel);
        apfGenerator.addLoad16(ApfGenerator.Register.R0, 20);
        apfGenerator.addJumpIfR0Equals(1, "checkTargetIPv4");
        maybeSetupCounter(apfGenerator, Counter.PASSED_ARP_UNKNOWN);
        apfGenerator.addJumpIfR0NotEquals(2, this.mCountAndPassLabel);
        apfGenerator.addLoad32(ApfGenerator.Register.R0, 28);
        maybeSetupCounter(apfGenerator, Counter.DROPPED_ARP_REPLY_SPA_NO_HOST);
        apfGenerator.addJumpIfR0Equals(0, this.mCountAndDropLabel);
        apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 0);
        maybeSetupCounter(apfGenerator, Counter.PASSED_ARP_UNICAST_REPLY);
        apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, ETH_BROADCAST_MAC_ADDRESS, this.mCountAndPassLabel);
        apfGenerator.defineLabel("checkTargetIPv4");
        if (this.mIPv4Address == null) {
            apfGenerator.addLoad32(ApfGenerator.Register.R0, 38);
            maybeSetupCounter(apfGenerator, Counter.DROPPED_GARP_REPLY);
            apfGenerator.addJumpIfR0Equals(0, this.mCountAndDropLabel);
        } else {
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 38);
            maybeSetupCounter(apfGenerator, Counter.DROPPED_ARP_OTHER_HOST);
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mIPv4Address, this.mCountAndDropLabel);
        }
        maybeSetupCounter(apfGenerator, Counter.PASSED_ARP);
        apfGenerator.addJump(this.mCountAndPassLabel);
    }

    private void generateIPv4FilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        if (this.mMulticastFilter) {
            apfGenerator.addLoad8(ApfGenerator.Register.R0, 23);
            apfGenerator.addJumpIfR0NotEquals(OsConstants.IPPROTO_UDP, "skip_dhcp_v4_filter");
            apfGenerator.addLoad16(ApfGenerator.Register.R0, 20);
            apfGenerator.addJumpIfR0AnyBitsSet(8191, "skip_dhcp_v4_filter");
            apfGenerator.addLoadFromMemory(ApfGenerator.Register.R1, 13);
            apfGenerator.addLoad16Indexed(ApfGenerator.Register.R0, 16);
            apfGenerator.addJumpIfR0NotEquals(68, "skip_dhcp_v4_filter");
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 50);
            apfGenerator.addAddR1();
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, this.mHardwareAddress, "skip_dhcp_v4_filter");
            maybeSetupCounter(apfGenerator, Counter.PASSED_DHCP);
            apfGenerator.addJump(this.mCountAndPassLabel);
            apfGenerator.defineLabel("skip_dhcp_v4_filter");
            apfGenerator.addLoad8(ApfGenerator.Register.R0, 30);
            apfGenerator.addAnd(240);
            maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV4_MULTICAST);
            apfGenerator.addJumpIfR0Equals(224, this.mCountAndDropLabel);
            maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV4_BROADCAST_ADDR);
            apfGenerator.addLoad32(ApfGenerator.Register.R0, 30);
            apfGenerator.addJumpIfR0Equals(-1, this.mCountAndDropLabel);
            if (this.mIPv4Address != null && this.mIPv4PrefixLength < 31) {
                maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV4_BROADCAST_NET);
                apfGenerator.addJumpIfR0Equals(ipv4BroadcastAddress(this.mIPv4Address, this.mIPv4PrefixLength), this.mCountAndDropLabel);
            }
            generateV4KeepaliveFilters(apfGenerator);
            generateV4NattKeepaliveFilters(apfGenerator);
            maybeSetupCounter(apfGenerator, Counter.PASSED_IPV4_UNICAST);
            apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 0);
            apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, ETH_BROADCAST_MAC_ADDRESS, this.mCountAndPassLabel);
            maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV4_L2_BROADCAST);
            apfGenerator.addJump(this.mCountAndDropLabel);
        } else {
            generateV4KeepaliveFilters(apfGenerator);
            generateV4NattKeepaliveFilters(apfGenerator);
        }
        maybeSetupCounter(apfGenerator, Counter.PASSED_IPV4);
        apfGenerator.addJump(this.mCountAndPassLabel);
    }

    private void generateKeepaliveFilters(ApfGenerator apfGenerator, final Class<?> cls, int i, int i2, String str) throws ApfGenerator.IllegalInstructionException {
        if (NetworkStackUtils.any(this.mKeepalivePackets, new Predicate() { // from class: android.net.apf.-$$Lambda$ApfFilter$eonHm6slAtXMiMDd5rCyQhJglJ0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return cls.isInstance((ApfFilter.KeepalivePacket) obj);
            }
        })) {
            apfGenerator.addLoad8(ApfGenerator.Register.R0, i2);
            apfGenerator.addJumpIfR0NotEquals(i, str);
            for (int i3 = 0; i3 < this.mKeepalivePackets.size(); i3++) {
                KeepalivePacket keepalivePacketValueAt = this.mKeepalivePackets.valueAt(i3);
                if (cls.isInstance(keepalivePacketValueAt)) {
                    keepalivePacketValueAt.generateFilterLocked(apfGenerator);
                }
            }
            apfGenerator.defineLabel(str);
        }
    }

    private void generateV4KeepaliveFilters(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        generateKeepaliveFilters(apfGenerator, TcpKeepaliveAckV4.class, OsConstants.IPPROTO_TCP, 23, "skip_v4_keepalive_filter");
    }

    private void generateV4NattKeepaliveFilters(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        generateKeepaliveFilters(apfGenerator, NattKeepaliveResponse.class, OsConstants.IPPROTO_UDP, 23, "skip_v4_nattkeepalive_filter");
    }

    private void generateIPv6FilterLocked(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        apfGenerator.addLoad8(ApfGenerator.Register.R0, 20);
        if (this.mMulticastFilter) {
            if (this.mInDozeMode) {
                apfGenerator.addJumpIfR0NotEquals(OsConstants.IPPROTO_ICMPV6, "dropAllIPv6Multicast");
                apfGenerator.addLoad8(ApfGenerator.Register.R0, 54);
                apfGenerator.addJumpIfR0NotEquals(128, "skipIPv6MulticastFilter");
            } else {
                apfGenerator.addJumpIfR0Equals(OsConstants.IPPROTO_ICMPV6, "skipIPv6MulticastFilter");
            }
            apfGenerator.defineLabel("dropAllIPv6Multicast");
            maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV6_NON_ICMP_MULTICAST);
            apfGenerator.addLoad8(ApfGenerator.Register.R0, 38);
            apfGenerator.addJumpIfR0Equals(255, this.mCountAndDropLabel);
            generateV6KeepaliveFilters(apfGenerator);
            maybeSetupCounter(apfGenerator, Counter.PASSED_IPV6_UNICAST_NON_ICMP);
            apfGenerator.addJump(this.mCountAndPassLabel);
            apfGenerator.defineLabel("skipIPv6MulticastFilter");
        } else {
            generateV6KeepaliveFilters(apfGenerator);
            maybeSetupCounter(apfGenerator, Counter.PASSED_IPV6_NON_ICMP);
            apfGenerator.addJumpIfR0NotEquals(OsConstants.IPPROTO_ICMPV6, this.mCountAndPassLabel);
        }
        apfGenerator.addLoad8(ApfGenerator.Register.R0, 54);
        maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV6_ROUTER_SOLICITATION);
        apfGenerator.addJumpIfR0Equals(133, this.mCountAndDropLabel);
        apfGenerator.addJumpIfR0NotEquals(136, "skipUnsolicitedMulticastNA");
        apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 38);
        apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, IPV6_ALL_NODES_ADDRESS, "skipUnsolicitedMulticastNA");
        maybeSetupCounter(apfGenerator, Counter.DROPPED_IPV6_MULTICAST_NA);
        apfGenerator.addJump(this.mCountAndDropLabel);
        apfGenerator.defineLabel("skipUnsolicitedMulticastNA");
    }

    private void generateV6KeepaliveFilters(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        generateKeepaliveFilters(apfGenerator, TcpKeepaliveAckV6.class, OsConstants.IPPROTO_TCP, 20, "skip_v6_keepalive_filter");
    }

    private ApfGenerator emitPrologueLocked() throws ApfGenerator.IllegalInstructionException {
        ApfGenerator apfGenerator = new ApfGenerator(this.mApfCapabilities.apfVersionSupported);
        if (this.mApfCapabilities.hasDataAccess()) {
            maybeSetupCounter(apfGenerator, Counter.TOTAL_PACKETS);
            apfGenerator.addLoadData(ApfGenerator.Register.R0, 0);
            apfGenerator.addAdd(1);
            apfGenerator.addStoreData(ApfGenerator.Register.R0, 0);
        }
        apfGenerator.addLoad16(ApfGenerator.Register.R0, 12);
        if (this.mDrop802_3Frames) {
            maybeSetupCounter(apfGenerator, Counter.DROPPED_802_3_FRAME);
            apfGenerator.addJumpIfR0LessThan(1536, this.mCountAndDropLabel);
        }
        maybeSetupCounter(apfGenerator, Counter.DROPPED_ETHERTYPE_BLACKLISTED);
        for (int i : this.mEthTypeBlackList) {
            apfGenerator.addJumpIfR0Equals(i, this.mCountAndDropLabel);
        }
        apfGenerator.addJumpIfR0NotEquals(OsConstants.ETH_P_ARP, "skipArpFilters");
        generateArpFilterLocked(apfGenerator);
        apfGenerator.defineLabel("skipArpFilters");
        apfGenerator.addJumpIfR0NotEquals(OsConstants.ETH_P_IP, "skipIPv4Filters");
        generateIPv4FilterLocked(apfGenerator);
        apfGenerator.defineLabel("skipIPv4Filters");
        apfGenerator.addJumpIfR0Equals(OsConstants.ETH_P_IPV6, "IPv6Filters");
        apfGenerator.addLoadImmediate(ApfGenerator.Register.R0, 0);
        maybeSetupCounter(apfGenerator, Counter.PASSED_NON_IP_UNICAST);
        apfGenerator.addJumpIfBytesNotEqual(ApfGenerator.Register.R0, ETH_BROADCAST_MAC_ADDRESS, this.mCountAndPassLabel);
        maybeSetupCounter(apfGenerator, Counter.DROPPED_ETH_BROADCAST);
        apfGenerator.addJump(this.mCountAndDropLabel);
        apfGenerator.defineLabel("IPv6Filters");
        generateIPv6FilterLocked(apfGenerator);
        return apfGenerator;
    }

    private void emitEpilogue(ApfGenerator apfGenerator) throws ApfGenerator.IllegalInstructionException {
        if (this.mApfCapabilities.hasDataAccess()) {
            maybeSetupCounter(apfGenerator, Counter.PASSED_IPV6_ICMP);
            apfGenerator.defineLabel(this.mCountAndPassLabel);
            apfGenerator.addLoadData(ApfGenerator.Register.R0, 0);
            apfGenerator.addAdd(1);
            apfGenerator.addStoreData(ApfGenerator.Register.R0, 0);
            apfGenerator.addJump("__PASS__");
            apfGenerator.defineLabel(this.mCountAndDropLabel);
            apfGenerator.addLoadData(ApfGenerator.Register.R0, 0);
            apfGenerator.addAdd(1);
            apfGenerator.addStoreData(ApfGenerator.Register.R0, 0);
            apfGenerator.addJump("__DROP__");
        }
    }

    void installNewProgramLocked() {
        purgeExpiredRasLocked();
        ArrayList arrayList = new ArrayList();
        ApfCapabilities apfCapabilities = this.mApfCapabilities;
        long j = apfCapabilities.maximumApfProgramSize;
        if (apfCapabilities.hasDataAccess()) {
            j -= (long) Counter.totalSize();
        }
        try {
            ApfGenerator apfGeneratorEmitPrologueLocked = emitPrologueLocked();
            emitEpilogue(apfGeneratorEmitPrologueLocked);
            if (apfGeneratorEmitPrologueLocked.programLengthOverEstimate() > j) {
                Log.e("ApfFilter", "Program exceeds maximum size " + j);
                return;
            }
            for (Ra ra : this.mRas) {
                ra.generateFilterLocked(apfGeneratorEmitPrologueLocked);
                if (apfGeneratorEmitPrologueLocked.programLengthOverEstimate() > j) {
                    break;
                } else {
                    arrayList.add(ra);
                }
            }
            ApfGenerator apfGeneratorEmitPrologueLocked2 = emitPrologueLocked();
            Iterator it = arrayList.iterator();
            long jMin = Long.MAX_VALUE;
            while (it.hasNext()) {
                jMin = Math.min(jMin, ((Ra) it.next()).generateFilterLocked(apfGeneratorEmitPrologueLocked2));
            }
            emitEpilogue(apfGeneratorEmitPrologueLocked2);
            byte[] bArrGenerate = apfGeneratorEmitPrologueLocked2.generate();
            long jCurrentTimeSeconds = currentTimeSeconds();
            this.mLastTimeInstalledProgram = jCurrentTimeSeconds;
            this.mLastInstalledProgramMinLifetime = jMin;
            this.mLastInstalledProgram = bArrGenerate;
            this.mNumProgramUpdates++;
            this.mIpClientCallback.installPacketFilter(bArrGenerate);
            logApfProgramEventLocked(jCurrentTimeSeconds);
            this.mLastInstallEvent = new ApfProgramEvent.Builder().setLifetime(jMin).setFilteredRas(arrayList.size()).setCurrentRas(this.mRas.size()).setProgramLength(bArrGenerate.length).setFlags(this.mIPv4Address != null, this.mMulticastFilter);
        } catch (ApfGenerator.IllegalInstructionException | IllegalStateException e) {
            Log.e("ApfFilter", "Failed to generate APF program.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logApfProgramEventLocked(long j) {
        ApfProgramEvent.Builder builder = this.mLastInstallEvent;
        if (builder == null) {
            return;
        }
        this.mLastInstallEvent = null;
        long j2 = j - this.mLastTimeInstalledProgram;
        builder.setActualLifetime(j2);
        if (j2 < 2) {
            return;
        }
        this.mMetricsLog.log(builder.build());
    }

    private boolean shouldInstallnewProgram() {
        return this.mLastTimeInstalledProgram + this.mLastInstalledProgramMinLifetime < currentTimeSeconds() + 30;
    }

    private void purgeExpiredRasLocked() {
        int i = 0;
        while (i < this.mRas.size()) {
            if (this.mRas.get(i).isExpired()) {
                log("Expiring " + this.mRas.get(i));
                this.mRas.remove(i);
            } else {
                i++;
            }
        }
    }

    synchronized ProcessRaResult processRa(byte[] bArr, int i) {
        for (int i2 = 0; i2 < this.mRas.size(); i2++) {
            Ra ra = this.mRas.get(i2);
            if (ra.matches(bArr, i)) {
                ra.mLastSeen = currentTimeSeconds();
                ra.mMinLifetime = ra.minLifetime();
                ra.seenCount++;
                this.mRas.add(0, this.mRas.remove(i2));
                if (shouldInstallnewProgram()) {
                    installNewProgramLocked();
                    return ProcessRaResult.UPDATE_EXPIRY;
                }
                return ProcessRaResult.MATCH;
            }
        }
        purgeExpiredRasLocked();
        if (this.mRas.size() >= 10) {
            return ProcessRaResult.DROPPED;
        }
        try {
            Ra ra2 = new Ra(bArr, i);
            if (ra2.isExpired()) {
                return ProcessRaResult.ZERO_LIFETIME;
            }
            log("Adding " + ra2);
            this.mRas.add(ra2);
            installNewProgramLocked();
            return ProcessRaResult.UPDATE_NEW_RA;
        } catch (Exception e) {
            Log.e("ApfFilter", "Error parsing RA", e);
            return ProcessRaResult.PARSE_ERROR;
        }
    }

    public static ApfFilter maybeCreate(Context context, ApfConfiguration apfConfiguration, InterfaceParams interfaceParams, IpClient.IpClientCallbacksWrapper ipClientCallbacksWrapper) {
        ApfCapabilities apfCapabilities;
        int i;
        if (context == null || apfConfiguration == null || interfaceParams == null || (apfCapabilities = apfConfiguration.apfCapabilities) == null || (i = apfCapabilities.apfVersionSupported) == 0) {
            return null;
        }
        if (apfCapabilities.maximumApfProgramSize < 512) {
            Log.e("ApfFilter", "Unacceptably small APF limit: " + apfCapabilities.maximumApfProgramSize);
            return null;
        }
        if (apfCapabilities.apfPacketFormat != OsConstants.ARPHRD_ETHER) {
            return null;
        }
        if (!ApfGenerator.supportsVersion(i)) {
            Log.e("ApfFilter", "Unsupported APF version: " + apfCapabilities.apfVersionSupported);
            return null;
        }
        return new ApfFilter(context, apfConfiguration, interfaceParams, ipClientCallbacksWrapper, new IpConnectivityLog());
    }

    public synchronized void shutdown() {
        if (this.mReceiveThread != null) {
            log("shutting down");
            this.mReceiveThread.halt();
            this.mReceiveThread = null;
        }
        this.mRas.clear();
        this.mContext.unregisterReceiver(this.mDeviceIdleReceiver);
    }

    public synchronized void setMulticastFilter(boolean z) {
        if (this.mMulticastFilter == z) {
            return;
        }
        this.mMulticastFilter = z;
        if (!z) {
            this.mNumProgramUpdatesAllowingMulticast++;
        }
        installNewProgramLocked();
    }

    public synchronized void setDozeMode(boolean z) {
        if (this.mInDozeMode == z) {
            return;
        }
        this.mInDozeMode = z;
        installNewProgramLocked();
    }

    private static LinkAddress findIPv4LinkAddress(LinkProperties linkProperties) {
        LinkAddress linkAddress = null;
        for (LinkAddress linkAddress2 : linkProperties.getLinkAddresses()) {
            if (linkAddress2.getAddress() instanceof Inet4Address) {
                if (linkAddress != null && !linkAddress.isSameAddressAs(linkAddress2)) {
                    return null;
                }
                linkAddress = linkAddress2;
            }
        }
        return linkAddress;
    }

    public synchronized void setLinkProperties(LinkProperties linkProperties) {
        LinkAddress linkAddressFindIPv4LinkAddress = findIPv4LinkAddress(linkProperties);
        byte[] address = linkAddressFindIPv4LinkAddress != null ? linkAddressFindIPv4LinkAddress.getAddress().getAddress() : null;
        int prefixLength = linkAddressFindIPv4LinkAddress != null ? linkAddressFindIPv4LinkAddress.getPrefixLength() : 0;
        if (prefixLength == this.mIPv4PrefixLength && Arrays.equals(address, this.mIPv4Address)) {
            return;
        }
        this.mIPv4Address = address;
        this.mIPv4PrefixLength = prefixLength;
        installNewProgramLocked();
    }

    public synchronized void addTcpKeepalivePacketFilter(int i, TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) {
        KeepalivePacket tcpKeepaliveAckV6;
        log("Adding keepalive ack(" + i + ")");
        if (this.mKeepalivePackets.get(i) != null) {
            throw new IllegalArgumentException("Keepalive slot " + i + " is occupied");
        }
        char c = tcpKeepalivePacketDataParcelable.srcAddress.length == 4 ? (char) 4 : (char) 6;
        SparseArray<KeepalivePacket> sparseArray = this.mKeepalivePackets;
        if (c == 4) {
            tcpKeepaliveAckV6 = new TcpKeepaliveAckV4(this, tcpKeepalivePacketDataParcelable);
        } else {
            tcpKeepaliveAckV6 = new TcpKeepaliveAckV6(this, tcpKeepalivePacketDataParcelable);
        }
        sparseArray.put(i, tcpKeepaliveAckV6);
        installNewProgramLocked();
    }

    public synchronized void addNattKeepalivePacketFilter(int i, NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable) {
        log("Adding NAT-T keepalive packet(" + i + ")");
        if (this.mKeepalivePackets.get(i) != null) {
            throw new IllegalArgumentException("NAT-T Keepalive slot " + i + " is occupied");
        }
        if (nattKeepalivePacketDataParcelable.srcAddress.length != 4) {
            throw new IllegalArgumentException("NAT-T keepalive is only supported on IPv4");
        }
        this.mKeepalivePackets.put(i, new NattKeepaliveResponse(nattKeepalivePacketDataParcelable));
        installNewProgramLocked();
    }

    public synchronized void removeKeepalivePacketFilter(int i) {
        log("Removing keepalive packet(" + i + ")");
        this.mKeepalivePackets.remove(i);
        installNewProgramLocked();
    }

    public static long counterValue(byte[] bArr, Counter counter) throws ArrayIndexOutOfBoundsException {
        int iOffset = counter.offset();
        if (iOffset < 0) {
            iOffset += bArr.length;
        }
        long j = 0;
        for (int i = 0; i < 4; i++) {
            j = (j << 8) | ((long) (bArr[iOffset] & 255));
            iOffset++;
        }
        return j;
    }

    public synchronized void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("Capabilities: " + this.mApfCapabilities);
        StringBuilder sb = new StringBuilder();
        sb.append("Receive thread: ");
        sb.append(this.mReceiveThread != null ? "RUNNING" : "STOPPED");
        indentingPrintWriter.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Multicast: ");
        sb2.append(this.mMulticastFilter ? "DROP" : "ALLOW");
        indentingPrintWriter.println(sb2.toString());
        indentingPrintWriter.println("Minimum RDNSS lifetime: " + this.mMinRdnssLifetimeSec);
        try {
            indentingPrintWriter.println("IPv4 address: " + InetAddress.getByAddress(this.mIPv4Address).getHostAddress());
        } catch (NullPointerException | UnknownHostException unused) {
        }
        if (this.mLastTimeInstalledProgram == 0) {
            indentingPrintWriter.println("No program installed.");
            return;
        }
        indentingPrintWriter.println("Program updates: " + this.mNumProgramUpdates);
        indentingPrintWriter.println(String.format("Last program length %d, installed %ds ago, lifetime %ds", Integer.valueOf(this.mLastInstalledProgram.length), Long.valueOf(currentTimeSeconds() - this.mLastTimeInstalledProgram), Long.valueOf(this.mLastInstalledProgramMinLifetime)));
        indentingPrintWriter.println("RA filters:");
        indentingPrintWriter.increaseIndent();
        for (Ra ra : this.mRas) {
            indentingPrintWriter.println(ra);
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.println(String.format("Seen: %d, last %ds ago", Integer.valueOf(ra.seenCount), Long.valueOf(currentTimeSeconds() - ra.mLastSeen)));
            indentingPrintWriter.println("Last match:");
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.println(ra.getLastMatchingPacket());
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.decreaseIndent();
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("TCP Keepalive filters:");
        indentingPrintWriter.increaseIndent();
        for (int i = 0; i < this.mKeepalivePackets.size(); i++) {
            Object obj = (KeepalivePacket) this.mKeepalivePackets.valueAt(i);
            if (obj instanceof TcpKeepaliveAck) {
                indentingPrintWriter.print("Slot ");
                indentingPrintWriter.print(this.mKeepalivePackets.keyAt(i));
                indentingPrintWriter.print(": ");
                indentingPrintWriter.println(obj);
            }
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("NAT-T Keepalive filters:");
        indentingPrintWriter.increaseIndent();
        for (int i2 = 0; i2 < this.mKeepalivePackets.size(); i2++) {
            Object obj2 = (KeepalivePacket) this.mKeepalivePackets.valueAt(i2);
            if (obj2 instanceof NattKeepaliveResponse) {
                indentingPrintWriter.print("Slot ");
                indentingPrintWriter.print(this.mKeepalivePackets.keyAt(i2));
                indentingPrintWriter.print(": ");
                indentingPrintWriter.println(obj2);
            }
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Last program:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println(HexDump.toHexString(this.mLastInstalledProgram, false));
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("APF packet counters: ");
        indentingPrintWriter.increaseIndent();
        if (!this.mApfCapabilities.hasDataAccess()) {
            indentingPrintWriter.println("APF counters not supported");
        } else if (this.mDataSnapshot == null) {
            indentingPrintWriter.println("No last snapshot.");
        } else {
            try {
                Counter[] counterArr = (Counter[]) Counter.class.getEnumConstants();
                for (Counter counter : Arrays.asList(counterArr).subList(1, counterArr.length)) {
                    long jCounterValue = counterValue(this.mDataSnapshot, counter);
                    if (jCounterValue != 0) {
                        indentingPrintWriter.println(counter.toString() + ": " + jCounterValue);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                indentingPrintWriter.println("Uh-oh: " + e);
            }
        }
        indentingPrintWriter.decreaseIndent();
    }

    public static int ipv4BroadcastAddress(byte[] bArr, int i) {
        return bytesToBEInt(bArr) | ((int) (Integer.toUnsignedLong(-1) >>> i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getUint16(ByteBuffer byteBuffer, int i) {
        return byteBuffer.getShort(i) & 65535;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getUint32(ByteBuffer byteBuffer, int i) {
        return Integer.toUnsignedLong(byteBuffer.getInt(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getUint8(ByteBuffer byteBuffer, int i) {
        return uint8(byteBuffer.get(i));
    }

    private static int bytesToBEInt(byte[] bArr) {
        return (uint8(bArr[0]) << 24) + (uint8(bArr[1]) << 16) + (uint8(bArr[2]) << 8) + uint8(bArr[3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] concatArrays(byte[]... bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        int length2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        return bArr3;
    }
}
