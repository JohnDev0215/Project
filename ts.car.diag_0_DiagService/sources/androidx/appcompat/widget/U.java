package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleableRes;
import androidx.core.content.res.e;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f329a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f330b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f331c;

    private U(Context context, TypedArray typedArray) {
        this.f329a = context;
        this.f330b = typedArray;
    }

    public static U s(Context context, int i, int[] iArr) {
        return new U(context, context.obtainStyledAttributes(i, iArr));
    }

    public static U t(Context context, AttributeSet attributeSet, int[] iArr) {
        return new U(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static U u(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new U(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean a(int i, boolean z) {
        return this.f330b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f330b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListA;
        return (!this.f330b.hasValue(i) || (resourceId = this.f330b.getResourceId(i, 0)) == 0 || (colorStateListA = androidx.appcompat.a.a.a.a(this.f329a, resourceId)) == null) ? this.f330b.getColorStateList(i) : colorStateListA;
    }

    public int d(int i, int i2) {
        return this.f330b.getDimensionPixelOffset(i, i2);
    }

    public int e(int i, int i2) {
        return this.f330b.getDimensionPixelSize(i, i2);
    }

    public Drawable f(int i) {
        int resourceId;
        return (!this.f330b.hasValue(i) || (resourceId = this.f330b.getResourceId(i, 0)) == 0) ? this.f330b.getDrawable(i) : androidx.appcompat.a.a.a.b(this.f329a, resourceId);
    }

    public Drawable g(int i) {
        int resourceId;
        if (!this.f330b.hasValue(i) || (resourceId = this.f330b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return C0063i.b().d(this.f329a, resourceId, true);
    }

    public float h(int i, float f) {
        return this.f330b.getFloat(i, f);
    }

    @Nullable
    public Typeface i(@StyleableRes int i, int i2, @Nullable e.b bVar) {
        int resourceId = this.f330b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f331c == null) {
            this.f331c = new TypedValue();
        }
        return androidx.core.content.res.e.e(this.f329a, resourceId, this.f331c, i2, bVar);
    }

    public int j(int i, int i2) {
        return this.f330b.getInt(i, i2);
    }

    public int k(int i, int i2) {
        return this.f330b.getInteger(i, i2);
    }

    public int l(int i, int i2) {
        return this.f330b.getLayoutDimension(i, i2);
    }

    public int m(int i, int i2) {
        return this.f330b.getResourceId(i, i2);
    }

    public String n(int i) {
        return this.f330b.getString(i);
    }

    public CharSequence o(int i) {
        return this.f330b.getText(i);
    }

    public CharSequence[] p(int i) {
        return this.f330b.getTextArray(i);
    }

    public TypedArray q() {
        return this.f330b;
    }

    public boolean r(int i) {
        return this.f330b.hasValue(i);
    }

    public void v() {
        this.f330b.recycle();
    }
}
