package kotlin.text;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes.dex */
final class MatcherMatchResult implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f5157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matcher f5158b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CharSequence f5159c;

    public MatcherMatchResult(Matcher matcher, CharSequence charSequence) {
        kotlin.jvm.internal.i.c(matcher, "matcher");
        kotlin.jvm.internal.i.c(charSequence, "input");
        this.f5158b = matcher;
        this.f5159c = charSequence;
        this.f5157a = new MatcherMatchResult$groups$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MatchResult c() {
        return this.f5158b;
    }

    @Override // kotlin.text.i
    public kotlin.o.c a() {
        return j.h(c());
    }

    @Override // kotlin.text.i
    public i next() {
        int iEnd = c().end() + (c().end() == c().start() ? 1 : 0);
        if (iEnd > this.f5159c.length()) {
            return null;
        }
        Matcher matcher = this.f5158b.pattern().matcher(this.f5159c);
        kotlin.jvm.internal.i.b(matcher, "matcher.pattern().matcher(input)");
        return j.f(matcher, iEnd, this.f5159c);
    }
}
