package b.a.b.e.f;

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
    public static Drawable a(Integer num, int i) {
        if (num == null) {
            return null;
        }
        if (i == 0) {
            return new ColorDrawable(num.intValue());
        }
        float[] fArr = new float[8];
        Arrays.fill(fArr, i);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(fArr);
        gradientDrawable.setColor(num.intValue());
        return gradientDrawable;
    }

    public static ColorStateList b(int i) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i});
    }

    @TargetApi(21)
    public static Drawable c(int i, Integer num, int i2) {
        return new RippleDrawable(b(i), num != null ? new ColorDrawable(num.intValue()) : null, d(-1, i2));
    }

    public static Drawable d(int i, int i2) {
        float[] fArr = new float[8];
        Arrays.fill(fArr, i2);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    public static StateListDrawable e(int i, Integer num, int i2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, a(Integer.valueOf(i), i2));
        if (num != null) {
            stateListDrawable.addState(new int[0], a(num, i2));
        }
        return stateListDrawable;
    }

    public static void f(View view, int i, int i2) {
        g(view, i, null, i2);
    }

    public static void g(View view, int i, Integer num, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            view.setBackground(c(i, num, i2));
        } else {
            view.setBackgroundDrawable(e(i, num, i2));
        }
    }

    public static void h(Context context, View view) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        a.a(view, drawable);
    }
}
