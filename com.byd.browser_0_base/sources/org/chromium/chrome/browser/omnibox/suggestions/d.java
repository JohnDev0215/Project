package org.chromium.chrome.browser.omnibox.suggestions;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.ca;
import defpackage.ce;
import defpackage.cw2;
import defpackage.f2;
import defpackage.ga;
import defpackage.ge0;
import defpackage.he0;
import defpackage.i31;
import defpackage.ja;
import defpackage.kg2;
import defpackage.la;
import defpackage.n3;
import defpackage.n53;
import defpackage.na;
import defpackage.nw2;
import defpackage.oa;
import defpackage.oj2;
import defpackage.pr1;
import defpackage.ps;
import defpackage.qm0;
import defpackage.sj3;
import defpackage.vr1;
import defpackage.ya1;
import defpackage.zp1;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: AutocompleteMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements AutocompleteController.a, OmniboxSuggestionsDropdown.a, vr1, cw2 {
    public boolean A;
    public boolean B;
    public int C;
    public boolean G;
    public boolean H;
    public String I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f73J;
    public long K;
    public final Context e;
    public final ca f;
    public final ga g;
    public final sj3 h;
    public final PropertyModel i;
    public final Handler j;
    public final org.chromium.chrome.browser.omnibox.f k;
    public final nw2<bi1> l;
    public final ge0 m;
    public final he0 n;
    public final Callback<Tab> o;
    public final nw2<n53> p;
    public final i31 q;
    public final qm0 r;
    public Runnable t;
    public Runnable u;
    public boolean v;
    public AutocompleteController w;
    public long x;
    public boolean y;
    public boolean z;
    public AutocompleteResult s = AutocompleteResult.e;
    public int D = 0;
    public int E = 0;
    public long F = -1;
    public boolean L = true;

    /* JADX INFO: compiled from: AutocompleteMediator.java */
    public class a implements Runnable {
        public final /* synthetic */ Runnable e;

        public a(Runnable runnable) {
            this.e = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            d dVar = d.this;
            dVar.getClass();
            this.e.run();
            dVar.t = null;
        }
    }

    public d(Context context, ca caVar, ga gaVar, org.chromium.chrome.browser.omnibox.h hVar, PropertyModel propertyModel, Handler handler, zp1 zp1Var, nw2 nw2Var, nw2 nw2Var2, org.chromium.chrome.browser.omnibox.f fVar, ps psVar, nw2 nw2Var3, ce.a aVar, i31 i31Var, f2 f2Var) {
        this.e = context;
        this.f = caVar;
        this.g = gaVar;
        this.h = hVar;
        this.i = propertyModel;
        this.q = i31Var;
        this.l = zp1Var;
        this.j = handler;
        this.k = fVar;
        this.o = psVar;
        this.p = nw2Var3;
        ya1 ya1Var = (ya1) propertyModel.j(i.c);
        ge0 ge0Var = new ge0(nw2Var, aVar, f2Var);
        this.m = ge0Var;
        ge0Var.f = nw2Var2;
        this.n = new he0(context, ya1Var);
        this.r = new qm0(17, this);
    }

    public static void c(d dVar) {
        dVar.D = 0;
        dVar.F = -1L;
        org.chromium.chrome.browser.omnibox.c cVar = (org.chromium.chrome.browser.omnibox.c) dVar.g;
        if (cVar.k.K) {
            org.chromium.chrome.browser.omnibox.f fVar = dVar.k;
            if (fVar.q() || fVar.e()) {
                int iJ = fVar.j(cVar.k.E, false);
                dVar.y = iJ == 16;
                dVar.w.c(((org.chromium.chrome.browser.omnibox.h) dVar.h).b(), iJ, fVar.n(), fVar.getTitle());
            }
        }
    }

    @Override // defpackage.vr1
    public final void D0() {
        ((org.chromium.chrome.browser.omnibox.c) this.g).h.e(true, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0354  */
    @Override // org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(org.chromium.components.omnibox.AutocompleteResult r22, java.lang.String r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 1068
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.suggestions.d.a(org.chromium.components.omnibox.AutocompleteResult, java.lang.String, boolean):void");
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown.a
    public final void b(long j, boolean z) {
        n(false);
        if (z) {
            this.K = j;
        }
    }

    public final void d() {
        this.y = false;
        Runnable runnable = this.t;
        if (runnable != null) {
            this.j.removeCallbacks(runnable);
            this.t = null;
        }
    }

    public final void e(long j, String str) {
        AutocompleteController autocompleteController;
        AutocompleteMatch autocompleteMatchA;
        boolean z = false;
        if (this.s.b.size() > 0 && str.trim().equals(this.I.trim())) {
            autocompleteMatchA = this.s.b.get(0);
            z = true;
        } else if (!this.v || (autocompleteController = this.w) == null || (autocompleteMatchA = autocompleteController.a(str, ((org.chromium.chrome.browser.omnibox.c) this.g).k.E)) == null) {
            return;
        }
        AutocompleteMatch autocompleteMatch = autocompleteMatchA;
        boolean z2 = z;
        kg2.b(autocompleteMatch);
        g(0, autocompleteMatch, autocompleteMatch.j, j, z2);
    }

    public final void f() {
        if (!this.v || this.w == null) {
            return;
        }
        n(true);
        he0 he0Var = this.n;
        he0Var.e.clear();
        he0Var.b.clear();
        this.s = AutocompleteResult.e;
        if (pr1.c()) {
            return;
        }
        o();
    }

    public final void g(int i, AutocompleteMatch autocompleteMatch, GURL gurl, long j, boolean z) {
        TraceEvent traceEventH0 = TraceEvent.H0("AutocompleteMediator.loadUrlFromOmniboxMatch", null);
        try {
            oj2.Y(System.currentTimeMillis() - this.x, "Omnibox.FocusToOpenTimeAnyPopupState3");
            this.u = null;
            this.G = true;
            GURL gurlP = p(autocompleteMatch, i, gurl, !z);
            int i2 = autocompleteMatch.n;
            k(i, 1, autocompleteMatch);
            int i3 = i2 & 255;
            int i4 = autocompleteMatch.a;
            if (i3 == 1 && TextUtils.equals(gurlP.j(), this.k.n())) {
                i2 = 8;
            } else if (i4 == 0 && ((org.chromium.chrome.browser.omnibox.h) this.h).f.n) {
                i2 = 0;
            }
            int i5 = i2;
            boolean z2 = this.z;
            ga gaVar = this.g;
            if (!z2) {
                ((org.chromium.chrome.browser.omnibox.c) gaVar).k.s(null, 12, false);
            }
            if (i4 == 27) {
                ((org.chromium.chrome.browser.omnibox.c) gaVar).k.X(gurlP.j(), i5, j, autocompleteMatch.p, autocompleteMatch.q);
            } else {
                ((org.chromium.chrome.browser.omnibox.c) gaVar).k.X(gurlP.j(), i5, j, null, null);
            }
            if (this.A) {
                this.j.post(this.r);
            } else if (this.z) {
                ((org.chromium.chrome.browser.omnibox.c) gaVar).k.s(null, 12, false);
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final void h(int i, AutocompleteMatch autocompleteMatch, GURL gurl) {
        if (!this.s.c || (this.v && this.w != null)) {
            g(i, autocompleteMatch, gurl, this.K, true);
        } else {
            this.u = new la(this, i, autocompleteMatch, gurl);
        }
    }

    public final void i(final String str, String str2) {
        AutocompleteController autocompleteController;
        if (this.f73J) {
            return;
        }
        this.L = true;
        d();
        if (this.D == 0 && this.v && (autocompleteController = this.w) != null) {
            long j = autocompleteController.f;
            if (j != 0) {
                N.MHXditHc(j);
            }
            this.F = SystemClock.elapsedRealtime();
            this.D = 1;
        }
        n(false);
        boolean zIsEmpty = TextUtils.isEmpty(str);
        ga gaVar = this.g;
        if (zIsEmpty) {
            f();
            j(new ja(this, 1), -1L);
        } else {
            org.chromium.chrome.browser.omnibox.f fVar = this.k;
            if (fVar.q() || fVar.e()) {
                org.chromium.chrome.browser.omnibox.h hVar = (org.chromium.chrome.browser.omnibox.h) this.h;
                final boolean z = !hVar.f.f();
                final int selectionStart = hVar.f.getSelectionStart() == hVar.f.getSelectionEnd() ? hVar.f.getSelectionStart() : -1;
                final int iJ = fVar.j(((org.chromium.chrome.browser.omnibox.c) gaVar).k.E, false);
                final String strN = fVar.n();
                j(new Runnable() { // from class: ka
                    @Override // java.lang.Runnable
                    public final void run() {
                        String str3 = strN;
                        int i = iJ;
                        String str4 = str;
                        this.e.w.b(i, selectionStart, str3, str4, z);
                    }
                }, 30L);
            }
        }
        ((org.chromium.chrome.browser.omnibox.c) gaVar).k.n0();
    }

    public final void j(Runnable runnable, long j) {
        d();
        a aVar = new a(runnable);
        this.t = aVar;
        if (!this.v || this.w == null) {
            return;
        }
        if (j == -1) {
            aVar.run();
        } else {
            this.j.postDelayed(aVar, j);
        }
    }

    public final void k(int i, int i2, AutocompleteMatch autocompleteMatch) {
        oj2.O("Android.Omnibox.UsedSuggestionFromCache", this.s.c);
        if (this.s.c) {
            return;
        }
        org.chromium.chrome.browser.omnibox.f fVar = this.k;
        String strN = fVar.n();
        int iJ = fVar.j(((org.chromium.chrome.browser.omnibox.c) this.g).k.E, false);
        long jElapsedRealtime = this.F > 0 ? SystemClock.elapsedRealtime() - this.F : -1L;
        org.chromium.chrome.browser.omnibox.h hVar = (org.chromium.chrome.browser.omnibox.h) this.h;
        int length = hVar.b().length() - hVar.c().length();
        WebContents webContentsC = fVar.q() ? fVar.d().c() : null;
        AutocompleteController autocompleteController = this.w;
        int i3 = autocompleteMatch.a;
        if (autocompleteController.f != 0 && autocompleteController.g.a(i, 1)) {
            N.MqRSHXK7(autocompleteController.f, i, i2, strN, iJ, jElapsedRealtime, length, webContentsC);
        }
    }

    @Override // defpackage.vr1
    public final void k0() {
        this.H = true;
        ((org.chromium.chrome.browser.omnibox.c) this.g).h.e(false, false);
    }

    public final void l() {
        if (!this.v || this.w == null) {
            return;
        }
        Runnable runnable = this.u;
        Handler handler = this.j;
        if (runnable != null) {
            handler.post(runnable);
            this.u = null;
            d();
        } else {
            Runnable runnable2 = this.t;
            if (runnable2 != null) {
                handler.postAtFrontOfQueue(runnable2);
            }
        }
    }

    public final void m(AutocompleteMatch autocompleteMatch, Runnable runnable) {
        n3.X0("MobileOmniboxDeleteGesture");
        if (autocompleteMatch.o) {
            Context context = this.e;
            View viewInflate = LayoutInflater.from(context).inflate(R$layout.dialog_clear_delete, (ViewGroup) null);
            TextView textView = (TextView) viewInflate.findViewById(R$id.tv_message);
            Button button = (Button) viewInflate.findViewById(R$id.btn_cancel);
            Button button2 = (Button) viewInflate.findViewById(R$id.btn_sure);
            LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
            boolean z = true;
            if (n3.Q0()) {
                linearLayout.setLayoutDirection(1);
            } else {
                linearLayout.setLayoutDirection(0);
            }
            int i = R$string.omnibox_confirm_delete;
            int i2 = autocompleteMatch.a;
            if (i2 != 19 && i2 != 26 && i2 != 27) {
                z = false;
            }
            if (z) {
                i = R$string.omnibox_confirm_delete_from_clipboard;
            }
            textView.setText(i);
            Dialog dialog = new Dialog(context, R$style.Dialog_Style);
            dialog.setContentView(viewInflate);
            dialog.setCancelable(false);
            Window window = dialog.getWindow();
            window.setGravity(17);
            WindowManager.LayoutParams attributes = window.getAttributes();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            attributes.width = (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 4) / 5;
            attributes.height = -2;
            window.setAttributes(attributes);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            button2.setOnClickListener(new na(runnable, dialog));
            button.setOnClickListener(new oa(dialog));
        }
    }

    public final void n(boolean z) {
        AutocompleteController autocompleteController;
        if (this.v && (autocompleteController = this.w) != null) {
            long j = autocompleteController.f;
            if (j != 0) {
                N.MktNJvjP(j, z);
            }
        }
        d();
    }

    public final void o() {
        boolean z = this.C == 2 && this.s.b.size() > 0;
        PropertyModel.n nVar = i.a;
        PropertyModel propertyModel = this.i;
        boolean zK = propertyModel.k(nVar);
        propertyModel.l(nVar, z);
        if (!z || zK) {
            return;
        }
        this.L = true;
    }

    public final GURL p(AutocompleteMatch autocompleteMatch, int i, GURL gurl, boolean z) {
        int i2;
        int i3;
        if (!this.v || this.w == null || (i2 = autocompleteMatch.a) == 20 || i2 == 28 || i2 == 29) {
            return gurl;
        }
        if (z) {
            i3 = -1;
        } else {
            if (this.s.b.size() <= i || this.s.b.get(i) != autocompleteMatch) {
                i = 0;
                while (true) {
                    if (i >= this.s.b.size()) {
                        i = -1;
                        break;
                    }
                    if (autocompleteMatch.equals(this.s.b.get(i))) {
                        break;
                    }
                    i++;
                }
            }
            i3 = i;
        }
        if (i3 == -1) {
            return gurl;
        }
        AutocompleteController autocompleteController = this.w;
        GURL gurl2 = (autocompleteController.f != 0 && autocompleteController.g.a(i3, 2)) ? (GURL) N.MXE8I_ZK(autocompleteController.f, i3, this.F > 0 ? SystemClock.elapsedRealtime() - this.F : -1L, null, null) : null;
        return gurl2 == null ? gurl : gurl2;
    }
}
