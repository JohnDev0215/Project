package android.net.dhcp;

import android.content.Context;
import android.net.INetworkStackStatusCallback;
import android.net.IpPrefix;
import android.net.MacAddress;
import android.net.TrafficStats;
import android.net.dhcp.DhcpLeaseRepository;
import android.net.dhcp.DhcpPacket;
import android.net.dhcp.DhcpServingParams;
import android.net.dhcp.IDhcpServer;
import android.net.networkstack.util.HexDump;
import android.net.networkstack.util.State;
import android.net.networkstack.util.StateMachine;
import android.net.util.NetworkStackUtils;
import android.net.util.SharedLog;
import android.net.util.SocketUtils;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.Pair;
import com.android.networkstack.util.Inet4AddressUtils;
import com.android.server.util.NetworkStackConstants;
import com.android.server.util.PermissionUtil;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DhcpServer extends StateMachine {
    protected static final int CMD_RECEIVE_PACKET = 4;
    private final Clock mClock;
    private final Dependencies mDeps;
    private final boolean mDhcpRapidCommitEnabled;
    private IDhcpEventCallbacks mEventCallbacks;
    private final String mIfName;
    private final DhcpLeaseRepository mLeaseRepo;
    private final SharedLog mLog;
    private DhcpPacketListener mPacketListener;
    private final RunningState mRunningState;
    private DhcpServingParams mServingParams;
    private FileDescriptor mSocket;
    private final StartedState mStartedState;
    private final StoppedState mStoppedState;
    private final WaitBeforeRetrievalState mWaitBeforeRetrievalState;

    public interface Dependencies {
        void addArpEntry(Inet4Address inet4Address, MacAddress macAddress, String str, FileDescriptor fileDescriptor) throws IOException;

        boolean isFeatureEnabled(Context context, String str);

        Clock makeClock();

        DhcpLeaseRepository makeLeaseRepository(DhcpServingParams dhcpServingParams, SharedLog sharedLog, Clock clock);

        DhcpPacketListener makePacketListener(Handler handler);

        void sendPacket(FileDescriptor fileDescriptor, ByteBuffer byteBuffer, InetAddress inetAddress) throws IOException, ErrnoException;
    }

    public static class Clock {
        public long elapsedRealtime() {
            return SystemClock.elapsedRealtime();
        }
    }

    private class DependenciesImpl implements Dependencies {
        private DependenciesImpl() {
        }

        @Override // android.net.dhcp.DhcpServer.Dependencies
        public void sendPacket(FileDescriptor fileDescriptor, ByteBuffer byteBuffer, InetAddress inetAddress) throws IOException, ErrnoException {
            Os.sendto(fileDescriptor, byteBuffer, 0, inetAddress, 68);
        }

        @Override // android.net.dhcp.DhcpServer.Dependencies
        public DhcpLeaseRepository makeLeaseRepository(DhcpServingParams dhcpServingParams, SharedLog sharedLog, Clock clock) {
            return new DhcpLeaseRepository(DhcpServingParams.makeIpPrefix(dhcpServingParams.serverAddr), dhcpServingParams.excludedAddrs, dhcpServingParams.dhcpLeaseTimeSecs * 1000, dhcpServingParams.singleClientAddr, sharedLog.forSubComponent("Repository"), clock);
        }

        @Override // android.net.dhcp.DhcpServer.Dependencies
        public DhcpPacketListener makePacketListener(Handler handler) {
            return DhcpServer.this.new PacketListener(handler);
        }

        @Override // android.net.dhcp.DhcpServer.Dependencies
        public Clock makeClock() {
            return new Clock();
        }

        @Override // android.net.dhcp.DhcpServer.Dependencies
        public void addArpEntry(Inet4Address inet4Address, MacAddress macAddress, String str, FileDescriptor fileDescriptor) throws IOException {
            NetworkStackUtils.addArpEntry(inet4Address, macAddress, str, fileDescriptor);
        }

        @Override // android.net.dhcp.DhcpServer.Dependencies
        public boolean isFeatureEnabled(Context context, String str) {
            return NetworkStackUtils.isFeatureEnabled(context, "connectivity", str);
        }
    }

    private static class MalformedPacketException extends Exception {
        MalformedPacketException(String str, Throwable th) {
            super(str, th);
        }
    }

    public DhcpServer(Context context, String str, DhcpServingParams dhcpServingParams, SharedLog sharedLog) {
        this(context, str, dhcpServingParams, sharedLog, null);
    }

    DhcpServer(Context context, String str, DhcpServingParams dhcpServingParams, SharedLog sharedLog, Dependencies dependencies) {
        super(DhcpServer.class.getSimpleName() + "." + str);
        this.mStoppedState = new StoppedState();
        this.mStartedState = new StartedState();
        this.mRunningState = new RunningState();
        this.mWaitBeforeRetrievalState = new WaitBeforeRetrievalState();
        dependencies = dependencies == null ? new DependenciesImpl() : dependencies;
        this.mIfName = str;
        this.mServingParams = dhcpServingParams;
        this.mLog = sharedLog;
        this.mDeps = dependencies;
        Clock clockMakeClock = dependencies.makeClock();
        this.mClock = clockMakeClock;
        this.mLeaseRepo = dependencies.makeLeaseRepository(this.mServingParams, this.mLog, clockMakeClock);
        this.mDhcpRapidCommitEnabled = dependencies.isFeatureEnabled(context, "dhcp_rapid_commit_version");
        addState(this.mStoppedState);
        addState(this.mStartedState);
        addState(this.mRunningState, this.mStartedState);
        addState(this.mWaitBeforeRetrievalState, this.mStartedState);
        setInitialState(this.mStoppedState);
        super.start();
    }

    public IDhcpServer makeConnector() {
        return new DhcpServerConnector();
    }

    private class DhcpServerConnector extends IDhcpServer.Stub {
        @Override // android.net.dhcp.IDhcpServer
        public String getInterfaceHash() {
            return "02cd6fd07d5c04eca0c35a350f7b0be576242883";
        }

        @Override // android.net.dhcp.IDhcpServer
        public int getInterfaceVersion() {
            return 7;
        }

        private DhcpServerConnector() {
        }

        @Override // android.net.dhcp.IDhcpServer
        public void start(INetworkStackStatusCallback iNetworkStackStatusCallback) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            DhcpServer.this.start(iNetworkStackStatusCallback);
        }

        @Override // android.net.dhcp.IDhcpServer
        public void startWithCallbacks(INetworkStackStatusCallback iNetworkStackStatusCallback, IDhcpEventCallbacks iDhcpEventCallbacks) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            DhcpServer.this.start(iNetworkStackStatusCallback, iDhcpEventCallbacks);
        }

        @Override // android.net.dhcp.IDhcpServer
        public void updateParams(DhcpServingParamsParcel dhcpServingParamsParcel, INetworkStackStatusCallback iNetworkStackStatusCallback) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            DhcpServer.this.updateParams(dhcpServingParamsParcel, iNetworkStackStatusCallback);
        }

        @Override // android.net.dhcp.IDhcpServer
        public void stop(INetworkStackStatusCallback iNetworkStackStatusCallback) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            DhcpServer.this.stop(iNetworkStackStatusCallback);
        }
    }

    void start(INetworkStackStatusCallback iNetworkStackStatusCallback) {
        start(iNetworkStackStatusCallback, null);
    }

    void start(INetworkStackStatusCallback iNetworkStackStatusCallback, IDhcpEventCallbacks iDhcpEventCallbacks) {
        sendMessage(1, new Pair(iNetworkStackStatusCallback, iDhcpEventCallbacks));
    }

    void updateParams(DhcpServingParamsParcel dhcpServingParamsParcel, INetworkStackStatusCallback iNetworkStackStatusCallback) {
        try {
            sendMessage(3, new Pair(DhcpServingParams.fromParcelableObject(dhcpServingParamsParcel), iNetworkStackStatusCallback));
        } catch (DhcpServingParams.InvalidParameterException e) {
            this.mLog.e("Invalid parameters sent to DhcpServer", e);
            maybeNotifyStatus(iNetworkStackStatusCallback, 2);
        }
    }

    void stop(INetworkStackStatusCallback iNetworkStackStatusCallback) {
        sendMessage(2, iNetworkStackStatusCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeNotifyStatus(INetworkStackStatusCallback iNetworkStackStatusCallback, int i) {
        if (iNetworkStackStatusCallback == null) {
            return;
        }
        try {
            iNetworkStackStatusCallback.onStatusAvailable(i);
        } catch (RemoteException e) {
            this.mLog.e("Could not send status back to caller", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdateServingParams(DhcpServingParams dhcpServingParams, INetworkStackStatusCallback iNetworkStackStatusCallback) {
        this.mServingParams = dhcpServingParams;
        this.mLeaseRepo.updateParams(DhcpServingParams.makeIpPrefix(dhcpServingParams.serverAddr), dhcpServingParams.excludedAddrs, dhcpServingParams.dhcpLeaseTimeSecs * 1000, dhcpServingParams.singleClientAddr);
        maybeNotifyStatus(iNetworkStackStatusCallback, 1);
    }

    class StoppedState extends State {
        private INetworkStackStatusCallback mOnStopCallback;

        StoppedState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            DhcpServer.this.maybeNotifyStatus(this.mOnStopCallback, 1);
            this.mOnStopCallback = null;
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (message.what != 1) {
                return false;
            }
            Pair pair = (Pair) message.obj;
            DhcpServer.this.mStartedState.mOnStartCallback = (INetworkStackStatusCallback) pair.first;
            DhcpServer.this.mEventCallbacks = (IDhcpEventCallbacks) pair.second;
            DhcpServer dhcpServer = DhcpServer.this;
            dhcpServer.transitionTo(dhcpServer.mRunningState);
            return true;
        }
    }

    class StartedState extends State {
        private INetworkStackStatusCallback mOnStartCallback;

        StartedState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            if (DhcpServer.this.mPacketListener != null) {
                DhcpServer.this.mLog.e("Starting DHCP server more than once is not supported.");
                DhcpServer.this.maybeNotifyStatus(this.mOnStartCallback, 3);
                this.mOnStartCallback = null;
                return;
            }
            DhcpServer dhcpServer = DhcpServer.this;
            dhcpServer.mPacketListener = dhcpServer.mDeps.makePacketListener(DhcpServer.this.getHandler());
            if (!DhcpServer.this.mPacketListener.start()) {
                DhcpServer.this.mLog.e("Fail to start DHCP Packet Listener, rollback to StoppedState");
                DhcpServer dhcpServer2 = DhcpServer.this;
                dhcpServer2.deferMessage(dhcpServer2.obtainMessage(2, (Object) null));
                DhcpServer.this.maybeNotifyStatus(this.mOnStartCallback, 3);
                this.mOnStartCallback = null;
                return;
            }
            if (DhcpServer.this.mEventCallbacks != null) {
                DhcpServer.this.mLeaseRepo.addLeaseCallbacks(DhcpServer.this.mEventCallbacks);
            }
            DhcpServer.this.maybeNotifyStatus(this.mOnStartCallback, 1);
            this.mOnStartCallback = null;
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                DhcpServer.this.mLog.e("ALERT: START received in StartedState. Please fix caller.");
                return true;
            }
            if (i != 2) {
                if (i != 3) {
                    return false;
                }
                Pair pair = (Pair) message.obj;
                DhcpServer.this.handleUpdateServingParams((DhcpServingParams) pair.first, (INetworkStackStatusCallback) pair.second);
                return true;
            }
            DhcpServer.this.mStoppedState.mOnStopCallback = (INetworkStackStatusCallback) message.obj;
            DhcpServer dhcpServer = DhcpServer.this;
            dhcpServer.transitionTo(dhcpServer.mStoppedState);
            return true;
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            DhcpServer.this.mPacketListener.stop();
            DhcpServer.this.mLog.logf("DHCP Packet Listener stopped", new Object[0]);
        }
    }

    class RunningState extends State {
        RunningState() {
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (message.what != 4) {
                return false;
            }
            processPacket((DhcpPacket) message.obj);
            return true;
        }

        private void processPacket(DhcpPacket dhcpPacket) {
            DhcpServer.this.mLog.log("Received packet of type " + dhcpPacket.getClass().getSimpleName());
            Inet4Address inet4Address = dhcpPacket.mServerIdentifier;
            if (inet4Address != null && !inet4Address.equals(DhcpServer.this.mServingParams.serverAddr.getAddress())) {
                DhcpServer.this.mLog.log("Packet ignored due to wrong server identifier: " + inet4Address);
                return;
            }
            try {
                if (dhcpPacket instanceof DhcpDiscoverPacket) {
                    processDiscover((DhcpDiscoverPacket) dhcpPacket);
                    return;
                }
                if (dhcpPacket instanceof DhcpRequestPacket) {
                    processRequest((DhcpRequestPacket) dhcpPacket);
                    return;
                }
                if (dhcpPacket instanceof DhcpReleasePacket) {
                    processRelease((DhcpReleasePacket) dhcpPacket);
                    return;
                }
                if (!(dhcpPacket instanceof DhcpDeclinePacket)) {
                    DhcpServer.this.mLog.e("Unknown packet type: " + dhcpPacket.getClass().getSimpleName());
                    return;
                }
                processDecline((DhcpDeclinePacket) dhcpPacket);
            } catch (MalformedPacketException e) {
                DhcpServer.this.mLog.e("Ignored malformed packet: " + e.getMessage());
            }
        }

        private void logIgnoredPacketInvalidSubnet(DhcpLeaseRepository.InvalidSubnetException invalidSubnetException) {
            DhcpServer.this.mLog.e("Ignored packet from invalid subnet: " + invalidSubnetException.getMessage());
        }

        private void processDiscover(DhcpDiscoverPacket dhcpDiscoverPacket) throws MalformedPacketException {
            MacAddress macAddr = DhcpServer.this.getMacAddr(dhcpDiscoverPacket);
            try {
                if (!DhcpServer.this.mDhcpRapidCommitEnabled || !dhcpDiscoverPacket.mRapidCommit) {
                    DhcpServer.this.transmitOffer(dhcpDiscoverPacket, DhcpServer.this.mLeaseRepo.getOffer(dhcpDiscoverPacket.getExplicitClientIdOrNull(), macAddr, dhcpDiscoverPacket.mRelayIp, dhcpDiscoverPacket.mRequestedIp, dhcpDiscoverPacket.mHostName), macAddr);
                } else {
                    DhcpServer.this.transmitAck(dhcpDiscoverPacket, DhcpServer.this.mLeaseRepo.getCommittedLease(dhcpDiscoverPacket.getExplicitClientIdOrNull(), macAddr, dhcpDiscoverPacket.mRelayIp, dhcpDiscoverPacket.mHostName), macAddr);
                }
            } catch (DhcpLeaseRepository.InvalidSubnetException e) {
                logIgnoredPacketInvalidSubnet(e);
            } catch (DhcpLeaseRepository.OutOfAddressesException unused) {
                DhcpServer.this.transmitNak(dhcpDiscoverPacket, "Out of addresses to offer");
            }
        }

        private void processRequest(DhcpRequestPacket dhcpRequestPacket) throws MalformedPacketException {
            boolean z = dhcpRequestPacket.mServerIdentifier != null;
            MacAddress macAddr = DhcpServer.this.getMacAddr(dhcpRequestPacket);
            try {
                DhcpServer.this.transmitAck(dhcpRequestPacket, DhcpServer.this.mLeaseRepo.requestLease(dhcpRequestPacket.getExplicitClientIdOrNull(), macAddr, dhcpRequestPacket.mClientIp, dhcpRequestPacket.mRelayIp, dhcpRequestPacket.mRequestedIp, z, dhcpRequestPacket.mHostName), macAddr);
            } catch (DhcpLeaseRepository.InvalidAddressException unused) {
                DhcpServer.this.transmitNak(dhcpRequestPacket, "Invalid requested address");
            } catch (DhcpLeaseRepository.InvalidSubnetException e) {
                logIgnoredPacketInvalidSubnet(e);
            }
        }

        private void processRelease(DhcpReleasePacket dhcpReleasePacket) throws MalformedPacketException {
            DhcpServer.this.mLeaseRepo.releaseLease(dhcpReleasePacket.getExplicitClientIdOrNull(), DhcpServer.this.getMacAddr(dhcpReleasePacket), dhcpReleasePacket.mClientIp);
        }

        private void processDecline(DhcpDeclinePacket dhcpDeclinePacket) throws MalformedPacketException {
            byte[] explicitClientIdOrNull = dhcpDeclinePacket.getExplicitClientIdOrNull();
            MacAddress macAddr = DhcpServer.this.getMacAddr(dhcpDeclinePacket);
            int size = DhcpServer.this.mLeaseRepo.getCommittedLeases().size();
            if (DhcpServer.this.mLeaseRepo.markAndReleaseDeclinedLease(explicitClientIdOrNull, macAddr, dhcpDeclinePacket.mRequestedIp) && DhcpServer.this.mServingParams.changePrefixOnDecline && size <= 1) {
                if (DhcpServer.this.mEventCallbacks == null) {
                    DhcpServer.this.mLog.e("changePrefixOnDecline enabled but caller didn't pass a validIDhcpEventCallbacks callback.");
                    return;
                }
                try {
                    DhcpServer.this.mEventCallbacks.onNewPrefixRequest(DhcpServingParams.makeIpPrefix(DhcpServer.this.mServingParams.serverAddr));
                    DhcpServer.this.transitionTo(DhcpServer.this.mWaitBeforeRetrievalState);
                } catch (RemoteException e) {
                    DhcpServer.this.mLog.e("could not request a new prefix to caller", e);
                }
            }
        }
    }

    class WaitBeforeRetrievalState extends State {
        WaitBeforeRetrievalState() {
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != 3) {
                if (i != 4) {
                    return false;
                }
                DhcpServer.this.deferMessage(message);
                return true;
            }
            Pair pair = (Pair) message.obj;
            IpPrefix ipPrefixMakeIpPrefix = DhcpServingParams.makeIpPrefix(DhcpServer.this.mServingParams.serverAddr);
            IpPrefix ipPrefixMakeIpPrefix2 = DhcpServingParams.makeIpPrefix(((DhcpServingParams) pair.first).serverAddr);
            DhcpServer.this.handleUpdateServingParams((DhcpServingParams) pair.first, (INetworkStackStatusCallback) pair.second);
            if (ipPrefixMakeIpPrefix != null && !ipPrefixMakeIpPrefix.equals(ipPrefixMakeIpPrefix2)) {
                DhcpServer dhcpServer = DhcpServer.this;
                dhcpServer.transitionTo(dhcpServer.mRunningState);
            }
            return true;
        }
    }

    private Inet4Address getAckOrOfferDst(DhcpPacket dhcpPacket, DhcpLease dhcpLease, boolean z) {
        if (!isEmpty(dhcpPacket.mRelayIp)) {
            return dhcpPacket.mRelayIp;
        }
        if (z) {
            return NetworkStackConstants.IPV4_ADDR_ALL;
        }
        if (!isEmpty(dhcpPacket.mClientIp)) {
            return dhcpPacket.mClientIp;
        }
        return dhcpLease.getNetAddr();
    }

    private static boolean getBroadcastFlag(DhcpPacket dhcpPacket, DhcpLease dhcpLease) {
        return isEmpty(dhcpPacket.mClientIp) && (dhcpPacket.mBroadcast || isEmpty(dhcpLease.getNetAddr()));
    }

    private static String getHostnameIfRequested(DhcpPacket dhcpPacket, DhcpLease dhcpLease) {
        if (!dhcpPacket.hasRequestedParam((byte) 12) || TextUtils.isEmpty(dhcpLease.getHostname())) {
            return null;
        }
        return dhcpLease.getHostname();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean transmitOffer(DhcpPacket dhcpPacket, DhcpLease dhcpLease, MacAddress macAddress) {
        boolean broadcastFlag = getBroadcastFlag(dhcpPacket, dhcpLease);
        int leaseTimeout = getLeaseTimeout(dhcpLease);
        Inet4Address prefixMaskAsInet4Address = Inet4AddressUtils.getPrefixMaskAsInet4Address(this.mServingParams.serverAddr.getPrefixLength());
        Inet4Address broadcastAddress = Inet4AddressUtils.getBroadcastAddress(this.mServingParams.getServerInet4Addr(), this.mServingParams.serverAddr.getPrefixLength());
        String hostnameIfRequested = getHostnameIfRequested(dhcpPacket, dhcpLease);
        int i = dhcpPacket.mTransId;
        Inet4Address serverInet4Addr = this.mServingParams.getServerInet4Addr();
        Inet4Address inet4Address = dhcpPacket.mRelayIp;
        Inet4Address netAddr = dhcpLease.getNetAddr();
        byte[] bArr = dhcpPacket.mClientMac;
        Integer numValueOf = Integer.valueOf(leaseTimeout);
        ArrayList arrayList = new ArrayList(this.mServingParams.defaultRouters);
        ArrayList arrayList2 = new ArrayList(this.mServingParams.dnsServers);
        Inet4Address serverInet4Addr2 = this.mServingParams.getServerInet4Addr();
        DhcpServingParams dhcpServingParams = this.mServingParams;
        return transmitOfferOrAckPacket(DhcpPacket.buildOfferPacket(2, i, broadcastFlag, serverInet4Addr, inet4Address, netAddr, bArr, numValueOf, prefixMaskAsInet4Address, broadcastAddress, arrayList, arrayList2, serverInet4Addr2, null, hostnameIfRequested, dhcpServingParams.metered, (short) dhcpServingParams.linkMtu, null), dhcpPacket, dhcpLease, macAddress, broadcastFlag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean transmitAck(DhcpPacket dhcpPacket, DhcpLease dhcpLease, MacAddress macAddress) {
        boolean broadcastFlag = getBroadcastFlag(dhcpPacket, dhcpLease);
        int leaseTimeout = getLeaseTimeout(dhcpLease);
        String hostnameIfRequested = getHostnameIfRequested(dhcpPacket, dhcpLease);
        int i = dhcpPacket.mTransId;
        Inet4Address serverInet4Addr = this.mServingParams.getServerInet4Addr();
        Inet4Address inet4Address = dhcpPacket.mRelayIp;
        Inet4Address netAddr = dhcpLease.getNetAddr();
        Inet4Address inet4Address2 = dhcpPacket.mClientIp;
        byte[] bArr = dhcpPacket.mClientMac;
        Integer numValueOf = Integer.valueOf(leaseTimeout);
        Inet4Address prefixMaskAsAddress = this.mServingParams.getPrefixMaskAsAddress();
        Inet4Address broadcastAddress = this.mServingParams.getBroadcastAddress();
        ArrayList arrayList = new ArrayList(this.mServingParams.defaultRouters);
        ArrayList arrayList2 = new ArrayList(this.mServingParams.dnsServers);
        Inet4Address serverInet4Addr2 = this.mServingParams.getServerInet4Addr();
        DhcpServingParams dhcpServingParams = this.mServingParams;
        return transmitOfferOrAckPacket(DhcpPacket.buildAckPacket(2, i, broadcastFlag, serverInet4Addr, inet4Address, netAddr, inet4Address2, bArr, numValueOf, prefixMaskAsAddress, broadcastAddress, arrayList, arrayList2, serverInet4Addr2, null, hostnameIfRequested, dhcpServingParams.metered, (short) dhcpServingParams.linkMtu, dhcpPacket.mRapidCommit && this.mDhcpRapidCommitEnabled, null), dhcpPacket, dhcpLease, macAddress, broadcastFlag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean transmitNak(DhcpPacket dhcpPacket, String str) {
        Inet4Address inet4Address;
        this.mLog.w("Transmitting NAK: " + str);
        ByteBuffer byteBufferBuildNakPacket = DhcpPacket.buildNakPacket(2, dhcpPacket.mTransId, this.mServingParams.getServerInet4Addr(), dhcpPacket.mRelayIp, dhcpPacket.mClientMac, true, str);
        if (isEmpty(dhcpPacket.mRelayIp)) {
            inet4Address = NetworkStackConstants.IPV4_ADDR_ALL;
        } else {
            inet4Address = dhcpPacket.mRelayIp;
        }
        return transmitPacket(byteBufferBuildNakPacket, DhcpNakPacket.class.getSimpleName(), inet4Address);
    }

    private boolean transmitOfferOrAckPacket(ByteBuffer byteBuffer, DhcpPacket dhcpPacket, DhcpLease dhcpLease, MacAddress macAddress, boolean z) {
        this.mLog.logf("Transmitting %s with lease %s", dhcpPacket.getClass().getSimpleName(), dhcpLease);
        if (addArpEntry(macAddress, dhcpLease.getNetAddr())) {
            return transmitPacket(byteBuffer, dhcpPacket.getClass().getSimpleName(), getAckOrOfferDst(dhcpPacket, dhcpLease, z));
        }
        return false;
    }

    private boolean transmitPacket(ByteBuffer byteBuffer, String str, Inet4Address inet4Address) {
        try {
            this.mDeps.sendPacket(this.mSocket, byteBuffer, inet4Address);
            return true;
        } catch (ErrnoException | IOException e) {
            this.mLog.e("Can't send packet " + str, e);
            return false;
        }
    }

    private boolean addArpEntry(MacAddress macAddress, Inet4Address inet4Address) {
        try {
            this.mDeps.addArpEntry(inet4Address, macAddress, this.mIfName, this.mSocket);
            return true;
        } catch (IOException e) {
            this.mLog.e("Error adding client to ARP table", e);
            return false;
        }
    }

    private int getLeaseTimeout(DhcpLease dhcpLease) {
        long expTime = (dhcpLease.getExpTime() - this.mClock.elapsedRealtime()) / 1000;
        if (expTime >= 0) {
            if (expTime >= Integer.toUnsignedLong(-1)) {
                return -1;
            }
            return (int) expTime;
        }
        this.mLog.e("Processing expired lease " + dhcpLease);
        return 120;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MacAddress getMacAddr(DhcpPacket dhcpPacket) throws MalformedPacketException {
        try {
            return MacAddress.fromBytes(dhcpPacket.getClientMac());
        } catch (IllegalArgumentException e) {
            throw new MalformedPacketException("Invalid MAC address in packet: " + HexDump.dumpHexString(dhcpPacket.getClientMac()), e);
        }
    }

    private static boolean isEmpty(Inet4Address inet4Address) {
        return inet4Address == null || NetworkStackConstants.IPV4_ADDR_ANY.equals(inet4Address);
    }

    private class PacketListener extends DhcpPacketListener {
        PacketListener(Handler handler) {
            super(handler);
        }

        @Override // android.net.dhcp.DhcpPacketListener
        protected void onReceive(DhcpPacket dhcpPacket, Inet4Address inet4Address, int i) {
            if (i != 68) {
                DhcpServer.this.mLog.logf("Ignored packet of type %s sent from client port %d", dhcpPacket.getClass().getSimpleName(), Integer.valueOf(i));
            } else {
                DhcpServer.this.sendMessage(4, dhcpPacket);
            }
        }

        @Override // android.net.util.FdEventsReader
        protected void logError(String str, Exception exc) {
            DhcpServer.this.mLog.e("Error receiving packet: " + str, exc);
        }

        @Override // android.net.dhcp.DhcpPacketListener
        protected void logParseError(byte[] bArr, int i, DhcpPacket.ParseException parseException) {
            DhcpServer.this.mLog.e("Error parsing packet", parseException);
        }

        @Override // android.net.util.FdEventsReader
        protected FileDescriptor createFd() {
            int andSetThreadStatsTag = TrafficStats.getAndSetThreadStatsTag(-509);
            try {
                DhcpServer.this.mSocket = Os.socket(OsConstants.AF_INET, OsConstants.SOCK_DGRAM | OsConstants.SOCK_NONBLOCK, OsConstants.IPPROTO_UDP);
                SocketUtils.bindSocketToInterface(DhcpServer.this.mSocket, DhcpServer.this.mIfName);
                Os.setsockoptInt(DhcpServer.this.mSocket, OsConstants.SOL_SOCKET, OsConstants.SO_REUSEADDR, 1);
                Os.setsockoptInt(DhcpServer.this.mSocket, OsConstants.SOL_SOCKET, OsConstants.SO_BROADCAST, 1);
                Os.bind(DhcpServer.this.mSocket, NetworkStackConstants.IPV4_ADDR_ANY, 67);
                return DhcpServer.this.mSocket;
            } catch (ErrnoException | IOException e) {
                DhcpServer.this.mLog.e("Error creating UDP socket", e);
                return null;
            } finally {
                TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
            }
        }
    }
}
