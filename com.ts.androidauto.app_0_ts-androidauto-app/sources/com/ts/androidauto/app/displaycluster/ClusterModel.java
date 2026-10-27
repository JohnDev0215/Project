package com.ts.androidauto.app.displaycluster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.whud.BYDAutoWhudDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.ts.androidauto.app.R;
import com.ts.androidauto.app.common.IModel;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.common.UiConfigLoader;
import com.ts.androidauto.app.common.VehicleInfoLoader;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener;
import com.ts.car.core.ServiceProvider;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.INavigationDataListener;
import ts.car.service.androidauto.data.UiConfigData;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
public class ClusterModel extends IModel {
    private static final int AAP_DEFAULT_HEIGHT = 720;
    private static final int AAP_DEFAULT_WIDTH = 1920;
    private static final int AAP_DISPLAY_MODE_480P = 0;
    private static final int AAP_DISPLAY_MODE_720P = 1;
    private static final int AAP_DISPLAY_MODE_HIDE_CLUSTER_PROJECTION = 8;
    private static final int AAP_DISPLAY_MODE_NATIVE = 2;
    private static final int AAP_DISPLAY_MODE_PROJECTED = 3;
    private static final int AAP_DISPLAY_MODE_SHOW_CLUSTER_PROJECTION = 7;
    private static final int AAP_LINK_STATUS_CHANGE = 6;
    private static final int AAP_NAVI_FOCUS_CHANGE = 5;
    private static final int AAP_UPDATE_UICONFIG_REPLY_OK = 4;
    private static final int MAP_STATE_FULL_CLOSE = 3;
    private static final int MAP_STATE_FULL_OPEN = 6;
    public static final int MAP_STATE_NAVI_CLOSE = 1;
    public static final int MAP_STATE_SIMPLE_CLOSE = 4;
    public static final int MAP_STATE_SIMPLE_OPEN = 7;
    private static final int MAP_STATE_SMALL_CLOSE = 2;
    private static final int MAP_STATE_SMALL_OPEN = 5;
    private static final int MAP_TYPE_AUTO = 3;
    private static final String MC_LEFT_VALUE = "180";
    private static final String MR_RIGHT_VALUE = "123";
    private static final int NAVI_STATE_END = 2;
    private static final int NAVI_STATE_START = 1;
    public static final int NAV_CLOSE = 1;
    public static final int NAV_FULL = 4;
    public static final int NAV_SIMPLE = 2;
    public static final int NAV_SMALL = 3;
    public static final int OVERSEAS_MAP_SUPPLIER_TYPE_AUTO = 10;
    public static final int OVERSEAS_MAP_SUPPLIER_TYPE_INVALID = 0;
    private static final String PA_RIGHT_VALUE = "143";
    private static final String PERSIST_SYS_AUTO_TYPE = "persist.sys.AutoType";
    private static final String RECEIVER_FINISH_ACTION = "com.ts.androidauto.app.display.aapclusteractivity.RECEIVER_FINISH_ACTION";
    private static final String TAG = "AAP" + ClusterModel.class.getSimpleName();
    private static final String TANG_VALUE = "196";
    private static final String UEK_RIGHT_VALUE = "208";
    private static final int UISTATE_FULL_MAP_TRANS = 1;
    private static final int UISTATE_SMALL_MAP_TRANS = 3;
    private static final String UKE_LEFT_VALUE = "182";
    private static final String VEHICLE_MODEL_TANG = "BYD 5.0F_01";
    private static final String VEHICLE_MODEL_UKE = "BYD 5.0F_02";
    private static final int WHUD_NAVI_STATE_END = 4;
    private static final int WHUD_NAVI_STATE_START = 2;
    private boolean isAaNaviInUse;
    private boolean isAlreadyShow;
    private int mAapDisplayAreaHeight;
    private int mAapDisplayAreaMarginLeft;
    private int mAapDisplayAreaMarginTop;
    private int mAapDisplayAreaWidth;
    private int mAapDisplayMode;
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private IAndroidAutoRemoteUiManagerListener mAndroidAutoRemoteUiManagerListener;
    private final BroadcastReceiver mBroadcastReceiver;
    private ClusterDisplayManager mClusterDisplayManager;
    private Context mContext;
    private int mCurrentClusterNavDisplayMode;
    private int mCurrentClusterNavFocus;
    private String mCurrentVehicleModel;
    private LinearLayout mDisplayArea;
    private Surface mDisplaySurface;
    private Handler mHandler;
    private HorizontalScrollView mHorizontalScrollView;
    private AbsBYDAutoNaviListener mInstrumentListener;
    private Object mLock;
    private INavigationDataListener mNavigationDataListener;
    private int mPreClusterNavDisplayMode;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mScrollHorizontalLength;
    private int mScrollVerticalLength;
    private ScrollView mScrollView;
    private final IServiceConnectionListener mServiceConnectionListener;
    private boolean mSupportHud;
    private final SurfaceHolder.Callback mSurfaceHolderCallback;
    private SurfaceView mSurfaceProjection;
    private UiConfigLoader mUiConfigLoader;
    private VehicleData mVehicleInfo;

