package androidx.recyclerview.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import defpackage.A5;
import defpackage.AbstractC0865h6;
import defpackage.B5;
import defpackage.C0474a6;
import defpackage.C0586c6;
import defpackage.C1857z5;
import defpackage.C5;
import defpackage.H5;
import defpackage.V5;
import defpackage.W5;
import defpackage.X5;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends W5 {
    public B5 q;
    public H5 r;
    public boolean s;
    public boolean t;
    public int p = 1;
    public boolean u = false;
    public boolean v = false;
    public boolean w = true;
    public int x = -1;
    public int y = Integer.MIN_VALUE;
    public SavedState z = null;
    public final C1857z5 A = new C1857z5();
    public final A5 B = new A5();
    public int C = 2;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C5();
        public int s;
        public int t;
        public boolean u;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.s);
            parcel.writeInt(this.t);
            parcel.writeInt(this.u ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.s = parcel.readInt();
            this.t = parcel.readInt();
            this.u = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.s = savedState.s;
            this.t = savedState.t;
            this.u = savedState.u;
        }
    }

    public LinearLayoutManager(int i, boolean z) {
        this.t = false;
        m0(i);
        a(null);
        if (z == this.t) {
            return;
        }
        this.t = z;
        a0();
    }

    @Override // defpackage.W5
    public boolean B() {
        return true;
    }

    @Override // defpackage.W5
    public void G(RecyclerView recyclerView, C0474a6 c0474a6) {
        F();
    }

    @Override // defpackage.W5
    public void H(AccessibilityEvent accessibilityEvent) {
        C0474a6 c0474a6 = this.b.s;
        I(accessibilityEvent);
        if (p() > 0) {
            View viewJ0 = j0(0, p(), false, true);
            if (viewJ0 != null) {
                x(viewJ0);
                throw null;
            }
            accessibilityEvent.setFromIndex(-1);
            View viewJ02 = j0(p() - 1, -1, false, true);
            if (viewJ02 == null) {
                accessibilityEvent.setToIndex(-1);
            } else {
                x(viewJ02);
                throw null;
            }
        }
    }

    @Override // defpackage.W5
    public void S(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.z = (SavedState) parcelable;
            a0();
        }
    }

    @Override // defpackage.W5
    public Parcelable T() {
        SavedState savedState = this.z;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (p() <= 0) {
            savedState2.s = -1;
            return savedState2;
        }
        g0();
        boolean z = this.s ^ this.u;
        savedState2.u = z;
        if (!z) {
            x(l0());
            throw null;
        }
        View viewK0 = k0();
        savedState2.t = this.r.d() - this.r.b(viewK0);
        x(viewK0);
        throw null;
    }

    @Override // defpackage.W5
    public void a(String str) {
        if (this.z == null) {
            super.a(str);
        }
    }

    @Override // defpackage.W5
    public boolean b() {
        return this.p == 0;
    }

    @Override // defpackage.W5
    public boolean c() {
        return this.p == 1;
    }

    @Override // defpackage.W5
    public boolean c0() {
        return this.z == null && this.s == this.v;
    }

    public final int d0(C0586c6 c0586c6) {
        if (p() == 0) {
            return 0;
        }
        g0();
        return AbstractC0865h6.a(c0586c6, this.r, i0(!this.w, true), h0(!this.w, true), this, this.w);
    }

    public final int e0(C0586c6 c0586c6) {
        if (p() == 0) {
            return 0;
        }
        g0();
        AbstractC0865h6.b(c0586c6, this.r, i0(!this.w, true), h0(!this.w, true), this, this.w, this.u);
        return 0;
    }

    @Override // defpackage.W5
    public int f(C0586c6 c0586c6) {
        return d0(c0586c6);
    }

    public final int f0(C0586c6 c0586c6) {
        if (p() == 0) {
            return 0;
        }
        g0();
        return AbstractC0865h6.c(c0586c6, this.r, i0(!this.w, true), h0(!this.w, true), this, this.w);
    }

    @Override // defpackage.W5
    public int g(C0586c6 c0586c6) {
        e0(c0586c6);
        return 0;
    }

    public void g0() {
        if (this.q == null) {
            this.q = new B5();
        }
    }

    @Override // defpackage.W5
    public int h(C0586c6 c0586c6) {
        return f0(c0586c6);
    }

    public final View h0(boolean z, boolean z2) {
        return this.u ? j0(0, p(), z, z2) : j0(p() - 1, -1, z, z2);
    }

    @Override // defpackage.W5
    public int i(C0586c6 c0586c6) {
        return d0(c0586c6);
    }

    public final View i0(boolean z, boolean z2) {
        return this.u ? j0(p() - 1, -1, z, z2) : j0(0, p(), z, z2);
    }

    @Override // defpackage.W5
    public int j(C0586c6 c0586c6) {
        e0(c0586c6);
        return 0;
    }

    public View j0(int i, int i2, boolean z, boolean z2) {
        g0();
        int i3 = z ? 24579 : 320;
        int i4 = z2 ? 320 : 0;
        return this.p == 0 ? this.e.a(i, i2, i3, i4) : this.f.a(i, i2, i3, i4);
    }

    @Override // defpackage.W5
    public int k(C0586c6 c0586c6) {
        return f0(c0586c6);
    }

    public final View k0() {
        return o(this.u ? 0 : p() - 1);
    }

    @Override // defpackage.W5
    public X5 l() {
        return new X5(-2, -2);
    }

    public final View l0() {
        return o(this.u ? p() - 1 : 0);
    }

    public void m0(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(Z7.a("invalid orientation:", i));
        }
        a(null);
        if (i != this.p || this.r == null) {
            H5 h5A = H5.a(this, i);
            this.r = h5A;
            this.A.a = h5A;
            this.p = i;
            a0();
        }
    }

    public void n0(boolean z) {
        a(null);
        if (this.v == z) {
            return;
        }
        this.v = z;
        a0();
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.t = false;
        V5 v5Y = W5.y(context, attributeSet, i, i2);
        m0(v5Y.a);
        boolean z = v5Y.c;
        a(null);
        if (z != this.t) {
            this.t = z;
            a0();
        }
        n0(v5Y.d);
    }
}
