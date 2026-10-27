package b.c.a.e;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import b.c.a.f.w;
import com.byd.globalnotification.notification.NotifyMsgBean;
import com.google.android.material.imageview.ShapeableImageView;

/* JADX INFO: loaded from: classes.dex */
public class n extends l {
    public long H;

    /* JADX WARN: Illegal instructions before constructor call */
    public n(a.k.f fVar, View view) {
        Object[] objArrQ = ViewDataBinding.q(fVar, view, 11, null);
        super(fVar, view, 7, (FrameLayout) objArrQ[10], (LinearLayout) objArrQ[0], (ShapeableImageView) objArrQ[3], (ImageView) objArrQ[7], (ConstraintLayout) objArrQ[2], (ProgressBar) objArrQ[9], (View) objArrQ[1], (TextView) objArrQ[8], (TextView) objArrQ[4], (TextView) objArrQ[6], (TextView) objArrQ[5]);
        this.H = -1L;
        this.u.setTag(null);
        this.v.setTag(null);
        this.w.setTag(null);
        this.x.setTag(null);
        this.y.setTag(null);
        this.z.setTag(null);
        this.A.setTag(null);
        this.B.setTag(null);
        this.C.setTag(null);
        this.D.setTag(null);
        this.E.setTag(null);
        view.setTag(a.k.r.a.dataBinding, this);
        synchronized (this) {
            this.H = 512L;
        }
        u();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011d  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void k() {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.e.n.k():void");
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean n() {
        synchronized (this) {
            return this.H != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 1;
                    break;
                }
                return true;
            case 1:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 2;
                    break;
                }
                return true;
            case 2:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 4;
                    break;
                }
                return true;
            case 3:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 8;
                    break;
                }
                return true;
            case 4:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 16;
                    break;
                }
                return true;
            case 5:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 32;
                    break;
                }
                return true;
            case 6:
                if (i2 != 0) {
                    return false;
                }
                synchronized (this) {
                    this.H |= 64;
                    break;
                }
                return true;
            default:
                return false;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean v(int i, Object obj) {
        if (1 == i) {
            this.G = (NotifyMsgBean) obj;
            synchronized (this) {
                this.H |= 128;
            }
            i(1);
            super.u();
            return true;
        }
        if (2 != i) {
            return false;
        }
        this.F = (w) obj;
        synchronized (this) {
            this.H |= 256;
        }
        i(2);
        super.u();
        return true;
    }
}
