package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;

/* JADX INFO: compiled from: UxrPivotFilterImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class lc implements kc {
    public final RecyclerView.Adapter<?> b;
    public final int c;
    public final int d;
    public int e;
    public int f;
    public final b g;
    public final b h;

    /* JADX INFO: compiled from: UxrPivotFilterImpl.java */
    public static class b {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;

        private b() {
        }

        public final int a(int i) {
            return (i - this.a) + this.c;
        }

        @NonNull
        public final String toString() {
            StringBuilder sbK = c.k("ListRange{ mStartIndex=");
            sbK.append(this.a);
            sbK.append(", mEndIndex=");
            sbK.append(this.b);
            sbK.append(", mClampedHead=");
            sbK.append(this.c);
            sbK.append(", mClampedTail=");
            sbK.append(this.d);
            sbK.append(", mLimitedCount=");
            sbK.append(this.e);
            sbK.append('}');
            return sbK.toString();
        }
    }

    public lc(RecyclerView.Adapter<?> adapter, int i) {
        this.g = new b();
        this.h = new b();
        this.b = adapter;
        if (i <= 0) {
            this.d = 0;
            this.c = 0;
        } else {
            int i2 = i / 2;
            this.d = i2;
            this.c = (i2 * 2) + 1;
        }
    }

    @Override // spi.kc
    public final void a(int i, int i2) {
        if (i2 < 0 || i <= i2) {
            Log.e("UxrPivotFilterImpl", "Invalid pivotIndex: " + i2 + " newCount: " + i);
            i2 = 0;
        }
        this.e = i;
        this.f = i2;
        b bVar = this.g;
        bVar.c = 0;
        bVar.d = 0;
        int i3 = this.c;
        if (i <= i3) {
            bVar.a = 0;
            bVar.b = i;
            bVar.e = i;
            return;
        }
        if (i3 <= 0) {
            bVar.a = 0;
            bVar.b = 0;
            bVar.e = 1;
            bVar.d = 1;
            return;
        }
        int i4 = this.d;
        if (i2 <= i4) {
            bVar.a = 0;
            bVar.b = i3;
            bVar.e = i3 + 1;
            bVar.d = 1;
            return;
        }
        if ((i - 1) - i2 <= i4) {
            bVar.b = i;
            bVar.a = i - i3;
            bVar.e = i3 + 1;
            bVar.c = 1;
            return;
        }
        bVar.a = i2 - i4;
        bVar.b = i2 + i4 + 1;
        bVar.e = i3 + 2;
        bVar.c = 1;
        bVar.d = 1;
    }

    @Override // spi.kc
    public final int b() {
        return this.g.e;
    }

    @Override // spi.kc
    public final void c() {
        if (this.g.c > 0) {
            this.b.notifyItemChanged(0);
        }
        if (this.g.d > 0) {
            this.b.notifyItemChanged(r0.e - 1);
        }
    }

    @Override // spi.kc
    public final int d(int i) {
        b bVar = this.g;
        int i2 = i - bVar.c;
        int i3 = bVar.a;
        int i4 = i2 + i3;
        if (i4 < i3 || bVar.b <= i4) {
            return -1;
        }
        return i4;
    }

    @Override // spi.kc
    public final void e(int i) {
        if (this.f == i) {
            return;
        }
        b bVar = this.h;
        b bVar2 = this.g;
        Objects.requireNonNull(bVar);
        bVar.a = bVar2.a;
        bVar.b = bVar2.b;
        bVar.c = bVar2.c;
        bVar.d = bVar2.d;
        bVar.e = bVar2.e;
        a(this.e, i);
        if (Log.isLoggable("UxrPivotFilterImpl", 3)) {
            StringBuilder sbL = c.l("updatePivotIndex pivot: ", i, " saved: ");
            sbL.append(this.h);
            sbL.append(" new: ");
            sbL.append(this.g);
            Log.d("UxrPivotFilterImpl", sbL.toString());
        }
        b bVar3 = this.h;
        b bVar4 = this.g;
        Objects.requireNonNull(bVar3);
        if (!(bVar4.b > bVar3.a && bVar3.b > bVar4.a)) {
            this.b.notifyDataSetChanged();
            return;
        }
        b bVar5 = this.h;
        if (bVar5.d < this.g.d) {
            this.b.notifyItemInserted(bVar5.e);
        }
        b bVar6 = this.h;
        if (bVar6.d > this.g.d) {
            this.b.notifyItemRemoved(bVar6.e - 1);
        }
        b bVar7 = this.h;
        int i2 = bVar7.b;
        if (i2 < this.g.b) {
            this.b.notifyItemRangeInserted(bVar7.a(i2), this.g.b - this.h.b);
        }
        b bVar8 = this.h;
        int i3 = bVar8.b;
        int i4 = this.g.b;
        if (i3 > i4) {
            this.b.notifyItemRangeRemoved(bVar8.a(i4), this.h.b - this.g.b);
        }
        b bVar9 = this.h;
        int i5 = bVar9.a;
        if (i5 > this.g.a) {
            this.b.notifyItemRangeInserted(bVar9.a(i5), this.h.a - this.g.a);
        }
        b bVar10 = this.h;
        int i6 = bVar10.a;
        if (i6 < this.g.a) {
            this.b.notifyItemRangeRemoved(bVar10.a(i6), this.g.a - this.h.a);
        }
        if (this.h.c < this.g.c) {
            this.b.notifyItemInserted(0);
        }
        if (this.h.c > this.g.c) {
            this.b.notifyItemRemoved(0);
        }
    }

    @Override // spi.kc
    public final int f(int i) {
        b bVar = this.g;
        if (bVar.a > i || i >= bVar.b) {
            return -1;
        }
        return bVar.a(i);
    }

    @NonNull
    public final String toString() {
        StringBuilder sbK = c.k("UxrPivotFilterImpl{ mMaxItemsDiv2=");
        sbK.append(this.d);
        sbK.append(", mUnlimitedCount=");
        sbK.append(this.e);
        sbK.append(", mPivotIndex=");
        sbK.append(this.f);
        sbK.append(", mRange=");
        sbK.append(this.g.toString());
        sbK.append('}');
        return sbK.toString();
    }
}
