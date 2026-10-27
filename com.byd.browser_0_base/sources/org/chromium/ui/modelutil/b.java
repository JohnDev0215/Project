package org.chromium.ui.modelutil;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import defpackage.ag;
import defpackage.bg;
import defpackage.da;
import defpackage.ea;
import defpackage.im2;
import defpackage.kf0;
import defpackage.ku2;
import defpackage.mn3;
import defpackage.pr1;
import defpackage.se3;
import defpackage.tr1;
import defpackage.za1;
import defpackage.zf;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout;
import org.chromium.chrome.browser.omnibox.suggestions.editurl.EditUrlSuggestionView;
import org.chromium.chrome.browser.omnibox.suggestions.l;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: LazyConstructionPropertyMcp.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b<M extends k<P>, V, P> implements k.a<P> {
    public final M e;
    public final P f;
    public final a<M> g;
    public final mn3<V> h;
    public final j.a<M, V, P> i;
    public boolean j;
    public V k;
    public final HashSet l;

    /* JADX INFO: compiled from: LazyConstructionPropertyMcp.java */
    public interface a<T> {
    }

    public b(PropertyModel propertyModel, im2 im2Var, org.chromium.chrome.browser.omnibox.suggestions.b bVar, ag agVar) {
        P p = (P) org.chromium.chrome.browser.omnibox.suggestions.i.a;
        HashSet hashSet = new HashSet();
        this.l = hashSet;
        this.e = propertyModel;
        this.f = p;
        this.g = im2Var;
        this.h = bVar;
        this.i = agVar;
        hashSet.addAll(propertyModel.b());
        se3 se3Var = new se3(24, this);
        l.a aVar = bVar.b;
        if (aVar != null) {
            se3Var.onResult(aVar);
        } else {
            bVar.a.add(se3Var);
        }
        propertyModel.a(this);
    }

    @Override // org.chromium.ui.modelutil.k.a
    public final void a(k<P> kVar, P p) {
        this.l.add(p);
        if (((PropertyModel) this.e).k((PropertyModel.n) ((im2) this.g).e) || p == this.f) {
            if (this.k != null) {
                b();
                return;
            }
            if (this.j) {
                return;
            }
            int i = 1;
            this.j = true;
            org.chromium.chrome.browser.omnibox.suggestions.b bVar = (org.chromium.chrome.browser.omnibox.suggestions.b) this.h;
            Context context = bVar.c;
            ku2 ku2VarT = ku2.t();
            try {
                OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = new OmniboxSuggestionsDropdown(context);
                ku2VarT.close();
                SuggestionLayout suggestionLayout = new SuggestionLayout(context);
                suggestionLayout.setSearchClick(new org.chromium.chrome.browser.omnibox.suggestions.a(bVar));
                if (!pr1.c()) {
                    omniboxSuggestionsDropdown.getViewGroup().setVisibility(8);
                }
                int i2 = 0;
                omniboxSuggestionsDropdown.getViewGroup().setClipToPadding(false);
                tr1 tr1Var = new tr1(bVar.d);
                omniboxSuggestionsDropdown.setAdapter(tr1Var);
                tr1Var.H(0, new da(0), new org.chromium.chrome.browser.omnibox.suggestions.base.b(new ag(7)));
                tr1Var.H(1, new za1() { // from class: fa
                    @Override // defpackage.za1
                    public final View a(ViewGroup viewGroup) {
                        return new EditUrlSuggestionView(viewGroup.getContext());
                    }
                }, new kf0());
                int i3 = 14;
                tr1Var.H(2, new da(3), new org.chromium.chrome.browser.omnibox.suggestions.base.b(new zf(i3)));
                tr1Var.H(3, new ea(2), new org.chromium.chrome.browser.omnibox.suggestions.base.b(new bg(16)));
                int i4 = 15;
                tr1Var.H(4, new da(4), new org.chromium.chrome.browser.omnibox.suggestions.base.b(new zf(i4)));
                tr1Var.H(5, new ea(3), new org.chromium.chrome.browser.omnibox.suggestions.base.b(new zf(11)));
                tr1Var.H(7, new ea(i2), new bg(i3));
                tr1Var.H(6, new da(1), new zf(12));
                tr1Var.H(8, new ea(i), new org.chromium.chrome.browser.omnibox.suggestions.base.b(new bg(i4)));
                tr1Var.H(9, new da(2), new zf(13));
                bVar.b = new l.a((ViewGroup) ((ViewStub) bVar.e.e.getRootView().findViewById(R$id.omnibox_results_container_stub)).inflate(), omniboxSuggestionsDropdown, suggestionLayout);
                while (i2 < bVar.a.size()) {
                    ((Callback) bVar.a.get(i2)).onResult(bVar.b);
                    i2++;
                }
                bVar.a = null;
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
    }

    public final void b() {
        M m;
        j.a<M, V, P> aVar;
        P p;
        HashSet hashSet = this.l;
        Iterator it = hashSet.iterator();
        boolean z = false;
        while (true) {
            boolean zHasNext = it.hasNext();
            m = this.e;
            aVar = this.i;
            p = this.f;
            if (!zHasNext) {
                break;
            }
            Object next = it.next();
            if (next == p) {
                z = true;
            } else {
                aVar.b(m, this.k, next);
            }
        }
        if (z) {
            aVar.b(m, this.k, p);
        }
        hashSet.clear();
    }
}
