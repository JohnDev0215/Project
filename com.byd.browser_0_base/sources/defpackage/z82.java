package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.util.TypedValue;
import org.chromium.chrome.R$drawable;

/* JADX INFO: compiled from: RatingStarSpan.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z82 extends DynamicDrawableSpan {
    public final Context e;
    public final int f;

    public z82(Context context, int i) {
        this.e = context;
        this.f = i;
    }

    @Override // android.text.style.DynamicDrawableSpan
    public final Drawable getDrawable() {
        int i;
        Context context = this.e;
        Resources resources = context.getResources();
        int i2 = this.f;
        if (i2 == 0) {
            i = R$drawable.ic_rating_star_outline;
        } else if (i2 == 1) {
            i = R$drawable.ic_rating_star_half;
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException("RatingStarType value is invalid.");
            }
            i = R$drawable.ic_rating_star_full;
        }
        Resources.Theme theme = context.getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        Drawable drawable = resources.getDrawable(i, theme);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }
}
