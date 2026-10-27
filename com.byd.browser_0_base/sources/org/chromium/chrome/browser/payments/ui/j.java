package org.chromium.chrome.browser.payments.ui;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;
import defpackage.C0173a;
import defpackage.ao3;
import defpackage.b4;
import defpackage.b80;
import defpackage.c4;
import defpackage.cy;
import defpackage.dh2;
import defpackage.dy;
import defpackage.ey;
import defpackage.g20;
import defpackage.g71;
import defpackage.il;
import defpackage.iy1;
import defpackage.jy1;
import defpackage.jz1;
import defpackage.ky1;
import defpackage.kz1;
import defpackage.l61;
import defpackage.lf0;
import defpackage.ly1;
import defpackage.m91;
import defpackage.nj2;
import defpackage.no0;
import defpackage.nz1;
import defpackage.oy1;
import defpackage.pz1;
import defpackage.qa;
import defpackage.ra;
import defpackage.ry1;
import defpackage.s31;
import defpackage.sy1;
import defpackage.tl2;
import defpackage.ty1;
import defpackage.tz1;
import defpackage.v02;
import defpackage.wz1;
import defpackage.xf0;
import defpackage.y13;
import defpackage.yq;
import defpackage.yy1;
import defpackage.z51;
import defpackage.zx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfileBridge;
import org.chromium.chrome.browser.payments.ui.PaymentRequestSection;
import org.chromium.chrome.browser.payments.ui.e;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.e;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PaymentUiService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements nj2.a, yy1.a, PaymentRequestSection.OptionSection.a, PersonalDataManager.NormalizedAddressRequestDelegate, e.b, l61.a {
    public static final no0 H = new no0(1);
    public dh2 A;
    public zx B;
    public List<PersonalDataManager.AutofillProfile> D;
    public y13 E;
    public TabModel F;
    public z51 G;
    public final ly1 e;
    public final boolean f;
    public ey k;
    public yy1 l;
    public Callback<jz1> m;
    public dh2 n;
    public final c o;
    public final WebContents p;
    public final String q;
    public final String r;
    public final c4 t;
    public final oy1 v;
    public final s31 w;
    public e x;
    public tl2 y;
    public dh2 z;
    public final Handler g = new Handler();
    public final LinkedList h = new LinkedList();
    public boolean C = true;
    public final d u = new d();
    public final HashMap s = new HashMap();
    public final h i = new h(this);
    public final i j = new i(this);

    /* JADX INFO: compiled from: PaymentUiService.java */
    public class a implements Callback<ra> {
        public final /* synthetic */ ra e;

        public a(ra raVar) {
            this.e = raVar;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(ra raVar) {
            pz1 pz1Var;
            ra raVar2 = raVar;
            j jVar = j.this;
            if (jVar.x == null) {
                return;
            }
            if (raVar2 != null) {
                jVar.k.j = null;
                if (!raVar2.a) {
                    jVar.B.c = -1;
                } else if (this.e == null) {
                    zx zxVar = jVar.B;
                    zxVar.a.add(0, raVar2);
                    zxVar.c = 0;
                } else {
                    iy1 iy1Var = new iy1(0);
                    iy1Var.c = raVar2.q;
                    iy1Var.d = raVar2.r;
                    iy1Var.b = raVar2.s;
                    yq yqVar = (yq) jVar.o;
                    org.chromium.components.payments.e eVar = yqVar.a;
                    if (eVar != null && yqVar.g && (pz1Var = eVar.A) != null) {
                        pz1Var.G(iy1Var);
                    }
                }
            }
            jVar.x.n(3, jVar.B);
            LinkedList linkedList = jVar.h;
            if (linkedList.isEmpty()) {
                return;
            }
            jVar.g.post((Runnable) linkedList.remove());
        }
    }

    /* JADX INFO: compiled from: PaymentUiService.java */
    public class b implements Callback<qa> {
        public final /* synthetic */ qa e;

        public b(qa qaVar) {
            this.e = qaVar;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(qa qaVar) {
            qa qaVar2 = qaVar;
            j jVar = j.this;
            if (jVar.x == null) {
                return;
            }
            if (qaVar2 != null) {
                jVar.t.s = null;
                qaVar2.i();
                if (qaVar2.a) {
                    if (this.e == null) {
                        dh2 dh2Var = jVar.A;
                        dh2Var.a.add(0, qaVar2);
                        dh2Var.c = 0;
                    }
                    zx zxVar = jVar.B;
                    if (zxVar != null) {
                        zxVar.e(qaVar2);
                        jVar.x.n(3, jVar.B);
                    }
                    PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                    PersonalDataManager.AutofillProfile autofillProfile = qaVar2.l;
                    personalDataManagerC.getClass();
                    Object obj = ThreadUtils.a;
                    N.M7ysHTTC(personalDataManagerC.a, personalDataManagerC, autofillProfile, 5, jVar);
                } else {
                    jVar.A.c = -1;
                    jVar.u();
                }
            } else {
                jVar.u();
            }
            LinkedList linkedList = jVar.h;
            if (linkedList.isEmpty()) {
                return;
            }
            jVar.g.post((Runnable) linkedList.remove());
        }
    }

    /* JADX INFO: compiled from: PaymentUiService.java */
    public interface c {
    }

    /* JADX INFO: compiled from: PaymentUiService.java */
    public class d {
        public boolean a;
        public boolean b;

        public d() {
        }

        public final void a() {
            j jVar = j.this;
            e eVar = jVar.x;
            if (eVar == null) {
                return;
            }
            boolean z = true;
            boolean z2 = !this.a && this.b;
            org.chromium.chrome.browser.payments.ui.a aVar = eVar.k;
            if (z2) {
                aVar.getClass();
                try {
                    aVar.a.show();
                } catch (WindowManager.BadTokenException unused) {
                    z = false;
                }
            } else {
                aVar.a.hide();
            }
            if (z) {
                return;
            }
            yq yqVar = (yq) jVar.o;
            yqVar.e.a(8);
            yqVar.b("Fails to show payment request UI. Please try again.");
        }
    }

    public j(c cVar, oy1 oy1Var, WebContents webContents, boolean z, s31 s31Var, String str) {
        this.o = cVar;
        this.v = oy1Var;
        this.t = new c4(!z);
        this.w = s31Var;
        this.p = webContents;
        this.q = str;
        this.r = webContents.getTitle();
        this.f = z;
        this.e = new ly1(oy1Var);
    }

    public final void A(PaymentApp paymentApp) {
        if (N.M1X7xdZV("WebPaymentsModifiers")) {
            org.chromium.components.payments.e eVar = (org.chromium.components.payments.e) this.v;
            if (eVar.u) {
                return;
            }
            ty1 ty1VarM = m(paymentApp);
            kz1 kz1Var = ty1VarM == null ? null : ty1VarM.b;
            if (kz1Var == null) {
                kz1Var = eVar.t.c().b;
            }
            g20 g20VarN = n(kz1Var.c);
            this.y.a = new g71(kz1Var.b, N.MnbePYrk(g20VarN.a, g20VarN), g20VarN.b(kz1Var.c.c), false);
            this.y.c = ty1VarM != null ? l(Arrays.asList(ty1VarM.c)) : null;
            e eVar2 = this.x;
            if (eVar2 != null) {
                eVar2.k(this.y);
            }
        }
    }

    @Override // nj2.a
    public final void b(qa qaVar) {
        ArrayList<lf0> arrayList;
        qaVar.i();
        dh2 dh2Var = this.A;
        if (dh2Var != null) {
            int i = 0;
            while (true) {
                arrayList = dh2Var.a;
                if (i >= arrayList.size() || TextUtils.equals(arrayList.get(i).g, qaVar.g)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < arrayList.size()) {
                arrayList.set(i, qaVar);
                if (dh2Var.c == i && !qaVar.a) {
                    dh2Var.c = -1;
                }
            } else {
                arrayList.add(0, qaVar);
                if (qaVar.a) {
                    dh2Var.c = 0;
                } else {
                    dh2Var.c = -1;
                }
            }
            this.x.n(1, this.A);
        }
        zx zxVar = this.B;
        if (zxVar != null) {
            zxVar.e(qaVar);
            this.x.n(3, this.B);
        }
    }

    public final void c(Context context) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < this.D.size(); i2++) {
            PersonalDataManager.AutofillProfile autofillProfile = this.D.get(i2);
            String phoneNumber = autofillProfile.getPhoneNumber();
            c4 c4Var = this.t;
            c4Var.getClass();
            if (!TextUtils.isEmpty(phoneNumber)) {
                c4Var.e.add(phoneNumber.toString());
            }
            if (!TextUtils.isEmpty(autofillProfile.getStreetAddress())) {
                arrayList.add(new qa(context, autofillProfile));
            }
        }
        Collections.sort(arrayList, H);
        List listSubList = arrayList.subList(0, Math.min(arrayList.size(), 4));
        HashSet hashSet = new HashSet();
        for (int i3 = 0; i3 < listSubList.size(); i3++) {
            String strG = qa.g(((qa) listSubList.get(i3)).l);
            if (!hashSet.contains(strG)) {
                hashSet.add(strG);
                PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                personalDataManagerC.getClass();
                Object obj = ThreadUtils.a;
                N.Mj65Bkg_(personalDataManagerC.a, personalDataManagerC, strG);
            }
        }
        boolean z = !listSubList.isEmpty() && ((qa) listSubList.get(0)).a;
        if (this.n.b() == null || !z) {
            i = -1;
        } else {
            qa qaVar = (qa) listSubList.get(0);
            if (qaVar.n == null) {
                PersonalDataManager personalDataManagerC2 = PersonalDataManager.c();
                qaVar.n = N.MGlLlw0K(personalDataManagerC2.a, personalDataManagerC2, qaVar.l);
            }
            PersonalDataManager.AutofillProfile autofillProfile2 = qaVar.l;
            String str = qaVar.n;
            autofillProfile2.q = str;
            qaVar.i[1] = str;
        }
        int size = listSubList.size();
        s31 s31Var = this.w;
        N.MPFG5SwC(s31Var.a, s31Var, 2, size, z);
        this.A = new dh2(1, i, listSubList);
    }

    public final void d(qa qaVar) {
        String string;
        qa qaVar2;
        b bVar = new b(qaVar);
        c4 c4Var = this.t;
        if (c4Var.i == null) {
            c4Var.i = new AutofillProfileBridge();
        }
        if (qaVar == null) {
            qaVar2 = new qa(c4Var.b, new PersonalDataManager.AutofillProfile());
            string = c4Var.b.getString(R$string.autofill_create_profile);
        } else {
            string = qaVar.e;
            qaVar2 = qaVar;
        }
        c4Var.q = new il(string);
        c4Var.p = qaVar2.l;
        c4Var.n = null;
        if (c4Var.j == null) {
            c4Var.j = xf0.a(c4Var.b.getString(R$string.autofill_profile_editor_country), AutofillProfileBridge.b(), null);
        }
        xf0 xf0Var = c4Var.j;
        xf0Var.p = new b4(c4Var);
        xf0Var.n = qa.g(c4Var.p);
        c4Var.h.a = c4Var.j.n.toString();
        c4Var.g.f = c4Var.j.n.toString();
        HashMap map = c4Var.d;
        int i = 8;
        int i2 = 4;
        if (map.isEmpty()) {
            map.put(2, xf0.b());
            map.put(3, xf0.b());
            map.put(7, xf0.b());
            map.put(4, new xf0(6));
            map.put(5, new xf0(6));
            map.put(6, new xf0(3));
            map.put(8, new xf0(4));
        }
        if (c4Var.k == null) {
            c4Var.k = xf0.c(1, c4Var.b.getString(R$string.autofill_profile_editor_phone_number), c4Var.e, c4Var.g, c4Var.h, c4Var.b.getString(R$string.pref_edit_dialog_field_required_validation_message), c4Var.b.getString(R$string.payments_phone_invalid_validation_message), null);
        }
        c4Var.k.n = c4Var.p.getPhoneNumber();
        il ilVar = c4Var.q;
        ilVar.h = new m91(c4Var, i, bVar, qaVar);
        ilVar.g = new b80(c4Var, i2, qaVar2, bVar);
        c4Var.d(c4Var.j.n.toString());
        if (c4Var.s != null) {
            c4Var.a.j();
        }
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        yq yqVar = (yq) this.o;
        yqVar.e.a(0);
        yqVar.b("Tab overview mode dismissed Payment Request UI.");
    }

    public final void j(ra raVar) {
        xf0 xf0VarC;
        xf0 xf0VarC2;
        final ey eyVar = this.k;
        final a aVar = new a(raVar);
        final ra raVar2 = raVar == null ? new ra(eyVar.b, new PersonalDataManager.AutofillProfile(), null, null, null, 7, eyVar.c, eyVar.d, eyVar.e) : raVar;
        xf0 xf0VarC3 = eyVar.c ? xf0.c(4, eyVar.b.getString(R$string.payments_name_field_in_contact_details), eyVar.g, null, null, eyVar.b.getString(R$string.pref_edit_dialog_field_required_validation_message), null, raVar2.q) : null;
        if (eyVar.d) {
            String string = eyVar.b.getString(R$string.autofill_profile_editor_phone_number);
            HashSet hashSet = eyVar.h;
            v02 v02Var = new v02();
            if (eyVar.k == null) {
                eyVar.k = new cy();
            }
            xf0VarC = xf0.c(1, string, hashSet, v02Var, eyVar.k, eyVar.b.getString(R$string.pref_edit_dialog_field_required_validation_message), eyVar.b.getString(R$string.payments_phone_invalid_validation_message), raVar2.r);
        } else {
            xf0VarC = null;
        }
        if (eyVar.e) {
            String string2 = eyVar.b.getString(R$string.autofill_profile_editor_email_address);
            HashSet hashSet2 = eyVar.i;
            if (eyVar.l == null) {
                eyVar.l = new dy();
            }
            xf0VarC2 = xf0.c(2, string2, hashSet2, null, eyVar.l, eyVar.b.getString(R$string.pref_edit_dialog_field_required_validation_message), eyVar.b.getString(R$string.payments_email_invalid_validation_message), raVar2.s);
        } else {
            xf0VarC2 = null;
        }
        il ilVar = new il(raVar == null ? eyVar.b.getString(R$string.payments_add_contact_details_label) : raVar.e);
        if (xf0VarC3 != null) {
            jy1 jy1Var = eyVar.j;
            xf0VarC3.k = jy1Var != null ? jy1Var.c : null;
            ilVar.a(xf0VarC3);
        }
        if (xf0VarC != null) {
            jy1 jy1Var2 = eyVar.j;
            xf0VarC.k = jy1Var2 != null ? jy1Var2.d : null;
            ilVar.a(xf0VarC);
        }
        if (xf0VarC2 != null) {
            jy1 jy1Var3 = eyVar.j;
            xf0VarC2.k = jy1Var3 != null ? jy1Var3.b : null;
            ilVar.a(xf0VarC2);
        }
        ilVar.h = aVar.p1(raVar);
        final xf0 xf0Var = xf0VarC3;
        final xf0 xf0Var2 = xf0VarC;
        final xf0 xf0Var3 = xf0VarC2;
        ilVar.g = new Runnable() { // from class: ay
            @Override // java.lang.Runnable
            public final void run() {
                String string3;
                String string4;
                ey eyVar2 = eyVar;
                eyVar2.getClass();
                ra raVar3 = raVar2;
                PersonalDataManager.AutofillProfile autofillProfile = raVar3.k;
                xf0 xf0Var4 = xf0Var;
                String string5 = null;
                if (xf0Var4 != null) {
                    string3 = xf0Var4.n.toString();
                    autofillProfile.getClass();
                    autofillProfile.f = new PersonalDataManager.b(string3, 4);
                } else {
                    string3 = null;
                }
                xf0 xf0Var5 = xf0Var2;
                if (xf0Var5 != null) {
                    string4 = xf0Var5.n.toString();
                    autofillProfile.getClass();
                    autofillProfile.o = new PersonalDataManager.b(string4, 4);
                } else {
                    string4 = null;
                }
                xf0 xf0Var6 = xf0Var3;
                if (xf0Var6 != null) {
                    string5 = xf0Var6.n.toString();
                    autofillProfile.getClass();
                    autofillProfile.p = new PersonalDataManager.b(string5, 4);
                }
                if (eyVar2.f) {
                    PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                    personalDataManagerC.getClass();
                    Object obj = ThreadUtils.a;
                    autofillProfile.a = N.McRRW$S3(personalDataManagerC.a, personalDataManagerC, autofillProfile);
                }
                if (autofillProfile.getGUID().isEmpty()) {
                    autofillProfile.a = UUID.randomUUID().toString();
                }
                autofillProfile.c = true;
                raVar3.f(autofillProfile.getGUID(), string3, string4, string5);
                raVar3.g(0);
                aVar.onResult(raVar3);
            }
        };
        eyVar.a.i(ilVar);
        if (eyVar.j != null) {
            eyVar.a.j();
        }
    }

    public final void k() {
        if (this.m != null && this.z != null) {
            u();
            return;
        }
        this.x.k(this.y);
        if (w()) {
            this.x.n(2, this.n);
        }
    }

    public final List<g71> l(List<kz1> list) {
        if (list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            kz1 kz1Var = list.get(i);
            g20 g20VarN = n(kz1Var.c);
            arrayList.add(new g71(kz1Var.b, this.s.size() > 1 ? N.MnbePYrk(g20VarN.a, g20VarN) : "", g20VarN.b(kz1Var.c.c), kz1Var.d));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final ty1 m(PaymentApp paymentApp) {
        org.chromium.components.payments.e eVar = (org.chromium.components.payments.e) this.v;
        if (eVar.u) {
            return null;
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(eVar.t.b());
        if (!mapUnmodifiableMap.isEmpty() && paymentApp != null) {
            HashSet<String> hashSet = new HashSet(paymentApp.j());
            hashSet.retainAll(mapUnmodifiableMap.keySet());
            if (hashSet.isEmpty()) {
                return null;
            }
            for (String str : hashSet) {
                ty1 ty1Var = (ty1) mapUnmodifiableMap.get(str);
                if (paymentApp.t(str, ty1Var.d)) {
                    return ty1Var;
                }
            }
        }
        return null;
    }

    public final g20 n(ry1 ry1Var) {
        String str = ry1Var.b;
        HashMap map = this.s;
        g20 g20Var = (g20) map.get(str);
        if (g20Var != null) {
            return g20Var;
        }
        g20 g20Var2 = new g20(ry1Var.b, Locale.getDefault());
        map.put(str, g20Var2);
        return g20Var2;
    }

    public final ArrayList o() {
        ArrayList arrayList = new ArrayList();
        dh2 dh2Var = this.z;
        if (dh2Var == null) {
            return arrayList;
        }
        Iterator<lf0> it = dh2Var.a.iterator();
        while (it.hasNext()) {
            arrayList.add((PaymentApp) it.next());
        }
        return arrayList;
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public final void onAddressNormalized(PersonalDataManager.AutofillProfile autofillProfile) {
        yq yqVar = (yq) this.o;
        yqVar.c.getClass();
        Context contextA = e.a.a(yqVar.b);
        if (contextA == null) {
            yqVar.e.a(8);
            yqVar.b("Unable to find Chrome context.");
            return;
        }
        ky1 ky1VarJ = new qa(contextA, autofillProfile).j();
        org.chromium.components.payments.e eVar = yqVar.a;
        if (eVar == null) {
            return;
        }
        eVar.r(ky1VarJ);
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public final void onCouldNotNormalize(PersonalDataManager.AutofillProfile autofillProfile) {
        onAddressNormalized(autofillProfile);
    }

    public final void p(tz1 tz1Var, int i) {
        this.g.post(tz1Var.p1(i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : this.z : this.B : this.n : this.A));
    }

    public final PaymentApp q() {
        dh2 dh2Var = this.z;
        if (dh2Var == null) {
            return null;
        }
        return (PaymentApp) dh2Var.b();
    }

    public final int r(org.chromium.chrome.browser.payments.ui.c cVar, int i) {
        if (i == 1) {
            d(null);
            this.m = cVar;
            return 1;
        }
        if (i != 3) {
            return 3;
        }
        j(null);
        return 2;
    }

    public final int s(int i, lf0 lf0Var, org.chromium.chrome.browser.payments.ui.c cVar) {
        if (i == 1) {
            d((qa) lf0Var);
            this.m = cVar;
            return 1;
        }
        if (i != 3) {
            return 3;
        }
        j((ra) lf0Var);
        return 2;
    }

    public final int t(int i, lf0 lf0Var, org.chromium.chrome.browser.payments.ui.c cVar) {
        pz1 pz1Var;
        pz1 pz1Var2;
        yq yqVar = (yq) this.o;
        yqVar.c.getClass();
        Context contextA = e.a.a(yqVar.b);
        if (contextA == null) {
            return 3;
        }
        boolean z = yqVar.g;
        if (i == 1) {
            qa qaVar = (qa) lf0Var;
            if (qaVar.a) {
                this.A.d(lf0Var);
                PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                PersonalDataManager.AutofillProfile autofillProfile = qaVar.l;
                personalDataManagerC.getClass();
                Object obj = ThreadUtils.a;
                N.M7ysHTTC(personalDataManagerC.a, personalDataManagerC, autofillProfile, 5, this);
            } else {
                d(qaVar);
            }
            this.m = cVar;
            return 1;
        }
        if (i == 2) {
            this.n.d(lf0Var);
            String str = lf0Var.g;
            org.chromium.components.payments.e eVar = yqVar.a;
            if (eVar != null && (pz1Var2 = eVar.A) != null) {
                pz1Var2.W(str);
            }
            this.m = cVar;
            return 1;
        }
        if (i == 3) {
            ra raVar = (ra) lf0Var;
            if (raVar.a) {
                this.B.d(lf0Var);
                if (!z) {
                    return 3;
                }
                iy1 iy1Var = new iy1(0);
                iy1Var.c = raVar.q;
                iy1Var.d = raVar.r;
                iy1Var.b = raVar.s;
                org.chromium.components.payments.e eVar2 = yqVar.a;
                if (eVar2 != null && yqVar.g && (pz1Var = eVar2.A) != null) {
                    pz1Var.G(iy1Var);
                }
            } else {
                j(raVar);
                if (!z) {
                    return 2;
                }
            }
            this.m = cVar;
            return 1;
        }
        if (i == 4) {
            if (w() && this.A == null) {
                c(contextA);
            }
            if (v() && this.B == null) {
                this.B = new zx(contextA, this.D, this.k, this.w);
            }
            e eVar3 = this.x;
            dh2 dh2Var = this.A;
            dh2 dh2Var2 = this.n;
            zx zxVar = this.B;
            j jVar = (j) eVar3.g;
            if (jVar.w() && eVar3.z.getVisibility() == 8) {
                eVar3.n(1, dh2Var);
                eVar3.n(2, dh2Var2);
                eVar3.z.setVisibility(0);
                eVar3.r.getChildAt(eVar3.r.indexOfChild(eVar3.z) - 1).setVisibility(0);
                int iIndexOfChild = eVar3.r.indexOfChild(eVar3.A);
                if (iIndexOfChild != -1) {
                    eVar3.A.setVisibility(0);
                    eVar3.r.getChildAt(iIndexOfChild - 1).setVisibility(0);
                }
            } else if (!jVar.w() && eVar3.z.getVisibility() == 0) {
                eVar3.z.setVisibility(8);
                eVar3.r.getChildAt(eVar3.r.indexOfChild(eVar3.z) - 1).setVisibility(8);
                int iIndexOfChild2 = eVar3.r.indexOfChild(eVar3.A);
                if (iIndexOfChild2 != -1) {
                    eVar3.A.setVisibility(8);
                    eVar3.r.getChildAt(iIndexOfChild2 - 1).setVisibility(8);
                }
            }
            if (jVar.v() && eVar3.B.getVisibility() == 8) {
                eVar3.n(3, zxVar);
                eVar3.B.setVisibility(0);
                eVar3.r.getChildAt(eVar3.r.indexOfChild(eVar3.B) - 1).setVisibility(0);
            } else if (!jVar.v() && eVar3.B.getVisibility() == 0) {
                eVar3.B.setVisibility(8);
                eVar3.r.getChildAt(eVar3.r.indexOfChild(eVar3.B) - 1).setVisibility(8);
            }
            ao3.e(eVar3.r, "PaymentRequestUI.selectedPaymentMethodUpdated");
            A((PaymentApp) lf0Var);
            this.z.d(lf0Var);
        }
        return 3;
    }

    public final void u() {
        dh2 dh2Var = this.z;
        PaymentApp paymentAppQ = q();
        dh2Var.d = (paymentAppQ == null ? 0 : paymentAppQ.k()) != 2;
        this.m.onResult(new jz1(this.y, this.A, this.n, this.B, this.z));
        this.m = null;
    }

    public final boolean v() {
        PaymentApp paymentAppQ = q();
        oy1 oy1Var = this.v;
        if (((org.chromium.components.payments.e) oy1Var).u) {
            return false;
        }
        nz1 nz1Var = ((org.chromium.components.payments.e) oy1Var).n;
        if (nz1Var.b && (paymentAppQ == null || !paymentAppQ.n())) {
            return true;
        }
        if (!nz1Var.d || (paymentAppQ != null && paymentAppQ.o())) {
            return nz1Var.c && (paymentAppQ == null || !paymentAppQ.m());
        }
        return true;
    }

    public final boolean w() {
        oy1 oy1Var = this.v;
        if (((org.chromium.components.payments.e) oy1Var).u || !((org.chromium.components.payments.e) oy1Var).n.e) {
            return false;
        }
        PaymentApp paymentAppQ = q();
        return paymentAppQ == null || !paymentAppQ.p();
    }

    public final void x(String str) {
        if (w()) {
            if ((this.n.a.isEmpty() || !TextUtils.isEmpty(str)) && this.A.b() != null) {
                this.A.b().j = false;
                dh2 dh2Var = this.A;
                dh2Var.c = -2;
                dh2Var.e = str;
            }
        }
    }

    public final void y() {
        kz1 kz1Var;
        if (N.M1X7xdZV("WebPaymentsModifiers")) {
            org.chromium.components.payments.e eVar = (org.chromium.components.payments.e) this.v;
            if (eVar.u || eVar.t.a().isEmpty() || this.z == null) {
                return;
            }
            for (int i = 0; i < this.z.c(); i++) {
                PaymentApp paymentApp = (PaymentApp) this.z.a(i);
                ty1 ty1VarM = m(paymentApp);
                paymentApp.f = (ty1VarM == null || (kz1Var = ty1VarM.b) == null) ? null : n(kz1Var.c).b(ty1VarM.b.c.c);
            }
            A(q());
        }
    }

    public final void z(sy1 sy1Var) {
        dh2 dh2Var;
        kz1 kz1Var = sy1Var.b;
        if (kz1Var != null) {
            n(kz1Var.c);
        }
        kz1[] kz1VarArr = sy1Var.c;
        if (kz1VarArr != null) {
            for (kz1 kz1Var2 : kz1VarArr) {
                n(kz1Var2.c);
            }
        }
        wz1[] wz1VarArr = sy1Var.d;
        if (wz1VarArr != null) {
            for (wz1 wz1Var : wz1VarArr) {
                n(wz1Var.d);
            }
        }
        ty1[] ty1VarArr = sy1Var.e;
        if (ty1VarArr != null) {
            for (ty1 ty1Var : ty1VarArr) {
                kz1 kz1Var3 = ty1Var.b;
                if (kz1Var3 != null) {
                    n(kz1Var3.c);
                }
                for (kz1 kz1Var4 : ty1Var.c) {
                    n(kz1Var4.c);
                }
            }
        }
        g20 g20VarN = n(sy1Var.b.c);
        g71 g71Var = new g71(sy1Var.b.b, N.MnbePYrk(g20VarN.a, g20VarN), g20VarN.b(sy1Var.b.c.c), false);
        kz1[] kz1VarArr2 = sy1Var.c;
        this.y = new tl2(g71Var, l(kz1VarArr2 == null ? new ArrayList<>() : Arrays.asList(kz1VarArr2)));
        if (this.n == null || sy1Var.d != null) {
            wz1[] wz1VarArr2 = sy1Var.d;
            if (wz1VarArr2 == null || wz1VarArr2.length == 0) {
                dh2Var = new dh2(2);
            } else {
                ArrayList arrayList = new ArrayList();
                int i = -1;
                for (int i2 = 0; i2 < wz1VarArr2.length; i2++) {
                    wz1 wz1Var2 = wz1VarArr2[i2];
                    g20 g20VarN2 = n(wz1Var2.d);
                    String str = this.s.size() > 1 ? N.MnbePYrk(g20VarN2.a, g20VarN2) + " " : "";
                    String str2 = wz1Var2.b;
                    String str3 = wz1Var2.c;
                    StringBuilder sbJ = C0173a.j(str);
                    sbJ.append(g20VarN2.b(wz1Var2.d.c));
                    arrayList.add(new lf0(null, str2, str3, sbJ.toString()));
                    if (wz1Var2.e) {
                        i = i2;
                    }
                }
                dh2Var = new dh2(2, i, Collections.unmodifiableList(arrayList));
            }
            this.n = dh2Var;
        }
        y();
    }

    @Override // nj2.a
    public final void a() {
    }
}
