package com.android.launcher3;

import android.app.backup.BackupManager;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.android.launcher3.AutoInstallsLayout;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.model.DbDowngradeHelper;
import com.android.launcher3.provider.LauncherDbUtils;
import com.android.launcher3.provider.RestoreDbTask;
import com.android.launcher3.util.IOUtils;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSet;
import com.android.launcher3.util.NoLocaleSQLiteHelper;
import com.android.launcher3.util.Preconditions;
import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.function.Supplier;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherProvider extends ContentProvider {
    public static final String AUTHORITY = "com.android.launcher3.settings";
    private static final String DOWNGRADE_SCHEMA_FILE = "downgrade_schema.json";
    static final String EMPTY_DATABASE_CREATED = "EMPTY_DATABASE_CREATED";
    private static final boolean LOGD = false;
    public static final int SCHEMA_VERSION = 28;
    private static final String TAG = "LauncherProvider";
    private Handler mListenerHandler;
    private final ChangeListenerWrapper mListenerWrapper = new ChangeListenerWrapper();
    protected DatabaseHelper mOpenHelper;

    static /* synthetic */ XmlPullParser lambda$createWorkspaceLoaderFromAppRestriction$0(XmlPullParser xmlPullParser) {
        return xmlPullParser;
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        LauncherAppState instanceNoCreate = LauncherAppState.getInstanceNoCreate();
        if (instanceNoCreate == null || !instanceNoCreate.getModel().isModelLoaded()) {
            return;
        }
        instanceNoCreate.getModel().dumpState("", fd, writer, args);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Log.e("pengbin", "LauncherProvider......onCreate");
        this.mListenerHandler = new Handler(this.mListenerWrapper);
        MainProcessInitializer.initialize(getContext().getApplicationContext());
        return true;
    }

    public void setLauncherProviderChangeListener(LauncherProviderChangeListener listener) {
        Preconditions.assertUIThread();
        this.mListenerWrapper.mListener = listener;
    }

    @Override // android.content.ContentProvider, android.content.ContentInterface
    public String getType(Uri uri) {
        SqlArguments sqlArguments = new SqlArguments(uri, null, null);
        if (TextUtils.isEmpty(sqlArguments.where)) {
            return "vnd.android.cursor.dir/" + sqlArguments.table;
        }
        return "vnd.android.cursor.item/" + sqlArguments.table;
    }

    protected synchronized void createDbIfNotExists() {
        if (this.mOpenHelper == null) {
            this.mOpenHelper = new DatabaseHelper(getContext(), this.mListenerHandler);
            if (RestoreDbTask.isPending(getContext())) {
                if (!RestoreDbTask.performRestore(getContext(), this.mOpenHelper, new BackupManager(getContext()))) {
                    DatabaseHelper databaseHelper = this.mOpenHelper;
                    databaseHelper.createEmptyDB(databaseHelper.getWritableDatabase());
                }
                RestoreDbTask.setPending(getContext(), false);
            }
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        createDbIfNotExists();
        SqlArguments sqlArguments = new SqlArguments(uri, selection, selectionArgs);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(sqlArguments.table);
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.mOpenHelper.getWritableDatabase(), projection, sqlArguments.where, sqlArguments.args, null, null, sortOrder);
        cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
        return cursorQuery;
    }

    static int dbInsertAndCheck(DatabaseHelper helper, SQLiteDatabase db, String table, String nullColumnHack, ContentValues values) {
        if (values == null) {
            throw new RuntimeException("Error: attempting to insert null values");
        }
        if (!values.containsKey("_id")) {
            throw new RuntimeException("Error: attempting to add item without specifying an id");
        }
        helper.checkId(values);
        return (int) db.insert(table, nullColumnHack, values);
    }

    private void reloadLauncherIfExternal() {
        LauncherAppState instanceNoCreate;
        if (Binder.getCallingPid() == Process.myPid() || (instanceNoCreate = LauncherAppState.getInstanceNoCreate()) == null) {
            return;
        }
        instanceNoCreate.getModel().forceReload();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues initialValues) {
        createDbIfNotExists();
        SqlArguments sqlArguments = new SqlArguments(uri);
        if (Binder.getCallingPid() != Process.myPid() && !initializeExternalAdd(initialValues)) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        addModifiedTime(initialValues);
        int iDbInsertAndCheck = dbInsertAndCheck(this.mOpenHelper, writableDatabase, sqlArguments.table, null, initialValues);
        if (iDbInsertAndCheck < 0) {
            return null;
        }
        this.mOpenHelper.onAddOrDeleteOp(writableDatabase);
        Uri uriWithAppendedId = ContentUris.withAppendedId(uri, iDbInsertAndCheck);
        notifyListeners();
        reloadLauncherIfExternal();
        return uriWithAppendedId;
    }

    private boolean initializeExternalAdd(ContentValues values) {
        values.put("_id", Integer.valueOf(this.mOpenHelper.generateNewItemId()));
        Integer asInteger = values.getAsInteger(LauncherSettings.Favorites.ITEM_TYPE);
        if (asInteger == null || asInteger.intValue() != 4 || values.containsKey("appWidgetId")) {
            return true;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(values.getAsString("appWidgetProvider"));
        if (componentNameUnflattenFromString != null) {
            try {
                AppWidgetHost appWidgetHostNewLauncherWidgetHost = this.mOpenHelper.newLauncherWidgetHost();
                int iAllocateAppWidgetId = appWidgetHostNewLauncherWidgetHost.allocateAppWidgetId();
                values.put("appWidgetId", Integer.valueOf(iAllocateAppWidgetId));
                if (appWidgetManager.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, componentNameUnflattenFromString)) {
                    return true;
                }
                appWidgetHostNewLauncherWidgetHost.deleteAppWidgetId(iAllocateAppWidgetId);
                return false;
            } catch (RuntimeException e) {
                Log.e(TAG, "Failed to initialize external widget", e);
            }
        }
        return false;
    }

    @Override // android.content.ContentProvider, android.content.ContentInterface
    public int bulkInsert(Uri uri, ContentValues[] values) {
        createDbIfNotExists();
        SqlArguments sqlArguments = new SqlArguments(uri);
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(writableDatabase);
        try {
            int length = values.length;
            for (int i = 0; i < length; i++) {
                addModifiedTime(values[i]);
                if (dbInsertAndCheck(this.mOpenHelper, writableDatabase, sqlArguments.table, null, values[i]) < 0) {
                    sQLiteTransaction.close();
                    return 0;
                }
            }
            this.mOpenHelper.onAddOrDeleteOp(writableDatabase);
            sQLiteTransaction.commit();
            sQLiteTransaction.close();
            notifyListeners();
            reloadLauncherIfExternal();
            return values.length;
        } catch (Throwable th) {
            try {
                sQLiteTransaction.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        createDbIfNotExists();
        LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(this.mOpenHelper.getWritableDatabase());
        try {
            int size = operations.size();
            ContentProviderResult[] contentProviderResultArr = new ContentProviderResult[size];
            boolean z = false;
            for (int i = 0; i < size; i++) {
                ContentProviderOperation contentProviderOperation = operations.get(i);
                contentProviderResultArr[i] = contentProviderOperation.apply(this, contentProviderResultArr, i);
                z |= (contentProviderOperation.isInsert() || contentProviderOperation.isDelete()) && contentProviderResultArr[i].count != null && contentProviderResultArr[i].count.intValue() > 0;
            }
            if (z) {
                this.mOpenHelper.onAddOrDeleteOp(sQLiteTransaction.getDb());
            }
            sQLiteTransaction.commit();
            reloadLauncherIfExternal();
            sQLiteTransaction.close();
            return contentProviderResultArr;
        } catch (Throwable th) {
            try {
                sQLiteTransaction.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        createDbIfNotExists();
        SqlArguments sqlArguments = new SqlArguments(uri, selection, selectionArgs);
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        if (Binder.getCallingPid() != Process.myPid() && "favorites".equalsIgnoreCase(sqlArguments.table)) {
            DatabaseHelper databaseHelper = this.mOpenHelper;
            databaseHelper.removeGhostWidgets(databaseHelper.getWritableDatabase());
        }
        int iDelete = writableDatabase.delete(sqlArguments.table, sqlArguments.where, sqlArguments.args);
        if (iDelete > 0) {
            this.mOpenHelper.onAddOrDeleteOp(writableDatabase);
            notifyListeners();
            reloadLauncherIfExternal();
        }
        return iDelete;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        createDbIfNotExists();
        SqlArguments sqlArguments = new SqlArguments(uri, selection, selectionArgs);
        addModifiedTime(values);
        int iUpdate = this.mOpenHelper.getWritableDatabase().update(sqlArguments.table, values, sqlArguments.where, sqlArguments.args);
        if (iUpdate > 0) {
            notifyListeners();
        }
        reloadLauncherIfExternal();
        return iUpdate;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String method, final String arg, final Bundle extras) {
        if (Binder.getCallingUid() != Process.myUid()) {
            return null;
        }
        createDbIfNotExists();
        method.hashCode();
        switch (method) {
            case "delete_empty_folders":
                Bundle bundle = new Bundle();
                bundle.putIntArray("value", deleteEmptyFolders().toArray());
                break;
            case "remove_ghost_widgets":
                DatabaseHelper databaseHelper = this.mOpenHelper;
                databaseHelper.removeGhostWidgets(databaseHelper.getWritableDatabase());
                break;
            case "generate_new_item_id":
                Bundle bundle2 = new Bundle();
                bundle2.putInt("value", this.mOpenHelper.generateNewItemId());
                break;
            case "generate_new_screen_id":
                Bundle bundle3 = new Bundle();
                bundle3.putInt("value", this.mOpenHelper.generateNewScreenId());
                break;
            case "clear_empty_db_flag":
                clearFlagEmptyDbCreated();
                break;
            case "load_default_favorites":
                loadDefaultFavoritesIfNecessary();
                break;
            case "get_empty_db_flag":
                Bundle bundle4 = new Bundle();
                bundle4.putBoolean("value", Utilities.getPrefs(getContext()).getBoolean(EMPTY_DATABASE_CREATED, false));
                break;
            case "refresh_backup_table":
                DatabaseHelper databaseHelper2 = this.mOpenHelper;
                databaseHelper2.mBackupTableExists = LauncherDbUtils.tableExists(databaseHelper2.getReadableDatabase(), LauncherSettings.Favorites.BACKUP_TABLE_NAME);
                break;
            case "new_db_transaction":
                Bundle bundle5 = new Bundle();
                bundle5.putBinder("value", new LauncherDbUtils.SQLiteTransaction(this.mOpenHelper.getWritableDatabase()));
                break;
            case "create_empty_db":
                DatabaseHelper databaseHelper3 = this.mOpenHelper;
                databaseHelper3.createEmptyDB(databaseHelper3.getWritableDatabase());
                break;
        }
        return null;
    }

    private IntArray deleteEmptyFolders() {
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        try {
            LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(writableDatabase);
            try {
                IntArray intArrayQueryIntArray = LauncherDbUtils.queryIntArray(writableDatabase, "favorites", "_id", "itemType = 2 AND _id NOT IN (SELECT container FROM favorites)", null, null);
                if (!intArrayQueryIntArray.isEmpty()) {
                    writableDatabase.delete("favorites", Utilities.createDbSelectionQuery("_id", intArrayQueryIntArray), null);
                }
                sQLiteTransaction.commit();
                sQLiteTransaction.close();
                return intArrayQueryIntArray;
            } finally {
            }
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
            return new IntArray();
        }
    }

    protected void notifyListeners() {
        this.mListenerHandler.sendEmptyMessage(1);
    }

    static void addModifiedTime(ContentValues values) {
        values.put("modified", Long.valueOf(System.currentTimeMillis()));
    }

    private void clearFlagEmptyDbCreated() {
        Utilities.getPrefs(getContext()).edit().remove(EMPTY_DATABASE_CREATED).commit();
    }

    private synchronized void loadDefaultFavoritesIfNecessary() {
        Partner partner;
        Resources resources;
        int identifier;
        if (Utilities.getPrefs(getContext()).getBoolean(EMPTY_DATABASE_CREATED, false)) {
            Log.d(TAG, "loading default workspace");
            AppWidgetHost appWidgetHostNewLauncherWidgetHost = this.mOpenHelper.newLauncherWidgetHost();
            AutoInstallsLayout autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction = createWorkspaceLoaderFromAppRestriction(appWidgetHostNewLauncherWidgetHost);
            if (autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction == null) {
                autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction = AutoInstallsLayout.get(getContext(), appWidgetHostNewLauncherWidgetHost, this.mOpenHelper);
            }
            if (autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction == null && (partner = Partner.get(getContext().getPackageManager())) != null && partner.hasDefaultLayout() && (identifier = (resources = partner.getResources()).getIdentifier(Partner.RES_DEFAULT_LAYOUT, "xml", partner.getPackageName())) != 0) {
                autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction = new DefaultLayoutParser(getContext(), appWidgetHostNewLauncherWidgetHost, this.mOpenHelper, resources, identifier);
            }
            boolean z = autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction != null;
            if (autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction == null) {
                autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction = getDefaultLayoutParser(appWidgetHostNewLauncherWidgetHost);
            }
            DatabaseHelper databaseHelper = this.mOpenHelper;
            databaseHelper.createEmptyDB(databaseHelper.getWritableDatabase());
            DatabaseHelper databaseHelper2 = this.mOpenHelper;
            if (databaseHelper2.loadFavorites(databaseHelper2.getWritableDatabase(), autoInstallsLayoutCreateWorkspaceLoaderFromAppRestriction) <= 0 && z) {
                DatabaseHelper databaseHelper3 = this.mOpenHelper;
                databaseHelper3.createEmptyDB(databaseHelper3.getWritableDatabase());
                DatabaseHelper databaseHelper4 = this.mOpenHelper;
                databaseHelper4.loadFavorites(databaseHelper4.getWritableDatabase(), getDefaultLayoutParser(appWidgetHostNewLauncherWidgetHost));
            }
            clearFlagEmptyDbCreated();
        }
    }

    private AutoInstallsLayout createWorkspaceLoaderFromAppRestriction(AppWidgetHost widgetHost) throws Throwable {
        Context context = getContext();
        InvariantDeviceProfile idp = LauncherAppState.getIDP(context);
        String string = Settings.Secure.getString(context.getContentResolver(), "launcher3.layout.provider");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider(string, 0);
        if (providerInfoResolveContentProvider == null) {
            Log.e(TAG, "No provider found for authority " + string);
            return null;
        }
        try {
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(new Uri.Builder().scheme("content").authority(string).path("launcher_layout").appendQueryParameter("version", "1").appendQueryParameter("gridWidth", Integer.toString(idp.numColumns)).appendQueryParameter("gridHeight", Integer.toString(idp.numRows)).appendQueryParameter("hotseatSize", Integer.toString(idp.numHotseatIcons)).build());
            try {
                String str = new String(IOUtils.toByteArray(inputStreamOpenInputStream));
                final XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                xmlPullParserNewPullParser.setInput(new StringReader(str));
                Log.d(TAG, "Loading layout from " + string);
                AutoInstallsLayout autoInstallsLayout = new AutoInstallsLayout(context, widgetHost, this.mOpenHelper, context.getPackageManager().getResourcesForApplication(providerInfoResolveContentProvider.applicationInfo), (Supplier<XmlPullParser>) new Supplier() { // from class: com.android.launcher3.-$$Lambda$LauncherProvider$FalrF7_eVqGanvwCGrVR1NiuXdE
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return LauncherProvider.lambda$createWorkspaceLoaderFromAppRestriction$0(xmlPullParserNewPullParser);
                    }
                }, AutoInstallsLayout.TAG_WORKSPACE);
                if (inputStreamOpenInputStream != null) {
                    inputStreamOpenInputStream.close();
                }
                return autoInstallsLayout;
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting layout stream from: " + string, e);
            return null;
        }
    }

    private DefaultLayoutParser getDefaultLayoutParser(AppWidgetHost widgetHost) {
        InvariantDeviceProfile idp = LauncherAppState.getIDP(getContext());
        int i = idp.defaultLayoutId;
        if (UserManagerCompat.getInstance(getContext()).isDemoUser() && idp.demoModeLayoutId != 0) {
            i = idp.demoModeLayoutId;
        }
        return new DefaultLayoutParser(getContext(), widgetHost, this.mOpenHelper, getContext().getResources(), i);
    }

    public static class DatabaseHelper extends NoLocaleSQLiteHelper implements AutoInstallsLayout.LayoutParserCallback {
        private final BackupManager mBackupManager;
        private boolean mBackupTableExists;
        private final Context mContext;
        private int mMaxItemId;
        private int mMaxScreenId;
        private final Handler mWidgetHostResetHandler;

        DatabaseHelper(Context context, Handler widgetHostResetHandler) {
            this(context, widgetHostResetHandler, LauncherFiles.LAUNCHER_DB);
            if (!LauncherDbUtils.tableExists(getReadableDatabase(), "favorites")) {
                Log.e(LauncherProvider.TAG, "Tables are missing after onCreate has been called. Trying to recreate");
                addFavoritesTable(getWritableDatabase(), true);
            }
            this.mBackupTableExists = LauncherDbUtils.tableExists(getReadableDatabase(), LauncherSettings.Favorites.BACKUP_TABLE_NAME);
            initIds();
        }

        public DatabaseHelper(Context context, Handler widgetHostResetHandler, String tableName) {
            super(context, tableName, 28);
            this.mMaxItemId = -1;
            this.mMaxScreenId = -1;
            this.mContext = context;
            this.mWidgetHostResetHandler = widgetHostResetHandler;
            this.mBackupManager = new BackupManager(context);
        }

        protected void initIds() {
            if (this.mMaxItemId == -1) {
                this.mMaxItemId = initializeMaxItemId(getWritableDatabase());
            }
            if (this.mMaxScreenId == -1) {
                this.mMaxScreenId = initializeMaxScreenId(getWritableDatabase());
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            this.mMaxItemId = 1;
            this.mMaxScreenId = 0;
            addFavoritesTable(db, false);
            this.mMaxItemId = initializeMaxItemId(db);
            onEmptyDbCreated();
        }

        protected void onAddOrDeleteOp(SQLiteDatabase db) {
            if (this.mBackupTableExists) {
                LauncherDbUtils.dropTable(db, LauncherSettings.Favorites.BACKUP_TABLE_NAME);
                this.mBackupTableExists = false;
            }
        }

        protected void onEmptyDbCreated() {
            if (this.mWidgetHostResetHandler != null) {
                newLauncherWidgetHost().deleteHost();
                this.mWidgetHostResetHandler.sendEmptyMessage(2);
            }
            Utilities.getPrefs(this.mContext).edit().putBoolean(LauncherProvider.EMPTY_DATABASE_CREATED, true).commit();
        }

        public long getSerialNumberForUser(UserHandle user) {
            return UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(user);
        }

        public long getDefaultUserSerial() {
            return getSerialNumberForUser(Process.myUserHandle());
        }

        private void addFavoritesTable(SQLiteDatabase db, boolean optional) {
            LauncherSettings.Favorites.addTableToDb(db, getDefaultUserSerial(), optional);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            File fileStreamPath = this.mContext.getFileStreamPath(LauncherProvider.DOWNGRADE_SCHEMA_FILE);
            if (!fileStreamPath.exists()) {
                handleOneTimeDataUpgrade(db);
            }
            DbDowngradeHelper.updateSchemaFile(fileStreamPath, 28, this.mContext);
        }

        protected void handleOneTimeDataUpgrade(SQLiteDatabase db) {
            UserManagerCompat userManagerCompat = UserManagerCompat.getInstance(this.mContext);
            Iterator<UserHandle> it = userManagerCompat.getUserProfiles().iterator();
            while (it.hasNext()) {
                db.execSQL("update favorites set intent = replace(intent, ';l.profile=" + userManagerCompat.getSerialNumberForUser(it.next()) + ";', ';') where itemType = 0;");
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x004d, code lost:
        
            if (addIntegerColumn(r10, "options", 0) == false) goto L44;
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
        @Override // android.database.sqlite.SQLiteOpenHelper
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onUpgrade(android.database.sqlite.SQLiteDatabase r10, int r11, int r12) {
            /*
                Method dump skipped, instruction units count: 290
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.LauncherProvider.DatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                DbDowngradeHelper.parse(this.mContext.getFileStreamPath(LauncherProvider.DOWNGRADE_SCHEMA_FILE)).onDowngrade(db, oldVersion, newVersion);
            } catch (Exception e) {
                Log.d(LauncherProvider.TAG, "Unable to downgrade from: " + oldVersion + " to " + newVersion + ". Wiping databse.", e);
                createEmptyDB(db);
            }
        }

        public void createEmptyDB(SQLiteDatabase db) {
            LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(db);
            try {
                LauncherDbUtils.dropTable(db, "favorites");
                LauncherDbUtils.dropTable(db, "workspaceScreens");
                onCreate(db);
                sQLiteTransaction.commit();
                sQLiteTransaction.close();
            } catch (Throwable th) {
                try {
                    sQLiteTransaction.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        public void removeGhostWidgets(SQLiteDatabase db) {
            AppWidgetHost appWidgetHostNewLauncherWidgetHost = newLauncherWidgetHost();
            try {
                int[] appWidgetIds = appWidgetHostNewLauncherWidgetHost.getAppWidgetIds();
                IntSet intSetWrap = IntSet.wrap(LauncherDbUtils.queryIntArray(db, "favorites", "appWidgetId", "itemType=4", null, null));
                for (int i : appWidgetIds) {
                    if (!intSetWrap.contains(i)) {
                        try {
                            FileLog.d(LauncherProvider.TAG, "Deleting invalid widget " + i);
                            appWidgetHostNewLauncherWidgetHost.deleteAppWidgetId(i);
                        } catch (RuntimeException unused) {
                        }
                    }
                }
            } catch (IncompatibleClassChangeError e) {
                Log.e(LauncherProvider.TAG, "getAppWidgetIds not supported", e);
            }
        }

        void convertShortcutsToLauncherActivities(SQLiteDatabase db) {
            try {
                LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(db);
                try {
                    Cursor cursorQuery = db.query("favorites", new String[]{"_id", "intent"}, "itemType=1 AND profileId=" + getDefaultUserSerial(), null, null, null, null);
                    try {
                        SQLiteStatement sQLiteStatementCompileStatement = db.compileStatement("UPDATE favorites SET itemType=0 WHERE _id=?");
                        try {
                            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
                            int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("intent");
                            while (cursorQuery.moveToNext()) {
                                try {
                                    if (Utilities.isLauncherAppTarget(Intent.parseUri(cursorQuery.getString(columnIndexOrThrow2), 0))) {
                                        sQLiteStatementCompileStatement.bindLong(1, cursorQuery.getInt(columnIndexOrThrow));
                                        sQLiteStatementCompileStatement.executeUpdateDelete();
                                    }
                                } catch (URISyntaxException e) {
                                    Log.e(LauncherProvider.TAG, "Unable to parse intent", e);
                                }
                            }
                            sQLiteTransaction.commit();
                            if (sQLiteStatementCompileStatement != null) {
                                sQLiteStatementCompileStatement.close();
                            }
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            sQLiteTransaction.close();
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (SQLException e2) {
                Log.w(LauncherProvider.TAG, "Error deduping shortcuts", e2);
            }
        }

        boolean updateFolderItemsRank(SQLiteDatabase db, boolean addRankColumn) {
            try {
                LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(db);
                if (addRankColumn) {
                    try {
                        db.execSQL("ALTER TABLE favorites ADD COLUMN rank INTEGER NOT NULL DEFAULT 0;");
                    } finally {
                    }
                }
                Cursor cursorRawQuery = db.rawQuery("SELECT container, MAX(cellX) FROM favorites WHERE container IN (SELECT _id FROM favorites WHERE itemType = ?) GROUP BY container;", new String[]{Integer.toString(2)});
                while (cursorRawQuery.moveToNext()) {
                    db.execSQL("UPDATE favorites SET rank=cellX+(cellY*?) WHERE container=? AND cellX IS NOT NULL AND cellY IS NOT NULL;", new Object[]{Long.valueOf(cursorRawQuery.getLong(1) + 1), Long.valueOf(cursorRawQuery.getLong(0))});
                }
                cursorRawQuery.close();
                sQLiteTransaction.commit();
                sQLiteTransaction.close();
                return true;
            } catch (SQLException e) {
                Log.e(LauncherProvider.TAG, e.getMessage(), e);
                return false;
            }
        }

        private boolean addIntegerColumn(SQLiteDatabase db, String columnName, long defaultValue) {
            try {
                LauncherDbUtils.SQLiteTransaction sQLiteTransaction = new LauncherDbUtils.SQLiteTransaction(db);
                try {
                    db.execSQL("ALTER TABLE favorites ADD COLUMN " + columnName + " INTEGER NOT NULL DEFAULT " + defaultValue + ";");
                    sQLiteTransaction.commit();
                    sQLiteTransaction.close();
                    return true;
                } finally {
                }
            } catch (SQLException e) {
                Log.e(LauncherProvider.TAG, e.getMessage(), e);
                return false;
            }
        }

        @Override // com.android.launcher3.AutoInstallsLayout.LayoutParserCallback
        public int generateNewItemId() {
            int i = this.mMaxItemId;
            if (i < 0) {
                throw new RuntimeException("Error: max item id was not initialized");
            }
            int i2 = i + 1;
            this.mMaxItemId = i2;
            return i2;
        }

        public AppWidgetHost newLauncherWidgetHost() {
            return new LauncherAppWidgetHost(this.mContext);
        }

        @Override // com.android.launcher3.AutoInstallsLayout.LayoutParserCallback
        public int insertAndCheck(SQLiteDatabase db, ContentValues values) {
            return LauncherProvider.dbInsertAndCheck(this, db, "favorites", null, values);
        }

        public void checkId(ContentValues values) {
            this.mMaxItemId = Math.max(values.getAsInteger("_id").intValue(), this.mMaxItemId);
            Integer asInteger = values.getAsInteger(LauncherSettings.Favorites.SCREEN);
            Integer asInteger2 = values.getAsInteger(LauncherSettings.Favorites.CONTAINER);
            if (asInteger == null || asInteger2 == null || asInteger2.intValue() != -100) {
                return;
            }
            this.mMaxScreenId = Math.max(asInteger.intValue(), this.mMaxScreenId);
        }

        private int initializeMaxItemId(SQLiteDatabase db) {
            return LauncherProvider.getMaxId(db, "SELECT MAX(%1$s) FROM %2$s", "_id", "favorites");
        }

        public int generateNewScreenId() {
            int i = this.mMaxScreenId;
            if (i < 0) {
                throw new RuntimeException("Error: max screen id was not initialized");
            }
            int i2 = i + 1;
            this.mMaxScreenId = i2;
            return i2;
        }

        private int initializeMaxScreenId(SQLiteDatabase db) {
            return LauncherProvider.getMaxId(db, "SELECT MAX(%1$s) FROM %2$s WHERE %3$s = %4$d", LauncherSettings.Favorites.SCREEN, "favorites", LauncherSettings.Favorites.CONTAINER, -100);
        }

        int loadFavorites(SQLiteDatabase db, AutoInstallsLayout loader) {
            int iLoadLayout = loader.loadLayout(db, new IntArray());
            this.mMaxItemId = initializeMaxItemId(db);
            this.mMaxScreenId = initializeMaxScreenId(db);
            return iLoadLayout;
        }
    }

    static int getMaxId(SQLiteDatabase db, String query, Object... args) {
        int iLongForQuery = (int) DatabaseUtils.longForQuery(db, String.format(Locale.ENGLISH, query, args), null);
        if (iLongForQuery >= 0) {
            return iLongForQuery;
        }
        throw new RuntimeException("Error: could not query max id");
    }

    static class SqlArguments {
        public final String[] args;
        public final String table;
        public final String where;

        SqlArguments(Uri url, String where, String[] args) {
            if (url.getPathSegments().size() == 1) {
                this.table = url.getPathSegments().get(0);
                this.where = where;
                this.args = args;
            } else {
                if (url.getPathSegments().size() != 2) {
                    throw new IllegalArgumentException("Invalid URI: " + url);
                }
                if (!TextUtils.isEmpty(where)) {
                    throw new UnsupportedOperationException("WHERE clause not supported: " + url);
                }
                this.table = url.getPathSegments().get(0);
                this.where = "_id=" + ContentUris.parseId(url);
                this.args = null;
            }
        }

        SqlArguments(Uri url) {
            if (url.getPathSegments().size() == 1) {
                this.table = url.getPathSegments().get(0);
                this.where = null;
                this.args = null;
                return;
            }
            throw new IllegalArgumentException("Invalid URI: " + url);
        }
    }

    private static class ChangeListenerWrapper implements Handler.Callback {
        private static final int MSG_APP_WIDGET_HOST_RESET = 2;
        private static final int MSG_LAUNCHER_PROVIDER_CHANGED = 1;
        private LauncherProviderChangeListener mListener;

        private ChangeListenerWrapper() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            if (this.mListener != null) {
                int i = msg.what;
                if (i == 1) {
                    this.mListener.onLauncherProviderChanged();
                } else if (i == 2) {
                    this.mListener.onAppWidgetHostReset();
                }
            }
            return true;
        }
    }
}
