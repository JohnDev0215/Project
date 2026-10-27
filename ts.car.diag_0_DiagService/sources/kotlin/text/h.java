package kotlin.text;

import java.util.Iterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a#\u0010\t\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\u000b\u001a\u00020\b*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0013\u0010\u000f\u001a\u00020\u000e*\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Ljava/lang/Iterable;", "Lkotlin/Int;", "h", "(Ljava/lang/Iterable;)I", "Ljava/util/regex/Matcher;", "from", "Lkotlin/CharSequence;", "input", "Lkotlin/text/f;", "e", "(Ljava/util/regex/Matcher;ILjava/lang/CharSequence;)Lkotlin/text/f;", "f", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)Lkotlin/text/f;", "Ljava/util/regex/MatchResult;", "Lkotlin/t/c;", "g", "(Ljava/util/regex/MatchResult;)Lkotlin/t/c;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class h {
    /* JADX INFO: Access modifiers changed from: private */
    public static final f e(@NotNull Matcher matcher, int i, CharSequence charSequence) {
        if (matcher.find(i)) {
            return new g(matcher, charSequence);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final f f(@NotNull Matcher matcher, CharSequence charSequence) {
        if (matcher.matches()) {
            return new g(matcher, charSequence);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final kotlin.t.c g(@NotNull MatchResult matchResult) {
        return kotlin.t.g.d(matchResult.start(), matchResult.end());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int h(@NotNull Iterable<? extends d> iterable) {
        Iterator<? extends d> it = iterable.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= it.next().getValue();
        }
        return value;
    }
}
