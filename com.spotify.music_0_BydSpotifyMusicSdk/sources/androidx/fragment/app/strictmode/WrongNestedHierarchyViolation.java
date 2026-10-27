package androidx.fragment.app.strictmode;

import p.u4;
import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public final class WrongNestedHierarchyViolation extends Violation {
    private final int containerId;
    private final ww0 expectedParentFragment;

    public WrongNestedHierarchyViolation(ww0 ww0Var, ww0 ww0Var2, int i) {
        StringBuilder sb = new StringBuilder("Attempting to nest fragment ");
        sb.append(ww0Var);
        sb.append(" within the view of parent fragment ");
        sb.append(ww0Var2);
        sb.append(" via container with ID ");
        super(ww0Var, u4.l(sb, i, " without using parent's childFragmentManager"));
        this.expectedParentFragment = ww0Var2;
        this.containerId = i;
    }
}
