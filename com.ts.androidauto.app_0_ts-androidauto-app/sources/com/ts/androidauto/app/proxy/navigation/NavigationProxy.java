package com.ts.androidauto.app.proxy.navigation;

import android.content.Context;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.byd.bydnaviapi.AbsBYDAutoNaviListener;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.ts.androidauto.app.common.IProxy;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.car.core.ServiceProvider;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.INavigationDataListener;
import ts.car.service.androidauto.data.NavigationData;
import ts.car.service.androidauto.data.NavigationDestDistanceData;
import ts.car.service.androidauto.data.NavigationPositionData;
import ts.car.service.androidauto.data.NavigationStateData;
import ts.car.service.androidauto.data.NavigationStepData;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationProxy extends IProxy {
    private static final String CONVERT_DISTANCE_FEET_UNIT = "ft";
    private static final String CONVERT_DISTANCE_KILOMETER_UNIT = "km";
    private static final String CONVERT_DISTANCE_METER_UNIT = "m";
    private static final String CONVERT_DISTANCE_MILE_UNIT = "mi";
    private static final String CONVERT_DISTANCE_YARDS_UNIT = "yards";
    private static final int DISTANCE_FEET_UNIT = 6;
    private static final int DISTANCE_KILOMETERS_UNIT = 3;
    private static final int DISTANCE_KILOMETER_UNIT = 2;
    private static final int DISTANCE_METER_UNIT = 1;
    private static final int DISTANCE_MILES_UNIT = 5;
    private static final int DISTANCE_MILE_UNIT = 4;
    private static final int DISTANCE_UNKNOWN_UNIT = 0;
    private static final int DISTANCE_YARDS_UNIT = 7;
    private static final int MAP_TYPE_AUTO = 3;
    private static final String MC_LEFT_VALUE = "180";
    private static final String MR_RIGHT_VALUE = "123";
    private static final String PA_RIGHT_VALUE = "143";
    private static final String PERSIST_SYS_AUTO_TYPE = "persist.sys.AutoType";
    private static final String TANG_VALUE = "196";
    private static final String UEK_RIGHT_VALUE = "208";
    private static final String UKE_LEFT_VALUE = "182";
    private static final int UNIT_1000 = 1000;
    private static final int UNIT_24 = 24;
    private static final int UNIT_60 = 60;
    private AndroidAutoRemoteUiManager androidAutoRemoteUiManager;
    IAndroidAutoRemoteUiManagerListener iAndroidAutoRemoteUiManagerListener;
    private boolean isAaNaviInUse;
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private Context mContext;
    private int mCurrentClusterNavFocus;
    private List<GuidanceLineMapBean> mCurrentGuidanceLineMapIds;
    private String mDistanceToDestination;
    private String mDistanceToDestinationUnit;
    private String mDistanceToNextPoint;
    private String mDistanceToNextPointUnit;
    private Handler mHandler;
    private AbsBYDAutoNaviListener mInstrumentListener;
    private int mManeuverType;
    private INavigationDataListener mNavigationDataListener;
    private NavigationInfoForClusterBean mNavigationInfoForClusterBean;
    private int mRoundaboutExitAngle;
    private final IServiceConnectionListener mServiceConnectionListener;
    private boolean mSupportHud;
    private long mTimeToArrivalSeconds;
    private static final String TAG = "AAP" + NavigationProxy.class.getSimpleName();
    private static final String CONVERT_DISTANCE_UNKNOWN_UNIT = null;

    public NavigationProxy(Looper looper) {
        super(looper);
        this.mAndroidAutoAppManager = null;
        this.mHandler = new Handler();
        this.isAaNaviInUse = false;
        this.mSupportHud = true;
        this.mServiceConnectionListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.app.proxy.navigation.NavigationProxy.1
            public void onServiceConnected(IPlatformService service) {
                LogUtil.debug(NavigationProxy.TAG, "onServiceConnected.");
                if (NavigationProxy.this.mAndroidAutoAppManager != null) {
                    try {
                        NavigationProxy.this.mAndroidAutoAppManager.registerNavigationDataListener(NavigationProxy.this.mNavigationDataListener);
                        return;
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                LogUtil.debug(NavigationProxy.TAG, "mAndroidAutoAppManager is null.");
            }

            public void onServiceDisconnected(IPlatformService service) {
                LogUtil.debug(NavigationProxy.TAG, "onServiceDisconnected.");
            }
        };
        this.iAndroidAutoRemoteUiManagerListener = new AndroidAutoRemoteUiManagerListener();
        this.mNavigationDataListener = new AnonymousClass2();
        this.mInstrumentListener = new AbsBYDAutoNaviListener() { // from class: com.ts.androidauto.app.proxy.navigation.NavigationProxy.3
            @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
            public void onInsUiChanged(int uiState) {
                LogUtil.debug(NavigationProxy.TAG, "onInsUiChanged, uiState:" + uiState);
            }

            @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
            public void onMapFocusChanged(int mapType) {
                LogUtil.debug(NavigationProxy.TAG, "onMapFocusChanged, mapType:" + mapType);
            }

            @Override // com.byd.bydnaviapi.AbsBYDAutoNaviListener
            public void onINSNavDisplayMode(int value) {
                LogUtil.debug(NavigationProxy.TAG, "onINSNavDisplayMode, value:" + value);
            }
        };
    }

    @Override // com.ts.androidauto.app.common.IProxy
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        this.androidAutoRemoteUiManager = androidAutoRemoteUiManager;
        if (androidAutoRemoteUiManager != null) {
            androidAutoRemoteUiManager.addAndroidAutoRemoteUiManagerListener(this.iAndroidAutoRemoteUiManagerListener);
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.mContext);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mServiceConnectionListener);
        }
        BydAutoNaviDevice.getInstance(this.mContext).registerListener(this.mInstrumentListener);
        this.mCurrentClusterNavFocus = BydAutoNaviDevice.getInstance(this.mContext).getFocusMapType();
        this.mSupportHud = isSupportHud();
    }

    @Override // com.ts.androidauto.app.common.IProxy
    public void uninit() {
        LogUtil.info(TAG, "uninit");
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
        this.isAaNaviInUse = false;
        this.mNavigationInfoForClusterBean = null;
        this.mCurrentClusterNavFocus = 0;
        BydAutoNaviDevice.getInstance(this.mContext).unregisterListener(this.mInstrumentListener);
        removeCallbacksAndMessages(null);
    }

    class AndroidAutoRemoteUiManagerListener implements IAndroidAutoRemoteUiManagerListener {
        AndroidAutoRemoteUiManagerListener() {
        }

        @Override // com.ts.androidauto.app.manager.IAndroidAutoRemoteUiManagerListener
        public void onNavigationFocusChanged(final boolean useNativeNavigation) {
            LogUtil.debug(NavigationProxy.TAG, "onNavigationFocusChanged useNativeNavigation:" + useNativeNavigation);
            NavigationProxy.this.isAaNaviInUse = useNativeNavigation ^ true;
            if (NavigationProxy.this.mHandler != null) {
                NavigationProxy.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.app.proxy.navigation.-$$Lambda$NavigationProxy$AndroidAutoRemoteUiManagerListener$CTuZRk2-Xt5Fza5S2uzKavZ2pow
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onNavigationFocusChanged$0$NavigationProxy$AndroidAutoRemoteUiManagerListener(useNativeNavigation);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onNavigationFocusChanged$0$NavigationProxy$AndroidAutoRemoteUiManagerListener(boolean useNativeNavigation) {
            if (!useNativeNavigation) {
                if (NavigationProxy.this.mNavigationInfoForClusterBean == null) {
                    LogUtil.info(NavigationProxy.TAG, "mNavigationInfoBean is null, do new.");
                    NavigationProxy.this.mNavigationInfoForClusterBean = new NavigationInfoForClusterBean(3);
                    return;
                }
                return;
            }
            NavigationProxy.this.mNavigationInfoForClusterBean = null;
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.app.proxy.navigation.NavigationProxy$2, reason: invalid class name */
    class AnonymousClass2 implements INavigationDataListener {
        AnonymousClass2() {
        }

        public void onNavigationStatusChanged(int status) {
        }

        public void onNextTurnChanged(NavigationData navigationData) {
        }

        public void onNextTurnDistanceChanged(int distanceMeters, int timeSeconds, int displayDistanceE3, int displayDistanceUnit) {
        }

        public void onNavigationStateChanged(NavigationStateData navigationStateData) {
            List<NavigationStepData> stepDataList;
            final NavigationStepData stepData;
            if (navigationStateData != null && (stepDataList = navigationStateData.getStepData()) != null && stepDataList.size() > 0 && (stepData = stepDataList.get(0)) != null && NavigationProxy.this.mHandler != null) {
                NavigationProxy.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.app.proxy.navigation.-$$Lambda$NavigationProxy$2$mqGA9bpAbhPj9dcU145MIs3jNto
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onNavigationStateChanged$0$NavigationProxy$2(stepData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onNavigationStateChanged$0$NavigationProxy$2(NavigationStepData stepData) {
            NavigationProxy.this.updateStepDataOnDim(stepData);
        }

        public void onNavigationCurrentPositionChanged(final NavigationPositionData navigationPositionData) {
            if (NavigationProxy.this.mHandler != null) {
                NavigationProxy.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.app.proxy.navigation.-$$Lambda$NavigationProxy$2$Tsf2QdhJR6QThvZMtijGMMKxvto
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onNavigationCurrentPositionChanged$1$NavigationProxy$2(navigationPositionData);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onNavigationCurrentPositionChanged$1$NavigationProxy$2(NavigationPositionData navigationPositionData) {
            NavigationProxy.this.updatePositionDataOnDim(navigationPositionData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStepDataOnDim(NavigationStepData stepData) {
        try {
            LogUtil.debug(TAG, "updateStepDataOnDim, stepData: " + stepData.toString());
            this.mManeuverType = stepData.getManeuverType();
            int roundaboutExitAngle = stepData.getRoundaboutExitAngle();
            this.mRoundaboutExitAngle = roundaboutExitAngle;
            int maneuverIconId = GuidanceLineUtils.convertManeuverIconId(this.mManeuverType, roundaboutExitAngle);
            if (this.mNavigationInfoForClusterBean != null) {
                LogUtil.debug(TAG, "updateTurnByTurnArrowAndName, maneuverIconId: " + maneuverIconId + " nextRoadName: " + stepData.getRoad());
                this.mNavigationInfoForClusterBean.setNextRoadName(stepData.getRoad());
                this.mNavigationInfoForClusterBean.setNextTbtIcon(maneuverIconId);
                updateNavigationInfoOnDim();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePositionDataOnDim(NavigationPositionData navigationPositionData) {
        try {
            LogUtil.debug(TAG, "updatePositionDataOnDim, navigationPositionData: " + navigationPositionData.toString());
            this.mDistanceToNextPoint = navigationPositionData.getDisplayValue();
            this.mDistanceToNextPointUnit = convertDistanceUnit(navigationPositionData.getDisplayUnits());
            if (this.mNavigationInfoForClusterBean != null) {
                this.mNavigationInfoForClusterBean.setNextRoadDist(this.mDistanceToNextPoint);
                this.mNavigationInfoForClusterBean.setNextDistUnit(this.mDistanceToNextPointUnit);
            }
            List<NavigationDestDistanceData> destDistanceDataList = navigationPositionData.getNavigationDestDistanceData();
            if (destDistanceDataList != null && destDistanceDataList.size() > 0) {
                NavigationDestDistanceData destDistanceData = destDistanceDataList.get(0);
                this.mDistanceToDestination = destDistanceData.getDisplayValue();
                this.mDistanceToDestinationUnit = convertDistanceUnit(destDistanceData.getDisplayUnits());
                long timeSeconds = destDistanceData.getTimeSeconds();
                this.mTimeToArrivalSeconds = timeSeconds;
                String arrivalTime = calculateArrivalTime(timeSeconds);
                String estimatedTime = calculateEstimatedArrivalTime(this.mTimeToArrivalSeconds);
                if (this.mNavigationInfoForClusterBean != null) {
                    this.mNavigationInfoForClusterBean.setDistToDest(this.mDistanceToDestination);
                    this.mNavigationInfoForClusterBean.setDistToDestUnit(this.mDistanceToDestinationUnit);
                    this.mNavigationInfoForClusterBean.setToDestTime(destDistanceData.getEstimatedTime());
                    this.mNavigationInfoForClusterBean.setToDestDays(convertSecondToDays(destDistanceData.getTimeSeconds()));
                    if (!TextUtils.isEmpty(estimatedTime)) {
                        this.mNavigationInfoForClusterBean.setToDestHours(Integer.parseInt(estimatedTime.split(":")[0].trim()));
                        this.mNavigationInfoForClusterBean.setToDestMinutes(Integer.parseInt(estimatedTime.split(":")[1].trim()));
                        this.mNavigationInfoForClusterBean.setToDestSeconds(Integer.parseInt(estimatedTime.split(":")[2].trim()));
                    }
                    if (!TextUtils.isEmpty(arrivalTime)) {
                        this.mNavigationInfoForClusterBean.setDistToDestTime(arrivalTime);
                    }
                }
            }
            updateNavigationInfoOnDim();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateNavigationInfoOnDim() {
        this.mCurrentClusterNavFocus = BydAutoNaviDevice.getInstance(this.mContext).getFocusMapType();
        LogUtil.debug(TAG, "updateNavigationInfoOnDim() isAaNaviInUse == " + this.isAaNaviInUse + " mCurrentClusterNavFocus == " + this.mCurrentClusterNavFocus);
        if (this.mCurrentClusterNavFocus == 3) {
            if (this.isAaNaviInUse && this.mNavigationInfoForClusterBean != null) {
                LogUtil.info(TAG, "naviInfoBean=" + this.mNavigationInfoForClusterBean.toString());
                if (this.mNavigationInfoForClusterBean.getNextTbtIcon() == 0) {
                    this.mNavigationInfoForClusterBean.setNextRoadDist(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    this.mNavigationInfoForClusterBean.setNextRoadName(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    this.mNavigationInfoForClusterBean.setNextDistUnit(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                    this.mNavigationInfoForClusterBean.setToDestTime(null);
                }
                if (this.mNavigationInfoForClusterBean.getNextRoadDist() == null) {
                    this.mNavigationInfoForClusterBean.setNextRoadDist(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                }
                if (this.mNavigationInfoForClusterBean.getNextRoadName() == null) {
                    this.mNavigationInfoForClusterBean.setNextRoadName(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                }
                if (this.mNavigationInfoForClusterBean.getNextDistUnit() == null) {
                    this.mNavigationInfoForClusterBean.setNextDistUnit(AndroidAutoConst.DEFAULT_SCREEN_NAME);
                }
                if (this.mSupportHud) {
                    BydAutoNaviDevice.getInstance(this.mContext).sendSimpleNaviInfo(this.mNavigationInfoForClusterBean.getMapType(), this.mNavigationInfoForClusterBean.getNextTbtIcon(), this.mNavigationInfoForClusterBean.getNextRoadName(), this.mNavigationInfoForClusterBean.getNextRoadDist(), this.mNavigationInfoForClusterBean.getNextDistUnit(), this.mNavigationInfoForClusterBean.getNextNextTbtIcon(), this.mNavigationInfoForClusterBean.getNextNextRoadDist(), this.mNavigationInfoForClusterBean.getNextNextDistUnit(), this.mNavigationInfoForClusterBean.getDistToDest(), this.mNavigationInfoForClusterBean.getDistToDestUnit(), null, null, null, null, this.mNavigationInfoForClusterBean.getDistToDestTime(), null, this.mNavigationInfoForClusterBean.getToDestDays(), this.mNavigationInfoForClusterBean.getToDestTime(), null, null, null);
                    return;
                } else {
                    BYDAutoInstrumentDevice.getInstance(this.mContext).sendTbtSimpleNaviInfo(this.mNavigationInfoForClusterBean.getMapType(), this.mNavigationInfoForClusterBean.getNextTbtIcon(), this.mNavigationInfoForClusterBean.getNextRoadName(), this.mNavigationInfoForClusterBean.getNextRoadDist(), this.mNavigationInfoForClusterBean.getNextDistUnit(), this.mNavigationInfoForClusterBean.getNextNextTbtIcon(), this.mNavigationInfoForClusterBean.getNextNextRoadDist(), this.mNavigationInfoForClusterBean.getNextNextDistUnit(), this.mNavigationInfoForClusterBean.getDistToDest(), this.mNavigationInfoForClusterBean.getDistToDestUnit(), this.mNavigationInfoForClusterBean.getDistToDestTime(), this.mNavigationInfoForClusterBean.getToDestDays(), this.mNavigationInfoForClusterBean.getToDestTime(), null, null, null);
                    return;
                }
            }
            return;
        }
        LogUtil.debug(TAG, "mCurrentClusterNavFocus is not in androidauto do not send updateNavigationInfoOnDim data ");
    }

    private String convertSecondToDays(long secondTime) {
        long days = secondTime / 86400;
        if (days > 0) {
            String convertSecondToDays = "+" + days + "D";
            return convertSecondToDays;
        }
        return null;
    }

    private String convertTimeStamp(String ampm) {
        SimpleDateFormat parse;
        LogUtil.debug(TAG, "convertTimeStamp: ampm==" + ampm);
        String convertTimeStamp = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            if (ampm.indexOf(CONVERT_DISTANCE_METER_UNIT) > -1 || ampm.indexOf("M") > -1) {
                parse = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            } else {
                parse = format;
            }
            convertTimeStamp = format.format(parse.parse(ampm));
        } catch (Exception e) {
            LogUtil.debug(TAG, "convertTimeStamp: exception");
        }
        LogUtil.debug(TAG, "convertTimeStamp: convertTimeStamp==" + convertTimeStamp);
        return convertTimeStamp;
    }

    private String calculateEstimatedArrivalTime(long remainSeconds) {
        LogUtil.debug(TAG, "calculateEstimatedArrivalTime: remainSeconds==" + remainSeconds);
        String calculateEstimatedArrivalTime = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            long timeSeconds = System.currentTimeMillis() + (1000 * remainSeconds);
            calculateEstimatedArrivalTime = format.format(Long.valueOf(timeSeconds));
        } catch (Exception e) {
            LogUtil.debug(TAG, "calculateEstimatedArrivalTime: exception");
        }
        LogUtil.debug(TAG, "calculateEstimatedArrivalTime: calculateEstimatedArrivalTime==" + calculateEstimatedArrivalTime);
        return calculateEstimatedArrivalTime;
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

    private String convertDisplayValue(String displayValue) {
        LogUtil.debug(TAG, "convertDisplayValue: displayValue==" + displayValue);
        String convertDisplayValue = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        if (!TextUtils.isEmpty(displayValue)) {
            convertDisplayValue = displayValue.replace(",", ".").trim().toString();
        }
        LogUtil.debug(TAG, "convertDisplayValue: convertDisplayValue==" + convertDisplayValue);
        return convertDisplayValue;
    }

    private String convertDistanceUnit(int googleUnitDefine) {
        LogUtil.debug(TAG, "convertDistanceUnit: googleUnitDefine==" + googleUnitDefine);
        String distanceUnit = null;
        switch (googleUnitDefine) {
            case 0:
                distanceUnit = CONVERT_DISTANCE_UNKNOWN_UNIT;
                break;
            case 1:
                distanceUnit = CONVERT_DISTANCE_METER_UNIT;
                break;
            case 2:
            case 3:
                distanceUnit = CONVERT_DISTANCE_KILOMETER_UNIT;
                break;
            case 4:
            case 5:
                distanceUnit = CONVERT_DISTANCE_MILE_UNIT;
                break;
            case 6:
                distanceUnit = CONVERT_DISTANCE_FEET_UNIT;
                break;
            case 7:
                distanceUnit = CONVERT_DISTANCE_YARDS_UNIT;
                break;
        }
        LogUtil.debug(TAG, "convertDistanceUnit: convertDistanceUnitResult==" + distanceUnit);
        return distanceUnit;
    }

    private boolean convertNavigationStatus(int naviState) {
        LogUtil.debug(TAG, "convertNavigationStatus: " + naviState);
        if (naviState == 1 || naviState == 3) {
            return true;
        }
        return false;
    }

    private boolean isSupportHud() {
        String modelsCode = SystemProperties.get(PERSIST_SYS_AUTO_TYPE);
        LogUtil.info(TAG, "getModelsCode :" + modelsCode);
        return TextUtils.isEmpty(modelsCode) || modelsCode.equals(TANG_VALUE) || modelsCode.equals(UKE_LEFT_VALUE) || modelsCode.equals(UEK_RIGHT_VALUE) || modelsCode.equals(MR_RIGHT_VALUE) || modelsCode.equals(PA_RIGHT_VALUE);
    }
}
