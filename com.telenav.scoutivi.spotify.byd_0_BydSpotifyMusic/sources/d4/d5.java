package d4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: compiled from: TitleItemBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d5 extends ViewDataBinding {
    protected com.vivid.spotify.queue.j G;

    protected d5(Object obj, View view, int i7) {
        super(obj, view, i7);
    }

    public static d5 e0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        return f0(layoutInflater, viewGroup, z6, androidx.databinding.g.h());
    }

    @Deprecated
    public static d5 f0(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6, Object obj) {
        return (d5) ViewDataBinding.J(layoutInflater, com.vivid.spotify.v.title_item, viewGroup, z6, obj);
    }

    public com.vivid.spotify.queue.j d0() {
        return this.G;
    }

    public abstract void g0(com.vivid.spotify.queue.j jVar);
}
