package com.spotify.localfiles.mediastore;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.FileUtils;
import android.provider.MediaStore;
import androidx.annotation.Keep;
import com.spotify.webapi.service.models.Search;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import p.ux2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class LocalFileSyncMigrator {
    private final Uri audioCollection;
    private final ContentResolver resolver;

    public LocalFileSyncMigrator(Context context) {
        v03.j(context, "context");
        this.resolver = context.getContentResolver();
        this.audioCollection = Build.VERSION.SDK_INT >= 29 ? MediaStore.Audio.Media.getContentUri("external_primary") : MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }

    public final String copyFile(String str, String str2, String str3, String str4, int i, String str5, String str6) {
        v03.j(str, "srcPath");
        v03.j(str2, "title");
        v03.j(str3, Search.Type.ARTIST);
        v03.j(str4, Search.Type.ALBUM);
        v03.j(str5, "displayName");
        v03.j(str6, "relativePath");
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", str2);
        contentValues.put(Search.Type.ARTIST, str3);
        contentValues.put(Search.Type.ALBUM, str4);
        contentValues.put("duration", Integer.valueOf(i * 1000));
        contentValues.put("_display_name", str5);
        contentValues.put("relative_path", str6);
        contentValues.put("is_pending", (Integer) 1);
        contentValues.put("date_expires", Long.valueOf(new Date().getTime() + ((long) 3600000)));
        Uri uriInsert = this.resolver.insert(this.audioCollection, contentValues);
        if (uriInsert == null) {
            return "ERR_INSERT";
        }
        try {
            OutputStream outputStreamOpenOutputStream = this.resolver.openOutputStream(uriInsert);
            if (outputStreamOpenOutputStream == null) {
                return "ERR_OUTSTREAM";
            }
            try {
                FileUtils.copy(new FileInputStream(str), outputStreamOpenOutputStream);
                ux2.e(outputStreamOpenOutputStream, null);
                try {
                    new File(str).delete();
                } catch (Exception unused) {
                }
                contentValues.clear();
                contentValues.put("is_pending", (Integer) 0);
                this.resolver.update(uriInsert, contentValues, null, null);
                return "";
            } finally {
            }
        } catch (FileNotFoundException e) {
            return "ERR_FILE_NOT_FOUND_" + e.getMessage();
        } catch (IOException e2) {
            return "ERR_COPY_" + e2.getMessage();
        }
    }
}
