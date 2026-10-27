package com.android.providers.media;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.ArraySet;
import com.android.providers.media.util.Logging;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class LegacyMediaProvider extends ContentProvider {
    private static final UriMatcher BASIC_URI_MATCHER;
    private DatabaseHelper mExternalDatabase;
    private DatabaseHelper mInternalDatabase;

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (!providerInfo.exported) {
            throw new SecurityException("Provider must be exported");
        }
        if (!"android.permission.WRITE_MEDIA_STORAGE".equals(providerInfo.readPermission) || !"android.permission.WRITE_MEDIA_STORAGE".equals(providerInfo.writePermission)) {
            throw new SecurityException("Provider must be protected by WRITE_MEDIA_STORAGE");
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        Logging.initPersistent(context.getDir("logs", 0));
        this.mInternalDatabase = new DatabaseHelper(context, "internal.db", true, false, true, null, null, null, null, null);
        this.mExternalDatabase = new DatabaseHelper(context, "external.db", false, false, true, null, null, null, null, null);
        return true;
    }

    private DatabaseHelper getDatabaseForUri(Uri uri) {
        String volumeName = MediaStore.getVolumeName(uri);
        if (((volumeName.hashCode() == 570410685 && volumeName.equals("internal")) ? (byte) 0 : (byte) -1) == 0) {
            DatabaseHelper databaseHelper = this.mInternalDatabase;
            Objects.requireNonNull(databaseHelper, "Missing internal database");
            return databaseHelper;
        }
        DatabaseHelper databaseHelper2 = this.mExternalDatabase;
        Objects.requireNonNull(databaseHelper2, "Missing external database");
        return databaseHelper2;
    }

    @Override // android.content.ContentProvider
    public Cursor query(final Uri uri, final String[] strArr, String str, final String[] strArr2, final String str2) {
        final String appendedSelection = getAppendedSelection(str, uri);
        return (Cursor) getDatabaseForUri(uri).runWithoutTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$LegacyMediaProvider$7ZS0Y5k0Z2rWMZcOPILwNTmAeIk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SQLiteDatabase) obj).query(LegacyMediaProvider.getTableName(uri), strArr, appendedSelection, strArr2, null, null, str2);
            }
        });
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        ArraySet arraySet = new ArraySet();
        try {
            Iterator<ContentProviderOperation> it = arrayList.iterator();
            while (it.hasNext()) {
                DatabaseHelper databaseForUri = getDatabaseForUri(it.next().getUri());
                if (!arraySet.contains(databaseForUri)) {
                    databaseForUri.beginTransaction();
                    arraySet.add(databaseForUri);
                }
            }
            ContentProviderResult[] contentProviderResultArrApplyBatch = super.applyBatch(arrayList);
            Iterator it2 = arraySet.iterator();
            while (it2.hasNext()) {
                ((DatabaseHelper) it2.next()).setTransactionSuccessful();
            }
            return contentProviderResultArrApplyBatch;
        } finally {
            Iterator it3 = arraySet.iterator();
            while (it3.hasNext()) {
                ((DatabaseHelper) it3.next()).endTransaction();
            }
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        if (!uri.getBooleanQueryParameter("silent", false)) {
            try {
                File file = new File(contentValues.getAsString("_data"));
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return ContentUris.withAppendedId(uri, ((Long) getDatabaseForUri(uri).runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$LegacyMediaProvider$EyBA-0CIQ3P6SmxF35UXQEoMYsc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((SQLiteDatabase) obj).insert(LegacyMediaProvider.getTableName(uri), null, contentValues));
            }
        })).longValue());
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        BASIC_URI_MATCHER = uriMatcher;
        uriMatcher.addURI("media_legacy", "*/audio/playlists/#/members", 112);
        uriMatcher.addURI("media_legacy", "*/file/#", 701);
    }

    private static String getAppendedSelection(String str, Uri uri) {
        String str2;
        int iMatch = BASIC_URI_MATCHER.match(uri);
        if (iMatch == 112) {
            str2 = "playlist_id=" + uri.getPathSegments().get(3);
        } else if (iMatch != 701) {
            str2 = "";
        } else {
            str2 = "_id=" + uri.getPathSegments().get(2);
        }
        if (str == null || str.isEmpty()) {
            return str2;
        }
        if (str2.isEmpty()) {
            return str;
        }
        return str2 + " AND " + str;
    }

    private static String getTableName(Uri uri) {
        return BASIC_URI_MATCHER.match(uri) == 112 ? "audio_playlists_map" : "files";
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.Bundle call(java.lang.String r4, java.lang.String r5, java.lang.String r6, android.os.Bundle r7) {
        /*
            r3 = this;
            int r4 = r5.hashCode()
            r7 = 13428996(0xcce904, float:1.8818031E-38)
            r0 = 0
            r1 = 1
            r2 = -1
            if (r4 == r7) goto L1c
            r7 = 704014613(0x29f66915, float:1.09428234E-13)
            if (r4 == r7) goto L12
            goto L26
        L12:
            java.lang.String r4 = "start_legacy_migration"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L26
            r4 = r0
            goto L27
        L1c:
            java.lang.String r4 = "finish_legacy_migration"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L26
            r4 = r1
            goto L27
        L26:
            r4 = r2
        L27:
            if (r4 == r1) goto L2a
            goto L5d
        L2a:
            int r4 = r6.hashCode()
            r5 = 570410685(0x21ffc6bd, float:1.7332078E-18)
            if (r4 == r5) goto L34
            goto L3d
        L34:
            java.lang.String r4 = "internal"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L3d
            goto L3e
        L3d:
            r0 = r2
        L3e:
            if (r0 == 0) goto L4f
            com.android.providers.media.DatabaseHelper r4 = r3.mExternalDatabase
            r4.close()
            android.content.Context r3 = r3.getContext()
            java.lang.String r4 = "external.db"
            r3.deleteDatabase(r4)
            goto L5d
        L4f:
            com.android.providers.media.DatabaseHelper r4 = r3.mInternalDatabase
            r4.close()
            android.content.Context r3 = r3.getContext()
            java.lang.String r4 = "internal.db"
            r3.deleteDatabase(r4)
        L5d:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.LegacyMediaProvider.call(java.lang.String, java.lang.String, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        Logging.dumpPersistent(printWriter);
    }
}
