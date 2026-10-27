package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class L2 implements V2 {
    public final /* synthetic */ String a;

    public L2(String str) {
        this.a = str;
    }

    @Override // defpackage.V2
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(P2 p2) {
        synchronized (Q2.c) {
            ArrayList arrayList = (ArrayList) Q2.d.get(this.a);
            if (arrayList == null) {
                return;
            }
            Q2.d.remove(this.a);
            for (int i = 0; i < arrayList.size(); i++) {
                ((V2) arrayList.get(i)).a(p2);
            }
        }
    }
}
