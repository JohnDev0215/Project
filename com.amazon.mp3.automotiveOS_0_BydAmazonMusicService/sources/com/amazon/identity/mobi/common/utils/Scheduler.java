package com.amazon.identity.mobi.common.utils;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class Scheduler {
    private static final Map<String, Scheduler> SCHEDULER_MAP = new HashMap();
    private final Handler mHandler;

    private Scheduler(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    public static synchronized Scheduler getInstance(String str) {
        Scheduler scheduler;
        Map<String, Scheduler> map = SCHEDULER_MAP;
        scheduler = map.get(str);
        if (scheduler == null) {
            scheduler = new Scheduler(str);
            map.put(str, scheduler);
        }
        return scheduler;
    }

    public void scheduleJob(Runnable runnable, long j) {
        this.mHandler.postDelayed(runnable, j);
    }
}
