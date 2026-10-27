package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.telephony.SmsManager;
import android.text.TextUtils;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
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
    protected String mSystemState = "";

    public static class CacheEntry extends BitmapInfo {
        public CharSequence title = "";
        public CharSequence contentDescription = "";
    }

    protected abstract BaseIconFactory getIconFactory();

    protected abstract long getSerialNumberForUser(UserHandle user);

    protected abstract boolean isInstantApp(ApplicationInfo info);

    public BaseIconCache(Context context, String dbFileName, Looper bgLooper, int iconDpi, int iconPixelSize, boolean inMemoryCache) {
        this.mContext = context;
        this.mDbFileName = dbFileName;
        this.mPackageManager = context.getPackageManager();
        this.mBgLooper = bgLooper;
        this.mWorkerHandler = new Handler(bgLooper);
        if (inMemoryCache) {
            this.mCache = new HashMap(50);
        } else {
            this.mCache = new AbstractMap<ComponentKey, CacheEntry>() { // from class: com.android.launcher3.icons.cache.BaseIconCache.1
                @Override // java.util.AbstractMap, java.util.Map
                public CacheEntry put(ComponentKey key, CacheEntry value) {
                    return value;
                }

                @Override // java.util.AbstractMap, java.util.Map
                public Set<Map.Entry<ComponentKey, CacheEntry>> entrySet() {
                    return Collections.emptySet();
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
        this.mWorkerHandler.post(new Runnable() { // from class: com.android.launcher3.icons.cache.-$$Lambda$BaseIconCache$yspm7I4ZHVYDBeN018eQavWryvA
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
                return resources.getDrawableForDensity(iconId, this.mIconDpi, resources.newTheme());
            } catch (Resources.NotFoundException unused) {
            }
        }
        return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
    }

    public Drawable getFullResIcon(String packageName, int iconId) {
        try {
            return getFullResIcon(this.mPackageManager.getResourcesForApplication(packageName), iconId);
        } catch (PackageManager.NameNotFoundException unused) {
            return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
        }
    }

    public Drawable getFullResIcon(ActivityInfo info) {
        try {
            return getFullResIcon(this.mPackageManager.getResourcesForApplication(info.applicationInfo), info.getIconResource());
        } catch (PackageManager.NameNotFoundException unused) {
            return BaseIconFactory.getFullResDefaultActivityIcon(this.mIconDpi);
        }
    }

    private BitmapInfo makeDefaultIcon(UserHandle user) {
        BaseIconFactory iconFactory = getIconFactory();
        try {
            BitmapInfo bitmapInfoMakeDefaultIcon = iconFactory.makeDefaultIcon(user);
            if (iconFactory != null) {
                iconFactory.close();
            }
            return bitmapInfoMakeDefaultIcon;
        } catch (Throwable th) {
            if (iconFactory != null) {
                try {
                    iconFactory.close();
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
        HashSet hashSet = new HashSet();
        for (ComponentKey componentKey : this.mCache.keySet()) {
            if (componentKey.componentName.getPackageName().equals(packageName) && componentKey.user.equals(user)) {
                hashSet.add(componentKey);
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            this.mCache.remove((ComponentKey) it.next());
        }
    }

    public synchronized void removeIconsForPkg(String packageName, UserHandle user) {
        removeFromMemCacheLocked(packageName, user);
        this.mIconDb.delete("componentName LIKE ? AND profileId = ?", new String[]{packageName + "/%", Long.toString(getSerialNumberForUser(user))});
    }

    public IconCacheUpdateHandler getUpdateHandler() {
        updateSystemState();
        return new IconCacheUpdateHandler(this);
    }

    private void updateSystemState() {
        this.mSystemState = this.mContext.getResources().getConfiguration().getLocales().toLanguageTags() + SmsManager.REGEX_PREFIX_DELIMITER + Build.VERSION.SDK_INT;
    }

    protected String getIconSystemState(String packageName) {
        return this.mSystemState;
    }

    protected synchronized <T> void addIconToDBAndMemCache(T object, CachingLogic<T> cachingLogic, PackageInfo info, long userSerial, boolean replaceExisting) {
        CacheEntry cacheEntry;
        UserHandle user = cachingLogic.getUser(object);
        ComponentName component = cachingLogic.getComponent(object);
        ComponentKey componentKey = new ComponentKey(component, user);
        CacheEntry cacheEntry2 = null;
        if (!replaceExisting && (cacheEntry = this.mCache.get(componentKey)) != null && cacheEntry.icon != null && !cacheEntry.isLowRes()) {
            cacheEntry2 = cacheEntry;
        }
        if (cacheEntry2 == null) {
            cacheEntry2 = new CacheEntry();
            cachingLogic.loadIcon(this.mContext, object, cacheEntry2);
        }
        cacheEntry2.title = cachingLogic.getLabel(object);
        cacheEntry2.contentDescription = this.mPackageManager.getUserBadgedLabel(cacheEntry2.title, user);
        this.mCache.put(componentKey, cacheEntry2);
        addIconToDB(newContentValues(cacheEntry2, cacheEntry2.title.toString(), component.getPackageName()), component, info, userSerial);
    }

    private void addIconToDB(ContentValues values, ComponentName key, PackageInfo info, long userSerial) {
        values.put(IconDB.COLUMN_COMPONENT, key.flattenToString());
        values.put("profileId", Long.valueOf(userSerial));
        values.put(IconDB.COLUMN_LAST_UPDATED, Long.valueOf(info.lastUpdateTime));
        values.put("version", Long.valueOf(info.getLongVersionCode()));
        this.mIconDb.insertOrReplace(values);
    }

    public synchronized BitmapInfo getDefaultIcon(UserHandle user) {
        if (!this.mDefaultIcons.containsKey(user)) {
            this.mDefaultIcons.put(user, makeDefaultIcon(user));
        }
        return this.mDefaultIcons.get(user);
    }

    public boolean isDefaultIcon(Bitmap icon, UserHandle user) {
        return getDefaultIcon(user).icon == icon;
    }

    protected <T> CacheEntry cacheLocked(ComponentName componentName, UserHandle user, Supplier<T> infoProvider, CachingLogic<T> cachingLogic, boolean usePackageIcon, boolean useLowResIcon) {
        return cacheLocked(componentName, user, infoProvider, cachingLogic, usePackageIcon, useLowResIcon, true);
    }

    protected <T> CacheEntry cacheLocked(ComponentName componentName, UserHandle user, Supplier<T> infoProvider, CachingLogic<T> cachingLogic, boolean usePackageIcon, boolean useLowResIcon, boolean addToMemCache) {
        CacheEntry entryForPackageLocked;
        assertWorkerThread();
        ComponentKey componentKey = new ComponentKey(componentName, user);
        CacheEntry cacheEntry = this.mCache.get(componentKey);
        if (cacheEntry == null || (cacheEntry.isLowRes() && !useLowResIcon)) {
            cacheEntry = new CacheEntry();
            if (addToMemCache) {
                this.mCache.put(componentKey, cacheEntry);
            }
            T t = null;
            boolean entryFromDB = getEntryFromDB(componentKey, cacheEntry, useLowResIcon);
            boolean z = false;
            if (!entryFromDB) {
                t = infoProvider.get();
                if (t != null) {
                    cachingLogic.loadIcon(this.mContext, t, cacheEntry);
                } else {
                    if (usePackageIcon && (entryForPackageLocked = getEntryForPackageLocked(componentName.getPackageName(), user, false)) != null) {
                        entryForPackageLocked.applyTo(cacheEntry);
                        cacheEntry.title = entryForPackageLocked.title;
                        cacheEntry.contentDescription = entryForPackageLocked.contentDescription;
                    }
                    if (cacheEntry.icon == null) {
                        getDefaultIcon(user).applyTo(cacheEntry);
                    }
                }
                z = true;
            }
            if (TextUtils.isEmpty(cacheEntry.title)) {
                if (t == null && !z) {
                    t = infoProvider.get();
                }
                if (t != null) {
                    cacheEntry.title = cachingLogic.getLabel(t);
                    cacheEntry.contentDescription = this.mPackageManager.getUserBadgedLabel(cacheEntry.title, user);
                }
            }
        }
        return cacheEntry;
    }

    public synchronized void clear() {
        assertWorkerThread();
        this.mIconDb.clear();
    }

    public synchronized void cachePackageInstallInfo(String packageName, UserHandle user, Bitmap icon, CharSequence title) {
        removeFromMemCacheLocked(packageName, user);
        ComponentKey packageKey = getPackageKey(packageName, user);
        CacheEntry cacheEntry = this.mCache.get(packageKey);
        if (cacheEntry == null) {
            cacheEntry = new CacheEntry();
        }
        if (!TextUtils.isEmpty(title)) {
            cacheEntry.title = title;
        }
        if (icon != null) {
            BaseIconFactory iconFactory = getIconFactory();
            iconFactory.createIconBitmap(icon).applyTo(cacheEntry);
            iconFactory.close();
        }
        if (!TextUtils.isEmpty(title) && cacheEntry.icon != null) {
            this.mCache.put(packageKey, cacheEntry);
        }
    }

    private static ComponentKey getPackageKey(String packageName, UserHandle user) {
        return new ComponentKey(new ComponentName(packageName, packageName + "."), user);
    }

    protected CacheEntry getEntryForPackageLocked(String packageName, UserHandle user, boolean useLowResIcon) {
        assertWorkerThread();
        ComponentKey packageKey = getPackageKey(packageName, user);
        CacheEntry cacheEntry = this.mCache.get(packageKey);
        if (cacheEntry == null || (cacheEntry.isLowRes() && !useLowResIcon)) {
            cacheEntry = new CacheEntry();
            boolean z = true;
            if (!getEntryFromDB(packageKey, cacheEntry, useLowResIcon)) {
                try {
                    PackageInfo packageInfo = this.mPackageManager.getPackageInfo(packageName, Process.myUserHandle().equals(user) ? 0 : 8192);
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if (applicationInfo == null) {
                        throw new PackageManager.NameNotFoundException("ApplicationInfo is null");
                    }
                    BaseIconFactory iconFactory = getIconFactory();
                    BitmapInfo bitmapInfoCreateBadgedIconBitmap = iconFactory.createBadgedIconBitmap(applicationInfo.loadIcon(this.mPackageManager), user, applicationInfo.targetSdkVersion, isInstantApp(applicationInfo));
                    iconFactory.close();
                    cacheEntry.title = applicationInfo.loadLabel(this.mPackageManager);
                    cacheEntry.contentDescription = this.mPackageManager.getUserBadgedLabel(cacheEntry.title, user);
                    cacheEntry.icon = useLowResIcon ? BitmapInfo.LOW_RES_ICON : bitmapInfoCreateBadgedIconBitmap.icon;
                    cacheEntry.color = bitmapInfoCreateBadgedIconBitmap.color;
                    addIconToDB(newContentValues(bitmapInfoCreateBadgedIconBitmap, cacheEntry.title.toString(), packageName), packageKey.componentName, packageInfo, getSerialNumberForUser(user));
                } catch (PackageManager.NameNotFoundException unused) {
                    z = false;
                }
            }
            if (z) {
                this.mCache.put(packageKey, cacheEntry);
            }
        }
        return cacheEntry;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0081 A[PHI: r2
  0x0081: PHI (r2v1 android.database.Cursor) = (r2v0 android.database.Cursor), (r2v2 android.database.Cursor) binds: [B:26:0x007f, B:20:0x0072] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean getEntryFromDB(com.android.launcher3.util.ComponentKey r11, com.android.launcher3.icons.cache.BaseIconCache.CacheEntry r12, boolean r13) {
        /*
            r10 = this;
            java.lang.String r0 = ""
            r1 = 0
            r2 = 0
            com.android.launcher3.icons.cache.BaseIconCache$IconDB r3 = r10.mIconDb     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            if (r13 == 0) goto Lb
            java.lang.String[] r4 = com.android.launcher3.icons.cache.BaseIconCache.IconDB.COLUMNS_LOW_RES     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            goto Ld
        Lb:
            java.lang.String[] r4 = com.android.launcher3.icons.cache.BaseIconCache.IconDB.COLUMNS_HIGH_RES     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
        Ld:
            java.lang.String r5 = "componentName = ? AND profileId = ?"
            r6 = 2
            java.lang.String[] r7 = new java.lang.String[r6]     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.content.ComponentName r8 = r11.componentName     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r8 = r8.flattenToString()     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r7[r1] = r8     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.os.UserHandle r8 = r11.user     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            long r8 = r10.getSerialNumberForUser(r8)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r8 = java.lang.Long.toString(r8)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r9 = 1
            r7[r9] = r8     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.database.Cursor r2 = r3.query(r4, r5, r7)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            if (r3 == 0) goto L72
            int r3 = r2.getInt(r1)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r4 = 255(0xff, float:3.57E-43)
            int r3 = com.android.launcher3.icons.GraphicsUtils.setColorAlphaBound(r3, r4)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r12.color = r3     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.String r3 = r2.getString(r9)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r12.title = r3     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.CharSequence r3 = r12.title     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            if (r3 != 0) goto L4c
            r12.title = r0     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r12.contentDescription = r0     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            goto L58
        L4c:
            android.content.pm.PackageManager r0 = r10.mPackageManager     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.CharSequence r3 = r12.title     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.os.UserHandle r11 = r11.user     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            java.lang.CharSequence r11 = r0.getUserBadgedLabel(r3, r11)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r12.contentDescription = r11     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
        L58:
            if (r13 == 0) goto L5f
            android.graphics.Bitmap r10 = com.android.launcher3.icons.BitmapInfo.LOW_RES_ICON     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r12.icon = r10     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            goto L6c
        L5f:
            byte[] r11 = r2.getBlob(r6)     // Catch: java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            int r13 = r11.length     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.graphics.BitmapFactory$Options r10 = r10.mDecodeOptions     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeByteArray(r11, r1, r13, r10)     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
            r12.icon = r10     // Catch: java.lang.Exception -> L6c java.lang.Throwable -> L75 android.database.sqlite.SQLiteException -> L77
        L6c:
            if (r2 == 0) goto L71
            r2.close()
        L71:
            return r9
        L72:
            if (r2 == 0) goto L84
            goto L81
        L75:
            r10 = move-exception
            goto L85
        L77:
            r10 = move-exception
            java.lang.String r11 = "BaseIconCache"
            java.lang.String r12 = "Error reading icon cache"
            android.util.Log.d(r11, r12, r10)     // Catch: java.lang.Throwable -> L75
            if (r2 == 0) goto L84
        L81:
            r2.close()
        L84:
            return r1
        L85:
            if (r2 == 0) goto L8a
            r2.close()
        L8a:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.cache.BaseIconCache.getEntryFromDB(com.android.launcher3.util.ComponentKey, com.android.launcher3.icons.cache.BaseIconCache$CacheEntry, boolean):boolean");
    }

    static final class IconDB extends SQLiteCacheHelper {
        public static final String COLUMN_COMPONENT = "componentName";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_LABEL = "label";
        public static final String COLUMN_LAST_UPDATED = "lastUpdated";
        public static final String COLUMN_ROWID = "rowid";
        public static final String COLUMN_SYSTEM_STATE = "system_state";
        public static final String COLUMN_USER = "profileId";
        public static final String COLUMN_VERSION = "version";
        private static final int RELEASE_VERSION = 26;
        public static final String TABLE_NAME = "icons";
        public static final String COLUMN_ICON_COLOR = "icon_color";
        public static final String[] COLUMNS_HIGH_RES = {COLUMN_ICON_COLOR, "label", "icon"};
        public static final String[] COLUMNS_LOW_RES = {COLUMN_ICON_COLOR, "label"};

        public IconDB(Context context, String dbFileName, int iconPixelSize) {
            super(context, dbFileName, iconPixelSize + 1703936, TABLE_NAME);
        }

        @Override // com.android.launcher3.util.SQLiteCacheHelper
        protected void onCreateTable(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS icons (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, icon BLOB, icon_color INTEGER NOT NULL DEFAULT 0, label TEXT, system_state TEXT, PRIMARY KEY (componentName, profileId) );");
        }
    }

    private ContentValues newContentValues(BitmapInfo bitmapInfo, String label, String packageName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("icon", bitmapInfo.isLowRes() ? null : GraphicsUtils.flattenBitmap(bitmapInfo.icon));
        contentValues.put(IconDB.COLUMN_ICON_COLOR, Integer.valueOf(bitmapInfo.color));
        contentValues.put("label", label);
        contentValues.put(IconDB.COLUMN_SYSTEM_STATE, getIconSystemState(packageName));
        return contentValues;
    }

    private void assertWorkerThread() {
        if (Looper.myLooper() != this.mBgLooper) {
            throw new IllegalStateException("Cache accessed on wrong thread " + Looper.myLooper());
        }
    }
}
