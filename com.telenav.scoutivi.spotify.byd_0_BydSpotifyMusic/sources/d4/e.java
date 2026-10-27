package d4;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: ActivityMainBindingW840dpH480dpImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends a {
    private static final ViewDataBinding.i L;
    private static final SparseIntArray M;
    private final ConstraintLayout I;
    private final View J;
    private long K;

    static {
        ViewDataBinding.i iVar = new ViewDataBinding.i(3);
        L = iVar;
        iVar.a(0, new String[]{"fragment_login"}, new int[]{2}, new int[]{com.vivid.spotify.v.fragment_login});
        M = null;
    }

    public e(androidx.databinding.f fVar, View view) {
        this(fVar, view, ViewDataBinding.N(fVar, view, 3, L, M));
    }

    private boolean g0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 4;
        }
        return true;
    }

    private boolean h0(ObservableBoolean observableBoolean, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 1;
        }
        return true;
    }

    private boolean i0(a1 a1Var, int i7) {
        if (i7 != com.vivid.spotify.a.f5196a) {
            return false;
        }
        synchronized (this) {
            this.K |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean I() {
        synchronized (this) {
            if (this.K != 0) {
                return true;
            }
            return this.G.I();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void K() {
        synchronized (this) {
            this.K = 32L;
        }
        this.G.K();
        S();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean O(int i7, Object obj, int i8) {
        if (i7 == 0) {
            return h0((ObservableBoolean) obj, i8);
        }
        if (i7 == 1) {
            return i0((a1) obj, i8);
        }
        if (i7 != 2) {
            return false;
        }
        return g0((ObservableBoolean) obj, i8);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void W(androidx.lifecycle.o oVar) {
        super.W(oVar);
        this.G.W(oVar);
    }

    @Override // d4.a
    public void f0(MainUIViewModel mainUIViewModel) {
        this.H = mainUIViewModel;
        synchronized (this) {
            this.K |= 8;
        }
        p(com.vivid.spotify.a.f5201f);
        super.S();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0041 A[PHI: r2
  0x0041: PHI (r2v5 long) = (r2v0 long), (r2v10 long) binds: [B:9:0x001f, B:22:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void x() {
        /*
            r18 = this;
            r1 = r18
            monitor-enter(r18)
            long r2 = r1.K     // Catch: java.lang.Throwable -> L97
            r4 = 0
            r1.K = r4     // Catch: java.lang.Throwable -> L97
            monitor-exit(r18)     // Catch: java.lang.Throwable -> L97
            com.vivid.spotify.rootui.MainUIViewModel r0 = r1.H
            r6 = 45
            long r6 = r6 & r2
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            r7 = 44
            r9 = 41
            r11 = 0
            if (r6 == 0) goto L6c
            long r12 = r2 & r9
            int r6 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            r12 = 8
            r13 = 0
            if (r6 == 0) goto L41
            if (r0 == 0) goto L26
            androidx.databinding.ObservableBoolean r14 = r0.needShowSplash
            goto L27
        L26:
            r14 = r13
        L27:
            r1.a0(r11, r14)
            if (r14 == 0) goto L31
            boolean r14 = r14.q()
            goto L32
        L31:
            r14 = r11
        L32:
            if (r6 == 0) goto L3c
            if (r14 == 0) goto L39
            r15 = 128(0x80, double:6.3E-322)
            goto L3b
        L39:
            r15 = 64
        L3b:
            long r2 = r2 | r15
        L3c:
            if (r14 == 0) goto L3f
            goto L41
        L3f:
            r6 = r12
            goto L42
        L41:
            r6 = r11
        L42:
            long r14 = r2 & r7
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 == 0) goto L66
            if (r0 == 0) goto L4c
            androidx.databinding.ObservableBoolean r13 = r0.needLogin
        L4c:
            r15 = 2
            r1.a0(r15, r13)
            if (r13 == 0) goto L57
            boolean r13 = r13.q()
            goto L58
        L57:
            r13 = r11
        L58:
            if (r14 == 0) goto L62
            if (r13 == 0) goto L5f
            r14 = 512(0x200, double:2.53E-321)
            goto L61
        L5f:
            r14 = 256(0x100, double:1.265E-321)
        L61:
            long r2 = r2 | r14
        L62:
            if (r13 == 0) goto L65
            goto L66
        L65:
            r11 = r12
        L66:
            r17 = r11
            r11 = r6
            r6 = r17
            goto L6d
        L6c:
            r6 = r11
        L6d:
            long r9 = r9 & r2
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 == 0) goto L77
            android.view.View r9 = r1.J
            r9.setVisibility(r11)
        L77:
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 == 0) goto L85
            d4.a1 r7 = r1.G
            android.view.View r7 = r7.G()
            r7.setVisibility(r6)
        L85:
            r6 = 40
            long r2 = r2 & r6
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L91
            d4.a1 r2 = r1.G
            r2.d0(r0)
        L91:
            d4.a1 r0 = r1.G
            androidx.databinding.ViewDataBinding.z(r0)
            return
        L97:
            r0 = move-exception
            monitor-exit(r18)     // Catch: java.lang.Throwable -> L97
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: d4.e.x():void");
    }

    private e(androidx.databinding.f fVar, View view, Object[] objArr) {
        super(fVar, view, 3, (a1) objArr[2]);
        this.K = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.I = constraintLayout;
        constraintLayout.setTag(null);
        View view2 = (View) objArr[1];
        this.J = view2;
        view2.setTag(null);
        V(this.G);
        X(view);
        K();
    }
}
