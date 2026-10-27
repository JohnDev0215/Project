package com.android.launcher3;

import android.content.Context;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.graphics.IconShape;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.util.ResourceBasedOverride;

/* JADX INFO: loaded from: classes4.dex */
public class MainProcessInitializer implements ResourceBasedOverride {
    public static void initialize(Context context) {
        ((MainProcessInitializer) ResourceBasedOverride.Overrides.getObject(MainProcessInitializer.class, context, R.string.main_process_initializer_class)).init(context);
    }

    protected void init(Context context) {
        FileLog.setDir(context.getApplicationContext().getFilesDir());
        FeatureFlags.initialize(context);
        SessionCommitReceiver.applyDefaultUserPrefs(context);
        IconShape.init(context);
    }
}
