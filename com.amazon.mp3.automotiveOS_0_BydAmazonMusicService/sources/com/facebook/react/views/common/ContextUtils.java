package com.facebook.react.views.common;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public class ContextUtils {
    @Nullable
    public static <T> T findContextOfType(@Nullable Context context, Class<? extends T> cls) {
        Context baseContext;
        Object obj = context;
        while (!cls.isInstance(obj)) {
            if (!(obj instanceof ContextWrapper) || obj == (baseContext = obj.getBaseContext())) {
                return null;
            }
            obj = (T) baseContext;
        }
        return (T) obj;
    }
}
