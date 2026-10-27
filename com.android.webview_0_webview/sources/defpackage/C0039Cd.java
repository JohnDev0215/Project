package defpackage;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: Cd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0039Cd extends AbstractRunnableC0129Hd {
    public final Map t;
    public final /* synthetic */ C1773xd u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0039Cd(C1773xd c1773xd, Map map) {
        super(c1773xd, null);
        this.u = c1773xd;
        this.t = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractRunnableC0129Hd
    public final void a() {
        InterfaceC1395qj interfaceC1395qj;
        C0059Df c0059Df = new C0059Df(this.u.d);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : this.t.keySet()) {
            Objects.requireNonNull((BaseGmsClient) obj);
            if (((C1883zd) this.t.get(obj)).c) {
                arrayList2.add(obj);
            } else {
                arrayList.add(obj);
            }
        }
        int iA = -1;
        int i = 0;
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            while (i < size) {
                Object obj2 = arrayList.get(i);
                i++;
                iA = c0059Df.a(this.u.c, (InterfaceC1882zc) obj2);
                if (iA != 0) {
                    break;
                }
            }
        } else {
            int size2 = arrayList2.size();
            while (i < size2) {
                Object obj3 = arrayList2.get(i);
                i++;
                iA = c0059Df.a(this.u.c, (InterfaceC1882zc) obj3);
                if (iA == 0) {
                    break;
                }
            }
        }
        if (iA != 0) {
            ConnectionResult connectionResult = new ConnectionResult(iA, null);
            C1773xd c1773xd = this.u;
            C0272Pd c0272Pd = c1773xd.a;
            c0272Pd.e.sendMessage(c0272Pd.e.obtainMessage(1, new C0021Bd(this, c1773xd, connectionResult)));
            return;
        }
        C1773xd c1773xd2 = this.u;
        if (c1773xd2.m && (interfaceC1395qj = c1773xd2.k) != null) {
            C0674dj c0674dj = (C0674dj) interfaceC1395qj;
            c0674dj.c(new C0202Le(c0674dj));
        }
        for (InterfaceC1882zc interfaceC1882zc : this.t.keySet()) {
            InterfaceC0184Ke interfaceC0184Ke = (InterfaceC0184Ke) this.t.get(interfaceC1882zc);
            Objects.requireNonNull((BaseGmsClient) interfaceC1882zc);
            if (c0059Df.a(this.u.c, interfaceC1882zc) != 0) {
                C1773xd c1773xd3 = this.u;
                C0272Pd c0272Pd2 = c1773xd3.a;
                c0272Pd2.e.sendMessage(c0272Pd2.e.obtainMessage(1, new C0075Ed(c1773xd3, interfaceC0184Ke)));
            } else {
                ((BaseGmsClient) interfaceC1882zc).c(interfaceC0184Ke);
            }
        }
    }
}
