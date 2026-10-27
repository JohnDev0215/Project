package defpackage;

import androidx.fragment.app.Fragment;
import com.google.android.material.datepicker.f;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: PickerFragment.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f12<S> extends Fragment {
    public final LinkedHashSet<ks1<S>> Z = new LinkedHashSet<>();

    public boolean H0(f.c cVar) {
        return this.Z.add(cVar);
    }
}
