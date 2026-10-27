package com.amazon.avod.fileio;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.amazon.avod.util.DLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaStoreFileUtil {
    public boolean createFile(@Nonnull File file, @Nonnull Context context, @Nullable String str) {
        try {
            OutputStream outputStreamOpenOutputStream = openOutputStream(file.getName(), context, str);
            try {
                if (outputStreamOpenOutputStream == null) {
                    DLog.warnf("Unable to create writeable file");
                    if (outputStreamOpenOutputStream != null) {
                        outputStreamOpenOutputStream.close();
                    }
                    return false;
                }
                boolean zExists = file.exists();
                outputStreamOpenOutputStream.close();
                return zExists;
            } finally {
            }
        } catch (IOException unused) {
            DLog.warnf("Unable to create writeable file");
            return false;
        }
    }

    @Nullable
    public OutputStream openOutputStream(@Nonnull String str, @Nonnull Context context, @Nullable String str2) throws FileNotFoundException {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        if (str2 != null) {
            contentValues.put("mime_type", str2);
        }
        Uri newContentUri = getNewContentUri(str, contentResolver);
        if (newContentUri == null) {
            DLog.warnf("Unable to create writeable file");
            return null;
        }
        return contentResolver.openOutputStream(newContentUri);
    }

    @Nullable
    public InputStream openInputStream(@Nonnull String str, @Nonnull Context context) throws FileNotFoundException {
        ContentResolver contentResolver = context.getContentResolver();
        Uri contentUri = getContentUri(str, contentResolver);
        if (contentUri == null) {
            DLog.warnf("No matching file found");
            return null;
        }
        return contentResolver.openInputStream(contentUri);
    }

    private Uri getNewContentUri(@Nonnull String str, @Nonnull ContentResolver contentResolver) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        Uri uriInsert = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues);
        if (uriInsert != null) {
            return uriInsert;
        }
        removeOrphanedMediaStoreEntry(str, contentResolver);
        return contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues);
    }

    private void removeOrphanedMediaStoreEntry(@Nonnull String str, @Nonnull ContentResolver contentResolver) {
        Uri contentUri = getContentUri(str, contentResolver);
        if (contentUri == null) {
            return;
        }
        contentResolver.delete(contentUri, null, null);
    }

    private Uri getContentUri(@Nonnull String str, @Nonnull ContentResolver contentResolver) {
        Uri contentUri = MediaStore.Files.getContentUri("external");
        Cursor cursorQuery = contentResolver.query(contentUri, new String[]{"_display_name", "_id"}, "_display_name=?", new String[]{str}, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToNext()) {
                    Uri uriWithAppendedId = ContentUris.withAppendedId(contentUri, cursorQuery.getLong(cursorQuery.getColumnIndex("_id")));
                    cursorQuery.close();
                    return uriWithAppendedId;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return null;
    }
}
