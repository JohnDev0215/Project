package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentContainerView;

/* JADX INFO: compiled from: FragmentMainRouteBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class p1 extends ViewDataBinding {
    public final FragmentContainerView G;

    protected p1(Object obj, View view, int i7, FragmentContainerView fragmentContainerView) {
        super(obj, view, i7);
        this.G = fragmentContainerView;
    }

    public static p1 d0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return e0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static p1 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (p1) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.fragment_main_route, viewGroup, z6, obj);
    }
}
