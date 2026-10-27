package b.c.a.e;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import b.c.a.f.w;
import com.byd.globalnotification.R;
import com.byd.globalnotification.notification.NotifyMsgBean;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes.dex */
public class h extends g {
    public static final SparseIntArray G;
    public long F;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        G = sparseIntArray;
        sparseIntArray.put(R.id.divider, 9);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public h(a.k.f fVar, View view) {
        Object[] objArrQ = ViewDataBinding.q(fVar, view, 10, G);
        super(fVar, view, 4, (FrameLayout) objArrQ[8], (LinearLayout) objArrQ[7], (View) objArrQ[9], (LinearLayout) objArrQ[0], (ShapeableImageView) objArrQ[3], (ImageView) objArrQ[6], (ConstraintLayout) objArrQ[2], (View) objArrQ[1], (TextView) objArrQ[4], (TextView) objArrQ[5]);
        this.F = -1L;
        this.u.setTag(null);
        this.v.setTag(null);
        this.w.setTag(null);
        this.x.setTag(null);
        this.y.setTag(null);
        this.z.setTag(null);
        this.A.setTag(null);
        this.B.setTag(null);
        this.C.setTag(null);
        view.setTag(a.k.r.a.dataBinding, this);
        synchronized (this) {
            this.F = 64L;
        }
        u();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008b  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k() {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.e.h.k():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean n() {
        synchronized (this) {
            return this.F != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r(int i, Object obj, int i2) {
        if (i == 0) {
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.F |= 1;
            }
            return true;
        }
        if (i == 1) {
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.F |= 2;
            }
            return true;
        }
        if (i == 2) {
            if (i2 != 0) {
                return false;
            }
            synchronized (this) {
                this.F |= 4;
            }
            return true;
        }
        if (i != 3) {
            return false;
        }
        if (i2 != 0) {
            return false;
        }
        synchronized (this) {
            this.F |= 8;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean v(int i, Object obj) {
        if (1 == i) {
            this.E = (NotifyMsgBean) obj;
            synchronized (this) {
                this.F |= 16;
            }
            i(1);
            super.u();
            return true;
        }
        if (2 != i) {
            return false;
        }
        this.D = (w) obj;
        synchronized (this) {
            this.F |= 32;
        }
        i(2);
        super.u();
        return true;
    }
}
