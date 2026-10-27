package d4;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: FragmentLoginBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b1 extends a1 {
    private static final ViewDataBinding.i M = null;
    private static final SparseIntArray N;
    private final RelativeLayout K;
    private long L;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        N = sparseIntArray;
        sparseIntArray.put(com.vivid.spotify.t.logo, 3);
    }

    public b1(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 4, M, N));
    }

    private boolean e0(androidx.lifecycle.t<Boolean> tVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 1;
        }
        return true;
    }

    private boolean f0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 4;
        }
        return true;
    }

    private boolean g0(androidx.databinding.l<String> lVar, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.L |= 2;
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
            this.L = 16L;
        }
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return e0((androidx.lifecycle.t) obj, i8);
        }
        if (i7 == 1) {
            return g0((androidx.databinding.l) obj, i8);
        }
        if (i7 != 2) {
            return false;
        }
        return f0((androidx.databinding.l) obj, i8);
    }

    @Override // d4.a1
    public void d0(MainUIViewModel mainUIViewModel) {
        this.J = mainUIViewModel;
        synchronized (this) {
            this.L |= 8;
        }
        p(com.vivid.spotify.a.f5202g);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            r19 = this;
            r1 = r19
            monitor-enter(r19)
            long r2 = r1.L     // Catch: java.lang.Throwable -> Lb9
            r4 = 0
            r1.L = r4     // Catch: java.lang.Throwable -> Lb9
            monitor-exit(r19)     // Catch: java.lang.Throwable -> Lb9
            r0 = 0
            com.vivid.spotify.rootui.MainUIViewModel r6 = r1.J
            r7 = 31
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            r8 = 26
            r10 = 28
            r12 = 25
            r14 = 0
            if (r7 == 0) goto L7f
            long r16 = r2 & r12
            int r7 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r7 == 0) goto L4b
            if (r6 == 0) goto L26
            androidx.lifecycle.t<java.lang.Boolean> r0 = r6.isInSafeMode
            goto L27
        L26:
            r0 = 0
        L27:
            r1.Z(r14, r0)
            if (r0 == 0) goto L33
            java.lang.Object r0 = r0.e()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L34
        L33:
            r0 = 0
        L34:
            boolean r0 = androidx.databinding.ViewDataBinding.U(r0)
            if (r7 == 0) goto L43
            if (r0 == 0) goto L3f
            r16 = 64
            goto L41
        L3f:
            r16 = 32
        L41:
            long r2 = r2 | r16
        L43:
            if (r0 == 0) goto L49
            r0 = 1050253722(0x3e99999a, float:0.3)
            goto L4b
        L49:
            r0 = 1065353216(0x3f800000, float:1.0)
        L4b:
            long r16 = r2 & r8
            int r7 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r7 == 0) goto L64
            if (r6 == 0) goto L56
            androidx.databinding.l<java.lang.String> r7 = r6.loginText
            goto L57
        L56:
            r7 = 0
        L57:
            r15 = 1
            r1.a0(r15, r7)
            if (r7 == 0) goto L64
            java.lang.Object r7 = r7.q()
            java.lang.String r7 = (java.lang.String) r7
            goto L65
        L64:
            r7 = 0
        L65:
            long r17 = r2 & r10
            int r15 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r15 == 0) goto L80
            if (r6 == 0) goto L70
            androidx.databinding.l<java.lang.String> r6 = r6.loginButton
            goto L71
        L70:
            r6 = 0
        L71:
            r15 = 2
            r1.a0(r15, r6)
            if (r6 == 0) goto L80
            java.lang.Object r6 = r6.q()
            r15 = r6
            java.lang.String r15 = (java.lang.String) r15
            goto L81
        L7f:
            r7 = 0
        L80:
            r15 = 0
        L81:
            long r12 = r12 & r2
            int r6 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r6 == 0) goto L93
            int r6 = androidx.databinding.ViewDataBinding.E()
            r12 = 11
            if (r6 < r12) goto L93
            android.widget.Button r6 = r1.G
            r6.setAlpha(r0)
        L93:
            long r10 = r10 & r2
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 == 0) goto L9d
            android.widget.Button r0 = r1.G
            n0.e.c(r0, r15)
        L9d:
            r10 = 16
            long r10 = r10 & r2
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 == 0) goto Lae
            android.widget.Button r0 = r1.G
            d4.a0.c(r0, r14, r14)
            android.widget.TextView r0 = r1.I
            d4.a0.c(r0, r14, r14)
        Lae:
            long r2 = r2 & r8
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto Lb8
            android.widget.TextView r0 = r1.I
            n0.e.c(r0, r7)
        Lb8:
            return
        Lb9:
            r0 = move-exception
            monitor-exit(r19)     // Catch: java.lang.Throwable -> Lb9
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.b1.x():void");
    }

    private b1(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 3, (Button) objArr[2], (ImageView) objArr[3], (TextView) objArr[1]);
        this.L = -1L;
        this.G.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.K = relativeLayout;
        relativeLayout.setTag(null);
        this.I.setTag(null);
        X(view);
        K();
    }
}
