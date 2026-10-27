package p;

import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.common.uri.SpotifyUriParserException;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import com.spotify.connectivity.flags.LoadedFlagsKt;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class if2 implements Comparable {
    public static final HashSet v = new HashSet(Arrays.asList("albums", "appears-on", "appears_on", "playlists", "related", "releases", "singles", "popular-tracks", "play_button"));
    public final boolean c;
    public final SpotifyUri$Kind e;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f134p;
    public final SpotifyUri$Kind q;
    public final List r;
    public final String s;
    public final String t;
    public final String u;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03cf  */
    /* JADX WARN: Type inference failed for: r1v229 */
    /* JADX WARN: Type inference failed for: r1v347, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v361 */
    /* JADX WARN: Type inference failed for: r1v362 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public if2(java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 4374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.if2.<init>(java.lang.String):void");
    }

    public static String b(String str) {
        try {
            String strEncode = URLEncoder.encode(str, "utf-8");
            return strEncode.indexOf(42) != -1 ? strEncode.replace("*", "%2A") : strEncode;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String c(String str) {
        try {
            String strEncode = URLEncoder.encode(str, "utf-8");
            if (strEncode.indexOf(42) != -1) {
                strEncode = strEncode.replace("*", "%2A");
            }
            return strEncode.contains("%7E") ? strEncode.replace("%7E", "~") : strEncode;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static int d(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:173:0x05df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.StringBuilder a() {
        /*
            Method dump skipped, instruction units count: 2198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.if2.a():java.lang.StringBuilder");
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0068, code lost:
    
        if (r0 != 0) goto L10;
     */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int compareTo(java.lang.Object r5) {
        /*
            r4 = this;
            p.if2 r5 = (p.if2) r5
            com.spotify.common.uri.SpotifyUri$Kind r0 = r5.e
            com.spotify.common.uri.SpotifyUri$Kind r1 = r4.e
            if (r1 == r0) goto Le
            int r5 = r1.compareTo(r0)
            goto L73
        Le:
            com.spotify.common.uri.SpotifyUri$Kind r0 = r4.q
            com.spotify.common.uri.SpotifyUri$Kind r2 = r5.q
            if (r0 == r2) goto L19
            int r5 = r0.compareTo(r2)
            goto L73
        L19:
            java.lang.String r0 = r4.k
            java.lang.String r2 = r5.k
            int r0 = d(r0, r2)
            if (r0 == 0) goto L25
        L23:
            r5 = r0
            goto L73
        L25:
            java.lang.String r0 = r4.l
            java.lang.String r2 = r5.l
            int r0 = d(r0, r2)
            if (r0 == 0) goto L30
            goto L23
        L30:
            java.lang.String r0 = r4.m
            java.lang.String r2 = r5.m
            int r0 = d(r0, r2)
            if (r0 == 0) goto L3b
            goto L23
        L3b:
            java.lang.String r0 = r4.n
            java.lang.String r2 = r5.n
            int r0 = d(r0, r2)
            if (r0 == 0) goto L46
            goto L23
        L46:
            com.spotify.common.uri.SpotifyUri$Kind r0 = com.spotify.common.uri.SpotifyUri$Kind.ENTITY_SET
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L6b
            java.lang.String r0 = ":"
            r1 = 0
            java.util.List r2 = r4.r
            if (r2 != 0) goto L57
            r2 = r1
            goto L5b
        L57:
            java.lang.String r2 = java.lang.String.join(r0, r2)
        L5b:
            java.util.List r3 = r5.r
            if (r3 != 0) goto L60
            goto L64
        L60:
            java.lang.String r1 = java.lang.String.join(r0, r3)
        L64:
            int r0 = d(r2, r1)
            if (r0 == 0) goto L6b
            goto L23
        L6b:
            long r0 = r4.o
            long r2 = r5.o
            int r5 = java.lang.Long.compare(r0, r2)
        L73:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p.if2.compareTo(java.lang.Object):int");
    }

    public final String e() {
        String str = "https://open.spotify.com" + a().toString().replaceFirst("^spotify", "").replace(":", "/") + ((String) Optional.ofNullable(this.u).orElse(""));
        int iOrdinal = this.e.ordinal();
        if (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3 || iOrdinal == 5 || iOrdinal == 6 || iOrdinal == 7 || iOrdinal == 21 || iOrdinal == 22 || iOrdinal == 27 || iOrdinal == 28 || iOrdinal == 59 || iOrdinal == 60 || iOrdinal == 70 || iOrdinal == 71 || iOrdinal == 80 || iOrdinal == 81) {
            return str;
        }
        switch (iOrdinal) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 19:
            case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
            case 84:
            case 88:
            case 107:
            case 113:
            case 114:
            case 115:
            case 120:
            case 121:
            case 123:
            case 124:
            case 126:
            case 127:
            case 128:
            case 132:
            case 135:
            case 136:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
                return str;
            case 12:
                return str.replaceFirst("playlists", "collection/playlists");
            case 64:
                return str.replaceFirst("original-content", "original");
            case 90:
                return str.replaceFirst("home", "browse/featured");
            default:
                switch (iOrdinal) {
                    case 45:
                    case 46:
                    case 47:
                        return str;
                    default:
                        switch (iOrdinal) {
                            case 73:
                            case 74:
                            case 75:
                            case 76:
                            case 77:
                                return str;
                            default:
                                switch (iOrdinal) {
                                    case 94:
                                    case 95:
                                    case 96:
                                    case 97:
                                    case 98:
                                    case 99:
                                    case LoadedFlagsKt.INITIAL_CAPACITY /* 100 */:
                                        return str;
                                    default:
                                        throw new IllegalArgumentException(String.format("Unable to turn uri into string: %s", str));
                                }
                        }
                }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || if2.class != obj.getClass()) {
            return false;
        }
        if2 if2Var = (if2) obj;
        if (this.o != if2Var.o || !Objects.equals(this.l, if2Var.l)) {
            return false;
        }
        SpotifyUri$Kind spotifyUri$Kind = if2Var.e;
        SpotifyUri$Kind spotifyUri$Kind2 = this.e;
        if (spotifyUri$Kind2 != spotifyUri$Kind || this.q != if2Var.q || !Objects.equals(this.n, if2Var.n) || !Objects.equals(this.m, if2Var.m)) {
            return false;
        }
        if (SpotifyUri$Kind.ENTITY_SET.equals(spotifyUri$Kind2)) {
            List list = this.r;
            List list2 = if2Var.r;
            if (!Objects.equals(list, list2) && ((list != null && !list.isEmpty()) || (list2 != null && !list2.isEmpty()))) {
                return false;
            }
        }
        return Objects.equals(this.k, if2Var.k);
    }

    public final void f(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && !((cCharAt >= '0' && cCharAt <= '9') || cCharAt == '-' || cCharAt == '/' || cCharAt == '.' || cCharAt == ':'))) {
                throw new SpotifyUriParserException("Invalid Spotify application id: ".concat(str), this.c);
            }
        }
    }

    public final int hashCode() {
        SpotifyUri$Kind spotifyUri$Kind = this.e;
        int iHashCode = (this.q.hashCode() + (spotifyUri$Kind.hashCode() * 31)) * 31;
        String str = this.k;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.l;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.m;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.n;
        int iHashCode5 = iHashCode4 + (str4 != null ? str4.hashCode() : 0);
        if (SpotifyUri$Kind.ENTITY_SET.equals(spotifyUri$Kind)) {
            int i = iHashCode5 * 31;
            List list = this.r;
            iHashCode5 = i + (list != null ? list.hashCode() : 0);
        }
        long j = this.o;
        return (iHashCode5 * 31) + ((int) ((j >>> 32) ^ j));
    }

    public final String toString() {
        StringBuilder sbA = a();
        String str = this.u;
        if (str != null) {
            sbA.append(str);
        }
        return sbA.toString();
    }

    public if2(SpotifyUri$Kind spotifyUri$Kind, String str, String str2) {
        SpotifyUri$Kind spotifyUri$Kind2 = SpotifyUri$Kind.UNKNOWN;
        ArrayList arrayList = new ArrayList();
        this.c = true;
        this.e = spotifyUri$Kind;
        this.l = str;
        this.k = str2;
        this.m = null;
        this.n = null;
        this.o = -1L;
        this.q = spotifyUri$Kind2;
        this.r = arrayList;
        this.s = null;
        this.t = null;
        this.u = null;
    }
}
