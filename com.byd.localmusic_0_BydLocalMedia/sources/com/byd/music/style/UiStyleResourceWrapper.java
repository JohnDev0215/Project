package com.byd.music.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes5.dex */
public class UiStyleResourceWrapper extends Resources {
    public static final String RES_TYPE_DIMEN = "dimen";
    public static final String RES_TYPE_ID = "id";
    public static final String RES_TYPE_LAYOUT = "layout";
    private static final String TAG = "UiStyleResourceWrapper";
    private final Context mContext;
    private final Resources mResources;

    public UiStyleResourceWrapper(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        resetFields(resources);
        this.mResources = resources;
        this.mContext = Utils.getApp();
    }

    public Context getContext() {
        return this.mContext;
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int i) {
        try {
            return this.mResources.getLayout(UiStyleManager.getInstance().getLayout(getContext(), i));
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getLayout(i);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i, Resources.Theme theme) {
        try {
            return this.mResources.getDrawable(UiStyleManager.getInstance().getDrawableId(getContext(), i), theme);
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getDrawable(i, theme);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        try {
            return this.mResources.getDrawable(UiStyleManager.getInstance().getDrawableId(getContext(), i));
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getDrawable(i);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        try {
            return this.mResources.getDrawableForDensity(UiStyleManager.getInstance().getDrawableId(getContext(), i), i2, theme);
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getDrawableForDensity(i, i2, theme);
        }
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2) {
        try {
            return this.mResources.getDrawableForDensity(UiStyleManager.getInstance().getDrawableId(getContext(), i), i2);
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getDrawableForDensity(i, i2);
        }
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i, Resources.Theme theme) {
        try {
            return this.mResources.getColorStateList(UiStyleManager.getInstance().getColorId(getContext(), i), theme);
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getColorStateList(i, theme);
        }
    }

    @Override // android.content.res.Resources
    public int getColor(int i, Resources.Theme theme) {
        try {
            return this.mResources.getColor(UiStyleManager.getInstance().getColorId(getContext(), i), theme);
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getColor(i, theme);
        }
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int i) {
        try {
            return this.mResources.getColorStateList(UiStyleManager.getInstance().getColorId(getContext(), i));
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getColorStateList(i);
        }
    }

    @Override // android.content.res.Resources
    public int getColor(int i) {
        try {
            return this.mResources.getColor(UiStyleManager.getInstance().getColorId(getContext(), i));
        } catch (Throwable th) {
            th.printStackTrace();
            return super.getColor(i);
        }
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.mResources;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    private void resetFields(Resources resources) {
        Class<?> superclass = resources.getClass();
        while (!superclass.isAssignableFrom(Resources.class)) {
            superclass = superclass.getSuperclass();
        }
        Class<?> superclass2 = getClass();
        while (!superclass2.isAssignableFrom(Resources.class)) {
            superclass2 = superclass2.getSuperclass();
        }
        for (Field field : superclass.getDeclaredFields()) {
            if (field != null) {
                field.setAccessible(true);
                try {
                    Object obj = field.get(resources);
                    Field declaredField = superclass2.getDeclaredField(field.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(this, obj);
                } catch (Exception e) {
                    Log.e(TAG, "Exception={?}", e);
                }
            }
        }
    }
}
