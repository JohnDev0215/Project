package b.c.a.e;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.byd.globalnotification.R;

/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    public static final SparseIntArray w;
    public final ConstraintLayout u;
    public long v;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        w = sparseIntArray;
        sparseIntArray.put(R.id.title_bar_back, 1);
        w.put(R.id.tv_title_bar_back, 2);
        w.put(R.id.title_bar_title, 3);
        w.put(R.id.ll_notification_mgr, 4);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public p(a.k.f fVar, View view) {
        Object[] objArrQ = ViewDataBinding.q(fVar, view, 5, w);
        super(fVar, view, 0, (LinearLayout) objArrQ[4], (ImageView) objArrQ[1], (TextView) objArrQ[3], (TextView) objArrQ[2]);
        this.v = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArrQ[0];
        this.u = constraintLayout;
        constraintLayout.setTag(null);
        view.setTag(a.k.r.a.dataBinding, this);
        synchronized (this) {
            this.v = 2L;
        }
        u();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void k() {
        synchronized (this) {
            this.v = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean n() {
        synchronized (this) {
            return this.v != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean v(int i, Object obj) {
        if (2 != i) {
            return false;
        }
        return true;
    }
}
