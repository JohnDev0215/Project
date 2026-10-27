package com.byd.auto_emanual.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;

/* JADX INFO: loaded from: classes.dex */
public class ResourceUtil {
    public static int getColor(Context context, int attr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{attr});
        int color = typedArrayObtainStyledAttributes.getColor(0, SupportMenu.CATEGORY_MASK);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    public static ColorStateList getColorStateList(Context context, int attr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{attr});
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(0);
        typedArrayObtainStyledAttributes.recycle();
        return colorStateList;
    }

    public static void setTextColorA(TextView view, int attr) {
        view.setTextColor(getColorStateList(view.getContext(), attr));
    }

    public static void setTextColor(TextView view, int attr) {
        view.setTextColor(getColor(view.getContext(), attr));
    }

    public static void setHintTextColor(TextView view, int attr) {
        view.setHintTextColor(getColor(view.getContext(), attr));
    }

    public static void setBackgroundColor(View view, int attr) {
        view.setBackgroundColor(getColor(view.getContext(), attr));
    }

    public static Drawable getDrawable(Context context, int attr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{attr});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    public static void setBackground(View view, int attr) {
        view.setBackground(getDrawable(view.getContext(), attr));
    }

    public static void setBackgroundDrawable(View view, int resId) {
        view.setBackground(view.getContext().getTheme().getDrawable(resId));
    }

    public static int getResId(Context context, int attr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{attr});
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId;
    }

    public static int getDimensionPixelOffset(Context context, int attr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{attr});
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelOffset;
    }
}
