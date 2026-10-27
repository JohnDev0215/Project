package defpackage;

import java.util.HashSet;
import org.chromium.base.CollectionUtil;

/* JADX INFO: compiled from: UrlBarData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rj3 {
    public static final HashSet<String> f = CollectionUtil.e("about", "content", "data", "file", "ftp", "inline", "javascript", "chrome");
    public static final HashSet<String> g = CollectionUtil.e("about", "data", "file", "ftp", "http", "https", "inline", "javascript", "chrome");
    public static final rj3 h = b("");
    public final String a;
    public final CharSequence b;
    public final String c;
    public final int d;
    public final int e;

    public rj3(String str, CharSequence charSequence, int i, int i2, String str2) {
        this.a = str;
        this.b = charSequence;
        this.d = i;
        this.e = i2;
        this.c = str2;
    }

    public static rj3 a(String str, CharSequence charSequence, int i, int i2, String str2) {
        return new rj3(str, charSequence, i, i2, str2);
    }

    public static rj3 b(String str) {
        return a(null, str, 0, 0, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.rj3 c(java.lang.String r7, android.text.SpannableStringBuilder r8, java.lang.String r9) {
        /*
            java.lang.String r0 = r8.toString()
            android.net.Uri r1 = android.net.Uri.parse(r0)
            java.lang.String r1 = r1.getScheme()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 47
            r4 = 0
            if (r2 != 0) goto L85
            java.util.HashSet<java.lang.String> r2 = defpackage.rj3.f
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L26
            int r0 = r8.length()
            rj3 r7 = a(r7, r8, r4, r0, r9)
            return r7
        L26:
            java.lang.String r2 = "blob"
            boolean r2 = r2.equals(r1)
            r5 = 58
            if (r2 == 0) goto L5d
            int r2 = r1.length()
        L34:
            int r6 = r8.length()
            if (r2 >= r6) goto L46
            char r6 = r8.charAt(r2)
            if (r6 == r5) goto L43
            if (r6 == r3) goto L43
            goto L4a
        L43:
            int r2 = r2 + 1
            goto L34
        L46:
            int r2 = r8.length()
        L4a:
            java.lang.String r2 = r0.substring(r2)
            android.net.Uri r2 = android.net.Uri.parse(r2)
            java.lang.String r2 = r2.getScheme()
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            if (r6 != 0) goto L5d
            r1 = r2
        L5d:
            java.util.HashSet<java.lang.String> r2 = defpackage.rj3.g
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L85
            int r2 = r0.indexOf(r1)
            int r1 = r1.length()
            int r1 = r1 + r2
        L6e:
            int r2 = r8.length()
            if (r1 >= r2) goto L80
            char r2 = r8.charAt(r1)
            if (r2 == r5) goto L7d
            if (r2 == r3) goto L7d
            goto L86
        L7d:
            int r1 = r1 + 1
            goto L6e
        L80:
            int r1 = r8.length()
            goto L86
        L85:
            r1 = r4
        L86:
            int r2 = r8.length()
            r5 = -1
            if (r1 >= r2) goto L92
            int r1 = r0.indexOf(r3, r1)
            goto L93
        L92:
            r1 = r5
        L93:
            if (r1 != r5) goto L9e
            int r0 = r8.length()
            rj3 r7 = a(r7, r8, r4, r0, r9)
            return r7
        L9e:
            int r2 = r8.length()
            int r2 = r2 + r5
            if (r1 != r2) goto Lae
            java.lang.CharSequence r8 = r0.subSequence(r4, r1)
            rj3 r7 = a(r7, r8, r4, r1, r9)
            return r7
        Lae:
            rj3 r7 = a(r7, r8, r4, r1, r9)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rj3.c(java.lang.String, android.text.SpannableStringBuilder, java.lang.String):rj3");
    }
}
