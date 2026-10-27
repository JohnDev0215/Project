package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: We, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0399We extends BaseGmsClient implements InterfaceC1882zc, InterfaceC1830yf {
    public final Set A;
    public final Account B;

    /* JADX WARN: Illegal instructions before constructor call */
    public AbstractC0399We(Context context, Looper looper, int i, C0381Ve c0381Ve, InterfaceC0397Wc interfaceC0397Wc, InterfaceC0947id interfaceC0947id) {
        synchronized (AbstractC0435Ye.a) {
            if (AbstractC0435Ye.b == null) {
                AbstractC0435Ye.b = new C0023Bf(context.getApplicationContext());
            }
        }
        AbstractC0435Ye abstractC0435Ye = AbstractC0435Ye.b;
        C1223nc c1223nc = C1223nc.c;
        Cif.f(interfaceC0397Wc);
        Cif.f(interfaceC0947id);
        super(context, looper, abstractC0435Ye, c1223nc, i, new C1775xf(interfaceC0397Wc), new C1720wf(interfaceC0947id), c0381Ve.f);
        this.B = c0381Ve.a;
        Set set = c0381Ve.c;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (!set.contains((Scope) it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.A = set;
    }
}
