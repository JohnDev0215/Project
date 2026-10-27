package b.m;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
class u extends z implements w {
    u(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    static u g(ViewGroup viewGroup) {
        return (u) z.e(viewGroup);
    }

    @Override // b.m.w
    public void c(View view) {
        this.f2783a.b(view);
    }

    @Override // b.m.w
    public void d(View view) {
        this.f2783a.g(view);
    }
}
