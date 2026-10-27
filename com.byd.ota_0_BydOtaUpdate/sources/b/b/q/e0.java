package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import b.f.f.c.f;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f1013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f1014c;

    public e0(Context context, TypedArray typedArray) {
        this.f1012a = context;
        this.f1013b = typedArray;
    }

    public static e0 s(Context context, int i2, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static e0 t(Context context, AttributeSet attributeSet, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static e0 u(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public boolean a(int i2, boolean z) {
        return this.f1013b.getBoolean(i2, z);
    }

    public int b(int i2, int i3) {
        return this.f1013b.getColor(i2, i3);
    }

    public ColorStateList c(int i2) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f1013b.hasValue(i2) || (resourceId = this.f1013b.getResourceId(i2, 0)) == 0 || (colorStateListC = b.b.l.a.a.c(this.f1012a, resourceId)) == null) ? this.f1013b.getColorStateList(i2) : colorStateListC;
    }

    public int d(int i2, int i3) {
        return this.f1013b.getDimensionPixelOffset(i2, i3);
    }

    public int e(int i2, int i3) {
        return this.f1013b.getDimensionPixelSize(i2, i3);
    }

    public Drawable f(int i2) {
        int resourceId;
        return (!this.f1013b.hasValue(i2) || (resourceId = this.f1013b.getResourceId(i2, 0)) == 0) ? this.f1013b.getDrawable(i2) : b.b.l.a.a.d(this.f1012a, resourceId);
    }

    public Drawable g(int i2) {
        int resourceId;
        if (!this.f1013b.hasValue(i2) || (resourceId = this.f1013b.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f1012a, resourceId, true);
    }

    public float h(int i2, float f2) {
        return this.f1013b.getFloat(i2, f2);
    }

    public Typeface i(int i2, int i3, f.a aVar) {
        int resourceId = this.f1013b.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1014c == null) {
            this.f1014c = new TypedValue();
        }
        return b.f.f.c.f.b(this.f1012a, resourceId, this.f1014c, i3, aVar);
    }

    public int j(int i2, int i3) {
        return this.f1013b.getInt(i2, i3);
    }

    public int k(int i2, int i3) {
        return this.f1013b.getInteger(i2, i3);
    }

    public int l(int i2, int i3) {
        return this.f1013b.getLayoutDimension(i2, i3);
    }

    public int m(int i2, int i3) {
        return this.f1013b.getResourceId(i2, i3);
    }

    public String n(int i2) {
        return this.f1013b.getString(i2);
    }

    public CharSequence o(int i2) {
        return this.f1013b.getText(i2);
    }

    public CharSequence[] p(int i2) {
        return this.f1013b.getTextArray(i2);
    }

    public TypedArray q() {
        return this.f1013b;
    }

    public boolean r(int i2) {
        return this.f1013b.hasValue(i2);
    }

    public void v() {
        this.f1013b.recycle();
    }
}
