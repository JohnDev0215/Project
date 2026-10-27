package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.core.content.c.f;

/* JADX INFO: loaded from: classes.dex */
public class v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f1044b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f1045c;

    private v0(Context context, TypedArray typedArray) {
        this.f1043a = context;
        this.f1044b = typedArray;
    }

    public static v0 t(Context context, int i, int[] iArr) {
        return new v0(context, context.obtainStyledAttributes(i, iArr));
    }

    public static v0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new v0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static v0 v(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new v0(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean a(int i, boolean z) {
        return this.f1044b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f1044b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f1044b.hasValue(i) || (resourceId = this.f1044b.getResourceId(i, 0)) == 0 || (colorStateListC = a.a.k.a.a.c(this.f1043a, resourceId)) == null) ? this.f1044b.getColorStateList(i) : colorStateListC;
    }

    public float d(int i, float f) {
        return this.f1044b.getDimension(i, f);
    }

    public int e(int i, int i2) {
        return this.f1044b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.f1044b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        return (!this.f1044b.hasValue(i) || (resourceId = this.f1044b.getResourceId(i, 0)) == 0) ? this.f1044b.getDrawable(i) : a.a.k.a.a.d(this.f1043a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        if (!this.f1044b.hasValue(i) || (resourceId = this.f1044b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return i.b().d(this.f1043a, resourceId, true);
    }

    public float i(int i, float f) {
        return this.f1044b.getFloat(i, f);
    }

    public Typeface j(int i, int i2, f.a aVar) {
        int resourceId = this.f1044b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1045c == null) {
            this.f1045c = new TypedValue();
        }
        return androidx.core.content.c.f.c(this.f1043a, resourceId, this.f1045c, i2, aVar);
    }

    public int k(int i, int i2) {
        return this.f1044b.getInt(i, i2);
    }

    public int l(int i, int i2) {
        return this.f1044b.getInteger(i, i2);
    }

    public int m(int i, int i2) {
        return this.f1044b.getLayoutDimension(i, i2);
    }

    public int n(int i, int i2) {
        return this.f1044b.getResourceId(i, i2);
    }

    public String o(int i) {
        return this.f1044b.getString(i);
    }

    public CharSequence p(int i) {
        return this.f1044b.getText(i);
    }

    public CharSequence[] q(int i) {
        return this.f1044b.getTextArray(i);
    }

    public TypedArray r() {
        return this.f1044b;
    }

    public boolean s(int i) {
        return this.f1044b.hasValue(i);
    }

    public void w() {
        this.f1044b.recycle();
    }
}
