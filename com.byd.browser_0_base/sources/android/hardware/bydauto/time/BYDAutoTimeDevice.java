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

    public BYDAutoTimeDevice(Context context) {
        super(context.getApplicationContext());
        this.mTimeManagerImpl = TimeManagerImpl.getsInstance(context.getApplicationContext());
        this.mInstrumentSettingsManagerImpl = InstrumentSettingsManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoTimeDevice getInstance(Context context) {
        if (sBYDAutoTimeDevice == null) {
            sBYDAutoTimeDevice = new BYDAutoTimeDevice(context.getApplicationContext());
        }
        return sBYDAutoTimeDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mTimeManagerImpl.get(iArr, cls);
    }

    public void getAllStatus() {
        this.mTimeManagerImpl.getAllStatus();
    }

    public int getDateFormat() {
        return this.mTimeManagerImpl.getDateFormat();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mTimeManagerImpl.getDevicetype();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mTimeManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_TIME_GET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_TIME_SET;
    }

    public int getSummertimeState() {
        return this.mTimeManagerImpl.getSummertimeState();
    }

    public int[] getTime() {
        return this.mTimeManagerImpl.getTime();
    }

    public int getTimeFormat() {
        return this.mTimeManagerImpl.getTimeFormat();
    }

    public int getTimeZone() {
        return this.mTimeManagerImpl.getTimeZone();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mTimeManagerImpl.getType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return this.mTimeManagerImpl.postEvent(i, i2, i3, obj);
    }

    public void registerListener(AbsBYDAutoTimeListener absBYDAutoTimeListener) {
        this.mTimeManagerImpl.registerListener(absBYDAutoTimeListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mTimeManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public void setAllStatus() {
        this.mTimeManagerImpl.setAllStatus();
    }

    public int setDate(int i, int i2, int i3, int i4) {
        return this.mTimeManagerImpl.setDate(i, i2, i3, i4);
    }

    public int setFullTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return this.mTimeManagerImpl.setFullTime(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public int setFullTimeToECM(int i, int i2, int i3, int i4, int i5, int i6) {
        return this.mTimeManagerImpl.setFullTimeToECM(i, i2, i3, i4, i5, i6);
    }

    public int setFullTimeWithTimeFormat(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return this.mTimeManagerImpl.setFullTimeWithTimeFormat(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public int setTime(int i, int i2, int i3) {
        return this.mTimeManagerImpl.setTime(i, i2, i3);
    }

    public int setTimeFormat(int i) {
        this.mInstrumentSettingsManagerImpl.setTimeFormat(i);
        return this.mTimeManagerImpl.setTimeFormat(i);
    }

    public int setTimeZone(int i) {
        return this.mTimeManagerImpl.setTimeZone(i);
    }

    public void unregisterListener(AbsBYDAutoTimeListener absBYDAutoTimeListener) {
        this.mTimeManagerImpl.unregisterListener(absBYDAutoTimeListener);
    }

    public void registerListener(AbsBYDAutoTimeListener absBYDAutoTimeListener, int[] iArr) {
        this.mTimeManagerImpl.registerListener(absBYDAutoTimeListener, iArr);
    }
}
