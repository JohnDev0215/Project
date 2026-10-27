package c.a.e.c;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import java.util.Arrays;

/* JADX INFO: compiled from: RippleHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static ColorStateList a(int i) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i});
    }

    @TargetApi(21)
    public static Drawable b(int i, Integer num, int i2) {
        return new RippleDrawable(a(i), num != null ? new ColorDrawable(num.intValue()) : null, c(-1, i2));
    }

    public static Drawable c(int i, int i2) {
        float[] fArr = new float[8];
        Arrays.fill(fArr, i2);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    public static void d(View view, int i, int i2) {
        e(view, i, null, i2);
    }

    public static void e(View view, int i, Integer num, int i2) {
        view.setBackground(b(i, num, i2));
    }

    public static void f(Context context, View view) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.selectableItemBackground});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        a.a(view, drawable);
    }
}
