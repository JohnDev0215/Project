package com.ts.carplay.linkadapter.iap;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ts.carplay.adapter.common.BaseAdapter;
import com.ts.carplay.common.data.CallStateInfo;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.NowPlayingInfo;
import com.ts.carplay.common.data.PlaybackMediaItemInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.RouteGuidanceInfo;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.linkadapter.iap.data.AccessoryWiFiInfo;
import com.ts.carplay.linkadapter.iap.data.CarplayStartSessionInfo;
import com.ts.carplay.linkadapter.iap.data.IapConfig;
import com.ts.carplay.linkadapter.iap.data.IapSessionStatus;
import com.ts.carplay.linkadapter.iap.data.WiredAttributes;
import com.ts.carplay.linkadapter.iap.data.WirelessAttributes;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.iap.protocol.IapController;
import ts.car.iap.protocol.IapListener;
import ts.car.iap.protocol.IapServiceListener;
import ts.car.iap.protocol.common.CallAcceptAction;
import ts.car.iap.protocol.common.CallEndAction;
import ts.car.iap.protocol.common.CallStateUpdate;
import ts.car.iap.protocol.common.CarplayAvialability;
import ts.car.iap.protocol.common.CommunicationsUpdate;
import ts.car.iap.protocol.common.DtmfToneType;
import ts.car.iap.protocol.common.FavoriteListInfo;
import ts.car.iap.protocol.common.InitiateCallService;
import ts.car.iap.protocol.common.InitiateCallType;
import ts.car.iap.protocol.common.LaneGuidanceInformation;
import ts.car.iap.protocol.common.LaneInformation;
import ts.car.iap.protocol.common.LaneStatus;
import ts.car.iap.protocol.common.MediaLibraryUpdate;
import ts.car.iap.protocol.common.NowPlayingPbMediaItemInfo;
import ts.car.iap.protocol.common.NowPlayingUpdate;
import ts.car.iap.protocol.common.PairingState;
import ts.car.iap.protocol.common.PowerUpdate;
import ts.car.iap.protocol.common.RecentListInfo;
import ts.car.iap.protocol.common.RouteGuidanceManeuverUpdate;
import ts.car.iap.protocol.common.RouteGuidanceUpdate;
import ts.car.iap.protocol.common.WifiChannelType;
import ts.car.iap.protocol.common.WifiSecurityType;

/* JADX INFO: loaded from: classes.dex */
public class IapAdapter extends BaseAdapter implements IIapAdapter {
    private static final int ADVANCE_RATE_S_TO_MS = 1000;
    private static final int IAP_SERVICE_AVAILABLE = 1;
    private static final int IAP_SERVICE_NOT_AVAILABLE = 0;
    private static final int INDEX_CARPLAY_BT = 1;
    private static final int INDEX_CARPLAY_USB_DEVICE = 3;
    private static final int INDEX_CARPLAY_USB_HOST = 0;
    private static final int INDEX_CARPLAY_WIFI = 2;
    private static final int INDEX_INVALID = -1;
    private static final int INDEX_NUM = 4;
    private static final int IS_PART_CONFERENCE = 1;
    private static final int LIST_AVAILABLE = 1;
    private static final int MAX_AFTER_MANEUVER_ROAD_NAME_LENTH = 128;
    private static final int MAX_ARTWORK_PATH_INDEX = 5;
    private static final int MAX_GUIDANCE_MANEUVER_CAPACITY = 1;
    private static final int NEW_WIRELESS_CARPLAY_CONNECT = 1;
    private static final int NEW_WIRE_CARPLAY_CONNECT = 0;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final String TAG = IapAdapter.class.getSimpleName();
    private static final int UPDATE_NOWPLAYING_FREQUENCY = 1000;
    private String mArtworkFilePath;
    private int mArtworkPathIndex;
    private CallStateInfo mCallStateInfo;
    private Context mContext;
    private int mCurClientType;
    private List<FavoriteContactListInfo> mFavoriteContactListInfo;
    private int mFavoriteListAvailable;
    private int mFavoriteListCount;
    private volatile boolean mHasStartedCallState;
    private volatile boolean mHasStartedContactList;
    private volatile boolean mHasStartedNowPlaying;
    private volatile boolean mHasStartedRouteGuidance;
    private IapController mIapController;
    private IapListener mIapListenerImpl;
    private IapServiceListener mIapServiceListenerImpl;
    private boolean mIsBtA2dpConnected;
    private boolean mIsNeedStartCallState;
    private boolean mIsNeedStartContactList;
    private boolean mIsNeedStartNowPlaying;
    private boolean mIsNeedStartRouteGuidance;
    private IIapAdapterListener mListener;
    private IapSessionInfo[] mLists;
    private Object mLock;
    private Map<IapController.IapSession, String> mMap;
    private int mNewCarPlayConnectType;
    private NowPlayingInfo mNowPlayingInfo;
    private List<RecentContactListInfo> mRecentContactListInfo;
    private int mRecentListAvailable;
    private int mRecentListCount;
    private RouteGuidanceInfo mRouteGuidanceInfo;
    private Object mSynchronizedCallState;
    private Object mSynchronizedContactList;
    private Object mSynchronizedFavoriteContactList;
    private Object mSynchronizedNowPlaying;
    private long mSynchronizedOffsets;
    private Object mSynchronizedRecentContactList;
    private Object mSynchronizedRouteGuidance;
    private int mTimeZoneOffsetMinutes;

