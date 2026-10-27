package vendor.ts.iap.V1_0;

import android.hidl.base.V1_0.DebugInfo;
import android.hidl.base.V1_0.IBase;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.lib.common.CpConst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface IIap extends IBase {
    public static final String kInterfaceName = "vendor.ts.iap@1.0::IIap";

    @FunctionalInterface
    public interface getCurrentTrackPidCallback {
        void onValues(int i, long j, int i2);
    }

    @FunctionalInterface
    public interface getTrackInfoCallback {
        void onValues(int i, IAP_TAG_ST_H iap_tag_st_h);
    }

    int acceptCall(long j, byte b, String str) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    IHwBinder asBinder();

    int connect(long j) throws RemoteException;

    long create(HalIapConfig halIapConfig) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void debug(NativeHandle nativeHandle, ArrayList<String> arrayList) throws RemoteException;

    int destroy(long j) throws RemoteException;

    int disconnect(long j) throws RemoteException;

    int endCall(long j, byte b, String str) throws RemoteException;

    int getArtworkSize(long j, int i) throws RemoteException;

    ArrayList<Byte> getCertificate() throws RemoteException;

    ArrayList<Byte> getChallengeResponse(ArrayList<Byte> arrayList) throws RemoteException;

    void getCurrentTrackPid(long j, getCurrentTrackPidCallback getcurrenttrackpidcallback) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    DebugInfo getDebugInfo() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<byte[]> getHashChain() throws RemoteException;

    IapTrackInfo getIpodId3Info(long j) throws RemoteException;

    IapMediaListInfo getMediaList(long j, int i, int i2) throws RemoteException;

    IapMediaListInfo getNowPlayingList(long j, int i, int i2) throws RemoteException;

    int getNowPlayingTrackIndex(long j) throws RemoteException;

    int getNowPlayingTracksCount(long j) throws RemoteException;

    int getPlaybackStatus(long j) throws RemoteException;

    int getRepeatMode(long j) throws RemoteException;

    int getSampleRate(long j) throws RemoteException;

    ArrayList<Byte> getSerialNumber() throws RemoteException;

    int getShuffleMode(long j) throws RemoteException;

    int getSongDuration(long j) throws RemoteException;

    IapMediaListInfo getSubContentsAllMediaItems(long j, int i, int i2) throws RemoteException;

    void getTrackInfo(long j, int i, getTrackInfoCallback gettrackinfocallback) throws RemoteException;

    int holdStatusUpdate(long j, boolean z, String str) throws RemoteException;

    int identificationInfoUpdate(long j, HalIdentificationInfo halIdentificationInfo) throws RemoteException;

    int initiateCall(long j, byte b, String str, byte b2) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    ArrayList<String> interfaceChain() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    String interfaceDescriptor() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean linkToDeath(IHwBinder.DeathRecipient deathRecipient, long j) throws RemoteException;

    int mergeCalls(long j) throws RemoteException;

    int muteStatusUpdate(long j, boolean z) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void notifySyspropsChanged() throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void ping() throws RemoteException;

    int playCurrentAllSongs(long j, boolean z) throws RemoteException;

    int playMusicRadio(long j, String str) throws RemoteException;

    int playSong(long j, int i) throws RemoteException;

    int playSongs(long j, int i, ArrayList<String> arrayList) throws RemoteException;

    int pressHidKey(long j, int i) throws RemoteException;

    int registerIapCallback(long j, IIapCallback iIapCallback) throws RemoteException;

    int releaseHidKey(long j) throws RemoteException;

    int requestAppDiscoveryAppIcons(long j, String str) throws RemoteException;

    int requestAppLaunch(long j, String str, byte b) throws RemoteException;

    int resetMediaList(long j) throws RemoteException;

    int selectMediaList(long j, int i, int i2) throws RemoteException;

    int sendAccessoryWiFiConfigurationInformation(long j, HalAccessoryWiFiConfiguration halAccessoryWiFiConfiguration) throws RemoteException;

    int sendBluetoothPairingAccessoryInformation(long j, short s, ArrayList<Byte> arrayList, ArrayList<Byte> arrayList2) throws RemoteException;

    int sendBluetoothPairingStatus(long j, short s, boolean z, String str) throws RemoteException;

    int sendCarplayStartSession(long j, HalCarplayStartSessionInfo halCarplayStartSessionInfo) throws RemoteException;

    int sendDestinationInformationStatus(long j, HalDestinationInformationStatus halDestinationInformationStatus) throws RemoteException;

    int sendDtmf(long j, byte b, String str) throws RemoteException;

    int sendEapData(long j, short s, ArrayList<Byte> arrayList) throws RemoteException;

    int sendEapSessionStatus(long j, short s, byte b) throws RemoteException;

    int sendLocationInformation(long j, String str) throws RemoteException;

    int sendOobBtPairingAccessoryInformation(long j, int i) throws RemoteException;

    int sendOobBtPairingCompletionInformation(long j, byte b) throws RemoteException;

    int sendVehicleStatusInformation(long j, HalVehicleStatusInfo halVehicleStatusInfo) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    void setHALInstrumentation() throws RemoteException;

    int setSongElapsedTime(long j, int i) throws RemoteException;

    int shufflePlayCurrentAllSongs(long j, boolean z) throws RemoteException;

    int startAppDiscoveryUpdates(long j, HalCarplayAppDiscoveryInfo halCarplayAppDiscoveryInfo) throws RemoteException;

    int startBluetoothConnectionUpdates(long j) throws RemoteException;

    int startCallStateUpdates(long j) throws RemoteException;

    int startCommunicationsUpdates(long j) throws RemoteException;

    int startDestinationInformation(long j) throws RemoteException;

    int startListUpdates(long j) throws RemoteException;

    int startMediaLibraryUpdates(long j, String str) throws RemoteException;

    int startNowPlayingUpdates(long j) throws RemoteException;

    int startRouteGuidanceUpdates(long j, ArrayList<Short> arrayList, int i) throws RemoteException;

    int startUsbDeviceModeAudio(long j) throws RemoteException;

    int stopAppDiscoveryUpdates(long j) throws RemoteException;

    int stopBluetoothConnectionUpdates(long j) throws RemoteException;

    int stopCallStateUpdates(long j) throws RemoteException;

    int stopCommunicationsUpdates(long j) throws RemoteException;

    int stopDestinationInformation(long j) throws RemoteException;

    int stopListUpdates(long j) throws RemoteException;

    int stopMediaLibraryUpdates(long j) throws RemoteException;

    int stopNowPlayingUpdates(long j) throws RemoteException;

    int stopRouteGuidanceUpdates(long j, ArrayList<Short> arrayList) throws RemoteException;

    int stopUsbDeviceModeAudio(long j) throws RemoteException;

    int swapCalls(long j) throws RemoteException;

    int unRegisterIapCallback(long j, IIapCallback iIapCallback) throws RemoteException;

    @Override // android.hidl.base.V1_0.IBase
    boolean unlinkToDeath(IHwBinder.DeathRecipient deathRecipient) throws RemoteException;

    static IIap asInterface(IHwBinder binder) {
        if (binder == null) {
            return null;
        }
        IHwInterface iface = binder.queryLocalInterface(kInterfaceName);
        if (iface != null && (iface instanceof IIap)) {
            return (IIap) iface;
        }
        IIap proxy = new Proxy(binder);
        try {
            for (String descriptor : proxy.interfaceChain()) {
                if (descriptor.equals(kInterfaceName)) {
                    return proxy;
                }
            }
        } catch (RemoteException e) {
        }
        return null;
    }

    static IIap castFrom(IHwInterface iface) {
        if (iface == null) {
            return null;
        }
        return asInterface(iface.asBinder());
    }

    static IIap getService(String serviceName, boolean retry) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName, retry));
    }

    static IIap getService(boolean retry) throws RemoteException {
        return getService("default", retry);
    }

    static IIap getService(String serviceName) throws RemoteException {
        return asInterface(HwBinder.getService(kInterfaceName, serviceName));
    }

    static IIap getService() throws RemoteException {
        return getService("default");
    }

    public static final class Proxy implements IIap {
        private IHwBinder mRemote;

        public Proxy(IHwBinder remote) {
            Objects.requireNonNull(remote);
            this.mRemote = remote;
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this.mRemote;
        }

        public String toString() {
            try {
                return interfaceDescriptor() + "@Proxy";
            } catch (RemoteException e) {
                return "[class or subclass of vendor.ts.iap@1.0::IIap]@Proxy";
            }
        }

        public final boolean equals(Object other) {
            return HidlSupport.interfacesEqual(this, other);
        }

        public final int hashCode() {
            return asBinder().hashCode();
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public ArrayList<Byte> getCertificate() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(1, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<Byte> _hidl_out_cerData = _hidl_reply.readInt8Vector();
                return _hidl_out_cerData;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public ArrayList<Byte> getChallengeResponse(ArrayList<Byte> challenge) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt8Vector(challenge);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(2, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<Byte> _hidl_out_challengeResp = _hidl_reply.readInt8Vector();
                return _hidl_out_challengeResp;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public ArrayList<Byte> getSerialNumber() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(3, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<Byte> _hidl_out_serialNum = _hidl_reply.readInt8Vector();
                return _hidl_out_serialNum;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public long create(HalIapConfig config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(4, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                long _hidl_out_iapHandle = _hidl_reply.readInt64();
                return _hidl_out_iapHandle;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int destroy(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(5, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int registerIapCallback(long iapHandle, IIapCallback callback) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeStrongBinder(callback == null ? null : callback.asBinder());
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(6, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int unRegisterIapCallback(long iapHandle, IIapCallback callback) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeStrongBinder(callback == null ? null : callback.asBinder());
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(7, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int connect(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(8, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int disconnect(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(9, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startBluetoothConnectionUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(10, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopBluetoothConnectionUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(11, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startMediaLibraryUpdates(long iapHandle, String lastRevision) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeString(lastRevision);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(12, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopMediaLibraryUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(13, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int playSongs(long iapHandle, int startingIndex, ArrayList<String> songPids) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(startingIndex);
            _hidl_request.writeStringVector(songPids);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(14, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int playSong(long iapHandle, int pbIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(pbIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(15, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int setSongElapsedTime(long iapHandle, int elapsedTime) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(elapsedTime);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(16, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int playMusicRadio(long iapHandle, String radioPid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeString(radioPid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(17, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int pressHidKey(long iapHandle, int keyCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(keyCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(18, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int releaseHidKey(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(19, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startNowPlayingUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(20, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopNowPlayingUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(21, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startListUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(22, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopListUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(23, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startUsbDeviceModeAudio(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(24, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopUsbDeviceModeAudio(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(25, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startCallStateUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(26, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopCallStateUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(27, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int initiateCall(long iapHandle, byte callType, String destId, byte callServiceType) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt8(callType);
            _hidl_request.writeString(destId);
            _hidl_request.writeInt8(callServiceType);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(28, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int acceptCall(long iapHandle, byte acceptAction, String callUuid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt8(acceptAction);
            _hidl_request.writeString(callUuid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(29, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int endCall(long iapHandle, byte endAction, String callUuid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt8(endAction);
            _hidl_request.writeString(callUuid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(30, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int swapCalls(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(31, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int mergeCalls(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(32, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int holdStatusUpdate(long iapHandle, boolean holdStatus, String callUuid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeBool(holdStatus);
            _hidl_request.writeString(callUuid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(33, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int muteStatusUpdate(long iapHandle, boolean muteStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeBool(muteStatus);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(34, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendDtmf(long iapHandle, byte dtmfTone, String callUuid) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt8(dtmfTone);
            _hidl_request.writeString(callUuid);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(35, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startCommunicationsUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(36, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopCommunicationsUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(37, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendLocationInformation(long iapHandle, String nameSentence) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeString(nameSentence);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(38, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendVehicleStatusInformation(long iapHandle, HalVehicleStatusInfo vehicleStatusInfo) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            vehicleStatusInfo.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(39, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendOobBtPairingAccessoryInformation(long iapHandle, int deviceClass) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(deviceClass);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(40, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendOobBtPairingCompletionInformation(long iapHandle, byte resultCode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt8(resultCode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(41, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendAccessoryWiFiConfigurationInformation(long iapHandle, HalAccessoryWiFiConfiguration config) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            config.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(42, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startRouteGuidanceUpdates(long iapHandle, ArrayList<Short> routeGuidanDisplayComponentIDs, int parameters) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt16Vector(routeGuidanDisplayComponentIDs);
            _hidl_request.writeInt32(parameters);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(43, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopRouteGuidanceUpdates(long iapHandle, ArrayList<Short> routeGuidanDisplayComponentIDs) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt16Vector(routeGuidanDisplayComponentIDs);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(44, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startDestinationInformation(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(45, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopDestinationInformation(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(46, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendDestinationInformationStatus(long iapHandle, HalDestinationInformationStatus destinationInformationStatus) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            destinationInformationStatus.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(47, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendEapData(long iapHandle, short sessionId, ArrayList<Byte> data) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt16(sessionId);
            _hidl_request.writeInt8Vector(data);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(48, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendEapSessionStatus(long iapHandle, short sessionId, byte status) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt16(sessionId);
            _hidl_request.writeInt8(status);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(49, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int requestAppLaunch(long iapHandle, String appBundleId, byte appLaunchMethod) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeString(appBundleId);
            _hidl_request.writeInt8(appLaunchMethod);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(50, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendBluetoothPairingAccessoryInformation(long iapHandle, short btTransComId, ArrayList<Byte> pairingDataP192, ArrayList<Byte> pairingDataP256) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt16(btTransComId);
            _hidl_request.writeInt8Vector(pairingDataP192);
            _hidl_request.writeInt8Vector(pairingDataP256);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(51, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendBluetoothPairingStatus(long iapHandle, short btTransComId, boolean success, String cancelReason) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt16(btTransComId);
            _hidl_request.writeBool(success);
            _hidl_request.writeString(cancelReason);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(52, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int sendCarplayStartSession(long iapHandle, HalCarplayStartSessionInfo info) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            info.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(53, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int startAppDiscoveryUpdates(long iapHandle, HalCarplayAppDiscoveryInfo info) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            info.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(54, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int stopAppDiscoveryUpdates(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(55, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int requestAppDiscoveryAppIcons(long iapHandle, String appBundleId) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeString(appBundleId);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(56, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int identificationInfoUpdate(long iapHandle, HalIdentificationInfo info) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            info.writeToParcel(_hidl_request);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(57, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int resetMediaList(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(58, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_count = _hidl_reply.readInt32();
                return _hidl_out_count;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int selectMediaList(long iapHandle, int index, int level) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(index);
            _hidl_request.writeInt32(level);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(59, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_count = _hidl_reply.readInt32();
                return _hidl_out_count;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public IapMediaListInfo getMediaList(long iapHandle, int start, int count) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(start);
            _hidl_request.writeInt32(count);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(60, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                IapMediaListInfo _hidl_out_mediaListInfo = new IapMediaListInfo();
                _hidl_out_mediaListInfo.readFromParcel(_hidl_reply);
                return _hidl_out_mediaListInfo;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public IapMediaListInfo getSubContentsAllMediaItems(long iapHandle, int startIndex, int count) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(startIndex);
            _hidl_request.writeInt32(count);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(61, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                IapMediaListInfo _hidl_out_mediaListInfo = new IapMediaListInfo();
                _hidl_out_mediaListInfo.readFromParcel(_hidl_reply);
                return _hidl_out_mediaListInfo;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int shufflePlayCurrentAllSongs(long iapHandle, boolean bChangeMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeBool(bChangeMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(62, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int playCurrentAllSongs(long iapHandle, boolean bChangeMode) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeBool(bChangeMode);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(63, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_status = _hidl_reply.readInt32();
                return _hidl_out_status;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getPlaybackStatus(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(64, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getSongDuration(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(65, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getRepeatMode(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(66, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getShuffleMode(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(67, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getNowPlayingTrackIndex(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(68, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getNowPlayingTracksCount(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(69, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public IapMediaListInfo getNowPlayingList(long iapHandle, int start, int count) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(start);
            _hidl_request.writeInt32(count);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(70, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                IapMediaListInfo _hidl_out_mediaListInfo = new IapMediaListInfo();
                _hidl_out_mediaListInfo.readFromParcel(_hidl_reply);
                return _hidl_out_mediaListInfo;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getArtworkSize(long iapHandle, int trackIndex) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(trackIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(71, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public IapTrackInfo getIpodId3Info(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(72, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                IapTrackInfo _hidl_out_iapTrackInfo = new IapTrackInfo();
                _hidl_out_iapTrackInfo.readFromParcel(_hidl_reply);
                return _hidl_out_iapTrackInfo;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public void getTrackInfo(long iapHandle, int TrackIndex, getTrackInfoCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            _hidl_request.writeInt32(TrackIndex);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(73, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                IAP_TAG_ST_H _hidl_out_data = new IAP_TAG_ST_H();
                _hidl_out_data.readFromParcel(_hidl_reply);
                _hidl_cb.onValues(_hidl_out_result, _hidl_out_data);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public void getCurrentTrackPid(long iapHandle, getCurrentTrackPidCallback _hidl_cb) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(74, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                long _hidl_out_pid = _hidl_reply.readInt64();
                int _hidl_out_mediaType = _hidl_reply.readInt32();
                _hidl_cb.onValues(_hidl_out_result, _hidl_out_pid, _hidl_out_mediaType);
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap
        public int getSampleRate(long iapHandle) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IIap.kInterfaceName);
            _hidl_request.writeInt64(iapHandle);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(75, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                int _hidl_out_result = _hidl_reply.readInt32();
                return _hidl_out_result;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public ArrayList<String> interfaceChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256067662, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<String> _hidl_out_descriptors = _hidl_reply.readStringVector();
                return _hidl_out_descriptors;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            _hidl_request.writeNativeHandle(fd);
            _hidl_request.writeStringVector(options);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256131655, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public String interfaceDescriptor() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256136003, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                String _hidl_out_descriptor = _hidl_reply.readString();
                return _hidl_out_descriptor;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public ArrayList<byte[]> getHashChain() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256398152, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                ArrayList<byte[]> _hidl_out_hashchain = new ArrayList<>();
                HwBlob _hidl_blob = _hidl_reply.readBuffer(16L);
                int _hidl_vec_size = _hidl_blob.getInt32(8L);
                HwBlob childBlob = _hidl_reply.readEmbeddedBuffer(_hidl_vec_size * 32, _hidl_blob.handle(), 0L, true);
                _hidl_out_hashchain.clear();
                for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                    byte[] _hidl_vec_element = new byte[32];
                    long _hidl_array_offset_1 = _hidl_index_0 * 32;
                    childBlob.copyToInt8Array(_hidl_array_offset_1, _hidl_vec_element, 32);
                    _hidl_out_hashchain.add(_hidl_vec_element);
                }
                return _hidl_out_hashchain;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public void setHALInstrumentation() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256462420, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) throws RemoteException {
            return this.mRemote.linkToDeath(recipient, cookie);
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public void ping() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(256921159, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public DebugInfo getDebugInfo() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257049926, _hidl_request, _hidl_reply, 0);
                _hidl_reply.verifySuccess();
                _hidl_request.releaseTemporaryStorage();
                DebugInfo _hidl_out_info = new DebugInfo();
                _hidl_out_info.readFromParcel(_hidl_reply);
                return _hidl_out_info;
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public void notifySyspropsChanged() throws RemoteException {
            HwParcel _hidl_request = new HwParcel();
            _hidl_request.writeInterfaceToken(IBase.kInterfaceName);
            HwParcel _hidl_reply = new HwParcel();
            try {
                this.mRemote.transact(257120595, _hidl_request, _hidl_reply, 1);
                _hidl_request.releaseTemporaryStorage();
            } finally {
                _hidl_reply.release();
            }
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) throws RemoteException {
            return this.mRemote.unlinkToDeath(recipient);
        }
    }

    public static abstract class Stub extends HwBinder implements IIap {
        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public IHwBinder asBinder() {
            return this;
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final ArrayList<String> interfaceChain() {
            return new ArrayList<>(Arrays.asList(IIap.kInterfaceName, IBase.kInterfaceName));
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public void debug(NativeHandle fd, ArrayList<String> options) {
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final String interfaceDescriptor() {
            return IIap.kInterfaceName;
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final ArrayList<byte[]> getHashChain() {
            return new ArrayList<>(Arrays.asList(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-20, 127, -41, -98, -48, 45, -6, -123, -68, 73, -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, -54, 76}));
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final void setHALInstrumentation() {
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final boolean linkToDeath(IHwBinder.DeathRecipient recipient, long cookie) {
            return true;
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final void ping() {
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final DebugInfo getDebugInfo() {
            DebugInfo info = new DebugInfo();
            info.pid = HidlSupport.getPidIfSharable();
            info.ptr = 0L;
            info.arch = 0;
            return info;
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final void notifySyspropsChanged() {
            HwBinder.enableInstrumentation();
        }

        @Override // vendor.ts.iap.V1_0.IIap, android.hidl.base.V1_0.IBase
        public final boolean unlinkToDeath(IHwBinder.DeathRecipient recipient) {
            return true;
        }

        public IHwInterface queryLocalInterface(String descriptor) {
            if (IIap.kInterfaceName.equals(descriptor)) {
                return this;
            }
            return null;
        }

        public void registerAsService(String serviceName) throws RemoteException {
            registerService(serviceName);
        }

        public String toString() {
            return interfaceDescriptor() + "@Stub";
        }

        public void onTransact(int _hidl_code, HwParcel _hidl_request, final HwParcel _hidl_reply, int _hidl_flags) throws RemoteException {
            switch (_hidl_code) {
                case 1:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    ArrayList<Byte> _hidl_out_cerData = getCertificate();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt8Vector(_hidl_out_cerData);
                    _hidl_reply.send();
                    return;
                case 2:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    ArrayList<Byte> challenge = _hidl_request.readInt8Vector();
                    ArrayList<Byte> _hidl_out_challengeResp = getChallengeResponse(challenge);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt8Vector(_hidl_out_challengeResp);
                    _hidl_reply.send();
                    return;
                case 3:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    ArrayList<Byte> _hidl_out_serialNum = getSerialNumber();
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt8Vector(_hidl_out_serialNum);
                    _hidl_reply.send();
                    return;
                case 4:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    HalIapConfig config = new HalIapConfig();
                    config.readFromParcel(_hidl_request);
                    long _hidl_out_iapHandle = create(config);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt64(_hidl_out_iapHandle);
                    _hidl_reply.send();
                    return;
                case 5:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle = _hidl_request.readInt64();
                    int _hidl_out_status = destroy(iapHandle);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status);
                    _hidl_reply.send();
                    return;
                case 6:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle2 = _hidl_request.readInt64();
                    IIapCallback callback = IIapCallback.asInterface(_hidl_request.readStrongBinder());
                    int _hidl_out_status2 = registerIapCallback(iapHandle2, callback);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status2);
                    _hidl_reply.send();
                    return;
                case 7:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle3 = _hidl_request.readInt64();
                    IIapCallback callback2 = IIapCallback.asInterface(_hidl_request.readStrongBinder());
                    int _hidl_out_status3 = unRegisterIapCallback(iapHandle3, callback2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status3);
                    _hidl_reply.send();
                    return;
                case 8:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle4 = _hidl_request.readInt64();
                    int _hidl_out_status4 = connect(iapHandle4);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status4);
                    _hidl_reply.send();
                    return;
                case 9:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle5 = _hidl_request.readInt64();
                    int _hidl_out_status5 = disconnect(iapHandle5);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status5);
                    _hidl_reply.send();
                    return;
                case 10:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle6 = _hidl_request.readInt64();
                    int _hidl_out_status6 = startBluetoothConnectionUpdates(iapHandle6);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status6);
                    _hidl_reply.send();
                    return;
                case 11:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle7 = _hidl_request.readInt64();
                    int _hidl_out_status7 = stopBluetoothConnectionUpdates(iapHandle7);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status7);
                    _hidl_reply.send();
                    return;
                case 12:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle8 = _hidl_request.readInt64();
                    String lastRevision = _hidl_request.readString();
                    int _hidl_out_status8 = startMediaLibraryUpdates(iapHandle8, lastRevision);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status8);
                    _hidl_reply.send();
                    return;
                case 13:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle9 = _hidl_request.readInt64();
                    int _hidl_out_status9 = stopMediaLibraryUpdates(iapHandle9);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status9);
                    _hidl_reply.send();
                    return;
                case 14:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle10 = _hidl_request.readInt64();
                    int startingIndex = _hidl_request.readInt32();
                    ArrayList<String> songPids = _hidl_request.readStringVector();
                    int _hidl_out_status10 = playSongs(iapHandle10, startingIndex, songPids);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status10);
                    _hidl_reply.send();
                    return;
                case 15:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle11 = _hidl_request.readInt64();
                    int pbIndex = _hidl_request.readInt32();
                    int _hidl_out_status11 = playSong(iapHandle11, pbIndex);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status11);
                    _hidl_reply.send();
                    return;
                case 16:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle12 = _hidl_request.readInt64();
                    int elapsedTime = _hidl_request.readInt32();
                    int _hidl_out_status12 = setSongElapsedTime(iapHandle12, elapsedTime);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status12);
                    _hidl_reply.send();
                    return;
                case 17:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle13 = _hidl_request.readInt64();
                    String radioPid = _hidl_request.readString();
                    int _hidl_out_status13 = playMusicRadio(iapHandle13, radioPid);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status13);
                    _hidl_reply.send();
                    return;
                case 18:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle14 = _hidl_request.readInt64();
                    int keyCode = _hidl_request.readInt32();
                    int _hidl_out_status14 = pressHidKey(iapHandle14, keyCode);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status14);
                    _hidl_reply.send();
                    return;
                case 19:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle15 = _hidl_request.readInt64();
                    int _hidl_out_status15 = releaseHidKey(iapHandle15);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status15);
                    _hidl_reply.send();
                    return;
                case 20:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle16 = _hidl_request.readInt64();
                    int _hidl_out_status16 = startNowPlayingUpdates(iapHandle16);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status16);
                    _hidl_reply.send();
                    return;
                case 21:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle17 = _hidl_request.readInt64();
                    int _hidl_out_status17 = stopNowPlayingUpdates(iapHandle17);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status17);
                    _hidl_reply.send();
                    return;
                case 22:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle18 = _hidl_request.readInt64();
                    int _hidl_out_status18 = startListUpdates(iapHandle18);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status18);
                    _hidl_reply.send();
                    return;
                case 23:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle19 = _hidl_request.readInt64();
                    int _hidl_out_status19 = stopListUpdates(iapHandle19);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status19);
                    _hidl_reply.send();
                    return;
                case 24:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle20 = _hidl_request.readInt64();
                    int _hidl_out_status20 = startUsbDeviceModeAudio(iapHandle20);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status20);
                    _hidl_reply.send();
                    return;
                case 25:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle21 = _hidl_request.readInt64();
                    int _hidl_out_status21 = stopUsbDeviceModeAudio(iapHandle21);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status21);
                    _hidl_reply.send();
                    return;
                case 26:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle22 = _hidl_request.readInt64();
                    int _hidl_out_status22 = startCallStateUpdates(iapHandle22);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status22);
                    _hidl_reply.send();
                    return;
                case 27:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle23 = _hidl_request.readInt64();
                    int _hidl_out_status23 = stopCallStateUpdates(iapHandle23);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status23);
                    _hidl_reply.send();
                    return;
                case 28:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle24 = _hidl_request.readInt64();
                    byte callType = _hidl_request.readInt8();
                    String destId = _hidl_request.readString();
                    byte callServiceType = _hidl_request.readInt8();
                    int _hidl_out_status24 = initiateCall(iapHandle24, callType, destId, callServiceType);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status24);
                    _hidl_reply.send();
                    return;
                case 29:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle25 = _hidl_request.readInt64();
                    byte acceptAction = _hidl_request.readInt8();
                    String callUuid = _hidl_request.readString();
                    int _hidl_out_status25 = acceptCall(iapHandle25, acceptAction, callUuid);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status25);
                    _hidl_reply.send();
                    return;
                case 30:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle26 = _hidl_request.readInt64();
                    byte endAction = _hidl_request.readInt8();
                    String callUuid2 = _hidl_request.readString();
                    int _hidl_out_status26 = endCall(iapHandle26, endAction, callUuid2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status26);
                    _hidl_reply.send();
                    return;
                case 31:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle27 = _hidl_request.readInt64();
                    int _hidl_out_status27 = swapCalls(iapHandle27);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status27);
                    _hidl_reply.send();
                    return;
                case 32:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle28 = _hidl_request.readInt64();
                    int _hidl_out_status28 = mergeCalls(iapHandle28);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status28);
                    _hidl_reply.send();
                    return;
                case 33:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle29 = _hidl_request.readInt64();
                    boolean holdStatus = _hidl_request.readBool();
                    String callUuid3 = _hidl_request.readString();
                    int _hidl_out_status29 = holdStatusUpdate(iapHandle29, holdStatus, callUuid3);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status29);
                    _hidl_reply.send();
                    return;
                case 34:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle30 = _hidl_request.readInt64();
                    boolean muteStatus = _hidl_request.readBool();
                    int _hidl_out_status30 = muteStatusUpdate(iapHandle30, muteStatus);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status30);
                    _hidl_reply.send();
                    return;
                case 35:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle31 = _hidl_request.readInt64();
                    byte dtmfTone = _hidl_request.readInt8();
                    String callUuid4 = _hidl_request.readString();
                    int _hidl_out_status31 = sendDtmf(iapHandle31, dtmfTone, callUuid4);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status31);
                    _hidl_reply.send();
                    return;
                case 36:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle32 = _hidl_request.readInt64();
                    int _hidl_out_status32 = startCommunicationsUpdates(iapHandle32);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status32);
                    _hidl_reply.send();
                    return;
                case 37:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle33 = _hidl_request.readInt64();
                    int _hidl_out_status33 = stopCommunicationsUpdates(iapHandle33);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status33);
                    _hidl_reply.send();
                    return;
                case 38:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle34 = _hidl_request.readInt64();
                    String nameSentence = _hidl_request.readString();
                    int _hidl_out_status34 = sendLocationInformation(iapHandle34, nameSentence);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status34);
                    _hidl_reply.send();
                    return;
                case 39:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle35 = _hidl_request.readInt64();
                    HalVehicleStatusInfo vehicleStatusInfo = new HalVehicleStatusInfo();
                    vehicleStatusInfo.readFromParcel(_hidl_request);
                    int _hidl_out_status35 = sendVehicleStatusInformation(iapHandle35, vehicleStatusInfo);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status35);
                    _hidl_reply.send();
                    return;
                case 40:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle36 = _hidl_request.readInt64();
                    int deviceClass = _hidl_request.readInt32();
                    int _hidl_out_status36 = sendOobBtPairingAccessoryInformation(iapHandle36, deviceClass);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status36);
                    _hidl_reply.send();
                    return;
                case 41:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle37 = _hidl_request.readInt64();
                    byte resultCode = _hidl_request.readInt8();
                    int _hidl_out_status37 = sendOobBtPairingCompletionInformation(iapHandle37, resultCode);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status37);
                    _hidl_reply.send();
                    return;
                case 42:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle38 = _hidl_request.readInt64();
                    HalAccessoryWiFiConfiguration config2 = new HalAccessoryWiFiConfiguration();
                    config2.readFromParcel(_hidl_request);
                    int _hidl_out_status38 = sendAccessoryWiFiConfigurationInformation(iapHandle38, config2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status38);
                    _hidl_reply.send();
                    return;
                case 43:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle39 = _hidl_request.readInt64();
                    ArrayList<Short> routeGuidanDisplayComponentIDs = _hidl_request.readInt16Vector();
                    int parameters = _hidl_request.readInt32();
                    int _hidl_out_status39 = startRouteGuidanceUpdates(iapHandle39, routeGuidanDisplayComponentIDs, parameters);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status39);
                    _hidl_reply.send();
                    return;
                case 44:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle40 = _hidl_request.readInt64();
                    ArrayList<Short> routeGuidanDisplayComponentIDs2 = _hidl_request.readInt16Vector();
                    int _hidl_out_status40 = stopRouteGuidanceUpdates(iapHandle40, routeGuidanDisplayComponentIDs2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status40);
                    _hidl_reply.send();
                    return;
                case 45:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle41 = _hidl_request.readInt64();
                    int _hidl_out_status41 = startDestinationInformation(iapHandle41);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status41);
                    _hidl_reply.send();
                    return;
                case 46:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle42 = _hidl_request.readInt64();
                    int _hidl_out_status42 = stopDestinationInformation(iapHandle42);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status42);
                    _hidl_reply.send();
                    return;
                case 47:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle43 = _hidl_request.readInt64();
                    HalDestinationInformationStatus destinationInformationStatus = new HalDestinationInformationStatus();
                    destinationInformationStatus.readFromParcel(_hidl_request);
                    int _hidl_out_status43 = sendDestinationInformationStatus(iapHandle43, destinationInformationStatus);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status43);
                    _hidl_reply.send();
                    return;
                case 48:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle44 = _hidl_request.readInt64();
                    short sessionId = _hidl_request.readInt16();
                    ArrayList<Byte> data = _hidl_request.readInt8Vector();
                    int _hidl_out_status44 = sendEapData(iapHandle44, sessionId, data);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status44);
                    _hidl_reply.send();
                    return;
                case 49:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle45 = _hidl_request.readInt64();
                    short sessionId2 = _hidl_request.readInt16();
                    byte status = _hidl_request.readInt8();
                    int _hidl_out_status45 = sendEapSessionStatus(iapHandle45, sessionId2, status);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status45);
                    _hidl_reply.send();
                    return;
                case 50:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle46 = _hidl_request.readInt64();
                    String appBundleId = _hidl_request.readString();
                    byte appLaunchMethod = _hidl_request.readInt8();
                    int _hidl_out_status46 = requestAppLaunch(iapHandle46, appBundleId, appLaunchMethod);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status46);
                    _hidl_reply.send();
                    return;
                case 51:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle47 = _hidl_request.readInt64();
                    short btTransComId = _hidl_request.readInt16();
                    ArrayList<Byte> pairingDataP192 = _hidl_request.readInt8Vector();
                    ArrayList<Byte> pairingDataP256 = _hidl_request.readInt8Vector();
                    int _hidl_out_status47 = sendBluetoothPairingAccessoryInformation(iapHandle47, btTransComId, pairingDataP192, pairingDataP256);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status47);
                    _hidl_reply.send();
                    return;
                case 52:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle48 = _hidl_request.readInt64();
                    short btTransComId2 = _hidl_request.readInt16();
                    boolean success = _hidl_request.readBool();
                    String cancelReason = _hidl_request.readString();
                    int _hidl_out_status48 = sendBluetoothPairingStatus(iapHandle48, btTransComId2, success, cancelReason);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status48);
                    _hidl_reply.send();
                    return;
                case 53:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle49 = _hidl_request.readInt64();
                    HalCarplayStartSessionInfo info = new HalCarplayStartSessionInfo();
                    info.readFromParcel(_hidl_request);
                    int _hidl_out_status49 = sendCarplayStartSession(iapHandle49, info);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status49);
                    _hidl_reply.send();
                    return;
                case 54:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle50 = _hidl_request.readInt64();
                    HalCarplayAppDiscoveryInfo info2 = new HalCarplayAppDiscoveryInfo();
                    info2.readFromParcel(_hidl_request);
                    int _hidl_out_status50 = startAppDiscoveryUpdates(iapHandle50, info2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status50);
                    _hidl_reply.send();
                    return;
                case 55:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle51 = _hidl_request.readInt64();
                    int _hidl_out_status51 = stopAppDiscoveryUpdates(iapHandle51);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status51);
                    _hidl_reply.send();
                    return;
                case 56:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle52 = _hidl_request.readInt64();
                    String appBundleId2 = _hidl_request.readString();
                    int _hidl_out_status52 = requestAppDiscoveryAppIcons(iapHandle52, appBundleId2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status52);
                    _hidl_reply.send();
                    return;
                case 57:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle53 = _hidl_request.readInt64();
                    HalIdentificationInfo info3 = new HalIdentificationInfo();
                    info3.readFromParcel(_hidl_request);
                    int _hidl_out_status53 = identificationInfoUpdate(iapHandle53, info3);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status53);
                    _hidl_reply.send();
                    return;
                case 58:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle54 = _hidl_request.readInt64();
                    int _hidl_out_count = resetMediaList(iapHandle54);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_count);
                    _hidl_reply.send();
                    return;
                case 59:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle55 = _hidl_request.readInt64();
                    int index = _hidl_request.readInt32();
                    int level = _hidl_request.readInt32();
                    int _hidl_out_count2 = selectMediaList(iapHandle55, index, level);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_count2);
                    _hidl_reply.send();
                    return;
                case CarPlayConfig.DEFAULT_MAX_FPS /* 60 */:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle56 = _hidl_request.readInt64();
                    int start = _hidl_request.readInt32();
                    int count = _hidl_request.readInt32();
                    IapMediaListInfo _hidl_out_mediaListInfo = getMediaList(iapHandle56, start, count);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_mediaListInfo.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 61:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle57 = _hidl_request.readInt64();
                    int startIndex = _hidl_request.readInt32();
                    int count2 = _hidl_request.readInt32();
                    IapMediaListInfo _hidl_out_mediaListInfo2 = getSubContentsAllMediaItems(iapHandle57, startIndex, count2);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_mediaListInfo2.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 62:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle58 = _hidl_request.readInt64();
                    boolean bChangeMode = _hidl_request.readBool();
                    int _hidl_out_status54 = shufflePlayCurrentAllSongs(iapHandle58, bChangeMode);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status54);
                    _hidl_reply.send();
                    return;
                case 63:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle59 = _hidl_request.readInt64();
                    boolean bChangeMode2 = _hidl_request.readBool();
                    int _hidl_out_status55 = playCurrentAllSongs(iapHandle59, bChangeMode2);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_status55);
                    _hidl_reply.send();
                    return;
                case 64:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle60 = _hidl_request.readInt64();
                    int _hidl_out_result = getPlaybackStatus(iapHandle60);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result);
                    _hidl_reply.send();
                    return;
                case CpConst.DisconnectScenario.SCENARIO_DETECT_OOB_DEIVCE_WHILE_TURN_OFF_ACCESSORY /* 65 */:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle61 = _hidl_request.readInt64();
                    int _hidl_out_result2 = getSongDuration(iapHandle61);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result2);
                    _hidl_reply.send();
                    return;
                case 66:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle62 = _hidl_request.readInt64();
                    int _hidl_out_result3 = getRepeatMode(iapHandle62);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result3);
                    _hidl_reply.send();
                    return;
                case 67:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle63 = _hidl_request.readInt64();
                    int _hidl_out_result4 = getShuffleMode(iapHandle63);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result4);
                    _hidl_reply.send();
                    return;
                case 68:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle64 = _hidl_request.readInt64();
                    int _hidl_out_result5 = getNowPlayingTrackIndex(iapHandle64);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result5);
                    _hidl_reply.send();
                    return;
                case 69:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle65 = _hidl_request.readInt64();
                    int _hidl_out_result6 = getNowPlayingTracksCount(iapHandle65);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result6);
                    _hidl_reply.send();
                    return;
                case 70:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle66 = _hidl_request.readInt64();
                    int start2 = _hidl_request.readInt32();
                    int count3 = _hidl_request.readInt32();
                    IapMediaListInfo _hidl_out_mediaListInfo3 = getNowPlayingList(iapHandle66, start2, count3);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_mediaListInfo3.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 71:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle67 = _hidl_request.readInt64();
                    int trackIndex = _hidl_request.readInt32();
                    int _hidl_out_result7 = getArtworkSize(iapHandle67, trackIndex);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result7);
                    _hidl_reply.send();
                    return;
                case 72:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle68 = _hidl_request.readInt64();
                    IapTrackInfo _hidl_out_iapTrackInfo = getIpodId3Info(iapHandle68);
                    _hidl_reply.writeStatus(0);
                    _hidl_out_iapTrackInfo.writeToParcel(_hidl_reply);
                    _hidl_reply.send();
                    return;
                case 73:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle69 = _hidl_request.readInt64();
                    int TrackIndex = _hidl_request.readInt32();
                    getTrackInfo(iapHandle69, TrackIndex, new getTrackInfoCallback() { // from class: vendor.ts.iap.V1_0.IIap.Stub.1
                        @Override // vendor.ts.iap.V1_0.IIap.getTrackInfoCallback
                        public void onValues(int result, IAP_TAG_ST_H data2) {
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(result);
                            data2.writeToParcel(_hidl_reply);
                            _hidl_reply.send();
                        }
                    });
                    return;
                case 74:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle70 = _hidl_request.readInt64();
                    getCurrentTrackPid(iapHandle70, new getCurrentTrackPidCallback() { // from class: vendor.ts.iap.V1_0.IIap.Stub.2
                        @Override // vendor.ts.iap.V1_0.IIap.getCurrentTrackPidCallback
                        public void onValues(int result, long pid, int mediaType) {
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeInt32(result);
                            _hidl_reply.writeInt64(pid);
                            _hidl_reply.writeInt32(mediaType);
                            _hidl_reply.send();
                        }
                    });
                    return;
                case 75:
                    _hidl_request.enforceInterface(IIap.kInterfaceName);
                    long iapHandle71 = _hidl_request.readInt64();
                    int _hidl_out_result8 = getSampleRate(iapHandle71);
                    _hidl_reply.writeStatus(0);
                    _hidl_reply.writeInt32(_hidl_out_result8);
                    _hidl_reply.send();
                    return;
                default:
                    switch (_hidl_code) {
                        case 256067662:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            ArrayList<String> _hidl_out_descriptors = interfaceChain();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeStringVector(_hidl_out_descriptors);
                            _hidl_reply.send();
                            return;
                        case 256131655:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            NativeHandle fd = _hidl_request.readNativeHandle();
                            ArrayList<String> options = _hidl_request.readStringVector();
                            debug(fd, options);
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.send();
                            return;
                        case 256136003:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            String _hidl_out_descriptor = interfaceDescriptor();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.writeString(_hidl_out_descriptor);
                            _hidl_reply.send();
                            return;
                        case 256398152:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            ArrayList<byte[]> _hidl_out_hashchain = getHashChain();
                            _hidl_reply.writeStatus(0);
                            HwBlob _hidl_blob = new HwBlob(16);
                            int _hidl_vec_size = _hidl_out_hashchain.size();
                            _hidl_blob.putInt32(8L, _hidl_vec_size);
                            _hidl_blob.putBool(12L, false);
                            HwBlob childBlob = new HwBlob(_hidl_vec_size * 32);
                            for (int _hidl_index_0 = 0; _hidl_index_0 < _hidl_vec_size; _hidl_index_0++) {
                                long _hidl_array_offset_1 = _hidl_index_0 * 32;
                                byte[] _hidl_array_item_1 = _hidl_out_hashchain.get(_hidl_index_0);
                                if (_hidl_array_item_1 == null || _hidl_array_item_1.length != 32) {
                                    throw new IllegalArgumentException("Array element is not of the expected length");
                                }
                                childBlob.putInt8Array(_hidl_array_offset_1, _hidl_array_item_1);
                            }
                            _hidl_blob.putBlob(0L, childBlob);
                            _hidl_reply.writeBuffer(_hidl_blob);
                            _hidl_reply.send();
                            return;
                        case 256462420:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            setHALInstrumentation();
                            return;
                        case 256921159:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            ping();
                            _hidl_reply.writeStatus(0);
                            _hidl_reply.send();
                            return;
                        case 257049926:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            DebugInfo _hidl_out_info = getDebugInfo();
                            _hidl_reply.writeStatus(0);
                            _hidl_out_info.writeToParcel(_hidl_reply);
                            _hidl_reply.send();
                            return;
                        case 257120595:
                            _hidl_request.enforceInterface(IBase.kInterfaceName);
                            notifySyspropsChanged();
                            return;
                        default:
                            return;
                    }
            }
        }
    }
}
