package android.hardware.bydauto.energy.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto_test.TestEngergyManagerImplCallback;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class EnergyManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_EnergyManagerImpl";
    private static final String TAG = "EnergyManagerImpl";
    private static volatile EnergyManagerImpl mEnergyManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TestEngergyManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoEnergyListener> mAbsBydAutoEnergyListeners = new CopyOnWriteArrayList<>();
    private boolean mAssistInit = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private EnergyManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                StringBuilder sbK = e.k("EnergyManagerImpl mForTestValue is");
                sbK.append(this.mForTest);
                sbK.append(" execute return");
                Log.v(TAG, sbK.toString());
                this.mTestCallback = new TestEngergyManagerImplCallback();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.energy.tsimpl.EnergyManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                Log.v(EnergyManagerImpl.TAG, "onBinderStateChanged" + z);
            }

            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (EnergyManagerImpl.this.mAbsBydAutoEnergyListeners.isEmpty()) {
                    return;
                }
                try {
                    EnergyManagerImpl.this.processCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(EnergyManagerImpl.TAG, "processCallback is error");
                }
            }
        });
    }

    public static EnergyManagerImpl getInstance(Context context) {
        if (mEnergyManagerImpl == null) {
            synchronized (EnergyManagerImpl.class) {
                if (mEnergyManagerImpl == null) {
                    mEnergyManagerImpl = new EnergyManagerImpl(context);
                }
            }
        }
        return mEnergyManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        int i3;
        int operationModeNormalR;
        e.t("carBodyPacket propertyId:", i, TAG);
        switch (i) {
            case EnergyHalId.ID_OPERATION_MODER /* 557847552 */:
                Iterator<AbsBYDAutoEnergyListener> it = this.mAbsBydAutoEnergyListeners.iterator();
                while (it.hasNext()) {
                    it.next().onOperationModeChanged(carBodyPacket.getmOperationMode());
                }
                i3 = 2448;
                operationModeNormalR = carBodyPacket.getmOperationMode();
                break;
            case 557847553:
                Iterator<AbsBYDAutoEnergyListener> it2 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onRoadSurfaceChanged(carBodyPacket.getmRoadSurfaceMode());
                }
                return;
            case EnergyHalId.ID_POWER_GENERATION_STATE /* 557847554 */:
                Iterator<AbsBYDAutoEnergyListener> it3 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onPowerGenerationStateChanged(carBodyPacket.getmPowerGenerationState());
                }
                return;
            case EnergyHalId.ID_POWER_GENERATION_VALUE /* 557847555 */:
                Iterator<AbsBYDAutoEnergyListener> it4 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onPowerGenerationValueChanged(carBodyPacket.getmPowerGenerationValue());
                }
                return;
            case EnergyHalId.ID_ENERGY_MODER /* 557847556 */:
                Iterator<AbsBYDAutoEnergyListener> it5 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onEnergyModeChanged(carBodyPacket.getmEnergyMode());
                }
                return;
            case EnergyHalId.ID_DRIVE_DISTANCE_SHOW_MODER /* 557847558 */:
                Iterator<AbsBYDAutoEnergyListener> it6 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onDriveDistanceShowModeChanged(carBodyPacket.getmDriveDistanceShowModeR());
                }
                return;
            case EnergyHalId.ID_EPEDAL_STATER /* 557847563 */:
                Iterator<AbsBYDAutoEnergyListener> it7 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onEPedalStateChanged(carBodyPacket.getmEPedalStateR());
                }
                return;
            case EnergyHalId.ID_ELECTRIC_SHOW_MODER /* 557847565 */:
                Iterator<AbsBYDAutoEnergyListener> it8 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onElectricShowModeChanged(carBodyPacket.getmElectricShowModeR());
                }
                return;
            case EnergyHalId.ID_MANDATORY_ELECTRIC_STATER /* 557847567 */:
                Iterator<AbsBYDAutoEnergyListener> it9 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onMandatoryElectricStateChanged(carBodyPacket.getmMandatoryElectricStateR());
                }
                return;
            case EnergyHalId.ID_DRIVE_DISTANCE_SHOW_STANDARDR /* 557847569 */:
                Iterator<AbsBYDAutoEnergyListener> it10 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onDriveDistanceShowStandardChanged(carBodyPacket.getmDriveDistanceShowStandardR());
                }
                return;
            case EnergyHalId.ID_RESET_CURVE_DATA /* 557847571 */:
                Iterator<AbsBYDAutoEnergyListener> it11 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onResetCurveDataChanged(0, carBodyPacket.getmResetCurveData());
                }
                return;
            case EnergyHalId.ID_MEMORY_SPORT_STATER /* 557847572 */:
                Iterator<AbsBYDAutoEnergyListener> it12 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onMemorySportStateChanged(carBodyPacket.getmMemorySportStateR());
                }
                return;
            case EnergyHalId.ID_MEMORY_MAX_EV_STATER /* 557847574 */:
                Iterator<AbsBYDAutoEnergyListener> it13 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onMemoryMaxEvStateChanged(carBodyPacket.getmMemoryMaxEvStateR());
                }
                return;
            case EnergyHalId.ID_MEMORY_ELECTRIC_STATER /* 557847576 */:
                Iterator<AbsBYDAutoEnergyListener> it14 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onMemoryElectricStateChanged(carBodyPacket.getmMemoryElectricStateR());
                }
                return;
            case EnergyHalId.ID_PRE_SALE_MODEL_STATER /* 557847578 */:
                Iterator<AbsBYDAutoEnergyListener> it15 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onPreSaleModelStateChanged(carBodyPacket.getmPreSaleModelStateR());
                }
                return;
            case EnergyHalId.ID_OIL_POINT_FLAG /* 557847580 */:
                Iterator<AbsBYDAutoEnergyListener> it16 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onOilPointFlagChanged(carBodyPacket.getOilPointFlag());
                }
                return;
            case EnergyHalId.ID_ELECTRIC_POINT_FLAG /* 557847581 */:
                Iterator<AbsBYDAutoEnergyListener> it17 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onElectricPointFlagChanged(carBodyPacket.getElectricPointFlag());
                }
                return;
            case EnergyHalId.ID_ENERGY_STATER /* 557847586 */:
                Iterator<AbsBYDAutoEnergyListener> it18 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onEnergyStateChanged(carBodyPacket.getEnergyStateR());
                }
                return;
            case EnergyHalId.ID_HEV_ELECTRIC_POINT_FLAG_R /* 557847587 */:
                Iterator<AbsBYDAutoEnergyListener> it19 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onHEVElectricPointFlagChanged(carBodyPacket.getHevElectricPointFlagR());
                }
                return;
            case EnergyHalId.ID_OPERATION_MODE_NORMAL_R /* 557847590 */:
                Iterator<AbsBYDAutoEnergyListener> it20 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onOperationModeNormalChanged(carBodyPacket.getOperationModeNormalR());
                }
                i3 = 2450;
                operationModeNormalR = carBodyPacket.getOperationModeNormalR();
                break;
            case EnergyHalId.ID_ROAD_SURFACE_MODE_NORMAL_R /* 557847592 */:
                Iterator<AbsBYDAutoEnergyListener> it21 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onRoadSurfaceModeNormalChanged(carBodyPacket.getRoadSurfaceModeNormalR());
                }
                i3 = 2454;
                operationModeNormalR = carBodyPacket.getRoadSurfaceModeNormalR();
                break;
            case EnergyHalId.ID_I_T_A_C_P_STATE_R /* 557847594 */:
                Iterator<AbsBYDAutoEnergyListener> it22 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().oniTACStateChanged(carBodyPacket.getiTacpStateR());
                }
                return;
            case EnergyHalId.ID_I_T_A_C_P_ALLOW_TAG_R /* 557847596 */:
                Iterator<AbsBYDAutoEnergyListener> it23 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().oniTACAllowTagChanged(carBodyPacket.getiTacpAllowTagR());
                }
                return;
            case EnergyHalId.ID_CHANGE_P_HINT_R /* 557847597 */:
                Iterator<AbsBYDAutoEnergyListener> it24 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onChangePHintChanged(carBodyPacket.getChangePHintR());
                }
                return;
            case EnergyHalId.ID_I_T_A_C_EXIT_TAG_R /* 557847598 */:
                Iterator<AbsBYDAutoEnergyListener> it25 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().oniTACExitTagChanged(carBodyPacket.getiTacExitTagR());
                }
                return;
            case EnergyHalId.ID_I_T_A_C_MODE_R /* 557847599 */:
                Iterator<AbsBYDAutoEnergyListener> it26 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().oniTACModeChanged(carBodyPacket.getiTacModeR());
                }
                return;
            case EnergyHalId.ID_CHARGE_PORT_COVER_TIP_R /* 557847601 */:
                Iterator<AbsBYDAutoEnergyListener> it27 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onChargePortCoverHintChanged(carBodyPacket.getChargePortCoverTipR());
                }
                return;
            case EnergyHalId.ID_LAST200_M_OIL_CON /* 559944734 */:
                Iterator<AbsBYDAutoEnergyListener> it28 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onLast200MOilConChanged(carBodyPacket.getLast200MOilCon());
                }
                return;
            case EnergyHalId.ID_LAST200_M_ELECTRIC_CON /* 559944735 */:
                Iterator<AbsBYDAutoEnergyListener> it29 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onLast200MElectricConChanged(carBodyPacket.getLast200MElectricCon());
                }
                return;
            case EnergyHalId.ID_LAST50_K_M_OIL_CON_AVG /* 559944736 */:
                Iterator<AbsBYDAutoEnergyListener> it30 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onLast50KMOilConAvgChanged(carBodyPacket.getLast50KmOilConAvg());
                }
                return;
            case EnergyHalId.ID_LAST50_K_M_ELECTRIC_CON_AVG /* 559944737 */:
                Iterator<AbsBYDAutoEnergyListener> it31 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onLast50KMElectricConAvgChanged(carBodyPacket.getLast50KmElectricConAvg());
                }
                return;
            case EnergyHalId.ID_HEV_LAST_200_M_ELECTRIC_CON_R /* 559944740 */:
                Iterator<AbsBYDAutoEnergyListener> it32 = this.mAbsBydAutoEnergyListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onHEVLast200MElectricConChanged(carBodyPacket.getHevLast200MElectricConR());
                }
                return;
            default:
                return;
        }
        caseFeatureIds(i3, Integer.valueOf(operationModeNormalR));
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        float last200mElectricCon;
        int i;
        int electricPointFlag;
        Object objValueOf;
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i2 : iArr) {
                if (i2 > 0) {
                    switch (i2) {
                        case 244:
                            last200mElectricCon = getLast200mElectricCon();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 244;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 245:
                            electricPointFlag = getElectricPointFlag();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 245;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 246:
                            electricPointFlag = getHevElectricPointFlag();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 246;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 247:
                            last200mElectricCon = getHevLast200MElectricCon();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 247;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 250:
                            electricPointFlag = getOilPointFlag();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 250;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 251:
                            last200mElectricCon = getLast200mOilCon();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 251;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 273:
                            electricPointFlag = getDriveDistanceShowMode();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 273;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 287:
                            last200mElectricCon = getLast50KmElectricConAvg();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 287;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 288:
                            last200mElectricCon = getLast50KmOilConAvg();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 288;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 289:
                            last200mElectricCon = getLast50KmOilConAvg();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 289;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 747:
                            electricPointFlag = getPreSaleModelState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 747;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1061:
                            electricPointFlag = getEPedalState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 1061;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1214:
                            electricPointFlag = getMemoryMaxEvState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 1214;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1225:
                            electricPointFlag = getDriveDistanceShowStandard();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 1225;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1341:
                            electricPointFlag = getMemoryElectricState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 1341;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 1360:
                            electricPointFlag = getMemorySportState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 1360;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2442:
                            electricPointFlag = getEnergyMode();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2442;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2443:
                            electricPointFlag = getEnergyMode();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2443;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2448:
                            electricPointFlag = getOperationMode();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2448;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2450:
                            electricPointFlag = getOperationModeNormal();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2450;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2451:
                            electricPointFlag = getPowerGenerationState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2451;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2454:
                            electricPointFlag = getRoadSurfaceModeNormal();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2454;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2455:
                            electricPointFlag = getRoadSurfaceMode();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2455;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2457:
                            electricPointFlag = getEnergyState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2457;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 2665:
                            electricPointFlag = getMandatoryElectricState();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 2665;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 3808:
                            last200mElectricCon = getLast50KmElectricConAvg();
                            bYDAutoEventValue.floatValue = last200mElectricCon;
                            i = 3808;
                            objValueOf = Float.valueOf(last200mElectricCon);
                            sparseArray.put(i, objValueOf);
                            break;
                        case 4069:
                            electricPointFlag = getElectricShowMode();
                            bYDAutoEventValue.intValue = electricPointFlag;
                            i = 4069;
                            objValueOf = Integer.valueOf(electricPointFlag);
                            sparseArray.put(i, objValueOf);
                            break;
                        default:
                            Log.v(TAG, "case fail ! please check out");
                            break;
                    }
                    int i3 = 0;
                    for (int i4 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i3] = ((Integer) sparseArray.get(i4)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i3++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getChangePHint() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getChangePHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getChangePHint();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getChargePortCoverTip() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getChargePortCoverTip mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getChargePortCoverTip();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDriveDistanceShowMode() {
        Log.v(TAG, "getDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getDriveDistanceShowMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDriveDistanceShowMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getDriveDistanceShowStandard() {
        Log.v(TAG, "getDriveDistanceShowStandard is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getDriveDistanceShowStandard mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getDriveDistanceShowStandard();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEPedalState() {
        Log.v(TAG, "getEPedalState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEPedalState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEPedalState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getElectricPointFlag() {
        Log.v(TAG, "getElectricPointFlag is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getElectricPointFlag mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getElectricPointFlag();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getElectricShowMode() {
        Log.v(TAG, "getElectricShowMode is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getElectricShowMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getElectricShowMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEnergyCurve() {
        Log.v(TAG, "getEnergyCurve is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyCurve mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyCurve();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float[] getEnergyElectricCurveX(int i, int i2) {
        Log.v(TAG, "getEnergyElectricCurveX is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyElectricCurveX mForTestValue is"), this.mForTest, " execute return", TAG);
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyElectricCurveX(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new float[0];
    }

    public float[] getEnergyElectricCurveY(int i, int i2) {
        Log.v(TAG, "getEnergyElectricCurveY is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyElectricCurveY mForTestValue is"), this.mForTest, " execute return", TAG);
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyElectricCurveY(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new float[0];
    }

    @Deprecated
    public int getEnergyFeedback() {
        Log.v(TAG, "getEnergyFeedback is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyFeedback mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyFeedback();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEnergyMode() {
        Log.v(TAG, "getEnergyMode is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float[] getEnergyOilCurveX(int i, int i2) {
        Log.v(TAG, "getEnergyOilCurveX is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyOilCurveX mForTestValue is"), this.mForTest, " execute return", TAG);
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyOilCurveX(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new float[0];
    }

    public float[] getEnergyOilCurveY(int i, int i2) {
        Log.v(TAG, "getEnergyOilCurveY is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyOilCurveY mForTestValue is"), this.mForTest, " execute return", TAG);
            return new float[0];
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyOilCurveY(i, i2);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return new float[0];
    }

    public int getEnergyState() {
        Log.v(TAG, "getEnergyState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getEnergyState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getHevElectricPointFlag() {
        Log.v(TAG, "getHevElectricPointFlag is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getHevElectricPointFlag mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getHevElectricPointFlag();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public float getHevLast200MElectricCon() {
        Log.v(TAG, "getHevLast200MElectricCon is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getHevLast200MElectricCon mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getHevLast200MElectricCon();
        }
        Log.e(TAG, "adapter is not connect");
        return 0.0f;
    }

    public float getLast200mElectricCon() {
        Log.v(TAG, "getLast200MElectricCon is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getLast200MElectricCon mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLast200mElectricCon();
        }
        Log.e(TAG, "adapter is not connect");
        return 0.0f;
    }

    public float getLast200mOilCon() {
        Log.v(TAG, "getLast200MOilCon is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getLast200MOilCon mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLast200mOilCon();
        }
        Log.e(TAG, "adapter is not connect");
        return 0.0f;
    }

    public float getLast50KmElectricConAvg() {
        Log.v(TAG, "getLast50KMElectricConAvg is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getLast50KMElectricConAvg mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLast50KmElectricConAvg();
        }
        Log.e(TAG, "adapter is not connect");
        return 0.0f;
    }

    public float getLast50KmOilConAvg() {
        Log.v(TAG, "getLast50KMOilConAvg is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getLast50KMOilConAvg mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0f;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLast50KmOilConAvg();
        }
        Log.e(TAG, "adapter is not connect");
        return 0.0f;
    }

    public int getMandatoryElectricState() {
        Log.v(TAG, "getMandatoryElectricState is execute");
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getCarBodyManager().getMandatoryElectricState();
        }
        e.s(e.k("getMandatoryElectricState mForTestValue is"), this.mForTest, " execute return", TAG);
        return 0;
    }

    public int getMemoryElectricState() {
        Log.v(TAG, "getMemoryElectricState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getMemoryElectricState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMemoryElectricState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMemoryMaxEvState() {
        Log.v(TAG, "getMemoryMaxEvState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getMemoryMaxEvState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMemoryMaxEvState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMemorySportState() {
        Log.v(TAG, "getMemorySportState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getMemorySportState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMemorySportState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getOilPointFlag() {
        Log.v(TAG, "getOilPointFlag is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getOilPointFlag mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getOilPointFlag();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getOperationMode() {
        Log.v(TAG, "getOperationMode is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getOperationMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getOperationMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getOperationModeNormal() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getOperationModeNormal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getOperationModeNormal();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getPowerGenerationState() {
        Log.v(TAG, "getPowerGenerationState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getPowerGenerationState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getPowerGenerationState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPowerGenerationValue() {
        Log.v(TAG, "getPowerGenerationValue is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getPowerGenerationValue mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getPowerGenerationValue();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPreSaleModelState() {
        Log.v(TAG, "getPreSaleModelState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getPreSaleModelState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getPreSaleModelState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRoadSurfaceMode() {
        Log.v(TAG, "getRoadSurfaceMode is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getRoadSurfaceMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRoadSurfaceMode();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRoadSurfaceModeNormal() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getRoadSurfaceModeNormal mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRoadSurfaceModeNormal();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getSocTarget() {
        Log.v(TAG, "getSocTarget is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getSocTarget mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSocTarget();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getiTacExitTag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacExitTag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getiTacExitTag();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getiTacMode() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getiTacMode();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getiTacpAllowTag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacpAllowTag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getiTacpAllowTag();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getiTacpState() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getiTacpState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getiTacpState();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void registerListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe energy listener " + absBYDAutoEnergyListener);
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoEnergyListener(absBYDAutoEnergyListener);
            StringBuilder sb = new StringBuilder();
            sb.append("registerListener mForTestValue is");
            e.s(sb, this.mForTest, " execute return", TAG);
            return;
        }
        if (absBYDAutoEnergyListener == null) {
            return;
        }
        if (!this.mAbsBydAutoEnergyListeners.contains(absBYDAutoEnergyListener)) {
            this.mAbsBydAutoEnergyListeners.add(absBYDAutoEnergyListener);
        }
        registerConnectListener(this.mContext, absBYDAutoEnergyListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public int resetCurveData(int i) {
        Log.v(TAG, "resetCurveData is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().resetCurveData(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(6011, i);
        StringBuilder sb = new StringBuilder();
        sb.append("resetCurveData mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setChargePortCoverHint(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setChargePortCoverHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setChargePortCoverHint(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setConfirmChargePortCover(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setConfirmChargePortCover mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setConfirmChargePortCover(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setDriveDistanceShowMode(int i) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_DRIVE_DISTANCE_SHOW_MODE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setDriveDistanceShowMode mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setDriveDistanceShowStandard(int i) {
        Log.v(TAG, "setDriveDistanceShowStandard is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowStandard(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_DRIVE_DISTANCE_SHOW_STANDARD, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setDriveDistanceShowStandard mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setEPedalState(int i) {
        Log.v(TAG, "setEPedalState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setEPedalState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(6000, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setEPedalState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setElectricShowMode(int i) {
        Log.v(TAG, "setElectricShowMode is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setElectricShowMode(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_ELECTRIC_SHOW_MODE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setElectricShowMode mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    @Deprecated
    public int setEnergyFeedback(int i) {
        Log.v(TAG, "setEnergyFeedback is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setEnergyFeedback(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_ENERGY_FEEDBACK, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setEnergyFeedback mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        String str2;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 274) {
            setDriveDistanceShowMode(iIntValue);
            return 1;
        }
        if (i == 1062) {
            setEPedalState(iIntValue);
            str2 = "Setting.SET_E_PEDAL_MODE_STATE_SET:";
        } else if (i == 1215) {
            setMemoryMaxEvState(iIntValue);
            str2 = "Setting.SET_MAX_EV_MEMORY_STATE_SET:";
        } else if (i == 1342) {
            setMemoryElectricState(iIntValue);
            str2 = "Setting.SET_SAVE_MEMORY_STATE_SET:";
        } else if (i == 1361) {
            setMemorySportState(iIntValue);
            str2 = "Setting.SET_SPORT_MEMORY_STATE_SET:";
        } else {
            if (i == 2449) {
                setOperationMode(iIntValue);
                return 1;
            }
            if (i == 2667) {
                setMandatoryElectricState(iIntValue);
                return 1;
            }
            if (i == 3914) {
                resetCurveData(iIntValue);
                str2 = "resetCurveData:" + iIntValue;
            } else {
                if (i != 4070) {
                    str = "case fail ! please check out";
                    Log.v(TAG, str);
                    return -1;
                }
                setElectricShowMode(iIntValue);
                str2 = "Instrument.INSTRUMENT_ENERGY_DISPLAY_SET:";
            }
        }
        Log.v(TAG, str2);
        return 1;
    }

    public int setMandatoryElectricState(int i) {
        Log.v(TAG, "setMandatoryElectricState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setMandatoryElectricState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MANDATORY_ELECTRIC_STATE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setMandatoryElectricState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setMemoryElectricState(int i) {
        Log.v(TAG, "setMemoryElectricState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setMemoryElectricState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_ELECTRIC_STATE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setMemoryElectricState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setMemoryMaxEvState(int i) {
        Log.v(TAG, "setMemoryMaxEvState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setMemoryMaxEvState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_MAX_EV_STATE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setMemoryMaxEvState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setMemorySportState(int i) {
        Log.v(TAG, "setMemorySportState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setMemorySportState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_MEMORY_SPORT_STATE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setMemorySportState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setOperationMode(int i) {
        String str;
        if (1 == this.mForTest) {
            str = " setOperationMode mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setOperationMode(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return -1;
    }

    public int setPreSaleModelState(int i) {
        Log.v(TAG, "setPreSaleModelState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setPreSaleModelState(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_PRE_SALE_MODEL_STATE, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setPreSaleModelState mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setSocTarget(int i) {
        Log.v(TAG, "setSocTarget is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getCarBodyManager() != null) {
                return this.mTsManagerImpl.getCarBodyManager().setSocTarget(i);
            }
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        this.mTestCallback.enqueueMessage(TestEngergyManagerImplCallback.SET_S_O_C_TARGET, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setSocTarget mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setiTacMode(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setiTacMode mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setiTacMode(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setiTacpState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setiTacpState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setiTacpState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoEnergyListener absBYDAutoEnergyListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoEnergyListener);
            StringBuilder sb = new StringBuilder();
            sb.append("unregisterListener mForTestValue is");
            e.u(sb, this.mForTest, " execute return", TAG);
            return;
        }
        if (absBYDAutoEnergyListener == null) {
            return;
        }
        this.mAbsBydAutoEnergyListeners.remove(absBYDAutoEnergyListener);
        unregisterConnectListener(absBYDAutoEnergyListener);
    }
}
