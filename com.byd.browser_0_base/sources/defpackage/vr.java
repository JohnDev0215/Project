package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.ChromeTabbedActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class vr implements BooleanSupplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ ChromeTabbedActivity b;

    public /* synthetic */ vr(ChromeTabbedActivity chromeTabbedActivity, int i) {
        this.a = i;
        this.b = chromeTabbedActivity;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        int i = this.a;
        ChromeTabbedActivity chromeTabbedActivity = this.b;
        switch (i) {
            case 0:
                chromeTabbedActivity.getClass();
                return true;
            default:
                return chromeTabbedActivity.T;
        }
    }
}
