package com.ts.ota.otasdkmgr.impl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.ota.CarVendorOtaMgr;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import com.ts.qnxmanager.QnxManager;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AppointTime {
    private static final String ACTION_NEXT_WAKEUP = "android.intent.action.ACTION_NEXT_WAKEUP";
    private static final int APPOINTMENTTIME_CMD = 13;
    private static final int COUNTDOWN_APPOINTMENT_TIME = 1;
    private static final int DECEMBER = 11;
    private static final int HOURS_OF_23_MILLIS = 82800000;
    private static final int HOUR_2 = 2;
    private static final int HOUR_3 = 3;
    private static final int INVALID_TIME = 255;
    private static final int JANUARY = 0;
    private static final int MAX_STR_CYCLE = 3;
    private static final int MILLISECOND_OF_HOUR = 3600000;
    private static final int MINUTES_OF_10_MILLIS = 600000;
    private static final int MINUTE_20 = 20;
    private static final int MINUTE_30 = 30;
    private static final int MINUTE_40 = 40;
    private static final int ONE = 1;
    private static final String REAL_TIME = "realtime";
    private static final int REBOOT = 3;
    private static final String REBOOT_SRC = "reboot";
    private static final String SAVE_REALTIME = "saved";
    private static final int STAMP_APPOINTMENT_TIME = 0;
    private static final String TAG = "AppointTime";
    private static final int TYPE_CLR_APPOINTMENT = 3;
    private static final int TYPE_GET_APPOINTMENT = 2;
    private static final int TYPE_OTA = 8;
    private static final int TYPE_SET_APPOINTMENT = 1;
    private static final int WAKEUP_ONLY_PAD = 0;
    private static final int WAKEUP_WHOLE_CAR = 1;
    private static final int ZERO = 0;
    private static SharedPreferences.Editor appointTimeEditor;
    private static AppointTime mAppointTime;
    private static QnxManager qnxMgr;
    private static SharedPreferences.Editor realtimeEditor;
    private static SharedPreferences sharedAppointTime;
    private static SharedPreferences sharedRealtime;
    private static SharedPreferences sharedWakeupType;
    private static SharedPreferences.Editor wakeupTypeEditor;
    private final AlarmManager alarmMgr;
    private Car mCarClient;
    private final CarPowerEventListener mCarPowerEventListener = new CarPowerEventListener() { // from class: com.ts.ota.otasdkmgr.impl.AppointTime.2
        public void onCarPowerWorkModeChangeEvent(CarPowerWorkModeStatus status) {
            if (status.getCarPowerWorkModeStatusEnum().getVal() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.IVI_PM_STR_SUSPENDING.getVal()) {
                Log.w(AppointTime.TAG, "onCarPowerWorkModeChangeEvent: enter str");
                AppointTime.this.setRebootTime();
            } else if (status.getCarPowerWorkModeStatusEnum().getVal() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.STR_SUSPEND_EXIT.getVal()) {
                Log.w(AppointTime.TAG, "onCarPowerWorkModeChangeEvent: exit str");
                AppointTime.this.setRebootTime();
            }
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus status) {
        }

        public void onCarPowerServiceConnected() {
        }

        public void onCarPowerServiceDisconnected() {
        }

        public void onCarPowerEvent(int type, int state, CarPowerData data) {
        }

        public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
        }
    };
    private CarPowerManager mCarPowerMgr;
    private CarVendorOtaMgr mCarVendorOtaMgr;
    private Context mContext;
    private static final String CHARGE_SRC = "charge";
    private static final String UPGRADE_SRC = "upgrade";
    private static final String[] wakeupSrcList = {CHARGE_SRC, UPGRADE_SRC};
    private static final Map<String, PendingIntent> intentMap = new HashMap();

    private AppointTime(Context context) {
        this.mContext = context;
        initAppointTimeSharedPref();
        saveRealtime(getCurrentRealtime());
        initCar();
        this.alarmMgr = (AlarmManager) this.mContext.getSystemService("alarm");
        qnxMgr = QnxManager.getInstance();
        CarPowerManager carPowerManager = CarPowerManager.getInstance(this.mContext, this.mCarPowerEventListener);
        this.mCarPowerMgr = carPowerManager;
        carPowerManager.connect();
        Intent strInt = new Intent(ACTION_NEXT_WAKEUP, null, context, AlarmReceiver.class);
        strInt.setType(REBOOT_SRC);
        PendingIntent strPendingInt = PendingIntent.getBroadcast(context, 0, strInt, 134217728);
        intentMap.put(REBOOT_SRC, strPendingInt);
        for (String wakeupSrc : wakeupSrcList) {
            Intent intent = new Intent(ACTION_NEXT_WAKEUP, null, context, AlarmReceiver.class);
            intent.setType(wakeupSrc);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 134217728);
            intentMap.put(wakeupSrc, pendingIntent);
            Log.w(TAG, "init intent:" + intent + " " + pendingIntent);
        }
    }

    public static synchronized AppointTime getInstance(Context context) {
        if (mAppointTime == null) {
            mAppointTime = new AppointTime(context);
        }
        return mAppointTime;
    }

    private void initCar() {
        Car carCreateCar = Car.createCar(this.mContext, new CarConnectionCallback() { // from class: com.ts.ota.otasdkmgr.impl.AppointTime.1
            public void onConnected(Car car) {
                try {
                    AppointTime.this.mCarVendorOtaMgr = (CarVendorOtaMgr) car.getCarManager("ota");
                    Log.w(AppointTime.TAG, "onConnected: mCarVendorOtaMgr:" + AppointTime.this.mCarVendorOtaMgr);
                    AppointTime.this.setRebootTime();
                } catch (CarNotConnectedException e) {
                    Log.e(AppointTime.TAG, "onConnected: error");
                }
            }

            public void onDisconnected(Car car) {
            }
        });
        this.mCarClient = carCreateCar;
        carCreateCar.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRebootTime() {
        Log.w(TAG, "setRebootTime: start");
        try {
            if (this.mCarVendorOtaMgr != null) {
                int strCnt = this.mCarVendorOtaMgr.getStrResetCycleReqR();
                Log.w(TAG, "setRebootTime: remain cycle=" + strCnt);
                if (strCnt >= 3) {
                    Log.w(TAG, "setRebootTime: match max str,no need to set reboot time");
                    return;
                }
            }
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "setRebootTime: car not connect");
        }
        long upTimeDiff = getCurrentRealtime() - getSharedRealtime();
        long remainTime = 82800000 - upTimeDiff;
        Log.w(TAG, "setRebootTime: remainTime:" + remainTime);
        Calendar calendar = Calendar.getInstance();
        Log.w(TAG, "setRebootTime: current time:" + new SimpleDateFormat("MM-dd HH-mm-ss").format(Long.valueOf(calendar.getTimeInMillis())));
        if (remainTime > 0) {
            Log.w(TAG, "setRebootTime: need set reboot time after 23 hours");
            calendar.add(14, (int) remainTime);
        }
        Log.w(TAG, "setRebootTime: after time:" + new SimpleDateFormat("MM-dd HH-mm-ss").format(Long.valueOf(calendar.getTimeInMillis())));
        int targetHour = calendar.get(11);
        int targetMinute = calendar.get(12);
        if (targetHour > 2 || (targetHour == 2 && targetMinute >= 30)) {
            Log.w(TAG, "setRebootTime: date need add 1 day");
            calendar.add(5, 1);
        }
        int minutes = getMinuteOfRebootTime(calendar.get(5));
        if (minutes == 255) {
            removeStrWakeupTime();
            return;
        }
        calendar.set(11, 2);
        calendar.set(12, minutes);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Log.w(TAG, "setRebootTime: target time:" + new SimpleDateFormat("MM-dd HH-mm-ss").format(Long.valueOf(calendar.getTimeInMillis())));
        setStrWakeupTime(calendar);
        Log.w(TAG, "setRebootTime: end");
    }

    private int getMinuteOfRebootTime(int rebootDay) {
        Log.w(TAG, "getMinuteOfRebootTime");
        Map<String, Long> timeMap = getAllAppointTime();
        if (timeMap.isEmpty()) {
            Log.e(TAG, "getMinuteOfRebootTime: has no appointment time");
            return 30;
        }
        Collection<Long> values = timeMap.values();
        Object[] list = values.toArray();
        for (Object time : list) {
            Date date = new Date(((Long) time).longValue());
            int day = date.getDate();
            int hour = date.getHours();
            int minute = date.getMinutes();
            Log.w(TAG, "getMinuteOfRebootTime: find the date:[hour:" + hour + ", minutes:" + minute + ", day:" + day + ", rebootDay:" + rebootDay + "]");
            if (hour == 2 && day == rebootDay) {
                if (20 > minute || minute > 30) {
                    return (30 >= minute || minute > MINUTE_40) ? 30 : 20;
                }
                return 255;
            }
        }
        return 30;
    }

    public void setAppointmentTime(int day, int hour, int minute, int wakeupSrc, int wakeupType) {
        String typeStr = intToAppointSrc(wakeupSrc);
        Log.w(TAG, "setAppointmentTime intToAppointSrc typeStr:" + typeStr);
        if (day == 0) {
            removeAppointTime(typeStr);
        } else {
            long alarmTime = persistTime(day, hour, minute, typeStr, wakeupType);
            setAlarm(alarmTime, typeStr);
        }
        sendWakeUpTime();
    }

    private void setStrWakeupTime(Calendar calendar) {
        Log.w(TAG, "setStrWakeupTime");
        int day = calendar.get(5);
        int hour = calendar.get(11);
        int minute = calendar.get(12);
        long alarmTime = getAbsoluteTime(day, hour, minute);
        setAlarm(alarmTime, REBOOT_SRC);
        Log.w(TAG, "setStrWakeupTime: alarm: day-> " + day + " hours-> " + hour + " minutes-> " + minute);
        long time = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("setStrWakeupTime: current time:");
        sb.append(time);
        Log.w(TAG, sb.toString());
        long countDownInSec = (alarmTime - time) / 1000;
        int totalMin = ((int) countDownInSec) / 60;
        int cntDwnMin = totalMin % 60;
        int totalHour = totalMin / 60;
        int cntDwnHour = totalHour % 24;
        int cntDwnDay = totalHour / 24;
        sendWakeUpTimeToMcu(cntDwnDay, cntDwnHour, cntDwnMin, 0, 1, 1);
        Log.w(TAG, "setStrWakeupTime: strDay-> " + cntDwnDay + " strHour-> " + cntDwnHour + " strMin-> " + cntDwnMin + " countdown-> " + countDownInSec);
    }

    private void initAppointTimeSharedPref() {
        sharedAppointTime = this.mContext.getSharedPreferences("appointTime", 0);
        sharedWakeupType = this.mContext.getSharedPreferences("wakeupType", 0);
        sharedRealtime = this.mContext.getSharedPreferences(REAL_TIME, 0);
        appointTimeEditor = sharedAppointTime.edit();
        wakeupTypeEditor = sharedWakeupType.edit();
        realtimeEditor = sharedRealtime.edit();
    }

    private void saveRealtimeFirstly() {
        int value = sharedRealtime.getInt(SAVE_REALTIME, 0);
        if (value != 1) {
            Log.w(TAG, "saveRealtimeFirstly");
            saveRealtime(getCurrentRealtime());
            realtimeEditor.putInt(SAVE_REALTIME, 1);
            realtimeEditor.apply();
        }
    }

    public void recoverRealtimeStatus() {
        Log.w(TAG, "recoverRealtimeStatus");
        realtimeEditor.putInt(SAVE_REALTIME, 0);
        realtimeEditor.apply();
    }

    public Map<String, Long> getAllAppointTime() {
        return sharedAppointTime.getAll();
    }

    private void saveAppointTime(String name, long value) {
        appointTimeEditor.putLong(name, value);
        appointTimeEditor.apply();
    }

    private void saveWakeupType(String name, int value) {
        wakeupTypeEditor.putInt(name, value);
        wakeupTypeEditor.apply();
    }

    private void saveRealtime(long time) {
        Log.w(TAG, "saveRealtime: " + time);
        realtimeEditor.putLong(REAL_TIME, time);
        realtimeEditor.apply();
    }

    private long getSharedRealtime() {
        long value = sharedRealtime.getLong(REAL_TIME, 0L);
        Log.w(TAG, "getSharedRealtime: " + value);
        return value;
    }

    public void setNextTimeReboot() {
        Log.w(TAG, "setNextTimeReboot");
        saveRealtime(getCurrentRealtime());
        setRebootTime();
    }

    private long getCurrentRealtime() {
        long time = SystemClock.elapsedRealtime();
        Log.w(TAG, "getCurrentRealtime: " + time);
        return time;
    }

    public void removeAppointTime(String name) {
        PendingIntent intent = getPendingIntent(name);
        AlarmManager alarmManager = this.alarmMgr;
        if (alarmManager != null) {
            alarmManager.cancel(intent);
        }
        appointTimeEditor.remove(name);
        appointTimeEditor.apply();
        Log.w(TAG, "removeAppointTime: " + name + " has removed");
    }

    private void removeStrWakeupTime() {
        PendingIntent intent = intentMap.get(REBOOT_SRC);
        AlarmManager alarmManager = this.alarmMgr;
        if (alarmManager != null) {
            alarmManager.cancel(intent);
        }
        sendWakeUpTimeToMcu(0, 0, 0, 0, 3, 1);
    }

    private String intToAppointSrc(int type) {
        int num = 1;
        while (true) {
            String[] strArr = wakeupSrcList;
            if (num <= strArr.length) {
                if (type != num) {
                    num++;
                } else {
                    return strArr[num - 1];
                }
            } else {
                return strArr[0];
            }
        }
    }

    private PendingIntent getPendingIntent(String type) {
        Set<String> keys = intentMap.keySet();
        for (String key : keys) {
            if (type.equals(key)) {
                return intentMap.get(key);
            }
        }
        return intentMap.get(wakeupSrcList[0]);
    }

    private int getNextWakeupType(long time, Map<String, Long> alarmMap) {
        String wakeupKey = "";
        Set<String> keys = alarmMap.keySet();
        Iterator<String> it = keys.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String key = it.next();
            if (alarmMap.get(key).equals(Long.valueOf(time))) {
                wakeupKey = key;
                break;
            }
        }
        int wakeupType = sharedWakeupType.getInt(wakeupKey, 0);
        Log.w(TAG, "getNextWakeupType: wakeupKey->" + wakeupKey + " wakeupType->" + wakeupType);
        return wakeupType;
    }

    private long persistTime(int day, int hours, int minutes, String type, int wakeup) {
        long savedTime = getAbsoluteTime(day, hours, minutes);
        saveAppointTime(type, savedTime);
        saveWakeupType(type, wakeup);
        Log.w(TAG, "persistTime: type->" + type + " time->" + savedTime);
        return savedTime;
    }

    private long getAbsoluteTime(int day, int hours, int minutes) {
        Date date = new Date();
        int curYear = date.getYear();
        int curMonth = date.getMonth();
        int result = date.compareTo(new Date(curYear, curMonth, day, hours, minutes));
        if (result > 0 && (curMonth = curMonth + 1) > 11) {
            curMonth = 0;
            curYear++;
        }
        Log.w(TAG, "getAbsoluteTime: year->" + (curYear + 1900) + ", month->" + (curMonth + 1) + ", day->" + day + ", hours->" + hours + ", mintues->" + minutes);
        date.setYear(curYear);
        date.setMonth(curMonth);
        date.setDate(day);
        date.setHours(hours);
        date.setMinutes(minutes);
        return date.getTime();
    }

    public void setAlarm(long time, String type) {
        PendingIntent intent = getPendingIntent(type);
        AlarmManager alarmManager = this.alarmMgr;
        if (alarmManager != null) {
            alarmManager.setExact(1, time, intent);
            Log.w(TAG, "setAlarm: type(" + type + "),time(" + time + ") has been set alarm");
            return;
        }
        Log.e(TAG, "setAlarm: alarmMgr is null");
    }

    public void sendWakeUpTime() {
        long wakeUpTime;
        if (qnxMgr == null) {
            Log.e(TAG, "sendWakeUpTime: qnxMgr is null");
            return;
        }
        Map<String, Long> alarmMap = getAllAppointTime();
        if (alarmMap.isEmpty()) {
            Log.e(TAG, "sendWakeUpTime: has no appointment time,cancel RTC wakeup");
            sendWakeUpTimeToMcu(0, 0, 0, 0, 3, 0);
            return;
        }
        if (alarmMap.size() == 1) {
            Set<String> key = alarmMap.keySet();
            Object[] obj = key.toArray();
            wakeUpTime = alarmMap.get((String) obj[0]).longValue();
        } else {
            Collection<Long> values = alarmMap.values();
            Object[] list = values.toArray();
            Arrays.sort(list);
            wakeUpTime = ((Long) list[0]).longValue();
        }
        int wakeupType = getNextWakeupType(wakeUpTime, alarmMap);
        Date date = new Date(wakeUpTime);
        int day = date.getDate();
        int hour = date.getHours();
        int minute = date.getMinutes();
        sendWakeUpTimeToMcu(day, hour, minute, wakeupType, 1, 0);
        Log.w(TAG, "sendWakeUpTime: day-> " + day + " hours-> " + hour + " minutes-> " + minute + " wakeupType-> " + wakeupType);
    }

    private void sendWakeUpTimeToMcu(int day, int hours, int minutes, int wakeupType, int type, int mode) {
        Log.w(TAG, "sendWakeUpTimeToMcu");
        if (qnxMgr == null) {
            Log.e(TAG, "sendWakeUpTimeToMcu: qnxMgr is null");
            return;
        }
        if (day < 0 || hours < 0 || minutes < 0) {
            Log.e(TAG, "sendWakeUpTimeToMcu: failed, illegal time!");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("appointmentDay", day);
        bundle.putInt("appointmentHour", hours);
        bundle.putInt("appointmentMin", minutes);
        bundle.putInt("appointmentTimeType", type);
        bundle.putInt("wakeUpStatus", wakeupType);
        bundle.putInt("appointmentTimeMode", mode);
        qnxMgr.sendMessage(8, 13, null, bundle);
        Log.w(TAG, "sendWakeUpTimeToMcu: finish");
    }
}
