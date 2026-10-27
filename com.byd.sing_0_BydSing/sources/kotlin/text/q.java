package kotlin.text;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Strings.kt */
/* JADX INFO: loaded from: classes.dex */
public class q extends p {
    public static CharSequence Q(CharSequence charSequence) {
        kotlin.jvm.internal.h.d(charSequence, "$this$reversed");
        StringBuilder sbReverse = new StringBuilder(charSequence).reverse();
        kotlin.jvm.internal.h.c(sbReverse, "StringBuilder(this).reverse()");
        return sbReverse;
    }
}
