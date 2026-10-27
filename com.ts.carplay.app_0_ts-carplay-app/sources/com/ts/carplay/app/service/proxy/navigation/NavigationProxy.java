package com.ts.carplay.app.service.proxy.navigation;

import android.content.Context;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.ts.carplay.app.R;
import com.ts.carplay.app.service.common.IProxy;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.app.service.proxy.navigation.Utils;
import com.ts.carplay.app.ui.display.constants.DisplayConstants;
import com.ts.carplay.app.ui.display.model.LinkStatusCallback;
import com.ts.carplay.app.ui.display.model.LinkStatusModel;
import com.ts.carplay.utils.LogUtil;
import com.ts.lib.cluster.ClusterCommManager;
import com.ts.lib.cluster.common.IClusterCommCallback;
import com.ts.lib.cluster.common.data.Constants;
import ts.car.service.carplay.data.RouteGuidanceInfo;

/* JADX INFO: loaded from: classes.dex */
public class NavigationProxy extends IProxy {
    private static final int CLUSTER_HEIGHT_PIXELS = 372;
    private static final int CLUSTER_MAP_STATE_BIG = 1;
    private static final int CLUSTER_MAP_STATE_SMALL = 3;
    private static final int CLUSTER_WIDTH_PIXELS = 660;
    private static final String CONVERT_DISTANCE_DISPLAY_UNITS_FEET = "ft";
    private static final String CONVERT_DISTANCE_DISPLAY_UNITS_KM = "km";
    private static final String CONVERT_DISTANCE_DISPLAY_UNITS_METER = "m";
    private static final String CONVERT_DISTANCE_DISPLAY_UNITS_MILES = "mi";
    private static final String CONVERT_DISTANCE_DISPLAY_UNITS_YARDS = "yards";
    private static final int DATA_TYPE_NAVI_SEND_NAVI_STATE = 35;
    private static final int DISTANCE_DISPLAY_NOT_PRESENT = -1;
    private static final int DISTANCE_DISPLAY_UNITS_FEET = 4;
    private static final int DISTANCE_DISPLAY_UNITS_KM = 0;
    private static final int DISTANCE_DISPLAY_UNITS_M = 2;
    private static final int DISTANCE_DISPLAY_UNITS_METER = 2;
    private static final int DISTANCE_DISPLAY_UNITS_MILES = 1;
    private static final int DISTANCE_DISPLAY_UNITS_YARDS = 3;
    private static final int HUD_NAVI_STATE_END = 4;
    private static final int HUD_NAVI_STATE_START = 2;
    private static final String KEY_BUNDLE_MAP_TYPE = "mapType";
    private static final String KEY_BUNDLE_NAVI_STATE = "naviState";
    private static final String KEY_MSG_TYPE = "msgType";
    private static final String KEY_SUB_TYPE = "subtype";
    private static final int MAP_STATE_CLOSE = 1;
    private static final int MAP_STATE_FULL_CLOSE = 3;
    private static final int MAP_STATE_FULL_OPEN = 6;
    private static final int MAP_STATE_NOT_SUPPORTED = 10;
    private static final int MAP_STATE_SAMPLE_OPEN = 7;
    private static final int MAP_STATE_SMALL_CLOSE = 2;
    private static final int MAP_STATE_SMALL_OPEN = 5;
    private static final int MAP_TYPE_AUTO = 3;
    private static final int MAP_TYPE_CARPLAY = 2;
    private static final int MAP_TYPE_INVALID = 0;
    private static final int MAP_TYPE_LOCAL = 1;
    private static final String MC_LEFT_VALUE = "180";
    private static final String MR_RIGHT_VALUE = "123";
    private static final int MSG_TYPE_NAVI = 3;
    private static final int NAVI_STATE_END = 2;
    private static final int NAVI_STATE_START = 1;
    public static final int NAV_CLOSE = 1;
    public static final int NAV_FULL = 4;
    public static final int NAV_SIMPLE = 2;
    public static final int NAV_SMALL = 3;
    private static final int ONE_DAY_HOUR = 24;
    private static final int ONE_HOUR_SECONDS = 3600;
    private static final int ONE_MINUTE_SECONDS = 60;
    public static final int OVERSEAS_MAP_SUPPLIER_TYPE_CARPLAY = 9;
    public static final int OVERSEAS_MAP_SUPPLIER_TYPE_INVALID = 0;
    private static final String PA_RIGHT_VALUE = "143";
    private static final String PERSIST_SYS_AUTO_TYPE = "persist.sys.AutoType";
    private static final int PRESENTATION_DELAY = 90;
    private static final int SINGLE_DIGIT_THRESHOLD = 10;
    private static final int SUB_TYPE_NAVI_SEND_NAVI_STATE = 2;
    private static final String TAG = "NavigationProxy";
    private static final String TANG_VALUE = "196";
    private static final int THOUSAND = 1000;
    private static final String UEK_RIGHT_VALUE = "208";
    private static final String UKE_LEFT_VALUE = "182";
    public static final int VIEW_AREA_FULL = 1;
    public static final int VIEW_AREA_SMALL = 0;
    private static final int WAIT_FOR_SUGGEST_UI = 300;
    private static final int WAIT_FOR_UPDATEVIEWAREA = 470;
    private int mAapDisplayAreaHeight;
    private int mAapDisplayAreaMarginLeft;
    private int mAapDisplayAreaMarginTop;
    private int mAapDisplayAreaWidth;
    private BYDAutoInstrumentDevice mBydAutoInstrumentDevice;
    private BydAutoNaviDevice mBydAutoNaviDevice;
    private BydNaviListener mBydNaviListener;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private ICarPlayRemoteManagerListener mCarPlayRemoteManagerListener;
    private IClusterCommCallback.Stub mClusterCallBack;
    private ClusterDisplayManager mClusterDisplayManager;
    private Context mContext;
    private int mCurrentClusterNavDisplayMode;
    private LinearLayout mDisplayArea;
    private Surface mDisplaySurface;
    private final Handler mHandler;
    private boolean mHasShown;
    private boolean mIsAlreadyValid;
    private boolean mIsBigMap;
    private boolean mIsCarPlayConnected;
    private boolean mIsCarPlayNavi;
    private boolean mIsFirstShow;
    private boolean mIsFocus;
    private boolean mIsNativeNaviActive;
    private boolean mIsNeedEndNaviByRouteInfo;
    private boolean mIsPreNativeNaviActive;
    private int mLinkStatus;
    LinkStatusCallback mLinkStatusCallback;
    LinkStatusModel mLinkStatusModel;
    private final Object mLock;
    private long mNaviStartTime;
    private int mPreClusterNaviDisplayMode;
    private int mSuggestUrl;
    private boolean mSupportHud;
    private final SurfaceHolder.Callback mSurfaceHolderCallback;
    private SurfaceView mSurfaceProjection;
    private int mSurfaceWidth;
    private int mViewAreaIndex;

