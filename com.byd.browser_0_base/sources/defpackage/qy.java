package defpackage;

import defpackage.k81;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.language.settings.LanguageItemListFragment;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.chrome.browser.language.settings.c;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qy implements l81 {
    public final /* synthetic */ int e;
    public final /* synthetic */ ya1 f;
    public final /* synthetic */ k81.a g;
    public final /* synthetic */ b h;

    public /* synthetic */ qy(b bVar, ya1 ya1Var, k81.a aVar, int i) {
        this.e = i;
        this.h = bVar;
        this.f = ya1Var;
        this.g = aVar;
    }

    @Override // defpackage.l81
    public final k81 b() {
        int i = this.e;
        k81.a aVar = this.g;
        ya1 ya1Var = this.f;
        b bVar = this.h;
        switch (i) {
            case 0:
                ContentLanguagesPreference.a aVar2 = (ContentLanguagesPreference.a) bVar;
                aVar2.getClass();
                c.h(6);
                return new zd(aVar2.q, ya1Var, aVar);
            default:
                return new zd(LanguageItemListFragment.this.K(), ya1Var, aVar);
        }
    }
}
