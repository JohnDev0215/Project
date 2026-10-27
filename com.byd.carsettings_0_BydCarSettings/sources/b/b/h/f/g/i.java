package b.b.h.f.g;

import com.byd.db.db.sqlite.ColumnDbType;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: FloatColumnConverter.java */
/* JADX INFO: loaded from: classes2.dex */
public class i implements e<Float> {
    @Override // b.b.h.f.g.e
    public ColumnDbType b() {
        return ColumnDbType.REAL;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Object c(Float f2) {
        return f2;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Float a(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Float.valueOf(cursor.getFloat(i));
    }
}
