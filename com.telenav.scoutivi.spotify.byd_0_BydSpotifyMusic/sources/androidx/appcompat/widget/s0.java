package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import y.b;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f1184b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f1185c;

    private s0(Context context, TypedArray typedArray) {
        this.f1183a = context;
        this.f1184b = typedArray;
    }

    public static s0 t(Context context, int i7, int[] iArr) {
        return new s0(context, context.obtainStyledAttributes(i7, iArr));
    }

    public static s0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new s0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static s0 v(Context context, AttributeSet attributeSet, int[] iArr, int i7, int i8) {
        return new s0(context, context.obtainStyledAttributes(attributeSet, iArr, i7, i8));
    }

    public boolean a(int i7, boolean z6) {
        return this.f1184b.getBoolean(i7, z6);
    }

    public int b(int i7, int i8) {
        return this.f1184b.getColor(i7, i8);
    }

    public ColorStateList c(int i7) {
        int resourceId;
        ColorStateList colorStateListA;
        return (!this.f1184b.hasValue(i7) || (resourceId = this.f1184b.getResourceId(i7, 0)) == 0 || (colorStateListA = f.a.a(this.f1183a, resourceId)) == null) ? this.f1184b.getColorStateList(i7) : colorStateListA;
    }

    public float d(int i7, float f7) {
        return this.f1184b.getDimension(i7, f7);
    }

    public int e(int i7, int i8) {
        return this.f1184b.getDimensionPixelOffset(i7, i8);
    }

    public int f(int i7, int i8) {
        return this.f1184b.getDimensionPixelSize(i7, i8);
    }

    public Drawable g(int i7) {
        int resourceId;
        return (!this.f1184b.hasValue(i7) || (resourceId = this.f1184b.getResourceId(i7, 0)) == 0) ? this.f1184b.getDrawable(i7) : f.a.b(this.f1183a, resourceId);
    }

    public Drawable h(int i7) {
        int resourceId;
        if (!this.f1184b.hasValue(i7) || (resourceId = this.f1184b.getResourceId(i7, 0)) == 0) {
            return null;
        }
        return i.b().d(this.f1183a, resourceId, true);
    }

    public float i(int i7, float f7) {
        return this.f1184b.getFloat(i7, f7);
    }

    public Typeface j(int i7, int i8, b.a aVar) {
        int resourceId = this.f1184b.getResourceId(i7, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1185c == null) {
            this.f1185c = new TypedValue();
        }
        return y.b.d(this.f1183a, resourceId, this.f1185c, i8, aVar);
    }

    public int k(int i7, int i8) {
        return this.f1184b.getInt(i7, i8);
    }

    public int l(int i7, int i8) {
        return this.f1184b.getInteger(i7, i8);
    }

    public int m(int i7, int i8) {
        return this.f1184b.getLayoutDimension(i7, i8);
    }

    public int n(int i7, int i8) {
        return this.f1184b.getResourceId(i7, i8);
    }

    public String o(int i7) {
        return this.f1184b.getString(i7);
    }

    public CharSequence p(int i7) {
        return this.f1184b.getText(i7);
    }

    public CharSequence[] q(int i7) {
        return this.f1184b.getTextArray(i7);
    }

    public TypedArray r() {
        return this.f1184b;
    }

    public boolean s(int i7) {
        return this.f1184b.hasValue(i7);
    }

    public void w() {
        this.f1184b.recycle();
    }
}
