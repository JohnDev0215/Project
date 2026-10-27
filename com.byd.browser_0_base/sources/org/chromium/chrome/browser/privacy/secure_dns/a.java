package org.chromium.chrome.browser.privacy.secure_dns;

import J.N;
import java.util.ArrayList;

/* JADX INFO: compiled from: SecureDnsBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: org.chromium.chrome.browser.privacy.secure_dns.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SecureDnsBridge.java */
    public static class C0109a {
        public final String a;
        public final String b;
        public final String c;

        public C0109a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public final String toString() {
            return this.a;
        }
    }

    public static ArrayList a() {
        String[][] strArrMvmUzLgj = N.MvmUzLgj();
        ArrayList arrayList = new ArrayList(strArrMvmUzLgj.length);
        for (String[] strArr : strArrMvmUzLgj) {
            arrayList.add(new C0109a(strArr[0], strArr[1], strArr[2]));
        }
        return arrayList;
    }
}
