package android.hardware.bydauto.whud;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.whud.tsimpl.WhudManagerImpl;
import android.hardware.bydauto.whud.tsimpl.WhudSettingManagerImpl;

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

    private BYDAutoWhudDevice(Context context) {
        super(context.getApplicationContext());
        this.mWhudManagerImpl = WhudManagerImpl.getInstance(context.getApplicationContext());
        this.mWhudSettingManagerImpl = WhudSettingManagerImpl.getInstance(context.getApplicationContext());
    }

    public static BYDAutoWhudDevice getInstance(Context context) {
        if (sDevice == null) {
            synchronized (BYDAutoWhudDevice.class) {
                if (sDevice == null) {
                    sDevice = new BYDAutoWhudDevice(context.getApplicationContext());
                }
            }
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mWhudManagerImpl.get(iArr, cls);
    }

    public int getAccCruisingLamp() {
        return this.mWhudManagerImpl.getAccCruisingLamp();
    }

    public int getAccCruisingLampColor() {
        return this.mWhudManagerImpl.getAccCruisingLampColor();
    }

    public int getAccCruisingSpeedValue() {
        return this.mWhudManagerImpl.getAccCruisingSpeedValue();
    }

    public int getAheadVehicleSpace() {
        return this.mWhudManagerImpl.getAheadVehicleSpace();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    public int getLeftLine() {
        return this.mWhudManagerImpl.getLeftLine();
    }

    public int getRightLine() {
        return this.mWhudManagerImpl.getRightLine();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    public int getShowAdasWork() {
        return this.mWhudManagerImpl.getShowAdasWork();
    }

    public int getTextHint() {
        return this.mWhudManagerImpl.getTextHint();
    }

    public int getTextMessageHint() {
        return this.mWhudManagerImpl.getTextMessageHint();
    }

    public int getTrafficSignRecognitionValue() {
        return this.mWhudManagerImpl.getTrafficSignRecognitionValue();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getVehicleBodyPosition() {
        return this.mWhudManagerImpl.getVehicleBodyPosition();
    }

    public int getVehicleSpace() {
        return this.mWhudManagerImpl.getVehicleSpace();
    }

    public void registerListener(AbsBYDAutoWhudListener absBYDAutoWhudListener) {
        this.mWhudManagerImpl.registerListener(absBYDAutoWhudListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mWhudManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setArrivalDayEstimated(int i) {
        return this.mWhudManagerImpl.setArrivalDayEstimated(i);
    }

    public int setArrivalHourEstimated(int i) {
        return this.mWhudManagerImpl.setArrivalHourEstimated(i);
    }

    public int setArrivalMinuteEstimated(int i) {
        return this.mWhudManagerImpl.setArrivalMinuteEstimated(i);
    }

    public int setArrivalSecondEstimated(int i) {
        return this.mWhudManagerImpl.setArrivalSecondEstimated(i);
    }

    public int setCallNumber(String str) {
        return this.mWhudManagerImpl.setCallNumber(str);
    }

    public int setCallState(int i) {
        return this.mWhudManagerImpl.setCallState(i);
    }

    public int setCallTimeHour(int i) {
        return this.mWhudManagerImpl.setCallTimeHour(i);
    }

    public int setCallTimeMinute(int i) {
        return this.mWhudManagerImpl.setCallTimeMinute(i);
    }

    public int setCallTimeSecond(int i) {
        return this.mWhudManagerImpl.setCallTimeSecond(i);
    }

    public int setCurrentDayUnitLen(String str) {
        return this.mWhudManagerImpl.setCurrentDayUnitLen(str);
    }

    public int setCurrentHourUnitLen(String str) {
        return this.mWhudManagerImpl.setCurrentHourUnitLen(str);
    }

    public int setCurrentMinUnitLen(String str) {
        return this.mWhudManagerImpl.setCurrentMinUnitLen(str);
    }

    public int setDistanceAhead(int i) {
        return this.mWhudManagerImpl.setDistanceAhead(i);
    }

    public int setDistanceAheadUnit(int i) {
        return this.mWhudManagerImpl.setDistanceAheadUnit(i);
    }

    public int setDistanceFrontTarget(int i) {
        return this.mWhudManagerImpl.setDistanceFrontTarget(i);
    }

    public int setDistanceFrontTargetUnit(int i) {
        return this.mWhudManagerImpl.setDistanceFrontTargetUnit(i);
    }

    public int setDriveRemainingDays(int i) {
        return this.mWhudManagerImpl.setDriveRemainingDays(i);
    }

    public int setDriveRemainingHour(int i) {
        return this.mWhudManagerImpl.setDriveRemainingHour(i);
    }

    public int setDriveRemainingHourFormat(int i) {
        return this.mWhudManagerImpl.setDriveRemainingHourFormat(i);
    }

    public int setDriveRemainingMileage(long j) {
        return this.mWhudManagerImpl.setDriveRemainingMileage(j);
    }

    public int setDriveRemainingMinute(int i) {
        return this.mWhudManagerImpl.setDriveRemainingMinute(i);
    }

    public int setDriveRemainingSecond(int i) {
        return this.mWhudManagerImpl.setDriveRemainingSecond(i);
    }

    public int setDriveRemainingUnit(int i) {
        return this.mWhudManagerImpl.setDriveRemainingUnit(i);
    }

    public int setEasyNavGuideInfo(int i, long j, int i2) {
        return this.mWhudManagerImpl.setEasyNavGuideInfo(i, j, i2);
    }

    public int setMediaSource(int i) {
        return this.mWhudManagerImpl.setMediaSource(i);
    }

    public int setMusicCurrentName(String str) {
        return this.mWhudManagerImpl.setMusicCurrentName(str);
    }

    public int setMusicPlayProgress(int i) {
        return this.mWhudManagerImpl.setMusicPlayProgress(i);
    }

    public int setMusicPlayState(int i) {
        return this.mWhudManagerImpl.setMusicPlayState(i);
    }

    public int setNameNextRoad(String str) {
        return this.mWhudManagerImpl.setNameNextRoad(str);
    }

    public int setNavGuideInfo(int i) {
        return this.mWhudManagerImpl.setNavGuideInfo(i);
    }

    public int setNavGuideInfoAdvanced(int i) {
        return this.mWhudManagerImpl.setNavGuideInfoAdvanced(i);
    }

    public int setNaviState(int i) {
        return this.mWhudManagerImpl.setNaviState(i);
    }

    public int setNaviType(int i) {
        return this.mWhudManagerImpl.setNaviType(i);
    }

    public int setNextRoadDistanceUnitDataLen(String str) {
        return this.mWhudManagerImpl.setNextRoadDistanceUnitDataLen(str);
    }

    public int setNextRoadDistanceUnitDataLenV2(String str, String str2) {
        return 0;
    }

    public int setOverseasMapSupplier(int i) {
        this.mWhudSettingManagerImpl.setOverseasMapSupplier(i);
        return this.mWhudManagerImpl.setOverseasMapSupplier(i);
    }

    public int setRadioPlayState(int i) {
        return this.mWhudManagerImpl.setRadioPlayState(i);
    }

    public int setScreenDisplayState(int i) {
        return this.mWhudManagerImpl.setScreenDisplayState(i);
    }

    public int setTargetDistanceUnitDataLen(String str) {
        return this.mWhudManagerImpl.setTargetDistanceUnitDataLen(str);
    }

    public void unregisterListener(AbsBYDAutoWhudListener absBYDAutoWhudListener) {
        this.mWhudManagerImpl.unregisterListener(absBYDAutoWhudListener);
    }
}
