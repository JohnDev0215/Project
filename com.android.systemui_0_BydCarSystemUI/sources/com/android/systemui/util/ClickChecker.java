package com.android.systemui.util;

import android.view.View;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ClickChecker {
    private static final long CLICK_COUNT_MAX = 5;
    public static final long CLICK_DOUBLE_LIMIT_TIME = 300;
    private static final long CLICK_LIMIT_TIME = 3000;
    public static final long CLICK_MUTI_LIMIT_TIME = 1200;
    private static final String TAG = "ClickChecker";
    private static volatile int clickCount = 0;
    private static int clickCountDouble = 0;
    private static final Object lock = new Object();
    private static ClickModel mClickModel = null;
    private static volatile long preMutiTime = -1;
    private static volatile long preTime = -1;
    private static long preTimeDouble = -1;
    private static ConcurrentHashMap<String, Long> preTimeMap = null;
    private static final long preTimeMuti = -1;
    private static ConcurrentHashMap<Integer, Long> vPreTimeMap;

    public static boolean checkDebugClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        clickCount++;
        if (clickCount == 1) {
            preTime = jCurrentTimeMillis;
        }
        if (clickCount < CLICK_COUNT_MAX) {
            return false;
        }
        clickCount = 0;
        if (Math.abs(jCurrentTimeMillis - preTime) > CLICK_LIMIT_TIME) {
            return false;
        }
        preTime = jCurrentTimeMillis;
        return true;
    }

    public static boolean checkDoubleClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - preTimeDouble;
        if (Math.abs(j) > 300) {
            preTimeDouble = jCurrentTimeMillis;
            clickCountDouble = 1;
            return false;
        }
        int i = clickCountDouble + 1;
        clickCountDouble = i;
        return i == 2 && Math.abs(j) < 300;
    }

    public static boolean checkMutiClick(String clickTag) {
        if (preTimeMap == null) {
            preTimeMap = new ConcurrentHashMap<>();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l = preTimeMap.get(clickTag);
        if (l == null) {
            preTimeMap.put(clickTag, Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - l.longValue()) <= CLICK_MUTI_LIMIT_TIME) {
            return true;
        }
        preTimeMap.put(clickTag, Long.valueOf(jCurrentTimeMillis));
        return false;
    }

    public static boolean checkViewMutiClick(View v, long interval) {
        if (v == null) {
            return true;
        }
        if (vPreTimeMap == null) {
            vPreTimeMap = new ConcurrentHashMap<>();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l = vPreTimeMap.get(Integer.valueOf(v.getId()));
        if (l == null) {
            vPreTimeMap.put(Integer.valueOf(v.getId()), Long.valueOf(jCurrentTimeMillis));
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - l.longValue()) <= interval) {
            return true;
        }
        vPreTimeMap.put(Integer.valueOf(v.getId()), Long.valueOf(jCurrentTimeMillis));
        return false;
    }

    public static boolean checkMutiClick(long interval) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (lock) {
            if (Math.abs(jCurrentTimeMillis - preMutiTime) <= interval) {
                return true;
            }
            preMutiTime = jCurrentTimeMillis;
            return false;
        }
    }

    public static boolean checkViewClickedTimes(View v, long interval, int clickTimes) {
        if (v == null) {
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ClickModel clickModel = mClickModel;
        if (clickModel == null || clickModel.viewId != v.getId()) {
            ClickModel clickModel2 = new ClickModel();
            mClickModel = clickModel2;
            clickModel2.firstClickTime = jCurrentTimeMillis;
            mClickModel.clickCount = 1;
            mClickModel.viewId = v.getId();
            return false;
        }
        mClickModel.clickCount++;
        if (mClickModel.clickCount < clickTimes) {
            return false;
        }
        if (Math.abs(jCurrentTimeMillis - mClickModel.firstClickTime) > interval) {
            mClickModel.firstClickTime = jCurrentTimeMillis;
            mClickModel.clickCount = 1;
            return false;
        }
        mClickModel = null;
        return true;
    }

    private static class ClickModel {
        int clickCount;
        long firstClickTime;
        int viewId;

        private ClickModel() {
        }
    }
}
