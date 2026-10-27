package p;

import com.spotify.common.uri.SpotifyUriParserException;

/* JADX INFO: loaded from: classes.dex */
public final class jf2 {
    public static final String[] g = {"http://spotify.com/", "https://spotify.com/", "http://open.spotify.com/", "https://open.spotify.com/"};
    public final boolean a = true;
    public final byte[] b;
    public final byte[] c;
    public int d;
    public final String e;
    public final String f;

    public jf2(String str) {
        int iIndexOf = str.indexOf("?");
        String strSubstring = iIndexOf == -1 ? str : str.substring(0, iIndexOf);
        this.e = strSubstring;
        this.f = iIndexOf == -1 ? null : str.substring(iIndexOf);
        this.b = new byte[strSubstring.length()];
        this.c = new byte[strSubstring.length()];
        for (int i = 0; i < this.e.length(); i++) {
            char cCharAt = this.e.charAt(i);
            if (cCharAt < ' ' || cCharAt > '~') {
                throw new SpotifyUriParserException("Spotify uri contained non-ASCII characters at position " + i + ": " + str, this.a);
            }
            if (cCharAt == ' ') {
                throw new SpotifyUriParserException("Spotify uri contained space", this.a);
            }
            this.b[i] = (byte) cCharAt;
        }
        this.d = 0;
    }

    public static int h(byte b) {
        if (b >= 48 && b <= 57) {
            return b - 48;
        }
        if (b >= 65 && b <= 70) {
            return b - 55;
        }
        if (b < 97 || b > 102) {
            return -1;
        }
        return b - 87;
    }

    public final boolean a() {
        return this.d == this.b.length;
    }

    public final String b() {
        return this.f;
    }

    public final String c() {
        return d(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
    
        r10.d = r1.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
    
        throw new com.spotify.common.uri.SpotifyUriParserException(p.u4.x("Bad hex character in Spotify uri: ", r4), r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String d(boolean r11) {
        /*
            r10 = this;
            int r0 = r10.d
            byte[] r1 = r10.b
            int r2 = r1.length
            boolean r3 = r10.a
            java.lang.String r4 = r10.e
            if (r0 >= r2) goto L84
            r0 = 0
            r2 = r0
        Ld:
            int r5 = r10.d
            int r6 = r1.length
            byte[] r7 = r10.c
            if (r5 >= r6) goto L7c
            int r6 = r5 + 1
            r10.d = r6
            r8 = r1[r5]
            r9 = 65
            if (r8 >= r9) goto L76
            r9 = 43
            if (r8 != r9) goto L25
            r8 = 32
            goto L76
        L25:
            r9 = 37
            if (r8 != r9) goto L6b
            int r8 = r5 + 3
            int r9 = r1.length
            if (r8 > r9) goto L5c
            int r5 = r5 + 2
            r10.d = r5
            r5 = r1[r6]
            int r5 = h(r5)
            int r6 = r10.d
            int r8 = r6 + 1
            r10.d = r8
            r6 = r1[r6]
            int r6 = h(r6)
            if (r5 < 0) goto L4d
            if (r6 < 0) goto L4d
            int r5 = r5 * 16
            int r5 = r5 + r6
            byte r8 = (byte) r5
            goto L76
        L4d:
            int r11 = r1.length
            r10.d = r11
            com.spotify.common.uri.SpotifyUriParserException r11 = new com.spotify.common.uri.SpotifyUriParserException
            java.lang.String r0 = "Bad hex character in Spotify uri: "
            java.lang.String r0 = p.u4.x(r0, r4)
            r11.<init>(r0, r3)
            throw r11
        L5c:
            int r11 = r1.length
            r10.d = r11
            com.spotify.common.uri.SpotifyUriParserException r11 = new com.spotify.common.uri.SpotifyUriParserException
            java.lang.String r0 = "Badly encoded character in Spotify uri: "
            java.lang.String r0 = p.u4.x(r0, r4)
            r11.<init>(r0, r3)
            throw r11
        L6b:
            if (r11 == 0) goto L76
            r5 = 58
            if (r8 == r5) goto L7c
            r5 = 47
            if (r8 != r5) goto L76
            goto L7c
        L76:
            int r5 = r2 + 1
            r7[r2] = r8
            r2 = r5
            goto Ld
        L7c:
            java.lang.String r11 = new java.lang.String
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.UTF_8
            r11.<init>(r7, r0, r2, r1)
            return r11
        L84:
            com.spotify.common.uri.SpotifyUriParserException r11 = new com.spotify.common.uri.SpotifyUriParserException
            java.lang.String r0 = "End of Spotify uri reached unexpectedly: "
            java.lang.String r0 = p.u4.x(r0, r4)
            r11.<init>(r0, r3)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p.jf2.d(boolean):java.lang.String");
    }

    public final String e() {
        int i = this.d;
        if (i <= this.b.length) {
            String strD = d(false);
            this.d = i;
            return strD;
        }
        throw new SpotifyUriParserException("End of Spotify uri reached unexpectedly: " + this.e, this.a);
    }

    public final String f() {
        return d(false);
    }

    public final void g() {
        if (this.d != 0) {
            throw new RuntimeException("skipSpotifyPrefix must be the first call to SpotifyUriParser");
        }
        String[] strArr = g;
        int i = 0;
        while (true) {
            String str = this.e;
            if (i >= 4) {
                if (!str.startsWith("spotify:")) {
                    throw new SpotifyUriParserException("Spotify uri doesn't start with a proper prefix: " + str, this.a);
                }
                this.d = 8;
                if (8 + 1 < str.length() && str.charAt(this.d) == '/' && str.charAt(this.d + 1) == '/') {
                    this.d += 2;
                    return;
                }
                return;
            }
            String str2 = strArr[i];
            if (str.startsWith(str2)) {
                this.d = str2.length();
                return;
            }
            i++;
        }
    }
}
