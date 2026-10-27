package com.ts.car.timesync;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IHwBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.ArrayMap;
import com.ts.car.input.ConfigParser;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Locale;
import ts.car.service.time.TimeSyncConstant;
import vendor.ts.timesync.V1_0.ITimeUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public class TimeSyncJudgment extends ITimeUpdateListener.Stub implements IHwBinder.DeathRecipient {
    private static final String ACTION_SHUTDOWN_FOR_UT = "timesync.test.action.ACTION_SHUTDOWN";
    private static final int CONNECT_INTERVAL = 1000;
    private static final String CURRENT_PRIORITY_PROPERTY = "sys.current.priority";
    private static final String LAST_UTC_PROPERTY = "persist.sys.last_utc";
    private static final int MAXC_CONNECT_TIMES = 10;
    private static final String TAG = "TimeSyncJudgment";
    private static final String TIME_SOURCE_LOCATION = "CLOCK_SOURCE_TYPE_";
    private static final String TIME_SYNC_PREFIX = "CLOCK_SOURCE_TYPE_";
    private static final int TYPE_MCU = 6;
    private static volatile TimeSyncJudgment sInstance = null;
    private AlarmManager mAlarmManager;
    private ClockSourceFactory mClockFactory;
    private Context mContext;
    private boolean mEnableResyncMcu;
    private boolean mEnableSetKernelTime;
    private boolean mEnableSetKernelTimeZone;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mHasRtc;
    private TimeUpdateListener mListener;
    private int mSkewThreshold;
    private McuClockSource mcuClockSource;
    private int mConnectTime = 0;
    private int mCurrentPriority = 0;
    private final Object mLock = new Object();
    private ArrayMap<String, Integer> mSourceDefineMap = new ArrayMap<>();
    private final IntentFilter mTimeChangeIntentFilter = new IntentFilter();
    private ArrayMap<Integer, ClockSourceInfo> mSourcePriorityMap = new ArrayMap<>();
    private TimeSyncReceiver mReceiver = new TimeSyncReceiver();
    private final BroadcastReceiver mTimeChangeReceiver = new BroadcastReceiver() { // from class: com.ts.car.timesync.TimeSyncJudgment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtils.debug(TimeSyncJudgment.TAG, "mTimeChangeReceiver-onReceive, action:" + intent.getAction());
            TimeSyncJudgment.this.mcuClockSource.setTime(System.currentTimeMillis());
        }
    };

    private TimeSyncJudgment(Context context) {
        this.mContext = context;
    }

    public void serviceDied(long cookie) {
        LogUtils.debug(TAG, "serviceDied() ");
    }

    public static TimeSyncJudgment getInstance(Context context) {
        if (sInstance == null) {
            synchronized (TimeSyncJudgment.class) {
                if (sInstance == null) {
                    sInstance = new TimeSyncJudgment(context);
                }
            }
        }
        return sInstance;
    }

    public void release() {
        this.mClockFactory.release();
    }

    public void init() {
        LogUtils.debug(TAG, "init start.");
        this.mClockFactory = new ClockSourceFactory(this.mContext);
        String priority = SystemProperties.get(CURRENT_PRIORITY_PROPERTY);
        if (priority != null && !priority.isEmpty()) {
            this.mCurrentPriority = Integer.parseInt(priority);
        }
        loadClockSourceDefine();
        loadSourceDate();
        this.mAlarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        registShutDown();
        registerTimeChangeReceiver();
        this.mcuClockSource = this.mClockFactory.getMcuClockSource();
    }

    private void registShutDown() {
        if (this.mHasRtc) {
            LogUtils.info(TAG, "Has RTC!!! Don't regist shut down broadcase.");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        intentFilter.addAction(ACTION_SHUTDOWN_FOR_UT);
        intentFilter.setPriority(CONNECT_INTERVAL);
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
    }

    private void registerTimeChangeReceiver() {
        this.mTimeChangeIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.mContext.registerReceiver(this.mTimeChangeReceiver, this.mTimeChangeIntentFilter);
    }

    public class TimeSyncReceiver extends BroadcastReceiver {
        public TimeSyncReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtils.debug(TimeSyncJudgment.TAG, "onReceive action:" + action);
            TimeSyncJudgment.this.saveTimeForHal();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveTimeForHal() {
        try {
            SystemProperties.set(LAST_UTC_PROPERTY, String.valueOf(currentTimeMillis()));
        } catch (Exception exception) {
            exception.printStackTrace();
            LogUtils.err(TAG, "Set last UTC [persist.vendor.last_utc] failed!");
        }
    }

    private void loadSourceDate() {
        String source;
        Integer sourceTypeValue;
        TimeSyncData data = DataLoader.getInstance().loadDataFromJson();
        this.mSkewThreshold = data.getSkewThreshold();
        this.mHasRtc = data.getHasRtc();
        this.mEnableResyncMcu = data.getEnableReSync();
        this.mEnableSetKernelTime = data.getEnableSetKernelTime();
        this.mEnableSetKernelTimeZone = data.getEnableSetKernelTimeZone();
        LogUtils.debug(TAG, "mEnableSetKernelTime:" + this.mEnableSetKernelTime);
        LogUtils.debug(TAG, "mEnableSetKernelTimeZone:" + this.mEnableSetKernelTimeZone);
        ArrayList<ClockSourceInfo> sourceListInfo = data.getClockSourceList();
        synchronized (this.mSourcePriorityMap) {
            if (sourceListInfo != null) {
                this.mSourcePriorityMap.clear();
                for (int i = 0; i < sourceListInfo.size(); i++) {
                    if (sourceListInfo.get(i).getIsEnable() && (sourceTypeValue = this.mSourceDefineMap.get((source = sourceListInfo.get(i).getName()))) != null) {
                        int sourceType = sourceTypeValue.intValue();
                        this.mSourcePriorityMap.put(Integer.valueOf(sourceType), sourceListInfo.get(i));
                        if (sourceListInfo.get(i).getIsInternalSource()) {
                            createInternalSource(source, sourceListInfo.get(i).getPriority());
                        }
                    }
                }
            }
        }
    }

    private void createInternalSource(String name, int priority) {
        LogUtils.debug(TAG, "source name is " + name + " priority is " + priority);
        this.mClockFactory.createClockSource(name, priority);
    }

    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // vendor.ts.timesync.V1_0.ITimeUpdateListener
    public void onTimeUpdate(long millis, String timeZone, int type) throws RemoteException {
        LogUtils.debug(TAG, "onTimeUpdate() time=" + millis + " timeZone=" + timeZone + " type=" + type);
        if (6 == type) {
            setTime(millis, type, timeZone);
        } else if (8 == type) {
            resyncTime(millis);
        }
    }

    private void loadClockSourceDefine() {
        LogUtils.debug(TAG, "loadClockSourceDefine()");
        try {
            synchronized (this.mSourceDefineMap) {
                this.mSourceDefineMap.clear();
                Field[] fields = TimeSyncConstant.class.getFields();
                if (fields.length <= 0) {
                    return;
                }
                for (Field field : fields) {
                    if (field.getType() == Integer.TYPE && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.getName().contains("CLOCK_SOURCE_TYPE_")) {
                        String name = field.getName();
                        String name2 = name.replaceAll("CLOCK_SOURCE_TYPE_", ConfigParser.EMPTY).toLowerCase(Locale.US);
                        int val = ((Integer) field.get(null)).intValue();
                        this.mSourceDefineMap.put(name2, Integer.valueOf(val));
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public void setTime(long time, int type, String timeZone) {
        LogUtils.debug(TAG, "setTime start. time is " + time + " type is " + this.mSourceDefineMap.get(Integer.valueOf(type)));
        synchronized (this.mLock) {
            if (judgeSourcePriority(type)) {
                setKernelTime(time);
                if (this.mListener != null) {
                    this.mListener.onTimeUpdate(time, timeZone);
                }
                if (type != 6) {
                    this.mcuClockSource.setTime(time);
                }
                setKernelTimeZone(timeZone);
            }
        }
    }

    private void setKernelTime(long time) {
        if (!this.mEnableSetKernelTime) {
            LogUtils.info(TAG, "Unable to set kernel time!");
            return;
        }
        if (this.mAlarmManager == null) {
            this.mAlarmManager = (AlarmManager) this.mContext.getSystemService("alarm");
        }
        this.mAlarmManager.setTime(time);
    }

    public int getCurrentPriority() {
        return this.mCurrentPriority;
    }

    public void setTimeUpdateListener(TimeUpdateListener listener) {
        this.mListener = listener;
    }

    private boolean judgeSkew(long time) {
        long skew = Math.abs(currentTimeMillis() - time);
        LogUtils.debug(TAG, "skew = " + skew + "mSkewThreshold = " + this.mSkewThreshold);
        return skew < ((long) this.mSkewThreshold);
    }

    private void setKernelTimeZone(String timeZone) {
        if (!this.mEnableSetKernelTimeZone) {
            LogUtils.info(TAG, "Unable to set kernel time zone!");
            return;
        }
        LogUtils.info(TAG, "timeZone is " + timeZone);
        if (timeZone == null || timeZone.isEmpty()) {
            return;
        }
        try {
            this.mAlarmManager.setTimeZone(timeZone);
        } catch (IllegalArgumentException ex) {
            LogUtils.warning(TAG, "set time zone error!!!", ex);
            ex.printStackTrace();
        }
    }

    private boolean judgeSourcePriority(int type) {
        if (this.mSourcePriorityMap.isEmpty()) {
            LogUtils.err(TAG, "mSourcePriorityMap is empty!!!");
            return false;
        }
        if (!this.mSourcePriorityMap.containsKey(Integer.valueOf(type))) {
            LogUtils.info(TAG, "mSourcePriorityMap do not contain " + type);
            return false;
        }
        if (type == 1) {
            LogUtils.info(TAG, "force update!!!");
            return true;
        }
        int priority = this.mSourcePriorityMap.get(Integer.valueOf(type)).getPriority();
        if (priority < this.mCurrentPriority) {
            LogUtils.info(TAG, "priority < mCurrentPriority");
            LogUtils.info(TAG, "current priority is " + this.mCurrentPriority + ", priority is " + priority);
            return false;
        }
        setCurrentPriority(priority);
        return true;
    }

    private void setCurrentPriority(int priority) {
        LogUtils.info(TAG, "setCurrentPriority  priority is " + priority);
        this.mCurrentPriority = priority;
        try {
            SystemProperties.set(CURRENT_PRIORITY_PROPERTY, String.valueOf(priority));
        } catch (Exception e) {
            LogUtils.err(TAG, "Set current priority[sys.current.priority] failed!");
        }
    }

    private void resyncTime(long time) {
        if (!this.mEnableResyncMcu) {
            LogUtils.info(TAG, "mEnableResyncMcu is false!!!");
        } else if (!judgeSkew(time)) {
            this.mcuClockSource.setTime(System.currentTimeMillis());
        }
    }
}
