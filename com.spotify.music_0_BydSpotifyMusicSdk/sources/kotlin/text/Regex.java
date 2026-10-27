package kotlin.text;

import java.io.Serializable;
import java.util.Set;
import java.util.regex.Pattern;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Regex implements Serializable {
    private Set<Object> _options;
    private final Pattern nativePattern;

    public static final class Serialized implements Serializable {
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        public Serialized(String str, int i) {
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            Pattern patternCompile = Pattern.compile(this.pattern, this.flags);
            v03.i(patternCompile, "compile(pattern, flags)");
            return new Regex(patternCompile);
        }
    }

    public Regex(Pattern pattern) {
        this.nativePattern = pattern;
    }

    private final Object writeReplace() {
        String strPattern = this.nativePattern.pattern();
        v03.i(strPattern, "nativePattern.pattern()");
        return new Serialized(strPattern, this.nativePattern.flags());
    }

    public final boolean a(CharSequence charSequence) {
        v03.j(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final String b(CharSequence charSequence, String str) {
        v03.j(charSequence, "input");
        String strReplaceAll = this.nativePattern.matcher(charSequence).replaceAll(str);
        v03.i(strReplaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return strReplaceAll;
    }

    public final String toString() {
        String string = this.nativePattern.toString();
        v03.i(string, "nativePattern.toString()");
        return string;
    }

    public Regex(String str) {
        Pattern patternCompile = Pattern.compile(str);
        v03.i(patternCompile, "compile(pattern)");
        this.nativePattern = patternCompile;
    }
}
