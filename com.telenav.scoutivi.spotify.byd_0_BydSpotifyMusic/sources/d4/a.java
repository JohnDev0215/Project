package d4;

import android.view.LayoutInflater;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: ActivityMainBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewDataBinding {
    public final a1 G;
    protected MainUIViewModel H;

    protected a(Object obj, View view, int i7, a1 a1Var) {
        super(obj, view, i7);
        this.G = a1Var;
    }

    public static a d0(LayoutInflater layoutInflater) {
        return e0(layoutInflater, androidx.databinding.g.h());
    }

    @Deprecated
    public static a e0(LayoutInflater layoutInflater, Object obj) {
        return (a) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.activity_main, null, false, obj);
    }

    public abstract void f0(MainUIViewModel mainUIViewModel);
}
