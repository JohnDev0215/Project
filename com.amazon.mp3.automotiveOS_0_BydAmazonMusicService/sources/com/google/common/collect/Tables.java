package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Table;
import java.util.Collections;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class Tables {
    private static final Function<? extends Map<?, ?>, ? extends Map<?, ?>> UNMODIFIABLE_WRAPPER = new Function<Map<Object, Object>, Map<Object, Object>>() { // from class: com.google.common.collect.Tables.1
        @Override // com.google.common.base.Function
        public Map<Object, Object> apply(Map<Object, Object> map) {
            return Collections.unmodifiableMap(map);
        }
    };

    private Tables() {
    }

    static abstract class AbstractCell<R, C, V> implements Table.Cell<R, C, V> {
        AbstractCell() {
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            return Objects.equal(getRowKey(), cell.getRowKey()) && Objects.equal(getColumnKey(), cell.getColumnKey()) && Objects.equal(getValue(), cell.getValue());
        }

        public int hashCode() {
            return Objects.hashCode(getRowKey(), getColumnKey(), getValue());
        }

        public String toString() {
            String strValueOf = String.valueOf(getRowKey());
            String strValueOf2 = String.valueOf(getColumnKey());
            String strValueOf3 = String.valueOf(getValue());
            StringBuilder sb = new StringBuilder(strValueOf.length() + 4 + strValueOf2.length() + strValueOf3.length());
            sb.append("(");
            sb.append(strValueOf);
            sb.append(",");
            sb.append(strValueOf2);
            sb.append(")=");
            sb.append(strValueOf3);
            return sb.toString();
        }
    }

    static boolean equalsImpl(Table<?, ?, ?> table, @CheckForNull Object obj) {
        if (obj == table) {
            return true;
        }
        if (obj instanceof Table) {
            return table.cellSet().equals(((Table) obj).cellSet());
        }
        return false;
    }
}
