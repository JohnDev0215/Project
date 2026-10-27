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
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
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

    private WhudManagerImpl(Context con) {
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
        mTsManagerImpl = TsManagerImpl.getInstance(con);
        mTsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.whud.tsimpl.WhudManagerImpl.1
            public void onCarGeneralChanged(int id, int areaId, GeneralPacket packet) {
                if (!WhudManagerImpl.this.mAbsBYDAutoWhudListeners.isEmpty()) {
                    try {
                        WhudManagerImpl.this.whudCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(WhudManagerImpl.TAG, "whudCallback is error");
                    }
                }
            }

            public void onCarGeneralBinderChanged(boolean binderState) {
                Log.v(WhudManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }

            public void onCarClusterBrightnessChanged(int brightness) {
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

    public void registerListener(AbsBYDAutoWhudListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoWhudListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe whud listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (!this.mAbsBYDAutoWhudListeners.contains(listener)) {
            this.mAbsBYDAutoWhudListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoWhudListener listener : this.mAbsBYDAutoWhudListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    public void unregisterListener(AbsBYDAutoWhudListener listener) {
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoWhudListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void whudCallback(int id, int areaId, GeneralPacket packet) {
        if (packet == null) {
            Log.e(TAG, "packet is null");
            return;
        }
        Log.v(TAG, "packet propertyId:" + id);
        if (id != 557875227) {
            switch (id) {
                case 557872384:
                    for (AbsBYDAutoWhudListener listener : this.mAbsBYDAutoWhudListeners) {
                        listener.onAdasWorkChanged(packet.getShowAdasWorkR());
                    }
                    break;
                case 557872385:
                    for (AbsBYDAutoWhudListener listener2 : this.mAbsBYDAutoWhudListeners) {
                        listener2.onVehicleSpaceChanged(packet.getVehicleSpaceR());
                    }
                    break;
                case 557872386:
                    for (AbsBYDAutoWhudListener listener3 : this.mAbsBYDAutoWhudListeners) {
                        listener3.onAheadVehicleSpaceChanged(packet.getAheadVehicleSpaceR());
                    }
                    break;
                case 557872387:
                    for (AbsBYDAutoWhudListener listener4 : this.mAbsBYDAutoWhudListeners) {
                        listener4.onTextHintChanged(packet.getTextHintR());
                    }
                    break;
                case 557872388:
                    for (AbsBYDAutoWhudListener listener5 : this.mAbsBYDAutoWhudListeners) {
                        listener5.onVehicleBodyPositionChanged(packet.getVehicleBodyPositionR());
                    }
                    break;
                case 557872389:
                    for (AbsBYDAutoWhudListener listener6 : this.mAbsBYDAutoWhudListeners) {
                        listener6.onLeftLineChanged(packet.getLeftLineR());
                    }
                    break;
                case 557872390:
                    for (AbsBYDAutoWhudListener listener7 : this.mAbsBYDAutoWhudListeners) {
                        listener7.onRightLineChanged(packet.getRightLineR());
                    }
                    break;
                case 557872391:
                    for (AbsBYDAutoWhudListener listener8 : this.mAbsBYDAutoWhudListeners) {
                        listener8.onAccCruisingSpeedValueChanged(packet.getAccCruisingSpeedValueR());
                    }
                    break;
                case 557872392:
                    for (AbsBYDAutoWhudListener listener9 : this.mAbsBYDAutoWhudListeners) {
                        listener9.onTrafficSignRecognitionValueChanged(packet.getTrafficSignRecognitionValueR());
                    }
                    break;
                case 557872393:
                    for (AbsBYDAutoWhudListener listener10 : this.mAbsBYDAutoWhudListeners) {
                        listener10.onAccCruisingLampChanged(packet.getAccCruisingLampR());
                    }
                    break;
                case 557872394:
                    for (AbsBYDAutoWhudListener listener11 : this.mAbsBYDAutoWhudListeners) {
                        listener11.onAccCruisingLampColorChanged(packet.getAccCruisingLampColorR());
                    }
                    break;
            }
            return;
        }
        for (AbsBYDAutoWhudListener listener12 : this.mAbsBYDAutoWhudListeners) {
            listener12.onTextMessageHintChanged(packet.getTextMessageHintR());
        }
        caseFeatureIds(4001, Integer.valueOf(packet.getTextMessageHintR()));
    }

    public int setNavGuideInfo(int type) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNavGuideInfo(type);
    }

    public int setDistanceAhead(int value) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceAhead(value);
    }

    public int setDistanceAheadUnit(int unit) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceAheadUnit(unit);
    }

    public int setDriveRemainingHour(int hour) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingHour(hour);
    }

    public int setDriveRemainingMinute(int minute) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingMinute(minute);
    }

    public int setDriveRemainingSecond(int second) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingSecond(second);
    }

    public int setDriveRemainingDays(int days) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingDays(days);
    }

    public int setDriveRemainingMileage(long value) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingMileage(value);
    }

    public int setDriveRemainingUnit(int unit) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingUnit(unit);
    }

    public int setDriveRemainingHourFormat(int hourFormat) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDriveRemainingHourFormat(hourFormat);
    }

    public int setNavGuideInfoAdvanced(int type) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNavGuideInfoAdvanced(type);
    }

    public int setDistanceFrontTarget(int value) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceFrontTarget(value);
    }

    public int setDistanceFrontTargetUnit(int unit) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setDistanceFrontTargetUnit(unit);
    }

    public int setArrivalDayEstimated(int day) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalDayEstimated(day);
    }

    public int setArrivalHourEstimated(int hour) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalHourEstimated(hour);
    }

    public int setArrivalMinuteEstimated(int minute) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalMinuteEstimated(minute);
    }

    public int setArrivalSecondEstimated(int second) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setArrivalSecondEstimated(second);
    }

    public int setNameNextRoad(String name) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNameNextRoad(name);
    }

    public int setNaviType(int type) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNaviType(type);
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

    public int setMusicPlayState(int state) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMusicPlayState(state);
    }

    public int setRadioPlayState(int state) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setRadioPlayState(state);
    }

    public int setMusicPlayProgress(int value) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMusicPlayProgress(value);
    }

    public int setCallTimeHour(int hour) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallTimeHour(hour);
    }

    public int setCallTimeMinute(int minute) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallTimeMinute(minute);
    }

    public int setCallTimeSecond(int second) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallTimeSecond(second);
    }

    public int setMediaSource(int type) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMediaSource(type);
    }

    public int setOverseasMapSupplier(int state) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setOverseasMapSupplier(state);
    }

    public int setCallState(int state) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallState(state);
    }

    public int setScreenDisplayState(int state) {
        if (mTsManagerImpl.getGeneralAdapterManager() != null) {
            return 0;
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setMusicCurrentName(String name) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setMusicCurrentName(name);
    }

    public int setCallNumber(String number) {
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCallNumber(number);
    }

    public int setNaviState(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setNaviState mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNaviState(state);
    }

    public int setEasyNavGuideInfo(int type, long distance, int unit) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setEasyNavGuideInfo mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        long[] arr = {type, distance, unit};
        return mTsManagerImpl.getGeneralAdapterManager().setEasyNavGuideInfo(arr);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    if (eventType == 3813) {
                        bydAutoEventValue.intValue = getLeftLine();
                        sparseArray.put(3813, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3926) {
                        bydAutoEventValue.intValue = getAccCruisingSpeedValue();
                        sparseArray.put(3926, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 4189) {
                        bydAutoEventValue.intValue = getTrafficSignRecognitionValue();
                        sparseArray.put(4189, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 4526) {
                        bydAutoEventValue.intValue = getAheadVehicleSpace();
                        sparseArray.put(4526, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 4527) {
                        switch (eventType) {
                            case 3694:
                                bydAutoEventValue.intValue = getTextHint();
                                sparseArray.put(3694, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3695:
                                bydAutoEventValue.intValue = getVehicleSpace();
                                sparseArray.put(3695, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3696:
                                bydAutoEventValue.intValue = getShowAdasWork();
                                sparseArray.put(3696, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 3697:
                                bydAutoEventValue.intValue = getVehicleBodyPosition();
                                sparseArray.put(3697, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                        }
                    } else {
                        bydAutoEventValue.intValue = getRightLine();
                        sparseArray.put(4527, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 1019) {
            setCallState(intValue);
            return 1;
        }
        if (eventType == 4123) {
            setMusicPlayProgress(intValue);
            return 1;
        }
        if (eventType == 4528) {
            setRadioPlayState(intValue);
            return 1;
        }
        if (eventType == 4125) {
            setMediaSource(intValue);
            return 1;
        }
        if (eventType != 4126) {
            switch (eventType) {
                case 3902:
                    setCallTimeHour(intValue);
                    return 1;
                case 3903:
                    setCallTimeMinute(intValue);
                    return 1;
                case 3904:
                    setCallTimeSecond(intValue);
                    return 1;
                default:
                    Log.v(TAG, "case fail ! please check out");
                    return -1;
            }
        }
        setMusicPlayState(intValue);
        return 1;
    }

    public int setCurrentDayUnitLen(String dayUnit) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCurrentDayUnitLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCurrentDayUnitLen(dayUnit);
    }

    public int setCurrentHourUnitLen(String dayUnit) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCurrentHourUnitLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCurrentHourUnitLen(dayUnit);
    }

    public int setCurrentMinUnitLen(String dayUnit) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setCurrentMinUnitLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setCurrentMinUnitLen(dayUnit);
    }

    public int setTargetDistanceUnitDataLen(String date) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setTargetDistanceUnitDataLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setTargetDistanceUnitDataLen(date);
    }

    public int setNextRoadDistanceUnitDataLen(String date) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setNextRoadDistanceUnitDataLen mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNextRoadDistanceUnitDataLen(date);
    }

    public int setNextRoadDistanceUnitDataLenV2(String date, String unit) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setNextRoadDistanceUnitDataLenV2 mForTestValue is 0 execute return");
            return -1;
        }
        return mTsManagerImpl.getGeneralAdapterManager().setNextRoadDistanceUnitDataLenV2(date, unit);
    }
}
