package ts.car.iap.protocol;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import com.ts.car.iap.protocol.IapNotificationType;
import com.ts.car.iap.util.AutoServiceConnector4Hidl;
import com.ts.car.iap.util.IapParcel;
import com.ts.carplay.lib.common.CpConst;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import ts.car.iap.protocol.common.CallAcceptAction;
import ts.car.iap.protocol.common.CallEndAction;
import ts.car.iap.protocol.common.CallStateUpdate;
import ts.car.iap.protocol.common.CarPlayAppList;
import ts.car.iap.protocol.common.CarplayAppDiscoveryAppIcon;
import ts.car.iap.protocol.common.CarplayAppDiscoveryAppIconData;
import ts.car.iap.protocol.common.CarplayAppDiscoveryInfo;
import ts.car.iap.protocol.common.CarplayAppListUpdate;
import ts.car.iap.protocol.common.CarplayAvialability;
import ts.car.iap.protocol.common.CarplayStartSessionInfo;
import ts.car.iap.protocol.common.CommunicationsUpdate;
import ts.car.iap.protocol.common.DestinationInformation;
import ts.car.iap.protocol.common.DestinationInformationCoordinate;
import ts.car.iap.protocol.common.DestinationInformationStatus;
import ts.car.iap.protocol.common.DtmfToneType;
import ts.car.iap.protocol.common.FavoriteListInfo;
import ts.car.iap.protocol.common.IapConfig;
import ts.car.iap.protocol.common.IapSessionStatus;
import ts.car.iap.protocol.common.IdentificationInfo;
import ts.car.iap.protocol.common.InitiateCallService;
import ts.car.iap.protocol.common.InitiateCallType;
import ts.car.iap.protocol.common.LaneGuidanceInformation;
import ts.car.iap.protocol.common.LaneInformation;
import ts.car.iap.protocol.common.LaneStatus;
import ts.car.iap.protocol.common.LogEncryption;
import ts.car.iap.protocol.common.MediaItem;
import ts.car.iap.protocol.common.MediaItemInfo;
import ts.car.iap.protocol.common.MediaLibraryUpdate;
import ts.car.iap.protocol.common.MediaListInfo;
import ts.car.iap.protocol.common.MediaPlaylist;
import ts.car.iap.protocol.common.NowPlayingPbMediaItemInfo;
import ts.car.iap.protocol.common.NowPlayingUpdate;
import ts.car.iap.protocol.common.PairingState;
import ts.car.iap.protocol.common.PowerProvidingCapability;
import ts.car.iap.protocol.common.PowerUpdate;
import ts.car.iap.protocol.common.RecentListInfo;
import ts.car.iap.protocol.common.RouteGuidanceManeuverUpdate;
import ts.car.iap.protocol.common.RouteGuidanceUpdate;
import ts.car.iap.protocol.common.TrackInfo;
import ts.car.iap.protocol.common.VehicleStatusUpdate;
import ts.car.iap.protocol.common.WifiChannelType;
import ts.car.iap.protocol.common.WifiSecurityType;
import vendor.ts.iap.V1_0.EapInfo;
import vendor.ts.iap.V1_0.HalAccessoryWiFiConfiguration;
import vendor.ts.iap.V1_0.HalCarplayAppDiscoveryInfo;
import vendor.ts.iap.V1_0.HalCarplayStartSessionInfo;
import vendor.ts.iap.V1_0.HalDestinationInformationStatus;
import vendor.ts.iap.V1_0.HalIapConfig;
import vendor.ts.iap.V1_0.HalIdentificationInfo;
import vendor.ts.iap.V1_0.HalVehicleStatusInfo;
import vendor.ts.iap.V1_0.HalWiredAttribute;
import vendor.ts.iap.V1_0.HalWirelessAttribute;
import vendor.ts.iap.V1_0.IIap;
import vendor.ts.iap.V1_0.IIapCallback;
import vendor.ts.iap.V1_0.IapMediaItemInfo;
import vendor.ts.iap.V1_0.IapMediaListInfo;
import vendor.ts.iap.V1_0.IapTrackInfo;
import vendor.ts.iap.V1_0.RouteGuidanceInfo;

/* JADX INFO: loaded from: classes.dex */
public class IapController extends AutoServiceConnector4Hidl<IIap> {
    private static final int BYTE_VALUE_MASK = 255;
    private static final int PAIRING_DATA_VALUE_LENGTH = 16;
    private static final int SERVICE_AVAILABLE = 1;
    private static final int SERVICE_NOT_AVAILABLE = 0;
    private byte[] mCertificate;
    private Context mContext;
    private IapServiceListener mServiceListener;
    private static final String TAG = IapController.class.getSimpleName();
    private static IapController mIapController = null;
    private static IIap mIIap = null;
    private LongSparseArray<IapSession> mSessions = new LongSparseArray<>();
    private LongSparseArray<IapCallback> mIapCallbacks = new LongSparseArray<>();
    private ReadWriteLock mSessionsLock = new ReentrantReadWriteLock();
    private ReadWriteLock mCallbackLock = new ReentrantReadWriteLock();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ts.car.iap.util.AutoServiceConnector4Hidl
    public IIap createService() {
        try {
            IIap service = IIap.getService();
            if (service == null) {
                Log.e(TAG, "createService IIap.getService() failed.");
            }
            return service;
        } catch (RemoteException ex) {
            Log.e(TAG, "createService IIap.getService() RemoteException " + ex.toString());
            return null;
        } catch (Exception exp) {
            Log.e(TAG, "createService IIap.getService() exception " + exp.toString());
            return null;
        }
    }

    @Override // com.ts.car.iap.util.AutoServiceConnector4Hidl
    protected void onServiceConnected() {
        Log.i(TAG, "onServiceConnected IIap HAL service connected.");
        mIIap = getService();
        IapServiceListener iapServiceListener = this.mServiceListener;
        if (iapServiceListener != null) {
            iapServiceListener.onServiceStatusChanged(1);
        }
    }

    @Override // com.ts.car.iap.util.AutoServiceConnector4Hidl
    protected void onServiceDisconnected() {
        Log.i(TAG, "onServiceDisconnected IIap HAL service disconnected.");
        mIIap = null;
        IapServiceListener iapServiceListener = this.mServiceListener;
        if (iapServiceListener != null) {
            iapServiceListener.onServiceStatusChanged(0);
        }
        this.mSessionsLock.writeLock().lock();
        try {
            this.mSessions.clear();
            this.mSessionsLock.writeLock().unlock();
            this.mCallbackLock.writeLock().lock();
            try {
                this.mIapCallbacks.clear();
            } finally {
                this.mCallbackLock.writeLock().unlock();
            }
        } catch (Throwable th) {
            this.mSessionsLock.writeLock().unlock();
            throw th;
        }
    }

    private boolean registerCallback(long handle) {
        if (mIIap == null) {
            Log.e(TAG, "registerCallback: mIIap is null");
            return false;
        }
        IapCallback callback = new IapCallback();
        try {
            Log.e(TAG, "registerIapCallback -- callback =  " + System.identityHashCode(callback));
            int status = mIIap.registerIapCallback(handle, callback);
            if (status != 0) {
                Log.e(TAG, "registerIapCallback fail!");
                return false;
            }
            this.mCallbackLock.writeLock().lock();
            try {
                try {
                    this.mIapCallbacks.put(handle, callback);
                    this.mCallbackLock.writeLock().unlock();
                    Log.e(TAG, "mIapCallbacks list = " + this.mIapCallbacks.size());
                    return true;
                } catch (Exception exp) {
                    Log.e(TAG, "Failed to add callback to callbacks list!");
                    Log.e(TAG, "Exception: " + exp.toString());
                    this.mCallbackLock.writeLock().unlock();
                    return false;
                }
            } catch (Throwable th) {
                this.mCallbackLock.writeLock().unlock();
                throw th;
            }
        } catch (RemoteException ex) {
            Log.e(TAG, "registerIapCallback fail: RemoteException " + ex.toString());
            return false;
        }
    }

    private boolean unRegisterCallback(long handle) {
        if (mIIap == null) {
            Log.e(TAG, "unRegisterCallback: mIIap is null");
            return false;
        }
        Log.e(TAG, "mIapCallbacks list = " + this.mIapCallbacks.size());
        this.mCallbackLock.readLock().lock();
        try {
            IapCallback callback = this.mIapCallbacks.get(handle);
            if (callback == null) {
                Log.e(TAG, "unRegisterCallback: callback is null");
                return false;
            }
            this.mCallbackLock.readLock().unlock();
            try {
                Log.e(TAG, "unRegisterIapCallback -- callback =  " + System.identityHashCode(callback));
                int status = mIIap.unRegisterIapCallback(handle, callback);
                if (status != 0) {
                    Log.e(TAG, "unRegisterIapCallback fail!");
                    return false;
                }
                this.mCallbackLock.writeLock().lock();
                try {
                    try {
                        this.mIapCallbacks.delete(handle);
                        this.mCallbackLock.writeLock().unlock();
                        return true;
                    } catch (Exception exp) {
                        Log.e(TAG, "Failed to delete callback from callbacks list!");
                        Log.e(TAG, "Exception: " + exp.toString());
                        this.mCallbackLock.writeLock().unlock();
                        return false;
                    }
                } catch (Throwable th) {
                    this.mCallbackLock.writeLock().unlock();
                    throw th;
                }
            } catch (RemoteException ex) {
                Log.e(TAG, "unRegisterIapCallback fail: RemoteException " + ex.toString());
                return false;
            }
        } catch (Exception exp2) {
            Log.e(TAG, "Failed to get callback from callbacks list!");
            Log.e(TAG, "Exception: " + exp2.toString());
            return false;
        } finally {
            this.mCallbackLock.readLock().unlock();
        }
    }

    public static synchronized IapController getInstance(Context context) {
        return getInstance(context, null);
    }

    public static synchronized IapController getInstance(Context context, IapServiceListener listener) {
        if (mIapController == null) {
            if (context == null) {
                Log.e(TAG, "failed to get instance of IapController with null context!");
                return null;
            }
            mIapController = new IapController(context, listener);
        }
        return mIapController;
    }

    private IapController(Context context, IapServiceListener listener) {
        this.mContext = null;
        this.mServiceListener = null;
        this.mContext = context;
        this.mServiceListener = listener;
    }

    public byte[] getCertificate() throws RemoteException {
        if (mIIap == null) {
            Log.e(TAG, "getCertificate: mIIap is null");
            return null;
        }
        if (this.mCertificate == null) {
            ArrayList<Byte> certificateList = new ArrayList<>();
            try {
                certificateList = mIIap.getCertificate();
            } catch (RemoteException ex) {
                Log.e(TAG, "getCertificate: mIIap.getCertificate: ", ex);
            }
            byte[] bArrByteArrayFromArrayList = byteArrayFromArrayList(certificateList);
            this.mCertificate = bArrByteArrayFromArrayList;
            if (bArrByteArrayFromArrayList == null) {
                Log.e(TAG, "Failed to get certificate from accessory!");
                return null;
            }
        }
        Log.d(TAG, "certificate data length: " + this.mCertificate.length);
        return (byte[]) this.mCertificate.clone();
    }

    public byte[] getChallengeResponse(byte[] challenge) throws RemoteException {
        if (mIIap == null) {
            Log.e(TAG, "getChallengeResponse: mIIap is null");
            return null;
        }
        if (challenge == null) {
            Log.e(TAG, "Failed to get challenge response for null challenge!");
            return null;
        }
        Log.d(TAG, "challenge length: " + challenge.length);
        ArrayList<Byte> challangeList = byteArrayToArrayList(challenge);
        ArrayList<Byte> responseList = new ArrayList<>();
        try {
            responseList = mIIap.getChallengeResponse(challangeList);
        } catch (RemoteException ex) {
            Log.e(TAG, "getChallengeResponse: mIIap.getChallengeResponse: ", ex);
        }
        byte[] response = byteArrayFromArrayList(responseList);
        if (response == null) {
            Log.e(TAG, "Failed to get challenge response!");
        } else {
            Log.d(TAG, "challenge response length: " + response.length);
        }
        return response;
    }

    public IapSession createSession(IapConfig config, IapListener listener) throws RemoteException {
        Log.i(TAG, "createSession config=" + config + " listener=" + listener);
        if (mIIap == null) {
            Log.e(TAG, "createSession: mIIap is null");
            return null;
        }
        if (config == null || listener == null) {
            Log.e(TAG, "Failed to create iap session for null parameter!");
            return null;
        }
        Log.i(TAG, "createSession config=" + config.toString());
        if (!config.checkValidity()) {
            Log.e(TAG, "Failed to create iap session for invalid IapConfig!");
            return null;
        }
        HalIapConfig halIapconfig = constructHalIapConfig(config);
        if (halIapconfig == null) {
            Log.e(TAG, "Failed to construct hal Iapconfig!");
            return null;
        }
        long handle = -1;
        try {
            handle = mIIap.create(halIapconfig);
        } catch (RemoteException ex) {
            Log.e(TAG, "createSession: mIIap.create: ", ex);
        }
        if (handle == 0) {
            Log.e(TAG, "Failed to create iap session for native create failure!");
            return null;
        }
        IapSession session = new IapSession(config, listener);
        session.setNativeHandle(handle);
        this.mSessionsLock.writeLock().lock();
        try {
            try {
                this.mSessions.put(handle, session);
                this.mSessionsLock.writeLock().unlock();
                if (!registerCallback(session.getNativeHandle())) {
                    Log.e(TAG, "registerCallback fail");
                    return null;
                }
                Log.i(TAG, "new iap session " + session + " created. hal handle = " + handle);
                return session;
            } catch (Exception exp) {
                Log.e(TAG, "Failed to add session handle to sessions list!");
                Log.e(TAG, "Exception: " + exp.toString());
                try {
                    mIIap.destroy(handle);
                } catch (RemoteException ex2) {
                    Log.e(TAG, "createSession: mIIap.destroy: ", ex2);
                }
                this.mSessionsLock.writeLock().unlock();
                return null;
            }
        } catch (Throwable th) {
            this.mSessionsLock.writeLock().unlock();
            throw th;
        }
    }

    public int destroySession(IapSession session) throws RemoteException {
        if (mIIap == null) {
            Log.e(TAG, "destroySession: mIIap is null");
            return -1;
        }
        if (session == null) {
            Log.e(TAG, "Failed to destroy iap session for null session handle!");
            return -1;
        }
        this.mSessionsLock.writeLock().lock();
        try {
            if (this.mSessions.get(session.getNativeHandle()) == null) {
                Log.e(TAG, "Failed to destroy iap session for invalid session handle!");
                return -1;
            }
            this.mSessions.delete(session.getNativeHandle());
            this.mSessionsLock.writeLock().unlock();
            if (unRegisterCallback(session.getNativeHandle())) {
                Log.d(TAG, "unRegisterCallback succeed");
            }
            int ret = -1;
            try {
                ret = mIIap.destroy(session.getNativeHandle());
            } catch (RemoteException ex) {
                Log.e(TAG, "destroySession: mIIap.destroy: ", ex);
            }
            Log.i(TAG, "native iap destroyed, native handle = " + session.getNativeHandle() + " ret = " + ret);
            session.setNativeHandle(-1L);
            return 0;
        } catch (Exception ex2) {
            Log.e(TAG, "Failed to remove session handle from sessions list!");
            Log.e(TAG, "Exception: " + ex2.toString());
            return -1;
        } finally {
            this.mSessionsLock.writeLock().unlock();
        }
    }

    public static class IapSession {
        private String mBtTransportIdentifier;
        private CallStateUpdate mCallStateUpdate;
        private CarplayAvialability mCarplayAvialability;
        private CommunicationsUpdate mCommunicationsUpdate;
        private IapConfig mConfig;
        private DestinationInformation mDestinationInformation;
        private String mDeviceName;
        private String mDeviceUuid;
        private boolean mIsSetElapsedTimeAvailable;
        private LaneGuidanceInformation mLaneGuidanceInformation;
        private IapListener mListener;
        private boolean mLocationInformationStarted;
        private RouteGuidanceManeuverUpdate mManeuverUpdate;
        private MediaLibraryUpdate mMediaLibraryUpdate;
        private NowPlayingUpdate mNowPlayingUpdate;
        private PowerUpdate mPowerUpdate;
        private RouteGuidanceUpdate mRouteGuidanceUpdate;
        private String mUsbTransportIdentifier;
        private boolean mWirelessCarPlayAvailable;
        private long mNativeHandle = -1;
        private IapSessionStatus mSessionStatus = IapSessionStatus.IapSessionUnknownStatus;
        private int mConnectedBtProfiles = -1;
        private int mSampleRate = -1;
        private int mSentenceType = -1;
        private boolean mVehicleStatusUpdateStarted = false;
        private int mStartVehicleStatusUpdateType = -1;
        private List<Integer> mActiveEapSessions = new ArrayList();

        IapSession(IapConfig config, IapListener listener) {
            this.mConfig = config;
            this.mListener = listener;
        }

        public String getDeviceId() {
            return this.mConfig.getDeviceId();
        }

        long getNativeHandle() {
            return this.mNativeHandle;
        }

        void setNativeHandle(long handle) {
            this.mNativeHandle = handle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int onIapNotification(int feature, int type, byte[] data) {
            IapParcel parcel = new IapParcel(data);
            switch (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(feature).ordinal()]) {
                case 1:
                    int status = parcel.readInt();
                    Log.i(IapController.TAG, "onIapNotification session status=" + status);
                    IapSessionStatus iapSessionStatusFromStatusValue = IapSessionStatus.fromStatusValue(status);
                    this.mSessionStatus = iapSessionStatusFromStatusValue;
                    this.mListener.onIapSessionStatusChanged(this, iapSessionStatusFromStatusValue);
                    return 0;
                case 2:
                    int ret = onDeviceNotification(type, parcel);
                    return ret;
                case 3:
                    int ret2 = onDevicePowerNotification(type, parcel);
                    return ret2;
                case 4:
                    this.mConnectedBtProfiles = parcel.readInt();
                    Log.e(IapController.TAG, "onIapNotification ConnectedBtProfiles=" + Integer.toHexString(this.mConnectedBtProfiles));
                    this.mListener.onBluetoothConnectionUpdate(this, this.mConnectedBtProfiles);
                    return 0;
                case 5:
                    int ret3 = onMediaLibraryNotification(type, parcel);
                    return ret3;
                case 6:
                    int ret4 = onNowPlayingNotification(type, parcel);
                    return ret4;
                case 7:
                    this.mSampleRate = parcel.readInt();
                    Log.i(IapController.TAG, "onIapNotification SampleRate=" + this.mSampleRate);
                    this.mListener.onUsbDeviceModeAudioInformation(this, this.mSampleRate);
                    return 0;
                case 8:
                    int ret5 = onCallStateNotification(type, parcel);
                    return ret5;
                case 9:
                    int ret6 = onListUpdateNotification(type, parcel);
                    return ret6;
                case 10:
                    int ret7 = onNowPlayingPbNotification(type, parcel);
                    return ret7;
                case 11:
                    int ret8 = onCommunicationsNotification(type, parcel);
                    return ret8;
                case 12:
                    int ret9 = onLocationInformationNotification(type, parcel);
                    return ret9;
                case 13:
                    int ret10 = onGprmcDataStatusNotification(type, parcel);
                    return ret10;
                case 14:
                    int ret11 = onOobBtPairingNotification(type, parcel);
                    return ret11;
                case 15:
                    Log.i(IapController.TAG, "onIapNotification RequestAccessoryWiFiConfigurationInformation");
                    this.mListener.onRequestAccessoryWiFiConfigurationInformation(this);
                    return 0;
                case 16:
                    int ret12 = onRouteGuidanceNotification(type, parcel);
                    return ret12;
                case 17:
                    int ret13 = onEapNotification(type, parcel);
                    return ret13;
                case 18:
                    int ret14 = onOobBluetoothPairingNotification(type, parcel);
                    return ret14;
                case 19:
                    int ret15 = onCarplayConnectionNotification(type, parcel);
                    return ret15;
                case 20:
                    int ret16 = onCarplayAppDiscovery(type, parcel);
                    return ret16;
                case 21:
                    int ret17 = onDestinationInformation(type, parcel);
                    return ret17;
                case 22:
                    int ret18 = onVehicleStatusNotification(type, parcel);
                    return ret18;
                default:
                    Log.e(IapController.TAG, "onIapNotification failed for unknown feature!");
                    return -1;
            }
        }

