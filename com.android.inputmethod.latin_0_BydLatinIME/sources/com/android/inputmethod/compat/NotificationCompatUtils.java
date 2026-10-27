package com.android.inputmethod.compat;

import android.app.Notification;
import android.os.Build;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationCompatUtils {
    private static final String CATEGORY_RECOMMENDATION;
    private static final Field FIELD_CATEGORY_RECOMMENDATION;
    private static final Field FIELD_PRIORITY_LOW;
    private static final Field FIELD_VISIBILITY_SECRET;
    private static final int PRIORITY_LOW;
    private static final int VISIBILITY_SECRET;
    private static final Method METHOD_setColor = CompatUtils.getMethod(Notification.Builder.class, "setColor", Integer.TYPE);
    private static final Method METHOD_setVisibility = CompatUtils.getMethod(Notification.Builder.class, "setVisibility", Integer.TYPE);
    private static final Method METHOD_setCategory = CompatUtils.getMethod(Notification.Builder.class, "setCategory", String.class);
    private static final Method METHOD_setPriority = CompatUtils.getMethod(Notification.Builder.class, "setPriority", Integer.TYPE);
    private static final Method METHOD_build = CompatUtils.getMethod(Notification.Builder.class, "build", new Class[0]);

    static {
        Field field = CompatUtils.getField(Notification.class, "VISIBILITY_SECRET");
        FIELD_VISIBILITY_SECRET = field;
        VISIBILITY_SECRET = field == null ? 0 : ((Integer) CompatUtils.getFieldValue(null, null, field)).intValue();
        Field field2 = CompatUtils.getField(Notification.class, "CATEGORY_RECOMMENDATION");
        FIELD_CATEGORY_RECOMMENDATION = field2;
        CATEGORY_RECOMMENDATION = field2 == null ? "" : (String) CompatUtils.getFieldValue(null, null, field2);
        Field field3 = CompatUtils.getField(Notification.class, "PRIORITY_LOW");
        FIELD_PRIORITY_LOW = field3;
        PRIORITY_LOW = field3 != null ? ((Integer) CompatUtils.getFieldValue(null, null, field3)).intValue() : 0;
    }

    private NotificationCompatUtils() {
    }

    public static void setColor(final Notification.Builder builder, final int color) {
        CompatUtils.invoke(builder, null, METHOD_setColor, Integer.valueOf(color));
    }

    public static void setVisibilityToSecret(final Notification.Builder builder) {
        CompatUtils.invoke(builder, null, METHOD_setVisibility, Integer.valueOf(VISIBILITY_SECRET));
    }

    public static void setCategoryToRecommendation(final Notification.Builder builder) {
        CompatUtils.invoke(builder, null, METHOD_setCategory, CATEGORY_RECOMMENDATION);
    }

    public static void setPriorityToLow(final Notification.Builder builder) {
        CompatUtils.invoke(builder, null, METHOD_setPriority, Integer.valueOf(PRIORITY_LOW));
    }

    public static Notification build(final Notification.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16) {
            return (Notification) CompatUtils.invoke(builder, null, METHOD_build, new Object[0]);
        }
        return builder.getNotification();
    }
}
