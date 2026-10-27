package defpackage;

import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: Dd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0057Dd extends AbstractRunnableC0129Hd {
    public final ArrayList t;
    public final /* synthetic */ C1773xd u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0057Dd(C1773xd c1773xd, ArrayList arrayList) {
        super(c1773xd, null);
        this.u = c1773xd;
        this.t = arrayList;
    }

    @Override // defpackage.AbstractRunnableC0129Hd
    public final void a() {
        Set setEmptySet;
        C1773xd c1773xd = this.u;
        C0165Jd c0165Jd = c1773xd.a.m;
        if (c1773xd.r == null) {
            setEmptySet = Collections.emptySet();
        } else {
            HashSet hashSet = new HashSet(c1773xd.r.b);
            Map map = c1773xd.r.d;
            for (C0020Bc c0020Bc : map.keySet()) {
                if (!c1773xd.a.g.containsKey(c0020Bc.a())) {
                    throw null;
                }
            }
            setEmptySet = hashSet;
        }
        c0165Jd.q = setEmptySet;
        ArrayList arrayList = this.t;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            C1773xd c1773xd2 = this.u;
            ((BaseGmsClient) ((InterfaceC1882zc) obj)).h(c1773xd2.o, c1773xd2.a.m.q);
        }
    }
}
