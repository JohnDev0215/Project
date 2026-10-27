package defpackage;

import J.N;
import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bn0 implements BooleanSupplier {
    public final /* synthetic */ int a;

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        switch (this.a) {
            case 0:
                int i = FirstRunActivity.x0;
                return !SharedPreferencesManager.getInstance().readBoolean("skip_welcome_page", false);
            case 1:
                boolean z = zk0.b;
                return N.MpwNAobK();
            default:
                return N.M$3vpOHw();
        }
    }
}
