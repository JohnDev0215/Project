package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.AppLocalesStorageHelper;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.VectorEnabledTintResources;
import defpackage.no2;

/* JADX INFO: compiled from: SkinCompatVectorResources.java */
/* JADX INFO: loaded from: classes3.dex */
public final class xo2 implements hp2 {
    public static xo2 a;

    public xo2() {
        po2.d().f.add(this);
    }

    public static Drawable a(Context context, int i) {
        int iE;
        Drawable drawableB;
        ColorStateList colorStateListA;
        if (a == null) {
            synchronized (xo2.class) {
                if (a == null) {
                    a = new xo2();
                }
            }
        }
        a.getClass();
        AppLocalesStorageHelper.SerialExecutor serialExecutor = AppCompatDelegate.e;
        int i2 = VectorEnabledTintResources.a;
        vo2 vo2Var = vo2.i;
        if (!vo2Var.d && (colorStateListA = vo2Var.a(i)) != null) {
            return new ColorDrawable(colorStateListA.getDefaultColor());
        }
        if (!vo2Var.h && (drawableB = vo2Var.b(i)) != null) {
            return drawableB;
        }
        no2.b bVar = po2.d().d;
        if (bVar != null) {
            bVar.e();
        }
        return (po2.d().e || (iE = po2.d().e(context, i)) == 0) ? AppCompatResources.a(context, i) : po2.d().a.getDrawable(iE);
    }

    @Override // defpackage.hp2
    public final void clear() {
        if (ko2.c == null) {
            ko2.c = new ko2();
        }
        ko2.c.b.clear();
        ko2.d.f(-1);
    }
}
