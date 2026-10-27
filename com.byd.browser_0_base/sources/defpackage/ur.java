package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ur implements BooleanSupplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ ChromeActivity b;

    public /* synthetic */ ur(ChromeActivity chromeActivity, int i) {
        this.a = i;
        this.b = chromeActivity;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        int i = this.a;
        ChromeActivity chromeActivity = this.b;
        switch (i) {
            case 0:
                return ((ChromeTabbedActivity) chromeActivity).b3();
            case 1:
                return chromeActivity.b3();
            default:
                return ((BaseCustomTabActivity) chromeActivity).b3();
        }
    }
}
