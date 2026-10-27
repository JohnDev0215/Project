package b.b.h.f.g;

import com.byd.db.db.sqlite.ColumnDbType;
import java.util.Date;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: DateColumnConverter.java */
/* JADX INFO: loaded from: classes2.dex */
public class g implements e<Date> {
    @Override // b.b.h.f.g.e
    public ColumnDbType b() {
        return ColumnDbType.INTEGER;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Object c(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Date a(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return new Date(cursor.getLong(i));
    }
}
