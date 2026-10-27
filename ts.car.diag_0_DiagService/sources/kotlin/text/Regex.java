package kotlin.text;

import com.byd.mediacenter.api.media.MediaControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.collections.C0123n;
import kotlin.jvm.internal.FunctionReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 82\u00020\u0001:\u00029:B\u0011\b\u0016\u0012\u0006\u00100\u001a\u00020#¢\u0006\u0004\b1\u00102B\u0011\b\u0016\u0012\u0006\u00100\u001a\u00020\u0015¢\u0006\u0004\b1\u00103B\u0019\b\u0016\u0012\u0006\u00100\u001a\u00020\u0015\u0012\u0006\u00105\u001a\u000204¢\u0006\u0004\b1\u00106B\u0019\b\u0016\u0012\u0006\u00100\u001a\u00020\u0015\u0012\u0006\u00105\u001a\u00020&¢\u0006\u0004\b1\u00107J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\tJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u0017\u0010\u001bJ\u001d\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u001c\u0010\u0018J\u001f\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u000b¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0015H\u0016¢\u0006\u0004\b!\u0010\"J\r\u0010$\u001a\u00020#¢\u0006\u0004\b$\u0010%R\u0013\u0010)\u001a\u00020&8\u0006@\u0000¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0013\u0010+\u001a\u00020\u00158\u0006@\u0000¢\u0006\u0006\u001a\u0004\b*\u0010\"R\u0016\u0010,\u001a\u00020#8\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020&8\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/¨\u0006;"}, d2 = {"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/Any;", "writeReplace", "()Ljava/lang/Object;", "Lkotlin/CharSequence;", "input", "Lkotlin/Boolean;", "matches", "(Ljava/lang/CharSequence;)Z", "containsMatchIn", "Lkotlin/Int;", "startIndex", "Lkotlin/text/f;", "find", "(Ljava/lang/CharSequence;I)Lkotlin/text/f;", "Lkotlin/sequences/b;", "findAll", "(Ljava/lang/CharSequence;I)Lkotlin/sequences/b;", "matchEntire", "(Ljava/lang/CharSequence;)Lkotlin/text/f;", "Lkotlin/String;", "replacement", "replace", "(Ljava/lang/CharSequence;Ljava/lang/String;)Ljava/lang/String;", "Lkotlin/Function1;", "transform", "(Ljava/lang/CharSequence;Lkotlin/jvm/b/l;)Ljava/lang/String;", "replaceFirst", "limit", "Ljava/util/List;", "split", "(Ljava/lang/CharSequence;I)Ljava/util/List;", "toString", "()Ljava/lang/String;", "Ljava/util/regex/Pattern;", "toPattern", "()Ljava/util/regex/Pattern;", "Ljava/util/Set;", "getOptions", "()Ljava/util/Set;", "options", "getPattern", MediaControl.PARAM_PATTERN, "nativePattern", "Ljava/util/regex/Pattern;", "_options", "Ljava/util/Set;", "p0", "<init>", "(Ljava/util/regex/Pattern;)V", "(Ljava/lang/String;)V", "Lkotlin/text/RegexOption;", "p1", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "(Ljava/lang/String;Ljava/util/Set;)V", "Companion", "Serialized", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class Regex implements Serializable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Set<? extends RegexOption> _options;
    private final Pattern nativePattern;

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0002\u0014\u0015B\u0019\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u0019\u0010\u0006\u001a\u00020\u00058\u0006@\u0000¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0019\u0010\u000b\u001a\u00020\n8\u0006@\u0000¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/Any;", "readResolve", "()Ljava/lang/Object;", "Lkotlin/Int;", "flags", "I", "getFlags", "()I", "Lkotlin/String;", MediaControl.PARAM_PATTERN, "Ljava/lang/String;", "getPattern", "()Ljava/lang/String;", "p0", "p1", "<init>", "(Ljava/lang/String;I)V", "Companion", "a", "Serialized", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    private static final class Serialized implements Serializable {
        private static final long serialVersionUID = 0;
        private final int flags;

        @NotNull
        private final String pattern;

        public Serialized(@NotNull String str, int i) {
            kotlin.jvm.internal.q.c(str, MediaControl.PARAM_PATTERN);
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            Pattern patternCompile = Pattern.compile(this.pattern, this.flags);
            kotlin.jvm.internal.q.b(patternCompile, "Pattern.compile(pattern, flags)");
            return new Regex(patternCompile);
        }

        public final int getFlags() {
            return this.flags;
        }

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }
    }

    /* JADX INFO: renamed from: kotlin.text.Regex$a, reason: from kotlin metadata */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u0000:\u0001\nB\t\b\u0012¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u0017\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, d2 = {"Lkotlin/text/Regex$a;", "Lkotlin/Int;", "flags", "b", "(I)I", "<init>", "()V", "Lkotlin/jvm/internal/o;", "p0", "(Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int b(int flags) {
            return (flags & 2) != 0 ? flags | 64 : flags;
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.o oVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: kotlin.text.Regex$findAll$2, reason: invalid class name */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/text/MatchResult;", "p1", "invoke"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    static final /* synthetic */ class AnonymousClass2 extends FunctionReference implements kotlin.jvm.b.l<f, f> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getName() {
            return "next";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final kotlin.reflect.e getOwner() {
            return kotlin.jvm.internal.s.b(f.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "next()Lkotlin/text/MatchResult;";
        }

        @Override // kotlin.jvm.b.l
        @Nullable
        public final f invoke(@NotNull f fVar) {
            kotlin.jvm.internal.q.c(fVar, "p1");
            return fVar.next();
        }
    }

    @PublishedApi
    public Regex(@NotNull Pattern pattern) {
        kotlin.jvm.internal.q.c(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    public static /* synthetic */ f find$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.find(charSequence, i);
    }

    public static /* synthetic */ kotlin.sequences.b findAll$default(Regex regex, CharSequence charSequence, int i, int i2, Object obj) {
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
        kotlin.jvm.internal.q.b(strPattern, "nativePattern.pattern()");
        return new Serialized(strPattern, this.nativePattern.flags());
    }

    public final boolean containsMatchIn(@NotNull CharSequence input) {
        kotlin.jvm.internal.q.c(input, "input");
        return this.nativePattern.matcher(input).find();
    }

    @Nullable
    public final f find(@NotNull CharSequence input, int startIndex) {
        kotlin.jvm.internal.q.c(input, "input");
        Matcher matcher = this.nativePattern.matcher(input);
        kotlin.jvm.internal.q.b(matcher, "nativePattern.matcher(input)");
        return h.e(matcher, startIndex, input);
    }

    @NotNull
    public final kotlin.sequences.b<f> findAll(@NotNull final CharSequence input, final int startIndex) {
        kotlin.jvm.internal.q.c(input, "input");
        return kotlin.sequences.g.a(new kotlin.jvm.b.a<f>() { // from class: kotlin.text.Regex.findAll.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.b.a
            @Nullable
            public final f invoke() {
                return Regex.this.find(input, startIndex);
            }
        }, AnonymousClass2.INSTANCE);
    }

    @NotNull
    public final Set<RegexOption> getOptions() {
        Set set = this._options;
        if (set != null) {
            return set;
        }
        final int iFlags = this.nativePattern.flags();
        EnumSet enumSetAllOf = EnumSet.allOf(RegexOption.class);
        kotlin.collections.t.d(enumSetAllOf, new kotlin.jvm.b.l<T, Boolean>() { // from class: kotlin.text.Regex$fromInt$$inlined$apply$lambda$1
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
            public final boolean invoke(Enum r2) {
                d dVar = (d) r2;
                return (iFlags & dVar.getMask()) == dVar.getValue();
            }
        });
        Set<RegexOption> setUnmodifiableSet = Collections.unmodifiableSet(enumSetAllOf);
        kotlin.jvm.internal.q.b(setUnmodifiableSet, "Collections.unmodifiable…mask == it.value }\n    })");
        this._options = setUnmodifiableSet;
        return setUnmodifiableSet;
    }

    @NotNull
    public final String getPattern() {
        String strPattern = this.nativePattern.pattern();
        kotlin.jvm.internal.q.b(strPattern, "nativePattern.pattern()");
        return strPattern;
    }

    @Nullable
    public final f matchEntire(@NotNull CharSequence input) {
        kotlin.jvm.internal.q.c(input, "input");
        Matcher matcher = this.nativePattern.matcher(input);
        kotlin.jvm.internal.q.b(matcher, "nativePattern.matcher(input)");
        return h.f(matcher, input);
    }

    public final boolean matches(@NotNull CharSequence input) {
        kotlin.jvm.internal.q.c(input, "input");
        return this.nativePattern.matcher(input).matches();
    }

    @NotNull
    public final String replace(@NotNull CharSequence input, @NotNull String replacement) {
        kotlin.jvm.internal.q.c(input, "input");
        kotlin.jvm.internal.q.c(replacement, "replacement");
        String strReplaceAll = this.nativePattern.matcher(input).replaceAll(replacement);
        kotlin.jvm.internal.q.b(strReplaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return strReplaceAll;
    }

    @NotNull
    public final String replaceFirst(@NotNull CharSequence input, @NotNull String replacement) {
        kotlin.jvm.internal.q.c(input, "input");
        kotlin.jvm.internal.q.c(replacement, "replacement");
        String strReplaceFirst = this.nativePattern.matcher(input).replaceFirst(replacement);
        kotlin.jvm.internal.q.b(strReplaceFirst, "nativePattern.matcher(in…replaceFirst(replacement)");
        return strReplaceFirst;
    }

    @NotNull
    public final List<String> split(@NotNull CharSequence input, int limit) {
        kotlin.jvm.internal.q.c(input, "input");
        int iEnd = 0;
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
        }
        Matcher matcher = this.nativePattern.matcher(input);
        if (!matcher.find() || limit == 1) {
            return C0123n.a(input.toString());
        }
        ArrayList arrayList = new ArrayList(limit > 0 ? kotlin.t.g.b(limit, 10) : 10);
        int i = limit - 1;
        do {
            arrayList.add(input.subSequence(iEnd, matcher.start()).toString());
            iEnd = matcher.end();
            if (i >= 0 && arrayList.size() == i) {
                break;
            }
        } while (matcher.find());
        arrayList.add(input.subSequence(iEnd, input.length()).toString());
        return arrayList;
    }

    @NotNull
    /* JADX INFO: renamed from: toPattern, reason: from getter */
    public final Pattern getNativePattern() {
        return this.nativePattern;
    }

    @NotNull
    public String toString() {
        String string = this.nativePattern.toString();
        kotlin.jvm.internal.q.b(string, "nativePattern.toString()");
        return string;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(@NotNull String str) {
        kotlin.jvm.internal.q.c(str, MediaControl.PARAM_PATTERN);
        Pattern patternCompile = Pattern.compile(str);
        kotlin.jvm.internal.q.b(patternCompile, "Pattern.compile(pattern)");
        this(patternCompile);
    }

    @NotNull
    public final String replace(@NotNull CharSequence input, @NotNull kotlin.jvm.b.l<? super f, ? extends CharSequence> transform) {
        kotlin.jvm.internal.q.c(input, "input");
        kotlin.jvm.internal.q.c(transform, "transform");
        int iIntValue = 0;
        f fVarFind$default = find$default(this, input, 0, 2, null);
        if (fVarFind$default == null) {
            return input.toString();
        }
        int length = input.length();
        StringBuilder sb = new StringBuilder(length);
        while (fVarFind$default != null) {
            sb.append(input, iIntValue, fVarFind$default.a().g().intValue());
            sb.append(transform.invoke(fVarFind$default));
            iIntValue = fVarFind$default.a().f().intValue() + 1;
            fVarFind$default = fVarFind$default.next();
            if (iIntValue >= length || fVarFind$default == null) {
                if (iIntValue < length) {
                    sb.append(input, iIntValue, length);
                }
                String string = sb.toString();
                kotlin.jvm.internal.q.b(string, "sb.toString()");
                return string;
            }
        }
        kotlin.jvm.internal.q.h();
        throw null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(@NotNull String str, @NotNull RegexOption regexOption) {
        kotlin.jvm.internal.q.c(str, MediaControl.PARAM_PATTERN);
        kotlin.jvm.internal.q.c(regexOption, "option");
        Pattern patternCompile = Pattern.compile(str, INSTANCE.b(regexOption.getValue()));
        kotlin.jvm.internal.q.b(patternCompile, "Pattern.compile(pattern,…nicodeCase(option.value))");
        this(patternCompile);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Regex(@NotNull String str, @NotNull Set<? extends RegexOption> set) {
        kotlin.jvm.internal.q.c(str, MediaControl.PARAM_PATTERN);
        kotlin.jvm.internal.q.c(set, "options");
        Pattern patternCompile = Pattern.compile(str, INSTANCE.b(h.h(set)));
        kotlin.jvm.internal.q.b(patternCompile, "Pattern.compile(pattern,…odeCase(options.toInt()))");
        this(patternCompile);
    }
}
