package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.vivid.labels.LabelsView;
import com.vivid.spotify.common.ui.BaseTabLayout;
import com.vivid.spotify.search.SearchViewModel;

/* JADX INFO: compiled from: FragmentSearchBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t2 extends ViewDataBinding {
    public final AppCompatEditText G;
    public final ConstraintLayout H;
    public final TextView I;
    public final TextView J;
    public final TextView K;
    public final LinearLayout L;
    public final ImageView M;
    public final View N;
    public final LabelsView O;
    public final ImageView P;
    public final ConstraintLayout Q;
    public final d3 R;
    public final BaseTabLayout S;
    public final TextView T;
    public final ImageView U;
    public final ViewPager2 V;
    protected SearchViewModel W;

    protected t2(Object obj, View view, int i7, AppCompatEditText appCompatEditText, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, ImageView imageView, View view2, LabelsView labelsView, ImageView imageView2, ConstraintLayout constraintLayout2, d3 d3Var, BaseTabLayout baseTabLayout, TextView textView4, ImageView imageView3, ViewPager2 viewPager2) {
        super(obj, view, i7);
        this.G = appCompatEditText;
        this.H = constraintLayout;
        this.I = textView;
        this.J = textView2;
        this.K = textView3;
        this.L = linearLayout;
        this.M = imageView;
        this.N = view2;
        this.O = labelsView;
        this.P = imageView2;
        this.Q = constraintLayout2;
        this.R = d3Var;
        this.S = baseTabLayout;
        this.T = textView4;
        this.U = imageView3;
        this.V = viewPager2;
    }

    public static t2 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static t2 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (t2) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_search, viewGroup, z6, obj);
    }

    public SearchViewModel d0() {
        return this.W;
    }

    public abstract void g0(SearchViewModel searchViewModel);
}
