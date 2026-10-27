package b.b.h.f;

import java.util.LinkedHashMap;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: CursorUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static b.b.h.f.i.c a(Cursor cursor) {
        b.b.h.f.i.c cVar = new b.b.h.f.i.c();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            cVar.a(cursor.getColumnName(i), cursor.getString(i));
        }
        return cVar;
    }

    public static <T> T b(b.b.h.f.i.d<T> dVar, Cursor cursor) throws Throwable {
        T tA = dVar.a();
        LinkedHashMap<String, b.b.h.f.i.a> linkedHashMapD = dVar.d();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            b.b.h.f.i.a aVar = linkedHashMapD.get(cursor.getColumnName(i));
            if (aVar != null) {
                aVar.i(tA, cursor, i);
            }
        }
        return tA;
    }
}
