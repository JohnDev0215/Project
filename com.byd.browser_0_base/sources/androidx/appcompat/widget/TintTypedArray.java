package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bz;
import defpackage.rb2;

/* JADX INFO: loaded from: classes.dex */
public class TintTypedArray {
    public final Context a;
    public final TypedArray b;
    public TypedValue c;

    public static class Api21Impl {
        private Api21Impl() {
        }
    }

    public TintTypedArray(Context context, TypedArray typedArray) {
        this.a = context;
        this.b = typedArray;
    }

    public static TintTypedArray m(Context context, AttributeSet attributeSet, int[] iArr, int i) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
    }

    public final boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public final ColorStateList b(int i) {
        int resourceId;
        ColorStateList colorStateListB;
        TypedArray typedArray = this.b;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (colorStateListB = bz.b(this.a, resourceId)) == null) ? typedArray.getColorStateList(i) : colorStateListB;
    }

    public final int c(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public final int d(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public final Drawable e(int i) {
        int resourceId;
        TypedArray typedArray = this.b;
        return (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) ? typedArray.getDrawable(i) : AppCompatResources.a(this.a, resourceId);
    }

    public final Drawable f(int i) {
        int resourceId;
        Drawable drawableD;
        if (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) {
            return null;
        }
        AppCompatDrawableManager appCompatDrawableManagerA = AppCompatDrawableManager.a();
        Context context = this.a;
        synchronized (appCompatDrawableManagerA) {
            drawableD = appCompatDrawableManagerA.a.d(resourceId, context, true);
        }
        return drawableD;
    }

    public final Typeface g(int i, int i2, rb2.c cVar) {
        int resourceId = this.b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        TypedValue typedValue = this.c;
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        Context context = this.a;
        if (context.isRestricted()) {
            return null;
        }
        return rb2.c(context, resourceId, typedValue, i2, cVar, true, false);
    }

    public final int h(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public final int i(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public final String j(int i) {
        return this.b.getString(i);
    }

    public final CharSequence k(int i) {
        return this.b.getText(i);
    }

    public final boolean l(int i) {
        return this.b.hasValue(i);
    }

    public final void n() {
        this.b.recycle();
    }
}
