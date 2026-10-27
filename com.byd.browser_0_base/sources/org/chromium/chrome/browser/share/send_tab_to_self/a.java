package org.chromium.chrome.browser.share.send_tab_to_self;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.PatternSyntaxException;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: NotificationSharedPrefManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: org.chromium.chrome.browser.share.send_tab_to_self.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NotificationSharedPrefManager.java */
    public static class C0112a {
        public final int a;
        public final String b;
        public final int c;

        public C0112a(int i, int i2, String str) {
            this.a = i2;
            this.b = str;
            this.c = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof C0112a)) {
                return false;
            }
            C0112a c0112a = (C0112a) obj;
            return this.a == c0112a.a && this.b.equals(c0112a.b) && this.c == c0112a.c;
        }
    }

    public static C0112a a(String str) {
        Set<String> setI;
        C0112a c0112a;
        String[] strArrSplit;
        if (str == null || (setI = SharedPreferencesManager.getInstance().i("send_tab_to_self.notification.active", null)) == null) {
            return null;
        }
        Iterator<String> it = setI.iterator();
        while (it.hasNext()) {
            try {
                strArrSplit = it.next().split("_");
            } catch (NumberFormatException | PatternSyntaxException unused) {
            }
            if (strArrSplit.length != 3) {
                c0112a = null;
                if (c0112a == null && str.equals(c0112a.b)) {
                    return c0112a;
                }
            } else {
                c0112a = new C0112a(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), strArrSplit[2]);
                if (c0112a == null) {
                }
            }
        }
        return null;
    }
}
