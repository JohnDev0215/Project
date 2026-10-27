package c.a.b.c;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.bydux.api.R;

/* JADX INFO: compiled from: ActivityTestBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    public static final ViewDataBinding.j d0 = null;
    public static final SparseIntArray e0;
    public final LinearLayout f0;
    public long g0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e0 = sparseIntArray;
        sparseIntArray.put(R.id.btn_get, 1);
        sparseIntArray.put(R.id.btn_timer, 2);
        sparseIntArray.put(R.id.btn_mcu, 3);
        sparseIntArray.put(R.id.btn_charge_invalid, 4);
        sparseIntArray.put(R.id.btn_charge_start, 5);
        sparseIntArray.put(R.id.btn_wait, 6);
        sparseIntArray.put(R.id.btn_end, 7);
        sparseIntArray.put(R.id.btn_gun_now, 8);
        sparseIntArray.put(R.id.btn_plan_change, 9);
        sparseIntArray.put(R.id.btn_cloud_1, 10);
        sparseIntArray.put(R.id.btn_cloud_2, 11);
        sparseIntArray.put(R.id.btn_cloud_3, 12);
        sparseIntArray.put(R.id.btn_cloud_4, 13);
        sparseIntArray.put(R.id.btn_cloud_5, 14);
        sparseIntArray.put(R.id.btn_cloud_6, 15);
        sparseIntArray.put(R.id.btn_cloud_7, 16);
        sparseIntArray.put(R.id.btn_time_zone, 17);
        sparseIntArray.put(R.id.btn_clear, 18);
        sparseIntArray.put(R.id.btn_ele_price, 19);
        sparseIntArray.put(R.id.btn_usecar_time, 20);
        sparseIntArray.put(R.id.btn_usecar_on, 21);
        sparseIntArray.put(R.id.btn_usecar_off, 22);
        sparseIntArray.put(R.id.btn_ele_price2, 23);
        sparseIntArray.put(R.id.btn_dm_charge_set, 24);
        sparseIntArray.put(R.id.btn_dm_charge_setfull, 25);
        sparseIntArray.put(R.id.btn_dm_charge_close, 26);
        sparseIntArray.put(R.id.btn_dm_charge_now, 27);
        sparseIntArray.put(R.id.tv_power_msg, 28);
        sparseIntArray.put(R.id.tv_msg, 29);
        sparseIntArray.put(R.id.iv_msg, 30);
    }

    public p(b.j.e eVar, View view) {
        this(eVar, view, ViewDataBinding.u(eVar, view, 31, d0, e0));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void m() {
        synchronized (this) {
            this.g0 = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r() {
        synchronized (this) {
            return this.g0 != 0;
        }
    }

    public void z() {
        synchronized (this) {
            this.g0 = 1L;
        }
        x();
    }

    public p(b.j.e eVar, View view, Object[] objArr) {
        super(eVar, view, 0, (Button) objArr[4], (Button) objArr[5], (Button) objArr[18], (Button) objArr[10], (Button) objArr[11], (Button) objArr[12], (Button) objArr[13], (Button) objArr[14], (Button) objArr[15], (Button) objArr[16], (Button) objArr[26], (Button) objArr[27], (Button) objArr[24], (Button) objArr[25], (Button) objArr[19], (Button) objArr[23], (Button) objArr[7], (Button) objArr[1], (Button) objArr[8], (Button) objArr[3], (Button) objArr[9], (Button) objArr[17], (Button) objArr[2], (Button) objArr[22], (Button) objArr[21], (Button) objArr[20], (Button) objArr[6], (ImageView) objArr[30], (TextView) objArr[29], (TextView) objArr[28]);
        this.g0 = -1L;
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.f0 = linearLayout;
        linearLayout.setTag(null);
        y(view);
        z();
    }
}
