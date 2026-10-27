package defpackage;

import J.N;

/* JADX INFO: renamed from: Fo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0104Fo {
    public long a;

    public C0104Fo() {
        long jM2x3bkF7 = N.M2x3bkF7();
        C1569tt.l.b();
        this.a = jM2x3bkF7;
    }

    public static C0086Eo a(String str, String str2) {
        if (str.startsWith("http:///.")) {
            String strSubstring = str.substring(8);
            StringBuilder sbF = Z7.f("http://");
            sbF.append(str.substring(9));
            str = sbF.toString();
            if (!str2.matches("^.*(?i);[\\t ]*Domain[\\t ]*=.*$")) {
                str2 = str2.matches("^.*;\\s*$") ? Z7.d(str2, " Domain=", strSubstring) : Z7.d(str2, "; Domain=", strSubstring);
            }
        }
        return new C0086Eo(str, str2);
    }

    public String b(String str) {
        String strMwtyXc6D = N.MwtyXc6D(this.a, this, str);
        if (strMwtyXc6D == null || strMwtyXc6D.trim().isEmpty()) {
            return null;
        }
        return strMwtyXc6D;
    }
}
