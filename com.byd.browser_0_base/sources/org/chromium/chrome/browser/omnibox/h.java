package org.chromium.chrome.browser.omnibox;

import android.text.TextUtils;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.ag;
import defpackage.b50;
import defpackage.it3;
import defpackage.lj1;
import defpackage.pr1;
import defpackage.sj3;
import defpackage.t41;
import defpackage.vj3;
import defpackage.wz;
import defpackage.zc0;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: UrlBarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements sj3, vj3, t41.b {
    public static final wz p = new wz(19);
    public final LinearLayout e;
    public UrlBar f;
    public final TextView g;
    public final View h;
    public i i;
    public final t41 j;
    public final it3 k;
    public Runnable l;
    public Runnable m;
    public Callback<Boolean> n;
    public boolean o;

    /* JADX WARN: Multi-variable type inference failed */
    public h(UrlBar urlBar, it3 it3Var, ActionMode.Callback callback, Callback<Boolean> callback2, UrlBar.c cVar, t41 t41Var, boolean z, Callback<Throwable> callback3) {
        wz wzVar = p;
        this.l = wzVar;
        this.m = wzVar;
        this.f = urlBar;
        urlBar.setTag(R$id.report_exception_callback, callback3);
        this.j = t41Var;
        this.k = it3Var;
        this.n = callback2;
        HashMap mapE = PropertyModel.e(j.o);
        PropertyModel.r<ActionMode.Callback> rVar = j.a;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = callback;
        mapE.put(rVar, gVar);
        PropertyModel.r<it3> rVar2 = j.n;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = it3Var;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<UrlBar.c> rVar3 = j.d;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = cVar;
        mapE.put(rVar3, gVar3);
        PropertyModel.n nVar = j.m;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = z;
        mapE.put(nVar, aVar);
        PropertyModel propertyModel = new PropertyModel(mapE);
        org.chromium.ui.modelutil.j.b(propertyModel, urlBar, new ag(5));
        this.i = new i(new lj1(7, this), propertyModel);
        t41Var.a(this);
        ViewGroup viewGroup = (ViewGroup) this.f.getParent();
        this.e = (LinearLayout) viewGroup.findViewById(R$id.url_bar_linearlayout);
        this.g = (TextView) viewGroup.findViewById(R$id.url_bar_hint);
        this.h = viewGroup.findViewById(R$id.location_bar_status_icon);
    }

    @Override // t41.b
    public final void a(boolean z) {
        if (this.o) {
            this.f.setCursorVisible(z);
        }
    }

    public final String b() {
        return this.f.getTextWithAutocomplete();
    }

    public final String c() {
        return this.f.getTextWithoutAutocomplete();
    }

    public final void d(boolean z) {
        TextView textView = this.g;
        if (textView == null) {
            return;
        }
        View view = this.h;
        LinearLayout linearLayout = this.e;
        int i = 0;
        if (z) {
            linearLayout.setVisibility(8);
            textView.setVisibility(8);
            UrlBar urlBar = this.f;
            urlBar.setHint(urlBar.getContext().getString(R$string.search_or_type_web_address));
            view.setVisibility(0);
            return;
        }
        this.f.setHint("");
        boolean zIsEmpty = TextUtils.isEmpty(this.i.h.c);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.i.h.a);
        linearLayout.setVisibility(zIsEmpty2 ? 0 : 8);
        textView.setVisibility((zIsEmpty2 && zIsEmpty) ? 0 : 8);
        if (zIsEmpty2 && zIsEmpty) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public final void e(boolean z, boolean z2) {
        this.f.removeCallbacks(this.m);
        if (z) {
            f(32, false);
            this.j.i(this.f);
        } else {
            zc0 zc0Var = new zc0(23, this);
            this.m = zc0Var;
            this.f.postDelayed(zc0Var, z2 ? 150L : 0L);
            f(16, true);
        }
    }

    public final void f(int i, boolean z) {
        if (pr1.a()) {
            return;
        }
        this.f.removeCallbacks(this.l);
        it3 it3Var = this.k;
        if (it3Var != null) {
            Window window = it3Var.a;
            if (window.getAttributes().softInputMode == i) {
                return;
            }
            if (!z) {
                window.setSoftInputMode(i);
                return;
            }
            b50 b50Var = new b50(i, 3, this);
            this.l = b50Var;
            this.f.postDelayed(b50Var, 300L);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b9 A[EDGE_INSN: B:55:0x00b9->B:56:0x00ba BREAK  A[LOOP:0: B:42:0x0088->B:53:0x00b4]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean g(defpackage.rj3 r13, int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.h.g(rj3, int, int):boolean");
    }

    @Override // defpackage.vj3
    public final void k(boolean z) {
        this.f.removeCallbacks(this.l);
    }
}
