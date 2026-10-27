package y5;

import java.io.Serializable;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Serializable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f10008g = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Pattern f10009e;

    /* JADX INFO: compiled from: Regex.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: Regex.kt */
    private static final class b implements Serializable {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f10010h = new a(null);
        private static final long serialVersionUID = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final String f10011e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final int f10012g;

        /* JADX INFO: compiled from: Regex.kt */
        public static final class a {
            private a() {
            }

            public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
                this();
            }
        }

        public b(String pattern, int i7) {
            kotlin.jvm.internal.l.e(pattern, "pattern");
            this.f10011e = pattern;
            this.f10012g = i7;
        }

        private final Object readResolve() {
            Pattern patternCompile = Pattern.compile(this.f10011e, this.f10012g);
            kotlin.jvm.internal.l.d(patternCompile, "compile(pattern, flags)");
            return new f(patternCompile);
        }
    }

    public f(Pattern nativePattern) {
        kotlin.jvm.internal.l.e(nativePattern, "nativePattern");
        this.f10009e = nativePattern;
    }

    private final Object writeReplace() {
        String strPattern = this.f10009e.pattern();
        kotlin.jvm.internal.l.d(strPattern, "nativePattern.pattern()");
        return new b(strPattern, this.f10009e.flags());
    }

    public final boolean a(CharSequence input) {
        kotlin.jvm.internal.l.e(input, "input");
        return this.f10009e.matcher(input).matches();
    }

    public final String b(CharSequence input, String replacement) {
        kotlin.jvm.internal.l.e(input, "input");
        kotlin.jvm.internal.l.e(replacement, "replacement");
        String strReplaceAll = this.f10009e.matcher(input).replaceAll(replacement);
        kotlin.jvm.internal.l.d(strReplaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return strReplaceAll;
    }

    public String toString() {
        String string = this.f10009e.toString();
        kotlin.jvm.internal.l.d(string, "nativePattern.toString()");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public f(String pattern) {
        kotlin.jvm.internal.l.e(pattern, "pattern");
        Pattern patternCompile = Pattern.compile(pattern);
        kotlin.jvm.internal.l.d(patternCompile, "compile(pattern)");
        this(patternCompile);
    }
}
