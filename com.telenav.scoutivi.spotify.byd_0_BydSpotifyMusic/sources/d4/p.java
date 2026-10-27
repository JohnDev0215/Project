package d4;

import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;

/* JADX INFO: compiled from: CommonErrorLayoutBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p extends ViewDataBinding {
    public final LinearLayout G;
    protected LiveData<Boolean> H;
    protected View.OnClickListener I;

    protected p(Object obj, View view, int i7, LinearLayout linearLayout) {
        super(obj, view, i7);
        this.G = linearLayout;
    }

    public abstract void d0(LiveData<Boolean> liveData);

    public abstract void e0(View.OnClickListener onClickListener);
}
