package spi;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: RangeFilterImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n9 implements m9 {
    public final RecyclerView.Adapter<?> a;
    public final int b;
    public final int c;
    public final int d;
    public int e;

    @VisibleForTesting
    public int f;
    public final a g = new a();

    /* JADX INFO: compiled from: RangeFilterImpl.java */
    public static class a {

        @VisibleForTesting
        public int a;

        @VisibleForTesting
        public int b;

        @VisibleForTesting
        public int c;

        @VisibleForTesting
        public int d;

        @VisibleForTesting
        public int e;

        public final String toString() {
            StringBuilder sbK = c.k("ListRange{mStartIndex=");
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

    public n9(RecyclerView.Adapter<?> adapter, int i) {
        this.a = adapter;
        if (i <= 0) {
            this.c = 0;
            this.d = 0;
            this.b = 0;
        } else {
            int i2 = i / 2;
            this.c = i2;
            this.d = i - i2;
            this.b = i;
        }
    }

    @Override // spi.m9
    public final void a(int i, int i2) {
        if (i2 < 0 || i <= i2) {
            Log.e("RangeFilterImpl", "Invalid pivotIndex: " + i2 + " newCount: " + i);
            i2 = 0;
        }
        this.e = i;
        this.f = i2;
        a aVar = this.g;
        aVar.c = 0;
        aVar.d = 0;
        int i3 = this.b;
        if (i <= i3) {
            aVar.a = 0;
            aVar.b = i;
            aVar.e = i;
            return;
        }
        if (i3 <= 0) {
            aVar.a = 0;
            aVar.b = 0;
            aVar.e = 1;
            aVar.d = 1;
            return;
        }
        int i4 = this.c;
        if (i2 <= i4) {
            aVar.a = 0;
            aVar.b = i3;
            aVar.e = i3 + 1;
            aVar.d = 1;
            return;
        }
        int i5 = (i - 1) - i2;
        int i6 = this.d;
        if (i5 <= i6) {
            aVar.b = i;
            aVar.a = i - i3;
            aVar.e = i3 + 1;
            aVar.c = 1;
            return;
        }
        aVar.a = i2 - i4;
        aVar.b = i2 + i6;
        aVar.e = i3 + 2;
        aVar.c = 1;
        aVar.d = 1;
    }

    @Override // spi.m9
    public final int b() {
        return this.g.e;
    }

    @Override // spi.m9
    public final void c() {
        if (this.g.c > 0) {
            this.a.notifyItemChanged(0);
        }
        if (this.g.d > 0) {
            this.a.notifyItemChanged(r0.e - 1);
        }
    }

    @Override // spi.m9
    public final int d(int i) {
        a aVar = this.g;
        int i2 = i - aVar.c;
        int i3 = aVar.a;
        int i4 = i2 + i3;
        if (i4 < i3 || aVar.b <= i4) {
            return -1;
        }
        return i4;
    }

    @Override // spi.m9
    public final void e() {
        a aVar = this.g;
        if (aVar.d == 1) {
            this.a.notifyItemRemoved(aVar.e - 1);
            RecyclerView.Adapter<?> adapter = this.a;
            a aVar2 = this.g;
            adapter.notifyItemRangeInserted(aVar2.e - 1, this.e - aVar2.b);
        }
        a aVar3 = this.g;
        if (aVar3.c == 1) {
            this.a.notifyItemRangeInserted(1, aVar3.a);
            this.a.notifyItemRemoved(0);
        }
    }

    @Override // spi.m9
    public final void f() {
        if (this.g.d == 1) {
            this.a.notifyItemInserted(this.e);
            RecyclerView.Adapter<?> adapter = this.a;
            int i = this.g.b;
            adapter.notifyItemRangeRemoved(i, this.e - i);
        }
        a aVar = this.g;
        if (aVar.c == 1) {
            this.a.notifyItemRangeRemoved(0, aVar.a);
            this.a.notifyItemInserted(0);
        }
    }

    public final String toString() {
        StringBuilder sbK = c.k("RangeFilterImpl{mMaxItemsFirstHalf=");
        sbK.append(this.c);
        sbK.append("mMaxItemsSecondHalf=");
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
