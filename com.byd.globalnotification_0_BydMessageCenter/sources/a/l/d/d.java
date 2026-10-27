package a.l.d;

import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class d extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Fragment f944a;

    public d(Fragment fragment) {
        this.f944a = fragment;
    }

    @Override // a.l.d.k
    public View b(int i) {
        View view = this.f944a.F;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment " + this + " does not have a view");
    }

    @Override // a.l.d.k
    public boolean f() {
        return this.f944a.F != null;
    }
}
