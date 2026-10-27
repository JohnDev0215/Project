package android.hardware.bydauto.time.tsimpl;

import android.app.timedetector.TimeDetector;
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
    public static final String TAG = "TimeManagerImpl";
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
        public void onChange(boolean z3) {
            Log.d(TimeManagerImpl.TAG, "m12And24HourFormatObserver-value changed.");
            TimeManagerImpl.this.mTimeListenerHelper.notifyTimeFormatChanged(TimeManagerImpl.this.getTimeFormat());
        }
    };

    public TimeManagerImpl(Context context) {
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

    private void addTimeZoneData() {
        this.mTimeZoneHashMap.put(0, "GMT+00:00");
        this.mTimeZoneHashMap.put(1, "GMT+01:00");
        this.mTimeZoneHashMap.put(2, "GMT+02:00");
        this.mTimeZoneHashMap.put(3, "GMT+03:00");
        this.mTimeZoneHashMap.put(4, "GMT+04:00");
        this.mTimeZoneHashMap.put(5, "GMT+05:00");
        this.mTimeZoneHashMap.put(6, "GMT+06:00");
        this.mTimeZoneHashMap.put(7, "GMT+07:00");
        this.mTimeZoneHashMap.put(8, "GMT+08:00");
        this.mTimeZoneHashMap.put(9, "GMT+09:00");
        this.mTimeZoneHashMap.put(10, "GMT+10:00");
        this.mTimeZoneHashMap.put(11, "GMT+11:00");
        this.mTimeZoneHashMap.put(12, "GMT+12:00");
        this.mTimeZoneHashMap.put(13, "GMT-01:00");
        this.mTimeZoneHashMap.put(14, "GMT-02:00");
        this.mTimeZoneHashMap.put(15, "GMT-03:00");
        this.mTimeZoneHashMap.put(16, "GMT-04:00");
        this.mTimeZoneHashMap.put(17, "GMT-05:00");
        this.mTimeZoneHashMap.put(18, "GMT-06:00");
        this.mTimeZoneHashMap.put(19, "GMT-07:00");
        this.mTimeZoneHashMap.put(20, "GMT-08:00");
        this.mTimeZoneHashMap.put(21, "GMT-09:00");
        this.mTimeZoneHashMap.put(22, "GMT-10:00");
        this.mTimeZoneHashMap.put(23, "GMT-11:00");
    }

    private final boolean checkSetDateParams(int i3, int i4, int i5, int i6) {
        return yearCheck(i3) && monthCheck(i4) && dayCheck(i5);
    }

    private final boolean checkSetTimeParams(int i3, int i4, int i5) {
        return hourCheck(i3) && minuteCheck(i4) && secondCheck(i5);
    }

    private final boolean dayCheck(int i3) {
        return i3 >= 1 && i3 <= 31;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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

    private final boolean hourCheck(int i3) {
        return i3 >= 0 && i3 <= 23;
    }

    private final boolean minuteCheck(int i3) {
        return i3 >= 0 && i3 <= 59;
    }

    private final boolean monthCheck(int i3) {
        return i3 >= 1 && i3 <= 12;
    }

    private final boolean secondCheck(int i3) {
        return i3 >= 0 && i3 <= 59;
    }

    private boolean timeZoneCheck(int i3) {
        return i3 >= 0 && i3 <= 23;
    }

    private final boolean yearCheck(int i3) {
        return i3 >= 2001 && i3 <= 2255;
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i3 : iArr) {
                if (i3 > 0) {
                    if (i3 != 167) {
                        Log.d(TAG, "case fail ! please check out");
                    } else {
                        int timeFormat = getTimeFormat();
                        bYDAutoEventValue.intValue = timeFormat;
                        sparseArray.put(167, Integer.valueOf(timeFormat));
                    }
                    int i4 = 0;
                    for (int i5 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i4] = ((Integer) sparseArray.get(i5)).intValue();
                            i4++;
                        } catch (NullPointerException e3) {
                            e3.printStackTrace();
                        }
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public void getAllStatus() {
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        Log.d(TAG, "getAllStatus is empty implementation. ");
    }

    public int getDateFormat() {
        Log.d(TAG, "getDateFormat is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return 1;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype: is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return 1024;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return null;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return null;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return null;
    }

    public int getSummertimeState() {
        Log.d(TAG, "getSummertimeState is execute. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        TimeZone timeZone = TimeZone.getDefault();
        return (timeZone.useDaylightTime() && timeZone.inDaylightTime(new Date())) ? 1 : 0;
    }

    public int[] getTime() {
        Log.d(TAG, "getTime is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return DateUtils.millisToFormatDate(System.currentTimeMillis());
    }

    public int getTimeFormat() {
        Log.d(TAG, "getTimeFormat is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return TextUtils.equals("12", Settings.System.getString(this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24)) ? 0 : 1;
    }

    public int getTimeZone() {
        Log.d(TAG, "getTimeZone is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        int totalSeconds = ZonedDateTime.now().getOffset().getTotalSeconds() / 3600;
        Log.d(TAG, "getTimeZone, zoneInfo:" + totalSeconds);
        if (totalSeconds >= 0 && totalSeconds <= 12) {
            return totalSeconds;
        }
        if (totalSeconds >= WEST_ZONE_MIN && totalSeconds < 0) {
            return 12 - totalSeconds;
        }
        Log.w(TAG, "getTimeZone illegal zoneInfo:" + totalSeconds);
        return -2147482648;
    }

    public int getType() {
        Log.d(TAG, "getType is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_GET);
        return 0;
    }

    public boolean postEvent(int i3, int i4, int i5, Object obj) {
        Log.d(TAG, "postEvent is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        return false;
    }

    public void registerListener(AbsBYDAutoTimeListener absBYDAutoTimeListener) {
        Log.d(TAG, "registerListener: is run");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
        if (!this.mTimeListenerHelper.registerListener(absBYDAutoTimeListener) || this.isReceiverRegistered) {
            return;
        }
        this.isReceiverRegistered = true;
        this.mContext.registerReceiver(this.mTimeChangeReceiver, this.mTimeChangeIntentFilter);
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(BYDAutoSysPropertiesDevice.TIME_12_24), true, this.mObserver);
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i3 : iArr) {
                if (i3 > 0) {
                    switch (i3) {
                        case 168:
                            int[] iArr2 = bYDAutoEventValue.intArrayValue;
                            setTime(iArr2[0], iArr2[1], iArr2[2]);
                            Log.d(TAG, "setTime" + bYDAutoEventValue.intValue);
                            break;
                        case 169:
                            int[] iArr3 = bYDAutoEventValue.intArrayValue;
                            setTime(iArr3[0], iArr3[1], iArr3[2]);
                            Log.d(TAG, "setTime" + bYDAutoEventValue.intValue);
                            break;
                        case 170:
                            int[] iArr4 = bYDAutoEventValue.intArrayValue;
                            setTime(iArr4[0], iArr4[1], iArr4[2]);
                            Log.d(TAG, "setTime" + bYDAutoEventValue.intValue);
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return 1;
    }

    public void setAllStatus() {
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        Log.d(TAG, "setAllStatus is empty implementation. ");
    }

    public int setDate(int i3, int i4, int i5, int i6) {
        Log.d(TAG, "setDate start, params-year:" + i3 + ", month:" + i4 + ", day:" + i5 + ", weekday:" + i6);
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (!checkSetDateParams(i3, i4, i5, i6)) {
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
        long yearMonthDayToMillis = DateUtils.formatYearMonthDayToMillis(i3, i4, i5);
        this.mTimeDetector.suggestManualTime(TimeDetector.createManualTimeSuggestion(yearMonthDayToMillis, TAG));
        Log.d(TAG, "setDate success, millis:" + yearMonthDayToMillis);
        return 0;
    }

    public int setFullTime(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        Log.d(TAG, "setFullTime is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (2 != i3) {
            Log.w(TAG, "setFullTime mode-" + i3 + " is not supported");
            return -2147482645;
        }
        if (yearCheck(i4) && monthCheck(i5) && dayCheck(i6) && hourCheck(i8) && minuteCheck(i9) && secondCheck(i10)) {
            if (this.mTimeDetector == null) {
                Log.w(TAG, "mTimeDetector is null, check service of TimeDetector");
                return -2147482648;
            }
            if (DateUtils.isAutoTimeSwitchOpen(this.mContext)) {
                Log.w(TAG, "setFullTime error, auto time switch is open, pls close this switch.");
                return -2147482648;
            }
            long dateToMillis = DateUtils.formatDateToMillis(new int[]{i4, i5, i6, i8, i9, i10});
            if (-1 == dateToMillis) {
                Log.w(TAG, "setFullTime error ,maybe params error, check log for more details.");
                return -2147482645;
            }
            this.mTimeDetector.suggestManualTime(TimeDetector.createManualTimeSuggestion(dateToMillis, TAG));
            Log.d(TAG, "setDate success, millis:" + dateToMillis);
            return 0;
        }
        Log.w(TAG, "setFullTime param error, mode:" + i3 + ", year:" + i4 + ", month:" + i5 + ", day:" + i6 + ", hour:" + i8 + ", minute:" + i9 + ", second:" + i10);
        return -2147482645;
    }

    public int setFullTimeToECM(int i3, int i4, int i5, int i6, int i7, int i8) {
        Log.d(TAG, "setFullTimeToECM is empty implementation. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        return 0;
    }

    public int setFullTimeWithTimeFormat(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        Log.d(TAG, "setFullTimeWithTimeFormat is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (1 != i10 && i10 != 0) {
            Log.w(TAG, "setFullTimeWithTimeFormat， unsupported timeFormat:" + i10);
            return -2147482645;
        }
        if (yearCheck(i3) && monthCheck(i4) && dayCheck(i5) && hourCheck(i6) && minuteCheck(i7) && secondCheck(i8)) {
            if (DateUtils.isAutoTimeSwitchOpen(this.mContext)) {
                Log.w(TAG, "setFullTimeWithTimeFormat error, auto time switch is open, pls close this switch.");
                return -2147482648;
            }
            setTimeFormat(i10);
            return setFullTime(2, i3, i4, i5, i9, i6, i7, i8);
        }
        Log.d(TAG, "setFullTime param error, year:" + i3 + ", month:" + i4 + ", day:" + i5 + ", hour:" + i6 + ", minute:" + i7 + ", second:" + i8);
        return -2147482645;
    }

    public int setTime(int i3, int i4, int i5) {
        Log.d(TAG, "setTime start, params-hour:" + i3 + ", minute:" + i4 + ", second:" + i5);
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (!checkSetTimeParams(i3, i4, i5)) {
            Log.w(TAG, "setTime error, param check failed, hour:" + i3 + ", minute:" + i4 + ", second:" + i5);
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
        long hourMinuteSecondToMillis = DateUtils.formatHourMinuteSecondToMillis(i3, i4, i5);
        this.mTimeDetector.suggestManualTime(TimeDetector.createManualTimeSuggestion(hourMinuteSecondToMillis, TAG));
        Log.d(TAG, "setTime success, millis:" + hourMinuteSecondToMillis);
        return 0;
    }

    public int setTimeFormat(int i3) {
        String str;
        Log.d(TAG, "setTimeFormat is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (i3 == 0) {
            str = "12";
        } else {
            if (1 != i3) {
                Log.w(TAG, "setTimeFormat with illegal value:" + i3);
                return -2147482645;
            }
            str = "24";
        }
        Settings.System.putString(this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24, str);
        Log.d(TAG, "setTimeFormat success, value:" + i3 + ", format:" + str);
        return 0;
    }

    public int setTimeZone(int i3) {
        Log.d(TAG, "setTimeZone is execute");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_SET);
        if (!timeZoneCheck(i3)) {
            Log.d(TAG, "setTimeZone illegal value:" + i3);
            return -2147482645;
        }
        if (DateUtils.isAutoTimeZoneSwitchOpen(this.mContext)) {
            Log.w(TAG, "setTimeZone error, auto time switch is open, pls close this switch.");
            return -2147482648;
        }
        String[] availableIDs = TimeZone.getAvailableIDs(TimeZone.getTimeZone(this.mTimeZoneHashMap.get(Integer.valueOf(i3))).getRawOffset());
        if ((availableIDs == null ? 0 : availableIDs.length) == 0) {
            Log.w(TAG, "setTimeZone error, find no zoneId, pls check system zone id config.");
            return -2147482648;
        }
        this.mTimeZoneDetector.suggestManualTimeZone(TimeZoneDetector.createManualTimeZoneSuggestion(availableIDs[0], TAG));
        return 0;
    }

    public void unregisterListener(AbsBYDAutoTimeListener absBYDAutoTimeListener) {
        Log.d(TAG, "unregisterListener is run. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
        boolean zUnregisterListener = this.mTimeListenerHelper.unregisterListener(absBYDAutoTimeListener);
        if (this.isReceiverRegistered && zUnregisterListener && this.mTimeListenerHelper.getListenerSize() == 0) {
            this.isReceiverRegistered = false;
            this.mContext.unregisterReceiver(this.mTimeChangeReceiver);
            this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        }
    }

    public void registerListener(AbsBYDAutoTimeListener absBYDAutoTimeListener, int[] iArr) {
        Log.d(TAG, "registerListener two para is run. ");
        enforcePermission(BYDAutoTimeDevice.PERMISSION_BYDAUTO_TIME_COMMON);
    }
}
