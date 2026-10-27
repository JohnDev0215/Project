package d4;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ListSpeedBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p3 extends ViewDataBinding {
    public final RecyclerView G;
    public final TextView H;
    protected com.vivid.spotify.nowplaying.k I;

    protected p3(Object obj, View view, int i7, RecyclerView recyclerView, TextView textView) {
        super(obj, view, i7);
        this.G = recyclerView;
        this.H = textView;
    }

    public abstract void d0(com.vivid.spotify.nowplaying.k kVar);
}
