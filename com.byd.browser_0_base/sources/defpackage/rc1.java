package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;

/* JADX INFO: compiled from: MaterialResources.java */
/* JADX INFO: loaded from: classes.dex */
public final class rc1 {
    public static ColorStateList a(Context context, TypedArray typedArray, int i) {
        int resourceId;
        ColorStateList colorStateListB;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateListB = bz.b(context, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateListB;
    }

    public static ColorStateList b(Context context, TintTypedArray tintTypedArray, int i) {
        int i2;
        ColorStateList colorStateListB;
        return (!tintTypedArray.l(i) || (i2 = tintTypedArray.i(i, 0)) == 0 || (colorStateListB = bz.b(context, i2)) == null) ? tintTypedArray.b(i) : colorStateListB;
    }

    public static int c(Context context, TypedArray typedArray, int i, int i2) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i, i2);
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, i2);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static Drawable d(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable drawableA;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (drawableA = AppCompatResources.a(context, resourceId)) == null) ? typedArray.getDrawable(i) : drawableA;
    }

    public static boolean e(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }
}
