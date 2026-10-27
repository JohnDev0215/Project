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
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                Log.v(TAG, "UiStyleConfigManagerImpl mForTestValue is" + this.mForTest + " execute return");
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
    public int ambientFactoryMode(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "ambientFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().ambientFactoryMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "backAndLeaveDelayFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().backAndLeaveDelayFactoryMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int dayLightFactory(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "dayLightFactory mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().dayLightFactory(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    if (i7 == 557) {
                        int leftRearMassageChairConfigurationForECU = getLeftRearMassageChairConfigurationForECU();
                        bYDAutoEventValue.intValue = leftRearMassageChairConfigurationForECU;
                        sparseArray.put(557, Integer.valueOf(leftRearMassageChairConfigurationForECU));
                    } else if (i7 == 558) {
                        int leftRearMassageChairConfigurationForECU2 = getLeftRearMassageChairConfigurationForECU();
                        bYDAutoEventValue.intValue = leftRearMassageChairConfigurationForECU2;
                        sparseArray.put(558, Integer.valueOf(leftRearMassageChairConfigurationForECU2));
                    } else if (i7 == 566) {
                        int mainDriverSeatAdjustmentConfigurationByGCtrl = getMainDriverSeatAdjustmentConfigurationByGCtrl();
                        bYDAutoEventValue.intValue = mainDriverSeatAdjustmentConfigurationByGCtrl;
                        sparseArray.put(566, Integer.valueOf(mainDriverSeatAdjustmentConfigurationByGCtrl));
                    } else if (i7 == 567) {
                        int mainDriveElectricHeadrestAdjustmentByGCtrl = getMainDriveElectricHeadrestAdjustmentByGCtrl();
                        bYDAutoEventValue.intValue = mainDriveElectricHeadrestAdjustmentByGCtrl;
                        sparseArray.put(567, Integer.valueOf(mainDriveElectricHeadrestAdjustmentByGCtrl));
                    } else if (i7 == 1154) {
                        int ambientAreaSupport = getAmbientAreaSupport();
                        bYDAutoEventValue.intValue = ambientAreaSupport;
                        sparseArray.put(1154, Integer.valueOf(ambientAreaSupport));
                    } else if (i7 == 1155) {
                        int ambientLightColorSupport = mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
                        bYDAutoEventValue.intValue = ambientLightColorSupport;
                        sparseArray.put(1155, Integer.valueOf(ambientLightColorSupport));
                    } else if (i7 == 1158) {
                        int ambientLightSupport = getAmbientLightSupport();
                        bYDAutoEventValue.intValue = ambientLightSupport;
                        sparseArray.put(1158, Integer.valueOf(ambientLightSupport));
                    } else if (i7 == 1159) {
                        int ambientLightColorSupport2 = getAmbientLightColorSupport();
                        bYDAutoEventValue.intValue = ambientLightColorSupport2;
                        sparseArray.put(1159, Integer.valueOf(ambientLightColorSupport2));
                    } else if (i7 == 2788) {
                        int frontLightDimmingCtrlConfigBy3ac = getFrontLightDimmingCtrlConfigBy3ac();
                        bYDAutoEventValue.intValue = frontLightDimmingCtrlConfigBy3ac;
                        sparseArray.put(2788, Integer.valueOf(frontLightDimmingCtrlConfigBy3ac));
                    } else if (i7 == 2789) {
                        int supportAiMatrixLight = getSupportAiMatrixLight();
                        bYDAutoEventValue.intValue = supportAiMatrixLight;
                        sparseArray.put(2789, Integer.valueOf(supportAiMatrixLight));
                    } else if (i7 == 3173) {
                        int supportLaneDepartureStop = getSupportLaneDepartureStop();
                        bYDAutoEventValue.intValue = supportLaneDepartureStop;
                        sparseArray.put(3173, Integer.valueOf(supportLaneDepartureStop));
                    } else if (i7 != 3174) {
                        switch (i7) {
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
                                switch (i7) {
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
                                        switch (i7) {
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
                                                switch (i7) {
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
                                                        Log.v(TAG, "case fail ! please check out");
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
                    int i8 = 0;
                    for (int i9 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i8] = ((Integer) sparseArray.get(i9)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i8++;
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
            Log.v(TAG, "getACAutoAirSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACAutoAirSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getACBTWindSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACBTWindSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACBTWindSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getACPauseCycleSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACPauseCycleSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACPauseCycleSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getACTunnelCycleSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getACTunnelCycleSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getADASSystemState() {
        Log.v(TAG, "getADASSystemState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getADASSystemState();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcFaultNumShownState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcFaultNumShownState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAcType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcType();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAcWarmSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcWarmSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWarmSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcWarmTypeOnlineState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWarmTypeOnlineState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAcWindModeNum() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAcWindModeNum mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAcWindModeNum();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAdaptiveHeadlightsSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAdaptiveHeadlightsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAdaptiveHeadlightsSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAllRearSeatOneButtonDownSwitchDisplayConfig() {
        Log.v(TAG, "getAllRearSeatOneButtonDownSwitchDisplayConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAllRearSeatOneButtonDownSwitchDisplayConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getAmbientAreaSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientAreaSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientColorsSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientColorsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientColorsSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientLightColorSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientLightColorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientLightColorSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientLightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmbientModeSwitchSettings() {
        Log.v(TAG, "getAmbientModeSwitchSettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientModeSwitchSettings();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAmbientMusicModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAmbientMusicModeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAmplifierConfiguration() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getAmplifierConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getAmplifierConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getAppUseLimitSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAppUseLimitSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAppUseLimitSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoExternalRearMirrorFollowUpSwitchSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoExternalRearMirrorFollowUpSwitchSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoPowerOffSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOffSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoPowerOnStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPowerOnStateSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoPurificationSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoPurificationSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoPurificationSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoReportContentSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoReportContentSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoReportContentSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoWiperSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getAutoWiperSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getAutoWiperSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getAverageSpeedInterfaceStatus() {
        Log.v(TAG, "getAverageSpeedInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getAverageSpeedInterfaceStatus();
    }

    public int getBackHomeDelaySupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getBackHomeDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackHomeDelaySupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getBrakePedalModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getBrakePedalModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBrakePedalModeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getBsisSupportR() {
        Log.i(TAG, "getBsisSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "BsisSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getBsisSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCaliperSplintSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCaliperSplintSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCaliperSplintSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getCameraEerRomDeviceAddress() {
        Log.v(TAG, "getCameraEerRomDeviceAddress is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraEerRomDeviceAddress();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraIspId() {
        Log.v(TAG, "getCameraIspId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraIspId();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraLensId() {
        Log.v(TAG, "getCameraLensId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraLensId();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraSerializerId() {
        Log.v(TAG, "getCameraSerializerId is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraSerializerId();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraSupplierAndTestBoardInfo() {
        Log.v(TAG, "getCameraSupplierAndTestBoardInfo is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraSupplierAndTestBoardInfo();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoFormat() {
        Log.v(TAG, "getCameraVideoFormat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFormat();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoFrameRate() {
        Log.v(TAG, "getCameraVideoFrameRate is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoFrameRate();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCameraVideoResolution() {
        Log.v(TAG, "getCameraVideoResolution is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCameraVideoResolution();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getCarNum() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCarNum mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCarNum();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getCarPlanSupport() {
        Log.v(TAG, "getCarPlanSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCarPlanSupport();
    }

    public int getCarTipsSoundSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCarTipsSoundSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCarTipsSoundSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getCarType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCarType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCarType();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getChargeLimitSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getChargeLimitSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getChargeLimitSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getChargePreheatSupport() {
        Log.v(TAG, "getChargePreheatSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getChargePreheatSupport();
    }

    public int getChargeToaggleGuardSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getChargeToaggleGuardSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getChargeToaggleGuardSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getComfortableParkingBy37F() {
        Log.v(TAG, "getComfortableParkingBy37F is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingBy37F();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getComfortableParkingSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getComfortableParkingSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getComfortableParkingSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getComfortableSpeedDown() {
        Log.v(TAG, "getComfortableSpeedDown is execute");
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedDown();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getComfortableSpeedDown mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getComfortableSpeedUp() {
        Log.v(TAG, "getComfortableSpeedUp is execute");
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getComfortableSpeedUp();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getComfortableSpeedUp mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getCpdSupportR() {
        Log.i(TAG, "getCpdSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "CpdSupportR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCpdSupportR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getCumulativeAverageEnergyConsumptionInterfaceStatus() {
        Log.v(TAG, "getCumulativeAverageEnergyConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAverageEnergyConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getCumulativeAverageFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAverageFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAveragePowerConsumptionInterfaceStatus() {
        Log.v(TAG, "getCumulativeAveragePowerConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCumulativeAveragePowerConsumptionInterfaceStatus();
    }

    public int getCushionSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getCushionSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 1) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportDriver();
        }
        if (i7 == 2) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportPassenger();
        }
        if (i7 == 3) {
            return mTsManagerImpl.getStudyAdapterManager().getCushionSupportRearLeft();
        }
        if (i7 != 4) {
            return 0;
        }
        return mTsManagerImpl.getStudyAdapterManager().getCushionSupportRearRight();
    }

    public int getCvcConfig() {
        Log.v(TAG, "getCvcConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getCvcConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDMSAssembleStatus() {
        Log.v(TAG, "getDMSAssembleStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDMSAssembleStatus();
    }

    public int getDMType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDMType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDMType();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDayLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDayLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDayLightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDefrostRearConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDefrostRearConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDefrostRearConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDiSusShowStateByCan() {
        Log.v(TAG, "getDiSusShowStateByCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCan();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDiSusShowStateByCanFd() {
        Log.v(TAG, "getDiSusShowStateByCanFd is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDiSusShowStateByCanFd();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDigtalKeySupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDigtalKeySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDigtalKeySupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDischargeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDischargeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDischargeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDisinfSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDisinfSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDisinfSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorHeightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorHeightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorLightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDoorknobAutoShrinkSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDoorknobAutoShrinkSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveDistanceShowModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDistanceShowModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowModeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveDistanceShowStandardSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveDistanceShowStandardSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveDistanceShowStandardSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getDriveSeatAutoReturnSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriveSeatAutoReturnSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        Log.v(TAG, "getDriveTimeInterfaceStatusForSingleTrip is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getDriverSeatHotStoneMassageConfigurationByEcu() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByEcu is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByEcu();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getDriverSeatHotStoneMassageConfigurationByLeftCtrl() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByLeftCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByLeftCtrl();
    }

    public int getDriverSeatHotStoneMassageConfigurationByUniversalCtrl() {
        Log.v(TAG, "getDriverSeatHotStoneMassageConfigurationByUniversalCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDriverSeatHotStoneMassageConfigurationByUniversalCtrl();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        Log.v(TAG, "getDrivingRangeInterfaceStatusForFuel is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingRangeInterfaceStatusForFuel();
    }

    public int getDrivingVoiceBroadcastSwitchConfig() {
        Log.v(TAG, "getDrivingVoiceBroadcastSwitchConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getDrivingVoiceBroadcastSwitchConfig();
    }

    public int getDynamicSuspensionModeByCanFdR() {
        Log.i(TAG, "getDynamicSuspensionModeByCanFdR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "DynamicSuspensionModeByCanFdR mForTestValue is 0 execute return");
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
            Log.v(TAG, "DynamicSuspensionModeByCanR mForTestValue is 0 execute return");
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
            Log.v(TAG, "getDynamicSuspensionModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getDynamicSuspensionModeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getEPedalStateSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPedalStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getEPedalStateSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getElectricFrontHatchConfiguration() {
        Log.v(TAG, "getElectricFrontHatchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
    }

    public int getElectroniChildLockConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getElectroniChildLockConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getElectroniChildLockConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getElectronicParkingBrakeConfigutarion() {
        Log.v(TAG, "getElectronicParkingBrakeConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getElectronicParkingBrakeConfigutarion();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        Log.v(TAG, "getEnergyFlowDiagramInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getEnergyFlowDiagramInterfaceStatus();
    }

    @Deprecated
    public int getEnergyStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getEnergyStatus mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getEnergyStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getEngineSoundSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getEngineSoundSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getEngineSoundSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getExternalChargingPowerInterfaceStatus() {
        Log.v(TAG, "getExternalChargingPowerInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getExternalChargingPowerInterfaceStatus();
    }

    public int getFeaturesStateR() {
        Log.i(TAG, "getFeaturesStateR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "FeaturesStateR mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFeaturesStateR();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFoldDownTheSecondRowOfSeatsConfig() {
        Log.v(TAG, "getFoldDownTheSecondRowOfSeatsConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFoldDownTheSecondRowOfSeatsConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getFragranceInstallState(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragranceType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getFragranceInstallState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getFrontLightDimmingCtrlConfigBy3ac() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ac();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getFrontLightDimmingCtrlConfigBy3ac mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getFrontLightDimmingCtrlConfigBy3ad() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getFrontLightDimmingCtrlConfigBy3ad();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getFrontLightDimmingCtrlConfigBy3ad mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getFuelConsumptionInterfaceStatus();
    }

    public int getGuestLeftSeatRememeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getGuestLeftSeatRememeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getGuestLeftSeatRememeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getGuestRightSeatRememeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getGuestRightSeatRememeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getGuestRightSeatRememeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHUDModeConfiguration() {
        Log.v(TAG, "getHUDModeConfiguration is execute");
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
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getHasPredictCrashAlertMode mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getHeadlampLevelConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadlampLevelConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadlampLevelConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeadlampSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadlampSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadlampSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeadrestTurnHeightSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadrestTurnHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHeightSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeadrestTurnHorizatialSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHeadrestTurnHorizatialSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeadrestTurnHorizatialSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getHudHeightAdjustSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudHeightAdjustSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudHeightAdjustSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHudLightSdjustSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudLightSdjustSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudLightSdjustSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudParamsStateSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudParamsStateSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getHudShowParamsSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getHudShowParamsSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getHudShowParamsSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getIndoorLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIndoorLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIndoorLightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        Log.v(TAG, "getInstantaneousFuelConsumptionInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getInstrumentContentSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentContentSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentContentSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstrumentHeightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentHeightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getInstrumentThemeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getInstrumentThemeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getInstrumentThemeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getIntelligentAcAreaSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentAcAreaSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentAcAreaSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getIntelligentKeepTemSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentKeepTemSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentKeepTemSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentLockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentLockSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getIntelligentScreenCastingSwitchConfiguration() {
        Log.v(TAG, "getIntelligentScreenCastingSwitchConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getIntelligentScreenCastingSwitchConfiguration();
    }

    public int getIntelligentSpeedLimitMapVersionDisplay() {
        Log.v(TAG, "getIntelligentSpeedLimitMapVersionDisplay is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentSpeedLimitMapVersionDisplay();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getIntelligentUnlockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getIntelligentUnlockSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLandAdvanceAtSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLandAdvanceAtSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLandAdvanceAtSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeaveHomeDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLeaveHomeDelaySupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftPlatformConfigs() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLeftPlatformConfigs mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLeftPlatformConfigs();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftRESConfiguration() {
        Log.v(TAG, "getLeftRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRESConfiguration();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        Log.v(TAG, "getLeftRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMassageChairConfigurationForECU();
    }

    public int getLeftRearMemoryConfiguration() {
        Log.v(TAG, "getLeftRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearMemoryConfiguration();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        Log.v(TAG, "getLeftRearSeatBackAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatBackAdjustmentConfiguration();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        Log.v(TAG, "getLeftRearSeatHorizontalAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getLeftRearSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getLeftRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getLeftRearSeatLegBracket() {
        Log.v(TAG, "getLeftRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatLegBracket();
    }

    public int getLeftRearSeatReclineConfiguration() {
        Log.v(TAG, "getLeftRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfiguration();
    }

    public int getLeftRearSeatReclineConfigurationByECU() {
        Log.v(TAG, "getLeftRearSeatReclineConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getLeftRearSeatReclineConfigurationByECU();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLockCarRiseWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLockCarRiseWindowSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getLockOffSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getLockOffSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLockOffSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getLowSpeedEmergencyBrakingConfig() {
        Log.v(TAG, "getLowSpeedEmergencyBrakingConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getLowSpeedEmergencyBrakingConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        Log.v(TAG, "getMainDriveElectricHeadrestAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        Log.v(TAG, "getMainDriveElectricLegSupportAdjustmentByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        Log.v(TAG, "getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMainSeatRememberSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMainSeatRememberSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getMemoryParking() {
        Log.v(TAG, "getMemoryParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getMemoryParking();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMicroSwitchLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchLockWindowSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMicroSwitchUnlockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMicroSwitchUnlockWindowSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getMoisSupportR() {
        Log.i(TAG, "getMoisSupportR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "MoisSupportR mForTestValue is 0 execute return");
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
            Log.v(TAG, "getMoonRoofConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMoonRoofConfig();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getMotorPretensionerSeatbeltSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getMotorPretensionerSeatbeltSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getMotorPretensionerSeatbeltSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getNoSenseChargeSupport() {
        Log.v(TAG, "getNoSenseChargeSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getNoSenseChargeSupport();
    }

    public int getOBCType() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getOBCType mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOBCType();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() {
        Log.v(TAG, "getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() {
        Log.v(TAG, "getOneButtonDownConfigurationOfTheThirdRowRightRearSeat is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() {
        Log.v(TAG, "getOneButtonDownSwitchDisplayForTheThirdRowOfSeats is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getOverspeedLockSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getOverspeedLockSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getPM25PowerSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM25PowerSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25PowerSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM25SwitchCheckSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25SwitchCheckSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPM25TimeCkeckSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPM25TimeCkeckSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getPabSwitchState() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getPabSwitchState();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getPabSwitchState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getPanoramicSunroofConfigutarion() {
        Log.v(TAG, "getPanoramicSunroofConfigutarion is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPanoramicSunroofConfigutarion();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatElectricAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatElectricAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatHeadrestConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByGCtrl() {
        Log.v(TAG, "getPassengerSeatHeadrestConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHeadrestConfigurationByGCtrl();
    }

    public int getPassengerSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatHotStoneMassageConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl() {
        Log.v(TAG, "getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        Log.v(TAG, "getPassengerSeatMemoryFunctionCan is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCan();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        Log.v(TAG, "getPassengerSeatMemoryFunctionCanByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getPassengerSeatMemoryFunctionCanByECU();
    }

    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        Log.v(TAG, "getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        Log.v(TAG, "getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getPreSaleModelSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getPreSaleModelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getPreSaleModelSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRainAutoLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRainAutoLockWindowSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearAcMaxWindLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearAcMaxWindLevel();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBackrestAdjustmentConfigurationByECU();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftBidetAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearLeftLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        Log.v(TAG, "getRearLeftMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearLeftMemorySwitchConfigurationByECU();
    }

    public int getRearPanelLockTimeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearPanelLockTimeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearPanelLockTimeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightBackrestAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBackrestAdjustmentConfigurationByECU();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightBidetAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightBidetAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeadrestHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeadrestLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightHeightAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightLegSupportAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        Log.v(TAG, "getRearRightLevelAdjustmentConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        Log.v(TAG, "getRearRightMemorySwitchConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearRightMemorySwitchConfigurationByECU();
    }

    public int getRearSeatReclineConfiguration() {
        Log.v(TAG, "getRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRearSeatReclineConfiguration();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearViewMirrorFlipSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearViewMirrorFlipSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearviewMirrorSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRearviewMirrorSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRefrigeratorModeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRefrigeratorModeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRefrigeratorModeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRemindSpeedSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemindSpeedSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemindSpeedSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemoteControlLockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteControlLockWindowSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemoteControlUnlockWindowSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteControlUnlockWindowSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemoteKeyStartAcTimeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getRemoteKeyStartAcTimeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getReportContentSetSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getReportContentSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getReportContentSetSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getReportItemsSetSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getReportItemsSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getReportItemsSetSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getReportVolumeSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getReportVolumeSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getReportVolumeSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getRestModeConfiguration() {
        Log.v(TAG, "getRestModeConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRestModeConfiguration();
    }

    public int getRightRESConfiguration() {
        Log.v(TAG, "getRightRESConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRESConfiguration();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        Log.v(TAG, "getRightRearMassageChairConfigurationForECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMassageChairConfigurationForECU();
    }

    public int getRightRearMemoryConfiguration() {
        Log.v(TAG, "getRightRearMemoryConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearMemoryConfiguration();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        Log.v(TAG, "getRightRearSeatBackAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatBackAdjustmentConfiguration();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        Log.v(TAG, "getRightRearSeatHorizontalAdjustmentConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getRightRearSeatHotStoneMassageConfigurationByECU() {
        Log.v(TAG, "getRightRearSeatHotStoneMassageConfigurationByECU is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRightRearSeatLegBracket() {
        Log.v(TAG, "getRightRearSeatLegBracket is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatLegBracket();
    }

    public int getRightRearSeatReclineConfiguration() {
        Log.v(TAG, "getRightRearSeatReclineConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfiguration();
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        Log.v(TAG, "getRightRearSeatReclineConfigurationByEUC is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRightRearSeatReclineConfigurationByEUC();
    }

    public int getRuningCloseDoorSupport() {
        Log.v(TAG, "getRuningCloseDoorSupport is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getRuningCloseDoorSupport();
    }

    public int getSeatSetSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatSetSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatSetSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatTurnHeightSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatTurnHeightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHeightSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatTurnHorizatialSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatTurnHorizatialSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatTurnHorizatialSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSeatbackrestSupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSeatbackrestSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSeatbackrestSupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSecondSeatRememberSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSecondSeatRememberSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSecondSeatRememberSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSideDistanceWarningConfig() {
        Log.v(TAG, "getSideDistanceWarningConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSideDistanceWarningConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getStarTopSwitchSetting() {
        Log.v(TAG, "getStarTopSwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getStarTopSwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getSteerAssisSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteerAssisSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSteerAssisSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteerPositionAutoReturnSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSteerPositionAutoReturnSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSteeringWheelHeatingConfiguration() {
        Log.v(TAG, "getSteeringWheelHeatingConfiguration is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelHeatingConfiguration();
    }

    public int getSteeringWheelSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSteeringWheelSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSteeringWheelSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAFS() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAFS mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAFS();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAcAutoKeyCanClose() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAcAutoKeyCanClose mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAcAutoKeyCanClose();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiCare() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiCare mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiCare();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiDistanceLight() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiDistanceLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiDistanceLight();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiEmergencyBrake() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiEmergencyBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiEmergencyBrake();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAiMatrixLight() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAiMatrixLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAiMatrixLight();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAutoParking() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportAutoParking mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportAutoParking();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportAvh() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportAvh();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportAVH mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportBlindSpotAssist() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportBlindSpotAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssist();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportBlindSpotAssistBy418() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportBlindSpotAssistBy418 mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportBlindSpotAssistBy418();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportDigitalKey() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportDigitalKey mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDigitalKey();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportDimmingCanopyConfiguration() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDimmingCanopyConfiguration();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportDimmingCanopyConfiguration mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportDoorOpenWarning() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportDoorOpenWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportDoorOpenWarning();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportDoubleConfirm() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportDoubleConfirm();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportDoubleConfirm mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportESP() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportESP mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportESP();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportEmergencyLaneKeepAssist() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportEmergencyLaneKeepAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportEmergencyLaneKeepAssist();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportEndToEndNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportEndToEndNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportEndToEndNavigation();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportExtreme() {
        Log.v(TAG, "getSupportExtreme is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportExtreme();
    }

    public int getSupportFRElectricAirOutlet() {
        Log.v(TAG, "getSupportFRElectricAirOutlet is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportFRElectricAirOutlet();
    }

    public int getSupportFragrance() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragrance mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFragrance();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportFrontCrossingTrafficAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFrontCrossingTrafficAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficAlert();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportFrontCrossingTrafficBrake() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFrontCrossingTrafficBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportFrontCrossingTrafficBrake();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportHighTemperatureSterilize() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportHighTemperatureSterilize mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportHighTemperatureSterilize();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportHighwayNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportHighwayNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportHighwayNavigation();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntelligentNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntelligentNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigation();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntelligentNavigationBy432() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntelligentNavigationBy432 mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntelligentNavigationBy432();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntellignetSpeedLimitAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitAlert();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportIntellignetSpeedLimitControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportIntellignetSpeedLimitControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportIntellignetSpeedLimitControl();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLampSingnal() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLampSingnal mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLampSingnal();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneChangeAssist() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneChangeAssist mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneChangeAssist();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneDepartureStop() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneDepartureStop mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureStop();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneDepartureWarning() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneDepartureWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneDepartureWarning();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportLaneKeep() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportLaneKeep mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportLaneKeep();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportManualModelR() {
        Log.i(TAG, "getSupportManualModelR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportManualModelR mForTestValue is 0 execute return");
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
            Log.v(TAG, "getSupportMistakeGas mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportMistakeGas();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportNegativeIon() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportNegativeIon mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportNegativeIon();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportPM25Mode() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPM25Mode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPM25Mode();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportPanoramaImage() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPanoramaImage mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPanoramaImage();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportPredictCrashAlertMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportPredictCrashAlertMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportPredictCrashAlertMode();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAc() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAc mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAc();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcAutoControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcAutoControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcAutoControl();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcCompressor() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcCompressor mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcCompressor();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcKeyLock() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcKeyLock mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcKeyLock();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcSwitch() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcSwitch mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcSwitch();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcTempertureControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcTempertureControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcTempertureControl();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcWindLevelControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcWindLevelControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindLevelControl();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearAcWindModeControl() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearAcWindModeControl mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearAcWindModeControl();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearCollisionWarning() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearCollisionWarning mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearCollisionWarning();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearControlPanelSign() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportRearControlPanelSign();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportRearControlPanelSign mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportRearCrossBrake() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearCrossBrake mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearCrossBrake();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportRearTrafficCrossingAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRearTrafficCrossingAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRearTrafficCrossingAlert();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportRemenberPowerOff mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportRemenberPowerOff();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportReverseRadar() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportReverseRadar mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportReverseRadar();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int[] getSupportSeatAdjust(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSeatAdjust mForTestValue is 0 execute return");
            return new int[0];
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSeatAdjust(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return new int[0];
    }

    public int getSupportSeatMassage(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSeatMassage mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSeatMassage(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportShakeAlert() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportShakeAlert mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShakeAlert();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportShakeAlertR() {
        Log.i(TAG, "getSupportShakeAlertR is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportShakeAlertR mForTestValue is 0 execute return");
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
            Log.v(TAG, "getSupportShowCarModel mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportShowCarModel();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportSingleDoubleTempArea() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportSingleDoubleTempArea mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSingleDoubleTempArea();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportSmartChargingPHEV() {
        Log.v(TAG, "getSupportSmartChargingPHEV is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getSupportSmartChargingPHEV();
    }

    public int getSupportSmartChargingPhevPhase2R() {
        Log.i(TAG, "getSupportSmartChargingPhevPhase2R is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "SupportSmartChargingPhevPhase2R mForTestValue is 0 execute return");
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
            Log.v(TAG, "getSupportSteeringWheelWarm mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportSteeringWheelWarm();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTrafficLightAttention() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficLightAttention mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficLightAttention();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTrafficSignRecogniton() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficSignRecogniton mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignRecogniton();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTrafficSignsRecognition() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportTrafficSignsRecognition mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportTrafficSignsRecognition();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSupportTsrFlow() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getSupportTsrFlow();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getSupportTsrFlow mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getSupportUrbanNavigation() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportUrbanNavigation mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getSupportUrbanNavigation();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTailgateTypeMarking() {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getStudyAdapterManager() != null) {
                return mTsManagerImpl.getStudyAdapterManager().getTailgateTypeMarking();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTailgateTypeMarking mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTheLast50KMAverageEnergyConsumptionInterfaceState() {
        Log.v(TAG, "getTheLast50KMAverageEnergyConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAverageEnergyConsumptionInterfaceState();
    }

    public int getTheLast50KMAverageFuelConsumptionInterfaceState() {
        Log.v(TAG, "getTheLast50KMAverageFuelConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAverageFuelConsumptionInterfaceState();
    }

    public int getTheLast50KMAveragePowerConsumptionInterfaceState() {
        Log.v(TAG, "getTheLast50KMAveragePowerConsumptionInterfaceState is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTheLast50KMAveragePowerConsumptionInterfaceState();
    }

    public int getTirePressureInterfaceStatus() {
        Log.v(TAG, "getTirePressureInterfaceStatus is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getTirePressureInterfaceStatus();
    }

    public int getTrailerModeConfig() {
        Log.v(TAG, " getTrailerModeConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getTrailerModeConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    @Deprecated
    public int getTrayControlSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getTrayControlSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getTrayControlSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTrunkConfigutarion() {
        Log.v(TAG, "getTrunkConfigutarion is execute");
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
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTsrLitSpdChgSwitchState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getUiStyle() {
        Log.v(TAG, "getUiStyle is execute UI_STYLE_PROP_KEY is persist.sys.ui_style");
        String str = SystemProperties.get(BYDAutoSysPropertiesDevice.UI_STYLE);
        Log.v(TAG, "getUiStyle uiStyle is " + str);
        if (str != null && str != "") {
            return Integer.parseInt(str);
        }
        Log.v(TAG, " uiStyle is null");
        return -1;
    }

    public int getUsherLightSupport() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getUsherLightSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getUsherLightSupport();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getV2xConfig() {
        Log.v(TAG, "getV2xConfig is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getV2xConfig();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getValetParking() {
        Log.v(TAG, "getValetParking is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().getValetParking();
    }

    public int getWarningAuxiliarySwitchSetting() {
        Log.v(TAG, "getWarningAuxiliarySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWarningAuxiliarySwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getWelcomeCeremonySwitchSetting() {
        Log.v(TAG, "getWelcomeCeremonySwitchSetting is execute");
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWelcomeCeremonySwitchSetting();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return -1;
    }

    public int getWindowAntiPinchConfig() {
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().getWindowAntiPinchConfig();
        }
        Log.v(TAG, "adapter is not connect");
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
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerConnectListener(BYDAutoStudyListener bYDAutoStudyListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe study listener " + bYDAutoStudyListener);
        if (bYDAutoStudyListener == null) {
            Log.d(TAG, "listener is null");
        } else {
            registerConnectListener(this.mContext, bYDAutoStudyListener);
        }
    }

    public int resetAmbientSettings(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetAmbientSettings mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetAmbientSettings(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetAutoPurificationFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetAutoPurificationFactoryMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int resetBackAndLeaveDelaySupport(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetBackAndLeaveDelaySupport mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetBackAndLeaveDelaySupport(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int resetDayLight(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetDayLight mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetDayLight(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Deprecated
    public int resetDoorAndIndoor(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetDoorAndIndoor mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetDoorAndIndoor(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int resetHeadlamp(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "resetHeadlamp mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().resetHeadlamp(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int restoreFactorySettings() {
        Log.v(TAG, "restoreFactorySettings is execute");
        if (mTsManagerImpl.getStudyAdapterManager() == null) {
            return -1;
        }
        return mTsManagerImpl.getStudyAdapterManager().restoreFactorySettings();
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, "setAcWarmFactoryMode mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getStudyAdapterManager() != null) {
            return mTsManagerImpl.getStudyAdapterManager().setAcWarmFactoryMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i7, Object obj) {
        return 0;
    }
}
