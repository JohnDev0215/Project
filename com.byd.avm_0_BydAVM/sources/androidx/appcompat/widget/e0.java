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
    private final Context f429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f431c;

    private e0(Context context, TypedArray typedArray) {
        this.f429a = context;
        this.f430b = typedArray;
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
        return this.f430b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f430b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f430b.hasValue(i) || (resourceId = this.f430b.getResourceId(i, 0)) == 0 || (colorStateListC = b.a.k.a.a.c(this.f429a, resourceId)) == null) ? this.f430b.getColorStateList(i) : colorStateListC;
    }

    public float d(int i, float f) {
        return this.f430b.getDimension(i, f);
    }

    public int e(int i, int i2) {
        return this.f430b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.f430b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        return (!this.f430b.hasValue(i) || (resourceId = this.f430b.getResourceId(i, 0)) == 0) ? this.f430b.getDrawable(i) : b.a.k.a.a.d(this.f429a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        if (!this.f430b.hasValue(i) || (resourceId = this.f430b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f429a, resourceId, true);
    }

    public float i(int i, float f) {
        return this.f430b.getFloat(i, f);
    }

    public Typeface j(int i, int i2, f.a aVar) {
        int resourceId = this.f430b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f431c == null) {
            this.f431c = new TypedValue();
        }
        return androidx.core.content.c.f.c(this.f429a, resourceId, this.f431c, i2, aVar);
    }

    public int k(int i, int i2) {
        return this.f430b.getInt(i, i2);
    }

    public int l(int i, int i2) {
        return this.f430b.getInteger(i, i2);
    }

    public int m(int i, int i2) {
        return this.f430b.getLayoutDimension(i, i2);
    }

    public int n(int i, int i2) {
        return this.f430b.getResourceId(i, i2);
    }

    public String o(int i) {
        return this.f430b.getString(i);
    }

    public CharSequence p(int i) {
        return this.f430b.getText(i);
    }

    public CharSequence[] q(int i) {
        return this.f430b.getTextArray(i);
    }

    public TypedArray r() {
        return this.f430b;
    }

    public boolean s(int i) {
        return this.f430b.hasValue(i);
    }

    public void w() {
        this.f430b.recycle();
    }
}