    public NavigationProxy(Looper looper) {
        super(looper);
        this.mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener();
        this.mAapDisplayAreaWidth = 660;
        this.mAapDisplayAreaHeight = 372;
        this.mAapDisplayAreaMarginLeft = 0;
        this.mAapDisplayAreaMarginTop = 0;
        this.mDisplayArea = null;
        this.mDisplaySurface = null;
        this.mHasShown = false;
        this.mLock = new Object();
        this.mIsCarPlayConnected = false;
        this.mIsAlreadyValid = false;
        this.mIsFocus = false;
        this.mIsBigMap = false;
        this.mIsCarPlayNavi = false;
        this.mLinkStatus = -1;
        this.mViewAreaIndex = 0;
        this.mIsNativeNaviActive = false;
        this.mIsPreNativeNaviActive = false;
        this.mPreClusterNaviDisplayMode = 1;
        this.mSuggestUrl = -1;
        this.mSurfaceWidth = -1;
        this.mIsFirstShow = true;
        this.mSupportHud = true;
        this.mHandler = new Handler() { // from class: com.ts.carplay.app.service.proxy.navigation.NavigationProxy.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 300) {
                    NavigationProxy.this.notifyNotSupported();
                } else if (msg.what == 470) {
                    LogUtil.debug(NavigationProxy.TAG, "CP updateViewArea success, notify cluster Open CP FULL");
                    NavigationProxy.this.sendNaviPresentationState(true);
                }
            }
        };
        this.mSurfaceHolderCallback = new SurfaceHolder.Callback() { // from class: com.ts.carplay.app.service.proxy.navigation.NavigationProxy.2
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder holder) {
                LogUtil.info(NavigationProxy.TAG, "mSurfaceHolderCallback---surfaceCreated");
                if (NavigationProxy.this.mDisplaySurface != null) {
                    NavigationProxy.this.mDisplaySurface.release();
                }
                NavigationProxy.this.mDisplaySurface = holder.getSurface();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                LogUtil.info(NavigationProxy.TAG, "mSurfaceHolderCallback---surfaceChanged width: " + width + ", height: " + height);
                if (NavigationProxy.this.mDisplaySurface == null) {
                    LogUtil.info(NavigationProxy.TAG, "surfaceChanged create Surface");
                    NavigationProxy.this.mDisplaySurface = holder.getSurface();
                }
                NavigationProxy.this.mSurfaceWidth = width;
                NavigationProxy.this.setSurface();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder holder) {
                LogUtil.info(NavigationProxy.TAG, "mSurfaceHolderCallback---surfaceDestroyed");
                if (NavigationProxy.this.mDisplaySurface != null) {
                    NavigationProxy.this.mDisplaySurface.release();
                    NavigationProxy.this.mDisplaySurface = null;
                }
                NavigationProxy.this.mIsFirstShow = false;
                NavigationProxy.this.hideClusterProjection();
            }
        };
        this.mClusterCallBack = new IClusterCommCallback.Stub() { // from class: com.ts.carplay.app.service.proxy.navigation.NavigationProxy.3
            @Override // com.ts.lib.cluster.common.IClusterCommCallback
            public void onReceiveCommand(Bundle bundle) throws RemoteException {
                int dataType = bundle.getInt(Constants.Key.DATA_TYPE, -1);
                int msgType = bundle.getInt(NavigationProxy.KEY_MSG_TYPE, -1);
                int subType = bundle.getInt(NavigationProxy.KEY_SUB_TYPE, -1);
                if (35 == dataType && 3 == msgType && 2 == subType) {
                    int mapType = bundle.getInt(NavigationProxy.KEY_BUNDLE_MAP_TYPE, -1);
                    int naviState = bundle.getInt(NavigationProxy.KEY_BUNDLE_NAVI_STATE, -1);
                    LogUtil.info(NavigationProxy.TAG, "onReceiveCommand naviState change,mapType:" + mapType + " naviState:" + naviState);
                    if (mapType == 1) {
                        NavigationProxy.this.mIsNativeNaviActive = naviState == 1;
                        NavigationProxy.this.checkNativeNaviState();
                    } else if (naviState == 1) {
                        NavigationProxy.this.mIsNativeNaviActive = false;
                        NavigationProxy.this.checkNativeNaviState();
                    }
                }
            }
        };
        this.mLinkStatusCallback = new LinkStatusCallback() { // from class: com.ts.carplay.app.service.proxy.navigation.NavigationProxy.4
            @Override // com.ts.carplay.app.ui.display.model.LinkStatusCallback
            public void onStatusChanged(int linkStatus) {
                NavigationProxy.this.mLinkStatus = linkStatus;
                LogUtil.info(NavigationProxy.TAG, "onStatusChanged --- mLinkStatus: " + NavigationProxy.this.mLinkStatus);
            }

            @Override // com.ts.carplay.app.ui.display.model.LinkStatusCallback
            public void onRequestVideoFocus(int requestType) {
                LogUtil.info(NavigationProxy.TAG, "onRequestVideoFocus --- requestType: " + requestType);
            }
        };
    }

    @Override // com.ts.carplay.app.service.common.IProxy
    public void init(Context context) throws RemoteException {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        CarPlayRemoteManager carPlayRemoteManager = CarPlayRemoteManager.getInstance();
        this.mCarPlayRemoteManager = carPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.addCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
        }
        LinkStatusModel linkStatusModel = new LinkStatusModel(context);
        this.mLinkStatusModel = linkStatusModel;
        linkStatusModel.registerCallback(this.mLinkStatusCallback);
        this.mLinkStatusModel.initManager(context);
        this.mBydAutoNaviDevice = BydAutoNaviDevice.getInstance(context);
        this.mBydAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(context);
        BydNaviListener bydNaviListener = new BydNaviListener();
        this.mBydNaviListener = bydNaviListener;
        this.mBydAutoNaviDevice.registerListener((AbsBYDAutoNaviListener) bydNaviListener);
        this.mSupportHud = isSupportHud();
        if (ClusterCommManager.getInstance(this.mContext) != null) {
            ClusterCommManager.getInstance(this.mContext).registerCallback(16, this.mClusterCallBack);
        }
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.layout_cluster_navi, (ViewGroup) null, false);
        this.mDisplayArea = linearLayout;
        SurfaceView surfaceView = (SurfaceView) linearLayout.findViewById(R.id.surface_cluster_projection);
        this.mSurfaceProjection = surfaceView;
        surfaceView.getHolder().addCallback(this.mSurfaceHolderCallback);
        setDisplayParams();
        this.mClusterDisplayManager = new ClusterDisplayManager(this.mContext, this.mDisplayArea, this);
    }

    @Override // com.ts.carplay.app.service.common.IProxy
    public void uninit() throws RemoteException {
        LogUtil.info(TAG, "uninit");
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.removeCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
            this.mCarPlayRemoteManager.unregisterRouteGuidanceListener();
            this.mCarPlayRemoteManager = null;
        }
        removeCallbacksAndMessages(null);
        BydAutoNaviDevice bydAutoNaviDevice = this.mBydAutoNaviDevice;
        if (bydAutoNaviDevice != null) {
            BydNaviListener bydNaviListener = this.mBydNaviListener;
            if (bydNaviListener != null) {
                bydAutoNaviDevice.unregisterListener((AbsBYDAutoNaviListener) bydNaviListener);
                this.mBydNaviListener = null;
            }
            this.mBydAutoNaviDevice = null;
        }
        if (ClusterCommManager.getInstance(this.mContext) != null && this.mClusterCallBack != null) {
            ClusterCommManager.getInstance(this.mContext).unregisterCallback(16, this.mClusterCallBack);
            this.mIsPreNativeNaviActive = false;
            this.mClusterCallBack = null;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null) {
            linkStatusModel.unRegisterCallback();
            this.mLinkStatusModel.release(this.mContext);
            this.mLinkStatusModel = null;
        }
        ClusterDisplayManager clusterDisplayManager = this.mClusterDisplayManager;
        if (clusterDisplayManager != null) {
            clusterDisplayManager.destroy();
        }
        this.mIsFirstShow = false;
        SurfaceView surfaceView = this.mSurfaceProjection;
        if (surfaceView != null && surfaceView.getHolder() != null) {
            this.mSurfaceProjection.getHolder().removeCallback(this.mSurfaceHolderCallback);
        }
        this.mSurfaceWidth = -1;
        this.mSurfaceProjection = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        private CarPlayRemoteManagerListener() {
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlaySessionStateChanged(final int status) {
            NavigationProxy.this.post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$CarPlayRemoteManagerListener$u4x9qtqfhBtkeZq0mWfmGyl7otI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCarPlaySessionStateChanged$0$NavigationProxy$CarPlayRemoteManagerListener(status);
                }
            });
            if (status == 2) {
                NavigationProxy.this.mIsCarPlayConnected = true;
                if (NavigationProxy.this.mCarPlayRemoteManager != null) {
                    NavigationProxy.this.mCarPlayRemoteManager.registerRouteGuidanceListener();
                }
            } else if (status == 4) {
                if (NavigationProxy.this.mClusterDisplayManager != null) {
                    NavigationProxy.this.post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$CarPlayRemoteManagerListener$WRV5Uhqr4BjSfcCr014944_D3HU
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onCarPlaySessionStateChanged$1$NavigationProxy$CarPlayRemoteManagerListener();
                        }
                    });
                }
                NavigationProxy.this.mIsFirstShow = false;
                NavigationProxy.this.mIsCarPlayConnected = false;
                if (NavigationProxy.this.mCarPlayRemoteManager != null) {
                    NavigationProxy.this.mCarPlayRemoteManager.unregisterRouteGuidanceListener();
                }
                NavigationProxy.this.mSuggestUrl = -1;
                NavigationProxy.this.mViewAreaIndex = 0;
            }
            LogUtil.debug(NavigationProxy.TAG, "onCarPlaySessionStateChanged status: " + status + ", mIsCarPlayConnect: " + NavigationProxy.this.mIsCarPlayConnected);
        }

        public /* synthetic */ void lambda$onCarPlaySessionStateChanged$0$NavigationProxy$CarPlayRemoteManagerListener(int status) {
            NavigationProxy.this.handleCpNaviWithLinkStatus(status);
        }

        public /* synthetic */ void lambda$onCarPlaySessionStateChanged$1$NavigationProxy$CarPlayRemoteManagerListener() {
            NavigationProxy.this.mClusterDisplayManager.destroy();
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onAppStatesChanged(int appState, int entity, int speechMode) {
            LogUtil.debug(NavigationProxy.TAG, "onAppStatesChanged appState: " + appState + ", entity: " + entity + ", speechMode: " + speechMode + ", mIsCarPlayConnect: " + NavigationProxy.this.mIsCarPlayConnected);
            if (appState == 1) {
                if (NavigationProxy.this.mCarPlayRemoteManager != null) {
                    if (!NavigationProxy.this.mIsCarPlayConnected || entity != 1) {
                        if (NavigationProxy.this.mIsNeedEndNaviByRouteInfo) {
                            LogUtil.info(NavigationProxy.TAG, "notify DIM TurnByTurn end by route.");
                            return;
                        }
                        LogUtil.info(NavigationProxy.TAG, "notify DIM TurnByTurn end.");
                        NavigationProxy.this.mIsCarPlayNavi = false;
                        if (NavigationProxy.this.mIsFocus) {
                            LogUtil.info(NavigationProxy.TAG, "lost focus,notify cp navi end.");
                            if (NavigationProxy.this.mSupportHud) {
                                NavigationProxy.this.mBydAutoNaviDevice.sendNaviState(2, 2);
                                BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(4);
                                NavigationProxy.this.mBydAutoNaviDevice.setOverseasMapSupplier(0);
                            } else {
                                NavigationProxy.this.mBydAutoNaviDevice.sendNaviStateOnlyCPAA(2, 2);
                            }
                        }
                        if (NavigationProxy.this.mIsNativeNaviActive) {
                            final NavigationProxy navigationProxy = NavigationProxy.this;
                            navigationProxy.post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$CarPlayRemoteManagerListener$DZN6c8xAX--CHJPUtUaOT8EAwwA
                                @Override // java.lang.Runnable
                                public final void run() {
                                    navigationProxy.hideClusterProjection();
                                }
                            });
                            return;
                        }
                        return;
                    }
                    NavigationProxy.this.mNaviStartTime = System.currentTimeMillis() / 1000;
                    LogUtil.info(NavigationProxy.TAG, "notify DIM TurnByTurn Started. start time:" + NavigationProxy.this.mNaviStartTime);
                    NavigationProxy.this.mIsCarPlayNavi = true;
                    NavigationProxy.this.mIsNeedEndNaviByRouteInfo = false;
                    NavigationProxy navigationProxy2 = NavigationProxy.this;
                    navigationProxy2.mCurrentClusterNavDisplayMode = navigationProxy2.mBydAutoNaviDevice.getInsNavDisplayMode();
                    if (NavigationProxy.this.mBydAutoNaviDevice.getFocusMapType() == 2) {
                        LogUtil.debug(NavigationProxy.TAG, "when have map focus and CP navi in use sendNaviPresentationState");
                        if (NavigationProxy.this.mSupportHud) {
                            NavigationProxy.this.mBydAutoNaviDevice.sendNaviState(2, 1);
                            BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(2);
                            NavigationProxy.this.mBydAutoNaviDevice.setOverseasMapSupplier(9);
                        } else {
                            BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(4);
                            NavigationProxy.this.mBydAutoNaviDevice.sendNaviStateOnlyCPAA(2, 1);
                        }
                        NavigationProxy.this.sendNaviPresentationState(true);
                        return;
                    }
                    NavigationProxy navigationProxy3 = NavigationProxy.this;
                    navigationProxy3.updateViewArea(navigationProxy3.mCurrentClusterNavDisplayMode);
                    if (NavigationProxy.this.mCurrentClusterNavDisplayMode == 4 || (NavigationProxy.this.mCurrentClusterNavDisplayMode == 3 && !NavigationProxy.this.mHasShown)) {
                        LogUtil.info(NavigationProxy.TAG, "cp navi start, notify DIM close");
                        NavigationProxy.this.mBydAutoNaviDevice.sendNaviPresentationState(2, 1);
                    }
                    NavigationProxy.this.mBydAutoNaviDevice.requestMapFocus(2);
                    return;
                }
                LogUtil.warning(NavigationProxy.TAG, "onAppStatesChanged mCarPlayRemoteManager is null");
                return;
            }
            LogUtil.info(NavigationProxy.TAG, "the appState isn't TBT.");
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onRouteGuidanceUpdate(RouteGuidanceInfo info) {
            LogUtil.verbose(NavigationProxy.TAG, "mIsNeedEndNaviByRouteInfo:" + NavigationProxy.this.mIsNeedEndNaviByRouteInfo + " mIsCarPlayNavi:" + NavigationProxy.this.mIsCarPlayNavi + " mIsFocus:" + NavigationProxy.this.mIsFocus + " state:" + info.getRouteGuidanceState() + "++++++++++++++RouteGuidanceInfo: " + info.toString());
            if (!NavigationProxy.this.mIsCarPlayNavi && !NavigationProxy.this.mIsNativeNaviActive && info.getRouteGuidanceState() == 1) {
                LogUtil.info(NavigationProxy.TAG, "start cp navi by route");
                NavigationProxy.this.mIsNeedEndNaviByRouteInfo = true;
                NavigationProxy.this.mIsCarPlayNavi = true;
                if (NavigationProxy.this.mSupportHud) {
                    NavigationProxy.this.mBydAutoNaviDevice.sendNaviState(2, 1);
                    BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(2);
                    NavigationProxy.this.mBydAutoNaviDevice.setOverseasMapSupplier(9);
                } else {
                    BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(4);
                    NavigationProxy.this.mBydAutoNaviDevice.sendNaviStateOnlyCPAA(2, 1);
                }
                if (!NavigationProxy.this.mIsFocus) {
                    NavigationProxy.this.mBydAutoNaviDevice.requestMapFocus(2);
                } else {
                    NavigationProxy.this.sendNaviPresentationState(true);
                }
            }
            NavigationProxy.this.updateRouteGuidanceInfoOnDim(info);
            if (NavigationProxy.this.mIsFocus && NavigationProxy.this.mIsCarPlayNavi && NavigationProxy.this.mIsNeedEndNaviByRouteInfo && info.getRouteGuidanceState() == -1) {
                LogUtil.info(NavigationProxy.TAG, "close cp navi by route");
                NavigationProxy.this.mIsNeedEndNaviByRouteInfo = false;
                NavigationProxy.this.mIsCarPlayNavi = false;
                if (NavigationProxy.this.mSupportHud) {
                    NavigationProxy.this.mBydAutoNaviDevice.sendNaviState(2, 2);
                    BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(4);
                    NavigationProxy.this.mBydAutoNaviDevice.setOverseasMapSupplier(0);
                    return;
                }
                NavigationProxy.this.mBydAutoNaviDevice.sendNaviStateOnlyCPAA(2, 2);
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onSuggestUi(int url) {
            LogUtil.info(NavigationProxy.TAG, "onSuggestUi url: " + url + " mHasShown:" + NavigationProxy.this.mHasShown + " mIsFocus:" + NavigationProxy.this.mIsFocus + " mIsCarPlayNavi:" + NavigationProxy.this.mIsCarPlayNavi);
            if ((url & 1) > 0) {
                NavigationProxy.this.mSuggestUrl = 1;
                if (NavigationProxy.this.mIsCarPlayNavi && NavigationProxy.this.mIsFocus && !NavigationProxy.this.mHasShown) {
                    NavigationProxy.this.sendNaviPresentationState(true);
                }
            }
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onAltRequestSetup() {
            LogUtil.info(NavigationProxy.TAG, "onAltRequestSetup");
            NavigationProxy.this.mIsFirstShow = true;
            if (NavigationProxy.this.mClusterDisplayManager != null) {
                NavigationProxy.this.post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$CarPlayRemoteManagerListener$58lJqVGPDrc56JTS1voyxlOM2EI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onAltRequestSetup$3$NavigationProxy$CarPlayRemoteManagerListener();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onAltRequestSetup$3$NavigationProxy$CarPlayRemoteManagerListener() {
            NavigationProxy.this.mClusterDisplayManager.init();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewArea(int mode) {
        int index;
        if (mode != 4 && mode != 3) {
            LogUtil.info(TAG, "this mode do not need to update");
            return;
        }
        if (mode == 3) {
            index = 0;
        } else {
            index = 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("updateViewArea,mode:");
        sb.append(mode);
        sb.append(",index:");
        sb.append(index);
        sb.append(",mViewAreaIndex");
        sb.append(this.mViewAreaIndex);
        sb.append(",mCarPlayRemoteManager:");
        sb.append(this.mCarPlayRemoteManager != null);
        LogUtil.info(TAG, sb.toString());
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null && this.mViewAreaIndex != index) {
            this.mViewAreaIndex = index;
            carPlayRemoteManager.updateViewArea(DisplayConstants.SCREEN_CLUSTER_UUID, index, DisplayConstants.VIEW_AREA_ADJACENT_ARRAY, 10);
        }
    }

    private String convertDisplayUnitsToString(int carplayDisplayUnits) {
        LogUtil.debug(TAG, "convertDisplayUnitsToString: carplayDisplayUnits ==" + carplayDisplayUnits);
        String displayUnit = "";
        if (carplayDisplayUnits == 0) {
            displayUnit = CONVERT_DISTANCE_DISPLAY_UNITS_KM;
        } else if (carplayDisplayUnits == 1) {
            displayUnit = CONVERT_DISTANCE_DISPLAY_UNITS_MILES;
        } else if (carplayDisplayUnits == 2) {
            displayUnit = CONVERT_DISTANCE_DISPLAY_UNITS_METER;
        } else if (carplayDisplayUnits == 3) {
            displayUnit = CONVERT_DISTANCE_DISPLAY_UNITS_YARDS;
        } else if (carplayDisplayUnits == 4) {
            displayUnit = CONVERT_DISTANCE_DISPLAY_UNITS_FEET;
        }
        LogUtil.debug(TAG, "convertDisplayUnitsToString: convertDisplayUnitResult==" + displayUnit);
        return displayUnit;
    }

    private String convertSecondToDays(long secondTime) {
        long days = secondTime / 86400;
        if (days > 0) {
            String convertDays = "+" + days + "D";
            return convertDays;
        }
        return null;
    }

    private String calculateArrivalTime(long remainSeconds) {
        String calculateArrivalTime;
        LogUtil.debug(TAG, "calculateArrivalTime: remainSeconds==" + remainSeconds);
        long hours = remainSeconds / 3600;
        long remainingSeconds = remainSeconds % 3600;
        long minutes = remainingSeconds / 60;
        if (hours > 0 && minutes > 0) {
            calculateArrivalTime = String.format("%dH%dMin", Long.valueOf(hours), Long.valueOf(minutes));
        } else if (hours <= 0 && minutes > 0) {
            calculateArrivalTime = String.format("%dMin", Long.valueOf(minutes));
        } else if (hours > 0 && minutes <= 0) {
            calculateArrivalTime = String.format("%dH", Long.valueOf(hours));
        } else {
            calculateArrivalTime = null;
        }
        LogUtil.debug(TAG, "calculateArrivalTime: calculateArrivalTime==" + calculateArrivalTime);
        return calculateArrivalTime;
    }

    private String calculateToDestTime(int hour, int minute) {
        String minutes;
        LogUtil.debug(TAG, "calculateToDestTime: hour==" + hour + ", minute==" + minute);
        if (minute < 10) {
            minutes = "0" + minute;
        } else {
            minutes = String.valueOf(minute);
        }
        String calculateToDestTime = hour + ":" + minutes;
        LogUtil.debug(TAG, "calculateToDestTime: calculateToDestTime==" + calculateToDestTime);
        return calculateToDestTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRouteGuidanceInfoOnDim(RouteGuidanceInfo info) {
        String toDestTime;
        LogUtil.verbose(TAG, "updateRouteGuidanceInfoOnDim mIsCarPlayNavi: " + this.mIsCarPlayNavi + " mIsFocus:" + this.mIsFocus);
        if (this.mBydAutoNaviDevice != null) {
            if (this.mIsCarPlayNavi && this.mIsFocus) {
                int maneuverType = info.getManeuverType();
                int convertType = Utils.convertManeuver(maneuverType, info.getDrivingSide());
                LogUtil.verbose(TAG, "maneuverType " + maneuverType + " convertType:" + convertType);
                String nameToManeuver = info.getCurrentRoadName();
                StringBuilder sb = new StringBuilder();
                sb.append("nameToManeuver ");
                sb.append(nameToManeuver);
                LogUtil.verbose(TAG, sb.toString());
                String distanceToNextManeuverDisplayStr = info.getDistanceToNextManeuverDisplayStr();
                LogUtil.verbose(TAG, "distanceToNextManeuverDisplayStr " + distanceToNextManeuverDisplayStr);
                String distanceToNextManeuverDisplayUnits = convertDisplayUnitsToString(info.getDistanceToNextManeuverDisplayUnits());
                LogUtil.verbose(TAG, "distanceToNextManeuverDisplayUnits " + distanceToNextManeuverDisplayUnits);
                long endTime = info.getTimeRemainingToDestination();
                String distanceRemaining = info.getDistanceRemainingDisplayStr();
                if (TextUtils.isEmpty(distanceRemaining)) {
                    distanceRemaining = null;
                }
                String distanceToNextManeuver = distanceToNextManeuverDisplayStr;
                LogUtil.verbose(TAG, "distanceRemaining " + distanceRemaining);
                String distanceRemainingDisplayUnits = convertDisplayUnitsToString(info.getDistanceRemainingDisplayUnits());
                LogUtil.verbose(TAG, "distanceRemainingDisplayUnits " + distanceRemainingDisplayUnits);
                long estimatedTime = info.getEstimatedTimeOfArrival();
                Utils.EndTimeInfo estimatedTimeInfo = Utils.convertTime(endTime, estimatedTime);
                String toDestTime2 = calculateToDestTime(estimatedTimeInfo.getHours(), estimatedTimeInfo.getMinutes());
                LogUtil.verbose(TAG, estimatedTimeInfo.toString());
                String distToDestTime = calculateArrivalTime(endTime);
                if (convertType == 0 || distanceToNextManeuverDisplayUnits.equals("")) {
                    LogUtil.error(TAG, "TBT info is invalid, send 0m display Now");
                    convertType = 0;
                    distanceToNextManeuver = "";
                    nameToManeuver = "";
                    distanceToNextManeuverDisplayUnits = "";
                    toDestTime = null;
                } else {
                    toDestTime = toDestTime2;
                }
                if (this.mSupportHud) {
                    this.mBydAutoNaviDevice.sendSimpleNaviInfo(2, convertType, nameToManeuver, distanceToNextManeuver, distanceToNextManeuverDisplayUnits, 0, null, null, distanceRemaining, distanceRemainingDisplayUnits, null, null, null, null, distToDestTime, null, convertSecondToDays(endTime), toDestTime, null, null, null);
                    return;
                }
                this.mBydAutoInstrumentDevice.sendTbtSimpleNaviInfo(2, convertType, nameToManeuver, distanceToNextManeuver, distanceToNextManeuverDisplayUnits, 0, null, null, distanceRemaining, distanceRemainingDisplayUnits, distToDestTime, convertSecondToDays(endTime), toDestTime, null, null, null);
                return;
            }
            LogUtil.verbose(TAG, "has not notify DIM navi start.");
            return;
        }
        LogUtil.verbose(TAG, "updateRouteGuidanceInfoOnDim mNaviInteraction is null.");
    }

    public class BydNaviListener extends AbsBYDAutoNaviListener {
        public BydNaviListener() {
        }

        @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
        public void onInsUiChanged(int uiState) {
            NavigationProxy.this.mIsBigMap = uiState == 1 || uiState == 3;
            NavigationProxy navigationProxy = NavigationProxy.this;
            navigationProxy.mCurrentClusterNavDisplayMode = navigationProxy.mBydAutoNaviDevice.getInsNavDisplayMode();
            LogUtil.info(NavigationProxy.TAG, "onInsUiChanged value：" + uiState + " mIsBigMap:" + NavigationProxy.this.mIsBigMap + " mIsFocus:" + NavigationProxy.this.mIsFocus + " mode:" + NavigationProxy.this.mCurrentClusterNavDisplayMode);
            if (NavigationProxy.this.mIsFocus) {
                if (NavigationProxy.this.mCurrentClusterNavDisplayMode == 4 || NavigationProxy.this.mCurrentClusterNavDisplayMode == 3) {
                    if (NavigationProxy.this.mHasShown || NavigationProxy.this.mSuggestUrl <= 0 || !NavigationProxy.this.mIsCarPlayNavi) {
                        if (NavigationProxy.this.mCurrentClusterNavDisplayMode == 3 && NavigationProxy.this.mPreClusterNaviDisplayMode != NavigationProxy.this.mCurrentClusterNavDisplayMode) {
                            LogUtil.debug(NavigationProxy.TAG, "cluster changto SMALL Display mode, CP updateViewArea");
                            NavigationProxy navigationProxy2 = NavigationProxy.this;
                            navigationProxy2.updateViewArea(navigationProxy2.mCurrentClusterNavDisplayMode);
                        }
                    } else {
                        NavigationProxy.this.mHandler.removeMessages(300);
                        NavigationProxy.this.mLinkStatusModel.showUi(1, NavigationProxy.this.mSuggestUrl);
                        NavigationProxy.this.mHasShown = true;
                    }
                } else {
                    final NavigationProxy navigationProxy3 = NavigationProxy.this;
                    navigationProxy3.post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$BydNaviListener$i1v7gj1zHIIayw7v0Ktj8jptBc8
                        @Override // java.lang.Runnable
                        public final void run() {
                            navigationProxy3.hideClusterProjection();
                        }
                    });
                }
            } else {
                final NavigationProxy navigationProxy4 = NavigationProxy.this;
                navigationProxy4.post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$BydNaviListener$eYD48XmTzDzm6AEdKdQZDcjyBcE
                    @Override // java.lang.Runnable
                    public final void run() {
                        navigationProxy4.hideClusterProjection();
                    }
                });
            }
            NavigationProxy navigationProxy5 = NavigationProxy.this;
            navigationProxy5.mPreClusterNaviDisplayMode = navigationProxy5.mCurrentClusterNavDisplayMode;
        }

        @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
        public void onMapFocusChanged(int mapType) {
            NavigationProxy.this.mIsFocus = mapType == 2;
            LogUtil.info(NavigationProxy.TAG, "onMapFocusChanged mapType：" + mapType + " mIsFocus:" + NavigationProxy.this.mIsFocus + " mode:" + NavigationProxy.this.mCurrentClusterNavDisplayMode + " mIsCarPlayNavi:" + NavigationProxy.this.mIsCarPlayNavi);
            NavigationProxy navigationProxy = NavigationProxy.this;
            navigationProxy.mCurrentClusterNavDisplayMode = navigationProxy.mBydAutoNaviDevice.getInsNavDisplayMode();
            if (!NavigationProxy.this.mIsFocus) {
                NavigationProxy.this.mSuggestUrl = -1;
                return;
            }
            NavigationProxy navigationProxy2 = NavigationProxy.this;
            navigationProxy2.sendNaviPresentationState(navigationProxy2.mIsCarPlayNavi);
            if (NavigationProxy.this.mIsCarPlayNavi) {
                if (NavigationProxy.this.mSupportHud) {
                    NavigationProxy.this.mBydAutoNaviDevice.sendNaviState(2, 1);
                    BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(2);
                    NavigationProxy.this.mBydAutoNaviDevice.setOverseasMapSupplier(9);
                } else {
                    BYDAutoWhudDevice.getInstance(NavigationProxy.this.mContext).setNaviState(4);
                    NavigationProxy.this.mBydAutoNaviDevice.sendNaviStateOnlyCPAA(2, 1);
                }
            }
        }

        @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
        public void onINSNavDisplayMode(int value) {
            LogUtil.info(NavigationProxy.TAG, "onINSNavDisplayMode value：" + value + " mIsFocus:" + NavigationProxy.this.mIsFocus + " mode:" + NavigationProxy.this.mCurrentClusterNavDisplayMode);
            NavigationProxy.this.mCurrentClusterNavDisplayMode = value;
            if (NavigationProxy.this.mIsFocus) {
                int i = NavigationProxy.this.mCurrentClusterNavDisplayMode;
                if (i == 3) {
                    NavigationProxy.this.mHandler.removeMessages(470);
                    NavigationProxy navigationProxy = NavigationProxy.this;
                    navigationProxy.sendNaviPresentationState(navigationProxy.mIsFocus);
                } else if (i != 4) {
                    NavigationProxy.this.mHandler.removeMessages(470);
                    NavigationProxy navigationProxy2 = NavigationProxy.this;
                    navigationProxy2.sendNaviPresentationState(navigationProxy2.mIsFocus);
                } else {
                    NavigationProxy navigationProxy3 = NavigationProxy.this;
                    navigationProxy3.updateViewArea(navigationProxy3.mCurrentClusterNavDisplayMode);
                    NavigationProxy.this.mHandler.sendMessageDelayed(NavigationProxy.this.mHandler.obtainMessage(470), 470L);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNaviPresentationState(boolean isOpen) {
        BydAutoNaviDevice bydAutoNaviDevice = this.mBydAutoNaviDevice;
        if (bydAutoNaviDevice == null) {
            LogUtil.info(TAG, "sendNaviPresentationState() mBydAutoNaviDevice == null");
            return;
        }
        boolean z = bydAutoNaviDevice.getFocusMapType() == 2;
        this.mIsFocus = z;
        if (!z) {
            LogUtil.info(TAG, "sendNaviPresentationState() no focus,fail");
            return;
        }
        LogUtil.info(TAG, "sendNaviPresentationState() isOpen == " + isOpen + "- mHasShown" + this.mHasShown + " mCurrentClusterNavDisplayMode == " + this.mCurrentClusterNavDisplayMode);
        if (isOpen) {
            int i = this.mCurrentClusterNavDisplayMode;
            if (i == 2) {
                this.mBydAutoNaviDevice.sendNaviPresentationState(2, 7);
                LogUtil.info(TAG, "sendNaviPresentationState() cp start NAV_SAMPLE");
                return;
            }
            if (i == 3) {
                if (this.mSuggestUrl <= 0) {
                    LogUtil.info(TAG, "mSuggestUrl not callback,do not send cp start NAV_SMALL");
                    Handler handler = this.mHandler;
                    handler.sendMessageDelayed(handler.obtainMessage(300), 300L);
                    return;
                } else {
                    if (!this.mHasShown) {
                        this.mHandler.removeMessages(300);
                        this.mLinkStatusModel.showUi(1, this.mSuggestUrl);
                        this.mHasShown = true;
                    }
                    post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$SyKn8dWpgFqqZYJrj2guwj5yxVE
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.showClusterProjection();
                        }
                    });
                    postDelayed(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$Pwht3ZtJpNQZgKFn17vRwHoHuOQ
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$sendNaviPresentationState$0$NavigationProxy();
                        }
                    }, this.mClusterDisplayManager.getInitDelay() + 90);
                    return;
                }
            }
            if (i == 4) {
                if (this.mSuggestUrl <= 0) {
                    LogUtil.info(TAG, "mSuggestUrl not callback,do not send cp start NAV_FULL");
                    Handler handler2 = this.mHandler;
                    handler2.sendMessageDelayed(handler2.obtainMessage(300), 300L);
                    return;
                } else {
                    if (!this.mHasShown) {
                        this.mHandler.removeMessages(300);
                        this.mLinkStatusModel.showUi(1, this.mSuggestUrl);
                        this.mHasShown = true;
                    }
                    post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$SyKn8dWpgFqqZYJrj2guwj5yxVE
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.showClusterProjection();
                        }
                    });
                    postDelayed(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$ReEIsax3Uu1N1PkdeQpbYKxUl-Y
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$sendNaviPresentationState$1$NavigationProxy();
                        }
                    }, this.mClusterDisplayManager.getInitDelay() + 90);
                    return;
                }
            }
            this.mBydAutoNaviDevice.sendNaviPresentationState(2, 1);
            LogUtil.info(TAG, "sendNaviPresentationState() cp start NAV_CLOSE");
            return;
        }
        this.mBydAutoNaviDevice.sendNaviPresentationState(2, 1);
        LogUtil.info(TAG, "sendNaviPresentationState() cp NAV_CLOSE");
        post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$e-VSafOwSvKTZKV6Ru1UqbPeYLc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.hideClusterProjection();
            }
        });
    }

    public /* synthetic */ void lambda$sendNaviPresentationState$0$NavigationProxy() {
        this.mBydAutoNaviDevice.sendNaviPresentationState(2, 5);
        LogUtil.info(TAG, "sendNaviPresentationState() cp start NAV_SMALL");
    }

    public /* synthetic */ void lambda$sendNaviPresentationState$1$NavigationProxy() {
        this.mBydAutoNaviDevice.sendNaviPresentationState(2, 6);
        LogUtil.info(TAG, "sendNaviPresentationState() cp start NAV_FULL");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showClusterProjection() {
        synchronized (this.mLock) {
            if (this.mLinkStatus != 2) {
                LogUtil.info(TAG, "Projection device not activated and do nothing");
                return;
            }
            if (!this.mIsFocus) {
                LogUtil.info(TAG, "showClusterProjection nut no focus");
                return;
            }
            if (this.mSuggestUrl <= 0) {
                LogUtil.info(TAG, "suggest ui not callback,return");
                return;
            }
            if (this.mClusterDisplayManager != null) {
                this.mClusterDisplayManager.show();
            }
            LogUtil.info(TAG, " ---showClusterProjection--- mHasShown==" + this.mHasShown);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNotSupported() {
        if (this.mSuggestUrl <= 0 && this.mIsFocus) {
            LogUtil.debug(TAG, "notifyNotSupported ,mSuggestUrl ui not callback");
            this.mBydAutoNaviDevice.sendNaviPresentationState(2, 10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSurface() {
        Surface surface;
        LogUtil.info(TAG, " ---setSurface--- mHasShown==" + this.mHasShown + "===mIsFirstShow===" + this.mIsFirstShow + ",mSurfaceWidth:" + this.mSurfaceWidth);
        LinkStatusModel linkStatusModel = this.mLinkStatusModel;
        if (linkStatusModel != null && this.mIsFirstShow && (surface = this.mDisplaySurface) != null) {
            linkStatusModel.show(surface, 1);
            this.mIsFirstShow = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideClusterProjection() {
        LogUtil.info(TAG, " ---hideClusterProjection---");
        synchronized (this.mLock) {
            if (this.mClusterDisplayManager != null) {
                this.mClusterDisplayManager.hide();
            }
            if (this.mHasShown) {
                this.mLinkStatusModel.stopUi(1);
            }
            this.mHasShown = false;
            LogUtil.info(TAG, " ---hideClusterProjection--- mHasShown==" + this.mHasShown);
        }
    }

    private void setDisplayParams() {
        LinearLayout linearLayout = this.mDisplayArea;
        if (linearLayout == null) {
            LogUtil.info(TAG, "mDisplayArea == null ");
            return;
        }
        if (this.mSurfaceProjection == null) {
            LogUtil.info(TAG, "mSurfaceProjection == null ");
            return;
        }
        FrameLayout.LayoutParams displayParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        LinearLayout.LayoutParams surfaceParams = (LinearLayout.LayoutParams) this.mSurfaceProjection.getLayoutParams();
        if (displayParams == null) {
            LogUtil.info(TAG, "displayParams == null ");
            return;
        }
        if (surfaceParams == null) {
            LogUtil.info(TAG, "surfaceParams == null ");
            return;
        }
        displayParams.width = this.mAapDisplayAreaWidth;
        displayParams.height = this.mAapDisplayAreaHeight;
        surfaceParams.width = this.mAapDisplayAreaWidth;
        surfaceParams.height = this.mAapDisplayAreaHeight;
        displayParams.setMargins(this.mAapDisplayAreaMarginLeft, this.mAapDisplayAreaMarginTop, 0, 0);
        this.mDisplayArea.setLayoutParams(displayParams);
        this.mSurfaceProjection.setLayoutParams(surfaceParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNativeNaviState() {
        LogUtil.info(TAG, "mIsPreNativeNaviActive:" + this.mIsPreNativeNaviActive + " mIsNativeNaviActive:" + this.mIsNativeNaviActive);
        if (this.mIsPreNativeNaviActive != this.mIsNativeNaviActive) {
            post(new Runnable() { // from class: com.ts.carplay.app.service.proxy.navigation.-$$Lambda$NavigationProxy$g1pCKD--Uap6qB0VhE_Gxc4Fp3A
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$checkNativeNaviState$2$NavigationProxy();
                }
            });
        }
        this.mIsPreNativeNaviActive = this.mIsNativeNaviActive;
    }

    public /* synthetic */ void lambda$checkNativeNaviState$2$NavigationProxy() {
        this.mCarPlayRemoteManager.setNativeAppState(1, this.mIsNativeNaviActive, -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCpNaviWithLinkStatus(int linkStatus) {
        if ((linkStatus == 5 || linkStatus == 3 || linkStatus == 4) && this.mBydAutoNaviDevice.getFocusMapType() == 2) {
            this.mIsNeedEndNaviByRouteInfo = false;
            this.mIsCarPlayNavi = false;
            sendNaviPresentationState(false);
            LogUtil.debug(TAG, "handleCpNaviWithLinkStatus ---- when CPNavi cluster inuse and current navifocus is in CP ---sendNaviPresentationState()---- notify TBT close");
            if (this.mSupportHud) {
                BYDAutoWhudDevice.getInstance(this.mContext).setNaviState(4);
                LogUtil.debug(TAG, "handleCpNaviWithLinkStatus ---- when CPNavi cluster inuse and current navifocus is in CP ---setHudNaviState()---- notify HUD CarPlayNavi end");
                this.mBydAutoNaviDevice.sendNaviState(2, 2);
                this.mBydAutoNaviDevice.setOverseasMapSupplier(0);
                LogUtil.debug(TAG, "handleCpNaviWithLinkStatus ---- when CPNavi cluster inuse and current navifocus is in CP ---setNaviState()---- notify CarPlayNavi end");
            } else {
                LogUtil.debug(TAG, "handleCpNaviWithLinkStatus ---- when CPNavi cluster inuse and current navifocus is in CP ---setHudNaviState()---- notify HUD CarPlayNavi end");
                this.mBydAutoNaviDevice.sendNaviStateOnlyCPAA(2, 2);
                LogUtil.debug(TAG, "handleCpNaviWithLinkStatus ---- when CPNavi cluster inuse and current navifocus is in CP ---sendNaviStateOnlyCPAA()---- notify CarPlayNavi end");
            }
            this.mBydAutoNaviDevice.abandonMapFocus(2);
            this.mSuggestUrl = -1;
            LogUtil.debug(TAG, "handleCpNaviWithLinkStatus ----- abandonMapFocus");
        }
    }

    private String convertDisplayValue(String displayValue) {
        LogUtil.debug(TAG, "convertDisplayValue: displayValue==" + displayValue);
        String convertDisplayValue = "";
        if (!TextUtils.isEmpty(displayValue)) {
            convertDisplayValue = displayValue.replace(",", ".").trim().toString();
        }
        LogUtil.debug(TAG, "convertDisplayValue: convertDisplayValue==" + convertDisplayValue);
        return convertDisplayValue;
    }

    private boolean isSupportHud() {
        String modelsCode = SystemProperties.get(PERSIST_SYS_AUTO_TYPE);
        LogUtil.info(TAG, "getModelsCode :" + modelsCode);
        return TextUtils.isEmpty(modelsCode) || modelsCode.equals(TANG_VALUE) || modelsCode.equals(UKE_LEFT_VALUE) || modelsCode.equals(UEK_RIGHT_VALUE) || modelsCode.equals(MR_RIGHT_VALUE) || modelsCode.equals(PA_RIGHT_VALUE);
    }
}
