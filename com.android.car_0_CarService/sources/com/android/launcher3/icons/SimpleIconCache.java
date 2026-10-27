package com.android.launcher3.icons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.SparseLongArray;
import com.android.launcher3.icons.cache.BaseIconCache;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleIconCache extends BaseIconCache {
    private final UserManager mUserManager;
    private final SparseLongArray mUserSerialMap;
    private static SimpleIconCache sIconCache = null;
    private static final Object CACHE_LOCK = new Object();

    public SimpleIconCache(Context context, String dbFileName, Looper bgLooper, int iconDpi, int iconPixelSize, boolean inMemoryCache) {
        super(context, dbFileName, bgLooper, iconDpi, iconPixelSize, inMemoryCache);
        this.mUserSerialMap = new SparseLongArray(2);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        IntentFilter filter = new IntentFilter("android.intent.action.MANAGED_PROFILE_ADDED");
        filter.addAction("android.intent.action.MANAGED_PROFILE_REMOVED");
        context.registerReceiver(new BroadcastReceiver() { // from class: com.android.launcher3.icons.SimpleIconCache.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                SimpleIconCache.this.resetUserCache();
            }
        }, filter, null, new Handler(bgLooper), 0);
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected long getSerialNumberForUser(UserHandle user) {
        synchronized (this.mUserSerialMap) {
            int index = this.mUserSerialMap.indexOfKey(user.getIdentifier());
            if (index >= 0) {
                return this.mUserSerialMap.valueAt(index);
            }
            long serial = this.mUserManager.getSerialNumberForUser(user);
            this.mUserSerialMap.put(user.getIdentifier(), serial);
            return serial;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetUserCache() {
        synchronized (this.mUserSerialMap) {
            this.mUserSerialMap.clear();
        }
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected boolean isInstantApp(ApplicationInfo info) {
        return info.isInstantApp();
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected BaseIconFactory getIconFactory() {
        return IconFactory.obtain(this.mContext);
    }

    public static SimpleIconCache getIconCache(Context context) {
        synchronized (CACHE_LOCK) {
            if (sIconCache != null) {
                return sIconCache;
            }
            boolean inMemoryCache = context.getResources().getBoolean(R.bool.simple_cache_enable_im_memory);
            String dbFileName = context.getString(R.string.cache_db_name);
            HandlerThread bgThread = new HandlerThread("simple-icon-cache");
            bgThread.start();
            SimpleIconCache simpleIconCache = new SimpleIconCache(context.getApplicationContext(), dbFileName, bgThread.getLooper(), context.getResources().getConfiguration().densityDpi, context.getResources().getDimensionPixelSize(R.dimen.default_icon_bitmap_size), inMemoryCache);
            sIconCache = simpleIconCache;
            return simpleIconCache;
        }
    }
}
