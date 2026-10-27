package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.core.content.c.f;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f591c;

    private e0(Context context, TypedArray typedArray) {
        this.f589a = context;
        this.f590b = typedArray;
    }

    public static e0 t(Context context, int i, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(i, iArr));
    }

    public static e0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static e0 v(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean a(int i, boolean z) {
        return this.f590b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f590b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f590b.hasValue(i) || (resourceId = this.f590b.getResourceId(i, 0)) == 0 || (colorStateListC = b.a.k.a.a.c(this.f589a, resourceId)) == null) ? this.f590b.getColorStateList(i) : colorStateListC;
    }

    public float d(int i, float f2) {
        return this.f590b.getDimension(i, f2);
    }

    public int e(int i, int i2) {
        return this.f590b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.f590b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        return (!this.f590b.hasValue(i) || (resourceId = this.f590b.getResourceId(i, 0)) == 0) ? this.f590b.getDrawable(i) : b.a.k.a.a.d(this.f589a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        if (!this.f590b.hasValue(i) || (resourceId = this.f590b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f589a, resourceId, true);
    }

    public float i(int i, float f2) {
        return this.f590b.getFloat(i, f2);
    }

    public Typeface j(int i, int i2, f.a aVar) {
        int resourceId = this.f590b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f591c == null) {
            this.f591c = new TypedValue();
        }
        return androidx.core.content.c.f.c(this.f589a, resourceId, this.f591c, i2, aVar);
    }

    public int k(int i, int i2) {
        return this.f590b.getInt(i, i2);
    }

    public int l(int i, int i2) {
        return this.f590b.getInteger(i, i2);
    }

    public int m(int i, int i2) {
        return this.f590b.getLayoutDimension(i, i2);
    }

    public int n(int i, int i2) {
        return this.f590b.getResourceId(i, i2);
    }

    public String o(int i) {
        return this.f590b.getString(i);
    }

    public CharSequence p(int i) {
        return this.f590b.getText(i);
    }

    public CharSequence[] q(int i) {
        return this.f590b.getTextArray(i);
    }

    public TypedArray r() {
        return this.f590b;
    }

    public boolean s(int i) {
        return this.f590b.hasValue(i);
    }

    public void w() {
        this.f590b.recycle();
    }
}
