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

/* JADX INFO: loaded from: classes4.dex */
public class SimpleIconCache extends BaseIconCache {
    private static final Object CACHE_LOCK = new Object();
    private static SimpleIconCache sIconCache;
    private final UserManager mUserManager;
    private final SparseLongArray mUserSerialMap;

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected boolean isInstantApp(ApplicationInfo info) {
        return false;
    }

    public SimpleIconCache(Context context, String dbFileName, Looper bgLooper, int iconDpi, int iconPixelSize, boolean inMemoryCache) {
        super(context, dbFileName, bgLooper, iconDpi, iconPixelSize, inMemoryCache);
        this.mUserSerialMap = new SparseLongArray(2);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MANAGED_PROFILE_ADDED);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_REMOVED);
        context.registerReceiver(new BroadcastReceiver() { // from class: com.android.launcher3.icons.SimpleIconCache.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                SimpleIconCache.this.resetUserCache();
            }
        }, intentFilter, null, new Handler(bgLooper), 0);
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected long getSerialNumberForUser(UserHandle user) {
        long jValueAt;
        synchronized (this.mUserSerialMap) {
            jValueAt = this.mUserSerialMap.valueAt(0);
        }
        return jValueAt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetUserCache() {
        synchronized (this.mUserSerialMap) {
            this.mUserSerialMap.clear();
        }
    }

    @Override // com.android.launcher3.icons.cache.BaseIconCache
    protected BaseIconFactory getIconFactory() {
        return IconFactory.obtain(this.mContext);
    }

    public static SimpleIconCache getIconCache(Context context) {
        synchronized (CACHE_LOCK) {
            SimpleIconCache simpleIconCache = sIconCache;
            if (simpleIconCache != null) {
                return simpleIconCache;
            }
            boolean z = context.getResources().getBoolean(R.bool.simple_cache_enable_im_memory);
            String string = context.getString(R.string.cache_db_name);
            HandlerThread handlerThread = new HandlerThread("simple-icon-cache");
            handlerThread.start();
            SimpleIconCache simpleIconCache2 = new SimpleIconCache(context.getApplicationContext(), string, handlerThread.getLooper(), context.getResources().getConfiguration().densityDpi, context.getResources().getDimensionPixelSize(R.dimen.default_icon_bitmap_size), z);
            sIconCache = simpleIconCache2;
            return simpleIconCache2;
        }
    }
}
