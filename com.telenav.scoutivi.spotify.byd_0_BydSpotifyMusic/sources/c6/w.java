package c6;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: MediaType.kt */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3870a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3871b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3872c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String[] f3873d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f3869g = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Pattern f3867e = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Pattern f3868f = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: compiled from: MediaType.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final w a(String toMediaType) {
            kotlin.jvm.internal.l.e(toMediaType, "$this$toMediaType");
            Matcher matcher = w.f3867e.matcher(toMediaType);
            if (!matcher.lookingAt()) {
                throw new IllegalArgumentException(("No subtype found for: \"" + toMediaType + '\"').toString());
            }
            String strGroup = matcher.group(1);
            kotlin.jvm.internal.l.d(strGroup, "typeSubtype.group(1)");
            Locale locale = Locale.US;
            kotlin.jvm.internal.l.d(locale, "Locale.US");
            Objects.requireNonNull(strGroup, "null cannot be cast to non-null type java.lang.String");
            String lowerCase = strGroup.toLowerCase(locale);
            kotlin.jvm.internal.l.d(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            String strGroup2 = matcher.group(2);
            kotlin.jvm.internal.l.d(strGroup2, "typeSubtype.group(2)");
            kotlin.jvm.internal.l.d(locale, "Locale.US");
            Objects.requireNonNull(strGroup2, "null cannot be cast to non-null type java.lang.String");
            String lowerCase2 = strGroup2.toLowerCase(locale);
            kotlin.jvm.internal.l.d(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
            ArrayList arrayList = new ArrayList();
            Matcher matcher2 = w.f3868f.matcher(toMediaType);
            int iEnd = matcher.end();
            while (iEnd < toMediaType.length()) {
                matcher2.region(iEnd, toMediaType.length());
                if (!matcher2.lookingAt()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Parameter is not formatted correctly: \"");
                    String strSubstring = toMediaType.substring(iEnd);
                    kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.String).substring(startIndex)");
                    sb.append(strSubstring);
                    sb.append("\" for: \"");
                    sb.append(toMediaType);
                    sb.append('\"');
                    throw new IllegalArgumentException(sb.toString().toString());
                }
                String strGroup3 = matcher2.group(1);
                if (strGroup3 == null) {
                    iEnd = matcher2.end();
                } else {
                    String strGroup4 = matcher2.group(2);
                    if (strGroup4 == null) {
                        strGroup4 = matcher2.group(3);
                    } else if (y5.p.A(strGroup4, "'", false, 2, null) && y5.p.n(strGroup4, "'", false, 2, null) && strGroup4.length() > 2) {
                        strGroup4 = strGroup4.substring(1, strGroup4.length() - 1);
                        kotlin.jvm.internal.l.d(strGroup4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    arrayList.add(strGroup3);
                    arrayList.add(strGroup4);
                    iEnd = matcher2.end();
                }
            }
            Object[] array = arrayList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            return new w(toMediaType, lowerCase, lowerCase2, (String[]) array, null);
        }

        public final w b(String toMediaTypeOrNull) {
            kotlin.jvm.internal.l.e(toMediaTypeOrNull, "$this$toMediaTypeOrNull");
            try {
                return a(toMediaTypeOrNull);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    private w(String str, String str2, String str3, String[] strArr) {
        this.f3870a = str;
        this.f3871b = str2;
        this.f3872c = str3;
        this.f3873d = strArr;
    }

    public static final w c(String str) {
        return f3869g.b(str);
    }

    public boolean equals(Object obj) {
        return (obj instanceof w) && kotlin.jvm.internal.l.a(((w) obj).f3870a, this.f3870a);
    }

    public int hashCode() {
        return this.f3870a.hashCode();
    }

    public String toString() {
        return this.f3870a;
    }

    public /* synthetic */ w(String str, String str2, String str3, String[] strArr, kotlin.jvm.internal.g gVar) {
        this(str, str2, str3, strArr);
    }
}
