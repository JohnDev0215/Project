package dagger.internal.codegen.base;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Formatter<T> implements Function<T, String> {
    public abstract String format(T object);

    @Override // com.google.common.base.Function
    @Deprecated
    public final String apply(T object) {
        return format(object);
    }

    public static String formatArgumentInList(int index, int size, CharSequence name) {
        Preconditions.checkElementIndex(index, size);
        StringBuilder sb = new StringBuilder();
        if (index > 0) {
            sb.append("…, ");
        }
        sb.append(name);
        if (index < size - 1) {
            sb.append(", …");
        }
        return sb.toString();
    }
}
