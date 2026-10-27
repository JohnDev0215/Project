package com.android.car.apps.common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemProperties;

/* JADX INFO: loaded from: classes5.dex */
public class CommonFlags {
    private static final String FLAG_IMPROPER_IMAGE_REFS_KEY = "com.android.car.apps.common.FlagNonLocalImages";
    private static CommonFlags sInstance;
    private final Context mApplicationContext;
    private Boolean mFlagImproperImageRefs;

    public static CommonFlags getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CommonFlags(context);
        }
        return sInstance;
    }

    private CommonFlags(Context context) {
        this.mApplicationContext = context.getApplicationContext();
    }

    public boolean shouldFlagImproperImageRefs() {
        if (this.mFlagImproperImageRefs == null) {
            Resources res = this.mApplicationContext.getResources();
            this.mFlagImproperImageRefs = Boolean.valueOf(res.getBoolean(R.bool.flag_improper_image_references) || "1".equals(SystemProperties.get(FLAG_IMPROPER_IMAGE_REFS_KEY, "0")));
        }
        return this.mFlagImproperImageRefs.booleanValue();
    }
}