    public ClusterModel(Looper looper) {
        super(looper);
        this.mAndroidAutoRemoteUiManagerListener = new AndroidAutoRemoteUiManagerListener();
        this.mAndroidAutoAppManager = null;
        this.mLock = new Object();
        this.mAapDisplayMode = 0;
        this.mScreenWidth = -1;
        this.mScreenHeight = -1;
        this.mScrollVerticalLength = 0;
        this.mScrollHorizontalLength = 0;
        this.mAapDisplayAreaWidth = 1920;
        this.mAapDisplayAreaHeight = 720;
        this.mAapDisplayAreaMarginLeft = 0;
        this.mAapDisplayAreaMarginTop = 0;
        this.mVehicleInfo = null;
        this.isAlreadyShow = false;
        this.mDisplayArea = null;
        this.mDisplaySurface = null;
        this.mHandler = null;
        this.isAaNaviInUse = false;
        this.mPreClusterNavDisplayMode = 1;
        this.mSupportHud = true;
        this.mSurfaceHolderCallback = new SurfaceHolder.Callback() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.6
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder holder) {
                LogUtil.debug(ClusterModel.TAG, "mSurfaceHolderCallback---surfaceCreated");
                ClusterModel.this.mDisplaySurface = holder.getSurface();
                synchronized (ClusterModel.this.mLock) {
                    ClusterModel.this.mScreenWidth = -1;
                    ClusterModel.this.mScreenHeight = -1;
                    ClusterModel.this.isAlreadyShow = false;
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                LogUtil.debug(ClusterModel.TAG, "mSurfaceHolderCallback---surfaceChanged width: " + width + ", height: " + height);
                if (ClusterModel.this.mDisplaySurface == null) {
                    LogUtil.debug(ClusterModel.TAG, "surfaceChanged create Surface");
                    ClusterModel.this.mDisplaySurface = holder.getSurface();
                }
                synchronized (ClusterModel.this.mLock) {
                    ClusterModel.this.mScreenWidth = width;
                    ClusterModel.this.mScreenHeight = height;
                    if (ClusterModel.this.mAndroidAutoRemoteUiManager != null && !ClusterModel.this.isAlreadyShow && ClusterModel.this.mDisplaySurface != null && ClusterModel.this.mScreenWidth > 0 && ClusterModel.this.mScreenHeight > 0 && ClusterModel.this.mCurrentClusterNavFocus == 3) {
                        ClusterModel.this.isAlreadyShow = ClusterModel.this.mAndroidAutoRemoteUiManager.showCluster(ClusterModel.this.mDisplaySurface, ClusterModel.this.mScreenWidth, ClusterModel.this.mScreenHeight);
                        LogUtil.debug(ClusterModel.TAG, " ---surfaceChanged--- isAlreadyShow==" + ClusterModel.this.isAlreadyShow);
                    }
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder holder) {
                LogUtil.debug(ClusterModel.TAG, "mSurfaceHolderCallback---surfaceDestroyed ");
                ClusterModel.this.mDisplaySurface = null;
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.debug(ClusterModel.TAG, "onReceive --- intent: " + intent);
                if (ClusterModel.RECEIVER_FINISH_ACTION.equals(intent.getAction())) {
                    LogUtil.debug(ClusterModel.TAG, "onReceive --- finish cluster projection");
                    ClusterModel.this.hideClusterProjection();
                }
            }
        };
        this.mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.8
            public void onServiceConnected(IPlatformService service) {
                LogUtil.debug(ClusterModel.TAG, "onServiceConnected.");
                if (ClusterModel.this.mAndroidAutoAppManager != null) {
                    try {
                        ClusterModel.this.mAndroidAutoAppManager.registerNavigationDataListener(ClusterModel.this.mNavigationDataListener);
                        return;
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                LogUtil.debug(ClusterModel.TAG, "mAndroidAutoAppManager is null.");
            }

            public void onServiceDisconnected(IPlatformService service) {
                LogUtil.debug(ClusterModel.TAG, "onServiceDisconnected.");
            }
        };
        this.mNavigationDataListener = new INavigationDataListener() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.9
            public void onNavigationStatusChanged(int status) {
            }
        };
        this.mInstrumentListener = new AbsBYDAutoNaviListener() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.10
            @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
            public void onInsUiChanged(int uiState) {
                boolean isClusterProjection = true;
                if (uiState != 3 && uiState != 1) {
                    isClusterProjection = false;
                }
                LogUtil.debug(ClusterModel.TAG, "onInsUiChanged uiState：" + uiState + " isClusterProjection: " + isClusterProjection + " isAaNaviInUse: " + ClusterModel.this.isAaNaviInUse + " mCurrentClusterNavFocus == " + ClusterModel.this.mCurrentClusterNavFocus);
                if (!isClusterProjection || ClusterModel.this.mCurrentClusterNavFocus != 3) {
                    if (ClusterModel.this.mHandler != null && ClusterModel.this.mCurrentClusterNavFocus != 3) {
                        LogUtil.debug(ClusterModel.TAG, "onInsUiChanged---mHandler sendMessage AAP_DISPLAY_MODE_HIDE_CLUSTER_PROJECTION");
                        Message msg = ClusterModel.this.mHandler.obtainMessage(8);
                        ClusterModel.this.mHandler.sendMessage(msg);
                        return;
                    }
                    return;
                }
                if (ClusterModel.this.mHandler != null) {
                    LogUtil.debug(ClusterModel.TAG, "onInsUiChanged---mHandler sendMessage AAP_DISPLAY_MODE_SHOW_CLUSTER_PROJECTION");
                    Message msg2 = ClusterModel.this.mHandler.obtainMessage(7);
                    ClusterModel.this.mHandler.sendMessage(msg2);
                }
            }

            @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
            public void onMapFocusChanged(int mapType) {
                LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged, mapType:" + mapType);
                ClusterModel.this.mCurrentClusterNavFocus = mapType;
                if (3 == mapType && ClusterModel.this.isAaNaviInUse) {
                    if (!ClusterModel.this.mSupportHud) {
                        LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged---setHudNaviState()----tell HUD AndroidAutoNavi start");
                        BYDAutoWhudDevice.getInstance(ClusterModel.this.mContext).setNaviState(4);
                        BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviStateOnlyCPAA(3, 1);
                        LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged---sendNaviStateOnlyCPAA()----tell TBT AndroidAutoNavi start");
                    } else {
                        BYDAutoWhudDevice.getInstance(ClusterModel.this.mContext).setNaviState(2);
                        LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged---setHudNaviState()----tell HUD AndroidAutoNavi start");
                        BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviState(3, 1);
                        BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).setOverseasMapSupplier(10);
                        LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged---sendNaviState()----tell TBT AndroidAutoNavi start");
                    }
                    LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged---sendNaviPresentationState()");
                    ClusterModel.this.sendNaviPresentationState(true);
                    return;
                }
                LogUtil.debug(ClusterModel.TAG, "onMapFocusChanged---sendNaviPresentationState()");
                ClusterModel.this.sendNaviPresentationState(false);
            }

