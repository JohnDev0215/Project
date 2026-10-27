package p;

import com.google.common.collect.ImmutableMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class d7 {
    public static final ImmutableMap a;
    public static final ImmutableMap b;
    public static final ImmutableMap c;

    static {
        com.google.common.collect.b bVarA = ImmutableMap.a();
        bVarA.b("es_ES", "es-es");
        bVarA.b("es_AR", "es-ar");
        bVarA.b("fr_CA", "fr-ca");
        bVarA.b("pt_PT", "pt-pt");
        bVarA.b("zh_HK", "zh-tw");
        bVarA.b("zh_TW", "zh-tw");
        bVarA.b("es_MX", "es-mx");
        ImmutableMap immutableMapA = bVarA.a();
        v03.i(immutableMapA, "builder<String, String>(…\"es-mx\")\n        .build()");
        a = immutableMapA;
        com.google.common.collect.b bVarA2 = ImmutableMap.a();
        bVarA2.b("ca", "ca-es");
        bVarA2.b("es", "es-419");
        bVarA2.b("pt", "pt-br");
        ImmutableMap immutableMapA2 = bVarA2.a();
        v03.i(immutableMapA2, "builder<String, String>(…br\")\n            .build()");
        b = immutableMapA2;
        com.google.common.collect.b bVarA3 = ImmutableMap.a();
        bVarA3.b("in", "id");
        bVarA3.b("ji", "yi");
        bVarA3.b("iw", "he");
        ImmutableMap immutableMapA3 = bVarA3.a();
        v03.i(immutableMapA3, "builder<String, String>(…\", \"he\")\n        .build()");
        c = immutableMapA3;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037 A[PHI: r1
  0x0037: PHI (r1v8 java.lang.String) = (r1v6 java.lang.String), (r1v7 java.lang.String) binds: [B:15:0x0035, B:24:0x0058] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String a(java.lang.String r7, java.util.Locale r8) {
        /*
            java.lang.String r0 = "en"
            if (r8 == 0) goto L9
            java.lang.String r1 = r8.getLanguage()
            goto La
        L9:
            r1 = r0
        La:
            java.lang.String r2 = "langCode"
            p.v03.i(r1, r2)
            boolean r1 = b(r1)
            com.google.common.collect.ImmutableMap r3 = p.d7.b
            com.google.common.collect.ImmutableMap r4 = p.d7.a
            if (r1 == 0) goto L1b
            r1 = 1
            goto L5b
        L1b:
            if (r8 != 0) goto L1e
            goto L5a
        L1e:
            java.lang.String r1 = r8.toString()
            java.lang.String r5 = "androidLocale.toString()"
            p.v03.i(r1, r5)
            int r5 = r1.length()
            r6 = 2
            if (r5 >= r6) goto L2f
            goto L5a
        L2f:
            java.lang.Object r1 = r4.get(r1)
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L39
        L37:
            r0 = r1
            goto L5a
        L39:
            java.lang.String r1 = r8.getLanguage()
            p.v03.i(r1, r2)
            boolean r2 = b(r1)
            if (r2 == 0) goto L47
            goto L5a
        L47:
            java.lang.Object r0 = r3.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L50
            goto L5a
        L50:
            com.google.common.collect.ImmutableMap r0 = p.d7.c
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L37
        L5a:
            r1 = 0
        L5b:
            boolean r2 = r4.containsValue(r0)
            if (r2 != 0) goto L9b
            boolean r2 = r3.containsValue(r0)
            if (r2 == 0) goto L68
            goto L9b
        L68:
            if (r8 == 0) goto L9b
            if (r1 != 0) goto L9b
            java.lang.String r1 = r8.getCountry()
            java.lang.String r2 = "androidLocale.country"
            p.v03.i(r1, r2)
            int r1 = r1.length()
            if (r1 != 0) goto L7c
            goto L9b
        L7c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            int r0 = r7.length()
            if (r0 != 0) goto L8c
            java.lang.String r7 = "_"
        L8c:
            r1.append(r7)
            java.lang.String r7 = r8.getCountry()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            return r7
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.d7.a(java.lang.String, java.util.Locale):java.lang.String");
    }

    public static boolean b(String str) {
        return str.length() < 2 || v03.n(str.charAt(0), 97) < 0 || v03.n(str.charAt(0), 122) > 0 || v03.n(str.charAt(1), 97) < 0 || v03.n(str.charAt(1), 122) > 0;
    }
}
