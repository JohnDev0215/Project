package androidx.fragment.app.strictmode;

import android.view.ViewGroup;
import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public final class WrongFragmentContainerViolation extends Violation {
    private final ViewGroup container;

    public WrongFragmentContainerViolation(ww0 ww0Var, ViewGroup viewGroup) {
        super(ww0Var, "Attempting to add fragment " + ww0Var + " to container " + viewGroup + " which is not a FragmentContainerView");
        this.container = viewGroup;
    }
}
