package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: kE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1040kE implements InterfaceC1317pE {
    public final /* synthetic */ C1096lE a;

    public C1040kE(C1096lE c1096lE) {
        this.a = c1096lE;
    }

    public void a(C1153mF c1153mF) {
        ThreadUtils.b();
        C1096lE c1096lE = this.a;
        c1096lE.c = true;
        c1096lE.d = c1153mF;
        Iterator it = new ArrayList(this.a.b).iterator();
        while (it.hasNext()) {
            ((C1152mE) it.next()).x(c1153mF);
        }
    }
}
