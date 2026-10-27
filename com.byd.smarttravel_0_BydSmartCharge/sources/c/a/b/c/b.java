package c.a.b.c;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.bydux.api.R;

/* JADX INFO: compiled from: ActivityChargeMainBindingImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {
    public static final ViewDataBinding.j D = null;
    public static final SparseIntArray E;
    public final RelativeLayout F;
    public long G;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        E = sparseIntArray;
        sparseIntArray.put(R.id.tv_back, 1);
        sparseIntArray.put(R.id.tv_setting, 2);
        sparseIntArray.put(R.id.fr_content, 3);
        sparseIntArray.put(R.id.tv_mode, 4);
    }

    public b(b.j.e eVar, View view) {
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

    public b(b.j.e eVar, View view, Object[] objArr) {
        super(eVar, view, 0, (FrameLayout) objArr[3], (TextView) objArr[1], (TextView) objArr[4], (TextView) objArr[2]);
        this.G = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) objArr[0];
        this.F = relativeLayout;
        relativeLayout.setTag(null);
        y(view);
        z();
    }
}
