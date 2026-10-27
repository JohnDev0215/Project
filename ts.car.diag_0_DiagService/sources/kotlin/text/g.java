package kotlin.text;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.AbstractCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
final class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Matcher f1535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CharSequence f1536b;

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lkotlin/text/g$a;", "Ljava/util/Collection;", "Lkotlin/collections/AbstractCollection;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class a extends AbstractCollection<e> implements Object {
        @Nullable
        public abstract e b(int i);
    }

    public g(@NotNull Matcher matcher, @NotNull CharSequence charSequence) {
        kotlin.jvm.internal.q.c(matcher, "matcher");
        kotlin.jvm.internal.q.c(charSequence, "input");
        this.f1535a = matcher;
        this.f1536b = charSequence;
    }

    private final MatchResult b() {
        return this.f1535a;
    }

    @Override // kotlin.text.f
    @NotNull
    public kotlin.t.c a() {
        return h.g(b());
    }

    @Override // kotlin.text.f
    @Nullable
    public f next() {
        int iEnd = b().end() + (b().end() == b().start() ? 1 : 0);
        if (iEnd > this.f1536b.length()) {
            return null;
        }
        Matcher matcher = this.f1535a.pattern().matcher(this.f1536b);
        kotlin.jvm.internal.q.b(matcher, "matcher.pattern().matcher(input)");
        return h.e(matcher, iEnd, this.f1536b);
    }
}
