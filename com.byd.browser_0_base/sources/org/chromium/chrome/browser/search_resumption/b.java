package org.chromium.chrome.browser.search_resumption;

import J.N;
import android.text.TextUtils;
import android.view.ViewStub;
import defpackage.bq;
import defpackage.c01;
import defpackage.ca;
import defpackage.ng2;
import defpackage.og2;
import defpackage.oj2;
import defpackage.on0;
import defpackage.pg2;
import defpackage.z7;
import java.util.List;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_resumption.e;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.sync.SyncService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SearchResumptionModuleMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements AutocompleteController.a, SigninManager.b, SyncService.SyncStateChangedListener {
    public final ViewStub e;
    public final Tab f;
    public final Tab g;
    public final d h;
    public final SigninManager i;
    public final SyncService j;
    public final TemplateUrlService k;
    public AutocompleteController l;
    public PropertyModel m;
    public boolean n = true;
    public boolean o = true;
    public SearchResumptionModuleView p;
    public SearchResumptionModuleBridge q;

    public b(ViewStub viewStub, ca caVar, Tab tab, Tab tab2, Profile profile, d dVar, e.b bVar) {
        this.e = viewStub;
        this.f = tab;
        this.g = tab2;
        this.h = dVar;
        boolean zA = bq.a();
        TemplateUrlService templateUrlServiceQ = oj2.q(profile);
        this.k = templateUrlServiceQ;
        templateUrlServiceQ.a(new ng2(this));
        if (bVar != null) {
            if (zA) {
                f(bVar.b, bVar.c, true);
            } else {
                e(bVar.d, true);
            }
        } else if (zA) {
            SearchResumptionModuleBridge searchResumptionModuleBridge = new SearchResumptionModuleBridge(profile);
            this.q = searchResumptionModuleBridge;
            String strJ = tab.getUrl().j();
            c01 c01Var = new c01(16, this);
            long j = searchResumptionModuleBridge.a;
            if (j != 0) {
                searchResumptionModuleBridge.b = c01Var;
                N.MK2gNevr(j, searchResumptionModuleBridge, strJ);
            }
        } else {
            AutocompleteController autocompleteControllerB = caVar.b(profile);
            this.l = autocompleteControllerB;
            autocompleteControllerB.addOnSuggestionsReceivedListener(this);
            this.l.c("", templateUrlServiceQ.g(tab.getUrl()) ? 9 : 4, tab.getUrl().j(), tab.getTitle());
        }
        z7.m().getClass();
        SigninManager signinManagerD = z7.D(profile);
        this.i = signinManagerD;
        signinManagerD.i(this);
        SyncService syncService = SyncService.get();
        this.j = syncService;
        syncService.addSyncStateChangedListener(this);
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController.a
    public final void a(AutocompleteResult autocompleteResult, String str, boolean z) {
        boolean z2;
        if (z && this.m == null) {
            List<AutocompleteMatch> list = autocompleteResult.b;
            if (list.size() < 2) {
                z2 = false;
            } else {
                int i = 0;
                for (AutocompleteMatch autocompleteMatch : list) {
                    z2 = true;
                    if ((TextUtils.isEmpty(autocompleteMatch.d) ^ true) && autocompleteMatch.a == 8) {
                        i++;
                    }
                    if (i >= 2) {
                        break;
                    }
                }
                z2 = false;
            }
            if (z2) {
                e(autocompleteResult.b, false);
            } else {
                c.a(0);
            }
        }
    }

    public final void b() {
        AutocompleteController autocompleteController = this.l;
        if (autocompleteController != null) {
            autocompleteController.removeOnSuggestionsReceivedListener(this);
        }
        SearchResumptionModuleView searchResumptionModuleView = this.p;
        if (searchResumptionModuleView != null) {
            searchResumptionModuleView.b();
        }
        SearchResumptionModuleBridge searchResumptionModuleBridge = this.q;
        if (searchResumptionModuleBridge != null) {
            searchResumptionModuleBridge.a();
        }
        this.k.i(new ng2(this));
        this.i.n(this);
        this.j.removeSyncStateChangedListener(this);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void c() {
        this.o = true;
        g();
    }

    public final boolean d() {
        if (this.m != null) {
            return false;
        }
        this.p = (SearchResumptionModuleView) this.e.inflate();
        PropertyModel propertyModel = new PropertyModel(og2.c);
        this.m = propertyModel;
        j.b(propertyModel, this.p, new pg2());
        this.m.p(og2.b, new on0(7, this));
        return true;
    }

    public final void e(List<AutocompleteMatch> list, boolean z) {
        if (d()) {
            this.h.a(list, (SearchResumptionTileContainerView) this.p.findViewById(R.id.search_resumption_module_tiles_container));
            c.b(z);
            if (z) {
                return;
            }
            e eVarD = e.d();
            Tab tab = this.g;
            GURL url = this.f.getUrl();
            eVarD.getClass();
            e.a(tab, url, list);
        }
    }

    public final void f(String[] strArr, GURL[] gurlArr, boolean z) {
        if (d()) {
            this.h.b(strArr, gurlArr, (SearchResumptionTileContainerView) this.p.findViewById(R.id.search_resumption_module_tiles_container));
            c.b(z);
            if (z) {
                return;
            }
            e eVarD = e.d();
            Tab tab = this.g;
            GURL url = this.f.getUrl();
            eVarD.getClass();
            e.b(tab, url, strArr, gurlArr);
        }
    }

    public final void g() {
        PropertyModel propertyModel = this.m;
        if (propertyModel != null) {
            propertyModel.l(og2.a, this.n && this.o);
        }
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void j() {
        this.o = false;
        g();
    }
}
