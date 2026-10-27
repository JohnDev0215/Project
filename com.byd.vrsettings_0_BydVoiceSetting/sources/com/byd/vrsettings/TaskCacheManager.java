package com.byd.vrsettings;

import android.util.ArrayMap;
import com.byd.vrdownload.model.DownloadTask;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TaskCacheManager {
    static Map<String, DownloadTask> taskCache = new ArrayMap();

    public static void saveTask(String str, DownloadTask downloadTask) {
        taskCache.put(str, downloadTask);
    }

    public static DownloadTask getTask(String str) {
        return taskCache.get(str);
    }

    public static void clearTask(String str) {
        taskCache.remove(str);
    }

    public static void clearAllTask() {
        taskCache.clear();
    }
}
