package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class G extends Drawable.ConstantState {
    public int A;
    public int B;
    public boolean C;
    public ColorFilter D;
    public boolean E;
    public ColorStateList F;
    public PorterDuff.Mode G;
    public boolean H;
    public boolean I;
    public final H a;
    public Resources b;
    public int c;
    public int d;
    public int e;
    public SparseArray f;
    public Drawable[] g;
    public int h;
    public boolean i;
    public boolean j;
    public Rect k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public int q;
    public boolean r;
    public int s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public int z;

    public G(G g, H h, Resources resources) {
        this.c = 160;
        this.i = false;
        this.l = false;
        this.x = true;
        this.A = 0;
        this.B = 0;
        this.a = h;
        this.b = resources != null ? resources : g != null ? g.b : null;
        int iC = H.c(resources, g != null ? g.c : 0);
        this.c = iC;
        if (g == null) {
            this.g = new Drawable[10];
            this.h = 0;
            return;
        }
        this.d = g.d;
        this.e = g.e;
        this.v = true;
        this.w = true;
        this.i = g.i;
        this.l = g.l;
        this.x = g.x;
        this.y = g.y;
        this.z = g.z;
        this.A = g.A;
        this.B = g.B;
        this.C = g.C;
        this.D = g.D;
        this.E = g.E;
        this.F = g.F;
        this.G = g.G;
        this.H = g.H;
        this.I = g.I;
        if (g.c == iC) {
            if (g.j) {
                this.k = new Rect(g.k);
                this.j = true;
            }
            if (g.m) {
                this.n = g.n;
                this.o = g.o;
                this.p = g.p;
                this.q = g.q;
                this.m = true;
            }
        }
        if (g.r) {
            this.s = g.s;
            this.r = true;
        }
        if (g.t) {
            this.u = g.u;
            this.t = true;
        }
        Drawable[] drawableArr = g.g;
        this.g = new Drawable[drawableArr.length];
        this.h = g.h;
        SparseArray sparseArray = g.f;
        if (sparseArray != null) {
            this.f = sparseArray.clone();
        } else {
            this.f = new SparseArray(this.h);
        }
        int i = this.h;
        for (int i2 = 0; i2 < i; i2++) {
            if (drawableArr[i2] != null) {
                Drawable.ConstantState constantState = drawableArr[i2].getConstantState();
                if (constantState != null) {
                    this.f.put(i2, constantState);
                } else {
                    this.g[i2] = drawableArr[i2];
                }
            }
        }
    }

    public final int a(Drawable drawable) {
        int i = this.h;
        if (i >= this.g.length) {
            int i2 = i + 10;
            I i3 = (I) this;
            Drawable[] drawableArr = new Drawable[i2];
            System.arraycopy(i3.g, 0, drawableArr, 0, i);
            i3.g = drawableArr;
            int[][] iArr = new int[i2][];
            System.arraycopy(i3.f2J, 0, iArr, 0, i);
            i3.f2J = iArr;
        }
        drawable.mutate();
        drawable.setVisible(false, true);
        drawable.setCallback(this.a);
        this.g[i] = drawable;
        this.h++;
        this.e = drawable.getChangingConfigurations() | this.e;
        this.r = false;
        this.t = false;
        this.k = null;
        this.j = false;
        this.m = false;
        this.v = false;
        return i;
    }

    public void b() {
        this.m = true;
        c();
        int i = this.h;
        Drawable[] drawableArr = this.g;
        this.o = -1;
        this.n = -1;
        this.q = 0;
        this.p = 0;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (intrinsicWidth > this.n) {
                this.n = intrinsicWidth;
            }
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicHeight > this.o) {
                this.o = intrinsicHeight;
            }
            int minimumWidth = drawable.getMinimumWidth();
            if (minimumWidth > this.p) {
                this.p = minimumWidth;
            }
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumHeight > this.q) {
                this.q = minimumHeight;
            }
        }
    }

    public final void c() {
        SparseArray sparseArray = this.f;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                int iKeyAt = this.f.keyAt(i);
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.f.valueAt(i);
                Drawable[] drawableArr = this.g;
                Drawable drawableNewDrawable = constantState.newDrawable(this.b);
                if (Build.VERSION.SDK_INT >= 23) {
                    drawableNewDrawable.setLayoutDirection(this.z);
                }
                Drawable drawableMutate = drawableNewDrawable.mutate();
                drawableMutate.setCallback(this.a);
                drawableArr[iKeyAt] = drawableMutate;
            }
            this.f = null;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public boolean canApplyTheme() {
        int i = this.h;
        Drawable[] drawableArr = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            if (drawable == null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.f.get(i2);
                if (constantState != null && constantState.canApplyTheme()) {
                    return true;
                }
            } else if (drawable.canApplyTheme()) {
                return true;
            }
        }
        return false;
    }

    public final Drawable d(int i) {
        int iIndexOfKey;
        Drawable drawable = this.g[i];
        if (drawable != null) {
            return drawable;
        }
        SparseArray sparseArray = this.f;
        if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i)) < 0) {
            return null;
        }
        Drawable drawableNewDrawable = ((Drawable.ConstantState) this.f.valueAt(iIndexOfKey)).newDrawable(this.b);
        if (Build.VERSION.SDK_INT >= 23) {
            drawableNewDrawable.setLayoutDirection(this.z);
        }
        Drawable drawableMutate = drawableNewDrawable.mutate();
        drawableMutate.setCallback(this.a);
        this.g[i] = drawableMutate;
        this.f.removeAt(iIndexOfKey);
        if (this.f.size() == 0) {
            this.f = null;
        }
        return drawableMutate;
    }

    public abstract void e();

    public final void f(Resources resources) {
        if (resources != null) {
            this.b = resources;
            int iC = H.c(resources, this.c);
            int i = this.c;
            this.c = iC;
            if (i != iC) {
                this.m = false;
                this.j = false;
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.d | this.e;
    }
}
