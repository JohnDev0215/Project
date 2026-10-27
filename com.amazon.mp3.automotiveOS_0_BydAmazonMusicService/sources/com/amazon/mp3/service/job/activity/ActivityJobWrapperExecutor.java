package com.amazon.mp3.service.job.activity;

import android.content.Intent;

/* JADX INFO: loaded from: classes4.dex */
public class ActivityJobWrapperExecutor extends JobWrapperExecutor<ActivityJobWrapper> {
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        ActivityJobWrapper current = getCurrent();
        if (current != null && requestCode == current.getActivityRequestCode()) {
            current.notifyActivityResult(resultCode, data);
            runNext();
        }
    }
}
