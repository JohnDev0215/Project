package t2;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.view.View;
import java.util.Arrays;

/* JADX INFO: compiled from: RippleHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static Drawable a(Integer num, int i8) {
        if (num == null) {
            return null;
        }
        if (i8 == 0) {
            return new ColorDrawable(num.intValue());
        }
        float[] fArr = new float[8];
        Arrays.fill(fArr, i8);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(fArr);
        gradientDrawable.setColor(num.intValue());
        return gradientDrawable;
    }

    public static ColorStateList b(int i8) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i8});
    }

    @TargetApi(21)
    public static Drawable c(int i8, Integer num, int i9) {
        return new RippleDrawable(b(i8), num != null ? new ColorDrawable(num.intValue()) : null, d(-1, i9));
    }

    public static Drawable d(int i8, int i9) {
        float[] fArr = new float[8];
        Arrays.fill(fArr, i9);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(i8);
        return shapeDrawable;
    }

    public static StateListDrawable e(int i8, Integer num, int i9) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, a(Integer.valueOf(i8), i9));
        if (num != null) {
            stateListDrawable.addState(new int[0], a(num, i9));
        }
        return stateListDrawable;
    }

    public static void f(View view, int i8, int i9) {
        g(view, i8, null, i9);
    }

    public static void g(View view, int i8, Integer num, int i9) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackground(c(i8, num, i9));
        } else {
            view.setBackgroundDrawable(e(i8, num, i9));
        }
    }

    public static void h(Context context, View view) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        a.a(view, drawable);
    }
}
