package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.fl1;
import defpackage.il1;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.gesturenav.NavigationSheetView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.f;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: NavigationSheetCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class gl1 implements ii, fl1 {
    public final View e;
    public final LayoutInflater f;
    public final nw2<ji> g;
    public final il1 h;
    public final a i = new a();
    public final f j;
    public final int k;
    public final int l;
    public final View m;
    public fl1.a n;
    public NavigationSheetView o;
    public boolean p;
    public boolean q;
    public final Profile r;

    /* JADX INFO: compiled from: NavigationSheetCoordinator.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void c(int i) {
            gl1.this.c();
        }
    }

    public gl1(View view, AppCompatActivity appCompatActivity, c63 c63Var, Profile profile) {
        new Handler();
        ya1 ya1Var = new ya1();
        f fVar = new f(ya1Var);
        this.j = fVar;
        this.m = view;
        this.g = c63Var;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(appCompatActivity);
        this.f = layoutInflaterFrom;
        this.e = layoutInflaterFrom.inflate(R$layout.navigation_sheet_toolbar, (ViewGroup) null);
        this.r = profile;
        this.h = new il1(appCompatActivity, ya1Var, profile, new pq2(15, this));
        fVar.a(0, new o61(R$layout.navigation_popup_item), new zf(8));
        Math.min(appCompatActivity.getResources().getDisplayMetrics().density * 224.0f, view.getWidth() / 2);
        this.k = appCompatActivity.getResources().getDimensionPixelSize(R$dimen.navigation_popup_item_height);
        this.l = appCompatActivity.getResources().getDimensionPixelSize(R$dimen.navigation_sheet_content_bottom_padding) + appCompatActivity.getResources().getDimensionPixelSize(R$dimen.navigation_sheet_content_top_padding);
    }

    @Override // defpackage.fl1
    public final boolean b() {
        nw2<ji> nw2Var = this.g;
        boolean z = false;
        z = false;
        if (nw2Var.get() != null) {
            this.p = false;
            this.q = false;
        }
        this.q = true;
        NavigationSheetView navigationSheetView = (NavigationSheetView) this.f.inflate(R$layout.navigation_sheet, (ViewGroup) null);
        this.o = navigationSheetView;
        ((ListView) navigationSheetView.findViewById(R$id.navigation_entries)).setAdapter((ListAdapter) this.j);
        fl1.a aVar = this.n;
        boolean z2 = this.p;
        boolean zH = this.r.h();
        j63 j63Var = (j63) aVar;
        xk1 xk1VarM = j63Var.a.c().m().m(z2);
        if (!zH) {
            xk1VarM.a.add(new NavigationEntry(-1, new GURL("chrome://history/"), GURL.emptyGURL(), GURL.emptyGURL(), j63Var.c, null));
        }
        if (xk1VarM.b() != 0) {
            il1 il1Var = this.h;
            il1Var.l = xk1VarM;
            HashSet hashSet = new HashSet();
            for (int i = 0; i < il1Var.l.b(); i++) {
                PropertyModel propertyModel = new PropertyModel(Arrays.asList(il1.b.d));
                NavigationEntry navigationEntryA = il1Var.l.a(i);
                PropertyModel.r<String> rVar = il1.b.b;
                String strJ = navigationEntryA.e;
                GURL gurl = navigationEntryA.b;
                boolean zL = bk3.l(gurl);
                Profile profile = il1Var.k;
                if (zL) {
                    strJ = profile.h() ? il1Var.j : il1Var.i;
                }
                if (TextUtils.isEmpty(strJ)) {
                    strJ = navigationEntryA.d.j();
                }
                if (TextUtils.isEmpty(strJ)) {
                    strJ = gurl.j();
                }
                propertyModel.p(rVar, strJ);
                propertyModel.p(il1.b.c, new hl1(il1Var, i, z ? 1 : 0, navigationEntryA));
                xa1 xa1Var = new xa1(0, propertyModel);
                ya1 ya1Var = il1Var.e;
                ya1Var.t(xa1Var);
                if (navigationEntryA.f == null && !hashSet.contains(gurl)) {
                    du duVar = new du(il1Var, gurl);
                    if (gurl.j().equals("chrome://history/")) {
                        ya1Var.get(i).b.p(il1.b.a, il1Var.f);
                    } else {
                        FaviconHelper faviconHelper = il1Var.b;
                        faviconHelper.getClass();
                        faviconHelper.a(profile, gurl.j(), il1Var.d, duVar);
                        hashSet.add(gurl);
                    }
                }
            }
            if (nw2Var.get().d(this, true)) {
                nw2Var.get().g(this.i);
                z = true;
            } else {
                c();
                this.o = null;
            }
        }
        if (z) {
            n3.X0("BackMenu_Popup");
        }
        return z;
    }

    public final void c() {
        ji jiVar = this.g.get();
        if (jiVar == null) {
            return;
        }
        jiVar.c(this, false);
        jiVar.e(this.i);
        this.h.e.clear();
    }

    @Override // defpackage.ii
    public final View d() {
        return this.o;
    }

    @Override // defpackage.ii
    public final int e() {
        return 1;
    }

    @Override // defpackage.ii
    public final View g() {
        return this.e;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.overscroll_navigation_sheet_opened_half;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.overscroll_navigation_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        nw2<ji> nw2Var = this.g;
        if (nw2Var.get() == null || this.q || nw2Var.get().m()) {
            return -2;
        }
        Context context = this.m.getContext();
        return context.getResources().getDimensionPixelSize(R$dimen.navigation_sheet_peek_height);
    }

    @Override // defpackage.ii
    public final int m() {
        return this.o.getVerticalScrollOffset();
    }

    @Override // defpackage.ii
    public final float n() {
        if (this.q) {
            return -2.0f;
        }
        int height = this.m.getHeight() / 2;
        int i = this.k;
        return Math.min((i / 2) + height, (this.j.getCount() * i) + this.l) / r0.getHeight();
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.overscroll_navigation_sheet_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.overscroll_navigation_sheet_opened_full;
    }

    @Override // defpackage.ii
    public final float s() {
        View view = this.m;
        return Math.min(view.getHeight(), (this.j.getCount() * this.k) + this.l) / view.getHeight();
    }

    @Override // defpackage.ii
    public final boolean t() {
        return true;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
