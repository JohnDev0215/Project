package defpackage;

import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: FragmentReuseViolation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class op0 extends ko3 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public op0(Fragment fragment, String str) {
        super(fragment, "Attempting to reuse fragment " + fragment + " with previous ID " + str);
        i21.e(fragment, "fragment");
        i21.e(str, "previousFragmentId");
    }
}
