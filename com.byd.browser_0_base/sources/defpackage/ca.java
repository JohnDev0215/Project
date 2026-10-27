package defpackage;

import java.util.HashMap;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ThreadUtils;
import org.chromium.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.chrome.browser.profiles.b;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: AutocompleteControllerProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ca implements zi3 {
    public static final aj3<ca> f = new aj3<>(ca.class);
    public final b<AutocompleteController> e = new b<>(new m62(5));

    public static ca a(WindowAndroid windowAndroid) {
        Object obj = ThreadUtils.a;
        c cVar = windowAndroid.r;
        aj3<ca> aj3Var = f;
        ca caVar = (ca) aj3Var.e(cVar);
        if (caVar != null) {
            return caVar;
        }
        ca caVar2 = new ca();
        aj3Var.a(cVar, caVar2);
        return caVar2;
    }

    @Override // defpackage.zi3
    public final void O() {
        Object obj = ThreadUtils.a;
        b<AutocompleteController> bVar = this.e;
        b.a aVar = bVar.c;
        if (aVar != null) {
            ProfileManager.b(aVar);
        }
        bVar.c = null;
        HashMap map = bVar.a;
        CollectionUtil.b(map, new on0(2, bVar));
        map.clear();
    }

    public final AutocompleteController b(final Profile profile) {
        Object obj = ThreadUtils.a;
        final int i = 0;
        return this.e.a(profile, new nw2() { // from class: ba
            @Override // defpackage.nw2
            public final Object get() {
                int i2 = i;
                Profile profile2 = profile;
                switch (i2) {
                    case 0:
                        return new AutocompleteController(profile2);
                    default:
                        return new cu1(new a41(3, profile2));
                }
            }
        });
    }
}
