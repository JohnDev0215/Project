package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class AppCompatBackgroundHelper {
    public final View a;
    public TintInfo d;
    public TintInfo e;
    public TintInfo f;
    public int c = -1;
    public final AppCompatDrawableManager b = AppCompatDrawableManager.a();

    public AppCompatBackgroundHelper(View view) {
        this.a = view;
    }

    public final void a() {
        View view = this.a;
        Drawable background = view.getBackground();
        if (background != null) {
            boolean z = false;
            if (this.d != null) {
                if (this.f == null) {
                    this.f = new TintInfo();
                }
                TintInfo tintInfo = this.f;
                tintInfo.a = null;
                tintInfo.d = false;
                tintInfo.b = null;
                tintInfo.c = false;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                ColorStateList colorStateListC = om3.i.c(view);
                if (colorStateListC != null) {
                    tintInfo.d = true;
                    tintInfo.a = colorStateListC;
                }
                PorterDuff.Mode modeD = om3.i.d(view);
                if (modeD != null) {
                    tintInfo.c = true;
                    tintInfo.b = modeD;
                }
                if (tintInfo.d || tintInfo.c) {
                    AppCompatDrawableManager.e(background, tintInfo, view.getDrawableState());
                    z = true;
                }
                if (z) {
                    return;
                }
            }
            TintInfo tintInfo2 = this.e;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.e(background, tintInfo2, view.getDrawableState());
                return;
            }
            TintInfo tintInfo3 = this.d;
            if (tintInfo3 != null) {
                AppCompatDrawableManager.e(background, tintInfo3, view.getDrawableState());
            }
        }
    }

    public final ColorStateList b() {
        TintInfo tintInfo = this.e;
        if (tintInfo != null) {
            return tintInfo.a;
        }
        return null;
    }

    public final PorterDuff.Mode c() {
        TintInfo tintInfo = this.e;
        if (tintInfo != null) {
            return tintInfo.b;
        }
        return null;
    }

    public final void d(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListG;
        View view = this.a;
        Context context = view.getContext();
        int[] iArr = R.styleable.ViewBackgroundHelper;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(context, attributeSet, iArr, i);
        View view2 = this.a;
        Context context2 = view2.getContext();
        TypedArray typedArray = tintTypedArrayM.b;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(view2, context2, iArr, attributeSet, typedArray, i, 0);
        try {
            int i2 = R.styleable.ViewBackgroundHelper_android_background;
            if (tintTypedArrayM.l(i2)) {
                this.c = tintTypedArrayM.i(i2, -1);
                AppCompatDrawableManager appCompatDrawableManager = this.b;
                Context context3 = view.getContext();
                int i3 = this.c;
                synchronized (appCompatDrawableManager) {
                    colorStateListG = appCompatDrawableManager.a.g(context3, i3);
                }
                if (colorStateListG != null) {
                    g(colorStateListG);
                }
            }
            int i4 = R.styleable.ViewBackgroundHelper_backgroundTint;
            if (tintTypedArrayM.l(i4)) {
                om3.i.j(view, tintTypedArrayM.b(i4));
            }
            int i5 = R.styleable.ViewBackgroundHelper_backgroundTintMode;
            if (tintTypedArrayM.l(i5)) {
                om3.i.k(view, DrawableUtils.c(tintTypedArrayM.h(i5, -1), null));
            }
        } finally {
            tintTypedArrayM.n();
        }
    }

    public final void e() {
        this.c = -1;
        g(null);
        a();
    }

    public final void f(int i) {
        ColorStateList colorStateListG;
        this.c = i;
        AppCompatDrawableManager appCompatDrawableManager = this.b;
        if (appCompatDrawableManager != null) {
            Context context = this.a.getContext();
            synchronized (appCompatDrawableManager) {
                colorStateListG = appCompatDrawableManager.a.g(context, i);
            }
        } else {
            colorStateListG = null;
        }
        g(colorStateListG);
        a();
    }

    public final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new TintInfo();
            }
            TintInfo tintInfo = this.d;
            tintInfo.a = colorStateList;
            tintInfo.d = true;
        } else {
            this.d = null;
        }
        a();
    }

    public final void h(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new TintInfo();
        }
        TintInfo tintInfo = this.e;
        tintInfo.a = colorStateList;
        tintInfo.d = true;
        a();
    }

    public final void i(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new TintInfo();
        }
        TintInfo tintInfo = this.e;
        tintInfo.b = mode;
        tintInfo.c = true;
        a();
    }
}
