package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.icons.BaseIconFactory;
import com.android.launcher3.icons.BitmapInfo;
import com.android.launcher3.icons.BitmapRenderer;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.SQLiteCacheHelper;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes7.dex */
public abstract class BaseIconCache {
    private static final boolean DEBUG = false;
    public static final String EMPTY_CLASS_NAME = ".";
    private static final int INITIAL_ICON_CACHE_CAPACITY = 50;
    private static final String TAG = "BaseIconCache";
    private final Looper mBgLooper;
    private final Map<ComponentKey, CacheEntry> mCache;
    protected final Context mContext;
    private final String mDbFileName;
    private final BitmapFactory.Options mDecodeOptions;
    protected IconDB mIconDb;
    protected int mIconDpi;
    protected final PackageManager mPackageManager;
    protected final Handler mWorkerHandler;
    private final HashMap<UserHandle, BitmapInfo> mDefaultIcons = new HashMap<>();
    protected LocaleList mLocaleList = LocaleList.getEmptyLocaleList();
    protected String mSystemState = "";

    public static class CacheEntry {
        public BitmapInfo bitmap = BitmapInfo.LOW_RES_INFO;
        public CharSequence title = "";
        public CharSequence contentDescription = "";
    }

    protected abstract BaseIconFactory getIconFactory();

    protected abstract long getSerialNumberForUser(UserHandle userHandle);

    protected abstract boolean isInstantApp(ApplicationInfo applicationInfo);

