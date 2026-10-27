package k1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class t extends y implements v {
    public t(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    public static t g(ViewGroup viewGroup) {
        return (t) y.e(viewGroup);
    }

    @Override // k1.v
    public void c(View view) {
        this.f5836a.b(view);
    }

    @Override // k1.v
    public void d(View view) {
        this.f5836a.g(view);
    }
}
