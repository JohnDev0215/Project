package b.b.h.f.g;

import com.byd.db.db.sqlite.ColumnDbType;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: IntegerColumnConverter.java */
/* JADX INFO: loaded from: classes2.dex */
public class j implements e<Integer> {
    @Override // b.b.h.f.g.e
    public ColumnDbType b() {
        return ColumnDbType.INTEGER;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Object c(Integer num) {
        return num;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Integer a(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Integer.valueOf(cursor.getInt(i));
    }
}
