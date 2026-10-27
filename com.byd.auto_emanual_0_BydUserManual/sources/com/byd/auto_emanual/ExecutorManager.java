package com.byd.auto_emanual;

import android.text.TextUtils;
import com.byd.auto_emanual.fragment.Classify;
import com.byd.auto_emanual.fragment.Page;
import com.byd.auto_emanual.util.MLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class ExecutorManager {
    private static final String TAG = "ExecutorManager";
    private static final int TIME_THREAD_WAIT = 10;
    private static volatile ExecutorManager instance;
    private Runnable taskMobileData;
    private ExecutorService mExecutors = Executors.newCachedThreadPool();
    private Map<String, ModelRunnable> taskRecords = new HashMap();

    private ExecutorManager() {
    }

    public static synchronized ExecutorManager getInstance() {
        if (instance == null && instance == null) {
            instance = new ExecutorManager();
        }
        return instance;
    }

    public Future execute(String path, String classify, ModelRunnable runnable) {
        String str = path + classify;
        if (!this.taskRecords.containsKey(str)) {
            this.taskRecords.put(str, runnable);
            runnable.setFuture(this.mExecutors.submit(runnable));
        }
        if (classify != null && classify.equals(Classify.FEATURE) && !this.taskRecords.get(str).future.isDone()) {
            sleep();
        }
        return this.taskRecords.get(str).getFuture();
    }

    public void execute(Runnable runnable) {
        if (this.taskMobileData == null) {
            this.taskMobileData = runnable;
        }
        this.mExecutors.submit(runnable);
    }

    private void sleep() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            MLog.v(TAG, "Thread exception", e);
        }
    }

    public static void clearRecords() {
        if (instance != null) {
            instance.taskRecords.clear();
        }
    }

    public static void removeTask(String path) {
        if (instance != null) {
            String key = null;
            Iterator<Map.Entry<String, ModelRunnable>> it = instance.taskRecords.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, ModelRunnable> next = it.next();
                if (next.getKey().startsWith(path)) {
                    key = next.getKey();
                    break;
                }
            }
            if (TextUtils.isEmpty(key)) {
                return;
            }
            instance.taskRecords.remove(key);
        }
    }

    public static void removeJsonTask() {
        if (instance != null) {
            String key = null;
            Iterator<Map.Entry<String, ModelRunnable>> it = instance.taskRecords.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, ModelRunnable> next = it.next();
                if (next.getKey().endsWith(Page.JSON_DATA)) {
                    key = next.getKey();
                    break;
                }
            }
            if (TextUtils.isEmpty(key)) {
                return;
            }
            instance.taskRecords.remove(key);
        }
    }

    public static ManualRunnable getManualRunnable() {
        for (Map.Entry<String, ModelRunnable> entry : getInstance().taskRecords.entrySet()) {
            if (entry.getValue() instanceof ManualRunnable) {
                return (ManualRunnable) entry.getValue();
            }
        }
        return null;
    }

    public static JsonDataRunnable getJsonDataRunnable() {
        for (Map.Entry<String, ModelRunnable> entry : getInstance().taskRecords.entrySet()) {
            if (entry.getValue() instanceof JsonDataRunnable) {
                return (JsonDataRunnable) entry.getValue();
            }
        }
        return null;
    }
}