        private int onDeviceNotification(int type, IapParcel data) {
            long secondsSinceReferenceDate;
            int timeZoneOffsetMinutes;
            int daylightSavingsOffsetMinutes;
            Log.d(IapController.TAG, "onDeviceNotification type=" + type);
            int ret = 0;
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeDeviceName:
                    this.mDeviceName = data.readString();
                    Log.i(IapController.TAG, "onDeviceNotification DeviceName=" + LogEncryption.maskString(this.mDeviceName, 2, 2));
                    this.mListener.onDeviceInformationUpdate(this, this.mDeviceName);
                    break;
                case IapNotifyTypeDeviceUuid:
                    this.mDeviceUuid = data.readString();
                    Log.i(IapController.TAG, "onDeviceNotification DeviceUuid=" + LogEncryption.maskString(this.mDeviceUuid, 4, 4));
                    this.mListener.onDeviceUuidUpdate(this, this.mDeviceUuid);
                    break;
                case IapNotifyTypeWCPAvailable:
                    this.mWirelessCarPlayAvailable = data.readInt() == 1;
                    Log.i(IapController.TAG, "onDeviceNotification WirelessCarPlayAvailable=" + this.mWirelessCarPlayAvailable);
                    this.mListener.onWirelessCarPlayUpdate(this, this.mWirelessCarPlayAvailable);
                    break;
                case IapNotifyTypeDeviceBtTxId:
                    this.mBtTransportIdentifier = data.readString();
                    Log.i(IapController.TAG, "onDeviceNotification BtTransportIdentifier=" + LogEncryption.maskString(this.mBtTransportIdentifier, 2, 2));
                    break;
                case IapNotifyTypeDeviceUsbTxId:
                    this.mUsbTransportIdentifier = data.readString();
                    Log.i(IapController.TAG, "onDeviceNotification UsbTransportIdentifier=" + LogEncryption.maskString(this.mUsbTransportIdentifier, 4, 4));
                    break;
                case IapNotifyTypeMessageParsed:
                    Log.i(IapController.TAG, "onDeviceNotification report device transport identifier");
                    this.mListener.onDeviceTransportIdentifierNotification(this, this.mBtTransportIdentifier, this.mUsbTransportIdentifier);
                    break;
                case IapNotifyTypeDeviceTimeUpdate:
                    int secondsValid = data.readInt();
                    if (1 != secondsValid) {
                        secondsSinceReferenceDate = 0;
                    } else {
                        long secondsSinceReferenceDate2 = data.readLong();
                        Log.i(IapController.TAG, "onDeviceNotification SecondsSinceReferenceDate=" + secondsSinceReferenceDate2);
                        secondsSinceReferenceDate = secondsSinceReferenceDate2;
                    }
                    int timeZoneValid = data.readInt();
                    if (1 != timeZoneValid) {
                        timeZoneOffsetMinutes = 0;
                    } else {
                        int timeZoneOffsetMinutes2 = data.readInt();
                        Log.i(IapController.TAG, "onDeviceNotification TimeZoneOffsetMinutes=" + timeZoneOffsetMinutes2);
                        timeZoneOffsetMinutes = timeZoneOffsetMinutes2;
                    }
                    int daylightSavingsValid = data.readInt();
                    if (1 != daylightSavingsValid) {
                        daylightSavingsOffsetMinutes = 0;
                    } else {
                        int daylightSavingsOffsetMinutes2 = data.readInt();
                        Log.i(IapController.TAG, "onDeviceNotification DaylightSavingsOffsetMinutes=" + daylightSavingsOffsetMinutes2);
                        daylightSavingsOffsetMinutes = daylightSavingsOffsetMinutes2;
                    }
                    Log.i(IapController.TAG, "onDeviceNotification report DeviceTimeUpdate");
                    this.mListener.onDeviceTimeUpdate(this, secondsSinceReferenceDate, timeZoneOffsetMinutes, daylightSavingsOffsetMinutes);
                    break;
                default:
                    Log.e(IapController.TAG, "onDeviceNotification failed for unknown type!");
                    ret = -1;
                    break;
            }
            Log.d(IapController.TAG, "onDeviceNotification ret=" + ret);
            return ret;
        }

