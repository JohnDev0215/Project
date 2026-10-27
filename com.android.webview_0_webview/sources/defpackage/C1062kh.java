package defpackage;

import android.database.ContentObserver;
import java.util.Iterator;

/* JADX INFO: renamed from: kh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1062kh extends ContentObserver {
    public final /* synthetic */ C1006jh a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1062kh(C1006jh c1006jh) {
        super(null);
        this.a = c1006jh;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        C1006jh c1006jh = this.a;
        synchronized (c1006jh.d) {
            c1006jh.e = null;
            AbstractC1667vh.i.incrementAndGet();
        }
        synchronized (c1006jh) {
            Iterator it = c1006jh.f.iterator();
            while (it.hasNext()) {
                ((InterfaceC1228nh) it.next()).a();
            }
        }
    }
}
