package com.byd.auto_emanual;

import android.app.Activity;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ActivityManager {
    private static String TAG = "ActivityManager";
    private static ActivityManager instance;
    private List<Activity> activityList = new LinkedList();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (this.activityList.contains(activity)) {
            return;
        }
        this.activityList.add(activity);
    }

    public void deleteActivity(Activity activity) {
        List<Activity> list = this.activityList;
        if (list == null || list.size() <= 0 || activity == null) {
            return;
        }
        this.activityList.remove(activity);
    }

    public void exit() {
        for (Activity activity : this.activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}
