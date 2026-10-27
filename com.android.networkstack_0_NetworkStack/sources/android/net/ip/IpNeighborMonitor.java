package android.net.ip;

import android.net.MacAddress;
import android.net.ip.IpNeighborMonitor;
import android.net.netlink.NetlinkConstants;
import android.net.netlink.NetlinkMessage;
import android.net.netlink.NetlinkSocket;
import android.net.netlink.RtNetlinkNeighborMessage;
import android.net.netlink.StructNdMsg;
import android.net.util.IpMask;
import android.net.util.SharedLog;
import android.os.Handler;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import java.net.InetAddress;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes.dex */
public class IpNeighborMonitor extends NetlinkMonitor {
    private static final String TAG = "IpNeighborMonitor";
    private final NeighborEventConsumer mConsumer;

    public interface NeighborEventConsumer {
        void accept(NeighborEvent neighborEvent);
    }

    static /* synthetic */ void lambda$new$0(NeighborEvent neighborEvent) {
    }

    public static int startKernelNeighborProbe(int i, InetAddress inetAddress) {
        String str = "probing ip=" + inetAddress.getHostAddress() + "%" + i;
        try {
            NetlinkSocket.sendOneShotKernelMessage(OsConstants.NETLINK_ROUTE, RtNetlinkNeighborMessage.newNewNeighborMessage(1, inetAddress, (short) 16, i, null));
            return 0;
        } catch (ErrnoException e) {
            Log.e(TAG, "Error " + str + ": " + e);
            return -e.errno;
        }
    }

    public static class NeighborEvent {
        final long elapsedMs;
        final int ifindex;
        final InetAddress ip;
        final MacAddress macAddr;
        final short msgType;
        final short nudState;

        public NeighborEvent(long j, short s, int i, InetAddress inetAddress, short s2, MacAddress macAddress) {
            this.elapsedMs = j;
            this.msgType = s;
            this.ifindex = i;
            this.ip = inetAddress;
            this.nudState = s2;
            this.macAddr = macAddress;
        }

        public String toString() {
            return new StringJoiner(",", "NeighborEvent{", "}").add("@" + this.elapsedMs).add(NetlinkConstants.stringForNlMsgType(this.msgType)).add("if=" + this.ifindex).add(IpMask.maskIpMiddleString(this.ip.getHostAddress())).add(StructNdMsg.stringForNudState(this.nudState)).add("[" + IpMask.maskMac(this.macAddr) + "]").toString();
        }
    }

    public IpNeighborMonitor(Handler handler, SharedLog sharedLog, NeighborEventConsumer neighborEventConsumer) {
        super(handler, sharedLog, TAG, OsConstants.NETLINK_ROUTE, OsConstants.RTMGRP_NEIGH);
        this.mConsumer = neighborEventConsumer == null ? new NeighborEventConsumer() { // from class: android.net.ip.-$$Lambda$IpNeighborMonitor$4TdKAwtCtq9Ri1cSdW1mKm0JycM
            @Override // android.net.ip.IpNeighborMonitor.NeighborEventConsumer
            public final void accept(IpNeighborMonitor.NeighborEvent neighborEvent) {
                IpNeighborMonitor.lambda$new$0(neighborEvent);
            }
        } : neighborEventConsumer;
    }

    @Override // android.net.ip.NetlinkMonitor
    public void processNetlinkMessage(NetlinkMessage netlinkMessage, long j) {
        if (!(netlinkMessage instanceof RtNetlinkNeighborMessage)) {
            this.mLog.e("non-rtnetlink neighbor msg: " + netlinkMessage);
            return;
        }
        RtNetlinkNeighborMessage rtNetlinkNeighborMessage = (RtNetlinkNeighborMessage) netlinkMessage;
        short s = rtNetlinkNeighborMessage.getHeader().nlmsg_type;
        StructNdMsg ndHeader = rtNetlinkNeighborMessage.getNdHeader();
        if (ndHeader == null) {
            this.mLog.e("RtNetlinkNeighborMessage without ND message header!");
        } else {
            this.mConsumer.accept(new NeighborEvent(j, s, ndHeader.ndm_ifindex, rtNetlinkNeighborMessage.getDestination(), s == 29 ? (short) 0 : ndHeader.ndm_state, getMacAddress(rtNetlinkNeighborMessage.getLinkLayerAddress())));
        }
    }

    private static MacAddress getMacAddress(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return MacAddress.fromBytes(bArr);
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Failed to parse link-layer address: " + NetlinkConstants.hexify(bArr));
            return null;
        }
    }
}
