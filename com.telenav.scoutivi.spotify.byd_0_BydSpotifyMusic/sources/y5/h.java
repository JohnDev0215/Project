package y5;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Appendable.kt */
/* JADX INFO: loaded from: classes.dex */
public class h {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void a(Appendable appendable, T t6, r5.l<? super T, ? extends CharSequence> lVar) {
        kotlin.jvm.internal.l.e(appendable, "<this>");
        if (lVar != null) {
            appendable.append(lVar.invoke(t6));
            return;
        }
        if (t6 == 0 ? true : t6 instanceof CharSequence) {
            appendable.append((CharSequence) t6);
        } else if (t6 instanceof Character) {
            appendable.append(((Character) t6).charValue());
        } else {
            appendable.append(String.valueOf(t6));
        }
    }
}
