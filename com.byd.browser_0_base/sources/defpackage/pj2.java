package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: SettingsUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pj2 implements ViewTreeObserver.OnScrollChangedListener {
    public final /* synthetic */ View a;
    public final /* synthetic */ View b;

    public pj2(ViewGroup viewGroup, View view) {
        this.a = viewGroup;
        this.b = view;
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public final void onScrollChanged() {
        boolean zCanScrollVertically = this.a.canScrollVertically(-1);
        View view = this.b;
        if (zCanScrollVertically) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }
}
