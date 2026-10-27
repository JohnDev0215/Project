package com.amazon.mp3.util.extensions;

import android.app.ActivityManager;
import android.content.Intent;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActivityManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"getCategories", "", "", "Landroid/app/ActivityManager$AppTask;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
@JvmName(name = "ActivityManagerExtensions")
public final class ActivityManagerExtensions {
    public static final Set<String> getCategories(ActivityManager.AppTask appTask) {
        Intent intent;
        Intrinsics.checkNotNullParameter(appTask, "<this>");
        ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
        if (taskInfo == null || (intent = taskInfo.baseIntent) == null) {
            return null;
        }
        return intent.getCategories();
    }
}
