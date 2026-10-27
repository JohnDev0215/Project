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

/* JADX INFO: loaded from: classes2.dex */
public class WhudManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final int S_INVALID = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_WhudManagerImpl";
    protected static final String TAG = "WhudManagerImpl";
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

            public void onCarGeneralBinderChanged(boolean z) {
                Log.v(WhudManagerImpl.TAG, "onBinderStateChanged is " + z);
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

    public void registerListener(AbsBYDAutoWhudListener absBYDAutoWhudListener) {
        registerListener(absBYDAutoWhudListener, new int[0]);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoWhudListener> it = this.mAbsBYDAutoWhudListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void unregisterListener(AbsBYDAutoWhudListener absBYDAutoWhudListener) {
        if (absBYDAutoWhudListener == null) {
            return;
        }
        this.mAbsBYDAutoWhudListeners.remove(absBYDAutoWhudListener);
        unregisterConnectListener(absBYDAutoWhudListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void whudCallback(int i, int i2, GeneralPacket generalPacket) {
        if (generalPacket == null) {
            Log.e(TAG, "packet is null");
            return;
        }
        Log.v(TAG, "packet propertyId:" + i);
        if (i != 557875227) {
            switch (i) {
                case WhudHalId.ID_SHOW_ADAS_WORKR /* 557872384 */:
                    Iterator<AbsBYDAutoWhudListener> it = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it.hasNext()) {
                        it.next().onAdasWorkChanged(generalPacket.getShowAdasWorkR());
                    }
                    break;
                case WhudHalId.ID_VEHICLE_SPACER /* 557872385 */:
                    Iterator<AbsBYDAutoWhudListener> it2 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onVehicleSpaceChanged(generalPacket.getVehicleSpaceR());
                    }
                    break;
                case WhudHalId.ID_AHEAD_VEHICLE_SPACER /* 557872386 */:
                    Iterator<AbsBYDAutoWhudListener> it3 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onAheadVehicleSpaceChanged(generalPacket.getAheadVehicleSpaceR());
                    }
                    break;
                case WhudHalId.ID_TEXT_HINTR /* 557872387 */:
                    Iterator<AbsBYDAutoWhudListener> it4 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onTextHintChanged(generalPacket.getTextHintR());
                    }
                    break;
                case WhudHalId.ID_VEHICLE_BODY_POSITIONR /* 557872388 */:
                    Iterator<AbsBYDAutoWhudListener> it5 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onVehicleBodyPositionChanged(generalPacket.getVehicleBodyPositionR());
                    }
                    break;
                case WhudHalId.ID_LEFT_LINER /* 557872389 */:
                    Iterator<AbsBYDAutoWhudListener> it6 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onLeftLineChanged(generalPacket.getLeftLineR());
                    }
                    break;
                case WhudHalId.ID_RIGHT_LINER /* 557872390 */:
                    Iterator<AbsBYDAutoWhudListener> it7 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it7.hasNext()) {
                        it7.next().onRightLineChanged(generalPacket.getRightLineR());
                    }
                    break;
                case WhudHalId.ID_ACC_CRUISING_SPEED_VALUER /* 557872391 */:
                    Iterator<AbsBYDAutoWhudListener> it8 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it8.hasNext()) {
                        it8.next().onAccCruisingSpeedValueChanged(generalPacket.getAccCruisingSpeedValueR());
                    }
                    break;
                case WhudHalId.ID_TRAFFIC_SIGN_RECOGNITION_VALUER /* 557872392 */:
                    Iterator<AbsBYDAutoWhudListener> it9 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it9.hasNext()) {
                        it9.next().onTrafficSignRecognitionValueChanged(generalPacket.getTrafficSignRecognitionValueR());
                    }
                    break;
                case WhudHalId.ID_ACC_CRUISING_LAMPR /* 557872393 */:
                    Iterator<AbsBYDAutoWhudListener> it10 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it10.hasNext()) {
                        it10.next().onAccCruisingLampChanged(generalPacket.getAccCruisingLampR());
                    }
                    break;
                case WhudHalId.ID_ACC_CRUISING_LAMP_COLORR /* 557872394 */:
                    Iterator<AbsBYDAutoWhudListener> it11 = this.mAbsBYDAutoWhudListeners.iterator();
                    while (it11.hasNext()) {
                        it11.next().onAccCruisingLampColorChanged(generalPacket.getAccCruisingLampColorR());
                    }
                    break;
            }
            return;
        }
        Iterator<AbsBYDAutoWhudListener> it12 = this.mAbsBYDAutoWhudListeners.iterator();
        while (it12.hasNext()) {
            it12.next().onTextMessageHintChanged(generalPacket.getTextMessageHintR());
        }
        caseFeatureIds(4001, Integer.valueOf(generalPacket.getTextMessageHintR()));
    }

    public int setNavGuideInfo(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNavGuideInfo(i);
    }

    public int setDistanceAhead(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceAhead(i);
    }

    public int setDistanceAheadUnit(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceAheadUnit(i);
    }

    public int setDriveRemainingHour(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingHour(i);
    }

    public int setDriveRemainingMinute(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingMinute(i);
    }

    public int setDriveRemainingSecond(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingSecond(i);
    }

    public int setDriveRemainingDays(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingDays(i);
    }

    public int setDriveRemainingMileage(long j) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingMileage(j);
    }

    public int setDriveRemainingUnit(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingUnit(i);
    }

    public int setDriveRemainingHourFormat(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingHourFormat(i);
    }

    public int setNavGuideInfoAdvanced(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNavGuideInfoAdvanced(i);
    }

    public int setDistanceFrontTarget(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceFrontTarget(i);
    }

    public int setDistanceFrontTargetUnit(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceFrontTargetUnit(i);
    }

    public int setArrivalDayEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalDayEstimated(i);
    }

    public int setArrivalHourEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalHourEstimated(i);
    }

    public int setArrivalMinuteEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalMinuteEstimated(i);
    }

    public int setArrivalSecondEstimated(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalSecondEstimated(i);
    }

    public int setNameNextRoad(String str) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNameNextRoad(str);
    }

    public int setNaviType(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNaviType(i);
    }

    public int getShowAdasWork() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getShowAdasWork();
    }

    public int getVehicleSpace() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getVehicleSpace();
    }

    public int getAheadVehicleSpace() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getAheadVehicleSpace();
    }

    public int getTextHint() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getTextHint();
    }

    public int getVehicleBodyPosition() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getVehicleBodyPosition();
    }

    public int getLeftLine() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getLeftLine();
    }

    public int getRightLine() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getRightLine();
    }

    public int getTextMessageHint() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getTextMessageHint();
    }

    public int getAccCruisingSpeedValue() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getAccCruisingSpeedValue();
    }

    public int getTrafficSignRecognitionValue() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getTrafficSignRecognitionValue();
    }

    public int getAccCruisingLamp() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getAccCruisingLamp();
    }

    public int getAccCruisingLampColor() {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().getAccCruisingLampColor();
    }

    public int setMusicPlayState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMusicPlayState(i);
    }

    public int setRadioPlayState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setRadioPlayState(i);
    }

    public int setMusicPlayProgress(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMusicPlayProgress(i);
    }

    public int setCallTimeHour(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallTimeHour(i);
    }

    public int setCallTimeMinute(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallTimeMinute(i);
    }

    public int setCallTimeSecond(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallTimeSecond(i);
    }

    public int setMediaSource(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMediaSource(i);
    }

    public int setOverseasMapSupplier(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setOverseasMapSupplier(i);
    }

    public int setCallState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallState(i);
    }

    public int setScreenDisplayState(int i) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
        }
        return 0;
    }

    public int setMusicCurrentName(String str) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMusicCurrentName(str);
    }

    public int setCallNumber(String str) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallNumber(str);
    }

    public int setNaviState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setNaviState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNaviState(i);
    }

    public int setEasyNavGuideInfo(int i, long j, int i2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setEasyNavGuideInfo mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setEasyNavGuideInfo(new long[]{i, j, i2});
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
                        bYDAutoEventValue.intValue = getLeftLine();
                        sparseArray.put(3813, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3926) {
                        bYDAutoEventValue.intValue = getAccCruisingSpeedValue();
                        sparseArray.put(3926, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 4189) {
                        bYDAutoEventValue.intValue = getTrafficSignRecognitionValue();
                        sparseArray.put(4189, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 4526) {
                        bYDAutoEventValue.intValue = getAheadVehicleSpace();
                        sparseArray.put(4526, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i != 4527) {
                        switch (i) {
                            case 3694:
                                bYDAutoEventValue.intValue = getTextHint();
                                sparseArray.put(3694, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3695:
                                bYDAutoEventValue.intValue = getVehicleSpace();
                                sparseArray.put(3695, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3696:
                                bYDAutoEventValue.intValue = getShowAdasWork();
                                sparseArray.put(3696, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 3697:
                                bYDAutoEventValue.intValue = getVehicleBodyPosition();
                                sparseArray.put(3697, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                        }
                    } else {
                        bYDAutoEventValue.intValue = getRightLine();
                        sparseArray.put(4527, Integer.valueOf(bYDAutoEventValue.intValue));
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
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
        if (i != 4126) {
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
        setMusicPlayState(iIntValue);
        return 1;
    }

    public int setCurrentDayUnitLen(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCurrentDayUnitLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCurrentDayUnitLen(str);
    }

    public int setCurrentHourUnitLen(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCurrentHourUnitLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCurrentHourUnitLen(str);
    }

    public int setCurrentMinUnitLen(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCurrentMinUnitLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCurrentMinUnitLen(str);
    }

    public int setTargetDistanceUnitDataLen(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setTargetDistanceUnitDataLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setTargetDistanceUnitDataLen(str);
    }

    public int setNextRoadDistanceUnitDataLen(String str) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setNextRoadDistanceUnitDataLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNextRoadDistanceUnitDataLen(str);
    }

    public int setNextRoadDistanceUnitDataLenV2(String str, String str2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setNextRoadDistanceUnitDataLenV2 mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNextRoadDistanceUnitDataLenV2(str, str2);
    }
}
