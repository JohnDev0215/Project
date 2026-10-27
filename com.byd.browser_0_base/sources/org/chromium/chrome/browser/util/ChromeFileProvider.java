package org.chromium.chrome.browser.util;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.core.content.FileProvider;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeFileProvider extends FileProvider {
    public static final Object i = new Object();

    public static Uri c(Uri uri) {
        if (uri == null || !uri.getPath().contains("BlockedFile_")) {
            return uri;
        }
        synchronized (i) {
        }
        return null;
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        if (uri == null || !uri.getPath().contains("BlockedFile_")) {
            return super.delete(uri, str, strArr);
        }
        synchronized (i) {
        }
        return 0;
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final String getType(Uri uri) {
        Uri uriC = c(uri);
        if (uriC != null) {
            return super.getType(uriC);
        }
        return null;
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        Uri uriC = c(uri);
        if (uriC != null) {
            return super.openFile(uriC, str);
        }
        return null;
    }

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String[] strArr3;
        Uri uriC = c(uri);
        if (uriC == null) {
            return null;
        }
        Cursor cursorQuery = super.query(uriC, strArr, str, strArr2, str2);
        MatrixCursor matrixCursor = (MatrixCursor) cursorQuery;
        String[] columnNames = matrixCursor.getColumnNames();
        int length = columnNames.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                strArr3 = (String[]) Arrays.copyOf(columnNames, columnNames.length + 1);
                strArr3[columnNames.length] = "_data";
                break;
            }
            if ("_data".equals(columnNames[i2])) {
                strArr3 = columnNames;
                break;
            }
            i2++;
        }
        if (columnNames == strArr3) {
            return cursorQuery;
        }
        MatrixCursor matrixCursor2 = new MatrixCursor(strArr3, matrixCursor.getCount());
        cursorQuery.moveToPosition(-1);
        while (cursorQuery.moveToNext()) {
            MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor2.newRow();
            for (int i3 = 0; i3 < columnNames.length; i3++) {
                int type = matrixCursor.getType(i3);
                if (type == 1) {
                    rowBuilderNewRow.add(Integer.valueOf(matrixCursor.getInt(i3)));
                } else if (type == 2) {
                    rowBuilderNewRow.add(Float.valueOf(matrixCursor.getFloat(i3)));
                } else if (type == 3) {
                    rowBuilderNewRow.add(matrixCursor.getString(i3));
                } else if (type != 4) {
                    rowBuilderNewRow.add(null);
                } else {
                    rowBuilderNewRow.add(matrixCursor.getBlob(i3));
                }
            }
        }
        cursorQuery.close();
        return matrixCursor2;
    }
}
