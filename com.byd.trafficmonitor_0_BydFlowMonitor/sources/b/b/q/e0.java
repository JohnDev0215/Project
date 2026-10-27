package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import b.h.e.c.f;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f1722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f1723c;

    public e0(Context context, TypedArray typedArray) {
        this.f1721a = context;
        this.f1722b = typedArray;
    }

    public static e0 t(Context context, int i2, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static e0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static e0 v(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public boolean a(int i2, boolean z) {
        return this.f1722b.getBoolean(i2, z);
    }

    public int b(int i2, int i3) {
        return this.f1722b.getColor(i2, i3);
    }

    public ColorStateList c(int i2) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f1722b.hasValue(i2) || (resourceId = this.f1722b.getResourceId(i2, 0)) == 0 || (colorStateListC = b.b.l.a.a.c(this.f1721a, resourceId)) == null) ? this.f1722b.getColorStateList(i2) : colorStateListC;
    }

    public float d(int i2, float f2) {
        return this.f1722b.getDimension(i2, f2);
    }

    public int e(int i2, int i3) {
        return this.f1722b.getDimensionPixelOffset(i2, i3);
    }

    public int f(int i2, int i3) {
        return this.f1722b.getDimensionPixelSize(i2, i3);
    }

    public Drawable g(int i2) {
        int resourceId;
        return (!this.f1722b.hasValue(i2) || (resourceId = this.f1722b.getResourceId(i2, 0)) == 0) ? this.f1722b.getDrawable(i2) : b.b.l.a.a.d(this.f1721a, resourceId);
    }

    public Drawable h(int i2) {
        int resourceId;
        if (!this.f1722b.hasValue(i2) || (resourceId = this.f1722b.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f1721a, resourceId, true);
    }

    public float i(int i2, float f2) {
        return this.f1722b.getFloat(i2, f2);
    }

    public Typeface j(int i2, int i3, f.a aVar) {
        int resourceId = this.f1722b.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1723c == null) {
            this.f1723c = new TypedValue();
        }
        return b.h.e.c.f.c(this.f1721a, resourceId, this.f1723c, i3, aVar);
    }

    public int k(int i2, int i3) {
        return this.f1722b.getInt(i2, i3);
    }

    public int l(int i2, int i3) {
        return this.f1722b.getInteger(i2, i3);
    }

    public int m(int i2, int i3) {
        return this.f1722b.getLayoutDimension(i2, i3);
    }

    public int n(int i2, int i3) {
        return this.f1722b.getResourceId(i2, i3);
    }

    public String o(int i2) {
        return this.f1722b.getString(i2);
    }

    public CharSequence p(int i2) {
        return this.f1722b.getText(i2);
    }

    public CharSequence[] q(int i2) {
        return this.f1722b.getTextArray(i2);
    }

    public TypedArray r() {
        return this.f1722b;
    }

    public boolean s(int i2) {
        return this.f1722b.hasValue(i2);
    }

    public void w() {
        this.f1722b.recycle();
    }
}
