package org.chromium.chrome.browser.search_resumption;

import J.N;
import defpackage.bk3;
import defpackage.bq;
import defpackage.lk3;
import defpackage.sk;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SearchResumptionUserData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements lk3 {
    public long e = -1;
    public b f;

    /* JADX INFO: compiled from: SearchResumptionUserData.java */
    public static class a {
        public static final e a = new e();
    }

    public static void a(Tab tab, GURL gurl, List list) {
        if (tab == null || !bk3.l(tab.getUrl())) {
            return;
        }
        e eVarC = c(tab);
        if (eVarC == null) {
            eVarC = new e();
        }
        eVarC.e = System.currentTimeMillis();
        eVarC.f = new b(gurl, list);
        tab.B().d(e.class, eVarC);
    }

    public static void b(Tab tab, GURL gurl, String[] strArr, GURL[] gurlArr) {
        if (tab == null || !bk3.l(tab.getUrl())) {
            return;
        }
        e eVarC = c(tab);
        if (eVarC == null) {
            eVarC = new e();
        }
        eVarC.e = System.currentTimeMillis();
        eVarC.f = new b(gurl, strArr, gurlArr);
        tab.B().d(e.class, eVarC);
    }

    public static e c(Tab tab) {
        e eVar = (e) tab.B().b(e.class);
        if (eVar == null) {
            return null;
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - eVar.e);
        sk skVar = bq.a;
        if (!(seconds >= ((long) N.M37SqSAy("SearchResumptionModuleAndroid", "tab_expiration_time", 3600)))) {
            return eVar;
        }
        tab.B().c(e.class);
        return null;
    }

    public static e d() {
        return a.a;
    }

    /* JADX INFO: compiled from: SearchResumptionUserData.java */
    public static class b {
        public final GURL a;
        public final String[] b;
        public final GURL[] c;
        public final List<AutocompleteMatch> d;

        public b(GURL gurl, String[] strArr, GURL[] gurlArr) {
            this.a = gurl;
            this.b = strArr;
            this.c = gurlArr;
            this.d = null;
        }

        public b(GURL gurl, List<AutocompleteMatch> list) {
            this.a = gurl;
            this.d = list;
            this.b = null;
            this.c = null;
        }
    }
}
