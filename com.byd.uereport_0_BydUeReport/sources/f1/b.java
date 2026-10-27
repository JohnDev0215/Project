package f1;

import android.database.Cursor;
import android.database.MatrixCursor;

/* JADX INFO: compiled from: CursorUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static Cursor a(Cursor cursor) {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames(), cursor.getCount());
            while (cursor.moveToNext()) {
                Object[] objArr = new Object[cursor.getColumnCount()];
                for (int i8 = 0; i8 < cursor.getColumnCount(); i8++) {
                    int type = cursor.getType(i8);
                    if (type == 0) {
                        objArr[i8] = null;
                    } else if (type == 1) {
                        objArr[i8] = Long.valueOf(cursor.getLong(i8));
                    } else if (type == 2) {
                        objArr[i8] = Double.valueOf(cursor.getDouble(i8));
                    } else if (type == 3) {
                        objArr[i8] = cursor.getString(i8);
                    } else {
                        if (type != 4) {
                            throw new IllegalStateException();
                        }
                        objArr[i8] = cursor.getBlob(i8);
                    }
                }
                matrixCursor.addRow(objArr);
            }
            return matrixCursor;
        } finally {
            cursor.close();
        }
    }

    public static int b(Cursor cursor, String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        return cursor.getColumnIndexOrThrow("`" + str + "`");
    }
}
