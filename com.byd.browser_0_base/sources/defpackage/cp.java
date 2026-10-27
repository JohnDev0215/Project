package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.firstrun.FirstRunActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cp implements BooleanSupplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ cp(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        Object obj;
        int i = this.a;
        Object obj2 = this.b;
        switch (i) {
            case 0:
                obj = (ChromeActivity) obj2;
                break;
            case 1:
                obj = (BaseCustomTabActivity) obj2;
                break;
            case 2:
                return ((FirstRunActivity) obj2).p0.getBoolean("ShowSearchEnginePage");
            default:
                return ((bz0) obj2).b();
        }
        obj.getClass();
        return true;
    }
}
