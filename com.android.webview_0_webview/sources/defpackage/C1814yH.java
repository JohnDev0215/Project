package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: renamed from: yH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1814yH {
    public final AH a;
    public final ArrayList b = new ArrayList();

    public C1814yH(AH ah) {
        this.a = ah;
    }

    public void a() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        this.b.clear();
    }
}