    public BaseIconCache(Context context, String dbFileName, Looper bgLooper, int iconDpi, int iconPixelSize, boolean inMemoryCache) {
        this.mContext = context;
        this.mDbFileName = dbFileName;
        this.mPackageManager = context.getPackageManager();
        this.mBgLooper = bgLooper;
        this.mWorkerHandler = new Handler(this.mBgLooper);
        if (inMemoryCache) {
            this.mCache = new HashMap(50);
        } else {
            this.mCache = new AbstractMap<ComponentKey, CacheEntry>() { // from class: com.android.launcher3.icons.cache.BaseIconCache.1
                @Override // java.util.AbstractMap, java.util.Map
                public Set<Map.Entry<ComponentKey, CacheEntry>> entrySet() {
                    return Collections.emptySet();
                }

                @Override // java.util.AbstractMap, java.util.Map
                public CacheEntry put(ComponentKey key, CacheEntry value) {
                    return value;
                }
            };
        }
        if (BitmapRenderer.USE_HARDWARE_BITMAP && Build.VERSION.SDK_INT >= 26) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            this.mDecodeOptions = options;
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
        } else {
            this.mDecodeOptions = null;
        }
        updateSystemState();
        this.mIconDpi = iconDpi;
        this.mIconDb = new IconDB(context, dbFileName, iconPixelSize);
    }

    public void updateIconParams(final int iconDpi, final int iconPixelSize) {
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.launcher3.icons.cache.-$$Lambda$BaseIconCache$MwrqDESzzZpTlG8YHIovWQ692hc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateIconParams$0$BaseIconCache(iconDpi, iconPixelSize);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateIconParamsBg, reason: merged with bridge method [inline-methods] */
    public synchronized void lambda$updateIconParams$0$BaseIconCache(int iconDpi, int iconPixelSize) {
        this.mIconDpi = iconDpi;
        this.mDefaultIcons.clear();
        this.mIconDb.clear();
        this.mIconDb.close();
        this.mIconDb = new IconDB(this.mContext, this.mDbFileName, iconPixelSize);
        this.mCache.clear();
    }

    private Drawable getFullResIcon(Resources resources, int iconId) {
        if (resources != null && iconId != 0) {
            try {
                return resources.getDrawableForDensity(iconId, this.mIconDpi);
            } catch (Resources.NotFoundException e) {
            }
        }
        return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
    }

    public Drawable getFullResIcon(String packageName, int iconId) {
        try {
            return getFullResIcon(this.mPackageManager.getResourcesForApplication(packageName), iconId);
        } catch (PackageManager.NameNotFoundException e) {
            return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
        }
    }

    public Drawable getFullResIcon(ActivityInfo info) {
        try {
            return getFullResIcon(this.mPackageManager.getResourcesForApplication(info.applicationInfo), info.getIconResource());
        } catch (PackageManager.NameNotFoundException e) {
            return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
        }
    }

    private BitmapInfo makeDefaultIcon(UserHandle user) {
        BaseIconFactory li = getIconFactory();
        try {
            BitmapInfo bitmapInfoMakeDefaultIcon = li.makeDefaultIcon(user);
            if (li != null) {
                li.close();
            }
            return bitmapInfoMakeDefaultIcon;
        } catch (Throwable th) {
            if (li != null) {
                try {
                    li.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public synchronized void remove(ComponentName componentName, UserHandle user) {
        this.mCache.remove(new ComponentKey(componentName, user));
    }

    private void removeFromMemCacheLocked(String packageName, UserHandle user) {
        HashSet<ComponentKey> forDeletion = new HashSet<>();
        for (ComponentKey key : this.mCache.keySet()) {
            if (key.componentName.getPackageName().equals(packageName) && key.user.equals(user)) {
                forDeletion.add(key);
            }
        }
        for (ComponentKey condemned : forDeletion) {
            this.mCache.remove(condemned);
        }
    }

    public synchronized void removeIconsForPkg(String packageName, UserHandle user) {
        removeFromMemCacheLocked(packageName, user);
        long userSerial = getSerialNumberForUser(user);
        this.mIconDb.delete("componentName LIKE ? AND profileId = ?", new String[]{packageName + "/%", Long.toString(userSerial)});
    }

    public IconCacheUpdateHandler getUpdateHandler() {
        updateSystemState();
        return new IconCacheUpdateHandler(this);
    }

    private void updateSystemState() {
        this.mLocaleList = this.mContext.getResources().getConfiguration().getLocales();
        this.mSystemState = this.mLocaleList.toLanguageTags() + "," + Build.VERSION.SDK_INT;
    }

    protected String getIconSystemState(String packageName) {
        return this.mSystemState;
    }

    public synchronized <T> void addIconToDBAndMemCache(T object, CachingLogic<T> cachingLogic, PackageInfo info, long userSerial, boolean replaceExisting) {
        CacheEntry entry;
        UserHandle user = cachingLogic.getUser(object);
        ComponentName componentName = cachingLogic.getComponent(object);
        ComponentKey key = new ComponentKey(componentName, user);
        CacheEntry entry2 = null;
        if (!replaceExisting && ((entry2 = this.mCache.get(key)) == null || entry2.bitmap.isNullOrLowRes())) {
            entry2 = null;
        }
        if (entry2 != null) {
            entry = entry2;
        } else {
            CacheEntry entry3 = new CacheEntry();
            entry3.bitmap = cachingLogic.loadIcon(this.mContext, object);
            entry = entry3;
        }
        if (entry.bitmap.isNullOrLowRes()) {
            return;
        }
        entry.title = cachingLogic.getLabel(object);
        entry.contentDescription = this.mPackageManager.getUserBadgedLabel(entry.title, user);
        if (cachingLogic.addToMemCache()) {
            this.mCache.put(key, entry);
        }
        ContentValues values = newContentValues(entry.bitmap, entry.title.toString(), componentName.getPackageName(), cachingLogic.getKeywords(object, this.mLocaleList));
        addIconToDB(values, componentName, info, userSerial, cachingLogic.getLastUpdatedTime(object, info));
    }

    private void addIconToDB(ContentValues values, ComponentName key, PackageInfo info, long userSerial, long lastUpdateTime) {
        values.put(IconDB.COLUMN_COMPONENT, key.flattenToString());
        values.put(IconDB.COLUMN_USER, Long.valueOf(userSerial));
        values.put(IconDB.COLUMN_LAST_UPDATED, Long.valueOf(lastUpdateTime));
        values.put(IconDB.COLUMN_VERSION, Integer.valueOf(info.versionCode));
        this.mIconDb.insertOrReplace(values);
    }

    public synchronized BitmapInfo getDefaultIcon(UserHandle user) {
        if (!this.mDefaultIcons.containsKey(user)) {
            this.mDefaultIcons.put(user, makeDefaultIcon(user));
        }
        return this.mDefaultIcons.get(user);
    }

    public boolean isDefaultIcon(BitmapInfo icon, UserHandle user) {
        return getDefaultIcon(user).icon == icon.icon;
    }

    protected <T> CacheEntry cacheLocked(ComponentName componentName, UserHandle user, Supplier<T> infoProvider, CachingLogic<T> cachingLogic, boolean usePackageIcon, boolean useLowResIcon) {
        CacheEntry packageEntry;
        assertWorkerThread();
        ComponentKey cacheKey = new ComponentKey(componentName, user);
        CacheEntry entry = this.mCache.get(cacheKey);
        if (entry == null || (entry.bitmap.isLowRes() && !useLowResIcon)) {
            entry = new CacheEntry();
            if (cachingLogic.addToMemCache()) {
                this.mCache.put(cacheKey, entry);
            }
            T object = null;
            boolean providerFetchedOnce = false;
            if (!getEntryFromDB(cacheKey, entry, useLowResIcon)) {
                object = infoProvider.get();
                providerFetchedOnce = true;
                if (object != null) {
                    entry.bitmap = cachingLogic.loadIcon(this.mContext, object);
                } else {
                    if (usePackageIcon && (packageEntry = getEntryForPackageLocked(componentName.getPackageName(), user, false)) != null) {
                        entry.bitmap = packageEntry.bitmap;
                        entry.title = packageEntry.title;
                        entry.contentDescription = packageEntry.contentDescription;
                    }
                    if (entry.bitmap == null) {
                        entry.bitmap = getDefaultIcon(user);
                    }
                }
            }
            if (TextUtils.isEmpty(entry.title)) {
                if (object == null && !providerFetchedOnce) {
                    object = infoProvider.get();
                }
                if (object != null) {
                    entry.title = cachingLogic.getLabel(object);
                    entry.contentDescription = this.mPackageManager.getUserBadgedLabel(cachingLogic.getDescription(object, entry.title), user);
                }
            }
        }
        return entry;
    }

    public synchronized void clear() {
        assertWorkerThread();
        this.mIconDb.clear();
    }

    protected synchronized void cachePackageInstallInfo(String packageName, UserHandle user, Bitmap icon, CharSequence title) {
        removeFromMemCacheLocked(packageName, user);
        ComponentKey cacheKey = getPackageKey(packageName, user);
        CacheEntry entry = this.mCache.get(cacheKey);
        if (entry == null) {
            entry = new CacheEntry();
        }
        if (!TextUtils.isEmpty(title)) {
            entry.title = title;
        }
        if (icon != null) {
            BaseIconFactory li = getIconFactory();
            entry.bitmap = li.createIconBitmap(icon);
            li.close();
        }
        if (!TextUtils.isEmpty(title) && entry.bitmap.icon != null) {
            this.mCache.put(cacheKey, entry);
        }
    }

    private static ComponentKey getPackageKey(String packageName, UserHandle user) {
        ComponentName cn = new ComponentName(packageName, packageName + EMPTY_CLASS_NAME);
        return new ComponentKey(cn, user);
    }

    protected CacheEntry getEntryForPackageLocked(String packageName, UserHandle user, boolean useLowResIcon) {
        assertWorkerThread();
        ComponentKey cacheKey = getPackageKey(packageName, user);
        CacheEntry entry = this.mCache.get(cacheKey);
        if (entry != null && (!entry.bitmap.isLowRes() || useLowResIcon)) {
            return entry;
        }
        CacheEntry entry2 = new CacheEntry();
        boolean entryUpdated = true;
        if (!getEntryFromDB(cacheKey, entry2, useLowResIcon)) {
            try {
                int flags = Process.myUserHandle().equals(user) ? 0 : 8192;
                PackageInfo info = this.mPackageManager.getPackageInfo(packageName, flags);
                ApplicationInfo appInfo = info.applicationInfo;
                if (appInfo == null) {
                    throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
                }
                BaseIconFactory li = getIconFactory();
                BitmapInfo iconInfo = li.createBadgedIconBitmap(appInfo.loadIcon(this.mPackageManager), user, appInfo.targetSdkVersion, isInstantApp(appInfo));
                li.close();
                entry2.title = appInfo.loadLabel(this.mPackageManager);
                entry2.contentDescription = this.mPackageManager.getUserBadgedLabel(entry2.title, user);
                entry2.bitmap = BitmapInfo.of(useLowResIcon ? BitmapInfo.LOW_RES_ICON : iconInfo.icon, iconInfo.color);
                ContentValues values = newContentValues(iconInfo, entry2.title.toString(), packageName, null);
                addIconToDB(values, cacheKey.componentName, info, getSerialNumberForUser(user), info.lastUpdateTime);
            } catch (PackageManager.NameNotFoundException e) {
                entryUpdated = false;
            }
        }
        if (entryUpdated) {
            this.mCache.put(cacheKey, entry2);
        }
        return entry2;
    }

    protected boolean getEntryFromDB(ComponentKey cacheKey, CacheEntry entry, boolean lowRes) {
        Cursor c = null;
        try {
            try {
                c = this.mIconDb.query(lowRes ? IconDB.COLUMNS_LOW_RES : IconDB.COLUMNS_HIGH_RES, "componentName = ? AND profileId = ?", new String[]{cacheKey.componentName.flattenToString(), Long.toString(getSerialNumberForUser(cacheKey.user))});
            } catch (SQLiteException e) {
                Log.d(TAG, "Error reading icon cache", e);
                if (c != null) {
                }
            }
            if (!c.moveToNext()) {
                if (c != null) {
                }
                return false;
            }
            entry.bitmap = BitmapInfo.of(BitmapInfo.LOW_RES_ICON, GraphicsUtils.setColorAlphaBound(c.getInt(0), 255));
            entry.title = c.getString(1);
            if (entry.title == null) {
                entry.title = "";
                entry.contentDescription = "";
            } else {
                entry.contentDescription = this.mPackageManager.getUserBadgedLabel(entry.title, cacheKey.user);
            }
            if (!lowRes) {
                byte[] data = c.getBlob(2);
                try {
                    entry.bitmap = BitmapInfo.of(BitmapFactory.decodeByteArray(data, 0, data.length, this.mDecodeOptions), entry.bitmap.color);
                } catch (Exception e2) {
                }
            }
            return true;
            c.close();
            return false;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    public synchronized Cursor queryCacheDb(String[] columns, String selection, String[] selectionArgs) {
        return this.mIconDb.query(columns, selection, selectionArgs);
    }

    public static final class IconDB extends SQLiteCacheHelper {
        public static final String COLUMN_COMPONENT = "componentName";
        public static final String COLUMN_KEYWORDS = "keywords";
        public static final String COLUMN_LAST_UPDATED = "lastUpdated";
        public static final String COLUMN_ROWID = "rowid";
        public static final String COLUMN_SYSTEM_STATE = "system_state";
        public static final String COLUMN_USER = "profileId";
        public static final String COLUMN_VERSION = "version";
        private static final int RELEASE_VERSION = 27;
        public static final String TABLE_NAME = "icons";
        public static final String COLUMN_ICON_COLOR = "icon_color";
        public static final String COLUMN_LABEL = "label";
        public static final String COLUMN_ICON = "icon";
        public static final String[] COLUMNS_HIGH_RES = {COLUMN_ICON_COLOR, COLUMN_LABEL, COLUMN_ICON};
        public static final String[] COLUMNS_LOW_RES = {COLUMN_ICON_COLOR, COLUMN_LABEL};

        public IconDB(Context context, String dbFileName, int iconPixelSize) {
            super(context, dbFileName, 1769472 + iconPixelSize, TABLE_NAME);
        }

        @Override // com.android.launcher3.util.SQLiteCacheHelper
        protected void onCreateTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_color INTEGER NOT NULL DEFAULT 0, label TEXT, system_state TEXT, keywords TEXT, PRIMARY KEY (componentName, profileId) );");
        }
    }

    private ContentValues newContentValues(BitmapInfo bitmapInfo, String label, String packageName, String keywords) {
        ContentValues values = new ContentValues();
        values.put(IconDB.COLUMN_ICON, bitmapInfo.isLowRes() ? null : GraphicsUtils.flattenBitmap(bitmapInfo.icon));
        values.put(IconDB.COLUMN_ICON_COLOR, Integer.valueOf(bitmapInfo.color));
        values.put(IconDB.COLUMN_LABEL, label);
        values.put(IconDB.COLUMN_SYSTEM_STATE, getIconSystemState(packageName));
        values.put(IconDB.COLUMN_KEYWORDS, keywords);
        return values;
    }

    private void assertWorkerThread() {
        if (Looper.myLooper() != this.mBgLooper) {
            throw new IllegalStateException("Cache accessed on wrong thread " + Looper.myLooper());
        }
    }
}
