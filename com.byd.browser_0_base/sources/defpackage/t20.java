package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class t20 implements BooleanSupplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ t20(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return ((CustomTabActivity) obj).isFinishing();
            case 1:
                return ((rq0) obj).g();
            case 2:
                ((lw1) obj).getClass();
                return false;
            default:
                return ((qq0) obj).g();
        }
    }
}
