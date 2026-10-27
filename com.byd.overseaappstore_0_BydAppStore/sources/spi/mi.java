package spi;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class mi {
    public final String a;

    @NotNull
    public final String b;
    public final String[] c;
    public static final a f = new a(null);
    public static final Pattern d = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    public static final Pattern e = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @JvmName(name = "get")
        @NotNull
        public final mi a(@NotNull String toMediaType) {
            Intrinsics.checkNotNullParameter(toMediaType, "$this$toMediaType");
            Matcher matcher = mi.d.matcher(toMediaType);
            if (!matcher.lookingAt()) {
                throw new IllegalArgumentException(("No subtype found for: \"" + toMediaType + Typography.quote).toString());
            }
            String strGroup = matcher.group(1);
            Intrinsics.checkNotNullExpressionValue(strGroup, "typeSubtype.group(1)");
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            Objects.requireNonNull(strGroup, "null cannot be cast to non-null type java.lang.String");
            String lowerCase = strGroup.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            String strGroup2 = matcher.group(2);
            Intrinsics.checkNotNullExpressionValue(strGroup2, "typeSubtype.group(2)");
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.US");
            Objects.requireNonNull(strGroup2, "null cannot be cast to non-null type java.lang.String");
            String lowerCase2 = strGroup2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
            ArrayList arrayList = new ArrayList();
            Matcher matcher2 = mi.e.matcher(toMediaType);
            for (int iEnd = matcher.end(); iEnd < toMediaType.length(); iEnd = matcher2.end()) {
                matcher2.region(iEnd, toMediaType.length());
                if (!matcher2.lookingAt()) {
                    StringBuilder sbK = e.k("Parameter is not formatted correctly: \"");
                    String strSubstring = toMediaType.substring(iEnd);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
                    sbK.append(strSubstring);
                    sbK.append("\" for: \"");
                    sbK.append(toMediaType);
                    sbK.append(Typography.quote);
                    throw new IllegalArgumentException(sbK.toString().toString());
                }
                String strGroup3 = matcher2.group(1);
                if (strGroup3 != null) {
                    String strGroup4 = matcher2.group(2);
                    if (strGroup4 == null) {
                        strGroup4 = matcher2.group(3);
                    } else if (StringsKt.x(strGroup4, "'") && StringsKt.g(strGroup4, "'") && strGroup4.length() > 2) {
                        strGroup4 = strGroup4.substring(1, strGroup4.length() - 1);
                        Intrinsics.checkNotNullExpressionValue(strGroup4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                    arrayList.add(strGroup3);
                    arrayList.add(strGroup4);
                }
            }
            Object[] array = arrayList.toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            return new mi(toMediaType, lowerCase, lowerCase2, (String[]) array, null);
        }

        @JvmStatic
        @JvmName(name = "parse")
        @Nullable
        public final mi b(@NotNull String toMediaTypeOrNull) {
            Intrinsics.checkNotNullParameter(toMediaTypeOrNull, "$this$toMediaTypeOrNull");
            try {
                return a(toMediaTypeOrNull);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    private mi(String str, String str2, String str3, String[] strArr) {
        this.a = str;
        this.b = str2;
        this.c = strArr;
    }

    public /* synthetic */ mi(String str, String str2, String str3, String[] strArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, strArr);
    }

    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    public static final mi b(@NotNull String str) {
        return f.a(str);
    }

    @JvmOverloads
    @Nullable
    public final Charset a(@Nullable Charset charset) {
        String str;
        Intrinsics.checkNotNullParameter("charset", "name");
        IntProgression intProgressionStep = RangesKt.step(ArraysKt.getIndices(this.c), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if (step < 0 ? first < last : first > last) {
            str = null;
            break;
        }
        while (!StringsKt.h(this.c[first], "charset")) {
            if (first == last) {
                str = null;
                break;
            }
            first += step;
        }
        str = this.c[first + 1];
        if (str == null) {
            return charset;
        }
        try {
            return Charset.forName(str);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    public final boolean equals(@Nullable Object obj) {
        return (obj instanceof mi) && Intrinsics.areEqual(((mi) obj).a, this.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @NotNull
    public final String toString() {
        return this.a;
    }
}
