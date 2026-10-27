package a.b.p;

import a.b.k.l;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
public class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f389a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f390b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f391c;

    public x0(Context context, TypedArray typedArray) {
        this.f389a = context;
        this.f390b = typedArray;
    }

    public static x0 p(Context context, AttributeSet attributeSet, int[] iArr) {
        return new x0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static x0 q(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new x0(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean a(int i, boolean z) {
        return this.f390b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.f390b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListA;
        return (!this.f390b.hasValue(i) || (resourceId = this.f390b.getResourceId(i, 0)) == 0 || (colorStateListA = a.b.l.a.a.a(this.f389a, resourceId)) == null) ? this.f390b.getColorStateList(i) : colorStateListA;
    }

    public float d(int i, float f2) {
        return this.f390b.getDimension(i, f2);
    }

    public int e(int i, int i2) {
        return this.f390b.getDimensionPixelOffset(i, i2);
    }

    public int f(int i, int i2) {
        return this.f390b.getDimensionPixelSize(i, i2);
    }

    public Drawable g(int i) {
        int resourceId;
        return (!this.f390b.hasValue(i) || (resourceId = this.f390b.getResourceId(i, 0)) == 0) ? this.f390b.getDrawable(i) : a.b.l.a.a.b(this.f389a, resourceId);
    }

    public Drawable h(int i) {
        int resourceId;
        Drawable drawableF;
        if (!this.f390b.hasValue(i) || (resourceId = this.f390b.getResourceId(i, 0)) == 0) {
            return null;
        }
        j jVarA = j.a();
        Context context = this.f389a;
        synchronized (jVarA) {
            drawableF = jVarA.f292a.f(context, resourceId, true);
        }
        return drawableF;
    }

    public Typeface i(int i, int i2, a.h.d.b.e eVar) {
        int resourceId = this.f390b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f391c == null) {
            this.f391c = new TypedValue();
        }
        Context context = this.f389a;
        TypedValue typedValue = this.f391c;
        if (context.isRestricted()) {
            return null;
        }
        return l.i.U(context, resourceId, typedValue, i2, eVar, null, true, false);
    }

    public int j(int i, int i2) {
        return this.f390b.getInt(i, i2);
    }

    public int k(int i, int i2) {
        return this.f390b.getLayoutDimension(i, i2);
    }

    public int l(int i, int i2) {
        return this.f390b.getResourceId(i, i2);
    }

    public String m(int i) {
        return this.f390b.getString(i);
    }

    public CharSequence n(int i) {
        return this.f390b.getText(i);
    }

    public boolean o(int i) {
        return this.f390b.hasValue(i);
    }
}
