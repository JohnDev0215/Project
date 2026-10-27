package b6;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import r.e0;

/* JADX INFO: compiled from: MaterialResources.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static ColorStateList a(Context context, TypedArray typedArray, int i8) {
        int color;
        int resourceId;
        ColorStateList colorStateListC;
        return (!typedArray.hasValue(i8) || (resourceId = typedArray.getResourceId(i8, 0)) == 0 || (colorStateListC = m.a.c(context, resourceId)) == null) ? (Build.VERSION.SDK_INT > 15 || (color = typedArray.getColor(i8, -1)) == -1) ? typedArray.getColorStateList(i8) : ColorStateList.valueOf(color) : colorStateListC;
    }

    public static ColorStateList b(Context context, e0 e0Var, int i8) {
        int iB;
        int iN;
        ColorStateList colorStateListC;
        return (!e0Var.s(i8) || (iN = e0Var.n(i8, 0)) == 0 || (colorStateListC = m.a.c(context, iN)) == null) ? (Build.VERSION.SDK_INT > 15 || (iB = e0Var.b(i8, -1)) == -1) ? e0Var.c(i8) : ColorStateList.valueOf(iB) : colorStateListC;
    }

    public static int c(Context context, TypedArray typedArray, int i8, int i9) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i8, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i8, i9);
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, i9);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static Drawable d(Context context, TypedArray typedArray, int i8) {
        int resourceId;
        Drawable drawableD;
        return (!typedArray.hasValue(i8) || (resourceId = typedArray.getResourceId(i8, 0)) == 0 || (drawableD = m.a.d(context, resourceId)) == null) ? typedArray.getDrawable(i8) : drawableD;
    }

    public static int e(TypedArray typedArray, int i8, int i9) {
        return typedArray.hasValue(i8) ? i8 : i9;
    }

    public static d f(Context context, TypedArray typedArray, int i8) {
        int resourceId;
        if (!typedArray.hasValue(i8) || (resourceId = typedArray.getResourceId(i8, 0)) == 0) {
            return null;
        }
        return new d(context, resourceId);
    }
}
