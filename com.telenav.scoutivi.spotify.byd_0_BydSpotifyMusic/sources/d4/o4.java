package d4;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;

/* JADX INFO: compiled from: NowPlayingBarBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class o4 extends ViewDataBinding {
    public final FrameLayout G;
    public final FrameLayout H;
    public final FrameLayout I;
    public final FrameLayout J;
    public final View K;
    public final TextView L;
    public final TextView M;
    public final ProgressBar N;
    public final LinearLayoutCompat O;
    protected NowPlayingViewModel P;

    protected o4(Object obj, View view, int i7, FrameLayout frameLayout, FrameLayout frameLayout2, FrameLayout frameLayout3, FrameLayout frameLayout4, View view2, TextView textView, TextView textView2, ProgressBar progressBar, LinearLayoutCompat linearLayoutCompat) {
        super(obj, view, i7);
        this.G = frameLayout;
        this.H = frameLayout2;
        this.I = frameLayout3;
        this.J = frameLayout4;
        this.K = view2;
        this.L = textView;
        this.M = textView2;
        this.N = progressBar;
        this.O = linearLayoutCompat;
    }

    public abstract void d0(NowPlayingViewModel nowPlayingViewModel);
}
