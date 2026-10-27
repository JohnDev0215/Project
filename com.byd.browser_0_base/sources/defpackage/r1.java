package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ax0;
import defpackage.s1;
import defpackage.t41;
import defpackage.u1;
import defpackage.v1;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ui.android.webid.AccountSelectionBridge;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.android.webid.data.ClientIdMetadata;
import org.chromium.chrome.browser.ui.android.webid.data.IdentityProviderMetadata;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AccountSelectionMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r1 {
    public boolean a;
    public boolean b;
    public final m1 c;
    public final PropertyModel d;
    public final ya1 e;
    public final ax0 f;
    public final int g;
    public final ji h;
    public final l1 i;
    public v1.a k;
    public String l;
    public String m;
    public String n;
    public IdentityProviderMetadata o;
    public Bitmap p;
    public ClientIdMetadata q;
    public List<Account> r;
    public Account s;
    public long t;
    public final a u = new a();
    public final b j = new b();

    /* JADX INFO: compiled from: AccountSelectionMediator.java */
    public class a implements t41.b {
        public a() {
        }

        @Override // t41.b
        public final void a(boolean z) {
            if (z) {
                r1.this.b(3);
            }
        }
    }

    /* JADX INFO: compiled from: AccountSelectionMediator.java */
    public class b extends bh0 {
        public b() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void c(int i) {
            r1 r1Var = r1.this;
            r1Var.h.e(r1Var.j);
            if (r1Var.b) {
                return;
            }
            r1Var.b(i == 1 ? 2 : 0);
        }
    }

    public r1(m1 m1Var, PropertyModel propertyModel, ya1 ya1Var, ji jiVar, l1 l1Var, ax0 ax0Var, int i) {
        this.c = m1Var;
        this.d = propertyModel;
        this.e = ya1Var;
        this.f = ax0Var;
        this.g = i;
        this.h = jiVar;
        this.i = l1Var;
    }

    public final void a(Account account) {
        if (this.b) {
            return;
        }
        Account account2 = this.s;
        this.s = account;
        if (account2 == null && !account.f) {
            c(this.l, this.m, this.n, this.r, this.o, this.q, false, w1.a);
            return;
        }
        GURL gurl = this.o.d;
        long j = ((AccountSelectionBridge) this.c).a;
        if (j != 0) {
            N.MrAQTS0E(j, gurl, new String[]{account.a, account.b, account.c, account.d}, account.e, account.f);
        }
        if (this.k == v1.a.SIGN_IN) {
            this.k = v1.a.VERIFY;
            f(Arrays.asList(account), false, w1.c);
        }
        d();
    }

    public final void b(int i) {
        this.b = true;
        t41.f.h(this.u);
        this.h.c(this.i, true);
        d();
        long j = ((AccountSelectionBridge) this.c).a;
        if (j != 0) {
            N.MTfNRxYD(j, i);
        }
    }

    public final void c(String str, String str2, String str3, List list, IdentityProviderMetadata identityProviderMetadata, ClientIdMetadata clientIdMetadata, boolean z, PropertyModel.r rVar) {
        this.l = str;
        this.m = str2;
        this.n = str3;
        this.r = list;
        this.o = identityProviderMetadata;
        this.q = clientIdMetadata;
        Account account = this.s;
        if (account != null) {
            list = Arrays.asList(account);
        }
        this.k = z ? v1.a.VERIFY_AUTO_REAUTHN : v1.a.SIGN_IN;
        f(list, this.s == null, rVar);
        d();
    }

    public final void d() {
        q1 q1Var = (this.b || this.s == null || this.r.size() == 1) ? null : new q1(this, 0);
        l1 l1Var = this.i;
        l1Var.g = q1Var;
        l1Var.h.r(Boolean.valueOf(q1Var != null));
    }

    public final void e() {
        v1.a aVar = this.k;
        String str = this.l;
        String str2 = this.m;
        String str3 = this.n;
        q1 q1Var = new q1(this, 1);
        PropertyModel.b bVar = new PropertyModel.b(v1.g);
        bVar.e(v1.e, this.p);
        bVar.e(v1.a, q1Var);
        bVar.e(v1.b, str3);
        bVar.e(v1.c, str);
        bVar.e(v1.d, str2);
        bVar.e(v1.f, aVar);
        PropertyModel propertyModelA = bVar.a();
        this.d.p(w1.c, propertyModelA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [T, org.chromium.chrome.browser.ui.android.webid.data.Account] */
    /* JADX WARN: Type inference failed for: r1v16, types: [T, org.chromium.chrome.browser.ui.android.webid.data.Account] */
    /* JADX WARN: Type inference failed for: r4v13, types: [T, org.chromium.chrome.browser.ui.android.webid.data.IdentityProviderMetadata] */
    /* JADX WARN: Type inference failed for: r4v15, types: [T, p1] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, u1$a] */
    public final void f(List list, boolean z, PropertyModel.r rVar) {
        View viewFindViewById;
        boolean z2;
        boolean z3;
        PropertyModel propertyModelP;
        PropertyModel propertyModelP2;
        Account account;
        ya1 ya1Var = this.e;
        ya1Var.clear();
        Iterator it = list.iterator();
        while (true) {
            viewFindViewById = null;
            if (!it.hasNext()) {
                break;
            }
            ?? r1 = (Account) it.next();
            HashMap mapE = PropertyModel.e(s1.d);
            PropertyModel.l<Account> lVar = s1.b;
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = r1;
            mapE.put(lVar, gVar);
            PropertyModel.l<Callback<Account>> lVar2 = s1.c;
            T x10Var = z ? new x10(13, this) : 0;
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = x10Var;
            PropertyModel propertyModelP3 = C0175b.p(mapE, lVar2, gVar2, mapE);
            ya1Var.t(new xa1(1, propertyModelP3));
            Account account2 = (Account) propertyModelP3.j(lVar);
            String str = account2.c;
            String strJ = account2.e.j();
            boolean zIsEmpty = strJ.isEmpty();
            int i2 = this.g;
            if (zIsEmpty) {
                propertyModelP3.p(s1.a, new s1.a(i2, null, str));
            } else {
                this.f.d(ax0.a.a(i2, i2, strJ, "WebIDAccountSelection"), new mc((Object) this, 5, str, (Object) propertyModelP3));
            }
        }
        e();
        v1.a aVar = this.k;
        if (aVar != v1.a.SIGN_IN || (account = this.s) == null) {
            z2 = false;
            z3 = false;
        } else {
            z2 = !account.f;
            z3 = true;
        }
        if (aVar == v1.a.VERIFY_AUTO_REAUTHN) {
            a(this.s);
        }
        PropertyModel.r<PropertyModel> rVar2 = w1.a;
        if (z3) {
            ?? r12 = this.s;
            ?? r4 = this.o;
            HashMap mapE2 = PropertyModel.e(t1.d);
            PropertyModel.l<IdentityProviderMetadata> lVar3 = t1.b;
            int i3 = zj.a;
            PropertyModel.g gVar3 = new PropertyModel.g();
            gVar3.a = r4;
            mapE2.put(lVar3, gVar3);
            PropertyModel.l<Account> lVar4 = t1.a;
            PropertyModel.g gVar4 = new PropertyModel.g();
            gVar4.a = r12;
            mapE2.put(lVar4, gVar4);
            PropertyModel.l<Callback<Account>> lVar5 = t1.c;
            ?? p1Var = new p1(this, 1);
            PropertyModel.g gVar5 = new PropertyModel.g();
            gVar5.a = p1Var;
            propertyModelP = C0175b.p(mapE2, lVar5, gVar5, mapE2);
        } else {
            propertyModelP = null;
        }
        PropertyModel propertyModel = this.d;
        propertyModel.p(rVar2, propertyModelP);
        PropertyModel.r<PropertyModel> rVar3 = w1.b;
        if (z2) {
            String str2 = this.n;
            ClientIdMetadata clientIdMetadata = this.q;
            ?? aVar2 = new u1.a();
            aVar2.a = str2;
            aVar2.b = clientIdMetadata.a;
            aVar2.c = clientIdMetadata.b;
            aVar2.d = new o90(12);
            aVar2.e = new qe3(20);
            HashMap mapE3 = PropertyModel.e(u1.b);
            PropertyModel.l<u1.a> lVar6 = u1.a;
            int i4 = zj.a;
            PropertyModel.g gVar6 = new PropertyModel.g();
            gVar6.a = aVar2;
            propertyModelP2 = C0175b.p(mapE3, lVar6, gVar6, mapE3);
        } else {
            propertyModelP2 = null;
        }
        propertyModel.p(rVar3, propertyModelP2);
        l1 l1Var = this.i;
        View view = l1Var.e;
        if (view != null) {
            View viewFindViewById2 = view.findViewById(R$id.sheet_item_list_container);
            RecyclerView recyclerView = (RecyclerView) viewFindViewById2.findViewById(R$id.sheet_item_list);
            float fK = recyclerView.getAdapter().k();
            if (fK > 2.5f) {
                recyclerView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                viewFindViewById2.getLayoutParams().height = Math.round((recyclerView.getMeasuredHeight() / fK) * 2.5f);
            } else {
                viewFindViewById2.getLayoutParams().height = -2;
            }
        }
        ji jiVar = this.h;
        if (!jiVar.d(l1Var, true)) {
            b(0);
        } else if (!this.a) {
            this.a = true;
            jiVar.g(this.j);
            t41.f.a(this.u);
        }
        View view2 = l1Var.e;
        if (view2 == null) {
            return;
        }
        if (rVar == w1.c) {
            viewFindViewById = view2.findViewById(R$id.header_title);
        } else if (rVar == rVar2) {
            viewFindViewById = view2.findViewById(R$id.account_selection_continue_btn);
        }
        if (viewFindViewById != null) {
            viewFindViewById.requestFocus();
            viewFindViewById.sendAccessibilityEvent(8);
        }
    }
}