        private int onDevicePowerNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onDevicePowerNotification type=" + type);
            int ret = 0;
            if (this.mPowerUpdate == null) {
                this.mPowerUpdate = new PowerUpdate();
            }
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeMessageParsed:
                    Log.i(IapController.TAG, "onDevicePowerNotification report PowerUpdate");
                    this.mListener.onPowerUpdate(this, this.mPowerUpdate);
                    this.mPowerUpdate = null;
                    break;
                case IapNotifyTypeDeviceTimeUpdate:
                default:
                    Log.e(IapController.TAG, "onDevicePowerNotification failed for unknown type!");
                    ret = -1;
                    break;
                case IapNotifyTypeMaxDrawnCurrent:
                    int current = data.readInt();
                    Log.i(IapController.TAG, "onDevicePowerNotification MaxDrawnCurrent=" + current);
                    this.mPowerUpdate.setMaximumCurrentDrawnFromAccessory(current);
                    break;
                case IapNotifyTypeIfDeviceCharge:
                    int ifCharge = data.readInt();
                    Log.i(IapController.TAG, "onDevicePowerNotification willDeviceCharge=" + ifCharge);
                    this.mPowerUpdate.setDeviceBatteryWillChargeIfPowerIsPresent(ifCharge);
                    break;
                case IapNotifyTypeIsChargerConnected:
                    int isConnected = data.readInt();
                    Log.i(IapController.TAG, "onDevicePowerNotification isChargerConnected=" + isConnected);
                    this.mPowerUpdate.setIsExternalChargerConnected(isConnected);
                    break;
                case IapNotifyTypeChargingState:
                    int state = data.readInt();
                    Log.i(IapController.TAG, "onDevicePowerNotification chargingState=" + state);
                    this.mPowerUpdate.setBatteryChargingState(state);
                    break;
                case IapNotifyTypeChargeLevel:
                    int level = data.readInt();
                    Log.i(IapController.TAG, "onDevicePowerNotification chargeLevel=" + level);
                    this.mPowerUpdate.setBatteryChargeLevel(level);
                    break;
            }
            Log.d(IapController.TAG, "onDevicePowerNotification ret=" + ret);
            return ret;
        }

        private int onMediaLibraryNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onMediaLibraryNotification type=" + type);
            int ret = 0;
            if (type != IapNotificationType.IapNotifyTypeMediaPlaylistContent.getTypeValue() && this.mMediaLibraryUpdate == null) {
                this.mMediaLibraryUpdate = new MediaLibraryUpdate();
            }
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeMediaLibraryReset:
                    Log.i(IapController.TAG, "onMediaLibraryNotification media library reset!");
                    this.mMediaLibraryUpdate.setMediaLibReset(1);
                    break;
                case IapNotifyTypeMediaItemAdd:
                    MediaItem item = new MediaItem();
                    item.setPid(data.readString());
                    item.setTitle(data.readString());
                    item.setMediaType(data.readInt());
                    item.setAlbumPid(data.readString());
                    item.setAlbumTitle(data.readString());
                    item.setAlbumTrackNum(data.readInt());
                    item.setArtistPid(data.readString());
                    item.setArtist(data.readString());
                    item.setGenrePid(data.readString());
                    item.setGenre(data.readString());
                    item.setComposerPid(data.readString());
                    item.setComposer(data.readString());
                    this.mMediaLibraryUpdate.addMediaItem(item);
                    break;
                case IapNotifyTypeMediaItemDelete:
                    this.mMediaLibraryUpdate.addMediaItemDeletePid(data.readString());
                    break;
                case IapNotifyTypeMediaPlaylistAdd:
                    MediaPlaylist playlist = new MediaPlaylist();
                    playlist.setPid(data.readString());
                    playlist.setName(data.readString());
                    playlist.setIsRadio(data.readInt());
                    playlist.setParentPid(data.readString());
                    playlist.setIsFold(data.readInt());
                    playlist.setIsGeniusMix(data.readInt());
                    this.mMediaLibraryUpdate.addMediaPlaylist(playlist);
                    break;
                case IapNotifyTypeMediaPlaylistDelete:
                    this.mMediaLibraryUpdate.addMediaPlaylistDeletePid(data.readString());
                    break;
                case IapNotifyTypeMediaLibraryInfo:
                    this.mMediaLibraryUpdate.setMediaLibName(data.readString());
                    this.mMediaLibraryUpdate.setMediaLibUid(data.readString());
                    this.mMediaLibraryUpdate.setMediaLibRevision(data.readString());
                    this.mMediaLibraryUpdate.setMediaLibType(data.readInt());
                    this.mMediaLibraryUpdate.setMediaLibUpdateProgress(data.readInt());
                    Log.i(IapController.TAG, "onMediaLibraryNotification report MediaLibraryUpdate");
                    this.mListener.onMediaLibraryUpdate(this, this.mMediaLibraryUpdate);
                    this.mMediaLibraryUpdate = null;
                    break;
                case IapNotifyTypeMediaPlaylistContent:
                    this.mListener.onMediaPlaylistUpdate(this, data.readString(), data.createStringArray());
                    break;
                default:
                    Log.e(IapController.TAG, "onMediaLibraryNotification failed for unknown type!");
                    ret = -1;
                    break;
            }
            Log.d(IapController.TAG, "onMediaLibraryNotification ret=" + ret);
            return ret;
        }

        private int onNowPlayingNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onNowPlayingNotification type=" + type);
            if (type != IapNotificationType.IapNotifyTypePlaybackList.getTypeValue() && type != IapNotificationType.IapNotifyTypeArtwork.getTypeValue() && this.mNowPlayingUpdate == null) {
                this.mNowPlayingUpdate = new NowPlayingUpdate();
            }
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypePlaybackList:
                    boolean isAvailable = data.readInt() == 1;
                    String[] list = data.createStringArray();
                    String str = IapController.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onNowPlayingNotification playback list update, isAvailable=");
                    sb.append(isAvailable);
                    sb.append(" count=");
                    sb.append(list != null ? list.length : 0);
                    Log.v(str, sb.toString());
                    this.mListener.onPlaybackListUpdate(this, isAvailable, list);
                    return 0;
                case IapNotifyTypeArtwork:
                    Log.v(IapController.TAG, "onNowPlayingNotification artwork update");
                    int artworkSize = data.readInt();
                    if (artworkSize == 0) {
                        Log.v(IapController.TAG, "artwork size = " + artworkSize);
                        this.mListener.onArtworkInfoUpdate(this, artworkSize, null);
                        return 0;
                    }
                    byte[] artworkData = data.createByteArray();
                    this.mListener.onArtworkInfoUpdate(this, artworkSize, artworkData);
                    return 0;
                case IapNotifyTypeNowPlayingUpdate:
                    parseNowPlayingId3Info(this.mNowPlayingUpdate, data);
                    Log.i(IapController.TAG, "onNowPlayingNotification report NowPlayingUpdate");
                    this.mListener.onNowPlayingUpdate(this, this.mNowPlayingUpdate);
                    this.mNowPlayingUpdate = null;
                    return 0;
                default:
                    Log.e(IapController.TAG, "onNowPlayingNotification failed for unknown type!");
                    return -1;
            }
        }

        private void parseNowPlayingId3Info(NowPlayingUpdate nowPlayingUpdate, IapParcel data) {
            int pidValid = data.readInt();
            if (1 == pidValid) {
                String pid = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification pid=" + LogEncryption.maskString(pid, 2, 2));
                nowPlayingUpdate.setPid(pid);
            }
            int titleValid = data.readInt();
            if (1 == titleValid) {
                String title = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification title=" + LogEncryption.maskString(title, 2, 2));
                nowPlayingUpdate.setTitle(title);
            }
            int albumTitleValid = data.readInt();
            if (1 == albumTitleValid) {
                String albumTitle = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification albumTitle=" + LogEncryption.maskString(albumTitle, 2, 2));
                nowPlayingUpdate.setAlbumTitle(albumTitle);
            }
            int artistValid = data.readInt();
            if (1 == artistValid) {
                String artist = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification artist=" + LogEncryption.maskString(artist, 3, 0));
                nowPlayingUpdate.setArtist(artist);
            }
            int genreValid = data.readInt();
            if (1 == genreValid) {
                String genre = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification genre=" + LogEncryption.maskString(genre, 2, 2));
                nowPlayingUpdate.setGenre(genre);
            }
            int composerValid = data.readInt();
            if (1 == composerValid) {
                String composer = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification composer=" + LogEncryption.maskString(composer, 2, 2));
                nowPlayingUpdate.setComposer(composer);
            }
            int durationValid = data.readInt();
            if (1 == durationValid) {
                int duration = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification durationMs=" + LogEncryption.maskInt(duration));
                nowPlayingUpdate.setPlaybackDurationMs((long) duration);
            }
            int chapterCountValid = data.readInt();
            if (1 == chapterCountValid) {
                int chapterCount = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification chapterCount=" + chapterCount);
                nowPlayingUpdate.setChapterCount(chapterCount);
            }
            int statusValid = data.readInt();
            if (1 == statusValid) {
                int status = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification playStatus=" + status);
                nowPlayingUpdate.setPlaybackStatus(status);
            }
            int elapTimeValid = data.readInt();
            if (1 == elapTimeValid) {
                int time = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification elapsedTime=" + time);
                nowPlayingUpdate.setPlaybackElapsedTimeMs((long) time);
            }
            int queueIndexValid = data.readInt();
            if (1 == queueIndexValid) {
                int index = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification queueIndex=" + index);
                nowPlayingUpdate.setPlaybackQueueIndex((long) index);
            }
            int queueCountValid = data.readInt();
            if (1 == queueCountValid) {
                int count = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification queueCount=" + count);
                nowPlayingUpdate.setPlaybackQueueCount((long) count);
            }
            int chapterIndexValid = data.readInt();
            if (1 == chapterIndexValid) {
                int chapterIndex = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification chapterIndex=" + chapterIndex);
                nowPlayingUpdate.setPlaybackQueueChapterIndex((long) chapterIndex);
            }
            int shuffleModeValid = data.readInt();
            if (1 == shuffleModeValid) {
                int shuffle = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification shuffle=" + shuffle);
                nowPlayingUpdate.setPlaybackShuffleMode(shuffle);
            }
            int repeatModeValid = data.readInt();
            if (1 == repeatModeValid) {
                int repeat = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification repeat=" + repeat);
                nowPlayingUpdate.setPlaybackRepeatMode(repeat);
            }
            int playbackAppNameValid = data.readInt();
            if (1 == playbackAppNameValid) {
                String appName = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification PlaybackAppName=" + LogEncryption.maskString(appName, 2, 2));
                nowPlayingUpdate.setPlaybackAppName(appName);
            }
            int playbackAppBundleIDValid = data.readInt();
            if (1 == playbackAppBundleIDValid) {
                String appBundleID = data.readString();
                Log.v(IapController.TAG, "onNowPlayingNotification PlaybackAppBundleID=" + LogEncryption.maskString(appBundleID, 3, 2));
                nowPlayingUpdate.setPlaybackAppBundleID(appBundleID);
            }
            int availableModeValid = data.readInt();
            if (1 == availableModeValid) {
                int available = data.readInt();
                Log.v(IapController.TAG, "onNowPlayingNotification setElapsedTimeAvail=" + available);
                nowPlayingUpdate.setIsSetElapsedTimeAvailable(available);
                this.mIsSetElapsedTimeAvailable = available == 1;
            }
        }

        private MediaListInfo covertIapToMediaListInfo(IapMediaListInfo iapMediaListInfo) {
            if (iapMediaListInfo == null) {
                Log.e(IapController.TAG, "covertIapToMediaListInfo iapMediaListInfo is null");
                return null;
            }
            Log.v(IapController.TAG, "covertIapToMediaListInfo: Size=" + iapMediaListInfo.size + ", TotalCount=" + iapMediaListInfo.totalnum + ", StartIndex=" + iapMediaListInfo.start + ", EndIndex=" + iapMediaListInfo.end + ", Level=" + iapMediaListInfo.level + ", SubCount=" + iapMediaListInfo.subcontents + ", CategoryType=" + iapMediaListInfo.categorytype);
            ArrayList<MediaItemInfo> tepMediaItemInfo = new ArrayList<>();
            MediaListInfo tempMediaListInfo = new MediaListInfo(iapMediaListInfo.size, iapMediaListInfo.totalnum);
            tempMediaListInfo.setStartIndex(iapMediaListInfo.start);
            tempMediaListInfo.setEndIndex(iapMediaListInfo.end);
            tempMediaListInfo.setLevel(iapMediaListInfo.level);
            tempMediaListInfo.setCategoryType(iapMediaListInfo.categorytype);
            tempMediaListInfo.setSubCount(iapMediaListInfo.subcontents);
            int count = iapMediaListInfo.elements.size();
            for (int i = 0; i < count; i++) {
                IapMediaItemInfo item = iapMediaListInfo.elements.get(i);
                String str = IapController.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("covertIapToMediaListInfo: No.");
                sb.append(i);
                sb.append(" Name=");
                sb.append(item.name);
                sb.append(", Pid=");
                sb.append(item.id);
                sb.append(", Index=");
                sb.append(item.index);
                sb.append(", Sort=");
                sb.append(item.sort == null ? "null" : item.sort);
                sb.append(", KoName=");
                sb.append(item.koName);
                Log.v(str, sb.toString());
                MediaItemInfo mediaItemInfo = new MediaItemInfo(item.name, String.valueOf(item.id));
                mediaItemInfo.setIndex(item.index);
                mediaItemInfo.setKoName(item.koName);
                if (item.sort == null || item.sort.isEmpty()) {
                    mediaItemInfo.setInitial(item.name);
                } else {
                    mediaItemInfo.setInitial(item.sort);
                }
                tepMediaItemInfo.add(mediaItemInfo);
            }
            tempMediaListInfo.setMediaItemInfos(tepMediaItemInfo);
            return tempMediaListInfo;
        }

        private TrackInfo covertIapToTrackInfo(IapTrackInfo iapTrackInfo) {
            if (iapTrackInfo == null) {
                Log.e(IapController.TAG, "covertIapToTrackInfo iapTrackInfo is null");
                return null;
            }
            TrackInfo trackInfo = new TrackInfo(iapTrackInfo.Title);
            trackInfo.setAlbum(iapTrackInfo.Album);
            trackInfo.setArtist(iapTrackInfo.Artist);
            trackInfo.setGenre(iapTrackInfo.Genre);
            trackInfo.setComposer(iapTrackInfo.Composer);
            trackInfo.setIsChangePositionAvaible(iapTrackInfo.mIsChangePositionAvaible);
            trackInfo.setPid(iapTrackInfo.mPid);
            trackInfo.setMediaType(iapTrackInfo.mMediaType);
            trackInfo.setAppName(null);
            trackInfo.setAppBundleid(null);
            return trackInfo;
        }

        private int onCallStateNotification(int type, IapParcel data) {
            int ret;
            int ret2;
            Log.d(IapController.TAG, "onCallStateNotification type=" + type);
            if (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()] != 45) {
                Log.e(IapController.TAG, "onCallStateNotification failed for unknown type!");
                ret2 = -1;
            } else {
                this.mCallStateUpdate = new CallStateUpdate();
                int remoteIdValid = data.readInt();
                if (1 == remoteIdValid) {
                    String remoteId = data.readString();
                    Log.v(IapController.TAG, "onCallStateNotification CallRemoteId=" + LogEncryption.maskString(remoteId, 3, 4));
                    this.mCallStateUpdate.setRemoteId(remoteId);
                }
                int displayNameValid = data.readInt();
                if (1 == displayNameValid) {
                    String displayName = data.readString();
                    Log.v(IapController.TAG, "onCallStateNotification CallDisplayName=" + LogEncryption.maskString(displayName, 2, 2));
                    this.mCallStateUpdate.setDisplayName(displayName);
                }
                int statusValid = data.readInt();
                if (1 == statusValid) {
                    int status = data.readInt();
                    Log.v(IapController.TAG, "onCallStateNotification CallStatus=" + status);
                    this.mCallStateUpdate.setStatus(status);
                }
                int directionValid = data.readInt();
                if (1 == directionValid) {
                    int direction = data.readInt();
                    Log.v(IapController.TAG, "onCallStateNotification CallDirection=" + direction);
                    this.mCallStateUpdate.setDirection(direction);
                }
                int uuidValid = data.readInt();
                if (1 == uuidValid) {
                    String uuid = data.readString();
                    Log.v(IapController.TAG, "onCallStateNotification CallUuid=" + LogEncryption.maskString(uuid, 4, 4));
                    this.mCallStateUpdate.setCallUuid(uuid);
                }
                int addressBookIdValid = data.readInt();
                if (1 == addressBookIdValid) {
                    String addressBookId = data.readString();
                    Log.v(IapController.TAG, "onCallStateNotification AddressBookID=" + LogEncryption.maskString(addressBookId, 2, 2));
                    this.mCallStateUpdate.setAddressBookId(addressBookId);
                }
                int labelValid = data.readInt();
                if (1 == labelValid) {
                    String label = data.readString();
                    Log.v(IapController.TAG, "onCallStateNotification CallLabel=" + LogEncryption.maskString(label, 3, 2));
                    this.mCallStateUpdate.setLabel(label);
                }
                int serviceValid = data.readInt();
                if (1 == serviceValid) {
                    int service = data.readInt();
                    Log.v(IapController.TAG, "onCallStateNotification CallService=" + service);
                    this.mCallStateUpdate.setService(service);
                }
                int isConferencedValid = data.readInt();
                if (1 == isConferencedValid) {
                    int isConferenced = data.readInt();
                    Log.v(IapController.TAG, "onCallStateNotification IsConferenced=" + isConferenced);
                    this.mCallStateUpdate.setIsConferenced(isConferenced);
                }
                int groupValid = data.readInt();
                if (1 != groupValid) {
                    ret = 0;
                } else {
                    int group = data.readInt();
                    String str = IapController.TAG;
                    StringBuilder sb = new StringBuilder();
                    ret = 0;
                    sb.append("onCallStateNotification ConferenceGroup=");
                    sb.append(LogEncryption.maskInt(group));
                    Log.v(str, sb.toString());
                    this.mCallStateUpdate.setConferenceGroup(group);
                }
                int reasonValid = data.readInt();
                if (1 == reasonValid) {
                    int reason = data.readInt();
                    Log.v(IapController.TAG, "onCallStateNotification CallDisconnectReason=" + reason);
                    this.mCallStateUpdate.setDisconnectReason(reason);
                }
                int startTimeValid = data.readInt();
                if (1 == startTimeValid) {
                    long ts2 = data.readLong();
                    Log.v(IapController.TAG, "onCallStateNotification CallStartTimestamp=" + ts2);
                    this.mCallStateUpdate.setStartTimestamp(ts2);
                }
                Log.i(IapController.TAG, "onCallStateNotification report CallStateUpdate");
                this.mListener.onCallStateUpdate(this, this.mCallStateUpdate);
                this.mCallStateUpdate = null;
                ret2 = ret;
            }
            Log.d(IapController.TAG, "onCallStateNotification ret=" + ret2);
            return ret2;
        }

        private ArrayList<RecentListInfo> parseRecentListInfo(IapParcel data, int listCount) {
            Log.i(IapController.TAG, "parseRecentListInfo()/in");
            if (data == null) {
                Log.e(IapController.TAG, "parameter is null!");
                return null;
            }
            ArrayList<RecentListInfo> recentList = new ArrayList<>();
            for (int index = 0; index < listCount; index++) {
                RecentListInfo listInfo = new RecentListInfo();
                int indexValid = data.readInt();
                if (1 == indexValid) {
                    int indexValue = data.readInt();
                    listInfo.setIndex(indexValue);
                }
                int remoteIdValid = data.readInt();
                if (1 == remoteIdValid) {
                    String remoteId = data.readString();
                    listInfo.setRemoteId(remoteId);
                }
                int displayNameValid = data.readInt();
                if (1 == displayNameValid) {
                    String displayName = data.readString();
                    listInfo.setDisplayName(displayName);
                }
                int lableValid = data.readInt();
                if (1 == lableValid) {
                    String lable = data.readString();
                    listInfo.setLabel(lable);
                }
                int addressBookIdValid = data.readInt();
                if (1 == addressBookIdValid) {
                    String addressBookId = data.readString();
                    listInfo.setAddressBookId(addressBookId);
                }
                int serviceValid = data.readInt();
                if (1 == serviceValid) {
                    int service = data.readInt();
                    listInfo.setService(service);
                }
                int typeValid = data.readInt();
                if (1 == typeValid) {
                    int typeValue = data.readInt();
                    listInfo.setType(typeValue);
                }
                int unixTimestampValid = data.readInt();
                if (1 == unixTimestampValid) {
                    int unixTimestamp = data.readInt();
                    listInfo.setUnixTimestamp(unixTimestamp);
                }
                int durationValid = data.readInt();
                if (1 == durationValid) {
                    int duration = data.readInt();
                    listInfo.setDuration(duration);
                }
                int occurrencesValid = data.readInt();
                if (1 == occurrencesValid) {
                    int occurrences = data.readInt();
                    listInfo.setOccurrences(occurrences);
                }
                recentList.add(listInfo);
            }
            Log.i(IapController.TAG, "parseRecentListInfo()/out");
            return recentList;
        }

        private ArrayList<FavoriteListInfo> parseFavoriteListInfo(IapParcel data, int listCount) {
            Log.i(IapController.TAG, "parseFavoriteListInfo()/in");
            if (data == null) {
                Log.e(IapController.TAG, "parameter is null!");
                return null;
            }
            ArrayList<FavoriteListInfo> favoriteList = new ArrayList<>();
            for (int index = 0; index < listCount; index++) {
                FavoriteListInfo listInfo = new FavoriteListInfo();
                int indexValid = data.readInt();
                if (1 == indexValid) {
                    int indexValue = data.readInt();
                    listInfo.setIndex(indexValue);
                }
                int remoteIdValid = data.readInt();
                if (1 == remoteIdValid) {
                    String remoteId = data.readString();
                    listInfo.setRemoteId(remoteId);
                }
                int displayNameValid = data.readInt();
                if (1 == displayNameValid) {
                    String displayName = data.readString();
                    listInfo.setDisplayName(displayName);
                }
                int lableValid = data.readInt();
                if (1 == lableValid) {
                    String lable = data.readString();
                    listInfo.setLabel(lable);
                }
                int addressBookIdValid = data.readInt();
                if (1 == addressBookIdValid) {
                    String addressBookId = data.readString();
                    listInfo.setAddressBookId(addressBookId);
                }
                int serviceValid = data.readInt();
                if (1 == serviceValid) {
                    int service = data.readInt();
                    listInfo.setService(service);
                }
                favoriteList.add(listInfo);
            }
            Log.i(IapController.TAG, "parseFavoriteListInfo()/out");
            return favoriteList;
        }

        private ArrayList<NowPlayingPbMediaItemInfo> parseMediaIntemListInfo(IapParcel data, int mediaItemCount) {
            Log.i(IapController.TAG, "parseMediaIntemListInfo()/in");
            if (data == null) {
                Log.e(IapController.TAG, "parameter is null!");
                return null;
            }
            ArrayList<NowPlayingPbMediaItemInfo> mediaItemList = new ArrayList<>();
            for (int index = 0; index < mediaItemCount; index++) {
                NowPlayingPbMediaItemInfo mediaItemInfo = new NowPlayingPbMediaItemInfo();
                int pidValid = data.readInt();
                if (1 == pidValid) {
                    int pid = data.readInt();
                    mediaItemInfo.setPid(pid);
                }
                int titleValid = data.readInt();
                if (1 == titleValid) {
                    String title = data.readString();
                    mediaItemInfo.setTitle(title);
                }
                int albumTitleValid = data.readInt();
                if (1 == albumTitleValid) {
                    String albumTitle = data.readString();
                    mediaItemInfo.setAlbumTitle(albumTitle);
                }
                int artistValid = data.readInt();
                if (1 == artistValid) {
                    String artist = data.readString();
                    mediaItemInfo.setArtist(artist);
                }
                int albumArtistValid = data.readInt();
                if (1 == albumArtistValid) {
                    String albumArtist = data.readString();
                    mediaItemInfo.setAlbumArtist(albumArtist);
                }
                int genreValid = data.readInt();
                if (1 == genreValid) {
                    String genre = data.readString();
                    mediaItemInfo.setGenre(genre);
                }
                int composerValid = data.readInt();
                if (1 == composerValid) {
                    String composer = data.readString();
                    mediaItemInfo.setComposer(composer);
                }
                mediaItemList.add(mediaItemInfo);
            }
            Log.i(IapController.TAG, "parseMediaIntemListInfo()/out");
            return mediaItemList;
        }

        private int onListUpdateNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onListUpdateNotification type=" + type);
            int ret = 0;
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeRecentListAvailable:
                    int isAvailable = data.readInt();
                    int listCount = data.readInt();
                    Log.i(IapController.TAG, "onListUpdateNotification report RecentListAvailable: available = " + isAvailable + "listCount = " + listCount);
                    this.mListener.onRecentListAvailable(this, isAvailable, listCount);
                    break;
                case IapNotifyTypeFavoriteListAvailable:
                    int isAvailable2 = data.readInt();
                    int listCount2 = data.readInt();
                    Log.i(IapController.TAG, "onListUpdateNotification report FavoriteListAvailable: available = " + isAvailable2 + "listCount = " + listCount2);
                    this.mListener.onFavoriteListAvailable(this, isAvailable2, listCount2);
                    break;
                case IapNotifyTypeRecentListUpdate:
                    ArrayList<RecentListInfo> recentList = parseRecentListInfo(data, data.readInt());
                    Log.i(IapController.TAG, "onListUpdateNotification report RecentListInfo list");
                    this.mListener.onRecentListUpdate(this, recentList);
                    break;
                case IapNotifyTypeFavoriteListUpdate:
                    ArrayList<FavoriteListInfo> favoriteList = parseFavoriteListInfo(data, data.readInt());
                    Log.i(IapController.TAG, "onListUpdateNotification report FavoriteListInfo list");
                    this.mListener.onFavoriteListUpdate(this, favoriteList);
                    break;
                default:
                    Log.e(IapController.TAG, "onListUpdateNotification failed for unknown type!");
                    ret = -1;
                    break;
            }
            Log.d(IapController.TAG, "onListUpdateNotification ret=" + ret);
            return ret;
        }

        private int onNowPlayingPbNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onNowPlayingPbNotification type=" + type);
            int ret = 0;
            if (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()] != 50) {
                Log.e(IapController.TAG, "onNowPlayingPbNotification failed for unknown type!");
                ret = -1;
            } else {
                int mediaItemCount = data.readInt();
                int startIndex = data.readInt();
                Log.i(IapController.TAG, "mediaItemCount =" + mediaItemCount + "startIndex = " + startIndex);
                ArrayList<NowPlayingPbMediaItemInfo> mediaItemList = parseMediaIntemListInfo(data, mediaItemCount);
                this.mListener.onPlaybackListMeiaItemInfoUpdate(this, mediaItemCount, mediaItemCount, mediaItemList);
            }
            Log.d(IapController.TAG, "onNowPlayingPbNotification ret=" + ret);
            return ret;
        }

        private int onCommunicationsNotification(int type, IapParcel data) {
            int ret;
            int ret2;
            Log.d(IapController.TAG, "onCommunicationsNotification type=" + type);
            if (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()] != 51) {
                Log.e(IapController.TAG, "onCommunicationsNotification failed for unknown type!");
                ret2 = -1;
            } else {
                this.mCommunicationsUpdate = new CommunicationsUpdate();
                int signalValid = data.readInt();
                if (1 == signalValid) {
                    int signal = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification SignalStrength=" + signal);
                    this.mCommunicationsUpdate.setSignalStrength(signal);
                }
                int regStatusValid = data.readInt();
                if (1 == regStatusValid) {
                    int regStatus = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification RegistrationStatus=" + regStatus);
                    this.mCommunicationsUpdate.setRegistrationStatus(regStatus);
                }
                int apmStatusValid = data.readInt();
                if (1 == apmStatusValid) {
                    int apmStatus = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification AirplaneModeStatus=" + apmStatus);
                    this.mCommunicationsUpdate.setAirplaneModeStatus(apmStatus);
                }
                int carrierValid = data.readInt();
                if (1 == carrierValid) {
                    String carrier = data.readString();
                    Log.v(IapController.TAG, "onCommunicationsNotification CarrierName=" + carrier);
                    this.mCommunicationsUpdate.setCarrierName(carrier);
                }
                int cellularSupportedValid = data.readInt();
                if (1 == cellularSupportedValid) {
                    int cellularSupported = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification CellularSupported=" + cellularSupported);
                    this.mCommunicationsUpdate.setCellularSupported(cellularSupported);
                }
                int telephonyEnabledValid = data.readInt();
                if (1 == telephonyEnabledValid) {
                    int telephonyEnabled = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification TelephonyEnabled=" + telephonyEnabled);
                    this.mCommunicationsUpdate.setTelephonyEnabled(telephonyEnabled);
                }
                int facetimeAudioEnabledValid = data.readInt();
                if (1 == facetimeAudioEnabledValid) {
                    int facetimeAudioEnabled = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification FaceTimeAudioEnabled=" + facetimeAudioEnabled);
                    this.mCommunicationsUpdate.setFaceTimeAudioEnabled(facetimeAudioEnabled);
                }
                int facetimeVideoEnabledValid = data.readInt();
                if (1 == facetimeVideoEnabledValid) {
                    int facetimeVideoEnabled = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification FaceTimeVideoEnabled=" + facetimeVideoEnabled);
                    this.mCommunicationsUpdate.setFaceTimeVideoEnabled(facetimeVideoEnabled);
                }
                int muteStatusValid = data.readInt();
                if (1 == muteStatusValid) {
                    int muteStatus = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification MuteStatus=" + muteStatus);
                    this.mCommunicationsUpdate.setMuteStatus(muteStatus);
                }
                int currentCallCountValid = data.readInt();
                if (1 != currentCallCountValid) {
                    ret = 0;
                } else {
                    int currentCallCount = data.readInt();
                    String str = IapController.TAG;
                    StringBuilder sb = new StringBuilder();
                    ret = 0;
                    sb.append("onCommunicationsNotification CurrentCallCount=");
                    sb.append(currentCallCount);
                    Log.v(str, sb.toString());
                    this.mCommunicationsUpdate.setCurrentCallCount(currentCallCount);
                }
                int newVoicemailCountValid = data.readInt();
                if (1 == newVoicemailCountValid) {
                    int newVoicemailCount = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification NewVoicemailCount=" + newVoicemailCount);
                    this.mCommunicationsUpdate.setNewVoicemailCount(newVoicemailCount);
                }
                int initiateCallAvailableValid = data.readInt();
                if (1 == initiateCallAvailableValid) {
                    int initiateCallAvailable = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification InitiateCallAvailable=" + initiateCallAvailable);
                    this.mCommunicationsUpdate.setInitiateCallAvailable(initiateCallAvailable);
                }
                int endAndAcceptAvailableValid = data.readInt();
                if (1 == endAndAcceptAvailableValid) {
                    int endAndAcceptAvailable = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification EndAndAcceptAvailable=" + endAndAcceptAvailable);
                    this.mCommunicationsUpdate.setEndAndAcceptAvailable(endAndAcceptAvailable);
                }
                int holdAndAcceptAvailableValid = data.readInt();
                if (1 == holdAndAcceptAvailableValid) {
                    int holdAndAcceptAvailable = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification HoldAndAcceptAvailable=" + holdAndAcceptAvailable);
                    this.mCommunicationsUpdate.setHoldAndAcceptAvailable(holdAndAcceptAvailable);
                }
                int swapAvailableValid = data.readInt();
                if (1 == swapAvailableValid) {
                    int swapAvailable = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification SwapAvailable=" + swapAvailable);
                    this.mCommunicationsUpdate.setSwapAvailable(swapAvailable);
                }
                int mergeAvailableValid = data.readInt();
                if (1 == mergeAvailableValid) {
                    int mergeAvailable = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification MergeAvailable=" + mergeAvailable);
                    this.mCommunicationsUpdate.setMergeAvailable(mergeAvailable);
                }
                int holdAvailableValid = data.readInt();
                if (1 == holdAvailableValid) {
                    int holdAvailable = data.readInt();
                    Log.v(IapController.TAG, "onCommunicationsNotification HoldAvailable=" + holdAvailable);
                    this.mCommunicationsUpdate.setHoldAvailable(holdAvailable);
                }
                Log.i(IapController.TAG, "onCommunicationsNotification report CommunicationsUpdate");
                this.mListener.onCommunicationsUpdate(this, this.mCommunicationsUpdate);
                this.mCommunicationsUpdate = null;
                ret2 = ret;
            }
            Log.d(IapController.TAG, "onCommunicationsNotification ret=" + ret2);
            return ret2;
        }

        private int onLocationInformationNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onLocationInformationNotification type=" + type);
            int i = AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()];
            if (i != 52) {
                if (i != 53) {
                    Log.e(IapController.TAG, "onLocationInformationNotification failed for unknown type!");
                    return -1;
                }
                Log.i(IapController.TAG, "onLocationInformationNotification StopLocationInfo");
                this.mLocationInformationStarted = false;
                this.mSentenceType = -1;
                this.mListener.onStopLocationInformation(this);
                return 0;
            }
            this.mLocationInformationStarted = true;
            this.mSentenceType = data.readInt();
            Log.i(IapController.TAG, "onLocationInformationNotification StartLocationInfo SentenceType=" + this.mSentenceType);
            this.mListener.onStartLocationInformation(this, this.mSentenceType);
            return 0;
        }

        private int onGprmcDataStatusNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onGprmcDataStatusNotification type=" + type);
            if (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()] != 54) {
                Log.e(IapController.TAG, "onGprmcDataStatusNotification failed for unknown type!");
                return -1;
            }
            int gprmc = data.readInt();
            Log.i(IapController.TAG, "onGprmcDataStatusNotification gprmc=" + gprmc);
            this.mListener.onGprmcDataStatus(this, gprmc);
            return 0;
        }

        private int onOobBtPairingNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onOobBtPairingNotification type=" + type);
            int ret = 0;
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeStartOobBtPairing:
                    Log.i(IapController.TAG, "onOobBtPairingNotification StartOobBtPairing");
                    this.mListener.onStartOobBtPairing(this);
                    break;
                case IapNotifyTypeOobBtPairingLinkKey:
                    byte[] linkKey = data.createByteArray();
                    byte[] macAddr = data.createByteArray();
                    if (linkKey == null || macAddr == null) {
                        Log.e(IapController.TAG, "onOobBtPairingNotification createByteArray() fail, linkKey or macAddress is null");
                    } else {
                        Log.i(IapController.TAG, "onOobBtPairingNotification linkKey=" + IapController.bytes2HexString(linkKey) + " macAddr=" + LogEncryption.maskString(IapController.bytes2HexString(macAddr), 2, 2));
                        this.mListener.onOobBtPairingLinkKeyInformation(this, linkKey, macAddr);
                    }
                    break;
                case IapNotifyTypeStopOobBtPairing:
                    Log.i(IapController.TAG, "onOobBtPairingNotification StopOobBtPairing");
                    this.mListener.onStopOobBtPairing(this);
                    break;
                default:
                    Log.e(IapController.TAG, "onOobBtPairingNotification failed for unknown type!");
                    ret = -1;
                    break;
            }
            Log.d(IapController.TAG, "onOobBtPairingNotification ret=" + ret);
            return ret;
        }

        private int onOobBluetoothPairingNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onOobBluetoothPairingNotification type=" + type);
            int ret = 0;
            int i = AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()];
            if (i == 58) {
                Log.i(IapController.TAG, "onOobBluetoothPairingNotification onStartBluetoothPairing");
                int btTransComId = data.readInt();
                byte[] macAddr = data.createByteArray();
                if (macAddr == null) {
                    macAddr = new byte[0];
                    Log.e(IapController.TAG, "IapNotifyTypeStartBluetoothPairing not macaddr");
                }
                this.mListener.onStartBluetoothPairing(this, (short) btTransComId, macAddr);
            } else if (i != 59) {
                Log.e(IapController.TAG, "onOobBluetoothPairingNotification failed for unknown type!");
                ret = -1;
            } else {
                Log.i(IapController.TAG, "onOobBluetoothPairingNotification onStopBluetoothPairing");
                int btTransComId2 = data.readInt();
                PairingState state = PairingState.getPairingState(data.readInt());
                this.mListener.onStopBluetoothPairing(this, (short) btTransComId2, state);
            }
            Log.d(IapController.TAG, "onOobBtPairingNotification ret=" + ret);
            return ret;
        }

        private int onCarplayConnectionNotification(int type, IapParcel data) {
            Log.v(IapController.TAG, "onCarplayConnectionNotification type=" + type);
            if (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()] != 60) {
                Log.e(IapController.TAG, "onCarplayConnectionNotification failed for  unknown type!");
                return -1;
            }
            CarplayAvialability carplayAvialability = new CarplayAvialability();
            this.mCarplayAvialability = carplayAvialability;
            parseCarplayAvialability(carplayAvialability, data);
            this.mListener.onCarplayAvialability(this, this.mCarplayAvialability);
            this.mCarplayAvialability = null;
            return 0;
        }

        private void parseCarplayAvialability(CarplayAvialability avialability, IapParcel data) {
            int wiredValid = data.readInt();
            avialability.setWiredValid(wiredValid == 1);
            if (1 == wiredValid) {
                int avial = data.readInt();
                avialability.setWiredAvailable(avial == 1);
                int usbTransportIdentifierValid = data.readInt();
                if (1 == usbTransportIdentifierValid) {
                    avialability.setUsbTransportIdentifier(data.readString());
                }
            }
            int wirelessValid = data.readInt();
            avialability.setWirelessValid(wirelessValid == 1);
            if (1 == wirelessValid) {
                int avial2 = data.readInt();
                avialability.setWirelessAvailable(avial2 == 1);
                int btTransportIdentifierValid = data.readInt();
                if (1 == btTransportIdentifierValid) {
                    avialability.setBluetoothTransportIdentifier(data.readString());
                }
            }
        }

        private int onCarplayAppDiscovery(int type, IapParcel data) {
            Log.v(IapController.TAG, "onCarplayConnectionNotification type=" + type);
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeAppDiscoveryCarplayListUpdate:
                    CarplayAppListUpdate carplayAppListUpdate = new CarplayAppListUpdate();
                    parseCarplayAppDiscoveryListUpdate(carplayAppListUpdate, data);
                    if (carplayAppListUpdate.isAppListAvailableValid()) {
                        this.mListener.onCarPlayAppListAvailable(this, carplayAppListUpdate.getAppListAvailable(), carplayAppListUpdate.getTotalListCount());
                    } else {
                        this.mListener.onCarPlayAppList(this, carplayAppListUpdate.getCarPlayAppList());
                    }
                    return 0;
                case IapNotifyTypeAppDiscoveryCarplayAppIcon:
                    CarplayAppDiscoveryAppIcon carplayAppIcon = new CarplayAppDiscoveryAppIcon();
                    parseCarplayAppDiscoveryAppIcon(carplayAppIcon, data);
                    this.mListener.onCarplayAppDiscoveryAppIcon(this, carplayAppIcon);
                    return 0;
                case IapNotifyTypeCarplayAppIconDate:
                    CarplayAppDiscoveryAppIconData iconData = new CarplayAppDiscoveryAppIconData();
                    parseCarplayAppDiscoveryAppIconData(iconData, data);
                    this.mListener.onCarplayAppDiscoveryAppIconData(this, iconData);
                    return 0;
                default:
                    Log.e(IapController.TAG, "onCarplayAppDiscoveryUpdate failed for  unknown type!");
                    return -1;
            }
        }

        private void parseCarplayAppDiscoveryAppIconData(CarplayAppDiscoveryAppIconData iconData, IapParcel data) {
            Log.i(IapController.TAG, "parseCarplayAppDiscoveryAppIcon()/in");
            if (data == null) {
                Log.e(IapController.TAG, "parameter is null!");
                return;
            }
            int size = data.readInt();
            if (size == 0) {
                iconData.setIconData(null);
                return;
            }
            iconData.setIconData(data.createByteArray());
            iconData.setTransportID(data.readInt());
            int appBundleIdValid = data.readInt();
            if (1 == appBundleIdValid) {
                iconData.setAppBundleID(data.readString());
            }
            Log.i(IapController.TAG, "parseCarplayAppDiscoveryAppIcon()/out");
        }

        private void parseCarplayAppDiscoveryAppIcon(CarplayAppDiscoveryAppIcon appIcon, IapParcel data) {
            Log.i(IapController.TAG, "parseCarplayAppDiscoveryAppIcon()/in");
            if (data == null) {
                Log.e(IapController.TAG, "parameter is null!");
                return;
            }
            int idValid = data.readInt();
            if (1 == idValid) {
                appIcon.setAppBundleID(data.readString());
            }
            int availableValid = data.readInt();
            if (1 == availableValid) {
                appIcon.setAppIconAvailable(data.readInt());
            }
            int transferidValid = data.readInt();
            if (1 == transferidValid) {
                appIcon.setAppIconFileTransferID(data.readInt());
            }
            int identifierValid = data.readInt();
            if (1 == identifierValid) {
                appIcon.setAppIconIdentifier(data.readString());
            }
            Log.i(IapController.TAG, "parseCarplayAppDiscoveryAppIcon()/out");
        }

        private void parseCarplayAppDiscoveryListUpdate(CarplayAppListUpdate appListUpdate, IapParcel data) {
            Log.i(IapController.TAG, "parseCarplayAppDiscoveryListUpdate()/in");
            if (data == null) {
                Log.e(IapController.TAG, "parameter is null!");
                return;
            }
            int availableValid = data.readInt();
            int available = data.readInt();
            int totalAppListCount = data.readInt();
            if (1 == availableValid) {
                appListUpdate.setAppListAvailable(available);
            }
            if (totalAppListCount != 0) {
                appListUpdate.setTotalListCount(totalAppListCount);
            }
            int listCount = data.readInt();
            for (int index = 0; index < listCount; index++) {
                CarPlayAppList appInfo = new CarPlayAppList();
                int indexValid = data.readInt();
                if (1 == indexValid) {
                    int indexValue = data.readInt();
                    appInfo.setIndex(indexValue);
                }
                int appBundleIdValid = data.readInt();
                if (1 == appBundleIdValid) {
                    String bundleId = data.readString();
                    appInfo.setAppBundleID(bundleId);
                }
                int displayNameValid = data.readInt();
                if (1 == displayNameValid) {
                    String displayName = data.readString();
                    appInfo.setAppDisplayName(displayName);
                }
                int appCategoryIdValid = data.readInt();
                if (1 == appCategoryIdValid) {
                    int category = data.readInt();
                    appInfo.setAppCategoryID(category);
                }
                int appIconIdValid = data.readInt();
                if (1 == appIconIdValid) {
                    String appIconId = data.readString();
                    appInfo.setAppIconIdentifier(appIconId);
                }
                appListUpdate.addCarPlayAppList(appInfo);
            }
            Log.i(IapController.TAG, "parseCarplayAppDiscoveryListUpdate()/out");
        }

        private int onRouteGuidanceNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onRouteGuidanceNotification type=" + type);
            switch (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()]) {
                case 64:
                    RouteGuidanceUpdate routeGuidanceUpdate = new RouteGuidanceUpdate();
                    this.mRouteGuidanceUpdate = routeGuidanceUpdate;
                    parseRouteGuidanceInfo(routeGuidanceUpdate, data);
                    this.mListener.onRouteGuidanceUpdate(this, this.mRouteGuidanceUpdate);
                    this.mRouteGuidanceUpdate = null;
                    return 0;
                case CpConst.DisconnectScenario.SCENARIO_DETECT_OOB_DEIVCE_WHILE_TURN_OFF_ACCESSORY /* 65 */:
                    RouteGuidanceManeuverUpdate routeGuidanceManeuverUpdate = new RouteGuidanceManeuverUpdate();
                    this.mManeuverUpdate = routeGuidanceManeuverUpdate;
                    parseRouteGuidanceManeuverInfo(routeGuidanceManeuverUpdate, data);
                    this.mListener.onRouteGuidanceManeuverUpdate(this, this.mManeuverUpdate);
                    this.mManeuverUpdate = null;
                    return 0;
                case 66:
                    LaneGuidanceInformation laneGuidanceInformation = new LaneGuidanceInformation();
                    this.mLaneGuidanceInformation = laneGuidanceInformation;
                    parseLaneGuidanceInformation(laneGuidanceInformation, data);
                    this.mListener.onLaneGuidanceInformation(this, this.mLaneGuidanceInformation);
                    this.mLaneGuidanceInformation = null;
                    return 0;
                default:
                    Log.e(IapController.TAG, "onRouteGuidanceNotification failed for unknown type!");
                    return -1;
            }
        }

        private void parseRouteGuidanceInfo(RouteGuidanceUpdate routeGuidanceUpdate, IapParcel data) {
            parseRouteGuidanceInfoPart1(routeGuidanceUpdate, data);
            parseRouteGuidanceInfoPart2(routeGuidanceUpdate, data);
        }

        private void parseRouteGuidanceInfoPart1(RouteGuidanceUpdate routeGuidanceUpdate, IapParcel data) {
            int componentIdSize = data.readInt();
            for (int i = 0; i < componentIdSize; i++) {
                routeGuidanceUpdate.addRouteGuidanceDisplayComponentId(data.readInt());
            }
            int stateValid = data.readInt();
            if (1 == stateValid) {
                routeGuidanceUpdate.setState(data.readInt());
            }
            int maneuverStateValid = data.readInt();
            if (1 == maneuverStateValid) {
                routeGuidanceUpdate.setManeuverState(data.readInt());
            }
            int currentRoadNameValid = data.readInt();
            if (1 == currentRoadNameValid) {
                routeGuidanceUpdate.setCurrentRoadName(data.readString());
            }
            int destinationNameValid = data.readInt();
            if (1 == destinationNameValid) {
                routeGuidanceUpdate.setDestinationName(data.readString());
            }
            int estimatedTimeOfArrivalValid = data.readInt();
            if (1 == estimatedTimeOfArrivalValid) {
                routeGuidanceUpdate.setEstimatedTimeOfArrival(data.readLong());
            }
            int timeRemainingToDestinationValid = data.readInt();
            if (1 == timeRemainingToDestinationValid) {
                routeGuidanceUpdate.setTimeRemainingToDestination(data.readLong());
            }
            int distanceRemainingValid = data.readInt();
            if (1 == distanceRemainingValid) {
                routeGuidanceUpdate.setDistanceRemaining(data.readInt());
            }
            int distanceRemainingDisplayStrValid = data.readInt();
            if (1 == distanceRemainingDisplayStrValid) {
                routeGuidanceUpdate.setDistanceRemainingDisplayStr(data.readString());
            }
            int distanceRemainingDisplayUnitsValid = data.readInt();
            if (1 == distanceRemainingDisplayUnitsValid) {
                routeGuidanceUpdate.setDistanceRemainingDisplayUnits(data.readInt());
            }
            int distanceToNextManeuverValid = data.readInt();
            if (1 == distanceToNextManeuverValid) {
                routeGuidanceUpdate.setDistanceToNextManeuver(data.readInt());
            }
            int distanceToNextManeuverDisplayStrValid = data.readInt();
            if (1 == distanceToNextManeuverDisplayStrValid) {
                routeGuidanceUpdate.setDistanceToNextManeuverDisplayStr(data.readString());
            }
            int distanceToNextManeuverDisplayUnitsValid = data.readInt();
            if (1 == distanceToNextManeuverDisplayUnitsValid) {
                routeGuidanceUpdate.setmDistanceToNextManeuverDisplayUnits(data.readInt());
            }
            int maneuverCountValid = data.readInt();
            if (1 == maneuverCountValid) {
                routeGuidanceUpdate.setManeuverCount(data.readInt());
            }
            int maneuverCurrentListSize = data.readInt();
            int componentIdSize2 = 0;
            while (componentIdSize2 < maneuverCurrentListSize) {
                routeGuidanceUpdate.addManeuverListValue(data.readInt());
                componentIdSize2++;
                stateValid = stateValid;
            }
            int visibleInAppValid = data.readInt();
            if (1 == visibleInAppValid) {
                routeGuidanceUpdate.setVisibleInApp(data.readInt());
            }
        }

        private void parseRouteGuidanceInfoPart2(RouteGuidanceUpdate routeGuidanceUpdate, IapParcel data) {
            int laneGuidanceIndexValid = data.readInt();
            if (1 == laneGuidanceIndexValid) {
                routeGuidanceUpdate.setLaneGuidanceCurrentIndex(data.readInt());
            }
            int laneGuidanceTotalCountValid = data.readInt();
            if (1 == laneGuidanceTotalCountValid) {
                routeGuidanceUpdate.setLaneGuidanceTotalCount(data.readInt());
            }
            int laneGuidanceBeingShowInAppValid = data.readInt();
            if (1 == laneGuidanceBeingShowInAppValid) {
                routeGuidanceUpdate.setLaneGuidanceBeingShowInApp(data.readInt());
            }
            int sourceNameValid = data.readInt();
            if (1 == sourceNameValid) {
                routeGuidanceUpdate.setSourceName(data.readString());
            }
            int sourceSupportsValid = data.readInt();
            if (1 == sourceSupportsValid) {
                routeGuidanceUpdate.setSourceSupportsRouteGuidance(data.readInt());
            }
            int destinationTimeZoneOffsetMinutesValid = data.readInt();
            if (1 == destinationTimeZoneOffsetMinutesValid) {
                routeGuidanceUpdate.setDestinationTimeZoneOffsetMinutes(data.readInt());
            }
        }

        private void parseRouteGuidanceManeuverInfo(RouteGuidanceManeuverUpdate maneuverUpdate, IapParcel data) {
            int componentIdSize = data.readInt();
            for (int i = 0; i < componentIdSize; i++) {
                maneuverUpdate.addRouteGuidanceDisplayComponentId(data.readInt());
            }
            int indexValid = data.readInt();
            if (1 == indexValid) {
                maneuverUpdate.setIndex(data.readInt());
            }
            int maneuverDescriptionValid = data.readInt();
            if (1 == maneuverDescriptionValid) {
                maneuverUpdate.setManeuverDescription(data.readString());
            }
            int maneuverTypeValid = data.readInt();
            if (1 == maneuverTypeValid) {
                maneuverUpdate.setManeuverType(data.readInt());
            }
            int afterManeuverRoadNameValid = data.readInt();
            if (1 == afterManeuverRoadNameValid) {
                maneuverUpdate.setAfterManeuverRoadName(data.readString());
            }
            int distanceBetweenManeuverValid = data.readInt();
            if (1 == distanceBetweenManeuverValid) {
                maneuverUpdate.setDistanceBetweenManeuver(data.readInt());
            }
            int distanceBetweenManeuverDisplayStrValid = data.readInt();
            if (1 == distanceBetweenManeuverDisplayStrValid) {
                maneuverUpdate.setDistanceBetweenManeuverDisplayStr(data.readString());
            }
            int distanceBetweenManeuverDisplayUnitsValid = data.readInt();
            if (1 == distanceBetweenManeuverDisplayUnitsValid) {
                maneuverUpdate.setDistanceBetweenManeuverDisplayUnits(data.readInt());
            }
            int drivingSideValid = data.readInt();
            if (1 == drivingSideValid) {
                maneuverUpdate.setDrivingSide(data.readInt());
            }
            int junctionTypeValid = data.readInt();
            if (1 == junctionTypeValid) {
                maneuverUpdate.setJunctionType(data.readInt());
            }
            int junctionElementAngleSize = data.readInt();
            for (int i2 = 0; i2 < junctionElementAngleSize; i2++) {
                maneuverUpdate.addJunctionElementAngles(data.readInt());
            }
            int junctionElementExitAngleValid = data.readInt();
            if (1 == junctionElementExitAngleValid) {
                maneuverUpdate.setJunctionElementExitAngle(data.readInt());
            }
            int linkedLaneGuidanceInformationValid = data.readInt();
            if (1 == linkedLaneGuidanceInformationValid) {
                maneuverUpdate.setLinkedLaneGuidanceInformation(data.readInt());
            }
            int exitInfoValid = data.readInt();
            if (1 == exitInfoValid) {
                maneuverUpdate.setExitInfo(data.readString());
            }
        }

        private void parseLaneGuidanceInformation(LaneGuidanceInformation info, IapParcel data) {
            int componentIdSize = data.readInt();
            for (int i = 0; i < componentIdSize; i++) {
                info.addRouteGuidanceDisplayComponentId(data.readInt());
            }
            int laneGuidanceIndex = data.readInt();
            info.setLaneGuidanceIndex(laneGuidanceIndex);
            int laneInformationsSize = data.readInt();
            for (int i2 = 0; i2 < laneInformationsSize; i2++) {
                LaneInformation information = new LaneInformation();
                information.setIndex(data.readInt());
                information.setLaneStatus(LaneStatus.getLaneStatus(data.readInt()));
                int laneAnglesSize = data.readInt();
                for (int j = 0; j < laneAnglesSize; j++) {
                    information.addLaneAgnle(data.readInt());
                }
                int laneAngleHighlightValid = data.readInt();
                if (1 == laneAngleHighlightValid) {
                    information.setLaneAngleHighlight(data.readInt());
                }
                info.addLaneInformation(information);
            }
        }

        public int startDestinationInformation() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startDestinationInformation: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_DESTINATION_INFORMATION)) {
                Log.e(IapController.TAG, "startDestinationInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startDestinationInformation failed for session not connected!");
                return -1;
            }
            Log.i(IapController.TAG, "startDestinationInformation sourceName.");
            int ret = -1;
            try {
                ret = IapController.mIIap.startDestinationInformation(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startDestinationInformation:mIIap.startDestinationInformation: ", ex);
            }
            Log.i(IapController.TAG, "startDestinationInformation ret=" + ret);
            return ret;
        }

        public int stopDestinationInformation() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopDestinationInformation: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_DESTINATION_INFORMATION)) {
                Log.e(IapController.TAG, "stopDestinationInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopDestinationInformation failed for session not connected!");
                return -1;
            }
            Log.i(IapController.TAG, "stopDestinationInformation enter!");
            int ret = -1;
            try {
                ret = IapController.mIIap.stopDestinationInformation(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopDestinationInformation:mIIap.stopDestinationInformation: ", ex);
            }
            Log.i(IapController.TAG, "stopDestinationInformation ret=" + ret);
            return ret;
        }

        public int sendDestinationInformationStatus(DestinationInformationStatus destInforStatus) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendDestinationInformationStatus: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_DESTINATION_INFORMATION)) {
                Log.e(IapController.TAG, "sendDestinationInformationStatus failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendDestinationInformationStatus failed for session not connected!");
                return -1;
            }
            Log.i(IapController.TAG, "sendDestinationInformationStatus status=" + destInforStatus);
            HalDestinationInformationStatus halDestInfoStatus = destinationInformationStatus2HalDestinationInformationStatus(destInforStatus);
            if (halDestInfoStatus == null) {
                Log.e(IapController.TAG, "sendDestinationInformationStatus failed for destinationInformationStatus2HalDestinationInformationStatus");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendDestinationInformationStatus(this.mNativeHandle, halDestInfoStatus);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendDestinationInformationStatus:mIIap.sendDestinationInformationStatus: ", ex);
            }
            Log.i(IapController.TAG, "sendDestinationInformationStatus ret=" + ret);
            return ret;
        }

        private HalDestinationInformationStatus destinationInformationStatus2HalDestinationInformationStatus(DestinationInformationStatus destInfoStatus) {
            HalDestinationInformationStatus halDestInfoStatus = new HalDestinationInformationStatus();
            if (true == destInfoStatus.isDestinationIdentifierValid()) {
                halDestInfoStatus.destinationIdentifier = destInfoStatus.getDestinationIndentifier();
            } else {
                halDestInfoStatus.destinationIdentifier = null;
            }
            if (true == destInfoStatus.isFailureOccurredValid()) {
                halDestInfoStatus.failureOccurredValid = true;
                halDestInfoStatus.failureOccurred = destInfoStatus.getFailureOccurred();
            } else {
                halDestInfoStatus.failureOccurredValid = false;
                halDestInfoStatus.failureOccurred = -1;
            }
            return halDestInfoStatus;
        }

        private int onDestinationInformation(int type, IapParcel data) {
            Log.v(IapController.TAG, "onDestinationInformation type=" + type);
            if (AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()] != 67) {
                Log.e(IapController.TAG, "onDestinationInformation failed for  unknown type!");
                return -1;
            }
            DestinationInformation destinationInformation = new DestinationInformation();
            this.mDestinationInformation = destinationInformation;
            parseDestinationInformation(destinationInformation, data);
            this.mListener.onDestinationInformation(this, this.mDestinationInformation);
            this.mDestinationInformation = null;
            return 0;
        }

        private void parseDestinationInformation(DestinationInformation info, IapParcel data) {
            int destinationIdentifierValid = data.readInt();
            if (1 == destinationIdentifierValid) {
                info.setDestinationIdentifier(data.readString());
            }
            int diaplayNameValid = data.readInt();
            if (1 == diaplayNameValid) {
                info.setDisplayName(data.readString());
            }
            int CenterCoordinateLatitudeValid = data.readInt();
            if (1 == CenterCoordinateLatitudeValid) {
                info.setCenterCoordinateLatitude(data.readInt());
            }
            int CenterCoordinateLongitudeValid = data.readInt();
            if (1 == CenterCoordinateLongitudeValid) {
                info.setCenterCoordinateLongitude(data.readInt());
            }
            int addressValid = data.readInt();
            if (1 == addressValid) {
                info.setAddress(data.readString());
            }
            int entryPointSize = data.readInt();
            for (int i = 0; i < entryPointSize; i++) {
                DestinationInformationCoordinate entryPoint = new DestinationInformationCoordinate();
                int latitudeValid = data.readInt();
                if (1 == latitudeValid) {
                    entryPoint.setLatitude(data.readInt());
                }
                int longitudeValid = data.readInt();
                if (1 == longitudeValid) {
                    entryPoint.setLongitude(data.readInt());
                }
                info.addEntryPoint(entryPoint);
            }
            int coordinateThresholdValid = data.readInt();
            if (1 == coordinateThresholdValid) {
                info.setCoordinateThreshold(data.readLong());
            }
            int localeValid = data.readInt();
            if (1 == localeValid) {
                info.setLocale(data.readString());
            }
            int sourceNameValid = data.readInt();
            if (1 == sourceNameValid) {
                info.setSourceName(data.readString());
            }
        }

        private int onVehicleStatusNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onVehicleStatusNotification type=" + type);
            int i = AnonymousClass1.$SwitchMap$com$ts$car$iap$protocol$IapNotificationType[IapNotificationType.fromTypeValue(type).ordinal()];
            if (i != 68) {
                if (i != 69) {
                    Log.e(IapController.TAG, "onVehicleStatusNotification failed for unknown type!");
                    return -1;
                }
                Log.i(IapController.TAG, "onVehicleStatusNotification StopVehicleStatusUpdates");
                this.mVehicleStatusUpdateStarted = false;
                this.mStartVehicleStatusUpdateType = -1;
                this.mListener.onStopVehicleStatusUpdates(this);
                return 0;
            }
            this.mVehicleStatusUpdateStarted = true;
            this.mStartVehicleStatusUpdateType = data.readInt();
            Log.i(IapController.TAG, "onVehicleStatusNotification StartVehicleStatusUpdates mStartVehicleStatusUpdateType=" + this.mStartVehicleStatusUpdateType);
            this.mListener.onStartVehicleStatusUpdates(this, this.mStartVehicleStatusUpdateType);
            return 0;
        }

        public int sendVehicleStatusInformation(VehicleStatusUpdate vehicleStatusInfo) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendVehicleStatusInformation: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_VEHICLE_STATUS)) {
                Log.e(IapController.TAG, "sendVehicleStatusInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendVehicleStatusInformation failed for session not connected!");
                return -1;
            }
            if (!this.mVehicleStatusUpdateStarted) {
                Log.e(IapController.TAG, "sendVehicleStatusInformation failed as device not start vehicle status updates information!");
                return -1;
            }
            Log.i(IapController.TAG, "sendVehicleStatusInformation status=" + vehicleStatusInfo);
            HalVehicleStatusInfo halVehicleStatusInfo = VehicleStatusInfo2HalVehicleStatusInfos(vehicleStatusInfo);
            if (halVehicleStatusInfo == null) {
                Log.e(IapController.TAG, "sendVehicleStatusInformation failed for VehicleStatusInfo2HalVehicleStatusInfos");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendVehicleStatusInformation(this.mNativeHandle, halVehicleStatusInfo);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendVehicleStatusInformation:mIIap.sendVehicleStatusInformation: ", ex);
            }
            Log.i(IapController.TAG, "sendVehicleStatusInformation ret=" + ret);
            return ret;
        }

        private HalVehicleStatusInfo VehicleStatusInfo2HalVehicleStatusInfos(VehicleStatusUpdate vehicleStatusInfo) {
            HalVehicleStatusInfo halVehicleStatusInfo = new HalVehicleStatusInfo();
            halVehicleStatusInfo.rangeValid = vehicleStatusInfo.getRangeValid();
            halVehicleStatusInfo.range = (short) vehicleStatusInfo.getRange();
            halVehicleStatusInfo.outsideTemperatureValid = vehicleStatusInfo.getOutsideTemperatureValid();
            halVehicleStatusInfo.outsideTemperature = (short) vehicleStatusInfo.getOutsideTemperature();
            halVehicleStatusInfo.rangeWarningValid = vehicleStatusInfo.getRangeWarningValid();
            halVehicleStatusInfo.rangeWarning = vehicleStatusInfo.getRangeWarning();
            halVehicleStatusInfo.rangeGasolineValid = vehicleStatusInfo.getRangeGasolineValid();
            halVehicleStatusInfo.rangeGasoline = (short) vehicleStatusInfo.getRangeGasoline();
            halVehicleStatusInfo.rangeDieselValid = vehicleStatusInfo.getRangeDieselValid();
            halVehicleStatusInfo.rangeDiesel = (short) vehicleStatusInfo.getRangeDiesel();
            halVehicleStatusInfo.rangeElectricValid = vehicleStatusInfo.getRangeElectricValid();
            halVehicleStatusInfo.rangeElectric = (short) vehicleStatusInfo.getRangeElectric();
            halVehicleStatusInfo.rangeCNGValid = vehicleStatusInfo.getRangeCNGValid();
            halVehicleStatusInfo.rangeCNG = (short) vehicleStatusInfo.getRangeCNG();
            halVehicleStatusInfo.rangeWarningGasolineValid = vehicleStatusInfo.getRangeWarningGasolineValid();
            halVehicleStatusInfo.rangeWarningGasoline = vehicleStatusInfo.getRangeWarningGasoline();
            halVehicleStatusInfo.rangeWarningDieselValid = vehicleStatusInfo.getRangeWarningDieselValid();
            halVehicleStatusInfo.rangeWarningDiesel = vehicleStatusInfo.getRangeWarningDiesel();
            halVehicleStatusInfo.rangeWarningElectricValid = vehicleStatusInfo.getRangeWarningElectricValid();
            halVehicleStatusInfo.rangeWarningElectric = vehicleStatusInfo.getRangeWarningElectric();
            halVehicleStatusInfo.rangeWarningCNGValid = vehicleStatusInfo.getRangeWarningCNGValid();
            halVehicleStatusInfo.rangeWarningCNG = vehicleStatusInfo.getRangeWarningCNG();
            halVehicleStatusInfo.minBatteryChargeValid = vehicleStatusInfo.getMinBatteryChargeValid();
            halVehicleStatusInfo.minBatteryCharge = (int) vehicleStatusInfo.getMinBatteryCharge();
            halVehicleStatusInfo.currentBatteryChargeValid = vehicleStatusInfo.getCurrentBatteryChargeValid();
            halVehicleStatusInfo.currentBatteryCharge = (int) vehicleStatusInfo.getCurrentBatteryCharge();
            halVehicleStatusInfo.maxBatteryChargeValid = vehicleStatusInfo.getMaxBatteryChargeValid();
            halVehicleStatusInfo.maxBatteryCharge = (int) vehicleStatusInfo.getMaxBatteryCharge();
            halVehicleStatusInfo.displayedBatteryPercentageValid = vehicleStatusInfo.getDisplayedBatteryPercentageValid();
            halVehicleStatusInfo.displayedBatteryPercentage = (int) vehicleStatusInfo.getDisplayedBatteryPercentage();
            halVehicleStatusInfo.isChargingValid = vehicleStatusInfo.getIsChargingValid();
            halVehicleStatusInfo.isCharging = vehicleStatusInfo.getIsCharging();
            if (vehicleStatusInfo.getChargingParameterValid()) {
                halVehicleStatusInfo.chargingParameterValid = true;
                halVehicleStatusInfo.chargingParameter = vehicleStatusInfo.getChargingParameter();
            } else {
                halVehicleStatusInfo.chargingParameterValid = false;
                halVehicleStatusInfo.chargingParameter = null;
            }
            if (vehicleStatusInfo.getConsumptionParameterValid()) {
                halVehicleStatusInfo.consumptionParameterValid = true;
                halVehicleStatusInfo.consumptionParameter = vehicleStatusInfo.getConsumptionParameter();
            } else {
                halVehicleStatusInfo.consumptionParameterValid = false;
                halVehicleStatusInfo.consumptionParameter = null;
            }
            halVehicleStatusInfo.activeConnectorValid = vehicleStatusInfo.getActiveConnectorValid();
            halVehicleStatusInfo.activeConnector = (byte) vehicleStatusInfo.getActiveConnector();
            halVehicleStatusInfo.maxRangeElectricValid = vehicleStatusInfo.getMaxRangeElectricValid();
            halVehicleStatusInfo.maxRangeElectric = (short) vehicleStatusInfo.getMaxRangeElectric();
            return halVehicleStatusInfo;
        }

        private boolean isActiveEapSession(int sessionId) {
            return this.mActiveEapSessions.contains(Integer.valueOf(sessionId));
        }

        private int onEapNotification(int type, IapParcel data) {
            Log.d(IapController.TAG, "onEapNotification type=" + type);
            int ret = 0;
            switch (IapNotificationType.fromTypeValue(type)) {
                case IapNotifyTypeStartEapSession:
                    int eapId = data.readInt();
                    int sessionId = data.readInt();
                    Log.i(IapController.TAG, "StartEapSession eapId=" + eapId + " sessionId=" + sessionId);
                    if (eapId == -1 || sessionId == -1 || isActiveEapSession(sessionId)) {
                        Log.e(IapController.TAG, "StartEapSession failed for invalid id or session already active!");
                        return -1;
                    }
                    this.mActiveEapSessions.add(Integer.valueOf(sessionId));
                    this.mListener.onStartEapSession(this, eapId, sessionId);
                    break;
                    break;
                case IapNotifyTypeEapIncomingData:
                    int sessionId2 = data.readInt();
                    byte[] eapData = data.createByteArray();
                    String str = IapController.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("ReceiveEapData sessionId=");
                    sb.append(sessionId2);
                    sb.append(" eapData len=");
                    sb.append(eapData == null ? 0 : eapData.length);
                    Log.i(str, sb.toString());
                    if (!isActiveEapSession(sessionId2) || eapData == null) {
                        Log.e(IapController.TAG, "ReceiveEapData failed for invalid session or data!");
                        return -1;
                    }
                    this.mListener.onReceiveEapData(this, sessionId2, eapData);
                    break;
                    break;
                case IapNotifyTypeStopEapSession:
                    int sessionId3 = data.readInt();
                    Log.i(IapController.TAG, "StopEapSession sessionId=" + sessionId3);
                    if (!isActiveEapSession(sessionId3)) {
                        Log.e(IapController.TAG, "StopEapSession failed for invalid session!");
                        return -1;
                    }
                    this.mListener.onStopEapSession(this, sessionId3);
                    this.mActiveEapSessions.remove(Integer.valueOf(sessionId3));
                    break;
                    break;
                default:
                    Log.e(IapController.TAG, "onEapNotification failed for unknown type!");
                    ret = -1;
                    break;
            }
            Log.d(IapController.TAG, "onEapNotification ret=" + ret);
            return ret;
        }

        public int connect() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "connect: mIIap is null");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.connect(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "connect: mIIap.connect: ", ex);
            }
            if (ret != 0) {
                Log.e(IapController.TAG, "Failed to connect iap session for native connect failure! ret = " + ret);
                return -1;
            }
            Log.i(IapController.TAG, "Triggered native layer to connect iap session ...");
            return 0;
        }

        public int disconnect() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "disconnect: mIIap is null");
                return -1;
            }
            this.mSessionStatus = IapSessionStatus.IapSessionDisconnecting;
            int ret = -1;
            try {
                ret = IapController.mIIap.disconnect(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "disconnect: mIIap.disconnect: ", ex);
            }
            if (ret != 0) {
                Log.e(IapController.TAG, "Failed to disconnect iap session for native disconnect failure! ret = " + ret);
                return -1;
            }
            Log.i(IapController.TAG, "Triggered native layer to disconnect iap session ...");
            return 0;
        }

        public String getDeviceName() {
            if (!this.mConfig.isIapFeatureEnabled(1)) {
                Log.e(IapController.TAG, "getDeviceName failed for iap feature not enabled!");
                return null;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getDeviceName failed for session not connected!");
                return null;
            }
            Log.i(IapController.TAG, "getDeviceName DeviceName=" + LogEncryption.maskString(this.mDeviceName, 2, 2));
            return this.mDeviceName;
        }

        public int startBluetoothConnectionUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startBluetoothConnectionUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(4)) {
                Log.e(IapController.TAG, "startBluetoothConnectionUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startBluetoothConnectionUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.startBluetoothConnectionUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startBluetoothConnectionUpdates: mIIap.startBluetoothConnectionUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startBluetoothConnectionUpdates ret=" + ret);
            return ret;
        }

        public int stopBluetoothConnectionUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopBluetoothConnectionUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(4)) {
                Log.e(IapController.TAG, "stopBluetoothConnectionUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopBluetoothConnectionUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopBluetoothConnectionUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopBluetoothConnectionUpdates:mIIap.stopBluetoothConnectionUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopBluetoothConnectionUpdates ret=" + ret);
            return ret;
        }

        public int startMediaLibraryUpdates(String lastRevision) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startMediaLibraryUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "startMediaLibraryUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startMediaLibraryUpdates failed for session not connected!");
                return -1;
            }
            Log.i(IapController.TAG, "startMediaLibraryUpdates lastRevision=" + lastRevision);
            int ret = -1;
            try {
                ret = IapController.mIIap.startMediaLibraryUpdates(this.mNativeHandle, lastRevision);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startMediaLibraryUpdates:mIIap.startMediaLibraryUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startMediaLibraryUpdates ret=" + ret);
            return ret;
        }

        public int stopMediaLibraryUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopMediaLibraryUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "stopMediaLibraryUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopMediaLibraryUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopMediaLibraryUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopMediaLibraryUpdates: mIIap.stopMediaLibraryUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopMediaLibraryUpdates ret=" + ret);
            return ret;
        }

        public int playSongs(long startingIndex, String[] songPids) throws RemoteException {
            String str = IapController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("playSongs startingIndex=");
            sb.append(startingIndex);
            sb.append(" songPids len=");
            sb.append(songPids == null ? 0 : songPids.length);
            Log.i(str, sb.toString());
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "playSongs: mIIap is null");
                return -1;
            }
            if (startingIndex < 0 || songPids == null || songPids.length == 0 || startingIndex >= songPids.length) {
                Log.e(IapController.TAG, "playSongs failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(16)) {
                Log.e(IapController.TAG, "playSongs failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "playSongs failed for session not connected!");
                return -1;
            }
            ArrayList<String> halSongPids = new ArrayList<>(Arrays.asList(songPids));
            int ret = -1;
            try {
                ret = IapController.mIIap.playSongs(this.mNativeHandle, (int) startingIndex, halSongPids);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "playSongs:mIIap.playSongs: ", ex);
            }
            Log.i(IapController.TAG, "playSongs ret=" + ret);
            return ret;
        }

        public int playMusicRadio(String radioPid) throws RemoteException {
            Log.i(IapController.TAG, "playMusicRadio radioPid=" + radioPid);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "playMusicRadio: mIIap is null");
                return -1;
            }
            if (radioPid == null) {
                Log.e(IapController.TAG, "playMusicRadio failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(16)) {
                Log.e(IapController.TAG, "playMusicRadio failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "playMusicRadio failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.playMusicRadio(this.mNativeHandle, radioPid);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "playMusicRadio: IIap.playMusicRadio: ", ex);
            }
            Log.i(IapController.TAG, "playMusicRadio ret=" + ret);
            return ret;
        }

        public int startNowPlayingUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startNowPlayingUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(128)) {
                Log.e(IapController.TAG, "startNowPlayingUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startNowPlayingUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.startNowPlayingUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startNowPlayingUpdates: mIIap.startNowPlayingUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startNowPlayingUpdates ret=" + ret);
            return ret;
        }

        public int stopNowPlayingUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopNowPlayingUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(128)) {
                Log.e(IapController.TAG, "stopNowPlayingUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopNowPlayingUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopNowPlayingUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopNowPlayingUpdates: mIIap.stopNowPlayingUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopNowPlayingUpdates ret=" + ret);
            return ret;
        }

        public int startListUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startListUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(262144)) {
                Log.e(IapController.TAG, "startListUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startListUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.startListUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startListUpdates: mIIap.startListUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startListUpdates ret=" + ret);
            return ret;
        }

        public int stopListUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopListUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(262144)) {
                Log.e(IapController.TAG, "stopListUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopListUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopListUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopListUpdates: mIIap.stopListUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopListUpdates ret=" + ret);
            return ret;
        }

        public int playSong(long pbIndex) throws RemoteException {
            Log.i(IapController.TAG, "playSong pbIndex=" + pbIndex);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "playSong: mIIap is null");
                return -1;
            }
            if (pbIndex < 0) {
                Log.e(IapController.TAG, "playSong failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(256)) {
                Log.e(IapController.TAG, "playSong failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "playSong failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.playSong(this.mNativeHandle, (int) pbIndex);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "playSong: mIIap.playSong: ", ex);
            }
            Log.i(IapController.TAG, "playSong ret=" + ret);
            return ret;
        }

        public int setSongElapsedTime(long elapsedTime) throws RemoteException {
            Log.i(IapController.TAG, "setSongElapsedTime elapsedTime=" + elapsedTime);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "setSongElapsedTime: mIIap is null");
                return -1;
            }
            if (elapsedTime < 0) {
                Log.e(IapController.TAG, "setSongElapsedTime failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(256)) {
                Log.e(IapController.TAG, "setSongElapsedTime failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "setSongElapsedTime failed for session not connected!");
                return -1;
            }
            if (!this.mIsSetElapsedTimeAvailable) {
                Log.e(IapController.TAG, "setSongElapsedTime failed for device not support!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.setSongElapsedTime(this.mNativeHandle, (int) elapsedTime);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "setSongElapsedTime: mIIap.setSongElapsedTime: ", ex);
            }
            Log.i(IapController.TAG, "setSongElapsedTime ret=" + ret);
            return ret;
        }

        public int pressHidKey(int keyCode) throws RemoteException {
            Log.i(IapController.TAG, "pressHidKey keyCode=0x" + Integer.toHexString(keyCode));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "pressHidKey: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "pressHidKey failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isPlaybackHidEnabled(keyCode)) {
                Log.e(IapController.TAG, "pressHidKey failed for hid key not enabled or invalid!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "pressHidKey failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.pressHidKey(this.mNativeHandle, keyCode);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "pressHidKey: mIIap.pressHidKey: ", ex);
            }
            Log.i(IapController.TAG, "pressHidKey ret=" + ret);
            return ret;
        }

        public int releaseHidKey() throws RemoteException {
            Log.i(IapController.TAG, "releaseHidKey enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "releaseHidKey: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "releaseHidKey failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "releaseHidKey failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.releaseHidKey(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "releaseHidKey: mIIap.releaseHidKey: ", ex);
            }
            Log.i(IapController.TAG, "releaseHidKey ret=" + ret);
            return ret;
        }

        public int shufflePlayCurrentAllSongs(int bchangeMode) throws RemoteException {
            Log.i(IapController.TAG, "shufflePlayCurrentAllSongs enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "shufflePlayCurrentAllSongs: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(16)) {
                Log.e(IapController.TAG, "shufflePlayCurrentAllSongs failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "shufflePlayCurrentAllSongs failed for session not connected!");
                return -1;
            }
            boolean bchange = bchangeMode == 1;
            int ret = -1;
            try {
                ret = IapController.mIIap.shufflePlayCurrentAllSongs(this.mNativeHandle, bchange);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "shufflePlayCurrentAllSongs: mIIap.shufflePlayCurrentAllSongs: ", ex);
            }
            Log.i(IapController.TAG, "shufflePlayCurrentAllSongs ret=" + ret);
            return ret;
        }

        public int playCurrentAllSongs(int bchangeMode) throws RemoteException {
            Log.i(IapController.TAG, "playCurrentAllSongs enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "playCurrentAllSongs: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(16)) {
                Log.e(IapController.TAG, "playCurrentAllSongs failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "playCurrentAllSongs failed for session not connected!");
                return -1;
            }
            boolean bchange = bchangeMode == 1;
            int ret = -1;
            try {
                ret = IapController.mIIap.playCurrentAllSongs(this.mNativeHandle, bchange);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "playCurrentAllSongs: mIIap.playCurrentAllSongs: ", ex);
            }
            Log.i(IapController.TAG, "playCurrentAllSongs ret=" + ret);
            return ret;
        }

        public int getPlaybackStatus() throws RemoteException {
            Log.i(IapController.TAG, "getPlayBackStatus enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getPlayBackStatus: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getPlayBackStatus failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getPlayBackStatus failed for session not connected!");
                return -1;
            }
            int status = -1;
            try {
                status = IapController.mIIap.getPlaybackStatus(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getPlayBackStatus: mIIap.getPlayBackStatus: ", ex);
            }
            Log.i(IapController.TAG, "getPlayBackStatus status=" + status);
            return status;
        }

        public int getCurrentTrackDuration() throws RemoteException {
            Log.i(IapController.TAG, "getCurtrackDuration enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getCurtrackDuration: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getCurtrackDuration failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getCurtrackDuration failed for session not connected!");
                return -1;
            }
            int duration = -1;
            try {
                duration = IapController.mIIap.getSongDuration(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getCurtrackDuration: mIIap.getCurtrackDuration: ", ex);
            }
            Log.i(IapController.TAG, "getCurtrackDuration status=" + duration);
            return duration;
        }

        public int getRepeatMode() throws RemoteException {
            Log.i(IapController.TAG, "getRepeatMode enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getRepeatMode: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getRepeatMode failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getRepeatMode failed for session not connected!");
                return -1;
            }
            int repeatMode = -1;
            try {
                repeatMode = IapController.mIIap.getRepeatMode(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getRepeatMode: mIIap.getRepeatMode: ", ex);
            }
            Log.i(IapController.TAG, "getRepeatMode repeatMode=" + repeatMode);
            return repeatMode;
        }

        public int getShuffleMode() throws RemoteException {
            Log.i(IapController.TAG, "getShuffleMode enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getShuffleMode: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getShuffleMode failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getShuffleMode failed for session not connected!");
                return -1;
            }
            int shuffleMode = -1;
            try {
                shuffleMode = IapController.mIIap.getShuffleMode(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getShuffleMode: mIIap.getShuffleMode: ", ex);
            }
            Log.i(IapController.TAG, "getShuffleMode shuffleMode=" + shuffleMode);
            return shuffleMode;
        }

        public TrackInfo getIpodId3Info() throws RemoteException {
            Log.i(IapController.TAG, "getIpodId3Info enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getIpodId3Info: mIIap is null");
                return null;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getIpodId3Info failed for iap feature not enabled!");
                return null;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getIpodId3Info failed for session not connected!");
                return null;
            }
            IapTrackInfo iapTrackInfo = null;
            try {
                iapTrackInfo = IapController.mIIap.getIpodId3Info(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getIpodId3Info: mIIap.getIpodId3Info: ", ex);
            }
            TrackInfo trackInfo = covertIapToTrackInfo(iapTrackInfo);
            Log.i(IapController.TAG, "getIpodId3Info iapId3Info=" + trackInfo);
            return trackInfo;
        }

        public long getNowPlayingTrackIndex() throws RemoteException {
            Log.i(IapController.TAG, "getNowPlayingTrackIndex enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getNowPlayingTrackIndex: mIIap is null");
                return -1L;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getNowPlayingTrackIndex failed for iap feature not enabled!");
                return -1L;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getNowPlayingTrackIndex failed for session not connected!");
                return -1L;
            }
            int trackIndex = -1;
            try {
                trackIndex = IapController.mIIap.getNowPlayingTrackIndex(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getNowPlayingTrackIndex: mIIap.getNowPlayingTrackIndex: ", ex);
            }
            Log.i(IapController.TAG, "getNowPlayingTrackIndex trackIndex=" + trackIndex);
            return trackIndex;
        }

        public long getNowPlayingTracksCount() {
            Log.i(IapController.TAG, "getNowPlayingTracksNum enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getNowPlayingTracksNum: mIIap is null");
                return -1L;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getNowPlayingTracksNum failed for iap feature not enabled!");
                return -1L;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getNowPlayingTracksNum failed for session not connected!");
                return -1L;
            }
            long num = -1;
            try {
                num = IapController.mIIap.getNowPlayingTracksCount(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getNowPlayingTracksNum: mIIap.getNowPlayingTracksNum: ", ex);
            }
            Log.i(IapController.TAG, "getNowPlayingTracksNum num=" + num);
            return num;
        }

        public MediaListInfo getNowPlayingList(int start, int count) throws RemoteException {
            Log.i(IapController.TAG, "getNowPlayingList enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getNowPlayingList: mIIap is null");
                return null;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "getNowPlayingList failed for iap feature not enabled!");
                return null;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getNowPlayingList failed for session not connected!");
                return null;
            }
            IapMediaListInfo iapMediaListInfo = null;
            try {
                iapMediaListInfo = IapController.mIIap.getNowPlayingList(this.mNativeHandle, start, count);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getNowPlayingList: mIIap.getNowPlayingList: ", ex);
            }
            MediaListInfo mediaListInfo = covertIapToMediaListInfo(iapMediaListInfo);
            Log.i(IapController.TAG, "iapMediaListInfo =" + mediaListInfo);
            return mediaListInfo;
        }

        public long getArtworkSize(int trackIndex) {
            Log.i(IapController.TAG, "getArtworkSize enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getArtworkSize: mIIap is null");
                return -1L;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "getArtworkSize failed for iap feature not enabled!");
                return -1L;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getArtworkSize failed for session not connected!");
                return -1L;
            }
            long size = 0;
            try {
                size = IapController.mIIap.getArtworkSize(this.mNativeHandle, trackIndex);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getArtworkSize: mIIap.getArtworkSize: ", ex);
            }
            Log.i(IapController.TAG, "size=" + size);
            return size;
        }

        public long getSampleRate() {
            Log.i(IapController.TAG, "getSampleRate enter.");
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getSampleRate: mIIap is null");
                return -1L;
            }
            if (!this.mConfig.isIapFeatureEnabled(32)) {
                Log.e(IapController.TAG, "getSampleRate failed for iap feature not enabled!");
                return -1L;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getSampleRate failed for session not connected!");
                return -1L;
            }
            long sampleRate = 0;
            try {
                sampleRate = IapController.mIIap.getSampleRate(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getSampleRate: mIIap.getSampleRate: ", ex);
            }
            Log.i(IapController.TAG, "sampleRate=" + sampleRate);
            return sampleRate;
        }

        public int resetMediaList() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "resetMediaList: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "resetMediaList failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "resetMediaList failed for session not connected!");
                return -1;
            }
            int num = -1;
            try {
                num = IapController.mIIap.resetMediaList(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "resetMediaList: mIIap.resetMediaList: ", ex);
            }
            Log.i(IapController.TAG, "resetMediaList num=" + num);
            return num;
        }

        public int selectMediaList(int index, int level) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "selectMediaList: mIIap is null");
                return -1;
            }
            if (index < -1) {
                Log.e(IapController.TAG, "selectMediaList failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(24)) {
                Log.e(IapController.TAG, "selectMediaList failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "selectMediaList failed for session not connected!");
                return -1;
            }
            int num = -1;
            try {
                num = IapController.mIIap.selectMediaList(this.mNativeHandle, index, level);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "selectMediaList: mIIap.selectMediaList: ", ex);
            }
            Log.i(IapController.TAG, "selectMediaList num=" + num);
            return num;
        }

        public MediaListInfo getMediaList(int start, int count) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getMediaList: mIIap is null");
                return null;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "getMediaList failed for iap feature not enabled!");
                return null;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getMediaList failed for session not connected!");
                return null;
            }
            IapMediaListInfo iapMediaListInfo = null;
            try {
                iapMediaListInfo = IapController.mIIap.getMediaList(this.mNativeHandle, start, count);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getMediaList: mIIap.getMediaList: ", ex);
            }
            MediaListInfo mediaListInfo = covertIapToMediaListInfo(iapMediaListInfo);
            Log.i(IapController.TAG, "getMediaList pMediaListInfo=" + mediaListInfo);
            return mediaListInfo;
        }

        public MediaListInfo getSubContentsAllMediaItems(int start, int count) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "getSubContentsAllMediaItems: mIIap is null");
                return null;
            }
            if (!this.mConfig.isIapFeatureEnabled(8)) {
                Log.e(IapController.TAG, "getSubContentsAllMediaItems failed for iap feature not enabled!");
                return null;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "getSubContentsAllMediaItems failed for session not connected!");
                return null;
            }
            IapMediaListInfo iapMediaListInfo = null;
            try {
                iapMediaListInfo = IapController.mIIap.getSubContentsAllMediaItems(this.mNativeHandle, start, count);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "getSubContentsAllMediaItems: mIIap.getSubContentsAllMediaItems: ", ex);
            }
            MediaListInfo mediaListInfo = covertIapToMediaListInfo(iapMediaListInfo);
            Log.i(IapController.TAG, "getSubContentsAllMediaItems pMediaListInfo=" + mediaListInfo);
            return mediaListInfo;
        }

        public int startUsbDeviceModeAudio() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startUsbDeviceModeAudio: mIIap is null");
                return -1;
            }
            if (this.mConfig.getTransportType() != 0) {
                Log.e(IapController.TAG, "startUsbDeviceModeAudio failed for NOT usb device mode session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(64)) {
                Log.e(IapController.TAG, "startUsbDeviceModeAudio failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startUsbDeviceModeAudio failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.startUsbDeviceModeAudio(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startUsbDeviceModeAudio: mIIap.startUsbDeviceModeAudio: ", ex);
            }
            Log.i(IapController.TAG, "startUsbDeviceModeAudio ret=" + ret);
            return ret;
        }

        public int stopUsbDeviceModeAudio() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopUsbDeviceModeAudio: mIIap is null");
                return -1;
            }
            if (this.mConfig.getTransportType() != 0) {
                Log.e(IapController.TAG, "stopUsbDeviceModeAudio failed for NOT usb device mode session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(64)) {
                Log.e(IapController.TAG, "stopUsbDeviceModeAudio failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopUsbDeviceModeAudio failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopUsbDeviceModeAudio(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopUsbDeviceModeAudio: mIIap.stopUsbDeviceModeAudio: ", ex);
            }
            Log.i(IapController.TAG, "stopUsbDeviceModeAudio ret=" + ret);
            return ret;
        }

        public int startCallStateUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startCallStateUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(512)) {
                Log.e(IapController.TAG, "startCallStateUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startCallStateUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.startCallStateUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startCallStateUpdates: mIIap.startCallStateUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startCallStateUpdates ret=" + ret);
            return ret;
        }

        public int stopCallStateUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopCallStateUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(512)) {
                Log.e(IapController.TAG, "stopCallStateUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopCallStateUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopCallStateUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopCallStateUpdates: mIIap.stopCallStateUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopCallStateUpdates ret=" + ret);
            return ret;
        }

        public int initiateCall(InitiateCallType callType, String destId, InitiateCallService callService) throws RemoteException {
            Log.i(IapController.TAG, "initiateCall callType=" + callType + " destId=" + LogEncryption.maskString(destId, 2, 2) + " callService=" + callService);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "initiateCall: mIIap is null");
                return -1;
            }
            if (destId == null || callService == InitiateCallService.CallServiceUnknown) {
                if (callType == InitiateCallType.CallTypeDestination) {
                    Log.e(IapController.TAG, "initiateCall destination call failed for invalid parameter!");
                    return -1;
                }
                destId = "";
            }
            if (!this.mConfig.isIapFeatureEnabled(2048) || !this.mConfig.isIapFeatureOptionEnabled(2)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(2)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "initiateCall failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.initiateCall(this.mNativeHandle, (byte) callType.getCallType(), destId, (byte) callService.getCallService());
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "initiateCall: mIIap.initiateCall: ", ex);
            }
            Log.i(IapController.TAG, "initiateCall ret=" + ret);
            return ret;
        }

        public int acceptCall(CallAcceptAction action, String callUuid) throws RemoteException {
            Log.i(IapController.TAG, "acceptCall acceptAction=" + action + " callUuid=" + LogEncryption.maskString(callUuid, 3, 4));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "acceptCall: mIIap is null");
                return -1;
            }
            if (action == CallAcceptAction.ActionUnknown) {
                Log.e(IapController.TAG, "acceptCall failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "acceptCall failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(4)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "acceptCall failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.acceptCall(this.mNativeHandle, (byte) action.getAcceptAction(), callUuid);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "acceptCall: mIIap.acceptCall: ", ex);
            }
            Log.i(IapController.TAG, "acceptCall ret=" + ret);
            return ret;
        }

        public int endCall(CallEndAction action, String callUuid) throws RemoteException {
            Log.i(IapController.TAG, "endCall endAction=" + action + " callUuid=" + LogEncryption.maskString(callUuid, 3, 4));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "endCall: mIIap is null");
                return -1;
            }
            if (action == CallEndAction.ActionUnknown) {
                Log.e(IapController.TAG, "endCall failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "endCall failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(8)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "endCall failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.endCall(this.mNativeHandle, (byte) action.getEndAction(), callUuid);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "endCall: mIIap.endCall: ", ex);
            }
            Log.i(IapController.TAG, "endCall ret=" + ret);
            return ret;
        }

        public int swapCalls() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "swapCalls: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "swapCalls failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(16)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "swapCalls failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.swapCalls(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "swapCalls: mIIap.swapCalls: ", ex);
            }
            Log.i(IapController.TAG, "swapCalls ret=" + ret);
            return ret;
        }

        public int mergeCalls() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "mergeCalls: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "mergeCalls failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(32)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "mergeCalls failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.mergeCalls(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "mergeCalls: mIIap.mergeCalls: ", ex);
            }
            Log.i(IapController.TAG, "mergeCalls ret=" + ret);
            return ret;
        }

        public int holdStatusUpdate(boolean holdStatus, String callUuid) throws RemoteException {
            Log.i(IapController.TAG, "holdStatusUpdate holdStatus=" + holdStatus + " callUuid=" + LogEncryption.maskString(callUuid, 3, 4));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "holdStatusUpdate: mIIap is null");
                return -1;
            }
            if (callUuid == null) {
                Log.e(IapController.TAG, "holdStatusUpdate failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "holdStatusUpdate failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(64)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "holdStatusUpdate failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.holdStatusUpdate(this.mNativeHandle, holdStatus, callUuid);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "holdStatusUpdate: mIIap.holdStatusUpdate: ", ex);
            }
            Log.i(IapController.TAG, "holdStatusUpdate ret=" + ret);
            return ret;
        }

        public int muteStatusUpdate(boolean muteStatus) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "muteStatusUpdate: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "muteStatusUpdate failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(128)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "muteStatusUpdate failed for session not connected!");
                return -1;
            }
            Log.i(IapController.TAG, "muteStatusUpdate muteStatus=" + muteStatus);
            int ret = -1;
            try {
                ret = IapController.mIIap.muteStatusUpdate(this.mNativeHandle, muteStatus);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "muteStatusUpdate: mIIap.muteStatusUpdate: ", ex);
            }
            Log.i(IapController.TAG, "muteStatusUpdate ret=" + ret);
            return ret;
        }

        public int sendDtmf(DtmfToneType tone, String callUuid) throws RemoteException {
            Log.i(IapController.TAG, "sendDtmf tone=" + tone + " callUuid=" + LogEncryption.maskString(callUuid, 3, 4));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendDtmf: mIIap is null");
                return -1;
            }
            if (tone == DtmfToneType.DtmfToneUnknown || callUuid == null) {
                Log.e(IapController.TAG, "sendDtmf failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2048)) {
                Log.e(IapController.TAG, "sendDtmf failed for iap feature not enabled!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureOptionEnabled(256)) {
                Log.e(IapController.TAG, "initiateCall failed for iap feature option not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendDtmf failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendDtmf(this.mNativeHandle, (byte) tone.getDtmfTone(), callUuid);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendDtmf: mIIap.sendDtmf: ", ex);
            }
            Log.i(IapController.TAG, "sendDtmf ret=" + ret);
            return ret;
        }

        public int startCommunicationsUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startCommunicationsUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(1024)) {
                Log.e(IapController.TAG, "startCommunicationsUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startCommunicationsUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.startCommunicationsUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startCommunicationsUpdates: mIIap.startCommunicationsUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startCommunicationsUpdates ret=" + ret);
            return ret;
        }

        public int stopCommunicationsUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopCommunicationsUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(1024)) {
                Log.e(IapController.TAG, "stopCommunicationsUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopCommunicationsUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopCommunicationsUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopCommunicationsUpdates: mIIap.stopCommunicationsUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopCommunicationsUpdates ret=" + ret);
            return ret;
        }

        public int sendLocationInformation(String nmeaSentence) {
            Log.d(IapController.TAG, "sendLocationInformation nmeaSentence=" + nmeaSentence);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendLocationInformation: mIIap is null");
                return -1;
            }
            if (nmeaSentence == null) {
                Log.e(IapController.TAG, "sendLocationInformation failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(4096)) {
                Log.e(IapController.TAG, "sendLocationInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendLocationInformation failed for session not connected!");
                return -1;
            }
            if (!this.mLocationInformationStarted) {
                Log.e(IapController.TAG, "sendLocationInformation failed as device not start location information!");
                return -1;
            }
            try {
                int ret = IapController.mIIap.sendLocationInformation(this.mNativeHandle, nmeaSentence);
                return ret;
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendLocationInformation: mIIap.sendLocationInformation: ", ex);
                return -1;
            }
        }

        public int sendOobBtPairingAccessoryInformation(long deviceClass) throws RemoteException {
            Log.i(IapController.TAG, "sendOobBtPairingAccessoryInformation deviceClass=" + deviceClass);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendOobBtPairingAccessoryInformation: mIIap is null");
                return -1;
            }
            if (deviceClass < 0) {
                Log.e(IapController.TAG, "sendOobBtPairingAccessoryInformation failed for invalid parameter!");
                return -1;
            }
            if (this.mConfig.getTransportType() != 1) {
                Log.e(IapController.TAG, "sendOobBtPairingAccessoryInformation failed for NOT usb host mode session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(8192)) {
                Log.e(IapController.TAG, "sendOobBtPairingAccessoryInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendOobBtPairingAccessoryInformation failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendOobBtPairingAccessoryInformation(this.mNativeHandle, (int) deviceClass);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendOobBtPairingAccessoryInformation: mIIap.sendOobBtPairingAccessoryInformation: ", ex);
            }
            Log.i(IapController.TAG, "sendOobBtPairingAccessoryInformation ret=" + ret);
            return ret;
        }

        public int sendOobBtPairingCompletionInformation(int resultCode) throws RemoteException {
            Log.i(IapController.TAG, "sendOobBtPairingCompletionInformation resultCode=" + resultCode);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendOobBtPairingCompletionInformation: mIIap is null");
                return -1;
            }
            if (resultCode < 0 || resultCode > 1) {
                Log.e(IapController.TAG, "sendOobBtPairingCompletionInformation failed for invalid parameter!");
                return -1;
            }
            if (this.mConfig.getTransportType() != 1) {
                Log.e(IapController.TAG, "sendOobBtPairingCompletionInformation failed for NOT usb host mode session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(8192)) {
                Log.e(IapController.TAG, "sendOobBtPairingCompletionInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendOobBtPairingCompletionInformation failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendOobBtPairingCompletionInformation(this.mNativeHandle, (byte) resultCode);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendOobBtPairingCompletionInformation: mIIap.sendOobBtPairingCompletionInformation: ", ex);
            }
            Log.i(IapController.TAG, "sendOobBtPairingCompletionInformation ret=" + ret);
            return ret;
        }

        public int sendBluetoothPairingAccessoryInformation(short btTransComId, byte[][] pairingDataP192, byte[][] pairingDataP256) throws RemoteException {
            Log.i(IapController.TAG, "sendBluetoothPairingAccessoryInformation btTransComId=" + ((int) btTransComId) + " pairingDataP192:" + Arrays.toString(pairingDataP192) + " pairingDataP256:" + Arrays.toString(pairingDataP256));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendBluetoothPairingAccessoryInformation: mIIap is null");
                return -1;
            }
            if (pairingDataP192 == null || pairingDataP192[0].length != 16 || pairingDataP192.length != 2) {
                Log.e(IapController.TAG, "sendBluetoothPairingAccessoryInformation failed for invalid parameter pairingDataP192!");
                return -1;
            }
            if (pairingDataP256 != null && (pairingDataP256[0].length != 16 || pairingDataP256.length != 2)) {
                Log.e(IapController.TAG, "sendBluetoothPairingAccessoryInformation failed for invalid parameter pairingDataP256!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2097152)) {
                Log.e(IapController.TAG, "sendBluetoothPairingAccessoryInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendBluetoothPairingAccessoryInformation failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendBluetoothPairingAccessoryInformation(this.mNativeHandle, btTransComId, IapController.ddbyteArrayToArrayList(pairingDataP192), IapController.ddbyteArrayToArrayList(pairingDataP256));
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendBluetoothPairingAccessoryInformation: mIIap.sendBluetoothPairingAccessoryInformation: ", ex);
            }
            Log.i(IapController.TAG, "sendBluetoothPairingAccessoryInformation ret=" + ret);
            return ret;
        }

        public int sendBluetoothPairingStatus(short btTransComId, boolean success, String reason) throws RemoteException {
            Log.i(IapController.TAG, "sendBluetoothPairingStatus btTransComId=" + ((int) btTransComId) + " success:" + success + " reason:" + reason);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendBluetoothPairingStatus: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(2097152)) {
                Log.e(IapController.TAG, "sendBluetoothPairingStatus failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendBluetoothPairingStatus failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendBluetoothPairingStatus(this.mNativeHandle, btTransComId, success, reason == null ? "" : reason);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendBluetoothPairingStatus: mIIap.sendBluetoothPairingAccessoryInformation: ", ex);
            }
            Log.i(IapController.TAG, "sendBluetoothPairingStatus ret=" + ret);
            return ret;
        }

        public int sendAccessoryWiFiConfigurationInformation(String ssid, String passphrase, WifiSecurityType security, WifiChannelType channel) throws RemoteException {
            Log.i(IapController.TAG, "sendAccessoryWiFiConfigurationInformation ssid = " + LogEncryption.maskString(ssid, 2, 2) + " passphrase = " + LogEncryption.maskString(passphrase, 2, 2) + " security = " + security + " channel = " + channel);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendAccessoryWiFiConfigurationInformation: mIIap is null");
                return -1;
            }
            if (ssid == null || passphrase == null || security == WifiSecurityType.WifiSecurityUnknown || channel == WifiChannelType.WifiChannelUnknown) {
                Log.e(IapController.TAG, "sendAccessoryWiFiConfigurationInformation failed for invalid parameter!");
                return -1;
            }
            if (this.mConfig.getTransportType() != 2 && this.mConfig.getTransportType() != 3) {
                Log.e(IapController.TAG, "sendAccessoryWiFiConfigurationInformation failed for NOT Wireless CarPlay session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(16384)) {
                Log.e(IapController.TAG, "sendAccessoryWiFiConfigurationInformation failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionAuthenticated && this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendAccessoryWiFiConfigurationInformation failed for session not ready!");
                return -1;
            }
            HalAccessoryWiFiConfiguration halWifiConfig = new HalAccessoryWiFiConfiguration();
            halWifiConfig.ssid = ssid;
            halWifiConfig.passphrase = passphrase;
            halWifiConfig.securityType = security.getSecurityType();
            halWifiConfig.channel = channel.getChannelType();
            int ret = -1;
            try {
                ret = IapController.mIIap.sendAccessoryWiFiConfigurationInformation(this.mNativeHandle, halWifiConfig);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendAccessoryWiFiConfigInfo: mIIap.sendAccessoryWiFiConfigInfo: ", ex);
            }
            Log.i(IapController.TAG, "sendAccessoryWiFiConfigurationInformation ret=" + ret);
            return ret;
        }

        public int startRouteGuidanceUpdates() {
            return startRouteGuidanceUpdates(null);
        }

        public int startRouteGuidanceUpdates(short[] routeGuidanceComponentIDs) {
            return startRouteGuidanceUpdates(routeGuidanceComponentIDs, false, false, false, false);
        }

        public int startRouteGuidanceUpdates(short[] routeGuidanceComponentIDs, boolean sourceName, boolean supportsRouteGuidance, boolean supportsExitInfo, boolean destinationTimeZoneOffsetMin) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startRouteGuidanceUpdates: mIIap is null");
                return -1;
            }
            if (this.mConfig.getTransportType() != 1 && this.mConfig.getTransportType() != 3) {
                Log.e(IapController.TAG, "startRouteGuidanceUpdates failed for NOT CarPlay session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32768)) {
                Log.e(IapController.TAG, "startRouteGuidanceUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "startRouteGuidanceUpdates failed for session not connected!");
                return -1;
            }
            int parameters = 0;
            if (sourceName) {
                parameters = 0 | 1;
            }
            if (supportsRouteGuidance) {
                parameters |= 2;
            }
            if (supportsExitInfo) {
                parameters |= 4;
            }
            if (destinationTimeZoneOffsetMin) {
                parameters |= 8;
            }
            ArrayList<Short> componentIDs = IapController.shortArrayToArrayList(routeGuidanceComponentIDs);
            int ret = -1;
            try {
                ret = IapController.mIIap.startRouteGuidanceUpdates(this.mNativeHandle, componentIDs, parameters);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startRouteGuidanceUpdates: mIIap.startRouteGuidanceUpdates: ", ex);
            }
            Log.i(IapController.TAG, "startRouteGuidanceUpdates ret=" + ret);
            return ret;
        }

        public int stopRouteGuidanceUpdates() {
            return stopRouteGuidanceUpdates(null);
        }

        public int stopRouteGuidanceUpdates(short[] routeGuidanceComponentIDs) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopRouteGuidanceUpdates: mIIap is null");
                return -1;
            }
            if (this.mConfig.getTransportType() != 1 && this.mConfig.getTransportType() != 3) {
                Log.e(IapController.TAG, "stopRouteGuidanceUpdates failed for NOT CarPlay session!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(32768)) {
                Log.e(IapController.TAG, "stopRouteGuidanceUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopRouteGuidanceUpdates failed for session not connected!");
                return -1;
            }
            ArrayList<Short> componentIDs = IapController.shortArrayToArrayList(routeGuidanceComponentIDs);
            int ret = -1;
            try {
                ret = IapController.mIIap.stopRouteGuidanceUpdates(this.mNativeHandle, componentIDs);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopRouteGuidanceUpdates: mIIap.stopRouteGuidanceUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopRouteGuidanceUpdates ret=" + ret);
            return ret;
        }

        public int identificationInfoUpdate(IdentificationInfo info) throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "identificationInfoUpdate: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_IDENTIFICATION_UPDATE)) {
                Log.e(IapController.TAG, "identificationInfoUpdate failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "identificationInfoUpdate failed for session not connected!");
                return -1;
            }
            if (!checkIdentificationInfo(info)) {
                Log.e(IapController.TAG, "identificationInfoUpdate string must not null or length is 0");
                return -1;
            }
            HalIdentificationInfo halInfo = convertToHalIdentificationInfo(info);
            int ret = -1;
            try {
                ret = IapController.mIIap.identificationInfoUpdate(this.mNativeHandle, halInfo);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "identificationInfoUpdate: mIIap.identificationInfoUpdate: ", ex);
            }
            Log.i(IapController.TAG, "identificationInfoUpdate ret=" + ret);
            return ret;
        }

        private boolean checkIdentificationInfo(IdentificationInfo info) {
            if (TextUtils.isEmpty(info.getName()) || TextUtils.isEmpty(info.getSerialNumber()) || TextUtils.isEmpty(info.getModelIdentifier()) || TextUtils.isEmpty(info.getManufacturer()) || TextUtils.isEmpty(info.getHardwareVersion()) || TextUtils.isEmpty(info.getFirmwareVersion()) || TextUtils.isEmpty(info.getCurrentLanguage())) {
                return false;
            }
            return true;
        }

        private HalIdentificationInfo convertToHalIdentificationInfo(IdentificationInfo info) {
            if (info == null) {
                Log.e(IapController.TAG, "convertToHalIdentificationInfo IdentificationInfo is null");
                return null;
            }
            HalIdentificationInfo halInfo = new HalIdentificationInfo();
            halInfo.name = info.getName();
            halInfo.modelIdentifier = info.getModelIdentifier();
            halInfo.manufacturer = info.getManufacturer();
            halInfo.serialNumber = info.getSerialNumber();
            halInfo.firmwareVersion = info.getFirmwareVersion();
            halInfo.hardwareVersion = info.getHardwareVersion();
            halInfo.currentLanguage = info.getCurrentLanguage();
            return halInfo;
        }

        public int sendEapData(int sessionId, byte[] data) throws RemoteException {
            String str = IapController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("sendEapData sessionId=");
            sb.append(sessionId);
            sb.append(" data len=");
            sb.append(data == null ? 0 : data.length);
            Log.i(str, sb.toString());
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendEapData: mIIap is null");
                return -1;
            }
            if (sessionId < 0 || data == null || data.length == 0) {
                Log.e(IapController.TAG, "sendEapData failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(65536)) {
                Log.e(IapController.TAG, "sendEapData failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendEapData failed for session not connected!");
                return -1;
            }
            if (!isActiveEapSession(sessionId)) {
                Log.e(IapController.TAG, "sendEapData failed for inactive eap session!");
                return -1;
            }
            ArrayList<Byte> halDataList = IapController.byteArrayToArrayList(data);
            int ret = -1;
            try {
                ret = IapController.mIIap.sendEapData(this.mNativeHandle, (short) sessionId, halDataList);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendEapData: mIIap.sendEapData: ", ex);
            }
            Log.i(IapController.TAG, "sendEapData ret=" + ret);
            return ret;
        }

        public int sendEapSessionStatus(int sessionId, int status) throws RemoteException {
            Log.i(IapController.TAG, "sendEapSessionStatus sessionId=" + sessionId + " status=" + status);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendEapSessionStatus: mIIap is null");
                return -1;
            }
            if (sessionId < 0 || status < 0 || status > 1) {
                Log.e(IapController.TAG, "sendEapSessionStatus failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(65536)) {
                Log.e(IapController.TAG, "sendEapSessionStatus failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "sendEapSessionStatus failed for session not connected!");
                return -1;
            }
            if (!isActiveEapSession(sessionId)) {
                Log.e(IapController.TAG, "sendEapSessionStatus failed for inactive eap session!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.sendEapSessionStatus(this.mNativeHandle, (short) sessionId, (byte) status);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "sendEapSessionStatus: mIIap.sendEapSessionStatus: ", ex);
            }
            Log.i(IapController.TAG, "sendEapSessionStatus ret=" + ret);
            return ret;
        }

        public int requestAppLaunch(String appBundleId, int appLaunchMethod) throws RemoteException {
            Log.i(IapController.TAG, "requestAppLaunch appBundleId=" + LogEncryption.maskString(appBundleId, 2, 3) + " appLaunchMethod=" + appLaunchMethod);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "requestAppLaunch: mIIap is null");
                return -1;
            }
            if (appBundleId == null || appLaunchMethod < 0 || appLaunchMethod > 1) {
                Log.e(IapController.TAG, "requestAppLaunch failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(131072)) {
                Log.e(IapController.TAG, "requestAppLaunch failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "requestAppLaunch failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.requestAppLaunch(this.mNativeHandle, appBundleId, (byte) appLaunchMethod);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "requestAppLaunch: mIIap.requestAppLaunch: ", ex);
            }
            Log.i(IapController.TAG, "requestAppLaunch ret=" + ret);
            return ret;
        }

        public int requestAppDiscoveryAppIcons(String appbundleId) throws RemoteException {
            Log.i(IapController.TAG, "requestAppDiscoveryAppIcons appbundleId=" + LogEncryption.maskString(appbundleId, 2, 3));
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "requestAppDiscoveryAppIcons: mIIap is null");
                return -1;
            }
            if (appbundleId == null) {
                Log.e(IapController.TAG, "requestAppDiscoveryAppIcons failed for invalid parameter!");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_CARPLAY_APP_DISCOVERY)) {
                Log.e(IapController.TAG, "requestAppDiscoveryAppIcons failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "requestAppDiscoveryAppIcons failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.requestAppDiscoveryAppIcons(this.mNativeHandle, appbundleId);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "RequestAppDiscoveryAppIcons: mIIap.requestAppDiscoveryAppIcons: ", ex);
            }
            Log.i(IapController.TAG, "RequestAppDiscoveryAppIcons ret=" + ret);
            return ret;
        }

        public int startAppDiscoveryUpdates(CarplayAppDiscoveryInfo info) {
            Log.i(IapController.TAG, "startAppDiscoveryUpdates info=" + info);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "startAppDiscoveryUpdates: mIIap is null");
                return -1;
            }
            if (info == null || !info.checkValidity()) {
                Log.e(IapController.TAG, "startAppDiscoveryUpdates info is not valid:" + info);
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_CARPLAY_APP_DISCOVERY)) {
                Log.e(IapController.TAG, "startAppDiscoveryUpdates failed for iap feature not enabled!");
                return -1;
            }
            HalCarplayAppDiscoveryInfo halInfo = carplayAppDiscoveryInfo2HalCarplayAppDiscoveryInfo(info);
            if (halInfo == null) {
                Log.e(IapController.TAG, "startAppDiscoveryUpdates failed for HalCarplayAppDiscoveryInfo");
                return -1;
            }
            try {
                int ret = IapController.mIIap.startAppDiscoveryUpdates(this.mNativeHandle, halInfo);
                return ret;
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "startAppDiscoveryUpdates: mIIap.startAppDiscoveryUpdates: ", ex);
                return -1;
            }
        }

        private HalCarplayAppDiscoveryInfo carplayAppDiscoveryInfo2HalCarplayAppDiscoveryInfo(CarplayAppDiscoveryInfo info) {
            HalCarplayAppDiscoveryInfo halInfo = new HalCarplayAppDiscoveryInfo();
            halInfo.carPlayAppCategories = info.getAppCategories();
            halInfo.carPlayAppListMax = (short) info.getCarPlayAppListMax();
            halInfo.carPlayAppIconSize = (short) info.getCarPlayAppIconSize();
            return halInfo;
        }

        public int stopAppDiscoveryUpdates() throws RemoteException {
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "stopAppDiscoveryUpdates: mIIap is null");
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(IapConfig.IAP_FEATURE_CARPLAY_APP_DISCOVERY)) {
                Log.e(IapController.TAG, "stopAppDiscoveryUpdates failed for iap feature not enabled!");
                return -1;
            }
            if (this.mSessionStatus != IapSessionStatus.IapSessionConnected) {
                Log.e(IapController.TAG, "stopAppDiscoveryUpdates failed for session not connected!");
                return -1;
            }
            int ret = -1;
            try {
                ret = IapController.mIIap.stopAppDiscoveryUpdates(this.mNativeHandle);
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "stopAppDiscoveryUpdates: mIIap.stopAppDiscoveryUpdates: ", ex);
            }
            Log.i(IapController.TAG, "stopAppDiscoveryUpdates ret=" + ret);
            return ret;
        }

        public int sendCarplayStartSession(CarplayStartSessionInfo info) {
            Log.i(IapController.TAG, "sendCarplayStartSession info=" + info);
            if (IapController.mIIap == null) {
                Log.e(IapController.TAG, "sendCarplayStartSession: mIIap is null");
                return -1;
            }
            if (info == null || !info.checkValidity()) {
                Log.e(IapController.TAG, "sendCarplayStartSession info is not valid:" + info);
                return -1;
            }
            if (!this.mConfig.isIapFeatureEnabled(4194304)) {
                Log.e(IapController.TAG, "sendCarplayStartSession failed for iap feature not enabled!");
                return -1;
            }
            HalCarplayStartSessionInfo halInfo = carplayStartSessionInfo2HalCarplayStartSessionInfo(info);
            if (halInfo == null) {
                Log.e(IapController.TAG, "sendCarplayStartSession failed for carplayStartSessionInfo2HalCarplayStartSessionInfo");
                return -1;
            }
            try {
                int ret = IapController.mIIap.sendCarplayStartSession(this.mNativeHandle, halInfo);
                return ret;
            } catch (RemoteException ex) {
                Log.e(IapController.TAG, "requestAppLaunch: mIIap.requestAppLaunch: ", ex);
                return -1;
            }
        }

        private HalCarplayStartSessionInfo carplayStartSessionInfo2HalCarplayStartSessionInfo(CarplayStartSessionInfo info) {
            HalCarplayStartSessionInfo halInfo = new HalCarplayStartSessionInfo();
            halInfo.deviceIdentifier = info.getDeviceIdentifier();
            halInfo.port = info.getPort();
            halInfo.publicKey = info.getPublicKey();
            halInfo.sourceVersion = info.getSourceVersion();
            HalWiredAttribute halWiredAttr = halInfo.wiredAttribute;
            HalWirelessAttribute halWirelessAttr = halInfo.wirelessAttribute;
            if (info.getWiredAttribute() != null) {
                halWiredAttr.exsits = true;
                halWiredAttr.ipAddress = info.getWiredAttribute().getIpAddress();
            } else {
                halWiredAttr.exsits = false;
            }
            if (info.getWirelessAttribute() != null) {
                halWirelessAttr.exsits = true;
                halWirelessAttr.ipAddress = info.getWirelessAttribute().getIpAddress();
                halWirelessAttr.wifiSsid = info.getWirelessAttribute().getWifiSsid();
                halWirelessAttr.passphrase = info.getWirelessAttribute().getPassphrase();
                halWirelessAttr.channel = (short) info.getWirelessAttribute().getChannel().getChannelType();
                if (info.getWirelessAttribute().getSecurityType() == null) {
                    info.getWirelessAttribute().setSecurityType(WifiSecurityType.WifiSecurityUnknown);
                }
                halWirelessAttr.securityType = (byte) info.getWirelessAttribute().getSecurityType().getSecurityType();
            } else {
                halWirelessAttr.exsits = false;
            }
            return halInfo;
        }
    }

    private class IapCallback extends IIapCallback.Stub {
        private IapCallback() {
        }

        @Override // vendor.ts.iap.V1_0.IIapCallback
        public void onIapCallback(long iapHandle, int feature, int type, ArrayList<Byte> data) {
            Log.d(IapController.TAG, "hal Handle = " + iapHandle + "onIapCallback feature = " + feature + " type = " + type);
            byte[] dataInfo = IapController.byteArrayFromArrayList(data);
            IapController.mIapController.mSessionsLock.readLock().lock();
            try {
                IapSession target = (IapSession) IapController.mIapController.mSessions.get(iapHandle);
                if (target != null) {
                    target.onIapNotification(feature, type, dataInfo);
                } else {
                    Log.e(IapController.TAG, "onIapNotification failed for not finding target session!");
                }
                Log.d(IapController.TAG, "onIapCallback end");
            } catch (Exception ex) {
                Log.e(IapController.TAG, "Failed to find session of handle " + iapHandle);
                Log.e(IapController.TAG, "Exception: " + ex.toString());
            } finally {
                IapController.mIapController.mSessionsLock.readLock().unlock();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String bytes2HexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte val : data) {
            String hex = Integer.toHexString(val & 255);
            if (hex.length() < 2) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Byte> byteArrayToArrayList(byte[] bytes) {
        ArrayList<Byte> byteList = new ArrayList<>();
        for (byte b : bytes) {
            Byte b2 = Byte.valueOf(b);
            byteList.add(b2);
        }
        return byteList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] byteArrayFromArrayList(ArrayList<Byte> bytes) {
        byte[] byteArray = new byte[bytes.size()];
        int index = 0;
        for (Byte b : bytes) {
            byteArray[index] = b.byteValue();
            index++;
        }
        return byteArray;
    }

    private static ArrayList<String> stringArrayToArrayList(String[] strings) {
        ArrayList<String> stringList = new ArrayList<>();
        for (String b : strings) {
            stringList.add(b);
        }
        return stringList;
    }

    private static String[] stringArrayFromArrayList(ArrayList<String> strings) {
        String[] stringArray = new String[strings.size()];
        int index = 0;
        for (String b : strings) {
            stringArray[index] = b;
            index++;
        }
        return stringArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Byte> ddbyteArrayToArrayList(byte[][] pairingdata) {
        ArrayList<Byte> byteList = new ArrayList<>();
        if (pairingdata == null) {
            return byteList;
        }
        for (int i = 0; i < pairingdata.length; i++) {
            for (int j = 0; j < pairingdata[i].length; j++) {
                byteList.add(Byte.valueOf(pairingdata[i][j]));
            }
        }
        return byteList;
    }

    private static byte[][] ddbyteArrayFromArrayList(ArrayList<Byte> pairingdata) {
        byte[][] byteArray = (byte[][]) Array.newInstance((Class<?>) byte.class, 2, 16);
        for (int i = 0; i < pairingdata.size(); i++) {
            byteArray[i / 16][i % 16] = pairingdata.get(i).byteValue();
        }
        return byteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Short> shortArrayToArrayList(short[] shorts) {
        ArrayList<Short> shortList = new ArrayList<>();
        if (shorts == null) {
            return shortList;
        }
        for (short s : shorts) {
            Short s2 = Short.valueOf(s);
            shortList.add(s2);
        }
        return shortList;
    }

    private HalIapConfig constructHalIapConfig(IapConfig config) {
        HalIapConfig halIapConfig = new HalIapConfig();
        halIapConfig.deviceId = config.getDeviceId();
        halIapConfig.genericInfo.ppUuid = config.getProductPlanUuid();
        if (halIapConfig.genericInfo.ppUuid == null) {
            halIapConfig.genericInfo.ppUuid = " ";
        }
        halIapConfig.genericInfo.featureMask = config.getIapFeature();
        halIapConfig.genericInfo.featureOption = config.getIapFeatureOption();
        halIapConfig.genericInfo.playbackHidMask = config.getPlaybackHid();
        halIapConfig.genericInfo.nmeaSentenceMask = config.getNmeaSentence();
        halIapConfig.genericInfo.transportType = config.getTransportType();
        halIapConfig.genericInfo.deviceNode = config.getDeviceNode();
        halIapConfig.genericInfo.iapAppMode = config.getIapAppMode();
        halIapConfig.genericInfo.ncmInterfaceNum = config.getNcmInterfaceNum();
        halIapConfig.genericInfo.isWirelessCarPlayActive = config.getIsWirelessCarPlayActive();
        halIapConfig.genericInfo.BtMacAddress = config.getBtMacAddress();
        halIapConfig.genericInfo.playbackQueueListAvailable = config.getPlaybackQueueListAvailable();
        halIapConfig.genericInfo.isVehicleInformationComponentDeleted = config.getIsVehicleInformationComponentDeleted();
        halIapConfig.accessoryInfo.accessoryName = config.getAccessoryName();
        halIapConfig.accessoryInfo.accessoryModel = config.getAccessoryModel();
        halIapConfig.accessoryInfo.manufacturer = config.getManufacturer();
        halIapConfig.accessoryInfo.serialNumber = config.getSerialNumber();
        halIapConfig.accessoryInfo.currentLang = config.getCurrentLang();
        String[] temp = config.getSupportedLangList();
        for (String str : temp) {
            halIapConfig.accessoryInfo.supportedLangList.add(str);
        }
        halIapConfig.accessoryInfo.supportedLangNum = halIapConfig.accessoryInfo.supportedLangList.size();
        halIapConfig.accessoryInfo.fwVersion = config.getFwVersion();
        halIapConfig.accessoryInfo.hwVersion = config.getHwVersion();
        halIapConfig.accessoryInfo.vid = (short) config.getVid();
        halIapConfig.accessoryInfo.pid = (short) config.getPid();
        halIapConfig.accessoryInfo.availCurrent = (short) config.getAvailableCurrent();
        if (config.getTransportType() == 0 || config.getTransportType() == 1) {
            halIapConfig.accessoryInfo.powerProvidingCapability = (byte) config.getPowerProvidingCapability().getValue();
        } else {
            halIapConfig.accessoryInfo.powerProvidingCapability = (byte) PowerProvidingCapability.None.getValue();
        }
        halIapConfig.vehicleInfo.engineType = config.getEngineType();
        halIapConfig.vehicleInfo.displayName = config.getDisplayName();
        if ((config.getIapFeature() & IapConfig.IAP_FEATURE_DESTINATION_INFORMATION) != 0) {
            halIapConfig.vehicleInfo.mapsDisplayName = config.getMapsDisplayName();
        }
        if ((config.getIapFeature() & IapConfig.IAP_FEATURE_VEHICLE_STATUS) != 0) {
            halIapConfig.vehicleInfo.vehicleColorHexCode = config.getVehicleColorHexCode();
            halIapConfig.vehicleInfo.supportedChargingConnectors = (byte) config.getSupportedChargingConnectors();
            Log.d(TAG, "SupportedChargingConnectors =  " + ((int) halIapConfig.vehicleInfo.supportedChargingConnectors));
            long[] tempList = config.getPowerForConnectorTypeList();
            for (int i = 0; i < tempList.length; i++) {
                halIapConfig.vehicleInfo.powerForConnectorTypeList.add(Integer.valueOf((int) tempList[i]));
                Log.d(TAG, "PowerForConnectorTypeList[" + i + "] =  " + tempList[i]);
                Log.d(TAG, "HalPowerForConnectorTypeList[" + i + "] =  " + halIapConfig.vehicleInfo.powerForConnectorTypeList.get(i));
            }
            halIapConfig.vehicleStatus.identifier = (short) config.getVehicleStatusIdentifier();
            Log.d(TAG, "VehicleStatusIdentifier =  " + ((int) halIapConfig.vehicleStatus.identifier));
            halIapConfig.vehicleStatus.name = config.getVehicleStatusName();
        }
        if (config.getRouteGuidanceInfo() != null && config.getRouteGuidanceInfo().size() != 0) {
            int size = (byte) config.getRouteGuidanceInfo().size();
            ArrayList<IapConfig.RouteGuidanceInfo> routeGuidanceInfoList = config.getRouteGuidanceInfo();
            for (int index = 0; index < size; index++) {
                RouteGuidanceInfo routeGuidanceInfo = new RouteGuidanceInfo();
                routeGuidanceInfo.routeGuidanceDisplayIdentifier = (short) routeGuidanceInfoList.get(index).getRouteGuidanceDisplayIdentifier();
                routeGuidanceInfo.routeGuidanceDisplayName = routeGuidanceInfoList.get(index).getRouteGuidanceDisplayName();
                routeGuidanceInfo.maxCurrentRoadNameLength = (short) routeGuidanceInfoList.get(index).getMaxCurrentRoadNameLength();
                routeGuidanceInfo.maxDestinationNameLength = (short) routeGuidanceInfoList.get(index).getMaxDestinationNameLength();
                routeGuidanceInfo.maxAfterManeuverRoadNameLength = (short) routeGuidanceInfoList.get(index).getMaxAfterManeuverRoadNameLength();
                routeGuidanceInfo.maxManeuverDescriptionLength = (short) routeGuidanceInfoList.get(index).getMaxManeuverDescriptionLength();
                routeGuidanceInfo.maxGuidanceManeuverCapacity = (short) routeGuidanceInfoList.get(index).getMaxGuidanceManeuverCapacity();
                routeGuidanceInfo.maxLaneGuidanceDescriptionLength = (short) routeGuidanceInfoList.get(index).getMaxLaneGuidanceDescriptionLength();
                routeGuidanceInfo.maxLaneGuidanceStorageCapacity = (short) routeGuidanceInfoList.get(index).getMaxLaneGuidanceStorageCapacity();
                halIapConfig.routeGuidanceInfoList.add(routeGuidanceInfo);
            }
        }
        if (config.getEapInfoList() != null && config.getEapInfoList().size() != 0) {
            halIapConfig.eapInfoNum = config.getEapInfoList().size();
            ArrayList<IapConfig.EapInfo> eapInfoList = config.getEapInfoList();
            for (int index2 = 0; index2 < halIapConfig.eapInfoNum; index2++) {
                EapInfo eapInfo = new EapInfo();
                eapInfo.eapIdentifier = (byte) eapInfoList.get(index2).getEapIdentifier();
                eapInfo.eapName = eapInfoList.get(index2).getEapName();
                eapInfo.eapMatchAction = (byte) eapInfoList.get(index2).getEapMatchAction();
                eapInfo.isNativeTransport = eapInfoList.get(index2).getIsNativeTransport();
                eapInfo.nativeTransportId = (short) eapInfoList.get(index2).getNativeTransportId();
                eapInfo.isEapCarPlay = eapInfoList.get(index2).getIsEapCarPlay();
                halIapConfig.eapInfoList.add(eapInfo);
            }
        }
        return halIapConfig;
    }
}
