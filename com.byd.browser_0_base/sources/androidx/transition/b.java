package androidx.transition;

import android.view.View;
import defpackage.wn3;

/* JADX INFO: compiled from: Fade.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends g {
    public final /* synthetic */ View e;

    public b(View view) {
        this.e = view;
    }

    @Override // androidx.transition.Transition.d
    public final void d(Transition transition) {
        wn3.a aVar = wn3.a;
        this.e.setTransitionAlpha(1.0f);
        transition.C(this);
    }
}
