package c.a.b.c;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.byd.widget.BydSwitch;
import com.bydux.api.R;

/* JADX INFO: compiled from: ActivitySetinfoBydBindingPortImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends l {
    public static final ViewDataBinding.j H = null;
    public static final SparseIntArray I;
    public final RelativeLayout J;
    public long K;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        I = sparseIntArray;
        sparseIntArray.put(R.id.tv_back, 1);
        sparseIntArray.put(R.id.ll_tips, 2);
        sparseIntArray.put(R.id.view_acc_off, 3);
        sparseIntArray.put(R.id.switch_acc_off, 4);
        sparseIntArray.put(R.id.switch_charge_gun, 5);
        sparseIntArray.put(R.id.rl_statement, 6);
        sparseIntArray.put(R.id.tv_statement, 7);
        sparseIntArray.put(R.id.tv_version, 8);
    }

    public n(b.j.e eVar, View view) {
        this(eVar, view, ViewDataBinding.u(eVar, view, 9, H, I));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void m() {
        synchronized (this) {
            this.K = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r() {
        synchronized (this) {
            return this.K != 0;
        }
    }

    public void z() {
        synchronized (this) {
            this.K = 1L;
        }
        x();
    }

    public n(b.j.e eVar, View view, Object[] objArr) {
        super(eVar, view, 0, (LinearLayout) objArr[2], (RelativeLayout) objArr[6], (BydSwitch) objArr[4], (BydSwitch) objArr[5], (TextView) objArr[1], (TextView) objArr[7], (TextView) objArr[8], (LinearLayout) objArr[3]);
        this.K = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.J = relativeLayout;
        relativeLayout.setTag(null);
        y(view);
        z();
    }
}
