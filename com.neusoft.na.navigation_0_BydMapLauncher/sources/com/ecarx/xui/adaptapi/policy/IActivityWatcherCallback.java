package com.ecarx.xui.adaptapi.policy;

import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public interface IActivityWatcherCallback {
    void activityOnResume(String str, String str2, Intent intent);

    void activityStarting(String str, String str2, Intent intent);

    void startActivity(Intent intent);
}
