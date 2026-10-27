package defpackage;

import android.content.SharedPreferences;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: TabAttributeCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ky2 {
    public static SharedPreferences e;
    public final y13 a;
    public final b b = new b();
    public final a c;
    public final c d;

    /* JADX INFO: compiled from: TabAttributeCache.java */
    public class a extends j23 {
        public a(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.z10
        public final void H(int i, Tab tab) {
            if (tab.isIncognito()) {
                return;
            }
            ky2.c().edit().putInt(ky2.b(tab.getId()), i).apply();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (tabImpl.isIncognito() || tabImpl.g == null) {
                return;
            }
            ky2.a(tabImpl);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void r1(Tab tab) {
            if (tab.isIncognito()) {
                return;
            }
            ky2.c().edit().putString(ky2.e(tab.getId()), tab.getTitle()).apply();
        }

        @Override // defpackage.z10
        public final void u1(Tab tab, long j) {
            if (tab.isIncognito()) {
                return;
            }
            ky2.c().edit().putLong(ky2.d(tab.getId()), j).apply();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void x0(TabImpl tabImpl) {
            if (tabImpl.isIncognito()) {
                return;
            }
            ky2.c().edit().putString(ky2.f(tabImpl.getId()), tabImpl.getUrl().m()).apply();
        }
    }

    /* JADX INFO: compiled from: TabAttributeCache.java */
    public class b implements t13 {
        @Override // defpackage.t13
        public final void i1(Tab tab) {
            int id = tab.getId();
            ky2.c().edit().remove(id + "_url").remove(ky2.f(id)).remove(ky2.e(id)).remove(ky2.b(id)).remove(ky2.d(id)).remove(id + "_last_search_term").apply();
        }
    }

    /* JADX INFO: compiled from: TabAttributeCache.java */
    public class c implements e23 {
        public c() {
        }

        @Override // defpackage.e23
        public final void g() {
            SharedPreferences.Editor editorEdit = ky2.c().edit();
            editorEdit.clear();
            ky2 ky2Var = ky2.this;
            p13 p13VarD = ky2Var.a.u().d(false);
            for (int i = 0; i < p13VarD.getCount(); i++) {
                Tab tabAt = p13VarD.getTabAt(i);
                int id = tabAt.getId();
                editorEdit.putString(ky2.f(id), tabAt.getUrl().m());
                editorEdit.putString(ky2.e(id), tabAt.getTitle());
                CriticalPersistedTabData criticalPersistedTabDataM = CriticalPersistedTabData.m(tabAt);
                editorEdit.putInt(ky2.b(id), criticalPersistedTabDataM.q);
                editorEdit.putLong(ky2.d(id), criticalPersistedTabDataM.r);
            }
            editorEdit.apply();
            Tab tabI = ky2Var.a.i();
            if (tabI != null) {
                ky2.a(tabI);
            }
            p13VarD.f.a(ky2Var.b);
        }
    }

    public ky2(y13 y13Var) {
        this.a = y13Var;
        this.c = new a(y13Var);
        c cVar = new c();
        this.d = cVar;
        y13Var.m(cVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(org.chromium.chrome.browser.tab.Tab r7) {
        /*
            org.chromium.content_public.browser.WebContents r0 = r7.c()
            if (r0 != 0) goto L8
            goto Lc5
        L8:
            int r0 = r7.getId()
            org.chromium.content_public.browser.WebContents r1 = r7.c()
            org.chromium.content_public.browser.NavigationController r1 = r1.m()
            xk1 r1 = r1.o()
            org.chromium.content_public.browser.WebContents r2 = r7.c()
            org.chromium.chrome.browser.profiles.Profile r2 = org.chromium.chrome.browser.profiles.Profile.b(r2)
            if (r2 != 0) goto L24
            goto La4
        L24:
            org.chromium.components.search_engines.TemplateUrlService r2 = defpackage.oj2.q(r2)
            org.chromium.url.GURL r7 = r7.getUrl()
            long r3 = r2.c
            java.lang.String r7 = J.N.MfK2IDmL(r3, r2, r7)
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L3a
            goto La4
        L3a:
            int r7 = r1.b
            r3 = -1
            int r7 = r7 + r3
        L3e:
            if (r7 < 0) goto La4
            org.chromium.content_public.browser.NavigationEntry r4 = r1.a(r7)
            org.chromium.url.GURL r4 = r4.c
            long r5 = r2.c
            java.lang.String r4 = J.N.MfK2IDmL(r5, r2, r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto La1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r1 = 0
        L58:
            int r2 = r4.length()
            if (r1 >= r2) goto L9c
            char r2 = r4.charAt(r1)
            r5 = 37
            if (r2 != r5) goto L92
            int r2 = r1 + 2
            int r5 = r4.length()
            if (r2 < r5) goto L6f
            goto L92
        L6f:
            int r5 = r1 + 1
            char r5 = r4.charAt(r5)
            r6 = 16
            int r5 = java.lang.Character.digit(r5, r6)
            if (r5 == r3) goto L8a
            char r5 = r4.charAt(r2)
            int r5 = java.lang.Character.digit(r5, r6)
            if (r5 != r3) goto L88
            goto L8a
        L88:
            r1 = r2
            goto L99
        L8a:
            char r2 = r4.charAt(r1)
            r7.append(r2)
            goto L99
        L92:
            char r2 = r4.charAt(r1)
            r7.append(r2)
        L99:
            int r1 = r1 + 1
            goto L58
        L9c:
            java.lang.String r7 = r7.toString()
            goto La5
        La1:
            int r7 = r7 + (-1)
            goto L3e
        La4:
            r7 = 0
        La5:
            android.content.SharedPreferences r1 = c()
            android.content.SharedPreferences$Editor r1 = r1.edit()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "_last_search_term"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.content.SharedPreferences$Editor r7 = r1.putString(r0, r7)
            r7.apply()
        Lc5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ky2.a(org.chromium.chrome.browser.tab.Tab):void");
    }

    public static String b(int i) {
        return i + "_rootID";
    }

    public static SharedPreferences c() {
        if (e == null) {
            e = xz.a.getSharedPreferences("tab_attribute_cache", 0);
        }
        return e;
    }

    public static String d(int i) {
        return i + "_timestampMillis";
    }

    public static String e(int i) {
        return i + "_title";
    }

    public static String f(int i) {
        return i + "_gurl";
    }
}
