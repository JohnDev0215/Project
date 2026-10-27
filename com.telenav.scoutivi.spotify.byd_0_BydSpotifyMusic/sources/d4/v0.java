package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerLayout;
import com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView;
import com.vivid.spotify.details.DetailViewModel;

/* JADX INFO: compiled from: FragmentDetailsListBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class v0 extends ViewDataBinding {
    public final LinearLayout G;
    public final LinearLayout H;
    public final ViewDataBinding I;
    public final FrameLayout J;
    public final View K;
    public final ImageView L;
    public final ShimmerRecyclerView M;
    public final ImageView N;
    public final ImageView O;
    public final MotionLayout P;
    public final View Q;
    public final ShimmerLayout R;
    public final View S;
    public final TabLayout T;
    public final View U;
    public final LinearLayout V;
    public final ImageView W;
    public final ConstraintLayout X;
    public final TextView Y;
    public final TextView Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final ViewPager2 f5797a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    protected DetailViewModel f5798b0;

    protected v0(Object obj, View view, int i7, LinearLayout linearLayout, LinearLayout linearLayout2, ViewDataBinding viewDataBinding, FrameLayout frameLayout, View view2, ImageView imageView, ShimmerRecyclerView shimmerRecyclerView, ImageView imageView2, ImageView imageView3, MotionLayout motionLayout, View view3, ShimmerLayout shimmerLayout, View view4, TabLayout tabLayout, View view5, LinearLayout linearLayout3, ImageView imageView4, ConstraintLayout constraintLayout, TextView textView, TextView textView2, ViewPager2 viewPager2) {
        super(obj, view, i7);
        this.G = linearLayout;
        this.H = linearLayout2;
        this.I = viewDataBinding;
        this.J = frameLayout;
        this.K = view2;
        this.L = imageView;
        this.M = shimmerRecyclerView;
        this.N = imageView2;
        this.O = imageView3;
        this.P = motionLayout;
        this.Q = view3;
        this.R = shimmerLayout;
        this.S = view4;
        this.T = tabLayout;
        this.U = view5;
        this.V = linearLayout3;
        this.W = imageView4;
        this.X = constraintLayout;
        this.Y = textView;
        this.Z = textView2;
        this.f5797a0 = viewPager2;
    }

    public static v0 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static v0 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (v0) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_details_list, viewGroup, z6, obj);
    }

    public abstract void f0(DetailViewModel detailViewModel);
}
