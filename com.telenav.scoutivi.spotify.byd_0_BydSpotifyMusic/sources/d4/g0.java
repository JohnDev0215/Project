package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.spotify.common.ui.BaseTabLayout;
import com.vivid.spotify.rootui.category.CategoryViewModel;

/* JADX INFO: compiled from: FragmentCategoryBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g0 extends ViewDataBinding {
    public final ViewPager2 G;
    public final View H;
    public final ImageView I;
    public final ImageView J;
    public final MotionLayout K;
    public final BaseTabLayout L;
    public final ImageView M;
    public final TextView N;
    protected CategoryViewModel O;
    protected FragmentStateAdapter P;

    protected g0(Object obj, View view, int i7, ViewPager2 viewPager2, View view2, ImageView imageView, ImageView imageView2, MotionLayout motionLayout, BaseTabLayout baseTabLayout, ImageView imageView3, TextView textView) {
        super(obj, view, i7);
        this.G = viewPager2;
        this.H = view2;
        this.I = imageView;
        this.J = imageView2;
        this.K = motionLayout;
        this.L = baseTabLayout;
        this.M = imageView3;
        this.N = textView;
    }

    public static g0 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static g0 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (g0) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_category, viewGroup, z6, obj);
    }

    public abstract void f0(FragmentStateAdapter fragmentStateAdapter);

    public abstract void g0(CategoryViewModel categoryViewModel);
}
