package android.hardware.bydauto.time.tsimpl;

import android.app.timedetector.ManualTimeSuggestion;
import android.app.timedetector.TimeDetector;
import android.app.timezonedetector.ManualTimeZoneSuggestion;
import android.app.timezonedetector.TimeZoneDetector;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.AbsBYDAutoTimeListener;
import android.hardware.bydauto.time.BYDAutoTimeDevice;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class TimeManagerImpl {
    private static final int EAST_ZONE_MAX = 12;
    private static final int EAST_ZONE_MIN = 0;
    public static final int ENERGY_COMMAND_BUSY = -2147482647;
    public static final int ENERGY_COMMAND_FAILED = -2147482648;
    public static final int ENERGY_COMMAND_INVALID = -2147482645;
    public static final int ENERGY_COMMAND_SUCCESS = 0;
    public static final int ENERGY_COMMAND_TIMEOUT = -2147482646;
    private static final int SECONDS_PER_HOUR = 3600;
    public static final int SUBSCRIPT_FIVE = 5;
    public static final int SUBSCRIPT_FOUR = 4;
    public static final int SUBSCRIPT_ONE = 1;
    public static final int SUBSCRIPT_THREE = 3;
    public static final int SUBSCRIPT_TWO = 2;
    public static final int SUBSCRIPT_ZERO = 0;
    protected static final String TAG = "TimeManagerImpl";
    public static final int TIME_COMMAND_BUSY = -2147482647;
    public static final int TIME_COMMAND_FAILED = -2147482648;
    public static final int TIME_COMMAND_INVALID = -2147482645;
    public static final int TIME_COMMAND_SUCCESS = 0;
    public static final int TIME_COMMAND_TIMEOUT = -2147482646;
    public static final int TIME_DATE_FORMAT1 = 1;
    public static final int TIME_DATE_FORMAT2 = 2;
    public static final int TIME_DATE_FORMAT3 = 3;
    public static final int TIME_DAY_MAX = 31;
    public static final int TIME_DAY_MIN = 1;
    public static final int TIME_FORMAT_24H_OFF = 0;
    public static final int TIME_FORMAT_24H_ON = 1;
    private static final String TIME_FORMAT_HOURS_12 = "12";
    private static final String TIME_FORMAT_HOURS_24 = "24";
    public static final int TIME_FORMAT_INVALID = 0;
    public static final String TIME_FORMAT_TWELVE = "12";
    public static final int TIME_FORMAT_TWELVE_HOUR = 1;
    public static final int TIME_FORMAT_TWENTYFOUR_HOUR = 2;
    public static final String TIME_FORMAT_TWENTY_FOUR = "24";
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
    private static final int WEST_ZONE_MIN = -11;
    private static TimeManagerImpl sInstance;
    private Context mContext;
    private int[] mFeatureIds;
    private final IntentFilter mTimeChangeIntentFilter;
    private final TimeDetector mTimeDetector;
    private TimeListenerHelper mTimeListenerHelper;
    private final TimeZoneDetector mTimeZoneDetector;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();
    private final HashMap<Integer, String> mTimeZoneHashMap = new HashMap<>();
    private boolean isReceiverRegistered = false;
    private boolean mIgnoreBydPermission = false;
    private final BroadcastReceiver mTimeChangeReceiver = new BroadcastReceiver() { // from class: android.hardware.bydauto.time.tsimpl.TimeManagerImpl.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(TimeManagerImpl.TAG, "mTimeChangeReceiver-onReceive, action:" + intent.getAction());
            TimeManagerImpl.this.mTimeListenerHelper.notifyTimeChanged(TimeManagerImpl.this.getTime());
        }
    };
    private ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: android.hardware.bydauto.time.tsimpl.TimeManagerImpl.2
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            Log.d(TimeManagerImpl.TAG, "m12And24HourFormatObserver-value changed.");
            TimeManagerImpl.this.mTimeListenerHelper.notifyTimeFormatChanged(TimeManagerImpl.this.getTimeFormat());
        }
    };

    TimeManagerImpl(Context context) {
        this.mContext = context.getApplicationContext();
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
        this.mTimeDetector = (TimeDetector) this.mContext.getSystemService(TimeDetector.class);
        this.mTimeZoneDetector = (TimeZoneDetector) this.mContext.getSystemService(TimeZoneDetector.class);
        this.mTimeListenerHelper = new TimeListenerHelper();
        addTimeZoneData();
        IntentFilter intentFilter = new IntentFilter();
        this.mTimeChangeIntentFilter = intentFilter;
        intentFilter.addAction("android.intent.action.TIME_SET");
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    private void addTimeZoneData() {
        this.mTimeZoneHashMap.put(0, "GMT+00:00");
        int count = 0 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count), "GMT+01:00");
        int count2 = count + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count2), "GMT+02:00");
        int count3 = count2 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count3), "GMT+03:00");
        int count4 = count3 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count4), "GMT+04:00");
        int count5 = count4 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count5), "GMT+05:00");
        int count6 = count5 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count6), "GMT+06:00");
        int count7 = count6 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count7), "GMT+07:00");
        int count8 = count7 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count8), "GMT+08:00");
        int count9 = count8 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count9), "GMT+09:00");
        int count10 = count9 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count10), "GMT+10:00");
        int count11 = count10 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count11), "GMT+11:00");
        int count12 = count11 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count12), "GMT+12:00");
        int count13 = count12 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count13), "GMT-01:00");
        int count14 = count13 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count14), "GMT-02:00");
        int count15 = count14 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count15), "GMT-03:00");
        int count16 = count15 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count16), "GMT-04:00");
        int count17 = count16 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count17), "GMT-05:00");
        int count18 = count17 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count18), "GMT-06:00");
        int count19 = count18 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count19), "GMT-07:00");
        int count20 = count19 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count20), "GMT-08:00");
        int count21 = count20 + 1;
        this.mTimeZoneHashMap.put(Integer.valueOf(count21), "GMT-09:00");
        this.mTimeZoneHashMap.put(Integer.valueOf(count21 + 1), "GMT-10:00");
        this.mTimeZoneHashMap.put(23, "GMT-11:00");
    }

    public static TimeManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (TimeManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new TimeManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype: is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return 1024;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return null;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return null;
    }

    public int setFullTime(int mode, int year, int month, int day, int weekday, int hour, int minute, int second) {
        Log.d(TAG, "setFullTime is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (2 != mode) {
            Log.w(TAG, "setFullTime mode-" + mode + " is not supported");
            return -2147482645;
        }
        if (!yearCheck(year) || !monthCheck(month) || !dayCheck(day) || !hourCheck(hour) || !minuteCheck(minute) || !secondCheck(second)) {
            Log.w(TAG, "setFullTime param error, mode:" + mode + ", year:" + year + ", month:" + month + ", day:" + day + ", hour:" + hour + ", minute:" + minute + ", second:" + second);
            return -2147482645;
        }
        if (this.mTimeDetector == null) {
            Log.w(TAG, "mTimeDetector is null, check service of TimeDetector");
            return -2147482648;
        }
        if (DateUtils.isAutoTimeSwitchOpen(this.mContext)) {
            Log.w(TAG, "setFullTime error, auto time switch is open, pls close this switch.");
            return -2147482648;
        }
        int[] data = {year, month, day, hour, minute, second};
        long millis = DateUtils.formatDateToMillis(data);
        if (-1 == millis) {
            Log.w(TAG, "setFullTime error ,maybe params error, check log for more details.");
            return -2147482645;
        }
        ManualTimeSuggestion manualTimeSuggestion = TimeDetector.createManualTimeSuggestion(millis, TAG);
        this.mTimeDetector.suggestManualTime(manualTimeSuggestion);
        Log.d(TAG, "setDate success, millis:" + millis);
        return 0;
    }

    public int setFullTimeWithTimeFormat(int year, int month, int day, int hour, int minute, int second, int weekday, int timeFormat) {
        Log.d(TAG, "setFullTimeWithTimeFormat is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (1 != timeFormat && timeFormat != 0) {
            Log.w(TAG, "setFullTimeWithTimeFormat， unsupported timeFormat:" + timeFormat);
            return -2147482645;
        }
        if (!yearCheck(year) || !monthCheck(month) || !dayCheck(day) || !hourCheck(hour) || !minuteCheck(minute) || !secondCheck(second)) {
            Log.d(TAG, "setFullTime param error, year:" + year + ", month:" + month + ", day:" + day + ", hour:" + hour + ", minute:" + minute + ", second:" + second);
            return -2147482645;
        }
        if (DateUtils.isAutoTimeSwitchOpen(this.mContext)) {
            Log.w(TAG, "setFullTimeWithTimeFormat error, auto time switch is open, pls close this switch.");
            return -2147482648;
        }
        setTimeFormat(timeFormat);
        return setFullTime(2, year, month, day, weekday, hour, minute, second);
    }

    public int setDate(int year, int month, int day, int weekday) {
        Log.d(TAG, "setDate start, params-year:" + year + ", month:" + month + ", day:" + day + ", weekday:" + weekday);
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (!checkSetDateParams(year, month, day, weekday)) {
            Log.w(TAG, "mTimeDetector is null, check service of TimeDetector");
            return -2147482645;
        }
        if (this.mTimeDetector == null) {
            Log.w(TAG, "mTimeDetector is null, check service of TimeDetector");
            return -2147482648;
        }
        if (DateUtils.isAutoTimeSwitchOpen(this.mContext)) {
            Log.w(TAG, "setDate error, auto time switch is open, pls close this switch.");
            return -2147482648;
        }
        long millis = DateUtils.formatYearMonthDayToMillis(year, month, day);
        ManualTimeSuggestion manualTimeSuggestion = TimeDetector.createManualTimeSuggestion(millis, TAG);
        this.mTimeDetector.suggestManualTime(manualTimeSuggestion);
        Log.d(TAG, "setDate success, millis:" + millis);
        return 0;
    }

    private final boolean checkSetDateParams(int year, int month, int day, int weekday) {
        if (yearCheck(year) && monthCheck(month) && dayCheck(day)) {
            return true;
        }
        return false;
    }

    private final boolean yearCheck(int year) {
        if (year < 2001 || year > 2255) {
            return false;
        }
        return true;
    }

    private final boolean monthCheck(int month) {
        if (month >= 1 && month <= 12) {
            return true;
        }
        return false;
    }

    private final boolean dayCheck(int day) {
        if (day >= 1 && day <= 31) {
            return true;
        }
        return false;
    }

    public int setTime(int hour, int minute, int second) {
        Log.d(TAG, "setTime start, params-hour:" + hour + ", minute:" + minute + ", second:" + second);
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (!checkSetTimeParams(hour, minute, second)) {
            Log.w(TAG, "setTime error, param check failed, hour:" + hour + ", minute:" + minute + ", second:" + second);
            return -2147482645;
        }
        if (this.mTimeDetector == null) {
            Log.w(TAG, "mTimeDetector is null, check service of TimeDetector");
            return -2147482648;
        }
        if (DateUtils.isAutoTimeSwitchOpen(this.mContext)) {
            Log.w(TAG, "setTime error, auto time switch is open, pls close this switch.");
            return -2147482648;
        }
        long millis = DateUtils.formatHourMinuteSecondToMillis(hour, minute, second);
        ManualTimeSuggestion manualTimeSuggestion = TimeDetector.createManualTimeSuggestion(millis, TAG);
        this.mTimeDetector.suggestManualTime(manualTimeSuggestion);
        Log.d(TAG, "setTime success, millis:" + millis);
        return 0;
    }

    private final boolean checkSetTimeParams(int hour, int minute, int second) {
        if (hourCheck(hour) && minuteCheck(minute) && secondCheck(second)) {
            return true;
        }
        return false;
    }

    private final boolean hourCheck(int hour) {
        if (hour < 0 || hour > 23) {
            return false;
        }
        return true;
    }

    private final boolean minuteCheck(int minute) {
        if (minute < 0 || minute > 59) {
            return false;
        }
        return true;
    }

    private final boolean secondCheck(int second) {
        if (second < 0 || second > 59) {
            return false;
        }
        return true;
    }

    public int[] getTime() {
        Log.d(TAG, "getTime is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return DateUtils.millisToFormatDate(System.currentTimeMillis());
    }

    public int setTimeFormat(int value) {
        String hourFormat;
        Log.d(TAG, "setTimeFormat is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (value == 0) {
            hourFormat = "12";
        } else if (1 == value) {
            hourFormat = "24";
        } else {
            Log.w(TAG, "setTimeFormat with illegal value:" + value);
            return -2147482645;
        }
        Settings.System.putString(this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24, hourFormat);
        Log.d(TAG, "setTimeFormat success, value:" + value + ", format:" + hourFormat);
        return 0;
    }

    public int getTimeFormat() {
        Log.d(TAG, "getTimeFormat is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        String timeFormatStr = Settings.System.getString(this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        if (TextUtils.equals("12", timeFormatStr)) {
            return 0;
        }
        return 1;
    }

    public int setTimeZone(int value) {
        Log.d(TAG, "setTimeZone is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (!timeZoneCheck(value)) {
            Log.d(TAG, "setTimeZone illegal value:" + value);
            return -2147482645;
        }
        if (DateUtils.isAutoTimeZoneSwitchOpen(this.mContext)) {
            Log.w(TAG, "setTimeZone error, auto time switch is open, pls close this switch.");
            return -2147482648;
        }
        String zoneStr = this.mTimeZoneHashMap.get(Integer.valueOf(value));
        TimeZone zone = TimeZone.getTimeZone(zoneStr);
        String[] zoneIdArray = TimeZone.getAvailableIDs(zone.getRawOffset());
        int length = zoneIdArray == null ? 0 : zoneIdArray.length;
        if (length == 0) {
            Log.w(TAG, "setTimeZone error, find no zoneId, pls check system zone id config.");
            return -2147482648;
        }
        ManualTimeZoneSuggestion suggestion = TimeZoneDetector.createManualTimeZoneSuggestion(zoneIdArray[0], TAG);
        this.mTimeZoneDetector.suggestManualTimeZone(suggestion);
        return 0;
    }

    private boolean timeZoneCheck(int value) {
        if (value < 0 || value > 23) {
            return false;
        }
        return true;
    }

    public int getTimeZone() {
        Log.d(TAG, "getTimeZone is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        int secondsDiff = ZonedDateTime.now().getOffset().getTotalSeconds();
        int zoneInfo = secondsDiff / 3600;
        Log.d(TAG, "getTimeZone, zoneInfo:" + zoneInfo);
        if (zoneInfo >= 0 && zoneInfo <= 12) {
            return zoneInfo;
        }
        if (zoneInfo >= WEST_ZONE_MIN && zoneInfo < 0) {
            return 12 - zoneInfo;
        }
        Log.w(TAG, "getTimeZone illegal zoneInfo:" + zoneInfo);
        return -2147482648;
    }

    public int getDateFormat() {
        Log.d(TAG, "getDateFormat is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return 1;
    }

    public int getSummertimeState() {
        Log.d(TAG, "getSummertimeState is execute. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        TimeZone timeZone = TimeZone.getDefault();
        Date nowDate = new Date();
        if (timeZone.useDaylightTime() && timeZone.inDaylightTime(nowDate)) {
            return 1;
        }
        return 0;
    }

    public int setFullTimeToECM(int year, int month, int day, int hour, int minute, int second) {
        Log.d(TAG, "setFullTimeToECM is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        return 0;
    }

    public void setAllStatus() {
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        Log.d(TAG, "setAllStatus is empty implementation. ");
    }

    public void getAllStatus() {
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        Log.d(TAG, "getAllStatus is empty implementation. ");
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        Log.d(TAG, "postEvent is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        return false;
    }

    public int getType() {
        Log.d(TAG, "getType is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return 0;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return null;
    }

    public void registerListener(AbsBYDAutoTimeListener listener) {
        Log.d(TAG, "registerListener: is run");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
        boolean result = this.mTimeListenerHelper.registerListener(listener);
        if (result && !this.isReceiverRegistered) {
            this.isReceiverRegistered = true;
            this.mContext.registerReceiver(this.mTimeChangeReceiver, this.mTimeChangeIntentFilter);
            this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(BYDAutoSysPropertiesDevice.TIME_12_24), true, this.mObserver);
        }
    }

    public void registerListener(AbsBYDAutoTimeListener listener, int[] featureIDs) {
        Log.d(TAG, "registerListener two para is run. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
    }

    public void unregisterListener(AbsBYDAutoTimeListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
        boolean result = this.mTimeListenerHelper.unregisterListener(listener);
        if (this.isReceiverRegistered && result && this.mTimeListenerHelper.getListenerSize() == 0) {
            this.isReceiverRegistered = false;
            this.mContext.unregisterReceiver(this.mTimeChangeReceiver);
            this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        }
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
                    if (eventType == 167) {
                        bydAutoEventValue.intValue = getTimeFormat();
                        sparseArray.put(167, Integer.valueOf(bydAutoEventValue.intValue));
                    } else {
                        Log.d(TAG, "case fail ! please check out");
                    }
                    int index = 0;
                    for (int i : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                            index++;
                        } catch (NullPointerException errors) {
                            errors.printStackTrace();
                        }
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bydAutoEventValue;
    }

    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        if (eventTypes.length < 1) {
            eventValue.intValue = -2147482648;
        } else {
            for (int eventType : eventTypes) {
                if (eventType > 0) {
                    switch (eventType) {
                        case 168:
                            setTime(eventValue.intArrayValue[0], eventValue.intArrayValue[1], eventValue.intArrayValue[2]);
                            Log.d(TAG, "setTime" + eventValue.intValue);
                            break;
                        case 169:
                            setTime(eventValue.intArrayValue[0], eventValue.intArrayValue[1], eventValue.intArrayValue[2]);
                            Log.d(TAG, "setTime" + eventValue.intValue);
                            break;
                        case 170:
                            setTime(eventValue.intArrayValue[0], eventValue.intArrayValue[1], eventValue.intArrayValue[2]);
                            Log.d(TAG, "setTime" + eventValue.intValue);
                            break;
                    }
                } else {
                    eventValue.intValue = -2147482648;
                }
            }
        }
        return 1;
    }
}
