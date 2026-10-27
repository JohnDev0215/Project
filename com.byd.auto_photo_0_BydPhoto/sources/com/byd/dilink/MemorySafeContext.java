package com.byd.dilink;

import android.content.Context;
import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class MemorySafeContext {
    public static Context get(Context context) {
        return (context == null || context.getApplicationContext() == null) ? DiLinkServiceManager.getApplicationContext() != null ? DiLinkServiceManager.getApplicationContext() : context : context.getApplicationContext();
    }
}