    public IapAdapter(Looper looper) {
        super(looper);
        this.mRecentListAvailable = 0;
        this.mFavoriteListAvailable = 0;
        this.mRecentListCount = -1;
        this.mFavoriteListCount = -1;
        this.mCurClientType = 153;
        this.mHasStartedCallState = false;
        this.mHasStartedRouteGuidance = false;
        this.mHasStartedNowPlaying = false;
        this.mHasStartedContactList = false;
        this.mIsNeedStartCallState = false;
        this.mIsNeedStartRouteGuidance = false;
        this.mIsNeedStartNowPlaying = false;
        this.mIsNeedStartContactList = false;
        this.mNewCarPlayConnectType = 0;
        this.mIapController = null;
        this.mContext = null;
        this.mMap = new ConcurrentHashMap();
        this.mLists = new IapSessionInfo[4];
        this.mRouteGuidanceInfo = new RouteGuidanceInfo();
        this.mCallStateInfo = new CallStateInfo();
        this.mNowPlayingInfo = new NowPlayingInfo();
        this.mRecentContactListInfo = new ArrayList();
        this.mFavoriteContactListInfo = new ArrayList();
        this.mSynchronizedOffsets = 0L;
        this.mLock = new Object();
        this.mSynchronizedRouteGuidance = new Object();
        this.mSynchronizedCallState = new Object();
        this.mSynchronizedNowPlaying = new Object();
        this.mSynchronizedContactList = new Object();
        this.mSynchronizedRecentContactList = new Object();
        this.mSynchronizedFavoriteContactList = new Object();
        this.mIsBtA2dpConnected = false;
        this.mTimeZoneOffsetMinutes = 0;
        this.mIapServiceListenerImpl = new IapServiceListenerImpl();
        this.mIapListenerImpl = new IapListenerImpl(this, null);
        this.mArtworkPathIndex = 0;
        this.mArtworkFilePath = "storage/emulated/0/multimedia/carplay/";
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr != null) {
            iapSessionInfoArr[0] = new IapSessionInfo(17);
            this.mLists[1] = new IapSessionInfo(18);
            this.mLists[2] = new IapSessionInfo(19);
            this.mLists[3] = new IapSessionInfo(16);
        }
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void init(Context context) {
        LogUtil.info(TAG, "IapAdapter init context:" + context);
        boolean deleteFlag = deleteArtWorkPathDirectory(this.mArtworkFilePath);
        LogUtil.debug(TAG, "Artwork init deleteFlag" + deleteFlag);
        this.mContext = context;
        IapController iapController = IapController.getInstance(context, this.mIapServiceListenerImpl);
        this.mIapController = iapController;
        if (iapController == null) {
            LogUtil.warning(TAG, "init faild, mIapController is null.");
        }
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void uninit() {
    }

    public class IapServiceListenerImpl implements IapServiceListener {
        public IapServiceListenerImpl() {
        }

        @Override // ts.car.iap.protocol.IapServiceListener
        public int onServiceStatusChanged(int status) {
            LogUtil.debug(IapAdapter.TAG, "onServiceStatusChanged: status = " + status);
            if (status == 0) {
                IapAdapter iapAdapter = IapAdapter.this;
                int index = iapAdapter.convertClientTypeToIndex(iapAdapter.mCurClientType);
                if (-1 == index) {
                    LogUtil.warning(IapAdapter.TAG, "destroySession clientType:" + IapAdapter.this.mCurClientType + " invalid.");
                    return 0;
                }
                IapSessionInfo targetInfo = IapAdapter.this.mLists[index];
                if (targetInfo != null) {
                    synchronized (IapAdapter.this.mLists[index]) {
                        IapAdapter.this.mLists[index].setIapSession(null);
                        IapAdapter.this.mLists[index].setUuid(null);
                    }
                }
                IapAdapter.this.resetIapStates();
                if (IapAdapter.this.mListener != null) {
                    IapAdapter.this.mListener.onIapSessionStatusChanged(IapAdapter.this.mCurClientType, IapSessionStatus.IapSessionDisconnected);
                } else {
                    LogUtil.warning(IapAdapter.TAG, "onServiceStatusChanged mListener is null.");
                }
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class IapListenerImpl implements IapListener {
        private IapListenerImpl() {
        }

        /* synthetic */ IapListenerImpl(IapAdapter x0, AnonymousClass1 x1) {
            this();
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onIapSessionStatusChanged(IapController.IapSession session, final ts.car.iap.protocol.common.IapSessionStatus status) {
            final int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onIapSessionStatusChanged session:" + session + " invalid.");
                IapAdapter.this.mCurClientType = 153;
                IapAdapter.this.mHasStartedCallState = false;
                IapAdapter.this.mHasStartedRouteGuidance = false;
                IapAdapter.this.mHasStartedNowPlaying = false;
                IapAdapter.this.mHasStartedContactList = false;
                return -1;
            }
            if (status == ts.car.iap.protocol.common.IapSessionStatus.IapSessionConnected && (17 == clientType || 19 == clientType)) {
                IapAdapter.this.post(new Runnable() { // from class: com.ts.carplay.linkadapter.iap.-$$Lambda$IapAdapter$IapListenerImpl$1WnFxF3rNioJGSlsdcTvkwJIKRg
                    @Override // java.lang.Runnable
                    public final void run() throws RemoteException {
                        this.f$0.lambda$onIapSessionStatusChanged$0$IapAdapter$IapListenerImpl(clientType, status);
                    }
                });
            }
            if (IapAdapter.this.mListener != null) {
                LogUtil.debug(IapAdapter.TAG, "onIapSessionStatusChanged status:" + status.getStatusValue());
                IapAdapter.this.mListener.onIapSessionStatusChanged(clientType, IapSessionStatus.fromStatusValue(status.getStatusValue()));
            } else {
                LogUtil.warning(IapAdapter.TAG, "onIapSessionStatusChanged mListener is null.");
            }
            return 0;
        }

        public /* synthetic */ void lambda$onIapSessionStatusChanged$0$IapAdapter$IapListenerImpl(int clientType, ts.car.iap.protocol.common.IapSessionStatus status) throws RemoteException {
            if (IapAdapter.this.mCurClientType == 153) {
                IapAdapter.this.mCurClientType = clientType;
                LogUtil.debug(IapAdapter.TAG, "mCurClientType: " + IapAdapter.this.mCurClientType);
            }
            LogUtil.debug(IapAdapter.TAG, "startUpdates, status: " + status + ", clientType: " + clientType);
            IapAdapter iapAdapter = IapAdapter.this;
            iapAdapter.setCallStateUpdates(iapAdapter.mIsNeedStartCallState);
            IapAdapter iapAdapter2 = IapAdapter.this;
            iapAdapter2.setRouteGuidanceUpdates(iapAdapter2.mIsNeedStartRouteGuidance);
            IapAdapter iapAdapter3 = IapAdapter.this;
            iapAdapter3.setNowPlayingUpdates(iapAdapter3.mIsNeedStartNowPlaying);
            IapAdapter iapAdapter4 = IapAdapter.this;
            iapAdapter4.setContactListUpdates(iapAdapter4.mIsNeedStartContactList);
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onDeviceInformationUpdate(IapController.IapSession session, String deviceName) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onDeviceInformationUpdate session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onDeviceInformationUpdate(clientType, deviceName);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onDeviceInformationUpdate mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onDeviceTransportIdentifierNotification(IapController.IapSession session, String btTransportIdentifier, String usbTransportIdentifier) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onDeviceTransportIdentifierNotification session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onDeviceTransportIdentifierNotification(clientType, btTransportIdentifier, usbTransportIdentifier);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onDeviceTransportIdentifierNotification mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onRequestAccessoryWiFiConfigurationInformation(IapController.IapSession session) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onRequestAccessoryWiFiConfigurationInformation session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onRequestAccessoryWiFiConfigurationInformation(clientType);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onRequestAccessoryWiFiConfigurationInformation mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onWirelessCarPlayUpdate(IapController.IapSession session, boolean wirelessCarPlayAvailable) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onWirelessCarPlayUpdate session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onWirelessCarPlayUpdate(clientType, wirelessCarPlayAvailable);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onWirelessCarPlayUpdate mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onDeviceTimeUpdate(IapController.IapSession session, long secondsSinceReferenceDate, int timeZoneOffsetMinutes, int daylightSavingsOffsetMinutes) {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (IapAdapter.this.mLock) {
                if (currentTimeMillis > 0) {
                    IapAdapter.this.mSynchronizedOffsets = secondsSinceReferenceDate - (currentTimeMillis / 1000);
                    IapAdapter.this.mTimeZoneOffsetMinutes = timeZoneOffsetMinutes;
                    LogUtil.debug(IapAdapter.TAG, "onDeviceTimeUpdate Offsets:" + IapAdapter.this.mSynchronizedOffsets + " secondsSinceReferenceDate:" + secondsSinceReferenceDate + " timeZoneOffsetMinutes:" + timeZoneOffsetMinutes + " daylightSavingsOffsetMinutes:" + daylightSavingsOffsetMinutes + " currentTimeMillis:" + currentTimeMillis);
                } else {
                    IapAdapter.this.mTimeZoneOffsetMinutes = timeZoneOffsetMinutes;
                    LogUtil.debug(IapAdapter.TAG, "onDeviceTimeUpdate Offsets:" + IapAdapter.this.mSynchronizedOffsets + " secondsSinceReferenceDate:" + secondsSinceReferenceDate + " timeZoneOffsetMinutes:" + timeZoneOffsetMinutes + " daylightSavingsOffsetMinutes:" + daylightSavingsOffsetMinutes + " currentTimeMillis:" + currentTimeMillis);
                }
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStartLocationInformation(IapController.IapSession session, int sentenceType) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onStartLocationInformation session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onStartLocationInformation(clientType, sentenceType);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onStartLocationInformation mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStopLocationInformation(IapController.IapSession session) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onStopLocationInformation session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onStopLocationInformation(clientType);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onStopLocationInformation mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStartOobBtPairing(IapController.IapSession session) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onStartOobBtPairing session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onStartOobBtPairing(clientType);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onStartOobBtPairing mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStopOobBtPairing(IapController.IapSession session) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onStopOobBtPairing session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onStopOobBtPairing(clientType);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onStopOobBtPairing mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onOobBtPairingLinkKeyInformation(IapController.IapSession session, byte[] linkKey, byte[] devMacAddress) {
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onOobBtPairingLinkKeyInformation session:" + session + " invalid.");
                return -1;
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onOobBtPairingLinkKeyInformation(clientType, linkKey, devMacAddress);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onOobBtPairingLinkKeyInformation mListener is null.");
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onCallStateUpdate(IapController.IapSession session, CallStateUpdate update) {
            CallStateInfo info = new CallStateInfo();
            if (update != null) {
                long currentTimeMillis = System.currentTimeMillis();
                synchronized (IapAdapter.this.mCallStateInfo) {
                    if (update.isRemoteIdValid()) {
                        IapAdapter.this.mCallStateInfo.setRemoteId(update.getRemoteId());
                    }
                    if (update.isDisplayNameValid()) {
                        IapAdapter.this.mCallStateInfo.setDisplayName(update.getDisplayName());
                    }
                    IapAdapter.this.mCallStateInfo.setStatus(update.getStatus());
                    if (update.isStartTimestampValid()) {
                        LogUtil.debug(IapAdapter.TAG, "onCallStateUpdate currentTimeMillis:" + currentTimeMillis + " StartTimestamp:" + update.getStartTimestamp());
                        synchronized (IapAdapter.this.mLock) {
                            if (currentTimeMillis > 0) {
                                IapAdapter.this.mCallStateInfo.setDuration((currentTimeMillis / 1000) - (update.getStartTimestamp() - IapAdapter.this.mSynchronizedOffsets));
                            }
                        }
                    }
                    String uuid = update.getCallUuid();
                    LogUtil.info(IapAdapter.TAG, " update.isCallUuidValid():" + update.isCallUuidValid() + "update.getCallUuid():" + LogEncrypt.MacAddressEncrypt(uuid));
                    if (update.isCallUuidValid()) {
                        IapAdapter.this.mCallStateInfo.setCallUuid(update.getCallUuid());
                    }
                    if (update.isIsConferencedValid() && 1 == update.getIsConferenced()) {
                        IapAdapter.this.mCallStateInfo.setIsConferencedValid(true);
                        if (update.isConferenceGroupValid()) {
                            IapAdapter.this.mCallStateInfo.setConferenceGroup(update.getConferenceGroup());
                        }
                    } else {
                        IapAdapter.this.mCallStateInfo.setIsConferencedValid(false);
                    }
                    info.copy(IapAdapter.this.mCallStateInfo);
                }
                LogUtil.debug(IapAdapter.TAG, "onCallStateUpdate info: " + info);
                if (IapAdapter.this.mListener != null) {
                    IapAdapter.this.mListener.onCallStateUpdate(info);
                }
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onRouteGuidanceManeuverUpdate(IapController.IapSession session, RouteGuidanceManeuverUpdate update) {
            LogUtil.debug(IapAdapter.TAG, "onRouteGuidanceManeuverUpdate update: " + update);
            if (update != null) {
                synchronized (IapAdapter.this.mRouteGuidanceInfo) {
                    IapAdapter.this.mRouteGuidanceInfo.clearManeuverInfo();
                    if (update.isManeuverDescriptionValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setManeuverDescription(update.getManeuverDescription());
                    }
                    if (update.isDistanceBetweenManeuverValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceBetweenManeuver(update.getDistanceBetweenManeuver());
                    }
                    if (update.isDistanceBetweenManeuverDisplayStrValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceBetweenManeuverDisplayStr(update.getDistanceBetweenManeuverDisplayStr());
                    }
                    if (update.isDistanceBetweenManeuverDisplayUnitsValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceBetweenManeuverDisplayUnits(update.getDistanceBetweenManeuverDisplayUnits());
                    }
                    if (update.isJunctionElementAngleValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setJunctionElementAngles(update.getJunctionElementAngles());
                    }
                    if (update.isManeuverTypeValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setManeuverType(update.getManeuverType());
                    }
                    if (update.isAfterManeuverRoadNameValid()) {
                        if (update.getAfterManeuverRoadName() == null) {
                            LogUtil.warning(IapAdapter.TAG, "setAfterManeuverRoadName: " + update.getAfterManeuverRoadName());
                        }
                        IapAdapter.this.mRouteGuidanceInfo.setAfterManeuverRoadName(update.getAfterManeuverRoadName());
                    }
                    if (update.isJunctionElementExitAngleValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setJunctionElementExitAngle(update.getJunctionElementExitAngle());
                    }
                    if (update.isJunctionTypeValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setJunctionType(update.getJunctionType());
                    }
                    if (update.isDrivingSideValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDrivingSide(update.getDrivingSide());
                    }
                    if (update.isExitInfoValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setExitInfo(update.getExitInfo());
                    }
                }
                return 0;
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onRouteGuidanceUpdate(IapController.IapSession session, RouteGuidanceUpdate update) {
            LogUtil.debug(IapAdapter.TAG, "onRouteGuidanceUpdate update: " + update);
            RouteGuidanceInfo info = new RouteGuidanceInfo();
            if (update != null) {
                synchronized (IapAdapter.this.mRouteGuidanceInfo) {
                    if (update.isManeuverStateValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setManeuverState(update.getManeuverState());
                    }
                    if (update.isCurrentRoadNameValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setCurrentRoadName(update.getCurrentRoadName());
                    }
                    if (update.isDestinationNameValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDestinationName(update.getDestinationName());
                    }
                    if (update.isEstimatedTimeOfArrivalValid()) {
                        if (update.isDestinationTimeZoneOffsetMinutesValid()) {
                            IapAdapter.this.mRouteGuidanceInfo.setDestinationTimeZoneOffsetMinutes(update.getDestinationTimeZoneOffsetMinutes());
                            IapAdapter.this.mRouteGuidanceInfo.setEstimatedTimeOfArrival(update.getEstimatedTimeOfArrival() + (((long) update.getDestinationTimeZoneOffsetMinutes()) * 60));
                        } else {
                            IapAdapter.this.mRouteGuidanceInfo.setEstimatedTimeOfArrival(update.getEstimatedTimeOfArrival() + (((long) IapAdapter.this.mTimeZoneOffsetMinutes) * 60));
                        }
                    }
                    if (update.isDistanceRemainingValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceRemaining(update.getDistanceRemaining());
                    }
                    if (update.isDistanceToNextManeuverValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceToNextManeuver(update.getDistanceToNextManeuver());
                    }
                    if (update.isManeuverCountValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setManeuverGuidanceTotalCount(update.getManeuverCount());
                    }
                    boolean z = true;
                    if (update.isVisibleInAppValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setRouteGuidanceVisibleInApp(update.getVisibleInApp() == 1);
                    }
                    if (update.isLaneGuidanceTotalCount()) {
                        IapAdapter.this.mRouteGuidanceInfo.setLaneGuidanceTotalCount(update.getLaneGuidanceTotalCount());
                    }
                    if (update.isLaneGuidanceBeingShowInApp()) {
                        RouteGuidanceInfo routeGuidanceInfo = IapAdapter.this.mRouteGuidanceInfo;
                        if (update.getLaneGuidanceBeingShowInApp() != 1) {
                            z = false;
                        }
                        routeGuidanceInfo.setLaneGuidanceBeingShowInApp(z);
                    }
                    if (update.isDistanceRemainingDisplayStrValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceRemainingDisplayStr(update.getDistanceRemainingDisplayStr());
                    }
                    if (update.isDistanceRemainingDisplayUnitsValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceRemainingDisplayUnits(update.getDistanceRemainingDisplayUnits());
                    }
                    if (update.isTimeRemainingToDestinationValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setTimeRemainingToDestination(update.getTimeRemainingToDestination());
                    }
                    if (update.isDistanceToNextManeuverDisplayStrValid()) {
                        if (update.getDistanceToNextManeuverDisplayStr() == null) {
                            LogUtil.warning(IapAdapter.TAG, "setDistanceBetweenManeuverDisplayStr: " + update.getDistanceToNextManeuverDisplayStr());
                        }
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceToNextManeuverDisplayStr(update.getDistanceToNextManeuverDisplayStr());
                    }
                    if (update.isDistanceToNextManeuverDisplayUnitsValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setDistanceToNextManeuverDisplayUnits(update.getmDistanceToNextManeuverDisplayUnits());
                    }
                    if (update.isSourceName()) {
                        IapAdapter.this.mRouteGuidanceInfo.setSourceName(update.getSourceName());
                    }
                    if (update.isSourceSupportsRouteGuidance()) {
                        IapAdapter.this.mRouteGuidanceInfo.setSourceSupportsRouteGuidance(update.getSourceSupportsRouteGuidance());
                    }
                    if (update.isStateValid()) {
                        if (update.getState() == 0) {
                            IapAdapter.this.mRouteGuidanceInfo.clear();
                        } else {
                            IapAdapter.this.mRouteGuidanceInfo.setRouteGuidanceState(update.getState());
                        }
                    }
                    info.copy(IapAdapter.this.mRouteGuidanceInfo);
                }
                if (IapAdapter.this.mListener != null) {
                    IapAdapter.this.mListener.onRouteGuidanceUpdate(info);
                }
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onLaneGuidanceInformation(IapController.IapSession session, LaneGuidanceInformation update) {
            LogUtil.debug(IapAdapter.TAG, "onLaneGuidanceInformation update: " + update);
            if (update != null) {
                synchronized (IapAdapter.this.mRouteGuidanceInfo) {
                    if (update.getLaneInformationList() == null || update.getLaneInformationList().size() < 1) {
                        IapAdapter.this.mRouteGuidanceInfo.setLaneInformationList(new ArrayList());
                    } else {
                        List<LaneInformation> list = update.getLaneInformationList();
                        List<com.ts.carplay.common.data.LaneInformation> laneInfoList = new ArrayList<>();
                        if (list != null) {
                            for (LaneInformation iapInfo : list) {
                                int laneSatus = -1;
                                if (LaneStatus.LANE_SATUS_NOT_GOOD == iapInfo.getLaneStatus()) {
                                    laneSatus = 0;
                                } else if (LaneStatus.LANE_SATUS_GOOD == iapInfo.getLaneStatus()) {
                                    laneSatus = 1;
                                } else if (LaneStatus.LANE_SATUS_PREFERRED == iapInfo.getLaneStatus()) {
                                    laneSatus = 2;
                                }
                                com.ts.carplay.common.data.LaneInformation laneInfo = new com.ts.carplay.common.data.LaneInformation(iapInfo.getIndex(), laneSatus, iapInfo.getLaneAgnles(), iapInfo.getLaneAgnlesHighlight());
                                laneInfoList.add(laneInfo);
                            }
                        }
                        IapAdapter.this.mRouteGuidanceInfo.setLaneInformationList(laneInfoList);
                    }
                    if (update.getInstructionTextStrValid()) {
                        IapAdapter.this.mRouteGuidanceInfo.setInstructionText(update.getInstructTextStr());
                    } else {
                        IapAdapter.this.mRouteGuidanceInfo.setInstructionText("");
                    }
                }
                return 0;
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onMediaLibraryUpdate(IapController.IapSession session, MediaLibraryUpdate update) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onMediaPlaylistUpdate(IapController.IapSession session, String plPid, String[] pids) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onNowPlayingUpdate(IapController.IapSession session, NowPlayingUpdate update) {
            NowPlayingInfo info = new NowPlayingInfo();
            boolean isNeedUpdate = false;
            if (update != null) {
                synchronized (IapAdapter.this.mNowPlayingInfo) {
                    if (update.isPidValid() && !TextUtils.isEmpty(update.getPid())) {
                        if (!TextUtils.equals(IapAdapter.this.mNowPlayingInfo.getPid(), update.getPid())) {
                            LogUtil.info(IapAdapter.TAG, "onNowPlayingUpdate pid:" + update.getPid());
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setPid(update.getPid());
                    }
                    if (update.getPlaybackStatus() != -1) {
                        if (IapAdapter.this.mNowPlayingInfo.getPlaybackStatus() != update.getPlaybackStatus()) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setPlaybackStatus(update.getPlaybackStatus());
                    }
                    if (update.isTitleValid()) {
                        if (IapAdapter.this.mNowPlayingInfo.getMediaItemTitle() != null && update.getTitle() != null && !IapAdapter.this.mNowPlayingInfo.getMediaItemTitle().equals(update.getTitle())) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setMediaItemTitle(update.getTitle());
                    }
                    if (update.isArtistValid()) {
                        if (IapAdapter.this.mNowPlayingInfo.getMediaItemArtist() != null && update.getArtist() != null && !IapAdapter.this.mNowPlayingInfo.getMediaItemArtist().equals(update.getArtist())) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setMediaItemArtist(update.getArtist());
                    }
                    if (update.isAlbumTitleValid()) {
                        if (IapAdapter.this.mNowPlayingInfo.getAlbumTitle() != null && update.getAlbumTitle() != null && !IapAdapter.this.mNowPlayingInfo.getAlbumTitle().equals(update.getAlbumTitle())) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setAlbumTitle(update.getAlbumTitle());
                    }
                    long playbackDuration = update.getPlaybackDurationMs();
                    if (playbackDuration != -1) {
                        if (IapAdapter.this.mNowPlayingInfo.getMediaItemPlaybackDurationMs() != playbackDuration) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setMediaItemPlaybackDurationMs(playbackDuration);
                    }
                    long playbackElapsedTime = update.getPlaybackElapsedTimeMs();
                    if (playbackElapsedTime != -1) {
                        if (IapAdapter.this.mNowPlayingInfo.getPlaybackElapsedTimeMs() / 1000 != playbackElapsedTime / 1000) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setPlaybackElapsedTimeMs(playbackElapsedTime);
                    }
                    int playbackShuffleMode = update.getPlaybackShuffleMode();
                    if (playbackShuffleMode != -1) {
                        if (IapAdapter.this.mNowPlayingInfo.getPlaybackShuffleMode() != playbackShuffleMode) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setPlaybackShuffleMode(playbackShuffleMode);
                    }
                    int playbackRepeatMode = update.getPlaybackRepeatMode();
                    if (playbackRepeatMode != -1) {
                        if (IapAdapter.this.mNowPlayingInfo.getPlaybackRepeatMode() != playbackRepeatMode) {
                            isNeedUpdate = true;
                        }
                        IapAdapter.this.mNowPlayingInfo.setPlaybackRepeatMode(playbackRepeatMode);
                    }
                    info.copy(IapAdapter.this.mNowPlayingInfo);
                }
                if (IapAdapter.this.mListener != null && isNeedUpdate) {
                    IapAdapter.this.mListener.onNowPlayingUpdate(info);
                    return 0;
                }
                return 0;
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onPlaybackListUpdate(IapController.IapSession session, boolean isAvailable, String[] pids) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onArtworkInfoUpdate(IapController.IapSession session, int artworkSize, byte[] artworkData) {
            String str = IapAdapter.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onArtworkInfoUpdate : artworkSize = ");
            sb.append(artworkSize);
            sb.append(", artworkData: ");
            sb.append(artworkData == null);
            LogUtil.info(str, sb.toString());
            NowPlayingInfo info = new NowPlayingInfo();
            synchronized (IapAdapter.this.mNowPlayingInfo) {
                IapAdapter.this.transferByteToUrl(artworkData, artworkSize);
                info.copy(IapAdapter.this.mNowPlayingInfo);
                info.setArtworkSize(artworkSize);
                info.setArtworkData(artworkData);
                LogUtil.info(IapAdapter.TAG, "onArtworkInfoUpdate : mNowPlayingInfo = " + info.toString());
                if (IapAdapter.this.mListener != null) {
                    IapAdapter.this.mListener.onArtworkUpdate(info);
                }
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onDeviceUuidUpdate(IapController.IapSession session, String uuid) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onBluetoothConnectionUpdate(IapController.IapSession session, int connectedBtProfiles) {
            LogUtil.warning(IapAdapter.TAG, "onBluetoothConnectionUpdate connectedBtProfiles: " + connectedBtProfiles);
            boolean btA2dpConnected = (connectedBtProfiles & 8) == 8;
            if (btA2dpConnected != IapAdapter.this.mIsBtA2dpConnected && IapAdapter.this.mListener != null) {
                IapAdapter.this.mIsBtA2dpConnected = btA2dpConnected;
                IapAdapter.this.mListener.onBtA2dpConnect(IapAdapter.this.mIsBtA2dpConnected);
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onUsbDeviceModeAudioInformation(IapController.IapSession session, int sampleRate) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onCommunicationsUpdate(IapController.IapSession session, CommunicationsUpdate update) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onPowerUpdate(IapController.IapSession session, PowerUpdate update) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStartEapSession(IapController.IapSession session, int eapId, int sessionId) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onReceiveEapData(IapController.IapSession session, int sessionId, byte[] data) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStopEapSession(IapController.IapSession session, int sessionId) {
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onRecentListAvailable(IapController.IapSession session, int isAvailable, int listCount) {
            LogUtil.info(IapAdapter.TAG, "onRecentListAvailable : isAvailable = " + isAvailable + ",listCount = " + listCount);
            IapAdapter.this.mRecentListAvailable = isAvailable;
            synchronized (IapAdapter.this.mSynchronizedRecentContactList) {
                if (IapAdapter.this.mRecentContactListInfo != null) {
                    IapAdapter.this.mRecentContactListInfo.clear();
                }
            }
            IapAdapter.this.mRecentListCount = listCount;
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onRecentListCount(listCount);
                return 0;
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onFavoriteListAvailable(IapController.IapSession session, int isAvailable, int listCount) {
            LogUtil.info(IapAdapter.TAG, "onFavoriteListAvailable : isAvailable = " + isAvailable + ",listCount = " + listCount);
            IapAdapter.this.mFavoriteListAvailable = isAvailable;
            synchronized (IapAdapter.this.mSynchronizedFavoriteContactList) {
                if (IapAdapter.this.mFavoriteContactListInfo != null) {
                    IapAdapter.this.mFavoriteContactListInfo.clear();
                }
            }
            IapAdapter.this.mFavoriteListCount = listCount;
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onFavoriteListCount(listCount);
                return 0;
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onRecentListUpdate(IapController.IapSession session, ArrayList<RecentListInfo> list) {
            LogUtil.debug(IapAdapter.TAG, "onRecentListUpdate.");
            if (list != null) {
                if (IapAdapter.this.mRecentListAvailable != 1 || list.isEmpty()) {
                    LogUtil.warning(IapAdapter.TAG, "onRecentListUpdate RecentListInfo is not available");
                    return -1;
                }
                List<RecentContactListInfo> recentContactListInfo = null;
                synchronized (IapAdapter.this.mSynchronizedRecentContactList) {
                    if (IapAdapter.this.mRecentContactListInfo != null) {
                        recentContactListInfo = IapAdapter.this.copyRecentListInfo(list);
                    }
                }
                if (IapAdapter.this.mListener != null) {
                    IapAdapter.this.mListener.onRecentListUpdate(recentContactListInfo);
                    return 0;
                }
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onRecentListUpdate list is null");
            return -1;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onFavoriteListUpdate(IapController.IapSession session, ArrayList<FavoriteListInfo> list) {
            LogUtil.debug(IapAdapter.TAG, "onFavoriteListUpdate.");
            if (list != null) {
                if (IapAdapter.this.mFavoriteListAvailable != 1 || list.isEmpty()) {
                    LogUtil.warning(IapAdapter.TAG, "onFavoriteListUpdate FavoriteListInfo is not available");
                    return -1;
                }
                List<FavoriteContactListInfo> favoriteContactListInfo = null;
                synchronized (IapAdapter.this.mSynchronizedFavoriteContactList) {
                    if (IapAdapter.this.mFavoriteContactListInfo != null) {
                        favoriteContactListInfo = IapAdapter.this.copyFavoriteListInfo(list);
                    }
                }
                if (IapAdapter.this.mListener != null) {
                    IapAdapter.this.mListener.onFavoriteListUpdate(favoriteContactListInfo);
                    return 0;
                }
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onFavoriteListUpdate FavoriteListInfo is null");
            return -1;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onGprmcDataStatus(IapController.IapSession session, int gprmcData) {
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onGprmcDataStatus(gprmcData);
                return 0;
            }
            LogUtil.warning(IapAdapter.TAG, "onGprmcDataStatus mListener is null");
            return -1;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onPlaybackListMeiaItemInfoUpdate(IapController.IapSession session, int mediaItemCount, int mediaItemStartIndex, ArrayList<NowPlayingPbMediaItemInfo> mediaItemList) {
            LogUtil.verbose(IapAdapter.TAG, "onPlaybackListMeiaItemInfoUpdate mediaItemCount:" + mediaItemCount + " mediaItemStartIndex:" + mediaItemStartIndex);
            ArrayList<PlaybackMediaItemInfo> outList = new ArrayList<>();
            if (mediaItemList != null) {
                LogUtil.verbose(IapAdapter.TAG, "onPlaybackListMeiaItemInfoUpdate mediaItemList:" + mediaItemList.toString());
                outList.clear();
                for (NowPlayingPbMediaItemInfo iapPbMediaItemInfo : mediaItemList) {
                    if (iapPbMediaItemInfo != null) {
                        PlaybackMediaItemInfo playbackMediaItemInfo = new PlaybackMediaItemInfo();
                        if (iapPbMediaItemInfo.isPidValid()) {
                            playbackMediaItemInfo.mPid = iapPbMediaItemInfo.getPid();
                        }
                        if (iapPbMediaItemInfo.isTitleValid()) {
                            playbackMediaItemInfo.mTitle = iapPbMediaItemInfo.getTitle();
                        }
                        if (iapPbMediaItemInfo.isTitleValid()) {
                            playbackMediaItemInfo.mAlbum = iapPbMediaItemInfo.getAlbumTitle();
                        }
                        if (iapPbMediaItemInfo.isartistValid()) {
                            playbackMediaItemInfo.mArtist = iapPbMediaItemInfo.getArtist();
                        }
                        if (iapPbMediaItemInfo.isAlbumArtistValid()) {
                            playbackMediaItemInfo.mAlbumArtist = iapPbMediaItemInfo.getAlbumArtist();
                        }
                        if (iapPbMediaItemInfo.isGenreValid()) {
                            playbackMediaItemInfo.mGenre = iapPbMediaItemInfo.getGenre();
                        }
                        if (iapPbMediaItemInfo.isComposerValid()) {
                            playbackMediaItemInfo.mComposer = iapPbMediaItemInfo.getComposer();
                        }
                        outList.add(playbackMediaItemInfo);
                    }
                }
            } else {
                LogUtil.warning(IapAdapter.TAG, "onPlaybackListMeiaItemInfoUpdate mediaItemList is null");
            }
            if (IapAdapter.this.mListener != null) {
                IapAdapter.this.mListener.onPlaybackListUpdate(mediaItemCount, mediaItemStartIndex, outList);
                return 0;
            }
            return 0;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onCarplayAvialability(IapController.IapSession session, final CarplayAvialability availability) {
            if (availability == null) {
                LogUtil.warning(IapAdapter.TAG, "onCarplayAvialability availability is null");
                return 0;
            }
            if (IapAdapter.this.mListener == null) {
                LogUtil.warning(IapAdapter.TAG, "onCarplayAvialability mListener is null");
                return 0;
            }
            final int clientType = IapAdapter.this.findClientType(session);
            if (17 != clientType && 18 != clientType && 16 != clientType) {
                LogUtil.warning(IapAdapter.TAG, "onCarplayAvialability invalid clientType:" + clientType);
                return 0;
            }
            IapAdapter.this.post(new Runnable() { // from class: com.ts.carplay.linkadapter.iap.-$$Lambda$IapAdapter$IapListenerImpl$OWg8tx-EKOqrscn-tf94i4WjntM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCarplayAvialability$1$IapAdapter$IapListenerImpl(availability, clientType);
                }
            });
            return 0;
        }

        public /* synthetic */ void lambda$onCarplayAvialability$1$IapAdapter$IapListenerImpl(CarplayAvialability availability, int clientType) {
            WiredAttributes wired = new WiredAttributes();
            wired.setWiredValid(availability.isWiredValid());
            wired.setWiredAvailable(availability.isWiredAvailable());
            wired.setUsbTransportIdentifier(availability.isUsbTransportIdentifierValid() ? availability.getUsbTransportIdentifier() : null);
            WirelessAttributes wireless = new WirelessAttributes();
            wireless.setWirelessValid(availability.isWirelessValid());
            wireless.setWirelessAvailable(availability.isWirelessAvailable());
            wireless.setBluetoothTransportIdentifier(availability.isBluetoothTransportIdentifierValid() ? availability.getBluetoothTransportIdentifier() : null);
            IapAdapter.this.mListener.onCarplayAvialability(clientType, wired, wireless);
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStartBluetoothPairing(IapController.IapSession session, short btTransComId, byte[] macAddress) {
            LogUtil.debug(IapAdapter.TAG, "onStartBluetoothPairing session: " + session + ", btTransComId: " + ((int) btTransComId) + ", macAddress: " + macAddress);
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onStartBluetoothPairing session:" + session + " invalid.");
            }
            if (IapAdapter.this.mListener != null) {
                return IapAdapter.this.mListener.onStartBluetoothPairing(clientType, btTransComId, macAddress);
            }
            LogUtil.warning(IapAdapter.TAG, "onStartBluetoothPairing mListener is null");
            return -1;
        }

        @Override // ts.car.iap.protocol.IapListener
        public int onStopBluetoothPairing(IapController.IapSession session, short btTransComId, PairingState state) {
            int pairingState;
            LogUtil.debug(IapAdapter.TAG, "onStopBluetoothPairing session: " + session + ", btTransComId: " + ((int) btTransComId) + ", state: " + state);
            int clientType = IapAdapter.this.findClientType(session);
            if (153 == clientType) {
                LogUtil.warning(IapAdapter.TAG, "onStartBluetoothPairing session:" + session + " invalid.");
            }
            if (IapAdapter.this.mListener != null) {
                LogUtil.debug(IapAdapter.TAG, "onStopBluetoothPairing btTransComId:" + ((int) btTransComId) + ", state: " + state);
                int i = AnonymousClass1.$SwitchMap$ts$car$iap$protocol$common$PairingState[state.ordinal()];
                if (i == 1) {
                    pairingState = 0;
                } else if (i == 2) {
                    pairingState = 1;
                } else if (i == 3) {
                    pairingState = 2;
                } else {
                    pairingState = -1;
                }
                return IapAdapter.this.mListener.onStopBluetoothPairing(clientType, btTransComId, pairingState);
            }
            LogUtil.warning(IapAdapter.TAG, "onStopBluetoothPairing mListener is null.");
            return -1;
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.linkadapter.iap.IapAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ts$car$iap$protocol$common$PairingState;

        static {
            int[] iArr = new int[PairingState.values().length];
            $SwitchMap$ts$car$iap$protocol$common$PairingState = iArr;
            try {
                iArr[PairingState.PairingSuccess.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$ts$car$iap$protocol$common$PairingState[PairingState.PairingAlreadyConnected.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$ts$car$iap$protocol$common$PairingState[PairingState.PairingCancel.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetIapStates() {
        if (this.mLists[0].getIapSession() != null) {
            this.mCurClientType = 17;
        } else if (this.mLists[2].getIapSession() != null) {
            this.mCurClientType = 19;
        } else {
            this.mCurClientType = 153;
        }
        this.mHasStartedCallState = false;
        this.mHasStartedRouteGuidance = false;
        this.mHasStartedNowPlaying = false;
        this.mHasStartedContactList = false;
        synchronized (this.mSynchronizedCallState) {
            this.mIsNeedStartCallState = false;
        }
        synchronized (this.mSynchronizedRouteGuidance) {
            this.mIsNeedStartRouteGuidance = false;
        }
        synchronized (this.mSynchronizedNowPlaying) {
            this.mIsNeedStartNowPlaying = false;
        }
        synchronized (this.mSynchronizedContactList) {
            this.mIsNeedStartContactList = false;
        }
        synchronized (this.mCallStateInfo) {
            this.mCallStateInfo.clear();
        }
        synchronized (this.mNowPlayingInfo) {
            this.mNowPlayingInfo.clear();
        }
        synchronized (this.mRouteGuidanceInfo) {
            this.mRouteGuidanceInfo.clear();
        }
        synchronized (this.mSynchronizedRecentContactList) {
            this.mRecentContactListInfo.clear();
            this.mRecentListCount = -1;
        }
        synchronized (this.mSynchronizedFavoriteContactList) {
            this.mFavoriteContactListInfo.clear();
            this.mFavoriteListCount = -1;
        }
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void registerListener(IIapAdapterListener listener) {
        this.mListener = listener;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void unregisterListener(IIapAdapterListener listener) {
        this.mListener = null;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public boolean createSession(int clientType, IapConfig config) {
        IapController.IapSession iapSession;
        LogUtil.debug(TAG, "createSession clientType:" + clientType + " config:" + config);
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "createSession clientType:" + clientType + " invalid.");
            return false;
        }
        boolean ret = false;
        IapController iapController = this.mIapController;
        if (iapController != null && config != null && (iapSession = iapController.createSession(ConversionUtil.getInstance().convertIapConfig(config), this.mIapListenerImpl)) != null) {
            synchronized (this.mLists[index]) {
                this.mLists[index].setIapSession(iapSession);
                this.mLists[index].setUuid(null);
            }
            ret = true;
        }
        LogUtil.debug(TAG, "createSession ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public boolean destroySession(int clientType) {
        IapSessionInfo targetInfo;
        LogUtil.debug(TAG, "destroySession clientType:" + clientType);
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "destroySession clientType:" + clientType + " invalid.");
            return false;
        }
        boolean ret = false;
        IapController iapController = this.mIapController;
        if (iapController != null && (targetInfo = this.mLists[index]) != null && iapController.destroySession(targetInfo.getIapSession()) == 0) {
            synchronized (this.mLists[index]) {
                this.mLists[index].setIapSession(null);
                this.mLists[index].setUuid(null);
                if (17 == clientType || 19 == clientType) {
                    LogUtil.debug(TAG, "destroySession mCurClientType:" + this.mCurClientType);
                    if (this.mCurClientType != 153 && this.mLists[convertClientTypeToIndex(this.mCurClientType)].getIapSession() == null) {
                        LogUtil.debug(TAG, "destroySession, clientType: " + clientType);
                        resetIapStates();
                    }
                }
            }
            ret = true;
        }
        LogUtil.debug(TAG, "destroySession ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int connectSession(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "connectSession clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "connectSession clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.connect();
        } else {
            LogUtil.warning(TAG, "connectSession iapSession is null.");
        }
        LogUtil.debug(TAG, "connectSession ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int disconnectSession(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "disconnectSession clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "disconnectSession clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.disconnect();
        } else {
            LogUtil.warning(TAG, "disconnectSession iapSession is null.");
        }
        LogUtil.debug(TAG, "disconnectSession ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendBluetoothPairingAccessoryInformation(int clientType, short btTransComId, byte[][] pairingDataP192, byte[][] pairingDataP256) throws RemoteException {
        LogUtil.debug(TAG, "sendBluetoothPairingAccessoryInformation clientType:" + clientType + " btTransComId:" + ((int) btTransComId));
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendBluetoothPairingAccessoryInformation clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.sendBluetoothPairingAccessoryInformation(btTransComId, pairingDataP192, pairingDataP256);
        } else {
            LogUtil.warning(TAG, "sendBluetoothPairingAccessoryInformation iapSession is null.");
        }
        LogUtil.debug(TAG, "sendBluetoothPairingAccessoryInformation ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendBluetoothPairingStatus(int clientType, short btTransComId, boolean success, String reason) throws RemoteException {
        LogUtil.debug(TAG, "sendBluetoothPairingStatus clientType:" + clientType + " btTransComId:" + ((int) btTransComId) + ", success: " + success + ", reason: " + reason);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendBluetoothPairingStatus clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.sendBluetoothPairingStatus(btTransComId, success, reason);
        } else {
            LogUtil.warning(TAG, "sendBluetoothPairingStatus iapSession is null.");
        }
        LogUtil.debug(TAG, "sendBluetoothPairingStatus ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendLocationInformation(int clientType, String nmeaSentence) {
        LogUtil.verbose(TAG, "sendLocationInformation clientType:" + clientType + " nmeaSentence:" + nmeaSentence);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendLocationInformation clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.sendLocationInformation(nmeaSentence);
        } else {
            LogUtil.warning(TAG, "sendLocationInformation iapSession is null.");
        }
        LogUtil.verbose(TAG, "sendLocationInformation ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendOobBtPairingAccessoryInformation(int clientType, long deviceClass) throws RemoteException {
        LogUtil.debug(TAG, "sendOobBtPairingAccessoryInformation clientType:" + clientType + " deviceClass:" + deviceClass);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendOobBtPairingAccessoryInformation clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.sendOobBtPairingAccessoryInformation(deviceClass);
        } else {
            LogUtil.warning(TAG, "sendOobBtPairingAccessoryInformation iapSession is null.");
        }
        LogUtil.debug(TAG, "sendOobBtPairingAccessoryInformation ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendOobBtPairingCompletionInformation(int clientType, int resultCode) throws RemoteException {
        LogUtil.debug(TAG, "sendOobBtPairingCompletionInformation clientType:" + clientType + " resultCode:" + resultCode);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendOobBtPairingCompletionInformation clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.sendOobBtPairingCompletionInformation(resultCode);
        } else {
            LogUtil.warning(TAG, "sendOobBtPairingCompletionInformation iapSession is null.");
        }
        LogUtil.debug(TAG, "sendOobBtPairingCompletionInformation ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendAccessoryWiFiConfigurationInformation(int clientType, AccessoryWiFiInfo info) throws RemoteException {
        LogUtil.debug(TAG, "sendAccessoryWiFiConfigurationInformation clientType:" + clientType + " info:" + info);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendAccessoryWiFiConfigurationInformation clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            if (info != null) {
                ret = iapSession.sendAccessoryWiFiConfigurationInformation(info.getSsid(), info.getPassphrase(), WifiSecurityType.fromTypeValue(info.getSecurity()), WifiChannelType.fromTypeValue(info.getChannel()));
            }
        } else {
            LogUtil.warning(TAG, "sendAccessoryWiFiConfigurationInformation iapSession is null.");
        }
        LogUtil.debug(TAG, "sendAccessoryWiFiConfigurationInformation ret:" + ret);
        return ret;
    }

    private int startRouteGuidanceUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "startRouteGuidanceUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "startRouteGuidanceUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            synchronized (this.mRouteGuidanceInfo) {
                this.mRouteGuidanceInfo.clear();
            }
            ret = iapSession.startRouteGuidanceUpdates(null, true, true, true, true);
        } else {
            LogUtil.warning(TAG, "startRouteGuidanceUpdates iapSession is null.");
        }
        this.mHasStartedRouteGuidance = true;
        LogUtil.debug(TAG, "startRouteGuidanceUpdates ret:" + ret);
        return ret;
    }

    private int stopRouteGuidanceUpdates(int clientType) {
        LogUtil.debug(TAG, "stopRouteGuidanceUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "stopRouteGuidanceUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.stopRouteGuidanceUpdates();
        } else {
            LogUtil.warning(TAG, "stopRouteGuidanceUpdates iapSession is null.");
        }
        this.mHasStartedRouteGuidance = false;
        LogUtil.debug(TAG, "stopRouteGuidanceUpdates ret:" + ret);
        return ret;
    }

    private int startCallStateUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "startCallStateUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "startCallStateUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            synchronized (this.mCallStateInfo) {
                this.mCallStateInfo.clear();
            }
            ret = iapSession.startCallStateUpdates();
        } else {
            LogUtil.warning(TAG, "startCallStateUpdates iapSession is null.");
        }
        this.mHasStartedCallState = true;
        LogUtil.debug(TAG, "startCallStateUpdates ret:" + ret);
        return ret;
    }

    private int stopCallStateUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "stopCallStateUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "stopCallStateUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.stopCallStateUpdates();
        } else {
            LogUtil.warning(TAG, "stopCallStateUpdates iapSession is null.");
        }
        this.mHasStartedCallState = false;
        LogUtil.debug(TAG, "stopCallStateUpdates ret:" + ret);
        return ret;
    }

    private int startContactListUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "startContactListUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "startContactListUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.startListUpdates();
        } else {
            LogUtil.warning(TAG, "startContactListUpdates iapSession is null.");
        }
        this.mHasStartedContactList = true;
        LogUtil.debug(TAG, "startContactListUpdates ret:" + ret);
        return ret;
    }

    private int stopContactListUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "stopContactListUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "stopContactListUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.stopListUpdates();
        } else {
            LogUtil.warning(TAG, "stopContactListUpdates iapSession is null.");
        }
        this.mHasStartedContactList = false;
        LogUtil.debug(TAG, "stopContactListUpdates ret:" + ret);
        return ret;
    }

    private int startNowPlayingUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "startNowPlayingUpdates clientType:" + clientType);
        boolean deleteFlag = deleteArtWorkPathDirectory(this.mArtworkFilePath);
        LogUtil.debug(TAG, "clearNowPlayingInfo deleteFlag" + deleteFlag);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "startNowPlayingUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            synchronized (this.mNowPlayingInfo) {
                this.mNowPlayingInfo.clear();
            }
            ret = iapSession.startNowPlayingUpdates();
        } else {
            LogUtil.warning(TAG, "startNowPlayingUpdates iapSession is null.");
        }
        this.mHasStartedNowPlaying = true;
        LogUtil.debug(TAG, "startNowPlayingUpdates ret:" + ret);
        return ret;
    }

    private int stopNowPlayingUpdates(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "stopNowPlayingUpdates clientType:" + clientType);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "stopNowPlayingUpdates clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.stopNowPlayingUpdates();
        } else {
            LogUtil.warning(TAG, "stopNowPlayingUpdates iapSession is null.");
        }
        this.mHasStartedNowPlaying = false;
        LogUtil.debug(TAG, "stopNowPlayingUpdates ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int setSongElapsedTime(int clientType, long elapsedTime) throws RemoteException {
        LogUtil.debug(TAG, "setSongElapsedTime clientType:" + clientType + ",elapsedTime:" + elapsedTime);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "setSongElapsedTime clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.setSongElapsedTime(elapsedTime);
        } else {
            LogUtil.warning(TAG, "setSongElapsedTime iapSession is null.");
        }
        LogUtil.debug(TAG, "setSongElapsedTime ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int playSong(int clientType, long pbIndex) throws RemoteException {
        LogUtil.debug(TAG, "playSong pbIndex:" + pbIndex);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "playSong clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.playSong(pbIndex);
        } else {
            LogUtil.warning(TAG, "playSong iapSession is null.");
        }
        LogUtil.debug(TAG, "playSong ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int requestAppLaunch(int clientType, String appBundleId, int appLaunchMethod) throws RemoteException {
        LogUtil.debug(TAG, "requestAppLaunch clientType:" + clientType + " appBundleId:" + appBundleId + " appLaunchMethod:" + appLaunchMethod);
        int ret = -1;
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "requestAppLaunch clientType:" + clientType + " invalid.");
            return -1;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            ret = iapSession.requestAppLaunch(appBundleId, appLaunchMethod);
        } else {
            LogUtil.warning(TAG, "requestAppLaunch iapSession is null.");
        }
        LogUtil.debug(TAG, "requestAppLaunch ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void initiateCall(int clientType, int callType, String destId, int callService) throws RemoteException {
        LogUtil.debug(TAG, "initiateCall clientType:" + clientType + "callType:" + callType + ",destId:" + destId + ",callService" + callService);
        int ret = -1;
        IapController.IapSession iapSession = getIapSession(clientType);
        if (iapSession != null) {
            ret = iapSession.initiateCall(InitiateCallType.fromTypeValue(callType), destId, InitiateCallService.fromServiceValue(callService));
        } else {
            LogUtil.warning(TAG, "initiateCall iapSession is null.");
        }
        LogUtil.debug(TAG, "initiateCall ret:" + ret);
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void acceptCall(int clientType, int action, String callUuid) throws RemoteException {
        LogUtil.debug(TAG, "acceptCall clientType:" + clientType + ",action:" + action + ",callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        int ret = -1;
        IapController.IapSession iapSession = getIapSession(clientType);
        if (iapSession != null) {
            ret = iapSession.acceptCall(CallAcceptAction.fromActionValue(action), callUuid);
        } else {
            LogUtil.warning(TAG, "acceptCall iapSession is null.");
        }
        LogUtil.debug(TAG, "acceptCall ret:" + ret);
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void endCall(int clientType, int action, String callUuid) throws RemoteException {
        LogUtil.debug(TAG, "endCall clientType:" + clientType + ",action:" + action + ",callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        int ret = -1;
        IapController.IapSession iapSession = getIapSession(clientType);
        if (iapSession != null) {
            ret = iapSession.endCall(CallEndAction.fromActionValue(action), callUuid);
        } else {
            LogUtil.warning(TAG, "endCall iapSession is null.");
        }
        LogUtil.debug(TAG, "endCall ret:" + ret);
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void swapCalls(int clientType) throws RemoteException {
        LogUtil.debug(TAG, "swapCalls clientType:" + clientType);
        int ret = -1;
        IapController.IapSession iapSession = getIapSession(clientType);
        if (iapSession != null) {
            ret = iapSession.swapCalls();
        } else {
            LogUtil.warning(TAG, "swapCalls iapSession is null.");
        }
        LogUtil.debug(TAG, "swapCalls ret:" + ret);
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void sendDtmf(int clientType, int toneType, String callUuid) throws RemoteException {
        LogUtil.debug(TAG, "sendDtmf clientType:" + clientType + ",toneType:" + toneType + ",callUuid:" + LogEncrypt.MacAddressEncrypt(callUuid));
        int ret = -1;
        IapController.IapSession iapSession = getIapSession(clientType);
        if (iapSession != null) {
            ret = iapSession.sendDtmf(DtmfToneType.fromToneValue(toneType), callUuid);
        } else {
            LogUtil.warning(TAG, "sendDtmf iapSession is null.");
        }
        LogUtil.debug(TAG, "sendDtmf ret:" + ret);
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void sendHidEventOverIap(int clientType, int keyCode, int keyAction) throws RemoteException {
        int ret;
        LogUtil.debug(TAG, "sendHidEventOverIap clientType:" + clientType + " keyCode:" + keyCode + " keyAction:" + keyAction);
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "sendHidEventOverIap clientType:" + clientType + " invalid.");
            return;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        if (iapSession != null) {
            if (keyAction == 0) {
                ret = iapSession.pressHidKey(ConversionUtil.getInstance().convertHidKeyCode(keyCode));
            } else if (keyAction == 1) {
                ret = iapSession.releaseHidKey();
            } else {
                LogUtil.warning(TAG, "sendHidEventOverIap invalid keyAction:" + keyAction);
                return;
            }
            LogUtil.debug(TAG, "sendHidEventOverIap ret:" + ret);
            return;
        }
        LogUtil.warning(TAG, "sendHidEventOverIap iapSession is null.");
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public void setCallbackUpdate(final int type, final boolean isStart) {
        LogUtil.info(TAG, "setCallbackListSize type = " + type + ",isStart = " + isStart + ",mCurClientType = " + this.mCurClientType + ", mHasStartedCallState = " + this.mHasStartedCallState + ", mHasStartedRouteGuidance = " + this.mHasStartedRouteGuidance + ", mHasStartedNowPlaying = " + this.mHasStartedNowPlaying + ", mHasStartedContactList = " + this.mHasStartedContactList);
        post(new Runnable() { // from class: com.ts.carplay.linkadapter.iap.-$$Lambda$IapAdapter$HKXaF0E5pEnwTycFtpHdbtOSu84
            @Override // java.lang.Runnable
            public final void run() throws RemoteException {
                this.f$0.lambda$setCallbackUpdate$0$IapAdapter(type, isStart);
            }
        });
    }

    public /* synthetic */ void lambda$setCallbackUpdate$0$IapAdapter(int type, boolean isStart) throws RemoteException {
        if (type == 0) {
            setCallStateUpdates(isStart);
            return;
        }
        if (type == 1) {
            setRouteGuidanceUpdates(isStart);
        } else if (type == 2) {
            setNowPlayingUpdates(isStart);
        } else if (type == 3) {
            setContactListUpdates(isStart);
        }
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public Object getCurrentInfo(int type) {
        LogUtil.debug(TAG, "getInfo type = " + type);
        Object info = null;
        if (type == 0) {
            synchronized (this.mCallStateInfo) {
                if (!this.mCallStateInfo.isDefaultValue()) {
                    CallStateInfo callStateInfo = new CallStateInfo();
                    callStateInfo.copy(this.mCallStateInfo);
                    info = callStateInfo;
                    LogUtil.debug(TAG, "CallStateInfo info: " + info.toString());
                }
            }
        } else if (type == 1) {
            synchronized (this.mRouteGuidanceInfo) {
                if (!this.mRouteGuidanceInfo.isDefaultValue()) {
                    RouteGuidanceInfo routeGuidanceInfo = new RouteGuidanceInfo();
                    routeGuidanceInfo.copy(this.mRouteGuidanceInfo);
                    info = routeGuidanceInfo;
                    LogUtil.debug(TAG, "RouteGuidanceInfo info: " + info.toString());
                }
            }
        } else if (type == 2) {
            synchronized (this.mNowPlayingInfo) {
                if (!this.mNowPlayingInfo.isDefaultValue()) {
                    NowPlayingInfo nowPlayingInfo = new NowPlayingInfo();
                    nowPlayingInfo.copy(this.mNowPlayingInfo);
                    info = nowPlayingInfo;
                    LogUtil.debug(TAG, "NowPlayingInfo info: " + info.toString());
                }
            }
        }
        return info;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int getContactListCount(int type) {
        int count = -1;
        if (type == 0) {
            count = this.mRecentListCount;
        } else if (type == 1) {
            count = this.mFavoriteListCount;
        }
        LogUtil.debug(TAG, "getContactListCount count: " + count + ", type: " + type);
        return count;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public List<RecentContactListInfo> getRecentListInfo() {
        List<RecentContactListInfo> recentContactListInfo = null;
        synchronized (this.mSynchronizedRecentContactList) {
            if (this.mRecentContactListInfo != null) {
                recentContactListInfo = new ArrayList<>();
                for (RecentContactListInfo info : this.mRecentContactListInfo) {
                    recentContactListInfo.add(info);
                }
            }
        }
        return recentContactListInfo;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public List<FavoriteContactListInfo> getFavoriteListInfo() {
        List<FavoriteContactListInfo> favoriteContactListInfo = null;
        synchronized (this.mSynchronizedFavoriteContactList) {
            if (this.mFavoriteContactListInfo != null) {
                favoriteContactListInfo = new ArrayList<>();
                for (FavoriteContactListInfo info : this.mFavoriteContactListInfo) {
                    favoriteContactListInfo.add(info);
                }
            }
        }
        return favoriteContactListInfo;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int sendCarplayStartSession(int clientType, CarplayStartSessionInfo info) {
        LogUtil.debug(TAG, "sendCarplayStartSession CarplayStartSessionInfo:" + info);
        int ret = -1;
        IapController.IapSession iapSession = getIapSession(clientType);
        if (iapSession != null) {
            ret = iapSession.sendCarplayStartSession(ConversionUtil.getInstance().convertStartSession(clientType, info));
        } else {
            LogUtil.warning(TAG, "sendCarplayStartSession iapSession is null.");
        }
        LogUtil.debug(TAG, "sendCarplayStartSession ret:" + ret);
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.iap.IIapAdapter
    public int getNewConnectType() {
        return this.mNewCarPlayConnectType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int convertClientTypeToIndex(int clientType) {
        int ret = -1;
        switch (clientType) {
            case 16:
                ret = 3;
                break;
            case 17:
                ret = 0;
                this.mNewCarPlayConnectType = 0;
                break;
            case 18:
                ret = 1;
                this.mNewCarPlayConnectType = 1;
                break;
            case 19:
                ret = 2;
                break;
        }
        if (ret <= -1 || ret >= 4) {
            return -1;
        }
        return ret;
    }

    private IapController.IapSession getIapSession(int clientType) {
        int index = convertClientTypeToIndex(clientType);
        if (-1 == index) {
            LogUtil.warning(TAG, "getIapSession clientType:" + clientType + " invalid.");
            return null;
        }
        IapController.IapSession iapSession = null;
        IapSessionInfo[] iapSessionInfoArr = this.mLists;
        if (iapSessionInfoArr[index] != null) {
            synchronized (iapSessionInfoArr[index]) {
                iapSession = this.mLists[index].getIapSession();
            }
        }
        return iapSession;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001c, code lost:
    
        r0 = r6.mLists[r1].getClientType();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int findClientType(ts.car.iap.protocol.IapController.IapSession r7) {
        /*
            r6 = this;
            r0 = 153(0x99, float:2.14E-43)
            r1 = -1
            r2 = 0
            r1 = 0
        L5:
            r3 = 4
            if (r1 >= r3) goto L2e
            com.ts.carplay.linkadapter.iap.IapSessionInfo[] r4 = r6.mLists
            r4 = r4[r1]
            monitor-enter(r4)
            com.ts.carplay.linkadapter.iap.IapSessionInfo[] r5 = r6.mLists     // Catch: java.lang.Throwable -> L2b
            r5 = r5[r1]     // Catch: java.lang.Throwable -> L2b
            ts.car.iap.protocol.IapController$IapSession r5 = r5.getIapSession()     // Catch: java.lang.Throwable -> L2b
            r2 = r5
            if (r2 == 0) goto L27
            if (r7 == 0) goto L27
            if (r2 != r7) goto L27
            com.ts.carplay.linkadapter.iap.IapSessionInfo[] r5 = r6.mLists     // Catch: java.lang.Throwable -> L2b
            r5 = r5[r1]     // Catch: java.lang.Throwable -> L2b
            int r5 = r5.getClientType()     // Catch: java.lang.Throwable -> L2b
            r0 = r5
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2b
            goto L2e
        L27:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2b
            int r1 = r1 + 1
            goto L5
        L2b:
            r3 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L2b
            throw r3
        L2e:
            if (r1 != r3) goto L46
            java.lang.String r3 = com.ts.carplay.linkadapter.iap.IapAdapter.TAG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "findClientType has not session:"
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.ts.carplay.common.util.LogUtil.warning(r3, r4)
        L46:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.carplay.linkadapter.iap.IapAdapter.findClientType(ts.car.iap.protocol.IapController$IapSession):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<RecentContactListInfo> copyRecentListInfo(ArrayList<RecentListInfo> list) {
        if (list != null) {
            List<RecentContactListInfo> recentContactListInfo = new ArrayList<>();
            for (RecentListInfo recentListInfo : list) {
                RecentContactListInfo info = new RecentContactListInfo();
                if (recentListInfo != null) {
                    if (recentListInfo.isIndexValid()) {
                        info.mIndex = recentListInfo.getIndex();
                    }
                    if (recentListInfo.isRemoteIdValid()) {
                        info.mRemoteId = recentListInfo.getRemoteId();
                    }
                    if (recentListInfo.isDisplayNameValid()) {
                        info.mDisplayName = recentListInfo.getDisplayName();
                    }
                    if (recentListInfo.isLableValid()) {
                        info.mLabel = recentListInfo.getLabel();
                    }
                    if (recentListInfo.isAddressBookIdValid()) {
                        info.mAddressBookId = recentListInfo.getAddressBookId();
                    }
                    if (recentListInfo.isServiceValid()) {
                        info.mService = recentListInfo.getService();
                    }
                    if (recentListInfo.isTypeValid()) {
                        info.mType = recentListInfo.getType();
                    }
                    if (recentListInfo.isUnixTimestampValid()) {
                        info.mUnixTimestamp = recentListInfo.getUnixTimestamp();
                    }
                    if (recentListInfo.isDurationValid()) {
                        info.mDuration = recentListInfo.getDuration();
                    }
                    if (recentListInfo.isOccurrencesValid()) {
                        info.mOccurrences = recentListInfo.getOccurrences();
                    }
                    recentContactListInfo.add(info);
                    this.mRecentContactListInfo.add(info);
                    LogUtil.info(TAG, "copyRecentListInfo info: " + info);
                }
            }
            return recentContactListInfo;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FavoriteContactListInfo> copyFavoriteListInfo(ArrayList<FavoriteListInfo> list) {
        if (list != null) {
            List<FavoriteContactListInfo> favoriteContactListInfo = new ArrayList<>();
            for (FavoriteListInfo favoriteListInfo : list) {
                FavoriteContactListInfo info = new FavoriteContactListInfo();
                if (favoriteListInfo != null) {
                    if (favoriteListInfo.isIndexValid()) {
                        info.mIndex = favoriteListInfo.getIndex();
                    }
                    if (favoriteListInfo.isRemoteIdValid()) {
                        info.mRemoteId = favoriteListInfo.getRemoteId();
                    }
                    if (favoriteListInfo.isDisplayNameValid()) {
                        info.mDisplayName = favoriteListInfo.getDisplayName();
                    }
                    if (favoriteListInfo.isLableValid()) {
                        info.mLabel = favoriteListInfo.getLabel();
                    }
                    if (favoriteListInfo.isAddressBookIdValid()) {
                        info.mAddressBookId = favoriteListInfo.getAddressBookId();
                    }
                    if (favoriteListInfo.isServiceValid()) {
                        info.mService = favoriteListInfo.getService();
                    }
                    favoriteContactListInfo.add(info);
                    this.mFavoriteContactListInfo.add(info);
                    LogUtil.info(TAG, "copyFavoriteListInfo info: " + info);
                }
            }
            return favoriteContactListInfo;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallStateUpdates(boolean isStart) throws RemoteException {
        if (153 != this.mCurClientType) {
            if (!isStart) {
                if (this.mHasStartedCallState) {
                    LogUtil.info(TAG, "setCallbackListSize stopCallStateUpdates.");
                    stopCallStateUpdates(this.mCurClientType);
                }
            } else if (!this.mHasStartedCallState) {
                LogUtil.info(TAG, "setCallbackListSize startCallStateUpdates.");
                startCallStateUpdates(this.mCurClientType);
            }
        }
        synchronized (this.mSynchronizedCallState) {
            this.mIsNeedStartCallState = isStart;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRouteGuidanceUpdates(boolean isStart) throws RemoteException {
        if (153 != this.mCurClientType) {
            if (!isStart) {
                if (this.mHasStartedRouteGuidance) {
                    LogUtil.info(TAG, "setCallbackListSize stopRouteGuidanceUpdates.");
                    stopRouteGuidanceUpdates(this.mCurClientType);
                }
            } else if (!this.mHasStartedRouteGuidance) {
                LogUtil.info(TAG, "setCallbackListSize startRouteGuidanceUpdates.");
                startRouteGuidanceUpdates(this.mCurClientType);
            }
        }
        synchronized (this.mSynchronizedRouteGuidance) {
            this.mIsNeedStartRouteGuidance = isStart;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNowPlayingUpdates(boolean isStart) throws RemoteException {
        if (153 != this.mCurClientType) {
            if (!isStart) {
                if (this.mHasStartedNowPlaying) {
                    LogUtil.info(TAG, "setCallbackListSize stopNowPlayingUpdates.");
                    stopNowPlayingUpdates(this.mCurClientType);
                }
            } else if (!this.mHasStartedNowPlaying) {
                LogUtil.info(TAG, "setCallbackListSize startNowPlayingUpdates.");
                startNowPlayingUpdates(this.mCurClientType);
            }
        }
        synchronized (this.mSynchronizedNowPlaying) {
            this.mIsNeedStartNowPlaying = isStart;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContactListUpdates(boolean isStart) throws RemoteException {
        if (153 != this.mCurClientType) {
            if (!isStart) {
                if (this.mHasStartedContactList) {
                    LogUtil.info(TAG, "setCallbackListSize stopContactListUpdates.");
                    stopContactListUpdates(this.mCurClientType);
                }
            } else if (!this.mHasStartedContactList) {
                LogUtil.info(TAG, "setCallbackListSize startContactListUpdates.");
                startContactListUpdates(this.mCurClientType);
            }
        }
        synchronized (this.mSynchronizedContactList) {
            this.mIsNeedStartContactList = isStart;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transferByteToUrl(byte[] artworkData, int artworkSize) {
        if (artworkData == null || artworkSize == 0) {
            this.mNowPlayingInfo.setArtworkPath(null);
            return;
        }
        if (this.mNowPlayingInfo.getArtworkData() != null && artworkSize == this.mNowPlayingInfo.getArtworkSize() && Arrays.equals(artworkData, this.mNowPlayingInfo.getArtworkData())) {
            LogUtil.debug(TAG, "mNowPlayingInfo Artwork is equal, not need to handle");
            return;
        }
        int i = this.mArtworkPathIndex;
        if (i < 5) {
            this.mArtworkPathIndex = i + 1;
        } else {
            this.mArtworkPathIndex = 1;
        }
        File file = new File(this.mArtworkFilePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File fileArtwork = new File(this.mArtworkFilePath, this.mArtworkPathIndex + ".png");
        LogUtil.debug(TAG, "mNowPlayingInfo setArtworkPath:" + fileArtwork.getAbsolutePath());
        this.mNowPlayingInfo.setArtworkPath(fileArtwork.getAbsolutePath());
    }

    private boolean deleteArtWorkPathDirectory(String filePath) {
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteArtWorkPathDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        return flag;
    }

    private boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }
}
