package c.a.b.c;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.byd.widget.BydSwitch;
import com.byd.widget.ChargeAnimView;
import com.bydux.api.R;

/* JADX INFO: compiled from: ViewPlanUsecarHevBydBindingPortImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class l0 extends j0 {
    public static final ViewDataBinding.j N = null;
    public static final SparseIntArray O;
    public final RelativeLayout P;
    public long Q;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        O = sparseIntArray;
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
        sparseIntArray.put(R.id.charge_anim_view, 11);
        sparseIntArray.put(R.id.ic_chargegun_highlight, 12);
        sparseIntArray.put(R.id.tv_charge_status, 13);
        sparseIntArray.put(R.id.tv_remain_time, 14);
    }

    public l0(b.j.e eVar, View view) {
        this(eVar, view, ViewDataBinding.u(eVar, view, 15, N, O));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void m() {
        synchronized (this) {
            this.Q = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r() {
        synchronized (this) {
            return this.Q != 0;
        }
    }

    public void z() {
        synchronized (this) {
            this.Q = 1L;
        }
        x();
    }

    public l0(b.j.e eVar, View view, Object[] objArr) {
        super(eVar, view, 0, (ChargeAnimView) objArr[11], (ImageView) objArr[12], (ImageView) objArr[3], (ImageView) objArr[9], (BydSwitch) objArr[1], (TextView) objArr[13], (TextView) objArr[2], (TextView) objArr[10], (TextView) objArr[8], (TextView) objArr[7], (TextView) objArr[14], (TextView) objArr[5], (TextView) objArr[4], (TextView) objArr[6]);
        this.Q = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.P = relativeLayout;
        relativeLayout.setTag(null);
        y(view);
        z();
    }
}
