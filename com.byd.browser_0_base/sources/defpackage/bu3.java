package defpackage;

import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: WrongNestedHierarchyViolation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class bu3 extends ko3 {
    public bu3(Fragment fragment, Fragment fragment2, int i) {
        super(fragment, "Attempting to nest fragment " + fragment + " within the view of parent fragment " + fragment2 + " via container with ID " + i + " without using parent's childFragmentManager");
    }
}
