package kotlin.text;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.FunctionReference;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Regex implements Serializable {
    public static final a Companion = new a(null);
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;

    /* JADX INFO: compiled from: Regex.kt */
    private static final class Serialized implements Serializable {
        public static final a Companion = new a(null);
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        /* JADX INFO: compiled from: Regex.kt */
        public static final class a {
            private a() {
            }

            public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
                this();
            }
        }

        public Serialized(String str, int i) {
            kotlin.jvm.internal.i.c(str, BYDAutoMultimediaDevice.PARAM_PATTERN);
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            Pattern patternCompile = Pattern.compile(this.pattern, this.flags);
            kotlin.jvm.internal.i.b(patternCompile, "Pattern.compile(pattern, flags)");
            return new Regex(patternCompile);
        }

        public final int getFlags() {
            return this.flags;
        }

        public final String getPattern() {
            return this.pattern;
        }
    }

    /* JADX INFO: compiled from: Regex.kt */
    public static final class a {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int b(int i) {
            return (i & 2) != 0 ? i | 64 : i;
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: kotlin.text.Regex$findAll$2, reason: invalid class name */
    /* JADX INFO: compiled from: Regex.kt */
    static final /* synthetic */ class AnonymousClass2 extends FunctionReference implements kotlin.jvm.b.l<i, i> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getName() {
            return "next";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final kotlin.reflect.d getOwner() {
            return kotlin.jvm.internal.j.b(i.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "next()Lkotlin/text/MatchResult;";
        }

        @Override // kotlin.jvm.b.l
        public final i invoke(i iVar) {
            kotlin.jvm.internal.i.c(iVar, "p1");
            return iVar.next();
        }
    }

    public Regex(Pattern pattern) {
        kotlin.jvm.internal.i.c(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    public static /* synthetic */ i find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    public static /* synthetic */ kotlin.p.d findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.findAll(charSequence, i);
    }

    public static /* synthetic */ List split$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }

    private final Object writeReplace() {
        String strPattern = this.nativePattern.pattern();
        kotlin.jvm.internal.i.b(strPattern, "nativePattern.pattern()");
        return new Serialized(strPattern, this.nativePattern.flags());
    }

    public final boolean containsMatchIn(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        return this.nativePattern.matcher(charSequence).find();
    }

    public final i find(CharSequence charSequence, int i) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        kotlin.jvm.internal.i.b(matcher, "nativePattern.matcher(input)");
        return j.f(matcher, i, charSequence);
    }

    public final kotlin.p.d<i> findAll(final CharSequence charSequence, final int i) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        return kotlin.p.h.a(new kotlin.jvm.b.a<i>() { // from class: kotlin.text.Regex.findAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.b.a
            public final i invoke() {
                return Regex.this.find(charSequence, i);
            }
        }, AnonymousClass2.INSTANCE);
    }

    public final Set<RegexOption> getOptions() {
        Set set = this._options;
        if (set != null) {
            return set;
        }
        final int iFlags = this.nativePattern.flags();
        EnumSet enumSetAllOf = EnumSet.allOf(RegexOption.class);
        kotlin.collections.p.u(enumSetAllOf, new kotlin.jvm.b.l<T, Boolean>() { // from class: kotlin.text.Regex$fromInt$$inlined$apply$lambda$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
                return Boolean.valueOf(invoke((Enum) obj));
            }

            /* JADX WARN: Incorrect types in method signature: (TT;)Z */
            /* JADX WARN: Multi-variable type inference failed */
            public final boolean invoke(Enum r3) {
                f fVar = (f) r3;
                return (iFlags & fVar.getMask()) == fVar.getValue();
            }
        });
        Set<RegexOption> setUnmodifiableSet = Collections.unmodifiableSet(enumSetAllOf);
        kotlin.jvm.internal.i.b(setUnmodifiableSet, "Collections.unmodifiable…mask == it.value }\n    })");
        this._options = setUnmodifiableSet;
        return setUnmodifiableSet;
    }

    public final String getPattern() {
        String strPattern = this.nativePattern.pattern();
        kotlin.jvm.internal.i.b(strPattern, "nativePattern.pattern()");
        return strPattern;
    }

    public final i matchEntire(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        kotlin.jvm.internal.i.b(matcher, "nativePattern.matcher(input)");
        return j.g(matcher, charSequence);
    }

    public final boolean matches(CharSequence charSequence) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final String replace(CharSequence charSequence, String str) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        kotlin.jvm.internal.i.c(str, "replacement");
        String strReplaceAll = this.nativePattern.matcher(charSequence).replaceAll(str);
        kotlin.jvm.internal.i.b(strReplaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return strReplaceAll;
    }

    public final String replaceFirst(CharSequence charSequence, String str) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        kotlin.jvm.internal.i.c(str, "replacement");
        String strReplaceFirst = this.nativePattern.matcher(charSequence).replaceFirst(str);
        kotlin.jvm.internal.i.b(strReplaceFirst, "nativePattern.matcher(in…replaceFirst(replacement)");
        return strReplaceFirst;
    }

    public final List<String> split(CharSequence charSequence, int i) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        int iEnd = 0;
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + '.').toString());
        }
        Matcher matcher = this.nativePattern.matcher(charSequence);
        if (!matcher.find() || i == 1) {
            return kotlin.collections.j.b(charSequence.toString());
        }
        ArrayList arrayList = new ArrayList(i > 0 ? kotlin.o.f.c(i, 10) : 10);
        int i2 = i - 1;
        do {
            arrayList.add(charSequence.subSequence(iEnd, matcher.start()).toString());
            iEnd = matcher.end();
            if (i2 >= 0 && arrayList.size() == i2) {
                break;
            }
        } while (matcher.find());
        arrayList.add(charSequence.subSequence(iEnd, charSequence.length()).toString());
        return arrayList;
    }

    public final Pattern toPattern() {
        return this.nativePattern;
    }

    public String toString() {
        String string = this.nativePattern.toString();
        kotlin.jvm.internal.i.b(string, "nativePattern.toString()");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str) {
        kotlin.jvm.internal.i.c(str, BYDAutoMultimediaDevice.PARAM_PATTERN);
        Pattern patternCompile = Pattern.compile(str);
        kotlin.jvm.internal.i.b(patternCompile, "Pattern.compile(pattern)");
        this(patternCompile);
    }

    public final String replace(CharSequence charSequence, kotlin.jvm.b.l<? super i, ? extends CharSequence> lVar) {
        kotlin.jvm.internal.i.c(charSequence, "input");
        kotlin.jvm.internal.i.c(lVar, "transform");
        int iIntValue = 0;
        i iVarFind$default = find$default(this, charSequence, 0, 2, null);
        if (iVarFind$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            sb.append(charSequence, iIntValue, iVarFind$default.a().g().intValue());
            sb.append(lVar.invoke(iVarFind$default));
            iIntValue = iVarFind$default.a().f().intValue() + 1;
            iVarFind$default = iVarFind$default.next();
            if (iIntValue >= length) {
                break;
            }
        } while (iVarFind$default != null);
        if (iIntValue < length) {
            sb.append(charSequence, iIntValue, length);
        }
        String string = sb.toString();
        kotlin.jvm.internal.i.b(string, "sb.toString()");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, RegexOption regexOption) {
        kotlin.jvm.internal.i.c(str, BYDAutoMultimediaDevice.PARAM_PATTERN);
        kotlin.jvm.internal.i.c(regexOption, "option");
        Pattern patternCompile = Pattern.compile(str, Companion.b(regexOption.getValue()));
        kotlin.jvm.internal.i.b(patternCompile, "Pattern.compile(pattern,…nicodeCase(option.value))");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(String str, Set<? extends RegexOption> set) {
        kotlin.jvm.internal.i.c(str, BYDAutoMultimediaDevice.PARAM_PATTERN);
        kotlin.jvm.internal.i.c(set, "options");
        Pattern patternCompile = Pattern.compile(str, Companion.b(j.j(set)));
        kotlin.jvm.internal.i.b(patternCompile, "Pattern.compile(pattern,…odeCase(options.toInt()))");
        this(patternCompile);
    }
}
