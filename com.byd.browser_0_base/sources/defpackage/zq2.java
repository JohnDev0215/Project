package defpackage;

import defpackage.lw1;
import org.chromium.chrome.browser.base.SplitCompatApplication;
import org.chromium.chrome.browser.metrics.UmaUtils;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zq2 implements nw2 {
    public final /* synthetic */ int e;

    public /* synthetic */ zq2(int i) {
        this.e = i;
    }

    @Override // defpackage.nw2
    public final Object get() {
        boolean z = false;
        switch (this.e) {
            case 0:
                int i = SplitCompatApplication.g;
                return Boolean.valueOf(bq.t.a());
            case 1:
                return null;
            case 2:
                lw1.a aVar = lw1.L;
                return 0;
            case 3:
                if (UmaUtils.c() && !UmaUtils.b()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 4:
                return Float.valueOf(0.0f);
            case 5:
                return (ef1) hj3.a().d.b;
            default:
                return oj2.r();
        }
    }
}
