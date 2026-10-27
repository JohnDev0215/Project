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
    private final Context f582a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f583b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f584c;

    private e0(Context context, TypedArray typedArray) {
        this.f582a = context;
        this.f583b = typedArray;
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
        return this.f583b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f583b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f583b.hasValue(i) || (resourceId = this.f583b.getResourceId(i, 0)) == 0 || (colorStateListC = b.a.k.a.a.c(this.f582a, resourceId)) == null) ? this.f583b.getColorStateList(i) : colorStateListC;
    }

    public float d(int i, float f) {
        return this.f583b.getDimension(i, f);
    }

    public int e(int i, int i2) {
        return this.f583b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.f583b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        return (!this.f583b.hasValue(i) || (resourceId = this.f583b.getResourceId(i, 0)) == 0) ? this.f583b.getDrawable(i) : b.a.k.a.a.d(this.f582a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        if (!this.f583b.hasValue(i) || (resourceId = this.f583b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f582a, resourceId, true);
    }

    public float i(int i, float f) {
        return this.f583b.getFloat(i, f);
    }

    public Typeface j(int i, int i2, f.a aVar) {
        int resourceId = this.f583b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f584c == null) {
            this.f584c = new TypedValue();
        }
        return androidx.core.content.c.f.c(this.f582a, resourceId, this.f584c, i2, aVar);
    }

    public int k(int i, int i2) {
        return this.f583b.getInt(i, i2);
    }

    public int l(int i, int i2) {
        return this.f583b.getInteger(i, i2);
    }

    public int m(int i, int i2) {
        return this.f583b.getLayoutDimension(i, i2);
    }

    public int n(int i, int i2) {
        return this.f583b.getResourceId(i, i2);
    }

    public String o(int i) {
        return this.f583b.getString(i);
    }

    public CharSequence p(int i) {
        return this.f583b.getText(i);
    }

    public CharSequence[] q(int i) {
        return this.f583b.getTextArray(i);
    }

    public TypedArray r() {
        return this.f583b;
    }

    public boolean s(int i) {
        return this.f583b.hasValue(i);
    }

    public void w() {
        this.f583b.recycle();
    }
}
