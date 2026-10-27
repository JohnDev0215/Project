package defpackage;

import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: GetTargetFragmentUsageViolation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ts0 extends r63 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ts0(Fragment fragment) {
        super(fragment, "Attempting to get target fragment from fragment " + fragment);
        i21.e(fragment, "fragment");
    }
}
