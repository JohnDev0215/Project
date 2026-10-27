package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.sysprop.ContactsProperties;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.collect.Lists;
import com.google.android.collect.Sets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ContactDirectoryManager {
    private static final boolean DEBUG = AbstractContactsProvider.VERBOSE_LOGGING;
    private final ContactsProvider2 mContactsProvider;
    private final Context mContext;
    private volatile boolean mDirectoriesForceUpdated = false;
    private final PackageManager mPackageManager;

    private static final class DirectoryQuery {
        public static final String[] PROJECTION = {"accountName", "accountType", "displayName", "typeResourceId", "exportSupport", "shortcutSupport", "photoSupport"};
    }

    public static class DirectoryInfo {
        String accountName;
        String accountType;
        String authority;
        String displayName;
        long id;
        String packageName;
        int typeResourceId;
        int exportSupport = 0;
        int shortcutSupport = 0;
        int photoSupport = 0;

        public String toString() {
            return "DirectoryInfo:id=" + this.id + " packageName=" + this.accountType + " authority=" + this.authority + " accountName=*** accountType=" + this.accountType;
        }
    }

    public ContactDirectoryManager(ContactsProvider2 contactsProvider2) {
        this.mContactsProvider = contactsProvider2;
        Context context = contactsProvider2.getContext();
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
    }

    public ContactsDatabaseHelper getDbHelper() {
        return this.mContactsProvider.getDatabaseHelper();
    }

    public void setDirectoriesForceUpdated(boolean z) {
        this.mDirectoriesForceUpdated = z;
    }

    private boolean areTypeResourceIdsValid() {
        Cursor cursorRawQuery = getDbHelper().getReadableDatabase().rawQuery("SELECT DISTINCT typeResourceId,packageName,typeResourceName FROM directories", null);
        while (cursorRawQuery.moveToNext()) {
            try {
                int i = cursorRawQuery.getInt(0);
                if (i != 0) {
                    String string = cursorRawQuery.getString(1);
                    String string2 = cursorRawQuery.getString(2);
                    String resourceNameById = getResourceNameById(string, i);
                    if (!TextUtils.equals(string2, resourceNameById)) {
                        if (DEBUG) {
                            Log.d("ContactDirectoryManager", "areTypeResourceIdsValid: resourceId=" + i + " packageName=" + string + " storedResourceName=" + string2 + " resourceName=" + resourceNameById);
                        }
                        return false;
                    }
                }
            } finally {
                cursorRawQuery.close();
            }
        }
        return true;
    }

    private String getResourceNameById(String str, int i) {
        try {
            return this.mPackageManager.getResourcesForApplication(str).getResourceName(i);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
            return null;
        }
    }

    private void saveKnownDirectoryProviders(Set<String> set) {
        getDbHelper().setProperty("knownDirectoryPackages", TextUtils.join(",", set));
    }

    private boolean haveKnownDirectoryProvidersChanged(Set<String> set) {
        String strJoin = TextUtils.join(",", set);
        String property = getDbHelper().getProperty("knownDirectoryPackages", "");
        boolean z = !Objects.equals(strJoin, property);
        if (DEBUG) {
            Log.d("ContactDirectoryManager", "haveKnownDirectoryProvidersChanged=" + z + "\nprev=" + property + " current=" + strJoin);
        }
        return z;
    }

    boolean isRescanNeeded() {
        if (((Boolean) ContactsProperties.debug_scan_all_packages().orElse(Boolean.FALSE)).booleanValue()) {
            Log.w("ContactDirectoryManager", "debug.cp2.scan_all_packages set to 1.");
            return true;
        }
        if (!"1".equals(getDbHelper().getProperty("directoryScanComplete", "0"))) {
            if (DEBUG) {
                Log.d("ContactDirectoryManager", "DIRECTORY_SCAN_COMPLETE is 0.");
            }
            return true;
        }
        if (!haveKnownDirectoryProvidersChanged(getDirectoryProviderPackages(this.mPackageManager))) {
            return false;
        }
        Log.i("ContactDirectoryManager", "Directory provider packages have changed.");
        return true;
    }

    public int scanAllPackages(boolean z) {
        if (!areTypeResourceIdsValid()) {
            z = true;
            Log.i("ContactDirectoryManager", "!areTypeResourceIdsValid.");
        }
        if (z) {
            getDbHelper().forceDirectoryRescan();
        }
        return scanAllPackagesIfNeeded();
    }

    private int scanAllPackagesIfNeeded() {
        if (!isRescanNeeded()) {
            return 0;
        }
        if (DEBUG) {
            Log.d("ContactDirectoryManager", "scanAllPackagesIfNeeded()");
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.mDirectoriesForceUpdated = false;
        int iScanAllPackages = scanAllPackages();
        getDbHelper().setProperty("directoryScanComplete", "1");
        Log.i("ContactDirectoryManager", "Discovered " + iScanAllPackages + " contact directories in " + (SystemClock.elapsedRealtime() - jElapsedRealtime) + "ms");
        this.mContactsProvider.notifyChange(false, false);
        if (this.mDirectoriesForceUpdated) {
            this.mDirectoriesForceUpdated = false;
            this.mContactsProvider.scheduleRescanDirectories();
        }
        return iScanAllPackages;
    }

    static boolean isDirectoryProvider(ProviderInfo providerInfo) {
        Bundle bundle;
        Object obj;
        return (providerInfo == null || (bundle = providerInfo.metaData) == null || (obj = bundle.get("android.content.ContactDirectory")) == null || !Boolean.TRUE.equals(obj)) ? false : true;
    }

    private static List<ProviderInfo> getDirectoryProviderInfos(PackageManager packageManager) {
        return packageManager.queryContentProviders(null, 0, 0, "android.content.ContactDirectory");
    }

    static Set<String> getDirectoryProviderPackages(PackageManager packageManager) {
        HashSet hashSetNewHashSet = Sets.newHashSet();
        if (DEBUG) {
            Log.d("ContactDirectoryManager", "Listing directory provider packages...");
        }
        Iterator<ProviderInfo> it = getDirectoryProviderInfos(packageManager).iterator();
        while (it.hasNext()) {
            hashSetNewHashSet.add(it.next().packageName);
        }
        if (DEBUG) {
            Log.d("ContactDirectoryManager", "Found " + hashSetNewHashSet.size() + " directory provider packages");
        }
        return hashSetNewHashSet;
    }

    private int scanAllPackages() {
        List<DirectoryInfo> listUpdateDirectoriesForPackage;
        SQLiteDatabase writableDatabase = getDbHelper().getWritableDatabase();
        insertDefaultDirectory(writableDatabase);
        insertLocalInvisibleDirectory(writableDatabase);
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        sb.append("NOT (_id=? OR _id=?");
        arrayList.add(String.valueOf(0L));
        arrayList.add(String.valueOf(1L));
        Set<String> directoryProviderPackages = getDirectoryProviderPackages(this.mPackageManager);
        int size = 0;
        for (String str : directoryProviderPackages) {
            if (DEBUG) {
                Log.d("ContactDirectoryManager", "package=" + str);
            }
            if (this.mContext.getPackageName().equals(str)) {
                Log.w("ContactDirectoryManager", "  skipping self");
            } else {
                try {
                    PackageInfo packageInfo = this.mPackageManager.getPackageInfo(str, 136);
                    if (packageInfo != null && (listUpdateDirectoriesForPackage = updateDirectoriesForPackage(packageInfo, true)) != null && !listUpdateDirectoriesForPackage.isEmpty()) {
                        size += listUpdateDirectoriesForPackage.size();
                        for (DirectoryInfo directoryInfo : listUpdateDirectoriesForPackage) {
                            if (DEBUG) {
                                Log.d("ContactDirectoryManager", "  directory=" + directoryInfo);
                            }
                            sb.append(" OR ");
                            sb.append("(packageName=? AND authority=? AND accountName=? AND accountType=?)");
                            arrayList.add(directoryInfo.packageName);
                            arrayList.add(directoryInfo.authority);
                            arrayList.add(directoryInfo.accountName);
                            arrayList.add(directoryInfo.accountType);
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }
        sb.append(")");
        int iDelete = writableDatabase.delete("directories", sb.toString(), (String[]) arrayList.toArray(new String[0]));
        saveKnownDirectoryProviders(directoryProviderPackages);
        Log.i("ContactDirectoryManager", "deleted " + iDelete + " stale rows which don't have any relevant directory");
        return size;
    }

    private void insertDefaultDirectory(SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Long) 0L);
        contentValues.put("packageName", this.mContext.getPackageName());
        contentValues.put("authority", "com.android.contacts");
        contentValues.put("typeResourceId", Integer.valueOf(R.string.default_directory));
        contentValues.put("typeResourceName", this.mContext.getResources().getResourceName(R.string.default_directory));
        contentValues.put("exportSupport", (Integer) 0);
        contentValues.put("shortcutSupport", (Integer) 2);
        contentValues.put("photoSupport", (Integer) 3);
        sQLiteDatabase.replace("directories", null, contentValues);
    }

    private void insertLocalInvisibleDirectory(SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", (Long) 1L);
        contentValues.put("packageName", this.mContext.getPackageName());
        contentValues.put("authority", "com.android.contacts");
        contentValues.put("typeResourceId", Integer.valueOf(R.string.local_invisible_directory));
        contentValues.put("typeResourceName", this.mContext.getResources().getResourceName(R.string.local_invisible_directory));
        contentValues.put("exportSupport", (Integer) 0);
        contentValues.put("shortcutSupport", (Integer) 2);
        contentValues.put("photoSupport", (Integer) 3);
        sQLiteDatabase.replace("directories", null, contentValues);
    }

    public void onPackageChanged(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.mPackageManager.getPackageInfo(str, 136);
        } catch (PackageManager.NameNotFoundException unused) {
            PackageInfo packageInfo2 = new PackageInfo();
            packageInfo2.packageName = str;
            packageInfo = packageInfo2;
        }
        if (this.mContext.getPackageName().equals(packageInfo.packageName)) {
            if (DEBUG) {
                Log.d("ContactDirectoryManager", "Ignoring onPackageChanged for self");
                return;
            }
            return;
        }
        updateDirectoriesForPackage(packageInfo, false);
    }

    private List<DirectoryInfo> updateDirectoriesForPackage(PackageInfo packageInfo, boolean z) {
        if (DEBUG) {
            Log.d("ContactDirectoryManager", "updateDirectoriesForPackage  packageName=" + packageInfo.packageName + " initialScan=" + z);
        }
        ArrayList<DirectoryInfo> arrayListNewArrayList = Lists.newArrayList();
        ProviderInfo[] providerInfoArr = packageInfo.providers;
        if (providerInfoArr != null) {
            for (ProviderInfo providerInfo : providerInfoArr) {
                if (isDirectoryProvider(providerInfo)) {
                    queryDirectoriesForAuthority(arrayListNewArrayList, providerInfo);
                }
            }
        }
        if (arrayListNewArrayList.size() == 0 && z) {
            return null;
        }
        SQLiteDatabase writableDatabase = getDbHelper().getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            updateDirectories(writableDatabase, arrayListNewArrayList);
            StringBuilder sb = new StringBuilder("packageName=?");
            if (!arrayListNewArrayList.isEmpty()) {
                sb.append(" AND _id NOT IN(");
                Iterator<DirectoryInfo> it = arrayListNewArrayList.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().id);
                    sb.append(",");
                }
                sb.setLength(sb.length() - 1);
                sb.append(")");
            }
            int iDelete = writableDatabase.delete("directories", sb.toString(), new String[]{packageInfo.packageName});
            if (DEBUG) {
                Log.d("ContactDirectoryManager", "  deleted " + iDelete + " stale rows");
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            this.mContactsProvider.resetDirectoryCache();
            return arrayListNewArrayList;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    protected void queryDirectoriesForAuthority(ArrayList<DirectoryInfo> arrayList, ProviderInfo providerInfo) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = this.mContext.getContentResolver().query(new Uri.Builder().scheme("content").authority(providerInfo.authority).appendPath("directories").build(), DirectoryQuery.PROJECTION, null, null, null);
            if (cursorQuery == null) {
                Log.i("ContactDirectoryManager", providerDescription(providerInfo) + " returned a NULL cursor.");
            } else {
                while (cursorQuery.moveToNext()) {
                    DirectoryInfo directoryInfo = new DirectoryInfo();
                    directoryInfo.packageName = providerInfo.packageName;
                    directoryInfo.authority = providerInfo.authority;
                    directoryInfo.accountName = cursorQuery.getString(0);
                    directoryInfo.accountType = cursorQuery.getString(1);
                    directoryInfo.displayName = cursorQuery.getString(2);
                    if (!cursorQuery.isNull(3)) {
                        directoryInfo.typeResourceId = cursorQuery.getInt(3);
                    }
                    if (!cursorQuery.isNull(4)) {
                        int i = cursorQuery.getInt(4);
                        if (i == 0 || i == 1 || i == 2) {
                            directoryInfo.exportSupport = i;
                        } else {
                            Log.e("ContactDirectoryManager", providerDescription(providerInfo) + " - invalid export support flag: " + i);
                        }
                    }
                    if (!cursorQuery.isNull(5)) {
                        int i2 = cursorQuery.getInt(5);
                        if (i2 == 0 || i2 == 1 || i2 == 2) {
                            directoryInfo.shortcutSupport = i2;
                        } else {
                            Log.e("ContactDirectoryManager", providerDescription(providerInfo) + " - invalid shortcut support flag: " + i2);
                        }
                    }
                    if (!cursorQuery.isNull(6)) {
                        int i3 = cursorQuery.getInt(6);
                        if (i3 == 0 || i3 == 1 || i3 == 2 || i3 == 3) {
                            directoryInfo.photoSupport = i3;
                        } else {
                            Log.e("ContactDirectoryManager", providerDescription(providerInfo) + " - invalid photo support flag: " + i3);
                        }
                    }
                    arrayList.add(directoryInfo);
                }
            }
        } catch (Throwable th) {
            try {
                Log.e("ContactDirectoryManager", providerDescription(providerInfo) + " exception", th);
                if (0 == 0) {
                }
            } finally {
                if (0 != 0) {
                    cursorQuery.close();
                }
            }
        }
    }

    private void updateDirectories(SQLiteDatabase sQLiteDatabase, ArrayList<DirectoryInfo> arrayList) {
        long jInsert;
        for (DirectoryInfo directoryInfo : arrayList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("packageName", directoryInfo.packageName);
            contentValues.put("authority", directoryInfo.authority);
            contentValues.put("accountName", directoryInfo.accountName);
            contentValues.put("accountType", directoryInfo.accountType);
            contentValues.put("typeResourceId", Integer.valueOf(directoryInfo.typeResourceId));
            contentValues.put("displayName", directoryInfo.displayName);
            contentValues.put("exportSupport", Integer.valueOf(directoryInfo.exportSupport));
            contentValues.put("shortcutSupport", Integer.valueOf(directoryInfo.shortcutSupport));
            contentValues.put("photoSupport", Integer.valueOf(directoryInfo.photoSupport));
            int i = directoryInfo.typeResourceId;
            if (i != 0) {
                contentValues.put("typeResourceName", getResourceNameById(directoryInfo.packageName, i));
            }
            Cursor cursorQuery = sQLiteDatabase.query("directories", new String[]{"_id"}, "packageName=? AND authority=? AND accountName=? AND accountType=?", new String[]{directoryInfo.packageName, directoryInfo.authority, directoryInfo.accountName, directoryInfo.accountType}, null, null, null);
            try {
                if (cursorQuery.moveToFirst()) {
                    jInsert = cursorQuery.getLong(0);
                    sQLiteDatabase.update("directories", contentValues, "_id=?", new String[]{String.valueOf(jInsert)});
                } else {
                    jInsert = sQLiteDatabase.insert("directories", null, contentValues);
                }
                directoryInfo.id = jInsert;
            } finally {
                cursorQuery.close();
            }
        }
    }

    protected String providerDescription(ProviderInfo providerInfo) {
        return "Directory provider " + providerInfo.packageName + "(" + providerInfo.authority + ")";
    }
}