            @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
            public void onINSNavDisplayMode(int value) {
                LogUtil.debug(ClusterModel.TAG, "onINSNavDisplayMode, value:" + value);
                ClusterModel.this.mCurrentClusterNavDisplayMode = value;
                if (1 == value || 2 == value) {
                    LogUtil.debug(ClusterModel.TAG, "onINSNavDisplayMode---sendNaviPresentationState()");
                    ClusterModel.this.sendNaviPresentationState(false);
                    if (ClusterModel.this.mCurrentClusterNavFocus != 3 && ClusterModel.this.mHandler != null) {
                        LogUtil.debug(ClusterModel.TAG, "onINSNavDisplayMode---mHandler sendMessage AAP_DISPLAY_MODE_HIDE_CLUSTER_PROJECTION");
                        Message msg = ClusterModel.this.mHandler.obtainMessage(8);
                        ClusterModel.this.mHandler.sendMessage(msg);
                        return;
                    }
                    return;
                }
                if (3 == value || 4 == value) {
                    LogUtil.debug(ClusterModel.TAG, "onINSNavDisplayMode---setClusterUiConfig()");
                    ClusterModel.this.setClusterUiConfig(value);
                    if (ClusterModel.this.mCurrentClusterNavFocus == 3) {
                        LogUtil.debug(ClusterModel.TAG, "onINSNavDisplayMode---sendNaviPresentationState()");
                        ClusterModel.this.sendNaviPresentationState(true);
                    }
                }
            }
        };
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        this.mAndroidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        this.mUiConfigLoader = UiConfigLoader.getInstance();
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.addAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        BydAutoNaviDevice.getInstance(this.mContext).registerListener(this.mInstrumentListener);
        this.mCurrentClusterNavDisplayMode = BydAutoNaviDevice.getInstance(this.mContext).getInsNavDisplayMode();
        this.mCurrentClusterNavFocus = BydAutoNaviDevice.getInstance(this.mContext).getFocusMapType();
        IntentFilter onFinishIntentFilter = new IntentFilter(RECEIVER_FINISH_ACTION);
        this.mContext.registerReceiver(this.mBroadcastReceiver, onFinishIntentFilter);
        this.mSupportHud = isSupportHud();
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 2:
                        if (ClusterModel.this.mCurrentClusterNavFocus == 3) {
                            LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage AAP_DISPLAY_MODE_NATIVE");
                            BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviPresentationState(3, 1);
                            BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).abandonMapFocus(3);
                            LogUtil.debug(ClusterModel.TAG, "receiveMessage MODE_NATIVE---abandonMapFocus()");
                        }
                        break;
                    case 3:
                        LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage AAP_DISPLAY_MODE_PROJECTED");
                        if (ClusterModel.this.isAaNaviInUse) {
                            if (ClusterModel.this.mCurrentClusterNavFocus == 3) {
                                LogUtil.debug(ClusterModel.TAG, "receiveMessage MODE_PROJECTED sendNaviPresentationState()");
                                ClusterModel.this.sendNaviPresentationState(true);
                            } else {
                                BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).requestMapFocus(3);
                                LogUtil.debug(ClusterModel.TAG, "receiveMessage MODE_PROJECTED requestMapFocus()");
                            }
                        }
                        break;
                    case 4:
                        LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage AAP_UPDATE_UICONFIG_REPLY_OK");
                        if (ClusterModel.this.mCurrentVehicleModel != null && ClusterModel.this.mCurrentVehicleModel == ClusterModel.VEHICLE_MODEL_UKE) {
                            if (ClusterModel.this.mCurrentClusterNavDisplayMode == 3) {
                                ClusterModel clusterModel = ClusterModel.this;
                                clusterModel.updateDisplayParams(clusterModel.mVehicleInfo.mAapClusterVideoConfig1080pSmall);
                            } else {
                                ClusterModel clusterModel2 = ClusterModel.this;
                                clusterModel2.updateDisplayParams(clusterModel2.mVehicleInfo.mAapClusterVideoConfig1080pFull);
                            }
                            break;
                        }
                        break;
                    case 5:
                        LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage onNavigationStatusChanged");
                        ClusterModel.this.isAaNaviInUse = ((Boolean) msg.obj).booleanValue();
                        if (ClusterModel.this.isAaNaviInUse) {
                            if (ClusterModel.this.mCurrentClusterNavFocus == 3) {
                                if (!ClusterModel.this.mSupportHud) {
                                    LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged---setHudNaviState()----tell HUD AndroidAutoNavi start");
                                    BYDAutoWhudDevice.getInstance(ClusterModel.this.mContext).setNaviState(4);
                                    BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviStateOnlyCPAA(3, 1);
                                    LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged---sendNaviStateOnlyCPAA()----tell TBT AndroidAutoNavi start");
                                } else {
                                    BYDAutoWhudDevice.getInstance(ClusterModel.this.mContext).setNaviState(2);
                                    LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged---setHudNaviState()----tell HUD AndroidAutoNavi start");
                                    BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviState(3, 1);
                                    BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).setOverseasMapSupplier(10);
                                    LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged---sendNaviState()----tell TBT AndroidAutoNavi start");
                                }
                                LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged---sendNaviPresentationState()");
                                ClusterModel.this.sendNaviPresentationState(true);
                            } else {
                                BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).requestMapFocus(3);
                            }
                            LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged---requestMapFocus()");
                        } else if (BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).getFocusMapType() == 3) {
                            if (!ClusterModel.this.mSupportHud) {
                                LogUtil.debug(ClusterModel.TAG, "when AANavi end and current navifocus is in AA---setHudNaviState()----tell HUD AndroidAutoNavi end");
                                BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviStateOnlyCPAA(3, 2);
                            } else {
                                BYDAutoWhudDevice.getInstance(ClusterModel.this.mContext).setNaviState(4);
                                LogUtil.debug(ClusterModel.TAG, "when AANavi end and current navifocus is in AA---setHudNaviState()----tell HUD AndroidAutoNavi end");
                                BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviState(3, 2);
                                BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).setOverseasMapSupplier(0);
                            }
                        }
                        break;
                    case 6:
                        LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage AAP_LINK_STATUS_CHANGE");
                        int linkStatus = ((Integer) msg.obj).intValue();
                        if (linkStatus != 4) {
                            if (linkStatus == 5 || linkStatus == 2 || linkStatus == 1) {
                                ClusterModel.this.isAaNaviInUse = false;
                                if (BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).getFocusMapType() == 3) {
                                    if (!ClusterModel.this.mSupportHud) {
                                        LogUtil.debug(ClusterModel.TAG, "when AA disconnect or connect failed and current navifocus is in AA ---setHudNaviState()----tell HUD AndroidAutoNavi end");
                                        BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviStateOnlyCPAA(3, 2);
                                        LogUtil.debug(ClusterModel.TAG, "when AA disconnect or connect failed and current navifocus is in AA ---sendNaviStateOnlyCPAA()----tell TBT AndroidAutoNavi end");
                                    } else {
                                        BYDAutoWhudDevice.getInstance(ClusterModel.this.mContext).setNaviState(4);
                                        LogUtil.debug(ClusterModel.TAG, "when AA disconnect or connect failed and current navifocus is in AA ---setHudNaviState()----tell HUD AndroidAutoNavi end");
                                        BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviState(3, 2);
                                        BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).setOverseasMapSupplier(0);
                                        LogUtil.debug(ClusterModel.TAG, "when AA disconnect or connect failed and current navifocus is in AA ---sendNaviState()----tell TBT AndroidAutoNavi end");
                                    }
                                    BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).sendNaviPresentationState(3, 1);
                                    LogUtil.debug(ClusterModel.TAG, "when AA disconnect or connect failed and current navifocus is in AA ---sendNaviPresentationState() close");
                                    BydAutoNaviDevice.getInstance(ClusterModel.this.mContext).abandonMapFocus(3);
                                    LogUtil.debug(ClusterModel.TAG, "receiveMessage onNavigationStatusChanged-------abandonMapFocus()");
                                }
                                ClusterModel.this.hideClusterProjection();
                            }
                            break;
                        }
                        break;
                    case 7:
                        LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage AAP_DISPLAY_MODE_SHOW_CLUSTER_PROJECTION");
                        ClusterModel.this.showClusterProjection();
                        break;
                    case 8:
                        LogUtil.debug(ClusterModel.TAG, "mHandler receiveMessage AAP_DISPLAY_MODE_HIDE_CLUSTER_PROJECTION");
                        ClusterModel.this.hideClusterProjection();
                        break;
                }
            }
        };
        initDisplayParams();
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.layout_cluster_navi, (ViewGroup) null, false);
        this.mDisplayArea = linearLayout;
        SurfaceView surfaceView = (SurfaceView) linearLayout.findViewById(R.id.surface_cluster_projection);
        this.mSurfaceProjection = surfaceView;
        surfaceView.getHolder().addCallback(this.mSurfaceHolderCallback);
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) this.mDisplayArea.findViewById(R.id.horizontal_scrollView);
        this.mHorizontalScrollView = horizontalScrollView;
        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        ScrollView scrollView = (ScrollView) this.mDisplayArea.findViewById(R.id.scroll_view);
        this.mScrollView = scrollView;
        scrollView.setOnTouchListener(new View.OnTouchListener() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        setDisplayParams();
        this.mClusterDisplayManager = new ClusterDisplayManager(this.mContext, this.mDisplayArea, this.mCurrentVehicleModel);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void initDisplayParams() {
        VehicleData vehicleInfo = VehicleInfoLoader.getInstance().getVehicleInfo(this.mContext);
        this.mVehicleInfo = vehicleInfo;
        if (vehicleInfo != null) {
            this.mCurrentVehicleModel = vehicleInfo.mHeadUnitModel;
        }
        LogUtil.debug(TAG, "initDisplayParams CurrentVehicleModel = " + this.mCurrentVehicleModel);
        if (this.mVehicleInfo != null) {
            LogUtil.debug(TAG, "initDisplayParams vehicleInfo OK.");
            String str = this.mCurrentVehicleModel;
            byte b = -1;
            switch (str.hashCode()) {
                case -205498498:
                    if (str.equals(VEHICLE_MODEL_TANG)) {
                        b = 1;
                    }
                    break;
                case -205498497:
                    if (str.equals(VEHICLE_MODEL_UKE)) {
                        b = 0;
                    }
                    break;
            }
            if (b == 0) {
                if (this.mCurrentClusterNavDisplayMode == 3) {
                    this.mAapDisplayAreaWidth = this.mVehicleInfo.mAapClusterVideoConfig1080pSmall.mAapDisplayAreaWidth;
                    this.mAapDisplayAreaHeight = this.mVehicleInfo.mAapClusterVideoConfig1080pSmall.mAapDisplayAreaHeight;
                    this.mAapDisplayAreaMarginLeft = this.mVehicleInfo.mAapClusterVideoConfig1080pSmall.mAapDisplayAreaMarginLeft;
                    this.mAapDisplayAreaMarginTop = this.mVehicleInfo.mAapClusterVideoConfig1080pSmall.mAapDisplayAreaMarginTop;
                    return;
                }
                this.mAapDisplayAreaWidth = this.mVehicleInfo.mAapClusterVideoConfig1080pFull.mAapDisplayAreaWidth;
                this.mAapDisplayAreaHeight = this.mVehicleInfo.mAapClusterVideoConfig1080pFull.mAapDisplayAreaHeight;
                this.mAapDisplayAreaMarginLeft = this.mVehicleInfo.mAapClusterVideoConfig1080pFull.mAapDisplayAreaMarginLeft;
                this.mAapDisplayAreaMarginTop = this.mVehicleInfo.mAapClusterVideoConfig1080pFull.mAapDisplayAreaMarginTop;
                return;
            }
            this.mAapDisplayAreaWidth = this.mVehicleInfo.mAapClusterVideoConfig480p.mAapDisplayAreaWidth;
            this.mAapDisplayAreaHeight = this.mVehicleInfo.mAapClusterVideoConfig480p.mAapDisplayAreaHeight;
            this.mAapDisplayAreaMarginLeft = this.mVehicleInfo.mAapClusterVideoConfig480p.mAapDisplayAreaMarginLeft;
            this.mAapDisplayAreaMarginTop = this.mVehicleInfo.mAapClusterVideoConfig480p.mAapDisplayAreaMarginTop;
        }
    }

    private void setDisplayParams() {
        LogUtil.debug(TAG, "setDisplayParams enter.");
        LinearLayout linearLayout = this.mDisplayArea;
        if (linearLayout == null) {
            LogUtil.debug(TAG, "mDisplayArea == null ");
            return;
        }
        if (this.mSurfaceProjection == null) {
            LogUtil.debug(TAG, "mSurfaceProjection == null ");
            return;
        }
        FrameLayout.LayoutParams displayParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        FrameLayout.LayoutParams surfaceParams = (FrameLayout.LayoutParams) this.mSurfaceProjection.getLayoutParams();
        if (displayParams == null) {
            LogUtil.debug(TAG, "displayParams == null ");
            return;
        }
        if (surfaceParams == null) {
            LogUtil.debug(TAG, "surfaceParams == null ");
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
    public void updateDisplayParams(VehicleData.AapVideoConfig info) {
        FrameLayout.LayoutParams params;
        FrameLayout.LayoutParams displayParams;
        if (info != null) {
            LogUtil.debug(TAG, "updateDisplayParams---VehicleData.AapVideoConfig");
            this.mAapDisplayAreaWidth = info.mAapDisplayAreaWidth;
            this.mAapDisplayAreaHeight = info.mAapDisplayAreaHeight;
            this.mAapDisplayAreaMarginLeft = info.mAapDisplayAreaMarginLeft;
            this.mAapDisplayAreaMarginTop = info.mAapDisplayAreaMarginTop;
            this.mScrollHorizontalLength = info.mAapSurfaceViewScrollX;
            this.mScrollVerticalLength = info.mAapSurfaceViewScrollY;
            LinearLayout linearLayout = this.mDisplayArea;
            if (linearLayout != null && (displayParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams()) != null) {
                displayParams.width = this.mAapDisplayAreaWidth;
                displayParams.height = this.mAapDisplayAreaHeight;
                displayParams.setMargins(this.mAapDisplayAreaMarginLeft, this.mAapDisplayAreaMarginTop, 0, 0);
                this.mDisplayArea.setLayoutParams(displayParams);
            }
            SurfaceView surfaceView = this.mSurfaceProjection;
            if (surfaceView != null && (params = (FrameLayout.LayoutParams) surfaceView.getLayoutParams()) != null) {
                params.width = info.mAapSurfaceViewWidth;
                params.height = info.mAapSurfaceViewHeight;
                this.mSurfaceProjection.setLayoutParams(params);
            }
            if (this.mScrollView != null) {
                LogUtil.debug(TAG, "Scroll up " + this.mScrollVerticalLength + " pixels");
                this.mScrollView.post(new Runnable() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ClusterModel.this.mScrollView.smoothScrollTo(0, ClusterModel.this.mScrollVerticalLength);
                    }
                });
            }
            if (this.mHorizontalScrollView != null) {
                LogUtil.debug(TAG, "Horizontal Scroll up " + this.mScrollHorizontalLength + " pixels");
                this.mHorizontalScrollView.post(new Runnable() { // from class: com.ts.androidauto.app.displaycluster.ClusterModel.5
                    @Override // java.lang.Runnable
                    public void run() {
                        ClusterModel.this.mHorizontalScrollView.smoothScrollTo(ClusterModel.this.mScrollHorizontalLength, 0);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showClusterProjection() {
        synchronized (this.mLock) {
            if (this.mAndroidAutoRemoteUiManager != null && !this.isAlreadyShow && this.mDisplaySurface != null && this.mScreenWidth > 0 && this.mScreenHeight > 0) {
                this.isAlreadyShow = this.mAndroidAutoRemoteUiManager.showCluster(this.mDisplaySurface, this.mScreenWidth, this.mScreenHeight);
                LogUtil.debug(TAG, " ---showClusterProjection--- isAlreadyShow==" + this.isAlreadyShow);
            }
            if (this.mClusterDisplayManager != null) {
                this.mClusterDisplayManager.show();
            }
            LogUtil.debug(TAG, " ---showClusterProjection--- isAlreadyShow==" + this.isAlreadyShow);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideClusterProjection() {
        synchronized (this.mLock) {
            if (this.isAlreadyShow && this.mAndroidAutoRemoteUiManager != null) {
                this.mAndroidAutoRemoteUiManager.hideCluster();
            }
            if (this.mClusterDisplayManager != null) {
                this.mClusterDisplayManager.hide();
            }
            this.isAlreadyShow = false;
            LogUtil.debug(TAG, " ---hideClusterProjection--- isAlreadyShow==" + this.isAlreadyShow);
        }
    }

    @Override // com.ts.androidauto.app.common.IModel
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        if (this.mDisplaySurface != null) {
            LogUtil.debug(TAG, "mDisplaySurface is release!");
            this.mDisplaySurface = null;
        }
        hideClusterProjection();
        if (this.mContext != null) {
            LogUtil.debug(TAG, "onDestroy unregisterReceiver Broadcast");
            this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        }
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.removeAndroidAutoRemoteUiManagerListener(this.mAndroidAutoRemoteUiManagerListener);
            this.mAndroidAutoRemoteUiManager = null;
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                iAndroidAutoAppManager.unregisterNavigationDataListener(this.mNavigationDataListener);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            this.mAndroidAutoAppManager.unregisterConnectionListener(this.mServiceConnectionListener);
            this.mAndroidAutoAppManager = null;
        }
        this.mCurrentClusterNavDisplayMode = 0;
        this.mCurrentClusterNavFocus = 0;
        Context context = this.mContext;
        if (context != null) {
            BydAutoNaviDevice.getInstance(context).unregisterListener(this.mInstrumentListener);
        }
        this.isAaNaviInUse = false;
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler = null;
        removeCallbacksAndMessages(null);
    }

    private boolean convertNavigationStatus(int naviState) {
        LogUtil.debug(TAG, "convertNavigationStatus: " + naviState);
        if (naviState == 1 || naviState == 3) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClusterUiConfig(int mode) {
        UiConfigData configData;
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager;
        if ((mode != 4 && mode != 3) || VEHICLE_MODEL_TANG.equals(this.mCurrentVehicleModel)) {
            LogUtil.info(TAG, "this mode do not need to update cluster uiconfig");
            return;
        }
        LogUtil.debug(TAG, "setClusterUiConfig() mode: " + mode);
        if (mode == 3) {
            configData = this.mUiConfigLoader.getAapClusterSmallUiConfig1080p();
        } else {
            configData = this.mUiConfigLoader.getAapClusterFullUiConfig1080p();
        }
        if (configData != null && (androidAutoRemoteUiManager = this.mAndroidAutoRemoteUiManager) != null && this.mPreClusterNavDisplayMode != mode) {
            androidAutoRemoteUiManager.setClusterUiConfig(configData);
            this.mPreClusterNavDisplayMode = mode;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendNaviPresentationState(boolean isOpen) {
        this.mCurrentClusterNavDisplayMode = BydAutoNaviDevice.getInstance(this.mContext).getInsNavDisplayMode();
        this.mCurrentClusterNavFocus = BydAutoNaviDevice.getInstance(this.mContext).getFocusMapType();
        LogUtil.debug(TAG, "sendNaviPresentationState() isOpen == " + isOpen + " mCurrentClusterNavDisplayMode == " + this.mCurrentClusterNavDisplayMode + " mCurrentClusterNavFocus == " + this.mCurrentClusterNavFocus);
        if (isOpen) {
            int i = this.mCurrentClusterNavDisplayMode;
            if (i == 2) {
                BydAutoNaviDevice.getInstance(this.mContext).sendNaviPresentationState(3, 7);
                LogUtil.debug(TAG, "sendNaviPresentationState() SIMPLE_OPEN start");
                return;
            } else if (i == 3) {
                setClusterUiConfig(3);
                BydAutoNaviDevice.getInstance(this.mContext).sendNaviPresentationState(3, 5);
                LogUtil.debug(TAG, "sendNaviPresentationState() AANaviClusterproject start NAV_SMALL");
                return;
            } else {
                if (i == 4) {
                    setClusterUiConfig(4);
                    BydAutoNaviDevice.getInstance(this.mContext).sendNaviPresentationState(3, 6);
                    LogUtil.debug(TAG, "sendNaviPresentationState() AANaviClusterproject start NAV_FULL");
                    return;
                }
                return;
            }
        }
        if (this.mCurrentClusterNavFocus == 3) {
            int i2 = this.mCurrentClusterNavDisplayMode;
            if (i2 == 1) {
                BydAutoNaviDevice.getInstance(this.mContext).sendNaviPresentationState(3, 1);
                LogUtil.debug(TAG, "sendNaviPresentationState() NAV_CLOSE");
            } else if (i2 == 2) {
                if (this.isAaNaviInUse) {
                    BydAutoNaviDevice.getInstance(this.mContext).sendNaviPresentationState(3, 7);
                    LogUtil.debug(TAG, "sendNaviPresentationState() SIMPLE_OPEN");
                } else {
                    BydAutoNaviDevice.getInstance(this.mContext).sendNaviPresentationState(3, 4);
                    LogUtil.debug(TAG, "sendNaviPresentationState() SIMPLE_CLOSE");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AndroidAutoRemoteUiManagerListener implements IAndroidAutoRemoteUiManagerListener {
        private AndroidAutoRemoteUiManagerListener() {
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onServiceStateChanged(boolean connected) {
            LogUtil.info(ClusterModel.TAG, "onServiceStateChanged connected=" + connected);
            if (connected) {
                ClusterModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.displaycluster.-$$Lambda$ClusterModel$AndroidAutoRemoteUiManagerListener$F60VWuMqh8LyWV2_KTIedujL63Y
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onServiceStateChanged$0$ClusterModel$AndroidAutoRemoteUiManagerListener();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onServiceStateChanged$0$ClusterModel$AndroidAutoRemoteUiManagerListener() {
            ClusterModel clusterModel = ClusterModel.this;
            clusterModel.mCurrentClusterNavDisplayMode = BydAutoNaviDevice.getInstance(clusterModel.mContext).getInsNavDisplayMode();
            if (ClusterModel.this.mCurrentClusterNavDisplayMode == 4) {
                LogUtil.info(ClusterModel.TAG, "init mCurrentClusterNavDisplayMode is FULL");
                ClusterModel clusterModel2 = ClusterModel.this;
                clusterModel2.setClusterUiConfig(clusterModel2.mCurrentClusterNavDisplayMode);
            } else if (ClusterModel.this.mCurrentClusterNavDisplayMode == 3) {
                LogUtil.info(ClusterModel.TAG, "init mCurrentClusterNavDisplayMode is SMALL");
                ClusterModel clusterModel3 = ClusterModel.this;
                clusterModel3.setClusterUiConfig(clusterModel3.mCurrentClusterNavDisplayMode);
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onRequestClusterVideoFocus(final int requestType) {
            LogUtil.debug(ClusterModel.TAG, "onRequestClusterVideoFocus requestType: " + requestType);
            ClusterModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.displaycluster.-$$Lambda$ClusterModel$AndroidAutoRemoteUiManagerListener$jNnOC3jWRxMiiyKt6jO3upsKxR8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRequestClusterVideoFocus$1$ClusterModel$AndroidAutoRemoteUiManagerListener(requestType);
                }
            });
        }

        public /* synthetic */ void lambda$onRequestClusterVideoFocus$1$ClusterModel$AndroidAutoRemoteUiManagerListener(int requestType) {
            if (requestType == 0) {
                LogUtil.debug(ClusterModel.TAG, "onRequestClusterVideoFocus finish the Cluster");
                if (ClusterModel.this.mHandler != null) {
                    LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage VIDEOFOCUS_REQUEST_NATIVE");
                    Message msg = ClusterModel.this.mHandler.obtainMessage(2);
                    ClusterModel.this.mHandler.sendMessage(msg);
                    return;
                }
                return;
            }
            if (requestType == 4) {
                if (ClusterModel.this.mHandler != null) {
                    LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage VIDEOFOCUS_REQUEST_PROJECTED");
                    Message msg2 = ClusterModel.this.mHandler.obtainMessage(3);
                    ClusterModel.this.mHandler.sendMessage(msg2);
                    return;
                }
                return;
            }
            if (requestType == 1) {
                ClusterModel.this.mAapDisplayMode = 0;
                if (ClusterModel.this.mVehicleInfo != null && ClusterModel.this.mHandler != null) {
                    LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage 480P");
                    Message msg3 = ClusterModel.this.mHandler.obtainMessage(0, ClusterModel.this.mVehicleInfo.mAapClusterVideoConfig480p);
                    ClusterModel.this.mHandler.sendMessage(msg3);
                    return;
                }
                return;
            }
            if (requestType == 2) {
                ClusterModel.this.mAapDisplayMode = 1;
                if (ClusterModel.this.mVehicleInfo != null && ClusterModel.this.mHandler != null) {
                    LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage 720P");
                    Message msg4 = ClusterModel.this.mHandler.obtainMessage(1, ClusterModel.this.mVehicleInfo.mAapClusterVideoConfig720p);
                    ClusterModel.this.mHandler.sendMessage(msg4);
                }
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onClusterVideoUpdateUiConfigReply(final boolean replyOk) {
            LogUtil.debug(ClusterModel.TAG, "onClusterVideoUpdateUiConfigReply replyOk: " + replyOk);
            ClusterModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.displaycluster.-$$Lambda$ClusterModel$AndroidAutoRemoteUiManagerListener$noEsgN_wlgd_GmexVFi90NLikqY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onClusterVideoUpdateUiConfigReply$2$ClusterModel$AndroidAutoRemoteUiManagerListener(replyOk);
                }
            });
        }

        public /* synthetic */ void lambda$onClusterVideoUpdateUiConfigReply$2$ClusterModel$AndroidAutoRemoteUiManagerListener(boolean replyOk) {
            if (replyOk && ClusterModel.this.mHandler != null) {
                LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage onClusterVideoUpdateUiConfigReply");
                Message msg = ClusterModel.this.mHandler.obtainMessage(4);
                ClusterModel.this.mHandler.sendMessage(msg);
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onStatusChanged(final int status) {
            LogUtil.debug(ClusterModel.TAG, "onStatusChanged status: " + status);
            ClusterModel.this.post(new Runnable() { // from class: com.ts.androidauto.app.displaycluster.-$$Lambda$ClusterModel$AndroidAutoRemoteUiManagerListener$3fn7XQPsaRnCPrcPMB5xBCF_iyU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStatusChanged$3$ClusterModel$AndroidAutoRemoteUiManagerListener(status);
                }
            });
        }

        public /* synthetic */ void lambda$onStatusChanged$3$ClusterModel$AndroidAutoRemoteUiManagerListener(int status) {
            if (ClusterModel.this.mHandler != null) {
                LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage AAP_LINK_STATUS_CHANGE");
                Message msg = ClusterModel.this.mHandler.obtainMessage(6, Integer.valueOf(status));
                ClusterModel.this.mHandler.sendMessage(msg);
            }
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onNavigationFocusChanged(boolean useNativeNavigation) {
            LogUtil.debug(ClusterModel.TAG, "onNavigationFocusChanged useNativeNavigation:" + useNativeNavigation);
            boolean isAaNaviInUse = useNativeNavigation ^ true;
            LogUtil.debug(ClusterModel.TAG, "onNavigationStatusChanged isAaNaviInUse: " + isAaNaviInUse);
            if (ClusterModel.this.mHandler != null) {
                LogUtil.debug(ClusterModel.TAG, "mHandler sendMessage onNavigationStatusChanged");
                Message msg = ClusterModel.this.mHandler.obtainMessage(5, Boolean.valueOf(isAaNaviInUse));
                ClusterModel.this.mHandler.sendMessage(msg);
            }
        }
    }

    private boolean isSupportHud() {
        String modelsCode = SystemProperties.get(PERSIST_SYS_AUTO_TYPE);
        LogUtil.info(TAG, "getModelsCode :" + modelsCode);
        return TextUtils.isEmpty(modelsCode) || modelsCode.equals(TANG_VALUE) || modelsCode.equals(UKE_LEFT_VALUE) || modelsCode.equals(UEK_RIGHT_VALUE) || modelsCode.equals(MR_RIGHT_VALUE) || modelsCode.equals(PA_RIGHT_VALUE);
    }
}
