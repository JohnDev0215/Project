package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: ListSpeedItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u3 extends ViewDataBinding {
    public final ImageView G;
    public final ImageView H;
    protected com.vivid.spotify.nowplaying.j I;

    protected u3(Object obj, View view, int i7, ImageView imageView, ImageView imageView2) {
        super(obj, view, i7);
        this.G = imageView;
        this.H = imageView2;
    }

    public static u3 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static u3 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (u3) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.list_speed_item, viewGroup, z6, obj);
    }

    public com.vivid.spotify.nowplaying.j d0() {
        return this.I;
    }

    public abstract void g0(com.vivid.spotify.nowplaying.j jVar);
}
