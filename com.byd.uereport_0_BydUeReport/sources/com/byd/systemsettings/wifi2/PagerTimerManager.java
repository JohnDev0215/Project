package com.byd.systemsettings.wifi2;

import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.byd.systemsettings.wifi2.callback.TimerTimeoutable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class PagerTimerManager {
    private static final String TAG = "PagerTimerManager";
    private ConcurrentHashMap<String, PagerTimer> pagerTimerMap;

    public class PagerTimer {
        public TimerTimeoutable timeoutable;
        public Timer timer;

        public PagerTimer() {
        }

        public String toString() {
            return "PagerTimer{timeoutable=" + this.timeoutable + ", timer=" + this.timer + '}';
        }
    }

    public static class Singleton {
        private static final PagerTimerManager instance = new PagerTimerManager();

        public static PagerTimerManager get() {
            return instance;
        }
    }

    private String getClassKey(Class<?> cls) {
        return cls.getName() + BydPowerManagerDevice.SEPARATOR_K_V;
    }

    private String getKey(Class<?> cls, int i8) {
        return cls.getName() + BydPowerManagerDevice.SEPARATOR_K_V + i8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimerTimeout(Class<?> cls, int i8) {
        TimerTimeoutable timerTimeoutable;
        PagerTimer pagerTimer = this.pagerTimerMap.get(getKey(cls, i8));
        a.a(TAG, "onTimerTimeout", "cla: " + cls + ", actionCode: " + i8 + ", pt: " + pagerTimer);
        if (pagerTimer == null || (timerTimeoutable = pagerTimer.timeoutable) == null) {
            return;
        }
        timerTimeoutable.onTimerTimeout(i8);
        pagerTimer.timer = null;
    }

    private void setTimeoutable(Class<?> cls, TimerTimeoutable timerTimeoutable) {
        ConcurrentHashMap<String, PagerTimer> concurrentHashMap = this.pagerTimerMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        for (String str : this.pagerTimerMap.keySet()) {
            a.a(TAG, "setTimeoutable", "key: " + str);
            if (str.contains(getClassKey(cls))) {
                this.pagerTimerMap.get(str).timeoutable = timerTimeoutable;
            }
        }
    }

    public void registerTimeoutable(Class<?> cls, TimerTimeoutable timerTimeoutable) {
        ConcurrentHashMap<String, PagerTimer> concurrentHashMap;
        a.a(TAG, "registerTimeoutable", "cla: " + cls + ", timeoutable: " + timerTimeoutable);
        if (cls != null && (concurrentHashMap = this.pagerTimerMap) != null && !concurrentHashMap.isEmpty()) {
            setTimeoutable(cls, timerTimeoutable);
            return;
        }
        a.a(TAG, "registerTimeoutable", "has no timer, so, no need to register, cla: " + cls);
    }

    public void removeTimer(Class<?> cls, int i8) {
        Timer timer;
        ConcurrentHashMap<String, PagerTimer> concurrentHashMap = this.pagerTimerMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        String key = getKey(cls, i8);
        PagerTimer pagerTimer = this.pagerTimerMap.get(key);
        a.a(TAG, "removeTimer", "cla: " + cls + ", actionCode: " + i8 + ", pt: " + pagerTimer);
        if (pagerTimer != null && (timer = pagerTimer.timer) != null) {
            timer.cancel();
            pagerTimer.timer = null;
        }
        this.pagerTimerMap.remove(key);
    }

    public void startTimer(final Class<?> cls, final int i8, TimerTimeoutable timerTimeoutable, long j8) {
        a.a(TAG, "startTimer", "cla: " + cls + ", actionCode: " + i8);
        if (this.pagerTimerMap == null) {
            return;
        }
        String key = getKey(cls, i8);
        PagerTimer pagerTimer = this.pagerTimerMap.get(key);
        if (pagerTimer == null) {
            pagerTimer = new PagerTimer();
            this.pagerTimerMap.put(key, pagerTimer);
        } else {
            Timer timer = pagerTimer.timer;
            if (timer != null) {
                timer.cancel();
            }
        }
        pagerTimer.timeoutable = timerTimeoutable;
        Timer timer2 = new Timer();
        pagerTimer.timer = timer2;
        timer2.schedule(new TimerTask() { // from class: com.byd.systemsettings.wifi2.PagerTimerManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                PagerTimerManager.this.onTimerTimeout(cls, i8);
            }
        }, j8);
    }

    public void unregisterTimeoutable(Class<?> cls) {
        a.a(TAG, "unregisterTimeoutable", "cla: " + cls);
        if (this.pagerTimerMap == null) {
            return;
        }
        setTimeoutable(cls, null);
    }

    private PagerTimerManager() {
        this.pagerTimerMap = new ConcurrentHashMap<>();
    }
}
