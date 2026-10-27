package b.c.a.e;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import b.c.a.f.w;
import com.byd.globalnotification.R;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    public static final SparseIntArray B;
    public long A;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        B = sparseIntArray;
        sparseIntArray.put(R.id.tv_msg, 5);
        B.put(R.id.rvParent, 6);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public c(a.k.f fVar, View view) {
        Object[] objArrQ = ViewDataBinding.q(fVar, view, 7, B);
        super(fVar, view, 1, (ConstraintLayout) objArrQ[0], (LinearLayout) objArrQ[3], (RecyclerView) objArrQ[2], (ConstraintLayout) objArrQ[6], (ImageView) objArrQ[4], (TextView) objArrQ[1], (TextView) objArrQ[5]);
        this.A = -1L;
        this.u.setTag(null);
        this.v.setTag(null);
        this.w.setTag(null);
        this.x.setTag(null);
        this.y.setTag(null);
        view.setTag(a.k.r.a.dataBinding, this);
        synchronized (this) {
            this.A = 4L;
        }
        u();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k() {
        /*
            r21 = this;
            r1 = r21
            monitor-enter(r21)
            long r2 = r1.A     // Catch: java.lang.Throwable -> L9c
            r4 = 0
            r1.A = r4     // Catch: java.lang.Throwable -> L9c
            monitor-exit(r21)     // Catch: java.lang.Throwable -> L9c
            b.c.a.f.w r0 = r1.z
            r6 = 7
            long r6 = r6 & r2
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            r7 = 6
            r9 = 0
            if (r6 == 0) goto L49
            if (r0 == 0) goto L1d
            a.k.n<com.byd.globalnotification.notification.NotifyMsgBean> r11 = r0.f1759d
            e.a.a.f<com.byd.globalnotification.notification.NotifyMsgBean> r12 = r0.h
            goto L1f
        L1d:
            r11 = 0
            r12 = 0
        L1f:
            androidx.databinding.ViewDataBinding$f r13 = androidx.databinding.ViewDataBinding.q
            r1.x(r9, r11, r13)
            if (r11 == 0) goto L2b
            int r13 = r11.size()
            goto L2c
        L2b:
            r13 = r9
        L2c:
            r14 = 1
            if (r13 != 0) goto L31
            r15 = r14
            goto L32
        L31:
            r15 = r9
        L32:
            if (r13 <= 0) goto L35
            goto L36
        L35:
            r14 = r9
        L36:
            long r16 = r2 & r7
            int r13 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r13 == 0) goto L4d
            if (r0 == 0) goto L4d
            b.c.a.h.c.b r13 = r0.m
            b.c.a.h.c.b r0 = r0.n
            r20 = r13
            r13 = r11
            r11 = r15
            r15 = r20
            goto L51
        L49:
            r14 = r9
            r15 = r14
            r11 = 0
            r12 = 0
        L4d:
            r13 = r11
            r11 = r15
            r0 = 0
            r15 = 0
        L51:
            if (r6 == 0) goto L8a
            android.widget.LinearLayout r6 = r1.v
            a.b.k.l.i.x0(r6, r11, r9, r9)
            androidx.recyclerview.widget.RecyclerView r11 = r1.w
            if (r12 == 0) goto L82
            e.a.a.e r6 = new e.a.a.e
            r6.<init>(r12)
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r12 = r6
            r6 = r14
            r14 = r16
            r10 = r15
            r15 = r17
            r16 = r18
            r17 = r19
            c.b.j.a.d(r11, r12, r13, r14, r15, r16, r17)
            androidx.recyclerview.widget.RecyclerView r11 = r1.w
            a.b.k.l.i.x0(r11, r6, r9, r9)
            android.widget.ImageView r11 = r1.x
            a.b.k.l.i.x0(r11, r6, r9, r9)
            goto L8b
        L82:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "onItemBind == null"
            r0.<init>(r1)
            throw r0
        L8a:
            r10 = r15
        L8b:
            long r2 = r2 & r7
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L9b
            android.widget.ImageView r2 = r1.x
            r3 = 0
            a.b.k.l.i.Y(r2, r10, r9, r3)
            android.widget.TextView r1 = r1.y
            a.b.k.l.i.Y(r1, r0, r9, r3)
        L9b:
            return
        L9c:
            r0 = move-exception
            monitor-exit(r21)     // Catch: java.lang.Throwable -> L9c
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.e.c.k():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean n() {
        synchronized (this) {
            return this.A != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        if (i2 != 0) {
            return false;
        }
        synchronized (this) {
            this.A |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean v(int i, Object obj) {
        if (2 != i) {
            return false;
        }
        this.z = (w) obj;
        synchronized (this) {
            this.A |= 2;
        }
        i(2);
        super.u();
        return true;
    }
}
