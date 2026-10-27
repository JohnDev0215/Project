package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: MaterialDialogs.java */
/* JADX INFO: loaded from: classes.dex */
public final class mc1 {
    public static Rect a(Context context, int i, int i2) {
        TypedArray typedArrayD = r83.d(context, null, R$styleable.MaterialAlertDialog, i, i2, new int[0]);
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.MaterialAlertDialog_backgroundInsetStart, context.getResources().getDimensionPixelSize(R$dimen.mtrl_alert_dialog_background_inset_start));
        int dimensionPixelSize2 = typedArrayD.getDimensionPixelSize(R$styleable.MaterialAlertDialog_backgroundInsetTop, context.getResources().getDimensionPixelSize(R$dimen.mtrl_alert_dialog_background_inset_top));
        int dimensionPixelSize3 = typedArrayD.getDimensionPixelSize(R$styleable.MaterialAlertDialog_backgroundInsetEnd, context.getResources().getDimensionPixelSize(R$dimen.mtrl_alert_dialog_background_inset_end));
        int dimensionPixelSize4 = typedArrayD.getDimensionPixelSize(R$styleable.MaterialAlertDialog_backgroundInsetBottom, context.getResources().getDimensionPixelSize(R$dimen.mtrl_alert_dialog_background_inset_bottom));
        typedArrayD.recycle();
        if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
            dimensionPixelSize3 = dimensionPixelSize;
            dimensionPixelSize = dimensionPixelSize3;
        }
        return new Rect(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize4);
    }
}
