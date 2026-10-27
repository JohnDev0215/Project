package android.net.dhcp;

import android.content.Context;
import android.net.InetAddresses;
import android.net.Layer2PacketParcelable;
import android.net.MacAddress;
import android.net.NetworkStackIpMemoryStore;
import android.net.TrafficStats;
import android.net.dhcp.DhcpPacket;
import android.net.ipmemorystore.NetworkAttributes;
import android.net.ipmemorystore.OnNetworkAttributesRetrievedListener;
import android.net.ipmemorystore.OnStatusListener;
import android.net.ipmemorystore.Status;
import android.net.metrics.DhcpClientEvent;
import android.net.metrics.DhcpErrorEvent;
import android.net.metrics.IpConnectivityLog;
import android.net.networkstack.DhcpResults;
import android.net.networkstack.util.HexDump;
import android.net.networkstack.util.MessageUtils;
import android.net.networkstack.util.State;
import android.net.networkstack.util.StateMachine;
import android.net.networkstack.util.WakeupMessage;
import android.net.util.FdEventsReader;
import android.net.util.HostnameTransliterator;
import android.net.util.InterfaceParams;
import android.net.util.IpMask;
import android.net.util.NetworkStackUtils;
import android.net.util.PacketReader;
import android.net.util.SocketUtils;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.stats.connectivity.DhcpFeature;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.EventLog;
import android.util.Log;
import android.util.SparseArray;
import com.android.networkstack.R;
import com.android.networkstack.apishim.CaptivePortalDataShimImpl;
import com.android.networkstack.apishim.SocketUtilsShimImpl;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.arp.ArpPacket;
import com.android.networkstack.metrics.IpProvisioningMetrics;
import com.android.server.util.NetworkStackConstants;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class DhcpClient extends StateMachine {
    public static final String ARP_ANNOUNCE_INTERVAL_MS = "arp_announce_interval";
    public static final String ARP_FIRST_ANNOUNCE_DELAY_MS = "arp_first_announce_delay";
    public static final String ARP_FIRST_PROBE_DELAY_MS = "arp_first_probe_delay";
    public static final String ARP_PROBE_MAX_MS = "arp_probe_max";
    public static final String ARP_PROBE_MIN_MS = "arp_probe_min";
    public static final int CMD_ABORT_PRECONNECTION = 1011;
    private static final int CMD_ARP_ANNOUNCEMENT = 1112;
    private static final int CMD_ARP_PROBE = 1111;
    public static final int CMD_CLEAR_LINKADDRESS = 1007;
    public static final int CMD_CONFIGURE_LINKADDRESS = 1008;
    private static final int CMD_EXPIRE_DHCP = 1106;
    private static final int CMD_KICK = 1101;
    public static final int CMD_ON_QUIT = 1005;
    public static final int CMD_POST_DHCP_ACTION = 1004;
    public static final int CMD_PRE_DHCP_ACTION = 1003;
    public static final int CMD_PRE_DHCP_ACTION_COMPLETE = 1006;
    private static final int CMD_REBIND_DHCP = 1105;
    private static final int CMD_RECEIVED_PACKET = 1102;
    public static final int CMD_REFRESH_LINKADDRESS = 1012;
    private static final int CMD_RENEW_DHCP = 1104;
    public static final int CMD_START_DHCP = 1001;
    public static final int CMD_START_PRECONNECTION = 1010;
    public static final int CMD_STOP_DHCP = 1002;
    public static final int CMD_TIMEOUT = 1103;
    public static final String DHCP_RESTART_CONFIG_DELAY = "dhcp_restart_configuration_delay";
    private static final int EVENT_CONFIGURATION_INVALID = 1109;
    private static final int EVENT_CONFIGURATION_OBTAINED = 1108;
    private static final int EVENT_CONFIGURATION_TIMEOUT = 1107;
    private static final int EVENT_IP_CONFLICT = 1110;
    public static final int EVENT_LINKADDRESS_CONFIGURED = 1009;
    public static final long EXPIRED_LEASE = 1;
    private static final Class[] sMessageClasses;
    private static final SparseArray<String> sMessageNames;
    private Configuration mConfiguration;
    private State mConfiguringInterfaceState;
    private int mConflictCount;
    private final Context mContext;
    private final StateMachine mController;
    private Dependencies mDependencies;
    private State mDhcpBoundState;
    private State mDhcpDecliningState;
    private State mDhcpHaveLeaseState;
    private State mDhcpInitRebootState;
    private State mDhcpInitState;
    private DhcpResults mDhcpLease;
    private long mDhcpLeaseExpiry;
    private DhcpPacketHandler mDhcpPacketHandler;
    private State mDhcpPreconnectingState;
    private State mDhcpRebindingState;
    private State mDhcpRebootingState;
    private State mDhcpRenewingState;
    private State mDhcpRequestingState;
    private State mDhcpSelectingState;
    private State mDhcpState;
    private final WakeupMessage mExpiryAlarm;
    private final String mHostname;
    private byte[] mHwAddr;
    private InterfaceParams mIface;
    private final String mIfaceName;
    private SocketAddress mInterfaceBroadcastAddr;
    private State mIpAddressConflictDetectingState;
    private final NetworkStackIpMemoryStore mIpMemoryStore;
    private final WakeupMessage mKickAlarm;
    private Inet4Address mLastAssignedIpv4Address;
    private long mLastAssignedIpv4AddressExpiry;
    private long mLastBoundExitTime;
    private long mLastInitEnterTime;
    private final IpProvisioningMetrics mMetrics;
    private final IpConnectivityLog mMetricsLog;
    private State mObtainingConfigurationState;
    private DhcpResults mOffer;
    private final Random mRandom;
    private final WakeupMessage mRebindAlarm;
    private boolean mRegisteredForPreDhcpNotification;
    private final WakeupMessage mRenewAlarm;
    private State mStoppedState;
    private final WakeupMessage mTimeoutAlarm;
    private int mTransactionId;
    private long mTransactionStartMillis;
    private FileDescriptor mUdpSock;
    private State mWaitBeforeObtainingConfigurationState;
    private State mWaitBeforeRenewalState;
    private State mWaitBeforeStartState;
    private static final boolean VDBG = Log.isLoggable("DhcpClient", 2);
    private static final boolean STATE_DBG = Log.isLoggable("DhcpClient", 3);
    private static final boolean MSG_DBG = Log.isLoggable("DhcpClient", 3);
    private static final boolean PACKET_DBG = Log.isLoggable("DhcpClient", 3);
    static final byte[] DEFAULT_REQUESTED_PARAMS = {1, 3, 6, 15, 26, 28, 51, 58, 59, 43};

    static /* synthetic */ int access$1604(DhcpClient dhcpClient) {
        int i = dhcpClient.mConflictCount + 1;
        dhcpClient.mConflictCount = i;
        return i;
    }

    static {
        Class[] clsArr = {DhcpClient.class};
        sMessageClasses = clsArr;
        sMessageNames = MessageUtils.findMessageNames(clsArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getRequestedParams() {
        if (isCapportApiEnabled()) {
            byte[] bArr = DEFAULT_REQUESTED_PARAMS;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length + 1);
            bArrCopyOf[bArrCopyOf.length - 1] = 114;
            return bArrCopyOf;
        }
        return DEFAULT_REQUESTED_PARAMS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isCapportApiEnabled() {
        return CaptivePortalDataShimImpl.isSupported();
    }

    private WakeupMessage makeWakeupMessage(String str, int i) {
        return new WakeupMessage(this.mContext, getHandler(), DhcpClient.class.getSimpleName() + "." + this.mIfaceName + "." + str, i);
    }

    public static class Dependencies {
        private final IpProvisioningMetrics mMetrics;
        private final NetworkStackIpMemoryStore mNetworkStackIpMemoryStore;

        public Dependencies(NetworkStackIpMemoryStore networkStackIpMemoryStore, IpProvisioningMetrics ipProvisioningMetrics) {
            this.mNetworkStackIpMemoryStore = networkStackIpMemoryStore;
            this.mMetrics = ipProvisioningMetrics;
        }

        public boolean getSendHostnameOption(Context context) {
            return context.getResources().getBoolean(R.bool.config_dhcp_client_hostname);
        }

        public String getDeviceName(Context context) {
            return Settings.Global.getString(context.getContentResolver(), "device_name");
        }

        public NetworkStackIpMemoryStore getIpMemoryStore() {
            return this.mNetworkStackIpMemoryStore;
        }

        public IpProvisioningMetrics getIpProvisioningMetrics() {
            return this.mMetrics;
        }

        public boolean isFeatureEnabled(Context context, String str, boolean z) {
            return NetworkStackUtils.isFeatureEnabled(context, "connectivity", str, z);
        }

        public int getIntDeviceConfig(String str, int i, int i2, int i3) {
            return NetworkStackUtils.getDeviceConfigPropertyInt("connectivity", str, i, i2, i3);
        }

        public PowerManager.WakeLock getWakeLock(PowerManager powerManager) {
            return powerManager.newWakeLock(1, "DhcpClient");
        }
    }

    private DhcpClient(Context context, StateMachine stateMachine, String str, Dependencies dependencies) {
        super("DhcpClient", stateMachine.getHandler());
        this.mMetricsLog = new IpConnectivityLog();
        this.mStoppedState = new StoppedState();
        this.mDhcpState = new DhcpState();
        this.mDhcpInitState = new DhcpInitState();
        this.mDhcpPreconnectingState = new DhcpPreconnectingState();
        this.mDhcpSelectingState = new DhcpSelectingState(this);
        this.mDhcpRequestingState = new DhcpRequestingState();
        this.mDhcpHaveLeaseState = new DhcpHaveLeaseState();
        this.mConfiguringInterfaceState = new ConfiguringInterfaceState();
        this.mDhcpBoundState = new DhcpBoundState();
        this.mDhcpRenewingState = new DhcpRenewingState();
        this.mDhcpRebindingState = new DhcpRebindingState();
        this.mDhcpInitRebootState = new DhcpInitRebootState();
        this.mDhcpRebootingState = new DhcpRebootingState(this);
        this.mObtainingConfigurationState = new ObtainingConfigurationState();
        this.mWaitBeforeStartState = new WaitBeforeStartState(this, this.mDhcpInitState);
        this.mWaitBeforeRenewalState = new WaitBeforeRenewalState(this, this.mDhcpRenewingState);
        this.mWaitBeforeObtainingConfigurationState = new WaitBeforeObtainingConfigurationState(this, this.mObtainingConfigurationState);
        this.mIpAddressConflictDetectingState = new IpAddressConflictDetectingState();
        this.mDhcpDecliningState = new DhcpDecliningState();
        this.mDependencies = dependencies;
        this.mContext = context;
        this.mController = stateMachine;
        this.mIfaceName = str;
        this.mIpMemoryStore = dependencies.getIpMemoryStore();
        this.mMetrics = dependencies.getIpProvisioningMetrics();
        addState(this.mStoppedState);
        addState(this.mDhcpState);
        addState(this.mDhcpInitState, this.mDhcpState);
        addState(this.mWaitBeforeStartState, this.mDhcpState);
        addState(this.mWaitBeforeObtainingConfigurationState, this.mDhcpState);
        addState(this.mDhcpPreconnectingState, this.mDhcpState);
        addState(this.mObtainingConfigurationState, this.mDhcpState);
        addState(this.mDhcpSelectingState, this.mDhcpState);
        addState(this.mDhcpRequestingState, this.mDhcpState);
        addState(this.mIpAddressConflictDetectingState, this.mDhcpState);
        addState(this.mDhcpHaveLeaseState, this.mDhcpState);
        addState(this.mConfiguringInterfaceState, this.mDhcpHaveLeaseState);
        addState(this.mDhcpBoundState, this.mDhcpHaveLeaseState);
        addState(this.mWaitBeforeRenewalState, this.mDhcpHaveLeaseState);
        addState(this.mDhcpRenewingState, this.mDhcpHaveLeaseState);
        addState(this.mDhcpRebindingState, this.mDhcpHaveLeaseState);
        addState(this.mDhcpDecliningState, this.mDhcpHaveLeaseState);
        addState(this.mDhcpInitRebootState, this.mDhcpState);
        addState(this.mDhcpRebootingState, this.mDhcpState);
        setInitialState(this.mStoppedState);
        this.mRandom = new Random();
        this.mKickAlarm = makeWakeupMessage("KICK", CMD_KICK);
        this.mTimeoutAlarm = makeWakeupMessage("TIMEOUT", CMD_TIMEOUT);
        this.mRenewAlarm = makeWakeupMessage("RENEW", CMD_RENEW_DHCP);
        this.mRebindAlarm = makeWakeupMessage("REBIND", CMD_REBIND_DHCP);
        this.mExpiryAlarm = makeWakeupMessage("EXPIRY", CMD_EXPIRE_DHCP);
        boolean sendHostnameOption = dependencies.getSendHostnameOption(context);
        String strTransliterate = sendHostnameOption ? new HostnameTransliterator().transliterate(dependencies.getDeviceName(this.mContext)) : null;
        this.mHostname = strTransliterate;
        this.mMetrics.setHostnameTransinfo(sendHostnameOption, strTransliterate != null);
    }

    public void registerForPreDhcpNotification() {
        this.mRegisteredForPreDhcpNotification = true;
    }

    public static DhcpClient makeDhcpClient(Context context, StateMachine stateMachine, InterfaceParams interfaceParams, Dependencies dependencies) {
        DhcpClient dhcpClient = new DhcpClient(context, stateMachine, interfaceParams.name, dependencies);
        dhcpClient.mIface = interfaceParams;
        dhcpClient.start();
        return dhcpClient;
    }

    public boolean isDhcpLeaseCacheEnabled() {
        return this.mDependencies.isFeatureEnabled(this.mContext, "dhcp_init_reboot_version", ShimUtils.isReleaseOrDevelopmentApiAbove(29));
    }

    public boolean isDhcpRapidCommitEnabled() {
        return this.mDependencies.isFeatureEnabled(this.mContext, "dhcp_rapid_commit_version", false);
    }

    public boolean isDhcpIpConflictDetectEnabled() {
        return this.mDependencies.isFeatureEnabled(this.mContext, "dhcp_ip_conflict_detect_version", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetricEnabledFeatures() {
        if (isDhcpLeaseCacheEnabled()) {
            this.mMetrics.setDhcpEnabledFeature(DhcpFeature.DF_INITREBOOT);
        }
        if (isDhcpRapidCommitEnabled()) {
            this.mMetrics.setDhcpEnabledFeature(DhcpFeature.DF_RAPIDCOMMIT);
        }
        if (isDhcpIpConflictDetectEnabled()) {
            this.mMetrics.setDhcpEnabledFeature(DhcpFeature.DF_DAD);
        }
        if (this.mConfiguration.isPreconnectionEnabled) {
            this.mMetrics.setDhcpEnabledFeature(DhcpFeature.DF_FILS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirmDhcpLease(DhcpPacket dhcpPacket, DhcpResults dhcpResults) {
        setDhcpLeaseExpiry(dhcpPacket);
        acceptDhcpResults(dhcpResults, "Confirmed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initInterface() {
        if (this.mIface == null) {
            this.mIface = InterfaceParams.getByName(this.mIfaceName);
        }
        InterfaceParams interfaceParams = this.mIface;
        if (interfaceParams == null) {
            Log.e("DhcpClient", "Can't determine InterfaceParams for " + this.mIfaceName);
            return false;
        }
        this.mHwAddr = interfaceParams.macAddr.toByteArray();
        this.mInterfaceBroadcastAddr = SocketUtilsShimImpl.newInstance().makePacketSocketAddress(OsConstants.ETH_P_IP, this.mIface.index, DhcpPacket.ETHER_BROADCAST);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNewTransaction() {
        this.mTransactionId = this.mRandom.nextInt();
        this.mTransactionStartMillis = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initUdpSocket() {
        int andSetThreadStatsTag = TrafficStats.getAndSetThreadStatsTag(-511);
        try {
            FileDescriptor fileDescriptorSocket = Os.socket(OsConstants.AF_INET, OsConstants.SOCK_DGRAM, OsConstants.IPPROTO_UDP);
            this.mUdpSock = fileDescriptorSocket;
            SocketUtils.bindSocketToInterface(fileDescriptorSocket, this.mIfaceName);
            Os.setsockoptInt(this.mUdpSock, OsConstants.SOL_SOCKET, OsConstants.SO_REUSEADDR, 1);
            Os.setsockoptInt(this.mUdpSock, OsConstants.SOL_SOCKET, OsConstants.SO_BROADCAST, 1);
            Os.setsockoptInt(this.mUdpSock, OsConstants.SOL_SOCKET, OsConstants.SO_RCVBUF, 0);
            Os.bind(this.mUdpSock, NetworkStackConstants.IPV4_ADDR_ANY, 68);
            return true;
        } catch (ErrnoException | SocketException e) {
            Log.e("DhcpClient", "Error creating UDP socket", e);
            return false;
        } finally {
            TrafficStats.setThreadStatsTag(andSetThreadStatsTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean connectUdpSock(Inet4Address inet4Address) {
        try {
            Os.connect(this.mUdpSock, inet4Address, 67);
            return true;
        } catch (ErrnoException | SocketException e) {
            Log.e("DhcpClient", "Error connecting UDP socket", e);
            return false;
        }
    }

    private class DhcpPacketHandler extends PacketReader {
        private FileDescriptor mPacketSock;

        DhcpPacketHandler(Handler handler) {
            super(handler);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.net.util.FdEventsReader
        public void handlePacket(byte[] bArr, int i) {
            try {
                DhcpPacket dhcpPacketDecodeFullPacket = DhcpPacket.decodeFullPacket(bArr, i, 0, DhcpClient.isCapportApiEnabled() ? new byte[0] : new byte[]{114});
                Log.d("DhcpClient", "Received packet: " + dhcpPacketDecodeFullPacket);
                DhcpClient.this.sendMessage(DhcpClient.CMD_RECEIVED_PACKET, dhcpPacketDecodeFullPacket);
            } catch (DhcpPacket.ParseException e) {
                Log.e("DhcpClient", "Can't parse packet: " + e.getMessage());
                if (DhcpClient.PACKET_DBG) {
                    Log.d("DhcpClient", HexDump.dumpHexString(bArr, 0, i));
                }
                if (e.errorCode == 67502080) {
                    EventLog.writeEvent(1397638484, "31850211", -1, DhcpPacket.ParseException.class.getName());
                }
                DhcpClient.this.mMetricsLog.log(DhcpClient.this.mIfaceName, new DhcpErrorEvent(e.errorCode));
                DhcpClient.this.mMetrics.addDhcpErrorCode(e.errorCode);
            }
        }

        @Override // android.net.util.FdEventsReader
        protected FileDescriptor createFd() {
            try {
                FileDescriptor fileDescriptorSocket = Os.socket(OsConstants.AF_PACKET, OsConstants.SOCK_RAW | OsConstants.SOCK_NONBLOCK, 0);
                this.mPacketSock = fileDescriptorSocket;
                NetworkStackUtils.attachDhcpFilter(fileDescriptorSocket);
                Os.bind(this.mPacketSock, SocketUtils.makePacketSocketAddress(OsConstants.ETH_P_IP, DhcpClient.this.mIface.index));
                return this.mPacketSock;
            } catch (ErrnoException | SocketException e) {
                logError("Error creating packet socket", e);
                FdEventsReader.closeFd(this.mPacketSock);
                this.mPacketSock = null;
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.net.util.PacketReader, android.net.util.FdEventsReader
        public int readPacket(FileDescriptor fileDescriptor, byte[] bArr) throws Exception {
            try {
                return Os.read(fileDescriptor, bArr, 0, bArr.length);
            } catch (ErrnoException | IOException e) {
                DhcpClient.this.mMetricsLog.log(DhcpClient.this.mIfaceName, new DhcpErrorEvent(84017152));
                throw e;
            }
        }

        @Override // android.net.util.FdEventsReader
        protected void logError(String str, Exception exc) {
            Log.e("DhcpClient", str, exc);
        }

        public int transmitPacket(ByteBuffer byteBuffer, SocketAddress socketAddress) throws SocketException, ErrnoException {
            return Os.sendto(this.mPacketSock, byteBuffer.array(), 0, byteBuffer.limit(), 0, socketAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public short getSecs() {
        return (short) ((SystemClock.elapsedRealtime() - this.mTransactionStartMillis) / 1000);
    }

    private boolean transmitPacket(ByteBuffer byteBuffer, String str, int i, Inet4Address inet4Address) {
        try {
            if (i == 0) {
                Log.d("DhcpClient", "Broadcasting " + str);
                this.mDhcpPacketHandler.transmitPacket(byteBuffer, this.mInterfaceBroadcastAddr);
            } else if (i == 2 && inet4Address.equals(DhcpPacket.INADDR_BROADCAST)) {
                Log.d("DhcpClient", "Broadcasting " + str);
                Os.sendto(this.mUdpSock, byteBuffer, 0, inet4Address, 67);
            } else {
                Log.d("DhcpClient", String.format("Unicasting %s to %s", str, Os.getpeername(this.mUdpSock)));
                Os.write(this.mUdpSock, byteBuffer);
            }
            return true;
        } catch (ErrnoException | IOException e) {
            Log.e("DhcpClient", "Can't send packet: ", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendDiscoverPacket() {
        ByteBuffer byteBufferBuildDiscoverPacket = DhcpPacket.buildDiscoverPacket(0, this.mTransactionId, getSecs(), this.mHwAddr, false, getRequestedParams(), isDhcpRapidCommitEnabled(), this.mHostname);
        this.mMetrics.incrementCountForDiscover();
        return transmitPacket(byteBufferBuildDiscoverPacket, "DHCPDISCOVER", 0, DhcpPacket.INADDR_BROADCAST);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendRequestPacket(Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, Inet4Address inet4Address4) {
        int i = DhcpPacket.INADDR_ANY.equals(inet4Address) ? 0 : 2;
        ByteBuffer byteBufferBuildRequestPacket = DhcpPacket.buildRequestPacket(i, this.mTransactionId, getSecs(), inet4Address, false, this.mHwAddr, inet4Address2, inet4Address3, getRequestedParams(), this.mHostname);
        String str = "DHCPREQUEST ciaddr=" + IpMask.maskIpMiddleString(inet4Address.getHostAddress()) + " request=" + IpMask.maskIpMiddleString(inet4Address2.getHostAddress()) + " serverid=" + IpMask.maskIpMiddleString(inet4Address3 != null ? inet4Address3.getHostAddress() : null);
        this.mMetrics.incrementCountForRequest();
        return transmitPacket(byteBufferBuildRequestPacket, str, i, inet4Address4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendDeclinePacket(Inet4Address inet4Address, Inet4Address inet4Address2) {
        return transmitPacket(DhcpPacket.buildDeclinePacket(0, this.mTransactionId, this.mHwAddr, inet4Address, inet4Address2), "DHCPDECLINE", 0, DhcpPacket.INADDR_BROADCAST);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleLeaseTimers() {
        if (this.mDhcpLeaseExpiry == 0) {
            Log.d("DhcpClient", "Infinite lease, no timer scheduling needed");
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.mDhcpLeaseExpiry - jElapsedRealtime;
        long j2 = j / 2;
        long j3 = (7 * j) / 8;
        this.mRenewAlarm.schedule(jElapsedRealtime + j2);
        this.mRebindAlarm.schedule(jElapsedRealtime + j3);
        this.mExpiryAlarm.schedule(jElapsedRealtime + j);
        Log.d("DhcpClient", "Scheduling renewal in " + (j2 / 1000) + "s");
        Log.d("DhcpClient", "Scheduling rebind in " + (j3 / 1000) + "s");
        Log.d("DhcpClient", "Scheduling expiry in " + (j / 1000) + "s");
    }

    private void setLeaseExpiredToIpMemoryStore() {
        String str = this.mConfiguration.l2Key;
        if (str == null) {
            return;
        }
        NetworkAttributes.Builder builder = new NetworkAttributes.Builder();
        builder.setAssignedV4AddressExpiry(1L);
        this.mIpMemoryStore.storeNetworkAttributes(str, builder.build(), new OnStatusListener() { // from class: android.net.dhcp.-$$Lambda$DhcpClient$mBLJHc2MRMrEJWMIkwju34ACfZM
            @Override // android.net.ipmemorystore.OnStatusListener
            public final void onComplete(Status status) {
                DhcpClient.lambda$setLeaseExpiredToIpMemoryStore$0(status);
            }
        });
    }

    static /* synthetic */ void lambda$setLeaseExpiredToIpMemoryStore$0(Status status) {
        if (status.isSuccess()) {
            return;
        }
        Log.e("DhcpClient", "Failed to set lease expiry, status: " + status);
    }

    private void maybeSaveLeaseToIpMemoryStore() {
        DhcpResults dhcpResults;
        String str = this.mConfiguration.l2Key;
        if (str == null || (dhcpResults = this.mDhcpLease) == null || dhcpResults.ipAddress == null) {
            return;
        }
        NetworkAttributes.Builder builder = new NetworkAttributes.Builder();
        builder.setAssignedV4Address((Inet4Address) this.mDhcpLease.ipAddress.getAddress());
        int i = this.mDhcpLease.leaseDuration;
        builder.setAssignedV4AddressExpiry(Long.valueOf(i == -1 ? Long.MAX_VALUE : ((long) (i * 1000)) + System.currentTimeMillis()));
        builder.setDnsAddresses(this.mDhcpLease.dnsServers);
        builder.setMtu(Integer.valueOf(this.mDhcpLease.mtu));
        this.mIpMemoryStore.storeNetworkAttributes(str, builder.build(), new OnStatusListener() { // from class: android.net.dhcp.-$$Lambda$DhcpClient$jO1DBJK2VEXTQivKbgrR9F6ovq8
            @Override // android.net.ipmemorystore.OnStatusListener
            public final void onComplete(Status status) {
                DhcpClient.lambda$maybeSaveLeaseToIpMemoryStore$1(status);
            }
        });
    }

    static /* synthetic */ void lambda$maybeSaveLeaseToIpMemoryStore$1(Status status) {
        if (status.isSuccess()) {
            return;
        }
        Log.e("DhcpClient", "Failed to store network attrs, status: " + status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySuccess() {
        if (isDhcpLeaseCacheEnabled()) {
            maybeSaveLeaseToIpMemoryStore();
        }
        this.mController.sendMessage(CMD_POST_DHCP_ACTION, 1, 0, new DhcpResults(this.mDhcpLease));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyFailure() {
        if (isDhcpLeaseCacheEnabled()) {
            setLeaseExpiredToIpMemoryStore();
        }
        this.mController.sendMessage(CMD_POST_DHCP_ACTION, 2, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptDhcpResults(DhcpResults dhcpResults, String str) {
        this.mDhcpLease = dhcpResults;
        if (dhcpResults.dnsServers.isEmpty()) {
            for (String str2 : this.mContext.getResources().getStringArray(R.array.config_default_dns_servers)) {
                try {
                    this.mDhcpLease.dnsServers.add(InetAddresses.parseNumericAddress(str2));
                } catch (IllegalArgumentException e) {
                    Log.e("DhcpClient", "Invalid default DNS server: " + str2, e);
                }
            }
        }
        this.mOffer = null;
        Log.v("DhcpClient", str + " lease: " + this.mDhcpLease);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDhcpState() {
        this.mDhcpLease = null;
        this.mDhcpLeaseExpiry = 0L;
        this.mOffer = null;
    }

    public void doQuit() {
        Log.d("DhcpClient", "doQuit");
        quit();
    }

    @Override // android.net.networkstack.util.StateMachine
    protected void onQuitting() {
        Log.d("DhcpClient", "onQuitting");
        this.mController.sendMessage(CMD_ON_QUIT);
    }

    abstract class LoggingState extends State {
        private long mEnterTimeMs;

        LoggingState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            if (DhcpClient.STATE_DBG) {
                Log.d("DhcpClient", "Entering state " + getName());
            }
            this.mEnterTimeMs = SystemClock.elapsedRealtime();
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            DhcpClient.this.logState(getName(), (int) (SystemClock.elapsedRealtime() - this.mEnterTimeMs));
        }

        private String messageName(int i) {
            return (String) DhcpClient.sMessageNames.get(i, Integer.toString(i));
        }

        private String messageToString(Message message) {
            return " " + (message.getWhen() - SystemClock.uptimeMillis()) + messageName(message.what) + " " + message.arg1 + " " + message.arg2 + " " + message.obj;
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (!DhcpClient.MSG_DBG) {
                return false;
            }
            Log.d("DhcpClient", getName() + messageToString(message));
            return false;
        }

        @Override // android.net.networkstack.util.State, android.net.networkstack.util.IState
        public String getName() {
            return getClass().getSimpleName();
        }
    }

    abstract class WaitBeforeOtherState extends LoggingState {
        private final State mOtherState;

        WaitBeforeOtherState(State state) {
            super();
            this.mOtherState = state;
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            DhcpClient.this.mController.sendMessage(DhcpClient.CMD_PRE_DHCP_ACTION);
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            if (message.what != 1006) {
                return false;
            }
            DhcpClient.this.transitionTo(this.mOtherState);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preDhcpTransitionTo(State state, State state2) {
        if (!this.mRegisteredForPreDhcpNotification) {
            state = state2;
        }
        transitionTo(state);
    }

    public static class Configuration {
        public final boolean isPreconnectionEnabled;
        public final String l2Key;

        public Configuration(String str, boolean z) {
            this.l2Key = str;
            this.isPreconnectionEnabled = z;
        }
    }

    class StoppedState extends State {
        StoppedState() {
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (message.what != 1001) {
                return false;
            }
            DhcpClient.this.mConfiguration = (Configuration) message.obj;
            if (!DhcpClient.this.mConfiguration.isPreconnectionEnabled) {
                DhcpClient.this.startInitRebootOrInit();
            } else {
                DhcpClient dhcpClient = DhcpClient.this;
                dhcpClient.transitionTo(dhcpClient.mDhcpPreconnectingState);
            }
            DhcpClient.this.recordMetricEnabledFeatures();
            return true;
        }
    }

    class WaitBeforeStartState extends WaitBeforeOtherState {
        WaitBeforeStartState(DhcpClient dhcpClient, State state) {
            super(state);
        }
    }

    class WaitBeforeRenewalState extends WaitBeforeOtherState {
        WaitBeforeRenewalState(DhcpClient dhcpClient, State state) {
            super(state);
        }
    }

    class WaitBeforeObtainingConfigurationState extends WaitBeforeOtherState {
        WaitBeforeObtainingConfigurationState(DhcpClient dhcpClient, State state) {
            super(state);
        }
    }

    class DhcpState extends State {
        DhcpState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            DhcpClient.this.clearDhcpState();
            DhcpClient.this.mConflictCount = 0;
            if (DhcpClient.this.initInterface() && DhcpClient.this.initUdpSocket()) {
                DhcpClient dhcpClient = DhcpClient.this;
                dhcpClient.mDhcpPacketHandler = dhcpClient.new DhcpPacketHandler(dhcpClient.getHandler());
                if (DhcpClient.this.mDhcpPacketHandler.start()) {
                    return;
                } else {
                    Log.e("DhcpClient", "Fail to start DHCP Packet Handler");
                }
            }
            DhcpClient.this.notifyFailure();
            DhcpClient dhcpClient2 = DhcpClient.this;
            dhcpClient2.deferMessage(dhcpClient2.obtainMessage(DhcpClient.CMD_STOP_DHCP));
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            if (DhcpClient.this.mDhcpPacketHandler != null) {
                DhcpClient.this.mDhcpPacketHandler.stop();
                Log.d("DhcpClient", "DHCP Packet Handler stopped");
            }
            NetworkStackUtils.closeSocketQuietly(DhcpClient.this.mUdpSock);
            DhcpClient.this.clearDhcpState();
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            if (message.what != 1002) {
                return false;
            }
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mStoppedState);
            return true;
        }
    }

    public boolean isValidPacket(DhcpPacket dhcpPacket) {
        int transactionId = dhcpPacket.getTransactionId();
        if (transactionId != this.mTransactionId) {
            Log.d("DhcpClient", "Unexpected transaction ID " + transactionId + ", expected " + this.mTransactionId);
            return false;
        }
        if (Arrays.equals(dhcpPacket.getClientMac(), this.mHwAddr)) {
            return true;
        }
        Log.d("DhcpClient", "MAC addr mismatch: got " + HexDump.toHexString(dhcpPacket.getClientMac()) + ", expected " + HexDump.toHexString(dhcpPacket.getClientMac()));
        return false;
    }

    public void setDhcpLeaseExpiry(DhcpPacket dhcpPacket) {
        long leaseTimeMillis = dhcpPacket.getLeaseTimeMillis();
        this.mDhcpLeaseExpiry = leaseTimeMillis > 0 ? SystemClock.elapsedRealtime() + leaseTimeMillis : 0L;
    }

    abstract class TimeoutState extends LoggingState {
        protected int mTimeout;

        protected abstract void timeout();

        TimeoutState() {
            super();
            this.mTimeout = 0;
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            maybeInitTimeout();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            if (message.what != 1103) {
                return false;
            }
            timeout();
            return true;
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void exit() {
            super.exit();
            DhcpClient.this.mTimeoutAlarm.cancel();
        }

        private void maybeInitTimeout() {
            if (this.mTimeout > 0) {
                DhcpClient.this.mTimeoutAlarm.schedule(SystemClock.elapsedRealtime() + ((long) this.mTimeout));
            }
        }
    }

    abstract class PacketRetransmittingState extends TimeoutState {
        private int mTimer;

        protected abstract void receivePacket(DhcpPacket dhcpPacket);

        protected abstract boolean sendPacket();

        @Override // android.net.dhcp.DhcpClient.TimeoutState
        protected void timeout() {
        }

        PacketRetransmittingState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            initTimer();
            DhcpClient.this.sendMessage(DhcpClient.CMD_KICK);
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            int i = message.what;
            if (i == DhcpClient.CMD_KICK) {
                sendPacket();
                scheduleKick();
                return true;
            }
            if (i != DhcpClient.CMD_RECEIVED_PACKET) {
                return false;
            }
            receivePacket((DhcpPacket) message.obj);
            return true;
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void exit() {
            super.exit();
            DhcpClient.this.mKickAlarm.cancel();
        }

        protected void initTimer() {
            this.mTimer = 1000;
        }

        protected int jitterTimer(int i) {
            int i2 = i / 10;
            return i + (DhcpClient.this.mRandom.nextInt(i2 * 2) - i2);
        }

        protected void scheduleKick() {
            DhcpClient.this.mKickAlarm.schedule(SystemClock.elapsedRealtime() + ((long) jitterTimer(this.mTimer)));
            int i = this.mTimer * 2;
            this.mTimer = i;
            if (i > 512000) {
                this.mTimer = 512000;
            }
        }
    }

    class ObtainingConfigurationState extends LoggingState {
        ObtainingConfigurationState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            DhcpClient.this.sendMessageDelayed(DhcpClient.EVENT_CONFIGURATION_TIMEOUT, 1000L);
            DhcpClient.this.mIpMemoryStore.retrieveNetworkAttributes(DhcpClient.this.mConfiguration.l2Key, new OnNetworkAttributesRetrievedListener() { // from class: android.net.dhcp.-$$Lambda$DhcpClient$ObtainingConfigurationState$WzOAreG5bPPmz9UDMS7es2r0Dnk
                @Override // android.net.ipmemorystore.OnNetworkAttributesRetrievedListener
                public final void onNetworkAttributesRetrieved(Status status, String str, NetworkAttributes networkAttributes) {
                    this.f$0.lambda$enter$0$DhcpClient$ObtainingConfigurationState(status, str, networkAttributes);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$enter$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$enter$0$DhcpClient$ObtainingConfigurationState(Status status, String str, NetworkAttributes networkAttributes) {
            if (networkAttributes == null || networkAttributes.assignedV4Address == null) {
                if (!status.isSuccess()) {
                    Log.e("DhcpClient", "Error retrieving network attributes: " + status);
                }
                DhcpClient.this.sendMessage(DhcpClient.EVENT_CONFIGURATION_INVALID);
                return;
            }
            DhcpClient.this.sendMessage(DhcpClient.EVENT_CONFIGURATION_OBTAINED, networkAttributes);
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            switch (message.what) {
                case DhcpClient.EVENT_CONFIGURATION_TIMEOUT /* 1107 */:
                case DhcpClient.EVENT_CONFIGURATION_INVALID /* 1109 */:
                    DhcpClient dhcpClient = DhcpClient.this;
                    dhcpClient.transitionTo(dhcpClient.mDhcpInitState);
                    break;
                case DhcpClient.EVENT_CONFIGURATION_OBTAINED /* 1108 */:
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    NetworkAttributes networkAttributes = (NetworkAttributes) message.obj;
                    Log.d("DhcpClient", "l2key: " + DhcpClient.this.mConfiguration.l2Key + " lease address: " + IpMask.maskIpMiddle(networkAttributes.assignedV4Address) + " lease expiry: " + networkAttributes.assignedV4AddressExpiry + " current time: " + jCurrentTimeMillis);
                    if (jCurrentTimeMillis < networkAttributes.assignedV4AddressExpiry.longValue()) {
                        DhcpClient.this.mLastAssignedIpv4Address = networkAttributes.assignedV4Address;
                        DhcpClient.this.mLastAssignedIpv4AddressExpiry = networkAttributes.assignedV4AddressExpiry.longValue();
                        DhcpClient dhcpClient2 = DhcpClient.this;
                        dhcpClient2.transitionTo(dhcpClient2.mDhcpInitRebootState);
                    } else {
                        DhcpClient dhcpClient3 = DhcpClient.this;
                        dhcpClient3.transitionTo(dhcpClient3.mDhcpInitState);
                    }
                    break;
                default:
                    DhcpClient.this.deferMessage(message);
                    break;
            }
            return true;
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void exit() {
            super.exit();
            DhcpClient.this.removeMessages(DhcpClient.EVENT_CONFIGURATION_INVALID);
            DhcpClient.this.removeMessages(DhcpClient.EVENT_CONFIGURATION_TIMEOUT);
            DhcpClient.this.removeMessages(DhcpClient.EVENT_CONFIGURATION_OBTAINED);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void receiveOfferOrAckPacket(DhcpPacket dhcpPacket, boolean z) {
        DhcpResults dhcpResults;
        if (isValidPacket(dhcpPacket)) {
            if (dhcpPacket instanceof DhcpOfferPacket) {
                DhcpResults dhcpResults2 = dhcpPacket.toDhcpResults();
                this.mOffer = dhcpResults2;
                if (dhcpResults2 != null) {
                    Log.d("DhcpClient", "Got pending lease: " + this.mOffer);
                    transitionTo(this.mDhcpRequestingState);
                    return;
                }
                return;
            }
            if ((dhcpPacket instanceof DhcpAckPacket) && z && dhcpPacket.mRapidCommit && (dhcpResults = dhcpPacket.toDhcpResults()) != null) {
                confirmDhcpLease(dhcpPacket, dhcpResults);
                transitionTo(isDhcpIpConflictDetectEnabled() ? this.mIpAddressConflictDetectingState : this.mConfiguringInterfaceState);
            }
        }
    }

    class DhcpInitState extends PacketRetransmittingState {
        public DhcpInitState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            DhcpClient.this.startNewTransaction();
            DhcpClient.this.mLastInitEnterTime = SystemClock.elapsedRealtime();
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected boolean sendPacket() {
            return DhcpClient.this.sendDiscoverPacket();
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected void receivePacket(DhcpPacket dhcpPacket) {
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.receiveOfferOrAckPacket(dhcpPacket, dhcpClient.isDhcpRapidCommitEnabled());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startInitRebootOrInit() {
        if (isDhcpLeaseCacheEnabled()) {
            preDhcpTransitionTo(this.mWaitBeforeObtainingConfigurationState, this.mObtainingConfigurationState);
        } else {
            preDhcpTransitionTo(this.mWaitBeforeStartState, this.mDhcpInitState);
        }
    }

    class DhcpPreconnectingState extends TimeoutState {
        DhcpPreconnectingState() {
            super();
            this.mTimeout = 1000;
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            DhcpClient.this.startNewTransaction();
            DhcpClient.this.mLastInitEnterTime = SystemClock.elapsedRealtime();
            sendPreconnectionPacket();
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            int i = message.what;
            if (i == 1011) {
                DhcpClient.this.startInitRebootOrInit();
                return true;
            }
            if (i != DhcpClient.CMD_RECEIVED_PACKET) {
                return false;
            }
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.receiveOfferOrAckPacket((DhcpPacket) message.obj, dhcpClient.mConfiguration.isPreconnectionEnabled);
            return true;
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState
        public void timeout() {
            DhcpClient.this.startInitRebootOrInit();
        }

        private void sendPreconnectionPacket() {
            Layer2PacketParcelable layer2PacketParcelable = new Layer2PacketParcelable();
            ByteBuffer byteBufferBuildDiscoverPacket = DhcpPacket.buildDiscoverPacket(0, DhcpClient.this.mTransactionId, DhcpClient.this.getSecs(), DhcpClient.this.mHwAddr, false, DhcpClient.this.getRequestedParams(), true, DhcpClient.this.mHostname);
            layer2PacketParcelable.dstMacAddress = MacAddress.fromBytes(DhcpPacket.ETHER_BROADCAST);
            layer2PacketParcelable.payload = Arrays.copyOf(byteBufferBuildDiscoverPacket.array(), byteBufferBuildDiscoverPacket.limit());
            DhcpClient.this.mController.sendMessage(DhcpClient.CMD_START_PRECONNECTION, layer2PacketParcelable);
        }
    }

    class DhcpSelectingState extends LoggingState {
        DhcpSelectingState(DhcpClient dhcpClient) {
            super();
        }
    }

    class DhcpRequestingState extends PacketRetransmittingState {
        public DhcpRequestingState() {
            super();
            this.mTimeout = 18000;
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected boolean sendPacket() {
            DhcpClient dhcpClient = DhcpClient.this;
            return dhcpClient.sendRequestPacket(DhcpPacket.INADDR_ANY, (Inet4Address) dhcpClient.mOffer.ipAddress.getAddress(), DhcpClient.this.mOffer.serverAddress, DhcpPacket.INADDR_BROADCAST);
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected void receivePacket(DhcpPacket dhcpPacket) {
            if (DhcpClient.this.isValidPacket(dhcpPacket)) {
                if (dhcpPacket instanceof DhcpAckPacket) {
                    DhcpResults dhcpResults = dhcpPacket.toDhcpResults();
                    if (dhcpResults != null) {
                        DhcpClient.this.confirmDhcpLease(dhcpPacket, dhcpResults);
                        DhcpClient dhcpClient = DhcpClient.this;
                        dhcpClient.transitionTo(dhcpClient.isDhcpIpConflictDetectEnabled() ? DhcpClient.this.mIpAddressConflictDetectingState : DhcpClient.this.mConfiguringInterfaceState);
                        return;
                    }
                    return;
                }
                if (dhcpPacket instanceof DhcpNakPacket) {
                    Log.d("DhcpClient", "Received NAK, returning to INIT");
                    DhcpClient.this.mOffer = null;
                    DhcpClient dhcpClient2 = DhcpClient.this;
                    dhcpClient2.transitionTo(dhcpClient2.mDhcpInitState);
                }
            }
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState
        protected void timeout() {
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mDhcpInitState);
        }
    }

    class DhcpHaveLeaseState extends State {
        DhcpHaveLeaseState() {
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (message.what != DhcpClient.CMD_EXPIRE_DHCP) {
                return false;
            }
            Log.d("DhcpClient", "Lease expired!");
            DhcpClient.this.notifyFailure();
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mDhcpInitState);
            return true;
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            DhcpClient.this.mRenewAlarm.cancel();
            DhcpClient.this.mRebindAlarm.cancel();
            DhcpClient.this.mExpiryAlarm.cancel();
            DhcpClient.this.clearDhcpState();
            DhcpClient.this.mController.sendMessage(DhcpClient.CMD_CLEAR_LINKADDRESS);
        }
    }

    class ConfiguringInterfaceState extends LoggingState {
        ConfiguringInterfaceState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            DhcpClient.this.notifySuccess();
            DhcpClient.this.mController.sendMessage(DhcpClient.CMD_CONFIGURE_LINKADDRESS, DhcpClient.this.mDhcpLease.ipAddress);
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            if (message.what != 1009) {
                return false;
            }
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mDhcpBoundState);
            return true;
        }
    }

    private class IpConflictDetector extends PacketReader {
        private FileDescriptor mArpSock;
        private final Inet4Address mTargetIp;

        IpConflictDetector(Handler handler, Inet4Address inet4Address) {
            super(handler);
            this.mTargetIp = inet4Address;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.net.util.FdEventsReader
        public void handlePacket(byte[] bArr, int i) {
            try {
                if (DhcpClient.this.hasIpAddressConflict(ArpPacket.parseArpPacket(bArr, i), this.mTargetIp)) {
                    DhcpClient.this.mMetrics.incrementCountForIpConflict();
                    DhcpClient.this.sendMessage(DhcpClient.EVENT_IP_CONFLICT);
                }
            } catch (ArpPacket.ParseException e) {
                logError("Can't parse ARP packet", e);
            }
        }

        @Override // android.net.util.FdEventsReader
        protected FileDescriptor createFd() {
            try {
                this.mArpSock = Os.socket(OsConstants.AF_PACKET, OsConstants.SOCK_RAW | OsConstants.SOCK_NONBLOCK, 0);
                Os.bind(this.mArpSock, SocketUtils.makePacketSocketAddress(OsConstants.ETH_P_ARP, DhcpClient.this.mIface.index));
                return this.mArpSock;
            } catch (ErrnoException | SocketException e) {
                this.logError("Error creating ARP socket", e);
                FdEventsReader.closeFd(this.mArpSock);
                this.mArpSock = null;
                return null;
            }
        }

        @Override // android.net.util.FdEventsReader
        protected void logError(String str, Exception exc) {
            Log.e("DhcpClient", str, exc);
        }

        public boolean transmitPacket(Inet4Address inet4Address, Inet4Address inet4Address2, byte[] bArr, SocketAddress socketAddress) {
            ByteBuffer byteBufferBuildArpPacket = ArpPacket.buildArpPacket(DhcpPacket.ETHER_BROADCAST, bArr, inet4Address.getAddress(), new byte[6], inet4Address2.getAddress(), (short) 1);
            try {
                Os.sendto(this.mArpSock, byteBufferBuildArpPacket.array(), 0, byteBufferBuildArpPacket.limit(), 0, socketAddress);
                return true;
            } catch (ErrnoException | SocketException e) {
                logError("Can't send ARP packet", e);
                return false;
            }
        }
    }

    private boolean isArpProbe(ArpPacket arpPacket) {
        return arpPacket.opCode == 1 && arpPacket.senderIp.equals(DhcpPacket.INADDR_ANY) && !arpPacket.targetIp.equals(DhcpPacket.INADDR_ANY);
    }

    private boolean packetHasIpAddressConflict(ArpPacket arpPacket, Inet4Address inet4Address) {
        return ((!arpPacket.senderIp.equals(DhcpPacket.INADDR_ANY) && arpPacket.senderIp.equals(inet4Address)) || (isArpProbe(arpPacket) && arpPacket.targetIp.equals(inet4Address))) && !Arrays.equals(arpPacket.senderHwAddress.toByteArray(), this.mHwAddr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasIpAddressConflict(ArpPacket arpPacket, Inet4Address inet4Address) {
        if (!packetHasIpAddressConflict(arpPacket, inet4Address)) {
            return false;
        }
        String hostAddress = arpPacket.senderIp.getHostAddress();
        String hostAddress2 = arpPacket.targetIp.getHostAddress();
        MacAddress macAddress = arpPacket.senderHwAddress;
        MacAddress macAddressFromBytes = MacAddress.fromBytes(this.mHwAddr);
        StringBuilder sb = new StringBuilder();
        sb.append("IP address conflict detected:");
        sb.append(arpPacket.opCode == 1 ? "ARP Request" : "ARP Reply");
        sb.append(" ARP sender MAC: ");
        sb.append(macAddress.toString());
        sb.append(" host MAC: ");
        sb.append(macAddressFromBytes.toString());
        sb.append(" ARP sender IP: ");
        sb.append(hostAddress);
        sb.append(" ARP target IP: ");
        sb.append(hostAddress2);
        sb.append(" host target IP: ");
        sb.append(inet4Address.getHostAddress());
        Log.d("DhcpClient", sb.toString());
        return true;
    }

    class IpAddressConflictDetectingState extends LoggingState {
        private int mArpAnnounceCount;
        private int mArpAnnounceIntervalMs;
        private int mArpFirstAnnounceDelayMs;
        private int mArpFirstProbeDelayMs;
        private int mArpProbeCount;
        private int mArpProbeMaxDelayMs;
        private int mArpProbeMinDelayMs;
        private IpConflictDetector mIpConflictDetector;
        private Inet4Address mTargetIp;
        private PowerManager.WakeLock mTimeoutWakeLock;

        IpAddressConflictDetectingState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            this.mArpProbeCount = 0;
            this.mArpAnnounceCount = 0;
            this.mTargetIp = (Inet4Address) DhcpClient.this.mDhcpLease.ipAddress.getAddress();
            DhcpClient dhcpClient = DhcpClient.this;
            IpConflictDetector ipConflictDetector = dhcpClient.new IpConflictDetector(dhcpClient.getHandler(), this.mTargetIp);
            this.mIpConflictDetector = ipConflictDetector;
            if (!ipConflictDetector.start()) {
                Log.e("DhcpClient", "Fail to start IP Conflict Detector");
                DhcpClient dhcpClient2 = DhcpClient.this;
                dhcpClient2.transitionTo(dhcpClient2.mConfiguringInterfaceState);
                return;
            }
            readIpConflictParametersFromDeviceConfig();
            if (DhcpClient.VDBG) {
                Log.d("DhcpClient", "ARP First Probe delay: " + this.mArpFirstProbeDelayMs + " ARP Probe Max delay: " + this.mArpProbeMaxDelayMs + " ARP Probe Min delay: " + this.mArpProbeMinDelayMs + " ARP First Announce delay: " + this.mArpFirstAnnounceDelayMs + " ARP Announce interval: " + this.mArpAnnounceIntervalMs);
            }
            PowerManager.WakeLock wakeLock = DhcpClient.this.mDependencies.getWakeLock((PowerManager) DhcpClient.this.mContext.getSystemService(PowerManager.class));
            this.mTimeoutWakeLock = wakeLock;
            wakeLock.acquire();
            DhcpClient.this.sendMessageDelayed(DhcpClient.CMD_ARP_PROBE, r0.mRandom.nextInt(this.mArpFirstProbeDelayMs));
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            switch (message.what) {
                case DhcpClient.EVENT_IP_CONFLICT /* 1110 */:
                    DhcpClient dhcpClient = DhcpClient.this;
                    dhcpClient.transitionTo(dhcpClient.mDhcpDecliningState);
                    break;
                case DhcpClient.CMD_ARP_PROBE /* 1111 */:
                    sendArpProbe();
                    int i = this.mArpProbeCount + 1;
                    this.mArpProbeCount = i;
                    if (i < 3) {
                        scheduleProbe();
                    } else {
                        scheduleAnnounce(this.mArpFirstAnnounceDelayMs);
                    }
                    break;
                case DhcpClient.CMD_ARP_ANNOUNCEMENT /* 1112 */:
                    sendArpAnnounce();
                    int i2 = this.mArpAnnounceCount + 1;
                    this.mArpAnnounceCount = i2;
                    if (i2 < 2) {
                        scheduleAnnounce(this.mArpAnnounceIntervalMs);
                    } else {
                        DhcpClient dhcpClient2 = DhcpClient.this;
                        dhcpClient2.transitionTo(dhcpClient2.mConfiguringInterfaceState);
                    }
                    break;
            }
            return true;
        }

        private void scheduleProbe() {
            DhcpClient.this.sendMessageDelayed(DhcpClient.CMD_ARP_PROBE, DhcpClient.this.mRandom.nextInt(this.mArpProbeMaxDelayMs - this.mArpProbeMinDelayMs) + this.mArpProbeMinDelayMs);
        }

        private void scheduleAnnounce(int i) {
            DhcpClient.this.sendMessageDelayed(DhcpClient.CMD_ARP_ANNOUNCEMENT, i);
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void exit() {
            super.exit();
            this.mTimeoutWakeLock.release();
            this.mIpConflictDetector.stop();
            Log.d("DhcpClient", "IP Conflict Detector stopped");
            DhcpClient.this.removeMessages(DhcpClient.CMD_ARP_PROBE);
            DhcpClient.this.removeMessages(DhcpClient.CMD_ARP_ANNOUNCEMENT);
            DhcpClient.this.removeMessages(DhcpClient.EVENT_IP_CONFLICT);
        }

        private void readIpConflictParametersFromDeviceConfig() {
            this.mArpFirstProbeDelayMs = DhcpClient.this.mDependencies.getIntDeviceConfig(DhcpClient.ARP_FIRST_PROBE_DELAY_MS, 10, 1000, 100);
            int intDeviceConfig = DhcpClient.this.mDependencies.getIntDeviceConfig(DhcpClient.ARP_PROBE_MIN_MS, 10, 1000, 100);
            this.mArpProbeMinDelayMs = intDeviceConfig;
            this.mArpProbeMaxDelayMs = Math.max(intDeviceConfig + 1, DhcpClient.this.mDependencies.getIntDeviceConfig(DhcpClient.ARP_PROBE_MAX_MS, 20, 2000, 300));
            this.mArpFirstAnnounceDelayMs = DhcpClient.this.mDependencies.getIntDeviceConfig(DhcpClient.ARP_FIRST_ANNOUNCE_DELAY_MS, 20, 2000, 100);
            this.mArpAnnounceIntervalMs = DhcpClient.this.mDependencies.getIntDeviceConfig(DhcpClient.ARP_ANNOUNCE_INTERVAL_MS, 20, 2000, 100);
        }

        private boolean sendArpProbe() {
            return this.mIpConflictDetector.transmitPacket(this.mTargetIp, DhcpPacket.INADDR_ANY, DhcpClient.this.mHwAddr, DhcpClient.this.mInterfaceBroadcastAddr);
        }

        private boolean sendArpAnnounce() {
            IpConflictDetector ipConflictDetector = this.mIpConflictDetector;
            Inet4Address inet4Address = this.mTargetIp;
            return ipConflictDetector.transmitPacket(inet4Address, inet4Address, DhcpClient.this.mHwAddr, DhcpClient.this.mInterfaceBroadcastAddr);
        }
    }

    class DhcpBoundState extends LoggingState {
        DhcpBoundState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            if (DhcpClient.this.mDhcpLease.serverAddress != null) {
                DhcpClient dhcpClient = DhcpClient.this;
                if (!dhcpClient.connectUdpSock(dhcpClient.mDhcpLease.serverAddress)) {
                    DhcpClient.this.notifyFailure();
                    DhcpClient dhcpClient2 = DhcpClient.this;
                    dhcpClient2.transitionTo(dhcpClient2.mStoppedState);
                }
            }
            DhcpClient.this.scheduleLeaseTimers();
            logTimeToBoundState();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void exit() {
            super.exit();
            DhcpClient.this.mLastBoundExitTime = SystemClock.elapsedRealtime();
        }

        @Override // android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            super.processMessage(message);
            int i = message.what;
            if (i == 1012) {
                DhcpClient dhcpClient = DhcpClient.this;
                dhcpClient.transitionTo(dhcpClient.mDhcpRebindingState);
                return true;
            }
            if (i != DhcpClient.CMD_RENEW_DHCP) {
                return false;
            }
            DhcpClient dhcpClient2 = DhcpClient.this;
            dhcpClient2.preDhcpTransitionTo(dhcpClient2.mWaitBeforeRenewalState, DhcpClient.this.mDhcpRenewingState);
            return true;
        }

        private void logTimeToBoundState() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (DhcpClient.this.mLastBoundExitTime > DhcpClient.this.mLastInitEnterTime) {
                DhcpClient dhcpClient = DhcpClient.this;
                dhcpClient.logState("RenewingBoundState", (int) (jElapsedRealtime - dhcpClient.mLastBoundExitTime));
            } else {
                DhcpClient dhcpClient2 = DhcpClient.this;
                dhcpClient2.logState("InitialBoundState", (int) (jElapsedRealtime - dhcpClient2.mLastInitEnterTime));
            }
        }
    }

    abstract class DhcpReacquiringState extends PacketRetransmittingState {
        protected String mLeaseMsg;

        protected abstract Inet4Address packetDestination();

        DhcpReacquiringState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            DhcpClient.this.startNewTransaction();
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected boolean sendPacket() {
            DhcpClient dhcpClient = DhcpClient.this;
            return dhcpClient.sendRequestPacket((Inet4Address) dhcpClient.mDhcpLease.ipAddress.getAddress(), DhcpPacket.INADDR_ANY, null, packetDestination());
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected void receivePacket(DhcpPacket dhcpPacket) {
            if (DhcpClient.this.isValidPacket(dhcpPacket)) {
                if (dhcpPacket instanceof DhcpAckPacket) {
                    DhcpResults dhcpResults = dhcpPacket.toDhcpResults();
                    if (dhcpResults != null) {
                        if (!DhcpClient.this.mDhcpLease.ipAddress.equals(dhcpResults.ipAddress)) {
                            Log.d("DhcpClient", "Renewed lease not for our current IP address!");
                            DhcpClient.this.notifyFailure();
                            DhcpClient dhcpClient = DhcpClient.this;
                            dhcpClient.transitionTo(dhcpClient.mDhcpInitState);
                            return;
                        }
                        DhcpClient.this.setDhcpLeaseExpiry(dhcpPacket);
                        DhcpClient.this.acceptDhcpResults(dhcpResults, this.mLeaseMsg);
                        DhcpClient.this.notifySuccess();
                        DhcpClient dhcpClient2 = DhcpClient.this;
                        dhcpClient2.transitionTo(dhcpClient2.mDhcpBoundState);
                        return;
                    }
                    return;
                }
                if (dhcpPacket instanceof DhcpNakPacket) {
                    Log.d("DhcpClient", "Received NAK, returning to INIT");
                    DhcpClient.this.notifyFailure();
                    DhcpClient dhcpClient3 = DhcpClient.this;
                    dhcpClient3.transitionTo(dhcpClient3.mDhcpInitState);
                }
            }
        }
    }

    class DhcpRenewingState extends DhcpReacquiringState {
        public DhcpRenewingState() {
            super();
            this.mLeaseMsg = "Renewed";
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            if (message.what != DhcpClient.CMD_REBIND_DHCP) {
                return false;
            }
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mDhcpRebindingState);
            return true;
        }

        @Override // android.net.dhcp.DhcpClient.DhcpReacquiringState
        protected Inet4Address packetDestination() {
            return DhcpClient.this.mDhcpLease.serverAddress != null ? DhcpClient.this.mDhcpLease.serverAddress : DhcpPacket.INADDR_BROADCAST;
        }
    }

    class DhcpRebindingState extends DhcpReacquiringState {
        public DhcpRebindingState() {
            super();
            this.mLeaseMsg = "Rebound";
        }

        @Override // android.net.dhcp.DhcpClient.DhcpReacquiringState, android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            super.enter();
            NetworkStackUtils.closeSocketQuietly(DhcpClient.this.mUdpSock);
            if (DhcpClient.this.initUdpSocket()) {
                return;
            }
            Log.e("DhcpClient", "Failed to recreate UDP socket");
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mDhcpInitState);
        }

        @Override // android.net.dhcp.DhcpClient.DhcpReacquiringState
        protected Inet4Address packetDestination() {
            return DhcpPacket.INADDR_BROADCAST;
        }
    }

    class DhcpInitRebootState extends DhcpRequestingState {
        DhcpInitRebootState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            this.mTimeout = 5000;
            super.enter();
            DhcpClient.this.startNewTransaction();
        }

        @Override // android.net.dhcp.DhcpClient.DhcpRequestingState, android.net.dhcp.DhcpClient.PacketRetransmittingState
        protected boolean sendPacket() {
            DhcpClient dhcpClient = DhcpClient.this;
            return dhcpClient.sendRequestPacket(DhcpPacket.INADDR_ANY, dhcpClient.mLastAssignedIpv4Address, null, DhcpPacket.INADDR_BROADCAST);
        }

        @Override // android.net.dhcp.DhcpClient.PacketRetransmittingState, android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void exit() {
            DhcpClient.this.mLastAssignedIpv4Address = null;
            DhcpClient.this.mLastAssignedIpv4AddressExpiry = 0L;
        }
    }

    class DhcpRebootingState extends LoggingState {
        DhcpRebootingState(DhcpClient dhcpClient) {
            super();
        }
    }

    class DhcpDecliningState extends TimeoutState {
        DhcpDecliningState() {
            super();
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState, android.net.dhcp.DhcpClient.LoggingState, android.net.networkstack.util.State
        public void enter() {
            if (DhcpClient.access$1604(DhcpClient.this) <= 2) {
                this.mTimeout = DhcpClient.this.mDependencies.getIntDeviceConfig(DhcpClient.DHCP_RESTART_CONFIG_DELAY, 100, 10000, 1000);
                super.enter();
                sendPacket();
            } else {
                DhcpClient dhcpClient = DhcpClient.this;
                dhcpClient.transitionTo(dhcpClient.mConfiguringInterfaceState);
            }
        }

        @Override // android.net.dhcp.DhcpClient.TimeoutState
        protected void timeout() {
            DhcpClient dhcpClient = DhcpClient.this;
            dhcpClient.transitionTo(dhcpClient.mDhcpInitState);
        }

        private boolean sendPacket() {
            DhcpClient dhcpClient = DhcpClient.this;
            return dhcpClient.sendDeclinePacket((Inet4Address) dhcpClient.mDhcpLease.ipAddress.getAddress(), DhcpClient.this.mDhcpLease.serverAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logState(String str, int i) {
        this.mMetricsLog.log(this.mIfaceName, new DhcpClientEvent.Builder().setMsg(str).setDurationMs(i).build());
    }
}
