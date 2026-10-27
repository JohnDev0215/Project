package b.b.f;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import b.g.b.c.f;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f763c;

    public e0(Context context, TypedArray typedArray) {
        this.f761a = context;
        this.f762b = typedArray;
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
        return this.f762b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f762b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f762b.hasValue(i) || (resourceId = this.f762b.getResourceId(i, 0)) == 0 || (colorStateListC = b.b.b.a.a.c(this.f761a, resourceId)) == null) ? this.f762b.getColorStateList(i) : colorStateListC;
    }

    public float d(int i, float f) {
        return this.f762b.getDimension(i, f);
    }

    public int e(int i, int i2) {
        return this.f762b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.f762b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        return (!this.f762b.hasValue(i) || (resourceId = this.f762b.getResourceId(i, 0)) == 0) ? this.f762b.getDrawable(i) : b.b.b.a.a.d(this.f761a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        if (!this.f762b.hasValue(i) || (resourceId = this.f762b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f761a, resourceId, true);
    }

    public float i(int i, float f) {
        return this.f762b.getFloat(i, f);
    }

    public Typeface j(int i, int i2, f.a aVar) {
        int resourceId = this.f762b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f763c == null) {
            this.f763c = new TypedValue();
        }
        return b.g.b.c.f.c(this.f761a, resourceId, this.f763c, i2, aVar);
    }

    public int k(int i, int i2) {
        return this.f762b.getInt(i, i2);
    }

    public int l(int i, int i2) {
        return this.f762b.getInteger(i, i2);
    }

    public int m(int i, int i2) {
        return this.f762b.getLayoutDimension(i, i2);
    }

    public int n(int i, int i2) {
        return this.f762b.getResourceId(i, i2);
    }

    public String o(int i) {
        return this.f762b.getString(i);
    }

    public CharSequence p(int i) {
        return this.f762b.getText(i);
    }

    public CharSequence[] q(int i) {
        return this.f762b.getTextArray(i);
    }

    public TypedArray r() {
        return this.f762b;
    }

    public boolean s(int i) {
        return this.f762b.hasValue(i);
    }

    public void w() {
        this.f762b.recycle();
    }
}
