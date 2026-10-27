package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class W5 {
    public C0752f5 a;
    public RecyclerView b;
    public final InterfaceC1418r6 c = new T5(this);
    public final InterfaceC1418r6 d = new U5(this);
    public C1473s6 e = new C1473s6(this.c);
    public C1473s6 f = new C1473s6(this.d);
    public boolean g = false;
    public boolean h = false;
    public boolean i = true;
    public boolean j = true;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;

    public static int e(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
    }

    public static V5 y(Context context, AttributeSet attributeSet, int i, int i2) {
        V5 v5 = new V5();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.o0, i, i2);
        v5.a = typedArrayObtainStyledAttributes.getInt(9, 1);
        v5.b = typedArrayObtainStyledAttributes.getInt(7, 1);
        v5.c = typedArrayObtainStyledAttributes.getBoolean(6, false);
        v5.d = typedArrayObtainStyledAttributes.getBoolean(8, false);
        typedArrayObtainStyledAttributes.recycle();
        return v5;
    }

    public int A() {
        return 0;
    }

    public abstract boolean B();

    public boolean C() {
        return false;
    }

    public boolean D() {
        return false;
    }

    public void E() {
    }

    @Deprecated
    public void F() {
    }

    public abstract void G(RecyclerView recyclerView, C0474a6 c0474a6);

    public abstract void H(AccessibilityEvent accessibilityEvent);

    public void I(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z = true;
        if (!recyclerView.canScrollVertically(1) && !this.b.canScrollVertically(-1) && !this.b.canScrollHorizontally(-1) && !this.b.canScrollHorizontally(1)) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        Objects.requireNonNull(this.b);
    }

    public View J() {
        return null;
    }

    public void K(RecyclerView recyclerView, int i, int i2) {
    }

    public void L(RecyclerView recyclerView, int i, int i2, int i3) {
    }

    public void M(RecyclerView recyclerView, int i, int i2) {
    }

    public void N() {
    }

    public void O(RecyclerView recyclerView, int i, int i2, Object obj) {
        N();
    }

    public void P(int i, int i2) {
        this.b.f(i, i2);
    }

    @Deprecated
    public boolean Q(RecyclerView recyclerView) {
        return recyclerView.u();
    }

    public boolean R(RecyclerView recyclerView, View view, View view2) {
        return Q(recyclerView);
    }

    public abstract void S(Parcelable parcelable);

    public abstract Parcelable T();

    public void U(int i) {
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0068 A[PHI: r0
  0x0068: PHI (r0v6 int) = (r0v2 int), (r0v10 int) binds: [B:22:0x0059, B:15:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean V(int r5) {
        /*
            r4 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r4.b
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            r2 = 4096(0x1000, float:5.74E-42)
            r3 = 1
            if (r5 == r2) goto L3e
            r2 = 8192(0x2000, float:1.148E-41)
            if (r5 == r2) goto L12
            r5 = 0
            r0 = 0
            goto L69
        L12:
            r5 = -1
            boolean r0 = r0.canScrollVertically(r5)
            if (r0 == 0) goto L27
            int r0 = r4.o
            int r2 = r4.w()
            int r0 = r0 - r2
            int r2 = r4.t()
            int r0 = r0 - r2
            int r0 = -r0
            goto L28
        L27:
            r0 = 0
        L28:
            androidx.recyclerview.widget.RecyclerView r2 = r4.b
            boolean r5 = r2.canScrollHorizontally(r5)
            if (r5 == 0) goto L68
            int r5 = r4.n
            int r2 = r4.u()
            int r5 = r5 - r2
            int r2 = r4.v()
            int r5 = r5 - r2
            int r5 = -r5
            goto L69
        L3e:
            boolean r5 = r0.canScrollVertically(r3)
            if (r5 == 0) goto L52
            int r5 = r4.o
            int r0 = r4.w()
            int r5 = r5 - r0
            int r0 = r4.t()
            int r5 = r5 - r0
            r0 = r5
            goto L53
        L52:
            r0 = 0
        L53:
            androidx.recyclerview.widget.RecyclerView r5 = r4.b
            boolean r5 = r5.canScrollHorizontally(r3)
            if (r5 == 0) goto L68
            int r5 = r4.n
            int r2 = r4.u()
            int r5 = r5 - r2
            int r2 = r4.v()
            int r5 = r5 - r2
            goto L69
        L68:
            r5 = 0
        L69:
            if (r0 != 0) goto L6e
            if (r5 != 0) goto L6e
            return r1
        L6e:
            androidx.recyclerview.widget.RecyclerView r1 = r4.b
            r1.I(r5, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.W5.V(int):boolean");
    }

    public boolean W() {
        return false;
    }

    public void X(C0474a6 c0474a6) {
        int iP = p() - 1;
        if (iP < 0) {
            return;
        }
        RecyclerView.p(o(iP));
        throw null;
    }

    public void Y(C0474a6 c0474a6) {
        int size = c0474a6.a.size();
        int i = size - 1;
        if (i >= 0) {
            throw null;
        }
        c0474a6.a.clear();
        ArrayList arrayList = c0474a6.b;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (size > 0) {
            this.b.invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean Z(androidx.recyclerview.widget.RecyclerView r8, android.view.View r9, android.graphics.Rect r10, boolean r11, boolean r12) {
        /*
            r7 = this;
            int[] r9 = r7.q(r9, r10)
            r10 = 0
            r0 = r9[r10]
            r1 = 1
            r9 = r9[r1]
            if (r12 == 0) goto L49
            android.view.View r12 = r8.getFocusedChild()
            if (r12 != 0) goto L14
        L12:
            r12 = 0
            goto L47
        L14:
            int r2 = r7.u()
            int r3 = r7.w()
            int r4 = r7.n
            int r5 = r7.v()
            int r4 = r4 - r5
            int r5 = r7.o
            int r6 = r7.t()
            int r5 = r5 - r6
            androidx.recyclerview.widget.RecyclerView r6 = r7.b
            android.graphics.Rect r6 = r6.y
            androidx.recyclerview.widget.RecyclerView.q(r12, r6)
            int r12 = r6.left
            int r12 = r12 - r0
            if (r12 >= r4) goto L12
            int r12 = r6.right
            int r12 = r12 - r0
            if (r12 <= r2) goto L12
            int r12 = r6.top
            int r12 = r12 - r9
            if (r12 >= r5) goto L12
            int r12 = r6.bottom
            int r12 = r12 - r9
            if (r12 > r3) goto L46
            goto L12
        L46:
            r12 = 1
        L47:
            if (r12 == 0) goto L4e
        L49:
            if (r0 != 0) goto L4f
            if (r9 == 0) goto L4e
            goto L4f
        L4e:
            return r10
        L4f:
            if (r11 == 0) goto L55
            r8.scrollBy(r0, r9)
            goto L58
        L55:
            r8.I(r0, r9)
        L58:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.W5.Z(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public void a(String str) {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            if (recyclerView.u()) {
                if (str != null) {
                    throw new IllegalStateException(str);
                }
                StringBuilder sbF = Z7.f("Cannot call this method while RecyclerView is computing a layout or scrolling");
                sbF.append(recyclerView.m());
                throw new IllegalStateException(sbF.toString());
            }
            if (recyclerView.R > 0) {
                StringBuilder sbF2 = Z7.f("");
                sbF2.append(recyclerView.m());
                Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(sbF2.toString()));
            }
        }
    }

    public void a0() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public abstract boolean b();

    public void b0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.b = null;
            this.a = null;
            this.n = 0;
            this.o = 0;
        } else {
            this.b = recyclerView;
            this.a = recyclerView.v;
            this.n = recyclerView.getWidth();
            this.o = recyclerView.getHeight();
        }
        this.l = 1073741824;
        this.m = 1073741824;
    }

    public abstract boolean c();

    public abstract boolean c0();

    public boolean d(X5 x5) {
        return x5 != null;
    }

    public abstract int f(C0586c6 c0586c6);

    public abstract int g(C0586c6 c0586c6);

    public abstract int h(C0586c6 c0586c6);

    public abstract int i(C0586c6 c0586c6);

    public abstract int j(C0586c6 c0586c6);

    public abstract int k(C0586c6 c0586c6);

    public abstract X5 l();

    public X5 m(Context context, AttributeSet attributeSet) {
        return new X5(context, attributeSet);
    }

    public X5 n(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof X5 ? new X5((X5) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new X5((ViewGroup.MarginLayoutParams) layoutParams) : new X5(layoutParams);
    }

    public View o(int i) {
        C0752f5 c0752f5 = this.a;
        if (c0752f5 == null) {
            return null;
        }
        return ((L5) c0752f5.a).a(c0752f5.b(i));
    }

    public int p() {
        C0752f5 c0752f5 = this.a;
        if (c0752f5 != null) {
            return c0752f5.a();
        }
        return 0;
    }

    public final int[] q(View view, Rect rect) {
        int[] iArr = new int[2];
        int iU = u();
        int iW = w();
        int iV = this.n - v();
        int iT = this.o - t();
        int left = (view.getLeft() + rect.left) - view.getScrollX();
        int top = (view.getTop() + rect.top) - view.getScrollY();
        int iWidth = rect.width() + left;
        int iHeight = rect.height() + top;
        int i = left - iU;
        int iMin = Math.min(0, i);
        int i2 = top - iW;
        int iMin2 = Math.min(0, i2);
        int i3 = iWidth - iV;
        int iMax = Math.max(0, i3);
        int iMax2 = Math.max(0, iHeight - iT);
        if (s() != 1) {
            if (iMin == 0) {
                iMin = Math.min(i, iMax);
            }
            iMax = iMin;
        } else if (iMax == 0) {
            iMax = Math.max(iMin, i3);
        }
        if (iMin2 == 0) {
            iMin2 = Math.min(i2, iMax2);
        }
        iArr[0] = iMax;
        iArr[1] = iMin2;
        return iArr;
    }

    public int r(C0474a6 c0474a6, C0586c6 c0586c6) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            return 1;
        }
        Objects.requireNonNull(recyclerView);
        return 1;
    }

    public int s() {
        return AbstractC1251o3.k(this.b);
    }

    public int t() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public int u() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public int v() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public int w() {
        RecyclerView recyclerView = this.b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int x(View view) {
        Objects.requireNonNull((X5) view.getLayoutParams());
        throw null;
    }

    public int z(C0474a6 c0474a6, C0586c6 c0586c6) {
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            return 1;
        }
        Objects.requireNonNull(recyclerView);
        return 1;
    }
}
