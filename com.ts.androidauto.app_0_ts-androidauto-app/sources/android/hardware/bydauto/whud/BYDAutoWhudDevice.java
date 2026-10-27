package android.hardware.bydauto.whud;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.whud.tsimpl.WhudManagerImpl;
import android.hardware.bydauto.whud.tsimpl.WhudSettingManagerImpl;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoWhudDevice extends AbsBYDAutoDevice {
    public static final int CALL_STATE_CALL_IGNORED = 4;
    public static final int CALL_STATE_HANG_UP = 5;
    public static final int CALL_STATE_INCOMING_CALL = 2;
    public static final int CALL_STATE_IN_CALL = 3;
    public static final int CALL_STATE_MAKE_CALL = 1;
    public static final int DAY_TYPE_AFTER_TOMORROW = 3;
    public static final int DAY_TYPE_FRIDAY = 8;
    public static final int DAY_TYPE_INVALID = 0;
    public static final int DAY_TYPE_MONDAY = 4;
    public static final int DAY_TYPE_SATURDAY = 9;
    public static final int DAY_TYPE_SUNDAY = 10;
    public static final int DAY_TYPE_THURSDAY = 7;
    public static final int DAY_TYPE_TODAY = 1;
    public static final int DAY_TYPE_TOMORROW = 2;
    public static final int DAY_TYPE_TUESDAY = 5;
    public static final int DAY_TYPE_WEDNESDAY = 6;
    public static final int HOUR_FORMAT_12 = 1;
    public static final int HOUR_FORMAT_24 = 2;
    public static final int HOUR_FORMAT_INVALID = 0;
    public static final int LINE_TYPE_GRAY = 1;
    public static final int LINE_TYPE_GREEN = 2;
    public static final int LINE_TYPE_RED = 4;
    public static final int LINE_TYPE_YELLOW = 3;
    public static final int MEDIA_TYPE_AUX = 5;
    public static final int MEDIA_TYPE_CAR_K_SONG = 15;
    public static final int MEDIA_TYPE_CLOUD_LISTENING = 14;
    public static final int MEDIA_TYPE_COOL_ME = 9;
    public static final int MEDIA_TYPE_FM = 7;
    public static final int MEDIA_TYPE_HIMALAYAS = 12;
    public static final int MEDIA_TYPE_KOALA = 10;
    public static final int MEDIA_TYPE_KUGOU = 13;
    public static final int MEDIA_TYPE_LOCAL = 1;
    public static final int MEDIA_TYPE_NONPRESET_THIRDPARTY_APP = 11;
    public static final int MEDIA_TYPE_SD = 4;
    public static final int MEDIA_TYPE_TYPE_BT = 6;
    public static final int MEDIA_TYPE_USB_ONE = 2;
    public static final int MEDIA_TYPE_USB_TWO = 3;
    public static final int MEDIA_TYPE_VIDEO = 8;
    public static final int MUSIC_STATE_CLOSE = 3;
    public static final int MUSIC_STATE_PAUSE = 2;
    public static final int MUSIC_STATE_PLAYING = 1;
    public static final int NAVI_TYPE_AMAP = 1;
    public static final int NAVI_TYPE_BAIDU = 2;
    public static final int NAVI_TYPE_DEFAULT = 0;
    public static final int NAVI_TYPE_OTHER = 4;
    public static final int NAVI_TYPE_OVERSEAS_MAP = 3;
    public static final int POSITION_TYPE_CENTER = 1;
    public static final int POSITION_TYPE_LEFT = 2;
    public static final int POSITION_TYPE_RIGHT = 3;
    public static final int STATE = 1;
    public static final int STATE_ACC_ACTIVATED = 3;
    public static final int STATE_ACC_CANCELLED = 2;
    public static final int STATE_ACC_CANNOT_BEACTIVATED = 4;
    public static final int STATE_ACC_ON = 1;
    public static final int STATE_AHEAD_FRONT_NOTSPACE = 1;
    public static final int STATE_AHEAD_NOTFRONT_NOTSPACE = 2;
    public static final int STATE_AHEAD_SPACE_FOUR = 6;
    public static final int STATE_AHEAD_SPACE_ONE = 3;
    public static final int STATE_AHEAD_SPACE_THREE = 5;
    public static final int STATE_AHEAD_SPACE_TWO = 4;
    public static final int STATE_CONFIRM_DOOR_CLOSED = 6;
    public static final int STATE_CONFIRM_GEAR_D = 7;
    public static final int STATE_FASTEN_DRIVERS_SEATBELT = 5;
    public static final int STATE_FREQ_FOUR = 7;
    public static final int STATE_FREQ_HALF = 4;
    public static final int STATE_FREQ_ONE = 5;
    public static final int STATE_FREQ_QUARTER = 3;
    public static final int STATE_FREQ_TWO = 6;
    public static final int STATE_INVALID = 0;
    public static final int STATE_LAMP_BLUE = 5;
    public static final int STATE_LAMP_GREEN = 4;
    public static final int STATE_LAMP_RED = 3;
    public static final int STATE_LAMP_WHITE = 1;
    public static final int STATE_LAMP_YELLOW = 2;
    public static final int STATE_OFF = 2;
    public static final int STATE_ON = 1;
    public static final int STATE_RELEASE_EPB = 8;
    public static final int STATE_SPACE_FIVE = 5;
    public static final int STATE_SPACE_FOUR = 4;
    public static final int STATE_SPACE_ONE = 1;
    public static final int STATE_SPACE_THREE = 3;
    public static final int STATE_SPACE_TWO = 2;
    public static final int STATE_TURNON_ESP = 9;
    public static final int UNIT_INVALID = 0;
    public static final int UNIT_METRIC = 1;
    public static final int UNIT_MIL = 2;
    private static BYDAutoWhudDevice sDevice;
    private final WhudManagerImpl mWhudManagerImpl;
    private final WhudSettingManagerImpl mWhudSettingManagerImpl;

    private BYDAutoWhudDevice(Context con) {
        super(con.getApplicationContext());
        this.mWhudManagerImpl = WhudManagerImpl.getInstance(con.getApplicationContext());
        this.mWhudSettingManagerImpl = WhudSettingManagerImpl.getInstance(con.getApplicationContext());
    }

    public static BYDAutoWhudDevice getInstance(Context con) {
        if (sDevice == null) {
            synchronized (BYDAutoWhudDevice.class) {
                if (sDevice == null) {
                    sDevice = new BYDAutoWhudDevice(con.getApplicationContext());
                }
            }
        }
        return sDevice;
    }

    public int setNavGuideInfo(int type) {
        return this.mWhudManagerImpl.setNavGuideInfo(type);
    }

    public int setDistanceAhead(int value) {
        return this.mWhudManagerImpl.setDistanceAhead(value);
    }

    public int setDistanceAheadUnit(int unit) {
        return this.mWhudManagerImpl.setDistanceAheadUnit(unit);
    }

    public int setDriveRemainingHour(int hour) {
        return this.mWhudManagerImpl.setDriveRemainingHour(hour);
    }

    public int setDriveRemainingMinute(int minute) {
        return this.mWhudManagerImpl.setDriveRemainingMinute(minute);
    }

    public int setDriveRemainingSecond(int second) {
        return this.mWhudManagerImpl.setDriveRemainingSecond(second);
    }

    public int setDriveRemainingDays(int days) {
        return this.mWhudManagerImpl.setDriveRemainingDays(days);
    }

    public int setDriveRemainingMileage(long value) {
        return this.mWhudManagerImpl.setDriveRemainingMileage(value);
    }

    public int setDriveRemainingUnit(int unit) {
        return this.mWhudManagerImpl.setDriveRemainingUnit(unit);
    }

    public int setDriveRemainingHourFormat(int hourFormat) {
        return this.mWhudManagerImpl.setDriveRemainingHourFormat(hourFormat);
    }

    public int setNavGuideInfoAdvanced(int type) {
        return this.mWhudManagerImpl.setNavGuideInfoAdvanced(type);
    }

    public int setDistanceFrontTarget(int value) {
        return this.mWhudManagerImpl.setDistanceFrontTarget(value);
    }

    public int setDistanceFrontTargetUnit(int unit) {
        return this.mWhudManagerImpl.setDistanceFrontTargetUnit(unit);
    }

    public int setArrivalDayEstimated(int day) {
        return this.mWhudManagerImpl.setArrivalDayEstimated(day);
    }

    public int setArrivalHourEstimated(int hour) {
        return this.mWhudManagerImpl.setArrivalHourEstimated(hour);
    }

    public int setArrivalMinuteEstimated(int minute) {
        return this.mWhudManagerImpl.setArrivalMinuteEstimated(minute);
    }

    public int setArrivalSecondEstimated(int second) {
        return this.mWhudManagerImpl.setArrivalSecondEstimated(second);
    }

    public int setNameNextRoad(String name) {
        return this.mWhudManagerImpl.setNameNextRoad(name);
    }

    public int setNaviType(int type) {
        return this.mWhudManagerImpl.setNaviType(type);
    }

    public int getShowAdasWork() {
        return this.mWhudManagerImpl.getShowAdasWork();
    }

    public int getVehicleSpace() {
        return this.mWhudManagerImpl.getVehicleSpace();
    }

    public int getAheadVehicleSpace() {
        return this.mWhudManagerImpl.getAheadVehicleSpace();
    }

    public int getTextHint() {
        return this.mWhudManagerImpl.getTextHint();
    }

    public int getVehicleBodyPosition() {
        return this.mWhudManagerImpl.getVehicleBodyPosition();
    }

    public int getLeftLine() {
        return this.mWhudManagerImpl.getLeftLine();
    }

    public int getRightLine() {
        return this.mWhudManagerImpl.getRightLine();
    }

    public int getTextMessageHint() {
        return this.mWhudManagerImpl.getTextMessageHint();
    }

    public int getAccCruisingSpeedValue() {
        return this.mWhudManagerImpl.getAccCruisingSpeedValue();
    }

    public int getTrafficSignRecognitionValue() {
        return this.mWhudManagerImpl.getTrafficSignRecognitionValue();
    }

    public int getAccCruisingLamp() {
        return this.mWhudManagerImpl.getAccCruisingLamp();
    }

    public int getAccCruisingLampColor() {
        return this.mWhudManagerImpl.getAccCruisingLampColor();
    }

    public int setMusicPlayState(int state) {
        return this.mWhudManagerImpl.setMusicPlayState(state);
    }

    public int setRadioPlayState(int state) {
        return this.mWhudManagerImpl.setRadioPlayState(state);
    }

    public int setMusicPlayProgress(int value) {
        return this.mWhudManagerImpl.setMusicPlayProgress(value);
    }

    public int setCallTimeHour(int hour) {
        return this.mWhudManagerImpl.setCallTimeHour(hour);
    }

    public int setCallTimeMinute(int minute) {
        return this.mWhudManagerImpl.setCallTimeMinute(minute);
    }

    public int setCallTimeSecond(int second) {
        return this.mWhudManagerImpl.setCallTimeSecond(second);
    }

    public int setMediaSource(int type) {
        return this.mWhudManagerImpl.setMediaSource(type);
    }

    public int setOverseasMapSupplier(int state) {
        this.mWhudSettingManagerImpl.setOverseasMapSupplier(state);
        return this.mWhudManagerImpl.setOverseasMapSupplier(state);
    }

    public int setCallState(int state) {
        return this.mWhudManagerImpl.setCallState(state);
    }

    @Deprecated
    public int setScreenDisplayState(int state) {
        return this.mWhudManagerImpl.setScreenDisplayState(state);
    }

    public int setMusicCurrentName(String name) {
        return this.mWhudManagerImpl.setMusicCurrentName(name);
    }

    public int setCallNumber(String number) {
        return this.mWhudManagerImpl.setCallNumber(number);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return AndroidAutoConst.DEFAULT_SCREEN_NAME;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int setNaviState(int state) {
        return this.mWhudManagerImpl.setNaviState(state);
    }

    public int setEasyNavGuideInfo(int type, long distance, int unit) {
        return this.mWhudManagerImpl.setEasyNavGuideInfo(type, distance, unit);
    }

    public void registerListener(AbsBYDAutoWhudListener l) {
        this.mWhudManagerImpl.registerListener(l);
    }

    public void unregisterListener(AbsBYDAutoWhudListener l) {
        this.mWhudManagerImpl.unregisterListener(l);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mWhudManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mWhudManagerImpl.set(eventTypes, eventValue);
    }

    public int setCurrentDayUnitLen(String dayUnit) {
        return this.mWhudManagerImpl.setCurrentDayUnitLen(dayUnit);
    }

    public int setCurrentHourUnitLen(String dayUnit) {
        return this.mWhudManagerImpl.setCurrentHourUnitLen(dayUnit);
    }

    public int setCurrentMinUnitLen(String dayUnit) {
        return this.mWhudManagerImpl.setCurrentMinUnitLen(dayUnit);
    }

    public int setTargetDistanceUnitDataLen(String date) {
        return this.mWhudManagerImpl.setTargetDistanceUnitDataLen(date);
    }

    public int setNextRoadDistanceUnitDataLen(String date) {
        return this.mWhudManagerImpl.setNextRoadDistanceUnitDataLen(date);
    }

    public int setNextRoadDistanceUnitDataLenV2(String date, String unit) {
        return this.mWhudManagerImpl.setNextRoadDistanceUnitDataLenV2(date, unit);
    }
}
