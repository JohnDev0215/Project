package defpackage;

import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: SetTargetFragmentUsageViolation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class hj2 extends r63 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public hj2(Fragment fragment, Fragment fragment2, int i) {
        super(fragment, "Attempting to set target fragment " + fragment2 + " with request code " + i + " for fragment " + fragment);
        i21.e(fragment, "fragment");
        i21.e(fragment2, "targetFragment");
    }
}
