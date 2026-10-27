package com.android.internal.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.android.internal.R;

/* JADX INFO: loaded from: classes4.dex */
public class ScreenRecordHelper {
    private final Context mContext;

    public ScreenRecordHelper(Context context) {
        this.mContext = context;
    }

    public void launchRecordPrompt() {
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(this.mContext.getResources().getString(R.string.config_screenRecorderComponent));
        Intent intent = new Intent();
        intent.setComponent(componentNameUnflattenFromString);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }
}
