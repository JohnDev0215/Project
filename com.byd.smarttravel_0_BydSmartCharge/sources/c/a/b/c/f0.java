package c.a.b.c;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.byd.widget.BydSwitch;
import com.bydux.api.R;

/* JADX INFO: compiled from: ViewPlanUsecarBydBindingPortImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 extends d0 {
    public static final ViewDataBinding.j J = null;
    public static final SparseIntArray K;
    public final RelativeLayout L;
    public long M;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        K = sparseIntArray;
        sparseIntArray.put(R.id.sbg_charge, 1);
        sparseIntArray.put(R.id.tv_fun_name, 2);
        sparseIntArray.put(R.id.iv_bg, 3);
        sparseIntArray.put(R.id.tv_usecar, 4);
        sparseIntArray.put(R.id.tv_start_time, 5);
        sparseIntArray.put(R.id.tv_week, 6);
        sparseIntArray.put(R.id.tv_price_title, 7);
        sparseIntArray.put(R.id.tv_price_time, 8);
        sparseIntArray.put(R.id.iv_price_time, 9);
        sparseIntArray.put(R.id.tv_more, 10);
    }

    public f0(b.j.e eVar, View view) {
        this(eVar, view, ViewDataBinding.u(eVar, view, 11, J, K));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void m() {
        synchronized (this) {
            this.M = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r() {
        synchronized (this) {
            return this.M != 0;
        }
    }

    public void z() {
        synchronized (this) {
            this.M = 1L;
        }
        x();
    }

    public f0(b.j.e eVar, View view, Object[] objArr) {
        super(eVar, view, 0, (ImageView) objArr[3], (ImageView) objArr[9], (BydSwitch) objArr[1], (TextView) objArr[2], (TextView) objArr[10], (TextView) objArr[8], (TextView) objArr[7], (TextView) objArr[5], (TextView) objArr[4], (TextView) objArr[6]);
        this.M = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.L = relativeLayout;
        relativeLayout.setTag(null);
        y(view);
        z();
    }
}
