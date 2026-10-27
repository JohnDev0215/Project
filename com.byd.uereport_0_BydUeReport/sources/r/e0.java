package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import f0.f;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7511a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f7512b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f7513c;

    public e0(Context context, TypedArray typedArray) {
        this.f7511a = context;
        this.f7512b = typedArray;
    }

    public static e0 t(Context context, int i8, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(i8, iArr));
    }

    public static e0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static e0 v(Context context, AttributeSet attributeSet, int[] iArr, int i8, int i9) {
        return new e0(context, context.obtainStyledAttributes(attributeSet, iArr, i8, i9));
    }

    public boolean a(int i8, boolean z7) {
        return this.f7512b.getBoolean(i8, z7);
    }

    public int b(int i8, int i9) {
        return this.f7512b.getColor(i8, i9);
    }

    public ColorStateList c(int i8) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f7512b.hasValue(i8) || (resourceId = this.f7512b.getResourceId(i8, 0)) == 0 || (colorStateListC = m.a.c(this.f7511a, resourceId)) == null) ? this.f7512b.getColorStateList(i8) : colorStateListC;
    }

    public float d(int i8, float f8) {
        return this.f7512b.getDimension(i8, f8);
    }

    public int e(int i8, int i9) {
        return this.f7512b.getDimensionPixelOffset(i8, i9);
    }

    public int f(int i8, int i9) {
        return this.f7512b.getDimensionPixelSize(i8, i9);
    }

    public Drawable g(int i8) {
        int resourceId;
        return (!this.f7512b.hasValue(i8) || (resourceId = this.f7512b.getResourceId(i8, 0)) == 0) ? this.f7512b.getDrawable(i8) : m.a.d(this.f7511a, resourceId);
    }

    public Drawable h(int i8) {
        int resourceId;
        if (!this.f7512b.hasValue(i8) || (resourceId = this.f7512b.getResourceId(i8, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f7511a, resourceId, true);
    }

    public float i(int i8, float f8) {
        return this.f7512b.getFloat(i8, f8);
    }

    public Typeface j(int i8, int i9, f.a aVar) {
        int resourceId = this.f7512b.getResourceId(i8, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f7513c == null) {
            this.f7513c = new TypedValue();
        }
        return f0.f.c(this.f7511a, resourceId, this.f7513c, i9, aVar);
    }

    public int k(int i8, int i9) {
        return this.f7512b.getInt(i8, i9);
    }

    public int l(int i8, int i9) {
        return this.f7512b.getInteger(i8, i9);
    }

    public int m(int i8, int i9) {
        return this.f7512b.getLayoutDimension(i8, i9);
    }

    public int n(int i8, int i9) {
        return this.f7512b.getResourceId(i8, i9);
    }

    public String o(int i8) {
        return this.f7512b.getString(i8);
    }

    public CharSequence p(int i8) {
        return this.f7512b.getText(i8);
    }

    public CharSequence[] q(int i8) {
        return this.f7512b.getTextArray(i8);
    }

    public TypedArray r() {
        return this.f7512b;
    }

    public boolean s(int i8) {
        return this.f7512b.hasValue(i8);
    }

    public void w() {
        this.f7512b.recycle();
    }
}
