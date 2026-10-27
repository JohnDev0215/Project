package defpackage;

import java.util.concurrent.Callable;
import org.chromium.support_lib_boundary.IsomorphicObjectBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class NM implements IsomorphicObjectBoundaryInterface {
    public abstract AbstractC1675vp a();

    @Override // org.chromium.support_lib_boundary.IsomorphicObjectBoundaryInterface
    public Object getOrCreatePeer(Callable callable) {
        AbstractC1675vp abstractC1675vpA = a();
        if (abstractC1675vpA == null) {
            return null;
        }
        Object obj = abstractC1675vpA.a;
        if (obj != null) {
            return obj;
        }
        try {
            Object objCall = callable.call();
            abstractC1675vpA.a = objCall;
            return objCall;
        } catch (Exception e) {
            throw new RuntimeException("Could not create peered object", e);
        }
    }
}
