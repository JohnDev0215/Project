package d4;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.nowplaying.NowPlayingViewModel;

/* JADX INFO: compiled from: FragmentNowPlayingBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u1 extends ViewDataBinding {
    public final ConstraintLayout G;
    public final FrameLayout H;
    public final FrameLayout I;
    public final ConstraintLayout J;
    public final ConstraintLayout K;
    public final FrameLayout L;
    public final FrameLayout M;
    public final FrameLayout N;
    public final View O;
    public final TextView P;
    public final ImageView Q;
    public final ImageView R;
    public final ImageView S;
    public final FrameLayout T;
    public final FrameLayout U;
    public final TextView V;
    public final AppCompatSeekBar W;
    public final ProgressBar X;
    public final FrameLayout Y;
    public final RelativeLayout Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final FrameLayout f5789a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    protected NowPlayingViewModel f5790b0;

    protected u1(Object obj, View view, int i7, ConstraintLayout constraintLayout, FrameLayout frameLayout, FrameLayout frameLayout2, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, FrameLayout frameLayout3, FrameLayout frameLayout4, FrameLayout frameLayout5, View view2, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, FrameLayout frameLayout6, FrameLayout frameLayout7, TextView textView2, AppCompatSeekBar appCompatSeekBar, ProgressBar progressBar, FrameLayout frameLayout8, RelativeLayout relativeLayout, FrameLayout frameLayout9) {
        super(obj, view, i7);
        this.G = constraintLayout;
        this.H = frameLayout;
        this.I = frameLayout2;
        this.J = constraintLayout2;
        this.K = constraintLayout3;
        this.L = frameLayout3;
        this.M = frameLayout4;
        this.N = frameLayout5;
        this.O = view2;
        this.P = textView;
        this.Q = imageView;
        this.R = imageView2;
        this.S = imageView3;
        this.T = frameLayout6;
        this.U = frameLayout7;
        this.V = textView2;
        this.W = appCompatSeekBar;
        this.X = progressBar;
        this.Y = frameLayout8;
        this.Z = relativeLayout;
        this.f5789a0 = frameLayout9;
    }

    public abstract void d0(NowPlayingViewModel nowPlayingViewModel);
}
