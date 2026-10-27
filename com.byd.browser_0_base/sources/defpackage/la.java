package defpackage;

import defpackage.w01;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class la implements Runnable {
    public final /* synthetic */ int e = 0;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ la(w01 w01Var, w01.b bVar, int i, ka2 ka2Var) {
        this.g = w01Var;
        this.h = bVar;
        this.f = i;
        this.i = ka2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        Object obj = this.i;
        Object obj2 = this.h;
        Object obj3 = this.g;
        switch (i) {
            case 0:
                d dVar = (d) obj3;
                dVar.g(this.f, (AutocompleteMatch) obj2, (GURL) obj, dVar.K, true);
                break;
            default:
                ((w01) obj3).F((w01.b) obj2, this.f, (ka2) obj);
                break;
        }
    }

    public /* synthetic */ la(d dVar, int i, AutocompleteMatch autocompleteMatch, GURL gurl) {
        this.g = dVar;
        this.f = i;
        this.h = autocompleteMatch;
        this.i = gurl;
    }
}
