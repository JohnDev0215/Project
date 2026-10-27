package org.chromium.chrome.browser.search_resumption;

import android.view.ViewStub;
import defpackage.ca;
import defpackage.pq2;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.search_resumption.e;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: SearchResumptionModuleCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final b a;
    public final d b;

    public a(NewTabPageLayout newTabPageLayout, ca caVar, Tab tab, Tab tab2, Profile profile, int i, e.b bVar) {
        d dVar = new d(new pq2(25, tab2));
        this.b = dVar;
        this.a = new b((ViewStub) newTabPageLayout.findViewById(i), caVar, tab, tab2, profile, dVar, bVar);
    }
}
