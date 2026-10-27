package b.x;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class u extends z implements w {
    public u(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    public static u g(ViewGroup viewGroup) {
        return (u) z.e(viewGroup);
    }

    @Override // b.x.w
    public void c(View view) {
        this.f3329a.b(view);
    }

    @Override // b.x.w
    public void d(View view) {
        this.f3329a.g(view);
    }
}
