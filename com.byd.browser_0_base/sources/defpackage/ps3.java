package defpackage;

import android.util.Pair;
import defpackage.fc;
import java.util.HashMap;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ps3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ ps3(Object obj, int i, Object obj2, boolean z) {
        this.e = i;
        this.g = obj;
        this.h = obj2;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        boolean z = this.f;
        Object obj = this.h;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                WebappRegistry webappRegistry = (WebappRegistry) obj2;
                webappRegistry.getClass();
                Object obj3 = ThreadUtils.a;
                for (Pair pair : (List) obj) {
                    HashMap map = webappRegistry.b;
                    if (!map.containsKey(pair.first)) {
                        map.put((String) pair.first, (ds3) pair.second);
                    }
                }
                if (z) {
                    oj2.Q(webappRegistry.b().size(), "WebApk.WebappRegistry.NumberOfOrigins");
                }
                break;
            default:
                ((fk1) obj2).g();
                ((fc.a) obj).b(z);
                break;
        }
    }
}
