package com.android.car.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes7.dex */
public final class CarUiUtils {
    private static final SparseArray<String> READ_ONLY_SYSTEM_PROPERTY_MAP = new SparseArray<>();
    private static final String READ_ONLY_SYSTEM_PROPERTY_PREFIX = "ro.";
    private static final String TAG = "CarUiUtils";

    private CarUiUtils() {
    }

    public static float getFloat(Resources res, int resId) {
        TypedValue outValue = new TypedValue();
        res.getValue(resId, outValue, true);
        return outValue.getFloat();
    }

    public static int getAttrResourceId(Context context, int attr) {
        return getAttrResourceId(context, 0, attr);
    }

    public static int getAttrResourceId(Context context, int styleResId, int attr) {
        TypedArray ta = context.obtainStyledAttributes(styleResId, new int[]{attr});
        int resId = ta.getResourceId(0, 0);
        ta.recycle();
        return resId;
    }

    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static void setRotaryScrollEnabled(View view, boolean isVertical) {
        view.setContentDescription(isVertical ? RotaryConstants.ROTARY_VERTICALLY_SCROLLABLE : RotaryConstants.ROTARY_HORIZONTALLY_SCROLLABLE);
    }

    public static <T extends View> T findViewByRefId(View view, int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return (T) view.findViewById(i);
        }
        if (i == -1) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        view.getResources().getValue(i, typedValue, true);
        return (T) view.findViewById(typedValue.resourceId);
    }

    public static <T extends View> T requireViewByRefId(View view, int i) {
        T t = (T) findViewByRefId(view, i);
        if (t == null) {
            throw new IllegalArgumentException("ID " + view.getResources().getResourceName(i) + " does not reference a View inside this View");
        }
        return t;
    }

    public static boolean getBooleanSystemProperty(Resources resources, int propertyResId, boolean defaultValue) {
        String value = getSystemProperty(resources, propertyResId);
        if (!TextUtils.isEmpty(value)) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public static String getSystemProperty(Resources resources, int propertyResId) {
        String propertyName = resources.getString(propertyResId);
        boolean isReadOnly = propertyName.startsWith(READ_ONLY_SYSTEM_PROPERTY_PREFIX);
        if (!isReadOnly) {
            return readSystemProperty(propertyName);
        }
        synchronized (READ_ONLY_SYSTEM_PROPERTY_MAP) {
            if (READ_ONLY_SYSTEM_PROPERTY_MAP.indexOfKey(propertyResId) >= 0) {
                return READ_ONLY_SYSTEM_PROPERTY_MAP.get(propertyResId);
            }
            String value = readSystemProperty(propertyName);
            READ_ONLY_SYSTEM_PROPERTY_MAP.put(propertyResId, value);
            return value;
        }
    }

    private static String readSystemProperty(String propertyName) {
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            try {
                Method getMethod = systemPropertiesClass.getMethod("get", String.class);
                try {
                    Object[] params = {propertyName};
                    String value = (String) getMethod.invoke(systemPropertiesClass, params);
                    if (TextUtils.isEmpty(value)) {
                        return null;
                    }
                    return value;
                } catch (Exception e) {
                    Log.w(TAG, "Failed to invoke SystemProperties.get(): ", e);
                    return null;
                }
            } catch (NoSuchMethodException e2) {
                Log.w(TAG, "Cannot find SystemProperties.get(): ", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            Log.w(TAG, "Cannot find android.os.SystemProperties: ", e3);
            return null;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
