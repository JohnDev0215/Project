package android.view.autofill;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class Helper {
    public static boolean sDebug = false;
    public static boolean sVerbose = false;

    public static void appendRedacted(StringBuilder sb, CharSequence charSequence) {
        sb.append(getRedacted(charSequence));
    }

    public static String getRedacted(CharSequence charSequence) {
        return charSequence == null ? "null" : charSequence.length() + "_chars";
    }

    public static void appendRedacted(StringBuilder sb, String[] strArr) {
        if (strArr == null) {
            sb.append("N/A");
            return;
        }
        sb.append("[");
        for (String str : strArr) {
            sb.append(" '");
            appendRedacted(sb, str);
            sb.append("'");
        }
        sb.append(" ]");
    }

    public static AutofillId[] toArray(Collection<AutofillId> collection) {
        if (collection == null) {
            return new AutofillId[0];
        }
        AutofillId[] autofillIdArr = new AutofillId[collection.size()];
        collection.toArray(autofillIdArr);
        return autofillIdArr;
    }

    public static <T> ArrayList<T> toList(Set<T> set) {
        if (set == null) {
            return null;
        }
        return new ArrayList<>(set);
    }

    private Helper() {
        throw new UnsupportedOperationException("contains static members only");
    }
}
