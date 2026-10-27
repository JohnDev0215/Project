package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;

/* JADX INFO: compiled from: DetailErrorLayoutBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 extends b0 {
    private static final ViewDataBinding.i M = null;
    private static final SparseIntArray N = null;
    private final TextView J;
    private final Button K;
    private long L;

    public c0(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, M, N));
    }

    private boolean f0(LiveData<Boolean> liveData, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            return this.L != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.L = 4L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 != 0) {
            return false;
        }
        return f0((LiveData) obj, i8);
    }

    @Override // d4.b0
    public void d0(LiveData<Boolean> liveData) {
        Z(0, liveData);
        this.H = liveData;
        synchronized (this) {
            this.L |= 1;
        }
        p(com.vivid.spotify.a.f5197b);
        super.S();
    }

    @Override // d4.b0
    public void e0(View.OnClickListener onClickListener) {
        this.I = onClickListener;
        synchronized (this) {
            this.L |= 2;
        }
        p(com.vivid.spotify.a.f5199d);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033 A[PHI: r0
  0x0033: PHI (r0v2 long) = (r0v1 long), (r0v7 long) binds: [B:6:0x0014, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            r13 = this;
            monitor-enter(r13)
            long r0 = r13.L     // Catch: java.lang.Throwable -> L5d
            r2 = 0
            r13.L = r2     // Catch: java.lang.Throwable -> L5d
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L5d
            android.view.View$OnClickListener r4 = r13.I
            androidx.lifecycle.LiveData<java.lang.Boolean> r5 = r13.H
            r6 = 0
            r7 = 5
            long r9 = r0 & r7
            int r9 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            r10 = 0
            if (r9 == 0) goto L33
            if (r5 == 0) goto L1f
            java.lang.Object r5 = r5.e()
            r6 = r5
            java.lang.Boolean r6 = (java.lang.Boolean) r6
        L1f:
            boolean r5 = androidx.databinding.ViewDataBinding.U(r6)
            if (r9 == 0) goto L2d
            if (r5 == 0) goto L2a
            r11 = 16
            goto L2c
        L2a:
            r11 = 8
        L2c:
            long r0 = r0 | r11
        L2d:
            if (r5 == 0) goto L30
            goto L33
        L30:
            r5 = 8
            goto L34
        L33:
            r5 = r10
        L34:
            long r6 = r0 & r7
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 == 0) goto L3f
            android.widget.LinearLayout r6 = r13.G
            r6.setVisibility(r5)
        L3f:
            r5 = 4
            long r5 = r5 & r0
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r5 == 0) goto L50
            android.widget.TextView r5 = r13.J
            d4.a0.c(r5, r10, r10)
            android.widget.Button r5 = r13.K
            d4.a0.c(r5, r10, r10)
        L50:
            r5 = 6
            long r0 = r0 & r5
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L5c
            android.widget.Button r0 = r13.K
            r0.setOnClickListener(r4)
        L5c:
            return
        L5d:
            r0 = move-exception
            monitor-exit(r13)     // Catch: java.lang.Throwable -> L5d
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.c0.x():void");
    }

    private c0(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 1, (LinearLayout) objArr[0]);
        this.L = -1L;
        this.G.setTag(null);
        TextView textView = (TextView) objArr[1];
        this.J = textView;
        textView.setTag(null);
        Button button = (Button) objArr[2];
        this.K = button;
        button.setTag(null);
        X(view);
        K();
    }
}
