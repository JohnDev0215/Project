package p;

import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class pi {
    public Object a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;

    public final String a() {
        StringBuilder sb = new StringBuilder();
        String str = (String) this.e;
        if (str != null) {
            if (str.length() <= 0) {
                str = null;
            }
            if (str != null) {
                if (kotlin.text.b.i0(str, "*")) {
                    sb.append(str);
                } else {
                    String str2 = String.format(Locale.US, "\"%s\"", Arrays.copyOf(new Object[]{str}, 1));
                    v03.i(str2, "format(locale, format, *args)");
                    sb.append(str2);
                }
            }
        }
        String str3 = (String) this.c;
        if (str3 != null && str3.length() != 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            String str4 = String.format(Locale.US, "artist:\"%s\"", Arrays.copyOf(new Object[]{(String) this.c}, 1));
            v03.i(str4, "format(locale, format, *args)");
            sb.append(str4);
        }
        String str5 = (String) this.a;
        if (str5 != null && str5.length() != 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            String str6 = String.format(Locale.US, "album:\"%s\"", Arrays.copyOf(new Object[]{(String) this.a}, 1));
            v03.i(str6, "format(locale, format, *args)");
            sb.append(str6);
        }
        String str7 = (String) this.d;
        if (str7 != null && str7.length() != 0) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            String str8 = String.format(Locale.US, "genre:\"%s\"", Arrays.copyOf(new Object[]{(String) this.d}, 1));
            v03.i(str8, "format(locale, format, *args)");
            sb.append(str8);
        }
        String string = sb.toString();
        v03.i(string, "result.toString()");
        return string;
    }

    public final nh b() {
        String strH = ((Long) this.a) == null ? " timestamp" : "";
        if (((String) this.b) == null) {
            strH = strH.concat(" type");
        }
        if (((z30) this.c) == null) {
            strH = u4.h(strH, " app");
        }
        if (((a40) this.d) == null) {
            strH = u4.h(strH, " device");
        }
        if (strH.isEmpty()) {
            return new nh(((Long) this.a).longValue(), (String) this.b, (z30) this.c, (a40) this.d, (b40) this.e);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final rh c() {
        String strH = ((String) this.b) == null ? " type" : "";
        if (((z31) this.c) == null) {
            strH = strH.concat(" frames");
        }
        if (((Integer) this.e) == null) {
            strH = u4.h(strH, " overflowCount");
        }
        if (strH.isEmpty()) {
            return new rh((String) this.b, (String) this.a, (z31) this.c, (u30) this.d, ((Integer) this.e).intValue());
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final uh d() {
        String strH = ((Long) this.a) == null ? " pc" : "";
        if (((String) this.b) == null) {
            strH = strH.concat(" symbol");
        }
        if (((Long) this.d) == null) {
            strH = u4.h(strH, " offset");
        }
        if (((Integer) this.e) == null) {
            strH = u4.h(strH, " importance");
        }
        if (strH.isEmpty()) {
            return new uh(((Long) this.a).longValue(), (String) this.b, (String) this.c, ((Long) this.d).longValue(), ((Integer) this.e).intValue());
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }
}
