package c.b.a.a.x;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: PickerFragment.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l<S> extends Fragment {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet<k<S>> f4141b = new LinkedHashSet<>();

    public boolean b(k<S> kVar) {
        return this.f4141b.add(kVar);
    }

    public void c() {
        this.f4141b.clear();
    }
}
