package androidx.fragment.app.strictmode;

import android.view.ViewGroup;
import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentTagUsageViolation extends Violation {
    private final ViewGroup parentContainer;

    public FragmentTagUsageViolation(ww0 ww0Var, ViewGroup viewGroup) {
        super(ww0Var, "Attempting to use <fragment> tag to add fragment " + ww0Var + " to container " + viewGroup);
        this.parentContainer = viewGroup;
    }
}
