package androidx.fragment.app.strictmode;

import p.v03;
import p.ww0;

/* JADX INFO: loaded from: classes.dex */
public final class FragmentReuseViolation extends Violation {
    private final String previousFragmentId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentReuseViolation(ww0 ww0Var, String str) {
        super(ww0Var, "Attempting to reuse fragment " + ww0Var + " with previous ID " + str);
        v03.j(str, "previousFragmentId");
        this.previousFragmentId = str;
    }
}
