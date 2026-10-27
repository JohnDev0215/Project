package b.b.h.f.g;

import com.byd.db.db.sqlite.ColumnDbType;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: ByteArrayColumnConverter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b implements e<byte[]> {
    @Override // b.b.h.f.g.e
    public ColumnDbType b() {
        return ColumnDbType.BLOB;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public Object c(byte[] bArr) {
        return bArr;
    }

    @Override // b.b.h.f.g.e
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public byte[] a(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return cursor.getBlob(i);
    }
}
