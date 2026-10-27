package android.hardware.bydauto.time;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.instrument.tsimpl.InstrumentSettingsManagerImpl;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoTimeDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_TIME_COMMON = "android.permission.BYDAUTO_TIME_COMMON";
    public static final String PERMISSION_BYDAUTO_TIME_GET = "android.permission.BYDAUTO_TIME_GET";
    public static final String PERMISSION_BYDAUTO_TIME_SET = "android.permission.BYDAUTO_TIME_SET";
    protected static final String TAG = "BYDAutoTIMEDevice";
    public static final int TIME_COMMAND_BUSY = -2147482647;
    public static final int TIME_COMMAND_FAILED = -2147482648;
    public static final int TIME_COMMAND_INVALID_VALUE = -2147482645;
    public static final int TIME_COMMAND_SUCCESS = 0;
    public static final int TIME_COMMAND_TIMEOUT = -2147482646;
    public static final int TIME_DATE_FORMAT1 = 1;
    public static final int TIME_DATE_FORMAT2 = 2;
    public static final int TIME_DATE_FORMAT3 = 3;
    public static final int TIME_DAY_MAX = 31;
    public static final int TIME_DAY_MIN = 1;
    public static final int TIME_FORMAT_24H_OFF = 0;
    public static final int TIME_FORMAT_24H_ON = 1;
    public static final int TIME_FORMAT_INVALID = 0;
    public static final int TIME_FORMAT_TWELVE_HOUR = 1;
    public static final int TIME_FORMAT_TWENTYFOUR_HOUR = 2;
    public static final int TIME_HOUR_MAX = 23;
    public static final int TIME_HOUR_MIN = 0;
    public static final int TIME_MINUTE_MAX = 59;
    public static final int TIME_MINUTE_MIN = 0;
    public static final int TIME_MONTH_MAX = 12;
    public static final int TIME_MONTH_MIN = 1;
    public static final int TIME_SECOND_MAX = 59;
    public static final int TIME_SECOND_MIN = 0;
    public static final int TIME_SET_AUTO = 1;
    public static final int TIME_SET_MANUAL = 2;
    public static final int TIME_SUMMERTIME_OFF = 0;
    public static final int TIME_SUMMERTIME_ON = 1;
    public static final int TIME_WEEKDAY_MAX = 7;
    public static final int TIME_WEEKDAY_MIN = 1;
    public static final int TIME_YEAR_MAX = 2255;
    public static final int TIME_YEAR_MIN = 2001;
    public static final int TIME_ZONE_MAX = 23;
    public static final int TIME_ZONE_MIN = 0;
    private static BYDAutoTimeDevice sBYDAutoTimeDevice;
    private InstrumentSettingsManagerImpl mInstrumentSettingsManagerImpl;
    private TimeManagerImpl mTimeManagerImpl;

    BYDAutoTimeDevice(Context con) {
        super(con.getApplicationContext());
        this.mTimeManagerImpl = TimeManagerImpl.getsInstance(con.getApplicationContext());
        this.mInstrumentSettingsManagerImpl = InstrumentSettingsManagerImpl.getInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoTimeDevice getInstance(Context con) {
        if (sBYDAutoTimeDevice == null) {
            sBYDAutoTimeDevice = new BYDAutoTimeDevice(con.getApplicationContext());
        }
        return sBYDAutoTimeDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mTimeManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_TIME_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_TIME_GET;
    }

    public int setFullTime(int mode, int year, int month, int day, int weekday, int hour, int minute, int second) {
        return this.mTimeManagerImpl.setFullTime(mode, year, month, day, weekday, hour, minute, second);
    }

    public int setFullTimeWithTimeFormat(int year, int month, int day, int hour, int minute, int second, int weekday, int timeFormat) {
        return this.mTimeManagerImpl.setFullTimeWithTimeFormat(year, month, day, hour, minute, second, weekday, timeFormat);
    }

    public int setDate(int year, int month, int day, int weekday) {
        return this.mTimeManagerImpl.setDate(year, month, day, weekday);
    }

    public int setTime(int hour, int minute, int second) {
        return this.mTimeManagerImpl.setTime(hour, minute, second);
    }

    public int[] getTime() {
        return this.mTimeManagerImpl.getTime();
    }

    public int setTimeFormat(int value) {
        this.mInstrumentSettingsManagerImpl.setTimeFormat(value);
        return this.mTimeManagerImpl.setTimeFormat(value);
    }

    public int getTimeFormat() {
        return this.mTimeManagerImpl.getTimeFormat();
    }

    public int setTimeZone(int value) {
        return this.mTimeManagerImpl.setTimeZone(value);
    }

    public int getTimeZone() {
        return this.mTimeManagerImpl.getTimeZone();
    }

    public int getDateFormat() {
        return this.mTimeManagerImpl.getDateFormat();
    }

    public int getSummertimeState() {
        return this.mTimeManagerImpl.getSummertimeState();
    }

    public int setFullTimeToECM(int year, int month, int day, int hour, int minute, int second) {
        return this.mTimeManagerImpl.setFullTimeToECM(year, month, day, hour, minute, second);
    }

    public void setAllStatus() {
        this.mTimeManagerImpl.setAllStatus();
    }

    public void getAllStatus() {
        this.mTimeManagerImpl.getAllStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return this.mTimeManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mTimeManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mTimeManagerImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoTimeListener listener) {
        this.mTimeManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoTimeListener listener, int[] featureIDs) {
        this.mTimeManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoTimeListener listener) {
        this.mTimeManagerImpl.unregisterListener(listener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mTimeManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mTimeManagerImpl.set(eventTypes, eventValue);
    }
}
