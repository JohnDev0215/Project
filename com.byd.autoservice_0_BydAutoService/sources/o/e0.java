package o;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import c0.f;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f4253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f4254c;

    public e0(Context context, TypedArray typedArray) {
        this.f4252a = context;
        this.f4253b = typedArray;
    }

    public static e0 t(Context context, int i3, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(i3, iArr));
    }

    public static e0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static e0 v(Context context, AttributeSet attributeSet, int[] iArr, int i3, int i4) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr, i3, i4));
    }

    public boolean a(int i3, boolean z3) {
        return this.f4253b.getBoolean(i3, z3);
    }

    public int b(int i3, int i4) {
        return this.f4253b.getColor(i3, i4);
    }

    public ColorStateList c(int i3) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f4253b.hasValue(i3) || (resourceId = this.f4253b.getResourceId(i3, 0)) == 0 || (colorStateListC = j.a.c(this.f4252a, resourceId)) == null) ? this.f4253b.getColorStateList(i3) : colorStateListC;
    }

    public float d(int i3, float f3) {
        return this.f4253b.getDimension(i3, f3);
    }

    public int e(int i3, int i4) {
        return this.f4253b.getDimensionPixelOffset(i3, i4);
    }

    public int f(int i3, int i4) {
        return this.f4253b.getDimensionPixelSize(i3, i4);
    }

    public Drawable g(int i3) {
        int resourceId;
        return (!this.f4253b.hasValue(i3) || (resourceId = this.f4253b.getResourceId(i3, 0)) == 0) ? this.f4253b.getDrawable(i3) : j.a.d(this.f4252a, resourceId);
    }

    public Drawable h(int i3) {
        int resourceId;
        if (!this.f4253b.hasValue(i3) || (resourceId = this.f4253b.getResourceId(i3, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f4252a, resourceId, true);
    }

    public float i(int i3, float f3) {
        return this.f4253b.getFloat(i3, f3);
    }

    public Typeface j(int i3, int i4, f.a aVar) {
        int resourceId = this.f4253b.getResourceId(i3, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f4254c == null) {
            this.f4254c = new TypedValue();
        }
        return c0.f.c(this.f4252a, resourceId, this.f4254c, i4, aVar);
    }

    public int k(int i3, int i4) {
        return this.f4253b.getInt(i3, i4);
    }

    public int l(int i3, int i4) {
        return this.f4253b.getInteger(i3, i4);
    }

    public int m(int i3, int i4) {
        return this.f4253b.getLayoutDimension(i3, i4);
    }

    public int n(int i3, int i4) {
        return this.f4253b.getResourceId(i3, i4);
    }

    public String o(int i3) {
        return this.f4253b.getString(i3);
    }

    public CharSequence p(int i3) {
        return this.f4253b.getText(i3);
    }

    public CharSequence[] q(int i3) {
        return this.f4253b.getTextArray(i3);
    }

    public TypedArray r() {
        return this.f4253b;
    }

    public boolean s(int i3) {
        return this.f4253b.hasValue(i3);
    }

    public void w() {
        this.f4253b.recycle();
    }
}
