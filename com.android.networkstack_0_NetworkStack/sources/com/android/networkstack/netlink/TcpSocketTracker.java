package com.android.networkstack.netlink;

import android.content.Context;
import android.net.INetd;
import android.net.MarkMaskParcel;
import android.net.Network;
import android.net.netlink.InetDiagMessage;
import android.net.netlink.NetlinkConstants;
import android.net.netlink.NetlinkSocket;
import android.net.netlink.StructNlMsgHdr;
import android.net.util.NetworkStackUtils;
import android.net.util.SocketUtils;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.DeviceConfig;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructTimeval;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import com.android.networkstack.apishim.NetworkShimImpl;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.apishim.common.UnsupportedApiLevelException;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class TcpSocketTracker {
    private static final int[] ADDRESS_FAMILIES = {OsConstants.AF_INET6, OsConstants.AF_INET};
    private final Dependencies mDependencies;
    private int mLatestPacketFailPercentage;
    private int mLatestReceivedCount;
    private final INetd mNetd;
    private final Network mNetwork;
    private final int mNetworkMark;
    private final int mNetworkMask;
    private int mSentSinceLastRecv;
    private final LongSparseArray<SocketInfo> mSocketInfos = new LongSparseArray<>();
    private final SparseArray<byte[]> mSockDiagMsg = new SparseArray<>();
    private int mMinPacketsThreshold = 10;
    private int mTcpPacketsFailRateThreshold = 80;
    protected final DeviceConfig.OnPropertiesChangedListener mConfigListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.networkstack.netlink.TcpSocketTracker.1
        public void onPropertiesChanged(DeviceConfig.Properties properties) {
            TcpSocketTracker tcpSocketTracker = TcpSocketTracker.this;
            tcpSocketTracker.mMinPacketsThreshold = tcpSocketTracker.mDependencies.getDeviceConfigPropertyInt("connectivity", "tcp_min_packets_threshold", 10);
            TcpSocketTracker tcpSocketTracker2 = TcpSocketTracker.this;
            tcpSocketTracker2.mTcpPacketsFailRateThreshold = tcpSocketTracker2.mDependencies.getDeviceConfigPropertyInt("connectivity", "tcp_packets_fail_percentage", 80);
        }
    };

    private static boolean isValidInetDiagMsgSize(int i) {
        return i >= 88;
    }

    private void log(String str) {
    }

    public TcpSocketTracker(Dependencies dependencies, Network network) {
        this.mDependencies = dependencies;
        this.mNetwork = network;
        this.mNetd = dependencies.getNetd();
        MarkMaskParcel networkMarkMask = getNetworkMarkMask();
        this.mNetworkMark = networkMarkMask != null ? networkMarkMask.mark : -1;
        this.mNetworkMask = networkMarkMask != null ? networkMarkMask.mask : 0;
        if (this.mDependencies.isTcpInfoParsingSupported()) {
            for (int i : ADDRESS_FAMILIES) {
                this.mSockDiagMsg.put(i, InetDiagMessage.InetDiagReqV2(OsConstants.IPPROTO_TCP, null, null, i, (short) 769, 0, 2, 14));
            }
            this.mDependencies.addDeviceConfigChangedListener(this.mConfigListener);
        }
    }

    private MarkMaskParcel getNetworkMarkMask() {
        try {
            return this.mNetd.getFwmarkForNetwork(NetworkShimImpl.newInstance(this.mNetwork).getNetId());
        } catch (RemoteException e) {
            Log.e("TcpSocketTracker", "Error getting fwmark for network, ", e);
            return null;
        } catch (UnsupportedApiLevelException unused) {
            this.log("Get netId is not available in this API level.");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.networkstack.netlink.TcpSocketTracker$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileDescriptor] */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public boolean pollSocketsInfo() {
        long jElapsedRealtime;
        FileDescriptor fileDescriptorConnectToKernel;
        ByteBuffer byteBuffer;
        int i;
        int[] iArr;
        int i2;
        if (!this.mDependencies.isTcpInfoParsingSupported()) {
            return false;
        }
        FileDescriptor fileDescriptor = 0;
        fileDescriptor = 0;
        try {
            try {
                jElapsedRealtime = SystemClock.elapsedRealtime();
                fileDescriptorConnectToKernel = this.mDependencies.connectToKernel();
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    TcpStat tcpStat = new TcpStat();
                    int[] iArr2 = ADDRESS_FAMILIES;
                    int length = iArr2.length;
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = iArr2[i3];
                        this.mDependencies.sendPollingRequest(fileDescriptorConnectToKernel, this.mSockDiagMsg.get(i4));
                        ByteBuffer byteBufferRecvMessage = this.mDependencies.recvMessage(fileDescriptorConnectToKernel);
                        while (true) {
                            try {
                                if (!enoughBytesRemainForValidNlMsg(byteBufferRecvMessage)) {
                                    break;
                                }
                                StructNlMsgHdr structNlMsgHdr = StructNlMsgHdr.parse(byteBufferRecvMessage);
                                if (structNlMsgHdr == null) {
                                    Log.e("TcpSocketTracker", "Badly formatted data.");
                                    break;
                                }
                                int i5 = structNlMsgHdr.nlmsg_len;
                                log("pollSocketsInfo: nlmsghdr=" + structNlMsgHdr + ", limit=" + byteBufferRecvMessage.limit());
                                if (structNlMsgHdr.nlmsg_type == 3) {
                                    break;
                                }
                                if (structNlMsgHdr.nlmsg_type != 20) {
                                    Log.e("TcpSocketTracker", "Expect to get family " + i4 + " SOCK_DIAG_BY_FAMILY message but get " + ((int) structNlMsgHdr.nlmsg_type));
                                    break;
                                }
                                if (isValidInetDiagMsgSize(i5)) {
                                    byteBufferRecvMessage.position(byteBufferRecvMessage.position() + 44);
                                    long j = byteBufferRecvMessage.getLong();
                                    byteBufferRecvMessage.position(((byteBufferRecvMessage.position() + 72) - 44) - 8);
                                    byteBuffer = byteBufferRecvMessage;
                                    i = i3;
                                    iArr = iArr2;
                                    i2 = i4;
                                    try {
                                        SocketInfo sockInfo = parseSockInfo(byteBufferRecvMessage, i4, i5, jElapsedRealtime);
                                        tcpStat.accumulate(calculateLatestPacketsStat(sockInfo, this.mSocketInfos.get(j)));
                                        this.mSocketInfos.put(j, sockInfo);
                                    } catch (IllegalArgumentException | BufferUnderflowException e) {
                                        e = e;
                                        Log.wtf("TcpSocketTracker", "Unexpected socket info parsing, family " + i2 + " buffer:" + byteBuffer + " " + Base64.getEncoder().encodeToString(byteBuffer.array()), e);
                                        i3 = i + 1;
                                        iArr2 = iArr;
                                    }
                                } else {
                                    byteBuffer = byteBufferRecvMessage;
                                    i = i3;
                                    iArr = iArr2;
                                    i2 = i4;
                                }
                                byteBufferRecvMessage = byteBuffer;
                                i4 = i2;
                                i3 = i;
                                iArr2 = iArr;
                            } catch (IllegalArgumentException | BufferUnderflowException e2) {
                                e = e2;
                                byteBuffer = byteBufferRecvMessage;
                                i = i3;
                                iArr = iArr2;
                                i2 = i4;
                            }
                        }
                        i = i3;
                        iArr = iArr2;
                        i3 = i + 1;
                        iArr2 = iArr;
                    }
                    this.mSentSinceLastRecv = tcpStat.receivedCount == 0 ? this.mSentSinceLastRecv + tcpStat.sentCount : 0;
                    this.mLatestReceivedCount = tcpStat.receivedCount;
                    this.mLatestPacketFailPercentage = tcpStat.sentCount != 0 ? ((tcpStat.retransmitCount + tcpStat.lostCount) * 100) / tcpStat.sentCount : 0;
                    cleanupSocketInfo(jElapsedRealtime);
                    NetworkStackUtils.closeSocketQuietly(fileDescriptorConnectToKernel);
                    return true;
                } catch (ErrnoException | InterruptedIOException | SocketException e3) {
                    e = e3;
                    fileDescriptor = fileDescriptorConnectToKernel;
                    Log.e("TcpSocketTracker", "Fail to get TCP info via netlink.", e);
                    NetworkStackUtils.closeSocketQuietly(fileDescriptor);
                    return false;
                }
            } catch (Throwable th2) {
                th = th2;
                fileDescriptor = fileDescriptorConnectToKernel;
                NetworkStackUtils.closeSocketQuietly(fileDescriptor);
                throw th;
            }
        } catch (ErrnoException | InterruptedIOException | SocketException e4) {
            e = e4;
        }
    }

    private void cleanupSocketInfo(long j) {
        int size = this.mSocketInfos.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            long jKeyAt = this.mSocketInfos.keyAt(i);
            if (this.mSocketInfos.get(jKeyAt).updateTime < j) {
                arrayList.add(Long.valueOf(jKeyAt));
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.mSocketInfos.remove(((Long) it.next()).longValue());
        }
    }

    SocketInfo parseSockInfo(ByteBuffer byteBuffer, int i, int i2, long j) {
        int iPosition = (byteBuffer.position() + i2) - 88;
        TcpInfo tcpInfo = null;
        int i3 = 0;
        while (byteBuffer.position() < iPosition) {
            RoutingAttribute routingAttribute = new RoutingAttribute(this, byteBuffer.getShort(), byteBuffer.getShort());
            short dataLength = routingAttribute.getDataLength();
            short s = routingAttribute.rtaType;
            if (s == 2) {
                tcpInfo = TcpInfo.parse(byteBuffer, dataLength);
            } else if (s == 15) {
                i3 = byteBuffer.getInt();
            } else {
                skipRemainingAttributesBytesAligned(byteBuffer, dataLength);
            }
        }
        SocketInfo socketInfo = new SocketInfo(this, tcpInfo, i, i3, j);
        log("parseSockInfo, " + socketInfo);
        return socketInfo;
    }

    public boolean isDataStallSuspected() {
        return this.mDependencies.isTcpInfoParsingSupported() && getLatestPacketFailPercentage() >= getTcpPacketsFailRateThreshold();
    }

    private TcpStat calculateLatestPacketsStat(SocketInfo socketInfo, SocketInfo socketInfo2) {
        TcpInfo tcpInfo;
        TcpStat tcpStat = new TcpStat();
        if ((socketInfo.fwmark & this.mNetworkMask) != this.mNetworkMark) {
            return null;
        }
        TcpInfo tcpInfo2 = socketInfo.tcpInfo;
        if (tcpInfo2 == null) {
            log("Current tcpInfo is null.");
            return null;
        }
        int i = tcpInfo2.mSegsOut;
        tcpStat.sentCount = i;
        int i2 = tcpInfo2.mSegsIn;
        tcpStat.receivedCount = i2;
        int i3 = tcpInfo2.mLost;
        tcpStat.lostCount = i3;
        int i4 = tcpInfo2.mRetransmits;
        tcpStat.retransmitCount = i4;
        if (socketInfo2 != null && (tcpInfo = socketInfo2.tcpInfo) != null) {
            tcpStat.sentCount = i - tcpInfo.mSegsOut;
            tcpStat.receivedCount = i2 - tcpInfo.mSegsIn;
            tcpStat.lostCount = i3 - tcpInfo.mLost;
            tcpStat.retransmitCount = i4 - tcpInfo.mRetransmits;
        }
        return tcpStat;
    }

    public int getLatestPacketFailPercentage() {
        if (this.mDependencies.isTcpInfoParsingSupported() && getSentSinceLastRecv() >= getMinPacketsThreshold()) {
            return this.mLatestPacketFailPercentage;
        }
        return -1;
    }

    public int getSentSinceLastRecv() {
        if (this.mDependencies.isTcpInfoParsingSupported()) {
            return this.mSentSinceLastRecv;
        }
        return -1;
    }

    public int getLatestReceivedCount() {
        if (this.mDependencies.isTcpInfoParsingSupported()) {
            return this.mLatestReceivedCount;
        }
        return -1;
    }

    static boolean enoughBytesRemainForValidNlMsg(ByteBuffer byteBuffer) {
        return byteBuffer.remaining() >= 16;
    }

    private int getMinPacketsThreshold() {
        return this.mMinPacketsThreshold;
    }

    private int getTcpPacketsFailRateThreshold() {
        return this.mTcpPacketsFailRateThreshold;
    }

    private void skipRemainingAttributesBytesAligned(ByteBuffer byteBuffer, short s) {
        byteBuffer.position(byteBuffer.position() + NetlinkConstants.alignedLengthOf(s));
    }

    class RoutingAttribute {
        public final short rtaLen;
        public final short rtaType;

        RoutingAttribute(TcpSocketTracker tcpSocketTracker, short s, short s2) {
            this.rtaLen = s;
            this.rtaType = s2;
        }

        public short getDataLength() {
            return (short) (this.rtaLen - 4);
        }
    }

    class SocketInfo {
        public final int fwmark;
        public final int ipFamily;
        public final TcpInfo tcpInfo;
        public final long updateTime;

        SocketInfo(TcpSocketTracker tcpSocketTracker, TcpInfo tcpInfo, int i, int i2, long j) {
            this.tcpInfo = tcpInfo;
            this.ipFamily = i;
            this.updateTime = j;
            this.fwmark = i2;
        }

        public String toString() {
            return "SocketInfo {Type:" + ipTypeToString(this.ipFamily) + ", " + this.tcpInfo + ", mark:" + this.fwmark + " updated at " + this.updateTime + "}";
        }

        private String ipTypeToString(int i) {
            return i == OsConstants.AF_INET ? "IP" : i == OsConstants.AF_INET6 ? "IPV6" : "UNKNOWN";
        }
    }

    private class TcpStat {
        public int lostCount;
        public int receivedCount;
        public int retransmitCount;
        public int sentCount;

        private TcpStat(TcpSocketTracker tcpSocketTracker) {
        }

        void accumulate(TcpStat tcpStat) {
            if (tcpStat == null) {
                return;
            }
            this.sentCount += tcpStat.sentCount;
            this.lostCount += tcpStat.lostCount;
            this.receivedCount += tcpStat.receivedCount;
            this.retransmitCount += tcpStat.retransmitCount;
        }
    }

    public static class Dependencies {
        private final Context mContext;

        public Dependencies(Context context) {
            this.mContext = context;
        }

        public FileDescriptor connectToKernel() throws SocketException, ErrnoException {
            FileDescriptor fileDescriptorSocket = Os.socket(OsConstants.AF_NETLINK, OsConstants.SOCK_DGRAM | OsConstants.SOCK_CLOEXEC, OsConstants.NETLINK_INET_DIAG);
            Os.connect(fileDescriptorSocket, SocketUtils.makeNetlinkSocketAddress(0, 0));
            return fileDescriptorSocket;
        }

        public void sendPollingRequest(FileDescriptor fileDescriptor, byte[] bArr) throws ErrnoException, InterruptedIOException {
            Os.setsockoptTimeval(fileDescriptor, OsConstants.SOL_SOCKET, OsConstants.SO_SNDTIMEO, StructTimeval.fromMillis(3000L));
            Os.write(fileDescriptor, bArr, 0, bArr.length);
        }

        public int getDeviceConfigPropertyInt(String str, String str2, int i) {
            return NetworkStackUtils.getDeviceConfigPropertyInt(str, str2, i);
        }

        public boolean isTcpInfoParsingSupported() {
            return ShimUtils.isReleaseOrDevelopmentApiAbove(29);
        }

        public ByteBuffer recvMessage(FileDescriptor fileDescriptor) throws ErrnoException, InterruptedIOException {
            return NetlinkSocket.recvMessage(fileDescriptor, 60000, 3000L);
        }

        public INetd getNetd() {
            return INetd.Stub.asInterface((IBinder) this.mContext.getSystemService("netd"));
        }

        public void addDeviceConfigChangedListener(DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener) {
            DeviceConfig.addOnPropertiesChangedListener("connectivity", AsyncTask.THREAD_POOL_EXECUTOR, onPropertiesChangedListener);
        }
    }
}
