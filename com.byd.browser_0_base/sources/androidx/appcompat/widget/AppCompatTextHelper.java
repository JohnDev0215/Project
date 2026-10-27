package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.R;
import defpackage.in3;
import defpackage.om3;
import defpackage.rb2;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class AppCompatTextHelper {
    public final TextView a;
    public TintInfo b;
    public TintInfo c;
    public TintInfo d;
    public TintInfo e;
    public TintInfo f;
    public TintInfo g;
    public TintInfo h;
    public final AppCompatTextViewAutoSizeHelper i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    public static class Api17Impl {
        private Api17Impl() {
        }
    }

    public static class Api21Impl {
        private Api21Impl() {
        }
    }

    public static class Api24Impl {
        private Api24Impl() {
        }
    }

    public static class Api26Impl {
        private Api26Impl() {
        }
    }

    public static class Api28Impl {
        private Api28Impl() {
        }
    }

    public AppCompatTextHelper(TextView textView) {
        this.a = textView;
        this.i = new AppCompatTextViewAutoSizeHelper(textView);
    }

    public static TintInfo c(Context context, AppCompatDrawableManager appCompatDrawableManager, int i) {
        ColorStateList colorStateListG;
        synchronized (appCompatDrawableManager) {
            colorStateListG = appCompatDrawableManager.a.g(context, i);
        }
        if (colorStateListG == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.d = true;
        tintInfo.a = colorStateListG;
        return tintInfo;
    }

    public final void a(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.e(drawable, tintInfo, this.a.getDrawableState());
    }

    public final void b() {
        TintInfo tintInfo = this.b;
        TextView textView = this.a;
        if (tintInfo != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f == null && this.g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f);
        a(compoundDrawablesRelative[2], this.g);
    }

    public final ColorStateList d() {
        TintInfo tintInfo = this.h;
        if (tintInfo != null) {
            return tintInfo.a;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        TintInfo tintInfo = this.h;
        if (tintInfo != null) {
            return tintInfo.b;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c1  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(android.util.AttributeSet r20, int r21) {
        /*
            Method dump skipped, instruction units count: 928
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.f(android.util.AttributeSet, int):void");
    }

    public final void g(Context context, int i) {
        String strJ;
        TintTypedArray tintTypedArray = new TintTypedArray(context, context.obtainStyledAttributes(i, R.styleable.TextAppearance));
        int i2 = R.styleable.TextAppearance_textAllCaps;
        boolean zL = tintTypedArray.l(i2);
        TextView textView = this.a;
        if (zL) {
            textView.setAllCaps(tintTypedArray.a(i2, false));
        }
        int i3 = R.styleable.TextAppearance_android_textSize;
        if (tintTypedArray.l(i3) && tintTypedArray.d(i3, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        j(context, tintTypedArray);
        int i4 = R.styleable.TextAppearance_fontVariationSettings;
        if (tintTypedArray.l(i4) && (strJ = tintTypedArray.j(i4)) != null) {
            textView.setFontVariationSettings(strJ);
        }
        tintTypedArray.n();
        Typeface typeface = this.l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.j);
        }
    }

    public final void h(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new TintInfo();
        }
        TintInfo tintInfo = this.h;
        tintInfo.a = colorStateList;
        tintInfo.d = colorStateList != null;
        this.b = tintInfo;
        this.c = tintInfo;
        this.d = tintInfo;
        this.e = tintInfo;
        this.f = tintInfo;
        this.g = tintInfo;
    }

    public final void i(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new TintInfo();
        }
        TintInfo tintInfo = this.h;
        tintInfo.b = mode;
        tintInfo.c = mode != null;
        this.b = tintInfo;
        this.c = tintInfo;
        this.d = tintInfo;
        this.e = tintInfo;
        this.f = tintInfo;
        this.g = tintInfo;
    }

    public final void j(Context context, TintTypedArray tintTypedArray) {
        String strJ;
        this.j = tintTypedArray.h(R.styleable.TextAppearance_android_textStyle, this.j);
        int iH = tintTypedArray.h(R.styleable.TextAppearance_android_textFontWeight, -1);
        this.k = iH;
        if (iH != -1) {
            this.j = (this.j & 2) | 0;
        }
        int i = R.styleable.TextAppearance_android_fontFamily;
        if (!tintTypedArray.l(i) && !tintTypedArray.l(R.styleable.TextAppearance_fontFamily)) {
            int i2 = R.styleable.TextAppearance_android_typeface;
            if (tintTypedArray.l(i2)) {
                this.m = false;
                int iH2 = tintTypedArray.h(i2, 1);
                if (iH2 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iH2 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iH2 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i3 = R.styleable.TextAppearance_fontFamily;
        if (tintTypedArray.l(i3)) {
            i = i3;
        }
        final int i4 = this.k;
        final int i5 = this.j;
        if (!context.isRestricted()) {
            final WeakReference weakReference = new WeakReference(this.a);
            try {
                Typeface typefaceG = tintTypedArray.g(i, this.j, new rb2.c() { // from class: androidx.appcompat.widget.AppCompatTextHelper.1
                    @Override // rb2.c
                    public final void d(final Typeface typeface) {
                        int i6 = i4;
                        if (i6 != -1) {
                            typeface = Typeface.create(typeface, i6, (i5 & 2) != 0);
                        }
                        AppCompatTextHelper appCompatTextHelper = AppCompatTextHelper.this;
                        if (appCompatTextHelper.m) {
                            appCompatTextHelper.l = typeface;
                            final TextView textView = (TextView) weakReference.get();
                            if (textView != null) {
                                WeakHashMap<View, in3> weakHashMap = om3.a;
                                if (!om3.g.b(textView)) {
                                    textView.setTypeface(typeface, appCompatTextHelper.j);
                                } else {
                                    final int i7 = appCompatTextHelper.j;
                                    textView.post(new Runnable() { // from class: androidx.appcompat.widget.AppCompatTextHelper.2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            textView.setTypeface(typeface, i7);
                                        }
                                    });
                                }
                            }
                        }
                    }

                    @Override // rb2.c
                    public final void c(int i6) {
                    }
                });
                if (typefaceG != null) {
                    if (this.k != -1) {
                        this.l = Typeface.create(Typeface.create(typefaceG, 0), this.k, (this.j & 2) != 0);
                    } else {
                        this.l = typefaceG;
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strJ = tintTypedArray.j(i)) == null) {
            return;
        }
        if (this.k != -1) {
            this.l = Typeface.create(Typeface.create(strJ, 0), this.k, (this.j & 2) != 0);
        } else {
            this.l = Typeface.create(strJ, this.j);
        }
    }
}
