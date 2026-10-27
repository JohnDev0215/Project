package b.b.h.f.g;

import com.byd.db.db.sqlite.ColumnDbType;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: CharColumnConverter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d implements e<Character> {
    @Override // b.b.h.f.g.e
    public ColumnDbType b() {
        return ColumnDbType.INTEGER;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Object c(Character ch) {
        if (ch == null) {
            return null;
        }
        return Integer.valueOf(ch.charValue());
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Character a(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Character.valueOf((char) cursor.getInt(i));
    }
}
