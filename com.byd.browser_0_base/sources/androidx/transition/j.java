package androidx.transition;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: Visibility.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends g {
    public final /* synthetic */ ViewGroup e;
    public final /* synthetic */ View f;
    public final /* synthetic */ View g;
    public final /* synthetic */ Visibility h;

    public j(Visibility visibility, ViewGroup viewGroup, View view, View view2) {
        this.h = visibility;
        this.e = viewGroup;
        this.f = view;
        this.g = view2;
    }

    @Override // androidx.transition.g, androidx.transition.Transition.d
    public final void a() {
        this.e.getOverlay().remove(this.f);
    }

    @Override // androidx.transition.Transition.d
    public final void d(Transition transition) {
        this.g.setTag(R$id.save_overlay_view, null);
        this.e.getOverlay().remove(this.f);
        transition.C(this);
    }

    @Override // androidx.transition.g, androidx.transition.Transition.d
    public final void e() {
        View view = this.f;
        if (view.getParent() == null) {
            this.e.getOverlay().add(view);
        } else {
            this.h.h();
        }
    }
}
