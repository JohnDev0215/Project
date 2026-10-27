package defpackage;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: DomDistillerTabUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class pb0 {
    public static Integer a;
    public static final yj1 b = new yj1("ReaderModeInCCT", false);

    public static int a() {
        if (a == null) {
            a = Integer.valueOf(N.MWpdTnYb());
        }
        return a.intValue();
    }

    public static boolean b(Tab tab) {
        return !oj2.k(Profile.b(tab.c())).a("dom_distiller.reader_for_accessibility") && a() == 2;
    }
}
