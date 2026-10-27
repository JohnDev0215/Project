package com.android.networkstack.tethering;

import android.hardware.tetheroffload.config.V1_0.IOffloadConfig;
import android.hardware.tetheroffload.control.V1_0.IOffloadControl;
import android.hardware.tetheroffload.control.V1_0.IPv4AddrPortPair;
import android.hardware.tetheroffload.control.V1_0.ITetheringOffloadCallback;
import android.hardware.tetheroffload.control.V1_0.NatTimeoutUpdate;
import android.net.netlink.NetlinkSocket;
import android.net.netlink.StructNlMsgHdr;
import android.net.util.SharedLog;
import android.net.util.SocketUtils;
import android.net.util.TetheringUtils;
import android.os.Handler;
import android.os.NativeHandle;
import android.os.RemoteException;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public class OffloadHardwareInterface {
    private static final String TAG = "OffloadHardwareInterface";
    private ControlCallback mControlCallback;
    private final Dependencies mDeps;
    private final Handler mHandler;
    private final SharedLog mLog;
    private IOffloadControl mOffloadControl;
    private TetheringOffloadCallback mTetheringOffloadCallback;

    public static class ControlCallback {
        public abstract void onNatTimeoutUpdate(int i, String str, int i2, String str2, int i3);

        public abstract void onStarted();

        public abstract void onStoppedError();

        public abstract void onStoppedLimitReached();

        public abstract void onStoppedUnsupported();

        public abstract void onSupportAvailable();
    }

    public int getDefaultTetherOffloadDisabled() {
        return 0;
    }

    public static class ForwardedStats {
        public long rxBytes;
        public long txBytes;

        public ForwardedStats() {
            this.rxBytes = 0L;
            this.txBytes = 0L;
        }

        public ForwardedStats(long j, long j2) {
            this.rxBytes = j;
            this.txBytes = j2;
        }

        public void add(ForwardedStats forwardedStats) {
            this.rxBytes += forwardedStats.rxBytes;
            this.txBytes += forwardedStats.txBytes;
        }

        public String toString() {
            return String.format("rx:%s tx:%s", Long.valueOf(this.rxBytes), Long.valueOf(this.txBytes));
        }
    }

    public OffloadHardwareInterface(Handler handler, SharedLog sharedLog) {
        this(handler, sharedLog, new Dependencies(sharedLog));
    }

    OffloadHardwareInterface(Handler handler, SharedLog sharedLog, Dependencies dependencies) {
        this.mHandler = handler;
        this.mLog = sharedLog.forSubComponent(TAG);
        this.mDeps = dependencies;
    }

    static class Dependencies {
        private final SharedLog mLog;

        Dependencies(SharedLog sharedLog) {
            this.mLog = sharedLog;
        }

        public IOffloadConfig getOffloadConfig() {
            try {
                return IOffloadConfig.getService(true);
            } catch (RemoteException | NoSuchElementException e) {
                this.mLog.e("getIOffloadConfig error " + e);
                return null;
            }
        }

        public IOffloadControl getOffloadControl() {
            try {
                return IOffloadControl.getService(true);
            } catch (RemoteException | NoSuchElementException e) {
                this.mLog.e("tethering offload control not supported: " + e);
                return null;
            }
        }

        public NativeHandle createConntrackSocket(int i) {
            try {
                FileDescriptor fileDescriptorForProto = NetlinkSocket.forProto(OsConstants.NETLINK_NETFILTER);
                SocketAddress socketAddressMakeNetlinkSocketAddress = SocketUtils.makeNetlinkSocketAddress(0, i);
                try {
                    Os.bind(fileDescriptorForProto, socketAddressMakeNetlinkSocketAddress);
                    try {
                        Os.connect(fileDescriptorForProto, socketAddressMakeNetlinkSocketAddress);
                        return new NativeHandle(fileDescriptorForProto, true);
                    } catch (ErrnoException | SocketException e) {
                        this.mLog.e("connect to kernel fail for groups " + i + " error: " + e);
                        try {
                            SocketUtils.closeSocket(fileDescriptorForProto);
                        } catch (IOException unused) {
                        }
                        return null;
                    }
                } catch (ErrnoException | SocketException e2) {
                    this.mLog.e("Unable to bind conntrack socket for groups " + i + " error: " + e2);
                    try {
                        SocketUtils.closeSocket(fileDescriptorForProto);
                    } catch (IOException unused2) {
                    }
                    return null;
                }
            } catch (ErrnoException e3) {
                this.mLog.e("Unable to create conntrack socket " + e3);
                return null;
            }
        }
    }

    public boolean initOffloadConfig() {
        IOffloadConfig offloadConfig = this.mDeps.getOffloadConfig();
        if (offloadConfig == null) {
            this.mLog.e("Could not find IOffloadConfig service");
            return false;
        }
        NativeHandle nativeHandleCreateConntrackSocket = this.mDeps.createConntrackSocket(5);
        if (nativeHandleCreateConntrackSocket == null) {
            return false;
        }
        sendNetlinkMessage(nativeHandleCreateConntrackSocket, (short) 257, (short) 769);
        NativeHandle nativeHandleCreateConntrackSocket2 = this.mDeps.createConntrackSocket(6);
        if (nativeHandleCreateConntrackSocket2 == null) {
            closeFdInNativeHandle(nativeHandleCreateConntrackSocket);
            return false;
        }
        final CbResults cbResults = new CbResults();
        try {
            offloadConfig.setHandles(nativeHandleCreateConntrackSocket, nativeHandleCreateConntrackSocket2, new IOffloadConfig.setHandlesCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$odGCw9tpAHBKEwqEALKj1MIFKvA
                @Override // android.hardware.tetheroffload.config.V1_0.IOffloadConfig.setHandlesCallback
                public final void onValues(boolean z, String str) {
                    OffloadHardwareInterface.lambda$initOffloadConfig$0(cbResults, z, str);
                }
            });
            closeFdInNativeHandle(nativeHandleCreateConntrackSocket);
            closeFdInNativeHandle(nativeHandleCreateConntrackSocket2);
            record("initOffloadConfig, setHandles results:", cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record("initOffloadConfig, setHandles fail", e);
            return false;
        }
    }

    static /* synthetic */ void lambda$initOffloadConfig$0(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    public void sendNetlinkMessage(NativeHandle nativeHandle, short s, short s2) {
        byte[] bArr = new byte[16];
        StructNlMsgHdr structNlMsgHdr = new StructNlMsgHdr();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        structNlMsgHdr.nlmsg_len = 16;
        structNlMsgHdr.nlmsg_type = s;
        structNlMsgHdr.nlmsg_flags = s2;
        structNlMsgHdr.nlmsg_seq = 1;
        structNlMsgHdr.pack(byteBufferWrap);
        try {
            NetlinkSocket.sendMessage(nativeHandle.getFileDescriptor(), bArr, 0, 16, 500L);
        } catch (ErrnoException | InterruptedIOException e) {
            this.mLog.e("Unable to send netfilter message, error: " + e);
        }
    }

    private void closeFdInNativeHandle(NativeHandle nativeHandle) {
        try {
            nativeHandle.close();
        } catch (IOException | IllegalStateException unused) {
        }
    }

    public boolean initOffloadControl(ControlCallback controlCallback) {
        String str;
        this.mControlCallback = controlCallback;
        if (this.mOffloadControl == null) {
            IOffloadControl offloadControl = this.mDeps.getOffloadControl();
            this.mOffloadControl = offloadControl;
            if (offloadControl == null) {
                this.mLog.e("tethering IOffloadControl.getService() returned null");
                return false;
            }
        }
        Object[] objArr = new Object[1];
        if (controlCallback == null) {
            str = "null";
        } else {
            str = "0x" + Integer.toHexString(System.identityHashCode(controlCallback));
        }
        objArr[0] = str;
        String str2 = String.format("initOffloadControl(%s)", objArr);
        this.mTetheringOffloadCallback = new TetheringOffloadCallback(this.mHandler, this.mControlCallback, this.mLog);
        final CbResults cbResults = new CbResults();
        try {
            this.mOffloadControl.initOffload(this.mTetheringOffloadCallback, new IOffloadControl.initOffloadCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$51mDdmcPu9xPLRXKfIX7vqwDBzg
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.initOffloadCallback
                public final void onValues(boolean z, String str3) {
                    OffloadHardwareInterface.lambda$initOffloadControl$1(cbResults, z, str3);
                }
            });
            record(str2, cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record(str2, e);
            return false;
        }
    }

    static /* synthetic */ void lambda$initOffloadControl$1(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    public void stopOffloadControl() {
        IOffloadControl iOffloadControl = this.mOffloadControl;
        if (iOffloadControl != null) {
            try {
                iOffloadControl.stopOffload(new IOffloadControl.stopOffloadCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$e5Wll_plHMXnXDs8Cz8b4jPYXec
                    @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.stopOffloadCallback
                    public final void onValues(boolean z, String str) {
                        this.f$0.lambda$stopOffloadControl$2$OffloadHardwareInterface(z, str);
                    }
                });
            } catch (RemoteException e) {
                this.mLog.e("failed to stopOffload: " + e);
            }
        }
        this.mOffloadControl = null;
        this.mTetheringOffloadCallback = null;
        this.mControlCallback = null;
        this.mLog.log("stopOffloadControl()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$stopOffloadControl$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$stopOffloadControl$2$OffloadHardwareInterface(boolean z, String str) {
        if (z) {
            return;
        }
        this.mLog.e("stopOffload failed: " + str);
    }

    public ForwardedStats getForwardedStats(String str) {
        String str2 = String.format("getForwardedStats(%s)", str);
        final ForwardedStats forwardedStats = new ForwardedStats();
        try {
            this.mOffloadControl.getForwardedStats(str, new IOffloadControl.getForwardedStatsCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$kJSz0h94fjcxV-MeeLRSL2pIHTg
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.getForwardedStatsCallback
                public final void onValues(long j, long j2) {
                    OffloadHardwareInterface.lambda$getForwardedStats$3(forwardedStats, j, j2);
                }
            });
            return forwardedStats;
        } catch (RemoteException e) {
            record(str2, e);
            return forwardedStats;
        }
    }

    static /* synthetic */ void lambda$getForwardedStats$3(ForwardedStats forwardedStats, long j, long j2) {
        if (j <= 0) {
            j = 0;
        }
        forwardedStats.rxBytes = j;
        if (j2 <= 0) {
            j2 = 0;
        }
        forwardedStats.txBytes = j2;
    }

    public boolean setLocalPrefixes(ArrayList<String> arrayList) {
        String str = String.format("setLocalPrefixes([%s])", String.join(",", arrayList));
        final CbResults cbResults = new CbResults();
        try {
            this.mOffloadControl.setLocalPrefixes(arrayList, new IOffloadControl.setLocalPrefixesCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$2R6zp5FBRw8oIVmneYN_e2JVnR8
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.setLocalPrefixesCallback
                public final void onValues(boolean z, String str2) {
                    OffloadHardwareInterface.lambda$setLocalPrefixes$4(cbResults, z, str2);
                }
            });
            record(str, cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record(str, e);
            return false;
        }
    }

    static /* synthetic */ void lambda$setLocalPrefixes$4(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    public boolean setDataLimit(String str, long j) {
        String str2 = String.format("setDataLimit(%s, %d)", str, Long.valueOf(j));
        final CbResults cbResults = new CbResults();
        try {
            this.mOffloadControl.setDataLimit(str, j, new IOffloadControl.setDataLimitCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$sT0hgeQoQFNiRI8KgKWGTJN9ze0
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.setDataLimitCallback
                public final void onValues(boolean z, String str3) {
                    OffloadHardwareInterface.lambda$setDataLimit$5(cbResults, z, str3);
                }
            });
            record(str2, cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record(str2, e);
            return false;
        }
    }

    static /* synthetic */ void lambda$setDataLimit$5(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    public boolean setUpstreamParameters(String str, String str2, String str3, ArrayList<String> arrayList) {
        String str4 = str != null ? str : "";
        String str5 = str2 != null ? str2 : "";
        String str6 = str3 != null ? str3 : "";
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        ArrayList<String> arrayList2 = arrayList;
        String str7 = String.format("setUpstreamParameters(%s, %s, %s, [%s])", str4, str5, str6, String.join(",", arrayList2));
        final CbResults cbResults = new CbResults();
        try {
            this.mOffloadControl.setUpstreamParameters(str4, str5, str6, arrayList2, new IOffloadControl.setUpstreamParametersCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$QQUCpPDwCLhmj9hi1UJrLb1WIHU
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.setUpstreamParametersCallback
                public final void onValues(boolean z, String str8) {
                    OffloadHardwareInterface.lambda$setUpstreamParameters$6(cbResults, z, str8);
                }
            });
            record(str7, cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record(str7, e);
            return false;
        }
    }

    static /* synthetic */ void lambda$setUpstreamParameters$6(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    public boolean addDownstreamPrefix(String str, String str2) {
        String str3 = String.format("addDownstreamPrefix(%s, %s)", str, str2);
        final CbResults cbResults = new CbResults();
        try {
            this.mOffloadControl.addDownstream(str, str2, new IOffloadControl.addDownstreamCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$9HYimrgKZ0UzQBCD21Xc5ZttGt8
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.addDownstreamCallback
                public final void onValues(boolean z, String str4) {
                    OffloadHardwareInterface.lambda$addDownstreamPrefix$7(cbResults, z, str4);
                }
            });
            record(str3, cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record(str3, e);
            return false;
        }
    }

    static /* synthetic */ void lambda$addDownstreamPrefix$7(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    public boolean removeDownstreamPrefix(String str, String str2) {
        String str3 = String.format("removeDownstreamPrefix(%s, %s)", str, str2);
        final CbResults cbResults = new CbResults();
        try {
            this.mOffloadControl.removeDownstream(str, str2, new IOffloadControl.removeDownstreamCallback() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$SRo-eaakXAX_ZotbhMl6XR5zNFY
                @Override // android.hardware.tetheroffload.control.V1_0.IOffloadControl.removeDownstreamCallback
                public final void onValues(boolean z, String str4) {
                    OffloadHardwareInterface.lambda$removeDownstreamPrefix$8(cbResults, z, str4);
                }
            });
            record(str3, cbResults);
            return cbResults.mSuccess;
        } catch (RemoteException e) {
            record(str3, e);
            return false;
        }
    }

    static /* synthetic */ void lambda$removeDownstreamPrefix$8(CbResults cbResults, boolean z, String str) {
        cbResults.mSuccess = z;
        cbResults.mErrMsg = str;
    }

    private void record(String str, Throwable th) {
        this.mLog.e(str + " -> exception: " + th);
    }

    private void record(String str, CbResults cbResults) {
        String str2 = str + " -> " + cbResults;
        if (!cbResults.mSuccess) {
            this.mLog.e(str2);
        } else {
            this.mLog.log(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class TetheringOffloadCallback extends ITetheringOffloadCallback.Stub {
        public final ControlCallback controlCb;
        public final Handler handler;
        public final SharedLog log;

        TetheringOffloadCallback(Handler handler, ControlCallback controlCallback, SharedLog sharedLog) {
            this.handler = handler;
            this.controlCb = controlCallback;
            this.log = sharedLog;
        }

        @Override // android.hardware.tetheroffload.control.V1_0.ITetheringOffloadCallback
        public void onEvent(final int i) {
            this.handler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$TetheringOffloadCallback$8qgyJ8rpwhyZzNN2xPcQF2XvWRg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onEvent$0$OffloadHardwareInterface$TetheringOffloadCallback(i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onEvent$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onEvent$0$OffloadHardwareInterface$TetheringOffloadCallback(int i) {
            if (i == 1) {
                this.controlCb.onStarted();
                return;
            }
            if (i == 2) {
                this.controlCb.onStoppedError();
                return;
            }
            if (i == 3) {
                this.controlCb.onStoppedUnsupported();
                return;
            }
            if (i == 4) {
                this.controlCb.onSupportAvailable();
                return;
            }
            if (i == 5) {
                this.controlCb.onStoppedLimitReached();
                return;
            }
            this.log.e("Unsupported OffloadCallbackEvent: " + i);
        }

        @Override // android.hardware.tetheroffload.control.V1_0.ITetheringOffloadCallback
        public void updateTimeout(final NatTimeoutUpdate natTimeoutUpdate) {
            this.handler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadHardwareInterface$TetheringOffloadCallback$unyIcu3GczX1B1RNofFu7Vi7cLU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateTimeout$1$OffloadHardwareInterface$TetheringOffloadCallback(natTimeoutUpdate);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$updateTimeout$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$updateTimeout$1$OffloadHardwareInterface$TetheringOffloadCallback(NatTimeoutUpdate natTimeoutUpdate) {
            ControlCallback controlCallback = this.controlCb;
            int iNetworkProtocolToOsConstant = OffloadHardwareInterface.networkProtocolToOsConstant(natTimeoutUpdate.proto);
            IPv4AddrPortPair iPv4AddrPortPair = natTimeoutUpdate.src;
            String str = iPv4AddrPortPair.addr;
            int iUint16 = TetheringUtils.uint16(iPv4AddrPortPair.port);
            IPv4AddrPortPair iPv4AddrPortPair2 = natTimeoutUpdate.dst;
            controlCallback.onNatTimeoutUpdate(iNetworkProtocolToOsConstant, str, iUint16, iPv4AddrPortPair2.addr, TetheringUtils.uint16(iPv4AddrPortPair2.port));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int networkProtocolToOsConstant(int i) {
        if (i == 6) {
            return OsConstants.IPPROTO_TCP;
        }
        if (i == 17) {
            return OsConstants.IPPROTO_UDP;
        }
        return -Math.abs(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CbResults {
        String mErrMsg;
        boolean mSuccess;

        private CbResults() {
        }

        public String toString() {
            if (this.mSuccess) {
                return "ok";
            }
            return "fail: " + this.mErrMsg;
        }
    }
}
