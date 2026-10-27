package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import org.chromium.components.browser_ui.widget.R$color;

/* JADX INFO: compiled from: TintedDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public class za3 extends BitmapDrawable {
    public ColorStateList a;

    public za3(Context context, Bitmap bitmap) {
        super(context.getResources(), bitmap);
        this.a = bz.b(context, R$color.default_icon_color_tint_list);
    }

    public static za3 a(Context context, int i) {
        return new za3(context, BitmapFactory.decodeResource(context.getResources(), i));
    }

    public static za3 b(Context context, int i, int i2) {
        za3 za3VarA = a(context, i);
        za3VarA.c(bz.b(context, i2));
        return za3VarA;
    }

    public void c(ColorStateList colorStateList) {
        if (this.a == colorStateList) {
            return;
        }
        this.a = colorStateList;
        if (colorStateList == null) {
            return;
        }
        setColorFilter(colorStateList.getColorForState(getState(), 0), PorterDuff.Mode.SRC_IN);
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.a;
        boolean z = false;
        if (colorStateList != null) {
            setColorFilter(colorStateList.getColorForState(getState(), 0), PorterDuff.Mode.SRC_IN);
            z = true;
        }
        super.onStateChange(iArr);
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        c(ColorStateList.valueOf(i));
    }
}
