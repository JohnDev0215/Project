package kotlin.text;

import kotlin.Metadata;
import kotlin.PublishedApi;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/Char;", "Lkotlin/Boolean;", "b", "(C)Z", "Lkotlin/Int;", "radix", "a", "(I)I", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, pn = "", xi = 0, xs = "kotlin/text/a")
public class b {
    @PublishedApi
    public static int a(int i) {
        if (2 <= i && 36 >= i) {
            return i;
        }
        throw new IllegalArgumentException("radix " + i + " was not in valid range " + new kotlin.t.c(2, 36));
    }

    public static final boolean b(char c2) {
        return Character.isWhitespace(c2) || Character.isSpaceChar(c2);
    }
}
