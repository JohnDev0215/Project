package defpackage;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: FragmentTagUsageViolation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class up0 extends ko3 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public up0(Fragment fragment, ViewGroup viewGroup, int i) {
        super(fragment, "Attempting to use <fragment> tag to add fragment " + fragment + " to container " + viewGroup);
        if (i != 1) {
            return;
        }
        i21.e(viewGroup, "container");
        super(fragment, "Attempting to add fragment " + fragment + " to container " + viewGroup + " which is not a FragmentContainerView");
    }
}
