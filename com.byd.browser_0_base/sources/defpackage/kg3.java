package defpackage;

import J.N;
import defpackage.s4;
import defpackage.v4;
import java.io.Serializable;
import java.util.HashMap;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class kg3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Serializable g;

    public /* synthetic */ kg3(Object obj, Serializable serializable, int i) {
        this.e = i;
        this.f = obj;
        this.g = serializable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Serializable serializable = this.g;
        Object obj = this.f;
        switch (i) {
            case 0:
                String str = (String) serializable;
                long j = ((TtsPlatformImpl) obj).a;
                if (j != 0) {
                    N.M47GdBO5(j, Integer.parseInt(str));
                }
                break;
            default:
                ((v4.e) ((s4.a) obj)).c((HashMap) serializable);
                break;
        }
    }
}
