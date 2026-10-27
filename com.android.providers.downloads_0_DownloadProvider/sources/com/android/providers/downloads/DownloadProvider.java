package com.android.providers.downloads;

import android.app.AppOpsManager;
import android.app.job.JobScheduler;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Downloads;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.providers.downloads.DownloadInfo;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes.dex */
public final class DownloadProvider extends ContentProvider {
    private static final Uri[] BASE_URIS;
    private static final Map<String, String> sDownloadsMap;
    private static final Map<String, String> sHeadersMap;
    private static final UriMatcher sURIMatcher;
    private AppOpsManager mAppOpsManager;
    private SQLiteOpenHelper mOpenHelper = null;
    SystemFacade mSystemFacade;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURIMatcher = uriMatcher;
        uriMatcher.addURI("downloads", "my_downloads", 1);
        sURIMatcher.addURI("downloads", "my_downloads/#", 2);
        sURIMatcher.addURI("downloads", "all_downloads", 4);
        sURIMatcher.addURI("downloads", "all_downloads/#", 5);
        sURIMatcher.addURI("downloads", "my_downloads/#/headers", 3);
        sURIMatcher.addURI("downloads", "all_downloads/#/headers", 6);
        sURIMatcher.addURI("downloads", "download", 1);
        sURIMatcher.addURI("downloads", "download/#", 2);
        sURIMatcher.addURI("downloads", "download/#/headers", 3);
        BASE_URIS = new Uri[]{Downloads.Impl.CONTENT_URI, Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI};
        ArrayMap arrayMap = new ArrayMap();
        sDownloadsMap = arrayMap;
        addMapping(arrayMap, "_id", "_id");
        addMapping(arrayMap, "local_filename", "_data");
        addMapping(arrayMap, "mediaprovider_uri");
        addMapping(arrayMap, "destination");
        addMapping(arrayMap, "title");
        addMapping(arrayMap, "description");
        addMapping(arrayMap, "uri");
        addMapping(arrayMap, "status");
        addMapping(arrayMap, "hint");
        addMapping(arrayMap, "media_type", "mimetype");
        addMapping(arrayMap, "total_size", "total_bytes");
        addMapping(arrayMap, "last_modified_timestamp", "lastmod");
        addMapping(arrayMap, "bytes_so_far", "current_bytes");
        addMapping(arrayMap, "allow_write");
        addMapping(arrayMap, "local_uri", "'placeholder'");
        addMapping(arrayMap, "reason", "'placeholder'");
        addMapping(arrayMap, "_display_name", "title");
        addMapping(arrayMap, "_size", "total_bytes");
        addMapping(arrayMap, "_id");
        addMapping(arrayMap, "_data");
        addMapping(arrayMap, "allowed_network_types");
        addMapping(arrayMap, "allow_metered");
        addMapping(arrayMap, "allow_roaming");
        addMapping(arrayMap, "allow_write");
        addMapping(arrayMap, "entity");
        addMapping(arrayMap, "bypass_recommended_size_limit");
        addMapping(arrayMap, "control");
        addMapping(arrayMap, "cookiedata");
        addMapping(arrayMap, "current_bytes");
        addMapping(arrayMap, "deleted");
        addMapping(arrayMap, "description");
        addMapping(arrayMap, "destination");
        addMapping(arrayMap, "errorMsg");
        addMapping(arrayMap, "numfailed");
        addMapping(arrayMap, "hint");
        addMapping(arrayMap, "flags");
        addMapping(arrayMap, "is_public_api");
        addMapping(arrayMap, "is_visible_in_downloads_ui");
        addMapping(arrayMap, "lastmod");
        addMapping(arrayMap, "mediaprovider_uri");
        addMapping(arrayMap, "scanned");
        addMapping(arrayMap, "mediastore_uri");
        addMapping(arrayMap, "mimetype");
        addMapping(arrayMap, "no_integrity");
        addMapping(arrayMap, "notificationclass");
        addMapping(arrayMap, "notificationextras");
        addMapping(arrayMap, "notificationpackage");
        addMapping(arrayMap, "otheruid");
        addMapping(arrayMap, "referer");
        addMapping(arrayMap, "status");
        addMapping(arrayMap, "title");
        addMapping(arrayMap, "total_bytes");
        addMapping(arrayMap, "uri");
        addMapping(arrayMap, "useragent");
        addMapping(arrayMap, "visibility");
        addMapping(arrayMap, "etag");
        addMapping(arrayMap, "method");
        addMapping(arrayMap, "uid");
        ArrayMap arrayMap2 = new ArrayMap();
        sHeadersMap = arrayMap2;
        addMapping(arrayMap2, "id");
        addMapping(arrayMap2, "download_id");
        addMapping(arrayMap2, "header");
        addMapping(arrayMap2, "value");
    }

    private static void addMapping(Map<String, String> map, String str) {
        if (map.containsKey(str)) {
            return;
        }
        map.put(str, str);
    }

    private static void addMapping(Map<String, String> map, String str, String str2) {
        if (map.containsKey(str)) {
            return;
        }
        map.put(str, str2 + " AS " + str);
    }

    private final class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, "downloads.db", (SQLiteDatabase.CursorFactory) null, 114);
            setIdleConnectionTimeout(30000L);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "populating new database");
            }
            onUpgrade(sQLiteDatabase, 0, 114);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 31) {
                i = 100;
            } else {
                if (i < 100) {
                    Log.i("DownloadManager", "Upgrading downloads database from version " + i + " to version " + i2 + ", which will destroy all old data");
                } else if (i > i2) {
                    Log.i("DownloadManager", "Downgrading downloads database from version " + i + " (current version is " + i2 + "), destroying all old data");
                }
                i = 99;
            }
            while (true) {
                i++;
                if (i > i2) {
                    return;
                } else {
                    upgradeTo(sQLiteDatabase, i);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void upgradeTo(android.database.sqlite.SQLiteDatabase r7, int r8) {
            /*
                r6 = this;
                r0 = 1
                java.lang.String r1 = "BOOLEAN NOT NULL DEFAULT 0"
                java.lang.String r2 = "TEXT"
                java.lang.String r3 = "INTEGER NOT NULL DEFAULT 1"
                java.lang.String r4 = "INTEGER NOT NULL DEFAULT 0"
                java.lang.String r5 = "downloads"
                switch(r8) {
                    case 100: goto L83;
                    case 101: goto L7f;
                    case 102: goto L6f;
                    case 103: goto L66;
                    case 104: goto L60;
                    case 105: goto L5c;
                    case 106: goto L51;
                    case 107: goto L4b;
                    case 108: goto L45;
                    case 109: goto L3f;
                    case 110: goto L39;
                    case 111: goto L31;
                    case 112: goto L2d;
                    case 113: goto L29;
                    case 114: goto L25;
                    default: goto Le;
                }
            Le:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r0 = "Don't know how to upgrade to "
                r7.append(r0)
                r7.append(r8)
                java.lang.String r7 = r7.toString()
                r6.<init>(r7)
                throw r6
            L25:
                r6.nullifyMediaStoreUris(r7)
                goto L87
            L29:
                r6.canonicalizeDataPaths(r7)
                goto L86
            L2d:
                r6.updateMediaStoreUrisFromFilesToDownloads(r7)
                goto L86
            L31:
                java.lang.String r8 = "mediastore_uri"
                java.lang.String r1 = "TEXT DEFAULT NULL"
                r6.addColumn(r7, r5, r8, r1)
                goto L87
            L39:
                java.lang.String r8 = "flags"
                r6.addColumn(r7, r5, r8, r4)
                goto L86
            L3f:
                java.lang.String r8 = "allow_write"
                r6.addColumn(r7, r5, r8, r1)
                goto L86
            L45:
                java.lang.String r8 = "allow_metered"
                r6.addColumn(r7, r5, r8, r3)
                goto L86
            L4b:
                java.lang.String r8 = "errorMsg"
                r6.addColumn(r7, r5, r8, r2)
                goto L86
            L51:
                java.lang.String r8 = "mediaprovider_uri"
                r6.addColumn(r7, r5, r8, r2)
                java.lang.String r8 = "deleted"
                r6.addColumn(r7, r5, r8, r1)
                goto L86
            L5c:
                r6.fillNullValues(r7)
                goto L86
            L60:
                java.lang.String r8 = "bypass_recommended_size_limit"
                r6.addColumn(r7, r5, r8, r4)
                goto L86
            L66:
                java.lang.String r8 = "is_visible_in_downloads_ui"
                r6.addColumn(r7, r5, r8, r3)
                r6.makeCacheDownloadsInvisible(r7)
                goto L86
            L6f:
                java.lang.String r8 = "is_public_api"
                r6.addColumn(r7, r5, r8, r4)
                java.lang.String r8 = "allow_roaming"
                r6.addColumn(r7, r5, r8, r4)
                java.lang.String r8 = "allowed_network_types"
                r6.addColumn(r7, r5, r8, r4)
                goto L86
            L7f:
                r6.createHeadersTable(r7)
                goto L86
            L83:
                r6.createDownloadsTable(r7)
            L86:
                r0 = 0
            L87:
                if (r0 == 0) goto L92
                com.android.providers.downloads.DownloadProvider r6 = com.android.providers.downloads.DownloadProvider.this
                android.content.Context r6 = r6.getContext()
                com.android.providers.downloads.MediaScanTriggerJob.schedule(r6)
            L92:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.DatabaseHelper.upgradeTo(android.database.sqlite.SQLiteDatabase, int):void");
        }

        private void fillNullValues(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("current_bytes", (Integer) 0);
            fillNullValuesForColumn(sQLiteDatabase, contentValues);
            contentValues.put("total_bytes", (Integer) (-1));
            fillNullValuesForColumn(sQLiteDatabase, contentValues);
            contentValues.put("title", "");
            fillNullValuesForColumn(sQLiteDatabase, contentValues);
            contentValues.put("description", "");
            fillNullValuesForColumn(sQLiteDatabase, contentValues);
        }

        private void fillNullValuesForColumn(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
            sQLiteDatabase.update("downloads", contentValues, contentValues.valueSet().iterator().next().getKey() + " is null", null);
            contentValues.clear();
        }

        private void makeCacheDownloadsInvisible(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_visible_in_downloads_ui", Boolean.FALSE);
            sQLiteDatabase.update("downloads", contentValues, "destination != 0", null);
        }

        private void updateMediaStoreUrisFromFilesToDownloads(SQLiteDatabase sQLiteDatabase) {
            Cursor cursorQuery = sQLiteDatabase.query("downloads", new String[]{"_id", "mediastore_uri"}, "mediastore_uri IS NOT NULL", null, null, null, null);
            try {
                ContentValues contentValues = new ContentValues();
                while (cursorQuery.moveToNext()) {
                    long j = cursorQuery.getLong(0);
                    Uri uri = Uri.parse(cursorQuery.getString(1));
                    Uri contentUri = MediaStore.Downloads.getContentUri(MediaStore.getVolumeName(uri), ContentUris.parseId(uri));
                    contentValues.clear();
                    contentValues.put("mediastore_uri", contentUri.toString());
                    sQLiteDatabase.update("downloads", contentValues, "_id=?", new String[]{Long.toString(j)});
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private void canonicalizeDataPaths(SQLiteDatabase sQLiteDatabase) {
            Cursor cursorQuery = sQLiteDatabase.query("downloads", new String[]{"_id", "_data"}, "_data IS NOT NULL", null, null, null, null);
            try {
                ContentValues contentValues = new ContentValues();
                while (cursorQuery.moveToNext()) {
                    long j = cursorQuery.getLong(0);
                    String string = cursorQuery.getString(1);
                    try {
                        String canonicalPath = new File(string).getCanonicalPath();
                        contentValues.clear();
                        contentValues.put("_data", canonicalPath);
                        sQLiteDatabase.update("downloads", contentValues, "_id=?", new String[]{Long.toString(j)});
                    } catch (IOException unused) {
                        Log.e("DownloadManager", "Found invalid path='" + string + "' for id=" + j);
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private void nullifyMediaStoreUris(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("mediastore_uri");
            sQLiteDatabase.update("downloads", contentValues, "_data IS NOT NULL AND (is_visible_in_downloads_ui=1 OR scanned=1) AND (destination=0 OR destination=4 OR destination=6)", null);
        }

        private void addColumn(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
            sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD COLUMN " + str2 + " " + str3);
        }

        private void createDownloadsTable(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS downloads");
                sQLiteDatabase.execSQL("CREATE TABLE downloads(_id INTEGER PRIMARY KEY AUTOINCREMENT,uri TEXT, method INTEGER, entity TEXT, no_integrity BOOLEAN, hint TEXT, otaupdate BOOLEAN, _data TEXT, mimetype TEXT, destination INTEGER, no_system BOOLEAN, visibility INTEGER, control INTEGER, status INTEGER, numfailed INTEGER, lastmod BIGINT, notificationpackage TEXT, notificationclass TEXT, notificationextras TEXT, cookiedata TEXT, useragent TEXT, referer TEXT, total_bytes INTEGER, current_bytes INTEGER, etag TEXT, uid INTEGER, otheruid INTEGER, title TEXT, description TEXT, scanned BOOLEAN);");
            } catch (SQLException e) {
                Log.e("DownloadManager", "couldn't create table in downloads database");
                throw e;
            }
        }

        private void createHeadersTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS request_headers");
            sQLiteDatabase.execSQL("CREATE TABLE request_headers(id INTEGER PRIMARY KEY AUTOINCREMENT,download_id INTEGER NOT NULL,header TEXT NOT NULL,value TEXT NOT NULL);");
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (this.mSystemFacade == null) {
            this.mSystemFacade = new RealSystemFacade(getContext());
        }
        this.mOpenHelper = new DatabaseHelper(getContext());
        this.mAppOpsManager = (AppOpsManager) getContext().getSystemService(AppOpsManager.class);
        Cursor cursorQuery = this.mOpenHelper.getReadableDatabase().query("downloads", new String[]{"_id", "uid"}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                long j = cursorQuery.getLong(0);
                String[] packagesForUid = getContext().getPackageManager().getPackagesForUid(cursorQuery.getInt(1));
                if (!ArrayUtils.isEmpty(packagesForUid)) {
                    grantAllDownloadsPermission(packagesForUid[0], j);
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = sURIMatcher.match(uri);
        if (iMatch == 1) {
            return "vnd.android.cursor.dir/download";
        }
        if (iMatch != 2) {
            if (iMatch == 4) {
                return "vnd.android.cursor.dir/download";
            }
            if (iMatch != 5) {
                if (Constants.LOGV) {
                    Log.v("DownloadManager", "calling getType on an unknown URI: " + uri);
                }
                throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        }
        String strStringForQuery = DatabaseUtils.stringForQuery(this.mOpenHelper.getReadableDatabase(), "SELECT mimetype FROM downloads WHERE _id = ?", new String[]{getDownloadIdFromUri(uri)});
        return TextUtils.isEmpty(strStringForQuery) ? "vnd.android.cursor.item/download" : strStringForQuery;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.Bundle call(java.lang.String r6, java.lang.String r7, android.os.Bundle r8) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.call(java.lang.String, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabase;
        checkInsertPermissions(contentValues);
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        int iMatch = sURIMatcher.match(uri);
        if (iMatch != 1) {
            Log.d("DownloadManager", "calling insert on an unknown/invalid URI: " + uri);
            throw new IllegalArgumentException("Unknown/Invalid URI " + uri);
        }
        ContentValues contentValues2 = new ContentValues();
        boolean z = contentValues.getAsBoolean("is_public_api") == Boolean.TRUE;
        Integer asInteger = contentValues.getAsInteger("destination");
        if (asInteger != null) {
            if (getContext().checkCallingOrSelfPermission("android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED") != 0 && (asInteger.intValue() == 1 || asInteger.intValue() == 3)) {
                throw new SecurityException("setting destination to : " + asInteger + " not allowed, unless PERMISSION_ACCESS_ADVANCED is granted");
            }
            boolean z2 = getContext().checkCallingOrSelfPermission("android.permission.DOWNLOAD_CACHE_NON_PURGEABLE") == 0;
            if (z && asInteger.intValue() == 2 && z2) {
                asInteger = 1;
            }
            if (asInteger.intValue() == 4) {
                checkFileUriDestination(contentValues);
            } else if (asInteger.intValue() == 6) {
                checkDownloadedFilePath(contentValues);
            } else if (asInteger.intValue() == 0) {
                getContext().enforceCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE", "No permission to write");
                if (this.mAppOpsManager.noteProxyOp(60, getCallingPackage(), Binder.getCallingUid(), getCallingAttributionTag(), (String) null) != 0) {
                    throw new SecurityException("No permission to write");
                }
            }
            contentValues2.put("destination", asInteger);
        }
        ensureDefaultColumns(contentValues);
        copyString("uri", contentValues, contentValues2);
        copyString("entity", contentValues, contentValues2);
        copyBoolean("no_integrity", contentValues, contentValues2);
        copyString("hint", contentValues, contentValues2);
        copyString("mimetype", contentValues, contentValues2);
        copyBoolean("is_public_api", contentValues, contentValues2);
        Integer asInteger2 = contentValues.getAsInteger("visibility");
        if (asInteger2 == null) {
            if (asInteger.intValue() == 0) {
                contentValues2.put("visibility", (Integer) 1);
            } else {
                contentValues2.put("visibility", (Integer) 2);
            }
        } else {
            contentValues2.put("visibility", asInteger2);
        }
        copyInteger("control", contentValues, contentValues2);
        if (contentValues.getAsInteger("destination").intValue() == 6) {
            contentValues2.put("status", (Integer) 200);
            contentValues2.put("total_bytes", contentValues.getAsLong("total_bytes"));
            contentValues2.put("current_bytes", (Integer) 0);
            copyString("_data", contentValues, contentValues2);
            copyBoolean("allow_write", contentValues, contentValues2);
        } else {
            contentValues2.put("status", (Integer) 190);
            contentValues2.put("total_bytes", (Integer) (-1));
            contentValues2.put("current_bytes", (Integer) 0);
        }
        contentValues2.put("lastmod", Long.valueOf(this.mSystemFacade.currentTimeMillis()));
        String asString = contentValues.getAsString("notificationpackage");
        String asString2 = contentValues.getAsString("notificationclass");
        if (asString == null || (asString2 == null && !z)) {
            sQLiteDatabase = writableDatabase;
        } else {
            int callingUid = Binder.getCallingUid();
            if (callingUid != 0) {
                sQLiteDatabase = writableDatabase;
                try {
                    if (this.mSystemFacade.userOwnsPackage(callingUid, asString)) {
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            } else {
                sQLiteDatabase = writableDatabase;
            }
            contentValues2.put("notificationpackage", asString);
            if (asString2 != null) {
                contentValues2.put("notificationclass", asString2);
            }
        }
        copyString("notificationextras", contentValues, contentValues2);
        copyString("cookiedata", contentValues, contentValues2);
        copyString("useragent", contentValues, contentValues2);
        copyString("referer", contentValues, contentValues2);
        if (getContext().checkCallingOrSelfPermission("android.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED") == 0) {
            copyInteger("otheruid", contentValues, contentValues2);
        }
        contentValues2.put("uid", Integer.valueOf(Binder.getCallingUid()));
        if (Binder.getCallingUid() == 0) {
            copyInteger("uid", contentValues, contentValues2);
        }
        copyStringWithDefault("title", contentValues, contentValues2, "");
        copyStringWithDefault("description", contentValues, contentValues2, "");
        copyBoolean("is_visible_in_downloads_ui", contentValues, contentValues2);
        if (z) {
            copyInteger("allowed_network_types", contentValues, contentValues2);
            copyBoolean("allow_roaming", contentValues, contentValues2);
            copyBoolean("allow_metered", contentValues, contentValues2);
            copyInteger("flags", contentValues, contentValues2);
        }
        Integer asInteger3 = contentValues.getAsInteger("scanned");
        contentValues2.put("scanned", Integer.valueOf(asInteger3 == null ? 0 : asInteger3.intValue()));
        if ((contentValues2.getAsBoolean("is_visible_in_downloads_ui").booleanValue() || contentValues2.getAsInteger("scanned").intValue() == 0) && contentValues2.getAsInteger("destination").intValue() == 6) {
            ContentProvider.CallingIdentity callingIdentityClearCallingIdentity = clearCallingIdentity();
            try {
                Uri uriScanFile = MediaStore.scanFile(getContext().getContentResolver(), new File(contentValues2.getAsString("_data")));
                if (uriScanFile != null) {
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("download_uri", contentValues2.getAsString("uri"));
                    contentValues3.put("referer_uri", contentValues2.getAsString("referer"));
                    contentValues3.put("owner_package_name", Helpers.getPackageForUid(getContext(), contentValues2.getAsInteger("uid").intValue()));
                    getContext().getContentResolver().update(Helpers.convertToMediaStoreDownloadsUri(uriScanFile), contentValues3, null, null);
                    contentValues2.put("mediastore_uri", uriScanFile.toString());
                    contentValues2.put("mediaprovider_uri", uriScanFile.toString());
                    contentValues2.put("scanned", (Integer) 1);
                }
            } finally {
                restoreCallingIdentity(callingIdentityClearCallingIdentity);
            }
        }
        if (Constants.LOGVV) {
            Log.v("DownloadManager", "initiating download with UID " + contentValues2.getAsInteger("uid"));
            if (contentValues2.containsKey("otheruid")) {
                Log.v("DownloadManager", "other UID " + contentValues2.getAsInteger("otheruid"));
            }
        }
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        long jInsert = sQLiteDatabase2.insert("downloads", null, contentValues2);
        if (jInsert == -1) {
            Log.d("DownloadManager", "couldn't insert into downloads database");
            return null;
        }
        insertRequestHeaders(sQLiteDatabase2, jInsert, contentValues);
        String packageForUid = Helpers.getPackageForUid(getContext(), Binder.getCallingUid());
        if (packageForUid == null) {
            Log.e("DownloadManager", "Package does not exist for calling uid");
            return null;
        }
        grantAllDownloadsPermission(packageForUid, jInsert);
        notifyContentChanged(uri, iMatch);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Helpers.scheduleJob(getContext(), jInsert);
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return ContentUris.withAppendedId(Downloads.Impl.CONTENT_URI, jInsert);
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    Uri updateMediaProvider(ContentProviderClient contentProviderClient, ContentValues contentValues) {
        String asString = contentValues.getAsString("_data");
        Uri mediaStoreUri = getMediaStoreUri(contentProviderClient, asString);
        try {
            if (mediaStoreUri == null) {
                Uri uriInsert = contentProviderClient.insert(Helpers.getContentUriForPath(getContext(), asString), contentValues);
                if (uriInsert == null) {
                    Log.e("DownloadManager", "Error inserting into mediaProvider: " + contentValues);
                }
                return uriInsert;
            }
            if (contentProviderClient.update(mediaStoreUri, contentValues, null, null) != 1) {
                Log.e("DownloadManager", "Error updating MediaProvider, uri: " + mediaStoreUri + ", values: " + contentValues);
            }
            return mediaStoreUri;
        } catch (RemoteException unused) {
            return null;
        }
    }

    private Uri getMediaStoreUri(ContentProviderClient contentProviderClient, String str) {
        Uri includePending = MediaStore.setIncludePending(Helpers.getContentUriForPath(getContext(), str));
        try {
            Cursor cursorQuery = contentProviderClient.query(includePending, new String[]{"_id"}, "_data=?", new String[]{str}, null, null);
            try {
                if (cursorQuery.moveToNext()) {
                    Uri uriWithAppendedId = ContentUris.withAppendedId(includePending, cursorQuery.getLong(0));
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return uriWithAppendedId;
                }
                if (cursorQuery == null) {
                    return null;
                }
                cursorQuery.close();
                return null;
            } finally {
            }
        } catch (RemoteException unused) {
            return null;
        }
        return null;
    }

    ContentValues convertToMediaProviderValues(DownloadInfo downloadInfo) {
        try {
            String canonicalPath = new File(downloadInfo.mFileName).getCanonicalPath();
            boolean zIsStatusCompleted = Downloads.Impl.isStatusCompleted(downloadInfo.mStatus);
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", canonicalPath);
            contentValues.put("volume_name", Helpers.extractVolumeName(canonicalPath));
            contentValues.put("relative_path", Helpers.extractRelativePath(canonicalPath));
            contentValues.put("_display_name", Helpers.extractDisplayName(canonicalPath));
            contentValues.put("_size", Long.valueOf(zIsStatusCompleted ? downloadInfo.mTotalBytes : downloadInfo.mCurrentBytes));
            contentValues.put("download_uri", downloadInfo.mUri);
            contentValues.put("referer_uri", downloadInfo.mReferer);
            contentValues.put("mime_type", downloadInfo.mMimeType);
            contentValues.put("is_pending", Integer.valueOf(!zIsStatusCompleted ? 1 : 0));
            contentValues.put("owner_package_name", Helpers.getPackageForUid(getContext(), downloadInfo.mUid));
            return contentValues;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Uri getFileUri(String str) {
        Uri uri = Uri.parse(str);
        if (TextUtils.equals(uri.getScheme(), "file")) {
            return uri;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void ensureDefaultColumns(android.content.ContentValues r8) {
        /*
            r7 = this;
            java.lang.String r7 = "destination"
            java.lang.Integer r7 = r8.getAsInteger(r7)
            java.lang.String r0 = "is_visible_in_downloads_ui"
            if (r7 == 0) goto L6f
            int r1 = r7.intValue()
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L15
        L13:
            r2 = r4
            goto L5e
        L15:
            int r1 = r7.intValue()
            r5 = 4
            if (r1 == r5) goto L25
            int r1 = r7.intValue()
            r6 = 6
            if (r1 == r6) goto L25
        L23:
            r3 = r4
            goto L5e
        L25:
            int r7 = r7.intValue()
            if (r7 != r5) goto L3f
            java.lang.String r7 = "hint"
            java.lang.String r7 = r8.getAsString(r7)
            java.io.File r1 = new java.io.File
            android.net.Uri r7 = getFileUri(r7)
            java.lang.String r7 = r7.getPath()
            r1.<init>(r7)
            goto L4a
        L3f:
            java.io.File r1 = new java.io.File
            java.lang.String r7 = "_data"
            java.lang.String r7 = r8.getAsString(r7)
            r1.<init>(r7)
        L4a:
            java.lang.String r7 = r1.getAbsolutePath()
            boolean r7 = com.android.providers.downloads.Helpers.isFileInExternalAndroidDirs(r7)
            if (r7 == 0) goto L55
            goto L23
        L55:
            boolean r7 = com.android.providers.downloads.Helpers.isFilenameValidInPublicDownloadsDir(r1)
            if (r7 == 0) goto L5c
            goto L13
        L5c:
            r2 = r4
            r3 = r2
        L5e:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)
            java.lang.String r1 = "scanned"
            r8.put(r1, r7)
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r3)
            r8.put(r0, r7)
            goto L7a
        L6f:
            boolean r7 = r8.containsKey(r0)
            if (r7 != 0) goto L7a
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            r8.put(r0, r7)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.ensureDefaultColumns(android.content.ContentValues):void");
    }

    private void checkFileUriDestination(ContentValues contentValues) {
        String asString = contentValues.getAsString("hint");
        if (asString == null) {
            throw new IllegalArgumentException("DESTINATION_FILE_URI must include a file URI under COLUMN_FILE_NAME_HINT");
        }
        Uri fileUri = getFileUri(asString);
        if (fileUri == null) {
            throw new IllegalArgumentException("Not a file URI: " + fileUri);
        }
        String path = fileUri.getPath();
        if (path != null) {
            if (!("/" + path + "/").contains("/../")) {
                try {
                    File canonicalFile = new File(path).getCanonicalFile();
                    contentValues.put("hint", Uri.fromFile(canonicalFile).toString());
                    Helpers.checkDestinationFilePathRestrictions(canonicalFile, getCallingPackage(), getContext(), this.mAppOpsManager, getCallingAttributionTag(), this.mAppOpsManager.checkOp(87, Binder.getCallingUid(), getCallingPackage()) == 0, false);
                    return;
                } catch (IOException e) {
                    throw new SecurityException(e);
                }
            }
        }
        throw new IllegalArgumentException("Invalid file URI: " + fileUri);
    }

    private void checkDownloadedFilePath(ContentValues contentValues) {
        String asString = contentValues.getAsString("_data");
        if (asString != null) {
            if (!("/" + asString + "/").contains("/../")) {
                try {
                    File canonicalFile = new File(asString).getCanonicalFile();
                    contentValues.put("_data", canonicalFile.getPath());
                    if (!canonicalFile.exists()) {
                        throw new IllegalArgumentException("File doesn't exist: " + canonicalFile);
                    }
                    if (Binder.getCallingPid() == Process.myPid()) {
                        return;
                    }
                    Helpers.checkDestinationFilePathRestrictions(canonicalFile, getCallingPackage(), getContext(), this.mAppOpsManager, getCallingAttributionTag(), this.mAppOpsManager.checkOp(87, Binder.getCallingUid(), getCallingPackage()) == 0, true);
                    return;
                } catch (IOException e) {
                    throw new SecurityException(e);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid file path: ");
        if (asString == null) {
            asString = "null";
        }
        sb.append(asString);
        throw new IllegalArgumentException(sb.toString());
    }

    private void checkInsertPermissions(ContentValues contentValues) {
        if (getContext().checkCallingOrSelfPermission("android.permission.ACCESS_DOWNLOAD_MANAGER") == 0) {
            return;
        }
        getContext().enforceCallingOrSelfPermission("android.permission.INTERNET", "INTERNET permission is required to use the download manager");
        ContentValues contentValues2 = new ContentValues(contentValues);
        enforceAllowedValues(contentValues2, "is_public_api", Boolean.TRUE);
        if (contentValues2.getAsInteger("destination").intValue() == 6) {
            contentValues2.remove("total_bytes");
            contentValues2.remove("_data");
            contentValues2.remove("status");
        }
        enforceAllowedValues(contentValues2, "destination", 2, 4, 6);
        if (getContext().checkCallingOrSelfPermission("android.permission.DOWNLOAD_WITHOUT_NOTIFICATION") == 0) {
            enforceAllowedValues(contentValues2, "visibility", 2, 0, 1, 3);
        } else {
            enforceAllowedValues(contentValues2, "visibility", 0, 1, 3);
        }
        contentValues2.remove("uri");
        contentValues2.remove("title");
        contentValues2.remove("description");
        contentValues2.remove("mimetype");
        contentValues2.remove("hint");
        contentValues2.remove("notificationpackage");
        contentValues2.remove("allowed_network_types");
        contentValues2.remove("allow_roaming");
        contentValues2.remove("allow_metered");
        contentValues2.remove("flags");
        contentValues2.remove("is_visible_in_downloads_ui");
        contentValues2.remove("scanned");
        contentValues2.remove("allow_write");
        Iterator<Map.Entry<String, Object>> it = contentValues2.valueSet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().startsWith("http_header_")) {
                it.remove();
            }
        }
        if (contentValues2.size() > 0) {
            StringBuilder sb = new StringBuilder("Invalid columns in request: ");
            Iterator<Map.Entry<String, Object>> it2 = contentValues2.valueSet().iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().getKey());
            }
            throw new SecurityException(sb.toString());
        }
    }

    private void enforceAllowedValues(ContentValues contentValues, String str, Object... objArr) {
        Object obj = contentValues.get(str);
        contentValues.remove(str);
        for (Object obj2 : objArr) {
            if (obj == null && obj2 == null) {
                return;
            }
            if (obj != null && obj.equals(obj2)) {
                return;
            }
        }
        throw new SecurityException("Invalid value for " + str + ": " + obj);
    }

    private Cursor queryCleared(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return query(uri, strArr, str, strArr2, str2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.mOpenHelper.getReadableDatabase();
        int iMatch = sURIMatcher.match(uri);
        if (iMatch == -1) {
            if (Constants.LOGV) {
                Log.v("DownloadManager", "querying unknown URI: " + uri);
            }
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        if (iMatch == 3 || iMatch == 6) {
            if (strArr != null || str != null || str2 != null) {
                throw new UnsupportedOperationException("Request header queries do not support projections, selections or sorting");
            }
            getContext().enforceCallingOrSelfPermission("android.permission.ACCESS_ALL_DOWNLOADS", "DownloadManager");
            return getQueryBuilder(uri, iMatch).query(readableDatabase, new String[]{"header", "value"}, null, null, null, null, null);
        }
        if (Constants.LOGVV) {
            logVerboseQueryInfo(strArr, str, strArr2, str2, readableDatabase);
        }
        Cursor cursorQuery = getQueryBuilder(uri, iMatch).query(readableDatabase, strArr, str, strArr2, null, null, str2);
        if (cursorQuery != null) {
            cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
            if (Constants.LOGVV) {
                Log.v("DownloadManager", "created cursor " + cursorQuery + " on behalf of " + Binder.getCallingPid());
            }
        } else if (Constants.LOGV) {
            Log.v("DownloadManager", "query failed in downloads database");
        }
        return cursorQuery;
    }

    private void logVerboseQueryInfo(String[] strArr, String str, String[] strArr2, String str2, SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder();
        sb.append("starting query, database is ");
        if (sQLiteDatabase != null) {
            sb.append("not ");
        }
        sb.append("null; ");
        if (strArr == null) {
            sb.append("projection is null; ");
        } else if (strArr.length == 0) {
            sb.append("projection is empty; ");
        } else {
            for (int i = 0; i < strArr.length; i++) {
                sb.append("projection[");
                sb.append(i);
                sb.append("] is ");
                sb.append(strArr[i]);
                sb.append("; ");
            }
        }
        sb.append("selection is ");
        sb.append(str);
        sb.append("; ");
        if (strArr2 == null) {
            sb.append("selectionArgs is null; ");
        } else if (strArr2.length == 0) {
            sb.append("selectionArgs is empty; ");
        } else {
            for (int i2 = 0; i2 < strArr2.length; i2++) {
                sb.append("selectionArgs[");
                sb.append(i2);
                sb.append("] is ");
                sb.append(strArr2[i2]);
                sb.append("; ");
            }
        }
        sb.append("sort is ");
        sb.append(str2);
        sb.append(".");
        Log.v("DownloadManager", sb.toString());
    }

    private String getDownloadIdFromUri(Uri uri) {
        return uri.getPathSegments().get(1);
    }

    private void insertRequestHeaders(SQLiteDatabase sQLiteDatabase, long j, ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("download_id", Long.valueOf(j));
        for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
            if (entry.getKey().startsWith("http_header_")) {
                String string = entry.getValue().toString();
                if (!string.contains(":")) {
                    throw new IllegalArgumentException("Invalid HTTP header line: " + string);
                }
                String[] strArrSplit = string.split(":", 2);
                contentValues2.put("header", strArrSplit[0].trim());
                contentValues2.put("value", strArrSplit[1].trim());
                sQLiteDatabase.insert("request_headers", null, contentValues2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0280 A[PHI: r30
  0x0280: PHI (r30v4 com.android.providers.downloads.DownloadInfo$Reader) = 
  (r30v2 com.android.providers.downloads.DownloadInfo$Reader)
  (r30v5 com.android.providers.downloads.DownloadInfo$Reader)
 binds: [B:88:0x0202, B:85:0x01fd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:190:? A[Catch: all -> 0x02f4, SYNTHETIC, TRY_LEAVE, TryCatch #9 {all -> 0x02f4, blocks: (B:151:0x02f3, B:150:0x02f0, B:146:0x02ea), top: B:174:0x02ea, inners: #8 }] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int update(android.net.Uri r28, android.content.ContentValues r29, java.lang.String r30, java.lang.String[] r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 764
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.DownloadProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private void notifyContentChanged(Uri uri, int i) {
        Long lValueOf = (i == 2 || i == 5) ? Long.valueOf(Long.parseLong(getDownloadIdFromUri(uri))) : null;
        for (Uri uriWithAppendedId : BASE_URIS) {
            if (lValueOf != null) {
                uriWithAppendedId = ContentUris.withAppendedId(uriWithAppendedId, lValueOf.longValue());
            }
            getContext().getContentResolver().notifyChange(uriWithAppendedId, null);
        }
    }

    private SQLiteQueryBuilder getQueryBuilder(Uri uri, int i) {
        Map<String, String> map;
        StringBuilder sb = new StringBuilder();
        String str = "downloads";
        switch (i) {
            case 2:
                appendWhereExpression(sb, "_id=" + getDownloadIdFromUri(uri));
            case 1:
                Map<String, String> map2 = sDownloadsMap;
                if (getContext().checkCallingOrSelfPermission("android.permission.ACCESS_ALL_DOWNLOADS") != 0) {
                    appendWhereExpression(sb, "uid=" + Binder.getCallingUid() + " OR otheruid=" + Binder.getCallingUid());
                }
                map = map2;
                SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
                sQLiteQueryBuilder.setTables(str);
                sQLiteQueryBuilder.setProjectionMap(map);
                sQLiteQueryBuilder.setStrict(true);
                sQLiteQueryBuilder.setStrictColumns(true);
                sQLiteQueryBuilder.setStrictGrammar(true);
                sQLiteQueryBuilder.appendWhere(sb);
                return sQLiteQueryBuilder;
            case 3:
            case 6:
                Map<String, String> map3 = sHeadersMap;
                appendWhereExpression(sb, "download_id=" + getDownloadIdFromUri(uri));
                str = "request_headers";
                map = map3;
                SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
                sQLiteQueryBuilder2.setTables(str);
                sQLiteQueryBuilder2.setProjectionMap(map);
                sQLiteQueryBuilder2.setStrict(true);
                sQLiteQueryBuilder2.setStrictColumns(true);
                sQLiteQueryBuilder2.setStrictGrammar(true);
                sQLiteQueryBuilder2.appendWhere(sb);
                return sQLiteQueryBuilder2;
            case 5:
                appendWhereExpression(sb, "_id=" + getDownloadIdFromUri(uri));
            case 4:
                map = sDownloadsMap;
                SQLiteQueryBuilder sQLiteQueryBuilder22 = new SQLiteQueryBuilder();
                sQLiteQueryBuilder22.setTables(str);
                sQLiteQueryBuilder22.setProjectionMap(map);
                sQLiteQueryBuilder22.setStrict(true);
                sQLiteQueryBuilder22.setStrictColumns(true);
                sQLiteQueryBuilder22.setStrictGrammar(true);
                sQLiteQueryBuilder22.appendWhere(sb);
                return sQLiteQueryBuilder22;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    private static void appendWhereExpression(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append(" AND ");
        }
        sb.append('(');
        sb.append(str);
        sb.append(')');
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        Context context = getContext();
        ContentResolver contentResolver = context.getContentResolver();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        int iMatch = sURIMatcher.match(uri);
        if (iMatch == 1 || iMatch == 2 || iMatch == 4 || iMatch == 5) {
            SQLiteQueryBuilder queryBuilder = getQueryBuilder(uri, iMatch);
            Cursor cursorQuery = queryBuilder.query(writableDatabase, null, str, strArr, null, null, null);
            try {
                DownloadInfo.Reader reader = new DownloadInfo.Reader(contentResolver, cursorQuery);
                DownloadInfo downloadInfo = new DownloadInfo(context);
                while (cursorQuery.moveToNext()) {
                    reader.updateFromDatabase(downloadInfo);
                    jobScheduler.cancel((int) downloadInfo.mId);
                    revokeAllDownloadsPermission(downloadInfo.mId);
                    DownloadStorageProvider.onDownloadProviderDelete(getContext(), downloadInfo.mId);
                    String str2 = downloadInfo.mFileName;
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            File canonicalFile = new File(str2).getCanonicalFile();
                            if (Helpers.isFilenameValid(getContext(), canonicalFile)) {
                                Log.v("DownloadManager", "Deleting " + canonicalFile + " via provider delete");
                                canonicalFile.delete();
                                MediaStore.scanFile(getContext().getContentResolver(), canonicalFile);
                            } else {
                                Log.d("DownloadManager", "Ignoring invalid file: " + canonicalFile);
                            }
                        } catch (IOException e) {
                            Log.e("DownloadManager", "Couldn't delete file: " + str2, e);
                        }
                    }
                    if (!Downloads.Impl.isStatusCompleted(downloadInfo.mStatus)) {
                        downloadInfo.sendIntentIfRequested();
                    }
                    writableDatabase.delete("request_headers", "download_id=?", new String[]{Long.toString(downloadInfo.mId)});
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                int iDelete = queryBuilder.delete(writableDatabase, str, strArr);
                notifyContentChanged(uri, iMatch);
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Helpers.getDownloadNotifier(getContext()).update();
                    return iDelete;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } finally {
            }
        } else {
            Log.d("DownloadManager", "deleting unknown/invalid URI: " + uri);
            throw new UnsupportedOperationException("Cannot delete URI: " + uri);
        }
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(final Uri uri, String str) throws FileNotFoundException {
        int count;
        if (Constants.LOGVV) {
            logVerboseOpenFileInfo(uri, str);
        }
        Cursor cursorQuery = query(uri, new String[]{"_data"}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() != 0) {
                    IoUtils.closeQuietly(cursorQuery);
                    Cursor cursorQueryCleared = queryCleared(uri, new String[]{"_data", "status", "destination", "scanned"}, null, null, null);
                    final boolean z = false;
                    if (cursorQueryCleared != null) {
                        try {
                            count = cursorQueryCleared.getCount();
                        } catch (Throwable th) {
                            IoUtils.closeQuietly(cursorQueryCleared);
                            throw th;
                        }
                    } else {
                        count = 0;
                    }
                    if (count != 1) {
                        if (count == 0) {
                            throw new FileNotFoundException("No entry for " + uri);
                        }
                        throw new FileNotFoundException("Multiple items at " + uri);
                    }
                    if (cursorQueryCleared.moveToFirst()) {
                        int i = cursorQueryCleared.getInt(1);
                        int i2 = cursorQueryCleared.getInt(2);
                        int i3 = cursorQueryCleared.getInt(3);
                        String string = cursorQueryCleared.getString(0);
                        if (Downloads.Impl.isStatusSuccess(i) && ((i2 == 0 || i2 == 4 || i2 == 6) && i3 != 2)) {
                            z = true;
                        }
                        IoUtils.closeQuietly(cursorQueryCleared);
                        if (string == null) {
                            throw new FileNotFoundException("No filename found.");
                        }
                        try {
                            final File canonicalFile = new File(string).getCanonicalFile();
                            if (!Helpers.isFilenameValid(getContext(), canonicalFile)) {
                                throw new FileNotFoundException("Invalid file: " + canonicalFile);
                            }
                            int mode = ParcelFileDescriptor.parseMode(str);
                            if (mode == 268435456) {
                                return ParcelFileDescriptor.open(canonicalFile, mode);
                            }
                            try {
                                return ParcelFileDescriptor.open(canonicalFile, mode, Helpers.getAsyncHandler(), new ParcelFileDescriptor.OnCloseListener() { // from class: com.android.providers.downloads.DownloadProvider.1
                                    @Override // android.os.ParcelFileDescriptor.OnCloseListener
                                    public void onClose(IOException iOException) throws Throwable {
                                        ContentValues contentValues = new ContentValues();
                                        contentValues.put("total_bytes", Long.valueOf(canonicalFile.length()));
                                        contentValues.put("lastmod", Long.valueOf(System.currentTimeMillis()));
                                        DownloadProvider.this.update(uri, contentValues, null, null);
                                        if (z) {
                                            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                                            intent.setData(Uri.fromFile(canonicalFile));
                                            DownloadProvider.this.getContext().sendBroadcast(intent);
                                        }
                                    }
                                });
                            } catch (IOException e) {
                                throw new FileNotFoundException("Failed to open for writing: " + e);
                            }
                        } catch (IOException e2) {
                            throw new FileNotFoundException(e2.getMessage());
                        }
                    }
                    throw new FileNotFoundException("Failed moveToFirst");
                }
            } catch (Throwable th2) {
                IoUtils.closeQuietly(cursorQuery);
                throw th2;
            }
        }
        throw new FileNotFoundException("No file found for " + uri + " as UID " + Binder.getCallingUid());
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ", 120);
        indentingPrintWriter.println("Downloads updated in last hour:");
        indentingPrintWriter.increaseIndent();
        Cursor cursorQuery = this.mOpenHelper.getReadableDatabase().query("downloads", null, "lastmod>" + (this.mSystemFacade.currentTimeMillis() - 3600000), null, null, null, "_id ASC");
        try {
            String[] columnNames = cursorQuery.getColumnNames();
            int columnIndex = cursorQuery.getColumnIndex("_id");
            while (cursorQuery.moveToNext()) {
                indentingPrintWriter.println("Download #" + cursorQuery.getInt(columnIndex) + ":");
                indentingPrintWriter.increaseIndent();
                for (int i = 0; i < columnNames.length; i++) {
                    if (!"cookiedata".equals(columnNames[i])) {
                        indentingPrintWriter.printPair(columnNames[i], cursorQuery.getString(i));
                    }
                }
                indentingPrintWriter.println();
                indentingPrintWriter.decreaseIndent();
            }
            cursorQuery.close();
            indentingPrintWriter.decreaseIndent();
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    private void logVerboseOpenFileInfo(Uri uri, String str) {
        Log.v("DownloadManager", "openFile uri: " + uri + ", mode: " + str + ", uid: " + Binder.getCallingUid());
        Cursor cursorQuery = query(Downloads.Impl.CONTENT_URI, new String[]{"_id"}, null, null, "_id");
        if (cursorQuery == null) {
            Log.v("DownloadManager", "null cursor in openFile");
        } else {
            try {
                if (!cursorQuery.moveToFirst()) {
                    Log.v("DownloadManager", "empty cursor in openFile");
                } else {
                    do {
                        Log.v("DownloadManager", "row " + cursorQuery.getInt(0) + " available");
                    } while (cursorQuery.moveToNext());
                }
            } finally {
            }
        }
        cursorQuery = query(uri, new String[]{"_data"}, null, null, null);
        if (cursorQuery == null) {
            Log.v("DownloadManager", "null cursor in openFile");
            return;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                Log.v("DownloadManager", "empty cursor in openFile");
            } else {
                String string = cursorQuery.getString(0);
                Log.v("DownloadManager", "filename in openFile: " + string);
                if (new File(string).isFile()) {
                    Log.v("DownloadManager", "file exists in openFile");
                }
            }
        } finally {
        }
    }

    private static final void copyInteger(String str, ContentValues contentValues, ContentValues contentValues2) {
        Integer asInteger = contentValues.getAsInteger(str);
        if (asInteger != null) {
            contentValues2.put(str, asInteger);
        }
    }

    private static final void copyBoolean(String str, ContentValues contentValues, ContentValues contentValues2) {
        Boolean asBoolean = contentValues.getAsBoolean(str);
        if (asBoolean != null) {
            contentValues2.put(str, asBoolean);
        }
    }

    private static final void copyString(String str, ContentValues contentValues, ContentValues contentValues2) {
        String asString = contentValues.getAsString(str);
        if (asString != null) {
            contentValues2.put(str, asString);
        }
    }

    private static final void copyStringWithDefault(String str, ContentValues contentValues, ContentValues contentValues2, String str2) {
        copyString(str, contentValues, contentValues2);
        if (contentValues2.containsKey(str)) {
            return;
        }
        contentValues2.put(str, str2);
    }

    private void grantAllDownloadsPermission(String str, long j) {
        getContext().grantUriPermission(str, ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, j), 3);
    }

    private void revokeAllDownloadsPermission(long j) {
        getContext().revokeUriPermission(ContentUris.withAppendedId(Downloads.Impl.ALL_DOWNLOADS_CONTENT_URI, j), -1);
    }
}
