package a.l.d;

import a.h.g.a;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public final class f implements a.InterfaceC0012a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Fragment f958a;

    public f(Fragment fragment) {
        this.f958a = fragment;
    }

    public void a() {
        if (this.f958a.i() != null) {
            View viewI = this.f958a.i();
            this.f958a.H(null);
            viewI.clearAnimation();
        }
        this.f958a.I(null);
    }
}
