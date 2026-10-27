package android.hardware.bydauto.uistyleconfig.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public class UiStyleConfigManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = -1;
    private static final String S_KEY_FOR_TEST = "key_for_test_UiStyleManagerImpl";
    protected static final String TAG = "UiStyleManagerImpl";
    private static TsManagerImpl mTsManagerImpl;
    private static UiStyleConfigManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;

    UiStyleConfigManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                Log.d(TAG, "UiStyleConfigManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
    }

    public static UiStyleConfigManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (UiStyleConfigManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new UiStyleConfigManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    @Deprecated
    public int ambientFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "ambientFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().ambientFactoryMode(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "backAndLeaveDelayFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().backAndLeaveDelayFactoryMode(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int dayLightFactory(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "dayLightFactory mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().dayLightFactory(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 557) {
                        int leftRearMassageChairConfigurationForECU = getLeftRearMassageChairConfigurationForECU();
                        bYDAutoEventValue.intValue = leftRearMassageChairConfigurationForECU;
                        sparseArray.put(557, Integer.valueOf(leftRearMassageChairConfigurationForECU));
                    } else if (i == 558) {
                        int leftRearMassageChairConfigurationForECU2 = getLeftRearMassageChairConfigurationForECU();
                        bYDAutoEventValue.intValue = leftRearMassageChairConfigurationForECU2;
                        sparseArray.put(558, Integer.valueOf(leftRearMassageChairConfigurationForECU2));
                    } else if (i == 566) {
                        int mainDriverSeatAdjustmentConfigurationByGCtrl = getMainDriverSeatAdjustmentConfigurationByGCtrl();
                        bYDAutoEventValue.intValue = mainDriverSeatAdjustmentConfigurationByGCtrl;
                        sparseArray.put(566, Integer.valueOf(mainDriverSeatAdjustmentConfigurationByGCtrl));
                    } else if (i == 567) {
                        int mainDriveElectricHeadrestAdjustmentByGCtrl = getMainDriveElectricHeadrestAdjustmentByGCtrl();
                        bYDAutoEventValue.intValue = mainDriveElectricHeadrestAdjustmentByGCtrl;
                        sparseArray.put(567, Integer.valueOf(mainDriveElectricHeadrestAdjustmentByGCtrl));
                    } else if (i == 1154) {
                        int ambientAreaSupport = getAmbientAreaSupport();
                        bYDAutoEventValue.intValue = ambientAreaSupport;
                        sparseArray.put(1154, Integer.valueOf(ambientAreaSupport));
                    } else if (i == 1155) {
                        int ambientLightColorSupport = mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
                        bYDAutoEventValue.intValue = ambientLightColorSupport;
                        sparseArray.put(1155, Integer.valueOf(ambientLightColorSupport));
                    } else if (i == 1158) {
                        int ambientLightSupport = getAmbientLightSupport();
                        bYDAutoEventValue.intValue = ambientLightSupport;
                        sparseArray.put(1158, Integer.valueOf(ambientLightSupport));
                    } else if (i == 1159) {
                        int ambientLightColorSupport2 = getAmbientLightColorSupport();
                        bYDAutoEventValue.intValue = ambientLightColorSupport2;
                        sparseArray.put(1159, Integer.valueOf(ambientLightColorSupport2));
                    } else if (i == 2788) {
                        int frontLightDimmingCtrlConfigBy3ac = getFrontLightDimmingCtrlConfigBy3ac();
                        bYDAutoEventValue.intValue = frontLightDimmingCtrlConfigBy3ac;
                        sparseArray.put(2788, Integer.valueOf(frontLightDimmingCtrlConfigBy3ac));
                    } else if (i == 2789) {
                        int supportAiMatrixLight = getSupportAiMatrixLight();
                        bYDAutoEventValue.intValue = supportAiMatrixLight;
                        sparseArray.put(2789, Integer.valueOf(supportAiMatrixLight));
                    } else if (i == 3173) {
                        int supportLaneDepartureStop = getSupportLaneDepartureStop();
                        bYDAutoEventValue.intValue = supportLaneDepartureStop;
                        sparseArray.put(3173, Integer.valueOf(supportLaneDepartureStop));
                    } else if (i != 3174) {
                        switch (i) {
                            case 541:
                                int autoExternalRearMirrorFollowUpSwitchSupport = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                bYDAutoEventValue.intValue = autoExternalRearMirrorFollowUpSwitchSupport;
                                sparseArray.put(541, Integer.valueOf(autoExternalRearMirrorFollowUpSwitchSupport));
                                break;
                            case 560:
                                int restModeConfiguration = getRestModeConfiguration();
                                bYDAutoEventValue.intValue = restModeConfiguration;
                                sparseArray.put(560, Integer.valueOf(restModeConfiguration));
                                break;
                            case 569:
                                int mainDriveElectricLegSupportAdjustmentByGCtrl = getMainDriveElectricLegSupportAdjustmentByGCtrl();
                                bYDAutoEventValue.intValue = mainDriveElectricLegSupportAdjustmentByGCtrl;
                                sparseArray.put(569, Integer.valueOf(mainDriveElectricLegSupportAdjustmentByGCtrl));
                                break;
                            case 571:
                                int rearLeftBackrestAdjustmentConfigurationByECU = getRearLeftBackrestAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = rearLeftBackrestAdjustmentConfigurationByECU;
                                sparseArray.put(571, Integer.valueOf(rearLeftBackrestAdjustmentConfigurationByECU));
                                break;
                            case 573:
                                int rearLeftLevelAdjustmentConfigurationByECU = getRearLeftLevelAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = rearLeftLevelAdjustmentConfigurationByECU;
                                sparseArray.put(573, Integer.valueOf(rearLeftLevelAdjustmentConfigurationByECU));
                                break;
                            case 579:
                                int rearLeftLegSupportAdjustmentConfigurationByECU = getRearLeftLegSupportAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = rearLeftLegSupportAdjustmentConfigurationByECU;
                                sparseArray.put(579, Integer.valueOf(rearLeftLegSupportAdjustmentConfigurationByECU));
                                break;
                            case 585:
                                int passengerSeatElectricAdjustmentConfigurationByECU = getPassengerSeatElectricAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = passengerSeatElectricAdjustmentConfigurationByECU;
                                sparseArray.put(585, Integer.valueOf(passengerSeatElectricAdjustmentConfigurationByECU));
                                break;
                            case 590:
                                int rearRightBackrestAdjustmentConfigurationByECU = getRearRightBackrestAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = rearRightBackrestAdjustmentConfigurationByECU;
                                sparseArray.put(590, Integer.valueOf(rearRightBackrestAdjustmentConfigurationByECU));
                                break;
                            case 592:
                                int rearRightLevelAdjustmentConfigurationByECU = getRearRightLevelAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = rearRightLevelAdjustmentConfigurationByECU;
                                sparseArray.put(592, Integer.valueOf(rearRightLevelAdjustmentConfigurationByECU));
                                break;
                            case 598:
                                int rearRightLegSupportAdjustmentConfigurationByECU = getRearRightLegSupportAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = rearRightLegSupportAdjustmentConfigurationByECU;
                                sparseArray.put(598, Integer.valueOf(rearRightLegSupportAdjustmentConfigurationByECU));
                                break;
                            case 953:
                                int doorHeightSupport = mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
                                bYDAutoEventValue.intValue = doorHeightSupport;
                                sparseArray.put(953, Integer.valueOf(doorHeightSupport));
                                break;
                            case 987:
                                int electroniChildLockConfig = mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
                                bYDAutoEventValue.intValue = electroniChildLockConfig;
                                sparseArray.put(987, Integer.valueOf(electroniChildLockConfig));
                                break;
                            case 1066:
                                int supportSeatMassage = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(1);
                                bYDAutoEventValue.intValue = supportSeatMassage;
                                sparseArray.put(1066, Integer.valueOf(supportSeatMassage));
                                break;
                            case 1069:
                                int supportSeatMassage2 = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(2);
                                bYDAutoEventValue.intValue = supportSeatMassage2;
                                sparseArray.put(1069, Integer.valueOf(supportSeatMassage2));
                                break;
                            case 1146:
                                int autoExternalRearMirrorFollowUpSwitchSupport2 = getAutoExternalRearMirrorFollowUpSwitchSupport();
                                bYDAutoEventValue.intValue = autoExternalRearMirrorFollowUpSwitchSupport2;
                                sparseArray.put(1146, Integer.valueOf(autoExternalRearMirrorFollowUpSwitchSupport2));
                                break;
                            case 1255:
                                int ambientMusicModeSupport = mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
                                bYDAutoEventValue.intValue = ambientMusicModeSupport;
                                sparseArray.put(1255, Integer.valueOf(ambientMusicModeSupport));
                                break;
                            case 1268:
                                int passengerSeatElectricAdjustmentConfigurationByECU2 = getPassengerSeatElectricAdjustmentConfigurationByECU();
                                bYDAutoEventValue.intValue = passengerSeatElectricAdjustmentConfigurationByECU2;
                                sparseArray.put(1268, Integer.valueOf(passengerSeatElectricAdjustmentConfigurationByECU2));
                                break;
                            case 1285:
                                int supportSeatMassage3 = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(3);
                                bYDAutoEventValue.intValue = supportSeatMassage3;
                                sparseArray.put(1285, Integer.valueOf(supportSeatMassage3));
                                break;
                            case 1296:
                                int supportSeatMassage4 = mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(4);
                                bYDAutoEventValue.intValue = supportSeatMassage4;
                                sparseArray.put(1296, Integer.valueOf(supportSeatMassage4));
                                break;
                            case 1705:
                                int comfortableSpeedUp = getComfortableSpeedUp();
                                bYDAutoEventValue.intValue = comfortableSpeedUp;
                                sparseArray.put(1705, Integer.valueOf(comfortableSpeedUp));
                                break;
                            case 1722:
                                int refrigeratorModeSupport = getRefrigeratorModeSupport();
                                bYDAutoEventValue.intValue = refrigeratorModeSupport;
                                sparseArray.put(1722, Integer.valueOf(refrigeratorModeSupport));
                                break;
                            case 1732:
                                int electricFrontHatchConfiguration = getElectricFrontHatchConfiguration();
                                bYDAutoEventValue.intValue = electricFrontHatchConfiguration;
                                sparseArray.put(1732, Integer.valueOf(electricFrontHatchConfiguration));
                                break;
                            case 2005:
                                int rearviewMirrorSupport = mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
                                bYDAutoEventValue.intValue = rearviewMirrorSupport;
                                sparseArray.put(2005, Integer.valueOf(rearviewMirrorSupport));
                                break;
                            case 2020:
                                int carPlanSupport = getCarPlanSupport();
                                bYDAutoEventValue.intValue = carPlanSupport;
                                sparseArray.put(2020, Integer.valueOf(carPlanSupport));
                                break;
                            case 2089:
                                int windowAntiPinchConfig = mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
                                bYDAutoEventValue.intValue = windowAntiPinchConfig;
                                sparseArray.put(2089, Integer.valueOf(windowAntiPinchConfig));
                                break;
                            case 2553:
                                int intelligentKeepTemSupport = getIntelligentKeepTemSupport();
                                bYDAutoEventValue.intValue = intelligentKeepTemSupport;
                                sparseArray.put(2553, Integer.valueOf(intelligentKeepTemSupport));
                                break;
                            case 2575:
                                int dischargeSupport = getDischargeSupport();
                                bYDAutoEventValue.intValue = dischargeSupport;
                                sparseArray.put(2575, Integer.valueOf(dischargeSupport));
                                break;
                            case 2587:
                                int chargeToaggleGuardSupport = getChargeToaggleGuardSupport();
                                bYDAutoEventValue.intValue = chargeToaggleGuardSupport;
                                sparseArray.put(2587, Integer.valueOf(chargeToaggleGuardSupport));
                                break;
                            case 2609:
                                int oBCType = getOBCType();
                                bYDAutoEventValue.intValue = oBCType;
                                sparseArray.put(2609, Integer.valueOf(oBCType));
                                break;
                            case 2660:
                                int supportSmartChargingPHEV = getSupportSmartChargingPHEV();
                                bYDAutoEventValue.intValue = supportSmartChargingPHEV;
                                sparseArray.put(2660, Integer.valueOf(supportSmartChargingPHEV));
                                break;
                            case 2671:
                                int chargeLimitSupport = getChargeLimitSupport();
                                bYDAutoEventValue.intValue = chargeLimitSupport;
                                sparseArray.put(2671, Integer.valueOf(chargeLimitSupport));
                                break;
                            case 2674:
                                int noSenseChargeSupport = getNoSenseChargeSupport();
                                bYDAutoEventValue.intValue = noSenseChargeSupport;
                                sparseArray.put(2674, Integer.valueOf(noSenseChargeSupport));
                                break;
                            case 2803:
                                int dayLightSupport = getDayLightSupport();
                                bYDAutoEventValue.intValue = dayLightSupport;
                                sparseArray.put(2803, Integer.valueOf(dayLightSupport));
                                break;
                            case 3150:
                                int supportBlindSpotAssist = getSupportBlindSpotAssist();
                                bYDAutoEventValue.intValue = supportBlindSpotAssist;
                                sparseArray.put(3150, Integer.valueOf(supportBlindSpotAssist));
                                break;
                            case 3160:
                                int comfortableParkingSupport = getComfortableParkingSupport();
                                bYDAutoEventValue.intValue = comfortableParkingSupport;
                                sparseArray.put(3160, Integer.valueOf(comfortableParkingSupport));
                                break;
                            case 3163:
                                int brakePedalModeSupport = getBrakePedalModeSupport();
                                bYDAutoEventValue.intValue = brakePedalModeSupport;
                                sparseArray.put(3163, Integer.valueOf(brakePedalModeSupport));
                                break;
                            case 3167:
                                int supportDoorOpenWarning = getSupportDoorOpenWarning();
                                bYDAutoEventValue.intValue = supportDoorOpenWarning;
                                sparseArray.put(3167, Integer.valueOf(supportDoorOpenWarning));
                                break;
                            case 3180:
                                int supportEmergencyLaneKeepAssist = getSupportEmergencyLaneKeepAssist();
                                bYDAutoEventValue.intValue = supportEmergencyLaneKeepAssist;
                                sparseArray.put(3180, Integer.valueOf(supportEmergencyLaneKeepAssist));
                                break;
                            case 3192:
                                int supportFrontCrossingTrafficAlert = getSupportFrontCrossingTrafficAlert();
                                bYDAutoEventValue.intValue = supportFrontCrossingTrafficAlert;
                                sparseArray.put(3192, Integer.valueOf(supportFrontCrossingTrafficAlert));
                                break;
                            case 3195:
                                int supportFrontCrossingTrafficBrake = getSupportFrontCrossingTrafficBrake();
                                bYDAutoEventValue.intValue = supportFrontCrossingTrafficBrake;
                                sparseArray.put(3195, Integer.valueOf(supportFrontCrossingTrafficBrake));
                                break;
                            case 3199:
                                int supportPredictCrashAlertMode = getSupportPredictCrashAlertMode();
                                bYDAutoEventValue.intValue = supportPredictCrashAlertMode;
                                sparseArray.put(3199, Integer.valueOf(supportPredictCrashAlertMode));
                                break;
                            case 3218:
                                int supportESP = getSupportESP();
                                bYDAutoEventValue.intValue = supportESP;
                                sparseArray.put(3218, Integer.valueOf(supportESP));
                                break;
                            case 3225:
                                int supportIntelligentNavigation = getSupportIntelligentNavigation();
                                bYDAutoEventValue.intValue = supportIntelligentNavigation;
                                sparseArray.put(3225, Integer.valueOf(supportIntelligentNavigation));
                                break;
                            case 3229:
                                int supportHighwayNavigation = getSupportHighwayNavigation();
                                bYDAutoEventValue.intValue = supportHighwayNavigation;
                                sparseArray.put(3229, Integer.valueOf(supportHighwayNavigation));
                                break;
                            case 3234:
                                int supportLaneChangeAssist = getSupportLaneChangeAssist();
                                bYDAutoEventValue.intValue = supportLaneChangeAssist;
                                sparseArray.put(3234, Integer.valueOf(supportLaneChangeAssist));
                                break;
                            case 3237:
                                int supportIntellignetSpeedLimitControl = getSupportIntellignetSpeedLimitControl();
                                bYDAutoEventValue.intValue = supportIntellignetSpeedLimitControl;
                                sparseArray.put(3237, Integer.valueOf(supportIntellignetSpeedLimitControl));
                                break;
                            case 3253:
                                int supportShakeAlert = getSupportShakeAlert();
                                bYDAutoEventValue.intValue = supportShakeAlert;
                                sparseArray.put(3253, Integer.valueOf(supportShakeAlert));
                                break;
                            case 3269:
                                int supportRearCollisionWarning = getSupportRearCollisionWarning();
                                bYDAutoEventValue.intValue = supportRearCollisionWarning;
                                sparseArray.put(3269, Integer.valueOf(supportRearCollisionWarning));
                                break;
                            case 3276:
                                int supportRearTrafficCrossingAlert = getSupportRearTrafficCrossingAlert();
                                bYDAutoEventValue.intValue = supportRearTrafficCrossingAlert;
                                sparseArray.put(3276, Integer.valueOf(supportRearTrafficCrossingAlert));
                                break;
                            case 3278:
                                int supportRearCrossBrake = getSupportRearCrossBrake();
                                bYDAutoEventValue.intValue = supportRearCrossBrake;
                                sparseArray.put(3278, Integer.valueOf(supportRearCrossBrake));
                                break;
                            case 3287:
                                int supportIntellignetSpeedLimitAlert = getSupportIntellignetSpeedLimitAlert();
                                bYDAutoEventValue.intValue = supportIntellignetSpeedLimitAlert;
                                sparseArray.put(3287, Integer.valueOf(supportIntellignetSpeedLimitAlert));
                                break;
                            case 3300:
                                int supportTrafficLightAttention = getSupportTrafficLightAttention();
                                bYDAutoEventValue.intValue = supportTrafficLightAttention;
                                sparseArray.put(3300, Integer.valueOf(supportTrafficLightAttention));
                                break;
                            case 3307:
                                int supportUrbanNavigation = getSupportUrbanNavigation();
                                bYDAutoEventValue.intValue = supportUrbanNavigation;
                                sparseArray.put(3307, Integer.valueOf(supportUrbanNavigation));
                                break;
                            case 3315:
                                int supportAcAutoKeyCanClose = getSupportAcAutoKeyCanClose();
                                bYDAutoEventValue.intValue = supportAcAutoKeyCanClose;
                                sparseArray.put(3315, Integer.valueOf(supportAcAutoKeyCanClose));
                                break;
                            case 3344:
                                int supportFRElectricAirOutlet = getSupportFRElectricAirOutlet();
                                bYDAutoEventValue.intValue = supportFRElectricAirOutlet;
                                sparseArray.put(3344, Integer.valueOf(supportFRElectricAirOutlet));
                                break;
                            case 3348:
                                int supportFragrance = getSupportFragrance();
                                bYDAutoEventValue.intValue = supportFragrance;
                                sparseArray.put(3348, Integer.valueOf(supportFragrance));
                                break;
                            case 3465:
                                int supportRearAcKeyLock = getSupportRearAcKeyLock();
                                bYDAutoEventValue.intValue = supportRearAcKeyLock;
                                sparseArray.put(3465, Integer.valueOf(supportRearAcKeyLock));
                                break;
                            case 3520:
                                int intelligentAcAreaSupport = getIntelligentAcAreaSupport();
                                bYDAutoEventValue.intValue = intelligentAcAreaSupport;
                                sparseArray.put(3520, Integer.valueOf(intelligentAcAreaSupport));
                                break;
                            case 4193:
                                int driveTimeInterfaceStatusForSingleTrip = getDriveTimeInterfaceStatusForSingleTrip();
                                bYDAutoEventValue.intValue = driveTimeInterfaceStatusForSingleTrip;
                                sparseArray.put(4193, Integer.valueOf(driveTimeInterfaceStatusForSingleTrip));
                                break;
                            case 4537:
                                int ambientColorsSupport = mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
                                bYDAutoEventValue.intValue = ambientColorsSupport;
                                sparseArray.put(4537, Integer.valueOf(ambientColorsSupport));
                                break;
                            case 4553:
                                int supportAutoParking = getSupportAutoParking();
                                bYDAutoEventValue.intValue = supportAutoParking;
                                sparseArray.put(4553, Integer.valueOf(supportAutoParking));
                                break;
                            default:
                                switch (i) {
                                    case 3208:
                                        int supportAiEmergencyBrake = getSupportAiEmergencyBrake();
                                        bYDAutoEventValue.intValue = supportAiEmergencyBrake;
                                        sparseArray.put(3208, Integer.valueOf(supportAiEmergencyBrake));
                                        break;
                                    case 3209:
                                        int hasPredictCrashAlertMode = getHasPredictCrashAlertMode();
                                        bYDAutoEventValue.intValue = hasPredictCrashAlertMode;
                                        sparseArray.put(3209, Integer.valueOf(hasPredictCrashAlertMode));
                                        break;
                                    case 3210:
                                        int supportAiDistanceLight = getSupportAiDistanceLight();
                                        bYDAutoEventValue.intValue = supportAiDistanceLight;
                                        sparseArray.put(3210, Integer.valueOf(supportAiDistanceLight));
                                        break;
                                    case 3211:
                                        int supportLaneDepartureWarning = getSupportLaneDepartureWarning();
                                        bYDAutoEventValue.intValue = supportLaneDepartureWarning;
                                        sparseArray.put(3211, Integer.valueOf(supportLaneDepartureWarning));
                                        break;
                                    case 3212:
                                        int supportLaneKeep = getSupportLaneKeep();
                                        bYDAutoEventValue.intValue = supportLaneKeep;
                                        sparseArray.put(3212, Integer.valueOf(supportLaneKeep));
                                        break;
                                    case 3213:
                                        int supportTrafficSignsRecognition = getSupportTrafficSignsRecognition();
                                        bYDAutoEventValue.intValue = supportTrafficSignsRecognition;
                                        sparseArray.put(3213, Integer.valueOf(supportTrafficSignsRecognition));
                                        break;
                                    case 3214:
                                        int supportIntelligentNavigation2 = getSupportIntelligentNavigation();
                                        bYDAutoEventValue.intValue = supportIntelligentNavigation2;
                                        sparseArray.put(3214, Integer.valueOf(supportIntelligentNavigation2));
                                        break;
                                    default:
                                        switch (i) {
                                            case 3677:
                                                int theLast50KMAveragePowerConsumptionInterfaceState = getTheLast50KMAveragePowerConsumptionInterfaceState();
                                                bYDAutoEventValue.intValue = theLast50KMAveragePowerConsumptionInterfaceState;
                                                sparseArray.put(3677, Integer.valueOf(theLast50KMAveragePowerConsumptionInterfaceState));
                                                break;
                                            case 3678:
                                                int theLast50KMAverageEnergyConsumptionInterfaceState = getTheLast50KMAverageEnergyConsumptionInterfaceState();
                                                bYDAutoEventValue.intValue = theLast50KMAverageEnergyConsumptionInterfaceState;
                                                sparseArray.put(3678, Integer.valueOf(theLast50KMAverageEnergyConsumptionInterfaceState));
                                                break;
                                            case 3679:
                                                int theLast50KMAverageFuelConsumptionInterfaceState = getTheLast50KMAverageFuelConsumptionInterfaceState();
                                                bYDAutoEventValue.intValue = theLast50KMAverageFuelConsumptionInterfaceState;
                                                sparseArray.put(3679, Integer.valueOf(theLast50KMAverageFuelConsumptionInterfaceState));
                                                break;
                                            case 3680:
                                                int cumulativeAveragePowerConsumptionInterfaceStatus = getCumulativeAveragePowerConsumptionInterfaceStatus();
                                                bYDAutoEventValue.intValue = cumulativeAveragePowerConsumptionInterfaceStatus;
                                                sparseArray.put(3680, Integer.valueOf(cumulativeAveragePowerConsumptionInterfaceStatus));
                                                break;
                                            case 3681:
                                                int cumulativeAverageEnergyConsumptionInterfaceStatus = getCumulativeAverageEnergyConsumptionInterfaceStatus();
                                                bYDAutoEventValue.intValue = cumulativeAverageEnergyConsumptionInterfaceStatus;
                                                sparseArray.put(3681, Integer.valueOf(cumulativeAverageEnergyConsumptionInterfaceStatus));
                                                break;
                                            case 3682:
                                                int cumulativeAverageFuelConsumptionInterfaceStatus = getCumulativeAverageFuelConsumptionInterfaceStatus();
                                                bYDAutoEventValue.intValue = cumulativeAverageFuelConsumptionInterfaceStatus;
                                                sparseArray.put(3682, Integer.valueOf(cumulativeAverageFuelConsumptionInterfaceStatus));
                                                break;
                                            case 3683:
                                                int averageSpeedInterfaceStatus = getAverageSpeedInterfaceStatus();
                                                bYDAutoEventValue.intValue = averageSpeedInterfaceStatus;
                                                sparseArray.put(3683, Integer.valueOf(averageSpeedInterfaceStatus));
                                                break;
                                            case 3684:
                                                int tirePressureInterfaceStatus = getTirePressureInterfaceStatus();
                                                bYDAutoEventValue.intValue = tirePressureInterfaceStatus;
                                                sparseArray.put(3684, Integer.valueOf(tirePressureInterfaceStatus));
                                                break;
                                            case 3685:
                                                int energyFlowDiagramInterfaceStatus = getEnergyFlowDiagramInterfaceStatus();
                                                bYDAutoEventValue.intValue = energyFlowDiagramInterfaceStatus;
                                                sparseArray.put(3685, Integer.valueOf(energyFlowDiagramInterfaceStatus));
                                                break;
                                            case 3686:
                                                int externalChargingPowerInterfaceStatus = getExternalChargingPowerInterfaceStatus();
                                                bYDAutoEventValue.intValue = externalChargingPowerInterfaceStatus;
                                                sparseArray.put(3686, Integer.valueOf(externalChargingPowerInterfaceStatus));
                                                break;
                                            case 3687:
                                                int fuelConsumptionInterfaceStatus = getFuelConsumptionInterfaceStatus();
                                                bYDAutoEventValue.intValue = fuelConsumptionInterfaceStatus;
                                                sparseArray.put(3687, Integer.valueOf(fuelConsumptionInterfaceStatus));
                                                break;
                                            case 3688:
                                                int drivingRangeInterfaceStatusForFuel = getDrivingRangeInterfaceStatusForFuel();
                                                bYDAutoEventValue.intValue = drivingRangeInterfaceStatusForFuel;
                                                sparseArray.put(3688, Integer.valueOf(drivingRangeInterfaceStatusForFuel));
                                                break;
                                            case 3689:
                                                int instantaneousFuelConsumptionInterfaceStatus = getInstantaneousFuelConsumptionInterfaceStatus();
                                                bYDAutoEventValue.intValue = instantaneousFuelConsumptionInterfaceStatus;
                                                sparseArray.put(3689, Integer.valueOf(instantaneousFuelConsumptionInterfaceStatus));
                                                break;
                                            case 3690:
                                                int carType = getCarType();
                                                bYDAutoEventValue.intValue = carType;
                                                sparseArray.put(3690, Integer.valueOf(carType));
                                                break;
                                            default:
                                                switch (i) {
                                                    case 4556:
                                                        int runingCloseDoorSupport = getRuningCloseDoorSupport();
                                                        bYDAutoEventValue.intValue = runingCloseDoorSupport;
                                                        sparseArray.put(4556, Integer.valueOf(runingCloseDoorSupport));
                                                        break;
                                                    case 4557:
                                                        int runingCloseDoorSupport2 = getRuningCloseDoorSupport();
                                                        bYDAutoEventValue.intValue = runingCloseDoorSupport2;
                                                        sparseArray.put(4557, Integer.valueOf(runingCloseDoorSupport2));
                                                        break;
                                                    case 4558:
                                                        int runingCloseDoorSupport3 = getRuningCloseDoorSupport();
                                                        bYDAutoEventValue.intValue = runingCloseDoorSupport3;
                                                        sparseArray.put(4558, Integer.valueOf(runingCloseDoorSupport3));
                                                        break;
                                                    case 4559:
                                                        int runingCloseDoorSupport4 = getRuningCloseDoorSupport();
                                                        bYDAutoEventValue.intValue = runingCloseDoorSupport4;
                                                        sparseArray.put(4559, Integer.valueOf(runingCloseDoorSupport4));
                                                        break;
                                                    default:
                                                        Log.d(TAG, "case fail ! please check out");
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                    } else {
                        int supportEndToEndNavigation = getSupportEndToEndNavigation();
                        bYDAutoEventValue.intValue = supportEndToEndNavigation;
                        sparseArray.put(3174, Integer.valueOf(supportEndToEndNavigation));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    @Deprecated
    public int getACAutoAirSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getACAutoAirSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACAutoAirSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getACBTWindSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getACBTWindSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACBTWindSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getACPauseCycleSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getACPauseCycleSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACPauseCycleSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getACTunnelCycleSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACTunnelCycleSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getADASSystemState() {
        Log.d(TAG, "getADASSystemState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getADASSystemState();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcFaultNumShownState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcFaultNumShownState();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAcType() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcType();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAcWarmSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcWarmSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWarmSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcWarmTypeOnlineState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWarmTypeOnlineState();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAcWindModeNum() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAcWindModeNum mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWindModeNum();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAdaptiveHeadlightsSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAdaptiveHeadlightsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAdaptiveHeadlightsSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAllRearSeatOneButtonDownSwitchDisplayConfig() {
        Log.d(TAG, "getAllRearSeatOneButtonDownSwitchDisplayConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAllRearSeatOneButtonDownSwitchDisplayConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getAmbientAreaSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbientAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientAreaSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientColorsSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientLightColorSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbientLightColorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientLightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbientLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientLightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientModeSwitchSettings() {
        Log.d(TAG, "getAmbientModeSwitchSettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientModeSwitchSettings();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAmbientMusicModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmplifierConfiguration() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmplifierConfiguration();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getAmplifierConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getAppUseLimitSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAppUseLimitSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAppUseLimitSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoPowerOffSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOffSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoPowerOnStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOnStateSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoPurificationSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoPurificationSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPurificationSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoReportContentSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoReportContentSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoReportContentSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoWiperSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getAutoWiperSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoWiperSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getAverageSpeedInterfaceStatus() {
        Log.d(TAG, "getAverageSpeedInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAverageSpeedInterfaceStatus();
    }

    public int getBackHomeDelaySupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getBackHomeDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackHomeDelaySupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getBrakePedalModeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getBrakePedalModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBrakePedalModeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getBsisSupportR() {
        Log.i(TAG, "getBsisSupportR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "BsisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBsisSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCaliperSplintSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getCaliperSplintSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCaliperSplintSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getCameraEerRomDeviceAddress() {
        Log.d(TAG, "getCameraEerRomDeviceAddress is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraEerRomDeviceAddress();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraIspId() {
        Log.d(TAG, "getCameraIspId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraIspId();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraLensId() {
        Log.d(TAG, "getCameraLensId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraLensId();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraSerializerId() {
        Log.d(TAG, "getCameraSerializerId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraSerializerId();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraSupplierAndTestBoardInfo() {
        Log.d(TAG, "getCameraSupplierAndTestBoardInfo is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraSupplierAndTestBoardInfo();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoFormat() {
        Log.d(TAG, "getCameraVideoFormat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFormat();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoFrameRate() {
        Log.d(TAG, "getCameraVideoFrameRate is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFrameRate();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoResolution() {
        Log.d(TAG, "getCameraVideoResolution is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoResolution();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCarNum() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getCarNum mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCarNum();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getCarPlanSupport() {
        Log.d(TAG, "getCarPlanSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarPlanSupport();
    }

    public int getCarTipsSoundSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getCarTipsSoundSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCarTipsSoundSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getCarType() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getCarType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCarType();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getChargeLimitSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getChargeLimitSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getChargeLimitSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getChargePreheatSupport() {
        Log.d(TAG, "getChargePreheatSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getChargePreheatSupport();
    }

    public int getChargeToaggleGuardSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getChargeToaggleGuardSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getChargeToaggleGuardSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getComfortableParkingBy37F() {
        Log.d(TAG, "getComfortableParkingBy37F is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingBy37F();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getComfortableParkingSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getComfortableParkingSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getComfortableSpeedDown() {
        Log.d(TAG, "getComfortableSpeedDown is execute");
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedDown();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getComfortableSpeedDown mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getComfortableSpeedUp() {
        Log.d(TAG, "getComfortableSpeedUp is execute");
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedUp();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, "getComfortableSpeedUp mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getCpdSupportR() {
        Log.i(TAG, "getCpdSupportR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "CpdSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCpdSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCumulativeAverageEnergyConsumptionInterfaceStatus() {
        Log.d(TAG, "getCumulativeAverageEnergyConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAverageEnergyConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageFuelConsumptionInterfaceStatus() {
        Log.d(TAG, "getCumulativeAverageFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAverageFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAveragePowerConsumptionInterfaceStatus() {
        Log.d(TAG, "getCumulativeAveragePowerConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAveragePowerConsumptionInterfaceStatus();
    }

    public int getCushionSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getCushionSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportDriver();
        }
        if (i == 2) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportPassenger();
        }
        if (i == 3) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportRearLeft();
        }
        if (i != 4) {
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCushionSupportRearRight();
    }

    public int getCvcConfig() {
        Log.d(TAG, "getCvcConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCvcConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDMSAssembleStatus() {
        Log.d(TAG, "getDMSAssembleStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDMSAssembleStatus();
    }

    public int getDMType() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDMType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDMType();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayLightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDayLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDayLightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDefrostRearConfig() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDefrostRearConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDefrostRearConfig();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDiSusShowStateByCan() {
        Log.d(TAG, "getDiSusShowStateByCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCan();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDiSusShowStateByCanFd() {
        Log.d(TAG, "getDiSusShowStateByCanFd is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCanFd();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDigtalKeySupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDigtalKeySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDigtalKeySupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDischargeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDischargeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDischargeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDisinfSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDisinfSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDisinfSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorHeightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDoorHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorLightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDoorLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorLightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDoorknobAutoShrinkSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorknobAutoShrinkSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveConfig() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDriveConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveConfig();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveDistanceShowModeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDriveDistanceShowModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowModeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveDistanceShowStandardSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDriveDistanceShowStandardSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowStandardSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDriveSeatAutoReturnSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveSeatAutoReturnSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        Log.d(TAG, "getDriveTimeInterfaceStatusForSingleTrip is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getDriverSeatHotStoneMassageConfigurationByEcu() {
        Log.d(TAG, "getDriverSeatHotStoneMassageConfigurationByEcu is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByEcu();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDriverSeatHotStoneMassageConfigurationByLeftCtrl() {
        Log.d(TAG, "getDriverSeatHotStoneMassageConfigurationByLeftCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByLeftCtrl();
    }

    public int getDriverSeatHotStoneMassageConfigurationByUniversalCtrl() {
        Log.d(TAG, "getDriverSeatHotStoneMassageConfigurationByUniversalCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByUniversalCtrl();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        Log.d(TAG, "getDrivingRangeInterfaceStatusForFuel is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingRangeInterfaceStatusForFuel();
    }

    public int getDrivingVoiceBroadcastSwitchConfig() {
        Log.d(TAG, "getDrivingVoiceBroadcastSwitchConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingVoiceBroadcastSwitchConfig();
    }

    public int getDynamicSuspensionModeByCanFdR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanFdR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "DynamicSuspensionModeByCanFdR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanFdR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDynamicSuspensionModeByCanR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "DynamicSuspensionModeByCanR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeByCanR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDynamicSuspensionModeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getDynamicSuspensionModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getEPedalStateSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getEPedalStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getEPedalStateSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getElectricFrontHatchConfiguration() {
        Log.d(TAG, "getElectricFrontHatchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
    }

    public int getElectroniChildLockConfig() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getElectroniChildLockConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getElectronicParkingBrakeConfigutarion() {
        Log.d(TAG, "getElectronicParkingBrakeConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectronicParkingBrakeConfigutarion();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        Log.d(TAG, "getEnergyFlowDiagramInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEnergyFlowDiagramInterfaceStatus();
    }

    @Deprecated
    public int getEnergyStatus() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getEnergyStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getEnergyStatus();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getEngineSoundSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getEngineSoundSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getEngineSoundSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getExternalChargingPowerInterfaceStatus() {
        Log.d(TAG, "getExternalChargingPowerInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getExternalChargingPowerInterfaceStatus();
    }

    public int getFeaturesStateR() {
        Log.i(TAG, "getFeaturesStateR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "FeaturesStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFeaturesStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFoldDownTheSecondRowOfSeatsConfig() {
        Log.d(TAG, "getFoldDownTheSecondRowOfSeatsConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFoldDownTheSecondRowOfSeatsConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getFragranceInstallState(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportFragranceType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFragranceInstallState(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getFrontLightDimmingCtrlConfigBy3ac() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ac();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getFrontLightDimmingCtrlConfigBy3ac mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getFrontLightDimmingCtrlConfigBy3ad() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ad();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getFrontLightDimmingCtrlConfigBy3ad mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getFuelConsumptionInterfaceStatus() {
        Log.d(TAG, "getFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFuelConsumptionInterfaceStatus();
    }

    public int getGuestLeftSeatRememeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getGuestLeftSeatRememeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getGuestLeftSeatRememeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getGuestRightSeatRememeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getGuestRightSeatRememeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getGuestRightSeatRememeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHUDModeConfiguration() {
        Log.d(TAG, "getHUDModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getHUDModeConfiguration();
    }

    public int getHasPredictCrashAlertMode() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getHasPredictCrashAlertMode();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getHasPredictCrashAlertMode mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getHeadlampLevelConfig() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHeadlampLevelConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadlampLevelConfig();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeadlampSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHeadlampSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadlampSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestTurnHeightSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHeadrestTurnHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHeightSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHeadrestTurnHorizatialSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHorizatialSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHudHeightAdjustSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHudHeightAdjustSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudHeightAdjustSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHudLightSdjustSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHudLightSdjustSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudLightSdjustSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHudParamsStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudParamsStateSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getHudShowParamsSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getHudShowParamsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudShowParamsSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getIndoorLightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getIndoorLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIndoorLightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        Log.d(TAG, "getInstantaneousFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getInstrumentContentSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getInstrumentContentSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentContentSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstrumentHeightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getInstrumentHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentHeightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstrumentThemeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getInstrumentThemeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentThemeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getIntelligentAcAreaSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getIntelligentAcAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentAcAreaSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getIntelligentKeepTemSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getIntelligentKeepTemSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentKeepTemSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getIntelligentLockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentLockSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getIntelligentScreenCastingSwitchConfiguration() {
        Log.d(TAG, "getIntelligentScreenCastingSwitchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentScreenCastingSwitchConfiguration();
    }

    public int getIntelligentSpeedLimitMapVersionDisplay() {
        Log.d(TAG, "getIntelligentSpeedLimitMapVersionDisplay is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentSpeedLimitMapVersionDisplay();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getIntelligentUnlockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentUnlockSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLandAdvanceAtSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getLandAdvanceAtSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLandAdvanceAtSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getLeaveHomeDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLeaveHomeDelaySupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftPlatformConfigs() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getLeftPlatformConfigs mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLeftPlatformConfigs();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftRESConfiguration() {
        Log.d(TAG, "getLeftRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRESConfiguration();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        Log.d(TAG, "getLeftRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
    }

    public int getLeftRearMemoryConfiguration() {
        Log.d(TAG, "getLeftRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        Log.d(TAG, "getLeftRearSeatBackAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        Log.d(TAG, "getLeftRearSeatHorizontalAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getLeftRearSeatHotStoneMassageConfigurationByECU() {
        Log.d(TAG, "getLeftRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getLeftRearSeatLegBracket() {
        Log.d(TAG, "getLeftRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
    }

    public int getLeftRearSeatReclineConfiguration() {
        Log.d(TAG, "getLeftRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfiguration();
    }

    public int getLeftRearSeatReclineConfigurationByECU() {
        Log.d(TAG, "getLeftRearSeatReclineConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfigurationByECU();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getLockCarRiseWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLockCarRiseWindowSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getLockOffSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getLockOffSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLockOffSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getLowSpeedEmergencyBrakingConfig() {
        Log.d(TAG, "getLowSpeedEmergencyBrakingConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLowSpeedEmergencyBrakingConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        Log.d(TAG, "getMainDriveElectricHeadrestAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        Log.d(TAG, "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        Log.d(TAG, "getMainDriveElectricLegSupportAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        Log.d(TAG, "getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        Log.d(TAG, "getMainDriverSeatAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        Log.d(TAG, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getMainSeatRememberSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMainSeatRememberSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getMemoryParking() {
        Log.d(TAG, "getMemoryParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMemoryParking();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getMicroSwitchLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchLockWindowSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getMicroSwitchUnlockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchUnlockWindowSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getMoisSupportR() {
        Log.i(TAG, "getMoisSupportR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "MoisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMoisSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMoonRoofConfig() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getMoonRoofConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMoonRoofConfig();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getMotorPretensionerSeatbeltSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getMotorPretensionerSeatbeltSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMotorPretensionerSeatbeltSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getNoSenseChargeSupport() {
        Log.d(TAG, "getNoSenseChargeSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getNoSenseChargeSupport();
    }

    public int getOBCType() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getOBCType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOBCType();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() {
        Log.d(TAG, "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() {
        Log.d(TAG, "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() {
        Log.d(TAG, "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getOverspeedLockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOverspeedLockSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getPM25PowerSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getPM25PowerSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25PowerSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getPM25SwitchCheckSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25SwitchCheckSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getPM25TimeCkeckSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25TimeCkeckSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getPabSwitchState() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPabSwitchState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getPabSwitchState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPanoramicSunroofConfigutarion() {
        Log.d(TAG, "getPanoramicSunroofConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPanoramicSunroofConfigutarion();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        Log.d(TAG, "getPassengerSeatElectricAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        Log.d(TAG, "getPassengerSeatHeadrestConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByGCtrl() {
        Log.d(TAG, "getPassengerSeatHeadrestConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByGCtrl();
    }

    public int getPassengerSeatHotStoneMassageConfigurationByECU() {
        Log.d(TAG, "getPassengerSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHotStoneMassageConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        Log.d(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl() {
        Log.d(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        Log.d(TAG, "getPassengerSeatMemoryFunctionCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCan();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        Log.d(TAG, "getPassengerSeatMemoryFunctionCanByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
    }

    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.d(TAG, "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.d(TAG, "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getPreSaleModelSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getPreSaleModelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPreSaleModelSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRainAutoLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRainAutoLockWindowSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRearAcMaxWindLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearAcMaxWindLevel();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearLeftLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        Log.d(TAG, "getRearLeftMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
    }

    public int getRearPanelLockTimeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRearPanelLockTimeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearPanelLockTimeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        Log.d(TAG, "getRearRightLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        Log.d(TAG, "getRearRightMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
    }

    public int getRearSeatReclineConfiguration() {
        Log.d(TAG, "getRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRearViewMirrorFlipSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearViewMirrorFlipSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearviewMirrorSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getRefrigeratorModeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRefrigeratorModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRefrigeratorModeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getRemindSpeedSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRemindSpeedSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemindSpeedSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRemoteControlLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteControlLockWindowSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRemoteControlUnlockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteControlUnlockWindowSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getRemoteKeyStartAcTimeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteKeyStartAcTimeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getReportContentSetSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getReportContentSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getReportContentSetSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getReportItemsSetSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getReportItemsSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getReportItemsSetSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getReportVolumeSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getReportVolumeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getReportVolumeSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getRestModeConfiguration() {
        Log.d(TAG, "getRestModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRestModeConfiguration();
    }

    public int getRightRESConfiguration() {
        Log.d(TAG, "getRightRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRESConfiguration();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        Log.d(TAG, "getRightRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
    }

    public int getRightRearMemoryConfiguration() {
        Log.d(TAG, "getRightRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        Log.d(TAG, "getRightRearSeatBackAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        Log.d(TAG, "getRightRearSeatHorizontalAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getRightRearSeatHotStoneMassageConfigurationByECU() {
        Log.d(TAG, "getRightRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRightRearSeatLegBracket() {
        Log.d(TAG, "getRightRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
    }

    public int getRightRearSeatReclineConfiguration() {
        Log.d(TAG, "getRightRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfiguration();
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        Log.d(TAG, "getRightRearSeatReclineConfigurationByEUC is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
    }

    public int getRuningCloseDoorSupport() {
        Log.d(TAG, "getRuningCloseDoorSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRuningCloseDoorSupport();
    }

    public int getSeatSetSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSeatSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatSetSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatTurnHeightSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSeatTurnHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHeightSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatTurnHorizatialSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSeatTurnHorizatialSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHorizatialSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatbackrestSupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSeatbackrestSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatbackrestSupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSecondSeatRememberSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSecondSeatRememberSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSecondSeatRememberSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSideDistanceWarningConfig() {
        Log.d(TAG, "getSideDistanceWarningConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSideDistanceWarningConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getStarTopSwitchSetting() {
        Log.d(TAG, "getStarTopSwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getStarTopSwitchSetting();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getSteerAssisSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSteerAssisSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSteerAssisSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSteerPositionAutoReturnSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSteerPositionAutoReturnSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSteeringWheelHeatingConfiguration() {
        Log.d(TAG, "getSteeringWheelHeatingConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelHeatingConfiguration();
    }

    public int getSteeringWheelSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSteeringWheelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAFS() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAFS mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAFS();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAcAutoKeyCanClose() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAcAutoKeyCanClose mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAcAutoKeyCanClose();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiCare() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAiCare mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiCare();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiDistanceLight() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAiDistanceLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiDistanceLight();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiEmergencyBrake() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAiEmergencyBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiEmergencyBrake();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiMatrixLight() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAiMatrixLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiMatrixLight();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAutoParking() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportAutoParking mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAutoParking();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAvh() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAvh();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getSupportAVH mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportBlindSpotAssist() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportBlindSpotAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssist();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportBlindSpotAssistBy418() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportBlindSpotAssistBy418 mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssistBy418();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportDigitalKey() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportDigitalKey mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDigitalKey();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportDimmingCanopyConfiguration() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDimmingCanopyConfiguration();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getSupportDimmingCanopyConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportDoorOpenWarning() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportDoorOpenWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDoorOpenWarning();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportDoubleConfirm() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDoubleConfirm();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getSupportDoubleConfirm mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportESP() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportESP mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportESP();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportEmergencyLaneKeepAssist() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportEmergencyLaneKeepAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportEmergencyLaneKeepAssist();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportEndToEndNavigation() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportEndToEndNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportEndToEndNavigation();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportExtreme() {
        Log.d(TAG, "getSupportExtreme is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportExtreme();
    }

    public int getSupportFRElectricAirOutlet() {
        Log.d(TAG, "getSupportFRElectricAirOutlet is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFRElectricAirOutlet();
    }

    public int getSupportFragrance() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportFragrance mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFragrance();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportFrontCrossingTrafficAlert() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportFrontCrossingTrafficAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficAlert();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportFrontCrossingTrafficBrake() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportFrontCrossingTrafficBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficBrake();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportHighTemperatureSterilize() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportHighTemperatureSterilize mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportHighTemperatureSterilize();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportHighwayNavigation() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportHighwayNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportHighwayNavigation();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntelligentNavigation() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportIntelligentNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigation();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntelligentNavigationBy432() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportIntelligentNavigationBy432 mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigationBy432();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportIntellignetSpeedLimitAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitAlert();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntellignetSpeedLimitControl() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportIntellignetSpeedLimitControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitControl();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLampSingnal() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportLampSingnal mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLampSingnal();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneChangeAssist() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportLaneChangeAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneChangeAssist();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneDepartureStop() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportLaneDepartureStop mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureStop();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneDepartureWarning() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportLaneDepartureWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureWarning();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneKeep() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportLaneKeep mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneKeep();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportManualModelR() {
        Log.i(TAG, "getSupportManualModelR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "SupportManualModelR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportManualModelR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportMistakeGas() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportMistakeGas mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportMistakeGas();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportNegativeIon() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportNegativeIon mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportNegativeIon();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportPM25Mode() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportPM25Mode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPM25Mode();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportPanoramaImage() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportPanoramaImage mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPanoramaImage();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportPredictCrashAlertMode() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportPredictCrashAlertMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPredictCrashAlertMode();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAc() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAc mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAc();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcAutoControl() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcAutoControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcAutoControl();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcCompressor() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcCompressor mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcCompressor();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcKeyLock() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcKeyLock mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcKeyLock();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcSwitch() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcSwitch();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcTempertureControl() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcTempertureControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcTempertureControl();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcWindLevelControl() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcWindLevelControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindLevelControl();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcWindModeControl() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearAcWindModeControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindModeControl();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearCollisionWarning() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearCollisionWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearCollisionWarning();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearControlPanelSign() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearControlPanelSign();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getSupportRearControlPanelSign mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportRearCrossBrake() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearCrossBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearCrossBrake();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearTrafficCrossingAlert() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRearTrafficCrossingAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearTrafficCrossingAlert();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportRemenberPowerOff mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRemenberPowerOff();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportReverseRadar() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportReverseRadar mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportReverseRadar();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getSupportSeatAdjust(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportSeatAdjust mForTestValue is 0 execute return");
            return new int[0];
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSeatAdjust(i);
        }
        Log.d(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getSupportSeatMassage(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportSeatMassage mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportShakeAlert() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportShakeAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlert();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportShakeAlertR() {
        Log.i(TAG, "getSupportShakeAlertR is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "SupportShakeAlertR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlertR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSupportShowCarModel() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportShowCarModel mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShowCarModel();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportSingleDoubleTempArea() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportSingleDoubleTempArea mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSingleDoubleTempArea();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportSmartChargingPHEV() {
        Log.d(TAG, "getSupportSmartChargingPHEV is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPHEV();
    }

    public int getSupportSmartChargingPhevPhase2R() {
        Log.i(TAG, "getSupportSmartChargingPhevPhase2R is execute");
        if (1 == this.mForTest) {
            Log.d(TAG, "SupportSmartChargingPhevPhase2R mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPhevPhase2R();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportSteeringWheelWarm mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSteeringWheelWarm();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTrafficLightAttention() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportTrafficLightAttention mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficLightAttention();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTrafficSignRecogniton() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportTrafficSignRecogniton mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignRecogniton();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTrafficSignsRecognition() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportTrafficSignsRecognition mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignsRecognition();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTsrFlow() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportTsrFlow();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getSupportTsrFlow mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportUrbanNavigation() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getSupportUrbanNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportUrbanNavigation();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getTailgateTypeMarking() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getTailgateTypeMarking();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getTailgateTypeMarking mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTheLast50KMAverageEnergyConsumptionInterfaceState() {
        Log.d(TAG, "getTheLast50KMAverageEnergyConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAverageEnergyConsumptionInterfaceState();
    }

    public int getTheLast50KMAverageFuelConsumptionInterfaceState() {
        Log.d(TAG, "getTheLast50KMAverageFuelConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAverageFuelConsumptionInterfaceState();
    }

    public int getTheLast50KMAveragePowerConsumptionInterfaceState() {
        Log.d(TAG, "getTheLast50KMAveragePowerConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAveragePowerConsumptionInterfaceState();
    }

    public int getTirePressureInterfaceStatus() {
        Log.d(TAG, "getTirePressureInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTirePressureInterfaceStatus();
    }

    public int getTrailerModeConfig() {
        Log.d(TAG, " getTrailerModeConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getTrailerModeConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getTrayControlSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getTrayControlSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getTrayControlSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getTrunkConfigutarion() {
        Log.d(TAG, "getTrunkConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTrunkConfigutarion();
    }

    public int getTsrLitSpdChgSwitchState() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getTsrLitSpdChgSwitchState();
            }
            Log.d(TAG, "adapter is not connect");
            return 0;
        }
        Log.d(TAG, " getTsrLitSpdChgSwitchState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getUiStyle() {
        Log.d(TAG, "getUiStyle is execute UI_STYLE_PROP_KEY is persist.sys.ui_style");
        String str = SystemProperties.get(BYDAutoSysPropertiesDevice.UI_STYLE);
        Log.d(TAG, "getUiStyle uiStyle is " + str);
        if (str != null && str != "") {
            return Integer.parseInt(str);
        }
        Log.d(TAG, " uiStyle is null");
        return -1;
    }

    public int getUsherLightSupport() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getUsherLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getUsherLightSupport();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int getV2xConfig() {
        Log.d(TAG, "getV2xConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getV2xConfig();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getValetParking() {
        Log.d(TAG, "getValetParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getValetParking();
    }

    public int getWarningAuxiliarySwitchSetting() {
        Log.d(TAG, "getWarningAuxiliarySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWarningAuxiliarySwitchSetting();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getWelcomeCeremonySwitchSetting() {
        Log.d(TAG, "getWelcomeCeremonySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWelcomeCeremonySwitchSetting();
        }
        Log.d(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getWindowAntiPinchConfig() {
        if (1 == this.mForTest) {
            Log.d(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public boolean isDenza() {
        return getUiStyle() == 1;
    }

    public boolean isDynasty() {
        return getUiStyle() == 0;
    }

    public boolean isOcean() {
        return getUiStyle() == 2;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerConnectListener(BYDAutoStudyListener bYDAutoStudyListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe study listener " + bYDAutoStudyListener);
        if (bYDAutoStudyListener == null) {
            Log.d(TAG, "listener is null");
        } else {
            registerConnectListener(this.mContext, bYDAutoStudyListener);
        }
    }

    public int resetAmbientSettings(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "resetAmbientSettings mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetAmbientSettings(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "resetAutoPurificationFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetAutoPurificationFactoryMode(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "resetBackAndLeaveDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetBackAndLeaveDelaySupport(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int resetDayLight(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "resetDayLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetDayLight(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "resetDoorAndIndoor mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetDoorAndIndoor(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int resetHeadlamp(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "resetHeadlamp mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetHeadlamp(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    public int restoreFactorySettings() {
        Log.d(TAG, "restoreFactorySettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().restoreFactorySettings();
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        if (1 == this.mForTest) {
            Log.d(TAG, "setAcWarmFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().setAcWarmFactoryMode(i);
        }
        Log.d(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        return 0;
    }
}
