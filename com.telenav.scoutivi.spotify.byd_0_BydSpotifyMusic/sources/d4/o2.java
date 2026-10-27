package d4;

import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.queue.QueueViewModel;

/* JADX INFO: compiled from: FragmentQueueRhdBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o2 extends ViewDataBinding {
    public final FrameLayout G;
    public final ShimmerRecyclerView H;
    protected QueueViewModel I;

    protected o2(Object obj, View view, int i7, FrameLayout frameLayout, ShimmerRecyclerView shimmerRecyclerView) {
        super(obj, view, i7);
        this.G = frameLayout;
        this.H = shimmerRecyclerView;
    }

    public abstract void d0(QueueViewModel queueViewModel);
}
