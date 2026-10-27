package b.c.a.e;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import b.c.a.f.w;
import com.byd.globalnotification.R;
import com.byd.globalnotification.notification.NotifyMsgBean;

/* JADX INFO: loaded from: classes.dex */
public class k extends j {
    public static final SparseIntArray A;
    public final ConstraintLayout y;
    public long z;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        A = sparseIntArray;
        sparseIntArray.put(R.id.msg_item_body, 3);
        A.put(R.id.divider, 4);
        A.put(R.id.top_margin, 5);
        A.put(R.id.bottom_margin, 6);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public k(a.k.f fVar, View view) {
        Object[] objArrQ = ViewDataBinding.q(fVar, view, 7, A);
        super(fVar, view, 3, (View) objArrQ[6], (LinearLayout) objArrQ[2], (View) objArrQ[4], (ImageView) objArrQ[1], (View) objArrQ[3], (View) objArrQ[5]);
        this.z = -1L;
        this.u.setTag(null);
        this.v.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArrQ[0];
        this.y = constraintLayout;
        constraintLayout.setTag(null);
        view.setTag(a.k.r.a.dataBinding, this);
        synchronized (this) {
            this.z = 32L;
        }
        u();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k() {
        /*
            r24 = this;
            r1 = r24
            monitor-enter(r24)
            long r2 = r1.z     // Catch: java.lang.Throwable -> Lbf
            r4 = 0
            r1.z = r4     // Catch: java.lang.Throwable -> Lbf
            monitor-exit(r24)     // Catch: java.lang.Throwable -> Lbf
            com.byd.globalnotification.notification.NotifyMsgBean r0 = r1.x
            b.c.a.f.w r6 = r1.w
            r7 = 63
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            r8 = 58
            r10 = 60
            r12 = 41
            r14 = 0
            if (r7 == 0) goto L64
            long r16 = r2 & r12
            int r7 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r7 == 0) goto L30
            if (r0 == 0) goto L27
            a.k.k r7 = r0.isExpand
            goto L28
        L27:
            r7 = 0
        L28:
            r1.w(r14, r7)
            if (r7 == 0) goto L30
            boolean r7 = r7.f924b
            goto L31
        L30:
            r7 = r14
        L31:
            long r16 = r2 & r8
            int r16 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r16 == 0) goto L4a
            if (r0 == 0) goto L40
            a.k.k r16 = r0.isMultiple()
            r15 = r16
            goto L41
        L40:
            r15 = 0
        L41:
            r14 = 1
            r1.w(r14, r15)
            if (r15 == 0) goto L4a
            boolean r14 = r15.f924b
            goto L4b
        L4a:
            r14 = 0
        L4b:
            long r18 = r2 & r10
            int r15 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r15 == 0) goto L62
            if (r0 == 0) goto L56
            a.k.l<android.widget.RemoteViews> r15 = r0.contentView
            goto L57
        L56:
            r15 = 0
        L57:
            r8 = 2
            r1.w(r8, r15)
            if (r15 == 0) goto L62
            T r8 = r15.f925b
            android.widget.RemoteViews r8 = (android.widget.RemoteViews) r8
            goto L67
        L62:
            r8 = 0
            goto L67
        L64:
            r7 = 0
            r8 = 0
            r14 = 0
        L67:
            r20 = 62
            long r20 = r2 & r20
            int r9 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            r20 = 56
            if (r9 == 0) goto L86
            long r22 = r2 & r20
            int r9 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r9 == 0) goto L80
            if (r6 == 0) goto L80
            b.c.a.h.c.b<com.byd.globalnotification.notification.NotifyMsgBean> r15 = r6.o
            b.c.a.h.c.b<com.byd.globalnotification.notification.NotifyMsgBean> r9 = r6.l
            r16 = r9
            goto L83
        L80:
            r15 = 0
            r16 = 0
        L83:
            r9 = r16
            goto L88
        L86:
            r9 = 0
            r15 = 0
        L88:
            long r10 = r10 & r2
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r10 == 0) goto L92
            android.widget.LinearLayout r10 = r1.u
            a.b.k.l.i.z0(r10, r8, r6, r0)
        L92:
            long r10 = r2 & r12
            int r8 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r8 == 0) goto La1
            android.widget.ImageView r8 = r1.v
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            a.b.k.l.i.s0(r8, r7)
        La1:
            r7 = 58
            long r7 = r7 & r2
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 == 0) goto Lad
            android.widget.ImageView r7 = r1.v
            a.b.k.l.i.B0(r7, r6, r0, r14)
        Lad:
            long r2 = r2 & r20
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto Lbe
            android.widget.ImageView r2 = r1.v
            r3 = 0
            a.b.k.l.i.Y(r2, r15, r3, r0)
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.y
            a.b.k.l.i.Y(r1, r9, r3, r0)
        Lbe:
            return
        Lbf:
            r0 = move-exception
            monitor-exit(r24)     // Catch: java.lang.Throwable -> Lbf
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.e.k.k():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean n() {
        synchronized (this) {
            return this.z != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r(int i, Object obj, int i2) {
        if (i == 0) {
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.z |= 1;
            }
            return true;
        }
        if (i == 1) {
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.z |= 2;
            }
            return true;
        }
        if (i != 2) {
            return false;
        }
        if (i2 != 0) {
            return false;
        }
        synchronized (this) {
            this.z |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean v(int i, Object obj) {
        if (1 == i) {
            this.x = (NotifyMsgBean) obj;
            synchronized (this) {
                this.z |= 8;
            }
            i(1);
            super.u();
            return true;
        }
        if (2 != i) {
            return false;
        }
        this.w = (w) obj;
        synchronized (this) {
            this.z |= 16;
        }
        i(2);
        super.u();
        return true;
    }
}
