package com.android.launcher3.provider;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.SparseBooleanArray;
import com.android.launcher3.AutoInstallsLayout;
import com.android.launcher3.DefaultLayoutParser;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.logging.FileLog;
import com.android.launcher3.model.GridSizeMigrationTask;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSparseArrayMap;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public class ImportDataTask {
    private static final int BATCH_INSERT_SIZE = 15;
    public static final String KEY_DATA_IMPORT_SRC_AUTHORITY = "data_import_src_authority";
    public static final String KEY_DATA_IMPORT_SRC_PKG = "data_import_src_pkg";
    private static final int MIN_ITEM_COUNT_FOR_SUCCESSFUL_MIGRATION = 6;
    private static final String TAG = "ImportDataTask";
    private final Context mContext;
    private int mHotseatSize;
    private int mMaxGridSizeX;
    private int mMaxGridSizeY;
    private final Uri mOtherFavoritesUri;

    private ImportDataTask(Context context, String sourceAuthority) {
        this.mContext = context;
        this.mOtherFavoritesUri = Uri.parse("content://" + sourceAuthority + "/favorites");
    }

    public boolean importWorkspace() throws Exception {
        FileLog.d(TAG, "Importing DB from " + this.mOtherFavoritesUri);
        this.mMaxGridSizeY = 0;
        this.mMaxGridSizeX = 0;
        this.mHotseatSize = 0;
        importWorkspaceItems();
        GridSizeMigrationTask.markForMigration(this.mContext, this.mMaxGridSizeX, this.mMaxGridSizeY, this.mHotseatSize);
        LauncherSettings.Settings.call(this.mContext.getContentResolver(), LauncherSettings.Settings.METHOD_CLEAR_EMPTY_DB_FLAG);
        return true;
    }

    private void importWorkspaceItems() throws Exception {
        ImportDataTask importDataTask;
        int i;
        int i2;
        int i3;
        String str;
        int i4;
        String str2;
        int i5;
        String str3;
        int i6;
        Intent intent;
        HashSet hashSet;
        String string = Long.toString(UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(Process.myUserHandle()));
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>(15);
        HashSet hashSet2 = new HashSet();
        Cursor cursorQuery = this.mContext.getContentResolver().query(this.mOtherFavoritesUri, null, "profileId = ?", new String[]{string}, "container , screen");
        try {
            int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("intent");
            int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("title");
            String str4 = "title";
            int columnIndexOrThrow4 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.CONTAINER);
            int i7 = columnIndexOrThrow3;
            int columnIndexOrThrow5 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.ITEM_TYPE);
            String str5 = LauncherSettings.Favorites.CONTAINER;
            int columnIndexOrThrow6 = cursorQuery.getColumnIndexOrThrow("appWidgetProvider");
            String str6 = LauncherSettings.Favorites.ITEM_TYPE;
            int columnIndexOrThrow7 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.SCREEN);
            String str7 = LauncherSettings.Favorites.SCREEN;
            int columnIndexOrThrow8 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.CELLX);
            String str8 = LauncherSettings.Favorites.CELLX;
            int columnIndexOrThrow9 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.CELLY);
            String str9 = LauncherSettings.Favorites.CELLY;
            int columnIndexOrThrow10 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.SPANX);
            String str10 = LauncherSettings.Favorites.SPANX;
            int columnIndexOrThrow11 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.SPANY);
            String str11 = LauncherSettings.Favorites.SPANY;
            int columnIndexOrThrow12 = cursorQuery.getColumnIndexOrThrow("rank");
            String str12 = "_id";
            String str13 = "rank";
            int columnIndexOrThrow13 = cursorQuery.getColumnIndexOrThrow("icon");
            int i8 = columnIndexOrThrow12;
            String str14 = "intent";
            int columnIndexOrThrow14 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.ICON_PACKAGE);
            String str15 = "icon";
            int i9 = columnIndexOrThrow13;
            int columnIndexOrThrow15 = cursorQuery.getColumnIndexOrThrow(LauncherSettings.Favorites.ICON_RESOURCE);
            String str16 = LauncherSettings.Favorites.ICON_RESOURCE;
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            int i10 = columnIndexOrThrow15;
            ContentValues contentValues = new ContentValues();
            String str17 = LauncherSettings.Favorites.ICON_PACKAGE;
            int i11 = 0;
            int i12 = 0;
            Integer numValueOf = null;
            while (true) {
                int i13 = columnIndexOrThrow14;
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                contentValues.clear();
                int i14 = columnIndexOrThrow2;
                int i15 = cursorQuery.getInt(columnIndexOrThrow);
                int iMax = Math.max(i11, i15);
                int i16 = columnIndexOrThrow;
                int i17 = cursorQuery.getInt(columnIndexOrThrow5);
                int i18 = columnIndexOrThrow5;
                int i19 = cursorQuery.getInt(columnIndexOrThrow4);
                int i20 = cursorQuery.getInt(columnIndexOrThrow7);
                int i21 = cursorQuery.getInt(columnIndexOrThrow8);
                int i22 = cursorQuery.getInt(columnIndexOrThrow9);
                int i23 = cursorQuery.getInt(columnIndexOrThrow10);
                int i24 = cursorQuery.getInt(columnIndexOrThrow11);
                int i25 = columnIndexOrThrow11;
                if (i19 == -101) {
                    importDataTask = this;
                    i = columnIndexOrThrow10;
                    importDataTask.mHotseatSize = Math.max(importDataTask.mHotseatSize, i20 + 1);
                } else if (i19 != -100) {
                    if (sparseBooleanArray.get(i19)) {
                        importDataTask = this;
                        i = columnIndexOrThrow10;
                    } else {
                        FileLog.d(TAG, String.format("Skipping item %d, type %d not in a valid folder %d", Integer.valueOf(i15), Integer.valueOf(i17), Integer.valueOf(i19)));
                        columnIndexOrThrow2 = i14;
                        columnIndexOrThrow14 = i13;
                        columnIndexOrThrow = i16;
                        columnIndexOrThrow5 = i18;
                        i11 = iMax;
                        columnIndexOrThrow11 = i25;
                    }
                } else if (i20 < 0) {
                    FileLog.d(TAG, String.format("Skipping item %d, type %d not on a valid screen %d", Integer.valueOf(i15), Integer.valueOf(i17), Integer.valueOf(i20)));
                    columnIndexOrThrow2 = i14;
                    columnIndexOrThrow14 = i13;
                    columnIndexOrThrow = i16;
                    columnIndexOrThrow5 = i18;
                    i11 = iMax;
                    columnIndexOrThrow11 = i25;
                } else {
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(i20);
                    }
                    importDataTask = this;
                    i = columnIndexOrThrow10;
                    importDataTask.mMaxGridSizeX = Math.max(importDataTask.mMaxGridSizeX, i21 + i23);
                    importDataTask.mMaxGridSizeY = Math.max(importDataTask.mMaxGridSizeY, i22 + i24);
                }
                if (i17 == 0 || i17 == 1) {
                    Intent uri = Intent.parseUri(cursorQuery.getString(i14), 0);
                    if (Utilities.isLauncherAppTarget(uri)) {
                        i3 = i13;
                        i4 = 0;
                        i2 = i14;
                        str = str16;
                    } else {
                        i2 = i14;
                        i3 = i13;
                        String str18 = str17;
                        contentValues.put(str18, cursorQuery.getString(i13));
                        str17 = str18;
                        int i26 = i10;
                        String string2 = cursorQuery.getString(i26);
                        i10 = i26;
                        str = str16;
                        contentValues.put(str, string2);
                        i4 = i17;
                    }
                    int i27 = i4;
                    int i28 = i9;
                    str2 = str;
                    byte[] blob = cursorQuery.getBlob(i28);
                    i5 = i28;
                    String str19 = str15;
                    contentValues.put(str19, blob);
                    str15 = str19;
                    String str20 = str14;
                    contentValues.put(str20, uri.toUri(0));
                    int i29 = i8;
                    i8 = i29;
                    str14 = str20;
                    String str21 = str13;
                    contentValues.put(str21, Integer.valueOf(cursorQuery.getInt(i29)));
                    str3 = str21;
                    i6 = 1;
                    contentValues.put("restored", (Integer) 1);
                    intent = uri;
                    i17 = i27;
                } else {
                    if (i17 == 2) {
                        sparseBooleanArray.put(i15, true);
                        intent = new Intent();
                        str3 = str13;
                        i3 = i13;
                    } else if (i17 != 4) {
                        FileLog.d(TAG, String.format("Skipping item %d, not a valid type %d", Integer.valueOf(i15), Integer.valueOf(i17)));
                        str3 = str13;
                        i3 = i13;
                        i2 = i14;
                        i5 = i9;
                        str2 = str16;
                        str16 = str2;
                        i9 = i5;
                        columnIndexOrThrow2 = i2;
                        columnIndexOrThrow = i16;
                        columnIndexOrThrow5 = i18;
                        i11 = iMax;
                        columnIndexOrThrow11 = i25;
                        columnIndexOrThrow14 = i3;
                        columnIndexOrThrow10 = i;
                        str13 = str3;
                    } else {
                        contentValues.put("restored", (Integer) 7);
                        contentValues.put("appWidgetProvider", cursorQuery.getString(columnIndexOrThrow6));
                        str3 = str13;
                        i3 = i13;
                        intent = null;
                    }
                    i6 = 1;
                    i2 = i14;
                    i5 = i9;
                    str2 = str16;
                }
                if (i19 != -101) {
                    hashSet = hashSet2;
                } else if (intent == null) {
                    Object[] objArr = new Object[i6];
                    objArr[0] = Integer.valueOf(i15);
                    FileLog.d(TAG, String.format("Skipping item %d, null intent on hotseat", objArr));
                    str16 = str2;
                    i9 = i5;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow = i16;
                    columnIndexOrThrow5 = i18;
                    i11 = iMax;
                    columnIndexOrThrow11 = i25;
                    columnIndexOrThrow14 = i3;
                    columnIndexOrThrow10 = i;
                    str13 = str3;
                } else {
                    if (intent.getComponent() != null) {
                        intent.setPackage(intent.getComponent().getPackageName());
                    }
                    hashSet = hashSet2;
                    hashSet.add(getPackage(intent));
                }
                String str22 = str12;
                contentValues.put(str22, Integer.valueOf(i15));
                String str23 = str6;
                contentValues.put(str23, Integer.valueOf(i17));
                String str24 = str5;
                contentValues.put(str24, Integer.valueOf(i19));
                str6 = str23;
                String str25 = str7;
                contentValues.put(str25, Integer.valueOf(i20));
                str7 = str25;
                String str26 = str8;
                contentValues.put(str26, Integer.valueOf(i21));
                str8 = str26;
                String str27 = str9;
                contentValues.put(str27, Integer.valueOf(i22));
                str9 = str27;
                String str28 = str10;
                contentValues.put(str28, Integer.valueOf(i23));
                str10 = str28;
                String str29 = str11;
                contentValues.put(str29, Integer.valueOf(i24));
                str11 = str29;
                int i30 = i7;
                String string3 = cursorQuery.getString(i30);
                i7 = i30;
                String str30 = str4;
                contentValues.put(str30, string3);
                str4 = str30;
                ArrayList<ContentProviderOperation> arrayList2 = arrayList;
                arrayList2.add(ContentProviderOperation.newInsert(LauncherSettings.Favorites.CONTENT_URI).withValues(contentValues).build());
                if (i19 < 0) {
                    i12++;
                }
                if (arrayList2.size() >= 15) {
                    importDataTask.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, arrayList2);
                    arrayList2.clear();
                }
                arrayList = arrayList2;
                str12 = str22;
                str5 = str24;
                hashSet2 = hashSet;
                str16 = str2;
                i9 = i5;
                columnIndexOrThrow2 = i2;
                columnIndexOrThrow = i16;
                columnIndexOrThrow5 = i18;
                i11 = iMax;
                columnIndexOrThrow11 = i25;
                columnIndexOrThrow14 = i3;
                columnIndexOrThrow10 = i;
                str13 = str3;
            }
            HashSet hashSet3 = hashSet2;
            ArrayList<ContentProviderOperation> arrayList3 = arrayList;
            int i31 = i12;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            FileLog.d(TAG, i31 + " items imported from external source");
            if (i31 < 6) {
                throw new Exception("Insufficient data");
            }
            if (!arrayList3.isEmpty()) {
                this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, arrayList3);
                arrayList3.clear();
            }
            IntSparseArrayMap<Object> intSparseArrayMapRemoveBrokenHotseatItems = GridSizeMigrationTask.removeBrokenHotseatItems(this.mContext);
            int i32 = LauncherAppState.getIDP(this.mContext).numHotseatIcons;
            if (intSparseArrayMapRemoveBrokenHotseatItems.size() < i32) {
                new HotseatLayoutParser(this.mContext, new HotseatParserCallback(hashSet3, intSparseArrayMapRemoveBrokenHotseatItems, arrayList3, i11 + 1, i32)).loadLayout(null, new IntArray());
                this.mHotseatSize = intSparseArrayMapRemoveBrokenHotseatItems.keyAt(intSparseArrayMapRemoveBrokenHotseatItems.size() - 1) + 1;
                if (arrayList3.isEmpty()) {
                    return;
                }
                this.mContext.getContentResolver().applyBatch(LauncherProvider.AUTHORITY, arrayList3);
            }
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            try {
                cursorQuery.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getPackage(Intent intent) {
        return intent.getComponent() != null ? intent.getComponent().getPackageName() : intent.getPackage();
    }

    public static boolean performImportIfPossible(Context context) throws Exception {
        SharedPreferences devicePrefs = Utilities.getDevicePrefs(context);
        String string = devicePrefs.getString(KEY_DATA_IMPORT_SRC_PKG, "");
        String string2 = devicePrefs.getString(KEY_DATA_IMPORT_SRC_AUTHORITY, "");
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            devicePrefs.edit().remove(KEY_DATA_IMPORT_SRC_PKG).remove(KEY_DATA_IMPORT_SRC_AUTHORITY).commit();
            if (!LauncherSettings.Settings.call(context.getContentResolver(), LauncherSettings.Settings.METHOD_WAS_EMPTY_DB_CREATED).getBoolean("value", false)) {
                return false;
            }
            for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, context.getApplicationInfo().uid, 0)) {
                if (string.equals(providerInfo.packageName)) {
                    if ((providerInfo.applicationInfo.flags & 1) == 0) {
                        return false;
                    }
                    if (string2.equals(providerInfo.authority) && (TextUtils.isEmpty(providerInfo.readPermission) || context.checkPermission(providerInfo.readPermission, Process.myPid(), Process.myUid()) == 0)) {
                        return new ImportDataTask(context, string2).importWorkspace();
                    }
                }
            }
        }
        return false;
    }

    private static class HotseatLayoutParser extends DefaultLayoutParser {
        public HotseatLayoutParser(Context context, AutoInstallsLayout.LayoutParserCallback callback) {
            super(context, null, callback, context.getResources(), LauncherAppState.getIDP(context).defaultLayoutId);
        }

        @Override // com.android.launcher3.DefaultLayoutParser, com.android.launcher3.AutoInstallsLayout
        protected ArrayMap<String, AutoInstallsLayout.TagParser> getLayoutElementsMap() {
            ArrayMap<String, AutoInstallsLayout.TagParser> arrayMap = new ArrayMap<>();
            arrayMap.put("favorite", new DefaultLayoutParser.AppShortcutWithUriParser());
            arrayMap.put("shortcut", new DefaultLayoutParser.UriShortcutParser(this.mSourceRes));
            arrayMap.put("resolve", new DefaultLayoutParser.ResolveParser());
            return arrayMap;
        }
    }

    private static class HotseatParserCallback implements AutoInstallsLayout.LayoutParserCallback {
        private final HashSet<String> mExistingApps;
        private final IntSparseArrayMap<Object> mExistingItems;
        private final ArrayList<ContentProviderOperation> mOutOps;
        private final int mRequiredSize;
        private int mStartItemId;

        HotseatParserCallback(HashSet<String> existingApps, IntSparseArrayMap<Object> existingItems, ArrayList<ContentProviderOperation> outOps, int startItemId, int requiredSize) {
            this.mExistingApps = existingApps;
            this.mExistingItems = existingItems;
            this.mOutOps = outOps;
            this.mRequiredSize = requiredSize;
            this.mStartItemId = startItemId;
        }

        @Override // com.android.launcher3.AutoInstallsLayout.LayoutParserCallback
        public int generateNewItemId() {
            int i = this.mStartItemId;
            this.mStartItemId = i + 1;
            return i;
        }

        @Override // com.android.launcher3.AutoInstallsLayout.LayoutParserCallback
        public int insertAndCheck(SQLiteDatabase db, ContentValues values) {
            if (this.mExistingItems.size() >= this.mRequiredSize) {
                return 0;
            }
            Integer num = -101;
            if (!num.equals(values.getAsInteger(LauncherSettings.Favorites.CONTAINER))) {
                return 0;
            }
            try {
                Intent uri = Intent.parseUri(values.getAsString("intent"), 0);
                String str = ImportDataTask.getPackage(uri);
                if (str != null && !this.mExistingApps.contains(str)) {
                    this.mExistingApps.add(str);
                    int i = 0;
                    while (this.mExistingItems.get(i) != null) {
                        i++;
                    }
                    this.mExistingItems.put(i, uri);
                    values.put(LauncherSettings.Favorites.SCREEN, Integer.valueOf(i));
                    this.mOutOps.add(ContentProviderOperation.newInsert(LauncherSettings.Favorites.CONTENT_URI).withValues(values).build());
                }
            } catch (URISyntaxException unused) {
            }
            return 0;
        }
    }
}
