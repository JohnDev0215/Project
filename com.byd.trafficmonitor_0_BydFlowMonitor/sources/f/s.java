package f;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: MediaType.java */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f5375a = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f5376b = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5377c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f5378d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f5379e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f5380f;

    public s(String str, String str2, String str3, String str4) {
        this.f5377c = str;
        this.f5378d = str2;
        this.f5379e = str3;
        this.f5380f = str4;
    }

    public static s c(String str) {
        Matcher matcher = f5375a.matcher(str);
        if (!matcher.lookingAt()) {
            return null;
        }
        String strGroup = matcher.group(1);
        Locale locale = Locale.US;
        String lowerCase = strGroup.toLowerCase(locale);
        String lowerCase2 = matcher.group(2).toLowerCase(locale);
        Matcher matcher2 = f5376b.matcher(str);
        String str2 = null;
        for (int iEnd = matcher.end(); iEnd < str.length(); iEnd = matcher2.end()) {
            matcher2.region(iEnd, str.length());
            if (!matcher2.lookingAt()) {
                return null;
            }
            String strGroup2 = matcher2.group(1);
            if (strGroup2 != null && strGroup2.equalsIgnoreCase("charset")) {
                String strGroup3 = matcher2.group(2);
                if (strGroup3 == null) {
                    strGroup3 = matcher2.group(3);
                } else if (strGroup3.startsWith("'") && strGroup3.endsWith("'") && strGroup3.length() > 2) {
                    strGroup3 = strGroup3.substring(1, strGroup3.length() - 1);
                }
                if (str2 != null && !strGroup3.equalsIgnoreCase(str2)) {
                    throw new IllegalArgumentException("Multiple different charsets: " + str);
                }
                str2 = strGroup3;
            }
        }
        return new s(str, lowerCase, lowerCase2, str2);
    }

    public Charset a() {
        String str = this.f5380f;
        if (str != null) {
            return Charset.forName(str);
        }
        return null;
    }

    public Charset b(Charset charset) {
        String str = this.f5380f;
        return str != null ? Charset.forName(str) : charset;
    }

    public boolean equals(Object obj) {
        return (obj instanceof s) && ((s) obj).f5377c.equals(this.f5377c);
    }

    public int hashCode() {
        return this.f5377c.hashCode();
    }

    public String toString() {
        return this.f5377c;
    }
}
