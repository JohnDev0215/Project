package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class B1 extends ViewGroup.MarginLayoutParams {
    public AbstractC1798y1 a;
    public boolean b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public View k;
    public View l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public final Rect q;

    public B1(int i, int i2) {
        super(i, i2);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.q = new Rect();
    }

    public boolean a(int i) {
        if (i == 0) {
            return this.n;
        }
        if (i != 1) {
            return false;
        }
        return this.o;
    }

    public void b(int i, boolean z) {
        if (i == 0) {
            this.n = z;
        } else {
            if (i != 1) {
                return;
            }
            this.o = z;
        }
    }

    public B1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.q = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.f23J);
        this.c = typedArrayObtainStyledAttributes.getInteger(6, 0);
        this.f = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        this.d = typedArrayObtainStyledAttributes.getInteger(1, 0);
        this.e = typedArrayObtainStyledAttributes.getInteger(5, -1);
        this.g = typedArrayObtainStyledAttributes.getInt(4, 0);
        this.h = typedArrayObtainStyledAttributes.getInt(3, 0);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(2);
        this.b = zHasValue;
        if (zHasValue) {
            CoordinatorLayout.n(context, attributeSet, typedArrayObtainStyledAttributes.getString(2));
            this.a = null;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public B1(B1 b1) {
        super((ViewGroup.MarginLayoutParams) b1);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.q = new Rect();
    }

    public B1(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.q = new Rect();
    }

    public B1(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.b = false;
        this.c = 0;
        this.d = 0;
        this.e = -1;
        this.f = -1;
        this.g = 0;
        this.h = 0;
        this.q = new Rect();
    }
}
