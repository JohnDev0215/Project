package android.hardware.bydauto.whud.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.whud.AbsBYDAutoWhudListener;
import android.util.SparseArray;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class WhudManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_WhudManagerImpl";
    public static final String TAG = "WhudManagerImpl";
    private static final int TYRE_COMMAND_INVALID_VALUE = 0;
    private static volatile TsManagerImpl mTsManagerImpl;
    private static volatile WhudManagerImpl mWhudManagerImpl;
    private AbsBYDAutoWhudListener mAbsBydAutoWhudListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private final CopyOnWriteArrayList<AbsBYDAutoWhudListener> mAbsBYDAutoWhudListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private int mForTest = 0;

    private WhudManagerImpl(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.whud.tsimpl.WhudManagerImpl.1
            public void onCarClusterBrightnessChanged(int i) {
            }

            public void onCarGeneralBinderChanged(boolean z) {
                Log.v(WhudManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onCarGeneralChanged(int i, int i2, GeneralPacket generalPacket) {
                if (WhudManagerImpl.this.mAbsBYDAutoWhudListeners.isEmpty()) {
                    return;
                }
                try {
                    WhudManagerImpl.this.whudCallback(i, i2, generalPacket);
                } catch (Exception unused) {
                    Log.e(WhudManagerImpl.TAG, "whudCallback is error");
                }
            }
        });
    }

    public static WhudManagerImpl getInstance(Context context) {
        if (mWhudManagerImpl == null) {
            synchronized (WhudManagerImpl.class) {
                if (mWhudManagerImpl == null) {
                    mWhudManagerImpl = new WhudManagerImpl(context);
                }
            }
        }
        return mWhudManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void whudCallback(int i, int i2, GeneralPacket generalPacket) {
        if (generalPacket == null) {
            Log.e(TAG, "packet is null");
        }
        Log.v(TAG, "packet propertyId:" + i);
        if (i == 557875227) {
            Iterator<AbsBYDAutoWhudListener> it = this.mAbsBYDAutoWhudListeners.iterator();
            while (it.hasNext()) {
                it.next().onTextMessageHintChanged(generalPacket.getTextMessageHintR());
            }
            caseFeatureIds(4001, Integer.valueOf(generalPacket.getTextMessageHintR()));
            return;
        }
        switch (i) {
            case WhudHalId.ID_SHOW_ADAS_WORKR /* 557872384 */:
                Iterator<AbsBYDAutoWhudListener> it2 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onAdasWorkChanged(generalPacket.getShowAdasWorkR());
                }
                break;
            case WhudHalId.ID_VEHICLE_SPACER /* 557872385 */:
                Iterator<AbsBYDAutoWhudListener> it3 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onVehicleSpaceChanged(generalPacket.getVehicleSpaceR());
                }
                break;
            case WhudHalId.ID_AHEAD_VEHICLE_SPACER /* 557872386 */:
                Iterator<AbsBYDAutoWhudListener> it4 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onAheadVehicleSpaceChanged(generalPacket.getAheadVehicleSpaceR());
                }
                break;
            case WhudHalId.ID_TEXT_HINTR /* 557872387 */:
                Iterator<AbsBYDAutoWhudListener> it5 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onTextHintChanged(generalPacket.getTextHintR());
                }
                break;
            case WhudHalId.ID_VEHICLE_BODY_POSITIONR /* 557872388 */:
                Iterator<AbsBYDAutoWhudListener> it6 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onVehicleBodyPositionChanged(generalPacket.getVehicleBodyPositionR());
                }
                break;
            case WhudHalId.ID_LEFT_LINER /* 557872389 */:
                Iterator<AbsBYDAutoWhudListener> it7 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onLeftLineChanged(generalPacket.getLeftLineR());
                }
                break;
            case WhudHalId.ID_RIGHT_LINER /* 557872390 */:
                Iterator<AbsBYDAutoWhudListener> it8 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onRightLineChanged(generalPacket.getRightLineR());
                }
                break;
            case WhudHalId.ID_ACC_CRUISING_SPEED_VALUER /* 557872391 */:
                Iterator<AbsBYDAutoWhudListener> it9 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onAccCruisingSpeedValueChanged(generalPacket.getAccCruisingSpeedValueR());
                }
                break;
            case WhudHalId.ID_TRAFFIC_SIGN_RECOGNITION_VALUER /* 557872392 */:
                Iterator<AbsBYDAutoWhudListener> it10 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onTrafficSignRecognitionValueChanged(generalPacket.getTrafficSignRecognitionValueR());
                }
                break;
            case WhudHalId.ID_ACC_CRUISING_LAMPR /* 557872393 */:
                Iterator<AbsBYDAutoWhudListener> it11 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onAccCruisingLampChanged(generalPacket.getAccCruisingLampR());
                }
                break;
            case WhudHalId.ID_ACC_CRUISING_LAMP_COLORR /* 557872394 */:
                Iterator<AbsBYDAutoWhudListener> it12 = this.mAbsBYDAutoWhudListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onAccCruisingLampColorChanged(generalPacket.getAccCruisingLampColorR());
                }
                break;
        }
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
                    if (i == 3813) {
                        int leftLine = getLeftLine();
                        bYDAutoEventValue.intValue = leftLine;
                        sparseArray.put(3813, Integer.valueOf(leftLine));
                    } else if (i == 3926) {
                        int accCruisingSpeedValue = getAccCruisingSpeedValue();
                        bYDAutoEventValue.intValue = accCruisingSpeedValue;
                        sparseArray.put(3926, Integer.valueOf(accCruisingSpeedValue));
                    } else if (i == 4189) {
                        int trafficSignRecognitionValue = getTrafficSignRecognitionValue();
                        bYDAutoEventValue.intValue = trafficSignRecognitionValue;
                        sparseArray.put(4189, Integer.valueOf(trafficSignRecognitionValue));
                    } else if (i == 4526) {
                        int aheadVehicleSpace = getAheadVehicleSpace();
                        bYDAutoEventValue.intValue = aheadVehicleSpace;
                        sparseArray.put(4526, Integer.valueOf(aheadVehicleSpace));
                    } else if (i != 4527) {
                        switch (i) {
                            case 3694:
                                int textHint = getTextHint();
                                bYDAutoEventValue.intValue = textHint;
                                sparseArray.put(3694, Integer.valueOf(textHint));
                                break;
                            case 3695:
                                int vehicleSpace = getVehicleSpace();
                                bYDAutoEventValue.intValue = vehicleSpace;
                                sparseArray.put(3695, Integer.valueOf(vehicleSpace));
                                break;
                            case 3696:
                                int showAdasWork = getShowAdasWork();
                                bYDAutoEventValue.intValue = showAdasWork;
                                sparseArray.put(3696, Integer.valueOf(showAdasWork));
                                break;
                            case 3697:
                                int vehicleBodyPosition = getVehicleBodyPosition();
                                bYDAutoEventValue.intValue = vehicleBodyPosition;
                                sparseArray.put(3697, Integer.valueOf(vehicleBodyPosition));
                                break;
                        }
                    } else {
                        int rightLine = getRightLine();
                        bYDAutoEventValue.intValue = rightLine;
                        sparseArray.put(4527, Integer.valueOf(rightLine));
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

    public int getAccCruisingLamp() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getAccCruisingLamp();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAccCruisingLampColor() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getAccCruisingLampColor();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAccCruisingSpeedValue() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getAccCruisingSpeedValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAheadVehicleSpace() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getAheadVehicleSpace();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLeftLine() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getLeftLine();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getRightLine() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getRightLine();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getShowAdasWork() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getShowAdasWork();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTextHint() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getTextHint();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTextMessageHint() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getTextMessageHint();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTrafficSignRecognitionValue() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getTrafficSignRecognitionValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getVehicleBodyPosition() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getVehicleBodyPosition();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getVehicleSpace() {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().getVehicleSpace();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoWhudListener> it = this.mAbsBYDAutoWhudListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoWhudListener absBYDAutoWhudListener) {
        registerListener(absBYDAutoWhudListener, new int[0]);
    }

    public int setArrivalDayEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setArrivalDayEstimated(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setArrivalHourEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setArrivalHourEstimated(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setArrivalMinuteEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setArrivalMinuteEstimated(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setArrivalSecondEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setArrivalSecondEstimated(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallNumber(String str) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setCallNumber(str);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setCallState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallTimeHour(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setCallTimeHour(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallTimeMinute(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setCallTimeMinute(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCallTimeSecond(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setCallTimeSecond(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setCurrentDayUnitLen(String str) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getGeneralAdapterManager().setCurrentDayUnitLen(str);
        }
        Log.v(TAG, " setCurrentDayUnitLen mForTestValue is 0 execute return");
        return -1;
    }

    public int setCurrentHourUnitLen(String str) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getGeneralAdapterManager().setCurrentHourUnitLen(str);
        }
        Log.v(TAG, " setCurrentHourUnitLen mForTestValue is 0 execute return");
        return -1;
    }

    public int setCurrentMinUnitLen(String str) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getGeneralAdapterManager().setCurrentMinUnitLen(str);
        }
        Log.v(TAG, " setCurrentMinUnitLen mForTestValue is 0 execute return");
        return -1;
    }

    public int setDistanceAhead(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDistanceAhead(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDistanceAheadUnit(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDistanceAheadUnit(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDistanceFrontTarget(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDistanceFrontTarget(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDistanceFrontTargetUnit(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDistanceFrontTargetUnit(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingDays(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingDays(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingHour(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingHour(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingHourFormat(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingHourFormat(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingMileage(long j) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingMileage(j);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingMinute(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingMinute(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingSecond(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingSecond(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveRemainingUnit(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingUnit(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setEasyNavGuideInfo(int i, long j, int i2) {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getGeneralAdapterManager() != null) {
                return mTsManagerImpl.getGeneralAdapterManager().setEasyNavGuideInfo(new long[]{i, j, i2});
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setEasyNavGuideInfo mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    Log.v(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 1019) {
            setCallState(iIntValue);
            return 1;
        }
        if (i == 4123) {
            setMusicPlayProgress(iIntValue);
            return 1;
        }
        if (i == 4528) {
            setRadioPlayState(iIntValue);
            return 1;
        }
        if (i == 4125) {
            setMediaSource(iIntValue);
            return 1;
        }
        if (i == 4126) {
            setMusicPlayState(iIntValue);
            return 1;
        }
        switch (i) {
            case 3902:
                setCallTimeHour(iIntValue);
                return 1;
            case 3903:
                setCallTimeMinute(iIntValue);
                return 1;
            case 3904:
                setCallTimeSecond(iIntValue);
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }

    public int setMediaSource(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setMediaSource(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMusicCurrentName(String str) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setMusicCurrentName(str);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMusicPlayProgress(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setMusicPlayProgress(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMusicPlayState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setMusicPlayState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNameNextRoad(String str) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setNameNextRoad(str);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNavGuideInfo(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setNavGuideInfo(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNavGuideInfoAdvanced(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setNavGuideInfoAdvanced(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNaviState(int i) {
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getGeneralAdapterManager() != null) {
                return mTsManagerImpl.getGeneralAdapterManager().setNaviState(i);
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " setNaviState mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int setNaviType(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setNaviType(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setNextRoadDistanceUnitDataLen(String str) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getGeneralAdapterManager().setNextRoadDistanceUnitDataLen(str);
        }
        Log.v(TAG, " setNextRoadDistanceUnitDataLen mForTestValue is 0 execute return");
        return -1;
    }

    public int setNextRoadDistanceUnitDataLenV2(String str, String str2) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getGeneralAdapterManager().setNextRoadDistanceUnitDataLenV2(str, str2);
        }
        Log.v(TAG, " setNextRoadDistanceUnitDataLenV2 mForTestValue is 0 execute return");
        return -1;
    }

    public int setOverseasMapSupplier(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setOverseasMapSupplier(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRadioPlayState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return mTsManagerImpl.getGeneralAdapterManager().setRadioPlayState(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setScreenDisplayState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        }
        return 0;
    }

    public int setTargetDistanceUnitDataLen(String str) {
        if (1 != this.mForTest) {
            return mTsManagerImpl.getGeneralAdapterManager().setTargetDistanceUnitDataLen(str);
        }
        Log.v(TAG, " setTargetDistanceUnitDataLen mForTestValue is 0 execute return");
        return -1;
    }

    public void unregisterListener(AbsBYDAutoWhudListener absBYDAutoWhudListener) {
        if (absBYDAutoWhudListener == null) {
            return;
        }
        this.mAbsBYDAutoWhudListeners.remove(absBYDAutoWhudListener);
        unregisterConnectListener(absBYDAutoWhudListener);
    }

    public void registerListener(AbsBYDAutoWhudListener absBYDAutoWhudListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe whud listener " + absBYDAutoWhudListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoWhudListener == null) {
            return;
        }
        if (!this.mAbsBYDAutoWhudListeners.contains(absBYDAutoWhudListener)) {
            this.mAbsBYDAutoWhudListeners.add(absBYDAutoWhudListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoWhudListener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }
}
