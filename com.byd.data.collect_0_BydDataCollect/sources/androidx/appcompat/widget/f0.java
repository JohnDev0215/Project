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
public class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f619c;

    private f0(Context context, TypedArray typedArray) {
        this.f617a = context;
        this.f618b = typedArray;
    }

    public static f0 t(Context context, int i2, int[] iArr) {
        return new f0(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static f0 u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new f0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static f0 v(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new f0(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public boolean a(int i2, boolean z) {
        return this.f618b.getBoolean(i2, z);
    }

    public int b(int i2, int i3) {
        return this.f618b.getColor(i2, i3);
    }

    public ColorStateList c(int i2) {
        int resourceId;
        ColorStateList colorStateListC;
        return (!this.f618b.hasValue(i2) || (resourceId = this.f618b.getResourceId(i2, 0)) == 0 || (colorStateListC = b.a.k.a.a.c(this.f617a, resourceId)) == null) ? this.f618b.getColorStateList(i2) : colorStateListC;
    }

    public float d(int i2, float f2) {
        return this.f618b.getDimension(i2, f2);
    }

    public int e(int i2, int i3) {
        return this.f618b.getDimensionPixelOffset(i2, i3);
    }

    public int f(int i2, int i3) {
        return this.f618b.getDimensionPixelSize(i2, i3);
    }

    public Drawable g(int i2) {
        int resourceId;
        return (!this.f618b.hasValue(i2) || (resourceId = this.f618b.getResourceId(i2, 0)) == 0) ? this.f618b.getDrawable(i2) : b.a.k.a.a.d(this.f617a, resourceId);
    }

    public Drawable h(int i2) {
        int resourceId;
        if (!this.f618b.hasValue(i2) || (resourceId = this.f618b.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return f.b().d(this.f617a, resourceId, true);
    }

    public float i(int i2, float f2) {
        return this.f618b.getFloat(i2, f2);
    }

    public Typeface j(int i2, int i3, f.a aVar) {
        int resourceId = this.f618b.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f619c == null) {
            this.f619c = new TypedValue();
        }
        return androidx.core.content.c.f.c(this.f617a, resourceId, this.f619c, i3, aVar);
    }

    public int k(int i2, int i3) {
        return this.f618b.getInt(i2, i3);
    }

    public int l(int i2, int i3) {
        return this.f618b.getInteger(i2, i3);
    }

    public int m(int i2, int i3) {
        return this.f618b.getLayoutDimension(i2, i3);
    }

    public int n(int i2, int i3) {
        return this.f618b.getResourceId(i2, i3);
    }

    public String o(int i2) {
        return this.f618b.getString(i2);
    }

    public CharSequence p(int i2) {
        return this.f618b.getText(i2);
    }

    public CharSequence[] q(int i2) {
        return this.f618b.getTextArray(i2);
    }

    public TypedArray r() {
        return this.f618b;
    }

    public boolean s(int i2) {
        return this.f618b.hasValue(i2);
    }

    public void w() {
        this.f618b.recycle();
    }
}
