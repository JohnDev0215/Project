package p;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import coil.target.GenericViewTarget;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public static final p70 a = new p70();

    public static final boolean a(q31 q31Var) {
        int iOrdinal = q31Var.i.ordinal();
        if (iOrdinal == 0) {
            return false;
        }
        if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                throw new NoWhenBranchMatchedException();
            }
            rd2 rd2Var = q31Var.L.b;
            rd2 rd2Var2 = q31Var.B;
            if (rd2Var != null || !(rd2Var2 instanceof la0)) {
                yi2 yi2Var = q31Var.c;
                if (!(yi2Var instanceof GenericViewTarget) || !(rd2Var2 instanceof coil.size.a)) {
                    return false;
                }
                GenericViewTarget genericViewTarget = (GenericViewTarget) yi2Var;
                if (!(genericViewTarget.i() instanceof ImageView) || genericViewTarget.i() != ((h12) ((coil.size.a) rd2Var2)).a) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final Drawable b(q31 q31Var, Drawable drawable, Integer num, Drawable drawable2) {
        if (drawable != null) {
            return drawable;
        }
        if (num == null) {
            return drawable2;
        }
        if (num.intValue() == 0) {
            return null;
        }
        Context context = q31Var.a;
        int iIntValue = num.intValue();
        Drawable drawableF = l13.f(context, iIntValue);
        if (drawableF != null) {
            return drawableF;
        }
        throw new IllegalStateException(u4.e("Invalid resource ID: ", iIntValue).toString());
    }
}
