package defpackage;

import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: Violation.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ko3 extends RuntimeException {
    public final Fragment e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ko3(Fragment fragment, String str) {
        super(str);
        i21.e(fragment, "fragment");
        this.e = fragment;
    }
}
