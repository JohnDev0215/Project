package c.a.b.c;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.bydux.api.R;

/* JADX INFO: compiled from: AnimChargeViewBydBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class t extends s {
    public static final ViewDataBinding.j D = null;
    public static final SparseIntArray E;
    public final FrameLayout F;
    public long G;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        E = sparseIntArray;
        sparseIntArray.put(R.id.iv_car, 1);
        sparseIntArray.put(R.id.iv_battery_progress, 2);
        sparseIntArray.put(R.id.iv_scan, 3);
        sparseIntArray.put(R.id.tv_battery, 4);
    }

    public t(b.j.e eVar, View view) {
        this(eVar, view, ViewDataBinding.u(eVar, view, 5, D, E));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void m() {
        synchronized (this) {
            this.G = 0L;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean r() {
        synchronized (this) {
            return this.G != 0;
        }
    }

    public void z() {
        synchronized (this) {
            this.G = 1L;
        }
        x();
    }

    public t(b.j.e eVar, View view, Object[] objArr) {
        super(eVar, view, 0, (ImageView) objArr[2], (ImageView) objArr[1], (ImageView) objArr[3], (TextView) objArr[4]);
        this.G = -1L;
        FrameLayout frameLayout = (FrameLayout) objArr[0];
        this.F = frameLayout;
        frameLayout.setTag(null);
        y(view);
        z();
    }
}
